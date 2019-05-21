package ar.com.telecom.shiva.negocio.bean;

import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;

public class NroDocumentoLegal {

	private TipoClaseComprobanteEnum clase;
	private String sucursal;
	private String numero;
	
	public NroDocumentoLegal() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the clase
	 */
	public TipoClaseComprobanteEnum getClase() {
		return clase;
	}

	/**
	 * @param clase the clase to set
	 */
	public void setClase(TipoClaseComprobanteEnum clase) {
		this.clase = clase;
	}

	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	

}
