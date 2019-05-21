package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IOrganismoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrganismo;

public class OrganismoDaoImpl extends Dao implements IOrganismoDao {

	@SuppressWarnings("unchecked")
	public ShvParamOrganismo buscarOrganismo(String id) throws PersistenciaExcepcion {
		try {
			String query = "from ShvParamOrganismo where idOrganismo=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(id);
			
			List<ShvParamOrganismo> list = (List<ShvParamOrganismo>) buscarUsandoQueryConParametros(qp);
			if (!list.isEmpty()){
				return list.get(0);
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvParamOrganismo> listarOrganismos() throws PersistenciaExcepcion {
		try {
			String query = "from ShvParamOrganismo";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			
			List<ShvParamOrganismo> list = (List<ShvParamOrganismo>) buscarUsandoQueryConParametros(qp);
			return list;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ShvParamOrganismo buscarDescripcionAlternativa(String descripcionAlternativa) throws PersistenciaExcepcion {
		try {
			String query = "from ShvParamOrganismo where descripcionAlternativa like ?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			String parametro = Constantes.PERCENT + Constantes.SEPARADOR_PIPE+ descripcionAlternativa + Constantes.SEPARADOR_PIPE + Constantes.PERCENT;
			qp.addParametro(parametro);
			
			List<ShvParamOrganismo> list = (List<ShvParamOrganismo>) buscarUsandoQueryConParametros(qp);
			if (!list.isEmpty()){
				return list.get(0);
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

}
