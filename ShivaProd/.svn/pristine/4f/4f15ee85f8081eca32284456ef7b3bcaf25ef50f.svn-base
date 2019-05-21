package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.MotivoCobroUsoEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoCobroDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoCobro;

public class MotivoCobroDaoImpl extends Dao implements IMotivoCobroDao {

	@SuppressWarnings("unchecked")
	public ShvParamMotivoCobro buscarMotivoCobro(String idMotivoCobro) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamMotivoCobro where idMotivoCobro=?");
			qp.addParametro(new Integer(idMotivoCobro));
			List<ShvParamMotivoCobro> list = (List<ShvParamMotivoCobro>) buscarUsandoQueryConParametros(qp);
			return list.get(0);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvParamMotivoCobro> listarMotivosCobrosPorUso(MotivoCobroUsoEnum uso) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamMotivoCobro where uso=?");
			qp.addParametro(uso);
			
			List<ShvParamMotivoCobro> list = (List<ShvParamMotivoCobro>) buscarUsandoQueryConParametros(qp);
			return list;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

}
