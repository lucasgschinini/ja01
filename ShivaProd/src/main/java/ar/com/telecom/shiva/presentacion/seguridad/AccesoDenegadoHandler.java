package ar.com.telecom.shiva.presentacion.seguridad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;

import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class AccesoDenegadoHandler implements org.springframework.security.web.access.AccessDeniedHandler {

	private String accessDeniedUrl;
	
	public AccesoDenegadoHandler() {
	}
 
	public AccesoDenegadoHandler(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}
 
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		String usuario = Utilidad.getUsuarioSesion(request);
		Traza.advertencia(getClass(), usuario, Mensajes.NO_AUTORIZADA);
		response.sendRedirect(response.encodeURL(request.getContextPath() + accessDeniedUrl));
	}
 
	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}
 
	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}
}
