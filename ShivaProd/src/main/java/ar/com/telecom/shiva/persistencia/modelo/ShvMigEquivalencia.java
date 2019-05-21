package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "SHV_MIG_EQUIVALENCIA")
public class ShvMigEquivalencia {

	@Id
	@Column(name="ID")
	private Long idEquivalencia;
	@Column(name="DESCRIPCION")
	private String descripcion;
	@Column(name="ORIGEN")
	private String origen;
	@Column(name="DESTINO")
	private String destino;
	/**
	 * @return the idEquivalencia
	 */
	public Long getIdEquivalencia() {
		return idEquivalencia;
	}
	/**
	 * @param idEquivalencia the idEquivalencia to set
	 */
	public void setIdEquivalencia(Long idEquivalencia) {
		this.idEquivalencia = idEquivalencia;
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
	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}
	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	/**
	 * @return the destino
	 */
	public String getDestino() {
		return destino;
	}
	/**
	 * @param destino the destino to set
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	
}
