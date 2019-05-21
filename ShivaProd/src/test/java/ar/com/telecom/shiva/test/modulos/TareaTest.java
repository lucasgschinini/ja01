package ar.com.telecom.shiva.test.modulos;

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

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.persistencia.dao.IRegistroAVCDao;
import ar.com.telecom.shiva.persistencia.dao.ITalonarioDao;
import ar.com.telecom.shiva.persistencia.dao.IValorDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalTalonario;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/context/spring-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)

public class TareaTest{

	@Autowired 
	ITareaServicio tareaServicio;
	@Autowired 
	IValorDao valorDao;
	@Autowired 
	ITalonarioDao talonarioDao;
	@Autowired 
	IRegistroAVCDao registroAVCDao;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void setUpClass() throws Exception {
		System.setProperty(Constantes.PROPIEDAD_ENTORNO, Constantes.ENTORNO_LOCAL);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
        DataSource testDataSource = (DataSource) context.getBean("testDataSource");
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind("java:jboss/datasources/ShivaDS", testDataSource);
        builder.activate();
    }
	
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void testAsignarTalonarioAlSupervisor() throws Exception {
		System.out.println("Crear una tarea de un talonario: Asignar Talonario Al supervisor ------");
		String usuarioCreacion = "SHV566205";
		
		ShvTalTalonario talonarioModelo;
		talonarioModelo = talonarioDao.buscarTalonario(Integer.valueOf("133"));
		
		TareaDto tarea = new TareaDto();
		tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		tarea.setIdWorkflow(talonarioModelo.getWorkflow().getIdWorkflow());
		tarea.setTipoTarea(TipoTareaEnum.ASIG_GESTOR_TAL);
		tarea.setFechaCreacion(talonarioModelo.getWorkflow().getFechaUltimaModificacion());
		tarea.setUsuarioCreacion(usuarioCreacion);
		tarea.setPerfilAsignacion(Constantes.SUPERVISOR_TALONARIO);
		
		tarea.setGestionaSobre(TipoTareaGestionaEnum.TALONARIO);
		tarea.setIdTalonario(Long.valueOf(talonarioModelo.getIdTalonario()));
		
		tareaServicio.crearTareaTalonario(tarea);
	}

	public ITareaServicio getTareaServicio() {
		return tareaServicio;
	}

	public void setTareaServicio(ITareaServicio tareaServicio) {
		this.tareaServicio = tareaServicio;
	}

	public IValorDao getValorDao() {
		return valorDao;
	}

	public void setValorDao(IValorDao valorDao) {
		this.valorDao = valorDao;
	}

	public ITalonarioDao getTalonarioDao() {
		return talonarioDao;
	}

	public void setTalonarioDao(ITalonarioDao talonarioDao) {
		this.talonarioDao = talonarioDao;
	}

	public IRegistroAVCDao getRegistroAVCDao() {
		return registroAVCDao;
	}

	public void setRegistroAVCDao(IRegistroAVCDao registroAVCDao) {
		this.registroAVCDao = registroAVCDao;
	}
}
