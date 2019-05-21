package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsServicio;
import ar.com.telecom.shiva.base.jms.util.JmsMonitorMensajeria;
import ar.com.telecom.shiva.base.utils.ControlMemoriaCPU;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ControlThreadSingleton;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class ImputacionRecepcionBatchRunner {

	private ImputacionRecepcionBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		// Medicion de tiempo al inicio del procesamiento
		double fechaHoraInicioNanoTime = System.nanoTime();
					
		try {
			Traza.auditoria(ImputacionRecepcionBatchRunner.class, ControlMemoriaCPU.getInformacionGeneralSistema());
			
			//ControlVariablesSingleton.permitirTraceoSQL();
			
			Traza.auditoria(ImputacionRecepcionBatchRunner.class, 
						"Se ha iniciado el Batch de la recepcion de mensajes para la imputacion de cobros");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_IMPUTACION_RECEPCION_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
			
			for (int i=0; i <= 50; i++) {
				Traza.auditoria(ImputacionRecepcionBatchRunner.class, "Corrida Nro: "+ i +" .....");

				//Verifico si la version es correcta
				IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
				Utilidad.verificarVersion(parametroServicio);
				
				//Tarea 1 (MIC)
				Traza.auditoria(ImputacionRecepcionBatchRunner.class, "1.- ===================================================================================");
				procesarTareaRecepcionDeMensajesMic();
				
				//Tarea 2 (MIC)
				Traza.auditoria(ImputacionRecepcionBatchRunner.class, "2.- ===================================================================================");
				procesarTareaReintentosCobros();
				
				//Tarea 2 (MIC)
				Traza.auditoria(ImputacionRecepcionBatchRunner.class, "3.- ===================================================================================");
				procesarTareaReintentosDescobros();
				
				Traza.auditoria(ImputacionRecepcionBatchRunner.class, "FIN ===================================================================================");
				
				try {
					Traza.auditoria(ImputacionRecepcionBatchRunner.class, "EN ESPERA..... --> " + ControlMemoriaCPU.getInformacionMemoria());

					//Libero la memoria
					System.gc();
					Thread.sleep(10000);
					
					Traza.auditoria(ImputacionRecepcionBatchRunner.class, "ARRANCA DE NUEVO..... --> " + ControlMemoriaCPU.getInformacionMemoria());
				} catch (InterruptedException ignore) {
					System.err.println("Error de InterruptedException");
					Traza.error(ImputacionRecepcionBatchRunner.class, "Error en ciclo de loop", ignore);
				}
			} 
		} catch (AccessControlException e) {
			
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(ImputacionRecepcionBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(ImputacionRecepcionBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			
			System.exit(Constantes.SH_ERROR);
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(ImputacionRecepcionBatchRunner.class, 
					"Se ha finalizado el Batch de la recepcion de mensajes para la imputacion de cobros");
			
			/**Tiempo del proceso batch*/
			double elapsedTime = System.nanoTime() - fechaHoraInicioNanoTime;
			String detalle = "Tiempo de procesamiento batch de la recepcion de mensajes para la imputacion de cobros: " 
						+ ((double) elapsedTime / 1000000000.0);
			Traza.tiempo(ImputacionRecepcionBatchRunner.class, "main", detalle);
		}
		
		System.exit(0);
	}
	
	/**
	 * 
	 * @param cobroServicio
	 * @throws ShivaExcepcion 
	 */
	private static void procesarTareaRecepcionDeMensajesMic() throws ShivaExcepcion {
		try {
			Traza.auditoria(ImputacionRecepcionBatchRunner.class, "-- Se ha iniciado el proceso de Recepcion de mensajes MIC");
			IMicJmsServicio micJmsServicio = (IMicJmsServicio) Configuracion.getBeanBatch("micJmsServicio");
			
			Traza.auditoria(ImputacionRecepcionBatchRunner.class, "---- Recepcion de mensajes MIC para los cobros ----");
			micJmsServicio.recibirMensajes(TipoProcesoEnum.COBRO);
			Traza.auditoria(ImputacionRecepcionBatchRunner.class, "---- FIN - Recepcion de mensajes MIC para los cobros ----");
			Traza.auditoria(ImputacionRecepcionBatchRunner.class, "---------------------------------------------------------");
			Traza.auditoria(ImputacionRecepcionBatchRunner.class, "---- Recepcion de mensajes MIC para los descobros ----");
			micJmsServicio.recibirMensajes(TipoProcesoEnum.DESCOBRO);
			Traza.auditoria(ImputacionRecepcionBatchRunner.class, "---- FIN - Recepcion de mensajes MIC para los descobros ----");
			
			
    		Traza.auditoria(ImputacionRecepcionBatchRunner.class, "---- Se ha finalizado exitosamente el proceso de Recepcion de mensajes MIC");
		} catch (Throwable e) {
			Traza.advertencia(ImputacionRecepcionBatchRunner.class, "---- Se ha finalizado con error en el proceso de Recepcion de mensajes MIC");
			throw new ShivaExcepcion(e);
		}
	}
	
	/**
	 * 
	 * @param cobroServicio
	 * @throws ShivaExcepcion 
	 */
	private static void procesarTareaReintentosCobros() throws ShivaExcepcion {
		try {
			Traza.auditoria(ImputacionRecepcionBatchRunner.class, "-- Se ha iniciado el proceso de Reintentos (MIC) - Imputacion de Cobros");
			
			JmsMonitorMensajeria monitor = (JmsMonitorMensajeria) Configuracion.getBeanBatch("jmsMonitorMensajeria");
			monitor.reenviarMensajesPendientes(TipoProcesoEnum.COBRO);
			
			//Esperar a que todos los hilos esten terminados 
			ControlThreadSingleton.esperarFinTodosHilos();
			
			Traza.auditoria(ImputacionRecepcionBatchRunner.class, "---- Se ha finalizado exitosamente el Batch de Reintentos (MIC) - Imputacion de Cobros");
			
		} catch (Throwable e) {
			Traza.advertencia(ImputacionRecepcionBatchRunner.class, "---- Se ha finalizado con error en el proceso de Reintentos (MIC) - Imputacion de Cobros");
			throw new ShivaExcepcion(e);
		}
	}
	
	/**
	 * 
	 * @param cobroServicio
	 * @throws ShivaExcepcion 
	 */
	private static void procesarTareaReintentosDescobros() throws ShivaExcepcion {
		try {
			Traza.auditoria(ImputacionDescobrosBatchRunner.class, "-- Se ha iniciado el proceso de Reintentos (MIC) - Imputacion de Descobros");
			
			JmsMonitorMensajeria monitor = (JmsMonitorMensajeria) Configuracion.getBeanBatch("jmsMonitorMensajeria");
			monitor.reenviarMensajesPendientes(TipoProcesoEnum.DESCOBRO);
			
			//Esperar a que todos los hilos esten terminados 
			ControlThreadSingleton.esperarFinTodosHilos();
			
			Traza.auditoria(ImputacionDescobrosBatchRunner.class, "---- Se ha finalizado exitosamente el Batch de Reintentos (MIC) - Imputacion de Descobros");
			
		} catch (Throwable e) {
			Traza.advertencia(ImputacionDescobrosBatchRunner.class, "---- Se ha finalizado con error en el proceso de Reintentos (MIC) - Imputacion de Descobros");
			throw new ShivaExcepcion(e);
		}
	}
}
