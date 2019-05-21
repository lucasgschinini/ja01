package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IBancoClienteDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCliente;

public class BancoClienteDaoImpl extends Dao implements IBancoClienteDao{

	@SuppressWarnings("unchecked")
	public List<ShvParamBancoCliente> buscarBancoCliente() throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil();
			String consulta = "select bancoCliente from ShvParamBancoCliente as bancoCliente";
			qp.setSql(consulta);
			List<ShvParamBancoCliente> list = (List<ShvParamBancoCliente>) buscarUsandoQueryConParametros(qp);
			
			if(Validaciones.isCollectionNotEmpty(list)){
				return list;
			}
			return null;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

}
