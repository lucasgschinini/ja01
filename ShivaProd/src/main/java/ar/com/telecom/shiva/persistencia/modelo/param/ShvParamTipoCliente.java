package ar.com.telecom.shiva.persistencia.modelo.param;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_TIPO_CLIENTE")
public class ShvParamTipoCliente extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_TIPO_CLIENTE")
	private String idTipoCliente;
	
	@Column (name="DESCRIPCION")
	private String descripcion;

	/**
	 * @return the idTipoCliente
	 */
	public String getIdTipoCliente() {
		return idTipoCliente;
	}

	/**
	 * @param idTipoCliente the idTipoCliente to set
	 */
	public void setIdTipoCliente(String idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
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

	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/

			
}
