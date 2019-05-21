package ar.com.telecom.shiva.negocio.dto.cobros.imputacion;

import java.math.BigDecimal;


public class ResultadoImputacionDescobroCargoGeneradoMic extends ResultadoImputacionDescobroFactura {

	private BigDecimal interesesRealesNoReguladosTrasladados;
	private BigDecimal interesesRealesReguladosTrasladados;
	
	public BigDecimal getInteresesRealesNoReguladosTrasladados() {
		return interesesRealesNoReguladosTrasladados;
	}
	public void setInteresesRealesNoReguladosTrasladados(
			BigDecimal interesesRealesNoReguladosTrasladados) {
		this.interesesRealesNoReguladosTrasladados = interesesRealesNoReguladosTrasladados;
	}
	public BigDecimal getInteresesRealesReguladosTrasladados() {
		return interesesRealesReguladosTrasladados;
	}
	public void setInteresesRealesReguladosTrasladados(
			BigDecimal interesesRealesReguladosTrasladados) {
		this.interesesRealesReguladosTrasladados = interesesRealesReguladosTrasladados;
	}

}
