package ar.com.telecom.shiva.test.workflow;

import java.util.Collection;
import java.util.Iterator;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.workflow.IWorkflowService;
import ar.com.telecom.shiva.negocio.workflow.MotorWorkflow;
import ar.com.telecom.shiva.negocio.workflow.definicion.Transicion;
import ar.com.telecom.shiva.negocio.workflow.definicion.Workflow;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowBoletas;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowDescobros;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowRegistrosAVC;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowTalonarios;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowValores;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstadoHist;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "desa_local_ldap")
@ContextConfiguration(locations = { "classpath*:/context/spring-jdbc.xml" , "classpath*:/context/spring-security.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WorkflowTest {
	
	@Autowired IWorkflowService workflowService;
	@Autowired MotorWorkflow motorWorkflow;
	@Autowired IWorkflowBoletas workflowBoletas;
	@Autowired IWorkflowRegistrosAVC workflowRegistrosAVC;
	@Autowired IWorkflowCobros workflowCobros;
	@Autowired IWorkflowTalonarios workflowTalonarios;
	@Autowired IWorkflowValores workflowValores;
	@Autowired IWorkflowDescobros workflowDescobros;
	
	@Test
	public void testA_ListTransicionPorWorkflow() throws WorkflowExcepcion {
		System.out.println("Lista de Transiciones por workflow ------");
		try {
			Collection<Workflow> list = motorWorkflow.getWorkflows();
			Iterator<Workflow> it = list.iterator();
		    while(it.hasNext())
		    {
		    	Workflow o = it.next();
		    	System.out.println(o.getNombre() + " ==>");
		    	
		    	Iterator<Transicion> ti = workflowService.obtenerTransiciones(o).iterator();
			    while(ti.hasNext())
			    {
			    	Transicion t = ti.next();
		    		System.out.println(t.getEvento() 
		    				+ " - " + t.getOrigen().descripcion() + " --> " + t.getDestino().descripcion());
			    }
			    System.out.println(o.getNombre() + "<==");
		    }
		} catch (WorkflowExcepcion e) {
			System.out.println(e.getClass().toString() + ":");
			System.out.println(e.getMessage());
		}
		System.out.println("-----------------------------------------");
	}
	
	@Test
	public void testB_ValidarConfiguracionWorkflow() {
		System.out.println("-----------------------------------------");
		try {
			
			workflowService.validarConfiguracionWorkflow();
			System.out.println("Configuracion: El workflow SHIVA es valida");
		} catch (WorkflowExcepcion e) {
			System.out.println(e.getClass().toString() + ":");
			System.out.println(e.getMessage());
		}
		System.out.println("-----------------------------------------");
	}
	
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testC_workflowsEnBBDD() throws WorkflowExcepcion {
		System.out.println("Workflows en BD ------");
		try {
			this.insertarWorkflows();
			this.completarWorkflowBoleta();
			this.completarWorkflowRegistrosAVC();
			this.completarWorkflowCobros();
			this.completarWorkflowTalonarios();
			this.completarWorkflowValores();
			this.completarWorkflowDescobros();
			
			Collection<ShvWfWorkflow> list = workflowService.listarWorkflowTest();
			Iterator<ShvWfWorkflow> it = list.iterator();
		    while(it.hasNext())
		    {
		    	ShvWfWorkflow o = it.next();
		    	System.out.println(o.getIdWorkflow() + " - "+ o.getTipoWorkflow().name()  + " ==> " 
		    	   + o.getShvWfWorkflowEstado().iterator().next().getEstado().descripcion());
		    	
		    	Iterator<ShvWfWorkflowEstadoHist> itHis = o.getShvWfWorkflowEstadoHist().iterator();
			    while(itHis.hasNext())
			    {
			    	ShvWfWorkflowEstadoHist oH = itHis.next();
			    	System.out.println("Historico --> " + oH.getEstado().descripcion());
			    }
		    }
		    
		} catch (WorkflowExcepcion e) {
			
			System.out.println(e.getClass().toString() + ":");
			System.out.println(e.getMessage());
			throw e;
		}
		System.out.println("-----------------------------------------");
	}
	private void insertarWorkflows() throws WorkflowExcepcion {
		workflowBoletas.crearWorkflow("","Test");
		workflowBoletas.crearWorkflow("","Test1");
	}
	private void completarWorkflowBoleta() throws WorkflowExcepcion {
		System.out.println(Utilidad.getMethodName() + " ------");
		
		// Circuito 1
		ShvWfWorkflow workflow = workflowBoletas.crearWorkflow("","Usuario1");
		workflow = workflowBoletas.actualizarWorkflow(workflow, "Sugerida 1" ,"Usuario2");
		workflow = workflowBoletas.establecerBoletaConciliacionSugerida(workflow, "Sugerida" ,"Usuario3");
		workflow = workflowBoletas.conciliarBoletaConDiferencias(workflow, "Diferencia", "Usuario4");
		
		//Ej de error
		//workflow = workflowBoletas.solicitarAnulacion(workflow, "Diferencia", "Usuario1");
		System.out.println("-----------------------------------------");
	}
	private void completarWorkflowRegistrosAVC() throws WorkflowExcepcion {
		System.out.println(Utilidad.getMethodName() + " ------");
	    
		//Circuito 1
		ShvWfWorkflow workflow = workflowRegistrosAVC.crearWorkflow("datos originales","Usuario1");
		workflow = workflowRegistrosAVC.solicitarConfirmacionAltaValor(workflow, "Analista" ,"Usuario1");
		workflow = workflowRegistrosAVC.establecerRegistroConciliadoSinBoleta(workflow, "Sin boleta Analista", "Usuario1");
		
		System.out.println("-----------------------------------------");
	}
	private void completarWorkflowCobros() throws WorkflowExcepcion {
		System.out.println(Utilidad.getMethodName() + " ------");
		
		//Circuito 1
		ShvWfWorkflow workflow = workflowCobros.crearWorkflow("datos originales","Usuario1");
		workflow = workflowCobros.iniciarCobro(workflow, "Cobro" ,"Usuario1");
		workflow = workflowCobros.cobrarCobro(workflow, "Cobro", "Usuario1");
		
		System.out.println("-----------------------------------------");
	}
	private void completarWorkflowTalonarios() throws WorkflowExcepcion {
		System.out.println(Utilidad.getMethodName() + " ------");
		
		//Circuito 1
		ShvWfWorkflow workflow = workflowTalonarios.crearWorkflow("datos originales", "Usuario1");
		workflow = workflowTalonarios.asignarGestor(workflow, "Talonario", "Usuario1");
		workflow = workflowTalonarios.asignarAdministrador(workflow, "Talonario", "Usuario1");
		workflow = workflowTalonarios.aceptarRendicion(workflow, "Talonario", "Usuario1");
		
		System.out.println("-----------------------------------------");
	}
	private void completarWorkflowValores() throws WorkflowExcepcion {
		System.out.println(Utilidad.getMethodName() + " ------");
		
		//Circuito 1
		ShvWfWorkflow workflow = workflowValores.crearWorkflow("datos originales","Usuario1");
		workflow = workflowValores.disponibilizarValor(workflow, "valor" , "Usuario1");
		workflow = workflowValores.usarValor(workflow, "valor", "Usuario1");
		workflow = workflowValores.suspenderValorUsado(workflow, "valor", "Usuario1");
		workflow = workflowValores.anular(workflow, "valor", "Usuario1");
		
		System.out.println("-----------------------------------------");
	}
	private void completarWorkflowDescobros() throws WorkflowExcepcion {
		System.out.println(Utilidad.getMethodName() + " ------");
		
		//Circuito 1
		ShvWfWorkflow workflow = workflowDescobros.crearWorkflow("Usuario1", "");
		workflow = workflowDescobros.iniciarDescobro(workflow, "valor" , "Usuario1");
		workflow = workflowDescobros.regPendReversoDocCalipso(workflow, "valor", "Usuario1");
		workflow = workflowDescobros.procPendReversoDocCalipso(workflow, "valor", "Usuario1");
		workflow = workflowDescobros.descobrarCobro(workflow, "valor", "Usuario1");
		
		System.out.println("-----------------------------------------");
	}
}
