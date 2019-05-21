package ar.com.telecom.shiva.test.workflow;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.workflow.IWorkflowService;
import ar.com.telecom.shiva.negocio.workflow.MotorWorkflow;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowValores;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;


public class WorkflowValoresTest extends SoporteContextoSpringTest {
	
	@Autowired IWorkflowService workflowService;
	@Autowired MotorWorkflow motorWorkflow;
	@Autowired IWorkflowValores workflowValores;
	
	private String usuarioModificacion = "SHV578936";
	private String descripcion = "Test de Workflow de Valores - Prueba de u578936 Uehara";
	private String datosModificados = "[" + Constantes.DATOS_MODIFICADOS_VALOR + "] (" + descripcion  + ")";
	
	/**
	 * Se presenta el siguiente Test Case 0001 en donde se prueba el circuito con los siguientes estados:
	 * 
	 * 		Transición																						Estado esperado
	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *		Inicio WF Legajos																				No Disponible
	 *		Disponibilizar Valor																			val disponible
	 * 		Suspender Valor Disponible																		val suspendido
	 * 		usar Valor Suspendido																			val usado
	 * @throws WorkflowExcepcion
	 */
	
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowValoresCase0001() throws WorkflowExcepcion {

		this.inicio();
		
		// Crear un workflow de Legajos de Cheques Rechazados
		ShvWfWorkflow wf = workflowValores.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
		// avanza a "Abierto"
		wf = workflowValores.disponibilizarValor(wf, datosModificados, usuarioModificacion);
		
		// avanza a "Cerrado / Cheque Rechazado Pendiente de Entrega"
		wf = workflowValores.suspenderValorDisponible(wf, datosModificados, usuarioModificacion);
		System.out.println("Estado antes de la transicion: " + wf.getEstado().name());
		Assert.assertEquals(Estado.VAL_SUSPENDIDO, wf.getEstado());
		// avanza a "Cerrado / Cheque Rechazado Entregado"
		wf = workflowValores.usarValorSuspendido(wf, datosModificados, usuarioModificacion);
		System.out.println("Estado despues de la transicion: " + wf.getEstado().name());
		Assert.assertEquals(Estado.VAL_USADO, wf.getEstado());
		this.fin();
	}
	/**
	 * Se presenta el siguiente Test Case 0001 en donde se prueba el circuito con los siguientes estados:
	 * 
	 * 		Transición																						Estado esperado
	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *		Inicio WF Legajos																				No Disponible
	 *		Disponibilizar Valor																			val disponible
	 * 		usar Valor Suspendido																			val usado
	 * @throws WorkflowExcepcion
	 */
	
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowValoresCase0002() {

		this.inicio();
		try {
			// Crear un workflow de Legajos de Cheques Rechazados
			ShvWfWorkflow wf = workflowValores.crearWorkflow(datosModificados, usuarioModificacion); 
			System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
			
			// avanza a "Abierto"
			wf = workflowValores.disponibilizarValor(wf, datosModificados, usuarioModificacion);
			
			System.out.println("Estado antes de la transicion: " + wf.getEstado().name());
			// avanza a "Cerrado / Cheque Rechazado Entregado"
		
			wf = workflowValores.usarValorSuspendido(wf, datosModificados, usuarioModificacion);
			Assert.fail("No se tendria que haber ejecutado esta linea");
			System.out.println("No se tendria que llegar hasta esta linea");
		} catch (WorkflowExcepcion e) {
			Assert.assertTrue(true);
			System.out.println("Throws WorkflowExcepcion");
		}
		this.fin();
	}
//	/**
//	 * Se presenta el siguiente Test Case 0002 en donde se prueba el circuito con los siguientes estados:
//	 * 
//	 * 		Transición																						Estado esperado
//	 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//	 *		Inicio WF Legajos																				No Disponible
//     *      Solicitar Apertura de Legajo							                                        Abierto
//     *      Solicitar Entrega de Cheque para Legajo Abierto			                                        Abierto / Cheque Rechazado Entregado
//     *      Solicitar Cierre de Legajo para Cheque Entregado		                                        Cerrado / Cheque Rechazado Entregado
//	 * 
//	 * @throws WorkflowExcepcion
//	 */
//	@Test
//	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
//	public void testWorkflowLegajosChequesRechazadosCase0002() throws WorkflowExcepcion {
//
//		inicio();
//		
//		// Crear un workflow de Legajos de Cheques Rechazados
//		ShvWfWorkflow wf = workflowLegajosChequesRechazados.crearWorkflow(datosModificados, usuarioModificacion); 
//		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
//		
//		// avanza a "Abierto"
//		wf = workflowLegajosChequesRechazados.solicitarAperturaDeLegajo(wf, datosModificados, usuarioModificacion);
//		
//		// avanza a "Abierto / Cheque Rechazado Entregado"
//		wf = workflowLegajosChequesRechazados.solicitarEntregaDeChequeRechazadoParaLegajoAbierto(wf, datosModificados, usuarioModificacion);
//		
//		// avanza a "Cerrado / Cheque Rechazado Entregado"
//		wf = workflowLegajosChequesRechazados.solicitarCierreDeLegajoParaChequeRechazadoEntregado(wf, datosModificados, usuarioModificacion);
//
//		fin();
//	}

	

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
