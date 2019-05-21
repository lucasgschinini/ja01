package ar.com.telecom.shiva.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDescobroCodigoOperacionExternaSimplificado;

/**
 * 
 * @author u564030 Pablo M. Ibarrola
 *
 */
public interface DescobroCodigoOperacionExternaSimplificadoRepositorio extends JpaRepository<ShvCobDescobroCodigoOperacionExternaSimplificado, Long> {

	@Query("FROM ShvCobDescobroCodigoOperacionExternaSimplificado where idDescobro = ?1 ")
	public List<ShvCobDescobroCodigoOperacionExternaSimplificado> listarPorIdDescobro(Long idDescobro);
}
