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
import ar.com.telecom.shiva.negocio.servicios.ITeamComercialServicio;

public class ActualizarTeamComercialBatchRunner {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
 			Traza.auditoria(ActualizarTeamComercialBatchRunner.class, "INICIO - BATCH ACTUALIZACION TEAM COMERCIAL");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_ACTUALIZACION_TEAM_COMERCIAL_BATCH))
				throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);

			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			//PROCESO
			actualizarTeamComercial(args[0]);
		
		} catch (AccessControlException e) {
			Traza.error(ActualizarTeamComercialBatchRunner.class, Mensajes.LISTENER_UNICA_INSTANCIA, e);
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			System.exit(Constantes.SH_ERROR_INSTANCIA);
		} catch (ShivaExcepcion e) {
			Traza.error(ActualizarTeamComercialBatchRunner.class, e.getMessage());
			System.err.println(Utilidad.getStackTrace(e));
			System.exit(Constantes.SH_ERROR);
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(ActualizarTeamComercialBatchRunner.class, "Se ha finalizado el batch para actualizar team comercial");
		}
		System.exit(0);
	}
	
	/**
	 * 
	 * @throws ShivaExcepcion
	 */
	private static void actualizarTeamComercial(String strFechaParam) throws ShivaExcepcion {
		try {
			ITeamComercialServicio servicio =  (ITeamComercialServicio) Configuracion.getBeanBatch("teamComercialServicio");
			servicio.actualizarTeamComercial(strFechaParam);
			System.out.println("Se ha finalizado exitosamente el proceso de actualización team comercial");
			Traza.auditoria(ActualizarTeamComercialBatchRunner.class, "---- Se ha finalizado exitosamente el proceso de actualizacion team comercial");
		} catch (Throwable e) {
			Traza.error(ActualizarTeamComercialBatchRunner.class, "Se ha producido un error en el proceso de actualizacion team comercial", e);
			Traza.advertencia(ActualizarTeamComercialBatchRunner.class, "---- Se ha producido un error en el proceso de actualizacion team comercial");
			throw new ShivaExcepcion(e);
		}
	}
}
