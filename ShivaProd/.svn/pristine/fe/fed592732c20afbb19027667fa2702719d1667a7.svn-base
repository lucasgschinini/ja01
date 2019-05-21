package ar.com.telecom.shiva.negocio.servicios.impl;

import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.dao.IParametroDao;

public class ParametroServicioImpl implements IParametroServicio {

	@Autowired 
	IParametroDao parametroDao;
	
	public String getValorTexto(String clave) throws NegocioExcepcion {
		try {
			return parametroDao.getValorTexto(clave);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public void setValorTexto(String clave, String valorTexto) throws NegocioExcepcion {
		try {
			parametroDao.setValorTexto(clave, valorTexto);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public Long getValorNumerico(String clave) throws NegocioExcepcion {
		try {
			return parametroDao.getValorNumerico(clave);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public Statistics getEstadisticas() throws NegocioExcepcion {
		try {
			return parametroDao.getEstadisticas();
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Override
	public Long getLimiteParaExportarBusquedaValores() throws NegocioExcepcion {
		try {
			return parametroDao.getValorNumerico(Constantes.LIMITE_EXPORTACION_BUSQUEDA_VALORES);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public Long getLimiteParaExportarBusquedaCobros() throws NegocioExcepcion {
		try {
			return parametroDao.getValorNumerico(Constantes.LIMITE_EXPORTACION_BUSQUEDA_COBROS);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public Long getCantRegistrosAProcesar() throws NegocioExcepcion {
		try {
			return parametroDao.getValorNumerico(Constantes.CANT_REGISTROS_A_PROCESAR);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public Long getCantRegistrosPorVuelta() throws NegocioExcepcion {
		try {
			return parametroDao.getValorNumerico(Constantes.CANT_REGISTROS_POR_VUELTA);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	/********************************************************************************
	 * Getters & Setters
	 *******************************************************************************/
	
	public IParametroDao getParametroDao() {
		return parametroDao;
	}

	public void setParametroDao(IParametroDao parametroDao) {
		this.parametroDao = parametroDao;
	}


}
