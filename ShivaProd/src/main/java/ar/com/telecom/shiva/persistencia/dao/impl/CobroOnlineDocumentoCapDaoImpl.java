package ar.com.telecom.shiva.persistencia.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IEdDocumentoCapDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDocumentoCap;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineDocumentoCapRepositorio;

public class CobroOnlineDocumentoCapDaoImpl extends Dao implements IEdDocumentoCapDao{
	
	@Autowired CobroOnlineDocumentoCapRepositorio repositorio;
	
	/**
	 * Guardo el medioPago
	 */
	@Override
	public ShvCobEdDocumentoCap guardar(ShvCobEdDocumentoCap shvCobEdDocumentoCap) throws PersistenciaExcepcion {
		
		try {
			ShvCobEdDocumentoCap modelo = repositorio.save(shvCobEdDocumentoCap);
			repositorio.flush();
			return  modelo;
			
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	
	
	
}
