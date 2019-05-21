package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoLegajoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoLegajo;

public class MotivoLegajoDaoImpl extends Dao implements IMotivoLegajoDao {

	@SuppressWarnings("unchecked")
	public ShvParamMotivoLegajo buscarMotivoLegajo(String idMotivoLegajo) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamMotivoLegajo where idMotivoLegajo=?");
			qp.addParametro(new Integer(idMotivoLegajo));
			List<ShvParamMotivoLegajo> list = (List<ShvParamMotivoLegajo>) buscarUsandoQueryConParametros(qp);
			return list.get(0);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvParamMotivoLegajo> listarMotivosLegajos() throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamMotivoLegajo");
			
			List<ShvParamMotivoLegajo> list = (List<ShvParamMotivoLegajo>) buscarUsandoQueryConParametros(qp);
			return list;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

}
