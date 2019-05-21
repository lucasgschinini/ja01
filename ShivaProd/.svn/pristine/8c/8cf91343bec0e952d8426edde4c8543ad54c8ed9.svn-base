/**
 * 
 */
package ar.com.telecom.shiva.negocio.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IAcuerdoServicio;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;

/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public class AcuerdoServicioImpl implements IAcuerdoServicio {

	@Autowired IAcuerdoDao acuerdoDao;
	
	@Override
	public ShvParamAcuerdo buscar(String id) throws NegocioExcepcion {
	
		try {
			return acuerdoDao.buscarAcuerdo(id);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}	
	}
}
