package ar.com.telecom.shiva.persistencia.modelo.param;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

/**
 * @author U587496 Guido.Settecerze
 */

@Entity
@Table(name = "SHV_PARAM_TIPO_MEDIO_PAGO")
public class ShvParamSociedadGestionada extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column (name="ID_SOCIEDAD_GESTIONADA")
	private String idSociedadGestionada;
	
	@Column(name="AUD_REQUERIMIENTO_ORIGEN")
	private String audRequerimientoOrigen;
	
	@Override
	public Serializable getId() {
		return this.getIdSociedadGestionada();
	}

	/**
	 * @return the idSociedadGestionada
	 */
	public String getIdSociedadGestionada() {
		return idSociedadGestionada;
	}

	/**
	 * @param idSociedadGestionada the idSociedadGestionada to set
	 */
	public void setIdSociedadGestionada(String idSociedadGestionada) {
		this.idSociedadGestionada = idSociedadGestionada;
	}

	/**
	 * @return the audRequerimientoOrigen
	 */
	public String getAudRequerimientoOrigen() {
		return audRequerimientoOrigen;
	}

	/**
	 * @param audRequerimientoOrigen the audRequerimientoOrigen to set
	 */
	public void setAudRequerimientoOrigen(String audRequerimientoOrigen) {
		this.audRequerimientoOrigen = audRequerimientoOrigen;
	}

}
