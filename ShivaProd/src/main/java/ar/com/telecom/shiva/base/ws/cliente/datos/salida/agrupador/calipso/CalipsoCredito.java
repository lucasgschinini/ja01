package ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import ar.com.telecom.shiva.base.dto.SOA;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;

@SuppressWarnings("serial")
public class CalipsoCredito extends SOA {
	
	protected String	 		idClienteLegado;
	protected IdDocumento 		idDocumento;
	protected MonedaEnum 		monedaOriginalFactura;
	protected BigDecimal 		importeMonedaOrigen;
	protected BigDecimal 		importePesificado;
	protected BigDecimal 		saldoMonedaOrigen;
	protected BigDecimal		saldoPesificado;
	protected Date 				fechaEmision;
	protected Date 				fechaVencimiento;
	protected Date 				fechaUltimoMovimiento;
	protected BigDecimal		tipoCambioActual;
	protected BigInteger		idDocCtasCob;
	protected String		 	estadoMorosidad;
	protected SiNoEnum			marcaReclamo;
	protected SiNoEnum			marcaMigradoDeimos;
	protected String			idCobro;
	
	protected InformacionAdicionalTagetik informacionAdicionalTagetikCalipso = new InformacionAdicionalTagetik();
	protected InformacionAdicionalDacota informacionAdicionalDacota = new InformacionAdicionalDacota();
	
	public String getIdClienteLegado() {
		return idClienteLegado;
	}
	public void setIdClienteLegado(String idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}
	public IdDocumento getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(IdDocumento idDocumento) {
		this.idDocumento = idDocumento;
	}
	public MonedaEnum getMonedaOriginalFactura() {
		return monedaOriginalFactura;
	}
	public void setMonedaOriginalFactura(MonedaEnum monedaOriginalFactura) {
		this.monedaOriginalFactura = monedaOriginalFactura;
	}
	public BigDecimal getImporteMonedaOrigen() {
		return importeMonedaOrigen;
	}
	public void setImporteMonedaOrigen(BigDecimal importeMonedaOrigen) {
		this.importeMonedaOrigen = importeMonedaOrigen;
	}
	public BigDecimal getImportePesificado() {
		return importePesificado;
	}
	public void setImportePesificado(BigDecimal importePesificado) {
		this.importePesificado = importePesificado;
	}
	public BigDecimal getSaldoMonedaOrigen() {
		return saldoMonedaOrigen;
	}
	public void setSaldoMonedaOrigen(BigDecimal saldoMonedaOrigen) {
		this.saldoMonedaOrigen = saldoMonedaOrigen;
	}
	public BigDecimal getSaldoPesificado() {
		return saldoPesificado;
	}
	public void setSaldoPesificado(BigDecimal saldoPesificado) {
		this.saldoPesificado = saldoPesificado;
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
	public Date getFechaUltimoMovimiento() {
		return fechaUltimoMovimiento;
	}
	public void setFechaUltimoMovimiento(Date fechaUltimoMovimiento) {
		this.fechaUltimoMovimiento = fechaUltimoMovimiento;
	}
	public BigDecimal getTipoCambioActual() {
		return tipoCambioActual;
	}
	public void setTipoCambioActual(BigDecimal tipoCambioActual) {
		this.tipoCambioActual = tipoCambioActual;
	}
	public BigInteger getIdDocCtasCob() {
		return idDocCtasCob;
	}
	public void setIdDocCtasCob(BigInteger idDocCtasCob) {
		this.idDocCtasCob = idDocCtasCob;
	}
	public String getEstadoMorosidad() {
		return estadoMorosidad;
	}
	public void setEstadoMorosidad(String estadoMorosidad) {
		this.estadoMorosidad = estadoMorosidad;
	}
	public SiNoEnum getMarcaReclamo() {
		return marcaReclamo;
	}
	public void setMarcaReclamo(SiNoEnum marcaReclamo) {
		this.marcaReclamo = marcaReclamo;
	}
	public SiNoEnum getMarcaMigradoDeimos() {
		return marcaMigradoDeimos;
	}
	public void setMarcaMigradoDeimos(SiNoEnum marcaMigradoDeimos) {
		this.marcaMigradoDeimos = marcaMigradoDeimos;
	}
	public InformacionAdicionalTagetik getInformacionAdicionalTagetikCalipso() {
		return informacionAdicionalTagetikCalipso;
	}
	public void setInformacionAdicionalTagetikCalipso(
			InformacionAdicionalTagetik informacionAdicionalTagetikCalipso) {
		this.informacionAdicionalTagetikCalipso = informacionAdicionalTagetikCalipso;
	}
	public InformacionAdicionalDacota getInformacionAdicionalDacota() {
		return informacionAdicionalDacota;
	}
	public void setInformacionAdicionalDacota(
			InformacionAdicionalDacota informacionAdicionalDacota) {
		this.informacionAdicionalDacota = informacionAdicionalDacota;
	}
	public String getIdCobro() {
		return idCobro;
	}
	public void setIdCobro(String idCobro) {
		this.idCobro = idCobro;
	}
	
	
	
}
