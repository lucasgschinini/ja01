package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCobroSimplificadoCodigoExternoOperacion;

/**
 * @author u578936 M.A.Uehara
 *
 */
public interface CobroOnlineSimplificadoCodigoOperacionExternaRepositorio extends JpaRepository<ShvCobEdCobroSimplificadoCodigoExternoOperacion, Long> {

	@Query("FROM ShvCobEdCobroSimplificadoCodigoExternoOperacion where idOperacion = ?1 ")
	public ShvCobEdCobroSimplificadoCodigoExternoOperacion find(Long idOperacion);
}
