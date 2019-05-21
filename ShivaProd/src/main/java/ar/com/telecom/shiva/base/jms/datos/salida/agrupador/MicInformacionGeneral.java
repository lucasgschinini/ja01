package ar.com.telecom.shiva.base.jms.datos.salida.agrupador;

import java.math.BigDecimal;
import java.util.Date;



public class MicInformacionGeneral {

	private BigDecimal	importe;
	private BigDecimal  saldo;
	private Date		fechaAlta;
	private Date		fechaEmision;
	private Date		fechaVencimiento;
	private Date		fechaUltimoMovimientoCobroPP;
	private String		codigoMarcaReclamo;
	private String		descripcionMarcaReclamo;
	private String		codigoMarcaCyQ;
	private String		descripcionMarcaCyQ;
	private String		codigoEstadoCredito;
	private String 		descripcionEstadoCredito;
	
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Date getFechaUltimoMovimientoCobroPP() {
		return fechaUltimoMovimientoCobroPP;
	}
	public void setFechaUltimoMovimientoCobroPP(Date fechaUltimoMovimientoCobroPP) {
		this.fechaUltimoMovimientoCobroPP = fechaUltimoMovimientoCobroPP;
	}
	public String getCodigoMarcaReclamo() {
		return codigoMarcaReclamo;
	}
	public void setCodigoMarcaReclamo(String codigoMarcaReclamo) {
		this.codigoMarcaReclamo = codigoMarcaReclamo;
	}
	public String getDescripcionMarcaReclamo() {
		return descripcionMarcaReclamo;
	}
	public void setDescripcionMarcaReclamo(String descripcionMarcaReclamo) {
		this.descripcionMarcaReclamo = descripcionMarcaReclamo;
	}
	public String getCodigoMarcaCyQ() {
		return codigoMarcaCyQ;
	}
	public void setCodigoMarcaCyQ(String codigoMarcaCyQ) {
		this.codigoMarcaCyQ = codigoMarcaCyQ;
	}
	public String getDescripcionMarcaCyQ() {
		return descripcionMarcaCyQ;
	}
	public void setDescripcionMarcaCyQ(String descripcionMarcaCyQ) {
		this.descripcionMarcaCyQ = descripcionMarcaCyQ;
	}
	public String getCodigoEstadoCredito() {
		return codigoEstadoCredito;
	}
	public void setCodigoEstadoCredito(String codigoEstadoCredito) {
		this.codigoEstadoCredito = codigoEstadoCredito;
	}
	public String getDescripcionEstadoCredito() {
		return descripcionEstadoCredito;
	}
	public void setDescripcionEstadoCredito(String descripcionEstadoCredito) {
		this.descripcionEstadoCredito = descripcionEstadoCredito;
	}
	
	
	
}
