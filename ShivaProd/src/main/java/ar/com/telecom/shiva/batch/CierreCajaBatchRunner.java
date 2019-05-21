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

public class CierreCajaBatchRunner {

	private CierreCajaBatchRunner(){ 
	}
	
	private static final int FECHA = 0;
	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(CierreCajaBatchRunner.class, 
						"Se ha iniciado el Batch para la imputacion de cobros");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_CIERRE_CAJA_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			//Tarea 1
			procesarCierreCaja(args[FECHA]);
			
		} catch (AccessControlException e) {
    		System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
    		Traza.error(CierreCajaBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
    		
    		System.exit(Constantes.SH_ERROR_INSTANCIA);
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(ImputacionCobrosBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			
			System.exit(Constantes.SH_ERROR);	
    	} finally{
    		UnicaInstanciaFileKey.releaseLock();
    		
    		Traza.auditoria(CierreCajaBatchRunner.class, 
					"Se ha finalizado el Batch para cierre de cajas");
    	}
		
		System.exit(0);
	}
	
	/**
	 * 
	 * @param cobroServicio
	 * @throws ShivaExcepcion
	 */
	private static void procesarCierreCaja(String fecha) throws ShivaExcepcion {
		try {
			ICajaServicio cajaServicio = (ICajaServicio) Configuracion.getBeanBatch("cajaServicio");
			
			Traza.auditoria(CierreCajaBatchRunner.class, "Se ha iniciado el proceso de cierre de cajas");
			
			cajaServicio.cerrarCajas(fecha);
			
			System.out.println("Se ha finalizado exitosamente el proceso de cierre de cajas");
			Traza.auditoria(CierreCajaBatchRunner.class, "---- Se ha finalizado exitosamente el proceso de cierre de cajas");
		
		} catch (Throwable e) {
			Traza.error(CierreCajaBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(CierreCajaBatchRunner.class, "---- Se ha finalizado con error en el proceso de cierre de cajas");
			throw new ShivaExcepcion(e);
		}
	}
	
}
