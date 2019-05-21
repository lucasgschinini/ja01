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
import org.springframework.test.context.transaction.TransactionConfiguration;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.OrigenCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsServicio;
import ar.com.telecom.shiva.base.jms.util.JmsMonitorMensajeria;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.dto.cobros.MicApropiacionDeudaDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleGeneracionCargosDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleMedioPagoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicGeneracionCargosDebitoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring-context.xml" })
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class MicImputacionCobrosJmsTest {

	@Autowired 
	JmsMonitorMensajeria monitor;
	
	@Autowired
	IMicJmsServicio micJmsServicio;
	
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
	
	@Test
	public void apropiacionDeudaTest() throws PersistenciaExcepcion, InterruptedException, NegocioExcepcion {
		
		MicApropiacionDeudaDto transaccion = new MicApropiacionDeudaDto();
		transaccion.setIdOperacion(new Long(222));
		transaccion.setIdTransaccion(new Integer(389));
		transaccion.setNumeroTransaccion(new Integer(22));
		transaccion.setModoEjecucion(SiNoEnum.NO);
		
		transaccion.getDetalleFactura().setTipoOperacion(TipoPagoEnum.PAGO_PARCIAL);
		transaccion.getDetalleFactura().setReferenciaFactura("REF4872");
		transaccion.getDetalleFactura().setFechaValor("20140321");
		transaccion.getDetalleFactura().setAcuerdoFacturacionIntereses("acuerdo");
		transaccion.getDetalleFactura().setImporte(new BigDecimal("154.4"));
		transaccion.getDetalleFactura().setQueHacerConLosIntereses(TratamientoInteresesEnum.BM.getCodigoMicApropiacion());
		transaccion.getDetalleFactura().setQueHacerConLosTerceros(SiNoEnum.SI);

		MicDetalleMedioPagoDto mp = new MicDetalleMedioPagoDto();
		mp.setTipoMedioPago(TipoMedioPagoEnum.RT);
		mp.setImporteMedioPago(new BigDecimal("1.26"));
		mp.setCuentaRemanente(new Long("1"));
		mp.setTipoRemanente(TipoRemanenteEnum.TRANSFERIBLE_NO_ACTUALIZABLE);
		mp.setNumeroNC(new Long("4"));
		transaccion.getListaMediosPago().add(mp);
		
		MicTransaccionADCDto 
		 	respuesta = (MicTransaccionADCDto) 
		 		monitor.enviarMensaje(transaccion);
		
		System.out.println("Respuesta:");
		System.out.println("Id Mensajeria:" + respuesta.getMensajeria().getIdTransaccionMensajeria());
		
		micJmsServicio.recibirMensajes(TipoProcesoEnum.COBRO);
		
		//Falta procesar mensajes
	}
	
	@Test
	public void generacionCargosTest() throws PersistenciaExcepcion, InterruptedException, NegocioExcepcion {
		
		MicGeneracionCargosDebitoDto mensajeMIC = new MicGeneracionCargosDebitoDto();
		mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$06);
		mensajeMIC.setModoEjecucion(SiNoEnum.NO);
		mensajeMIC.setIdOperacion(new Long("1706")); //
		mensajeMIC.setIdTransaccion(new Integer("4"));
		mensajeMIC.setNumeroTransaccion(new Integer("32")); 
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
		
		MicTransaccionGeneracionCargosDto 
		 	respuesta = (MicTransaccionGeneracionCargosDto) monitor.enviarMensaje(mensajeMIC);
		
		System.out.println("Respuesta:");
		System.out.println("Id Mensajeria:" + respuesta.getMensajeria().getIdTransaccionMensajeria());
		
		micJmsServicio.recibirMensajes(TipoProcesoEnum.COBRO);
	
		// Falta procesar mensajes
	}
	
}
