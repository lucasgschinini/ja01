package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfHistorialObservacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfHistorialObservacionHist;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstado;

public interface IObservacionesWorkflowDao {
	public ShvWfHistorialObservacion obtenerObservacionCorrienteBy(ShvWfWorkflowEstado workflowEstado) throws PersistenciaExcepcion;

	public void borrarObservacionCorrienteBy(ShvWfWorkflowEstado workflowEstado) throws PersistenciaExcepcion;
	
	ShvWfHistorialObservacion insertarHistorialObservacion(ShvWfHistorialObservacion historialObservacion) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idWorkflow
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvWfHistorialObservacion> listarObservacionesActualesByIdWorkflowOrdenadoPorFecha(Integer idWorkflow) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idWorkflow
	 * @return
	 */
	List<ShvWfHistorialObservacionHist> listarObservacionesHistoricasOrdenadoPorFecha(Integer idWorkflow) throws PersistenciaExcepcion;
	/**
	 * @param workflowEstado
	 * @return
	 * @throws PersistenceException
	 */
	
}
