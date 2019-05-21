package ar.com.telecom.shiva.negocio.dto;

import java.io.Serializable;



/**
 * @author u578936 M.A.Uehara
 *
 */
public class ParametroOrigenDto implements Serializable {
	private static final long serialVersionUID = 20171219L;

	private Integer idOrigen;
	private String descripcion;
	
	public ParametroOrigenDto() {
	}

	/**
	 * @return the idOrigen
	 */
	public Integer getIdOrigen() {
		return idOrigen;
	}

	/**
	 * @param idOrigen the idOrigen to set
	 */
	public void setIdOrigen(Integer idOrigen) {
		this.idOrigen = idOrigen;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idOrigen == null) ? 0 : idOrigen.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParametroOrigenDto other = (ParametroOrigenDto) obj;
		if (idOrigen == null) {
			if (other.idOrigen != null)
				return false;
		} else if (!idOrigen.equals(other.idOrigen))
			return false;
		return true;
	}
}
