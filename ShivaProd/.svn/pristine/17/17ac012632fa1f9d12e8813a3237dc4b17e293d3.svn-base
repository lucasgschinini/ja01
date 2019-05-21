package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IMotivosSuspensionDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoSuspension;

public class MotivoSuspensionDaoImpl extends Dao implements IMotivosSuspensionDao{

	@Override
	@SuppressWarnings("unchecked")
	public ShvParamMotivoSuspension buscarMotivoSuspensionPorID(String idMotivo) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamMotivoSuspension where idMotivoSuspension=?");
			qp.addParametro(idMotivo);
			
			List<ShvParamMotivoSuspension> list = (List<ShvParamMotivoSuspension>) buscarUsandoQueryConParametros(qp);
			if (Validaciones.isCollectionNotEmpty(list)) {
				return list.get(0);
			} 
			return null;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ShvParamMotivoSuspension> buscarMotivoSuspensiones()	throws PersistenciaExcepcion {
		
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamMotivoSuspension");
			List<ShvParamMotivoSuspension> list = (List<ShvParamMotivoSuspension>) buscarUsandoQueryConParametros(qp);
			
			return list;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

}
