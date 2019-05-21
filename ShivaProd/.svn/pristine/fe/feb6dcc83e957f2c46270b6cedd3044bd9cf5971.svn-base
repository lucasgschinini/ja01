package ar.com.telecom.shiva.negocio.menu.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.menu.IPerfilServicio;
import ar.com.telecom.shiva.persistencia.modelo.ShvSegPerfil;
import ar.com.telecom.shiva.persistencia.modelo.ShvSegPerfilAplicativoAcciones;
import ar.com.telecom.shiva.persistencia.repository.PerfilRepositorio;

public class PerfilServicioImpl implements IPerfilServicio {
	
	@Autowired
	private PerfilRepositorio perfilRepositorio;
	
	@Override
	@Transactional(readOnly=true)
	public Collection<String> obtenerAccionesDePerfiles(String strRoles) 
			throws NegocioExcepcion {
		
		
		Collection<String> acciones = new ArrayList<String>(); 
				
		if (!Validaciones.isNullOrEmpty(strRoles)) {
			
			String[] perfilesLDAP = dividirEnPerfiles(strRoles);
			
			for (String perfilLDAP : perfilesLDAP) {
				String idPerfilAplicativo= dividirAtributosPerfil(perfilLDAP)[Constantes.POSICION_PERFIL_LDAP];
				List<ShvSegPerfil> perfilesBD = perfilRepositorio.buscarPerfil(idPerfilAplicativo.toUpperCase());
			
				if (Validaciones.isCollectionNotEmpty(perfilesBD)) {
					for (ShvSegPerfil perfilBD : perfilesBD) {
						if (!Validaciones.isObjectNull(perfilBD) && !Validaciones.isObjectNull(perfilBD.getPerfilAplicativo())) {
							if (Validaciones.isCollectionNotEmpty(perfilBD.getPerfilAplicativo().getAcciones())) {
								for (ShvSegPerfilAplicativoAcciones accion : perfilBD.getPerfilAplicativo().getAcciones()) {
									if (!acciones.contains(accion.getAccion().name()) ) {
										if (SiNoEnum.SI.getDescripcion().equals(accion.getTienePermiso().getDescripcion())) {
											acciones.add(accion.getAccion().name());
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		return acciones;
	}
	
	

	/******************************************************************************
	 * Utilidades
	 *****************************************************************************/
	
	/**
	 * Divide al perfil de la session en atributos
	 * @param perfilSesion --> Empresa_Perfil_Segmento
	 * @return {Empresa,Perfil,Segmento}
	 */
	private String[] dividirAtributosPerfil(String perfilSesion) {
		return perfilSesion.split("_");
	}
	
	/**
	 * Divide perfiles de la session en perfiles
	 * @param perfilSesion --> "Empresa_Perfil_Segmento,Empresa_Perfil_Segmento" 
	 * @return {Empresa_Perfil_Segmento,Empresa_Perfil_Segmento}
	 */
	private String[] dividirEnPerfiles(String perfilSesion) {
		return perfilSesion.split(",");
	}
}
