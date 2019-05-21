package ar.com.telecom.shiva.negocio.workflow.servicios;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

public interface IWorkflowBoletas extends IWorkflow {
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow solicitarBoletaAnulacion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow establecerBoletaConciliado(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow deshacerBoletaConciliacionSugerida(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow establecerBoletaConciliacionSugerida(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow conciliarBoletaConDiferencias(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;

}
