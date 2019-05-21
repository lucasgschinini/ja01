package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCuenta;

public interface IBancoCuentaDao {

	ShvParamBancoCuenta buscarBancoCuentaPorIdAcuerdo(String idAcuerdo) throws PersistenciaExcepcion;
	
	ShvParamBancoCuenta buscarBancoCuentaPorIdCuenta(String idCuenta) throws PersistenciaExcepcion;
	
	ShvParamBanco buscarBancoPorIdCuenta(String idCuenta) throws PersistenciaExcepcion;
	
	List<ShvParamBancoCuenta> listarCuentaPorIdBanco(String idBanco, String esConciliable) throws PersistenciaExcepcion;
	
	List<ShvParamBancoCuenta> actualizarCuentaPorIdOrigen(String idEmpresa, String idSegmento, String idOrigen) throws PersistenciaExcepcion;
	
	List<ShvParamBancoCuenta> actualizarCuentaPorIdSegmento(String idEmpresa, String idSegmento) throws PersistenciaExcepcion;
	
	List<ShvParamBanco> actualizarBancoPorIdOrigen(String idEmpresa, String idSegmento, String idOrigen) throws PersistenciaExcepcion;
	
	List<ShvParamBanco> actualizarBancoPorIdOrigenValor(String idEmpresa, String idSegmento, String idOrigen, String tipoValor) throws PersistenciaExcepcion;
	
	List<ShvParamBancoCuenta> actualizarCuentaPorIdOrigenValor(String idEmpresa, String idSegmento, String idOrigen, String tipoValor) throws PersistenciaExcepcion;	
	
	List<ShvParamBanco> actualizarBancoPorAcuerdo(String idAcuerdo) throws PersistenciaExcepcion;
	
	List<ShvParamBanco> listarBancosNoConciliables(List<ShvParamAcuerdo> listaAcuerdo) throws PersistenciaExcepcion;	
	
	List<ShvParamBancoCuenta> actualizarCuentaPorAcuerdo(String idAcuerdo) throws PersistenciaExcepcion;	
	
	List<ShvParamBancoCuenta> listarCuentaNoConciliables(List<ShvParamAcuerdo> listaAcuerdo) throws PersistenciaExcepcion;
	
	List<ShvParamBanco> actualizarBancoPorIdSegmento(String idEmpresa, String idSegmento) throws PersistenciaExcepcion;
}
