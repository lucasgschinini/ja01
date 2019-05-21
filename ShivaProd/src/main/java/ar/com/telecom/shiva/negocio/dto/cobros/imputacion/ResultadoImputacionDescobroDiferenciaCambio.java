package ar.com.telecom.shiva.negocio.dto.cobros.imputacion;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.OrigenDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;

/**
 * 
 * @author u573005, fabio.giaquinta.ruiz, Fase 3 Reversiones en Moneda Pesos
 *
 */
public class ResultadoImputacionDescobroDiferenciaCambio extends ResultadoImputacionDescobro {

	private Long idCobranza;
	private SistemaEnum sistemaOrigen;
	private BigDecimal importe;
	private BigDecimal importeCapital;
	private BigDecimal importeImpuestos;
	private TipoComprobanteEnum tipoComprobante;	
	private TipoClaseComprobanteEnum claseComprobante;	
	private String  sucursalComprobante;	
	private String  numeroComprobante;	
	private Long idDocumentoCuentasCobranza;
	private Date fechaVencimiento;
	private Date fechaImputacion;
	private BigDecimal importeAplicado;
	private OrigenDocumentoEnum origenDocumento;
	
	//Datos de la diferencia de cambio del cobro que 
	//sirven para encontrar el documento original 
	//y en esa transaccion agregar el contradocumento de diferencia de cambio
	private TipoComprobanteEnum tipoComprobanteOriginal;	
	private TipoClaseComprobanteEnum claseComprobanteOriginal;	
	private String  sucursalComprobanteOriginal;	
	private String  numeroComprobanteOriginal;	
	private Long idDocumentoCuentasCobranzaOriginal;
	
	public Long getIdCobranza() {
		return idCobranza;
	}
	public void setIdCobranza(Long idCobranza) {
		this.idCobranza = idCobranza;
	}
	public TipoComprobanteEnum getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(TipoComprobanteEnum tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public TipoClaseComprobanteEnum getClaseComprobante() {
		return claseComprobante;
	}
	public void setClaseComprobante(TipoClaseComprobanteEnum claseComprobante) {
		this.claseComprobante = claseComprobante;
	}
	public String getSucursalComprobante() {
		return sucursalComprobante;
	}
	public void setSucursalComprobante(String sucursalComprobante) {
		this.sucursalComprobante = sucursalComprobante;
	}
	public String getNumeroComprobante() {
		return numeroComprobante;
	}
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	public Long getIdDocumentoCuentasCobranza() {
		return idDocumentoCuentasCobranza;
	}
	public void setIdDocumentoCuentasCobranza(Long idDocumentoCuentasCobranza) {
		this.idDocumentoCuentasCobranza = idDocumentoCuentasCobranza;
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

	public TipoComprobanteEnum getTipoComprobanteOriginal() {
		return tipoComprobanteOriginal;
	}
	public void setTipoComprobanteOriginal(
			TipoComprobanteEnum tipoComprobanteOriginal) {
		this.tipoComprobanteOriginal = tipoComprobanteOriginal;
	}
	public TipoClaseComprobanteEnum getClaseComprobanteOriginal() {
		return claseComprobanteOriginal;
	}
	public void setClaseComprobanteOriginal(
			TipoClaseComprobanteEnum claseComprobanteOriginal) {
		this.claseComprobanteOriginal = claseComprobanteOriginal;
	}
	public String getSucursalComprobanteOriginal() {
		return sucursalComprobanteOriginal;
	}
	public void setSucursalComprobanteOriginal(String sucursalComprobanteOriginal) {
		this.sucursalComprobanteOriginal = sucursalComprobanteOriginal;
	}
	public String getNumeroComprobanteOriginal() {
		return numeroComprobanteOriginal;
	}
	public void setNumeroComprobanteOriginal(String numeroComprobanteOriginal) {
		this.numeroComprobanteOriginal = numeroComprobanteOriginal;
	}
	public Long getIdDocumentoCuentasCobranzaOriginal() {
		return idDocumentoCuentasCobranzaOriginal;
	}
	public void setIdDocumentoCuentasCobranzaOriginal(
			Long idDocumentoCuentasCobranzaOriginal) {
		this.idDocumentoCuentasCobranzaOriginal = idDocumentoCuentasCobranzaOriginal;
	}
	
	@Override
	public String getIdBusquedaRespuestaCobrador() {
		if(!Validaciones.isObjectNull(tipoComprobanteOriginal)
				&& !Validaciones.isObjectNull(claseComprobanteOriginal)){			
			return tipoComprobanteOriginal.name() + claseComprobanteOriginal.name() + sucursalComprobanteOriginal + numeroComprobanteOriginal;
		} else {
			return "";
		}
	}
	public OrigenDocumentoEnum getOrigenDocumento() {
		return origenDocumento;
	}
	public void setOrigenDocumento(OrigenDocumentoEnum origenDocumento) {
		this.origenDocumento = origenDocumento;
	}
	public Date getFechaImputacion() {
		return fechaImputacion;
	}
	public void setFechaImputacion(Date fechaImputacion) {
		this.fechaImputacion = fechaImputacion;
	}
	public SistemaEnum getSistemaOrigen() {
		return sistemaOrigen;
	}
	public void setSistemaOrigen(SistemaEnum sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}
	
}