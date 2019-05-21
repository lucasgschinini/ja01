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
import ar.com.telecom.shiva.negocio.servicios.ISubdiarioServicio;

public class SubdiarioCobranzaBatchRunner {

	private SubdiarioCobranzaBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(SubdiarioCobranzaBatchRunner.class, 
						"Se ha iniciado el Batch para el proceso de subdiario de cobranzas");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_SUBDIARIO_COBRANZA_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			String fechaHasta = args[0];
			//Tarea 1
			generarSubdiario(fechaHasta);
			
			Traza.auditoria(SubdiarioCobranzaBatchRunner.class, 
					"Se ha finalizado exitosamente el Batch para el proceso de subdiario de cobranzas");
			
		} catch (AccessControlException e) {
			
    		System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
    		Traza.error(SubdiarioCobranzaBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
    		
    		System.exit(Constantes.SH_ERROR_INSTANCIA);
    		
    	} catch (ShivaExcepcion e) {
    		Traza.error(SubdiarioCobranzaBatchRunner.class, e.getMessage());
    		System.err.println(Utilidad.getStackTrace(e));
    		System.exit(Constantes.SH_ERROR);
    		
		} finally{
    		UnicaInstanciaFileKey.releaseLock();
    		Traza.auditoria(SubdiarioCobranzaBatchRunner.class, 
					"Se ha finalizado el Batch para el proceso de subdiario de cobranzas");
    	}
		
		System.exit(0);
	}
	
	/**
	 * Se encarga de levantar los archivos de reversion y procesarlos uno a la vez.
	 * @param fechaHasta 
	 * 
	 */
	private static void generarSubdiario(String fechaHasta) throws ShivaExcepcion {
		try {
			ISubdiarioServicio subdiarioServicio = (ISubdiarioServicio) Configuracion.getBeanBatch("subdiarioServicio");
			
			Traza.auditoria(SubdiarioCobranzaBatchRunner.class, "Se ha iniciado el proceso de subdiario de cobranzas");
			subdiarioServicio.generarSubdiario(fechaHasta);
			
			System.out.println("Se ha finalizado exitosamente el proceso de subdiario de cobranzas");
			Traza.auditoria(SubdiarioCobranzaBatchRunner.class, "---- Se ha finalizado exitosamente el proceso de subdiario de cobranzas");
		
		} catch (Throwable e) {
			Traza.error(SubdiarioCobranzaBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(SubdiarioCobranzaBatchRunner.class, "---- Se ha finalizado con error en el proceso de subdiario de cobranzas");
			throw new ShivaExcepcion(e);
		}
	}
	
}
