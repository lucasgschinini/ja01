package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoReglaConciliacionEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_CONCILIACION")
public class ShvParamConciliacion extends Modelo{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_CONCILIACION")
	private Long idConciliacion;

	@Column (name="ID_TIPO_CONCILIACION")
	private String idTipoConciliacion;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_REGLA_CONCILIACION", referencedColumnName="ID_REGLA_CONCILIACION") 	
	private ShvParamReglaConciliacion reglaConciliacion;
	
	@Column (name="NUMERO_CONCILIACION")
	private Long numeroConciliacion;
	
	@Column (name="ORDEN_CONCILIACION")
	private Long ordenConciliacion;
	
	@Enumerated(EnumType.STRING)
	@Column (name="ESTADO")
	private EstadoReglaConciliacionEnum estado;
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/

	public Long getIdConciliacion() {
		return idConciliacion;
	}

	public void setIdConciliacion(Long idConciliacion) {
		this.idConciliacion = idConciliacion;
	}

	public String getIdTipoConciliacion() {
		return idTipoConciliacion;
	}

	public void setIdTipoConciliacion(String idTipoConciliacion) {
		this.idTipoConciliacion = idTipoConciliacion;
	}

	public ShvParamReglaConciliacion getReglaConciliacion() {
		return reglaConciliacion;
	}

	public void setReglaConciliacion(ShvParamReglaConciliacion reglaConciliacion) {
		this.reglaConciliacion = reglaConciliacion;
	}

	public Long getNumeroConciliacion() {
		return numeroConciliacion;
	}

	public void setNumeroConciliacion(Long numeroConciliacion) {
		this.numeroConciliacion = numeroConciliacion;
	}

	public Long getOrdenConciliacion() {
		return ordenConciliacion;
	}

	public void setOrdenConciliacion(Long ordenConciliacion) {
		this.ordenConciliacion = ordenConciliacion;
	}

	public EstadoReglaConciliacionEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoReglaConciliacionEnum estado) {
		this.estado = estado;
	}


}
