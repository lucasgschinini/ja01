package ar.com.telecom.shiva.presentacion.bean.dto;

import ar.com.telecom.shiva.base.dto.DTO;

public class CobroMedioPagoClienteDto extends DTO {
	
	private static final long serialVersionUID = 1L;

	private String idClienteLegado;

	public String getIdClienteLegado() {
		return idClienteLegado;
	}

	public void setIdClienteLegado(String idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}
}