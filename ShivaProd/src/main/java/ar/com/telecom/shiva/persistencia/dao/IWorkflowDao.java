package ar.com.telecom.shiva.persistencia.dao;

import java.util.Collection;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

public interface IWorkflowDao {

	Collection<ShvWfWorkflow> listarWorkflow() throws PersistenciaExcepcion;
	
	ShvWfWorkflow buscarWorkflow(Integer id) throws PersistenciaExcepcion;
	
	ShvWfWorkflow insertarWorkflow(ShvWfWorkflow workflow) throws PersistenciaExcepcion;
	
	ShvWfWorkflow actualizarWorkflow(ShvWfWorkflow workflow) throws PersistenciaExcepcion;

}
