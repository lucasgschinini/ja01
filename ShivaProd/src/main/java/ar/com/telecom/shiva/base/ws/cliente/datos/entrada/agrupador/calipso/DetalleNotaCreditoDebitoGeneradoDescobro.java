package ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.OrigenDocumentoEnum;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;

public class DetalleNotaCreditoDebitoGeneradoDescobro {
	
	protected BigInteger idCobranza;
	protected BigDecimal importe;
	protected BigDecimal importeCapital;
	protected BigDecimal importeImpuestos;
	protected IdDocumento documentoGenerado;
	protected BigInteger idDocumentoCuentasCobranza;
	private Date fechaVencimiento;
	private Date fechaImputacion;
	protected BigDecimal importeAplicado;
	protected OrigenDocumentoEnum origenDelDocumento;
	
	/*************************************************
	 * Getters & Setters
	 *************************************************/
	
	public BigInteger getIdCobranza() {
		return idCobranza;
	}
	public void setIdCobranza(BigInteger idCobranza) {
		this.idCobranza = idCobranza;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public BigDecimal getImporteCapital() {
		return importeCapital;
	}
	public void setImporteCapital(BigDecimal importeCapital) {
		this.importeCapital = importeCapital;
	}
	public BigDecimal getImporteImpuestos() {
		return importeImpuestos;
	}
	public void setImporteImpuestos(BigDecimal importeImpuestos) {
		this.importeImpuestos = importeImpuestos;
	}
	public IdDocumento getDocumentoGenerado() {
		return documentoGenerado;
	}
	public void setDocumentoGenerado(IdDocumento documentoGenerado) {
		this.documentoGenerado = documentoGenerado;
	}
	public BigInteger getIdDocumentoCuentasCobranza() {
		return idDocumentoCuentasCobranza;
	}
	public void setIdDocumentoCuentasCobranza(BigInteger idDocumentoCuentasCobranza) {
		this.idDocumentoCuentasCobranza = idDocumentoCuentasCobranza;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public BigDecimal getImporteAplicado() {
		return importeAplicado;
	}
	public void setImporteAplicado(BigDecimal importeAplicado) {
		this.importeAplicado = importeAplicado;
	}
	public OrigenDocumentoEnum getOrigenDelDocumento() {
		return origenDelDocumento;
	}
	public void setOrigenDelDocumento(OrigenDocumentoEnum origenDelDocumento) {
		this.origenDelDocumento = origenDelDocumento;
	}
	public Date getFechaImputacion() {
		return fechaImputacion;
	}
	public void setFechaImputacion(Date fechaImputacion) {
		this.fechaImputacion = fechaImputacion;
	}
	
}