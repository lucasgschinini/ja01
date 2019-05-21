package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_ORIGEN")
public class ShvParamOrigen extends Modelo{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_ORIGEN")
	private Integer idOrigen;

	@Column (name="DESCRIPCION")
	private String descripcion;

	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	public Integer getIdOrigen() {
		return idOrigen;
	}

	public void setIdOrigen(Integer idOrigen) {
		this.idOrigen = idOrigen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idOrigen == null) ? 0 : idOrigen.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShvParamOrigen other = (ShvParamOrigen) obj;
		if (idOrigen == null) {
			if (other.idOrigen != null)
				return false;
		} else if (!idOrigen.equals(other.idOrigen))
			return false;
		return true;
	}
	
}
