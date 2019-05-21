package ar.com.telecom.shiva.negocio.executor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacion;
import ar.com.telecom.shiva.negocio.executor.callable.ApropiacionCalipsoCallable;
import ar.com.telecom.shiva.negocio.executor.callable.ApropiacionDeimosCallable;
import ar.com.telecom.shiva.negocio.executor.callable.ApropiacionMicCallable;
import ar.com.telecom.shiva.negocio.executor.callable.ConsultarProveedorSapCallable;
import ar.com.telecom.shiva.negocio.executor.callable.GeneracionCargoCalipsoCallable;
import ar.com.telecom.shiva.negocio.executor.callable.GeneracionCargoMicCallable;
import ar.com.telecom.shiva.negocio.executor.callable.VerificarPartidasSapCallable;
import ar.com.telecom.shiva.negocio.executor.callable.resultado.IListaResultadoSimulacionCallable;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ApropiacionCalipsoRto;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ApropiacionDeimosRto;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ApropiacionMicRto;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ConsultarProveedoresSapRto;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.GeneracionCargoCalipsoRto;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.GeneracionCargoMicRto;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.VerificarPartidasSapRto;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class CobroSimulacionExecutor {

	private String usuario;
	private String idOperacion;
	private List<ApropiacionCalipsoRto> listaApropiacionCalipsoRto;
	private List<ApropiacionMicRto> listaApropiacionMicRto;
	private List<GeneracionCargoCalipsoRto> listaGeneracionCargoCalipsoRto;
	private List<GeneracionCargoMicRto> listaGeneracionCargoMicRto;
	private List<ApropiacionDeimosRto> listaApropiacionDeimosRto;
	private List<VerificarPartidasSapRto> listaVerificarPartidasSapRto;
	private List<ConsultarProveedoresSapRto> listaConsultarProveedoresSapRto;
	
	private int cantidadConcurrenciasApropiacionCalipso;
	private int cantidadConcurrenciasApropiacionMic;
	private int cantidadConcurrenciasGeneracionCargoCalipso;
	private int cantidadConcurrenciasGeneracionCargoMic;
	private int cantidadConcurrenciasApropiacionDeimos;
	private int cantidadConcurrenciasVerificarPartidasSap;
	private int cantidadConcurrenciasConsultarProveedoresSap;
	/**
	 * Constructor
	 * @param usuario
	 * @param idOperacion
	 */
	public CobroSimulacionExecutor(String usuario, String idOperacion) {
		this.usuario = usuario;
		this.idOperacion = idOperacion;
	}
	
	/**
	 * 
	 * @param listaApropiacionCalipsoRto
	 * @param listaApropiacionMicRto
	 * @param listaGeneracionCargoCalipsoRto
	 * @param listaGeneracionCargoMicRto
	 */
	public void inicializarListas(List<ApropiacionCalipsoRto> listaApropiacionCalipsoRto, 
								  List<ApropiacionMicRto> listaApropiacionMicRto,
								  List<GeneracionCargoCalipsoRto> listaGeneracionCargoCalipsoRto, 
								  List<GeneracionCargoMicRto> listaGeneracionCargoMicRto,
								  List<ApropiacionDeimosRto> listaApropiacionDeimosRto,
							  	  List<VerificarPartidasSapRto> listaVerificarPartidasSapRto,
							  	  List<ConsultarProveedoresSapRto> listaConsultarProveedoresSapRto
							  	  ) 
	{
		this.listaApropiacionCalipsoRto = listaApropiacionCalipsoRto;
		this.listaApropiacionMicRto = listaApropiacionMicRto;
		this.listaGeneracionCargoCalipsoRto = listaGeneracionCargoCalipsoRto;
		this.listaGeneracionCargoMicRto = listaGeneracionCargoMicRto;
		this.listaApropiacionDeimosRto = listaApropiacionDeimosRto;
		this.listaVerificarPartidasSapRto = listaVerificarPartidasSapRto;
		this.listaConsultarProveedoresSapRto = listaConsultarProveedoresSapRto;
		
	}
	
	/**
	 * Arranco la ejecucion de la simulación contra diferentes servicios externos
	 */
	public List<ResultadoSimulacion> ejecutarSimulacion() throws NegocioExcepcion {
		double startTime = System.nanoTime();
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		if (listaApropiacionDeimosRto==null) {
			Traza.auditoria(this.getClass(), 
				Utilidad.reemplazarMensajes("Se ha comenzado el proceso de la simulacion contra diferentes servicios: Nro Operacion {0}", this.idOperacion));
		} else { 
			Traza.auditoria(this.getClass(), 
				Utilidad.reemplazarMensajes("DEIMOS: Se ha comenzado el proceso de la simulacion contra el servicio de DEIMOS: Nro Operacion {0}", this.idOperacion));
		}
		
		try {
			this.inicializarConcurrencias();
			
			int cantidadHilos = 0;
			
			if (listaApropiacionDeimosRto==null) {
				
				ExecutorService poolApropiacionCalipso = null;
				ExecutorService poolApropiacionMic = null;
				ExecutorService poolGeneracionCargosCalipso = null;
				ExecutorService poolGeneracionCargosMic = null;
				ExecutorService poolVerificarPartidasSap = null;
				ExecutorService poolConsultarProveedoresSap = null;
				
				List<Future<List<ResultadoSimulacion>>> listaCallablesEnEjecucion1 = new ArrayList<Future<List<ResultadoSimulacion>>>();
				List<Future<List<ResultadoSimulacion>>> listaCallablesEnEjecucion2 = new ArrayList<Future<List<ResultadoSimulacion>>>();
				List<Future<List<ResultadoSimulacion>>> listaCallablesEnEjecucion3 = new ArrayList<Future<List<ResultadoSimulacion>>>();
				List<Future<List<ResultadoSimulacion>>> listaCallablesEnEjecucion4 = new ArrayList<Future<List<ResultadoSimulacion>>>();
				List<Future<List<ResultadoSimulacion>>> listaCallablesEnEjecucion5 = new ArrayList<Future<List<ResultadoSimulacion>>>();
				List<Future<List<ResultadoSimulacion>>> listaCallablesEnEjecucion6 = new ArrayList<Future<List<ResultadoSimulacion>>>();

				//Pool de ApropiacionCalipso
				if (cantidadConcurrenciasApropiacionCalipso > 0) {
					poolApropiacionCalipso = Executors.newFixedThreadPool(cantidadConcurrenciasApropiacionCalipso);
					for (ApropiacionCalipsoRto rto: listaApropiacionCalipsoRto) {
						cantidadHilos++;
						String idOperacion = Utilidad.rellenarCerosIzquierda(rto.getEntradaCalipsoApropiacionWS().getIdOperacion().toString(), 7);
						String numeroTransaccion = Utilidad.rellenarCerosIzquierda(rto.getEntradaCalipsoApropiacionWS().getNumeroTransaccion().toString(), 5);
						String idThread = idOperacion+"."+numeroTransaccion;
								
						IListaResultadoSimulacionCallable tarea = new ApropiacionCalipsoCallable(idThread, rto); 
						listaCallablesEnEjecucion1.add(poolApropiacionCalipso.submit(tarea));
						Traza.auditoria(getClass(), "Hilo ("+ idThread+ ") creado nro:" + cantidadHilos);
						
					}
				} else {
					Traza.auditoria(this.getClass(), 
						"No se correrá el proceso de simulación por encontrarse en el poolApropiacionCalipso con la cantidad de concurrencias = "+ this.cantidadConcurrenciasApropiacionCalipso);
				}
				
				//Pool de ApropiacionMIC
				if (cantidadConcurrenciasApropiacionMic > 0) {
					poolApropiacionMic = Executors.newFixedThreadPool(cantidadConcurrenciasApropiacionMic);
					for (ApropiacionMicRto rto: listaApropiacionMicRto) {
						cantidadHilos++;
						String idOperacion = Utilidad.rellenarCerosIzquierda(rto.getEntradaMicApropiacionJms().getIdOperacion().toString(), 7);
						String numeroTransaccion = Utilidad.rellenarCerosIzquierda(rto.getEntradaMicApropiacionJms().getNumeroTransaccion().toString(), 5);
						String idThread = idOperacion+"."+numeroTransaccion;
								
						IListaResultadoSimulacionCallable tarea = new ApropiacionMicCallable(idThread, rto);
						listaCallablesEnEjecucion2.add(poolApropiacionMic.submit(tarea));
						Traza.auditoria(getClass(), "Hilo ("+ idThread+ ") creado nro:" + cantidadHilos);
					}
				} else {
					Traza.auditoria(this.getClass(), 
						"No se correrá el proceso de simulación por encontrarse en el poolApropiacionMic con la cantidad de concurrencias = "+ this.cantidadConcurrenciasApropiacionMic);
				}
				
				//Pool de GeneracionCargosCalipso
				if (cantidadConcurrenciasGeneracionCargoCalipso > 0) {
					poolGeneracionCargosCalipso = Executors.newFixedThreadPool(cantidadConcurrenciasGeneracionCargoCalipso);
					for (GeneracionCargoCalipsoRto rto: listaGeneracionCargoCalipsoRto) {
						cantidadHilos++;
						String idThread = rto.getOperacionTransaccionFormateado();
						
						IListaResultadoSimulacionCallable tarea = new GeneracionCargoCalipsoCallable(idThread, rto);
						listaCallablesEnEjecucion3.add(poolGeneracionCargosCalipso.submit(tarea));
						Traza.auditoria(getClass(), "Hilo ("+ idThread+ ") creado nro:" + cantidadHilos);
					}
				} else {
					Traza.auditoria(this.getClass(), 
						"No se correrá el proceso de simulación por encontrarse en el poolGeneracionCargosCalipso con la cantidad de concurrencias = "+ this.cantidadConcurrenciasGeneracionCargoCalipso);
				}
				
				//Pool de GeneracionCargosMic
				if (cantidadConcurrenciasGeneracionCargoMic > 0) {
					poolGeneracionCargosMic = Executors.newFixedThreadPool(cantidadConcurrenciasGeneracionCargoMic);
					for (GeneracionCargoMicRto rto: listaGeneracionCargoMicRto) {
						cantidadHilos++;
						String idThread = rto.getOperacionTransaccionFormateado();
						
						IListaResultadoSimulacionCallable tarea = new GeneracionCargoMicCallable(idThread, rto);
						listaCallablesEnEjecucion4.add(poolGeneracionCargosMic.submit(tarea));
						Traza.auditoria(getClass(), "Hilo ("+ idThread+ ") creado nro:" + cantidadHilos);
					}
				} else {
					Traza.auditoria(this.getClass(), 
							"No se correrá el proceso de simulación por encontrarse en el poolGeneracionCargosMic con la cantidad de concurrencias = "+ this.cantidadConcurrenciasGeneracionCargoMic);
				}
				
				//Pool de Verificar Partidas en Sap
				if (cantidadConcurrenciasVerificarPartidasSap > 0) {
					poolVerificarPartidasSap = Executors.newFixedThreadPool(cantidadConcurrenciasVerificarPartidasSap);
					for (VerificarPartidasSapRto rto: listaVerificarPartidasSapRto) {
						cantidadHilos++;
						String idThread = rto.getOperacionFormateado();
						
						IListaResultadoSimulacionCallable tarea = new VerificarPartidasSapCallable(idThread, rto);
						listaCallablesEnEjecucion5.add(poolVerificarPartidasSap.submit(tarea));
						Traza.auditoria(getClass(), "Hilo ("+ idThread+ ") creado nro:" + cantidadHilos);
					}
				} else {
					Traza.auditoria(this.getClass(), 
							"No se correrá el proceso de simulación por encontrarse en el poolSolicitudGeneracionCompensacionSap con la cantidad de concurrencias = "+ this.cantidadConcurrenciasVerificarPartidasSap);
				}				
				
				//Pool de Consultar Proveedor en Sap
				if (cantidadConcurrenciasConsultarProveedoresSap > 0) {
					poolConsultarProveedoresSap = Executors.newFixedThreadPool(cantidadConcurrenciasConsultarProveedoresSap);
					for (ConsultarProveedoresSapRto rto: listaConsultarProveedoresSapRto) {
						cantidadHilos++;
						String idThread = rto.getOperacionFormateado();
						
						IListaResultadoSimulacionCallable tarea = new ConsultarProveedorSapCallable(idThread, rto);
						listaCallablesEnEjecucion6.add(poolConsultarProveedoresSap.submit(tarea));
						Traza.auditoria(getClass(), "Hilo ("+ idThread+ ") creado nro:" + cantidadHilos);
					}
				} else {
					Traza.auditoria(this.getClass(), 
							"No se correrá el proceso de simulación por encontrarse en el poolConsultarProveedoresSap con la cantidad de concurrencias = "+ this.cantidadConcurrenciasConsultarProveedoresSap);
				}
				
				// Espero a que terminen de ejecutarse todos los procesos 
	        	// para pasar a las siguientes instrucciones
				int cantidadHilosEjecutados = 0;
				if (poolApropiacionCalipso!=null) {
					cantidadHilosEjecutados += this.armarListaResultadoSimulacion(listaResultadoSimulacion, listaCallablesEnEjecucion1);
					poolApropiacionCalipso.shutdown();
				}
				if (poolApropiacionMic!=null) {
					cantidadHilosEjecutados += this.armarListaResultadoSimulacion(listaResultadoSimulacion, listaCallablesEnEjecucion2);
					poolApropiacionMic.shutdown();
				}
				if (poolGeneracionCargosCalipso!=null) {
					cantidadHilosEjecutados += this.armarListaResultadoSimulacion(listaResultadoSimulacion, listaCallablesEnEjecucion3);
					poolGeneracionCargosCalipso.shutdown();
				}
				if (poolGeneracionCargosMic!=null) {
					cantidadHilosEjecutados += this.armarListaResultadoSimulacion(listaResultadoSimulacion, listaCallablesEnEjecucion4);
					poolGeneracionCargosMic.shutdown();
				}
				if (poolVerificarPartidasSap != null) {
					cantidadHilosEjecutados += this.armarListaResultadoSimulacion(listaResultadoSimulacion, listaCallablesEnEjecucion5);
					poolVerificarPartidasSap.shutdown();
				}
				if (poolConsultarProveedoresSap != null) {
					cantidadHilosEjecutados += this.armarListaResultadoSimulacion(listaResultadoSimulacion, listaCallablesEnEjecucion6);
					poolConsultarProveedoresSap.shutdown();
				}
				Traza.auditoria(CobroSimulacionExecutor.class, "------ Se han ejecutado " + cantidadHilosEjecutados + " hilos de los " + cantidadHilos);
				
			} else {
				ExecutorService poolApropiacionDeimos = null;
				List<Future<List<ResultadoSimulacion>>> listaCallablesEnEjecucion7 = new ArrayList<Future<List<ResultadoSimulacion>>>();
				
				//Pool de ApropiacionDeimos
				if (cantidadConcurrenciasApropiacionDeimos > 0) {
					poolApropiacionDeimos = Executors.newFixedThreadPool(cantidadConcurrenciasApropiacionDeimos);
					for (ApropiacionDeimosRto rto: listaApropiacionDeimosRto) {
						cantidadHilos++;
						String idOperacion = Utilidad.rellenarCerosIzquierda(rto.getEntradaDeimosApropiacionWS().getIdOperacionShiva().toString(), 7);
						String numeroTransaccion = Utilidad.rellenarCerosIzquierda(rto.getEntradaDeimosApropiacionWS().getTransaccion().getIdSecuencia().toString(), 5);
						String idThread = idOperacion+"."+numeroTransaccion;
								
						IListaResultadoSimulacionCallable tarea = new ApropiacionDeimosCallable(idThread, rto);
						listaCallablesEnEjecucion7.add(poolApropiacionDeimos.submit(tarea));
						Traza.auditoria(getClass(), "DEIMOS: Hilo ("+ idThread+ ") creado nro:" + cantidadHilos);
					}
				} else {
					Traza.auditoria(this.getClass(), 
							"DEIMOS: No se correrá el proceso de simulación por encontrarse en el poolApropiacionDeimos con la cantidad de concurrencias = "+ this.cantidadConcurrenciasApropiacionDeimos);
				}
				
				// Espero a que terminen de ejecutarse todos los procesos 
	        	// para pasar a las siguientes instrucciones
				int cantidadHilosEjecutados = 0;
				if (poolApropiacionDeimos!=null) {
					//if (poolApropiacionDeimos.awaitTermination(1, TimeUnit.MINUTES)) {
						cantidadHilosEjecutados += this.armarListaResultadoSimulacion(listaResultadoSimulacion, listaCallablesEnEjecucion7);
						poolApropiacionDeimos.shutdown();
					//} else {} 
				}
				Traza.auditoria(CobroSimulacionExecutor.class, "DEIMOS: ------ Se han ejecutado " + cantidadHilosEjecutados + " hilos de los " + cantidadHilos);
			}
			
			
			return listaResultadoSimulacion;
		} catch (Throwable e) {
			Traza.error(this.getClass(), e.getMessage(), e);
			
			throw new NegocioExcepcion(e.getMessage(), e);
		} finally {
			Traza.auditoria(CobroSimulacionExecutor.class, 
					Utilidad.reemplazarMensajes("------ Se ha finalizado el proceso de la simulacion contra diferentes servicios: Nro Operacion {0}", this.idOperacion));
			
			double stopTime = System.nanoTime();
			double elapsedTime = (stopTime - startTime) / 1000000000.0;
			String detalle = "Resultado: " + new DecimalFormat("#.########").format(elapsedTime);
			
			Traza.tiempo(this.getClass(), usuario, "ejecutarSimulacionContraDiferentesServicios", detalle);
		}		
	}

	/**
	 * 
	 * @param listaCallablesEnEjecucion
	 */
	private int armarListaResultadoSimulacion(
			List<ResultadoSimulacion> listaResultadoSimulacion, 
			List<Future<List<ResultadoSimulacion>>> listaCallablesEnEjecucion) throws NegocioExcepcion {
		
		int cantidadHilosEjecutados = 0;
		for (Future<List<ResultadoSimulacion>> future : listaCallablesEnEjecucion) {
			try { 
				List<ResultadoSimulacion> resultado = future.get(3, TimeUnit.MINUTES);
				
				//Actualizar las mensajerias
				listaResultadoSimulacion.addAll(resultado);
				Traza.auditoria(getClass(), "Hilo finalizado nro:" + cantidadHilosEjecutados++);
				
			} catch (InterruptedException | ExecutionException ignorable) { 
				Traza.advertencia(this.getClass(), 
						Utilidad.reemplazarMensajes("------ Hubo una interrupcion en un proceso thread en la Operacion {0}", this.idOperacion), ignorable);
				
				throw new NegocioExcepcion(ignorable.getMessage(), ignorable);
				
			} catch (TimeoutException timeout) { 
				Traza.advertencia(this.getClass(), 
						Utilidad.reemplazarMensajes("------ Hubo un timeout en un proceso thread en la Operacion {0}", this.idOperacion), timeout);
				
				throw new NegocioExcepcion(timeout.getMessage(), timeout);
	        }
		}
		return cantidadHilosEjecutados;
	}
	
	/**
	 * Inicializo las concurrencias desde la base de datos
	 * @throws NumberFormatException
	 * @throws NegocioExcepcion
	 */
	private  void inicializarConcurrencias() throws NumberFormatException, NegocioExcepcion {
		IParametroServicio parametroServicio = (IParametroServicio) Configuracion.ctx.getBean("parametroServicio");
		
		this.cantidadConcurrenciasApropiacionCalipso = Integer.valueOf( 
				parametroServicio.getValorNumerico(ConstantesCobro.COBROS_SIMULACION_CANTIDAD_CONCURRENCIAS_APROPIACION_CALIPSO).toString());
		this.cantidadConcurrenciasApropiacionMic = Integer.valueOf( 
				parametroServicio.getValorNumerico(ConstantesCobro.COBROS_SIMULACION_CANTIDAD_CONCURRENCIAS_APROPIACION_MIC).toString());
		this.cantidadConcurrenciasGeneracionCargoCalipso = Integer.valueOf( 
				parametroServicio.getValorNumerico(ConstantesCobro.COBROS_SIMULACION_CANTIDAD_CONCURRENCIAS_GENERACION_CARGO_CALIPSO).toString());
		this.cantidadConcurrenciasGeneracionCargoMic = Integer.valueOf( 
				parametroServicio.getValorNumerico(ConstantesCobro.COBROS_SIMULACION_CANTIDAD_CONCURRENCIAS_GENERACION_CARGO_MIC).toString());
		this.cantidadConcurrenciasApropiacionDeimos = Integer.valueOf( 
				parametroServicio.getValorNumerico(ConstantesCobro.COBROS_SIMULACION_CANTIDAD_CONCURRENCIAS_APROPIACION_DEIMOS).toString());
		this.cantidadConcurrenciasVerificarPartidasSap = Integer.valueOf( 
				parametroServicio.getValorNumerico(ConstantesCobro.COBROS_SIMULACION_CANTIDAD_CONCURRENCIAS_VERIFICAR_PARTIDAS_SAP).toString());
		this.cantidadConcurrenciasConsultarProveedoresSap = Integer.valueOf( 
				parametroServicio.getValorNumerico(ConstantesCobro.COBROS_SIMULACION_CANTIDAD_CONCURRENCIAS_CONSULTAR_PROVEEDOR_SAP).toString());
	}
}
