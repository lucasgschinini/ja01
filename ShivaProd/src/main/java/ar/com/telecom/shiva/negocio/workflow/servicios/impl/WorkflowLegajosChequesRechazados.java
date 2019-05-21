package ar.com.telecom.shiva.negocio.workflow.servicios.impl;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.workflow.WorkflowServiceImpl;
import ar.com.telecom.shiva.negocio.workflow.definicion.TipoWorkflow;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowLegajosChequesRechazados;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

/**
 * 
 * @author u564030
 *
 */
public class WorkflowLegajosChequesRechazados extends WorkflowServiceImpl implements IWorkflowLegajosChequesRechazados {

	@Override
	public ShvWfWorkflow crearWorkflow(String datosOriginales, String usuarioModificacion) throws WorkflowExcepcion {

		ShvWfWorkflow wf = new ShvWfWorkflow();
		wf.setTipoWorkflow(TipoWorkflow.LEGAJOS_CHEQUES_RECHAZADOS);
		wf.setDatosModificados(datosOriginales);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.crearWorkflow(wf);
	}

	@Override
	public ShvWfWorkflow solicitarAperturaDeLegajo(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow anularLegajoAbierto(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarReversionDeCobrosrelacionados(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarOmitirProcesoDeReembolsoParaLegajoConChequeRechazadoAbierto(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow anularLegajoEnProcesoDeReversion(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarInicioProcesodeReembolso(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarFinalizarProcesodeReembolsoParaLegajoConChequeRechazadoPendienteDeEntrega(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarEntregaDeChequeRechazadoParaLegajoEnProcesoDeReembolso(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarEnvioALegalesParaLegajoEnProcesoDeReembolso(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarDesistir(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarCierreConChequeRechazadoPendienteDeEntregaParaLegajoReembolsado(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarEntregaDeChequeRechazadoParaLegajoCerrado(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarEntregaDeChequeRechazadoParaLegajoReembolsado(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarFinalizarProcesoDeReembolsoParaLegajoConChequeRechazadoEntregado(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarCierreParaLegajoConChequeRechazadoEntregado(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarCierreConChequeRechazadoPendienteDeEntregaParaLegajoAbierto(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarEntregaDeChequeRechazadoParaLegajoAbierto(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarCierreDeLegajoParaChequeRechazadoEntregado(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarEnvioALegalesParaLegajoAbierto(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarOmitirProcesoDeReembolsoParaLegajoConChequeRechazadoPendienteDeEntrega(
						ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
}
