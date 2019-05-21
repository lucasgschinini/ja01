package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccionMensajeriaDetalle;

public interface IMensajeriaTransaccionDao {

	public ShvCobTransaccionMensajeriaDetalle guardarMensaje(ShvCobTransaccionMensajeriaDetalle mensajeria) throws PersistenciaExcepcion;

	public ShvCobTransaccionMensajeriaDetalle buscarMensajePorIdMensajeria(Integer idMensajeria) throws PersistenciaExcepcion;
	
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajePorIdOperacionTransaccion(Long idOperacion, Integer idTransaccion) throws PersistenciaExcepcion;
	
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesPorIdOperacion(Long idOperacion, MensajeServicioEnum servicio) throws PersistenciaExcepcion;
	
	public ShvCobTransaccionMensajeriaDetalle buscarUltimoMensajeCompletado(Long idOperacion) throws PersistenciaExcepcion;
	
	public int borrarMensajesPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion;
	
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesPorIdOperacionTransaccion(Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio) throws PersistenciaExcepcion;
	
	//
	public List<ShvCobTransaccionMensajeriaDetalle> listarMensajesPendientesMIC(String fechaPermitidaParaReenvio) throws PersistenciaExcepcion;
	
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesRespuestaConfirmacion(Long idOperacion) throws PersistenciaExcepcion;
	
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesRespuestaDesapropiacion(Long idOperacion) throws PersistenciaExcepcion;
	
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesRespuestaApropiacion(Long idOperacion, Integer idTransaccion) throws PersistenciaExcepcion;
	
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesDesapropiacionConfirmacionEnviadosMIC(Long idOperacion) throws PersistenciaExcepcion;
	
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesApropiacionCargoEnviadosMIC(Long idOperacion, Integer idTransaccion) throws PersistenciaExcepcion;
	
	//
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajeCancelado(Long idOperacion) throws PersistenciaExcepcion;

	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajeDesapropiacionCompletadaMic(Long idOperacion) throws PersistenciaExcepcion;

	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajeConfirmacionCompletadaMic(Long idOperacion) throws PersistenciaExcepcion;

	/**
	 * Se espera id operacion cobro y numero de transaccion que coninciden entre el cobro y el descobro y el query retorna la mensajeria del descobro correspondiente
	 * @author u573005, fabio.giaquinta.ruiz
	 * @param idOperacion
	 * @param idTransaccion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesDescobroEnviadosMIC(Long idOperacionCobro, Integer numeroTransaccion)	throws PersistenciaExcepcion;
	/**
	 * Borroa los mensajes de transacciones segun el id de operacion y MesajeServicioEnum
	 * Los parametros servicio1, servicio2 y servicio3. forma un filtro del tipo "IN (servicio1 , servicio2 , servicio3)
	 * @param idOperacion
	 * @param servicio1
	 * @param servicio2
	 * @param servicio3
	 * @throws PersistenciaExcepcion
	 */
	public void borrarMensajesPorIdOperacionServicios(Long idOperacion, MensajeServicioEnum servicio1, MensajeServicioEnum servicio2, MensajeServicioEnum servicio3) throws PersistenciaExcepcion;
	

	public void borrarMensajesPorIdOperacionServiciosVarios(Long idOperacion, Integer idTransaccion, List<MensajeServicioEnum> listaMensajes) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @param idTransaccion
	 * @param servicio
	 * @param estado
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesPorIdOperacionTransaccionYEstadoMensaje(Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio, MensajeEstadoEnum estado) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idTransaccionMensajeria
	 * @throws PersistenciaExcepcion
	 */
	public void borrarMensajesPorIdTransaccionMensajeria(Integer idTransaccionMensajeria) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param strIdOperacion
	 * @param idTransaccion
	 * @param servicio
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public boolean mensajeAnteriormenteProcesado(Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajeDesapropiacionParcialCompletadaMic(Long idOperacion) throws PersistenciaExcepcion;
}
