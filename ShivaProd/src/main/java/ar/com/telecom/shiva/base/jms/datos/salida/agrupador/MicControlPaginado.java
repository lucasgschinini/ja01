package ar.com.telecom.shiva.base.jms.datos.salida.agrupador;

public class MicControlPaginado {

	protected Long cantidadRegistrosRetornados;
	protected Long cantidadRegistrosTotales;
	
	public Long getCantidadRegistrosRetornados() {
		return cantidadRegistrosRetornados;
	}
	public void setCantidadRegistrosRetornados(Long cantidadRegistrosRetornados) {
		this.cantidadRegistrosRetornados = cantidadRegistrosRetornados;
	}
	public Long getCantidadRegistrosTotales() {
		return cantidadRegistrosTotales;
	}
	public void setCantidadRegistrosTotales(Long cantidadRegistrosTotales) {
		this.cantidadRegistrosTotales = cantidadRegistrosTotales;
	}
	
	
}
