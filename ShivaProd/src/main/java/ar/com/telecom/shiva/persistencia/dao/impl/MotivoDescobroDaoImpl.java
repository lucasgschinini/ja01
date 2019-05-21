package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoDescobroDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoDescobro;

public class MotivoDescobroDaoImpl extends Dao implements IMotivoDescobroDao {

	@SuppressWarnings("unchecked")
	public ShvParamMotivoDescobro buscarMotivoDescobro(String idMotivoDescobro) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamMotivoDescobro where idMotivoDescobro=?");
			qp.addParametro(new Integer(idMotivoDescobro));
			List<ShvParamMotivoDescobro> list = (List<ShvParamMotivoDescobro>) buscarUsandoQueryConParametros(qp);
			
			if(Validaciones.isCollectionNotEmpty(list)){
				return list.get(0);
			} else {
				return null;	
			}
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvParamMotivoDescobro> listarMotivosDescobrosPorUsoOperacionMasiva(SiNoEnum usoOperacionMasiva) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamMotivoCobro where usoOperacionMasiva=?");
			qp.addParametro(usoOperacionMasiva);
			
			List<ShvParamMotivoDescobro> list = (List<ShvParamMotivoDescobro>) buscarUsandoQueryConParametros(qp);
			return list;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

}
