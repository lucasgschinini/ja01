package ar.com.telecom.shiva.persistencia.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoCapDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCap;
import ar.com.telecom.shiva.persistencia.repository.DocumentoCapRepositorio;

public class DocumentoCapDaoImpl extends Dao implements IDocumentoCapDao{

	@Autowired 
	DocumentoCapRepositorio documentoCapRepositorio;
	
	@Override
	public ShvCobMedioPagoDocumentoCap buscarPorIdK2(String IdDocumento) throws PersistenciaExcepcion {
		
		ShvCobMedioPagoDocumentoCap documentoCap = documentoCapRepositorio.buscarDocumentoCapPorIDK2(IdDocumento);
		
		return documentoCap;
	}

	@Override
	public ShvCobMedioPagoDocumentoCap actualizarDocumentoCap(
			ShvCobMedioPagoDocumentoCap documentoCap) throws PersistenciaExcepcion {
		
		ShvCobMedioPagoDocumentoCap documentoCapDB = documentoCapRepositorio.save(documentoCap);
		documentoCapRepositorio.flush();
		
		return documentoCapDB;
	}

	@Override
	public ShvCobMedioPagoDocumentoCap buscarDocumentoCapPorIdCobro(Long idCobro) throws PersistenciaExcepcion {
		ShvCobMedioPagoDocumentoCap documentoCapDB = documentoCapRepositorio.buscarDocumentoCapPorIdCobro(idCobro);
		return documentoCapDB;
	}
	
	@Override
	public ShvCobMedioPagoDocumentoCap buscarPorIdK2andIdSociedadandAnioFiscal(String IdDocumento, String idSociedad, Long anioFiscal) throws PersistenciaExcepcion {
		
				
		ShvCobMedioPagoDocumentoCap documentoCapDB = documentoCapRepositorio.buscarPorIdK2andIdSociedadandAnioFiscal(IdDocumento,idSociedad,anioFiscal);
		
		return documentoCapDB;
	}

	@Override
	public ShvCobMedioPagoDocumentoCap buscarPorIdContraK2andIdSociedadContraDocandAnioFiscalContraDoc(String idContradocumento, String idSociedadContradocumento, Long anioFiscalContradocumento) throws PersistenciaExcepcion {

		ShvCobMedioPagoDocumentoCap documentoCapDB = documentoCapRepositorio.buscarPorIdContraK2andIdSociedadContraDocandAnioFiscalContraDoc(idContradocumento, idSociedadContradocumento, anioFiscalContradocumento);
		return documentoCapDB;
	}
	

}
