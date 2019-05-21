package ar.com.telecom.shiva.persistencia.modelo.param;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_MOTIVO")
public class ShvParamMotivo extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_MOTIVO")
	private Integer idMotivo;
	
	@Column (name="DESCRIPCION")
	private String descripcion;
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/


	public Integer getIdMotivo() {
		return idMotivo;
	}

	public void setIdMotivo(Integer idMotivo) {
		this.idMotivo = idMotivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



}
