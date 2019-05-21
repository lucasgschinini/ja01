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

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaAcuerdoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaClienteSalida;
import ar.com.telecom.shiva.base.jms.servicios.IFacJmsSyncServicio;
import ar.com.telecom.shiva.base.utils.Validaciones;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring-context.xml" })
public class FacConsultaJmsTest {

	@Autowired
	private IFacJmsSyncServicio facJmsSyncServicio;
	
	@BeforeClass
	@SuppressWarnings("resource")
    public static void setUpClass() throws Exception {
		System.setProperty("entorno", "desa3");
		System.setProperty(Constantes.PROPIEDAD_CONFIGURACION, "D:\\Telecom-Shiva-General\\ShivaDesarrollo\\src\\main\\resources\\shiva_desa3.properties");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
		DataSource testDataSource = (DataSource) context.getBean("testDataSource");
		SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
		builder.bind("java:jboss/datasources/ShivaDS", testDataSource);
		builder.activate();
    }
	
	@Test
	public void consultaAcuerdoTest() throws NegocioExcepcion {
		
		//Consulta de acuerdo 1
		//2501034914
		//9850697904 
		//3131012770
		//3131020093
		//9850696597
		//3131012770
		//9381046114
		
		FacConsultaAcuerdoSalida entrada1 = facJmsSyncServicio.consultarAcuerdo(new Long("9850697904"));
		System.out.println("consultaAcuerdoTest (I3): acuerdo ingresado ("+entrada1.getPrimerAcuerdoFacturacion().getNumeroAcuerdo()+"), estado -->  " + entrada1.getPrimerAcuerdoFacturacion().getEstadoAcuerdo());
	}
	
	@Test
	public void consultaAcuerdoXLineaTest() throws NegocioExcepcion {
		
		//Consulta de acuerdo 2
		//3424585439
		//3424509624
		//3424585310 --> usarlo
		//3424834761
		
		String linea = "3424585310";
		FacConsultaAcuerdoSalida entrada2  = facJmsSyncServicio.consultarAcuerdoxLinea(linea);
		
		System.out.println("consultaAcuerdoXLineaTest (A3): linea ingresada ("+linea+"), acuerdo--> "
				+entrada2.getPrimerAcuerdoFacturacion().getNumeroAcuerdo()+", estado -->  " + entrada2.getPrimerAcuerdoFacturacion().getEstadoAcuerdo());
		
	}
	
	@Test
	public void consultaAcuerdoXClienteTest() throws NegocioExcepcion {
		
		//Consulta de cliente
		//4000014744
		//4000067215
		//2601173930
//		2  0000097702 acuerdos --> 9850808570 --> 9850809570
//		7  1201031322
//		3  1400001507
//		4  1400001799
//		3  1400001853
//		5  1400002138
//		5  1400003790
//		2  1401000331
//		3  1401000363
//		3  1401000551
//		2  1401000785
//		2  1401000886
//		3  1401001184
//		6  1401002303
//		2  1401002517
//		3  1401003038
//		4  1401003124
		
		String cliente = "1401000363";
		FacConsultaAcuerdoSalida entrada3  = facJmsSyncServicio.consultarAcuerdoxCliente(new Long(cliente));
		if(!Validaciones.isObjectNull(entrada3)){
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> " + entrada3.toString());
		} else {
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> el servicio tuvo un error");
		}
		cliente = "0000097702";
		entrada3  = facJmsSyncServicio.consultarAcuerdoxCliente(new Long(cliente));
		if(!Validaciones.isObjectNull(entrada3)){
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> " + entrada3.toString());
		} else {
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> el servicio tuvo un error");
		}
		cliente = "1201031322";
		entrada3  = facJmsSyncServicio.consultarAcuerdoxCliente(new Long(cliente));
		if(!Validaciones.isObjectNull(entrada3)){
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> " + entrada3.toString());
		} else {
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> el servicio tuvo un error");
		}
		cliente = "1400001507";
		entrada3  = facJmsSyncServicio.consultarAcuerdoxCliente(new Long(cliente));
		if(!Validaciones.isObjectNull(entrada3)){
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> " + entrada3.toString());
		} else {
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> el servicio tuvo un error");
		}
		cliente = "1400001799";
		entrada3  = facJmsSyncServicio.consultarAcuerdoxCliente(new Long(cliente));
		if(!Validaciones.isObjectNull(entrada3)){
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> " + entrada3.toString());
		} else {
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> el servicio tuvo un error");
		}
		cliente = "1400001853";
		entrada3  = facJmsSyncServicio.consultarAcuerdoxCliente(new Long(cliente));
		if(!Validaciones.isObjectNull(entrada3)){
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> " + entrada3.toString());
		} else {
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> el servicio tuvo un error");
		}
		cliente = "1400002138";
		entrada3  = facJmsSyncServicio.consultarAcuerdoxCliente(new Long(cliente));
		if(!Validaciones.isObjectNull(entrada3)){
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> " + entrada3.toString());
		} else {
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> el servicio tuvo un error");
		}
		cliente = "1400003790";
		entrada3  = facJmsSyncServicio.consultarAcuerdoxCliente(new Long(cliente));
		if(!Validaciones.isObjectNull(entrada3)){
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> " + entrada3.toString());
		} else {
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> el servicio tuvo un error");
		}
		cliente = "1401000331";
		entrada3  = facJmsSyncServicio.consultarAcuerdoxCliente(new Long(cliente));
		if(!Validaciones.isObjectNull(entrada3)){
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> " + entrada3.toString());
		} else {
			System.out.println("consultaAcuerdoXClienteTest (B3): cliente ingresado ("+cliente+"), acuerdos --> el servicio tuvo un error");
		}
		
	}
	
//	@Test
	public void consultaClienteTest() throws NegocioExcepcion {
		
		//Consulta de cliente por acuerdo
		//3131024483
		//3131023483
		//9850696597
		//9850696324
		//7501029593
		//7501023693
		//9850807630
		
		String acuerdo = "9850696597";
		FacConsultaClienteSalida entradaCliente1  = facJmsSyncServicio.consultarClientexAcuerdo(acuerdo);
		System.out.println("consultaClienteTest (I1): acuerdo ingresado ("+acuerdo+"), cliente--> " + 
				(entradaCliente1==null?"vacio":entradaCliente1.getNumeroCliente()));
		acuerdo = "3131024483";
		entradaCliente1  = facJmsSyncServicio.consultarClientexAcuerdo(acuerdo);
		System.out.println("consultaClienteTest (I1): acuerdo ingresado ("+acuerdo+"), cliente--> " + 
				(entradaCliente1==null?"vacio":entradaCliente1.getNumeroCliente()));
		acuerdo = "3131023483";
		entradaCliente1  = facJmsSyncServicio.consultarClientexAcuerdo(acuerdo);
		System.out.println("consultaClienteTest (I1): acuerdo ingresado ("+acuerdo+"), cliente--> " + 
				(entradaCliente1==null?"vacio":entradaCliente1.getNumeroCliente()));
		acuerdo = "9850696324";
		entradaCliente1  = facJmsSyncServicio.consultarClientexAcuerdo(acuerdo);
		System.out.println("consultaClienteTest (I1): acuerdo ingresado ("+acuerdo+"), cliente--> " + 
				(entradaCliente1==null?"vacio":entradaCliente1.getNumeroCliente()));
		acuerdo = "7501029593";
		entradaCliente1  = facJmsSyncServicio.consultarClientexAcuerdo(acuerdo);
		System.out.println("consultaClienteTest (I1): acuerdo ingresado ("+acuerdo+"), cliente--> " + 
				(entradaCliente1==null?"vacio":entradaCliente1.getNumeroCliente()));
		acuerdo = "7501023693";
		entradaCliente1  = facJmsSyncServicio.consultarClientexAcuerdo(acuerdo);
		System.out.println("consultaClienteTest (I1): acuerdo ingresado ("+acuerdo+"), cliente--> " + 
				(entradaCliente1==null?"vacio":entradaCliente1.getNumeroCliente()));
		acuerdo = "9850807630";
		entradaCliente1  = facJmsSyncServicio.consultarClientexAcuerdo(acuerdo);
		System.out.println("consultaClienteTest (I1): acuerdo ingresado ("+acuerdo+"), cliente--> " + 
				(entradaCliente1==null?"vacio":entradaCliente1.getNumeroCliente()));
		
	}
	
}
