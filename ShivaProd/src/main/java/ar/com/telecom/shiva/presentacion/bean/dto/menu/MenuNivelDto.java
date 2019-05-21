package ar.com.telecom.shiva.presentacion.bean.dto.menu;

import ar.com.telecom.shiva.base.dto.DTO;

public class MenuNivelDto extends DTO {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descripcion;
	private Integer orden;
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
