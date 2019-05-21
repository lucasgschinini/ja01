package ar.com.telecom.shiva.base.excepciones.otros;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;


/**
 * Excepcion del MQ Series
 *
 */
public class JmsExcepcion extends NegocioExcepcion {

	private static final long serialVersionUID = 1L;

	private String mensajeAuxiliar;
	public JmsExcepcion() {
		super();
	}

	public JmsExcepcion(String message) {
		super(message);
	}

	public JmsExcepcion(Throwable cause) {
		super(cause);
	}

	public JmsExcepcion(String message, Throwable cause) {
		super(message, cause);
	}
	
	public JmsExcepcion(String message, Throwable cause, String mensajeAuxiliar) {
		super(message, cause);
		this.mensajeAuxiliar = mensajeAuxiliar;
	}

	public JmsExcepcion(String message, String mensajeAuxiliar) {
		super(message);
		this.mensajeAuxiliar = mensajeAuxiliar;
	}
	
	public String getMensajeAuxiliar() {
		return mensajeAuxiliar;
	}
}
