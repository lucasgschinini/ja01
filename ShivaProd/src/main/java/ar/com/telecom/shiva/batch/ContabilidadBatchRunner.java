package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IContabilidadServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class ContabilidadBatchRunner {

	private ContabilidadBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(ContabilidadBatchRunner.class, 
						"Se ha iniciado el Batch para la contabilidad");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_CONTABILIDAD_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			String fechaHasta = args[0];					
			//Tarea 1
			procesarContabilizar(fechaHasta);
			
		} catch (AccessControlException e) {
			
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(ContabilidadBatchRunner.class, 
					Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(ContabilidadBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(ContabilidadBatchRunner.class, "---- Se ha finalizado con error en el proceso de contabilidad");
			
			System.exit(Constantes.SH_ERROR);
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(ContabilidadBatchRunner.class, 
					"Se ha finalizado el Batch para la contabilidad");
		}
		
		System.exit(0);
	}
	
	/**
	 * 
	 * @param fechaHasta 
	 * @throws ShivaExcepcion 
	 */
	private static void procesarContabilizar(String fechaHasta) throws ShivaExcepcion {
		try {
			
			IContabilidadServicio contabilidadServicio = (IContabilidadServicio) Configuracion.getBeanBatch("contabilidadServicio");
			
			Traza.auditoria(ContabilidadBatchRunner.class, "Se ha iniciado el proceso de contabilidad");
			//Procesa el archivo de contabilidad
			String nombreArchivo = contabilidadServicio.procesarArchivoSAP(fechaHasta);
			
			System.out.println("Se ha generado el archivo de contabilidad: " + nombreArchivo);
			Traza.auditoria(ContabilidadBatchRunner.class, "Se ha generado el archivo de contabilidad: " + nombreArchivo);
			System.out.println("Se ha finalizado exitosamente el proceso de contabilidad");
			Traza.auditoria(ContabilidadBatchRunner.class, "---- Se ha finalizado exitosamente el proceso de contabilidad");
		
		} catch (Throwable e) {
			throw new ShivaExcepcion(e);
		}
	}
	
}
