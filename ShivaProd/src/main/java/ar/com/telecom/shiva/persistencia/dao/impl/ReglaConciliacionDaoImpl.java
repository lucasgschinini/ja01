package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IReglaConciliacionDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamConciliacion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamReglaConciliacion;

public class ReglaConciliacionDaoImpl extends Dao implements IReglaConciliacionDao{

	
	/**
	 * Retorna todas las reglas de conciliacion.
	 */
	@SuppressWarnings("unchecked")
	public List<ShvParamReglaConciliacion> listarReglasConciliacion() throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamReglaConciliacion");
			List<ShvParamReglaConciliacion> reglas = (List<ShvParamReglaConciliacion>)buscarUsandoQueryConParametros(qp); 
			return reglas;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Retorna todas las reglas de conciliacion.
	 */
	@SuppressWarnings("unchecked")
	public List<ShvParamConciliacion> listarConciliaciones() throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamConciliacion");
			List<ShvParamConciliacion> conciliaciones = (List<ShvParamConciliacion>)buscarUsandoQueryConParametros(qp); 
			
			return conciliaciones;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	
}
