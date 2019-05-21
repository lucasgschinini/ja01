package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjChequeRechazadoDetalleCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjEnvioReversionesIce;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjLegajoChequeRechazado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjChequeRechazadoDetalleCobroSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjChequeRechazadoDetalleDocumentoSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjLegajoChequeRechazadoSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjLegajoChequeRechazadoSimplificadoWorkFlow;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoFiltro;

public interface ILegajoChequeRechazadoDao {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvLgjLegajoChequeRechazado buscar(Object id) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvLgjLegajoChequeRechazado> listar(VistaSoporteLegajoChequeRechazadoFiltro filtro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param legajo
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvLgjLegajoChequeRechazado crear(ShvLgjLegajoChequeRechazado legajo) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param legajoChequeRechazado
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public Long validarUnicidadDatosModificadosNoExisteEnOtroLegajo(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param valor
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public Long validarUnicidadChequeAsociadoShivaNoExistaEnOtroLegajo(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param legajoChequeRechazado
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public Long validarUnicidadChequeAsociadoIceNoExistaEnOtroLegajo(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws PersistenciaExcepcion;
	
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<Bean> listarBusqueda(Filtro filtro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param legajoChequeRechazado
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvLgjLegajoChequeRechazado modificar(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param legajoChequeRechazado
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvLgjLegajoChequeRechazadoSimplificado modificar(ShvLgjLegajoChequeRechazadoSimplificado legajoChequeRechazado) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param detCobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvLgjChequeRechazadoDetalleCobroSimplificado modificarCobrosRelacionados(ShvLgjChequeRechazadoDetalleCobroSimplificado detCobro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idLegajo
	 * @param listaIdDetCobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvLgjChequeRechazadoDetalleCobroSimplificado> listarDetCobroSimplificado(List<Long> listaIdDetCobro) throws PersistenciaExcepcion;

	public void actualizarRegistrosDetalleCobroPorEnvioIce(List<ShvLgjChequeRechazadoDetalleCobro> listaShvMasRegistro) throws PersistenciaExcepcion;

	public List<ShvLgjChequeRechazadoDetalleCobro> listarDetalleCobrosPendientesDeEnviarIceOrdenadoPorBanco() throws PersistenciaExcepcion;
	
	public List<ShvLgjChequeRechazadoDetalleCobro> listarDetalleCobrosEnviadoAIcePorIdChequeRechazado(Long idChequeRechazado) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param legajoAdjunto
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvLgjAdjunto insertarDocumentoAdjunto(ShvLgjAdjunto legajoAdjunto) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param docAdjunto
	 * @throws PersistenciaExcepcion
	 */
	public void eliminarDocumentoAdjuntoDelLegajo(ShvDocDocumentoAdjunto docAdjunto) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idLegajo
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvDocDocumentoAdjunto> buscarAdjuntosDelLegajo(Long idLegajo) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param reversionIce
	 * @return 
	 * @throws PersistenciaExcepcion
	 */
	public ShvLgjEnvioReversionesIce grabarShvLgjEnvioReversionesIce(ShvLgjEnvioReversionesIce reversionIce) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvLgjChequeRechazadoDetalleDocumentoSimplificado> listarDetalleDocumentoSimplificados() throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param detalleDocumento
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvLgjChequeRechazadoDetalleDocumentoSimplificado actualizarChequeRechazadoDetalleDocumentoSimplificado(ShvLgjChequeRechazadoDetalleDocumentoSimplificado detalleDocumento)	throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvLgjLegajoChequeRechazadoSimplificadoWorkFlow buscarSimplificadoConWorkFlow(Object id) throws PersistenciaExcepcion;
}
