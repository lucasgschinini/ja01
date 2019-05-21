package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IProcesarEntradaDetalleAplicacionesManualesServicio;

public class ProcesarEntradaDetalleAplicacionesManualesBatchRunner {

	private ProcesarEntradaDetalleAplicacionesManualesBatchRunner() {
	}

	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(ProcesarEntradaDetalleAplicacionesManualesBatchRunner.class, 
						"Se ha lanzado el Batch para procesar los archivos de entrada con aplicación manual");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_PROCESAR_ENTRADA_DETALLE_APLICACIONES_MANUALES_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			//Tarea 1
			procesarArchivoEntrada();
			
		} catch (AccessControlException e) {
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(ProcesarEntradaDetalleAplicacionesManualesBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(ProcesarEntradaDetalleAplicacionesManualesBatchRunner.class, 
    				"Se ha producido un error al procesar los archivos de entrada", e);
			System.exit(Constantes.SH_ERROR);
			
		} catch (Exception e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(ProcesarEntradaDetalleAplicacionesManualesBatchRunner.class, 
    				"Se ha producido un error desconocido", e);
			System.exit(Constantes.SH_ERROR);
		}
		finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(ProcesarEntradaDetalleAplicacionesManualesBatchRunner.class, 
					"Se ha finalizado el Batch para procesar los archivos de entrada con aplicación manual");
		}
		
		System.exit(0);
	}
	
	
	private static void procesarArchivoEntrada() throws ShivaExcepcion {
		try{
			Traza.auditoria(ProcesarEntradaDetalleAplicacionesManualesBatchRunner.class, "Se inicia el Batch para procesar archivos de entrada con aplicación manual");
			IProcesarEntradaDetalleAplicacionesManualesServicio entradaApliManualServicio = (IProcesarEntradaDetalleAplicacionesManualesServicio) Configuracion.getBeanBatch("procesarArchivoEntradaApliManualServicio");
			entradaApliManualServicio.procesarArchivosEntrada();
		} catch (Throwable e) {
			throw new ShivaExcepcion(e);
		}
	}

}