package ar.com.telecom.shiva.negocio.workflow.servicios.impl;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.workflow.WorkflowServiceImpl;
import ar.com.telecom.shiva.negocio.workflow.definicion.TipoWorkflow;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowRecibosPreImpresos;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

public class WorkflowRecibosPreImpresosImpl extends WorkflowServiceImpl implements IWorkflowRecibosPreImpresos {
 
	@Override
	public ShvWfWorkflow crearWorkflow(String datosOriginales, String usuarioModificacion) throws WorkflowExcepcion {
		ShvWfWorkflow wf = new ShvWfWorkflow();
		wf.setTipoWorkflow(TipoWorkflow.RECIBO_PRE_IMPRESO);
		wf.setDatosModificados(datosOriginales);
		wf.setUsuarioModificacion(usuarioModificacion);
		
		return this.crearWorkflow(wf);
	}
	
	@Override
	public ShvWfWorkflow asociarCompensacion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow eliminarCompensacion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow establecerReciboPendiente(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow establecerReciboConciliado(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
					throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow eliminarConciliado(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow eliminarPendiente(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow eliminarConciliadoCompensaciones(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
					throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow eliminarPendienteCompensaciones(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
					throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow eliminarPendienteConciliado(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
					throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow actCompensacionesConciliado(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
					throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow actConciliadoPendiente(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
					throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow actCompensacionesPendiente(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
					throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow anularRecibo(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
					throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
}
