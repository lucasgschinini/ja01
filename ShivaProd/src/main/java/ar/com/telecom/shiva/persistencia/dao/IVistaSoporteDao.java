package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.QueryMarcaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteCobrosOnline;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteConsultaCapPdfCap;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteConsultaDeudaPdfCap;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteMotorConciliacion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteOperacionesMasivas;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteRegistrosAvcSinConciliar;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaCobroHistorial;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaCobroTransaccion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobro;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroHistorial;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroTransaccion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoCobrosRelacionado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoDetalleDocumento;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaOperacionMasivaHistorial;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaReciboPreimpreso;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaTalonarios;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaTareaPendiente;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValorDisponible;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoCobroCreditoDebito;
import ar.com.telecom.shiva.persistencia.modelo.ShvSopExcepcionMorosidad;
import ar.com.telecom.shiva.presentacion.bean.filtro.ExcepcionMorosidadFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.TalonarioFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaCobroHistorialFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaDescobroHistorialFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaReciboPreImpresoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteCobroOnlineFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteCobroTransaccionFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroOperacionRelacionadaFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroTransaccionFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteOperacionMasivaFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteRegistrosAvcSinConciliarFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteTareasPendientesFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteValoresDisponiblesFiltro;

public interface IVistaSoporteDao {
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaValorDisponible> consultarValoresDisponibles(
			VistaSoporteValoresDisponiblesFiltro filtro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaTareaPendiente> consultarTareasPendientes(
			VistaSoporteTareasPendientesFiltro filtro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaValor> buscarValores(
			VistaSoporteBusquedaValoresFiltro filtro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaTalonarios> buscarTalonarios(
			TalonarioFiltro filtro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteRegistrosAvcSinConciliar> listarRegistrosAVCSinConciliar(VistaSoporteRegistrosAvcSinConciliarFiltro filtro)
			throws PersistenciaExcepcion;

	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteCobrosOnline> listarCobrosOnline(VistaSoporteCobroOnlineFiltro cobroFiltro)
			throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteOperacionesMasivas> listarOperacionesMasivas(VistaSoporteOperacionMasivaFiltro operacionMasivaFiltro)
			throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaCobroHistorial> obtenerHistorialCobro (VistaSoporteBusquedaCobroHistorialFiltro filtro) 
			throws PersistenciaExcepcion;
	
	/**
	 * Dado un filtro seteando un id de cobro o id de operacion, se retorna una lista de transacciones que conforman el cobro
	 * en cuestión.
	 * 
	 * @param cobroTransaccionFiltro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaCobroTransaccion> 
		obtenerTransaccionesCobro (VistaSoporteCobroTransaccionFiltro cobroTransaccionFiltro) throws PersistenciaExcepcion;
	
	
	/**
	 * 
	 * @param idDebitoReferencia
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoCobroCreditoDebito> obtenerDebitosPorReferencia(String idDebitoReferencia, String idCobro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idCreditoReferencia
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoCobroCreditoDebito> obtenerCreditosPorReferencia(String idCreditoReferencia, String idCobro) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param idCreditoReferencia
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public boolean obtenerMarcaCreditoEnCobrosPendienteProceso(String idCreditoReferencia) throws PersistenciaExcepcion;
	/***
	 * 
	 * @param idDebitoReferencia
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public boolean obtenerMarcaDebitoEnCobrosPendienteProceso(String idDebitoReferencia) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param descobroFiltro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaDescobro> listarDescobros(VistaSoporteDescobroFiltro descobroFiltro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaDescobroHistorial> obtenerHistorialDescobro(VistaSoporteBusquedaDescobroHistorialFiltro filtro) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaDescobroTransaccion> obtenerTransaccionesDescobro(VistaSoporteDescobroTransaccionFiltro descobroTransaccionFiltro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas> obtenerOperacionesRelacionadasDescobro(VistaSoporteDescobroOperacionRelacionadaFiltro filtro)throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idDebitoReferencia
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public boolean obtenerMarca(Object valor, QueryMarcaEnum queryMarca) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idOperacioMasiva
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaOperacionMasivaHistorial> obtenerHistorialOperacionMasiva(String idOperacioMasiva) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param idCreditoReferencia
	 * @param idCobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteResultadoCobroCreditoDebito> obtenerDocumentoCapPorReferencia(String idCreditoReferencia, String idCobro) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param idCapReferencia
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public boolean obtenerMarcaCapEnCobrosPendienteProceso(String idCapReferencia) throws PersistenciaExcepcion;
	
	/**
	 * Retorno los resultados producto de la simulación (verificación) de documentos CAP contra SAP
	 * 
	 * @param idCobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<String> consultarResultadoVerificacionDocumentosCap(Long idCobro) throws PersistenciaExcepcion;
	/**
	 * obtengo los registros que forman el anexo de la carta de sap
	 * @param idCapReferencia
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteConsultaDeudaPdfCap> obtenerRegistrosDeudaPdf(Long idOperacion) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param idCobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteConsultaCapPdfCap> obtenerRegistrosCapPdf(Long idCobro) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvSopExcepcionMorosidad> obtenerRegistrosExcepcionMorosidad(ExcepcionMorosidadFiltro filtro) throws PersistenciaExcepcion;
	
	
	
	
	public List<Bean> listarLegajosChequeRechazado (VistaSoporteLegajoChequeRechazadoFiltro legajoFiltro) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listarCobrosRelacionados(VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro filtro) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado obtenerLegajoChequeRechazadoCobrosRelacionadosEstadoReversionShivaBy(Long idOperacion) throws NegocioExcepcion;

	/**
	 * 
	 * @param idChequeRechazado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaLegajoDetalleDocumento> obtenerChequeRechazadoDetalleDocumentoBy(Long idLegajo) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaLegajoDetalleDocumento> listarChequeRechazadoDetalleDocumento(Long idLegajoChequeRechazado)throws PersistenciaExcepcion;

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteMotorConciliacion> listarRegistrosMotorConciliacion() throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteMotorConciliacion> listarRegistrosMotorConciliacionPorReglaMenor() throws PersistenciaExcepcion;
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaReciboPreimpreso> buscarRecibos(VistaSoporteBusquedaReciboPreImpresoFiltro filtro) throws PersistenciaExcepcion;
}