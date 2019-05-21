package ar.com.telecom.shiva.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroDocumentoRelacionado;

public interface DescobroDocumentoRelacionadoRepositorio extends JpaRepository<ShvCobDescobroDocumentoRelacionado, Long>{

	@Query("FROM ShvCobDescobroDocumentoRelacionado where descobro.idDescobro = ?1")
	List<ShvCobDescobroDocumentoRelacionado> buscarDocumentosRelacionadosDescobro(Long idDescobro);
}
