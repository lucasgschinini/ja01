package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoCajaEnum;

@Entity
@Table (name = "SHV_CAJ_CAJA")
public class ShvCajCaja extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column (name = "ID_CAJA")
	private Long idCaja;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "ESTADO")
	private EstadoCajaEnum estado;
	
	@Column (name = "DESCRIPCION")
	private String descripcion;

	/**
	 * @return the id_caja
	 */
	public Long getIdCaja() {
		return idCaja;
	}

	/**
	 * @param id_caja the id_caja to set
	 */
	public void setIdCaja(Long id_caja) {
		this.idCaja = id_caja;
	}

	/**
	 * @return the estado
	 */
	public EstadoCajaEnum getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoCajaEnum estado) {
		this.estado = estado;
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
}
