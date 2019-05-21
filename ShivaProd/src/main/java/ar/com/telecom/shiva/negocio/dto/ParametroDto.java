/**
 * 
 */
package ar.com.telecom.shiva.negocio.dto;

import ar.com.telecom.shiva.base.dto.DTO;

/**
 * @author pablo.m.ibarrola
 *
 */
public class ParametroDto extends DTO {

	private static final long serialVersionUID = 1L;
	
	private String auditoriaRequerimientoOrigen;

	/**
	 * @return the auditoriaRequerimientoOrigen
	 */
	public String getAuditoriaRequerimientoOrigen() {
		return auditoriaRequerimientoOrigen;
	}

	/**
	 * @param auditoriaRequerimientoOrigen the auditoriaRequerimientoOrigen to set
	 */
	public void setAuditoriaRequerimientoOrigen(String auditoriaRequerimientoOrigen) {
		this.auditoriaRequerimientoOrigen = auditoriaRequerimientoOrigen;
	}
}
