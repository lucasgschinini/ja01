package ar.com.telecom.shiva.negocio.menu.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.menu.IMenuServicio;
import ar.com.telecom.shiva.persistencia.modelo.ShvMnuMenu;
import ar.com.telecom.shiva.persistencia.modelo.ShvMnuMenuPerfil;
import ar.com.telecom.shiva.persistencia.modelo.ShvSegPerfil;
import ar.com.telecom.shiva.persistencia.repository.PerfilRepositorio;
import ar.com.telecom.shiva.presentacion.bean.dto.menu.MenuDto;
import ar.com.telecom.shiva.presentacion.bean.dto.menu.MenuNivel0Dto;
import ar.com.telecom.shiva.presentacion.bean.dto.menu.MenuNivel1Dto;
import ar.com.telecom.shiva.presentacion.bean.dto.menu.MenuNivelDto;

public class MenuServicioImpl implements IMenuServicio {
	
	@Autowired
	private PerfilRepositorio perfilRepositorio;
	
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly=true)
	public MenuDto obtenerMenuPerfil(String perfilSesion) 
			throws NegocioExcepcion {
		
		MenuDto menu = new MenuDto();
		
		try {
			String[] perfilesLDAP = dividirEnPerfiles(perfilSesion);
			
			// 1) Preparo todos los niveles 0
			for (String perfilLDAP : perfilesLDAP) {
				String idPerfilAplicativo= dividirAtributosPerfil(perfilLDAP)[Constantes.POSICION_PERFIL_LDAP];
				List<ShvSegPerfil> perfilesBD = perfilRepositorio.buscarPerfil(idPerfilAplicativo.toUpperCase());
				
				if (Validaciones.isCollectionNotEmpty(perfilesBD)) {
					for (ShvSegPerfil perfilBD : perfilesBD) {
						if (!Validaciones.isObjectNull(perfilBD)) {
							if (Validaciones.isCollectionNotEmpty(perfilBD.getMenuPerfil())) {
								
								for (ShvMnuMenuPerfil o : perfilBD.getMenuPerfil()) {
									ShvMnuMenu shvMenu = o.getMenu();
							    	
							    	if (!Validaciones.isObjectNull(shvMenu) 
							    		&& !Validaciones.isObjectNull(shvMenu.getMenuPadre())) {
							    		
							    		MenuNivel0Dto nivel0 = new MenuNivel0Dto();
							    		nivel0.setId(shvMenu.getMenuPadre().getIdMenu());
							    		nivel0.setDescripcion(shvMenu.getMenuPadre().getDescripcion());
							    		nivel0.setOrden(shvMenu.getMenuPadre().getOrden());
							    		menu.getNivel0().put(nivel0.getId(), nivel0);
							    	}
							    }
							}
						}
					} 
				}
			} // 1)
			
			// 2) Vuelvo a iterar para preparar los niveles 1 en sus correspondientes niveles 0
			for (String perfilLDAP : perfilesLDAP) {
				String idPerfilAplicativo= dividirAtributosPerfil(perfilLDAP)[Constantes.POSICION_PERFIL_LDAP];
				List<ShvSegPerfil> perfilesBD = perfilRepositorio.buscarPerfil(idPerfilAplicativo.toUpperCase());
			
				if (Validaciones.isCollectionNotEmpty(perfilesBD)) {
					for (ShvSegPerfil perfilBD : perfilesBD) {
						for (ShvMnuMenuPerfil o : perfilBD.getMenuPerfil()) {
							
							ShvMnuMenu shvMenuNivel1 = o.getMenu();
							if (!Validaciones.isObjectNull(shvMenuNivel1) 
						    		&& !Validaciones.isObjectNull(shvMenuNivel1.getMenuPadre())) {
					    			
						    	MenuNivel0Dto menuNivel0 = buscarMenuNivel0(menu, shvMenuNivel1.getMenuPadre());
					    		
						    	MenuNivel1Dto nivel1 = new MenuNivel1Dto();
						    	nivel1.setId(shvMenuNivel1.getIdMenu());
					    		nivel1.setDescripcion(shvMenuNivel1.getDescripcion());
					    		nivel1.setUrlAcceso(shvMenuNivel1.getUrlAcceso());
					    		nivel1.setOrden(shvMenuNivel1.getOrden());
					    		menuNivel0.getNivel1().put(nivel1.getId(), nivel1);
						    	
					    		menu.getNivel0().put(menuNivel0.getId(), menuNivel0);
					    	}
						}
					} // 2)
					
				} // If perfiles BD
				
			} // For Perfiles LDAP
			
			
			//Ordena todo el menú según el orden
			if (Validaciones.isMapNotEmpty(menu.getNivel0())) {
				
				//Ordeno los items del nivel 0
				menu.setNivel0(ordenarItemsxNivel0(menu.getNivel0()));
				
				//Ordeno los items del nivel 1 
				Iterator<?> it = menu.getNivel0().entrySet().iterator();
				while(it.hasNext()) {
				   	Map.Entry e = (Map.Entry)it.next();
				    MenuNivel0Dto o = (MenuNivel0Dto)e.getValue();
				    
				    if (Validaciones.isMapNotEmpty(o.getNivel1())) {
				    	o.setNivel1(ordenarItemsxNivel1(o.getNivel1()));
				    }
			    }
			}
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
		return menu;
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
	/**
	 * Verifico que si el item del menu no se encuentra y puede insertar
	 * @param 
	 * @return 
	 */
	@SuppressWarnings("rawtypes")
	private MenuNivel0Dto buscarMenuNivel0(MenuDto menu, ShvMnuMenu mnu) {
		MenuNivel0Dto item = new MenuNivel0Dto();
		item.setDescripcion(mnu.getDescripcion());
		
		Iterator<?> it = menu.getNivel0().entrySet().iterator();
		while(it.hasNext()) {
		   	Map.Entry e = (Map.Entry)it.next();
		    Integer key = (Integer)e.getKey();
		    item = (MenuNivel0Dto)e.getValue();
		    
			if (key.compareTo(mnu.getIdMenu())==0) {
		    	return item;	
		    }
		} 
		return item; 
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<Integer, MenuNivel0Dto> ordenarItemsxNivel0(Map<Integer, MenuNivel0Dto> map) {
		 
		List list = new LinkedList(map.entrySet());
 
		// sort list based on comparator
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				MenuNivelDto m1 = (MenuNivelDto) ((Map.Entry) (o1)).getValue();
				MenuNivelDto m2 = (MenuNivelDto) ((Map.Entry) (o2)).getValue();
				
				return m1.getOrden().compareTo(m2.getOrden());
			}
		});
		
		// put sorted list into map again
        // LinkedHashMap make sure order in which keys were inserted
		Map<Integer,MenuNivel0Dto> sortedMap = new LinkedHashMap<Integer, MenuNivel0Dto>();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			
			sortedMap.put((Integer)entry.getKey(), 
					(MenuNivel0Dto) entry.getValue());
		}
		return sortedMap;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<Integer, MenuNivel1Dto> ordenarItemsxNivel1(Map<Integer, MenuNivel1Dto> map) {
		 
		List list = new LinkedList(map.entrySet());
 
		// sort list based on comparator
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				MenuNivelDto m1 = (MenuNivelDto) ((Map.Entry) (o1)).getValue();
				MenuNivelDto m2 = (MenuNivelDto) ((Map.Entry) (o2)).getValue();
				
				return m1.getOrden().compareTo(m2.getOrden());
			}
		});
		
		// put sorted list into map again
        // LinkedHashMap make sure order in which keys were inserted
		Map<Integer,MenuNivel1Dto> sortedMap = new LinkedHashMap<Integer, MenuNivel1Dto>();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			
			sortedMap.put((Integer)entry.getKey(), 
					(MenuNivel1Dto) entry.getValue());
		}
		return sortedMap;
	}
}
