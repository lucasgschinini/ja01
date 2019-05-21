package ar.com.telecom.shiva.negocio.workflow.servicios;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

public interface IWorkflowOperacionesMasivas extends IWorkflow {

	/**
	 * Cambia el estado del workflow de MAS_CONFIGURACION a MAS_PENDIENTE_PROCESAR
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow registrarOperacionMasivaEnProcesoDeImputacion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * Cambia el estado del workflow de MAS_PENDIENTE_PROCESAR a MAS_PROCESO_IMPUTACION
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow procesarOperacionMasiva(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * Cambia el estado del workflow de MAS_PROCESO_IMPUTACION a MAS_ERROR
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow registrarOperacionMasivaEnError(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * Cambia el estado del workflow de MAS_PROCESO_IMPUTACION a MAS_IMPUTADA
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow registrarOperacionMasivaProcesada(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * Cambia el estado del workflow de  MAS_PROCESO_IMPUTACION a MAS_PENDIENTE_PROCESAR
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow registrarOperacionMasivaProcesadaParcialmente(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	/**
	 * Cambia el estado del workflow de MAS_CONFIGURACION a MAS_PENDIENTE_APROBACION
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow solicitarAprobacionDeAltaDeOperacionMasiva(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * Cambia el estado del workflow de MAS_PENDIENTE_APROBACION a MAS_PENDIENTE_PROCESAR
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow aprobarAltaDeOperacionMasiva(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * Cambia el estado del workflow de MAS_PENDIENTE_APROBACION a MAS_RECHAZADA
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow rechazarAltaDeOperacionMasiva(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * Cambia el estado del workflow de MAS_RECHAZADA a MAS_PENDIENTE_APROBACION
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow solicitarReaprobacionAltaDeOperacionMasiva(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * Cambia el estado del workflow de MAS_PENDIENTE_PROCESAR a MAS_ANULADA
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow cancelarOperacionMasivaPendienteDeProcesar(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * Cambia el estado del workflow de MAS_PENDIENTE_PROCESAR a MAS_PENDIENTE_APROBACION
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow solicitarOperacionMasivaPedidoAutorizacionReferente(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * cambio el estado del workflow de MAS_RECHAZADA a MAS_ANULADA
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow anularOperacionMasivaRechazada(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * cambio el estado del workflow de MAS_ERROR a MAS_ANULADA
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow anularOperacionMasivaEnError(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * cambio el estado del workflow de MAS_ERROR a MAS_PENDIENTE_APROBACION
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow solicitarReaprobacionDeOperacionMasivaEnError(ShvWfWorkflow wf,String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * cambio el estado del workflow de MAS_RECHAZADA a MAS_PENDIENTE_PROCESAR
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow regresarOperacionMasivaRechazadaAPendienteProcesar(ShvWfWorkflow wf,String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * cambio el estado del workflow de MAS_ERROR a MAS_PENDIENTE_PROCESAR
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow regresarOperacionMasivaEnErrorAPendienteProcesar(ShvWfWorkflow wf,String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
}
