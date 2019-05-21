package ar.com.telecom.shiva.test.seguridad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.directory.SearchControls;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.ldap.elementos.RolLdap;
import ar.com.telecom.shiva.base.ldap.mapeos.RolLdapMapeo;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.impl.MotorProcesamientoConciliacionServicioImpl;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

@RunWith(SpringJUnit4ClassRunner.class)
public class LdapTest extends SoporteContextoSpringTest {

	@Autowired ApplicationContext context;
	@Autowired LdapContextSource contextSource;
	@Autowired LdapTemplate ldapTemplate;
	@Autowired AuthenticationManager authenticationManager;
	@Autowired ILdapServicio ldapServicio;

	@Test
	public void testBuscarRolesAsociadosAUid() throws LdapExcepcion {
		
		System.out.println("");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~ Ldap Test: método de prueba 'testBuscarRolesAsociadosAUid()'.");
		System.out.println("~ Se prueba el  método 'ldapServicio.buscarRolesAsociadosAUid()'.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		
		String legajoAnalista = "SHV564027";
		List<RolLdap> rolesLdap = (List<RolLdap>) ldapServicio.buscarRolesAsociadosAUid(legajoAnalista);
		
		if (rolesLdap.size() == 0) {
			Traza.auditoria(MotorProcesamientoConciliacionServicioImpl.class, "El legajo '" + legajoAnalista + "' obtenido desde 'Delta' NO posee perfiles asociados.");
		} else {
			Traza.auditoria(MotorProcesamientoConciliacionServicioImpl.class, "El legajo '" + legajoAnalista + "' obtenido desde 'Delta' posee perfiles asociados. Se procede a analizarlos...");

			Collection<RolLdap> listaRolesAnalistasTA = new ArrayList<RolLdap>();
			
			for (RolLdap rolLdap : rolesLdap) {
				if (rolLdap.esAnalistaTA()) {
					System.out.println("Rol recuperado de usuario: '" + rolLdap.getDescripcion() + "' es de tipo 'Analista - Telecom Argentina'.");
					listaRolesAnalistasTA.add(rolLdap);
				} else {
					System.out.println("Rol recuperado de usuario: '" + rolLdap.getDescripcion() + "' NO es de tipo 'Analista - Telecom Argentina'.");
				}
			}
			
			// Solo para trazas de control
			//
			if (listaRolesAnalistasTA.isEmpty()) {
				System.out.println("No existe un perfil de 'Analista - Telecom Argentina' definido en 'Delta' para este cliente.");
			}
			
			if (listaRolesAnalistasTA.size() > 1) {
				System.out.println("Existe mas de un perfil de 'Analista - Telecom Argentina' definido en 'Delta' para este cliente.");
			}
			// Fin solo para trazas de control
			//
		}
		
	}
	
	//@Test
	public void testAuthentication() {
		
		System.out.println("");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~ Ldap Test: método de prueba 'testAuthentication()'.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		Authentication auth = new UsernamePasswordAuthenticationToken("SHV564030", "Telecom01!");
		Authentication object = authenticationManager.authenticate(auth);
		
		SecurityContextHolder.getContext().setAuthentication((Authentication)object);
        SecurityContextHolder.clearContext();
	}
	
	//@Test 
	public void testLdapTemplate() {

		System.out.println("");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~ Ldap Test: método de prueba 'testLdapTemplate()'.");
		System.out.println("~ Se prueba el  método 'ldapTemplate.authenticate()'.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectclass", "TelecomPerson")).and(new EqualsFilter("cn", "SHV090824"));
		boolean authen = ldapTemplate.authenticate("ou=Usuarios", filter.toString(), "SHV_u090824");
		Assert.assertTrue(authen);
	}
	
	//@Test 
	@SuppressWarnings({"rawtypes"})
	public void testLdapRoles() {

		System.out.println("");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~ Ldap Test: método de prueba 'testLdapRoles()'.");
		System.out.println("~ Se prueba el  método 'ldapTemplate.search()'.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		//Trae todos sus roles
	    List list = ldapTemplate.search("ou=Servicios", 
    		"(&(objectClass=TelecomPermission)(equivalentToMe=cn=SHV564030,ou=Personas,ou=Usuarios,o=Telecom))",
    		SearchControls.SUBTREE_SCOPE, new RolLdapMapeo());
	    
	    Assert.assertTrue(!list.isEmpty());
	}
	
	//OGC
	//ANALISTA_TA_OGE, ANALISTA_TA_OTP, ANALISTA_TA_OCO]][login]: El usuario SHV182114 ha sido autorizado por LDAP, el ingreso al sistema Shiva
	
	@Test 
	public void testBuscarUsuariosPorEmpresaYSegmento() throws LdapExcepcion {

		System.out.println("");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~ Ldap Test: método de prueba 'testBuscarUsuariosPorEmpresaYSegmento()'.");
		System.out.println("~ Se prueba el  método 'ldapServicio.buscarUsuariosPorEmpresaSegmento()'.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		Collection<UsuarioLdapDto> list = ldapServicio.buscarUsuariosPorEmpresaSegmento("TA", "OGC");
	    
		System.out.println("Lista de usuarios por empresa y segmento");

		if (Validaciones.isCollectionNotEmpty(list)) {
			
			for (UsuarioLdapDto usuario : list) {
				System.out.println(usuario.getId() + " | " + usuario.getNombreCompleto());
			}
			
		} else {
			System.out.println("no hay usuarios para ese criteorio de busqueda.");
		}

		Assert.assertTrue(!list.isEmpty());
	}
	
	@Test
	public void testBuscarUsuarioPorUid() throws LdapExcepcion {

		System.out.println("");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~ Ldap Test: método de prueba 'testBuscarUsuarioPorUid()'.");
		System.out.println("~ Se prueba el  método 'ldapServicio.buscarUsuarioPorUid()'.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		//UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUid("U000051"); 
	    
	    System.out.println("-- Busco el usuario SHV564030");
	    UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUid("SHV56430");
	    if (usuarioLdap != null) {
	    	System.out.println(usuarioLdap.getNombreCompleto() +", " + usuarioLdap.getMail());
	    } else {
	    	System.out.println("No existe");
	    }

	    Assert.assertTrue(usuarioLdap!=null);

	    /*
	    //Usuarios PAU
	    System.out.println("------------ Usuarios PAU -----------------");
	    //SHV155616	Esther Tuñon
	    usuarioLdap = ldapServicio.buscarUsuarioPorUid("SHV155616");
	    if (usuarioLdap != null) {
	    	System.out.println(usuarioLdap.getNombreCompleto() +", " + usuarioLdap.getMail());
	    } else {
	    	System.out.println("No existe");
	    }
	    //SHV171832	Marcela Lopez de Gatica
	    usuarioLdap = ldapServicio.buscarUsuarioPorUid("SHV171832");
	    if (usuarioLdap != null) {
	    	System.out.println(usuarioLdap.getNombreCompleto() +", " + usuarioLdap.getMail());
	 
	    } else {
	    	System.out.println("No existe");
	    }
	    
	    //SHV176726	Marcela De Lucía
	    usuarioLdap = ldapServicio.buscarUsuarioPorUid("SHV176726");
	    if (usuarioLdap != null) {
	    	System.out.println(usuarioLdap.getNombreCompleto() +", " + usuarioLdap.getMail());
	    } else {
	    	System.out.println("No existe");
	    }
	    
	    //SHV182801	Paola Melaraña
	    usuarioLdap = ldapServicio.buscarUsuarioPorUid("SHV182801");
	    if (usuarioLdap != null) {
	    	System.out.println(usuarioLdap.getNombreCompleto() +", " + usuarioLdap.getMail());
	    } else {
	    	System.out.println("No existe");
	    }*/
	}
	
	@Test 
	@SuppressWarnings("unused")
	public void testBuscarUsuariosPorPerfil() throws LdapExcepcion {

		System.out.println("");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~ Ldap Test: método de prueba 'testBuscarUsuariosPorPerfil()'.");
		System.out.println("~ Se prueba el  método 'ldapServicio.buscarUsuariosPorPerfil()'.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		Collection<UsuarioLdapDto> list = ldapServicio.buscarUsuariosPorPerfil("Supervisor");
		Collection<UsuarioLdapDto> list2 = ldapServicio.buscarUsuariosPorPerfil("AdminValor");
		Collection<UsuarioLdapDto> list3 = ldapServicio.buscarUsuariosPorPerfil("Analista");
	    
	    Assert.assertTrue(!list.isEmpty());
	}

	@Test
	public void testAuthenticationTodosLosUsuariosYAmbientes() {

		System.out.println("");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~ Ldap Test: método de prueba 'testAuthenticationTodosLosUsuariosYAmbientes()'.");
		System.out.println("~ Se prueba el  método 'ldapServicio.buscarUsuarioPorUid()'.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		String[] entornos = {"desa3"}; //, "inte", "pau", "prod"};

		String [] usuarioYPass = {
					"SHV562201", "Telecom01!",		// Leandro 
					"SHV562163", "Telecom01!",		// Fernando
					"SHV529234", "Telecom01!",		// Hernán
					"SHV564030", "Telecom02!",		// Pablo
					"SHV564027", "Telecom03!",		// Nadia
					"SHV566205", "Telecom01!",		// Tomas
					"SHV562832", "Telecom01!",		// Daniela Bendersky
					"SHV182809", "Telecom01!",		// Damiana Pastorini 
					"SHV500137", "Telecom01!",		// Pablo Alderete 
					"SHV518416", "Telecom01!",		// Lucho Yang
					"SHV567416", "Telecom01!",		// Javier Mariano Alvarez
					"SHV568815", "Telecom01!",		// Pedro Luis Bourgaud
					"SHV197612", "Telecom01!",		// Carolina Natalia Perez
					"SHV567418", "Telecom01!",		// Charlot Astri Araujo Calixtro
					"SHV567711", "Telecom01!",		// Flavia Romina Larrubia
					"SHV566054", "Telecom01!",		// Gerardo Damian Tagliani
					"SHV573005", "Telecom2015!",	// Fabio
					"SHV525214", "Telecom01!",		// Juan Villagra
					"SHV576779", "Telecom2015!",	// Lucas
					"SHV572487", "Telecom01!",		// Guido	
					"SHV577859", "Telecom2015!",	// Eric
					"SHV586743", "Telecom01!",		// Juan C
					"SHV585863", "Telecom01!",		// Juan L
					"SHV578936", "Telecom01!",		// Maxi
					"SHV579607", "Telecom01!",		// Brian
					"SHV505222", "Telecom01!"		// Bibi
		};

		for (String entorno: entornos) {

			System.setProperty(Constantes.PROPIEDAD_ENTORNO, entorno);
			
			System.out.println("");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~ LDAP Test: Entorno de prueba: " + entorno);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			int i=0;
			while (i < usuarioYPass.length) {
				
				String usuario = usuarioYPass[i];
				String pass    = usuarioYPass[++i];
				
				System.out.println("");
				System.out.println("Usuario: " + usuario + " (contraseña: " + pass + ")");

		        UsuarioLdapDto usuarioLdap;
				try {
					usuarioLdap = ldapServicio.buscarUsuarioPorUid(usuario);

					if (usuarioLdap != null) {
						System.out.println("Nombre y apellido: " + usuarioLdap.getNombreCompleto());
						System.out.println("Mail: " + usuarioLdap.getMail());
		
						Authentication auth = new UsernamePasswordAuthenticationToken(usuario, pass);
						Authentication object = authenticationManager.authenticate(auth);
		
						if (object.getAuthorities().isEmpty() ) {
							System.out.println("No existen roles asignados a este usuario");
						} else {
							System.out.println("Roles: " + object.getAuthorities());
						}
					} else {
						System.out.println("Este usuario no se encuentra configurado en Ldap.");
					}

				} catch (AuthenticationException e) {
					System.out.println("Error de credenciales: Usuario o contraseña incorrectos!");
				}
				catch (LdapExcepcion e) {
					Traza.error(LdapTest.class, "Se ha producido un error", e);
				} 
				
				i++;
			}
		}
	}
}
