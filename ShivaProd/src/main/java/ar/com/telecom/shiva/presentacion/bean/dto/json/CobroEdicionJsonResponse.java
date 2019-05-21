package ar.com.telecom.shiva.presentacion.bean.dto.json;

import java.util.List;

import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;


public class CobroEdicionJsonResponse extends JsonResponse {

	private CobroDto cobro;
	private List<SelectOptionJsonResponse> listaCopropietarios;

	public CobroDto getCobro() {
		return cobro;
	}

	public void setCobro(CobroDto cobro) {
		this.cobro = cobro;
	}

	public List<SelectOptionJsonResponse> getListaCopropietarios() {
		return listaCopropietarios;
	}

	public void setListaCopropietarios(List<SelectOptionJsonResponse> listaCopropietarios) {
		this.listaCopropietarios = listaCopropietarios;
	}
	
}