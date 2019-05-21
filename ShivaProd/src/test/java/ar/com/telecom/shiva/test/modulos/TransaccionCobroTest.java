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
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.servicios.ITransaccionCobroServicio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransaccionCobroTest {

	@Autowired
	private ITransaccionCobroServicio transaccionCobroServicio;
	
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
	public void testBuscarIdTransaccion() throws NegocioExcepcion{
		System.out.println(transaccionCobroServicio.buscarIdTransaccion(new Long(1), 1));
	}

	@Test
	public void testBuscarNumeroTransaccion() throws NegocioExcepcion{
		System.out.println(transaccionCobroServicio.buscarNumeroTransaccion(new Long(1), 1));
	}

	
	
	
	public ITransaccionCobroServicio getTransaccionCobroServicio() {
		return transaccionCobroServicio;
	}

	public void setTransaccionCobroServicio(
			ITransaccionCobroServicio transaccionCobroServicio) {
		this.transaccionCobroServicio = transaccionCobroServicio;
	}
	

}
