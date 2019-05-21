package ar.com.telecom.shiva.persistencia.dao;

import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamCalendario;


public interface IParametroCalendarioDao {
	public List<ShvParamCalendario> buscaParamtroCalendarioApartirDe(Date fechaDesde) throws PersistenciaExcepcion;
}
