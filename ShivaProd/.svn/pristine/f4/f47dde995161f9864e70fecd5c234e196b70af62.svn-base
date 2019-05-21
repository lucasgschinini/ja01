package ar.com.telecom.shiva.persistencia.dao;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCap;

public interface IDocumentoCapDao {
	
	ShvCobMedioPagoDocumentoCap buscarPorIdK2(String IdDocumento) throws PersistenciaExcepcion;
	
	ShvCobMedioPagoDocumentoCap actualizarDocumentoCap(ShvCobMedioPagoDocumentoCap documentoCap)  throws PersistenciaExcepcion;

	ShvCobMedioPagoDocumentoCap buscarDocumentoCapPorIdCobro(Long idCobro) throws PersistenciaExcepcion;

	ShvCobMedioPagoDocumentoCap buscarPorIdK2andIdSociedadandAnioFiscal(String IdDocumento, String idSociedad, Long anioFiscal) throws PersistenciaExcepcion;
	
	ShvCobMedioPagoDocumentoCap buscarPorIdContraK2andIdSociedadContraDocandAnioFiscalContraDoc(String idContradocumento, String idSociedadContradocumento, Long anioFiscalContradocumento) throws PersistenciaExcepcion;
}
