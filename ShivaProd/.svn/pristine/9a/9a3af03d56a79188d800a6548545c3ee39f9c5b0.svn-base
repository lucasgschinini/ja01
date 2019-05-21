package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_SEGMENTO_EMPRESA")
public class ShvParamSegmentoEmpresa extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_SEGMENTO_EMPRESA")
	private String idSegmentoEmpresa;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumns({@JoinColumn(name="ID_EMPRESA", referencedColumnName="ID_EMPRESA")}) 
	private ShvParamEmpresa empresa;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumns({@JoinColumn(name="ID_SEGMENTO", referencedColumnName="ID_SEGMENTO")}) 
	private ShvParamSegmento segmento;

	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	
	public String getIdSegmentoEmpresa() {
		return idSegmentoEmpresa;
	}

	public void setIdSegmentoEmpresa(String idSegmentoEmpresa) {
		this.idSegmentoEmpresa = idSegmentoEmpresa;
	}

	public ShvParamEmpresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(ShvParamEmpresa empresa) {
		this.empresa = empresa;
	}

	public ShvParamSegmento getSegmento() {
		return segmento;
	}

	public void setSegmento(ShvParamSegmento segmento) {
		this.segmento = segmento;
	}
	
	
}
