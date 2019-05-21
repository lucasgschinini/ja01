package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrganismo;

public interface IOrganismoDao {

	ShvParamOrganismo buscarOrganismo(String id) throws PersistenciaExcepcion;

	List<ShvParamOrganismo> listarOrganismos() throws PersistenciaExcepcion;

	ShvParamOrganismo buscarDescripcionAlternativa(String descripcion) throws PersistenciaExcepcion; 
}
