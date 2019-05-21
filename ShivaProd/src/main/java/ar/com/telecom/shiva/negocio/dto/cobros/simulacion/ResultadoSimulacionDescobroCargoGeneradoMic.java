package ar.com.telecom.shiva.negocio.dto.cobros.simulacion;

import java.math.BigDecimal;


public class ResultadoSimulacionDescobroCargoGeneradoMic extends ResultadoSimulacionDescobroCargoGenerado {

	private BigDecimal interesesRealesNoReguladosTrasladados;
	private BigDecimal interesesRealesReguladosTrasladados;
	private boolean requiereBuscarAcuerdoActivo = false;
	
	
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
	public boolean isRequiereBuscarAcuerdoActivo() {
		return requiereBuscarAcuerdoActivo;
	}
	public void setRequiereBuscarAcuerdoActivo(boolean requiereBuscarAcuerdoActivo) {
		this.requiereBuscarAcuerdoActivo = requiereBuscarAcuerdoActivo;
	}

}
