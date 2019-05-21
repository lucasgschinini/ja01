package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;

public interface IBancoDao {

	ShvParamBanco buscarBanco(String idBanco) throws PersistenciaExcepcion;

	List<ShvParamBanco> buscarBancoOrigen() throws PersistenciaExcepcion;
	List<ShvParamBanco> buscarBancoOrigen(String idEmpresa) throws PersistenciaExcepcion;
	public List<ShvParamBanco> buscarBancoOrigenOrdenadoPorDescripcion(String idEmpresa) throws PersistenciaExcepcion;

}
