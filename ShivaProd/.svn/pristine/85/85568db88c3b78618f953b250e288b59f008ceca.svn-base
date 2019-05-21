package ar.com.telecom.shiva.negocio.servicios;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;


public interface ICajaServicio {
	
	
	/**
	 * Busca y cambia los estados a ABIERTA de todas las cajas habilitadas. 
	 */
	public void abrirCajas() throws NegocioExcepcion;
	
	
	/**
	 * Busca y cambia los estados a CERRADA de todas las cajas abiertas
	 * hasta una fecha dada.
	 */
	public void cerrarCajas(String fecha) throws NegocioExcepcion;
}
