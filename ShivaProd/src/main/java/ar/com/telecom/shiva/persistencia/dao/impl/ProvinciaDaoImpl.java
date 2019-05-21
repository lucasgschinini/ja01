/**
 * 
 */
package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IProvinciaDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamProvincia;

/**
 * @author u586743
 *
 */
public class ProvinciaDaoImpl extends Dao implements IProvinciaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvParamProvincia> listarProvincias() throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamProvincia");
			List<ShvParamProvincia> listaProvincia = (List<ShvParamProvincia>) buscarUsandoQueryConParametros(qp);
			return listaProvincia;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ShvParamProvincia listarProvinciasPorId(String idProvincia)	throws PersistenciaExcepcion {
		
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamProvincia where idProvincia=?");
			qp.addParametro(idProvincia);
			List<ShvParamProvincia> listaProvincia = (List<ShvParamProvincia>) buscarUsandoQueryConParametros(qp);
			return listaProvincia.get(0);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	public ProvinciaDaoImpl() {
		// TODO Auto-generated constructor stub
	}

}
