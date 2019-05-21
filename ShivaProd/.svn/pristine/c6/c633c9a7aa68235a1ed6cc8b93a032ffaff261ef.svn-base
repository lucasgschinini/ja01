package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IJurisdiccionDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamJurisdiccion;

public class JurisdiccionDaoImpl extends Dao implements IJurisdiccionDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvParamJurisdiccion> listarProvincias() throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamJurisdiccion");
			List<ShvParamJurisdiccion> listaProvincia = (List<ShvParamJurisdiccion>) buscarUsandoQueryConParametros(qp);
			return listaProvincia;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ShvParamJurisdiccion listarProvinciasPorId(String idProvincia)	throws PersistenciaExcepcion {
		
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamJurisdiccion where provincia=?");
			qp.addParametro(idProvincia);
			List<ShvParamJurisdiccion> listaProvincia = (List<ShvParamJurisdiccion>) buscarUsandoQueryConParametros(qp);
			return listaProvincia.get(0);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

}
