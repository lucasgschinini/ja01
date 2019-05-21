package ar.com.telecom.shiva.presentacion.seguridad;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;

public class QueryStringPropagateRedirectStrategy extends
		DefaultRedirectStrategy {

	
	public void sendRedirect(HttpServletRequest request,
            HttpServletResponse response, String url) throws IOException {
       
		String idUsuario = request.getParameter(Constantes.SECURITY_USERNAME);
		if (!Validaciones.isNullOrEmpty(idUsuario)) {
			
			HttpServletRequest req = (HttpServletRequest) request;
			
			UsuarioSesion usuarioSesion = new UsuarioSesion(req.getSession().getId(), idUsuario, null);
			TokenSessionListener.addListSesionesAbiertas(usuarioSesion, false);
			
			request.getSession().setAttribute(Constantes.SECURITY_USERNAME, idUsuario);
		}
		
        super.sendRedirect(request, response, url);
    }
}
