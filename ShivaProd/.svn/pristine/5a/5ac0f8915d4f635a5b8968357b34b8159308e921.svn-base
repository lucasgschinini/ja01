package ar.com.telecom.shiva.persistencia.dao;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;

public interface ICajaDao {
	
	/**
	 * Busca en la base las cajas cerradas y le cambia el estado a ABIERTA
	 */
	void abrirCajas() throws PersistenciaExcepcion;

	/**
	 * Busca en la base las cajas abiertas y le cambia el estado a CERRADA a
	 * partir de una fecha deseada.
	 */
	void cerrarCajas(String fecha) throws PersistenciaExcepcion;
}
