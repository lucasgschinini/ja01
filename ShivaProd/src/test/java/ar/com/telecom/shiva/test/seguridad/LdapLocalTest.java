package ar.com.telecom.shiva.test.seguridad;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.telecom.shiva.base.constantes.Constantes;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/context/spring-security.xml" })
public class LdapLocalTest {

	@Autowired ApplicationContext context;
	@Autowired LdapContextSource contextSource;
	@Autowired AuthenticationManager authenticationManager;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		System.setProperty(Constantes.PROPIEDAD_ENTORNO, Constantes.ENTORNO_LOCAL);
    }
	
	@Test
	public void testAuthentication() {
		Authentication auth = new UsernamePasswordAuthenticationToken("shiva", "shiva");
		Authentication object = authenticationManager.authenticate(auth);
		
		SecurityContextHolder.getContext().setAuthentication((Authentication)object);
        SecurityContextHolder.clearContext();
	}	
	
}
