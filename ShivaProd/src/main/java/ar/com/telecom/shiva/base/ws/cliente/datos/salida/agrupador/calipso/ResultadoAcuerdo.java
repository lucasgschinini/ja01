package ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso;

import java.math.BigInteger;

import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;

public class ResultadoAcuerdo {

	protected BigInteger idClienteLegado;
	protected EstadoAcuerdoFacturacionEnum estado;
	protected MonedaEnum moneda;

	public String toString() {
		return "ResultadoAcuerdo[" + this.idClienteLegado + "-" + ((this.estado == null) ? "" : this.estado.codigo()) + "-" + this.moneda + "]";
	}

	public EstadoAcuerdoFacturacionEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoAcuerdoFacturacionEnum estado) {
		this.estado = estado;
	}

	public BigInteger getIdClienteLegado() {
		return idClienteLegado;
	}

	public void setIdClienteLegado(BigInteger idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
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
}
