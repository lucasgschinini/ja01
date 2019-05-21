package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_BANCO")
public class ShvParamBanco extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_BANCO", nullable = false)
	private String idBanco;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@Column(name = "CUIT")
	private String cuit;

	@Lob
	@Column(name = "LOGO")
	private byte[] logo;

	@Column(name = "NUMERO_IIBB", nullable = false)
	private String numeroIibb;

	@Column(name = "CATEGORIA_IVA")
	private String categoriaIVA;

	@Column(name = "CATEGORIA_IIBB")
	private String categoriaIibb;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumns({ @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA") })
	private ShvParamEmpresa empresa;

	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	public String getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(String idBanco) {
		this.idBanco = idBanco;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getCodigoIibb() {
		return numeroIibb;
	}

	public void setCodigoIibb(String codigoIibb) {
		this.numeroIibb = codigoIibb;
	}

	public String getNumeroIibb() {
		return numeroIibb;
	}

	public void setNumeroIibb(String numeroIibb) {
		this.numeroIibb = numeroIibb;
	}

	public String getCategoriaIVA() {
		return categoriaIVA;
	}

	public void setCategoriaIVA(String categoriaIVA) {
		this.categoriaIVA = categoriaIVA;
	}

	public String getCategoriaIibb() {
		return categoriaIibb;
	}

	public void setCategoriaIibb(String categoriaIibb) {
		this.categoriaIibb = categoriaIibb;
	}

	public ShvParamEmpresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(ShvParamEmpresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idBanco == null) ? 0 : idBanco.hashCode());
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
		ShvParamBanco other = (ShvParamBanco) obj;
		if (idBanco == null) {
			if (other.idBanco != null)
				return false;
		} else if (!idBanco.equals(other.idBanco))
			return false;
		return true;
	}

}