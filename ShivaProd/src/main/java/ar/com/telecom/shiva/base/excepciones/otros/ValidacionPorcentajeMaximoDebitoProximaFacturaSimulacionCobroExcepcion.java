package ar.com.telecom.shiva.base.excepciones.otros;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

/**
 * @author Pablo M. Ibarrola
 *
 */
public class ValidacionPorcentajeMaximoDebitoProximaFacturaSimulacionCobroExcepcion extends NegocioExcepcion {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ValidacionPorcentajeMaximoDebitoProximaFacturaSimulacionCobroExcepcion() {
		super();
	}

	/**
	 * 
	 * @param message
	 */
	public ValidacionPorcentajeMaximoDebitoProximaFacturaSimulacionCobroExcepcion(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public ValidacionPorcentajeMaximoDebitoProximaFacturaSimulacionCobroExcepcion(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public ValidacionPorcentajeMaximoDebitoProximaFacturaSimulacionCobroExcepcion(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 * @param mensajeAuxiliar
	 */
	public ValidacionPorcentajeMaximoDebitoProximaFacturaSimulacionCobroExcepcion(String message, Throwable cause, String mensajeAuxiliar) {
		super(message, cause);
		super.mensajeAuxiliar = mensajeAuxiliar;
	}
}
