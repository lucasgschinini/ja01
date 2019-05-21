package ar.com.telecom.shiva.presentacion.seguridad;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import ar.com.telecom.shiva.base.utils.Validaciones;

public class CsrfSecurityRequestMatcher implements RequestMatcher {

	private List<String> unprotectedUrls;
	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
	
    
	@Override
	public boolean matches(HttpServletRequest request) {
		
		if (Validaciones.isCollectionNotEmpty(unprotectedUrls)) {
			for (String url: unprotectedUrls) {
				RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher(url, null);
				if (unprotectedMatcher.matches(request)) {
			    	return false;
				}
			}
		}
		
		// No CSRF due to allowedMethod
		if(allowedMethods.matcher(request.getMethod()).matches()){
            return false;
		}
       
		// CSRF for everything else that is not an API call or an allowedMethod
		return true;
    }


	public List<String> getUnprotectedUrls() {
		return unprotectedUrls;
	}


	public void setUnprotectedUrls(List<String> unprotectedUrls) {
		this.unprotectedUrls = unprotectedUrls;
	}
}
