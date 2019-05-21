package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IMedioPagoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaMic;

public class MedioPagoDaoImpl extends Dao implements IMedioPagoDao{

	/**
	 * Lista todos los cobros en estado Pendiente / En Proceso
	 */
	public List<ShvCobMedioPagoDebitoProximaFacturaCalipso> buscarMedioPagoDebProxCalipsoPorIdMedioPago(Integer idMedioPago) throws PersistenciaExcepcion {
		try {
			String query = "select mp from ShvCobMedioPagoDebitoProximaFacturaCalipso as mp "
						 + " where mp.idMedioPago = ? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idMedioPago);
			
			List<ShvCobMedioPagoDebitoProximaFacturaCalipso> resultado = (List<ShvCobMedioPagoDebitoProximaFacturaCalipso>) buscarUsandoQueryConParametros(qp);
			return resultado;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	
	public List<ShvCobMedioPagoDebitoProximaFacturaMic> buscarMedioPagoDebProxMicPorIdMedioPago(Integer idMedioPago) throws PersistenciaExcepcion {
		try {
			String query = "select mp from ShvCobMedioPagoDebitoProximaFacturaMic as mp "
						 + " where mp.idMedioPago = ? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idMedioPago);
			
			List<ShvCobMedioPagoDebitoProximaFacturaMic> resultado = (List<ShvCobMedioPagoDebitoProximaFacturaMic>) buscarUsandoQueryConParametros(qp);
			return resultado;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	
	
}



