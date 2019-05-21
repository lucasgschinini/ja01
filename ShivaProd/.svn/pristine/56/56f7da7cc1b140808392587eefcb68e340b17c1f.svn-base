package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;

public interface IParamClienteServicio {
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ClienteBean> consultarClientes(ClienteFiltro filtro) throws NegocioExcepcion;
	/**
	 * 
	 * @param listaIds
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ClienteBean> consultarClientes(List<Long> listaIds) throws NegocioExcepcion;
}
