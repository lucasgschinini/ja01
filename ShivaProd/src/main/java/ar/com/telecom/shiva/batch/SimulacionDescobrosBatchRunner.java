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
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroSimulacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;

public class SimulacionDescobrosBatchRunner {
		
	private SimulacionDescobrosBatchRunner(){ 
	}
	/**
	 * Main
	 * @throws NegocioExcepcion 
	 */
	public static void main(String[] args) throws NegocioExcepcion {
		
		double fechaHoraInicioNanoTime = System.nanoTime();

		try {			
			Traza.auditoria(SimulacionDescobrosBatchRunner.class, 
						"Se ha iniciado el batch para la simulacion de descobros");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_SIMULACION_DESCOBRO_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
		
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			simularDescobroBatch();
			
		} catch (AccessControlException e) {
			
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(SimulacionDescobrosBatchRunner.class,Mensajes.LISTENER_UNICA_INSTANCIA, e);
			System.exit(Constantes.SH_ERROR_INSTANCIA);

		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(SimulacionCobrosBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			
			System.exit(Constantes.SH_ERROR);
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(SimulacionDescobrosBatchRunner.class, 
					"Se ha finalizado el batch para la simulacion de descobros");
			
			/**Tiempo del proceso batch*/
			double elapsedTime = System.nanoTime() - fechaHoraInicioNanoTime;
			String detalle = "Tiempo de procesamiento batch de simulacion de descobros: " 
						+ ((double) elapsedTime / 1000000000.0);
			Traza.tiempo(SimulacionDescobrosBatchRunner.class, "main", detalle);
		}
		System.exit(0);
	}	

	
	/**
	 * Este metodo se encarga de simular de forma batch los cobros que se encuentran pendientes para dicha tarea
	 * @throws ShivaExcepcion
	 */
	private static void simularDescobroBatch() throws ShivaExcepcion{
	
		try {
			
			IParametroServicio parametroServicio = (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			IDescobroSimulacionServicio descobroBatchSimulacionServicio = (IDescobroSimulacionServicio) Configuracion.getBeanBatch("descobroBatchSimulacionServicio");
			IDescobroServicio descobroServicio = (IDescobroServicio) Configuracion.getBeanBatch("descobroServicio");

			List<ShvCobDescobro> descobrosPendienteSimulacionBatch = descobroServicio.buscarDescobrosPendienteSimulacionBatch();
		
			if(Validaciones.isCollectionNotEmpty(descobrosPendienteSimulacionBatch)){
				
				for (ShvCobDescobro descobroPendienteSimulacionBatch : descobrosPendienteSimulacionBatch) {
					
					Long idOperacion = descobroPendienteSimulacionBatch.getOperacion().getIdOperacion();
					
					//Comentar para subir a produccion
//					if("100440".equals(idOperacion.toString())){
					
						Traza.auditoria(SimulacionDescobrosBatchRunner.class, 
							Utilidad.reemplazarMensajes("Inicio Simulacion del descobro (idOperacion: {0})", String.valueOf(idOperacion)));
							
						try {
							descobroBatchSimulacionServicio.simularDescobroProcesamientoBatch(
									descobroPendienteSimulacionBatch,
									parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
						} catch (Exception e) {
							Traza.error(SimulacionDescobrosBatchRunner.class, 
								Utilidad.reemplazarMensajes("Se ha producido un error al simular el descobro (idOperacion: {0})", String.valueOf(idOperacion)), e);
								Traza.error(SimulacionCobrosBatchRunner.class, "------------------------------------------------------------------------");
						}
	
						Traza.auditoria(SimulacionDescobrosBatchRunner.class, 
								Utilidad.reemplazarMensajes("Finalizacion Simulacion del Descobro (idOperacion: {0})", String.valueOf(idOperacion)));
					}
//				}
			}
		
		} catch (Throwable e) {
			Traza.advertencia(SimulacionDescobrosBatchRunner.class, "--- Se ha finalizado con error en el proceso de Simulacion de Descobros ---");
			Traza.advertencia(SimulacionDescobrosBatchRunner.class, "------------------------------------------------------------------------");
			throw new ShivaExcepcion(e);
		}
	}
}
