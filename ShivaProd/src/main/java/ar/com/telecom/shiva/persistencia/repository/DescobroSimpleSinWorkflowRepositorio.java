package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDescobroSimpleSinWorkflow;

/**
 * 
 * @author u564030 Pablo M. Ibarrola
 *
 */
public interface DescobroSimpleSinWorkflowRepositorio  extends JpaRepository<ShvCobDescobroSimpleSinWorkflow, Long>{

}
