package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;

public interface DocumentoAdjuntoRepositorio extends JpaRepository<ShvDocDocumentoAdjunto, Long>{

}
