package ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso;

import java.math.BigInteger;

public class ControlPaginado {

	protected BigInteger cantidadRegistrosRetornados;
	protected BigInteger cantidadRegistrosTotales;
	
	public BigInteger getCantidadRegistrosRetornados() {
		return cantidadRegistrosRetornados;
	}
	public void setCantidadRegistrosRetornados(
			BigInteger cantidadRegistrosRetornados) {
		this.cantidadRegistrosRetornados = cantidadRegistrosRetornados;
	}
	public BigInteger getCantidadRegistrosTotales() {
		return cantidadRegistrosTotales;
	}
	public void setCantidadRegistrosTotales(BigInteger cantidadRegistrosTotales) {
		this.cantidadRegistrosTotales = cantidadRegistrosTotales;
	}
}
