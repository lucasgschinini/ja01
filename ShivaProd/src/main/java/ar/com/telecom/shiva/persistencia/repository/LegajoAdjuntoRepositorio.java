
package ar.com.telecom.shiva.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvLgjAdjunto;

public interface LegajoAdjuntoRepositorio  extends JpaRepository<ShvLgjAdjunto, Long>{

	
	@Query("FROM ShvLgjAdjunto where pk.idLegajo = ?1")
	List<ShvLgjAdjunto> buscarAdjuntosLegajos(Long idLegajo);
	
	@Query("FROM ShvLgjAdjunto where pk.documentoAdjunto.idValorAdjunto = ?1")
	List<ShvLgjAdjunto> buscarPorIdAdjuntoLegajos(Long idValorAdjunto);
}
