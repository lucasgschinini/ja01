package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ControlThreadSingleton;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;

public class ReconfirmacionCobrosBatchRunner {

	private ReconfirmacionCobrosBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(ReconfirmacionCobrosBatchRunner.class, 
						"Se ha iniciado el Batch para el proceso de reconfirmacion de cobros");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_RECONFIRMACION_COBRO_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			String idOperacion = args[0];
			Traza.advertencia(ReconfirmacionCobrosBatchRunner.class, "Id Operacion a reconfirmar: " + idOperacion);
			
			//Tarea 1
			procesarReconfirmacionCobros(idOperacion);
			
		} catch (AccessControlException e) {
			
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(ReconfirmacionCobrosBatchRunner.class, 
					Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			Traza.error(ReconfirmacionCobrosBatchRunner.class, e.getMessage());
			System.err.println(Utilidad.getStackTrace(e));
			System.exit(Constantes.SH_ERROR);
			
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(ReconfirmacionCobrosBatchRunner.class, 
					"Se ha finalizado el Batch para el proceso de reconfirmacion de cobros");
		}
		
		System.exit(0);
	}
	
	/**
	 * Se encarga de levantar reconfirmacion de cobros.
	 * @param idCobro 
	 * @throws ShivaExcepcion 
	 */
	private static void procesarReconfirmacionCobros(String idOperacion) throws ShivaExcepcion {
		try {
			ICobroImputacionServicio cobrosServicio = (ICobroImputacionServicio) Configuracion.getBeanBatch("cobroServicio");
			ICobroBatchSoporteImputacionServicio cobroBatchSoporteServicio = (ICobroBatchSoporteImputacionServicio) Configuracion.getBeanBatch("cobroSoporteServicio");
			Traza.auditoria(ReconfirmacionCobrosBatchRunner.class, "Se ha iniciado el proceso de reconfirmacion de cobros");
						
			// Se encarga de levantar reconfirmacion de cobros.
			cobrosServicio.reenviarConfirmacionCobros(Long.valueOf(idOperacion));
			
			//Esperar a que todos los hilos esten terminados 
			ControlThreadSingleton.esperarFinTodosHilos();
			
			//Despues de actualizar, envio un mail
			ShvCobCobro cobro = cobrosServicio.buscarCobroPorIdOperacion(Long.valueOf(idOperacion));
			if (cobro != null) { 
				// Por el manejo de la transaccion de la BD, se quedo fuera del metodo imputarCobro
				if (Estado.COB_CONFIRMADO_CON_ERROR.equals(cobro.getWorkflow().getEstado()) 
						|| !(Estado.COB_ERROR_CONFIRMACION.equals(cobro.getWorkflow().getEstado()) && cobrosServicio.contieneTransaccionesMic(cobro))) {
					try {
						cobroBatchSoporteServicio.enviarMailyGenerarTarea(cobro,null);
					} catch (Exception e){
						Traza.error(ImputacionCobrosBatchRunner.class, 
								"No se ha podido generar la tarea y enviar el mail. operacion id: " 
										+ cobro.getOperacion().getIdOperacion(), e);
					}	
				}
			}
			
			System.out.println("Se ha finalizado exitosamente el proceso de reconfirmacion de cobros");
			Traza.auditoria(ReconfirmacionCobrosBatchRunner.class, "---- Se ha finalizado exitosamente el proceso de reconfirmacion de cobros");
		
		} catch (Throwable e) {
			Traza.error(ReconfirmacionCobrosBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(ReconfirmacionCobrosBatchRunner.class, "---- Se ha finalizado con error en el proceso de reconfirmacion de cobros");
			
			throw new ShivaExcepcion(e);
		}
	}

}
