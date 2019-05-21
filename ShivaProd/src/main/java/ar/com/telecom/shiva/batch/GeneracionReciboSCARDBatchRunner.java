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
import ar.com.telecom.shiva.negocio.servicios.IScardServicio;

public class GeneracionReciboSCARDBatchRunner {

	private GeneracionReciboSCARDBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(GeneracionReciboSCARDBatchRunner.class, 
						"Se ha iniciado el Batch para la generación de los recibos de SCARD");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_RECIBO_SCARD_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			String fechaHasta = args[0];
			//Tarea 1
			generarRecibosScard(fechaHasta);
			
		} catch (AccessControlException e) {
			
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(GeneracionReciboSCARDBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(GeneracionReciboSCARDBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(GeneracionReciboSCARDBatchRunner.class, "---- Se ha finalizado con error en el proceso para la generación de los recibos de SCARD");
			
			System.exit(Constantes.SH_ERROR);
		} finally{
			UnicaInstanciaFileKey.releaseLock();
    		Traza.auditoria(GeneracionReciboSCARDBatchRunner.class, 
					"Se ha finalizado el Batch para la generación de los recibos de SCARD");
		}
		
		System.exit(0);
	}
	
	/**
	 * Genero recibos scard
	 * @param fechaHasta 
	 * @throws ShivaExcepcion 
	 */
	private static void generarRecibosScard(String fechaHasta) throws ShivaExcepcion {
		try {
			
			IScardServicio scardServicio = (IScardServicio) Configuracion.getBeanBatch("scardServicio");
			
			Traza.advertencia(GeneracionReciboSCARDBatchRunner.class, "Se ha iniciado el proceso para la generación de los recibos de SCARD");
			
			// Proceso los registros de SCARD pendientes a fin de generar los documentos correspondientes (recibos y notas de reembolso) 
			scardServicio.generarDocumentos(fechaHasta);
    		
			System.out.println("Se ha finalizado exitosamente el proceso para la generación de los recibos de SCARD");
			Traza.advertencia(GeneracionReciboSCARDBatchRunner.class, "---- Se ha finalizado exitosamente el proceso para la generación de los recibos de SCARD");
    		
		} catch (Throwable e) {
			throw new ShivaExcepcion(e);
		}
	}
}
