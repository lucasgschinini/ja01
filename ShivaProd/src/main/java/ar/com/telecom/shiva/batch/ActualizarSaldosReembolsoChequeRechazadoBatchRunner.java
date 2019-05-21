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
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class ActualizarSaldosReembolsoChequeRechazadoBatchRunner {

public static void main(String[] args) throws NegocioExcepcion {
		
		try {
			//ControlVariablesSingleton.permitirTraceoSQL();
			Traza.auditoria(ActualizarSaldosReembolsoChequeRechazadoBatchRunner.class, 
						"Se ha iniciado el Batch para la actualización de saldos de documentos para reembolso");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_ACTUALIZACION_SALDOS_REEMBOLSO_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
			
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			actualizarSaldos();
			
		} catch (AccessControlException e) {
			
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(ValidacionDebitosBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);

		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(ActualizarSaldosReembolsoChequeRechazadoBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			System.exit(Constantes.SH_ERROR);
			
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(ActualizarSaldosReembolsoChequeRechazadoBatchRunner.class, 
					"Se ha finalizado el Batch para la actualización de saldos de documentos para reembolso");
		}
		
		System.exit(0);
	}

	
	private static void actualizarSaldos() throws NegocioExcepcion{
	
		try {
			
			Traza.auditoria(ActualizarSaldosReembolsoChequeRechazadoBatchRunner.class, "Se ha iniciado el proceso de actualizacion de saldos");
			ILegajoChequeRechazadoServicio legajoChequeRechazadoServicio = (ILegajoChequeRechazadoServicio) Configuracion.getBeanBatch("legajoChequeRechazadoServicio");
			
			legajoChequeRechazadoServicio.actualizarSaldosDocumentosReembolso();
		} catch (Throwable e) {
			Traza.error(ActualizarSaldosReembolsoChequeRechazadoBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(ActualizarSaldosReembolsoChequeRechazadoBatchRunner.class, "---- Se ha finalizado con error en el proceso de validacion de registros de debitos");
		}
	}



}
