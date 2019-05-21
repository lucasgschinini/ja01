/**
 * 
 */
package ar.com.telecom.shiva.negocio.dto;

import java.util.Set;
import java.util.TreeSet;

import ar.com.telecom.shiva.base.dto.DTO;

/**
 * @author pablo.m.ibarrola
 *
 */
public class AgrupadorParametroBancoDto extends DTO implements Comparable<AgrupadorParametroBancoDto> {
	private static final long serialVersionUID = 20170524L;

	private int idValue;
	private String descripcion;
	private Set<ParametroBancoDto> bancos = new TreeSet<ParametroBancoDto>();
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
	/**
	 * @return the bancos
	 */
	public Set<ParametroBancoDto> getBancos() {
		return bancos;
	}
	/**
	 * @param bancos the bancos to set
	 */
	public void setBancos(Set<ParametroBancoDto> bancos) {
		this.bancos = bancos;
	}
	
	/**
	 * @return the idValue
	 */
	public int getIdValue() {
		return idValue;
	}
	/**
	 * @param idValue the idValue to set
	 */
	public void setIdValue(int idValue) {
		this.idValue = idValue;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
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
		AgrupadorParametroBancoDto other = (AgrupadorParametroBancoDto) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AgrupadorParametroBancoDto o) {
		return this.getDescripcion().compareTo(o.getDescripcion());
	}
}
