package ar.com.telecom.shiva.negocio.workflow.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;


public interface IWorkflowRegistrosAVC extends IWorkflow {

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow solicitarRegistroAnulacion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow confirmarAnulacion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow rechazarPedidoDeAnulacion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow establecerRegistroPendienteConciliadoSinBoleta(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow establecerRegistroConciliadoSinBoleta(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow conciliarRegistroConDiferencias(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow establecerRegistroConciliado(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow deshacerRegistroConciliacionSugerida(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow rechazarAltaValor(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow solicitarReconfirmacionAltaValor(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow establecerRegistroConciliacionSugerida(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow solicitarConfirmacionAltaValor(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow rechazarSolicitudAnulacionRegistroPendienteConfirmarValor(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow solicitarAnulacionRegistroPendienteConfirmarValor(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow solicitarAnulacionAltaValorRechazada(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * Creacion de un Deposito
	 * Retorna la lista de atributos del Deposito que debe mostrar para el alta de Deposito
	 * 
	 * @return
	 */
	List<String> getListaDatosOriginalesDeposito();
	
	/**
	 * Creacion de un Interdeposito
	 * Retorna la lista de atributos del Interdeposito que debe mostrar para el alta de Interdepositos
	 * 
	 * @return
	 */
	List<String> getListaDatosOriginalesInterdeposito(); 
	
	/**
	 * Creacion de una Transferencia
	 * Retorna la lista de atributos de la Transferencia que debe mostrar para el alta de Transferencia
	 * 
	 * @return
	 */
	List<String> getListaDatosOriginalesTransferencia();
}
