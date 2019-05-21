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
@Table(name = "SHV_PARAM_GESTOR_SEGMENTO_EMP")
public class ShvParamGestorSegmentoEmpresa extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_GESTOR_SEGMENTO_EMPRESA")
	private String idSegmentoEmpresa;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumns({@JoinColumn(name="ID_GESTOR", referencedColumnName="ID_GESTOR")}) 
	private ShvParamGestor gestor;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumns({@JoinColumn(name="ID_SEGMENTO_EMPRESA", referencedColumnName="ID_SEGMENTO_EMPRESA")}) 
	private ShvParamSegmentoEmpresa segmentoEmpresa;


	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	
	public String getIdSegmentoEmpresa() {
		return idSegmentoEmpresa;
	}
	
	public void setIdSegmentoEmpresa(String idSegmentoEmpresa) {
		this.idSegmentoEmpresa = idSegmentoEmpresa;
	}
	
	public ShvParamGestor getGestor() {
		return gestor;
	}
	
	public void setGestor(ShvParamGestor gestor) {
		this.gestor = gestor;
	}
	
	public ShvParamSegmentoEmpresa getSegmentoEmpresa() {
		return segmentoEmpresa;
	}
	
	public void setSegmentoEmpresa(ShvParamSegmentoEmpresa segmentoEmpresa) {
		this.segmentoEmpresa = segmentoEmpresa;
	}
	
	
	
}
