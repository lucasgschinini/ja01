package ar.com.telecom.shiva.negocio.servicios;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoAccionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfTarea;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteTareasPendientesFiltro;

public interface ITareaServicio {

	/**
	 * 
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	void crearTareaTalonario(DTO dto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	void crearTareaValor(DTO dto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	void crearTareaRegistroAVC(DTO dto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	void crearTareaOperacionMasiva(DTO dto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param tipoTarea
	 * @param idWorkflow
	 * @param fechaEjecucion
	 * @param usuarioEjecucion
	 * @param mailTarea
	 * @throws NegocioExcepcion
	 */
	public void finalizarTarea(TipoTareaEnum tipoTarea, Integer idWorkflow, Date fechaEjecucion, String usuarioEjecucion, Mail mailTarea) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param tipoTarea
	 * @param idWorkflow
	 * @param fechaEjecucion
	 * @param usuarioEjecucion
	 * @param mailTarea
	 * @throws NegocioExcepcion
	 */
	public void finalizarTareaCorrecto(TipoTareaEnum tipoTarea, Integer idWorkflow, Date fechaEjecucion, String usuarioEjecucion, Mail mailTarea) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param tipoTarea
	 * @param idWorkflow
	 * @param usuarioEjecucion
	 * @param mail
	 * @throws NegocioExcepcion
	 */
	public void finalizarTarea(TipoTareaEnum tipoTarea, Integer idWorkflow, String usuarioEjecucion, Mail mail) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	Collection<DTO> listarTareasPendientes(VistaSoporteTareasPendientesFiltro filtro) throws NegocioExcepcion;

	/**
	 * 
	 * @param tarea
	 * @throws NegocioExcepcion
	 */
	void crearTareaValorPorReversion(DTO tarea) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param tipoTarea
	 * @param idWorkflow
	 * @param usuarioAsignado
	 * @throws NegocioExcepcion
	 */
	public void eliminarTarea(TipoTareaEnum tipoTarea, Integer idWorkflow, String usuarioAsignado) throws NegocioExcepcion;

	/**
	 * 
	 * @param tarea
	 * @throws NegocioExcepcion
	 */
	void crearTareaResultadoValidacionDebitos(DTO dto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	void crearTareaAprobacionCobro(DTO dto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	void crearTareaRechazoAprobacionCobro(DTO dto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	void crearTareaResultadoImputacion(DTO dto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	void crearTareaResultadoSimulacion(DTO dto) throws NegocioExcepcion;
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param idWorkflow
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ShvWfTarea> buscarTareasPendientes(Integer idWorkflow) throws NegocioExcepcion;
	
	/**
	 * @author u587496, Guido.Settecerze
	 * @param idTarea
	 * @return
	 * @throws NegocioExcepcion
	 */
	public DTO buscarTarea(Long idTarea) throws NegocioExcepcion;

	/**
	 * @author u572487 Guido.Settecerze
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	public void crearTareaRechazoAprobacionOperacionMasiva(DTO dto) throws NegocioExcepcion;

	/**
	 * 
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	public void crearTareaReversionCompensacionPendiente(DTO dto) throws NegocioExcepcion;

	/**
	 * 
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	public void crearTareaImputacionReversionCobroSistemaExterno(DTO dto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param tipoTarea
	 * @param idWorkflow
	 * @param usuario
	 * @throws NegocioExcepcion
	 */
	public void tomarTarea(TipoTareaEnum tipoTarea, Integer idWorkflow, SociedadEnum sociedad, SistemaEnum sistema, String usuario) throws NegocioExcepcion;
	
/**
 * 
 * @param tipoTarea
 * @param idWorkflow
 * @param usuarioAsignacion
 * @param perfilAsignacion
 * @throws NegocioExcepcion
 */
	public void liberarTarea(TipoTareaEnum tipoTarea, Integer idWorkflow,SistemaEnum sistema, SociedadEnum sociedad, String usuarioAsignacion) throws NegocioExcepcion;

	/**
	 * 
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	public void crearTareaImputacionManualCobro(DTO dto) throws NegocioExcepcion;

	/**
	 * 
	 * @param tipoTarea
	 * @param sociedad
	 * @param sistema
	 * @param idWorkflow
	 * @param fechaEjecucion
	 * @param usuarioEjecucion
	 * @param mailTarea
	 * @param observacion 
	 * @throws NegocioExcepcion
	 */
	void finalizarTareaCorrecto(TipoTareaEnum tipoTarea, SociedadEnum sociedad,	SistemaEnum sistema, Integer idWorkflow, Date fechaEjecucion,
			String usuarioEjecucion, Mail mailTarea, TipoAccionEnum accion, String observacion) throws NegocioExcepcion;
}