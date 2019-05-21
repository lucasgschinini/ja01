package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoGestion;

public interface IAcuerdoDao {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvParamAcuerdo buscarAcuerdo(String id) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idCuenta
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvParamAcuerdo buscarAcuerdoConciliablePorIdCuenta(String idCuenta) throws PersistenciaExcepcion;
		
	/**
	 * 
	 * @param idCuenta
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvParamAcuerdo buscarAcuerdoPorIdCuenta(String idCuenta) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idCuenta
	 * @param esConcialiable
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvParamAcuerdo buscarAcuerdoNoConciliablesYconciliablesPorIdCuenta(String idCuenta, String esConcialiable) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idBanco
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvParamAcuerdo> listarAcuerdoPorIdBanco(String idBanco) throws PersistenciaExcepcion;	
	
	/**
	 * 
	 * @param idBanco
	 * @param esConcialiable
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvParamAcuerdo> listarAcuerdoNoConciliableYconciliablePorIdBanco(String idBanco, String esConcialiable) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param idOrigen
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvParamAcuerdo> actualizarAcuerdoPorIdOrigen(String idEmpresa, String idSegmento, String idOrigen)  throws PersistenciaExcepcion;	
	
	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param idOrigen
	 * @param tipoValor
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvParamAcuerdo> actualizarAcuerdoPorIdOrigenValor(String idEmpresa, String idSegmento, String idOrigen, String tipoValor) throws PersistenciaExcepcion;
		
	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvParamAcuerdo> actualizarAcuerdoPorIdSegmento(String idEmpresa,String idSegmento) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param tipoValor
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvParamAcuerdo> listarAcuerdoNoConciliables(String idEmpresa,String idSegmento, String tipoValor) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idEmpresa
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvParamAcuerdo> listarAcuerdo(String idEmpresa) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvParamAcuerdo> listarAcuerdo(String idEmpresa, String idSegmento) throws PersistenciaExcepcion;
	
	
	public List<ShvParamTipoGestion> listarParamTipoGestion(String idEmpresa, Integer idOrigen, List<TipoValorEnum> listaValores, SiNoEnum consiliable) throws PersistenciaExcepcion;


	public List<Map<String, Object>> listarParamTipoGestionDb(String idEmpresa, Integer idOrigen, List<TipoValorEnum> listaValores, SiNoEnum consiliable) throws PersistenciaExcepcion;
		
}
