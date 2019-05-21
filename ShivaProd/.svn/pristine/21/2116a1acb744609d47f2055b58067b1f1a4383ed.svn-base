package ar.com.telecom.shiva.test.soa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaCreditoEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaDeudaEntrada;
import ar.com.telecom.shiva.base.jms.datos.salida.MicConsultaCreditoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicConsultaDeudaSalida;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsSyncServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring-context.xml" })
public class MicConsultaDeudaCreditoJmsTest {

	@Autowired
	private IMicJmsSyncServicio micJmsSyncServicio;
	
	@BeforeClass
	@SuppressWarnings("resource")
    public static void setUpClass() throws Exception {
		System.setProperty(Constantes.PROPIEDAD_ENTORNO, "desa3");
		System.setProperty(Constantes.PROPIEDAD_CONFIGURACION, "D:\\Telecom-Shiva-General\\ShivaDesarrollo\\src\\main\\resources\\shiva_desa3.properties");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
		DataSource testDataSource = (DataSource) context.getBean("testDataSource");
		SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
		builder.bind("java:jboss/datasources/ShivaDS", testDataSource);
		builder.activate();
    }
	
	@Test
	public void consultaDeudaTest() throws NegocioExcepcion {
		
		MicConsultaDeudaEntrada entrada = new MicConsultaDeudaEntrada();
		
		List<BigInteger> listaClientes = new ArrayList<BigInteger>();
		listaClientes.add(new BigInteger("4000014744"));
		entrada.setListaClientes(listaClientes);
		
		entrada.getInformacionFactura().setFechaVencimientoDesde(Utilidad.restarDiasAFecha(new Date(), 30));
		entrada.getInformacionFactura().setFechaVencimientoHasta(new Date());
		entrada.getInformacionPaginado().setCantidadRegistrosARetornar(100);
		
		MicConsultaDeudaSalida salida = micJmsSyncServicio.consultarDeuda(entrada);
		
		System.out.println("consultaDeudaTest (MIC):" + salida);
	}
	
	@Test
	public void consultaCreditoTest() throws NegocioExcepcion {
		
		MicConsultaCreditoEntrada entrada = new MicConsultaCreditoEntrada();
		
		List<BigInteger> listaClientes = new ArrayList<BigInteger>();
		listaClientes.add(new BigInteger("4000014744"));
		entrada.setListaClientes(listaClientes);
		
		entrada.setTipoMedioPago(TipoMedioPagoEnum.TODOS);
		entrada.setFechaDesde(Utilidad.restarDiasAFecha(new Date(), 30));
		entrada.setFechaHasta(new Date());
		entrada.getInformacionPaginado().setCantidadRegistrosARetornar(100);
		
		MicConsultaCreditoSalida salida = micJmsSyncServicio.consultarCredito(entrada);
		
		System.out.println("consultaCreditoTest (MIC):" + salida);	
	}
	
}
