 package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class ValidacionDebitosBatchRunner {

	private ValidacionDebitosBatchRunner(){ 
	}
	
	/**
	 * Main
	 * @throws NegocioExcepcion 
	 */
	public static void main(String[] args) throws NegocioExcepcion {
		
		try {
			//ControlVariablesSingleton.permitirTraceoSQL();
			Traza.auditoria(ValidacionDebitosBatchRunner.class, 
						"Se ha iniciado el Batch para la validacion de debitos");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_VALIDACION_DEBITOS_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
			
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			validarDebitos();
			
		} catch (AccessControlException e) {
			
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(ValidacionDebitosBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);

		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(ValidacionDebitosBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			System.exit(Constantes.SH_ERROR);
			
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(ValidacionDebitosBatchRunner.class, 
					"Se ha finalizado el Batch de validacion de debitos");
		}
		
		System.exit(0);
	}
	
	
	/**
	 * 
	 * @throws NegocioExcepcion
	 */
	private static void validarDebitos() throws NegocioExcepcion{
		
		try {
			
			Traza.auditoria(ValidacionDebitosBatchRunner.class, "Se ha iniciado el proceso de validacion de registros de debitos");
			ICobroOnlineServicio cobroOnlineServicio = (ICobroOnlineServicio) Configuracion.getBeanBatch("cobroOnlineServicio");
			
			if (cobroOnlineServicio.procesarDebitosAValidar()){
				Traza.auditoria(ValidacionDebitosBatchRunner.class, "Se ha finalizado el proceso de validacion de registros de debitos");
			}
				
		} catch (Throwable e) {
			Traza.error(ValidacionDebitosBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(ValidacionDebitosBatchRunner.class, "---- Se ha finalizado con error en el proceso de validacion de registros de debitos");
		}
	}
}
