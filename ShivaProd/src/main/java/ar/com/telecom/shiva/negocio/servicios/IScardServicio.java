package ar.com.telecom.shiva.negocio.servicios;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobOperacion;

/**
 * 
 */
public interface IScardServicio {
	
	/**
	 * 
	 * @param operacion
	 * @throws NegocioExcepcion
	 */
	public void inicializarDocumentoOperacionCobro(ShvCobOperacion operacion) throws NegocioExcepcion;

	/**
	 * 
	 * @param operacion
	 * @throws NegocioExcepcion
	 */
	public void inicializarDocumentoOperacionDescobro(ShvCobOperacion operacion) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param fechaHasta
	 * @throws NegocioExcepcion
	 */
	public void generarDocumentos(String fechaHasta) throws NegocioExcepcion;
	
}
