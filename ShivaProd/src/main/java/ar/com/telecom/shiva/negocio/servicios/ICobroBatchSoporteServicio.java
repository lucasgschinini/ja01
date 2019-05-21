package ar.com.telecom.shiva.negocio.servicios;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.SimulacionCobroExcepcion;
import ar.com.telecom.shiva.negocio.servicios.impl.ComponentesCobroBatch;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;

public interface ICobroBatchSoporteServicio {
	
	/**
	 * 
	 * @param edCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ComponentesCobroBatch convertirCobroEnEdicionEnComponentesCobroBatch(ShvCobEdCobro edCobro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param componentesCobroBatch
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvCobCobro armarCobro(ComponentesCobroBatch componentesCobroBatch) throws NegocioExcepcion;

	/**
	 * 
	 * @param tratamientoDiferencia
	 * @param medioPago
	 * @param idOperacion
	 * @return
	 */
	public Modelo calcularOrigenCargo(ShvCobTratamientoDiferencia tratamientoDiferencia, ShvCobMedioPago medioPago, Long idOperacion);
	
	/**
	 * 
	 * @param documentoCalipso
	 */
	public void limpiarCamposCopiaDocumentoAplicacionPorDifCambio(ShvCobFacturaCalipso documentoCalipso);
	
	/**
	 * 
	 * @param shvCobFactura
	 * @param shvCobMedioPago
	 */
	public void agregarTipoCambioFactura(ShvCobFactura shvCobFactura, ShvCobMedioPago shvCobMedioPago);

	/**
	 * Metodo para simulacion de cobros
	 * @param valorSimplificado
	 * @param medioPago
	 * @param listaRespuesta
	 */
	public void validarSaldoYDisponibilidad(Long idValor, BigDecimal importe) throws SimulacionCobroExcepcion, NegocioExcepcion;
	
	/**
	 * Metodo sobrecargado para imputacion de cobros
	 * @param valorSimplificado
	 * @param medioPago
	 * @param listaRespuesta
	 */
//	public void validarSaldoYDisponibilidad(ShvValValorSimplificado valorSimplificado, ShvCobMedioPagoSinOperacion medioPago, String descripError);
}
