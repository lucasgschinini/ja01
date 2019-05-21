package ar.com.telecom.shiva.persistencia.dao;

import java.util.Collection;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public interface IDao {
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	Modelo buscar(Object id) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	Collection<Modelo> listar(Filtro filtro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param modelo
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	Modelo crear(Modelo modelo)throws PersistenciaExcepcion;

	/**
	 * 
	 * @param modelo
	 * @throws PersistenciaExcepcion
	 */
	void modificar(Modelo modelo) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param modelo
	 * @throws PersistenciaExcepcion
	 */
	void eliminar(Modelo modelo) throws PersistenciaExcepcion;
}
