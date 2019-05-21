package ar.com.telecom.shiva.negocio.workflow.servicios.impl;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.workflow.WorkflowServiceImpl;
import ar.com.telecom.shiva.negocio.workflow.definicion.TipoWorkflow;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowOperacionesMasivas;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

public class WorkflowOperacionesMasivasImpl extends WorkflowServiceImpl
		implements IWorkflowOperacionesMasivas {

	@Override
	public ShvWfWorkflow crearWorkflow(String datosOriginales,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		ShvWfWorkflow wf = new ShvWfWorkflow();
		wf.setTipoWorkflow(TipoWorkflow.OPERACION_MASIVA);
		wf.setDatosModificados(datosOriginales);
		wf.setUsuarioModificacion(usuarioModificacion);
		
		return this.crearWorkflow(wf);
	}

	@Override
	public ShvWfWorkflow registrarOperacionMasivaEnProcesoDeImputacion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow procesarOperacionMasiva(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarOperacionMasivaEnError(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarOperacionMasivaProcesada(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarOperacionMasivaProcesadaParcialmente(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarAprobacionDeAltaDeOperacionMasiva(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow aprobarAltaDeOperacionMasiva(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow rechazarAltaDeOperacionMasiva(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarReaprobacionAltaDeOperacionMasiva(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow cancelarOperacionMasivaPendienteDeProcesar(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarOperacionMasivaPedidoAutorizacionReferente(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow anularOperacionMasivaRechazada(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow anularOperacionMasivaEnError(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow solicitarReaprobacionDeOperacionMasivaEnError(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow regresarOperacionMasivaRechazadaAPendienteProcesar(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow regresarOperacionMasivaEnErrorAPendienteProcesar(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
}
