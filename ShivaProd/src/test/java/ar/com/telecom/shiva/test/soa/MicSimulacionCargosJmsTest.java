package ar.com.telecom.shiva.test.soa;

import java.math.BigDecimal;

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
import ar.com.telecom.shiva.base.enumeradores.OrigenCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaGeneracionCargoSalida;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsSyncServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleGeneracionCargosDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring-context.xml" })
public class MicSimulacionCargosJmsTest {

	@Autowired
	private IMicJmsSyncServicio micJmsSyncServicio;
	
	@BeforeClass
	@SuppressWarnings("resource")
    public static void setUpClass() throws Exception {
		System.setProperty(Constantes.PROPIEDAD_ENTORNO, "desa3");
		System.setProperty(Constantes.PROPIEDAD_CONFIGURACION, "D:\\Telecom-Shiva-General\\ShivaSprint5\\src\\main\\resources\\shiva_desa3.properties");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
		DataSource testDataSource = (DataSource) context.getBean("testDataSource");
		SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
		builder.bind("java:jboss/datasources/ShivaDS", testDataSource);
		builder.activate();
    }
	
	/**
	 * idOperacion: 1706
	 * Entrada:
	 * 
	 *
	 * Salida:
	 * 
	 */
	@Test
	public void simularGeneracionCargosDebitoMic() throws NegocioExcepcion {
		
		MicTransaccionGeneracionCargosDto mensajeMIC = new MicTransaccionGeneracionCargosDto();
		mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$06);
		mensajeMIC.setModoEjecucion(SiNoEnum.SI);
		mensajeMIC.setIdOperacion(new Long("1706")); //
		mensajeMIC.setIdTransaccion(new Integer("4"));
		mensajeMIC.setNumeroTransaccion(new Integer("3")); 
		mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		
		MicDetalleGeneracionCargosDto detalleGeneracionCargo = new MicDetalleGeneracionCargosDto();
		detalleGeneracionCargo.setAcuerdoFacturacion(null); 
		detalleGeneracionCargo.setImporteRegulado(new BigDecimal("1.03")); 
		detalleGeneracionCargo.setFechaDesde(Utilidad.formatDateAAAAMMDD(null)); 
		detalleGeneracionCargo.setCalcularFechaHasta(SiNoEnum.SI);
		detalleGeneracionCargo.setQueHacerConLosIntereses(TratamientoInteresesEnum.SC); 
		detalleGeneracionCargo.setOrigenCargo(OrigenCargoEnum.TRASLADO_SALDO_FACTURA);
		detalleGeneracionCargo.setLeyendaFacturaCargo("Traslado saldo FC 7105-01645198 (Op.0001706)"); 
		detalleGeneracionCargo.setLeyendaFacturaInteres("Int. Tras. saldo FC 7105-01645198 (Op.0001706)");
		mensajeMIC.setDetalleGeneracionCargos(detalleGeneracionCargo);
		
		// Resultados posibles: OK / ER 
		MicRespuestaGeneracionCargoSalida salida = micJmsSyncServicio.simularCargoDebito(mensajeMIC);
		System.out.println("simularGeneracionCargosDebitoMic (MIC):" + salida);
	}
	
	/**
	 * idOperacion: 1706 - idTransaccion: 3723
	 * Entrada:
	 * 
	 * 
	 * Salida:
	 */
	//@Test
	public void simularGeneracionCargosCreditoMic() throws NegocioExcepcion {
		MicTransaccionGeneracionCargosDto mensajeMIC = new MicTransaccionGeneracionCargosDto();
		MicRespuestaGeneracionCargoSalida salida = micJmsSyncServicio.simularCargoCredito(mensajeMIC);
		System.out.println("simularGeneracionCargosCreditoMic (MIC):" + salida);	
	}
	

	/**
	 * idOperacion: 1706 - idTransaccion: 3723
	 * Entrada:
	 * 
	 * 
	 * Salida:
	 */
	//@Test
	public void simularGeneracionCargosInteresesMic() throws NegocioExcepcion {
		MicTransaccionGeneracionCargosDto mensajeMIC = new MicTransaccionGeneracionCargosDto();
		MicRespuestaGeneracionCargoSalida salida = micJmsSyncServicio.simularInteres(mensajeMIC);
		System.out.println("simularGeneracionCargosCreditoMic (MIC):" + salida);	
	}
}
