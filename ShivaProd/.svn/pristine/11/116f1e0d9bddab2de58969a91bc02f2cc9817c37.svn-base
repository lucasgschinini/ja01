package ar.com.telecom.shiva.presentacion.seguridad;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.utils.logs.Traza;

import com.octo.captcha.service.multitype.MultiTypeCaptchaService;

@SuppressWarnings("serial")
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
	
	private boolean captchaPassed;

	public CustomWebAuthenticationDetails(HttpServletRequest request, MultiTypeCaptchaService captchaService) {
        super(request);
        String usarCaptcha = request.getParameter("j_usar_captcha");
        
        try {
        	if(SiNoEnum.getEnumByDescripcion(usarCaptcha).equals(SiNoEnum.SI)) {
        		String sessionId = request.getSession().getId();
        	    String jCaptchaValor = request.getParameter("j_captcha_response");
        		
        	    //this.captchaPassed = SimpleImageCaptchaServlet.validateResponse(request, jCaptchaValor);
        		this.captchaPassed = captchaService.validateResponseForID(sessionId, jCaptchaValor);
         	}else{
         		captchaPassed = true;
         	}
        } catch (Exception e) {
        	Traza.error(getClass(),"Se produjo error en el captcha:", e);
        }   
    }

	public boolean isCaptchaPassed() {
		return captchaPassed;
	}

	
}
