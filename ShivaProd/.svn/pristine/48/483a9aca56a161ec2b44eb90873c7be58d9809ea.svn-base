package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ReintentoExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTratamientoDiferenciaSinOperacion;


public interface ICobroBatchSoporteImputacionMicServicio {
	
	public void generarCargoReintegroMic(ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion mpProxFacturaMic,
			ShvCobTratamientoDiferenciaSinOperacion tratamientoDiferencia, ShvWfWorkflow workflow) throws NegocioExcepcion, ReintentoExcepcion;
	
	public void apropiacionMic(List<ShvCobMedioPagoSinOperacion> listaMediosPagoAEnviar, ShvCobFacturaSinOperacion factura, ShvCobTransaccionSinOperacion transaccion) throws NegocioExcepcion, ReintentoExcepcion;

	public void confirmacionMic(List<String> listaOperacionTransacciones, ShvCobCobro cobro, boolean reenvioConfirmacion) throws NegocioExcepcion, ReintentoExcepcion;
	
	public void desapropiacionMic(List<String> listaOperacionTransacciones, ShvCobCobro cobro, String idOperacionADesapropiar) throws NegocioExcepcion, ReintentoExcepcion;

	public ShvWfWorkflow pasarWorkflowPendienteProcesarMicAEnProceso(Long idOperacion,	TipoInvocacionEnum tipoInvocacion, String idOperacionTransaccion,
			String usuario) throws NegocioExcepcion;

	public void pasarWorkflowPendienteMICAPendienteProcesarMic(Long idOperacion, TipoInvocacionEnum tipoInvocacion, String idOperacionTransaccion,
			String usuario) throws NegocioExcepcion;

	public void cambiarEstadoCobroPendienteMicAEnProceso(ShvCobCobro cobro, String usuario, TipoInvocacionEnum tipoInvocacion,	String idOperacionTransaccion) throws NegocioExcepcion;
	
	/**
	 * Me devuelve la lista de cobros pendientes de MIC
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<Long> listarCobrosPendientesProcesarMIC() throws NegocioExcepcion;

	public void micRecepcionApropiacionDeuda(DTO respuesta) throws NegocioExcepcion;
	
	public void micRecepcionApropiacionMedioPago(DTO respuesta) throws NegocioExcepcion;
	
	public void micRecepcionApropiacionDeudaMedioPago(DTO respuesta) throws NegocioExcepcion;
	
	public void micRecepcionDesapropiacion(DTO respuesta) throws NegocioExcepcion;
	
	public void micRecepcionConfirmacion(DTO respuesta) throws NegocioExcepcion;
	
	public void micRecepcionCargo(DTO respuesta) throws NegocioExcepcion;

	public List<Long> listarCobrosImpManualPendientesProcesarMIC() throws NegocioExcepcion;
	
	public void desapropiacionParcialMic(ShvCobTransaccionSinOperacion transaccion) throws NegocioExcepcion;
}
