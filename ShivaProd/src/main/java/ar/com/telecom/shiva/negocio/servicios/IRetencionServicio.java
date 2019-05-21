package ar.com.telecom.shiva.negocio.servicios;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;



public interface IRetencionServicio {
	
	public void generarReporte(String fechaHasta) throws NegocioExcepcion;
}
