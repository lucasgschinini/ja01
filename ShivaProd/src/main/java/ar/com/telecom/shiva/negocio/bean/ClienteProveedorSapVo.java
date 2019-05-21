package ar.com.telecom.shiva.negocio.bean;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;

public class ClienteProveedorSapVo {
	
	private String codigoCUIT;
	private String codigoSociedad;
	private String codigoProveedor;
	private String codigoBloqueoProveedor;
	private String descripcionBloqueoProveedor;
	private List<ClienteDto> clienteLegado = new ArrayList<ClienteDto>();
	
	public ClienteProveedorSapVo() {
	}

	public ClienteProveedorSapVo(String codigoCUIT, List<ClienteDto> clienteLegado) {
		this.codigoCUIT = codigoCUIT;
		this.clienteLegado = clienteLegado;
	}

	public ClienteProveedorSapVo(String codigoCUIT, ClienteDto clienteLegado) {
		this.codigoCUIT = codigoCUIT;
		this.clienteLegado.add(clienteLegado);
	} 
	/**
	 * @return the codigoCUIT
	 */
	public String getCodigoCUIT() {
		return codigoCUIT;
	}

	/**
	 * @param codigoCUIT the codigoCUIT to set
	 */
	public void setCodigoCUIT(String codigoCUIT) {
		this.codigoCUIT = codigoCUIT;
	}

	/**
	 * @return the codigoSociedad
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad the codigoSociedad to set
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	/**
	 * @return the codigoProveedor
	 */
	public String getCodigoProveedor() {
		return codigoProveedor;
	}

	/**
	 * @param codigoProveedor the codigoProveedor to set
	 */
	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}

	/**
	 * @return the codigoBloqueoProveedor
	 */
	public String getCodigoBloqueoProveedor() {
		return codigoBloqueoProveedor;
	}

	/**
	 * @param codigoBloqueoProveedor the codigoBloqueoProveedor to set
	 */
	public void setCodigoBloqueoProveedor(String codigoBloqueoProveedor) {
		this.codigoBloqueoProveedor = codigoBloqueoProveedor;
	}

	/**
	 * @return the descripcionBloqueoProveedor
	 */
	public String getDescripcionBloqueoProveedor() {
		return descripcionBloqueoProveedor;
	}

	/**
	 * @param descripcionBloqueoProveedor the descripcionBloqueoProveedor to set
	 */
	public void setDescripcionBloqueoProveedor(String descripcionBloqueoProveedor) {
		this.descripcionBloqueoProveedor = descripcionBloqueoProveedor;
	}

	/**
	 * @return the clienteLegado
	 */
	public List<ClienteDto> getClienteLegado() {
		return clienteLegado;
	}

	/**
	 * @param clienteLegado the clienteLegado to set
	 */
	public void setClienteLegado(List<ClienteDto> clienteLegado) {
		this.clienteLegado = clienteLegado;
	}

	public void addCliente(ClienteDto clienteLegado) {
		this.clienteLegado.add(clienteLegado);
	}


	
}
