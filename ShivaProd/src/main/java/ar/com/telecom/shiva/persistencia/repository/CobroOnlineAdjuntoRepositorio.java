package ar.com.telecom.shiva.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobroAdjunto;

public interface CobroOnlineAdjuntoRepositorio extends JpaRepository<ShvCobEdCobroAdjunto, Long>{

	@Query("FROM ShvCobEdCobroAdjunto where pk.idCobro = ?1")
	List<ShvCobEdCobroAdjunto> buscarAdjuntosCobrosOnline(Long idCobro);
	
	@Query("FROM ShvCobEdCobroAdjunto where pk.documentoAdjunto.idValorAdjunto = ?1")
	List<ShvCobEdCobroAdjunto> buscarPorIdAdjuntoCobrosOnline(Long idValorAdjunto);

	@Modifying
	@Query("delete FROM ShvCobEdCobroAdjunto where pk.documentoAdjunto.idValorAdjunto = ?1")
	void eliminarAdjuntoPorId(Long idAdjunto);
}
