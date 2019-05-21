package ar.com.telecom.shiva.base.constantes;

import java.util.Collection;
import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class Memoria {

	private static ConcurrentMap<String, ObjetoUsuarioLdap> listaUsuariosLdap 
				= new ConcurrentHashMap<String, ObjetoUsuarioLdap> ();
	
	private static ConcurrentMap<String, ListaUsuarioLdapPerfil> listaPerfiles 
				= new ConcurrentHashMap<String, ListaUsuarioLdapPerfil> ();
	
	//----------------- Singleton ------------------
	private static Memoria SINGLETON = null;
	
	// Constructor privado
	private Memoria() {}
	
    // creador sincronizado para protegerse de posibles problemas  
	// multi-hilo para evitar instanciación múltiple
    private synchronized static void createInstance() {
        if (SINGLETON == null) { 
            SINGLETON = new Memoria();
        }
    }
    public static Memoria getInstance() {
        if (SINGLETON == null) createInstance();
        return SINGLETON;
    }
    //----------------- fin - Singleton ------------------
	 
    /**
	 * Elimino el usuario viejo y inserto el nuevo usuario
	 * @param dto
	 */
	public static synchronized void insertarUsuarioLdap(UsuarioLdapDto dto) {
		borrarUsuariosLdapExpirados(listaUsuariosLdap);
    	
    	ObjetoUsuarioLdap item = new ObjetoUsuarioLdap();
    	item.setUsuarioLdap(dto);
		item.setTimeStartSesion(new Date());
		listaUsuariosLdap.put(dto.getTuid(), item);
	}
	
	/**
	 * Busco el usuario en la memoria
	 * @param tuid
	 * @return
	 */
	public static UsuarioLdapDto buscarUsuarioLdap(String tuid) {
		borrarUsuariosLdapExpirados(listaUsuariosLdap);
		
    	if (Validaciones.isMapNotEmpty(listaUsuariosLdap)) {
    		ObjetoUsuarioLdap item = listaUsuariosLdap.get(tuid);
			if (item != null) {
				return item.getUsuarioLdap();
			}
		} 
		return null;
	}
	
	
	/**
     * Incorporo una nueva lista de usuarios del perfil
     * @param id
     * @param lista ldap
     */
    public static synchronized void insertarListaUsuariosDelPerfil(String perfil, Collection<UsuarioLdapDto> lista) {
    	borrarItemsExpirados(listaPerfiles);
    	
    	ListaUsuarioLdapPerfil item = new ListaUsuarioLdapPerfil();
    	item.setLista(lista);
		item.setTimeStartSesion(new Date());
		listaPerfiles.put(perfil, item);
	}
    
	
	/**
     * Tomo la lista de usuarios asociados al perfil de la memoria
     * @param id
     */
    public static Collection<UsuarioLdapDto> buscarListaUsuariosDelPerfil(String perfil) {
    	borrarItemsExpirados(listaPerfiles);
    	if (Validaciones.isMapNotEmpty(listaPerfiles)) {
    		ListaUsuarioLdapPerfil item = listaPerfiles.get(perfil);
			if (item != null) {
				return item.getLista();
			}
		} 
		return null;
	}
	
	/**
     * Metodo generico que borra todos los items expirados
     * @param lista
     */
    private static void borrarItemsExpirados(ConcurrentMap<String, ListaUsuarioLdapPerfil> list) {	
		if (Validaciones.isMapNotEmpty(list)) {
			for (Entry<String, ListaUsuarioLdapPerfil> e: list.entrySet()) {
				String id = (String) e.getKey();
				ListaUsuarioLdapPerfil item = (ListaUsuarioLdapPerfil) e.getValue();
				
				if (!Validaciones.isObjectNull(item)) {	
					if (item.getTimeStartSesion() != null) {
						long diferencia = Utilidad.calcularDiferenciaDeFechas(item.getTimeStartSesion(), new Date());
						if (diferencia > Constantes.$1_HORA_MS) {
							list.remove(id);
						}
					} else {
						//Si la fecha de comienzo de la sesion es nula, lo borro
						list.remove(id);
					}
				} else {
					//Si la lista es nula, lo borro
					list.remove(id);
				}
			}
		}
    }
    
    /**
     * Metodo generico que borra todos los items expirados
     * @param lista
     */
    private static void borrarUsuariosLdapExpirados(ConcurrentMap<String, ObjetoUsuarioLdap> list) {	
		if (Validaciones.isMapNotEmpty(list)) {
			for (Entry<String, ObjetoUsuarioLdap> e: list.entrySet()) {
				String id = (String) e.getKey();
				ObjetoUsuarioLdap item = (ObjetoUsuarioLdap) e.getValue();
				
				if (!Validaciones.isObjectNull(item)) {	
					if (item.getTimeStartSesion() != null) {
						long diferencia = Utilidad.calcularDiferenciaDeFechas(item.getTimeStartSesion(), new Date());
						if (diferencia > Constantes.$1_HORA_MS) {
							list.remove(id);
						}
					} else {
						//Si la fecha de comienzo de la sesion es nula, lo borro
						list.remove(id);
					}
				} else {
					//Si el objeto es nulo, lo borro
					list.remove(id);
				}
			}
		}
    }
    
    /**
     * Objeto inner 
     */
    public static class ListaUsuarioLdapPerfil {
    	private Collection<UsuarioLdapDto> lista;
    	private Date timeStartSesion;
    	
    	public ListaUsuarioLdapPerfil() {
    	}
		
    	public Collection<UsuarioLdapDto> getLista() {
			return lista;
		}
		public void setLista(Collection<UsuarioLdapDto> lista) {
			this.lista = lista;
		}
		public Date getTimeStartSesion() {
			return timeStartSesion;
		}
		public void setTimeStartSesion(Date timeStartSesion) {
			this.timeStartSesion = timeStartSesion;
		}
    }
    
    
    /**
     * Objeto inner 
     */
    public static class ObjetoUsuarioLdap {
    	private UsuarioLdapDto usuarioLdap;
    	private Date timeStartSesion;
    	
    	public ObjetoUsuarioLdap() {
    	}
		
    	public Date getTimeStartSesion() {
			return timeStartSesion;
		}
		public void setTimeStartSesion(Date timeStartSesion) {
			this.timeStartSesion = timeStartSesion;
		}
		public UsuarioLdapDto getUsuarioLdap() {
			return usuarioLdap;
		}
		public void setUsuarioLdap(UsuarioLdapDto usuarioLdap) {
			this.usuarioLdap = usuarioLdap;
		}
    }
}
