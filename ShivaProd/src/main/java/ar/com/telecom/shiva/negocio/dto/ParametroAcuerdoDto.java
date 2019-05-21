/**
 * 
 */
package ar.com.telecom.shiva.negocio.dto;

import ar.com.telecom.shiva.base.dto.DTO;

public class ParametroAcuerdoDto extends DTO {
	private static final long serialVersionUID = 20170520L;

	private Integer idAcuerdo;
	private ParametroBancoCuentaDto bancoCuenta;
	private String descripcion;
	private Integer secuencial;
	private char conciliable;
	private ParametroBancoDto banco;
	/**
	 * @return the idAcuerdo
	 */
	public Integer getIdAcuerdo() {
		return idAcuerdo;
	}
	/**
	 * @param idAcuerdo the idAcuerdo to set
	 */
	public void setIdAcuerdo(Integer idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
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
	 * @return the secuencial
	 */
	public Integer getSecuencial() {
		return secuencial;
	}
	/**
	 * @param secuencial the secuencial to set
	 */
	public void setSecuencial(Integer secuencial) {
		this.secuencial = secuencial;
	}
	/**
	 * @return the conciliable
	 */
	public char getConciliable() {
		return conciliable;
	}
	/**
	 * @param conciliable the conciliable to set
	 */
	public void setConciliable(char conciliable) {
		this.conciliable = conciliable;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the bancoCuenta
	 */
	public ParametroBancoCuentaDto getBancoCuenta() {
		return bancoCuenta;
	}
	/**
	 * @param bancoCuenta the bancoCuenta to set
	 */
	public void setBancoCuenta(ParametroBancoCuentaDto bancoCuenta) {
		this.bancoCuenta = bancoCuenta;
	}
	/**
	 * @return the banco
	 */
	public ParametroBancoDto getBanco() {
		return banco;
	}
	/**
	 * @param banco the banco to set
	 */
	public void setBanco(ParametroBancoDto banco) {
		this.banco = banco;
	}
	
}
