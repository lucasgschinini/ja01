package ar.com.telecom.shiva.presentacion.bean.dto.json;

import java.util.List;

import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.util.IDatosComunesEntrada;

public class DeimosJsonResponse {

	private List<IDatosComunesEntrada> listaDebitosCreditos;
	private List<IDatosComunesEntrada> listaDebitosCreditosSeleccionados;
	private String errorMensaje;

	public String getErrorMensaje() {
		return errorMensaje;
	}

	public void setErrorMensaje(String errorMensaje) {
		this.errorMensaje = errorMensaje;
	}

	public List<IDatosComunesEntrada> getListaDebitosCreditosSeleccionados() {
		return listaDebitosCreditosSeleccionados;
	}

	public void setListaDebitosCreditosSeleccionados(
			List<IDatosComunesEntrada> listaDebitosCreditosSeleccionados) {
		this.listaDebitosCreditosSeleccionados = listaDebitosCreditosSeleccionados;
	}

	public List<IDatosComunesEntrada> getListaDebitosCreditos() {
		return listaDebitosCreditos;
	}

	public void setListaDebitosCreditos(
			List<IDatosComunesEntrada> listaDebitosCreditos) {
		this.listaDebitosCreditos = listaDebitosCreditos;
	}

	
}