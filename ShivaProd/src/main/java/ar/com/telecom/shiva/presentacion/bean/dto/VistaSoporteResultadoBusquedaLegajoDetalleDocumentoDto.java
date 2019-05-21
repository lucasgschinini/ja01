package ar.com.telecom.shiva.presentacion.bean.dto;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;

public class VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto extends DTO{
	private static final long serialVersionUID = 20170724L;

	
	private SistemaEnum sistemaOrigenEnum;
	private String sistemaOrigenDescripcion;
	private String	tipoDocumento;
	private String	numeroLegal;
	private String	numeroReferencia; //SOLO MIC
	private String	clienteLegado;
	
	private String	importeTotalDocumento;
	private String	saldoDocumentoLuegoAplicarCheque;
	private String	importeAplicadoDelCheque;
	private String	saldoActualDocumento;
	private String	fechaConsultaSaldo;
	private String	monedaDocumento;

	

	public VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto() {
	}

	/**
	 * @return the sistemaOrigenEnum
	 */
	public SistemaEnum getSistemaOrigenEnum() {
		return sistemaOrigenEnum;
	}
	/**
	 * @param sistemaOrigenEnum the sistemaOrigenEnum to set
	 */
	public void setSistemaOrigenEnum(SistemaEnum sistemaOrigenEnum) {
		this.sistemaOrigenEnum = sistemaOrigenEnum;
	}
	/**
	 * @return the sistemaOrigenDescripcion
	 */
	public String getSistemaOrigenDescripcion() {
		return sistemaOrigenDescripcion;
	}
	/**
	 * @param sistemaOrigenDescripcion the sistemaOrigenDescripcion to set
	 */
	public void setSistemaOrigenDescripcion(String sistemaOrigenDescripcion) {
		this.sistemaOrigenDescripcion = sistemaOrigenDescripcion;
	}
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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
	 * @return the numeroReferencia
	 */
	public String getNumeroReferencia() {
		return numeroReferencia;
	}
	/**
	 * @param numeroReferencia the numeroReferencia to set
	 */
	public void setNumeroReferencia(String numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}
	/**
	 * @return the clienteLegado
	 */
	public String getClienteLegado() {
		return clienteLegado;
	}
	/**
	 * @param clienteLegado the clienteLegado to set
	 */
	public void setClienteLegado(String clienteLegado) {
		this.clienteLegado = clienteLegado;
	}
	/**
	 * @return the importeTotalDocumento
	 */
	public String getImporteTotalDocumento() {
		return importeTotalDocumento;
	}
	/**
	 * @param importeTotalDocumento the importeTotalDocumento to set
	 */
	public void setImporteTotalDocumento(String importeTotalDocumento) {
		this.importeTotalDocumento = importeTotalDocumento;
	}
	/**
	 * @return the saldoDocumentoLuegoAplicarCheque
	 */
	public String getSaldoDocumentoLuegoAplicarCheque() {
		return saldoDocumentoLuegoAplicarCheque;
	}
	/**
	 * @param saldoDocumentoLuegoAplicarCheque the saldoDocumentoLuegoAplicarCheque to set
	 */
	public void setSaldoDocumentoLuegoAplicarCheque(
			String saldoDocumentoLuegoAplicarCheque) {
		this.saldoDocumentoLuegoAplicarCheque = saldoDocumentoLuegoAplicarCheque;
	}
	/**
	 * @return the importeAplicadoDelCheque
	 */
	public String getImporteAplicadoDelCheque() {
		return importeAplicadoDelCheque;
	}
	/**
	 * @param importeAplicadoDelCheque the importeAplicadoDelCheque to set
	 */
	public void setImporteAplicadoDelCheque(String importeAplicadoDelCheque) {
		this.importeAplicadoDelCheque = importeAplicadoDelCheque;
	}
	/**
	 * @return the saldoActualDocumento
	 */
	public String getSaldoActualDocumento() {
		return saldoActualDocumento;
	}
	/**
	 * @param saldoActualDocumento the saldoActualDocumento to set
	 */
	public void setSaldoActualDocumento(String saldoActualDocumento) {
		this.saldoActualDocumento = saldoActualDocumento;
	}
	/**
	 * @return the fechaConsultaSaldo
	 */
	public String getFechaConsultaSaldo() {
		return fechaConsultaSaldo;
	}
	/**
	 * @param fechaConsultaSaldo the fechaConsultaSaldo to set
	 */
	public void setFechaConsultaSaldo(String fechaConsultaSaldo) {
		this.fechaConsultaSaldo = fechaConsultaSaldo;
	}
	/**
	 * @return the monedaDocumento
	 */
	public String getMonedaDocumento() {
		return monedaDocumento;
	}
	/**
	 * @param monedaDocumento the monedaDocumento to set
	 */
	public void setMonedaDocumento(String monedaDocumento) {
		this.monedaDocumento = monedaDocumento;
	}
}
