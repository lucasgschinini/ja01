package ar.com.telecom.shiva.test.seguridad;

import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.menu.IPerfilServicio;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "desa_oracle_ldap")
@ContextConfiguration(locations = { "classpath*:/context/spring-jdbc.xml","classpath*:/context/spring-security.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PerfilTest {

	@Autowired IPerfilServicio perfilServicio;
	
	@BeforeClass
	@SuppressWarnings("resource")
    public static void setUpClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
        DataSource testDataSource = (DataSource) context.getBean("testDataSource");
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind("java:jboss/datasources/ShivaDS", testDataSource);
        builder.activate();
    }
	
	
	private String getRolesToString(Collection<GrantedAuthority> roles) {
		if (Validaciones.isCollectionNotEmpty(roles)) {
			//Limpio los caracteres en blanco
			String strRoles = roles.toString().replace(" ", "");
			
			//Saco los corchetes []
			strRoles = strRoles.substring(1, strRoles.length()-1);
			
			return strRoles;
		}
		return "";
					
	}
	
	@Test
	@Transactional
	public void testA_Acciones() throws NegocioExcepcion {
		Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>(); 
		
		GrantedAuthority authority = new SimpleGrantedAuthority("ANALISTA_TA_OGC");
		roles.add(authority);
		
		authority = new SimpleGrantedAuthority("ANALISTA_TA_OGC");
		roles.add(authority);
		
		authority = new SimpleGrantedAuthority("ANALISTA_TA_OYP");
		roles.add(authority);
		
		authority = new SimpleGrantedAuthority("SUPERVISOR_TA_OGC");
		roles.add(authority);
		
		authority = new SimpleGrantedAuthority("AdminAVC");
		roles.add(authority);
		
		authority = new SimpleGrantedAuthority("AdminValor");
		roles.add(authority);
		
		Collection<String> perfilesAcciones = perfilServicio.obtenerAccionesDePerfiles(getRolesToString(roles));
		Assert.assertTrue(!perfilesAcciones.isEmpty());
	}
	
	@Test
	@Transactional
	public void testB_Sin_Acciones() throws NegocioExcepcion {
		Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>(); 
		
		GrantedAuthority authority = new SimpleGrantedAuthority("AdminAVC");
		roles.add(authority);
		
		authority = new SimpleGrantedAuthority("AdminValor");
		roles.add(authority);
		
		Collection<String> perfilesAcciones = perfilServicio.obtenerAccionesDePerfiles(getRolesToString(roles));
		Assert.assertTrue(perfilesAcciones.isEmpty());
	}
}