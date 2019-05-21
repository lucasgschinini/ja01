package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlMemoriaCPU;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ControlThreadSingleton;
import ar.com.telecom.shiva.negocio.executor.CobroImputacionExecutor;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ImputacionRespuestaRto;
import ar.com.telecom.shiva.negocio.executor.runnable.ImputacionRespuestaRunnable;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionMicServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class ImputacionCobrosManualBatchRunner {

	private ImputacionCobrosManualBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		// Medicion de tiempo al inicio del procesamiento
		double fechaHoraInicioNanoTime = System.nanoTime();
					
		try {
			Traza.auditoria(ImputacionCobrosManualBatchRunner.class, ControlMemoriaCPU.getInformacionGeneralSistema());
			
			//ControlVariablesSingleton.permitirTraceoSQL();
			
			Traza.auditoria(ImputacionCobrosManualBatchRunner.class, 
						"Se ha iniciado el Batch para la imputacion manual de cobros");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_IMPUTACION_MANUAL_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
			
			for (int i=0; i <= 50; i++) {
				Traza.auditoria(ImputacionCobrosManualBatchRunner.class, "Corrida Nro: "+ i +" .....");

				//Verifico si la version es correcta
				IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
				Utilidad.verificarVersion(parametroServicio);
				
				//Tarea 1 (PROCESAR MENSAJES DE COBROS)
				Traza.auditoria(ImputacionCobrosManualBatchRunner.class, "1.- ===================================================================================");
				procesarTareaMensajesCobros();
				
				//Libero la memoria
				System.gc();
				
				//Tarea 2 (IMPUTAR COBROS)
				Traza.auditoria(ImputacionCobrosManualBatchRunner.class, "2.- ===================================================================================");
				procesarTareaImputacionCobrosManual(i);
				
				Traza.auditoria(ImputacionCobrosManualBatchRunner.class, "FIN ===================================================================================");
				
				try {
					Traza.auditoria(ImputacionCobrosManualBatchRunner.class, "EN ESPERA..... --> " + ControlMemoriaCPU.getInformacionMemoria());
					
					//Libero la memoria
					System.gc();
					Thread.sleep(10000);
					
					Traza.auditoria(ImputacionCobrosManualBatchRunner.class, "ARRANCA DE NUEVO..... --> " + ControlMemoriaCPU.getInformacionMemoria());
				} catch (InterruptedException ignore) {
					System.err.println("Error de InterruptedException");
					Traza.error(ImputacionCobrosManualBatchRunner.class, "Error en ciclo de loop", ignore);
				}
			} 
		} catch (AccessControlException e) {
			
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(ImputacionCobrosManualBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(ImputacionCobrosManualBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			
			System.exit(Constantes.SH_ERROR);
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(ImputacionCobrosManualBatchRunner.class, 
					"Se ha finalizado el Batch para la imputacion manual de cobros");
			
			/**Tiempo del proceso batch*/
			double elapsedTime = System.nanoTime() - fechaHoraInicioNanoTime;
			String detalle = "Tiempo de procesamiento batch de imputacion manual de cobros: " 
						+ ((double) elapsedTime / 1000000000.0);
			Traza.tiempo(ImputacionCobrosManualBatchRunner.class, "main", detalle);
		}
		System.exit(0);
	}
	
	
	/**
	 * Método que se encarga de procesar los mensajes a partir de los cobros en estado pendiente MIC.
	 * @param cobroServicio
	 * @throws ShivaExcepcion 
	 */
	private static void procesarTareaMensajesCobros() throws ShivaExcepcion {
		try {
			Traza.auditoria(ImputacionCobrosBatchRunner.class, "-- Se ha iniciado el proceso de mensajes de respuesta MIC para cobros");
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			ICobroBatchSoporteImputacionMicServicio cobroBatchSoporteImputacionMicServicio = (ICobroBatchSoporteImputacionMicServicio) Configuracion.getBeanBatch("cobroSoporteMicServicio");
			
			// Obtengo las id de operaciones de los cobros "cobro pendiente procesar de MIC".
			List<Long> lista = cobroBatchSoporteImputacionMicServicio.listarCobrosImpManualPendientesProcesarMIC();
			
			Traza.auditoria(ImputacionCobrosBatchRunner.class, 
					Utilidad.reemplazarMensajes("Se ha encontrado {0} cobro pendiente de procesar de MIC", 
							String.valueOf(lista.size())));
			
			int cantidadHilos = 0;
			
			//Pool de Imputacion Recepcion
			Long cantConcurrenciasRecepcionCobros = parametroServicio.getValorNumerico(Constantes.CANTIDAD_CONCURRENCIAS_IMPUTACION_RESPUESTA_COBROS);
			Integer cantConcurrencias = Integer.valueOf(cantConcurrenciasRecepcionCobros.toString());
		
			if (cantConcurrencias > 0) {
				ExecutorService executorRespuesta = 
						Executors.newFixedThreadPool(cantConcurrencias);
				
				for (Long idOperacion: lista) {
					cantidadHilos++;
					
					ImputacionRespuestaRto rto = new ImputacionRespuestaRto(cantidadHilos, idOperacion, null, TipoProcesoEnum.COBRO);
					Runnable tarea = new ImputacionRespuestaRunnable(rto);
					executorRespuesta.execute(tarea);
					
					Traza.auditoria(ImputacionCobrosBatchRunner.class, "Hilo nro: "+ cantidadHilos +" (Id Operacion {"+ Utilidad.rellenarCerosIzquierda(idOperacion.toString(), 7) +"}) creado "
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
				cobroBatchSoporteImputacionMicServicio = null;
				
			} else {
				Traza.error(ImputacionCobrosBatchRunner.class, 
						"No se correrá el proceso de recepcion por encontrarse en el cantConcurrenciasRecepcionCobros con la cantidad de concurrencias = " 
									+ cantConcurrencias);
			}
			
    		Traza.auditoria(ImputacionCobrosBatchRunner.class, "---- Se ha finalizado exitosamente el proceso de mensajes de respuesta MIC para cobros");
		
		} catch (Throwable e) {
			Traza.advertencia(ImputacionCobrosBatchRunner.class, "---- Se ha finalizado con error en el proceso de mensajes de respuesta MIC para cobros");
			throw new ShivaExcepcion(e);
		}
	}
	
	
	
	
	
	/**
	 * Método que se encarga de imputar los cobros que esten en estado pendiente.
	 * @param cobroServicio
	 * @throws ShivaExcepcion 
	 */
	private static void procesarTareaImputacionCobrosManual(int i) throws ShivaExcepcion {
		try {
			
			ICobroImputacionServicio cobroServicio = (ICobroImputacionServicio) Configuracion.getBeanBatch("cobroServicio");
			
			CobroImputacionExecutor cobroImputacionExecutor = new CobroImputacionExecutor();
			cobroImputacionExecutor.ejecutarImputacionesManualesCobros();
			cobroImputacionExecutor = null;
			Integer cantidadHilosVivos = 0;
			boolean finalizarBatch = false;
			Long tiempoLimiteParaFinalizarProceso = new Long(0);
			
			if (i == 50){
				
				IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
				Long parametroFinalizarProceso = Long.valueOf(parametroServicio.getValorNumerico(Constantes.IMPUTACION_COBROS_LIMITE_FINALIZAR_PROCESO).toString());
				
				while (!finalizarBatch){
					
					cantidadHilosVivos = cobroServicio.hayHilosCobrosVivos(TipoImputacionEnum.MANUAL);
					
					if (cantidadHilosVivos > 0){
						Traza.auditoria(ImputacionCobrosManualBatchRunner.class, "---- Quedan " + cantidadHilosVivos + "hilos ejecutando.");
						Thread.sleep(2000);
						tiempoLimiteParaFinalizarProceso += 2000;
					
						if (tiempoLimiteParaFinalizarProceso >= parametroFinalizarProceso){
							break;
						}
					} else {
						Traza.auditoria(ImputacionCobrosManualBatchRunner.class, "---- Finalizaron todos los hilos.");
						finalizarBatch = true;
					}
				}
			}
			
			//Esperar a que todos los hilos esten terminados 
    		ControlThreadSingleton.esperarFinTodosHilos();
			
    		Traza.auditoria(ImputacionCobrosManualBatchRunner.class, "---- Se ha finalizado exitosamente el proceso de Imputacion de Cobros");
		
		} catch (Throwable e) {
			Traza.advertencia(ImputacionCobrosManualBatchRunner.class, "---- Se ha finalizado con error en el proceso de Imputacion de Cobros");
			throw new ShivaExcepcion(e);
		}
	}
	
	
}
