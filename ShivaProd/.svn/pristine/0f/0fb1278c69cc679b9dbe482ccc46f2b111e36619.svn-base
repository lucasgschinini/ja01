package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvValConstanciaRecepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvValConstanciaRecepcionValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValConstanciaRecepcionValorSimplificado;

/**
 * 
 *
 */
public interface IValorConstanciaRecepcionDao {

	/**
	 * 
	 * @param constancia
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvValConstanciaRecepcion actualizarConstanciaRecepcion(ShvValConstanciaRecepcion constancia) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param nuevaConstancia
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvValConstanciaRecepcionValor actualizarConstanciaRecepcionValor(ShvValConstanciaRecepcionValor nuevaConstancia) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idValor
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvValConstanciaRecepcionValor buscarConstanciaRecepcionAsignadaAValor(Long idValor) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idValor
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvValConstanciaRecepcionValorSimplificado buscarConstanciaRecepcionAsignadaAValorSimplificado(Long idValor) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idConstanciaRecepcion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvValValor> buscarValoresAsociadosAConstancia(Integer idConstanciaRecepcion) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param constanciaRecepcionValorActual
	 * @throws PersistenciaExcepcion
	 */
	void eliminarRelacionConstanciaValor(ShvValConstanciaRecepcionValor constanciaRecepcionValorActual) throws PersistenciaExcepcion;
}
