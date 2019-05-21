package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSimulacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;

public class SimulacionCobrosBatchRunner {
		
	private SimulacionCobrosBatchRunner(){ 
	}

	/**
	 * Main
	 * @throws NegocioExcepcion 
	 */
	public static void main(String[] args) throws NegocioExcepcion {
		
		double fechaHoraInicioNanoTime = System.nanoTime();

		try {			
			Traza.auditoria(SimulacionCobrosBatchRunner.class, 
						"Se ha iniciado el batch para la simulacion de cobros");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_SIMULACION_COBRO_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
		
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			simularCobroBatch();
			
		} catch (AccessControlException e) {
			
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(SimulacionCobrosBatchRunner.class,Mensajes.LISTENER_UNICA_INSTANCIA, e);
			System.exit(Constantes.SH_ERROR_INSTANCIA);

		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(SimulacionCobrosBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			
			System.exit(Constantes.SH_ERROR);
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(SimulacionCobrosBatchRunner.class, 
					"Se ha finalizado el batch para la simulacion de cobros");
			
			/**Tiempo del proceso batch*/
			double elapsedTime = System.nanoTime() - fechaHoraInicioNanoTime;
			String detalle = "Tiempo de procesamiento batch de simulacion de cobros: " 
						+ ((double) elapsedTime / 1000000000.0);
			Traza.tiempo(SimulacionCobrosBatchRunner.class, "main", detalle);
		}
		System.exit(0);
	}	

	
	/**
	 * Este metodo se encarga de simular de forma batch los cobros que se encuentran pendientes para dicha tarea
	 *  
	 * @throws NegocioExcepcion
	 */
	private static void simularCobroBatch() throws ShivaExcepcion {
	
		try {
			
			IParametroServicio parametroServicio = (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			ICobroBatchSimulacionServicio cobroBatchSimulacionServicio = (ICobroBatchSimulacionServicio) Configuracion.getBeanBatch("cobroBatchSimulacionServicio");
			ICobroBatchServicio cobroBatchServicio = (ICobroBatchServicio) Configuracion.getBeanBatch("cobroBatchServicio");
	
			List<ShvCobCobro> cobrosPendienteSimulacionBatch = cobroBatchServicio.buscarCobrosPendienteSimulacionBatch();
			
			for (ShvCobCobro cobroPendienteSimulacionBatch : cobrosPendienteSimulacionBatch) {
			
				Long idOperacion = cobroPendienteSimulacionBatch.getOperacion().getIdOperacion();
			
				Traza.auditoria(SimulacionCobrosBatchRunner.class, 
						Utilidad.reemplazarMensajes("Inicio Simulacion del cobro (idOperacion: {0})", String.valueOf(idOperacion)));
					
				try {

					cobroBatchSimulacionServicio.simularCobroBatch(
													cobroPendienteSimulacionBatch,
													parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));

				} catch (Exception e) {
					Traza.error(SimulacionCobrosBatchRunner.class, 
							Utilidad.reemplazarMensajes("Se ha producido un error al simular el cobro (idOperacion: {0})", String.valueOf(idOperacion)), e);
					Traza.error(SimulacionCobrosBatchRunner.class, "------------------------------------------------------------------------");
				}

				Traza.auditoria(SimulacionCobrosBatchRunner.class, 
						Utilidad.reemplazarMensajes("Finalizacion Simulacion del cobro (idOperacion: {0})", String.valueOf(idOperacion)));
			}
		
		} catch (Throwable e) {
			Traza.advertencia(SimulacionCobrosBatchRunner.class, "--- Se ha finalizado con error en el proceso de Simulacion de Cobros ---");
			Traza.advertencia(SimulacionCobrosBatchRunner.class, "------------------------------------------------------------------------");
			throw new ShivaExcepcion(e);
		}
	}
}
