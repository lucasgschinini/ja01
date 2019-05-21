package ar.com.telecom.shiva.test.modulos;

import java.util.Collection;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EnviarMailBoletaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.ImprimirBoletaEstadoEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.servicios.IBoletaSinValorServicio;
import ar.com.telecom.shiva.negocio.workflow.IWorkflowService;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowBoletas;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.dao.IBoletaDao;
import ar.com.telecom.shiva.persistencia.dao.IBoletaSinValorDao;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoDao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoletaSinValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvBolBoletaSinValorSimple;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaSinValorFiltro;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/context/spring-jdbc.xml" , "classpath*:/context/spring-security.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoletaTest {

	@Autowired IBoletaSinValorServicio boletaServicio;
	
	@Autowired IWorkflowService workflowService;
	@Autowired IWorkflowBoletas workflowBoletas;
	
	@Autowired IBoletaDao boletaDao;
	@Autowired IBoletaSinValorDao boletaSinValorDao;
	@Autowired IEmpresaDao empresaDao;
	@Autowired ISegmentoDao segmentoDao;
	@Autowired IAcuerdoDao acuerdoDao;
	@Autowired IMotivoDao motivoDao;
	
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void test_AInsertarBoletaSinValor() throws PersistenciaExcepcion{
		System.out.println("Insertar una boleta Sin valor ------");
		try {
			ShvBolBoletaSinValor boletaSinValor = new ShvBolBoletaSinValor();
			boletaSinValor.setIdClienteSiebel(new Long(1));  
			boletaSinValor.setIdClienteLegado(Long.valueOf(1));
			boletaSinValor.setRazonSocial("12222");
			boletaSinValor.setIdAnalista("ee");
			boletaSinValor.setIdCopropietario("22");
			boletaSinValor.setImporte(Utilidad.stringToBigDecimal("1"));
			boletaSinValor.setObservaciones("");
			boletaSinValor.setEmpresa(empresaDao.buscar("TA"));
			boletaSinValor.setSegmento(segmentoDao.buscarSegmento("OGC"));
			boletaSinValor.setAcuerdo(acuerdoDao.buscarAcuerdo("28"));
			boletaSinValor.setMotivo(motivoDao.buscarMotivo("1"));
			
			ShvBolBoleta boleta = new ShvBolBoleta();
			ShvWfWorkflow workflow = workflowBoletas.crearWorkflow("datos","Leo");
			boleta.setWorkFlow(workflow);
			boleta.setNumeroBoleta(234l);
			boleta.setImpresionEstado(ImprimirBoletaEstadoEnum.NO);
			boleta.setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.NO);
			boleta.setImpresionUsuario("usuario");
			boleta.setBoletaSinValor(boletaSinValor);
			boletaSinValor.setBoleta(boleta);
			
			ShvBolBoleta boletaNueva = boletaDao.insertarBoleta(boleta);
			
			ShvBolBoleta bol = boletaDao.buscarBoleta(boletaNueva.getIdBoleta());
			System.out.println("ver aca ----->  "+bol.getWorkFlow().getDatosModificados());
			assert(bol!=null);
			assert(bol.getWorkFlow().getDatosModificados().equals("datos"));
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Test
	public void test_buscarBoleta() throws PersistenciaExcepcion{
		System.out.println("Buscar boleta ------");
		try {
			ShvBolBoleta bol = boletaDao.buscarBoleta(new Long(1));
			assert(bol!=null);
			
			List<ShvBolBoleta> list= boletaDao.listarTodasBoletas();
			assert(list!=null);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Test
	public void test_buscarBoletasSinValor() throws PersistenciaExcepcion{
		System.out.println("Buscar boletas Sin valor ------");
		try {
			BoletaSinValorFiltro boletaFiltro = new BoletaSinValorFiltro("TA", "OGC");
			Collection<DTO> lista = boletaServicio.listar(boletaFiltro);
		
			assert(lista.size()==1);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Test
	public void test_buscarBoletasSinValorConFiltro() throws PersistenciaExcepcion{
		System.out.println("Buscar boletas Sin valor con filtro ------");
		try {
			BoletaSinValorFiltro boletaFiltro = new BoletaSinValorFiltro("TA", "OGC");
			boletaFiltro.setFechaDesde("2009-04-09 00:00:00");
			boletaFiltro.setFechaHasta("2012-12-09 00:00:00");
			boletaFiltro.setImporteDesde("1");
			boletaFiltro.setImporteHasta("200000");
			boletaFiltro.setUsuarioLogeado(new UsuarioSesion("1", "u567890", null));
			Collection<DTO> lista = boletaServicio.listar(boletaFiltro);
			
			assert(lista.size()>1);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Test
	public void test_buscarBoletaWorkflow() throws PersistenciaExcepcion{
		System.out.println("Buscar boleta Workflow ------");
		try {
			ShvWfWorkflow wf = workflowService.buscarWorkflow(new Integer(1));
			System.out.println(wf.getIdWorkflow() + " - "+ wf.getTipoWorkflow().name()  + " ==> " 
			    	   + wf.getShvWfWorkflowEstado().iterator().next().getEstado().descripcion());
			
			assert(wf!=null);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}  
	}
	
	@Test
	public void test_10CodigosClienteLegago() throws PersistenciaExcepcion{
		System.out.println("Buscar 10 Codigos Cliente Legago ------");
		try {
			Collection<ShvBolBoletaSinValorSimple> a = boletaSinValorDao.listarCodigosClienteLegado("usuario");
			System.out.println(a.toString());
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}  
	}
	
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void test_modificarBoletaWorkflow() throws PersistenciaExcepcion{
		System.out.println("Modificar boleta Workflow ------");
		try {
			BoletaSinValorFiltro boletaFiltro = new BoletaSinValorFiltro("1");
			BoletaSinValorDto boleta = (BoletaSinValorDto)boletaServicio.listar(boletaFiltro).iterator().next();
			boleta.setUsuarioModificacion("ICE561906");
			boleta.setEmail("dsfgdfs@sdfsdf.com");
			boletaServicio.modificar(boleta);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}  
	}
}