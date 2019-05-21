package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IWorkflowDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.repository.WorkflowActualRepositorio;
import ar.com.telecom.shiva.persistencia.repository.WorkflowHistoricoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.WorkflowRepositorio;

public class WorkflowDaoImpl extends Dao implements IWorkflowDao {
	
	@Autowired WorkflowRepositorio workflowRepository;
	@Autowired WorkflowActualRepositorio workflowActualRepository;
	@Autowired WorkflowHistoricoRepositorio workflowHistoricoRepository;
	
	@Override
	public Collection<ShvWfWorkflow> listarWorkflow()
			throws PersistenciaExcepcion {
		try {
			Collection<ShvWfWorkflow> list = workflowRepository.findAll();
			
			return list;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public ShvWfWorkflow buscarWorkflow(Integer id)
			throws PersistenciaExcepcion {
		try {
			ShvWfWorkflow wf = workflowRepository.findOne(id);
			return wf;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public ShvWfWorkflow insertarWorkflow(ShvWfWorkflow workflow)
			throws PersistenciaExcepcion {
		try {
			ShvWfWorkflow wf = workflowRepository.save(workflow);
			workflowRepository.flush();
			return wf;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public ShvWfWorkflow actualizarWorkflow(ShvWfWorkflow workflow)
			throws PersistenciaExcepcion {
		try {
			ShvWfWorkflow wf = workflowRepository.save(workflow); 
			workflowRepository.flush();
			return wf;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
}
