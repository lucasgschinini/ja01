package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivo;

public class MotivoDaoImpl extends Dao implements IMotivoDao {

	@SuppressWarnings("unchecked")
	public ShvParamMotivo buscarMotivo(String idMotivo) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamMotivo where idMotivo=?");
			qp.addParametro(new Integer(idMotivo));
			List<ShvParamMotivo> list = (List<ShvParamMotivo>) buscarUsandoQueryConParametros(qp);
			return list.get(0);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

}
