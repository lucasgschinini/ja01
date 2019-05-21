package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_ORGANISMO")
public class ShvParamOrganismo extends Modelo{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_ORGANISMO")
	private String idOrganismo;

	@Column (name="DESCRIPCION")
	private String descripcion;
	
	@Column (name="NUMERO_CLIENTE_ASOCIADO")
	private String numeroClienteAsociado;
	
	@Column (name="DESCRIPCION_ALTERNATIVA")
	private String descripcionAlternativa;

	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	public String getIdOrganismo() {
		return idOrganismo;
	}

	public void setIdOrganismo(String idOrganismo) {
		this.idOrganismo = idOrganismo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNumeroClienteAsociado() {
		return numeroClienteAsociado;
	}

	public void setNumeroClienteAsociado(String numeroClienteAsociado) {
		this.numeroClienteAsociado = numeroClienteAsociado;
	}
	/**
	 * @return the descripcionAlternativa
	 */
	public String getDescripcionAlternativa() {
		return descripcionAlternativa;
	}

	/**
	 * @param descripcionAlternativa the descripcionAlternativa to set
	 */
	public void setDescripcionAlternativa(String descripcionAlternativa) {
		this.descripcionAlternativa = descripcionAlternativa;
	}

}
