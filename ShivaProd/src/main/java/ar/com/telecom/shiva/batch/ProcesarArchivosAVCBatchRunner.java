package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IConciliacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class ProcesarArchivosAVCBatchRunner {

	private ProcesarArchivosAVCBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		boolean resultado = false;
		
		try {
			Traza.auditoria(ProcesarArchivosAVCBatchRunner.class, 
						"Se ha iniciado el Batch para el proceso de archivos Avc");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_PROCESAR_ARCHIVO_AVC_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//ControlVariablesSingleton.permitirTraceoSQL();
			
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			//Tarea 1
			resultado = procesarTareaProcesarArchivosAvc();
			
		} catch (AccessControlException e) {
			
    		System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
    		Traza.error(ProcesarArchivosAVCBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
    		
    		System.exit(Constantes.SH_ERROR_INSTANCIA);
    	
    	} catch (ShivaExcepcion e) {
    		Traza.error(ProcesarArchivosAVCBatchRunner.class, e.getMessage());
    		System.err.println(Utilidad.getStackTrace(e));
    		System.exit(Constantes.SH_ERROR);
    		
		} finally{
    		UnicaInstanciaFileKey.releaseLock();
    		Traza.auditoria(ProcesarArchivosAVCBatchRunner.class, 
					"Se ha finalizado el Batch para el proceso de archivos Avc");
    	}
		
		if (resultado) {
			System.out.println("Se ha finalizado exitosamente, el Batch para el proceso de archivos Avc");
			Traza.auditoria(ProcesarArchivosAVCBatchRunner.class, 
					"---- Se ha finalizado exitosamente, el Batch para el proceso de archivos Avc");
			
			System.exit(0);
		} else {
			
			System.out.println("Se ha finalizado con algun error, el Batch para el proceso de archivos Avc");
			Traza.advertencia(ProcesarArchivosAVCBatchRunner.class, 
					"---- Se ha finalizado con algun error, el Batch para el proceso de archivos Avc");
			
			System.exit(Constantes.SH_ERROR);
		}
	}
	
	/**
	 * Se encarga de levantar los archivos de reversion y procesarlos uno a la vez.
	 * @throws ShivaExcepcion
	 */
	private static boolean procesarTareaProcesarArchivosAvc() throws ShivaExcepcion {
		try {
			IConciliacionServicio conciliacionServicio = (IConciliacionServicio) Configuracion.getBeanBatch("conciliacionServicio");
			
			Traza.auditoria(ProcesarArchivosAVCBatchRunner.class, "Se ha iniciado el proceso de archivos Avc");
			
			//Se encarga de levantar los archivos de reversion y procesarlos uno a la vez.
			boolean resultado = conciliacionServicio.procesarArchivosRegistrosAVC();
			return resultado;
			
		} catch (Throwable e) {
			Traza.error(ProcesarArchivosAVCBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(ProcesarArchivosAVCBatchRunner.class, "---- Se ha finalizado con error en el proceso de archivos Avc");
			
			throw new ShivaExcepcion(e);
		}
	}
	
}
