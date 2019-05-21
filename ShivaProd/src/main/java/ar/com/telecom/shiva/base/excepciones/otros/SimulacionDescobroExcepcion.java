package ar.com.telecom.shiva.base.excepciones.otros;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

/**
 * @author Pablo M. Ibarrola
 *
 */
public class SimulacionDescobroExcepcion extends NegocioExcepcion {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public SimulacionDescobroExcepcion() {
		super();
	}

	/**
	 * 
	 * @param message
	 */
	public SimulacionDescobroExcepcion(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public SimulacionDescobroExcepcion(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public SimulacionDescobroExcepcion(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 * @param mensajeAuxiliar
	 */
	public SimulacionDescobroExcepcion(String message, Throwable cause, String mensajeAuxiliar) {
		super(message, cause);
		super.mensajeAuxiliar = mensajeAuxiliar;
	}
}
