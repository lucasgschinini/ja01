package ar.com.telecom.shiva.negocio.workflow.servicios.impl;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.workflow.WorkflowServiceImpl;
import ar.com.telecom.shiva.negocio.workflow.definicion.TipoWorkflow;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowValoresPorReversion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

public class WorkflowValoresPorReversionImpl extends WorkflowServiceImpl implements IWorkflowValoresPorReversion {

	@Override
	public ShvWfWorkflow crearWorkflow(String datosOriginales, String usuarioModificacion) throws WorkflowExcepcion {

		ShvWfWorkflow wf = new ShvWfWorkflow();
		wf.setTipoWorkflow(TipoWorkflow.VALORES_REVERSION);
		wf.setDatosModificados(datosOriginales);
		wf.setUsuarioModificacion(usuarioModificacion);
		
		return this.crearWorkflow(wf);
	}

	@Override
	public ShvWfWorkflow solicitarConfirmacionAltaValorPorReversion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow confirmarAltaValorPorReversion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow rechazarAltaValorPorReversion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarReconfirmacionAltaValorPorReversion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow actualizarSaldo(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
}
