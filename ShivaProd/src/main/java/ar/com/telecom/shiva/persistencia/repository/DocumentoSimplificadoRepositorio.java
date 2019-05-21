package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDocumentoSimplificado;

public interface DocumentoSimplificadoRepositorio extends JpaRepository<ShvCobDocumentoSimplificado, Long>{

}
