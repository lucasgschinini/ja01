package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCap;

public interface DocumentoCapRepositorio extends JpaRepository<ShvCobMedioPagoDocumentoCap, Integer>{
	
	@Query("FROM ShvCobMedioPagoDocumentoCap where IdDocumento = ?1")
	ShvCobMedioPagoDocumentoCap buscarDocumentoCapPorIDK2(String IdDocumento);
	
	@Query("FROM ShvCobMedioPagoDocumentoCap where IdCobro = ?1")
	ShvCobMedioPagoDocumentoCap buscarDocumentoCapPorIdCobro(Long IdCobro);
	
	@Query("FROM ShvCobMedioPagoDocumentoCap where IdDocumento = ?1 and idSociedad = ?2 and anioFiscal = ?3")
	ShvCobMedioPagoDocumentoCap buscarPorIdK2andIdSociedadandAnioFiscal(String IdDocumento, String idSociedad, Long anioFiscal);

	@Query("FROM ShvCobMedioPagoDocumentoCap where idContradocumento = ?1 and idSociedadContradocumento = ?2 and anioFiscalContradocumento = ?3")
	ShvCobMedioPagoDocumentoCap buscarPorIdContraK2andIdSociedadContraDocandAnioFiscalContraDoc(String idContradocumento, String idSociedadContradocumento, Long anioFiscalContradocumento);
}
