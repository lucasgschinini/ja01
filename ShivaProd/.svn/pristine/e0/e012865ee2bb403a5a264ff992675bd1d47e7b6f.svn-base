package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.presentacion.bean.filtro.EmpresaFiltro;

public interface IEmpresaDao {

	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvParamEmpresa> buscar(EmpresaFiltro filtro) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvParamEmpresa buscar(String idEmpresa) throws PersistenciaExcepcion;
}
