package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.facturas;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class InformacionAdicionalTagetikMIC extends Object implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=true)
	private String razonSocialCliente;
	@XmlElement(required=true)
	private String tipoCliente;
	@XmlElement(required=true)
	private String cuit;
	@XmlElement(required=true)
	private int cicloFacturacion;
	@XmlElement(required=true)
	private int fechaEmision;
	@XmlElement(required=true)
	private String marketingCustomerGroup;
	@XmlElement(required=true)
	private Integer tipoFactura;
	@XmlElement(required=true)
	private Long fechaVencimiento;
	
	
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


	/**
	 * @return the fechaVencimiento
	 */
	public Long getFechaVencimiento() {
		return fechaVencimiento;
	}


	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Long fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public int getCicloFacturacion() {
		return cicloFacturacion;
	}
	public void setCicloFacturacion(int cicloFacturacion) {
		this.cicloFacturacion = cicloFacturacion;
	}
	public int getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(int fechaEmision) {
		this.fechaEmision = fechaEmision;
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
}
