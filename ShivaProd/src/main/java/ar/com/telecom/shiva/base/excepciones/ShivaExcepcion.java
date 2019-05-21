package ar.com.telecom.shiva.base.excepciones;


/**
 * Excepcion utilizada en la presentacion
 *
 */
public class ShivaExcepcion extends Excepcion {

	private static final long serialVersionUID = 1L;
	
	public ShivaExcepcion() {
		super();
	}

	public ShivaExcepcion(String message) {
		super(message);
	}

	public ShivaExcepcion(Throwable cause) {
		super(cause);
	}

	public ShivaExcepcion(String message, Throwable cause) {
		super(message, cause);
	}
}

