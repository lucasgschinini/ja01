package ar.com.telecom.shiva.base.ws.cliente.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSiebelConsultarClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSiebelConsultarClienteWS;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;

public interface IClienteSiebelServicio {
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ClienteBean> consultarClientes(ClienteFiltro filtro) throws NegocioExcepcion;
	
	
	/**
	 * WS: ClienteConsultar Proveedor: Siebel
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaSiebelConsultarClienteWS consultarClienteSiebel(String codigoClienteLegajo) throws NegocioExcepcion;

	/**
	 * WS: ClienteConsultar Proveedor: Siebel
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaSiebelConsultarClienteWS consultarClientes(EntradaSiebelConsultarClienteWS entradaWS) throws NegocioExcepcion;
}
