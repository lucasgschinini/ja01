package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.conciliacion.definicion.ReglaConciliacionDto;
import ar.com.telecom.shiva.negocio.mapeos.ReglaConciliacionMapeador;
import ar.com.telecom.shiva.negocio.servicios.IReglaConciliacionServicio;
import ar.com.telecom.shiva.persistencia.dao.IReglaConciliacionDao;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamConciliacion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamReglaConciliacion;

public class ReglaConciliacionServicioImpl extends Servicio implements IReglaConciliacionServicio {

	@Autowired
	private IReglaConciliacionDao reglaConciliacionDao;
	
	@Autowired
	private ReglaConciliacionMapeador reglaConciliacionMapeador;
	
	public List<ReglaConciliacionDto> listarReglasConciliacion(List<ReglaConciliacionDto> reglas) throws NegocioExcepcion {
		try {
			Collection<ShvParamReglaConciliacion> listaReglasModelo = reglaConciliacionDao.listarReglasConciliacion();
			Collection<ShvParamConciliacion> listaConciliacionesModelo = reglaConciliacionDao.listarConciliaciones();
			
			return reglaConciliacionMapeador.mapearReglas(reglas, listaReglasModelo, listaConciliacionesModelo);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	public IReglaConciliacionDao getReglaConciliacionDao() {
		return reglaConciliacionDao;
	}

	public void setReglaConciliacionDao(IReglaConciliacionDao reglaConciliacionDao) {
		this.reglaConciliacionDao = reglaConciliacionDao;
	}

	public ReglaConciliacionMapeador getReglaConciliacionMapeador() {
		return reglaConciliacionMapeador;
	}

	public void setReglaConciliacionMapeador(
			ReglaConciliacionMapeador reglaConciliacionMapeador) {
		this.reglaConciliacionMapeador = reglaConciliacionMapeador;
	}


}
