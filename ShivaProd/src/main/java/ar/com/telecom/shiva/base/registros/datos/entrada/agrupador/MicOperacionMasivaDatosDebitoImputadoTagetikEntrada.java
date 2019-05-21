package ar.com.telecom.shiva.base.registros.datos.entrada.agrupador;

import ar.com.telecom.shiva.base.dto.REG;
import ar.com.telecom.shiva.base.enumeradores.TipoClienteTagetikEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;

@SuppressWarnings("serial")
public class MicOperacionMasivaDatosDebitoImputadoTagetikEntrada extends REG {
	private String razonSocialCliente;
	private TipoClienteTagetikEnum tipoCliente;
	//	Tipo de Cliente	Alfanumérico	2
	private String cuit;
	private Integer cicloFacturacion;
	private String marketingCustomerGroup;
	private TipoFacturaEnum tipoFactura;
		//	Tipo de Factura	Numérico	2
	//	Descripción Tipo de Factura	Alfanumérico	15

	/**
	 * @return the razonSocialCliente
	 */
	public String getRazonSocialCliente() {
		return razonSocialCliente;
	}
	/**
	 * @param razonSocialCliente the razonSocialCliente to set
	 */
	public void setRazonSocialCliente(String razonSocialCliente) {
		this.razonSocialCliente = razonSocialCliente;
	}
	/**
	 * @return the tipoCliente
	 */
	public TipoClienteTagetikEnum getTipoCliente() {
		return tipoCliente;
	}
	/**
	 * @param tipoCliente the tipoCliente to set
	 */
	public void setTipoCliente(TipoClienteTagetikEnum tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	/**
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}
	/**
	 * @param cuit the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	/**
	 * @return the cicloFacturacion
	 */
	public Integer getCicloFacturacion() {
		return cicloFacturacion;
	}
	/**
	 * @param cicloFacturacion the cicloFacturacion to set
	 */
	public void setCicloFacturacion(Integer cicloFacturacion) {
		this.cicloFacturacion = cicloFacturacion;
	}
	/**
	 * @return the marketingCustomerGroup
	 */
	public String getMarketingCustomerGroup() {
		return marketingCustomerGroup;
	}
	/**
	 * @param marketingCustomerGroup the marketingCustomerGroup to set
	 */
	public void setMarketingCustomerGroup(String marketingCustomerGroup) {
		this.marketingCustomerGroup = marketingCustomerGroup;
	}
	/**
	 * @return the tipoFactura
	 */
	public TipoFacturaEnum getTipoFactura() {
		return tipoFactura;
	}
	/**
	 * @param tipoFactura the tipoFactura to set
	 */
	public void setTipoFactura(TipoFacturaEnum tipoFactura) {
		this.tipoFactura = tipoFactura;
	}
}
