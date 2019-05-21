package ar.com.telecom.shiva.presentacion.seguridad;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.octo.captcha.service.multitype.MultiTypeCaptchaService;

public class CustomAuthenticationDetailsHandler 
	implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
    
	private MultiTypeCaptchaService captchaService;
	
	@Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new CustomWebAuthenticationDetails(context, captchaService);
    }
	
	public MultiTypeCaptchaService getCaptchaService() {
		return captchaService;
	}

	public void setCaptchaService(MultiTypeCaptchaService captchaService) {
		this.captchaService = captchaService;
	}
}
