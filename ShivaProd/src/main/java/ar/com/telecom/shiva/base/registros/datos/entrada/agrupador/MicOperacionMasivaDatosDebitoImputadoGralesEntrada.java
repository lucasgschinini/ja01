package ar.com.telecom.shiva.base.registros.datos.entrada.agrupador;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.telecom.shiva.base.dto.REG;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoConceptoTercerosEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDUCEnum;

@SuppressWarnings("serial")
public class MicOperacionMasivaDatosDebitoImputadoGralesEntrada extends REG {
	private Long cuenta;
	private TipoComprobanteEnum tipoDocumento;
	private TipoDUCEnum tipoDuc;
		//	Descripcion Tipo de DUC	Alfanumérico	20
	private EstadoDUCEnum estadoDuc;
		//	Descripción Estado del DUC	Alfanumérico	18
	private String acuerdo;
	private TipoClaseComprobanteEnum claseComprobante;
	private String sucursalComprobante;
	private String numeroComprobante;
	private Long numeroReferenciaMic;
	private Date fechaVencimiento;
	private BigDecimal importePrimerVencimiento;
	private BigDecimal importeSegundoVencimiento;
	private BigDecimal saldoPrimerVencimiento;
	private EstadoAcuerdoFacturacionEnum estadoAcuerdoFacturacion;
		//	Codigo Estado acuerdo Facturación	Numérico	2
		//	Descripcion Estado acuerdo Factuación	Alfanumérico	10
	//TODO Ver si no es el enum EstadoConceptoTercero
	private EstadoConceptoTercerosEnum estadoConceptoTerceros; //	Estado Conceptos de Terceros	Alfanumérico	1
	private SiNoEnum posibilidadDestransferencia;
	private BigDecimal importeTercerosTransferibles;
	private BigDecimal importePrimerVencimientoConTerceros;
	private BigDecimal importeSegundoVencimientoConTerceros;
	private EstadoOrigenEnum codigoEstadoFactura;
		//	Descripcion Estado Factura	Alfanumérico	20
	private MarcaReclamoEnum marcaReclamo;
	//	Codigo Marca Reclamo	Alfanumérico	1
	//	Descripcion Marca Reclamo	Alfanumérico	10
	private MarcaCyQEnum marcaCyq;
		//	Codigo Marca C&Q	Alfanumérico	1
		//	Descripcion Marca C&Q	Alfanumérico	8
	private Date fechaEmision;
	private Long numeroConvenio;
	private Long cuota;
	private Date fechaUltimoPagoParcial;
	private Date fechaPuestaCobro;

	/**
	 * @return the cuenta
	 */
	public Long getCuenta() {
		return cuenta;
	}
	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @return the tipoDocumento
	 */
	public TipoComprobanteEnum getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(TipoComprobanteEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return the tipoDuc
	 */
	public TipoDUCEnum getTipoDuc() {
		return tipoDuc;
	}
	/**
	 * @param tipoDuc the tipoDuc to set
	 */
	public void setTipoDuc(TipoDUCEnum tipoDuc) {
		this.tipoDuc = tipoDuc;
	}
	/**
	 * @return the estadoDuc
	 */
	public EstadoDUCEnum getEstadoDuc() {
		return estadoDuc;
	}
	/**
	 * @param estadoDuc the estadoDuc to set
	 */
	public void setEstadoDuc(EstadoDUCEnum estadoDuc) {
		this.estadoDuc = estadoDuc;
	}
	/**
	 * @return the acuerdo
	 */
	public String getAcuerdo() {
		return acuerdo;
	}
	/**
	 * @param acuerdo the acuerdo to set
	 */
	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}
	/**
	 * @return the claseComprobante
	 */
	public TipoClaseComprobanteEnum getClaseComprobante() {
		return claseComprobante;
	}
	/**
	 * @param claseComprobante the claseComprobante to set
	 */
	public void setClaseComprobante(TipoClaseComprobanteEnum claseComprobante) {
		this.claseComprobante = claseComprobante;
	}
	/**
	 * @return the sucursalComprobante
	 */
	public String getSucursalComprobante() {
		return sucursalComprobante;
	}
	/**
	 * @param sucursalComprobante the sucursalComprobante to set
	 */
	public void setSucursalComprobante(String sucursalComprobante) {
		this.sucursalComprobante = sucursalComprobante;
	}
	/**
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}
	/**
	 * @param numeroComprobante the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	/**
	 * @return the numeroReferenciaMic
	 */
	public Long getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}
	/**
	 * @param numeroReferenciaMic the numeroReferenciaMic to set
	 */
	public void setNumeroReferenciaMic(Long numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
	}
	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	/**
	 * @return the importePrimerVencimiento
	 */
	public BigDecimal getImportePrimerVencimiento() {
		return importePrimerVencimiento;
	}
	/**
	 * @param importePrimerVencimiento the importePrimerVencimiento to set
	 */
	public void setImportePrimerVencimiento(BigDecimal importePrimerVencimiento) {
		this.importePrimerVencimiento = importePrimerVencimiento;
	}
	/**
	 * @return the importeSegundoVencimiento
	 */
	public BigDecimal getImporteSegundoVencimiento() {
		return importeSegundoVencimiento;
	}
	/**
	 * @param importeSegundoVencimiento the importeSegundoVencimiento to set
	 */
	public void setImporteSegundoVencimiento(BigDecimal importeSegundoVencimiento) {
		this.importeSegundoVencimiento = importeSegundoVencimiento;
	}
	/**
	 * @return the saldoPrimerVencimiento
	 */
	public BigDecimal getSaldoPrimerVencimiento() {
		return saldoPrimerVencimiento;
	}
	/**
	 * @param saldoPrimerVencimiento the saldoPrimerVencimiento to set
	 */
	public void setSaldoPrimerVencimiento(BigDecimal saldoPrimerVencimiento) {
		this.saldoPrimerVencimiento = saldoPrimerVencimiento;
	}
	/**
	 * @return the estadoAcuerdoFacturacion
	 */
	public EstadoAcuerdoFacturacionEnum getEstadoAcuerdoFacturacion() {
		return estadoAcuerdoFacturacion;
	}
	/**
	 * @param estadoAcuerdoFacturacion the estadoAcuerdoFacturacion to set
	 */
	public void setEstadoAcuerdoFacturacion(
			EstadoAcuerdoFacturacionEnum estadoAcuerdoFacturacion) {
		this.estadoAcuerdoFacturacion = estadoAcuerdoFacturacion;
	}
	/**
	 * @return the estadoConceptoTerceros
	 */
	public EstadoConceptoTercerosEnum getEstadoConceptoTerceros() {
		return estadoConceptoTerceros;
	}
	/**
	 * @param estadoConceptoTerceros the estadoConceptoTerceros to set
	 */
	public void setEstadoConceptoTerceros(EstadoConceptoTercerosEnum estadoConceptoTerceros) {
		this.estadoConceptoTerceros = estadoConceptoTerceros;
	}
	/**
	 * @return the posibilidadDestransferencia
	 */
	public SiNoEnum getPosibilidadDestransferencia() {
		return posibilidadDestransferencia;
	}
	/**
	 * @param posibilidadDestransferencia the posibilidadDestransferencia to set
	 */
	public void setPosibilidadDestransferencia(SiNoEnum posibilidadDestransferencia) {
		this.posibilidadDestransferencia = posibilidadDestransferencia;
	}
	/**
	 * @return the importeTercerosTransferibles
	 */
	public BigDecimal getImporteTercerosTransferibles() {
		return importeTercerosTransferibles;
	}
	/**
	 * @param importeTercerosTransferibles the importeTercerosTransferibles to set
	 */
	public void setImporteTercerosTransferibles(
			BigDecimal importeTercerosTransferibles) {
		this.importeTercerosTransferibles = importeTercerosTransferibles;
	}
	/**
	 * @return the importePrimerVencimientoConTerceros
	 */
	public BigDecimal getImportePrimerVencimientoConTerceros() {
		return importePrimerVencimientoConTerceros;
	}
	/**
	 * @param importePrimerVencimientoConTerceros the importePrimerVencimientoConTerceros to set
	 */
	public void setImportePrimerVencimientoConTerceros(
			BigDecimal importePrimerVencimientoConTerceros) {
		this.importePrimerVencimientoConTerceros = importePrimerVencimientoConTerceros;
	}
	/**
	 * @return the importeSegundoVencimientoConTerceros
	 */
	public BigDecimal getImporteSegundoVencimientoConTerceros() {
		return importeSegundoVencimientoConTerceros;
	}
	/**
	 * @param importeSegundoVencimientoConTerceros the importeSegundoVencimientoConTerceros to set
	 */
	public void setImporteSegundoVencimientoConTerceros(
			BigDecimal importeSegundoVencimientoConTerceros) {
		this.importeSegundoVencimientoConTerceros = importeSegundoVencimientoConTerceros;
	}
	/**
	 * @return the codigoEstadoFactura
	 */
	public EstadoOrigenEnum getCodigoEstadoFactura() {
		return codigoEstadoFactura;
	}
	/**
	 * @param codigoEstadoFactura the codigoEstadoFactura to set
	 */
	public void setCodigoEstadoFactura(EstadoOrigenEnum codigoEstadoFactura) {
		this.codigoEstadoFactura = codigoEstadoFactura;
	}
	/**
	 * @return the marcaReclamo
	 */
	public MarcaReclamoEnum getMarcaReclamo() {
		return marcaReclamo;
	}
	/**
	 * @param marcaReclamo the marcaReclamo to set
	 */
	public void setMarcaReclamo(MarcaReclamoEnum marcaReclamo) {
		this.marcaReclamo = marcaReclamo;
	}
	/**
	 * @return the marcaCyq
	 */
	public MarcaCyQEnum getMarcaCyq() {
		return marcaCyq;
	}
	/**
	 * @param marcaCyq the marcaCyq to set
	 */
	public void setMarcaCyq(MarcaCyQEnum marcaCyq) {
		this.marcaCyq = marcaCyq;
	}
	/**
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	/**
	 * @return the numeroConvenio
	 */
	public Long getNumeroConvenio() {
		return numeroConvenio;
	}
	/**
	 * @param numeroConvenio the numeroConvenio to set
	 */
	public void setNumeroConvenio(Long numeroConvenio) {
		this.numeroConvenio = numeroConvenio;
	}
	/**
	 * @return the cuota
	 */
	public Long getCuota() {
		return cuota;
	}
	/**
	 * @param cuota the cuota to set
	 */
	public void setCuota(Long cuota) {
		this.cuota = cuota;
	}
	/**
	 * @return the fechaUltimoPagoParcial
	 */
	public Date getFechaUltimoPagoParcial() {
		return fechaUltimoPagoParcial;
	}
	/**
	 * @param fechaUltimoPagoParcial the fechaUltimoPagoParcial to set
	 */
	public void setFechaUltimoPagoParcial(Date fechaUltimoPagoParcial) {
		this.fechaUltimoPagoParcial = fechaUltimoPagoParcial;
	}
	/**
	 * @return the fechaPuestaCobro
	 */
	public Date getFechaPuestaCobro() {
		return fechaPuestaCobro;
	}
	/**
	 * @param fechaPuestaCobro the fechaPuestaCobro to set
	 */
	public void setFechaPuestaCobro(Date fechaPuestaCobro) {
		this.fechaPuestaCobro = fechaPuestaCobro;
	}
}
