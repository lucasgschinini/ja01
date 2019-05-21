package ar.com.telecom.shiva.negocio.workflow.servicios.impl;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.workflow.WorkflowServiceImpl;
import ar.com.telecom.shiva.negocio.workflow.definicion.TipoWorkflow;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowBoletas;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

public class WorkflowBoletasImpl extends WorkflowServiceImpl implements IWorkflowBoletas {

	@Override
	public ShvWfWorkflow crearWorkflow(String datosOriginales, String usuarioModificacion) throws WorkflowExcepcion {
		ShvWfWorkflow wf = new ShvWfWorkflow();
		wf.setTipoWorkflow(TipoWorkflow.BOLETA);
		wf.setDatosModificados(datosOriginales);
		wf.setUsuarioModificacion(usuarioModificacion);
		
		return this.crearWorkflow(wf);
	}

	@Override
	public ShvWfWorkflow solicitarBoletaAnulacion(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow establecerBoletaConciliado(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow deshacerBoletaConciliacionSugerida(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow establecerBoletaConciliacionSugerida(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow conciliarBoletaConDiferencias(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

}
