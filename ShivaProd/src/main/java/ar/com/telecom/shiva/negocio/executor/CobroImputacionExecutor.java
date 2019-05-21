package ar.com.telecom.shiva.negocio.executor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.EstadoProcesamientoHilosEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCobroHiloEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlMemoriaCPU;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.cobros.imputacion.ResultadoImputacionCobro;
import ar.com.telecom.shiva.negocio.executor.callable.ImputacionCobroCallable;
import ar.com.telecom.shiva.negocio.executor.callable.ImputacionCobroManualCallable;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ImputacionCobroRto;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionManualServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IProcesamientoHilosCobrosServicio;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaCantidadHilosEnCurso;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaCobrosPendientes;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobProcHilosCobros;

public class CobroImputacionExecutor {

	private List<ResultadoBusquedaCobrosPendientes> listaCobrosPendientes = new ArrayList<ResultadoBusquedaCobrosPendientes>() ;
	private int limiteDeTransaccionesCobrosChicos;
	private int cantidadTransaccionesAProcesarPorHilo;
	
	/**
	 * Arranco la ejecucion de diferentes imputaciones de cobros
	 * @throws PersistenciaExcepcion 
	 * @throws InterruptedException 
	 */
	public void ejecutarImputacionesCobros() throws NegocioExcepcion, PersistenciaExcepcion, InterruptedException {
		double startTime = System.nanoTime();
		
		String usuarioBatch = "";
		
		List <ShvCobProcHilosCobros> listaCobrosGrandesPendientes = new ArrayList<ShvCobProcHilosCobros>();
		List <ShvCobProcHilosCobros> listaCobrosChicosPendientes = new ArrayList<ShvCobProcHilosCobros>();
		Integer cantHilosCobrosChicosDisponibles = 0;
		Integer cantHilosCobrosGrandesDisponibles = 0;
		SiNoEnum procesarHilosChicosEnHilosGrandes = null;
		
		IParametroServicio parametroServicio = (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
		IProcesamientoHilosCobrosServicio procesamientoHilosCobrosServicio = (IProcesamientoHilosCobrosServicio) Configuracion.getBeanBatch("procesamientoHilosCobrosServicio");
		
		List<ResultadoBusquedaCantidadHilosEnCurso> listaCantHilosEnCurso = procesamientoHilosCobrosServicio.obtenerCantidadHilosEnCurso(EstadoProcesamientoHilosEnum.EN_CURSO,TipoImputacionEnum.AUTOMATICA);
		
		this.limiteDeTransaccionesCobrosChicos = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.IMPUTACION_COBROS_CANT_TRANSACCIONES_COBROS_CHICOS).toString()); 
		this.cantidadTransaccionesAProcesarPorHilo = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.IMPUTACION_COBROS_CANT_TRANSACCIONES_PROCESAR_HILO).toString());
		
		cantHilosCobrosChicosDisponibles = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.IMPUTACION_COBROS_CANT_HILOS_COBROS_CHICOS).toString());
		cantHilosCobrosGrandesDisponibles = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.IMPUTACION_COBROS_CANT_HILOS_COBROS_GRANDES).toString());
		procesarHilosChicosEnHilosGrandes = SiNoEnum.getEnumByDescripcionCorta(parametroServicio.getValorTexto(Constantes.IMPUTACION_COBROS_PROCESAR_COBROS_CHICOS_EN_HILOS_COBROS_GRANDES).toString());
		
		for (ResultadoBusquedaCantidadHilosEnCurso resultado : listaCantHilosEnCurso) {

			if (TipoCobroHiloEnum.GRANDE.equals(resultado.getTipoHilo())){
				cantHilosCobrosGrandesDisponibles = cantHilosCobrosGrandesDisponibles - resultado.getCantidadHilosEnCurso();
				
			} else if(TipoCobroHiloEnum.CHICO.equals(resultado.getTipoHilo())) {
				cantHilosCobrosChicosDisponibles = cantHilosCobrosChicosDisponibles - resultado.getCantidadHilosEnCurso();
				
			}
			
		}

		if (cantHilosCobrosGrandesDisponibles <= 0 && cantHilosCobrosChicosDisponibles <= 0){
			
			Traza.auditoria(this.getClass(), "No va a procesar ninguna imputacion de cobros porque no hay hilos disponibles.");
			Thread.sleep(5000);
			return;
		}
		
		Traza.auditoria(this.getClass(), "-- Se ha iniciado el proceso de Imputacion de Cobros");
		
		ICobroImputacionServicio cobroServicio = (ICobroImputacionServicio) Configuracion.getBeanBatch("cobroServicio");
		
		// Obtengo las id de operaciones de los cobros en estado Pendiente o en estado "cobro en proceso".
		listaCobrosPendientes = cobroServicio.listarCobrosPendientesImputacionManual(TipoImputacionEnum.AUTOMATICA);
		
		Traza.auditoria(this.getClass(), 
				Utilidad.reemplazarMensajes("Se ha encontrado {0} cobros pendientes / cobro en proceso", 
						String.valueOf(listaCobrosPendientes.size())));
		
		if (!this.listaCobrosPendientes.isEmpty()) {
			Traza.auditoria(this.getClass(), "Se ha comenzado el proceso de imputacion de diferentes operaciones en forma paralela - Lista de Operaciones: ");
			
			
			
			for (ResultadoBusquedaCobrosPendientes resultCobroPendiente : listaCobrosPendientes) {
				
				ShvCobProcHilosCobros cobroPendiente = new ShvCobProcHilosCobros();
				
				cobroPendiente.setIdCobro(resultCobroPendiente.getIdCobro());
				cobroPendiente.setIdOperacion(resultCobroPendiente.getIdOperacion());
				cobroPendiente.setCantTransacciones(resultCobroPendiente.getCantidadTransacciones());
				
				cobroPendiente.setContieneAplicacionManual(resultCobroPendiente.getContieneAplicacionManual());
				
				String datosCobroPendiente= "-ID COBRO: " + cobroPendiente.getIdCobro() + " - ID OPERACION: " + cobroPendiente.getIdOperacion() +" - ";
				datosCobroPendiente += "ESTADO: " + resultCobroPendiente.getEstadoCobro();
				datosCobroPendiente += " ULTIMA FECHA DE PROCESAMIENTO: " + resultCobroPendiente.getFechaUltimoProcesamiento();
				datosCobroPendiente += " - CANTIDAD DE TRANSACCIONES DEL COBRO: " + cobroPendiente.getCantTransacciones();
				
				//Pregunto por la cantidad de transacciones que tiene el cobro, y lo clasifico en grande o chico.
				if (cobroPendiente.getCantTransacciones() <= limiteDeTransaccionesCobrosChicos){
					
					//Es un cobro chico, entonces lo agrego a la lista de cobros chicos
					cobroPendiente.setTipoCobroCantTransacciones(TipoCobroHiloEnum.CHICO);
					listaCobrosChicosPendientes.add(cobroPendiente);
					datosCobroPendiente += " Se agrega a la lista de cobros chicos a procesar.";

				
				} else if (cobroPendiente.getCantTransacciones() >= limiteDeTransaccionesCobrosChicos){
				
					//Es un cobro grande, entonces lo agrego a la lista de cobros grandes
					cobroPendiente.setTipoCobroCantTransacciones(TipoCobroHiloEnum.GRANDE);
					listaCobrosGrandesPendientes.add(cobroPendiente);
					datosCobroPendiente += " Se agrega a la lista de cobros grandes a procesar.";
				}
				
				Traza.auditoria(this.getClass(), datosCobroPendiente);
				
			}
			
			Traza.auditoria(this.getClass(), "TOTAL COBROS GRANDES A PROCESAR: " + listaCobrosGrandesPendientes.size());
			Traza.auditoria(this.getClass(), "TOTAL COBROS CHICOS A PROCESAR: " + listaCobrosChicosPendientes.size());
			
		} else { 
			Traza.auditoria(this.getClass(), "No va a procesar ninguna imputacion de cobros por no encontrarse ninguna operacion pendiente o en proceso");
			return;
		}
		
		try {
			
			usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			
			ExecutorService poolImputacionCobro = null;
			List<Future<ResultadoImputacionCobro>> listaCallablesEnEjecucion1 = new ArrayList<Future<ResultadoImputacionCobro>>();
			
			int cantidadHilos = 0;
			
			//Pool de Imputacion Cobros
			poolImputacionCobro = Executors.newFixedThreadPool(cantHilosCobrosChicosDisponibles + cantHilosCobrosGrandesDisponibles);
			
			while (cantHilosCobrosChicosDisponibles > 0 && !listaCobrosChicosPendientes.isEmpty()){
				
				ShvCobProcHilosCobros cobroChicoPendiente = listaCobrosChicosPendientes.get(0);
				cobroChicoPendiente.setTipoHiloCantTransacciones(TipoCobroHiloEnum.CHICO);
				iniciarHilo(cobroChicoPendiente, listaCallablesEnEjecucion1, poolImputacionCobro, cantidadHilos);
				cantidadHilos++;
				cantHilosCobrosChicosDisponibles--;
				listaCobrosChicosPendientes.remove(0);
			}
			
			while (cantHilosCobrosGrandesDisponibles > 0 && !listaCobrosGrandesPendientes.isEmpty()){
				ShvCobProcHilosCobros cobroGrandePendiente = listaCobrosGrandesPendientes.get(0);
				cobroGrandePendiente.setTipoHiloCantTransacciones(TipoCobroHiloEnum.GRANDE);
				iniciarHilo(listaCobrosGrandesPendientes.get(0), listaCallablesEnEjecucion1, poolImputacionCobro, cantidadHilos);
				cantidadHilos++;
				cantHilosCobrosGrandesDisponibles--;
				listaCobrosGrandesPendientes.remove(0);
			}
			
			if (SiNoEnum.SI.equals(procesarHilosChicosEnHilosGrandes)){
				while (cantHilosCobrosGrandesDisponibles > 0 && !listaCobrosChicosPendientes.isEmpty()){
					
					ShvCobProcHilosCobros cobroChicoPendiente = listaCobrosChicosPendientes.get(0);
					cobroChicoPendiente.setTipoHiloCantTransacciones(TipoCobroHiloEnum.GRANDE);
					iniciarHilo(cobroChicoPendiente, listaCallablesEnEjecucion1, poolImputacionCobro, cantidadHilos);
					cantidadHilos++;
					cantHilosCobrosGrandesDisponibles--;
					listaCobrosChicosPendientes.remove(0);
				}
			}

			Thread.sleep(5000);
//			int cantidadHilosEjecutados = 0;
//			if (i == 1){
//				if (poolImputacionCobro!=null) {
					//if (poolImputacionCobro.awaitTermination(1, TimeUnit.MINUTES)) {
//						cantidadHilosEjecutados += this.armarListaResultadoImputacionCobro(listaCallablesEnEjecucion1);
//						poolImputacionCobro.shutdown();
					//} else {
					//	Notificar al usuario de la expiracion
					//}
//				}
//			}
			
//			Traza.auditoria(this.getClass(), "------ Se han ejecutado " + cantidadHilosEjecutados + " hilos de los " + cantidadHilos);
			
		} catch (Throwable e) {
			Traza.error(this.getClass(), e.getMessage(), e);
			throw new NegocioExcepcion(e.getMessage(), e);
		
		} finally {
			
			Traza.auditoria(this.getClass(), "Se ha finalizado el proceso de imputaciones de cobros");
			
			double stopTime = System.nanoTime();
			double elapsedTime = (stopTime - startTime) / 1000000000.0;
			String detalle = "Resultado: " + new DecimalFormat("#.########").format(elapsedTime);
			
			Traza.tiempo(this.getClass(), usuarioBatch, "ejecutarImputacionesCobros", detalle);
		}		
	}
	
	/**
	 * Arranco la ejecucion de diferentes imputaciones manuales de cobros 
	 * @throws PersistenciaExcepcion 
	 * @throws InterruptedException 
	 */
	public void ejecutarImputacionesManualesCobros() throws NegocioExcepcion, PersistenciaExcepcion, InterruptedException {
		double startTime = System.nanoTime();
		
		String usuarioBatch = "";
		
		List <ShvCobProcHilosCobros> listaCobros = new ArrayList<ShvCobProcHilosCobros>();
		Integer cantHilosDisponibles = 0;//PARAMETRICA
		
		
		IParametroServicio parametroServicio = (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
		IProcesamientoHilosCobrosServicio procesamientoHilosCobrosServicio = (IProcesamientoHilosCobrosServicio) Configuracion.getBeanBatch("procesamientoHilosCobrosServicio");
		
		List<ResultadoBusquedaCantidadHilosEnCurso> listaCantHilosEnCurso = procesamientoHilosCobrosServicio.obtenerCantidadHilosEnCurso(EstadoProcesamientoHilosEnum.EN_CURSO,TipoImputacionEnum.MANUAL);
		
		cantHilosDisponibles = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.IMPUTACION_MANUAL_COBROS_CANT_HILOS).toString());
		this.cantidadTransaccionesAProcesarPorHilo = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.IMPUTACION_COBROS_CANT_TRANSACCIONES_PROCESAR_HILO).toString());
		
		Integer cantidadHilosEnCurso = 0;
		
		for (ResultadoBusquedaCantidadHilosEnCurso resultado : listaCantHilosEnCurso) {
				cantidadHilosEnCurso = resultado.getCantidadHilosEnCurso();
		}
		
		cantHilosDisponibles = cantHilosDisponibles - cantidadHilosEnCurso;
		
		if (cantHilosDisponibles <= 0 ){
			
			Traza.auditoria(this.getClass(), "No va a procesar la imputacion de cobros manual porque no hay hilos disponibles.");
			Thread.sleep(5000);
			return;
		}
		
		
		
		Traza.auditoria(this.getClass(), "-- Se ha iniciado el proceso de Imputacion Manual de Cobros");
		
		ICobroImputacionManualServicio cobroServicio = (ICobroImputacionManualServicio) Configuracion.getBeanBatch("cobroManualServicio");
		
		// Obtengo las id de operaciones de los cobros en estado Pendiente o en estado "cobro en proceso".
		listaCobrosPendientes = cobroServicio.listarCobrosPendientesImputacionManual(TipoImputacionEnum.MANUAL);
		
		Traza.auditoria(this.getClass(), 
				Utilidad.reemplazarMensajes("Se ha encontrado {0} cobros pendientes imputacion manual", 
						String.valueOf(listaCobrosPendientes.size())));
		
		if (!this.listaCobrosPendientes.isEmpty()) {
			Traza.auditoria(this.getClass(), "Se ha comenzado el proceso de imputacion de diferentes operaciones en forma paralela - Lista de Operaciones: ");
			
			for (ResultadoBusquedaCobrosPendientes resultCobroPendiente : listaCobrosPendientes) {
				
				ShvCobProcHilosCobros cobroPendiente = new ShvCobProcHilosCobros();
				
				cobroPendiente.setIdCobro(resultCobroPendiente.getIdCobro());
				cobroPendiente.setIdOperacion(resultCobroPendiente.getIdOperacion());
				cobroPendiente.setCantTransacciones(resultCobroPendiente.getCantidadTransacciones());
				cobroPendiente.setEstadoCobro(resultCobroPendiente.getEstadoCobro());
				
				String datosCobroPendiente= "-ID COBRO: " + cobroPendiente.getIdCobro() + " - ID OPERACION: " + cobroPendiente.getIdOperacion() +" - ";
				datosCobroPendiente += "ESTADO: " + resultCobroPendiente.getEstadoCobro();
				datosCobroPendiente += " ULTIMA FECHA DE PROCESAMIENTO: " + resultCobroPendiente.getFechaUltimoProcesamiento();
				datosCobroPendiente += " - CANTIDAD DE TRANSACCIONES DEL COBRO: " + cobroPendiente.getCantTransacciones();
				
				listaCobros.add(cobroPendiente);
				Traza.auditoria(this.getClass(), datosCobroPendiente);
			}
			
		} else { 
			Traza.auditoria(this.getClass(), "No va a procesar ninguna imputacion de cobros por no encontrarse ninguna operacion pendiente o en proceso");
			return;
		}
		
		try {
			
			usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			
			ExecutorService poolImputacionCobro = null;
			List<Future<ResultadoImputacionCobro>> listaCallablesEnEjecucion1 = new ArrayList<Future<ResultadoImputacionCobro>>();
			
			int cantidadHilos = 0;
			
			//Pool de Imputacion Cobros
			poolImputacionCobro = Executors.newFixedThreadPool(cantHilosDisponibles);
			
			while (cantHilosDisponibles > 0 && !listaCobros.isEmpty()){
				
				ShvCobProcHilosCobros cobroChicoPendiente = listaCobros.get(0);
				iniciarHiloImputacionManual(cobroChicoPendiente, listaCallablesEnEjecucion1, poolImputacionCobro, cantidadHilos);
				cantidadHilos++;
				cantHilosDisponibles--;
				listaCobros.remove(0);
			}
			
			Thread.sleep(5000);
//			int cantidadHilosEjecutados = 0;
//			if (i == 1){
//				if (poolImputacionCobro!=null) {
					//if (poolImputacionCobro.awaitTermination(1, TimeUnit.MINUTES)) {
//						cantidadHilosEjecutados += this.armarListaResultadoImputacionCobro(listaCallablesEnEjecucion1);
//						poolImputacionCobro.shutdown();
					//} else {
					//	Notificar al usuario de la expiracion
					//}
//				}
//			}
			
//			Traza.auditoria(this.getClass(), "------ Se han ejecutado " + cantidadHilosEjecutados + " hilos de los " + cantidadHilos);
			
		} catch (Throwable e) {
			Traza.error(this.getClass(), e.getMessage(), e);
			throw new NegocioExcepcion(e.getMessage(), e);
		
		} finally {
			
			Traza.auditoria(this.getClass(), "Se ha finalizado el proceso de imputaciones de cobros");
			
			double stopTime = System.nanoTime();
			double elapsedTime = (stopTime - startTime) / 1000000000.0;
			String detalle = "Resultado: " + new DecimalFormat("#.########").format(elapsedTime);
			
			Traza.tiempo(this.getClass(), usuarioBatch, "ejecutarImputacionesCobros", detalle);
		}		
	}
	
	@SuppressWarnings("unused")
	private int armarListaResultadoImputacionCobro(
			List<Future<ResultadoImputacionCobro>> listaCallablesEnEjecucion) throws NegocioExcepcion {
		
		int cantidadHilosEjecutados = 0;
		for (Future<ResultadoImputacionCobro> future : listaCallablesEnEjecucion) {
			try { 
				ResultadoImputacionCobro resultado = future.get(30, TimeUnit.MINUTES);
				if (resultado.getResultado().booleanValue()) {
					Traza.auditoria(getClass(), "Finalizado Exitosamente --> Hilo nro:" + resultado.getNumHilo() 
							+" - Id_Operacion("+ Utilidad.rellenarCerosIzquierda(resultado.getIdOperacion(), 7) +")) "
							+ "--> " + ControlMemoriaCPU.getInformacionMemoria());
				} else {
					Traza.auditoria(getClass(), "Finalizado con ERROR --> Hilo nro:" + resultado.getNumHilo() 
							+" - Id_Operacion("+ Utilidad.rellenarCerosIzquierda(resultado.getIdOperacion(), 7) +")) "
							+ "--> " + ControlMemoriaCPU.getInformacionMemoria());
				}
				cantidadHilosEjecutados++;
				
			} catch (InterruptedException | ExecutionException ignorable) { 
				Traza.advertencia(this.getClass(), "------ Hubo una interrupcion en un proceso thread", ignorable);
				
				throw new NegocioExcepcion(ignorable.getMessage(), ignorable);
				
			} catch (TimeoutException timeout) { 
				Traza.advertencia(this.getClass(), "------ Hubo un timeout en un proceso thread", timeout);
				
				throw new NegocioExcepcion(timeout.getMessage(), timeout);
	        }
		}
		return cantidadHilosEjecutados;
	}


	private void iniciarHilo(ShvCobProcHilosCobros cobroAProcesar, List<Future<ResultadoImputacionCobro>> listaCallablesEnEjecucion1,
			ExecutorService poolImputacionCobro, int cantidadHilos) throws NegocioExcepcion {
		
		// U578936 M. A. Uehara. Comento el if.
//		if (cobroAProcesar.getIdOperacion().equals(new Long("8250884"))) {
			
			//Creo un nuevo registro en la base, guardo el estado del hilo actual (EN CURSO), y seteo los datos que tengo del cobro
			IProcesamientoHilosCobrosServicio procesamientoHilosCobrosServicio = (IProcesamientoHilosCobrosServicio) Configuracion.getBeanBatch("procesamientoHilosCobrosServicio");
			
			cobroAProcesar.setFechaInicio(new Date());
			cobroAProcesar.setEstado(EstadoProcesamientoHilosEnum.EN_CURSO);
			cobroAProcesar.setTipoImputacion(TipoImputacionEnum.AUTOMATICA);
			
			try {
				procesamientoHilosCobrosServicio.insertarActualizarHilo(cobroAProcesar);
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(),e);
			}
			
			ImputacionCobroRto rto = new ImputacionCobroRto(cobroAProcesar, cantidadHilos, cantidadTransaccionesAProcesarPorHilo, true);
			ImputacionCobroCallable tarea = new ImputacionCobroCallable(rto); 
			listaCallablesEnEjecucion1.add(poolImputacionCobro.submit(tarea));
			
			Traza.auditoria(getClass(), "Hilo nro: "+ cantidadHilos +" (Id Operacion {"+ Utilidad.rellenarCerosIzquierda(cobroAProcesar.getIdOperacion().toString(), 7) +"}) creado "
					  + "--> " + ControlMemoriaCPU.getInformacionMemoria());
//		}
	}
	
	private void iniciarHiloImputacionManual(ShvCobProcHilosCobros cobroAProcesar, List<Future<ResultadoImputacionCobro>> listaCallablesEnEjecucion1,
			ExecutorService poolImputacionCobro, int cantidadHilos) throws NegocioExcepcion {
		
		
		// U578936 M. A. Uehara. Comento el if.
//		if (cobroAProcesar.getIdOperacion().equals(new Long("8250884"))) {
		
			//TODO: GUARDAR HILO EN LA BASE
			//Creo un nuevo registro en la base, guardo el estado del hilo actual (EN CURSO), y seteo los datos que tengo del cobro
			IProcesamientoHilosCobrosServicio procesamientoHilosCobrosServicio = (IProcesamientoHilosCobrosServicio) Configuracion.getBeanBatch("procesamientoHilosCobrosServicio");
			
			cobroAProcesar.setFechaInicio(new Date());
			cobroAProcesar.setEstado(EstadoProcesamientoHilosEnum.EN_CURSO);
			cobroAProcesar.setTipoImputacion(TipoImputacionEnum.MANUAL);
			
			try {
				procesamientoHilosCobrosServicio.insertarActualizarHilo(cobroAProcesar);
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(),e);
			}
			ImputacionCobroRto rto = new ImputacionCobroRto(cobroAProcesar, cantidadHilos, cantidadTransaccionesAProcesarPorHilo, true);
			ImputacionCobroManualCallable tarea = new ImputacionCobroManualCallable(rto); 
			listaCallablesEnEjecucion1.add(poolImputacionCobro.submit(tarea));
			
			Traza.auditoria(getClass(), "Hilo nro: "+ cantidadHilos +" (Id Operacion {"+ Utilidad.rellenarCerosIzquierda(cobroAProcesar.getIdOperacion().toString(), 7) +"}) creado "
					  + "--> " + ControlMemoriaCPU.getInformacionMemoria());
//		}

	}
}
