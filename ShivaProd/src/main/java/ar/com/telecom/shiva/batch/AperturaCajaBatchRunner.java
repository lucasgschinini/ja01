package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.ICajaServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class AperturaCajaBatchRunner {

	private AperturaCajaBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(AperturaCajaBatchRunner.class, 
						"Se ha iniciado el Batch para la apertura de cajas");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_APERTURA_CAJA_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			//Tarea 1
			procesarAperturaCaja();
			
			
		} catch (AccessControlException e) {
    		System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
    		Traza.error(AperturaCajaBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(ImputacionCobrosBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			
			System.exit(Constantes.SH_ERROR);	
    	} finally{
    		UnicaInstanciaFileKey.releaseLock();
    		
    		Traza.auditoria(AperturaCajaBatchRunner.class, 
					"Se ha finalizado el Batch para la apertura de cajas");
    	}
		
		System.exit(0);
	}
	
	/**
	 * 
	 * @param cobroServicio
	 * @throws ShivaExcepcion
	 */
	private static void procesarAperturaCaja() throws ShivaExcepcion {
		try {
			ICajaServicio cajaServicio = (ICajaServicio) Configuracion.getBeanBatch("cajaServicio");
			
			Traza.auditoria(AperturaCajaBatchRunner.class, "Se ha iniciado el proceso de apertura de cajas");
			
			cajaServicio.abrirCajas();
			
			System.out.println("Se ha finalizado exitosamente el proceso de apertura de cajas");
			Traza.auditoria(AperturaCajaBatchRunner.class, "---- Se ha finalizado exitosamente el proceso de apertura de cajas");
		
		} catch (Throwable e) {
			Traza.error(AperturaCajaBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(AperturaCajaBatchRunner.class, "---- Se ha finalizado con error en el proceso de apertura de cajas");
			throw new ShivaExcepcion(e);
		}
	}
	
}
