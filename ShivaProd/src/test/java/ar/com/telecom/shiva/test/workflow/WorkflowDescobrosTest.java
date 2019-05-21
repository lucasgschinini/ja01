package ar.com.telecom.shiva.test.workflow;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowDescobros;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class WorkflowDescobrosTest extends SoporteContextoSpringTest {

	
	@Autowired IWorkflowDescobros workflowDescobros;
	
	private String usuarioModificacion = "U564030";
	private String descripcion = "Sprint 5 - Test de Workflow de Cobros - Prueba de Brian";
	private String datosModificados = "[" + Constantes.DATOS_MODIFICADOS_VALOR + "] (" + descripcion  + ")";
	
	
	/**
	 * Se presenta el siguiente Test para las pruebas de marcas
	 * 
	 * @throws WorkflowExcepcion
	 */
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowDescobrosPruebas1() throws WorkflowExcepcion {
	
	
		inicio();
		
//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowDescobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
//		 avanza a "En Edicion"
		wf = workflowDescobros.guardarDescobroEnEdicion(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());
		
//		 avanza a "Descobro Pendiente"
		wf = workflowDescobros.solicitarDescobro(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());

//		 avanza a "Descobro En Proceso"
		wf = workflowDescobros.iniciarDescobro(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());
		
//		 avanza a "Descobro En Error"
		wf = workflowDescobros.registrarDescobroEnError(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());
		
//		 avanza a "Descobro Pendiente"
		wf = workflowDescobros.reintentarDescobroEnError(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());
		
//		 avanza a "Descobro En Proceso"
		wf = workflowDescobros.iniciarDescobro(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());
		
//		 avanza a "Error En Primer Descobro"
		wf = workflowDescobros.registrarErrorEnPrimerDescobro(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());
		
//		 avanza a "Descobro Pendiente"
		wf = workflowDescobros.revertirDescobroEnErrorEnPrimerDescobro(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());
		
//		 avanza a "Descobro En Proceso"
		wf = workflowDescobros.iniciarDescobro(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());
		
//		 avanza a "Descobro Finalizado"
		wf = workflowDescobros.aplicarDescobro(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());
		
		
		fin();
	}

	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testWorkflowDescobrosPruebas2() throws WorkflowExcepcion {
		inicio();
		
//		 Crear un workflow de Cobros
		ShvWfWorkflow wf = workflowDescobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
//		 avanza a "En Edicion"
		wf = workflowDescobros.guardarDescobroEnEdicion(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());
		
//		 avanza a "Descobro Pendiente"
		wf = workflowDescobros.solicitarDescobro(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());

//		 avanza a "Descobro En Proceso"
		wf = workflowDescobros.iniciarDescobro(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());
		
//		 avanza a "Descobro En Pendiente confirmacion Manual"
		wf = workflowDescobros.registrarDescobroEnProcesoAPendienteConfirmacionManual(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());
		Assert.assertEquals(Estado.DES_PENDIENTE_CONFIRMACION_MANUAL, wf.getEstado());
		
//		 avanza a "A Finalizado"
		wf = workflowDescobros.registrarDescobroPendienteConfirmacionManualAEnProceso(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());
		
		Assert.assertEquals(Estado.DES_DESCOBRADO, wf.getEstado());
		
		wf = workflowDescobros.crearWorkflow(datosModificados, usuarioModificacion); 
		System.out.println("Se ha generado el workflow con Id: " + wf.getIdWorkflow());
		
//		 avanza a "En Edicion"
		wf = workflowDescobros.guardarDescobroEnEdicion(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());
		
//		 avanza a "Descobro Pendiente"
		wf = workflowDescobros.solicitarDescobro(wf, datosModificados, usuarioModificacion);
		System.out.println(wf.getEstado().descripcion());

//		 avanza a "Descobro En Pendiente confirmacion Manual"
		try {
			wf = workflowDescobros.registrarDescobroEnProcesoAPendienteConfirmacionManual(wf, datosModificados, usuarioModificacion);
			Assert.assertFalse(true);
		} catch (WorkflowExcepcion e) {
			System.out.println(wf.getEstado().descripcion());
			Assert.assertTrue(true);
		}
		
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
