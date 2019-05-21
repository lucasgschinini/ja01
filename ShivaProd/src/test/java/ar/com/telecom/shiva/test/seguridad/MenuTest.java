package ar.com.telecom.shiva.test.seguridad;

import java.util.Iterator;
import java.util.Map;

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
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.menu.IMenuServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.menu.MenuDto;
import ar.com.telecom.shiva.presentacion.bean.dto.menu.MenuNivel0Dto;
import ar.com.telecom.shiva.presentacion.bean.dto.menu.MenuNivel1Dto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/context/spring-jdbc.xml", "classpath*:/context/spring-security.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MenuTest {

	@Autowired IMenuServicio menuServicio;
	
	@BeforeClass
	@SuppressWarnings("resource")
    public static void setUpClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
        DataSource testDataSource = (DataSource) context.getBean("testDataSource");
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind("java:jboss/datasources/ShivaDS", testDataSource);
        builder.activate();
        
        System.setProperty(Constantes.PROPIEDAD_ENTORNO, Constantes.ENTORNO_LOCAL);
        
    }
	
	@SuppressWarnings("rawtypes")
	@Test
	@Transactional
	public void testA_UnPerfil() throws NegocioExcepcion {
		MenuDto menu = menuServicio.obtenerMenuPerfil("Analista_TA_OGC");
		
		Iterator<?> it = menu.getNivel0().entrySet().iterator();
		while(it.hasNext()) {
		   	Map.Entry e = (Map.Entry)it.next();
		    MenuNivel0Dto o = (MenuNivel0Dto)e.getValue();
		    System.out.println(o.getDescripcion());
		    
		    Iterator<?> it1 = o.getNivel1().entrySet().iterator();
			while(it1.hasNext()) {
			   	Map.Entry e1 = (Map.Entry)it1.next();
			    MenuNivel1Dto o1 = (MenuNivel1Dto)e1.getValue();
			    
				System.out.println("==>" + o1.getDescripcion() 
		    			+ ", " + o1.getUrlAcceso());
		    }
	    }
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	@Transactional
	public void testB_VariosPerfiles() throws NegocioExcepcion {
		MenuDto menu = menuServicio.obtenerMenuPerfil("Analista_TA_OGC,Supervisor_TA_OGC");
		
		Iterator<?> it = menu.getNivel0().entrySet().iterator();
		while(it.hasNext()) {
		   	Map.Entry e = (Map.Entry)it.next();
		    MenuNivel0Dto o = (MenuNivel0Dto)e.getValue();
		    System.out.println(o.getDescripcion());
		    
		    Iterator<?> it1 = o.getNivel1().entrySet().iterator();
			while(it1.hasNext()) {
			   	Map.Entry e1 = (Map.Entry)it1.next();
			    MenuNivel1Dto o1 = (MenuNivel1Dto)e1.getValue();
			    
				System.out.println("==>" + o1.getDescripcion() 
		    			+ ", " + o1.getUrlAcceso());
		    }
	    }
	}
}