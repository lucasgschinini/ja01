/**
 * 
 */
package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ICotizacionDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvParamCotizacion;
import ar.com.telecom.shiva.persistencia.repository.CotizacionRepositorio;
import ar.com.telecom.shiva.presentacion.bean.filtro.CotizacionFiltro;

/**
 * @author u586743
 *
 */
public class CotizacionDaoImpl extends Dao implements ICotizacionDao {

	@Autowired
	CotizacionRepositorio cotizacionRepositorio;
	

	@Override
	public ShvParamCotizacion buscar(Object id) throws PersistenciaExcepcion {

		return cotizacionRepositorio.findOne((Long) id);
	}

	@Override
	public List<ShvParamCotizacion> listar(CotizacionFiltro filtro) throws PersistenciaExcepcion {
		
		try {

			QueryParametrosUtil qp = new QueryParametrosUtil( "from ShvParamCotizacion as cotizacion where "
															+ "cotizacion.monedaDestino = ? and "
															+ "cotizacion.monedaProcedencia = ? and "
															+ "cotizacion.fechaValidezDesde = "
															+ "(select max(cotizacion.fechaValidezDesde) "
															+ "from ShvParamCotizacion as cotizacion "
															+ "where cotizacion.fechaValidezDesde <= ? and "
															+ "cotizacion.monedaProcedencia = ? ) ");
			
			qp.addParametro (filtro.getMonedaDestino());
			qp.addParametro	(filtro.getMonedaProcedencia());
			qp.addParametro (filtro.getFechaValidezDesde());
			qp.addParametro	(filtro.getMonedaProcedencia());
			List<ShvParamCotizacion> list = (List<ShvParamCotizacion>) buscarUsandoQueryConParametros(qp);
			
			if (list.isEmpty()) {
				return null;
			}
			
			return list;

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public ShvParamCotizacion crear(ShvParamCotizacion cotizacion) throws PersistenciaExcepcion {

		try {
			ShvParamCotizacion cotizacionBD = cotizacionRepositorio.save(cotizacion);
			cotizacionRepositorio.flush();
			return cotizacionBD;
			
		} catch(DataIntegrityViolationException e) {
			Traza.error(this.getClass(), "Error de registro duplicado al intentar crear el registro con los valores: " + cotizacion.toString());
			throw new PersistenciaExcepcion(e);

		} catch(Exception e) {
			Traza.error(this.getClass(), "Error al intengar crear el registro con los valores: " + cotizacion.toString());
			throw new PersistenciaExcepcion(e);
		}
	}
}
