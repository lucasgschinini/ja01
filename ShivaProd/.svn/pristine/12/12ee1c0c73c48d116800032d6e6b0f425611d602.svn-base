package ar.com.telecom.shiva.negocio.batch.bean.scard;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import ar.com.telecom.shiva.base.enumeradores.LeyendaPiePaginaDocumentoScardEnum;
@XmlRootElement
@XmlType(propOrder = {"origen",
					  "referencia",
					  "comprobante",
					  "clase",
					  "sucursal",
					  "numero",
					  "vencimiento",
					  "monedaOrigen",
					  "importeVencimiento",
					  "saldoAnterior",
					  "pago",
					  "importeMonedaOrigen",
					  "tipoCambio",
					  "acuerdo",
					  "operacion",
					  "intereses",
					  "interesesBonificados",
					  "consecuenciaCobro",
					  "importe",
					  "llamadaPiePagina"})

public class Factura {

	private String origen;
	private String referencia;
	private String comprobante;
	private String clase;
	private String sucursal;
	private String numero;
	private String vencimiento;
	private String monedaOrigen;
	private String importeVencimiento;
	private String saldoAnterior;
	private String pago;
	private String importeMonedaOrigen;
	private String tipoCambio;
	private String acuerdo;
	private String operacion;
	private String intereses;
	private String interesesBonificados;
	private String consecuenciaCobro;
	private String importe;
	private String llamadaPiePagina;
	private LeyendaPiePaginaDocumentoScardEnum leyendaPiePagina;
	
	@XmlElement
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	@XmlElement
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	@XmlElement
	public String getComprobante() {
		return comprobante;
	}
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
	@XmlElement
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	@XmlElement
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	@XmlElement
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	@XmlElement
	public String getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	@XmlElement(name="moneda_origen")
	public String getMonedaOrigen() {
		return monedaOrigen;
	}

	public void setMonedaOrigen(String moneda) {
		this.monedaOrigen = moneda;
	}
	
	@XmlElement(name="importe_vencimiento")
	public String getImporteVencimiento() {
		return importeVencimiento;
	}
	public void setImporteVencimiento(String importeVencimiento) {
		this.importeVencimiento = importeVencimiento;
	}
	@XmlElement(name="saldo_anterior")
	public String getSaldoAnterior() {
		return saldoAnterior;
	}
	public void setSaldoAnterior(String saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}
	@XmlElement
	public String getPago() {
		return pago;
	}
	public void setPago(String pago) {
		this.pago = pago;
	}
	@XmlElement(name="tipo_cambio")
	public String getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	@XmlElement
	public String getAcuerdo() {
		return acuerdo;
	}
	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}
	@XmlElement
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	@XmlElement
	public String getIntereses() {
		return intereses;
	}
	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}
	@XmlElement(name="intereses_bonificados")
	public String getInteresesBonificados() {
		return interesesBonificados;
	}
	public void setInteresesBonificados(String interesesBonificados) {
		this.interesesBonificados = interesesBonificados;
	}
	@XmlElement(name="consecuencia_del_cobro")
	public String getConsecuenciaCobro() {
		return consecuenciaCobro;
	}
	public void setConsecuenciaCobro(String consecuenciaCobro) {
		this.consecuenciaCobro = consecuenciaCobro;
	}
	@XmlElement
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	@XmlElement(name="llamada_pie_de_pagina")
	public String getLlamadaPiePagina() {
		return llamadaPiePagina;
	}
	public void setLlamadaPiePagina(String llamadaPiePagina) {
		this.llamadaPiePagina = llamadaPiePagina;
	}

	/**
	 * @return the importeMonedaOrigen
	 */
	@XmlElement(name="importe_moneda_ori")
	public String getImporteMonedaOrigen() {
		return importeMonedaOrigen;
	}
	/**
	 * @param importeMonedaOrigen the importeMonedaOrigen to set
	 */
	public void setImporteMonedaOrigen(String importeMonedaOrigen) {
		this.importeMonedaOrigen = importeMonedaOrigen;
	}

	/**
	 * @return the leyendaPiePagina
	 */
	@XmlTransient
	public LeyendaPiePaginaDocumentoScardEnum getLeyendaPiePagina() {
		return leyendaPiePagina;
	}
	
	/**
	 * @param leyendaPiePagina the leyendaPiePagina to set
	 */
	public void setLeyendaPiePagina(LeyendaPiePaginaDocumentoScardEnum leyendaPiePagina) {
		this.leyendaPiePagina = leyendaPiePagina;
	}
}
