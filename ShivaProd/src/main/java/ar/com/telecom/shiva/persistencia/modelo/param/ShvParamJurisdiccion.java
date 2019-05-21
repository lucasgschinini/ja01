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
@Table(name = "SHV_PARAM_JURISDICCION")
public class ShvParamJurisdiccion extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_JURISDICCION", nullable = false)
	private String idJurisdiccion;

	@Column(name = "ID_PROVINCIA", nullable = false)
	private String provincia;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@Enumerated(EnumType.STRING)
	@Column(name = "HABILITADA", nullable = false)
	private SiNoEnum habilitada;

	@Column(name = "TOPE_MESES_FECHA_EMISION")
	private Integer topeMesesFechaEmision;

	public String getIdJurisdiccion() {
		return idJurisdiccion;
	}

	public void setIdJurisdiccion(String idJurisdiccion) {
		this.idJurisdiccion = idJurisdiccion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
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

	public Integer getTopeMesesFechaEmision() {
		return topeMesesFechaEmision;
	}

	public void setTopeMesesFechaEmision(Integer topeMesesFechaEmision) {
		this.topeMesesFechaEmision = topeMesesFechaEmision;
	}

}
