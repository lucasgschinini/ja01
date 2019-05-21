package ar.com.telecom.shiva.test.workflow;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.workflow.IWorkflowService;
import ar.com.telecom.shiva.negocio.workflow.MotorWorkflow;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowLegajosChequesRechazados;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class WorkflowLegajosChequesRechazadosTest extends SoporteContextoSpringTest {
	
	@Autowired IWorkflowService workflowService;
	@Autowired MotorWorkflow motorWorkflow;
	@Autowired IWorkflowLegajosChequesRechazados workflowLegajosChequesRechazados;
	
	private String usuarioModificacion = "SHV564030";
	private String descripcion = "Test de Workflow de Legajos de Cheques Rechazados - Prueba de Pablo";
	private String datosModificados = "[" + Constantes.DATOS_MODIFICADOS_VALOR + "] (" + descripcion  + ")";
	
	/**
	 * Se presenta el siguiente Test Case 0001 en donde se prueba el circuito con los siguientes estados:
	 * 
	 * 		Transición																						Estado esperado
	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *		Inicio WF Legajos																				No Disponible
	 *		Solicitar Apertura de Legajo																	Abierto
	 * 		Solicitar Cierre con Cheque Rechazado Pendiente de Entrega para Legajo Abierto					Cerrado / Cheque Rechazado Pendiente de Entrega
	 * 		Solicitar Entrega de Cheque para Legajo Cerrado													Cerrado / Cheque Rechazado Entregado
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowLegajosChequesRechazadosCase0001() throws WorkflowExcepcion {

		inicio();
		
		// Crear un workflow de Legajos de Cheques Rechazados
		ShvWfWorkflow wf = workflowLegajosChequesRechazados.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
		// avanza a "Abierto"
		wf = workflowLegajosChequesRechazados.solicitarAperturaDeLegajo(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Cerrado / Cheque Rechazado Pendiente de Entrega"
		wf = workflowLegajosChequesRechazados.solicitarCierreConChequeRechazadoPendienteDeEntregaParaLegajoAbierto(wf, datosModificados, usuarioModificacion);

		// avanza a "Cerrado / Cheque Rechazado Entregado"
		wf = workflowLegajosChequesRechazados.solicitarEntregaDeChequeRechazadoParaLegajoCerrado(wf, datosModificados, usuarioModificacion);

		fin();
	}

	/**
	 * Se presenta el siguiente Test Case 0002 en donde se prueba el circuito con los siguientes estados:
	 * 
	 * 		Transición																						Estado esperado
	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *		Inicio WF Legajos																				No Disponible
     *      Solicitar Apertura de Legajo							                                        Abierto
     *      Solicitar Entrega de Cheque para Legajo Abierto			                                        Abierto / Cheque Rechazado Entregado
     *      Solicitar Cierre de Legajo para Cheque Entregado		                                        Cerrado / Cheque Rechazado Entregado
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowLegajosChequesRechazadosCase0002() throws WorkflowExcepcion {

		inicio();
		
		// Crear un workflow de Legajos de Cheques Rechazados
		ShvWfWorkflow wf = workflowLegajosChequesRechazados.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
		// avanza a "Abierto"
		wf = workflowLegajosChequesRechazados.solicitarAperturaDeLegajo(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Abierto / Cheque Rechazado Entregado"
		wf = workflowLegajosChequesRechazados.solicitarEntregaDeChequeRechazadoParaLegajoAbierto(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Cerrado / Cheque Rechazado Entregado"
		wf = workflowLegajosChequesRechazados.solicitarCierreDeLegajoParaChequeRechazadoEntregado(wf, datosModificados, usuarioModificacion);

		fin();
	}

	/**
	 * Se presenta el siguiente Test Case 0003 en donde se prueba el circuito con los siguientes estados:
	 * 
	 * 		Transición																						Estado esperado
	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *		Inicio WF Legajos																				No Disponible
     *      Solicitar Apertura de Legajo					                                                Abierto
     *      Solicitar Envío a Legales para Legajo Abierto	                                                Enviado a Legales
     *      Solicitar Desistir								                                                Desistido
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowLegajosChequesRechazadosCase0003() throws WorkflowExcepcion {

		inicio();
		
		// Crear un workflow de Legajos de Cheques Rechazados
		ShvWfWorkflow wf = workflowLegajosChequesRechazados.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
		// avanza a "Abierto"
		wf = workflowLegajosChequesRechazados.solicitarAperturaDeLegajo(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Enviado a Legales"
		wf = workflowLegajosChequesRechazados.solicitarEnvioALegalesParaLegajoAbierto(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Desistido"
		wf = workflowLegajosChequesRechazados.solicitarDesistir(wf, datosModificados, usuarioModificacion);

		fin();
	}

	/**
	 * Se presenta el siguiente Test Case 0004 en donde se prueba el circuito con los siguientes estados:
	 * 
	 * 		Transición																						Estado esperado
	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *		Inicio WF Legajos																				No Disponible
     *      Solicitar Apertura de Legajo																	Abierto
     *      Anular Legajo Abierto																			Anulado
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowLegajosChequesRechazadosCase0004() throws WorkflowExcepcion {

		inicio();
		
		// Crear un workflow de Legajos de Cheques Rechazados
		ShvWfWorkflow wf = workflowLegajosChequesRechazados.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
		// avanza a "Abierto"
		wf = workflowLegajosChequesRechazados.solicitarAperturaDeLegajo(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Anulado"
		wf = workflowLegajosChequesRechazados.anularLegajoAbierto(wf, datosModificados, usuarioModificacion);

		fin();
	}

	/**
	 * Se presenta el siguiente Test Case 0005 en donde se prueba el circuito con los siguientes estados:
	 * 
	 * 		Transición																						Estado esperado
	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *		Inicio WF Legajos																				No Disponible
     *      Solicitar Apertura de Legajo																	Abierto
     *      Solicitar Entrega del Cheque Rechazado															Reembolsado / Cheque Rechazado Pendiente de Entrega
     *      Solicitar Cierre con Cheque Rechazado Pendiente de Entrega para Legajo Reembolsado				Cerrado / Cheque Rechazado Pendiente de Entrega
     *      Solicitar Entrega de Cheque para Legajo Cerrado													Cerrado / Cheque Rechazado Entregado
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowLegajosChequesRechazadosCase0005() throws WorkflowExcepcion {

		inicio();
		
		// Crear un workflow de Legajos de Cheques Rechazados
		ShvWfWorkflow wf = workflowLegajosChequesRechazados.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
		// avanza a "Abierto"
		wf = workflowLegajosChequesRechazados.solicitarAperturaDeLegajo(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Reembolsado / Cheque Rechazado Pendiente de Entrega"
		wf = workflowLegajosChequesRechazados.solicitarOmitirProcesoDeReembolsoParaLegajoConChequeRechazadoAbierto(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Cerrado / Cheque Rechazado Pendiente de Entrega"
		wf = workflowLegajosChequesRechazados.solicitarCierreConChequeRechazadoPendienteDeEntregaParaLegajoReembolsado(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Cerrado / Cheque Rechazado Entregado"
		wf = workflowLegajosChequesRechazados.solicitarEntregaDeChequeRechazadoParaLegajoCerrado(wf, datosModificados, usuarioModificacion);

		fin();
	}

	/**
	 * Se presenta el siguiente Test Case 0006 en donde se prueba el circuito con los siguientes estados:
	 * 
	 * 		Transición																						Estado esperado
	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *		Inicio WF Legajos																				No Disponible
     *      Solicitar Apertura de Legajo																	Abierto
     *      Solicitar Entrega del Cheque Rechazado															Reembolsado / Cheque Rechazado Pendiente de Entrega
     *      Solicitar Entrega de Cheque para Legajo Reembolsado												Reembolsado / Cheque Rechazado Entregado
     *      Solicitar Cierre para Legajo con Cheque Rechazado Entregado										Cerrado / Cheque Rechazado Entregado
     *      
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowLegajosChequesRechazadosCase0006() throws WorkflowExcepcion {

		inicio();
		
		// Crear un workflow de Legajos de Cheques Rechazados
		ShvWfWorkflow wf = workflowLegajosChequesRechazados.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
		// avanza a "Abierto"
		wf = workflowLegajosChequesRechazados.solicitarAperturaDeLegajo(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Reembolsado / Cheque Rechazado Pendiente de Entrega"
		wf = workflowLegajosChequesRechazados.solicitarOmitirProcesoDeReembolsoParaLegajoConChequeRechazadoAbierto(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Reembolsado / Cheque Rechazado Entregado"
		wf = workflowLegajosChequesRechazados.solicitarEntregaDeChequeRechazadoParaLegajoReembolsado(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Cerrado / Cheque Rechazado Entregado"
		wf = workflowLegajosChequesRechazados.solicitarCierreParaLegajoConChequeRechazadoEntregado(wf, datosModificados, usuarioModificacion);

		fin();
	}

	/**
	 * Se presenta el siguiente Test Case 0007 en donde se prueba el circuito con los siguientes estados:
	 * 
	 * 		Transición																						Estado esperado
	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *		Inicio WF Legajos																				No Disponible
     *      Solicitar Apertura de Legajo																	Abierto
     *      Solicitar Reversión de cobros relacionados														En Proceso de Reversión de Cobros Relacionados
     *      Solicitar Inicio Proceso de Reembolso															En Proceso de Reembolso / Cheque Pendiente de Entrega
     *      Solicitar Finalizar Proceso de Reembolso para Legajo con Cheque Rechazado Pendiente de Entrega	Reembolsado / Cheque Rechazado Pendiente de Entrega
     *      Solicitar Cierre con Cheque Rechazado Pendiente de Entrega para Legajo Reembolsado				Cerrado / Cheque Rechazado Pendiente de Entrega
     *      Solicitar Entrega de Cheque para Legajo Cerrado													Cerrado / Cheque Rechazado Entregado
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowLegajosChequesRechazadosCase0007() throws WorkflowExcepcion {

		inicio();
		
		// Crear un workflow de Legajos de Cheques Rechazados
		ShvWfWorkflow wf = workflowLegajosChequesRechazados.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
		// avanza a "Abierto"
		wf = workflowLegajosChequesRechazados.solicitarAperturaDeLegajo(wf, datosModificados, usuarioModificacion);
		
		// avanza a "En Proceso de Reversión de Cobros Relacionados"
		wf = workflowLegajosChequesRechazados.solicitarReversionDeCobrosrelacionados(wf, datosModificados, usuarioModificacion);
		
		// avanza a "En Proceso de Reembolso / Cheque Pendiente de Entrega"
		wf = workflowLegajosChequesRechazados.solicitarInicioProcesodeReembolso(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Reembolsado / Cheque Rechazado Pendiente de Entrega"
		wf = workflowLegajosChequesRechazados.solicitarFinalizarProcesodeReembolsoParaLegajoConChequeRechazadoPendienteDeEntrega(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Cerrado / Cheque Rechazado Pendiente de Entrega"
		wf = workflowLegajosChequesRechazados.solicitarCierreConChequeRechazadoPendienteDeEntregaParaLegajoReembolsado(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Cerrado / Cheque Rechazado Entregado"
		wf = workflowLegajosChequesRechazados.solicitarEntregaDeChequeRechazadoParaLegajoCerrado(wf, datosModificados, usuarioModificacion);
		
		fin();
	}

	/**
	 * Se presenta el siguiente Test Case 0008 en donde se prueba el circuito con los siguientes estados:
	 * 
	 * 		Transición																						Estado esperado
	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *		Inicio WF Legajos																				No Disponible
     *      Solicitar Apertura de Legajo																	Abierto
     *      Solicitar Reversión de cobros relacionados														En Proceso de Reversión de Cobros Relacionados
     *      Solicitar Inicio Proceso de Reembolso															En Proceso de Reembolso / Cheque Pendiente de Entrega
     *      Solicitar Finalizar Proceso de Reembolso para Legajo con Cheque Rechazado Pendiente de Entrega	Reembolsado / Cheque Rechazado Pendiente de Entrega
     *      Solicitar Entrega de Cheque para Legajo Reembolsado												Reembolsado / Cheque Rechazado Entregado
     *      Solicitar Cierre para Legajo con Cheque Rechazado Entregado										Cerrado / Cheque Rechazado Entregado
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowLegajosChequesRechazadosCase0008() throws WorkflowExcepcion {

		inicio();
		
		// Crear un workflow de Legajos de Cheques Rechazados
		ShvWfWorkflow wf = workflowLegajosChequesRechazados.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
		// avanza a "Abierto"
		wf = workflowLegajosChequesRechazados.solicitarAperturaDeLegajo(wf, datosModificados, usuarioModificacion);
		
		// avanza a "En Proceso de Reversión de Cobros Relacionados"
		wf = workflowLegajosChequesRechazados.solicitarReversionDeCobrosrelacionados(wf, datosModificados, usuarioModificacion);
		
		// avanza a "En Proceso de Reembolso / Cheque Pendiente de Entrega"
		wf = workflowLegajosChequesRechazados.solicitarInicioProcesodeReembolso(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Reembolsado / Cheque Rechazado Pendiente de Entrega"
		wf = workflowLegajosChequesRechazados.solicitarFinalizarProcesodeReembolsoParaLegajoConChequeRechazadoPendienteDeEntrega(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Reembolsado / Cheque Rechazado Entregado"
		wf = workflowLegajosChequesRechazados.solicitarEntregaDeChequeRechazadoParaLegajoReembolsado(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Cerrado / Cheque Rechazado Entregado"
		wf = workflowLegajosChequesRechazados.solicitarCierreParaLegajoConChequeRechazadoEntregado(wf, datosModificados, usuarioModificacion);

		fin();
	}

	/**
	 * Se presenta el siguiente Test Case 0009 en donde se prueba el circuito con los siguientes estados:
	 * 
	 * 		Transición																						Estado esperado
	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *		Inicio WF Legajos																				No Disponible
     *      Solicitar Apertura de Legajo																	Abierto
     *      Solicitar Reversión de cobros relacionados														En Proceso de Reversión de Cobros Relacionados
     *      Solicitar Inicio Proceso de Reembolso															En Proceso de Reembolso / Cheque Pendiente de Entrega
     *      Solicitar Entrega de Cheque para Legajo en Proceso de Reembolso									En proceso de Reembolso / Cheque Rechazado Entregado
     *      Solicitar Finalizar Proceso de Reembolso para Legajo con Cheque Rechazado Entregado				Reembolsado / Cheque Rechazado Entregado
     *      Solicitar Cierre para Legajo con Cheque Rechazado Entregado										Cerrado / Cheque Rechazado Entregado
     *      
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowLegajosChequesRechazadosCase0009() throws WorkflowExcepcion {

		inicio();
		
		// Crear un workflow de Legajos de Cheques Rechazados
		ShvWfWorkflow wf = workflowLegajosChequesRechazados.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
		// avanza a "Abierto"
		wf = workflowLegajosChequesRechazados.solicitarAperturaDeLegajo(wf, datosModificados, usuarioModificacion);
		
		// avanza a "En Proceso de Reversión de Cobros Relacionados"
		wf = workflowLegajosChequesRechazados.solicitarReversionDeCobrosrelacionados(wf, datosModificados, usuarioModificacion);
		
		// avanza a "En Proceso de Reembolso / Cheque Pendiente de Entrega"
		wf = workflowLegajosChequesRechazados.solicitarInicioProcesodeReembolso(wf, datosModificados, usuarioModificacion);
		
		// avanza a "En proceso de Reembolso / Cheque Rechazado Entregado"
		wf = workflowLegajosChequesRechazados.solicitarEntregaDeChequeRechazadoParaLegajoEnProcesoDeReembolso(wf, datosModificados, usuarioModificacion);
				
		// avanza a "Reembolsado / Cheque Rechazado Entregado"
		wf = workflowLegajosChequesRechazados.solicitarFinalizarProcesoDeReembolsoParaLegajoConChequeRechazadoEntregado(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Cerrado / Cheque Rechazado Entregado"
		wf = workflowLegajosChequesRechazados.solicitarCierreParaLegajoConChequeRechazadoEntregado(wf, datosModificados, usuarioModificacion);
		
		fin();
	}

	/**
	 * Se presenta el siguiente Test Case 0010 en donde se prueba el circuito con los siguientes estados:
	 * 
	 * 		Transición																						Estado esperado
	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *		Inicio WF Legajos																				No Disponible
     *      Solicitar Apertura de Legajo																	Abierto
     *      Solicitar Reversión de cobros relacionados														En Proceso de Reversión de Cobros Relacionados
     *      Anular Legajo en Procedo de Reversión															Anulado
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowLegajosChequesRechazadosCase0010() throws WorkflowExcepcion {

		inicio();
		
		// Crear un workflow de Legajos de Cheques Rechazados
		ShvWfWorkflow wf = workflowLegajosChequesRechazados.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
		// avanza a "Abierto"
		wf = workflowLegajosChequesRechazados.solicitarAperturaDeLegajo(wf, datosModificados, usuarioModificacion);
		
		// avanza a "En Proceso de Reversión de Cobros Relacionados"
		wf = workflowLegajosChequesRechazados.solicitarReversionDeCobrosrelacionados(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Anulado"
		wf = workflowLegajosChequesRechazados.anularLegajoEnProcesoDeReversion(wf, datosModificados, usuarioModificacion);

		fin();
	}

	/**
	 * Se presenta el siguiente Test Case 0011 en donde se prueba el circuito con los siguientes estados:
	 * 
	 * 		Transición																						Estado esperado
	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *		Inicio WF Legajos																				No Disponible
	 *		Solicitar Apertura de Legajo																	Abierto
	 * 		Anular Legajo Abierto																			Anulado
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowLegajosChequesRechazadosCase0011() throws WorkflowExcepcion {
		
		inicio();
		
		// Crear un workflow de Legajos de Cheques Rechazados
		ShvWfWorkflow wf = workflowLegajosChequesRechazados.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());

		// avanza a "Abierto"
		wf = workflowLegajosChequesRechazados.solicitarAperturaDeLegajo(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Anulado"
		wf = workflowLegajosChequesRechazados.anularLegajoAbierto(wf, datosModificados, usuarioModificacion);
		
		fin();
	}
	
	/**
	 * Se presenta el siguiente Test Case 0012 en donde se prueba el circuito con los siguientes estados:
	 * 
	 * 		Transición																						Estado esperado
	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *		Inicio WF Legajos																				No Disponible
     *      Solicitar Apertura de Legajo																	Abierto
     *      Solicitar Reversión de cobros relacionados														En Proceso de Reversión de Cobros Relacionados
     *      Solicitar Inicio Proceso de Reembolso															En Proceso de Reembolso / Cheque Pendiente de Entrega
     *      Solicitar Envío a Legales para Legajo en Proceso de Reembolso									Enviado a Legales
     *      Solicitar Desistir																				Desistido
	 * 
	 * @throws WorkflowExcepcion
	 */

	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowLegajosChequesRechazadosCase0012() throws WorkflowExcepcion {

		inicio();
		
		// Crear un workflow de Legajos de Cheques Rechazados
		ShvWfWorkflow wf = workflowLegajosChequesRechazados.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
		// avanza a "Abierto"
		wf = workflowLegajosChequesRechazados.solicitarAperturaDeLegajo(wf, datosModificados, usuarioModificacion);
		
		// avanza a "En Proceso de Reversión de Cobros Relacionados"
		wf = workflowLegajosChequesRechazados.solicitarReversionDeCobrosrelacionados(wf, datosModificados, usuarioModificacion);

		// avanza a "En Proceso de Reembolso / Cheque Pendiente de Entrega"
		wf = workflowLegajosChequesRechazados.solicitarInicioProcesodeReembolso(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Enviado a Legales"
		wf = workflowLegajosChequesRechazados.solicitarEnvioALegalesParaLegajoEnProcesoDeReembolso(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Desistido"
		wf = workflowLegajosChequesRechazados.solicitarDesistir(wf, datosModificados, usuarioModificacion);
		
		fin();
	}
	
	/**
	 * Se presenta el siguiente Test Case 0007 en donde se prueba el circuito con los siguientes estados:
	 * 
	 * 		Transición																						Estado esperado
	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *		Inicio WF Legajos																				No Disponible
     *      Solicitar Apertura de Legajo																	Abierto
     *      Solicitar Reversión de cobros relacionados														En Proceso de Reversión de Cobros Relacionados
     *      Solicitar Omitir Proceso de Reembolso para Legajo con Cheque Rechazado Pendiente de Entrega		Reembolsado / Cheque Rechazado Pendiente de Entrega
     *      Solicitar Cierre con Cheque Rechazado Pendiente de Entrega para Legajo Reembolsado				Cerrado / Cheque Rechazado Pendiente de Entrega
     *      Solicitar Entrega de Cheque para Legajo Cerrado													Cerrado / Cheque Rechazado Entregado
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowLegajosChequesRechazadosCase0013() throws WorkflowExcepcion {

		inicio();
		
		// Crear un workflow de Legajos de Cheques Rechazados
		ShvWfWorkflow wf = workflowLegajosChequesRechazados.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
		// avanza a "Abierto"
		wf = workflowLegajosChequesRechazados.solicitarAperturaDeLegajo(wf, datosModificados, usuarioModificacion);
		
		// avanza a "En Proceso de Reversión de Cobros Relacionados"
		wf = workflowLegajosChequesRechazados.solicitarReversionDeCobrosrelacionados(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Reembolsado / Cheque Rechazado Pendiente de Entrega"
		wf = workflowLegajosChequesRechazados.solicitarOmitirProcesoDeReembolsoParaLegajoConChequeRechazadoPendienteDeEntrega(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Cerrado / Cheque Rechazado Pendiente de Entrega"
		wf = workflowLegajosChequesRechazados.solicitarCierreConChequeRechazadoPendienteDeEntregaParaLegajoReembolsado(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Cerrado / Cheque Rechazado Entregado"
		wf = workflowLegajosChequesRechazados.solicitarEntregaDeChequeRechazadoParaLegajoCerrado(wf, datosModificados, usuarioModificacion);
		
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
