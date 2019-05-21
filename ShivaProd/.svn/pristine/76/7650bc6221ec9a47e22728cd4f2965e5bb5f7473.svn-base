package ar.com.telecom.shiva.negocio.workflow.servicios.util;

import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfHistorialObservacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstado;



public interface IObservacionesWorkflowServicio {
	
	/**
	 * Permite agregar un registro de observación en el estado actual
	 * @param historialObservacion
	 * @return
	 */
	public ShvWfHistorialObservacion insertarHistorialObservacion(ShvWfWorkflowEstado workflowEstado, String observacion) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param workflowEstado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvWfHistorialObservacion obtenerObservacionCorrienteBy(ShvWfWorkflowEstado workflowEstado) throws NegocioExcepcion;
	/**
	 * 
	 * @param workflowEstado
	 * @throws PersistenciaExcepcion
	 */
	public void borrarObservacionCorrienteBy(ShvWfWorkflowEstado workflowEstado) throws NegocioExcepcion;

	/**
	 * Permite modificar el último registro de observación en el estado actual
	 * @param historialObservacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvWfHistorialObservacion modificarObservacionCorrienteBy(ShvWfHistorialObservacion historialObservacion) throws NegocioExcepcion;

	/**
	 * Permite recuperar los registro de observaciones actuales y los corrientes
	 * Si currienteEditable es true quiere decir que estoy en un estado del work que puedo editar la observacio.
	 * y en map.get("current") me retornara la observacion corriente
	 * y en map.get("historicos") me retona todas las observaciones menos las corrintes
	 * @param idWorkflow
	 * @param currienteEditable
	 * @return
	 * @throws NegocioExcepcion
	 */
	public Map<String, String> listarObservacionesHistoricasYActuales(Integer idWorkflow, boolean currienteEditable) throws NegocioExcepcion;
	
	/**Permite recuperar los registro de observaciones actuales y los corrientes
	 * En map.get("historicos") me retorna la observaciones menos la corrinte
	 * 
	 * @param idWorkflow
	 * @return
	 * @throws NegocioExcepcion
	 */
	public Map<String, String> listarObservacionesHistoricasYActuales(Integer idWorkflow) throws NegocioExcepcion;

	/**
	 * 
	 * @param idWorkflow
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ShvWfHistorialObservacion> listarObservacionesActualesByIdWorkflowOrdenadoPorFecha(Integer idWorkflow) throws NegocioExcepcion;
}
