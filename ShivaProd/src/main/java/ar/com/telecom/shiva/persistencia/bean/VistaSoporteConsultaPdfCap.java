package ar.com.telecom.shiva.persistencia.bean;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;

public class VistaSoporteConsultaPdfCap {
	protected String cliente;
	protected String nroDeComprobante;
	protected MonedaEnum moneda;
	protected BigDecimal importeOrigen;
	protected BigDecimal saldoActual;
	protected BigDecimal aCompensarEnPesos;
	protected BigDecimal montoReclamado = BigDecimal.ZERO;
	protected BigDecimal montoTotalACompensarEnPesos = BigDecimal.ZERO;;
	protected BigDecimal tipoCambio;
	protected BigDecimal importeACobrar;
	protected SistemaEnum sistema;
	
	//Mar
	protected SociedadEnum sociedad;
	protected String nroReferencia;
	
	private MonedaEnum monedaImporte;
	private BigDecimal saldo;
	
	public VistaSoporteConsultaPdfCap() {
	}

	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}

	
	/**
	 * @return the sociedad
	 */
	public SociedadEnum getSociedad() {
		return sociedad;
	}

	/**
	 * @param sociedad the sociedad to set
	 */
	public void setSociedad(SociedadEnum sociedad) {
		this.sociedad = sociedad;
	}
	/**
	 * @return the nroReferencia
	 */
	public String getNroReferencia() {
		return nroReferencia;
	}

	/**
	 * @param nroReferencia the nroReferencia to set
	 */
	public void setNroReferencia(String nroReferencia) {
		this.nroReferencia = nroReferencia;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the nroDeComprobante
	 */
	public String getNroDeComprobante() {
		return nroDeComprobante;
	}

	/**
	 * @param nroDeComprobante the nroDeComprobante to set
	 */
	public void setNroDeComprobante(String nroDeComprobante) {
		this.nroDeComprobante = nroDeComprobante;
	}

	/**
	 * @return the moneda
	 */
	public MonedaEnum getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the importeOrigen
	 */
	public BigDecimal getImporteOrigen() {
		return importeOrigen;
	}

	/**
	 * @param importeOrigen the importeOrigen to set
	 */
	public void setImporteOrigen(BigDecimal importeOrigen) {
		this.importeOrigen = importeOrigen;
	}

	/**
	 * @return the saldoActual
	 */
	public BigDecimal getSaldoActual() {
		return saldoActual;
	}

	/**
	 * @param saldoActual the saldoActual to set
	 */
	public void setSaldoActual(BigDecimal saldoActual) {
		this.saldoActual = saldoActual;
	}

	/**
	 * @return the aCompensarEnPesos
	 */
	public BigDecimal getaCompensarEnPesos() {
		return aCompensarEnPesos;
	}

	/**
	 * @param aCompensarEnPesos the aCompensarEnPesos to set
	 */
	public void setaCompensarEnPesos(BigDecimal aCompensarEnPesos) {
		this.aCompensarEnPesos = aCompensarEnPesos;
	}

	/**
	 * @return the montoReclamado
	 */
	public BigDecimal getMontoReclamado() {
		return montoReclamado;
	}

	/**
	 * @param montoReclamado the montoReclamado to set
	 */
	public void setMontoReclamado(BigDecimal montoReclamado) {
		this.montoReclamado = montoReclamado;
	}

	/**
	 * @return the montoTotalACompensarEnPesos
	 */
	public BigDecimal getMontoTotalACompensarEnPesos() {
		return montoTotalACompensarEnPesos;
	}

	/**
	 * @param montoTotalACompensarEnPesos the montoTotalACompensarEnPesos to set
	 */
	public void setMontoTotalACompensarEnPesos(
			BigDecimal montoTotalACompensarEnPesos) {
		this.montoTotalACompensarEnPesos = montoTotalACompensarEnPesos;
	}

	/**
	 * @return the tipoCambio
	 */
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	/**
	 * @param tipoCambio the tipoCambio to set
	 */
	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	/**
	 * @return the importeACobrarl
	 */
	public BigDecimal getImporteACobrar() {
		return importeACobrar;
	}

	/**
	 * @param importeACobrarl the importeACobrarl to set
	 */
	public void setImporteACobrar(BigDecimal importeACobrar) {
		this.importeACobrar = importeACobrar;
	}

	/**
	 * @return the sistema
	 */
	public SistemaEnum getSistema() {
		return sistema;
	}

	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}

	/**
	 * @return the monedaImporte
	 */
	public MonedaEnum getMonedaImporte() {
		return monedaImporte;
	}

	/**
	 * @param monedaImporte the monedaImporte to set
	 */
	public void setMonedaImporte(MonedaEnum monedaImporte) {
		this.monedaImporte = monedaImporte;
	}

	/**
	 * @return the saldo
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	
}
