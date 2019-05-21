/**
 * 
 */
package ar.com.telecom.shiva.negocio.dto.cobros.simulacion;

import ar.com.telecom.shiva.base.enumeradores.TipoResultadoSimulacionEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;

/**
 * @author u564027
 *
 */
public class RespuestaInvocacion {

	private TipoResultadoSimulacionEnum resultado;
	private String codigoError;
	private StringBuffer descripcionError = new StringBuffer();
	
	/**
	 * 
	 */
	public RespuestaInvocacion () {

	}

	/**
	 * 
	 * @param resultado
	 * @param codigoError
	 * @param descripcionError
	 */
	public RespuestaInvocacion (TipoResultadoSimulacionEnum resultado, String codigoError, String descripcionError) {

		this.resultado = resultado;
		this.codigoError = codigoError;
		if (!Validaciones.isObjectNull(descripcionError)) {
			this.descripcionError = new StringBuffer(descripcionError);
		}
	}
	
	/**
	 * @return the resultado
	 */
	public TipoResultadoSimulacionEnum getResultado() {
		return resultado;
	}
	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(TipoResultadoSimulacionEnum resultado) {
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
