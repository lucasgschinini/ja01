package ar.com.telecom.shiva.base.excepciones.otros;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;


/**
 * Excepcion del webservice
 *
 */
public class WorkflowExcepcion extends NegocioExcepcion {

	private static final long serialVersionUID = 1L;

	public WorkflowExcepcion() {
		super();
	}

	public WorkflowExcepcion(String message) {
		super(message);
	}

	public WorkflowExcepcion(Throwable cause) {
		super(cause);
	}

	public WorkflowExcepcion(String message, Throwable cause) {
		super(message, cause);
	}
}
