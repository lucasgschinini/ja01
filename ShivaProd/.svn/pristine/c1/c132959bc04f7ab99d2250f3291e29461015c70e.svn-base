package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDebitoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebitoPk;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineDebitoRepositorio;

public class CobroOnlineDebitoDaoImpl extends Dao implements ICobroOnlineDebitoDao {
	
	@Autowired 
	CobroOnlineDebitoRepositorio debitoRepositorio;
	
	@Autowired 
	IVistaSoporteServicio vistaSoporteServicio;
	
	/**
	 * Guarda un debito
	 * @param debito
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobEdDebito guardarDebito(ShvCobEdDebito debito) throws PersistenciaExcepcion {
		try{
			ShvCobEdDebito debitoBD = debitoRepositorio.save(debito);
			debitoRepositorio.flush();
			return debitoBD;
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Borra un debito
	 * @param debito
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	@SuppressWarnings("unused")
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
	public void borrarDebitosDelCobro(Long idCobro) throws PersistenciaExcepcion {
		try {
			int countDelete = debitoRepositorio.borrarDebitosDelCobro(idCobro);
			debitoRepositorio.flush();
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Busca un debito en la base.
	 */
	
//	public ShvCobEdDebito buscarDebito(Long id) throws PersistenciaExcepcion {
//		try {
//			return debitoRepositorio.buscarDebito(id);
//			
//		} catch (Throwable e) {
//			throw new PersistenciaExcepcion(e);
//		}
//	}

	/**
	 * Retorna la lista de debitos pendientes de validacion
	 */
	@Override
	public List<ShvCobEdDebito> listarDebitosPendienteValidacion()	throws PersistenciaExcepcion {
		try {
			List<ShvCobEdDebito> list = debitoRepositorio.listarDebitosPendienteValidacion();
			return list;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> listarIdCobrosPorIdCobroYTipoComprobante(List<Long> idCobros, TipoComprobanteEnum tipoComprobante) throws PersistenciaExcepcion {
		try {
			
			String query = "select distinct deb.pk.cobro.idCobro FROM ShvCobEdDebito as deb  where deb.pk.cobro.idCobro in( ? ) and deb.tipoComprobante = ?";
			
			QueryParametrosUtil qp = new QueryParametrosUtil();			
			qp.addParametro(idCobros);
			qp.addParametro(tipoComprobante);
			qp.setSql(query);
			
			List<Long> list = (List<Long>) buscarUsandoQueryConParametros(qp); 
			return list;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@Override
	public ShvCobEdDebito buscarDebito(long idDebito, long idCobro) throws PersistenciaExcepcion {
		ShvCobEdDebitoPk pk = new ShvCobEdDebitoPk();
		pk.setIdDebito(idDebito);
		ShvCobEdCobro cobro = new ShvCobEdCobro();
		cobro.setIdCobro(idCobro);
		pk.setCobro(cobro);
		try {
			return debitoRepositorio.findOne(pk);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
}