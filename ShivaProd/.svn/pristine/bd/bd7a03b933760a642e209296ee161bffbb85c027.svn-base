package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_TIPO_RET_IMPUESTO")
public class ShvParamTipoRetencionImpuesto extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_TIPO_RETENCION_IMPUESTO", nullable = false)
	private Long idTipoRetencionImpuesto;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@Enumerated(EnumType.STRING)
	@Column(name = "HABILITADA", nullable = false)
	private SiNoEnum habilitada;

	public Long getIdTipoRetencionImpuesto() {
		return idTipoRetencionImpuesto;
	}

	public void setIdTipoRetencionImpuesto(Long idTipoRetencionImpuesto) {
		this.idTipoRetencionImpuesto = idTipoRetencionImpuesto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public SiNoEnum getHabilitada() {
		return habilitada;
	}

	public void setHabilitada(SiNoEnum habilitada) {
		this.habilitada = habilitada;
	}

}
