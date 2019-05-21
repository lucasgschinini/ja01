package ar.com.telecom.shiva.base.excepciones.otros;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;


/**
 * Excepcion del webservice
 *
 */
public class WebServiceFormatoMensajeExcepcion extends NegocioExcepcion {

	private static final long serialVersionUID = 1L;

	public WebServiceFormatoMensajeExcepcion() {
		super();
	}

	public WebServiceFormatoMensajeExcepcion(String message) {
		super(message);
	}

	public WebServiceFormatoMensajeExcepcion(Throwable cause) {
		super(cause);
	}

	public WebServiceFormatoMensajeExcepcion(String message, Throwable cause) {
		super(message, cause);
	}
}
