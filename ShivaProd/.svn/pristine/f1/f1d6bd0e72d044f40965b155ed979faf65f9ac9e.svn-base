package ar.com.telecom.shiva.test;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.utils.singleton.ControlVariablesSingleton;

/**
 * @author u564030
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class SoporteContextoSpringTest {

	@SuppressWarnings("resource")
	@BeforeClass
	public static void setUpClass() throws Exception {
		String entorno = "desa4";
		String entorno_DS = "desa4_UsuarioZ";
		String path = "D:\\Desarrollo\\workspace\\Trunk\\src\\main\\resources";
		System.setProperty(Constantes.PROPIEDAD_ENTORNO, entorno);
		System.setProperty(Constantes.PROPIEDAD_CONFIGURACION, path + "\\shiva_"+entorno+".properties");
		
		ControlVariablesSingleton.permitirTraceoSQL();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
        DataSource testDataSource = (DataSource) context.getBean("testDataSource_"+entorno_DS);
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind("java:jboss/datasources/ShivaDS", testDataSource);
        builder.activate();
    }
	
	@Before
	public void beforeTest(){
		//Para funcionar el ldap
		if (SimpleNamingContextBuilder.getCurrentContextBuilder()!=null) {
			SimpleNamingContextBuilder.getCurrentContextBuilder().deactivate();
		}
	}
}