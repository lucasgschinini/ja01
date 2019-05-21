package ar.com.telecom.shiva.presentacion.seguridad;

import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;

public class TokenSessionListener implements HttpSessionListener {
	
	private static ConcurrentMap<String, UsuarioSesion> listSesionesAbiertas 
		= new ConcurrentHashMap<String, UsuarioSesion> ();
	
	/**
	 * Algun algoritmo que limpia la lista de sesiones abiertas despues de un buen tiempo.
	 * Para evitar crecientes en la memoria
	 */
	private static void limpiarListaSesionesAbiertas() {	
		
		if (Validaciones.isMapNotEmpty(listSesionesAbiertas)) {
			for (Entry<String, UsuarioSesion> e: listSesionesAbiertas.entrySet()) {
				String idSesion = (String) e.getKey();
				UsuarioSesion usuarioSesion = (UsuarioSesion) e.getValue();
				
				if (!Validaciones.isObjectNull(usuarioSesion)) {	
					if (usuarioSesion.getTimeStartSesion() != null) {
						long diferencia = Utilidad.calcularDiferenciaDeFechas(usuarioSesion.getTimeStartSesion(), new Date());
						if (diferencia > Constantes.$Media_HORA_MS) {
							listSesionesAbiertas.remove(idSesion);
							
							String detalle = Utilidad.reemplazarMensajes(Mensajes.SESION_REMOVE_USUARIO, idSesion, usuarioSesion.getIdUsuario());
							Traza.advertencia(TokenSessionListener.class, Constantes.SHIVA_APLICACION, detalle);
						}
					} else {
						//Si la fecha de comienzo de la sesion es nula, lo borro
						listSesionesAbiertas.remove(idSesion);
						
						String detalle = "(TimeStartSesion - Vacio): " + Utilidad.reemplazarMensajes(Mensajes.SESION_REMOVE_EXPIRED, idSesion);
						Traza.advertencia(TokenSessionListener.class, Constantes.SHIVA_APLICACION, detalle);
					}
				} else {
					//Si el usuarioSesion es nulo, lo borro
					listSesionesAbiertas.remove(idSesion);
					
					String detalle = "(UsuarioSesion - Vacio): " + Utilidad.reemplazarMensajes(Mensajes.SESION_REMOVE_EXPIRED, idSesion);
					Traza.advertencia(TokenSessionListener.class, Constantes.SHIVA_APLICACION, detalle);
				}
			}
		}
	}
	
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public void sessionCreated(HttpSessionEvent event) {	
		HttpSession session = event.getSession();
		UsuarioSesion usuarioSesion = (UsuarioSesion) event.getSession().getAttribute(Constantes.LOGIN);
		if (!Validaciones.isObjectNull(usuarioSesion)) {
			
			addListSesionesAbiertas(usuarioSesion, true);
		}
	}

	/**
	 * 
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		listSesionesAbiertas.remove(session.getId());
	}
	
	/**
	 * Verifico que si existe tal usuario logueado y los demas se invalidaran
	 * @param usuario
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean existeUsuarioLogueado(UsuarioSesion usuario) {
		limpiarListaSesionesAbiertas();
		
		if (Validaciones.isMapNotEmpty(listSesionesAbiertas)) {
			boolean existeUsuarioLogueado = false;
			
			for (Entry e: listSesionesAbiertas.entrySet()) {
				String idSesion = (String) e.getKey();
				UsuarioSesion usuarioSesion = (UsuarioSesion) e.getValue();
				
				//Si encuentro al mismo usuario en otra sesion abierta, 
				//lo mando a sacar de la lista de sesiones abiertas
				if (usuarioSesion.getUsuario().equalsIgnoreCase(usuario.getUsuario())) {
					listSesionesAbiertas.remove(idSesion);
					existeUsuarioLogueado = true;
				}
			}
			
			addListSesionesAbiertas(usuario, false);
			
			return existeUsuarioLogueado;
		} else {
			
			addListSesionesAbiertas(usuario, false);
			
			return false;	
		}
	}
		
	
	/**
	 * Verifico que si este usuario se encuentra en la lista 
	 * y permitir el uso de esta sesion
	 * Verdadero = puede continuar con la sesion
	 * Falso = no puede continuar con la sesion
	 * 
	 * @param usuario
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean permitirEsteUsuario(UsuarioSesion usuario) {
		limpiarListaSesionesAbiertas();
		
		if (Validaciones.isMapNotEmpty(listSesionesAbiertas)) {
			
			for (Entry e: listSesionesAbiertas.entrySet()) {
				
				String idSesion = (String) e.getKey();
				UsuarioSesion usuarioSesion = (UsuarioSesion) e.getValue();
				
				if (!Validaciones.isObjectNull(usuarioSesion)) {
					if (idSesion.equals(usuario.getIdSesion()) 
							&& usuarioSesion.getUsuario().equalsIgnoreCase(usuario.getUsuario())) {
						
						return true;
					}
				}
			}
			
			return false;
		} else {
			return false;	
		}
	}
	
	/**
	 * 
	 * @param usuarioSesion
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public static void addListSesionesAbiertas(UsuarioSesion usuarioSesion, boolean buscar) {
		//Como sigo usando la aplicacion, se posterga la expiracion de la lista de sesiones abiertas
		usuarioSesion.setTimeStartSesion(new Date());
		usuarioSesion.setCurrentThreadName(Thread.currentThread().getName());
		
		if (buscar) {
			if (Validaciones.isMapNotEmpty(listSesionesAbiertas)) {
				for (Entry e: listSesionesAbiertas.entrySet()) {
					String idSesion = (String) e.getKey();
					UsuarioSesion usuario = (UsuarioSesion) e.getValue();
					
					if (usuarioSesion.getUsuario().equalsIgnoreCase(usuario.getUsuario())) {
						
						// lo actualizo
						listSesionesAbiertas.put(usuarioSesion.getIdSesion(), usuarioSesion);
					}
				}
			}
		} else {
			//Para ahorrar tiempo de iteracion
			listSesionesAbiertas.put(usuarioSesion.getIdSesion(), usuarioSesion);
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static String getUsuario() {
		if (Validaciones.isMapNotEmpty(listSesionesAbiertas)) {
			for (Entry<String, UsuarioSesion> e: listSesionesAbiertas.entrySet()) {
				UsuarioSesion usuario = e.getValue();
				
				if (Thread.currentThread().getName().equals(usuario.getCurrentThreadName())) {
					return usuario.getIdUsuario();
				}	
			}
		} 
		return "";
	}
}
