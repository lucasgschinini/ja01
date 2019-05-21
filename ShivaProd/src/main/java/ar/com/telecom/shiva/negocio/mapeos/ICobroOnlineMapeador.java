package ar.com.telecom.shiva.negocio.mapeos;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

public interface ICobroOnlineMapeador {
	
	/**
	 * Mapeo las listas .
	 * @param campos
	 * @param debitoDto
	 * @return
	 */
	public abstract Modelo mapListas(DTO dto, Modelo vo) throws NegocioExcepcion;
	
}
