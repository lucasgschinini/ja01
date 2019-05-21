package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IParametroCalendarioDao;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamCalendario;
import ar.com.telecom.shiva.persistencia.repository.ParametroCalendarioRepositorio;

public class ParametroCalendarioDaoImpl extends Dao implements IParametroCalendarioDao {

	@Autowired ParametroCalendarioRepositorio parametroCalendarioRepositorio;

	
	@Override
	public List<ShvParamCalendario> buscaParamtroCalendarioApartirDe(Date fechaDesde) throws PersistenciaExcepcion {
		return parametroCalendarioRepositorio.buscaParamtroCalendarioApartirDe(fechaDesde);
	}
}
