package ar.com.telecom.shiva.presentacion.seguridad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import ar.com.telecom.shiva.base.utils.Utilidad;

public class AjaxOWebAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint { 
	
	public AjaxOWebAuthenticationEntryPoint(final String loginFormUrl) {
	    super(loginFormUrl);
	}
	  
	@Override
	public void commence(HttpServletRequest request, 
			HttpServletResponse response, 
			AuthenticationException authException) throws IOException, ServletException 
	{
		if (Utilidad.isAjax(request)) {
			response.sendError(HttpStatus.FORBIDDEN.value(), "Problemas de autenticacion");
		} else {
			super.commence(request, response, authException);
		}
	}
	
	
}