package ar.com.telecom.shiva.persistencia.bean;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;

public class VistaSoporteResultadoBusquedaLegajoDetalleDocumento extends VistaSoporteResultadoBusqueda implements Bean {
	private static final long serialVersionUID = 20170724L;

	private Long						idOperacion;
	private Long						idChequeRechazado;
	private SistemaEnum					sistemaOrigen;
	private SistemaEnum					sistemaImputacion;
	private String						numeroReferenciaMic;
	private Long						idClienteLegado;

	private String						tipoComprobante;
	
	private String						numeroLegal;

	private MonedaEnum					monedaDocumento;

	private BigDecimal					importeTotalDocumento;
	private BigDecimal					saldoDocumentoLuegoAplicarCheque;
	private BigDecimal					importeAplicadoDelCheque;
	private BigDecimal					saldoActualDocumento;
	private Date						fechaConsultaSaldo;
	

	public VistaSoporteResultadoBusquedaLegajoDetalleDocumento() {
	}

	/**
	 * @return the idOperacion
	 */
	public Long getIdOperacion() {
		return idOperacion;
	}

	/**
	 * @param idOperacion the idOperacion to set
	 */
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	/**
	 * @return the idChequeRechazado
	 */
	public Long getIdChequeRechazado() {
		return idChequeRechazado;
	}

	/**
	 * @param idChequeRechazado the idChequeRechazado to set
	 */
	public void setIdChequeRechazado(Long idChequeRechazado) {
		this.idChequeRechazado = idChequeRechazado;
	}

	/**
	 * @return the sistemaOrigen
	 */
	public SistemaEnum getSistemaOrigen() {
		return sistemaOrigen;
	}

	/**
	 * @param sistemaOrigen the sistemaOrigen to set
	 */
	public void setSistemaOrigen(SistemaEnum sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}

	/**
	 * @return the sistemaImputacion
	 */
	public SistemaEnum getSistemaImputacion() {
		return sistemaImputacion;
	}

	/**
	 * @param sistemaImputacion the sistemaImputacion to set
	 */
	public void setSistemaImputacion(SistemaEnum sistemaImputacion) {
		this.sistemaImputacion = sistemaImputacion;
	}

	/**
	 * @return the numeroReferenciaMic
	 */
	public String getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}

	/**
	 * @param numeroReferenciaMic the numeroReferenciaMic to set
	 */
	public void setNumeroReferenciaMic(String numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
	}

	/**
	 * @return the idClienteLegado
	 */
	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	/**
	 * @param idClienteLegado the idClienteLegado to set
	 */
	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	/**
	 * @return the tipoComprobante
	 */
	public String getTipoComprobante() {
		return tipoComprobante;
	}

	/**
	 * @param tipoComprobante the tipoComprobante to set
	 */
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}


	/**
	 * @return the numeroLegal
	 */
	public String getNumeroLegal() {
		return numeroLegal;
	}

	/**
	 * @param numeroLegal the numeroLegal to set
	 */
	public void setNumeroLegal(String numeroLegal) {
		this.numeroLegal = numeroLegal;
	}

	/**
	 * @return the monedaDocumento
	 */
	public MonedaEnum getMonedaDocumento() {
		return monedaDocumento;
	}

	/**
	 * @param monedaDocumento the monedaDocumento to set
	 */
	public void setMonedaDocumento(MonedaEnum monedaDocumento) {
		this.monedaDocumento = monedaDocumento;
	}

	/**
	 * @return the importeTotalDocumento
	 */
	public BigDecimal getImporteTotalDocumento() {
		return importeTotalDocumento;
	}

	/**
	 * @param importeTotalDocumento the importeTotalDocumento to set
	 */
	public void setImporteTotalDocumento(BigDecimal importeTotalDocumento) {
		this.importeTotalDocumento = importeTotalDocumento;
	}

	/**
	 * @return the saldoDocumentoLuegoAplicarCheque
	 */
	public BigDecimal getSaldoDocumentoLuegoAplicarCheque() {
		return saldoDocumentoLuegoAplicarCheque;
	}

	/**
	 * @param saldoDocumentoLuegoAplicarCheque the saldoDocumentoLuegoAplicarCheque to set
	 */
	public void setSaldoDocumentoLuegoAplicarCheque(
			BigDecimal saldoDocumentoLuegoAplicarCheque) {
		this.saldoDocumentoLuegoAplicarCheque = saldoDocumentoLuegoAplicarCheque;
	}

	/**
	 * @return the importeAplicadoDelCheque
	 */
	public BigDecimal getImporteAplicadoDelCheque() {
		return importeAplicadoDelCheque;
	}

	/**
	 * @param importeAplicadoDelCheque the importeAplicadoDelCheque to set
	 */
	public void setImporteAplicadoDelCheque(BigDecimal importeAplicadoDelCheque) {
		this.importeAplicadoDelCheque = importeAplicadoDelCheque;
	}

	/**
	 * @return the saldoActualDocumento
	 */
	public BigDecimal getSaldoActualDocumento() {
		return saldoActualDocumento;
	}

	/**
	 * @param saldoActualDocumento the saldoActualDocumento to set
	 */
	public void setSaldoActualDocumento(BigDecimal saldoActualDocumento) {
		this.saldoActualDocumento = saldoActualDocumento;
	}

	/**
	 * @return the fechaConsultaSaldo
	 */
	public Date getFechaConsultaSaldo() {
		return fechaConsultaSaldo;
	}

	/**
	 * @param fechaConsultaSaldo the fechaConsultaSaldo to set
	 */
	public void setFechaConsultaSaldo(Date fechaConsultaSaldo) {
		this.fechaConsultaSaldo = fechaConsultaSaldo;
	}
}