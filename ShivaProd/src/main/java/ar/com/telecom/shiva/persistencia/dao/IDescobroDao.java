package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;
import java.util.Set;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosImputacion;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosSimulacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroDocumentoRelacionado;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroOperacionRelacionada;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDescobroSimpleSinWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDescobroSimplificadoCodigoExternoOperacion;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public interface IDescobroDao {

	/**
	 * 
	 * @param descobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobDescobro insertarDescobro(ShvCobDescobro descobro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobDescobro buscarDescobro(Long id) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param descobroModelo
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobDescobro modificar(ShvCobDescobro descobroModelo) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idTransaccion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobDescobro buscarDescobroPorIdTransaccion(Integer idTransaccion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobDescobro buscarDescobroPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param subdiarioFiltro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<Object[]> buscarCobrosParaSubdiario(Filtro subdiarioFiltro) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param cobroAdjunto
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobDescobroAdjunto insertarDocumentoAdjunto(ShvCobDescobroAdjunto cobroAdjunto) throws PersistenciaExcepcion;

	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvCobDescobro> buscarDescobrosSimulacionEnProceso() throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idDescobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvDocDocumentoAdjunto> buscarAdjuntosDelDescobroOnline(Long idDescobro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idAdjunto
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvDocDocumentoAdjunto> buscarPorIdAdjuntoDescobrosOnline(Long idAdjunto) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param documentoAdjunto
	 * @throws PersistenciaExcepcion
	 */
	public void eliminarDocumentoAdjuntoDelDescobro(ShvDocDocumentoAdjunto documentoAdjunto) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacionCobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvCobDescobroOperacionRelacionada> buscarOperacionRelacionadaDescobroPorIdOperacionCobro(Long idOperacionCobro) throws PersistenciaExcepcion;
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * Guarda las operaciones relacionadas de descobros
	 * @param registro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobDescobroOperacionRelacionada insertarOperacionRelacionada(ShvCobDescobroOperacionRelacionada registro) throws PersistenciaExcepcion;
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * Elimina las operaciones relacionadas de descobros
	 * @param registrosABorrar
	 * @throws PersistenciaExcepcion
	 */
	public void borrarOperacionesRelacionadas(Set<ShvCobDescobroOperacionRelacionada> registrosABorrar) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<Long> listarDescobrosPendientes() throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<Long> listarDescobrosPendientesProcesarMIC() throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ResultadoBusquedaDatosSimulacion> buscarDatosSimulacion(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ResultadoBusquedaDatosImputacion> buscarDatosImputacion(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param registrosABorrar
	 * @throws PersistenciaExcepcion
	 */
	public void borrarDescobro(ShvCobDescobro registrosABorrar) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idCobro
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobDescobro buscarDescobroPorIdCobro(Long idCobro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public Long buscarIdDescobroPorIdOperacionCobro(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idDescobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvCobDescobroDocumentoRelacionado> buscarDocumentosRelacionadosDescobro(Long idDescobro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvWfWorkflow buscarWorkflowPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobDescobroSimplificadoCodigoExternoOperacion buscarDescobroSimplificadoCodigoExternoOperacion(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobDescobroSimpleSinWorkflow buscarDescobroSimpleSinWorkflowPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion;	
}
