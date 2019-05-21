package ar.com.telecom.shiva.base.jms.servicios;

import java.util.Collection;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionDescobroDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosDto;

public interface IMicJmsServicio extends IJmsServicio {
	
	/**
	 * Envio la apropiacion de deuda al MIC
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	public void apropiarDeuda(MicTransaccionADCDto dto) throws NegocioExcepcion;
	
	/**
	 * Envio la apropiacion de medio de pago al MIC
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	public void apropiarMedioPago(MicTransaccionADCDto dto) throws NegocioExcepcion;
	
	/**
	 * Envio la apropiacion de deuda/medio de pago al MIC
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	public void apropiarDeudaYMedioPago(MicTransaccionADCDto dto) throws NegocioExcepcion;
	
	/**
	 * Envio la desapropiacion al MIC
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	public void desapropiarOperacion(MicTransaccionADCDto dto) throws NegocioExcepcion;
	
	/**
	 * Envio la confirmacion al MIC
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	public void confirmarOperacion(MicTransaccionADCDto dto) throws NegocioExcepcion;
	
	/**
	 * Envio la Generacion del cargo tanto debito como credito
	 * @throws NegocioExcepcion
	 */
	public void generarCargo(MicTransaccionGeneracionCargosDto dto) throws NegocioExcepcion;
	
	/**
	 * Envio el Descobro de las operaciones
	 * @throws NegocioExcepcion
	 */
	public void descobrarOperacion(MicTransaccionDescobroDto dto) throws NegocioExcepcion;	
	
	/*********************************************************************************************************
	 * Recepcion de mensajes
	 ********************************************************************************************************/
	/**
	 * Recibe algun mensaje
	 * @throws NegocioExcepcion
	 */
	public void recibirMensajes(TipoProcesoEnum tipoProceso)  throws NegocioExcepcion;
	
	
	/*********************************************************************************************************
	 * Mensajeria
	 ********************************************************************************************************/
	/**
	 * Guarda un mensaje vacio y se obtiene el id
	 * @param mensaje
	 * @throws NegocioExcepcion
	 */
	public CobMensajeriaTransaccionDto crearMensajeria(CobMensajeriaTransaccionDto dto) throws NegocioExcepcion;
	
	
	/*********************************************************************************************************
	 * Para el uso de reintentos
	 ********************************************************************************************************/
	/**
	 * Listo mensajes pendientes
	 * @throws NegocioExcepcion
	 */
	public Collection<DTO> listarMensajesPendientes(TipoProcesoEnum tipoProceso) throws NegocioExcepcion;
	
	/**
	 * Reenvio de mensajes
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	public void reenviarMensaje(DTO dto) throws NegocioExcepcion;

}
