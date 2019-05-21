package ar.com.telecom.shiva.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCodigoOperacionExterna;

public interface CobroOnlineCodigoOperacionExternaRepositorio extends JpaRepository<ShvCobEdCodigoOperacionExterna, Long> {

	@Query("FROM ShvCobEdCodigoOperacionExterna where idCobro = ?1 ")
	public List<ShvCobEdCodigoOperacionExterna> lista(Long idCobro);
	
	@Query("FROM ShvCobEdCodigoOperacionExterna where idCobro = ?1 and grupo = ?2")
	public List<ShvCobEdCodigoOperacionExterna> lista(Long idCobro, String numGrupo);
}
