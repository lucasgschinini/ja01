package ar.com.telecom.shiva.persistencia.modelo.param;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoReglaConciliacionEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_REGLA_CONCILIACION")
public class ShvParamReglaConciliacion extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_REGLA_CONCILIACION")
	private Long idReglaConciliacion;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumns({@JoinColumn(name="ID_TIPO_VALOR", referencedColumnName="ID_TIPO_VALOR")}) 
	private ShvParamTipoValor tipoValor;
	
	@Enumerated(EnumType.STRING)
	@Column (name="ESTADO")
	private EstadoReglaConciliacionEnum estado;
	
//	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy="reglaConciliacion")
//	private Set<ShvParamConciliacion> shvConciliacion = new HashSet<ShvParamConciliacion>();
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	public Long getIdReglaConciliacion() {
		return idReglaConciliacion;
	}

	public void setIdReglaConciliacion(Long idReglaConciliacion) {
		this.idReglaConciliacion = idReglaConciliacion;
	}

	public ShvParamTipoValor getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(ShvParamTipoValor tipoValor) {
		this.tipoValor = tipoValor;
	}

	public EstadoReglaConciliacionEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoReglaConciliacionEnum estado) {
		this.estado = estado;
	}

//	public Set<ShvParamConciliacion> getShvConciliacion() {
//		return shvConciliacion;
//	}
//
//	public void setShvConciliacion(Set<ShvParamConciliacion> shvConciliacion) {
//		this.shvConciliacion = shvConciliacion;
//	}

}
