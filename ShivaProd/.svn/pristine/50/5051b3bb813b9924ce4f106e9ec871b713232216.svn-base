package ar.com.telecom.shiva.base.ldap.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.directory.SearchControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Memoria;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.ldap.elementos.RolLdap;
import ar.com.telecom.shiva.base.ldap.elementos.UsuarioLdap;
import ar.com.telecom.shiva.base.ldap.mapeos.RolLdapMapeo;
import ar.com.telecom.shiva.base.ldap.mapeos.UsuarioLdapMapeo;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.RolLdapDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;


public class LdapServicioImpl implements ILdapServicio {
	
	@Autowired
	private LdapTemplate ldapTemplate;
	@Autowired
	private IParametroServicio parametroServicio;
	
	String baseServicio = Propiedades.SHIVA_PROPIEDADES.getString("ldap.base.servicio");
	String baseUsuario = Propiedades.SHIVA_PROPIEDADES.getString("ldap.base.usuario");
	
	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<UsuarioLdapDto> buscarUsuariosPorEmpresaSegmento(String empresa, String segmento) throws LdapExcepcion {
		
		Map<String, UsuarioLdapDto> lista = new HashMap<String, UsuarioLdapDto>();
		
		try {
			
			String filtroTValue = Constantes.APLICACION_ENTORNO +"_*_"+empresa.toLowerCase()+"_"+segmento.toLowerCase();
			String filter = "(&(objectClass=TelecomPermission)(tvalue="+filtroTValue+"))";
			Traza.auditoria(getClass(),"Llamada a LDAP desde el método buscarUsuariosPorEmpresaSegmento con base: " + baseServicio + " y con filter: " + filter);
			List<RolLdap> list = ldapTemplate.search(baseServicio, filter, SearchControls.SUBTREE_SCOPE, new RolLdapMapeo());
			
			for (RolLdap rol: list) {
				String descripcionRol = rol.getDescripcion();
				RolLdapDto rolDto = new RolLdapDto();
				rolDto.setDescripcion(descripcionRol);
				
				for (UsuarioLdap usuario : rol.getMiembros()) {
				
					UsuarioLdapDto dto;
					if (!lista.containsKey(usuario.getTuid())) {
						dto = this.buscarUsuarioPorUidEnMemoria(usuario.getTuid());
					} else {
						dto = lista.get(usuario.getTuid()); 
					}
					dto.getRol().add(rolDto);
					
					lista.put(dto.getTuid(), dto);
				}
			}
		} catch (Throwable e) {
			throw new LdapExcepcion(e);
		}
		
		return lista.values();
	}

	
	@SuppressWarnings("unchecked")
	public Collection<UsuarioLdapDto> buscarUsuariosPorPerfil(String perfil) throws LdapExcepcion {
		
		Map<String, UsuarioLdapDto> lista = new HashMap<String, UsuarioLdapDto>();
		
		try {
			
			String filtroTValue = Constantes.APLICACION_ENTORNO +"_"+perfil+"*";
			String filter = "(&(objectClass=TelecomPermission)(tvalue="+filtroTValue+"))";
			Traza.auditoria(getClass(),"Llamada a LDAP desde el método buscarUsuariosPorPerfil con base: " + baseServicio + " y con filter: " + filter);
			List<RolLdap> list = ldapTemplate.search(baseServicio,filter, SearchControls.SUBTREE_SCOPE, new RolLdapMapeo());
			
			Traza.auditoria(getClass(),"Llamada a LDAP desde el método buscarUsuariosPorPerfil, elementos recuperados:["+ (Validaciones.isMapNotEmpty(lista)?lista.size():0)+"]");
			
			for (RolLdap rol: list) {
				String descripcionRol = rol.getDescripcion();
				RolLdapDto rolDto = new RolLdapDto();
				rolDto.setDescripcion(descripcionRol);
				
				Traza.auditoria(getClass(),"Rol encontrado:" +rolDto.getDescripcion());
				
				for (UsuarioLdap usuario : rol.getMiembros()) {
				
					UsuarioLdapDto dto;
					if (!lista.containsKey(usuario.getTuid())) {

						Traza.auditoria(getClass(),"usuario tuid:"+usuario.getTuid());
						dto = this.buscarUsuarioPorUidEnMemoria(usuario.getTuid());

						Traza.auditoria(getClass(),"descripcion del usuario:" + dto.getNombreCompleto());
					} else {
						dto = lista.get(usuario.getTuid());
						Traza.auditoria(getClass(),"descripcion del usuario:" + dto.getNombreCompleto());
					}
					
					
					dto.getRol().add(rolDto);
					
					lista.put(dto.getTuid(), dto);
				}
				
			}
			
			Traza.auditoria(getClass(), "Buscar usuarios por perfil: " + filtroTValue + " - Resultado: " + (Validaciones.isMapNotEmpty(lista)?lista.size():0));
			
		} catch (Throwable e) {
			Traza.error(getClass(), e.getMessage());
			throw new LdapExcepcion(e);
		}
		
		return lista.values();
	}
	
	public Collection<UsuarioLdapDto> buscarUsuariosPorPerfilEnMemoria(String perfil) throws LdapExcepcion {
		
		Map<String, UsuarioLdapDto> lista = new HashMap<String, UsuarioLdapDto>();
		
		if (!Validaciones.isNullOrEmpty(perfil)) {
			Collection<UsuarioLdapDto> listaMemoria = Memoria.buscarListaUsuariosDelPerfil(perfil);
			if (listaMemoria!= null) {
				Traza.auditoria(getClass(), "Se levantaron de la memoria "+listaMemoria.size()+" Registros.");
				return listaMemoria;
			} else {
				Collection<UsuarioLdapDto> listaLdap = this.buscarUsuariosPorPerfil(perfil);
				Traza.auditoria(getClass(), "Se levantaron de ldap "+listaLdap.size()+" Registros.");
				if (listaLdap!=null) {
					Memoria.insertarListaUsuariosDelPerfil(perfil, listaLdap);
				}	
				return listaLdap;
			}
		}
		return lista.values();
	}
	
	
	@SuppressWarnings("unchecked")
	public UsuarioLdapDto buscarUsuarioPorUid(String tuid) throws LdapExcepcion {
		if (!Validaciones.isNullOrEmpty(tuid)) {
			
			UsuarioLdapDto dto = new UsuarioLdapDto();
			try {
				String filter = "(&(objectClass=TelecomPerson)(cn=" + tuid + "))";
				Traza.auditoria(getClass(),"Llamada a LDAP desde el método buscarUsuarioPorUid con base: " + baseUsuario + " y con filter: " + filter);
				List<UsuarioLdap> list = ldapTemplate.search(baseUsuario, filter, SearchControls.SUBTREE_SCOPE, new UsuarioLdapMapeo());
			
				
				if (!Validaciones.isCollectionNotEmpty(list)) {
					
					Traza.auditoria(getClass(), "No se encontraron usuariosLdap, se va a buscar el usuario batch");
					
					buscarUsuarioBatchPorUidEnShiva(dto,tuid);
					
					Traza.auditoria(getClass(), "Se encontro el usuario batch: " +dto.getNombreCompleto());
					
					return dto;
				}
				

				Traza.auditoria(getClass(),"Llamada a LDAP ha retornado ["+list.size()+"] usuarios");
				
				UsuarioLdap usuario = list.get(0);
				dto.setId(usuario.getTuid());
				dto.setTuid(usuario.getTuid());
				dto.setNombreCompleto(usuario.getNombreCompleto());
				dto.setMail(usuario.getMail());
				

				Traza.auditoria(getClass(),"Llamada a LDAP ha retornado el usuario "
						+ "[ id: " + dto.getId() + "|tuid:" + dto.getTuid()
						+ " | nombre: " + dto.getNombreCompleto()
						+ " | mail: " + dto.getMail() + "]");
				
			} catch (Throwable e) {
				Traza.error(getClass(), e.getMessage());
				throw new LdapExcepcion("Error al buscar el usuario: " + tuid, e);
			}
			
			return dto;
		}
		return null;
	}
public void buscarUsuarioBatchPorUidEnShiva(UsuarioLdapDto dto, String tuid) throws NegocioExcepcion{
	 
	dto.setId(tuid);
	dto.setTuid(tuid);
	dto.setMail("");
	
	if (parametroServicio.getValorTexto(Constantes.USUARIO_BATCH).equalsIgnoreCase(tuid)) {
		
		dto.setNombreCompleto(parametroServicio.getValorTexto(Constantes.USUARIO_NOMBRE_BATCH));
		
	} else if(parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION).equalsIgnoreCase(tuid)){
		
		dto.setNombreCompleto(parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION_NOMBRE));
		
	} else if(parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_RECEPCION).equalsIgnoreCase(tuid)){
		
		dto.setNombreCompleto(parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_RECEPCION_NOMBRE));
		
	} else if(parametroServicio.getValorTexto(Constantes.USUARIO_SAP).equalsIgnoreCase(tuid)){
			
		dto.setNombreCompleto(parametroServicio.getValorTexto(Constantes.USUARIO_SAP_NOMBRE));
		
		} else if(parametroServicio.getValorTexto(Constantes.USUARIO_MIGRACION).equalsIgnoreCase(tuid)){
			
			dto.setNombreCompleto(parametroServicio.getValorTexto(Constantes.USUARIO_MIGRACION_BATCH));
		
		} else {
			
			dto.setNombreCompleto("Sin Nombre");
		
		}
}

	@Override
	public UsuarioLdapDto buscarUsuarioPorUidEnMemoria(String tuid)
			throws LdapExcepcion {
		if (!Validaciones.isNullOrEmpty(tuid)) {
			UsuarioLdapDto usuarioLdapMemoria = Memoria.buscarUsuarioLdap(tuid);
			if (usuarioLdapMemoria!= null) {
				return usuarioLdapMemoria;
			}
	        
			UsuarioLdapDto dto = this.buscarUsuarioPorUid(tuid);
	        if (dto!=null) {
	        	Memoria.insertarUsuarioLdap(dto);
	        }
	        return dto;
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Collection<RolLdap> buscarRolesAsociadosAUid(String tuid) throws LdapExcepcion {
		
		String filtroTValue = Constantes.APLICACION_ENTORNO + "*"; // + "_*_" + Constantes.EMPRESA_TELECOM_ARGENTINA;		
		
		String filter = "(&(objectClass=TelecomPermission)(tvalue="+filtroTValue+")(equivalentToMe=cn="+tuid+",ou=Personas,ou=Usuarios,o=Telecom))";
		Traza.auditoria(getClass(),"Llamada a LDAP desde el método buscarRolesAsociadosAUid con base: " + baseServicio + " y con filter: " + filter);
		
		
		return ldapTemplate.search(baseServicio, filter, SearchControls.SUBTREE_SCOPE,new RolLdapMapeo());
	}
	
}
	