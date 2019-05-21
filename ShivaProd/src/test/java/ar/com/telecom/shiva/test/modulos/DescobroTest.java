package ar.com.telecom.shiva.test.modulos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.negocio.workflow.IWorkflowService;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowDescobros;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.IGestorDao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class DescobroTest extends SoporteContextoSpringTest {
	
	@Autowired IDescobroDao descobroDao;
	@Autowired ICobroDao cobroDao;
	@Autowired IWorkflowService workflowService;
	@Autowired IWorkflowDescobros workflowDescobros;
	@Autowired IGestorDao gestorDao;
	@Autowired IEmpresaDao empresaDao;
	@Autowired ISegmentoDao segmentoDao;
	@Autowired IDescobroServicio descobroServicio;
	
	@Test
	public void insertarEnBase() throws Exception {
	
			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(new Long(100101));
		
			ShvCobDescobro descobro = new ShvCobDescobro();
			descobro.setOperacion(descobroServicio.inicializarOperacionDeDescobro(cobro.getOperacion()));
			ShvWfWorkflow workflowDescobro = workflowDescobros.crearWorkflow("", "SHV568815");
			workflowDescobro = workflowDescobros.guardarDescobroEnEdicion(workflowDescobro, "", "SHV568815");
			descobro.setWorkflow(workflowDescobro);
				
			//inserto el talonario
			ShvCobDescobro descobroNuevo = descobroDao.insertarDescobro(descobro);

	}
}
