package ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import ar.com.telecom.shiva.base.dto.SOA;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.util.IDatosComunesEntrada;

@SuppressWarnings("serial")
public class CalipsoDebito extends SOA implements IDatosComunesEntrada {
	
	protected String	 		idClienteLegado;
	protected IdDocumento 		idDocumento = new IdDocumento();
	protected Date 				fechaVencimiento;
	protected MonedaEnum 		monedaOriginalFactura;
	protected BigDecimal 		importe1erVencimientoMonedaOrigen;
	protected BigDecimal 		importe1erVencimientoPesificado;
	protected BigDecimal 		importe1erVencimientoPesificadoFechaCotizacion;
	protected BigDecimal 		saldo1erVencimientoMonedaOrigen;
	protected BigDecimal		saldoPesificado;
	protected BigDecimal 		SaldoPesificadoFechaCotizacion;
	protected String			codigoAcuerdoFacturacion;
	protected String			estadoAcuerdoFacturacion;
	protected String		 	estadoMorosidad;	    
	protected SiNoEnum			marcaReclamo;
	protected SiNoEnum			marcaMigradoDeimos;
	protected BigDecimal		tipoCambioActual;
	protected BigDecimal		tipoCambioFechaCotizacion;
	protected Date				fechaEmision;
	protected Date 				fechaUltimoPagoParcial;
	protected BigInteger		idDocCtasCob;
	protected String			idCobro;
	protected String			marcaAdhesionDebitoAutomatico;
	
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
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public MonedaEnum getMonedaOriginalFactura() {
		return monedaOriginalFactura;
	}
	public void setMonedaOriginalFactura(MonedaEnum moneda) {
		this.monedaOriginalFactura = moneda;
	}
	public BigDecimal getImporte1erVencimientoMonedaOrigen() {
		return importe1erVencimientoMonedaOrigen;
	}
	public void setImporte1erVencimientoMonedaOrigen(
			BigDecimal importe1erVencimientoMonedaOrigen) {
		this.importe1erVencimientoMonedaOrigen = importe1erVencimientoMonedaOrigen;
	}
	public BigDecimal getImporte1erVencimientoPesificado() {
		return importe1erVencimientoPesificado;
	}
	public void setImporte1erVencimientoPesificado(
			BigDecimal importe1erVencimientoPesificado) {
		this.importe1erVencimientoPesificado = importe1erVencimientoPesificado;
	}
	public BigDecimal getSaldo1erVencimientoMonedaOrigen() {
		return saldo1erVencimientoMonedaOrigen;
	}
	public void setSaldo1erVencimientoMonedaOrigen(
			BigDecimal saldo1erVencimientoMonedaOrigen) {
		this.saldo1erVencimientoMonedaOrigen = saldo1erVencimientoMonedaOrigen;
	}
	public BigDecimal getSaldoPesificado() {
		return saldoPesificado;
	}
	public void setSaldoPesificado(BigDecimal saldoPesificado) {
		this.saldoPesificado = saldoPesificado;
	}
	public String getCodigoAcuerdoFacturacion() {
		return codigoAcuerdoFacturacion;
	}
	public void setCodigoAcuerdoFacturacion(String codigoAcuerdoFacturacion) {
		this.codigoAcuerdoFacturacion = codigoAcuerdoFacturacion;
	}
	public String getEstadoAcuerdoFacturacion() {
		return estadoAcuerdoFacturacion;
	}
	public void setEstadoAcuerdoFacturacion(String estadoAcuerdoFacturacion) {
		this.estadoAcuerdoFacturacion = estadoAcuerdoFacturacion;
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
	public BigDecimal getTipoCambioActual() {
		return tipoCambioActual;
	}
	public void setTipoCambioActual(BigDecimal tipoCambioActual) {
		this.tipoCambioActual = tipoCambioActual;
	}
	public BigDecimal getTipoCambioFechaCotizacion() {
		return tipoCambioFechaCotizacion;
	}
	public void setTipoCambioFechaCotizacion(BigDecimal tipoCambioFechaCotizacion) {
		this.tipoCambioFechaCotizacion = tipoCambioFechaCotizacion;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Date getFechaUltimoPagoParcial() {
		return fechaUltimoPagoParcial;
	}
	public void setFechaUltimoPagoParcial(Date fechaUltimoPagoParcial) {
		this.fechaUltimoPagoParcial = fechaUltimoPagoParcial;
	}
	public BigInteger getIdDocCtasCob() {
		return idDocCtasCob;
	}
	public void setIdDocCtasCob(BigInteger idDocCtasCob) {
		this.idDocCtasCob = idDocCtasCob;
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
	
	@Override
	public String getNumeroReferenciaMic() {
		return null;
	}
	
	@Override
	public SistemaEnum getSistemaOrigen() {
		
		return SistemaEnum.CALIPSO;
	}
	
	@Override
	public ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento getIdDocumentoCalipso() {
		
		ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento idDocumento = new  ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento();
		idDocumento.setTipoComprobante(this.getIdDocumento().getTipoComprobante());
		idDocumento.setClaseComprobante(this.getIdDocumento().getClaseComprobante());
		idDocumento.setNumeroComprobante(this.getIdDocumento().getNumeroComprobante());
		idDocumento.setSucursalComprobante(this.getIdDocumento().getSucursalComprobante());
		
		return idDocumento;
	}
	
	@Override
	public String getIdPantalla() {
		return this.getIdDocumento().getTipoComprobante().name() + "_" + Utilidad.parsearNroDocNoDuc(this.getIdDocumento().getClaseComprobante(), this.getIdDocumento().getSucursalComprobante(), this.getIdDocumento().getNumeroComprobante());
	}
	@Override
	public String getClaseString() {
		return this.getClass().getSimpleName();
	}
	
	public String getIdCobro() {
		return idCobro;
	}
	
	public void setIdCobro(String idCobro) {
		this.idCobro = idCobro;
	}
	
	public BigDecimal getImporte1erVencimientoPesificadoFechaCotizacion() {
		return importe1erVencimientoPesificadoFechaCotizacion;
	}
	
	public void setImporte1erVencimientoPesificadoFechaCotizacion(
			BigDecimal importe1erVencimientoPesificadoFechaCotizacion) {
		this.importe1erVencimientoPesificadoFechaCotizacion = importe1erVencimientoPesificadoFechaCotizacion;
	}
	
	public BigDecimal getSaldoPesificadoFechaCotizacion() {
		return SaldoPesificadoFechaCotizacion;
	}
	
	public void setSaldoPesificadoFechaCotizacion(
			BigDecimal saldoPesificadoFechaCotizacion) {
		SaldoPesificadoFechaCotizacion = saldoPesificadoFechaCotizacion;
	}
	/**
	 * @return the marcaAdhesionDebitoAutomatico
	 */
	public String getMarcaAdhesionDebitoAutomatico() {
		return marcaAdhesionDebitoAutomatico;
	}
	/**
	 * @param marcaAdhesionDebitoAutomatico the marcaAdhesionDebitoAutomatico to set
	 */
	public void setMarcaAdhesionDebitoAutomatico(String marcaAdhesionDebitoAutomatico) {
		this.marcaAdhesionDebitoAutomatico = marcaAdhesionDebitoAutomatico;
	}

}
