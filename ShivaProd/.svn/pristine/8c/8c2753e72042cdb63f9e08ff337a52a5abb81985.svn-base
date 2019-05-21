package ar.com.telecom.shiva.negocio.workflow.servicios.impl;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.workflow.WorkflowServiceImpl;
import ar.com.telecom.shiva.negocio.workflow.definicion.TipoWorkflow;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowDescobros;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

public class WorkflowDescobrosImpl extends WorkflowServiceImpl implements IWorkflowDescobros {
	
	@Override
	public ShvWfWorkflow crearWorkflow(String datosOriginales, String usuarioModificacion) throws WorkflowExcepcion {
		ShvWfWorkflow wf = new ShvWfWorkflow();
		wf.setTipoWorkflow(TipoWorkflow.DESCOBRO);
		wf.setDatosModificados(datosOriginales);
		wf.setUsuarioModificacion(usuarioModificacion);
		wf = this.crearWorkflow(wf);
		return wf;
	}

	@Override
	public ShvWfWorkflow iniciarDescobro(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow descobrarCobro(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow regPendReversoDocMedioCobradores(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow regPendReversoDocCobradoresMedioCalipso(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow regPendReversoDocCobradoresMedioMic(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow regPendReversoDocCalipsoMedioCobradores(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow regPendReversoDocMicMedioCobradores(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow regPendReversoDocCobradores(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow regPendReversoMedioCobradores(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow regPendReversoDocMedioCalipso(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow regPendReversoDocCalipso(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow regPendReversoMedioCalipso(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow regPendReversoDocMedioMic(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow regPendReversoDocMic(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow regPendReversoMedioMic(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow procPendReversoDocMedioCobradores(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow procPendReversoDocCobradoresMedioCalipso(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow procPendReversoDocCobradoresMedioMic(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow procPendReversoDocCalipsoMedioCobradores(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow procPendReversoDocMicMedioCobradores(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow procPendReversoDocCobradores(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow procPendReversoMedioCobradores(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow procPendReversoDocMedioCalipso(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
 
	@Override
	public ShvWfWorkflow procPendReversoDocCalipso(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow procPendReversoMedioCalipso(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow procPendReversoDocMedioMic(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow procPendReversoDocMic(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow procPendReversoMedioMic(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow guardarDescobroEnEdicion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarDescobro(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarAnulacionDeDescobro(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow aplicarDescobro(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarDescobroEnError(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow reintentarDescobroEnError(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarErrorEnPrimerDescobro(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow revertirDescobroEnErrorEnPrimerDescobro(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow anularDescobroEnErrorEnPrimerDescobro(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow anularDescobroEnEdicion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow reintentarDescobro(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
					throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarDescobroDeEnProcesoAPendienteMic(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarDescobroDePendienteMicAPendienteProcesarMic(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarDescobroPendienteProcesarMicAEnProceso(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarDescobroEnProcesoAPendienteConfirmacionManual(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarDescobroPendienteConfirmacionManualAEnProceso(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
}
