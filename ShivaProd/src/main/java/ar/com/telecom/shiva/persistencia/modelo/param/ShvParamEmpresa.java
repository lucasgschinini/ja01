package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoActivoInactivoEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_EMPRESA")
public class ShvParamEmpresa extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_EMPRESA")
	private String idEmpresa;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "CUIT")
	private String cuit;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO", nullable = false)
	private EstadoActivoInactivoEnum estado;

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
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

	public EstadoActivoInactivoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoActivoInactivoEnum estado) {
		this.estado = estado;
	}

}
