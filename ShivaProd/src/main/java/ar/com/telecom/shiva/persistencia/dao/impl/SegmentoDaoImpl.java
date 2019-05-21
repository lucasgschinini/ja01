package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmentoEmpresa;

public class SegmentoDaoImpl extends Dao implements ISegmentoDao{

	@SuppressWarnings("unchecked")
	public ShvParamSegmento buscarSegmento(String idSegmento) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamSegmento where idSegmento= ? ");
			qp.addParametro(idSegmento);
			
			List<ShvParamSegmento> listaSegmentos = (List<ShvParamSegmento>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(listaSegmentos)){
				return listaSegmentos.get(0);
			}else{
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Devuelve los segmentos que puede ver el Usuario logueado y ademas
	 * filtrado por el idEmpresa.
	 */
	@SuppressWarnings("unchecked")
	public List<ShvParamSegmentoEmpresa> listarSegmentoPorIdEmpresa(String idEmpresa) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamSegmentoEmpresa where empresa.idEmpresa= ? ");
			qp.addParametro(idEmpresa);
			
			List<ShvParamSegmentoEmpresa> listaSegmentoEmpresa = (List<ShvParamSegmentoEmpresa>) buscarUsandoQueryConParametros(qp);
			return listaSegmentoEmpresa;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

}
