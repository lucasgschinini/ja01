package ar.com.telecom.shiva.negocio.servicios;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;



public interface IMigracionServicio {
	
	public void migracionValores() throws NegocioExcepcion;
}
