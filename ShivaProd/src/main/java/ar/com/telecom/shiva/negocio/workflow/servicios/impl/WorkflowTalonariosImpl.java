package ar.com.telecom.shiva.negocio.workflow.servicios.impl;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.workflow.WorkflowServiceImpl;
import ar.com.telecom.shiva.negocio.workflow.definicion.TipoWorkflow;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowTalonarios;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

public class WorkflowTalonariosImpl extends WorkflowServiceImpl implements IWorkflowTalonarios {
 
	@Override
	public ShvWfWorkflow crearWorkflow(String datosOriginales, String usuarioModificacion) throws WorkflowExcepcion {
		ShvWfWorkflow wf = new ShvWfWorkflow();
		wf.setTipoWorkflow(TipoWorkflow.TALONARIO);
		wf.setDatosModificados(datosOriginales);
		wf.setUsuarioModificacion(usuarioModificacion);
		
		return this.crearWorkflow(wf);
	}
	
	@Override
	public ShvWfWorkflow asignarGestor(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	@Override
	public ShvWfWorkflow asignarAdministrador(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow reasignarSupervisor(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow reasignarAdministrador(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow rechazarErrorRango(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow dispararAnulacionAutomatica(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow rechazarRendicion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow aceptarRendicion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow dispararAnulacionRendicion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
					throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

}
