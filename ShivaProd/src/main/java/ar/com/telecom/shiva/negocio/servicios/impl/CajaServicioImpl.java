package ar.com.telecom.shiva.negocio.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.servicios.ICajaServicio;
import ar.com.telecom.shiva.persistencia.dao.ICajaDao;

public class CajaServicioImpl implements ICajaServicio {
	
	@Autowired	
	private ICajaDao cajaDao;
	

	@Override
	public void abrirCajas() throws NegocioExcepcion {
		try {
			cajaDao.abrirCajas();
		} catch (PersistenciaExcepcion pe) {
			throw new NegocioExcepcion(pe.getMessage(), pe);
		}
	}

	@Override
	public void cerrarCajas(String fecha) throws NegocioExcepcion {
		try {
			cajaDao.cerrarCajas(fecha);
		} catch (PersistenciaExcepcion pe) {
			throw new NegocioExcepcion(pe.getMessage(), pe);
		}
	}

	public ICajaDao getCajaDao() {
		return cajaDao;
	}

	public void setCajaDao(ICajaDao cajaDao) {
		this.cajaDao = cajaDao;
	}

}
