package ar.com.telecom.shiva.presentacion.seguridad;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import ar.com.telecom.shiva.base.aspectos.util.Invocacion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ControlVariablesSingleton;

public class CustomAuthenticationFailureHandler 
	extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(
	        HttpServletRequest request, HttpServletResponse response,
	        AuthenticationException exception)
	        throws IOException, ServletException {
	
	    super.onAuthenticationFailure(request, response, exception);
	
	    String lastUserName = request.getParameter(Constantes.SECURITY_USERNAME);
	    HttpSession session = request.getSession(false);
	    if (session != null || isAllowSessionCreation()) {
	        request.getSession().setAttribute(Constantes.SECURITY_USERNAME, lastUserName);
	    }
	    
	    Date fechaActual = Utilidad.obtenerFechaActual();
	    Date primerIntento = null;
	    Date primerIntentoSumMin = null;
	    String pass = request.getParameter(Constantes.SECURITY_PASSWORD).trim();
	    if (ControlVariablesSingleton.getPassIntentosLogin().containsKey(pass)) {
	    	primerIntento  = ControlVariablesSingleton.getPassIntentosLogin().get(pass).getFechaPrimeraInvocacion();
	    	primerIntentoSumMin = Utilidad.sumMinutos(primerIntento, Constantes.MINUTOS_INTENTOS_LOGIN_MISMA_PASS);
	    	if (Utilidad.seEncuentraEnRango(primerIntento, primerIntentoSumMin, fechaActual)) {
	    		ControlVariablesSingleton.getPassIntentosLogin().get(pass).incrementarCantidad();
	    		if (ControlVariablesSingleton.getPassIntentosLogin().get(pass).getCantidad() >= Constantes.CANTIDAD_INTENTOS_LOGIN_MISMA_PASS) {
	    			Traza.posibleAtaqueBruteForce(getClass(),"Authentication failed. Posible ataque brute-force: "
	    					+ "Intento de acceso con misma password en más de " + Constantes.CANTIDAD_INTENTOS_LOGIN_MISMA_PASS + " usuarios, "
	    							+ "en los últimos " + Constantes.MINUTOS_INTENTOS_LOGIN_MISMA_PASS + " minuto/s.");
	    			ControlVariablesSingleton.getPassIntentosLogin().get(pass).setCantidad(1);
	    		}
	    	} else {
	    		ControlVariablesSingleton.getPassIntentosLogin().get(pass).setCantidad(1);
	    		ControlVariablesSingleton.getPassIntentosLogin().get(pass).setFechaPrimeraInvocacion(fechaActual);
	    	}
	    } else {
	    	ControlVariablesSingleton.getPassIntentosLogin().put(pass, new Invocacion(1, fechaActual));
	    }
	    
//	    for (String key : ControlVariablesSingleton.getPassIntentosLogin().keySet()) {
//	    	primerIntento = ControlVariablesSingleton.getPassIntentosLogin().get(key).getFechaPrimeraInvocacion();
//	    	primerIntentoSumMin = Utilidad.sumMinutos(primerIntento, Constantes.MINUTOS_INTENTOS_LOGIN_MISMA_PASS);
//	    	if (Utilidad.obtenerFechaEnMiliSegundos(fechaActual).compareTo(Utilidad.obtenerFechaEnMiliSegundos(primerIntentoSumMin)) >= 0) {
//	    		ControlVariablesSingleton.getPassIntentosLogin().remove(key);
//	    	}
//	    }
	    
	    String upperUserName =  lastUserName.toUpperCase();
	    if (Validaciones.isNullOrEmpty(upperUserName)) {
	    	upperUserName = Constantes.UNDEFINED.toUpperCase();
	    }
	    
	    if (ControlVariablesSingleton.getUsuarioIntentosLogin().containsKey(upperUserName)) {
	    	ControlVariablesSingleton.getUsuarioIntentosLogin().get(upperUserName).incrementarCantidad();
	    } else {
	    	ControlVariablesSingleton.getUsuarioIntentosLogin().put(upperUserName, new Invocacion(1, fechaActual));
	    }
	    
	    
	}
}
