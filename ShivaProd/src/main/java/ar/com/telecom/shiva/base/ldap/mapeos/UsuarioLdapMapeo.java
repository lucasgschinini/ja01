package ar.com.telecom.shiva.base.ldap.mapeos;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import ar.com.telecom.shiva.base.ldap.elementos.UsuarioLdap;

public class UsuarioLdapMapeo implements AttributesMapper {

	@Override
	public Object mapFromAttributes(Attributes attributes)
			throws NamingException {
		
		UsuarioLdap usuario = new UsuarioLdap();
		 
		Attribute tuid = attributes.get("uid");
		Attribute nombreCompleto = attributes.get("fullName");
		Attribute mail = attributes.get("mail");
		
		usuario.setTuid(tuid!=null?((String)tuid.get()).toUpperCase():"");
		usuario.setNombreCompleto(nombreCompleto!=null?(String)nombreCompleto.get():"");
		usuario.setMail(mail!=null?(String)mail.get():"");
		
		//TODO sacar roles;
		
		return usuario;
	}

}
