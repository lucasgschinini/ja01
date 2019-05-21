/**
 * 
 */
package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

/**
 * @author u586743
 *
 */
@Entity
@Table(name = "SHV_PARAM_PROVINCIA")
public class ShvParamProvincia extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_PROVINCIA", nullable = false)
	private String idProvincia;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	public ShvParamProvincia() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the provincia
	 */
	public String getIdProvincia() {
		return idProvincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setIdProvincia(String provincia) {
		this.idProvincia = provincia;
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
