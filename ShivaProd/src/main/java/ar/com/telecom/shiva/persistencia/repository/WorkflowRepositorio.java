package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

public interface WorkflowRepositorio extends JpaRepository<ShvWfWorkflow, Integer> {
	
}
