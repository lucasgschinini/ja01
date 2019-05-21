package ar.com.telecom.shiva.presentacion.bean.dto.menu;

import java.util.HashMap;
import java.util.Map;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class MenuDto extends DTO {
	private static final long serialVersionUID = 1L;
	
	private Map<Integer,MenuNivel0Dto> nivel0 = new HashMap<Integer,MenuNivel0Dto>();

	public Map<Integer,MenuNivel0Dto> getNivel0() {
		return nivel0;
	}

	public void setNivel0(Map<Integer,MenuNivel0Dto> nivel0) {
		this.nivel0 = nivel0;
	}
	/**
	 * Verifica si la requestUrl es parte de los url del menu
	 * contextPath tal cual viene del request
	 * @param contextPath
	 * @param requestUrl
	 * @return
	 */
	public boolean isUrlMenu(String contextPath, String requestUrl) {
		boolean es = false;

		int cantida = 0;
		char barra = '/';

		
		for (int index = 0; index < requestUrl.length() ; index++) {
			if ( barra == requestUrl.charAt(index)) {
				cantida++;
			}
		}
		if (cantida != 2) {
			es = false;
		} else {
			// La contextPath le sumo el caracter '/' dado que viene de la forma "/appName/";
			String url = requestUrl.substring(contextPath.length() + 1, requestUrl.length());

			//usuarioSesion.getMenu().getNivel0().values()
			for (MenuNivel0Dto nivel0 : getNivel0().values()) {
				for(MenuNivel1Dto nivel1 : nivel0.getNivel1().values()) {
					if (sacarParametros(nivel1.getUrlAcceso()).equals(url)) {
						return true;
					}
				}
			}
		}
		return es;
	}
	private String sacarParametros(String sr) {
		if (sr.indexOf("&") >= 0) {
			return sr.substring(0, sr.indexOf("&"));
		}
		return sr.substring(0, sr.indexOf("?"));
	}
	/**
	 * Agrega un parametro al url de las opciones de los menu.
	 * Para poder detectar que vino del menu
	 * @return
	 */
	public boolean setMenuParameter() {
		if (Validaciones.isMapNotEmpty(this.getNivel0())) {
			String param = Constantes.IS_MENU_PARAN_NAME + "=" + Constantes.IS_MENU_PARAN_VALUE;
			for (MenuNivel0Dto nivel0 : getNivel0().values()) {
				for(MenuNivel1Dto nivel1 : nivel0.getNivel1().values()) {
					if (nivel1.getUrlAcceso().indexOf('?') >= 0) {
						nivel1.setUrlAcceso(nivel1.getUrlAcceso() + "&" + param);	
					} else {
						nivel1.setUrlAcceso(nivel1.getUrlAcceso() + "?" + param);
					}
				}
			}
		}
		return false;
	}
}
