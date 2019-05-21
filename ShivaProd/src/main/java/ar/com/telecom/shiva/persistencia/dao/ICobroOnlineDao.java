package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobroAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCobroSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCobroSimplificadoCodigoExternoOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCobroSimplificadoConWorkflow;

public interface ICobroOnlineDao {
	
	ShvCobEdCobro guardarCobro(ShvCobEdCobro cobro) throws PersistenciaExcepcion;
	
	ShvCobEdCobro buscarCobro(Long id) throws PersistenciaExcepcion;
	
	ShvCobEdCobro buscarCobroPorIdOperacion(Long id) throws PersistenciaExcepcion;

	ShvCobEdCobroAdjunto insertarDocumentoAjunto(ShvCobEdCobroAdjunto registroAdjunto) throws PersistenciaExcepcion;
	
	List<ShvCobEdCobroAdjunto> buscarAdjuntosDelCobroOnline(Long idCobroOnline) throws PersistenciaExcepcion;
	
	void eliminarDocumentoAdjuntoDelCobro(ShvDocDocumentoAdjunto documentoAdjunto) throws PersistenciaExcepcion;
	
	/**
	 * Retorna la lista de clientes asociados a un cobro
	 * @param idCobroOnline
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvCobEdCliente> listarClientesDelCobroOnline(Long idCobroOnline) throws PersistenciaExcepcion;	
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobEdCobroSimplificado buscarCobroSimplificado(Long id) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobEdCobroSimplificado buscarCobroSimplificadoPorIdCobro(Long idCobro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobEdCobroSimplificadoConWorkflow buscarCobroSimplificadoConWorkflow(Long id) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param cobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobEdCobroSimplificado guardarCobroSimplificado(ShvCobEdCobroSimplificado cobro) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param cobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobEdCobroSimplificadoConWorkflow guardarCobroSimplificadoConWorkflow(ShvCobEdCobroSimplificadoConWorkflow cobro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobEdCobroSimplificadoCodigoExternoOperacion buscarCobroSimplificadoCodigoExternoOperacion(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idAdjunto
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvDocDocumentoAdjunto> buscarPorIdAdjuntoCobrosOnline(Long idAdjunto) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @param sociedad
	 * @param sistema
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobEdCobroAdjunto buscarAdjuntoPorGrupo(Long idOperacion, SociedadEnum sociedad, SistemaEnum sistema) throws PersistenciaExcepcion;
}
