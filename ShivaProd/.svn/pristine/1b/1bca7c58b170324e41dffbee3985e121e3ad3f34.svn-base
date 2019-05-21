package ar.com.telecom.shiva.persistencia.dao;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.bean.ControlDeHilosCobros;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCRespuestaDto;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaCobrosPendientes;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosImputacion;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosImputacionSap;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosSimulacion;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDocumentoScard;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaEstadoGrupos;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCTA;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobOperacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccionMensajeriaDetalle;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobCobroSimple;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobCobroSimpleSinWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobTransaccionSimple;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoCTASinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;

public interface ICobroDao {

	/**
	 * 
	 * @param cobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobCobro insertarCobro(ShvCobCobro cobro) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobCobro buscarCobro(Long id) throws PersistenciaExcepcion;

	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
//	List<ShvCobProcHilosCobros> listarCobrosPendientes() throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ResultadoBusquedaCobrosPendientes> listarCobrosImputacionManualPendientes(TipoImputacionEnum tipoImputacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<Long> listarCobrosPendientesProcesarMIC() throws PersistenciaExcepcion;
	
	List<Long> listarCobrosImpManualPendientesProcesarMIC() throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param cobroModelo
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobCobro modificar(ShvCobCobro cobroModelo) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param cobroModelo
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobCobroSimple modificar(ShvCobCobroSimple cobroModelo) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param cobroModelo
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobCobroSimpleSinWorkflow modificar(ShvCobCobroSimpleSinWorkflow cobroModelo) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param id
	 * @throws PersistenciaExcepcion
	 */
	void eliminar(Long id) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idTransaccion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobCobro buscarCobroPorIdTransaccion(Integer idTransaccion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobCobro buscarCobroPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobCobroSimple buscarCobroSimplePorIdOperacion(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idCobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobCobroSimple buscarCobroSimplePorIdCobro(Long idCobro) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */

	ShvCobCobroSimpleSinWorkflow buscarCobroSimpleSinWorkflowPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvCobCobro> listarCobrosErrorConfirmacion(Long idOperacion) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idOperacionTransaccion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobCobro buscarCobroPorIdOperacionYNroTransaccionParaReversion(String idOperacionTransaccion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param listaIdRetencion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvCobFactura> buscarFacturaParaReporteRetencion(List<String> listaIdRetencion) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param fechaFin
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<Map<String, Object>> buscarCobrosParaSubdiario(GregorianCalendar fechaFin) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ResultadoBusquedaDatosImputacion> buscarDatosImputacion(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ResultadoBusquedaDatosSimulacion> buscarDatosSimulacion(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvCobCobro> buscarCobrosSimulacionEnProceso() throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idValor
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<Long> listarOperacionesDeCobroEnProcesoPorIdValor(Long idValor) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvCobTransaccionMensajeriaDetalle> buscarConfirmacionFallida(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobOperacion buscarOperacionPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobTransaccion buscarTransaccionPorIdTransaccion(Integer idTransaccion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	boolean mensajeAnteriormenteProcesado(MicTransaccionADCRespuestaDto mensajeRespuesta, MensajeServicioEnum servicio) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobTransaccion guardarTransaccion(ShvCobTransaccion transaccion) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobTransaccionSinOperacion buscarTransaccionAProcesarPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvCobTransaccionSinOperacion> buscarTransaccionPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion;
	
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
	ShvCobTransaccionSinOperacion guardarTransaccionSinOperacion(ShvCobTransaccionSinOperacion transaccion) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobTransaccionSinOperacion buscarTransaccionSinOperacionPorIdTransaccion(Integer idTransaccion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param medioPago
	 * @throws PersistenciaExcepcion
	 */
	void guardarMedioPago(ShvCobMedioPago medioPago) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @param numeroTransaccion
	 * @param medPagoCta
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvCobMedioPagoCTA> buscarMedioPagoCtaIgualACta(Long idOperacion,	Integer numeroTransaccion, ShvCobMedioPagoCTASinOperacion medPagoCta) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	Integer obtenerEstadoDelHilo(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ResultadoBusquedaDatosImputacionSap> buscarDatosImputacionSap(Long idOperacion,boolean hayMpProveedor) throws PersistenciaExcepcion;
	
	/**
	 * 
	 */
	Long obtenerOrdenCap(String idProveedor) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idCobro
	 * @param idClienteLegado
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	String obtenerCuit(Long idCobro, Long idClienteLegado) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idDocumento
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ResultadoBusquedaDocumentoScard obtenerDocumentoScardCobroPorIdDocumento(Long idDocumento) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idDocumento
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ResultadoBusquedaDocumentoScard obtenerDocumentoScardDescobroPorIdDocumento(Long idDocumento) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idValor
	 * @return BigDecimal
	 * @throws PersistenciaExcepcion
	 */
	BigDecimal importeDeAplicacionesPendientesYCobrosEnProcesos(Long idValor) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	String buscarUsuarioConfirmacionManual(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvCobTransaccionSinOperacion> buscarTransaccionesPorIdOperacionParaRechazoConfirmAplicacionManual(Long idOperacion) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param listaIdClienteLegado
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	String buscarCuentaContablePorDefault(List<String> listaIdClienteLegado) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobTransaccionSinOperacion buscarTransaccionAProcesarPorIdOperacionImpManual(Long idOperacion) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param idOperacion
	 * @param grupo
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvCobTransaccionSinOperacion> buscarTransaccionSinOperacionPorIdOperacionYGrupo(Long idOperacion, Long grupo) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param idOperacion
	 * @param numeroTransaccion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobTransaccionSinOperacion buscarTransaccionSinOperacionPorIdOperacionYNumeroTransaccion(Long idOperacion, Integer numeroTransaccion) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<EstadoTransaccionEnum> listarEstadosTransaccionesAplicacionManualPendientesProcesar(Long idOperacion) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param idOperacion
	 * @param numeroTransaccion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobTransaccionSimple buscarTransaccionSimplePorIdOperacionYNumeroTransaccion(Long idOperacion, Integer numeroTransaccion) throws PersistenciaExcepcion;
	/**
	 *
	 * @param idOperacion
	 * @param sistema
	 * @param sociedad
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvCobTransaccionSinOperacion> buscarTransaccionSinOperacionPorIdOperacionYSistemaYSociedad(Long idOperacion, SistemaEnum sistema, SociedadEnum sociedad) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param idOperacion
	 * @param sistema
	 * @param sociedad
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvCobTransaccionSinOperacion> buscarTransaccionSinOperacionPorIdOperacionYSistemaDependenciaYSociedadDependencia(Long idOperacion, SistemaEnum sistema, SociedadEnum sociedad) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param idOperacion
	 * @param sociedad
	 * @param sistema
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ResultadoBusquedaDatosImputacion> buscarDatosImputacion(Long idOperacion, SociedadEnum sociedad, SistemaEnum sistema) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param transaccion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobTransaccionSinOperacion guardarTransaccionSinOperacionNoTransaccional(ShvCobTransaccionSinOperacion transaccion) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @param grupoActual
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ResultadoBusquedaEstadoGrupos> buscarEstadoGruposConAplicacionManual(Long idOperacion, Long grupoActual) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @param estado
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvCobTransaccionSinOperacion> buscarTransaccionPorIdOperacionYEstado(Long idOperacion, EstadoTransaccionEnum pendiente) throws PersistenciaExcepcion;
	
	/**
	 * 
	 */
	public ShvCobTransaccionSinOperacion buscarTransaccionAProcesarPorErrorDesapropiacionPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param tiempoLimite
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ControlDeHilosCobros> buscarHilosEnProceso(Integer tiempoLimite) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param listaEstadosEnum
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ControlDeHilosCobros> buscarCobrosEnProceso(List<Estado> listaEstadosEnum) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param tipoImputacionEnum
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public Integer hayHilosCobrosVivos(TipoImputacionEnum tipoImputacionEnum) throws PersistenciaExcepcion;
}

