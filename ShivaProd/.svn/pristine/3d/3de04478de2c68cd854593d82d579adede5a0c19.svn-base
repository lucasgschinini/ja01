/**
 * 
 */
package ar.com.telecom.shiva.negocio.dto;

import ar.com.telecom.shiva.base.dto.DTO;

/**
 * @author pablo.m.ibarrola
 *
 */
public class ParametroBancoDto extends DTO implements Comparable<ParametroBancoDto> {
	private static final long serialVersionUID = 20170520L;

	private String idBanco;
	private String descripcion;
	private String cuit;
	private String numeroIibb;
	private String categoriaIVA;
	private String categoriaIibb;
	private String idEmpresa;

	private int idAgrupador;

	 /**
	 * @return the idBanco
	 */
	public String getIdBanco() {
		return idBanco;
	}
	/**
	 * @param idBanco the idBanco to set
	 */
	public void setIdBanco(String idBanco) {
		this.idBanco = idBanco;
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
	/**
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}
	/**
	 * @param cuit the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	/**
	 * @return the numeroIibb
	 */
	public String getNumeroIibb() {
		return numeroIibb;
	}
	/**
	 * @param numeroIibb the numeroIibb to set
	 */
	public void setNumeroIibb(String numeroIibb) {
		this.numeroIibb = numeroIibb;
	}
	/**
	 * @return the categoriaIVA
	 */
	public String getCategoriaIVA() {
		return categoriaIVA;
	}
	/**
	 * @param categoriaIVA the categoriaIVA to set
	 */
	public void setCategoriaIVA(String categoriaIVA) {
		this.categoriaIVA = categoriaIVA;
	}
	/**
	 * @return the categoriaIibb
	 */
	public String getCategoriaIibb() {
		return categoriaIibb;
	}
	/**
	 * @param categoriaIibb the categoriaIibb to set
	 */
	public void setCategoriaIibb(String categoriaIibb) {
		this.categoriaIibb = categoriaIibb;
	}
	/**
	 * @return the idEmpresa
	 */
	public String getIdEmpresa() {
		return idEmpresa;
	}
	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	/**
	 * @return the idAgrupador
	 */
	public int getIdAgrupador() {
		return idAgrupador;
	}
	/**
	 * @param idAgrupador the idAgrupador to set
	 */
	public void setIdAgrupador(int idAgrupador) {
		this.idAgrupador = idAgrupador;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idBanco == null) ? 0 : idBanco.hashCode());
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
		ParametroBancoDto other = (ParametroBancoDto) obj;
		if (idBanco == null) {
			if (other.idBanco != null)
				return false;
		} else if (!idBanco.equals(other.idBanco))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ParametroBancoDto o) {
		return this.idBanco.compareTo(o.getIdBanco());
	}
	
}
