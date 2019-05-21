package ar.com.telecom.shiva.presentacion.bean.filtro;

import java.math.BigDecimal;

public class ReglaCobroFiltro extends Filtro {

	private static final long serialVersionUID = 1L;
	
	private String tipoRegla;
	private String empresa;
	private String segmento;
	private String moneda;
	private BigDecimal monto;
	private BigDecimal porcentaje;
	private String accion;

	/**
	 * @return the tipoRegla
	 */
	public String getTipoRegla() {
		return tipoRegla;
	}
	/**
	 * @param tipoRegla the tipoRegla to set
	 */
	public void setTipoRegla(String tipoRegla) {
		this.tipoRegla = tipoRegla;
	}
	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}
	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}
	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	/**
	 * @return the monto
	 */
	public BigDecimal getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	/**
	 * @return the porcentaje
	 */
	public BigDecimal getPorcentaje() {
		return porcentaje;
	}
	/**
	 * @param porcentaje the porcentaje to set
	 */
	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}
	@Override
	public String toString() {
		return "ReglaCobroFiltro [tipoRegla=" + tipoRegla + ", empresa="
				+ empresa + ", segmento=" + segmento + ", moneda=" + moneda
				+ ", monto=" + monto + ", porcentaje=" + porcentaje
				+ ", accion=" + accion + "]";
	}

}
