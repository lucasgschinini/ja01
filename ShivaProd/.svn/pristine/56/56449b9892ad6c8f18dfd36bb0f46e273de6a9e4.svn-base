package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.bean.ArchivoByteArray;
import ar.com.telecom.shiva.negocio.bean.CobroCompensacionPdfCap;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;

public interface ICobroBatchServicio extends IServicio {
	
	
	/**
	 * Sprint 5, se modifican en la base las transacciones que permiten tratamiento de intereses en la pantalla
	 * @author u573005, fabio.giaquinta.ruiz	
	 * @param CobroDto cobroDto
	 * @throws NegocioExcepcion
	 */
	public void modificarTransaccionesConIntereses(CobroDto cobroDto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param transaccion
	 */
	public void actualizarTratamientoInteresesTransaccion(ShvCobTransaccion transaccion);
	
	/**
	 * 
	 * @param cobro
	 */
	public void actualizarTratamientoIntereses(ShvCobCobro cobro);
	
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ShvCobCobro> buscarCobrosPendienteSimulacionBatch() throws NegocioExcepcion;
	/**
	 * 
	 * @param idCobro
	 * @param esCarta
	 * @return
	 * @throws NegocioExcepcion
	 */
	public CobroCompensacionPdfCap completarDatosParaInformesCapPdf(long idCobro, boolean esCarta) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ArchivoByteArray generarPdfCartaCompensacion(Long idCobro) throws NegocioExcepcion;

	/**
	 * 
	 * @param idCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ArchivoByteArray generarPdfResumenCompensacion(Long idCobro) throws NegocioExcepcion;
	
	/**
	 * Este método es el encargado de avanzar el WF por la transicion correspondiente, teniendo en cuenta
	 * el estado actual (origen) del cobro, y el estado hacia el cuál deseo avanzar (destino)
	 * 
	 * @param cobro
	 * @param estado
	 * @param usuario
	 * @throws NegocioExcepcion
	 */
	public void cambiarEstadoCobro(ShvCobCobro cobro, Estado estado, String usuario) throws NegocioExcepcion;

	/**
	 * Este método actualiza el estado del cobro teniendo en cuenta las tareas asociadas de 
	 * tipo 
	 *   - "Revisar Resultado de cobro con Error (COB_REV_ERR_APLIC_MANUAL)" 
	 *   - "Revisar Resultado de cobro con Error (COB_REV_COB_ERR)" 
	 *   
	 * y los estados y acciones que han sido tomadas sobre ellas.
	 *   
	 * @param idCobro
	 * @param usuario
	 * @throws NegocioExcepcion
	 */
	public void cambiarEstadoSegunEstadoDeTareas(Long idCobro, String usuario) throws NegocioExcepcion;

	/**
	 * Este método actualiza el estado del cobro teniendo en cuenta las tareas asociadas de 
	 * tipo 
	 *   - "Revisar Resultado de cobro con Error (COB_REV_ERR_APLIC_MANUAL)" 
	 *   - "Revisar Resultado de cobro con Error (COB_REV_COB_ERR)" 
	 *   
	 * y los estados y acciones que han sido tomadas sobre ellas.
	 *   
	 * @param cobro
	 * @param usuario
	 * @throws NegocioExcepcion
	 */
	public void cambiarEstadoSegunEstadoDeTareas(ShvCobCobro cobro, String usuario) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idCobro
	 * @param sistemaDestino
	 * @param sociedad
	 * @throws NegocioExcepcion
	 */
	public void actualizarReferenciaMediosDePagoCTA(Long idCobro, SistemaEnum sistemaDestino, SociedadEnum sociedad,Long grupo) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idCobro
	 * @param usuario
	 * @param idTarea
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean verificarTareasParaAnularCobro(Long idCobro, String usuario, Long idTarea) throws NegocioExcepcion;
}
