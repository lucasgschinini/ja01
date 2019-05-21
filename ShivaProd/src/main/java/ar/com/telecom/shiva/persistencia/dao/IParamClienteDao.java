package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamCliente;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;

public interface IParamClienteDao {
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvParamCliente> consultarClientes(ClienteFiltro filtro) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param listaIds
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvParamCliente> consultarClientes(List<Long> listaIds) throws PersistenciaExcepcion;
}
