package ar.com.telecom.shiva.presentacion.bean.dto.json;

import java.util.List;

import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;

public class ImportarDebitoJsonResponse extends JsonResponse {
	
	public List<ClienteDto> clientes;
	public List<CobroDebitoDto> debitos;
	private ComprobanteJsonResponse comprobanteJsonResponse;

	public List<ClienteDto> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClienteDto> clientes) {
		this.clientes = clientes;
	}

	public List<CobroDebitoDto> getDebitos() {
		return debitos;
	}

	public void setDebitos(List<CobroDebitoDto> debitos) {
		this.debitos = debitos;
	}

	public ComprobanteJsonResponse getComprobanteJsonResponse() {
		return comprobanteJsonResponse;
	}

	public void setComprobanteJsonResponse(ComprobanteJsonResponse comprobanteJsonResponse) {
		this.comprobanteJsonResponse = comprobanteJsonResponse;
	}
	
}