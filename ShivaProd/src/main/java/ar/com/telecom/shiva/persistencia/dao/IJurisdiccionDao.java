package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamJurisdiccion;

public interface IJurisdiccionDao {
	
	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvParamJurisdiccion> listarProvincias() throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idProvincia
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvParamJurisdiccion listarProvinciasPorId(String idProvincia) throws PersistenciaExcepcion;
	
}
