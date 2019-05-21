package ar.com.telecom.shiva.negocio.servicios;

import java.util.Collection;
import java.util.List;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccionMensajeriaDetalle;

public interface IMensajeriaTransaccionServicio {
	
	/**
	 * Retorna el DTO 
	 * @param id
	 * @return
	 * @throws NegocioExcepcion
	 */
	DTO buscar(Integer idMensajeriaTransaccion) throws NegocioExcepcion;
	
	/**
	 * Retorna el DTO 
	 * @param id
	 * @return
	 * @throws NegocioExcepcion
	 */
	DTO buscar(Long idOperacion, Integer idTransaccion, SistemaEnum cobrador, MensajeEstadoEnum estado) throws NegocioExcepcion;
	
	/**
	 * Retorna el DTO
	 * @return
	 * @throws NegocioExcepcion
	 */
	DTO buscarMensaje(Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws NegocioException
	 */
	DTO buscarUltimoMensajeRespuesta(Long idOperacion) throws NegocioExcepcion;
	
	/**
	 * Retorna el DTO 
	 * @param id
	 * @return
	 * @throws NegocioExcepcion
	 */
	DTO buscarMensajeRecibido(Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio, String mensajeRecibido) throws NegocioExcepcion;
	
	/**
	 * Retorna la lista de pendientes/enviados + reintentos 
	 * @param id
	 * @return
	 * @throws NegocioExcepcion
	 */
	Collection<DTO> listarMensajeriasPendientesMIC(Integer cantMaxReintentos, String fechaPermitidaParaReenvio, TipoProcesoEnum tipoImputacion) throws NegocioExcepcion;
	
	/**
	 * Retorna el ultimo mensaje Enviado al MIC (Desapropiacion / Confirmacion)
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	DTO buscarMensajeDesapropiacionConfirmacionEnviadoAlMIC(Long idOperacion) throws NegocioExcepcion;
	
	/**
	 * Retorna el ultimo mensaje Enviado al MIC (Apropiacion / Cargo)
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	DTO buscarMensajeApropiacionCargoEnviadoAlMIC(Long idOperacion, Integer idTransaccion) throws NegocioExcepcion;
	
	
	/**
	 * Retorna el ultimo mensaje enviado al Mic (Descobro / CargoDescobro) 
	 * @param idOperacion
	 * @param idTransaccion
	 * @return
	 * @throws NegocioExcepcion
	 */
	DTO buscarMensajeDescobroCargoEnviadoAlMIC(Long idOperacion, Integer idTransaccion) throws NegocioExcepcion;
	
	/**
	 * Crea un registro DTO
	 * @param dto
	 * @return
	 * @throws NegocioExcepcion
	 */
	DTO crearTransaccionPropia(DTO dto) throws NegocioExcepcion;
	DTO crearTransaccionPropagada(DTO dto) throws NegocioExcepcion;
	
	/**
	 * Retorna el DTO
	 * @param idOperacion
	 * @param servicio
	 * @return
	 * @throws NegocioExcepcion
	 */
	DTO buscarMensajePorIdOperacion(Long idOperacion, MensajeServicioEnum servicio) throws NegocioExcepcion;
	
	/**
	 * Retorna el DTO
	 * @param idOperacion
	 * @param idTransaccion
	 * @param servicio
	 * @return
	 * @throws NegocioExcepcion
	 */
	DTO buscarMensajePorIdOperacionTransaccion(Long idOperacion, Integer idTransaccion,MensajeServicioEnum servicio) throws NegocioExcepcion; 
	
	/**
	 * Modifica un registro DTO
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	void modificar(DTO dto) throws NegocioExcepcion;
	void modificarTransaccionPropia(DTO dto) throws NegocioExcepcion;
	
	/**
	 * Modifica un registro DTO
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	void cancelarMensaje(CobMensajeriaTransaccionDto mensajeDto) throws NegocioExcepcion;

	/**
	 * Modifica un registro DTO
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	DTO buscarMensajeCancelado(Long idOperacion) throws NegocioExcepcion;
	
	/**
	 * Modifica un registro DTO
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	DTO buscarRespuestaConfirmacionMic(Long idOperacion) throws NegocioExcepcion;
	
	/**
	 * Modifica un registro DTO
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	DTO buscarRespuestaDesapropiacionMic(Long idOperacion) throws NegocioExcepcion;
	
	public void borrarMensajesPorIdOperacionServiciosVarios(Long idOperacion, Integer idTransaccion, List<MensajeServicioEnum> listaMensajes ) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @param idTransaccion
	 * @param servicio
	 * @param estado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public DTO buscarMensajePorIdOperacionTransaccionYEstadoMensaje(Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio, MensajeEstadoEnum estado) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param mensaje
	 * @throws NegocioExcepcion
	 */
	public void borrarMensajesPorIdTransaccionMensajeria(ShvCobTransaccionMensajeriaDetalle mensaje) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return 
	 * @throws NegocioExcepcion
	 */
	public void borrarMensajesPorIdOperacion(Long idOperacion) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param strIdOperacion
	 * @param idTransaccion
	 * @param servicio
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public boolean mensajeAnteriormenteProcesado(Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<CobMensajeriaTransaccionDto> buscarRespuestasDesapropiacionParcialMic(Long idOperacion) throws NegocioExcepcion;

}
