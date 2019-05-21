package ar.com.telecom.shiva.persistencia.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineCreditoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCredito;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineCreditoRepositorio;

public class CobroOnlineCreditoDaoImpl extends Dao implements ICobroOnlineCreditoDao{
	
	@Autowired CobroOnlineCreditoRepositorio creditoRepositorio;
	
	/**
	 * Guardo el credito
	 */
	@Override
	public ShvCobEdCredito guardarCredito(ShvCobEdCredito credito)
			throws PersistenciaExcepcion {
		
		try{
			ShvCobEdCredito creditoBD = creditoRepositorio.save(credito);
			creditoRepositorio.flush();
			return creditoBD;
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Borra los creditos del cobro
	 * @param creditos
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	@SuppressWarnings("unused")
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
	public void borrarCreditosDelCobro(Long idCobro)
			throws PersistenciaExcepcion {
		try {
			int countDelete = creditoRepositorio.borrarCreditosDelCobro(idCobro);
			creditoRepositorio.flush();
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}	
	}
}
