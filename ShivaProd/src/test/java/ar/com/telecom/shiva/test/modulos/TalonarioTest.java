package ar.com.telecom.shiva.test.modulos;

import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.servicios.ITalonarioServicio;
import ar.com.telecom.shiva.negocio.workflow.IWorkflowService;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowTalonarios;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.IGestorDao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.dao.ITalonarioDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalTalonario;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamGestor;
import ar.com.telecom.shiva.presentacion.bean.dto.TalonarioDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.TalonarioFiltro;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/context/spring-jdbc.xml", "classpath*:/context/spring-security.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)

public class TalonarioTest{

	@Autowired IWorkflowService workflowService;
	@Autowired IWorkflowTalonarios workflowTalonarios;
	@Autowired ITalonarioDao talonarioDao;
	@Autowired IGestorDao gestorDao;
	@Autowired IEmpresaDao empresaDao;
	@Autowired ISegmentoDao segmentoDao;
	@Autowired ITalonarioServicio talonarioServicio;
	
	
	@BeforeClass
	@SuppressWarnings("resource")
    public static void setUpClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
        DataSource testDataSource = (DataSource) context.getBean("testDataSource");
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind("java:jboss/datasources/ShivaDS", testDataSource);
        builder.activate();
    }
	
	@SuppressWarnings("unused")
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void test_AInsertarTalonario() throws Exception {
		System.out.println("Insertar un talonario ------");
		
			//creo un objeto talonario
			ShvTalTalonario talonario = new ShvTalTalonario();
			talonario.setIdTalonario(1);
			talonario.setEmpresa(empresaDao.buscar("TA"));
			talonario.setSegmento(segmentoDao.buscarSegmento("OGC"));
			
			ShvWfWorkflow workflow = workflowTalonarios.crearWorkflow("datos originales", "hernan");
			talonario.setWorkflow(workflow);
			talonario.setRangoDesde(null);
			talonario.setRangoHasta(null);
//			talonario.setUsuarioGestor("panchovilla");
				
			//inserto el talonario
			ShvTalTalonario talonarioNuevo = talonarioDao.insertarTalonario(talonario);
			
			//busco el talonario que acabo de insertar
			TalonarioFiltro filtro = new TalonarioFiltro();
			Collection<ShvTalTalonario> tal = talonarioDao.listarTalonarios(filtro);
			assert(tal!=null);

			System.out.println("LISTADO DEL TALONARIO: ");
			for (ShvTalTalonario shvTalTalonario : tal) {
				System.out.println(shvTalTalonario.getIdTalonario());
				System.out.println(shvTalTalonario.getEmpresa());
				System.out.println(shvTalTalonario.getSegmento());
			}
	}
	
	@Test
	public void testComboEstados(){
		List<Estado> lista = Estado.listarEstados("tal");
		for(Estado e: lista){
			System.out.println(e.name());
			System.out.println(e.descripcion());
		}
	}
	
	@Test
	public void testUsuarioGestor() throws ShivaExcepcion{
		
		String idEmpresa = "TA";
		String idSegmento = "OGC";
		try {
			List<ShvParamGestor> lista = gestorDao.listarGestoresActivos(idEmpresa, idSegmento);
			for (ShvParamGestor  g : lista) {
				System.out.println(g.getNombreYApellido());
			}
		} catch (PersistenciaExcepcion e) {
			throw new ShivaExcepcion(e);
		}
	}

	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testCrearUsuarios() throws Exception{
		TalonarioDto talDto = (TalonarioDto)talonarioServicio.buscar(Integer.valueOf("350"));
		talDto.setUsuarioGestor("Test");
		talDto.setUsuarioModificacion("Tomas");
		talonarioServicio.asignarGestor(talDto);
		
	}
}
