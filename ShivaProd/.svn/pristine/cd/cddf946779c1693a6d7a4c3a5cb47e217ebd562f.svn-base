package ar.com.telecom.shiva.negocio.workflow.servicios.impl;

import java.util.HashSet;
import java.util.Set;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.workflow.WorkflowServiceImpl;
import ar.com.telecom.shiva.negocio.workflow.definicion.TipoWorkflow;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowMarca;


public class WorkflowCobrosImpl extends WorkflowServiceImpl implements IWorkflowCobros {

	@Override
	public ShvWfWorkflow crearWorkflow(String datosOriginales,
			String usuarioModificacion) throws WorkflowExcepcion {
		ShvWfWorkflow wf = new ShvWfWorkflow();
		wf.setTipoWorkflow(TipoWorkflow.COBRO);
		wf.setDatosModificados(datosOriginales);
		wf.setUsuarioModificacion(usuarioModificacion);
		
		return this.crearWorkflow(wf);
	}

	@Override
	public ShvWfWorkflow guardarCobroEnEdicion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
			
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}


	@Override
	public ShvWfWorkflow guardarCobroEnEdicionVerificacionDebitos(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}


	@Override
	public ShvWfWorkflow solicitarVerificacionDebitos(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		Set<ShvWfWorkflowMarca> marcas = this.copiarMarcas(wf.getWorkflowEstado().getWorkflowMarcas());

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);

		ShvWfWorkflow wfModificado = this.actualizarWorkflow(wf, Utilidad.getMethodName());

		return this.agregarWorkflowMarca(wfModificado, marcas);
	}

	@Override
	public ShvWfWorkflow solicitarEdicion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		Set<ShvWfWorkflowMarca> marcas = this.copiarMarcas(wf.getWorkflowEstado().getWorkflowMarcas());
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);

		ShvWfWorkflow wfModificado = this.actualizarWorkflow(wf, Utilidad.getMethodName());

		return this.agregarWorkflowMarca(wfModificado, marcas);
	}
	
	@Override
	public ShvWfWorkflow solicitarImputacionCobro(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarErrorCobro(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow registrarErrorApropiacion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
					throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow reintentarConfirmacion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
					throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow iniciarCobro(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow cobrarCobro(ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarErrorConfirmacion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarImputacionCobroConPedidoAutorizacion(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarImputacionCobroConPedidoAutorizacionReferente(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow solicitarImputacionCobroConPedidoAutorizacionSupOperacionesEspeciales(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarAutorizacionReferente(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow rechazarAutorizacionSolicitadaAlReferente(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarImputacionCobroRechazadoConPedidoAutorizacion(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow rechazarAutorizacionSolicitadaAlSupervisor(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow anularCobroRechazado(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow aprobarCobroSegunSupervisorSolicitandoImputacion(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow aprobarCobroSegunReferenteSolicitandoImputacion(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow anularCobroEnEdicion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarErrorDesapropiacion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow reeditarCobroErrorEnApropiacion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow reeditarCobroErrorEnCobro(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow reeditarCobroErrorConfirmacionParcial(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow anularCobroErrorEnApropiacion(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow anularCobroErrorEnCobro(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow aprobarCobroSegunSupervisorConMedioDePagoEnProceso(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow aprobarCobroSegunReferenteConMedioDePagoEnProceso(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow rechazarPorTiempoCobroConMedioDePagoEnProceso(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow editarCobroConMedioDePagoEnProceso(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarImputacionCobroConMedioPagoDefinitivo(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow anularCobroConMedioDePagoEnProceso(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarCobroMasivoPendienteCobrado(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	@Override
	public ShvWfWorkflow registrarCobroMasivoInicioCobrado(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow solicitarVerificacionDebitosCobroRechazado(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		Set<ShvWfWorkflowMarca> marcas = this.copiarMarcas(wf.getWorkflowEstado().getWorkflowMarcas());
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);

		ShvWfWorkflow wfModificado = this.actualizarWorkflow(wf, Utilidad.getMethodName());

		return this.agregarWorkflowMarca(wfModificado, marcas);
	}

	@Override
	public ShvWfWorkflow solicitarEdicionCobroRechazado(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		Set<ShvWfWorkflowMarca> marcas = this.copiarMarcas(wf.getWorkflowEstado().getWorkflowMarcas());
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);

		ShvWfWorkflow wfModificado = this.actualizarWorkflow(wf, Utilidad.getMethodName());

		return this.agregarWorkflowMarca(wfModificado, marcas);
	}

	@Override
	public ShvWfWorkflow solicitarImputacionCobroRechazado(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarAutorizacionCobroRechazadoAlReferente(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarEnviarDesapropiacion (
		ShvWfWorkflow wf, String datosModificados,
		String usuarioModificacion
	) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	private Set<ShvWfWorkflowMarca> copiarMarcas(Set<ShvWfWorkflowMarca> marcasEstadoActual) {
		Set<ShvWfWorkflowMarca> marcas = new HashSet<ShvWfWorkflowMarca>();
		for(ShvWfWorkflowMarca marca : marcasEstadoActual) {
			ShvWfWorkflowMarca m = new ShvWfWorkflowMarca();
			m.setFechaCreacion(marca.getFechaCreacion());
			m.setMarca(marca.getMarca());
			m.setUsuarioCreacion(marca.getUsuarioCreacion());
			marcas.add(m);
		}
		return marcas;
	}

	@Override
	public ShvWfWorkflow registrarCobroDeEnProcesoAPendienteMic(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarCobroDePendienteMicAPendienteProcesarMic(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	@Override
	public ShvWfWorkflow registrarCobroPendienteProcesarMicAEnProceso(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow aprobarCobroSegunSupervisorOperacionesEspecialesSolicitandoImputacion(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow solicitarAutorizacionCobroRechazadoAlSupervisor(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow rechazarAutorizacionSolicitadaAlSupOperacionesEspeciales(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	

	@Override
	public ShvWfWorkflow registrarCobroEnProcesoAPendienteDeConfirmacionManual(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarCobroPendienteDeConfirmacionManualAEnProceso(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	// <!-- NeedIT72562 -->
	@Override
	public ShvWfWorkflow registrarCobroEnProcesoDeAplicacionExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarCobroCobradoParcialmente(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarCobroCobradoParcialmente(ShvWfWorkflow wf,
			String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarCobroEnErrorConfirmacionParcial(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarCobroEnErrorConfirmacionParcial(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarCobroDeEnProcesoDeAplicacionExternaAErrorEnDesapropiacion(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarCobroConfirmadoParcialmenteConError(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarCobroConfirmadoParcialmenteConError(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#deErrorEnDesapropiacionParcialAEnProcesoDeAplicacionExterna(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow deErrorEnDesapropiacionParcialAEnProcesoDeAplicacionExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#deErrorEnDesapropiacionAEnProcesoDeAplicacionExterna(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow deErrorEnDesapropiacionAEnProcesoDeAplicacionExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarProcesoDeAplicacionManualAEnProceso(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarProcesoDeAplicacionManualAEnProceso(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarProcesoDeAplicacionExternaAEnProcesoAplicacionExternaConRtaPendienteMIC(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarProcesoDeAplicacionExternaAEnProcesoAplicacionExternaConRtaPendienteMIC(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarProcesoDeAplicacionExternaAEnPendienteDeConfirmacionManual(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarProcesoDeAplicacionExternaAEnPendienteDeConfirmacionManual(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarProcesoDeAplicacionExternaAEnPendienteDeConfirmacionManualYEnProcesoAplicacionExterna(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarProcesoDeAplicacionExternaAEnPendienteDeConfirmacionManualYEnProcesoAplicacionExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarProcesoDeAplicacionExternaAEnProceso(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarProcesoDeAplicacionExternaAEnProceso(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarProcesoDeAplicacionExternaPendienteMICAEnProcesoDeAplicacionExternaPendienteProcesarMIC(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarProcesoDeAplicacionExternaPendienteMICAEnProcesoDeAplicacionExternaPendienteProcesarMIC(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarProcesoDeAplicacionExternaPendienteProcesarMICAEnProcesoDeAplicacionExterna(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarProcesoDeAplicacionExternaPendienteProcesarMICAEnProcesoDeAplicacionExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarPendienteConfirmacionManualAEnProcesoDeAplicacionExterna(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarPendienteConfirmacionManualAEnProcesoDeAplicacionExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarPendienteConfirmacionManualAPendienteConfirmacionManual(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarPendienteConfirmacionManualAPendienteConfirmacionManual(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarPendienteConfirmacionManualEnProcesoApliExternaAEnPendienteConfirmacionManual(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarPendienteConfirmacionManualEnProcesoApliExternaAEnPendienteConfirmacionManual(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarPendienteConfirmacionManualAEnPendienteConfirmacionManualEnProcesoApliExterna(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarPendienteConfirmacionManualAEnPendienteConfirmacionManualEnProcesoApliExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarPendienteConfirmacionManualEnProcesoApliExternaAEnPendienteConfirmacionManualEnProcesoApliExternaPendienteMIC(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarPendienteConfirmacionManualEnProcesoApliExternaAEnPendienteConfirmacionManualEnProcesoApliExternaPendienteMIC(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarPendienteConfirmacionManualEnProcesoApliExternaAEnProcesoDeAplicacionExterna(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarPendienteConfirmacionManualEnProcesoApliExternaAEnProcesoDeAplicacionExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarConfirmacionManualEnProcesoApliExternaYPendienteProcesarMICAEnProcesoApliExternaYPendienteProcesarMIC(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarConfirmacionManualEnProcesoApliExternaYPendienteProcesarMICAEnProcesoApliExternaYPendienteProcesarMIC(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarConfirmacionManualEnProcesoApliExternaYPendienteProcesarMICAConfirmacionManualEnProcesoApliExternaYPendienteProcesarMIC(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarConfirmacionManualEnProcesoApliExternaYPendienteProcesarMICAConfirmacionManualEnProcesoApliExternaYPendienteProcesarMIC(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarPendienteConfirmacionManualEnProcesoApliExternaAPendienteConfirmacionManualEnProcesoDeAplicacionExterna(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarPendienteConfirmacionManualEnProcesoApliExternaAPendienteConfirmacionManualEnProcesoDeAplicacionExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarPendienteConfirmacionManualEnProcesoApliExternaPendienteMICAEnProcesoDeAplicacionExternaPendienteMIC(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarPendienteConfirmacionManualEnProcesoApliExternaPendienteMICAEnProcesoDeAplicacionExternaPendienteMIC(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	
	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarPendienteConfirmacionManualEnProcesoApliExternaPendienteMICAPendienteConfirmacionManualEnProcesoDeAplicacionExternaPendienteMIC(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarPendienteConfirmacionManualEnProcesoApliExternaPendienteMICAPendienteConfirmacionManualEnProcesoDeAplicacionExternaPendienteMIC(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}


	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarPendienteConfirmacionManualEnProcesoApliExternaPendienteMICAEnPendienteConfirmacionManualEnProcesoApliExternaPendienteProcesarMIC(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarPendienteConfirmacionManualEnProcesoApliExternaPendienteMICAEnPendienteConfirmacionManualEnProcesoApliExternaPendienteProcesarMIC(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarConfirmacionManualEnProcesoApliExternaYPendienteMICAEnPendienteConfirmacionManualYEnProcesoApliExterna(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarConfirmacionManualEnProcesoApliExternaYPendienteProcesarMICAEnPendienteConfirmacionManualYEnProcesoApliExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarConfirmacionManualEnProcesoApliExternaYPendienteMICAEnProcesoAplicExternaYPendienteProcesarMIC(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarConfirmacionManualEnProcesoApliExternaYPendienteMICAEnProcesoAplicExternaYPendienteProcesarMIC(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarProcesoAplicacionExternaYPendienteProcesarMICAEnProcesoAplicacionExterna(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarProcesoAplicacionExternaYPendienteProcesarMICAEnProcesoAplicacionExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarErrorConfirmacionParcialAEnConfrimadoParcialmenteConError(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarErrorConfirmacionParcialAEnConfrimadoParcialmenteConError(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarErrorDesapropiacionParcialAENProcesoAplicacionExterna(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarErrorDesapropiacionParcialAENProcesoAplicacionExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarErrorDesapropiacionAEnProcesoAplicacionExterna(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarErrorDesapropiacionAEnProcesoAplicacionExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarErrorDesapropiacionAEnCobroProceso(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarErrorDesapropiacionAEnCobroProceso(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	/*
	 * (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarEnProcesoACobroPendienteDesapropiacionManualExterna(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarEnProcesoACobroPendienteDesapropiacionManualExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
	/*
	 * (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros#registrarCobroPendienteDesapropiacionManualExternaEnProceso(ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow, java.lang.String, java.lang.String)
	 */
	@Override
	public ShvWfWorkflow registrarCobroPendienteDesapropiacionManualExternaEnProceso(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarCobroEnProcesoACobroParcialmenteEnError(
			ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarCobroParcialmenteEnErrorACobroParcialmenteImputado(
			ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {
		
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarCobroParcialmenteEnErrorACobroParcialmenteReeditado(
			ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarCobroParcialmenteReeditadoACobroReeditado(
			ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow reeditarParcialmenteCobroErrorEnCobro(
			ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion {

		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarCobroDePendienteConfirmacionManualYEnProcesoDeAplicacionExternaAErrorEnDesapropiacion(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarCobroDeErrorEnDesapropiacionAEnProcesoDeAplicacionExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarCobroDeEnErrorEnDesapropiacionAEnProceso(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarCobroDeErrorEnDesapropiacionAPendienteConfirmacionManualYEnProcesoDeAplicacionExterna(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}

	@Override
	public ShvWfWorkflow registrarErrorDesapropiacionAErrorEnDesapropiacion(
			ShvWfWorkflow wf, String datosModificados,
			String usuarioModificacion) throws WorkflowExcepcion {
		wf.setDatosModificados(datosModificados);
		wf.setUsuarioModificacion(usuarioModificacion);
		return this.actualizarWorkflow(wf, Utilidad.getMethodName());
	}
}
