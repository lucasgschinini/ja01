/**
 * 
 */
package ar.com.telecom.shiva.negocio.servicios;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.SimulacionCobroExcepcion;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCargosWS;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosDto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaMic;

/**
 * @author u564030
 *
 */
public interface ICobroBatchSimulacionServicio {

	/**
	 * 
	 * @param idCobro
	 * @throws NegocioExcepcion
	 */
	public ShvCobEdCobro simularCobroOnline(Long idCobro) throws NegocioExcepcion;

	/**
	 * 
	 * @param edCobro
	 * @throws SimulacionCobroExcepcion
	 * @throws NegocioExcepcion
	 */
	public void simularCobroOnline(ShvCobEdCobro edCobro) throws SimulacionCobroExcepcion, NegocioExcepcion;	
	
	/**
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	public void simularCobroProcesamientoOnline(ShvCobCobro cobro, String usuarioModificacion) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	public void simularCobroBatch(ShvCobCobro cobro, String usuarioModificacion) throws NegocioExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvCobCobro buscarCobroPorIdOperacion(Long idOperacion) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idCobro
	 */
	public void simularCobroEnCobradores(Long idCobro);
	
	/**
	 * 
	 * @param medioPago
	 */
	public EntradaCalipsoCargosWS prepararSimulacionGeneracionCargosDebitoCalipso(ShvCobMedioPagoDebitoProximaFacturaCalipso medioPago) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param medioPago
	 */
	public MicTransaccionGeneracionCargosDto prepararSimulacionGeneracionCargosDebitoMic(ShvCobMedioPagoDebitoProximaFacturaMic medioPago) throws NegocioExcepcion;
	
}

