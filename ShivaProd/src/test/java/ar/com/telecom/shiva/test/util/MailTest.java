package ar.com.telecom.shiva.test.util;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.mail.MailServicio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/context/spring-mail.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MailTest extends TestCase {
	
	@Autowired 
	MailServicio mailServicio;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		System.setProperty(Constantes.PROPIEDAD_ENTORNO, Constantes.ENTORNO_LOCAL);
    }
	
	@Test
	public void testMail() throws Exception {
		mailServicio.testMail(); 
	}
}
