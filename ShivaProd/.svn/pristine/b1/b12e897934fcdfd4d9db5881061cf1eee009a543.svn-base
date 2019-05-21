package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.menu.IMenuServicio;
import ar.com.telecom.shiva.negocio.menu.IPerfilServicio;
import ar.com.telecom.shiva.negocio.servicios.IPermisosServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.menu.MenuDto;

public class PermisosServicioImpl implements IPermisosServicio {
	
	@Autowired 
	IMenuServicio menuServicio;
	
	@Autowired 
	IPerfilServicio perfilServicio;
	
	@Override
	public MenuDto obtenerMenuPerfil(String perfilSesion)
			throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(perfilSesion)) {
			return menuServicio.obtenerMenuPerfil(perfilSesion);
		} else {
			return new MenuDto();
		}
	}

	@Override
	public Collection<String> obtenerAccionesDePerfiles(String strRoles)
			throws NegocioExcepcion {
		
		return perfilServicio.obtenerAccionesDePerfiles(strRoles);
	}

	/********************************************************************************
	 * Getters & Setters
	 *******************************************************************************/
	public IMenuServicio getMenuServicio() {
		return menuServicio;
	}

	public void setMenuServicio(IMenuServicio menuServicio) {
		this.menuServicio = menuServicio;
	}

	public IPerfilServicio getPerfilServicio() {
		return perfilServicio;
	}

	public void setPerfilServicio(IPerfilServicio perfilServicio) {
		this.perfilServicio = perfilServicio;
	}
	
}
