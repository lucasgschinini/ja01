package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IOperacionesTruncasServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class OperacionesTruncasBatchRunner {

	private OperacionesTruncasBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(OperacionesTruncasBatchRunner.class, 
						"Se ha iniciado el Batch para informar Operaciones truncas");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_OPERACION_TRUNCAS_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			String fechaHasta = args[0];
			//Tarea 1
			procesarOperacionesTruncas(fechaHasta);
			
		} catch (AccessControlException e) {
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(OperacionesTruncasBatchRunner.class, 
					Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			Traza.error(OperacionesTruncasBatchRunner.class, e.getMessage());
			System.err.println(Utilidad.getStackTrace(e));
			System.exit(Constantes.SH_ERROR);
			
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(OperacionesTruncasBatchRunner.class, 
					"Se ha finalizado el Batch para informar Operaciones truncas");
		}
		
		System.exit(0);
	}
	
	/**
	 * @param fechaHasta 
	 * @throws ShivaExcepcion 
	 *  
	 */
	private static void procesarOperacionesTruncas(String fechaHasta) throws ShivaExcepcion {
		try {
			IOperacionesTruncasServicio servicio =  (IOperacionesTruncasServicio) Configuracion.getBeanBatch("operacionesTruncasServicio");
			
			Traza.auditoria(OperacionesTruncasBatchRunner.class, "Se ha iniciado el proceso para informar Operaciones truncas");
			
			servicio.generarArchivoOperacionesTruncas(fechaHasta);
    		
			System.out.println("Se ha finalizado exitosamente el prceso para informar Operaciones truncas");
			Traza.auditoria(OperacionesTruncasBatchRunner.class, "---- Se ha finalizado exitosamente el prceso para informar Operaciones truncas");
			
		} catch (Throwable e) {
			Traza.error(OperacionesTruncasBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(OperacionesTruncasBatchRunner.class, "---- Se ha finalizado con error en el proceso para informar Operaciones truncas");
			
			throw new ShivaExcepcion(e);
		}
	}
}
