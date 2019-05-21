package ar.com.telecom.shiva.test.soa;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSiebelConsultarClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSiebelConsultarClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-context.xml"})
public class WSSiebelTest {

	@Autowired 
	IClienteSiebelServicio clienteSiebelServicio;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void setUpClass() throws Exception {
		
		System.setProperty("entorno", "desa4");
		System.setProperty("configuracion", "D:\\workspace\\Shiva_Sprint_04\\src\\main\\resources\\shiva_desa4.properties");
		
		//System.setProperty(Constantes.PROPIEDAD_ENTORNO, "inte");
		//System.setProperty(Constantes.PROPIEDAD_CONFIGURACION, "D:/Telecom-Shiva-General/ShivaSprint4/src/main/resources/shiva_inte.properties");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
        DataSource testDataSource = (DataSource) context.getBean("testDataSource");
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind("java:jboss/datasources/ShivaDS", testDataSource);
        builder.activate();
    }
	
	@Test
	public void testConsultarClienteSiebel() throws NegocioExcepcion {
		/*
		 	2601099569
			1401302924
			1401202724
			1401022360
			1401279138
			1401227300
			1401097870
			1401303251
			2601023708
			1401251574
		*/
		
		SalidaSiebelConsultarClienteWS datos = 
				clienteSiebelServicio.consultarClienteSiebel("0000007005");
		
		System.out.println("---WS testConsultarClienteSiebel - Resultado=0000007005 --- ");
		System.out.println("resultado: " + datos);
		System.out.println("Codigo Cliente Siebel: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getCodigoClienteAgrupador()));
		System.out.println("Razon Social: " + (datos==null||datos.getCliente()==null?"":datos.getCliente().getRazonSocialClienteAgrupador()));
		System.out.println("--------------------------------------");
		
//		datos = clienteSiebelServicio.consultarClienteSiebel("1401004124");
//		System.out.println("---WS testConsultarClienteSiebel - Resultado=(No existe) 1401004124 --- ");
//		System.out.println("resultado: " + datos);
//		System.out.println("Codigo Cliente Siebel: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getCodigoClienteAgrupador()));
//		System.out.println("Razon Social: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getRazonSocialClienteAgrupador()));
//		System.out.println("--------------------------------------");
//		
//		datos = clienteSiebelServicio.consultarClienteSiebel("2601099569");
//		System.out.println("---WS testConsultarClienteSiebel - Resultado=2601099569 --- ");
//		System.out.println("resultado: " + datos);
//		System.out.println("Codigo Cliente Siebel: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getCodigoClienteAgrupador()));
//		System.out.println("Razon Social: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getRazonSocialClienteAgrupador()));
//		System.out.println("--------------------------------------");
//		
//		datos = clienteSiebelServicio.consultarClienteSiebel("1401302924");
//		System.out.println("---WS testConsultarClienteSiebel - Resultado=1401302924 --- ");
//		System.out.println("resultado: " + datos);
//		System.out.println("Codigo Cliente Siebel: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getCodigoClienteAgrupador()));
//		System.out.println("Razon Social: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getRazonSocialClienteAgrupador()));
//		System.out.println("--------------------------------------");
//		
//		datos = clienteSiebelServicio.consultarClienteSiebel("1401202724");
//		System.out.println("---WS testConsultarClienteSiebel - Resultado=1401202724 --- ");
//		System.out.println("resultado: " + datos);
//		System.out.println("Codigo Cliente Siebel: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getCodigoClienteAgrupador()));
//		System.out.println("Razon Social: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getRazonSocialClienteAgrupador()));
//		System.out.println("--------------------------------------");
//		
//		datos = clienteSiebelServicio.consultarClienteSiebel("1401022360");
//		System.out.println("---WS testConsultarClienteSiebel - Resultado=1401022360 --- ");
//		System.out.println("resultado: " + datos);
//		System.out.println("Codigo Cliente Siebel: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getCodigoClienteAgrupador()));
//		System.out.println("Razon Social: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getRazonSocialClienteAgrupador()));
//		System.out.println("--------------------------------------");
//		
//		datos = clienteSiebelServicio.consultarClienteSiebel("1401279138");
//		System.out.println("---WS testConsultarClienteSiebel - Resultado=1401279138 --- ");
//		System.out.println("resultado: " + datos);
//		System.out.println("Codigo Cliente Siebel: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getCodigoClienteAgrupador()));
//		System.out.println("Razon Social: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getRazonSocialClienteAgrupador()));
//		System.out.println("--------------------------------------");
//		
//		datos = clienteSiebelServicio.consultarClienteSiebel("1401227300");
//		System.out.println("---WS testConsultarClienteSiebel - Resultado=1401227300 --- ");
//		System.out.println("resultado: " + datos);
//		System.out.println("Codigo Cliente Siebel: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getCodigoClienteAgrupador()));
//		System.out.println("Razon Social: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getRazonSocialClienteAgrupador()));
//		System.out.println("--------------------------------------");
//		
//		datos = clienteSiebelServicio.consultarClienteSiebel("1401097870");
//		System.out.println("---WS testConsultarClienteSiebel - Resultado=1401097870 --- ");
//		System.out.println("resultado: " + datos);
//		System.out.println("Codigo Cliente Siebel: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getCodigoClienteAgrupador()));
//		System.out.println("Razon Social: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getRazonSocialClienteAgrupador()));
//		System.out.println("--------------------------------------");
//		
//		datos = clienteSiebelServicio.consultarClienteSiebel("1401303251");
//		System.out.println("---WS testConsultarClienteSiebel - Resultado=1401303251 --- ");
//		System.out.println("resultado: " + datos);
//		System.out.println("Codigo Cliente Siebel: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getCodigoClienteAgrupador()));
//		System.out.println("Razon Social: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getRazonSocialClienteAgrupador()));
//		System.out.println("--------------------------------------");
//		
//		datos = clienteSiebelServicio.consultarClienteSiebel("2601023708");
//		System.out.println("---WS testConsultarClienteSiebel - Resultado=2601023708 --- ");
//		System.out.println("resultado: " + datos);
//		System.out.println("Codigo Cliente Siebel: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getCodigoClienteAgrupador()));
//		System.out.println("Razon Social: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getRazonSocialClienteAgrupador()));
//		System.out.println("--------------------------------------");
//		
//		datos = clienteSiebelServicio.consultarClienteSiebel("1401251574");
//		System.out.println("---WS testConsultarClienteSiebel - Resultado=1401251574 --- ");
//		System.out.println("resultado: " + datos);
//		System.out.println("Codigo Cliente Siebel: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getCodigoClienteAgrupador()));
//		System.out.println("Razon Social: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getRazonSocialClienteAgrupador()));
//		System.out.println("--------------------------------------");
	}
	
	//@Test
	public void testConsultarClientes() throws NegocioExcepcion {
		EntradaSiebelConsultarClienteWS entrada = new EntradaSiebelConsultarClienteWS(
				"", "2801175472", null, null, null);
		SalidaSiebelConsultarClienteWS datos = 
			clienteSiebelServicio.consultarClientes(entrada);
		
		System.out.println("---WS testConsultarClientes - Resultado=2801175472 --- ");
		System.out.println("resultado: " + datos);
		System.out.println("Codigo Cliente Siebel: " + (datos==null || datos.getCliente()==null?"":datos.getCliente().getCodigoClienteAgrupador()));
		System.out.println("Razon Social: " + (datos==null||datos.getCliente()==null?"":datos.getCliente().getRazonSocialClienteAgrupador()));
		System.out.println("--------------------------------------");

	}
}
