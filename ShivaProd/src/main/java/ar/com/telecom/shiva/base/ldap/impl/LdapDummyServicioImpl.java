package ar.com.telecom.shiva.base.ldap.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Memoria;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.ldap.elementos.RolLdap;
import ar.com.telecom.shiva.base.ldap.elementos.UsuarioLdap;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.RolLdapDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;


public class LdapDummyServicioImpl implements ILdapServicio {
	
	public Collection<UsuarioLdapDto> buscarUsuariosPorEmpresaSegmento(String empresa, String segmento) throws LdapExcepcion {
		
		Map<String, UsuarioLdapDto> lista = new HashMap<String, UsuarioLdapDto>();
		
		//Rol Analista
		String descripcionRolLdap = "ANALISTA_TA_OGC";
		RolLdap rol1 = new RolLdap();
		rol1.setDescripcion(descripcionRolLdap);
		
		//Usuarios Miembros del rol 
		UsuarioLdap usuarioMiembro = new UsuarioLdap();
		usuarioMiembro.setTuid("SHV562163");
		rol1.getMiembros().add(usuarioMiembro);
		
		usuarioMiembro = new UsuarioLdap();
		usuarioMiembro.setTuid("SHV562832");
		rol1.getMiembros().add(usuarioMiembro);
		
		//ROL Supervisor
		descripcionRolLdap = "SUPERVISOR_TA_OGC";
		RolLdap rol2 = new RolLdap();
		rol2.setDescripcion(descripcionRolLdap);
		
		//Usuarios Miembros del rol 
		usuarioMiembro = new UsuarioLdap();
		usuarioMiembro.setTuid("SHV562201");
		rol2.getMiembros().add(usuarioMiembro);
		
		usuarioMiembro = new UsuarioLdap();
		usuarioMiembro.setTuid("SHV562832");
		rol2.getMiembros().add(usuarioMiembro);
		
		
		List<RolLdap> list = new ArrayList<RolLdap>();
		list.add(rol1);
		list.add(rol2);
		
		try {
			for (RolLdap rol: list) {
				String descripcionRol = rol.getDescripcion();
				RolLdapDto rolDto = new RolLdapDto();
				rolDto.setDescripcion(descripcionRol);
				
				for (UsuarioLdap usuario : rol.getMiembros()) {
				
					UsuarioLdapDto dto;
					if (!lista.containsKey(usuario.getTuid())) {
						dto = this.buscarUsuarioPorUid(usuario.getTuid());
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

	
	public Collection<UsuarioLdapDto> buscarUsuariosPorPerfil(String perfil) 
			throws LdapExcepcion {
		
		Map<String, UsuarioLdapDto> lista = new HashMap<String, UsuarioLdapDto>();
		
		try {
			
			if (Constantes.ANALISTA.equalsIgnoreCase(perfil)) {
				
				String descripcionRolLdap = "ANALISTA_TA_OGC";
				RolLdap rol1 = new RolLdap();
				rol1.setDescripcion(descripcionRolLdap);
				
				//Usuarios Miembros del rol 
				UsuarioLdap usuarioMiembro = new UsuarioLdap();
				usuarioMiembro.setTuid("SHV562163");
				rol1.getMiembros().add(usuarioMiembro);
				
				usuarioMiembro = new UsuarioLdap();
				usuarioMiembro.setTuid("SHV562832");
				rol1.getMiembros().add(usuarioMiembro);
				
				List<RolLdap> list = new ArrayList<RolLdap>();
				list.add(rol1);
				
				for (RolLdap rol: list) {
					String descripcionRol = rol.getDescripcion();
					RolLdapDto rolDto = new RolLdapDto();
					rolDto.setDescripcion(descripcionRol);
					
					for (UsuarioLdap usuario : rol.getMiembros()) {
					
						UsuarioLdapDto dto;
						if (!lista.containsKey(usuario.getTuid())) {
							dto = this.buscarUsuarioPorUid(usuario.getTuid());
						} else {
							dto = lista.get(usuario.getTuid()); 
						}
						dto.getRol().add(rolDto);
						
						lista.put(dto.getTuid(), dto);
					}
				}
			}
			
			if (Constantes.ADMIN_TALONARIO.equalsIgnoreCase(perfil) 
					|| Constantes.ADMIN_VALOR.equalsIgnoreCase(perfil)) {
				
				String descripcionRolLdap = "ADMINTALONARIO";
				RolLdap rol1 = new RolLdap();
				rol1.setDescripcion(descripcionRolLdap);
				
				//Usuarios Miembros del rol 
				UsuarioLdap usuarioMiembro = new UsuarioLdap();
				usuarioMiembro.setTuid("SHV566205");
				rol1.getMiembros().add(usuarioMiembro);
				
				usuarioMiembro = new UsuarioLdap();
				usuarioMiembro.setTuid("SHV562832");
				rol1.getMiembros().add(usuarioMiembro);
				
				usuarioMiembro = new UsuarioLdap();
				usuarioMiembro.setTuid("SHV182809");
				rol1.getMiembros().add(usuarioMiembro);
				
				usuarioMiembro = new UsuarioLdap();
				usuarioMiembro.setTuid("SHV500137");
				rol1.getMiembros().add(usuarioMiembro);
				
				List<RolLdap> list = new ArrayList<RolLdap>();
				list.add(rol1);
				
				for (RolLdap rol: list) {
					String descripcionRol = rol.getDescripcion();
					RolLdapDto rolDto = new RolLdapDto();
					rolDto.setDescripcion(descripcionRol);
					
					for (UsuarioLdap usuario : rol.getMiembros()) {
					
						UsuarioLdapDto dto;
						if (!lista.containsKey(usuario.getTuid())) {
							dto = this.buscarUsuarioPorUid(usuario.getTuid());
						} else {
							dto = lista.get(usuario.getTuid()); 
						}
						dto.getRol().add(rolDto);
						
						lista.put(dto.getTuid(), dto);
					}
				}
			}
			
			if (Constantes.SUPERVISOR_TALONARIO.equalsIgnoreCase(perfil)
					|| Constantes.SUPERVISOR_ADMIN_VALOR.equalsIgnoreCase(perfil)) {
				
				String descripcionRolLdap = "SUPERVISORTALONARIO";
				RolLdap rol1 = new RolLdap();
				rol1.setDescripcion(descripcionRolLdap);
				
				//Usuarios Miembros del rol 
				UsuarioLdap usuarioMiembro = new UsuarioLdap();
				usuarioMiembro.setTuid("SHV500137");
				rol1.getMiembros().add(usuarioMiembro);
				
				usuarioMiembro = new UsuarioLdap();
				usuarioMiembro.setTuid("SHV182809");
				rol1.getMiembros().add(usuarioMiembro);
				
				usuarioMiembro = new UsuarioLdap();
				usuarioMiembro.setTuid("SHV562832");
				rol1.getMiembros().add(usuarioMiembro);
				
				usuarioMiembro = new UsuarioLdap();
				usuarioMiembro.setTuid("SHV564030");
				rol1.getMiembros().add(usuarioMiembro);
				
				List<RolLdap> list = new ArrayList<RolLdap>();
				list.add(rol1);
				
				for (RolLdap rol: list) {
					String descripcionRol = rol.getDescripcion();
					RolLdapDto rolDto = new RolLdapDto();
					rolDto.setDescripcion(descripcionRol);
					
					for (UsuarioLdap usuario : rol.getMiembros()) {
					
						UsuarioLdapDto dto;
						if (!lista.containsKey(usuario.getTuid())) {
							dto = this.buscarUsuarioPorUid(usuario.getTuid());
						} else {
							dto = lista.get(usuario.getTuid()); 
						}
						dto.getRol().add(rolDto);
						
						lista.put(dto.getTuid(), dto);
					}
				}
			}

		} catch (Throwable e) {
			throw new LdapExcepcion(e);
		}
		
		return lista.values();
	}
	
	public UsuarioLdapDto buscarUsuarioPorUid(String tuid) throws LdapExcepcion {
		if (!Validaciones.isNullOrEmpty(tuid)) {
			
			UsuarioLdapDto dto = new UsuarioLdapDto();
			dto.setId(tuid);
			dto.setTuid(tuid);
			
			dto.setNombreCompleto("Leandro Garayoa");
			dto.setMail("leandro@telecom.com.ar");
			
			return dto;
		}
		
		return null;
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
	public Collection<RolLdap> buscarRolesAsociadosAUid(String tuid) throws LdapExcepcion {
		
		List<RolLdap>  listaRoles = new ArrayList<RolLdap>();
		RolLdap rolSupervisor = new RolLdap();
		rolSupervisor.setDescripcion("SUPERVISOR_TA_OGC");
		listaRoles.add(rolSupervisor);
//		RolLdap rolAnalista = new RolLdap();
//		rolAnalista.setDescripcion("ANALISTA_TA_OGC");
//		listaRoles.add(rolAnalista);
		return listaRoles;
	}


	@Override
	public Collection<UsuarioLdapDto> buscarUsuariosPorPerfilEnMemoria(
			String perfil) throws LdapExcepcion {
		
		return this.buscarUsuariosPorPerfil(perfil);
	}
	
}
	