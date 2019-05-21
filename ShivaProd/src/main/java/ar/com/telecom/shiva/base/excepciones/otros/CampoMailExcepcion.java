package ar.com.telecom.shiva.base.excepciones.otros;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

public class CampoMailExcepcion extends NegocioExcepcion {

	private static final long serialVersionUID = 8560264713165502467L;
	
	public CampoMailExcepcion(String mensaje){
		super(mensaje);
	}

}
