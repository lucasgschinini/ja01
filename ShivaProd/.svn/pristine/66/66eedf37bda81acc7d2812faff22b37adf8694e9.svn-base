package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IOrigenDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrigen;

public class OrigenDaoImpl extends Dao implements IOrigenDao {

	@SuppressWarnings("unchecked")
	public ShvParamOrigen buscarOrigen(String idOrigen) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamOrigen where idOrigen=?");
			qp.addParametro(new Integer(idOrigen));
			
			List<ShvParamOrigen> list = (List<ShvParamOrigen>) buscarUsandoQueryConParametros(qp);
			if (Validaciones.isCollectionNotEmpty(list)) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvParamOrigen> listarOrigenPorIdEmpresaYIdSegmento(String idEmpresa, String idSegmento) throws PersistenciaExcepcion {
		try {
			String query = " select distinct tg.origen from ShvParamTipoGestion As tg " 
					+ " join tg.segmentoEmpresa as se "
					+ " join se.empresa as e "
					+ " join se.segmento as s "
					+ " where e.idEmpresa = ? "  
					+ " and s.idSegmento = ? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idEmpresa);
			qp.addParametro(idSegmento);
			
			List<ShvParamOrigen> listaOrigenes = buscarUsandoQueryConParametros(qp);
			
			return listaOrigenes;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvParamOrigen> listarOrigenPorIdEmpresaYIdSegmentoIdTipoValor(
			String idEmpresa, String idSegmento, String tipoValor)
			throws PersistenciaExcepcion {
		try {
			String query = " select distinct tg.origen from ShvParamTipoGestion As tg " 
					+ " join tg.segmentoEmpresa as se "
					+ " join se.empresa as e "
					+ " join se.segmento as s "
					+ " where e.idEmpresa = ? "  
					+ " and s.idSegmento = ? "
					+ " and tg.idTipoValor = ? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idEmpresa);
			qp.addParametro(idSegmento);
			qp.addParametro(new Integer(tipoValor));
			
			List<ShvParamOrigen> listaOrigenes = buscarUsandoQueryConParametros(qp);
			return listaOrigenes;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvParamOrigen> listarOrigenAnalista()
			throws PersistenciaExcepcion {
		try {

//			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamOrigen where idOrigen in (1,2,3,4)");
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamOrigen where idOrigen in (2,3,4,8)");
			List<ShvParamOrigen> list = (List<ShvParamOrigen>) buscarUsandoQueryConParametros(qp);

			return list;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
}
