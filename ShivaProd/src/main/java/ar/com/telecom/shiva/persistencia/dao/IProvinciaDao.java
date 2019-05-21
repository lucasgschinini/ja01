/**
 * 
 */
package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamProvincia;

/**
 * @author u586743
 *
 */
public interface IProvinciaDao {

	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvParamProvincia> listarProvincias() throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idProvincia
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvParamProvincia listarProvinciasPorId(String idProvincia) throws PersistenciaExcepcion;
	
	
	
	
}
