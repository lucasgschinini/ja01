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
public class ShvParamTipoOtrosDebito extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column (name="ID_TIPO_OTROS_DEBITO")
	private String idTipoOtrosDebito;
	
	@Column(name="TIPO")
	private String tipo;
	
	@Column(name="AUD_REQUERIMIENTO_ORIGEN")
	private String audRequerimientoOrigen;
	
	@Override
	public Serializable getId() {
		return this.getIdTipoOtrosDebito();
	}

	/**
	 * @return the idTipoOtrosDebito
	 */
	public String getIdTipoOtrosDebito() {
		return idTipoOtrosDebito;
	}

	/**
	 * @param idTipoOtrosDebito the idTipoOtrosDebito to set
	 */
	public void setIdTipoOtrosDebito(String idTipoOtrosDebito) {
		this.idTipoOtrosDebito = idTipoOtrosDebito;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
