package ar.com.telecom.shiva.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroAdjunto;

/**
 * @author u572487 Guido.Settecerze
 */
public interface DescobroAdjuntoRepositorio extends JpaRepository<ShvCobDescobroAdjunto, Long>{

	@Query("FROM ShvCobDescobroAdjunto where pk.idDescobro = ?1")
	List<ShvCobDescobroAdjunto> buscarAdjuntosDescobrosOnline(Long idDescobro);
	
	@Query("FROM ShvCobDescobroAdjunto where pk.documentoAdjunto.idValorAdjunto = ?1")
	List<ShvCobDescobroAdjunto> buscarPorIdAdjuntoDescobrosOnline(Long idValorAdjunto);
}
