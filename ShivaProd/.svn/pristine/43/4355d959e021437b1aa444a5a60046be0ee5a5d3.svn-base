package ar.com.telecom.shiva.base.excepciones;

import java.util.Date;

public class Excepcion extends Exception {

	/**	 */
	private static final long serialVersionUID = -5029207689823241705L;

	private long timeError;
	
	public Excepcion() {
		super();
		this.timeError = new Date().getTime();
	}

	public Excepcion(String message) {
		super(message);
		this.timeError = new Date().getTime();
	}

	public Excepcion(Throwable cause) {
		super(cause);
		this.timeError = new Date().getTime();
	}

	public Excepcion(String message, Throwable cause) {
		super(message, cause);
		this.timeError = new Date().getTime();
	}

	public long getTimeError() {
		return timeError;
	}

	
}
