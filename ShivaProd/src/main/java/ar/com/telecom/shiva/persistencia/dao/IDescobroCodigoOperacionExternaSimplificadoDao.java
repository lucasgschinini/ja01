/**
 * 
 */
package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDescobroCodigoOperacionExternaSimplificado;

/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public interface IDescobroCodigoOperacionExternaSimplificadoDao {

	/**
	 * Permite realizar la busqueda de codigos de operaciones externas filtrando por el ID de Descobro
	 * 
	 * @param idDescobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvCobDescobroCodigoOperacionExternaSimplificado> listarPorIdDescobro(Long idDescobro) throws PersistenciaExcepcion;
}
