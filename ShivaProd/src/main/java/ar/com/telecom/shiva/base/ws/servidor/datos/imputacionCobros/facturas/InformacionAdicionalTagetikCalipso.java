package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.facturas;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class InformacionAdicionalTagetikCalipso extends Object implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=true)
	private String razonSocialCliente;
	@XmlElement(required=true)
	private String tipoCliente;
	@XmlElement(required=true)
	private String cuit;
	@XmlElement(required=true)
	private BigDecimal importeOriginalPesificado;
	@XmlElement(required=true)
	private BigDecimal saldoActualPesificado;
	@XmlElement(required=true)
	private String monedaOriginalFactura;
	@XmlElement(required=true)
	private BigDecimal tipoCambioActual;
	@XmlElement(required=true)
	private Long fechaVencimiento;
	
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
	/**
	 * @return the razonSocial
	 */
	public String getRazonSocialCliente() {
		return razonSocialCliente;
	}
	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocialCliente(String razonSocialCliente) {
		this.razonSocialCliente = razonSocialCliente;
	}
	/**
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}
	/**
	 * @param tipoCliente the tipoCliente to set
	 */
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
	 * @return the importeOriginalPesificado
	 */
	public BigDecimal getImporteOriginalPesificado() {
		return importeOriginalPesificado;
	}
	/**
	 * @param importeOriginalPesificado the importeOriginalPesificado to set
	 */
	public void setImporteOriginalPesificado(BigDecimal importeOriginalPesificado) {
		this.importeOriginalPesificado = importeOriginalPesificado;
	}
	/**
	 * @return the saldoActualPesificado
	 */
	public BigDecimal getSaldoActualPesificado() {
		return saldoActualPesificado;
	}
	/**
	 * @param saldoActualPesificado the saldoActualPesificado to set
	 */
	public void setSaldoActualPesificado(BigDecimal saldoActualPesificado) {
		this.saldoActualPesificado = saldoActualPesificado;
	}
	/**
	 * @return the monedaOriginalFactura
	 */
	public String getMonedaOriginalFactura() {
		return monedaOriginalFactura;
	}
	/**
	 * @param monedaOriginalFactura the monedaOriginalFactura to set
	 */
	public void setMonedaOriginalFactura(String monedaOriginalFactura) {
		this.monedaOriginalFactura = monedaOriginalFactura;
	}
	/**
	 * @return the tipoCambioActual
	 */
	public BigDecimal getTipoCambioActual() {
		return tipoCambioActual;
	}
	/**
	 * @param tipoCambioActual the tipoCambioActual to set
	 */
	public void setTipoCambioActual(BigDecimal tipoCambioActual) {
		this.tipoCambioActual = tipoCambioActual;
	}
}
