package ar.com.telecom.shiva.negocio.workflow.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.workflow.WorkflowServiceImpl;
import ar.com.telecom.shiva.negocio.workflow.definicion.TipoWorkflow;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowRegistrosAVC;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

public class WorkflowRegistrosAVCImpl extends WorkflowServiceImpl implements IWorkflowRegistrosAVC {

	@Override
	public ShvWfWorkflow crearWorkflow(String datosOriginales, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		ShvWfWorkflow wf = new ShvWfWorkflow();
		wf.setTipoWorkflow(TipoWorkflow.REGISTRO_AVC);
		wf.setDatosModificados(datosOriginales);
		wf.setUsuarioModificacion(usuarioModificacion);
		
		return this.crearWorkflow(wf);
	}
	
	@Override
	public ShvWfWorkflow solicitarRegistroAnulacion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow confirmarAnulacion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow rechazarPedidoDeAnulacion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow establecerRegistroPendienteConciliadoSinBoleta(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
					throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	@Override
	public ShvWfWorkflow establecerRegistroConciliadoSinBoleta(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow conciliarRegistroConDiferencias(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow establecerRegistroConciliado(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow deshacerRegistroConciliacionSugerida(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow rechazarAltaValor(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarReconfirmacionAltaValor(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow establecerRegistroConciliacionSugerida(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarConfirmacionAltaValor(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow rechazarSolicitudAnulacionRegistroPendienteConfirmarValor(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarAnulacionRegistroPendienteConfirmarValor(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow solicitarAnulacionAltaValorRechazada(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/**
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowRegistrosAVC#getListaDatosOriginalesDeposito()
	 */
	@Override
	public List<String> getListaDatosOriginalesDeposito() {
		List<String> lista = new ArrayList<String>();
		
		lista.add("idRecInstrumento");
		lista.add("rend");
		lista.add("fechaPago");
		lista.add("nroCliente");
		lista.add("formaPago");
		lista.add("estadoAcreditacion");
		lista.add("fechaAcreditacion");
		lista.add("nroBoleta");
		lista.add("sucursalDeposito");
		lista.add("nombreSucursal");
		lista.add("grupoAcreedor");
		lista.add("nombreCliente");
		lista.add("codigoRechazo");
		lista.add("codigoBanco");
		lista.add("sucursal");
		lista.add("codigoPostal");
		lista.add("numeroCheque");
		lista.add("cuentaEmisora");
		lista.add("fechaVencimiento");
		
		return lista;
	}

	/**
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowRegistrosAVC#getListaDatosOriginalesInterdeposito()
	 */
	@Override
	public List<String> getListaDatosOriginalesInterdeposito() {
		List<String> lista = new ArrayList<String>();
		
		lista.add("fechaValor");
		lista.add("fechaIngreso");
		lista.add("concepto");
		lista.add("codigoOperacion");
		lista.add("deposito");
		lista.add("comprobante");
		lista.add("codigoOrganismo");
		lista.add("codigoInterdeposito");
		lista.add("sucursal");
		lista.add("codOpBanco");
		lista.add("pcc");

		return lista;		
	}

	/**
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowRegistrosAVC#getListaDatosOriginalesTransferencia()
	 */
	@Override
	public List<String> getListaDatosOriginalesTransferencia() {
		List<String> lista = new ArrayList<String>();
		
		lista.add("fechaIngreso");
		lista.add("sucursal");
		lista.add("referencia");
		lista.add("codigo");
		lista.add("observacion");
		lista.add("cuit");
		lista.add("subtotal");
		
		return lista;
	}
}
