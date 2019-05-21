package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcValor;

public interface IRegistroAVCDao {

	/**
	 * 
	 * @param registroAVC
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvAvcRegistroAvc crear(ShvAvcRegistroAvc registroAVC) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvAvcRegistroAvc> listarRegistrosDepositoPendientesConciliar() throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvAvcRegistroAvc> listarRegistrosInterdepositoYTransferenciaPendientesConciliar() throws PersistenciaExcepcion;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvAvcRegistroAvc buscarRegistroAVC(String id) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param registroModelo
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvAvcRegistroAvc modificar(ShvAvcRegistroAvc registroModelo) throws PersistenciaExcepcion;

	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvAvcRegistroAvc> listarRegistrosAVCSinConciliar() throws PersistenciaExcepcion;

	/**
	 * 
	 * @param registroAdjunto
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvAvcRegistroAdjunto insertarDocumentoAjunto(ShvAvcRegistroAdjunto registroAdjunto) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idRegistro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvAvcRegistroAdjunto> buscarDocumentosAdjuntosPorIdTransferencia(String idRegistro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param registroAVCValor
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvAvcRegistroAvcValor crearShvAvcRegistroAvcValor(ShvAvcRegistroAvcValor registroAVCValor) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idValor
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvAvcRegistroAvcValor buscarRegistroAvcValorPorIdValor(String idValor) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idRegistroAvc
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvAvcRegistroAvcValor buscarRegistroAvcValorPorIdRegistroAvc(String idRegistroAvc) throws PersistenciaExcepcion;

}
