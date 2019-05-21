package ar.com.telecom.shiva.negocio.servicios;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

public interface ISubdiarioServicio {
	
	void generarSubdiario(String fechaHasta) throws NegocioExcepcion;
}
