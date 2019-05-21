package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdClienteSimplificado;

public interface ICobroOnlineClienteDao {
	
	ShvCobEdCliente guardarCliente(ShvCobEdCliente cliente) throws PersistenciaExcepcion;
	
	void borrarClientesDelCobro(Long idCobro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idCobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvCobEdCliente> listarClientesPorIdCobro(Long idCobro) throws PersistenciaExcepcion;
	
	/**
	 * Retorna una lista de clientes de un cobro
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvCobEdCliente> listarClientesPorIdOperacionCobro(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * Busca un cliente por idClienteLegado dentro de un cobro dado
	 *  
	 * @param idCobro
	 * @param idClienteLegado
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobEdCliente buscarClientePorIdCobroYIdClienteLegado(Long idCobro, Long idClienteLegado) throws PersistenciaExcepcion;
	
	/**
	 * Busca un cliente por idClienteLegado dentro de una operacion de cobro dada 
	 * 
	 * @param idCobro
	 * @param idClienteLegado
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobEdCliente buscarClientePorIdOperacionCobroYIdClienteLegado(Long idOperacion, Long idClienteLegado) throws PersistenciaExcepcion;

	/**
	 * Busca un cliente por idClienteLegado dentro de una operacion de descobro dada 
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobEdCliente buscarClientePorIdOperacionDesCobroYIdClienteLegado(Long idOperacion, Long idClienteLegado) throws PersistenciaExcepcion;
	
	/**
	 * Busca un cliente por idClienteLegado dentro de una operacion de descobro dada 
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvCobEdClienteSimplificado> listarClientesSimplificadosPorIdCobro(Long idCobro) throws PersistenciaExcepcion;
}
