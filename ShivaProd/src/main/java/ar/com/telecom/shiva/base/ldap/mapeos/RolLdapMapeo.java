package ar.com.telecom.shiva.base.ldap.mapeos;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.ldap.elementos.RolLdap;
import ar.com.telecom.shiva.base.ldap.elementos.UsuarioLdap;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class RolLdapMapeo implements AttributesMapper {
	
	@Override
	public Object mapFromAttributes(Attributes attributes)
			throws NamingException {
		
		RolLdap rol = new RolLdap();
		Attribute tValue = attributes.get("tValue");
		
		if (!Validaciones.isObjectNull(tValue)) {
			String role = (String)tValue.get();
			
			if (!Validaciones.isNullOrEmpty(role)) {
				
				String roleAux = role.toUpperCase().replace((Constantes.APLICACION_ENTORNO.toUpperCase()+"_"), "");
				rol.setDescripcion(roleAux);
				
				int cantUsuarios = attributes.get("equivalenttome")==null?0:attributes.get("equivalenttome").size();
				for (int i=0;i < cantUsuarios;i++) {
					String cn = (String)attributes.get("equivalenttome").get(i);
					String tuid = cn.split(",")[0].split("=")[1];
					
					if (!Validaciones.isNullOrEmpty(tuid))  {
						UsuarioLdap usuario = new UsuarioLdap();
						usuario.setTuid(((String)tuid).toUpperCase());
						//usuario.setNombreCompleto();
					
						rol.getMiembros().add(usuario);
					}
				}
			}
		}
		
		return rol;
	}

}
