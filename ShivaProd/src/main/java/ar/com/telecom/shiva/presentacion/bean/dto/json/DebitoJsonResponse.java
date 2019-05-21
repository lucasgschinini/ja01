package ar.com.telecom.shiva.presentacion.bean.dto.json;

import java.util.List;

import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.paginacion.DebitosControlPaginacion;


public class DebitoJsonResponse extends JsonResponse {
	
	private DebitosControlPaginacion controlPaginacion;
	private List<CobroDebitoDto> resultado;
	private String errorMensaje;

	public List<CobroDebitoDto> getResultado() {
		return resultado;
	}

	public void setResultado(List<CobroDebitoDto> resultado) {
		this.resultado = resultado;
	}

	public DebitosControlPaginacion getControlPaginacion() {
		return controlPaginacion;
	}

	public void setControlPaginacion(DebitosControlPaginacion controlPaginacion) {
		this.controlPaginacion = controlPaginacion;
	}
	
	public String getErrorMensaje() {
		return errorMensaje;
	}

	public void setErrorMensaje(String errorMensaje) {
		this.errorMensaje = errorMensaje;
	}
}