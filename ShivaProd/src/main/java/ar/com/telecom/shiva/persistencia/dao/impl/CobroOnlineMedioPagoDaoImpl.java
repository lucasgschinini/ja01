package ar.com.telecom.shiva.persistencia.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineMedioPagoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosMedioPago;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineDocumentoCapRepositorio;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineMedioPagoClienteRepositorio;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineMedioPagoRepositorio;

public class CobroOnlineMedioPagoDaoImpl extends Dao implements ICobroOnlineMedioPagoDao{
	
	@Autowired CobroOnlineMedioPagoRepositorio medioPagoRepositorio;
	@Autowired CobroOnlineMedioPagoClienteRepositorio medioPagoClienteRepositorio;
	@Autowired CobroOnlineDocumentoCapRepositorio documentoCapRepositorio; 
	/**
	 * Guardo el medioPago
	 */
	@Override
	public ShvCobEdOtrosMedioPago guardarMedioPago(ShvCobEdOtrosMedioPago medioPago)
			throws PersistenciaExcepcion {
		
		try {
			ShvCobEdOtrosMedioPago medioPagoBD = medioPagoRepositorio.save(medioPago);
			medioPagoRepositorio.flush();
			return medioPagoBD;
			
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Borra los medioPago del cobro y sus hijos
	 * @param medioPago
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	@SuppressWarnings("unused")
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
	public void borrarMediosPagoDelCobro(Long idCobro)
			throws PersistenciaExcepcion {
		try {
			int countDelete1 = medioPagoClienteRepositorio.borrarMediosPagoClienteDelCobro(idCobro);
			medioPagoClienteRepositorio.flush();
			documentoCapRepositorio.borrarDocumentosCapsDelMedioDePagoDelCobro(idCobro);
			documentoCapRepositorio.flush();
			int countDelete = medioPagoRepositorio.borrarMediosPagoDelCobro(idCobro);
			medioPagoRepositorio.flush();
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
		
	}
}
