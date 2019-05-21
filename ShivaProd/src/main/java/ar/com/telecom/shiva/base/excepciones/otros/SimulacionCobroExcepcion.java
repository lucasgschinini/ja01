package ar.com.telecom.shiva.base.excepciones.otros;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

/**
 * @author Pablo M. Ibarrola
 *
 */
public class SimulacionCobroExcepcion extends NegocioExcepcion {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public SimulacionCobroExcepcion() {
		super();
	}

	/**
	 * 
	 * @param message
	 */
	public SimulacionCobroExcepcion(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public SimulacionCobroExcepcion(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public SimulacionCobroExcepcion(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 * @param mensajeAuxiliar
	 */
	public SimulacionCobroExcepcion(String message, Throwable cause, String mensajeAuxiliar) {
		super(message, cause);
		super.mensajeAuxiliar = mensajeAuxiliar;
	}
}
