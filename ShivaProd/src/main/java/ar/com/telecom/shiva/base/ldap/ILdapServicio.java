package ar.com.telecom.shiva.base.ldap;

import java.util.Collection;

import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.ldap.elementos.RolLdap;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public interface ILdapServicio {

	/**
	 * Me trae una lista de usuarios LDAP x Empresa y segmento
	 * @return
	 * @throws LdapExcepcion
	 */
	Collection<UsuarioLdapDto> buscarUsuariosPorEmpresaSegmento(String empresa, String segmento) throws LdapExcepcion;
	
	/**
	 * Me trae una lista de usuarios LDAP x Perfil
	 * @return
	 * @throws LdapExcepcion
	 */
	Collection<UsuarioLdapDto> buscarUsuariosPorPerfil(String perfil) throws LdapExcepcion;
	
	/**
	 * Me trae una lista de usuarios LDAP x perfil usando la memoria
	 * Si no lo encuentra en la memoria, se consulta por el servicio buscarUsuariosPorPerfil 
	 * @return
	 * @throws LdapExcepcion
	 */
	Collection<UsuarioLdapDto> buscarUsuariosPorPerfilEnMemoria(String perfil) throws LdapExcepcion;
	
	/**
	 * Me trae un usuario LDAP x numero tuid
	 * @return
	 * @throws LdapExcepcion
	 */
	UsuarioLdapDto buscarUsuarioPorUid(String tuid) throws LdapExcepcion;
	
	/**
	 * Me trae un usuario LDAP x numero tuid usando la memoria
	 * Si no lo encuentra en la memoria, se consulta por el servicio buscarUsuarioPorUid 
	 * @return
	 * @throws LdapExcepcion
	 */
	UsuarioLdapDto buscarUsuarioPorUidEnMemoria(String tuid) throws LdapExcepcion;
	
	/**
	 * Me trae todos los roles asociados a un tuid
	 * @param tuid
	 * @return
	 * @throws LdapExcepcion
	 */
	Collection<RolLdap> buscarRolesAsociadosAUid(String tuid) throws LdapExcepcion;
	
}
