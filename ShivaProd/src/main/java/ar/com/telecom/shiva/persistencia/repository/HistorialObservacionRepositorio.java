package ar.com.telecom.shiva.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvWfHistorialObservacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstado;

public interface HistorialObservacionRepositorio  extends JpaRepository<ShvWfHistorialObservacion, Long> {

	@Query("FROM ShvWfHistorialObservacion where workflowEstado = ?1 order by fechaCreacion desc")
	public List<ShvWfHistorialObservacion> obtenerObservacionesEstadoCorrienteBy(ShvWfWorkflowEstado workflowEstado);

	@Modifying
	@Query("DELETE ShvWfHistorialObservacion where idHistorialObservacion = ?1")
	public int borrarObservacionCorrienteBy(Long idHistorialObservacion);

	
}
