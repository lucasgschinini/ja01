package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrigen;

public interface IOrigenDao {

	ShvParamOrigen buscarOrigen(String id) throws PersistenciaExcepcion;

	List<ShvParamOrigen> listarOrigenPorIdEmpresaYIdSegmento(String idEmpresa, String idSegmento) throws PersistenciaExcepcion;
	
	List<ShvParamOrigen> listarOrigenPorIdEmpresaYIdSegmentoIdTipoValor(String idEmpresa, String idSegmento, String tipoValor) throws PersistenciaExcepcion;
	
	List<ShvParamOrigen> listarOrigenAnalista() throws PersistenciaExcepcion;
	
}
