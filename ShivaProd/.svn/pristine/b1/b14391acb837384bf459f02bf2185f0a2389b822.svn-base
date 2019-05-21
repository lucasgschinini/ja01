package ar.com.telecom.shiva.base.excepciones.otros;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;


/**
 * Excepcion del webservice
 *
 */
public class WebServiceExcepcion extends NegocioExcepcion {

	private static final long serialVersionUID = 1L;
	private boolean mensajeVacio;

	public WebServiceExcepcion() {
		super();
	}

	public WebServiceExcepcion(String message) {
		super(message);
	}
	
	public WebServiceExcepcion(String message, boolean mensajeVacio) {
		super(message);
		this.mensajeVacio = mensajeVacio;
	}

	public WebServiceExcepcion(Throwable cause) {
		super(cause);
	}

	public WebServiceExcepcion(String message, Throwable cause) {
		super(message, cause);
	}
	
	public WebServiceExcepcion(String message, Throwable cause, String mensajeAuxiliar) {
		super(message, cause);
		super.mensajeAuxiliar = mensajeAuxiliar;
	}
	
	public boolean isMensajeVacio() {
		return mensajeVacio;
	}

	public void setMensajeVacio(boolean mensajeVacio) {
		this.mensajeVacio = mensajeVacio;
	}

}
