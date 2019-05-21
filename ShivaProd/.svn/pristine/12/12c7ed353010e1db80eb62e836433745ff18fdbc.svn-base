package ar.com.telecom.shiva.negocio.servicios;

import java.math.BigDecimal;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ReintentoExcepcion;
import ar.com.telecom.shiva.base.mail.Adjunto;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCredito;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;


public interface ICobroBatchSoporteImputacionServicio {
	
	public boolean puedeEnviarMensaje(Long idOperacion, Integer idTransaccion, Integer numeroTransaccion, 
			SistemaEnum cobrador, TipoInvocacionEnum tipoInvocacion) throws NegocioExcepcion, ReintentoExcepcion;
	
	public boolean apropiacionDeimos(ShvCobTransaccionSinOperacion shvCobTransaccion) throws NegocioExcepcion;
	
	public MensajeServicioEnum getMensajeServicio(SistemaEnum cobrador, TipoInvocacionEnum tipoInvocacion) throws NegocioExcepcion;
	
	public ShvCobTransaccionSinOperacion generarCargosOReintegro(ShvCobTransaccionSinOperacion transaccion, ShvWfWorkflow workflow) throws NegocioExcepcion, ReintentoExcepcion;

	public void setearEstadoMedioPago(ShvCobMedioPagoSinOperacion medioPago, DetalleCTAoNotaCredito detalleCTAoNC, Long idOperacion);
	
	public ShvCobCobro eliminarTransaccionesEnEstadoDiferenciaDeCambioSim(ShvCobCobro cobro) throws NegocioExcepcion;
	
	public ShvCobMedioPago clonarListaDeClientesMedioPagoUsuario(ShvCobMedioPago medioPagoOrigen,ShvCobMedioPago medioPagoDestino) throws Exception;
	
	public List<ShvCobMedioPagoSinOperacion> listarMediosPago(ShvCobTransaccionSinOperacion transaccion, SistemaEnum cobrador);

	public void apropiacionShiva(List<ShvCobMedioPagoSinOperacion> listaMediosPagoShiva, ShvCobTransaccionSinOperacion transaccion, String usuario) throws NegocioExcepcion, ReintentoExcepcion;
	
	public void actualizarEstadoTransaccion(String idTransaccion, ShvCobCobro cobro, EstadoTransaccionEnum estado) throws PersistenciaExcepcion;
	
	public List<ShvCobMedioPagoSinOperacion> listarMediosPagoProxFactura(ShvCobTransaccionSinOperacion transaccion, SistemaEnum cobrador);
	
	public List<ShvCobMedioPago> listarMediosPagoProxFactura(ShvCobTransaccion transaccion, SistemaEnum cobrador);
			
	public List<ShvCobMedioPago> listarMediosPago(ShvCobTransaccion transaccion, SistemaEnum cobrador);
	
	public void actualizarEstadoFacturaYMP(ShvCobCobro cobro, String idTransaccion, EstadoFacturaMedioPagoEnum estado, SistemaEnum cobrador, String descripcionError) throws NegocioExcepcion;

	public EstadoFacturaMedioPagoEnum getEstadoMedioPagoCalipso(ShvCobTransaccionSinOperacion transaccion);

	public EstadoFacturaMedioPagoEnum getEstadoMedioPagoShiva(ShvCobTransaccionSinOperacion transaccion);

	public EstadoFacturaMedioPagoEnum getEstadoMedioPagoMIC(ShvCobTransaccionSinOperacion transaccion);

	public boolean existenMediosPagoCalipso(ShvCobTransaccionSinOperacion transaccion);

	public boolean existenMediosPagoMic(ShvCobTransaccionSinOperacion transaccion);

	public void verificarEstadoCobro(ShvCobCobro cobro, String usuario, boolean reenvioConfirmacion) throws NegocioExcepcion, ReintentoExcepcion;

	public void tracearDatosImputacionCobro(Long idCobro, ShvWfWorkflow workflow, List<ShvCobTransaccion> transaccionesOrdenadas, boolean esInicio, int contadorCobro);

	public boolean verificarErrorEnApropiacion(List<ShvCobTransaccion> listaTransaccionesOrdenada);

	public void realizarReversionMedioPagoValor(ShvCobCobro cobro)	throws NegocioExcepcion;

	public boolean existenCargosProximaFactura(ShvCobCobro cobro, ShvCobTransaccionSinOperacion transaccion);

	public boolean existenMediosPagoCalipso(ShvCobTransaccion transaccion);

	public EstadoFacturaMedioPagoEnum getEstadoMedioPagoCalipso(ShvCobTransaccion transaccion);

	public void aplicarCambiosEstadosSegunRespuestasDesapropiacion(String estadoRespuestaCalipso, String estadoRespuestaMic, ShvCobCobro cobro) throws NegocioExcepcion;

	public void aplicarCambiosEstadosSegunRespuestasConfirmacion(String estadoRespuestaCalipso, String estadoRespuestaMic,	ShvCobCobro cobro) throws NegocioExcepcion;

	public Boolean todasTransaccionesConfirmadas(ShvCobCobro cobro);

	public boolean existenMediosPagoMic(ShvCobTransaccion transaccion);

	public void tracearDatosProcesamientoTransaccion(Long idOperacion,	ShvCobTransaccionSinOperacion transaccion, boolean esInicio);

	public void actualizarEstadoFacturaYMPSinCobro(ShvCobTransaccionSinOperacion transaccion, EstadoFacturaMedioPagoEnum estado, SistemaEnum cobrador, String descripcionError) throws NegocioExcepcion;

	public Boolean contieneFacturaOMedioPagoMicEnProcesoDesapropiacion(ShvCobTransaccion transaccion);

	public void informarAContabilidadScard(ShvCobCobro cobro) throws NegocioExcepcion;

	/***
	 * 
	 * @param idOperacion
	 * @param estado
	 * @param usuario
	 * @throws NegocioExcepcion
	 */
	public void avanzarEstadoCobroImputacionManual(Long idOperacion, Estado estado, String usuario) throws NegocioExcepcion;
	
	public boolean tieneTransaccionesConAplicacionManualPendienteDeProcesar(Long idOperacion) throws NegocioExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @param estado
	 * @param usuario
	 * @throws NegocioExcepcion
	 */
	public void cambiarEstadoCobroImputacionManual(Long idOperacion, Estado estado, String usuario) throws NegocioExcepcion;

	/**
	 * 
	 * @param listaTransaccionesPorGrupo
	 * @param estado
	 * @throws NegocioExcepcion
	 */
	public void avanzarTransacciones(List<ShvCobTransaccionSinOperacion> listaTransaccionesPorGrupo, EstadoTransaccionEnum estado) throws NegocioExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @param grupo
	 * @param usuarioBatchCobroImputacion
	 * @param tareaRechazada
	 * @throws NegocioExcepcion
	 */
	public void desapropiarTransaccionesPorGrupo(Long idOperacion, Long idCobro, Long grupo, String usuarioBatchCobroImputacion, boolean tareaRechazada) throws NegocioExcepcion;

	/**
	 * 
	 * @param listaTransaccionesADesapropiar
	 * @param idOperacion
	 * @throws NegocioExcepcion
	 */
	public void realizarReversionParcialMedioPagoValor(List<ShvCobTransaccionSinOperacion> listaTransaccionesADesapropiar, Long idOperacion) throws NegocioExcepcion;

	/**
	 * 
	 * @param cobro
	 * @param estadoCobro
	 * @throws NegocioExcepcion
	 */
	public void evaluarEnvioMailYGenerarTarea(ShvCobCobro cobro, Estado estadoCobro) throws NegocioExcepcion;

	/**
	 * 
	 * @param cobro
	 * @param estado
	 * @throws NegocioExcepcion
	 */
	public void realizarReversionMedioPagoValor(ShvCobCobro cobro, Estado estado) throws NegocioExcepcion;

	/**
	 * 
	 * @param cobro
	 * @param estadoCobro
	 * @throws NegocioExcepcion
	 */
	public void enviarMailyGenerarTarea(ShvCobCobro cobro, Estado estadoCobro) throws NegocioExcepcion;

	/**
	 * 
	 * @param analista
	 * @param conCopia
	 * @param asunto
	 * @param cuerpo
	 * @param idCobro
	 * @param listaAdjuntos
	 * @param idOperacion
	 * @throws NegocioExcepcion
	 */
	public void enviarMail(String analista, String conCopia, String asunto, String cuerpo, Long idCobro, List<Adjunto> listaAdjuntos, Long idOperacion,Estado estadoCobro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @param grupo
	 * @param estadoDelGrupo
	 * @param sociedad
	 * @param sistema
	 * @param idCobro
	 * @param importe
	 * @throws NegocioExcepcion
	 */
	public void enviarMailyGenerarTareaImputacionManual(Long idOperacion, Long grupo, EstadoTransaccionEnum estadoDelGrupo, SociedadEnum sociedad, SistemaEnum sistema, Long idCobro, BigDecimal importe) throws NegocioExcepcion;

	/**
	 * 
	 * @param listaTransaccionesPorGrupo
	 * @return
	 */
	public BigDecimal calcularImporteParcialDelGrupo(List<ShvCobTransaccionSinOperacion> listaTransaccionesPorGrupo);

	/**
	 * 
	 * @param transaccion
	 * @return
	 */
	public boolean hayMedioPagoComProveedoresOLiquidoProducto(ShvCobTransaccionSinOperacion transaccion);

	/**
	 * 
	 * @param transaccion
	 * @param usuario
	 * @return
	 * @throws NegocioExcepcion
	 * @throws ReintentoExcepcion
	 */
	public ShvCobTransaccionSinOperacion verificarEstadoTransaccion(ShvCobTransaccionSinOperacion transaccion, String usuario) throws NegocioExcepcion, ReintentoExcepcion;

	/**
	 * 
	 * @param cobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public void calcularImporteParcial(ShvCobCobro cobro) throws PersistenciaExcepcion;

	/**
	 * Al haber un error en desapropiacion PARCIAL, el cobro debe avanzar hasta EN ERROR EN DESAPROPIACION
	 * @param workflow
	 * @param idOperacion
	 * @param usuario
	 * @param cambioEstado
	 * @throws NegocioExcepcion
	 */
	public void avanzarCobroAErrorEnDesapropiacionParcial(ShvWfWorkflow workflow, Long idOperacion, String usuario, boolean cambioEstado) throws NegocioExcepcion;

}