package ar.com.telecom.shiva.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasivaAdjunto;

public interface OperacionMasivaAdjuntoRepositorio extends JpaRepository<ShvMasOperacionMasivaAdjunto, Long> {

	@Query("FROM ShvMasOperacionMasivaAdjunto where operacionMasivaAdjuntoPk.operacionMasiva.idOperacionMasiva = ?1")
	List<ShvMasOperacionMasivaAdjunto> buscarComprobantesxIdOperacionMasiva(Long idOperacionMasiva);
		
}
