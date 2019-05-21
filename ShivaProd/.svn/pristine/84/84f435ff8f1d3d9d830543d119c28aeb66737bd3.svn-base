package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class OperacionesMasivasBatchRunner {

	private OperacionesMasivasBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(OperacionesMasivasBatchRunner.class, 
						"Se ha iniciado el Batch para operaciones masivas");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_OPERACIONES_MASIVAS_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			//Tarea 1
			procesarTareaOperacionesMasivas();
			
		} catch (AccessControlException e) {
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(OperacionesMasivasBatchRunner.class, 
					Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			Traza.error(OperacionesMasivasBatchRunner.class, e.getMessage());
			System.err.println(Utilidad.getStackTrace(e));
			System.exit(Constantes.SH_ERROR);
			
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			
			Traza.auditoria(OperacionesMasivasBatchRunner.class, 
					"Se ha finalizado el Batch para OPERACIONES MASIVAS");
		}
		
		System.exit(0);
	}
	
	/**
	 * Se encarga de levantar los archivos de operaciones masivas y procesarlos uno a la vez.
	 * 
	 * @throws ShivaExcepcion 
	 */
	private static void procesarTareaOperacionesMasivas() throws ShivaExcepcion {
		IOperacionMasivaServicio operacionMasivaServicio = null;
		try {
			operacionMasivaServicio =  (IOperacionMasivaServicio) Configuracion.getBeanBatch("operacionMasivaServicio");
			operacionMasivaServicio.generarArchivoEntradaMic();
			operacionMasivaServicio.actualizarContadoresEnOperacionesMasivas();
		} catch (Throwable e) {
			Traza.error(OperacionesMasivasBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(OperacionesMasivasBatchRunner.class, "---- Se ha finalizado con error en el proceso de Operaciones Masivas");
			throw new ShivaExcepcion(e);
		}
	}
}
