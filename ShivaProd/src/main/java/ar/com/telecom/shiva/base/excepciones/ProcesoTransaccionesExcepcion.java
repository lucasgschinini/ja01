package ar.com.telecom.shiva.base.excepciones;

public class ProcesoTransaccionesExcepcion extends RuntimeException {
	
	private static final long serialVersionUID = 5307635223283516749L;

	public ProcesoTransaccionesExcepcion() {
		super();
	}

	public ProcesoTransaccionesExcepcion(String message) {
		super(message);
	}

	public ProcesoTransaccionesExcepcion(Throwable cause) {
		super(cause);
	}

	public ProcesoTransaccionesExcepcion(String message, Throwable cause) {
		super(message, cause);
	}

}
