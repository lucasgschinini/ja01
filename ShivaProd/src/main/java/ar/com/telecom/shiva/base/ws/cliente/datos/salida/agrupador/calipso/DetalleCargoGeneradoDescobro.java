package ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoCargoGeneradoEnum;

public class DetalleCargoGeneradoDescobro {
	
	protected EstadoAcuerdoFacturacionEnum estadoAcuerdoFacturacion;
	protected EstadoCargoGeneradoEnum estadoCargoGenerado;
	protected BigDecimal interesesRealesTrasladados;
	protected Resultado resultadoDescobroCargoGenerado;
	
	/*************************************************
	 * Getters & Setters
	 *************************************************/
	public EstadoAcuerdoFacturacionEnum getEstadoAcuerdoFacturacion() {
		return estadoAcuerdoFacturacion;
	}
	public void setEstadoAcuerdoFacturacion(
			EstadoAcuerdoFacturacionEnum estadoAcuerdoFacturacion) {
		this.estadoAcuerdoFacturacion = estadoAcuerdoFacturacion;
	}
	public EstadoCargoGeneradoEnum getEstadoCargoGenerado() {
		return estadoCargoGenerado;
	}
	public void setEstadoCargoGenerado(EstadoCargoGeneradoEnum estadoCargoGenerado) {
		this.estadoCargoGenerado = estadoCargoGenerado;
	}
	public BigDecimal getInteresesRealesTrasladados() {
		return interesesRealesTrasladados;
	}
	public void setInteresesRealesTrasladados(BigDecimal interesesRealesTrasladados) {
		this.interesesRealesTrasladados = interesesRealesTrasladados;
	}
	public Resultado getResultadoDescobroCargoGenerado() {
		return resultadoDescobroCargoGenerado;
	}
	public void setResultadoDescobroCargoGenerado(
			Resultado resultadoDescobroCargoGenerado) {
		this.resultadoDescobroCargoGenerado = resultadoDescobroCargoGenerado;
	}
	
}