package ar.com.telecom.shiva.presentacion.bean.dto;

import java.math.BigDecimal;

public class DocumentoCapBd extends PartidaDto {

	private static final long serialVersionUID = 20161230L;

	private BigDecimal importeGestionableBg = BigDecimal.ZERO;
	// Nota 1. Se utiliza para el calculo de Importe Gestioble por diferencia de cambio
	private BigDecimal importeGestionableTotalBd;
	// Importe gestinable
	private BigDecimal importeGestionableAFechaShivaTotalBd;
	private BigDecimal importeGestionableAFechaEmisionTotalBd = BigDecimal.ZERO;
	// Nota 1. FIN
	//private String saldoMonedaOrigen;				// Saldo moneda origen TODO
	private BigDecimal saldoMonedaOrigenBd;
	
	private BigDecimal saldoPesificadoBd;

	private BigDecimal saldoPesificadoTotalAFechaShivaBd = BigDecimal.ZERO;
	private BigDecimal saldoPesificadoTotalAFechaEmisionBd = BigDecimal.ZERO;
	
	private BigDecimal importeMonedaOrigenDocAsociadoBd;
	private BigDecimal importePesificadoDocAsociadoBd;
	private BigDecimal importePesificadoDocAsociadoEmisionBd;
	private BigDecimal importeGestionableEmisionBd;

	
	public DocumentoCapBd() {}
	public DocumentoCapBd(DocumentoCapDto dto) {
		super(dto);
		
	}
	/**
	 * @return the importeGestionableBg
	 */
	public BigDecimal getImporteGestionableBg() {
		return importeGestionableBg;
	}
	/**
	 * @param importeGestionableBg the importeGestionableBg to set
	 */
	public void setImporteGestionableBg(BigDecimal importeGestionableBg) {
		this.importeGestionableBg = importeGestionableBg;
	}
	/**
	 * @return the importeGestionableTotalBd
	 */
	public BigDecimal getImporteGestionableTotalBd() {
		return importeGestionableTotalBd;
	}
	/**
	 * @param importeGestionableTotalBd the importeGestionableTotalBd to set
	 */
	public void setImporteGestionableTotalBd(BigDecimal importeGestionableTotalBd) {
		this.importeGestionableTotalBd = importeGestionableTotalBd;
	}
	/**
	 * @return the importeGestionableAFechaShivaTotalBd
	 */
	public BigDecimal getImporteGestionableAFechaShivaTotalBd() {
		return importeGestionableAFechaShivaTotalBd;
	}

	/**
	 * @param importeGestionableAFechaShivaTotalBd the importeGestionableAFechaShivaTotalBd to set
	 */
	public void setImporteGestionableAFechaShivaTotalBd(
			BigDecimal importeGestionableAFechaShivaTotalBd) {
		this.importeGestionableAFechaShivaTotalBd = importeGestionableAFechaShivaTotalBd;
	}
	/**
	 * @return the importeGestionableAFechaEmisionTotalBd
	 */
	public BigDecimal getImporteGestionableAFechaEmisionTotalBd() {
		return importeGestionableAFechaEmisionTotalBd;
	}
	/**
	 * @param importeGestionableAFechaEmisionTotalBd the importeGestionableAFechaEmisionTotalBd to set
	 */
	public void setImporteGestionableAFechaEmisionTotalBd(
			BigDecimal importeGestionableAFechaEmisionTotalBd) {
		this.importeGestionableAFechaEmisionTotalBd = importeGestionableAFechaEmisionTotalBd;
	}
	/**
	 * @return the saldoMonedaOrigenBd
	 */
	public BigDecimal getSaldoMonedaOrigenBd() {
		return saldoMonedaOrigenBd;
	}
	/**
	 * @param saldoMonedaOrigenBd the saldoMonedaOrigenBd to set
	 */
	public void setSaldoMonedaOrigenBd(BigDecimal saldoMonedaOrigenBd) {
		this.saldoMonedaOrigenBd = saldoMonedaOrigenBd;
	}
	/**
	 * @return the saldoPesificadoBd
	 */
	public BigDecimal getSaldoPesificadoBd() {
		return saldoPesificadoBd;
	}
	/**
	 * @param saldoPesificadoBd the saldoPesificadoBd to set
	 */
	public void setSaldoPesificadoBd(BigDecimal saldoPesificadoBd) {
		this.saldoPesificadoBd = saldoPesificadoBd;
	}
	/**
	 * @return the saldoPesificadoTotalAFechaShivaBd
	 */
	public BigDecimal getSaldoPesificadoTotalAFechaShivaBd() {
		return saldoPesificadoTotalAFechaShivaBd;
	}
	/**
	 * @param saldoPesificadoTotalAFechaShivaBd the saldoPesificadoTotalAFechaShivaBd to set
	 */
	public void setSaldoPesificadoTotalAFechaShivaBd(
			BigDecimal saldoPesificadoTotalAFechaShivaBd) {
		this.saldoPesificadoTotalAFechaShivaBd = saldoPesificadoTotalAFechaShivaBd;
	}
	/**
	 * @return the saldoPesificadoTotalAFechaEmisionBd
	 */
	public BigDecimal getSaldoPesificadoTotalAFechaEmisionBd() {
		return saldoPesificadoTotalAFechaEmisionBd;
	}
	/**
	 * @param saldoPesificadoTotalAFechaEmisionBd the saldoPesificadoTotalAFechaEmisionBd to set
	 */
	public void setSaldoPesificadoTotalAFechaEmisionBd(
			BigDecimal saldoPesificadoTotalAFechaEmisionBd) {
		this.saldoPesificadoTotalAFechaEmisionBd = saldoPesificadoTotalAFechaEmisionBd;
	}
	/**
	 * @return the importeMonedaOrigenDocAsociadoBd
	 */
	public BigDecimal getImporteMonedaOrigenDocAsociadoBd() {
		return importeMonedaOrigenDocAsociadoBd;
	}
	/**
	 * @param importeMonedaOrigenDocAsociadoBd the importeMonedaOrigenDocAsociadoBd to set
	 */
	public void setImporteMonedaOrigenDocAsociadoBd(
			BigDecimal importeMonedaOrigenDocAsociadoBd) {
		this.importeMonedaOrigenDocAsociadoBd = importeMonedaOrigenDocAsociadoBd;
	}
	/**
	 * @return the importePesificadoDocAsociadoBd
	 */
	public BigDecimal getImportePesificadoDocAsociadoBd() {
		return importePesificadoDocAsociadoBd;
	}
	/**
	 * @param importePesificadoDocAsociadoBd the importePesificadoDocAsociadoBd to set
	 */
	public void setImportePesificadoDocAsociadoBd(
			BigDecimal importePesificadoDocAsociadoBd) {
		this.importePesificadoDocAsociadoBd = importePesificadoDocAsociadoBd;
	}
	/**
	 * @return the importePesificadoDocAsociadoEmisionBd
	 */
	public BigDecimal getImportePesificadoDocAsociadoEmisionBd() {
		return importePesificadoDocAsociadoEmisionBd;
	}
	/**
	 * @param importePesificadoDocAsociadoEmisionBd the importePesificadoDocAsociadoEmisionBd to set
	 */
	public void setImportePesificadoDocAsociadoEmisionBd(
			BigDecimal importePesificadoDocAsociadoEmisionBd) {
		this.importePesificadoDocAsociadoEmisionBd = importePesificadoDocAsociadoEmisionBd;
	}
	/**
	 * @return the importeGestionableTotalEmisionBd
	 */
	public BigDecimal getImporteGestionableEmisionBd() {
		return importeGestionableEmisionBd;
	}
	/**
	 * @param importeGestionableTotalEmisionBd the importeGestionableTotalEmisionBd to set
	 */
	public void setImporteGestionableEmisionBd(
			BigDecimal importeGestionableTotalEmisionBd) {
		this.importeGestionableEmisionBd = importeGestionableTotalEmisionBd;
	}
	
}
