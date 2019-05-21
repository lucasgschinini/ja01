package ar.com.telecom.shiva.base.jms.datos.salida.agrupador;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import ar.com.telecom.shiva.base.dto.SOA;
import ar.com.telecom.shiva.base.enumeradores.EstadoConceptoTercerosEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.util.IDatosComunesEntrada;


@SuppressWarnings("serial")
public class MicDebito extends SOA implements IDatosComunesEntrada {

	private BigInteger 			codigoCliente;
	private Integer 			cuenta;
	private TipoComprobanteEnum tipoComprobante;
	private Integer				codigoTipoDuc;
	private String				descripcionTipoDuc;
	private String				codigoEstadoDuc;
	private String				descripcionEstadoDuc;
	private String				acuerdo;
	private TipoClaseComprobanteEnum claseComprobante;
	private String				sucursalComprobante;
	private String				numeroComprobante;
	private String				numeroReferenciaMic;
	private Date				fechaVencimiento;
	private BigDecimal			importeAl1erVencimiento;
	private BigDecimal			importeAl2doVencimiento;
	private BigDecimal			saldoAl1erVencimiento;
	private Integer				codigoEstadoAcuerdoFacturacion;
	private String				descripcionEstadoAcuerdoFacturacion;
	private EstadoConceptoTercerosEnum estadoConceptosTerceros;
	private SiNoEnum			posibilidadDestransferencia;
	private BigDecimal			importe3erosTransferidos;
	private BigDecimal			importe1erVencimientoCon3eros;
	private BigDecimal			importe2erVencimientoCon3eros;
	private String 				codigoEstadoFactura;
	private String				descripcionEstadoFactura;
	private String				codigoMarcaReclamo;
	private String				descripcionMarcaReclamo;
	private String				codigoMarcaCyQ;
	private String				descripcionMarcaCyQ;
	private Date				fechaEmision;
	private Integer				numeroConvenio;
	private Integer				cuota;
	private Date				fechaUltimoPagoParcial;
	private Date				fechaPuestaAlCobro;
	
	private MicInformacionAdicionalTagetik informacionAdicionalTagetik = new MicInformacionAdicionalTagetik();
	private MicInformacionAdicionalDacota informacionAdicionalDacota = new MicInformacionAdicionalDacota();
	private MicInformacionAdicionalSaldosTerceros informacionAdicionalSaldosTerceros = new MicInformacionAdicionalSaldosTerceros();
	
	private String				idCobro;
	
	public BigInteger getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(BigInteger codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public Integer getCuenta() {
		return cuenta;
	}
	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}
	public TipoComprobanteEnum getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(TipoComprobanteEnum tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public Integer getCodigoTipoDuc() {
		return codigoTipoDuc;
	}
	public void setCodigoTipoDuc(Integer codigoTipoDuc) {
		this.codigoTipoDuc = codigoTipoDuc;
	}
	public String getDescripcionTipoDuc() {
		return descripcionTipoDuc;
	}
	public void setDescripcionTipoDuc(String descripcionTipoDuc) {
		this.descripcionTipoDuc = descripcionTipoDuc;
	}
	public String getCodigoEstadoDuc() {
		return codigoEstadoDuc;
	}
	public void setCodigoEstadoDuc(String codigoEstadoDuc) {
		this.codigoEstadoDuc = codigoEstadoDuc;
	}
	public String getDescripcionEstadoDuc() {
		return descripcionEstadoDuc;
	}
	public void setDescripcionEstadoDuc(String descripcionEstadoDuc) {
		this.descripcionEstadoDuc = descripcionEstadoDuc;
	}
	public String getAcuerdo() {
		return acuerdo;
	}
	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
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
	public String getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}
	public void setNumeroReferenciaMic(String numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public BigDecimal getImporteAl1erVencimiento() {
		return importeAl1erVencimiento;
	}
	public void setImporteAl1erVencimiento(BigDecimal importeAl1erVencimiento) {
		this.importeAl1erVencimiento = importeAl1erVencimiento;
	}
	public BigDecimal getImporteAl2doVencimiento() {
		return importeAl2doVencimiento;
	}
	public void setImporteAl2doVencimiento(BigDecimal importeAl2doVencimiento) {
		this.importeAl2doVencimiento = importeAl2doVencimiento;
	}
	public BigDecimal getSaldoAl1erVencimiento() {
		return saldoAl1erVencimiento;
	}
	public void setSaldoAl1erVencimiento(BigDecimal saldoAl1erVencimiento) {
		this.saldoAl1erVencimiento = saldoAl1erVencimiento;
	}
	public Integer getCodigoEstadoAcuerdoFacturacion() {
		return codigoEstadoAcuerdoFacturacion;
	}
	public void setCodigoEstadoAcuerdoFacturacion(
			Integer codigoEstadoAcuerdoFacturacion) {
		this.codigoEstadoAcuerdoFacturacion = codigoEstadoAcuerdoFacturacion;
	}
	public String getDescripcionEstadoAcuerdoFacturacion() {
		return descripcionEstadoAcuerdoFacturacion;
	}
	public void setDescripcionEstadoAcuerdoFacturacion(
			String descripcionEstadoAcuerdoFacturacion) {
		this.descripcionEstadoAcuerdoFacturacion = descripcionEstadoAcuerdoFacturacion;
	}
	public EstadoConceptoTercerosEnum getEstadoConceptosTerceros() {
		return estadoConceptosTerceros;
	}
	public void setEstadoConceptosTerceros(EstadoConceptoTercerosEnum estadoConceptosTerceros) {
		this.estadoConceptosTerceros = estadoConceptosTerceros;
	}
	public SiNoEnum getPosibilidadDestransferencia() {
		return posibilidadDestransferencia;
	}
	public void setPosibilidadDestransferencia(SiNoEnum posibilidadDestransferencia) {
		this.posibilidadDestransferencia = posibilidadDestransferencia;
	}
	public BigDecimal getImporte3erosTransferidos() {
		return importe3erosTransferidos;
	}
	public void setImporte3erosTransferidos(BigDecimal importe3erosTransferidos) {
		this.importe3erosTransferidos = importe3erosTransferidos;
	}
	public BigDecimal getImporte1erVencimientoCon3eros() {
		return importe1erVencimientoCon3eros;
	}
	public void setImporte1erVencimientoCon3eros(
			BigDecimal importe1erVencimientoCon3eros) {
		this.importe1erVencimientoCon3eros = importe1erVencimientoCon3eros;
	}
	public BigDecimal getImporte2erVencimientoCon3eros() {
		return importe2erVencimientoCon3eros;
	}
	public void setImporte2erVencimientoCon3eros(
			BigDecimal importe2erVencimientoCon3eros) {
		this.importe2erVencimientoCon3eros = importe2erVencimientoCon3eros;
	}
	public String getCodigoEstadoFactura() {
		return codigoEstadoFactura;
	}
	public void setCodigoEstadoFactura(String codigoEstadoFactura) {
		this.codigoEstadoFactura = codigoEstadoFactura;
	}
	public String getDescripcionEstadoFactura() {
		return descripcionEstadoFactura;
	}
	public void setDescripcionEstadoFactura(String descripcionEstadoFactura) {
		this.descripcionEstadoFactura = descripcionEstadoFactura;
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
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Integer getNumeroConvenio() {
		return numeroConvenio;
	}
	public void setNumeroConvenio(Integer numeroConvenio) {
		this.numeroConvenio = numeroConvenio;
	}
	public Integer getCuota() {
		return cuota;
	}
	public void setCuota(Integer cuota) {
		this.cuota = cuota;
	}
	public Date getFechaUltimoPagoParcial() {
		return fechaUltimoPagoParcial;
	}
	public void setFechaUltimoPagoParcial(Date fechaUltimoPagoParcial) {
		this.fechaUltimoPagoParcial = fechaUltimoPagoParcial;
	}
	public Date getFechaPuestaAlCobro() {
		return fechaPuestaAlCobro;
	}
	public void setFechaPuestaAlCobro(Date fechaPuestaAlCobro) {
		this.fechaPuestaAlCobro = fechaPuestaAlCobro;
	}
	public MicInformacionAdicionalTagetik getInformacionAdicionalTagetik() {
		return informacionAdicionalTagetik;
	}
	public void setInformacionAdicionalTagetik(
			MicInformacionAdicionalTagetik informacionAdicionalTagetik) {
		this.informacionAdicionalTagetik = informacionAdicionalTagetik;
	}
	public MicInformacionAdicionalDacota getInformacionAdicionalDacota() {
		return informacionAdicionalDacota;
	}
	public void setInformacionAdicionalDacota(
			MicInformacionAdicionalDacota informacionAdicionalDacota) {
		this.informacionAdicionalDacota = informacionAdicionalDacota;
	}
	public MicInformacionAdicionalSaldosTerceros getInformacionAdicionalSaldosTerceros() {
		return informacionAdicionalSaldosTerceros;
	}
	public void setInformacionAdicionalSaldosTerceros(
			MicInformacionAdicionalSaldosTerceros informacionAdicionalSaldosTerceros) {
		this.informacionAdicionalSaldosTerceros = informacionAdicionalSaldosTerceros;
	}
	
	@Override
	public IdDocumento getIdDocumentoCalipso() {
		ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento idDocumento = new  ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento();
		idDocumento.setTipoComprobante(this.getTipoComprobante());
		idDocumento.setClaseComprobante(this.getClaseComprobante());
		idDocumento.setNumeroComprobante(this.getNumeroComprobante());
		idDocumento.setSucursalComprobante(this.getSucursalComprobante());
		return idDocumento;	
	}
	@Override
	public SistemaEnum getSistemaOrigen() {
		return SistemaEnum.MIC;
	}
	@Override
	public String getIdPantalla() {
		return this.getTipoComprobante().name() + "_" + Utilidad.parsearNroDocNoDuc(this.getClaseComprobante(), this.getSucursalComprobante(), this.getNumeroComprobante());
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
	
}
