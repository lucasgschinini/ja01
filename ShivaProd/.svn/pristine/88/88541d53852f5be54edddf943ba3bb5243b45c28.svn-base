package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IOperacionesTagetikServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class OperacionesTagetikBatchRunner {

	private OperacionesTagetikBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(OperacionesTagetikBatchRunner.class, 
						"Se ha iniciado el Batch para informar Operaciones tagetik");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_OPERACION_TAGETIK)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			String fechaHasta = args[0];
			//Tarea 1
			procesarOperacionesTagetik(fechaHasta);
		
		} catch (AccessControlException e) {
			
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(OperacionesTagetikBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			Traza.error(OperacionesTagetikBatchRunner.class, e.getMessage());
			System.err.println(Utilidad.getStackTrace(e));
			System.exit(Constantes.SH_ERROR);
			
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(OperacionesTagetikBatchRunner.class, 
					"Se ha finalizado el Batch para informar Operaciones tagetik");
		}
		
		System.exit(0);
	}
	
	/**
	 * Genera archivo para las operaciones Tagetik 
	 * @param fechaHasta 
	 * @throws ShivaExcepcion 
	 */
	private static void procesarOperacionesTagetik(String fechaHasta) throws ShivaExcepcion {
		try {
			IOperacionesTagetikServicio servicio =  (IOperacionesTagetikServicio) Configuracion.getBeanBatch("operacionesTagetikServicio");
			
			Traza.auditoria(OperacionesTagetikBatchRunner.class, "Se ha iniciado el proceso para informar Operaciones tagetik");
			
			servicio.generarArchivoOperacionesTagetik(fechaHasta);
			
			System.out.println("Se ha finalizado exitosamente el prceso para informar Operaciones tagetik");
			Traza.auditoria(OperacionesTagetikBatchRunner.class, "---- Se ha finalizado exitosamente el prceso para informar Operaciones tagetik");

		} catch (Throwable e) {
			Traza.error(OperacionesTagetikBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(OperacionesTagetikBatchRunner.class, "---- Se ha finalizado con error en el proceso para informar Operaciones tagetik");
			
			throw new ShivaExcepcion(e);
		}
	}
}
