package ar.com.telecom.shiva.test.seguridad;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
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
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Encriptador;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoAdjuntoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EncriptacionDatosTest{

	@Autowired 
	IDocumentoAdjuntoDao documentoAdjuntoDao;
	
	@Autowired 
	StandardPBEByteEncryptor byteEncryptor;
	
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
	public void testClaveEncripYDesencrip() throws Exception {
	
		String clave = "nN2$L2$$";
		String claveEncriptada = Encriptador.encrypt(clave);
		
		System.out.println(claveEncriptada);
	}
	
	//@Test
	public void testArchivoEncripYDesencrip() throws Exception {
	
		String filepath = "C:\\Desarrollo\\Permisos.txt";
		
		File file = new File(filepath);
		InputStream in = new FileInputStream(file);
		byte[] original = ControlArchivo.getBytesFromInputStream(in);
		
		byte[] encryptedBytes = byteEncryptor.encrypt(original);
		filepath = "C:\\Desarrollo\\encriptaciones\\PermisosEncriptado.txt";
		ControlArchivo.escribir(encryptedBytes, filepath);
		
		byte[] plainBytes = byteEncryptor.decrypt(encryptedBytes);
		filepath = "C:\\Desarrollo\\encriptaciones\\PermisosDesencriptado.txt";
		ControlArchivo.escribir(plainBytes, filepath);
	}
	
	//@Test
	public void testArchivoEncripYDesencrip2() throws Exception {
		Long idDocumentoAdjunto = testEncriptacion();
		testDesencriptado(idDocumentoAdjunto);
	}
	
	private Long testEncriptacion() throws Exception {
		String filepath = "C:\\Desarrollo\\foto.png";
		
		File file = new File(filepath);
		InputStream in = new FileInputStream(file);
		byte[] original = ControlArchivo.getBytesFromInputStream(in);
		
		ShvDocDocumentoAdjunto documentoAdjunto = new ShvDocDocumentoAdjunto();
        documentoAdjunto.setArchivoAdjunto(original);
        documentoAdjunto.setDescripcion("descripcion");
        documentoAdjunto.setFechaCreacion(new Date());
        documentoAdjunto.setNombreArchivo("nombreArchivo");
        documentoAdjunto.setUsuarioCreacion("josefin");
        documentoAdjunto.setIdValor(new ArrayList<ShvValValor>());
        
        Long idPrueba = Long.MIN_VALUE;
        documentoAdjunto.setIdValorAdjunto(idPrueba);
        documentoAdjunto = documentoAdjuntoDao.crear(documentoAdjunto);
        System.out.println("Nro de Id: " + documentoAdjunto.getIdValorAdjunto());
        return documentoAdjunto.getIdValorAdjunto();
	}

	private void testDesencriptado(Long idDocumentoAdjunto) throws Exception {
		ShvDocDocumentoAdjunto documentoAdjunto = documentoAdjuntoDao.buscarDocumentoAdjunto(idDocumentoAdjunto);
		InputStream in = new ByteArrayInputStream(documentoAdjunto.getArchivoAdjunto());
		
		byte[] original = ControlArchivo.getBytesFromInputStream(in);
		String filepath = "C:\\Desarrollo\\fotoDesc.png";
		
		ControlArchivo.escribir(original, filepath);
	}
	
	public IDocumentoAdjuntoDao getDocumentoAdjuntoDao() {
		return documentoAdjuntoDao;
	}

	public void setDocumentoAdjuntoDao(IDocumentoAdjuntoDao documentoAdjuntoDao) {
		this.documentoAdjuntoDao = documentoAdjuntoDao;
	}

	public StandardPBEByteEncryptor getByteEncryptor() {
		return byteEncryptor;
	}

	public void setByteEncryptor(StandardPBEByteEncryptor byteEncryptor) {
		this.byteEncryptor = byteEncryptor;
	}
	
}
