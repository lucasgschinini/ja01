package ar.com.telecom.shiva.negocio.workflow.servicios;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;


public interface IWorkflowRecibosPreImpresos extends IWorkflow {
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow asociarCompensacion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow eliminarCompensacion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow establecerReciboConciliado(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow establecerReciboPendiente(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow eliminarConciliado(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow eliminarPendiente(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow eliminarConciliadoCompensaciones(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow eliminarPendienteCompensaciones(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow eliminarPendienteConciliado(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow actCompensacionesConciliado(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow actConciliadoPendiente(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow actCompensacionesPendiente(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow anularRecibo(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
}

