package ar.com.telecom.shiva.base.excepciones;

public class NegocioExcepcion extends Excepcion {

	/**	 */
	private static final long serialVersionUID = -5029207689823241705L;
	protected String mensajeAuxiliar;
	
	public NegocioExcepcion() {
		super();
	}

	public NegocioExcepcion(String message) {
		super(message);
	}

	public NegocioExcepcion(Throwable cause) {
		super(cause);
	}

	public NegocioExcepcion(String message, Throwable cause) {
		super(message, cause);
	}

	public NegocioExcepcion(String message, Throwable cause, String mensajeAuxiliar) {
		super(message, cause);
		this.mensajeAuxiliar = mensajeAuxiliar;
	}
	
	public NegocioExcepcion(String message, String mensajeAuxiliar) {
		super(message);
		this.mensajeAuxiliar = mensajeAuxiliar;
	}
	
	public String getMensajeAuxiliar() {
		return mensajeAuxiliar;
	}
}
