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
public class ShvParamSistExtApliCobro extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column (name="ID_SISTEMA_EXTERN_APLICA_COBRO")
	private String idSistemaExternAplicaCobro;
	
	@Column(name="AUD_REQUERIMIENTO_ORIGEN")
	private String audRequerimientoOrigen;
	
	@Override
	public Serializable getId() {
		return this.getIdSistemaExternAplicaCobro();
	}

	
	/**
	 * @return the idSistemaExternAplicaCobro
	 */
	public String getIdSistemaExternAplicaCobro() {
		return idSistemaExternAplicaCobro;
	}

	/**
	 * @param idSistemaExternAplicaCobro the idSistemaExternAplicaCobro to set
	 */
	public void setIdSistemaExternAplicaCobro(String idSistemaExternAplicaCobro) {
		this.idSistemaExternAplicaCobro = idSistemaExternAplicaCobro;
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
