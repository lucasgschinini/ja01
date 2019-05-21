package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvValConstanciaRecepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvValConstanciaRecepcionValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValConstanciaRecepcionValorSimplificado;

/**
 * 
 */
public interface IValorConstanciaRecepcionServicio extends IServicio {

	/**
	 * 
	 * @param constancia
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvValConstanciaRecepcion actualizarConstanciaRecepcion(ShvValConstanciaRecepcion constancia) throws NegocioExcepcion;

	/**
	 * 
	 * @param idValor
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvValConstanciaRecepcionValor buscarConstanciaRecepcionAsignadaAValor(Long idValor) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idValor
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvValConstanciaRecepcionValorSimplificado buscarConstanciaRecepcionAsignadaAValorSimplificado(Long idValor) throws NegocioExcepcion;

	/**
	 * 
	 * @param nuevaConstancia
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvValConstanciaRecepcionValor actualizarConstanciaRecepcionValor(ShvValConstanciaRecepcionValor nuevaConstancia) throws NegocioExcepcion;

	/**
	 * 
	 * @param idConstanciaRecepcion
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvValValor> buscarValoresAsociadosAConstancia(Integer idConstanciaRecepcion) throws NegocioExcepcion;

	/**
	 * 
	 * @param valor
	 * @param nuevaConstancia
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvValConstanciaRecepcionValor actualizarRelacionConstanciaValor(ShvValValor valor, ShvValConstanciaRecepcion nuevaConstancia) throws NegocioExcepcion;

	/**
	 * 
	 * @param constanciaRecepcionValor
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvValValor> retornarListaValores(List<ShvValConstanciaRecepcionValor> constanciaRecepcionValor) throws NegocioExcepcion;
	
}	
