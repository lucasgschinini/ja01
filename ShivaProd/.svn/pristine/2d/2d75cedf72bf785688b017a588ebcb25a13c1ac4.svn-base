package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.PersistenceException;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IValorMedioPagoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamValorMedioPago;

public class ValorMedioPagoDaoImpl extends Dao implements IValorMedioPagoDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ShvParamValorMedioPago> listarValorMedioPago() throws PersistenciaExcepcion {
		QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamValorMedioPago");
		Collection<ShvParamValorMedioPago> list = buscarUsandoQueryConParametros(qp); 
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvParamValorMedioPago> buscarPorTipoMedioPago(String tipoMedioPago) throws PersistenceException {
		try {
			String query = "from ShvParamValorMedioPago " + ((Validaciones.isNullOrEmpty(tipoMedioPago))?"":" where tipoMedioPago.idTipoMedioPago=? ");
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			if (!Validaciones.isNullOrEmpty(tipoMedioPago)) {
				qp.addParametro(tipoMedioPago);
			}
			
			List<ShvParamValorMedioPago> valoresMP = (List<ShvParamValorMedioPago>) buscarUsandoQueryConParametros(qp);
			return valoresMP;
			
		} catch (PersistenciaExcepcion e) {
			throw new PersistenceException(e.getMessage(),e);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvParamValorMedioPago> buscarPorTipoValor(String tipoValor) throws PersistenceException {
		try {
			String query = "from ShvParamValorMedioPago " + ((Validaciones.isNullOrEmpty(tipoValor))?"":" where tipoValor.idTipoValor=? ");
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			if (!Validaciones.isNullOrEmpty(tipoValor)) {
				qp.addParametro(new Long(tipoValor));
			}
			
			List<ShvParamValorMedioPago> valoresMP = (List<ShvParamValorMedioPago>) buscarUsandoQueryConParametros(qp);
			return valoresMP;
		} catch (PersistenciaExcepcion e) {
			throw new PersistenceException(e.getMessage(),e);
		}
	}


}
