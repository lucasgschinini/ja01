package ar.com.telecom.shiva.test.workflow;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.workflow.IWorkflowService;
import ar.com.telecom.shiva.negocio.workflow.MotorWorkflow;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class WorkflowCobrosTest extends SoporteContextoSpringTest {
	
	@Autowired IWorkflowService workflowService;
	@Autowired MotorWorkflow motorWorkflow;
	@Autowired IWorkflowCobros workflowCobros;
	
	private String usuarioModificacion = "U564030";
	private String descripcion = "72562 Sap Hana (4Up) - Test de Workflow de Cobros - Prueba de Pablo";
	private String datosModificados = "[" + Constantes.DATOS_MODIFICADOS_VALOR + "] (" + descripcion  + ")";
	
	/**
	 * Se presenta el siguiente Test para las pruebas de marcas
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosMarcas() throws WorkflowExcepcion {
		
		inicio();
		
		//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);
		
		// Aplico la marca de Simulacion OK
		wf = workflowService.agregarWorkflowMarca(wf, usuarioModificacion, MarcaEnum.SIMULACION_ONLINE_FINALIZADA_CON_EXITO);
		
		//		 avanza a "En Edicion verificando Debitos" 
		wf = workflowCobros.solicitarVerificacionDebitos(wf, datosModificados, usuarioModificacion);
		
		// Aplico la marca de Simulacion OK
		wf = workflowService.agregarWorkflowMarca(wf, usuarioModificacion, MarcaEnum.SIMULACION_ONLINE_FINALIZADA_CON_EXITO);

		//		 avanza a "En Edicion"
		wf = workflowCobros.solicitarEdicion(wf, datosModificados, usuarioModificacion);
		

		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 * 		avanza a "En Edicion verificando Debitos" 
	 * 		avanza a "En Edicion"
	 * 		avanza a "Pend. Supervisor Cobranza" 
	 * 		avanza a "Cobro Rechazado" 
	 * 		avanza a "Pend. Supervisor de Cobranza" 
	 * 		avanza a "Pend. Referente Cobranza" 
	 * 		avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "Cobro en Proceso" 
	 * 		avanza a "Error en Cobro" 
	 * 		avanza a "Cobro Reeditado"
	 *  
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuitoPruebas1() throws WorkflowExcepcion {
		
		inicio();
		
		//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "En Edicion verificando Debitos" 
		wf = workflowCobros.solicitarVerificacionDebitos(wf, datosModificados, usuarioModificacion);

		//		 avanza a "En Edicion"
		wf = workflowCobros.solicitarEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pend. Supervisor Cobranza" 
		wf = workflowCobros.solicitarImputacionCobroConPedidoAutorizacion(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Rechazado" 
		wf = workflowCobros.rechazarAutorizacionSolicitadaAlSupervisor(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Pend. Supervisor de Cobranza" 
		wf = workflowCobros.solicitarImputacionCobroRechazadoConPedidoAutorizacion(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Pend. Referente Cobranza" 
		wf = workflowCobros.solicitarAutorizacionReferente(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.aprobarCobroSegunReferenteSolicitandoImputacion(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Error en Cobro" 
		wf = workflowCobros.registrarErrorCobro(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Reeditado"
		wf = workflowCobros.reeditarCobroErrorEnCobro(wf, datosModificados, usuarioModificacion);

		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 * 		avanza a "En Edicion verificando Debitos" 
	 * 		avanza a "En Edicion"
	 * 		avanza a "Pend. Supervisor Cobranza" 
	 * 		avanza a "Cobro Rechazado" 
	 * 		avanza a "Pend. Supervisor de Cobranza" 
	 * 		avanza a "Pend. Referente Cobranza" 
	 * 		avanza a "Cobro Rechazado" 
	 * 		avanza a "Pend. Supervisor de Cobranza" 
	 * 		avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "Cobro en Proceso" 
	 * 		avanza a "Error en Error en Desapropiacion"
	 * 		avanza a "Cobro en Proceso" 
	 *  
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuitoPruebas2() throws WorkflowExcepcion {
		
		inicio();
		
		//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "En Edicion verificando Debitos" 
		wf = workflowCobros.solicitarVerificacionDebitos(wf, datosModificados, usuarioModificacion);

		//		 avanza a "En Edicion"
		wf = workflowCobros.solicitarEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pend. Supervisor Cobranza" 
		wf = workflowCobros.solicitarImputacionCobroConPedidoAutorizacion(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Rechazado" 
		wf = workflowCobros.rechazarAutorizacionSolicitadaAlSupervisor(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Pend. Supervisor de Cobranza" 
		wf = workflowCobros.solicitarImputacionCobroRechazadoConPedidoAutorizacion(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Pend. Referente Cobranza" 
		wf = workflowCobros.solicitarAutorizacionReferente(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Rechazado"
		wf = workflowCobros.rechazarAutorizacionSolicitadaAlReferente(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pend. Supervisor de Cobranza" 
		wf = workflowCobros.solicitarImputacionCobroRechazadoConPedidoAutorizacion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.aprobarCobroSegunSupervisorSolicitandoImputacion(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Error en Desapropiacion" 
		wf = workflowCobros.registrarErrorDesapropiacion(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.solicitarEnviarDesapropiacion(wf, datosModificados, usuarioModificacion);
		
		fin();
	}		
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 * 		avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "Cobro en Proceso" 
	 * 		avanza a "Error en Error Apropiacion" 
	 * 		avanza a "Cobro Reeditado"
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuitoPruebas3() throws WorkflowExcepcion {
		
		inicio();
		
		//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Error en Error en Apropiacion" 
		wf = workflowCobros.registrarErrorApropiacion(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Reeditado"
		wf = workflowCobros.reeditarCobroErrorEnApropiacion(wf, datosModificados, usuarioModificacion);
		
		fin();
	}		

	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 * 		avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "Cobro en Proceso" 
	 * 		avanza a "Error en Apropiacion" 
	 * 		avanza a "Cobro Anulado"
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuitoPruebas4() throws WorkflowExcepcion {
		
		inicio();
		
		//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Error en Error en Apropiacion" 
		wf = workflowCobros.registrarErrorApropiacion(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Anulado"
		wf = workflowCobros.anularCobroErrorEnApropiacion(wf, datosModificados, usuarioModificacion);
		
		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 * 		avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "Cobro en Proceso" 
	 * 		avanza a "Error en Cobro" 
	 * 		avanza a "Cobro Anulado"
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuitoPruebas5() throws WorkflowExcepcion {
		
		inicio();
		
		//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Error en Error en Cobro" 
		wf = workflowCobros.registrarErrorCobro(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Anulado"
		wf = workflowCobros.anularCobroErrorEnCobro(wf, datosModificados, usuarioModificacion);
		
		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 * 		avanza a "Pend. Supervisor Cobranza" 
	 * 		avanza a "Cobro Rechazado" 
	 * 		avanza a "Cobro Anulado"
	 *  
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuitoPruebas6() throws WorkflowExcepcion {
		
		inicio();
		
		//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pend. Supervisor Cobranza" 
		wf = workflowCobros.solicitarImputacionCobroConPedidoAutorizacion(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Rechazado" 
		wf = workflowCobros.rechazarAutorizacionSolicitadaAlSupervisor(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Cobro Anulado"
		wf = workflowCobros.anularCobroRechazado(wf, datosModificados, usuarioModificacion);

		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 * 		avanza a "Pend. Supervisor Cobranza" 
	 * 		avanza a "Cobro Rechazado" 
	 * 		avanza a "Cobro Anulado"
	 *  
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuitoPruebas7() throws WorkflowExcepcion {
		
		inicio();
		
		//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pend. Supervisor Cobranza" 
		wf = workflowCobros.solicitarImputacionCobroConPedidoAutorizacion(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Rechazado" 
		wf = workflowCobros.rechazarAutorizacionSolicitadaAlSupervisor(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Cobro Anulado"
		wf = workflowCobros.anularCobroRechazado(wf, datosModificados, usuarioModificacion);

		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 * 		avanza a "Pend. Supervisor Cobranza" 
	 * 		avanza a "Cobro Rechazado" 
	 * 		avanza a "Pendiente de Imputar Cobro"
	 * 		avanza a "Cobro en Proceso"
	 * 		avanza a "Cobrado"
	 *  
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuitoPruebas8() throws WorkflowExcepcion {
		
		inicio();
		
		//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pend. Supervisor Cobranza" 
		wf = workflowCobros.solicitarImputacionCobroConPedidoAutorizacion(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Rechazado" 
		wf = workflowCobros.rechazarAutorizacionSolicitadaAlSupervisor(wf, datosModificados, usuarioModificacion);

		//		avanza a "En Edicion verificando Debitos"
		wf = workflowCobros.solicitarVerificacionDebitosCobroRechazado(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Rechazado" 
		wf = workflowCobros.solicitarEdicionCobroRechazado(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Pendiente de Imputar Cobro"
		wf = workflowCobros.solicitarImputacionCobroRechazado(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobrado" 
		wf = workflowCobros.cobrarCobro(wf, datosModificados, usuarioModificacion);
		fin();
	}		
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 * 		avanza a "Pend. Referente Cobranza" 
	 * 		avanza a "Cobro Rechazado" 
	 * 		avanza a "Pend. Referente Cobranza" 
	 * 		avanza a "Pendiente de Imputar Cobro"
	 * 		avanza a "Cobro en Proceso"
	 * 		avanza a "Error en Error Apropiacion" 
	 * 		avanza a "Cobro Reeditado"
	 *  
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuitoPruebas9() throws WorkflowExcepcion {
		
		inicio();
		
		//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pend. Referente Cobranza" 
		wf = workflowCobros.solicitarImputacionCobroConPedidoAutorizacionReferente(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Rechazado" 
		wf = workflowCobros.rechazarAutorizacionSolicitadaAlReferente(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pend. Referente Cobranza" 
		wf = workflowCobros.solicitarAutorizacionCobroRechazadoAlReferente(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro"
		wf = workflowCobros.aprobarCobroSegunReferenteSolicitandoImputacion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Error en Error en Apropiacion" 
		wf = workflowCobros.registrarErrorApropiacion(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Reeditado"
		wf = workflowCobros.reeditarCobroErrorEnApropiacion(wf, datosModificados, usuarioModificacion);
		
		
		fin();
	}
	

	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 *  	avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "En Proceso"
	 * 		avanza a "En Proceso de Aplicación Externa"
	 * 		avanza a "Pendiente de Confirmación Manual"
	 * 		avanza a "En Proceso de Aplicación Externa" 
	 * 		avanza a "Cobro en Proceso" 
	 *  
	 * @author u587496 Guido.Settecerze
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuito4UpPruebas1() throws WorkflowExcepcion {
		
		inicio();
		
//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "En Proceso de Aplicación Externa" 
		wf = workflowCobros.registrarCobroEnProcesoDeAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Pendiente de Confirmación Manual"
		wf = workflowCobros.registrarProcesoDeAplicacionExternaAEnPendienteDeConfirmacionManual(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "En Proceso de Aplicación Externa" 
		wf = workflowCobros.registrarPendienteConfirmacionManualAEnProcesoDeAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.registrarProcesoDeAplicacionManualAEnProceso(wf, datosModificados, usuarioModificacion);
		
		System.out.println(wf.getEstado().descripcion());
		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 *  	avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "En Proceso"
	 * 		avanza a "En Proceso de Aplicación Externa"
	 * 		avanza a "Pendiente de Confirmación Manual y en Proceso de Aplicación Externa"
	 * 		avanza a "Pendiente de Confirmación Manual"
	 *  
	 * @author u587496 Guido.Settecerze
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuito4UpPruebas2() throws WorkflowExcepcion {
		
		inicio();
		
//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "En Proceso de Aplicación Externa" 
		wf = workflowCobros.registrarCobroEnProcesoDeAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Pendiente de Confirmación Manual y en Proceso de Aplicación Externa"
		wf = workflowCobros.registrarProcesoDeAplicacionExternaAEnPendienteDeConfirmacionManualYEnProcesoAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Pendiente de Confirmación Manual"
		wf = workflowCobros.registrarPendienteConfirmacionManualEnProcesoApliExternaAEnPendienteConfirmacionManual(wf, datosModificados, usuarioModificacion);
		
		
		System.out.println(wf.getEstado().descripcion());
		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 *  	avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "En Proceso"
	 * 		avanza a "En Proceso de Aplicación Externa"
	 * 		avanza a "Pendiente de Confirmación Manual y en Proceso de Aplicación Externa"
	 * 		avanza a "En Proceso de Aplicación Externa"
	 *  
	 * @author u587496 Guido.Settecerze
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuito4UpPruebas3() throws WorkflowExcepcion {
		
		inicio();
		
//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "En Proceso de Aplicación Externa" 
		wf = workflowCobros.registrarCobroEnProcesoDeAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Pendiente de Confirmación Manual y en Proceso de Aplicación Externa"
		wf = workflowCobros.registrarProcesoDeAplicacionExternaAEnPendienteDeConfirmacionManualYEnProcesoAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "En Proceso de Aplicación Externa"
		wf = workflowCobros.registrarPendienteConfirmacionManualEnProcesoApliExternaAEnProcesoDeAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		
		System.out.println(wf.getEstado().descripcion());
		fin();
	}
	
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 *  	avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "En Proceso"
	 * 		avanza a "Cobro Cobrado Parcialmente" 
	 *  
	 * @author u587496 Guido.Settecerze
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuito4UpPruebas4() throws WorkflowExcepcion {
		
		inicio();
		
//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Cobrado Parcialmente" 
		wf = workflowCobros.registrarCobroCobradoParcialmente(wf, datosModificados, usuarioModificacion);
		
		System.out.println(wf.getEstado().descripcion());
		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 *  	avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "En Proceso"
	 * 		avanza a "Error en Confirmación Parcial"
	 *  	avanza a "Cobro Confirmado Parcialmente con Error"
	 *  
	 * @author u587496 Guido.Settecerze
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuito4UpPruebas5() throws WorkflowExcepcion {
		
		inicio();
		
//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Error en Confirmación Parcial" 
		wf = workflowCobros.registrarCobroEnErrorConfirmacionParcial(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Confirmado Parcialmente con Error" 
		wf = workflowCobros.registrarCobroConfirmadoParcialmenteConError(wf, datosModificados, usuarioModificacion);
		
		System.out.println(wf.getEstado().descripcion());
		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 *  	avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "En Proceso"
	 * 		avanza a "En Proceso de Aplicación Externa" 
	 * 		avanza a "Cobro en Proceso"
	 *  
	 * @author u587496 Guido.Settecerze
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuito4UpPruebas6() throws WorkflowExcepcion {
		
		inicio();
		
//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);
		
		//		avanza a "En Proceso de Aplicación Externa" 
		wf = workflowCobros.registrarCobroEnProcesoDeAplicacionExterna(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.registrarProcesoDeAplicacionExternaAEnProceso(wf, datosModificados, usuarioModificacion);
		
		System.out.println(wf.getEstado().descripcion());
		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 *  	avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "En Proceso"
	 * 		avanza a "En Proceso de Aplicación Externa" 
	 * 		avanza a "Pendiente de Confirmación Manual y en Proceso de Aplicación Externa"
	 * 		avanza a "En Pendiente Confirmacion Manual En Proceso Aplicación Externa Pendiente MIC"
	 * 		avanza a "En Proceso Aplicación Externa y Pendiente Procesar MIC" 
	 * 		avanza a "En Proceso de Aplicación Externa" 
	 * 		avanza a "En Pendiente De Confirmación Manual Y En Proceso Aplicación Externa" 
	 *  
	 * @author u587496 Guido.Settecerze
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuito4UpPruebas7() throws WorkflowExcepcion {
		
		inicio();
		
//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);
		
		//		avanza a "En Proceso de Aplicación Externa" 
		wf = workflowCobros.registrarCobroEnProcesoDeAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Pendiente de Confirmación Manual y en Proceso de Aplicación Externa"
		wf = workflowCobros.registrarProcesoDeAplicacionExternaAEnPendienteDeConfirmacionManualYEnProcesoAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "En Pendiente Confirmacion Manual En Proceso Aplicación Externa Pendiente MIC"
		wf = workflowCobros.registrarPendienteConfirmacionManualEnProcesoApliExternaAEnPendienteConfirmacionManualEnProcesoApliExternaPendienteMIC(wf, datosModificados, usuarioModificacion);
		
		//		avanza a "En Proceso Aplicación Externa y Pendiente Procesar MIC" 
		wf = workflowCobros.registrarConfirmacionManualEnProcesoApliExternaYPendienteMICAEnProcesoAplicExternaYPendienteProcesarMIC(wf, datosModificados, usuarioModificacion);
		
		//		avanza a "En Proceso de Aplicación Externa" 
		wf = workflowCobros.registrarProcesoDeAplicacionExternaPendienteProcesarMICAEnProcesoDeAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		//		avanza a "En Pendiente De Confirmación Manual Y En Proceso Aplicación Externa" 
		wf = workflowCobros.registrarProcesoDeAplicacionExternaAEnPendienteDeConfirmacionManualYEnProcesoAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		System.out.println(wf.getEstado().descripcion());
		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 *  	avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "En Proceso"
	 * 		avanza a "Cobro en Error en Desapropiacion Parcial"
	 * 		avanza a "Cobro en Proceso de Aplicación Externa" 
	 *  
	 * @author u587496 Guido.Settecerze
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuito4UpPruebas8() throws WorkflowExcepcion {
		
		inicio();
		
//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro en Error en Desapropiacion Parcial" 
//		wf = workflowCobros.registrarCobroEnErrorEnDesapropiacionParcial(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro en Proceso de Aplicación Externa" 
		wf = workflowCobros.deErrorEnDesapropiacionParcialAEnProcesoDeAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		System.out.println(wf.getEstado().descripcion());
		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 *  	avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "En Proceso"
	 * 		avanza a "Cobro en Error en Desapropiacion"
	 * 		avanza a "Cobro en Proceso de Aplicación Externa" 
	 *  
	 * @author u587496 Guido.Settecerze
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuito4UpPruebas9() throws WorkflowExcepcion {
		
		inicio();
		
//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edición"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro en Error en Desapropiación" 
		wf = workflowCobros.registrarErrorDesapropiacion(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro en Proceso Aplicación Externa" 
		wf = workflowCobros.deErrorEnDesapropiacionAEnProcesoDeAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		System.out.println(wf.getEstado().descripcion());
		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 *  	avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "En Proceso"
	 * 		avanza a "Cobro en Error en Desapropiacion Parcial"
	 * 		avanza a "Cobro en Proceso de Aplicación Externa" 
	 *  
	 * @author u587496 Guido.Settecerze
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuito4UpPruebas10() throws WorkflowExcepcion {
		
		inicio();
		
//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edición"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro en Error en Desapropiación Parcial" 
//		wf = workflowCobros.registrarCobroEnErrorEnDesapropiacionParcial(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro en Proceso de Aplicación Externa" 
		wf = workflowCobros.deErrorEnDesapropiacionParcialAEnProcesoDeAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		System.out.println(wf.getEstado().descripcion());
		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 *  	avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "En Proceso"
	 * 		avanza a "En Proceso de Aplicación Externa"
	 * 		avanza a "Pendiente de Confirmación Manual y en Proceso de Aplicación Externa"
	 * 		avanza a "En Pendiente Confirmacion Manual En Proceso Aplicación Externa Pendiente MIC"
	 * 		avanza a "En Pendiente Confirmacion Manual Y En Proceso Aplicación Externa"
	 *  
	 * @author u587496 Guido.Settecerze
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuito4UpPruebas11() throws WorkflowExcepcion {
		
		inicio();
		
//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "En Proceso de Aplicación Externa" 
		wf = workflowCobros.registrarCobroEnProcesoDeAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Pendiente de Confirmación Manual y en Proceso de Aplicación Externa"
		wf = workflowCobros.registrarProcesoDeAplicacionExternaAEnPendienteDeConfirmacionManualYEnProcesoAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "En Pendiente Confirmacion Manual En Proceso Aplicación Externa Pendiente MIC"
		wf = workflowCobros.registrarPendienteConfirmacionManualEnProcesoApliExternaAEnPendienteConfirmacionManualEnProcesoApliExternaPendienteMIC(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "En Pendiente Confirmacion Manual Y En Proceso Aplicación Externa"
//		wf = workflowCobros.registrarConfirmacionManualEnProcesoApliExternaYPendienteMICAEnPendienteConfirmacionManualYEnProcesoApliExterna(wf, datosModificados, usuarioModificacion);
		
		System.out.println(wf.getEstado().descripcion());
		fin();
	}
	
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 *  	avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "En Proceso"
	 * 		avanza a "En Proceso de Aplicación Externa"
	 * 		avanza a "Pendiente de Confirmación Manual y en Proceso de Aplicación Externa"
	 * 		avanza a "En Pendiente Confirmacion Manual En Proceso Aplicación Externa Pendiente MIC"
	 * 		avanza a "En Proceso De Aplicación Externa Pendiente MIC"
	 *  
	 * @author u587496 Guido.Settecerze
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuito4UpPruebas12() throws WorkflowExcepcion {
		
		inicio();
		
//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "En Proceso de Aplicación Externa" 
		wf = workflowCobros.registrarCobroEnProcesoDeAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Pendiente de Confirmación Manual y en Proceso de Aplicación Externa"
		wf = workflowCobros.registrarProcesoDeAplicacionExternaAEnPendienteDeConfirmacionManualYEnProcesoAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "En Pendiente Confirmación Manual En Proceso Aplicación Externa Pendiente MIC"
		wf = workflowCobros.registrarPendienteConfirmacionManualEnProcesoApliExternaAEnPendienteConfirmacionManualEnProcesoApliExternaPendienteMIC(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "En Proceso De Aplicación Externa Pendiente MIC"
		wf = workflowCobros.registrarPendienteConfirmacionManualEnProcesoApliExternaPendienteMICAEnProcesoDeAplicacionExternaPendienteMIC(wf, datosModificados, usuarioModificacion);
		
		System.out.println(wf.getEstado().descripcion());
		fin();
	}
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 *  	avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "En Proceso"
	 * 		avanza a "En Proceso de Aplicación Externa" 
	 * 		avanza a "En Proceso Aplicación Externa con respuesta Pendiente MIC"
	 * 		avanza a "En Proceso De Aplicación Externa Pendiente Procesar MIC"
	 * 		avanza a "En Proceso De Aplicación Externa"
	 *  
	 * @author u587496 Guido.Settecerze
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuito4UpPrueba13() throws WorkflowExcepcion {
		
		inicio();
		
//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);
		
		//		avanza a "En Proceso de Aplicación Externa" 
		wf = workflowCobros.registrarCobroEnProcesoDeAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		//		avanza a "En Proceso Aplicación Externa con respuesta Pendiente MIC" 
		wf = workflowCobros.registrarProcesoDeAplicacionExternaAEnProcesoAplicacionExternaConRtaPendienteMIC(wf, datosModificados, usuarioModificacion);
		
		//		avanza a "En Proceso De Aplicación Externa Pendiente Procesar MIC" 
		wf = workflowCobros.registrarProcesoDeAplicacionExternaPendienteMICAEnProcesoDeAplicacionExternaPendienteProcesarMIC(wf, datosModificados, usuarioModificacion);
		
		//		avanza a "En Proceso De Aplicación Externa" 
		wf = workflowCobros.registrarProcesoDeAplicacionExternaPendienteProcesarMICAEnProcesoDeAplicacionExterna(wf, datosModificados, usuarioModificacion);
		
		System.out.println(wf.getEstado().descripcion());
		fin();
	}
	
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 * 		avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "Cobro en Proceso" 
	 * 		avanza a "Cobro Parcialmente en Error" 
	 * 		avanza a "Cobro Reeditado Parcialmente"
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuitoPruebas14() throws WorkflowExcepcion {
		
		inicio();
		
		//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Cobro Parcialmente en Error" 
		wf = workflowCobros.registrarCobroEnProcesoACobroParcialmenteEnError(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Cobro Reeditado Parcialmente" 
		wf = workflowCobros.registrarCobroParcialmenteEnErrorACobroParcialmenteReeditado(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Reeditado" 
		wf = workflowCobros.registrarCobroParcialmenteReeditadoACobroReeditado(wf, datosModificados, usuarioModificacion);

		fin();
	}	
	
	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 * 		avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "Cobro en Proceso" 
	 * 		avanza a "Cobro Parcialmente en Error" 
	 * 		avanza a "Cobro Parcialmente Imputado"
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuitoPruebas15() throws WorkflowExcepcion {
		
		inicio();
		
		//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Cobro Parcialmente en Error" 
		wf = workflowCobros.registrarCobroEnProcesoACobroParcialmenteEnError(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Cobro Parcialmente Imputado" 
		wf = workflowCobros.registrarCobroParcialmenteEnErrorACobroParcialmenteImputado(wf, datosModificados, usuarioModificacion);
		
		fin();
	}		

	/**
	 * Se presenta el siguiente Test en donde se prueba el siguiente circuito, con los siguientes estados:
	 * 		Crear un workflow de Cobros
	 * 		avanza a "En Edicion"
	 * 		avanza a "Pendiente de Imputar Cobro" 
	 * 		avanza a "Cobro en Proceso" 
	 * 		avanza a "Error en Cobro" 
	 * 		avanza a "Cobro Parcialmente Reeditado"
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowCobrosCircuitoPruebas16() throws WorkflowExcepcion {
		
		inicio();
		
		//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowCobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		//		 avanza a "En Edicion"
		wf = workflowCobros.guardarCobroEnEdicion(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Pendiente de Imputar Cobro" 
		wf = workflowCobros.solicitarImputacionCobro(wf, datosModificados, usuarioModificacion);
				
		//		 avanza a "Cobro en Proceso" 
		wf = workflowCobros.iniciarCobro(wf, datosModificados, usuarioModificacion);

		//		 avanza a "Error en Error en Cobro" 
		wf = workflowCobros.registrarErrorCobro(wf, datosModificados, usuarioModificacion);
		
		//		 avanza a "Cobro Parcialmente Reeditado"
		wf = workflowCobros.reeditarParcialmenteCobroErrorEnCobro(wf, datosModificados, usuarioModificacion);
		
		fin();
	}	
	/**
	 * 
	 */
	private void inicio() {
		
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Comienza la ejecución del Test de Workflow");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
	}

	/**
	 * 
	 */
	private void fin() {

		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Finaliza la ejecución del Test de Workflow");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
	}	
	
}
