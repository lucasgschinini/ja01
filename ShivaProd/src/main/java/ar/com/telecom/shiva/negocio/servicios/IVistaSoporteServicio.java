/**
 * 
 */
package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;
import java.util.Map;

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

/**
 * @author pablo.m.ibarrola
 *
 */
public interface IVistaSoporteServicio {

	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaValorDisponible> consultarValoresDisponibles(
				VistaSoporteValoresDisponiblesFiltro filtro) throws NegocioExcepcion;

	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaTareaPendiente> consultarTareasPendientes(
			VistaSoporteTareasPendientesFiltro filtro) throws NegocioExcepcion;

	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws <code>NegocioExcepcion</code>
	 */
	public List<VistaSoporteResultadoBusquedaValor> buscarValores(
			VistaSoporteBusquedaValoresFiltro filtro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws <code>NegocioExcepcion</code>
	 */
	public List<VistaSoporteResultadoBusquedaTalonarios> buscarTalonarios(
			TalonarioFiltro filtro) throws NegocioExcepcion;

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteRegistrosAvcSinConciliar> listarRegistrosAVCSinConciliar(VistaSoporteRegistrosAvcSinConciliarFiltro filtro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @return (boleta, valorVista)
	 * @throws NegocioExcepcion
	 */
	public Map<Long, VistaSoporteResultadoBusquedaValor> getListaValoresPorIdsBoletas(VistaSoporteBusquedaValoresFiltro filtro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteCobrosOnline> listarCobrosOnline(VistaSoporteCobroOnlineFiltro cobroFiltro) throws NegocioExcepcion; // busqueda Cobros
	
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteOperacionesMasivas> listarOperacionesMasivas(VistaSoporteOperacionMasivaFiltro operacionMasivaFiltro) throws NegocioExcepcion; 
	
	
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaCobroHistorial> obtenerHistorialCobro (VistaSoporteBusquedaCobroHistorialFiltro filtro) throws NegocioExcepcion;

	/**
	 * @author u573005 fabio.giaquinta
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaCobroTransaccion> obtenerTransaccionesCobro(VistaSoporteCobroTransaccionFiltro filtro) throws NegocioExcepcion;
	
	
	/**
	 * 
	 * @param idDebitoReferencia
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoCobroCreditoDebito> obtenerDebitosPorReferencia(String idDebitoReferencia, String idCobro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idCreditoReferencia
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoCobroCreditoDebito> obtenerCreditosPorReferencia(String idCreditoReferencia, String idCobro) throws NegocioExcepcion;
	/**
	 * 
	 * @param idCapReferencia
	 * @param idCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoCobroCreditoDebito> obtenerDocumentoCapPorReferencia(String idCapReferencia, String idCobro) throws NegocioExcepcion;
	/**
	 * 
	 * @param idCreditoReferencia
	 * @param estados
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean obtenerMarcaCreditoEnCobrosPendienteProceso(String idCreditoReferencia) throws NegocioExcepcion;
	/**
	 * 
	 * @param idDebitoReferencia
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean obtenerMarcaDebitoEnCobrosPendienteProceso(String idDebitoReferencia) throws NegocioExcepcion;
	/**
	 * 
	 * @param descobroFiltro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaDescobro> listarDescobros (VistaSoporteDescobroFiltro descobroFiltro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaDescobroHistorial> obtenerHistorialDescobro (VistaSoporteBusquedaDescobroHistorialFiltro filtro) throws NegocioExcepcion;

	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaDescobroTransaccion> obtenerTransaccionesDescobro (VistaSoporteDescobroTransaccionFiltro filtro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas> obtenerOperacionesRelacionadasDescobro(VistaSoporteDescobroOperacionRelacionadaFiltro filtro)throws NegocioExcepcion;

	/**
	 * 
	 * @param idDebitoReferencia
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public boolean obtenerMarca(Object idCreditoReferencia, QueryMarcaEnum queryMarca) throws NegocioExcepcion;
	

	/**
	 * 
	 * @param idOperacioMasiva
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaOperacionMasivaHistorial> obtenerHistorialOperacionMasiva(String idOperacioMasiva) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idCapReferencia
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean obtenerMarcaCapEnCobrosPendienteProceso(String idCapReferencia) throws NegocioExcepcion;
	
	/**
	 * Retorno los resultados producto de la simulación (verificación) de documentos CAP contra SAP
	 *  
	 * @param idCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<String> consultarResultadoVerificacionDocumentosCap(Long idCobro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteConsultaDeudaPdfCap> obtenerRegistrosDeudaPdf(Long idOperacion) throws NegocioExcepcion;
	
	/**
	 * 
	 */
	public List<VistaSoporteConsultaCapPdfCap> obtenerRegistrosCapPdf(Long idOperacion) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ShvSopExcepcionMorosidad> obtenerRegistrosExcepcionMorosidad(ExcepcionMorosidadFiltro filtro) throws NegocioExcepcion;
	
	
	
	
	
	public List<Bean> listarLegajosChequeRechazado(VistaSoporteLegajoChequeRechazadoFiltro legajoFiltro) throws NegocioExcepcion;
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listarCobrosRelacionados(VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro filtro) throws NegocioExcepcion;

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
	 * @param idLegajo
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaLegajoDetalleDocumento> listarChequeRechazadoDetalleDocumento(Long idLegajo) throws NegocioExcepcion;

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteMotorConciliacion> listarRegistrosMotorConciliacionPorReglaMenor() throws NegocioExcepcion;

}
