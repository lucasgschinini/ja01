package ar.com.telecom.shiva.persistencia.modelo.param;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_SEGMENTO")
public class ShvParamSegmento extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_SEGMENTO")
	private String idSegmento;
	
	@Column (name="DESCRIPCION")
	private String descripcion;

	public String getIdSegmento() {
		return idSegmento;
	}

	public void setIdSegmento(String idSegmento) {
		this.idSegmento = idSegmento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
