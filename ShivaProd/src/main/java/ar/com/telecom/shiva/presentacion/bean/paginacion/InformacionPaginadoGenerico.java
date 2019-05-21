package ar.com.telecom.shiva.presentacion.bean.paginacion;

import java.io.Serializable;

public class InformacionPaginadoGenerico <T> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer cantidadRegistrosARetornar;
	
	private T ultimoId;
	
	public Integer getCantidadRegistrosARetornar() {
		return cantidadRegistrosARetornar;
	}
	public void setCantidadRegistrosARetornar(Integer cantidadRegistrosARetornar) {
		this.cantidadRegistrosARetornar = cantidadRegistrosARetornar;
	}
	public T getUltimoId() {
		return ultimoId;
	}
	public void setUltimoIdValor(T ultimoId) {
		this.ultimoId = ultimoId;
	}
	
}