package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;
import java.util.Set;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TeamComercialDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public interface ITeamComercialServicio {
	
	/**
	 * Actualiza la tabla de team comercial.
	 * @throws NegocioExcepcion
	 */
	public void actualizarTeamComercial(String strFechaParam) throws NegocioExcepcion;
	
	public TeamComercialDto buscarTeamComercial(String nroDeCliente) throws NegocioExcepcion;

	/**
	 * 
	 * @param clientes
	 * @throws NegocioExcepcion
	 */
	public List<String> listarIdAnalistaTeamComercialPorListaClientes(Set<ShvCobEdCliente> clientes) throws NegocioExcepcion;
	/**
	 * 
	 * @param clientes
	 * @return
	 * @throws NegocioExcepcion
	 */
	public Set<String> listarIdAnalistaTeamComercialPorListaClientesDto(Set<ClienteDto> clientes) throws NegocioExcepcion;
	/**
	 * 
	 * @param clientes
	 * @return
	 * @throws NegocioExcepcion
	 */
	public UsuarioLdapDto obtenerAnalistaTeamComercial(Set<ClienteDto> clientes) throws NegocioExcepcion;
}
