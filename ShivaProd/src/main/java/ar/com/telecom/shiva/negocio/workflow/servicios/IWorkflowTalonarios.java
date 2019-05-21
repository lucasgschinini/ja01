package ar.com.telecom.shiva.negocio.workflow.servicios;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;


public interface IWorkflowTalonarios extends IWorkflow {

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow asignarGestor(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow asignarAdministrador(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow reasignarSupervisor(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow reasignarAdministrador(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow rechazarErrorRango(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow dispararAnulacionAutomatica(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow rechazarRendicion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow aceptarRendicion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow dispararAnulacionRendicion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
}
