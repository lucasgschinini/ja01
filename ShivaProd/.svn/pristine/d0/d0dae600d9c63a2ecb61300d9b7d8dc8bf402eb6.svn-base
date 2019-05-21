package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;

public interface IClienteServicio {
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ClienteBean> consultarClientes(ClienteFiltro filtro) throws NegocioExcepcion;
	/**
	 * 
	 * @param idClienteLegado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ClienteBean consultarCliente(String idClienteLegado) throws NegocioExcepcion;
	/**
	 * 
	 * @param clienteBean
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ClienteDto mapear(ClienteBean clienteBean);
}
