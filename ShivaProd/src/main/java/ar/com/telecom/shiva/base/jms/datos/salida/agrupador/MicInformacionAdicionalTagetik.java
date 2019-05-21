package ar.com.telecom.shiva.base.jms.datos.salida.agrupador;

import java.math.BigDecimal;

public class MicInformacionAdicionalTagetik {

	protected String 	razonSocialCliente;
	protected String 	tipoCliente;
	protected String 	cuit;
	protected Integer 	cicloFacturacion;
	protected String 	marketingCustomerGroup;
	protected Integer	tipoFactura;
	protected String 	descripcionTipoFactura;
	protected BigDecimal importeOriginal;
	
	public String getRazonSocialCliente() {
		return razonSocialCliente;
	}
	public void setRazonSocialCliente(String razonSocialCliente) {
		this.razonSocialCliente = razonSocialCliente;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public Integer getCicloFacturacion() {
		return cicloFacturacion;
	}
	public void setCicloFacturacion(Integer cicloFacturacion) {
		this.cicloFacturacion = cicloFacturacion;
	}
	public String getMarketingCustomerGroup() {
		return marketingCustomerGroup;
	}
	public void setMarketingCustomerGroup(String marketingCustomerGroup) {
		this.marketingCustomerGroup = marketingCustomerGroup;
	}
	public Integer getTipoFactura() {
		return tipoFactura;
	}
	public void setTipoFactura(Integer tipoFactura) {
		this.tipoFactura = tipoFactura;
	}
	public BigDecimal getImporteOriginal() {
		return importeOriginal;
	}
	public void setImporteOriginal(BigDecimal importeOriginal) {
		this.importeOriginal = importeOriginal;
	}
	public String getDescripcionTipoFactura() {
		return descripcionTipoFactura;
	}
	public void setDescripcionTipoFactura(String descripcionTipoFactura) {
		this.descripcionTipoFactura = descripcionTipoFactura;
	}
	
}
