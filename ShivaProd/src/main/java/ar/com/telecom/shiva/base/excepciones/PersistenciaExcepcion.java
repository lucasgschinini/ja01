package ar.com.telecom.shiva.base.excepciones;

public class PersistenciaExcepcion extends Excepcion {

	/**	 */
	private static final long serialVersionUID = -5029207689823241705L;

	public PersistenciaExcepcion() {
		super();
	}

	public PersistenciaExcepcion(String message) {
		super(message);
	}

	public PersistenciaExcepcion(Throwable cause) {
		super(cause);
	}

	public PersistenciaExcepcion(String message, Throwable cause) {
		super(message, cause);
	}
}
