package ar.com.telecom.shiva.base.soa.reintentos;

import java.util.Collection;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;


public interface IReintentos {

	/**
	 * Envio por primera vez el mensaje 
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	void enviarMensaje(DTO dto, String mensajeAEnviar) throws NegocioExcepcion;
	
	/**
	 * Envio por primera vez el mensaje de respuesta 
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	void enviarRespuesta(DTO dto, String mensajeAEnviar) throws NegocioExcepcion;
	
	/**
	 * Lista de mensajes (pendientes/enviados)+reintentos<max
	 */
	Collection<DTO> listaMensajesAReenviarMIC(TipoProcesoEnum tipoImputacion) throws NegocioExcepcion;
	
	/**
	 * reenvio un mensaje 
	 */
	void reenviarMensaje(DTO dto, boolean incrementar) throws NegocioExcepcion;
	
}
