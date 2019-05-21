package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.OperacionMasivaBatchEmailEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class OperacionesMasivasSiebelBatchRunner {

	private OperacionesMasivasSiebelBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		String error ="";
		try {
			Traza.auditoria(OperacionesMasivasSiebelBatchRunner.class, 
						"Se ha iniciado el Batch para operaciones masivas Siebel");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_OPERACIONES_MASIVAS_SIEBEL_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
			
			
			for (int i=0; i <= 50; i++) {
				Traza.auditoria(ImputacionCobrosBatchRunner.class, "Corrida Nro: "+ i +" .....");
				
				//Verifico si la version es correcta
				IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
				Utilidad.verificarVersion(parametroServicio);
				
				Traza.auditoria(ImputacionCobrosBatchRunner.class, "1.- ===================================================================================");
				
				//Tarea 1
				boolean resultado = procesarTareaOperacionesMasivasSiebel();
				
				
				
				try {
					//Libero la memoria
					System.gc();
					
					if (resultado) {
						i=51;
						Traza.auditoria(ImputacionCobrosBatchRunner.class, "En esta corrida no se encuentran mas registros, por lo tanto se finaliza el proceso batch....");
						Traza.auditoria(ImputacionCobrosBatchRunner.class, "FIN ===================================================================================");
						
					} else {
						
						Traza.auditoria(ImputacionCobrosBatchRunner.class, "FIN ===================================================================================");
						Traza.auditoria(ImputacionCobrosBatchRunner.class, "EN ESPERA.....");
						Thread.sleep(10000);
						Traza.auditoria(ImputacionCobrosBatchRunner.class, "ARRANCA DE NUEVO.....");
					}
					
				} catch (InterruptedException ignore) {
					System.err.println("Error de InterruptedException");
					Traza.error(ImputacionCobrosBatchRunner.class, "Error en ciclo de loop", ignore);
				}
			}
			//Envio de mail
			IOperacionMasivaServicio operacionMasivaServicio = (IOperacionMasivaServicio) Configuracion.getBeanBatch("operacionMasivaServicio");
			try {
				operacionMasivaServicio.actualizarContadoresEnOperacionesMasivas();
			} catch (NegocioExcepcion e) {
				Traza.error(OperacionesMasivasSiebelBatchRunner.class, "Se ha produciodo un error al Actualizar contadores de Operacion masiva");
				error = Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.mail.error.actualizar.operaciones.masivas");
				throw new ShivaExcepcion(e);
			}
			
			if (!Validaciones.isObjectNull(operacionMasivaServicio)) {
				try {
					operacionMasivaServicio.enviarMailRta(
						OperacionMasivaBatchEmailEnum.SIEBEL_PROCESO_OK
					);
				} catch (NegocioExcepcion e1) {
					Traza.error(OperacionesMasivasSiebelBatchRunner.class, "No se pudo enviar el mail de error " + OperacionMasivaBatchEmailEnum.SIEBEL_PROCESO_OK.asunto);
					System.exit(Constantes.SH_ERROR);
				}
				Traza.auditoria(OperacionesMasivasSiebelBatchRunner.class, "Se envia un mail de error " + OperacionMasivaBatchEmailEnum.SIEBEL_PROCESO_OK.asunto);
			}
		} catch (AccessControlException e) {
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(OperacionesMasivasSiebelBatchRunner.class, 
					Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			Traza.error(OperacionesMasivasSiebelBatchRunner.class, e.getMessage());
			System.err.println(Utilidad.getStackTrace(e));
			//TODO POR AHORA LO INABILITO
			error = "";
			if (Validaciones.isEmptyString(error)) {
				error = "En Siebel";
			}
			IOperacionMasivaServicio operacionMasivaServicio = (IOperacionMasivaServicio) Configuracion.getBeanBatch("operacionMasivaServicio");
			if (!Validaciones.isObjectNull(operacionMasivaServicio)) {
				try {
					operacionMasivaServicio.enviarMailRta(
						OperacionMasivaBatchEmailEnum.SIEBEL_PROCESO_ERROR
					);
				} catch (NegocioExcepcion e1) {
					Traza.error(OperacionesMasivasSiebelBatchRunner.class, "No se pudo enviar el mail de error " + OperacionMasivaBatchEmailEnum.SIEBEL_PROCESO_ERROR.asunto);
					System.exit(Constantes.SH_ERROR);
				}
				Traza.auditoria(OperacionesMasivasSiebelBatchRunner.class, "Se envia un mail de error " + OperacionMasivaBatchEmailEnum.SIEBEL_PROCESO_ERROR.asunto);
			}
			System.exit(Constantes.SH_ERROR);
			
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			
			Traza.auditoria(OperacionesMasivasSiebelBatchRunner.class, 
					"Se ha finalizado el Batch para OPERACIONES MASIVAS SIEBEL");
		}
		
		System.exit(0);
	}
	
	/**
	 * Se encarga de levantar los archivos de reversion y procesarlos uno a la vez.
	 * 
	 * @throws ShivaExcepcion 
	 */
	private static boolean procesarTareaOperacionesMasivasSiebel() throws ShivaExcepcion {
		
		IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");

		try {
			int cantidadRegistrosAProcesar = parametroServicio.getCantRegistrosAProcesar().intValue();
			int cantidadRegistrosPorVuelta = parametroServicio.getCantRegistrosPorVuelta().intValue();
			
			IOperacionMasivaServicio operacionMasivaServicio = (IOperacionMasivaServicio) Configuracion.getBeanBatch("operacionMasivaServicio");
			return operacionMasivaServicio.procesarTareaOperacionesMasivasSiebel(cantidadRegistrosAProcesar,cantidadRegistrosPorVuelta);
			
		} catch (Throwable e) {
			Traza.error(OperacionesMasivasSiebelBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(OperacionesMasivasSiebelBatchRunner.class, "---- Se ha finalizado con error en el proceso de Operaciones Masivas Siebel");
			
			throw new ShivaExcepcion(e);
		}
	}
}
