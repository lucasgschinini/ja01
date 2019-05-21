package ar.com.telecom.shiva.persistencia.modelo.param;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name="SHV_PARAM_CALENDARIO")
public class ShvParamCalendario extends Modelo {
	private static final long serialVersionUID = 20170419L;

	@Id
	@Column(name="ID_CALENDARIO")
	private Long idCalendario;
	@Column(name="FECHA")
	private Date fecha;
	@Column(name="DESCRIPCION")
	private String descripcion;

	public ShvParamCalendario() {}

/***************************************************************************
 * GETTERS & SETTERS
 * ************************************************************************/

	/**
	 * @return the idCalendario
	 */
	public Long getIdCalendario() {
		return idCalendario;
	}

	/**
	 * @param idCalendario the idCalendario to set
	 */
	public void setIdCalendario(Long idCalendario) {
		this.idCalendario = idCalendario;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

