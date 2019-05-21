package ar.com.telecom.shiva.negocio.workflow.servicios;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

public interface IWorkflow {

	/**
	 * 
	 * @param datosOriginales
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow crearWorkflow(String datosOriginales, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow actualizarWorkflow(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

}
