package ar.com.telecom.shiva.test.modulos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OperacionMasivaTest {

//	@Autowired
	private IOperacionMasivaServicio operacionMasivaServicio;
	
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
	public void testProcesarArchivo() throws NegocioExcepcion{
		
		// Operacion masiva
		OperacionMasivaDto operacionMasiva = new OperacionMasivaDto();
		operacionMasiva.setIdAnalista("SHV564030");
		operacionMasiva.setNombreAnalista("Pepe");
		operacionMasiva.setIdCopropietario("SHV562163");
		operacionMasiva.setIdMotivo("1");
		operacionMasiva.setPathArchivo("DSIST_20140728.txt");
		operacionMasiva.setIdSegmento("OGC");
		operacionMasiva.setIdEmpresa("TA");
		
		//Comprobantes
		ComprobanteDto comp = new ComprobanteDto();		
		List<ComprobanteDto> listaComprobantes = new ArrayList<ComprobanteDto>();
		
		//File file = new File("D://" + operacionMasiva.getPathArchivo());
		//operacionMasivaServicio.procesarArchivo(file, operacionMasiva.getUsuarioModificacion());
		//UsuarioSesion usuario = new UsuarioSesion("SHV564030","SHV564030",null);
		
		//Comprobantes
		comp.setDescripcionArchivo("Comprobante Prueba");
		comp.setNombreArchivo("DSIST_20140728.txt");
		try {
			comp.setDocumento(Files.readAllBytes(new File("D://DSIST_20140728.txt").toPath()));
		} catch (IOException e) {
			throw new NegocioExcepcion (e.getMessage(), e);
		}
		listaComprobantes.add(comp);
		operacionMasiva.setListaComprobantes(listaComprobantes);
		
		//Operacion masiva
//		operacionMasivaServicio.procesarArchivo(operacionMasiva);
		
	}
	/*
	//@Test
	public void testProcesarComprobante() throws NegocioExcepcion{
		OperacionMasivaDto operacionMasiva = new OperacionMasivaDto();
		ComprobanteDto comp = new ComprobanteDto();		
		List<ComprobanteDto> listaComprobantes = new ArrayList<ComprobanteDto>();
		
		
		operacionMasiva.setIdEmpresa("TA");
		operacionMasiva.setEmpresa("Telecom Argentina");
		operacionMasiva.setIdSegmento("OGC");
		operacionMasiva.setSegmento("Grandes clientes");
		operacionMasiva.setIdAnalista("SHV564030");
		operacionMasiva.setNombreAnalista("Pepe");
		operacionMasiva.setIdCopropietario("SHV562163");
//		operacionMasiva.setIdMotivo("Desistimiento");
		operacionMasiva.setIdMotivo("3");
		operacionMasiva.setPathArchivo("D://DEUDA_20140721_001.txt");
		
		comp.setDescripcionArchivo("Comprobante Prueba");
		comp.setNombreArchivo("DEUDA_20140721_001.txt");
		try {
			comp.setDocumento(Files.readAllBytes(new File("D://DEUDA_20140721_001.txt").toPath()));
		} catch (IOException e) {
			throw new NegocioExcepcion (e.getMessage(), e);
		}
		listaComprobantes.add(comp);
		operacionMasiva.setListaComprobantes(listaComprobantes);
		
		//if (probarValidaciones(new File(operacionMasiva.getPathArchivo()))) {
			operacionMasivaServicio.procesarArchivo(operacionMasiva);
		//}
	}
	
	//@Test
	public boolean probarValidaciones(File file) {
		Long filesize;
		try {
			filesize = Long.valueOf(Files.readAllBytes(file.toPath()).length);
		String[] extensiones = Propiedades.VALIDACIONES_PROPIEDADES.getString("validacion.extensionesPosibles").split(PIPE);
		String[] nombresArchivos = Propiedades.VALIDACIONES_PROPIEDADES.getString("validacion.nombresProhibidos.archivo").split(PIPE);
		System.out.println(filesize);
		System.out.println(file.getName());
		for (int i = 0; i < extensiones.length; i++) {
			//if (extensiones[i].equalsIgnoreCase(file.))
		}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}*/

	
	public IOperacionMasivaServicio getOperacionMasivaServicio() {
		return operacionMasivaServicio;
	}

	public void setOperacionMasivaServicio(
			IOperacionMasivaServicio operacionMasivaServicio) {
		this.operacionMasivaServicio = operacionMasivaServicio;
	}

}
