package ar.com.telecom.shiva.base.excepciones.otros;



public class ValidacionChequeAplicadoEnOperacionExcepcion extends ValidacionExcepcion {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ValidacionChequeAplicadoEnOperacionExcepcion() {
		super();
	}

	/**
	 * 
	 * @param message
	 */
	public ValidacionChequeAplicadoEnOperacionExcepcion(String message) {
		super(message);
	}

	

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public ValidacionChequeAplicadoEnOperacionExcepcion(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 * @param mensajeAuxiliar
	 */
	public ValidacionChequeAplicadoEnOperacionExcepcion(String message, Throwable cause, String mensajeAuxiliar) {
		super(message, cause);
		super.mensajeAuxiliar = mensajeAuxiliar;
	}
}
