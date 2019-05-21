package ar.com.telecom.shiva.presentacion.facade;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ClienteJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;

public interface IClienteFacade {
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ClienteDto> consultarClienteSiebel(ClienteFiltro filtro) throws NegocioExcepcion;
	/**
	 * 
	 * @param idClienteLegado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public String consultarClienteString(String idClienteLegado) throws NegocioExcepcion;
	/**
	 * 
	 * @param idClienteLegado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ClienteDto consultarCliente(String idClienteLegado) throws NegocioExcepcion;
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ClienteJsonResponse consultarCliente(ClienteFiltro filtro) throws NegocioExcepcion;
}
