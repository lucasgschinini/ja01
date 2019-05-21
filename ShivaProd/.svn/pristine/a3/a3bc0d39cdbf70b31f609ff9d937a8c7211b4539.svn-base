package ar.com.telecom.shiva.negocio.dto.cobros.imputacion;

import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;


public class RespuestaInvocacion {

	private TipoResultadoEnum resultado;
	private String codigoError;
	private StringBuffer descripcionError = new StringBuffer();
	
	/**
	 * Constructor
	 */
	public RespuestaInvocacion () {
	}

	/**
	 * Constructor
	 * @param resultado
	 * @param codigoError
	 * @param descripcionError
	 */
	public RespuestaInvocacion (TipoResultadoEnum resultado, String codigoError, String descripcionError) {

		this.resultado = resultado;
		this.codigoError = codigoError;
		if (!Validaciones.isObjectNull(descripcionError)) {
			this.descripcionError = new StringBuffer(descripcionError);
		}
	}
	
	/**
	 * 
	 */
	public String toString() {
		String str = "[resultadoInvocacion:" + String.valueOf(resultado) + "]"
			+ "[codigoError:" + String.valueOf(codigoError) + "]"
			+ "[descripcionError:" + String.valueOf(descripcionError) + "]";
		return str;
	}
	
	
	/**
	 * @return the resultado
	 */
	public TipoResultadoEnum getResultado() {
		return resultado;
	}
	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(TipoResultadoEnum resultado) {
		this.resultado = resultado;
	}
	/**
	 * @return the codigoError
	 */
	public String getCodigoError() {
		return codigoError;
	}
	/**
	 * @param codigoError the codigoError to set
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}
	/**
	 * @return the descripcionError
	 */
	public StringBuffer getDescripcionError() {
		return descripcionError;
	}
	/**
	 * @param descripcionError the descripcionError to set
	 */
	public void setDescripcionError(StringBuffer descripcionError) {
		this.descripcionError = descripcionError;
	}
}
