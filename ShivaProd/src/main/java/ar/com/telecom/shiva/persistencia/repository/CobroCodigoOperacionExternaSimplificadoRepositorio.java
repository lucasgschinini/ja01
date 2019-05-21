package ar.com.telecom.shiva.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCodigoOperacionExternaSimplificado;

public interface CobroCodigoOperacionExternaSimplificadoRepositorio extends JpaRepository<ShvCobEdCodigoOperacionExternaSimplificado, Long> {

	@Query("FROM ShvCobEdCodigoOperacionExternaSimplificado where idCobro = ?1 ")
	public List<ShvCobEdCodigoOperacionExternaSimplificado> listarPorIdCobro(Long idCobro);
}
