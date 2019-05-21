package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IParamRespWfTareaDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamRespWfTarea;
import ar.com.telecom.shiva.presentacion.bean.filtro.PerfilFiltro;

public class ParamRespWfTareaDaoImpl extends Dao implements IParamRespWfTareaDao {
	

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvParamRespWfTarea> consultarPerfiles(PerfilFiltro filtro) throws PersistenciaExcepcion {
		try {
			
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamRespWfTarea where tipoTarea=?");
			qp.addParametro(filtro.getTipoTarea());

			return (List<ShvParamRespWfTarea>) buscarUsandoQueryConParametros(qp);
		} catch (Exception e) {
			Traza.error(getClass(), e.getMessage());
			throw new PersistenciaExcepcion(e);
		}
	}
	
	
	/**
	 * Método que devuelve el  perfil del responsable, se usa en la grilla de transacciones para armar el apocope.
	 **/
	@SuppressWarnings("unchecked")
	@Override
	public TipoPerfilEnum responsableImputacion(PerfilFiltro filtro) throws PersistenciaExcepcion {
		try {
			TipoPerfilEnum responsable = null;
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamRespWfTarea where idSociedad=? and sistemaOrigen=? and idSegmento =?");
			qp.addParametro(filtro.getSociedad());
			qp.addParametro(filtro.getSistema());
			qp.addParametro(filtro.getSegmento());

			List<ShvParamRespWfTarea> lista = (List<ShvParamRespWfTarea>) buscarUsandoQueryConParametros(qp);
			
			if (!lista.isEmpty()) {
				responsable = lista.get(0).getPerfil();
			}
			
			return responsable;
		} catch (Exception e) {
			Traza.error(getClass(), e.getMessage());
			throw new PersistenciaExcepcion(e);
		}
	}
}
