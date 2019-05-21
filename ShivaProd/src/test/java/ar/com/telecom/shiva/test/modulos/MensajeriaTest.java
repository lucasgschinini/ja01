package ar.com.telecom.shiva.test.modulos;

import java.util.List;

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
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.persistencia.dao.IMensajeriaTransaccionDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccionMensajeriaDetalle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MensajeriaTest{

	@Autowired 
	IMensajeriaTransaccionDao mensajeriaTransaccionDao;
	
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
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;
		ShvCobTransaccionMensajeriaDetalle registro;
		
		registro = mensajeriaTransaccionDao.buscarMensajePorIdMensajeria(new Integer(1));
		System.out.println(registro != null ? "buscarMensajePorIdMensajeria - resultado: " + 1 : "buscarMensajePorIdMensajeria - resultado: " + 0);
		
		lista = mensajeriaTransaccionDao.buscarMensajePorIdOperacionTransaccion(new Long(28), new Integer(44));
		System.out.println("buscarMensajePorIdOperacionTransaccion - resultado: " + lista.size());
		
		lista = mensajeriaTransaccionDao.buscarMensajesDesapropiacionConfirmacionEnviadosMIC(new Long(81));
		System.out.println("buscarMensajesDesapropiacionConfirmacionEnviadosMIC - resultado: " + lista.size());
		
		lista = mensajeriaTransaccionDao.listarMensajesPendientesMIC("19/11/2014");
		System.out.println("listarMensajesPendientesMIC - resultado: " + lista.size());
		
		lista = mensajeriaTransaccionDao.buscarMensajesRespuestaConfirmacion(new Long(24));
		System.out.println("buscarMensajesRespuestaConfirmacion - resultado: " + lista.size());
		
		lista = mensajeriaTransaccionDao.buscarMensajesRespuestaDesapropiacion(new Long(27));
		System.out.println("buscarMensajesRespuestaDesapropiacion - resultado: " + lista.size());
		
		lista = mensajeriaTransaccionDao.buscarMensajesRespuestaApropiacion(new Long(27), new Integer(41));
		System.out.println("buscarMensajesRespuestaApropiacion - resultado: " + lista.size());
		
		//lista = mensajeriaTransaccionDao.buscarMensajesPorIdOperacion(new Long(1), MensajeServicioEnum.MVK_RESPUESTA_IMPUTACION_COBRO);
		//System.out.println("buscarMensajesPorIdOperacion - resultado: " + lista.size());
	
		lista = mensajeriaTransaccionDao.buscarMensajesPorIdOperacionTransaccion(new Long(27), new Integer(41), MensajeServicioEnum.CLP_APROP_DEUDA);
		System.out.println("buscarMensajesPorIdOperacionTransaccion - resultado: " + lista.size());
		
		lista = mensajeriaTransaccionDao.buscarMensajeCancelado(new Long(1));
		System.out.println("buscarMensajeCancelado - resultado: " + lista.size());
		
		lista = mensajeriaTransaccionDao.buscarMensajeConfirmacionCompletadaMic(new Long(81));
		System.out.println("buscarMensajeConfirmacionCompletadaMic - resultado: " + lista.size());
		
		lista = mensajeriaTransaccionDao.buscarMensajeDesapropiacionCompletadaMic(new Long(141));
		System.out.println("buscarMensajeDesapropiacionCompletadaMic - resultado: " + lista.size());
	}
	
	
	
	
	public IMensajeriaTransaccionDao getMensajeriaTransaccionDao() {
		return mensajeriaTransaccionDao;
	}

	public void setMensajeriaTransaccionDao(
			IMensajeriaTransaccionDao mensajeriaTransaccionDao) {
		this.mensajeriaTransaccionDao = mensajeriaTransaccionDao;
	}
}
