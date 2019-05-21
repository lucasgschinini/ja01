package ar.com.telecom.shiva.persistencia.modelo.simple;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmentoEmpresa;

@Entity
@Table(name = "SHV_PARAM_TIPO_GESTION")
public class ShvParamTipoGestionSimple extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_TIPO_GESTION")
	private String idTipoGestion;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumns({@JoinColumn(name="ID_SEGMENTO_EMPRESA", referencedColumnName="ID_SEGMENTO_EMPRESA")}) 
	private ShvParamSegmentoEmpresa segmentoEmpresa;
	
	@Column (name="ID_TIPO_VALOR")
	private Integer idTipoValor;
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	
	public String getIdTipoGestion() {
		return idTipoGestion;
	}

	public void setIdTipoGestion(String idTipoGestion) {
		this.idTipoGestion = idTipoGestion;
	}

	public ShvParamSegmentoEmpresa getSegmentoEmpresa() {
		return segmentoEmpresa;
	}

	public void setSegmentoEmpresa(ShvParamSegmentoEmpresa segmentoEmpresa) {
		this.segmentoEmpresa = segmentoEmpresa;
	}

	public Integer getIdTipoValor() {
		return idTipoValor;
	}

	public void setIdTipoValor(Integer idTipoValor) {
		this.idTipoValor = idTipoValor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idTipoGestion == null) ? 0 : idTipoGestion.hashCode());
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
		ShvParamTipoGestionSimple other = (ShvParamTipoGestionSimple) obj;
		if (idTipoGestion == null) {
			if (other.idTipoGestion != null)
				return false;
		} else if (!idTipoGestion.equals(other.idTipoGestion))
			return false;
		return true;
	}
	
	
}
