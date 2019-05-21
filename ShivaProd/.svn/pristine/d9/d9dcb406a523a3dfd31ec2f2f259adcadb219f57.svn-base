package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvWfTarea;

public interface TareaRepositorio extends JpaRepository<ShvWfTarea, Long>{
	
	@Query("FROM ShvWfTarea where idWorkflow = ?1")
	ShvWfTarea buscarTareaPorIdWorkflow(Integer idWorkflow);
}
