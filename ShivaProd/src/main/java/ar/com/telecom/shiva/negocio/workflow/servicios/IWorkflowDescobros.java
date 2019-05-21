package ar.com.telecom.shiva.negocio.workflow.servicios;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;


public interface IWorkflowDescobros extends IWorkflow {
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow iniciarDescobro(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow descobrarCobro(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow regPendReversoDocMedioCobradores(ShvWfWorkflow wf,String datosModificados, String usuarioModificacion)throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow regPendReversoDocCobradoresMedioCalipso(ShvWfWorkflow wf,String datosModificados, String usuarioModificacion)throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow regPendReversoDocCobradoresMedioMic(ShvWfWorkflow wf,String datosModificados, String usuarioModificacion)throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow regPendReversoDocCalipsoMedioCobradores(ShvWfWorkflow wf,String datosModificados, String usuarioModificacion)throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow regPendReversoDocMicMedioCobradores(ShvWfWorkflow wf,String datosModificados, String usuarioModificacion)throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow regPendReversoDocCobradores(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow regPendReversoMedioCobradores(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow regPendReversoDocMedioCalipso(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow regPendReversoDocCalipso(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow regPendReversoMedioCalipso(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow regPendReversoDocMedioMic(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow regPendReversoDocMic(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow regPendReversoMedioMic(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow procPendReversoDocMedioCobradores(ShvWfWorkflow wf,String datosModificados, String usuarioModificacion)throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow procPendReversoDocCobradoresMedioCalipso(ShvWfWorkflow wf,String datosModificados, String usuarioModificacion)throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow procPendReversoDocCobradoresMedioMic(ShvWfWorkflow wf,String datosModificados, String usuarioModificacion)throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow procPendReversoDocCalipsoMedioCobradores(ShvWfWorkflow wf,String datosModificados, String usuarioModificacion)throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow procPendReversoDocMicMedioCobradores(ShvWfWorkflow wf,String datosModificados, String usuarioModificacion)throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow procPendReversoDocCobradores(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow procPendReversoMedioCobradores(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow procPendReversoDocMedioCalipso(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow procPendReversoDocCalipso(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow procPendReversoMedioCalipso(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow procPendReversoDocMedioMic(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow procPendReversoDocMic(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow procPendReversoMedioMic(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow guardarDescobroEnEdicion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow solicitarDescobro(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow solicitarAnulacionDeDescobro(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow aplicarDescobro(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow registrarDescobroEnError(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow reintentarDescobroEnError(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow registrarErrorEnPrimerDescobro(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow revertirDescobroEnErrorEnPrimerDescobro(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow anularDescobroEnErrorEnPrimerDescobro(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow anularDescobroEnEdicion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow reintentarDescobro(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow registrarDescobroDeEnProcesoAPendienteMic(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow registrarDescobroDePendienteMicAPendienteProcesarMic(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion;
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	
	public ShvWfWorkflow registrarDescobroPendienteProcesarMicAEnProceso(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow registrarDescobroEnProcesoAPendienteConfirmacionManual(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion;

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow registrarDescobroPendienteConfirmacionManualAEnProceso(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion;
}
