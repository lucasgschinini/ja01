package ar.com.telecom.shiva.test.volumen;

import java.util.Collection;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EnviarMailBoletaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.ImprimirBoletaEstadoEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IBoletaSinValorServicio;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowBoletas;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaSinValorFiltro;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "desa_oracle_ldap")
@ContextConfiguration(locations = { "classpath*:/context/spring-jdbc.xml", "classpath*:/context/spring-security.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class VolumenBoletaTest {

	@Autowired IBoletaSinValorServicio boletaServicio;
	@Autowired IWorkflowBoletas workflowBoletas;
	
	@BeforeClass
	@SuppressWarnings("resource")
    public static void setUpClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
        DataSource testDataSource = (DataSource) context.getBean("testDataSource");
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind("java:jboss/datasources/ShivaDS", testDataSource);
        builder.activate();
    }
	
	//@ Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void test_BuscarBoletaSinValor() throws Exception{
		System.out.println("Busca la boleta Sin valor con ID 3700  ------");
		
		BoletaSinValorFiltro boletaFiltro = new BoletaSinValorFiltro("3700");
		Collection<DTO> lista = boletaServicio.listar(boletaFiltro);
		assert(lista.iterator().next()!=null);
	}
	
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void test_Insertar2200BoletaSinValor() throws Exception{
		System.out.println("Insertar 2200 boletas Sin valor ------");
		
		try {
			for(int i=0;i<2200;i++){
				BoletaSinValorDto boletaSinValorDto = new BoletaSinValorDto();
				
				boletaSinValorDto.setIdEmpresa("TA");
				boletaSinValorDto.setEmpresa("Telecom Argentina");
				boletaSinValorDto.setIdSegmento("OCO");
				boletaSinValorDto.setSegmento("Residencial");
				boletaSinValorDto.setCodCliente("1401302924");
				boletaSinValorDto.setCodClienteAgrupador("3030277701");
				boletaSinValorDto.setRazonSocialClienteAgrupador("GONPEREZ, RAMPEREZ");
				boletaSinValorDto.setEmail("");
				boletaSinValorDto.setIdAnalista("SHV564030");             //estos dos campos 
				boletaSinValorDto.setUsuarioModificacion("SHV564030");    //deben tener el mismo valor
				//boletaSinValorDto.setIdCopropietario("1");
				boletaSinValorDto.setImporte("345,00");
				//boletaSinValorDto.setIdMotivo("1");
				boletaSinValorDto.setOperacionAsociada("987");
				boletaSinValorDto.setIdOrigen("2");
				boletaSinValorDto.setOrigen("Cliente");
				boletaSinValorDto.setIdAcuerdo("29");
				boletaSinValorDto.setAcuerdo("Acuerdo 29");
				boletaSinValorDto.setIdBancoDeposito("0072");
				boletaSinValorDto.setBancoDeposito("SANTANDER RIO");
				boletaSinValorDto.setIdNroCuenta("3");
				boletaSinValorDto.setNroCuenta("3063945373800429");
				boletaSinValorDto.setObservaciones("Probando");
				boletaSinValorDto.setBoletaImpresaEstado(ImprimirBoletaEstadoEnum.NO);
				boletaSinValorDto.setBoletaEnviadaMailEstado(EnviarMailBoletaEstadoEnum.NO);
				
				Long numeroBoleta = boletaServicio.crear(boletaSinValorDto);
				
				System.out.println("ver aca ----->  "+numeroBoleta);
				
				assert(numeroBoleta!=null);
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
}