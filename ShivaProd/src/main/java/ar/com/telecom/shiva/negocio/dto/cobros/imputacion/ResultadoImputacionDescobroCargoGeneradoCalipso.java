package ar.com.telecom.shiva.negocio.dto.cobros.imputacion;

import java.math.BigDecimal;


public class ResultadoImputacionDescobroCargoGeneradoCalipso extends ResultadoImputacionDescobroFactura {

	private BigDecimal interesesRealesTrasladados;

	public BigDecimal getInteresesRealesTrasladados() {
		return interesesRealesTrasladados;
	}

	public void setInteresesRealesTrasladados(BigDecimal interesesRealesTrasladados) {
		this.interesesRealesTrasladados = interesesRealesTrasladados;
	}
}
