package ar.com.telecom.shiva.base.jms.servicios;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaCreditoEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaDeudaEntrada;
import ar.com.telecom.shiva.base.jms.datos.salida.MicConsultaCreditoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicConsultaDeudaSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaADCSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaGeneracionCargoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaDescobroSalida;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionDescobroDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosDto;

public interface IMicJmsSyncServicio extends IJmsServicio {
	
	/**
	 * Envio la consulta de deuda al MIC
	 * @throws NegocioExcepcion
	 */
	public MicConsultaDeudaSalida consultarDeuda(MicConsultaDeudaEntrada entrada) throws JmsExcepcion;
	
	/**
	 * Envio la consulta de credito al MIC
	 * @throws NegocioExcepcion
	 */
	public MicConsultaCreditoSalida consultarCredito(MicConsultaCreditoEntrada entrada) throws JmsExcepcion;
	
	
	/***********************************************************************************************************
	 * Simulaciones de apropiaciones
	 **********************************************************************************************************/
	/**
	 * Envio la Apropiacion de una deuda a MIC
	 * @throws NegocioExcepcion
	 */
	public MicRespuestaADCSalida simularApropiarDeuda(MicTransaccionADCDto dto) throws JmsExcepcion;
	
	/**
	 * Envio la Apropiacion de un medio de pago a MIC
	 * @throws NegocioExcepcion
	 */
	public MicRespuestaADCSalida simularApropiarMedioPago(MicTransaccionADCDto dto) throws JmsExcepcion;
	
	/**
	 * Envio la Apropiacion de una deuda y de medios de pago a MIC
	 * @throws NegocioExcepcion
	 */
	public MicRespuestaADCSalida simularApropiarDeudaYMedioPago(MicTransaccionADCDto dto) throws JmsExcepcion;
	
	
	/***********************************************************************************************************
	 * Simulaciones de cargos
	 **********************************************************************************************************/
	/**
	 * Envio la Generacion del cargo de un debito
	 * @throws NegocioExcepcion
	 */
	public MicRespuestaGeneracionCargoSalida simularCargoDebito(MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion;
	
	/**
	 * Envio la Generacion del cargo de un credito
	 * @throws NegocioExcepcion
	 */
	public MicRespuestaGeneracionCargoSalida simularCargoCredito(MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion;
	
	/**
	 * Envio la simulacion de intereses
	 * @throws NegocioExcepcion
	 */
	public MicRespuestaGeneracionCargoSalida simularInteres(MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion;

	/***********************************************************************************************************
	 * Simulaciones de descobros
	 **********************************************************************************************************/
	 
	/**
	 * Envio la simulacion del descobro
	 * @throws NegocioExcepcion
	 */
	public MicRespuestaDescobroSalida simularDescobro(MicTransaccionDescobroDto dto) throws JmsExcepcion;
	
	/**
	 * Envio la Generacion del contracargo de un debito
	 * @throws NegocioExcepcion
	 */
	public MicRespuestaGeneracionCargoSalida simularContracargoDebito(MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion;
	
	/**
	 * Envio la Generacion del contracargo de un credito
	 * @throws NegocioExcepcion
	 */
	public MicRespuestaGeneracionCargoSalida simularContracargoCredito(MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion;
	
	/**
	 * Envio la simulacion de contracargo de intereses
	 * @throws NegocioExcepcion
	 */
	public MicRespuestaGeneracionCargoSalida simularContracargoInteres(MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion;
}
