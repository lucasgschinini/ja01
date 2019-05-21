/**
 * 
 */
package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvParamCotizacion;
import ar.com.telecom.shiva.presentacion.bean.filtro.CotizacionFiltro;

/**
 * @author u586743
 *
 */
public interface ICotizacionDao {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvParamCotizacion buscar(Object id) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvParamCotizacion> listar(CotizacionFiltro filtro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param cotizacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvParamCotizacion crear(ShvParamCotizacion cotizacion) throws PersistenciaExcepcion;

}
