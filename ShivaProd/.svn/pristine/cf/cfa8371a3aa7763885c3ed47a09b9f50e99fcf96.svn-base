package ar.com.telecom.shiva.persistencia.modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "SHV_SEG_ACCION_ESTADO")
public class ShvSegAccionEstado extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name="ID_ACCION_ESTADO")
	private Integer idAccionEstado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SEG_ACCION", nullable=false)
	private ShvSegAccion idAccion;

	@Column(name="ESTADO")
	private String estado;
	
	@Column(name="SUBESTADO")
	private String subestado;

	
	/**
	 * @return the idAccionEstado
	 */
	public Integer getIdAccionEstado() {
		return idAccionEstado;
	}

	/**
	 * @param idAccionEstado the idAccionEstado to set
	 */
	public void setIdAccionEstado(Integer idAccionEstado) {
		this.idAccionEstado = idAccionEstado;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the subestado
	 */
	public String getSubestado() {
		return subestado;
	}

	/**
	 * @param subestado the subestado to set
	 */
	public void setSubestado(String subestado) {
		this.subestado = subestado;
	}

	/**
	 * @return the idAccion
	 */
	public ShvSegAccion getIdAccion() {
		return idAccion;
	}

	/**
	 * @param idAccion the idAccion to set
	 */
	public void setIdAccion(ShvSegAccion idAccion) {
		this.idAccion = idAccion;
	}
}
