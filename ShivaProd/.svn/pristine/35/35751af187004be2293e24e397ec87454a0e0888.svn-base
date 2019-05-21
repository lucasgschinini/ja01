package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlMemoriaCPU;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ControlThreadSingleton;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ImputacionRespuestaRto;
import ar.com.telecom.shiva.negocio.executor.runnable.ImputacionRespuestaRunnable;
import ar.com.telecom.shiva.negocio.servicios.IDescobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;

public class ImputacionDescobrosBatchRunner {

	private ImputacionDescobrosBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		
		// Medicion de tiempo al inicio del procesamiento
		double fechaHoraInicioNanoTime = System.nanoTime();
					
		try {
			//ControlVariablesSingleton.permitirTraceoSQL();
			
			Traza.auditoria(ImputacionDescobrosBatchRunner.class, 
						"Se ha iniciado el Batch para la imputacion de Descobros");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_IMPUTACION_DESCOBRO_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
			
			for (int i=0; i <= 50; i++) {
				Traza.auditoria(ImputacionDescobrosBatchRunner.class, "Corrida Nro: "+ i +" .....");
				
				//Verifico si la version es correcta
				IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
				Utilidad.verificarVersion(parametroServicio);
				
				//Tarea 1 (PROCESAR MENSAJES DE COBROS)
				Traza.auditoria(ImputacionCobrosBatchRunner.class, "1.- ===================================================================================");
				procesarTareaMensajesDescobros();
				
				//Libero la memoria
				System.gc();
				
				//Tarea 2 (DESCOBROS)
				Traza.auditoria(ImputacionDescobrosBatchRunner.class, "2.- ===================================================================================");
				procesarTareaImputacionDescobros();
				
				Traza.auditoria(ImputacionDescobrosBatchRunner.class, "FIN ===================================================================================");
				
				try {
					Traza.auditoria(ImputacionDescobrosBatchRunner.class, "EN ESPERA.....");
					
					//Libero la memoria
					System.gc();
					
					Thread.sleep(10000);
					Traza.auditoria(ImputacionDescobrosBatchRunner.class, "ARRANCA DE NUEVO.....");
				} catch (InterruptedException ignore) {
					System.err.println("Error de InterruptedException");
					Traza.error(ImputacionDescobrosBatchRunner.class, "Error en ciclo de loop", ignore);
				}
			} 
		} catch (AccessControlException e) {
			
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(ImputacionDescobrosBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(ImputacionDescobrosBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			
			System.exit(Constantes.SH_ERROR);
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(ImputacionDescobrosBatchRunner.class, 
					"Se ha finalizado el Batch para la imputacion de descobros");
			
			/**Tiempo del proceso batch*/
			double elapsedTime = System.nanoTime() - fechaHoraInicioNanoTime;
			String detalle = "Tiempo de procesamiento batch de imputacion de descobros: " 
						+ ((double) elapsedTime / 1000000000.0);
			Traza.tiempo(ImputacionDescobrosBatchRunner.class, "main", detalle);
		}
		
		System.exit(0);
	}
	
	
	/**
	 * Método que se encarga de procesar los mensajes a partir de los cobros en estado pendiente MIC.
	 * @param cobroServicio
	 * @throws ShivaExcepcion 
	 */
	private static void procesarTareaMensajesDescobros() throws ShivaExcepcion {
		try {
			Traza.auditoria(ImputacionCobrosBatchRunner.class, "-- Se ha iniciado el proceso de mensajes de respuesta MIC para descobros");
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			IDescobroImputacionServicio descobroImputacionServicio = (IDescobroImputacionServicio) Configuracion.getBeanBatch("descobroImputacionServicio");
			
			// Obtengo las id de operaciones de los cobros "cobro pendiente de MIC".
			List<Long> lista = descobroImputacionServicio.listarDescobrosPendientesProcesarMIC();
			
			Traza.auditoria(ImputacionCobrosBatchRunner.class, 
					Utilidad.reemplazarMensajes("Se ha encontrado {0} cobro pendiente de MIC", 
							String.valueOf(lista.size())));
			
			int cantidadHilos = 0;
			
			//Pool de Imputacion Recepcion
			Long cantConcurrenciasRecepcionDescobros = parametroServicio.getValorNumerico(Constantes.CANTIDAD_CONCURRENCIAS_IMPUTACION_RESPUESTA_DESCOBROS);
			Integer cantConcurrencias = Integer.valueOf(cantConcurrenciasRecepcionDescobros.toString());
		
			if (cantConcurrencias > 0) {
				ExecutorService executorRespuesta = 
						Executors.newFixedThreadPool(cantConcurrencias);
				
				for (Long idOperacionDescobro: lista) {
					cantidadHilos++;
					
					ImputacionRespuestaRto rto = new ImputacionRespuestaRto(cantidadHilos, null, idOperacionDescobro, TipoProcesoEnum.DESCOBRO);
					Runnable tarea = new ImputacionRespuestaRunnable(rto);
					executorRespuesta.execute(tarea);
					
					Traza.auditoria(ImputacionCobrosBatchRunner.class, "Hilo nro: "+ cantidadHilos +" (Id Operacion Descobro {"+ Utilidad.rellenarCerosIzquierda(idOperacionDescobro.toString(), 7) +"}) creado "
						  + "--> " + ControlMemoriaCPU.getInformacionMemoria());
				}
								
				executorRespuesta.shutdown();
				// Espero a que terminen de ejecutarse todos los procesos 
				while (!executorRespuesta.isTerminated()) {}
//				o
//				try {
//					executorImputacionesRecepcion.awaitTermination(1, TimeUnit.HOURS);	
//				} catch (InterruptedException e) {
//					Traza.error(getClass(), "Problemas de executor:", e);
//				}
				executorRespuesta = null;
				parametroServicio = null;
				descobroImputacionServicio = null;
				
			} else {
				Traza.error(ImputacionCobrosBatchRunner.class, 
						"No se correrá el proceso de recepcion por encontrarse en el cantConcurrenciasRecepcionDescobros con la cantidad de concurrencias = " 
									+ cantConcurrencias);
			}
			
    		Traza.auditoria(ImputacionCobrosBatchRunner.class, "---- Se ha finalizado exitosamente el proceso de mensajes de respuesta MIC para descobros");
		
		} catch (Throwable e) {
			Traza.advertencia(ImputacionCobrosBatchRunner.class, "---- Se ha finalizado con error en el proceso de mensajes de respuesta MIC para descobros");
			throw new ShivaExcepcion(e);
		}
	}
	
	/**
	 * 
	 * @param cobroServicio
	 * @throws ShivaExcepcion 
	 */
	private static void procesarTareaImputacionDescobros() throws ShivaExcepcion {
		try {
			Traza.auditoria(ImputacionDescobrosBatchRunner.class, "-- Se ha iniciado el proceso de Imputacion de Descobros");
			
			IDescobroImputacionServicio descobroImputacionServicio = (IDescobroImputacionServicio) Configuracion.getBeanBatch("descobroImputacionServicio");
			procesarDescobros(descobroImputacionServicio);
			
			//Esperar a que todos los hilos esten terminados 
    		ControlThreadSingleton.esperarFinTodosHilos();
			
    		Traza.auditoria(ImputacionDescobrosBatchRunner.class, "---- Se ha finalizado exitosamente el proceso de Imputacion de Descobros");
		
		} catch (Throwable e) {
			Traza.advertencia(ImputacionDescobrosBatchRunner.class, "---- Se ha finalizado con error en el proceso de Imputacion de Descobros");
			throw new ShivaExcepcion(e);
		}
	}
	
	/**
	 * Método que se encarga de imputar los descobros que esten en estado pendiente.
	 */
	private static void procesarDescobros(IDescobroImputacionServicio descobroImputacionServicio) throws NegocioExcepcion{
		// TODO: Fabio-Fernando-Lean --> Por ahora, no tiene procesos paralelos
		
		// Obtengo las id de operaciones de los descobros en estado Pendiente o en estado "descobro en proceso".
		List<Long> lista = descobroImputacionServicio.listarDescobrosPendientesImputacion();
		
		Traza.auditoria(ImputacionDescobrosBatchRunner.class, 
				Utilidad.reemplazarMensajes("Se ha encontrado {0} descobros pendientes / descobro en proceso", 
						String.valueOf(lista.size())));
		
		int count = 0;
		for (Long idOperacion : lista) {
			count++;
//			Long idOperacion = new Long(100015);
			try {
				//TODO solo para las pruebas unitarias
//				if (idOperacion.compareTo(Long.valueOf("100080")) == 0 ) {
					
//					ShvCobDescobro descobro = descobroImputacionServicio.buscarDescobroPorIdOperacion(idOperacion);
					ShvCobDescobro descobro = null;
					
					//Imputo el cobro con el descobro pendiente/En proceso
					descobro = descobroImputacionServicio.imputarDescobro(idOperacion, count);

					
					//Por el manejo de la transaccion de la BD, se quedo fuera del metodo imputarCobro
					if (Estado.DES_DESCOBRADO.equals(descobro.getWorkflow().getEstado()) ||
							Estado.DES_EN_ERROR.equals(descobro.getWorkflow().getEstado()) ||
							Estado.DES_ERROR_EN_PRIMER_DESCOBRO.equals(descobro.getWorkflow().getEstado()) ||
							Estado.DES_PENDIENTE_CONFIRMACION_MANUAL.equals(descobro.getWorkflow().getEstado())) {
	
						try {
							//SHV - Se le deberá dar aviso al usuario de la finalización 
							//del proceso batch de reversión de cobro
							descobroImputacionServicio.enviarMailyGenerarTarea(descobro);
						} catch (Exception e){
							Traza.error(ImputacionDescobrosBatchRunner.class, 
									"No se ha podido generar la tarea y enviar el mail. operacion id: " 
											+ descobro.getOperacion().getIdOperacion(), e);
						}	
					}//Fin - Imputo el descobro
					
				//} else {
				//	Traza.error(ImputacionDescobrosBatchRunner.class, 
				//			Utilidad.reemplazarMensajes("No se imputara el descobro (idOperacion: {0}) por no corresponder a la prueba unitaria)",
				//					String.valueOf(idOperacion)));
//				}
				
			} catch (Exception e) {
				Traza.error(ImputacionDescobrosBatchRunner.class, 
						Utilidad.reemplazarMensajes("Se ha producido un error al imputar el descobro (idOperacion: {0})",
								String.valueOf(idOperacion)), e);
				Traza.auditoria(ImputacionDescobrosBatchRunner.class, "-------------------------------------------------------------------------------------");
				
			}
		}
	}
}
