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

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.persistencia.dao.ITipoMedioPagoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TipoMedioPagoTest{

	@Autowired 
	ITipoMedioPagoDao tipoMedioPagoDao;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void setUpClass() throws Exception {
		System.setProperty(Constantes.PROPIEDAD_ENTORNO, Constantes.ENTORNO_LOCAL);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
        DataSource testDataSource = (DataSource) context.getBean("testDataSource");
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind("java:jboss/datasources/Shiva2DS", testDataSource);
        builder.activate();
    }
	
	@Test
	public void test1() throws Exception {
		
		ShvParamTipoMedioPago registro;
		Modelo registroMod;
		
		registro = tipoMedioPagoDao.buscarTipo("06");
		System.out.println(registro != null ? "buscarTipo - resultado: " + 1 : "buscarMensajePorIdMensajeria - resultado: " + 0);
		
//		registro = tipoMedioPagoDao.buscar(TipoMedioPagoEnum.BOLETADEDEPOSITO);
//		System.out.println(registro != null ? "buscarMedioPago - resultado: " + 1 : "buscarMensajePorIdMensajeria - resultado: " + 0);
		
		registroMod = tipoMedioPagoDao.buscar((Object) "06");
		System.out.println(registroMod != null ? "buscar - resultado: " + 1 : "buscarMensajePorIdMensajeria - resultado: " + 0);

	}
	

}
