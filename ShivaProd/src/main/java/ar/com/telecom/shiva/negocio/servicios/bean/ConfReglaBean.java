package ar.com.telecom.shiva.negocio.servicios.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import ar.com.telecom.shiva.base.enumeradores.ConfFormObligatoriedadEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;

/**
 * @author u578936 M.A.Uehara
 *
 */
public class ConfReglaBean implements Serializable {
	private static final long serialVersionUID = 20180613L;

	private MonedaEnum monedaCobro;
	private SociedadEnum sociedad;
	private SistemaEnum sistema;
	private TipoComprobanteEnum tipoComprobante;
	private MonedaEnum monedaEnum;
	

	private String tipoCambio;//Ok
	private String nombre;
	private String longitud;
	private String tipoDeDato;
	private String validacion;
	private String validacionMsg;
	private ConfFormObligatoriedadEnum obligatoriedad;
	private BigDecimal orden;
	private BigDecimal idConfRango;
	private BigDecimal idConfCampo;

	public ConfReglaBean() {
	}

	/**
	 * @return the monedaCobro
	 */
	public MonedaEnum getMonedaCobro() {
		return monedaCobro;
	}

	/**
	 * @param monedaCobro the monedaCobro to set
	 */
	public void setMonedaCobro(MonedaEnum monedaCobro) {
		this.monedaCobro = monedaCobro;
	}

	/**
	 * @return the sociedad
	 */
	public SociedadEnum getSociedad() {
		return sociedad;
	}
	public String getSociedadDesc() {
		return sociedad.getApocope();
	}
	/**
	 * @param sociedad the sociedad to set
	 */
	public void setSociedad(SociedadEnum sociedad) {
		this.sociedad = sociedad;
	}

	/**
	 * @return the sistema
	 */
	public SistemaEnum getSistema() {
		return sistema;
	}
	public String getSistemaDesc() {
		return sistema.getDescripcionCorta();
	}
	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}

	/**
	 * @return the tipoDebito
	 */
	public TipoComprobanteEnum getTipoComprobante() {
		return tipoComprobante;
	}
	public String getTipoComprobanteDesc() {
		return tipoComprobante.getValor();
	}
	/**
	 * @param tipoDebito the tipoDebito to set
	 */
	public void setTipoComprobante(TipoComprobanteEnum tipoDebito) {
		this.tipoComprobante = tipoDebito;
	}

	/**
	 * @return the monedaEnum
	 */
	public MonedaEnum getMoneda() {
		return monedaEnum;
	}
	public String getMonedaDesc() {
		return monedaEnum.getSigno2();
	}
	/**
	 * @param monedaEnum the monedaEnum to set
	 */
	public void setMoneda(MonedaEnum monedaEnum) {
		this.monedaEnum = monedaEnum;
	}

	public String getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLongitud() {
		return longitud;
	}
	public Integer getLongitudLong() {
		return Integer.parseInt(longitud);
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getTipoDeDato() {
		return tipoDeDato;
	}

	public void setTipoDeDato(String tipoDeDato) {
		this.tipoDeDato = tipoDeDato;
	}

	public String getValidacionMsg() {
		return validacionMsg;
	}

	public void setValidacionMsg(String validacionMsg) {
		this.validacionMsg = validacionMsg;
	}

	public String getValidacion() {
		return validacion;
	}

	public void setValidacion(String validacion) {
		this.validacion = validacion;
	}

	public ConfFormObligatoriedadEnum getObligatoriedad() {
		return obligatoriedad;
	}

	public void setObligatoriedad(ConfFormObligatoriedadEnum obligatoriedad) {
		this.obligatoriedad = obligatoriedad;
	}

	public BigDecimal getOrden() {
		return orden;
	}

	public void setOrden(BigDecimal orden) {
		this.orden = orden;
	}

	/**
	 * @return the idConfRango
	 */
	public BigDecimal getIdConfRango() {
		return idConfRango;
	}

	/**
	 * @param idConfRango the idConfRango to set
	 */
	public void setIdConfRango(BigDecimal idConfRango) {
		this.idConfRango = idConfRango;
	}

	/**
	 * @return the idConfCampo
	 */
	public BigDecimal getIdConfCampo() {
		return idConfCampo;
	}

	/**
	 * @param idConfCampo the idConfCampo to set
	 */
	public void setIdConfCampo(BigDecimal idConfCampo) {
		this.idConfCampo = idConfCampo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConfReglaBean [monedaCobro=" + monedaCobro + ", sociedad="
				+ sociedad + ", sistema=" + sistema + ", tipoDebito="
				+ tipoComprobante + ", monedaEnum=" + monedaEnum + ", tipoCambio="
				+ tipoCambio + ", nombre=" + nombre + ", longitud=" + longitud
				+ ", tipoDeDato=" + tipoDeDato + ", validacion=" + validacion
				+ ", validacionMsg=" + validacionMsg + ", obligatoriedad="
				+ obligatoriedad + ", orden=" + orden + ", idConfRango="
				+ idConfRango + ", idConfCampo=" + idConfCampo + "]";
	}
	
}
