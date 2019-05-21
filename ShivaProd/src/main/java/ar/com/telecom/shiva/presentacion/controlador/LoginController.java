package ar.com.telecom.shiva.presentacion.controlador;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ControlVariablesSingleton;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.multitype.MultiTypeCaptchaService;

@Controller
public class LoginController {
	
	private static final String PAGINA_INGRESAR 	= "sesion/ingresar";
	private static final String PAGINA_PRINCIPAL 	= "sesion/principal";
	private static final String PAGINA_DEFAULT      = "bandeja-de-entrada";
	private static final String PAGINA_EXPIRADA 	= "sesion/expirado";
	private static final String PAGINA_DENEGADA 	= "sesion/denegado";
	
	@Autowired
	private IParametroServicio parametroServicio;
	
	@Autowired
	private MultiTypeCaptchaService captchaService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
	}

	/**
	 * Metodo Generico que limpia y valida por defecto
	 * @param request
	 * @throws Exception
	 */
	private void limpiarValidar(HttpServletRequest request) throws Exception {
		request.getSession().removeAttribute(Constantes.LOGIN);
		SecurityContextHolder.clearContext();
		
		request.getSession().invalidate();
		
		//Verifico si el argumento VM entorno existe 
		if (Validaciones.isNullOrEmpty(Constantes.ENTORNO)) {
			throw new ShivaExcepcion(Mensajes.ENTORNO_VM);
		} 
		
		//Verifico si la version es correcta
		Utilidad.verificarVersion(parametroServicio);
		
		//TODO Validar workflow
	}
	
	/**
	 * Limpia los archivos temporales como FFDC
	 */
	private synchronized void limpiarDirectorios() {
		try {
			String workingDirectory = System.getProperty("user.dir");

			File absoluteFilePath = new File(workingDirectory + File.separator + "FFDC");
			ControlArchivo.eliminarArchivosDelDirectorio(absoluteFilePath);
			
		} catch (Exception e) {
			Traza.error(getClass(), e.getMessage(), e);
		}
	}
	
	/**
	 * Pantalla login
	 * @return
	 * @throws ShivaExcepcion
	 */
	@RequestMapping(value="/ingresar", method = RequestMethod.GET)
	public String ingresar(HttpServletRequest request, ModelMap map) throws Exception {
		limpiarDirectorios();
		
		limpiarValidar(request);
		
		//No muestro el captcha por defecto
		map.addAttribute("mostrarCaptcha", false);
		map.addAttribute("usarCaptcha", SiNoEnum.NO);
		
		//TODO: Verificar el workflow
		
		//Verifico que si cumple con el rango permitido
		String resultadoRango = Utilidad.verificarRangoHorario(parametroServicio);
		if (!Validaciones.isNullOrEmpty(resultadoRango)) {
			
			return rangoHorarioNoPermitido(request, map, resultadoRango);
		}
		
		return PAGINA_INGRESAR;
	}
	
	/**
	 * Pantalla default (bandeja de entrada)
	 * @param request
	 * @param response
	 * @return
	 * @throws ShivaExcepcion
	 * @throws IOException
	 */
	@RequestMapping(value="/principal")
	public String principal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UsuarioSesion usuarioSesion = (UsuarioSesion) 
				request.getSession().getAttribute(Constantes.LOGIN);
		
		ControlVariablesSingleton.getUsuarioIntentosLogin().remove(Constantes.UNDEFINED.toUpperCase());
		if (ControlVariablesSingleton.getUsuarioIntentosLogin().containsKey(usuarioSesion.getIdUsuario())) {
			ControlVariablesSingleton.getUsuarioIntentosLogin().remove(usuarioSesion.getIdUsuario());
		}
		
		//Brian Botones volver
		usuarioSesion.getRetorno().clear();
		usuarioSesion.getRetorno().add("/principal");
		
		if (usuarioSesion.esPerfilConsulta()) {
			return PAGINA_PRINCIPAL;
		} else {
			return "redirect:" + PAGINA_DEFAULT; 
		} 
	}

	//@SuppressWarnings("restriction")
	@RequestMapping(value="/jcaptcha")
    protected ModelAndView jCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
         byte[] captchaChallengeAsJpeg = null;
         ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
         
         try {
            String captchaId = request.getSession().getId();
            
        	  // call the ImageCaptchaService getChallenge method
            BufferedImage challenge = ((ImageCaptchaService)captchaService).getImageChallengeForID(captchaId, request.getLocale());
 
            // a jpeg encoder --> deprecated
            //JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
            //jpegEncoder.encode(challenge);
            
            ImageIO.write(challenge, "jpg", jpegOutputStream);
            
        } catch (IllegalArgumentException e) {
        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        } catch (CaptchaServiceException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
 
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
 
        // flush it in the response
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        //response.setContentType("image/jpeg");

        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
 
        return null;
    }
	
	/**
	 * Pantalla Login con captcha actualizada
	 * @return
	 * @throws ShivaExcepcion
	 */
	@RequestMapping(value="/actualizarCaptcha")
	public String actualizarCaptcha(HttpServletRequest request, ModelMap map) throws Exception {
		String idUsuario = request.getParameter(Constantes.SECURITY_USERNAME);
		map.addAttribute(Constantes.SECURITY_USERNAME, idUsuario);
		
		Traza.auditoria(getClass(), "CAPTCHA", idUsuario + "- Se actualiza el captcha");
		
		map.addAttribute("mostrarCaptcha", true);
		map.addAttribute("usarCaptcha", SiNoEnum.SI);
		
		return PAGINA_INGRESAR;
	}
	
	/**
	 * Redirecciona a la pagina de login pero con error de autenticacion
	 * 
	 * A continuacion les detallo los tipos de errores que he probado
	 * 
	 * Error de conexion --> InternalAuthenticationServiceException/CommunicationException
	 * dlnx0067.telecom.arg.telecom.com.ar:38; nested exception is javax.naming.CommunicationException: 
	 * dlnx0067.telecom.arg.telecom.com.ar:38 [Root exception is java.net.ConnectException: Connection refused: connect]
	 *
	 * Error de dominio base --> InternalAuthenticationServiceException/NameNotFoundException
	 * [LDAP: error code 32 - NDS error: no such entry (-601)]; nested exception is javax.naming.NameNotFoundException: 
	 * [LDAP: error code 32 - NDS error: no such entry (-601)]; remaining name 'ou=Usuarios'
	 * 
	 * Super Usuario --> InternalAuthenticationServiceException/AuthenticationException
	 * [LDAP: error code 49 - NDS error: failed authentication (-669)]; nested exception is javax.naming.AuthenticationException: 
	 * [LDAP: error code 49 - NDS error: failed authentication (-669)]
	 * 
	 * Error de credencial --> BadCredentialsException/BadCredentialsException
	 * Empty (username/pass) o no autenticados
	 * 
	 * @param request
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/errorLogin")
	public String errorLogin(HttpServletRequest request, ModelMap map) throws Exception {
		String idUsuario = (String) request.getSession().getAttribute(Constantes.SECURITY_USERNAME);
		
		Exception ex = (AuthenticationException) request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		if (ex instanceof  InternalAuthenticationServiceException) {
			throw ex;
		}
		
		String detalle = Mensajes.USUARIO_INCORRECTO + ": " + ((ex!=null)?
				(!Validaciones.isNullOrEmpty(ex.getMessage())?ex.getMessage():""):"");
		
		Traza.auditoriaLogin(getClass(), detalle, idUsuario);
		
		map.addAttribute("mostrarCaptcha", false);
		map.addAttribute("usarCaptcha", SiNoEnum.NO);
		
		if (Validaciones.isNullOrEmpty(idUsuario)) {
			idUsuario = Constantes.UNDEFINED.toUpperCase();
	    }
		if (!Validaciones.isNullOrEmpty(idUsuario) 
				&& ControlVariablesSingleton.getUsuarioIntentosLogin().containsKey(idUsuario.toUpperCase()) 
				&& (ControlVariablesSingleton.getUsuarioIntentosLogin().get(idUsuario.toUpperCase()).getCantidad() 
							> Constantes.CANTIDAD_INTENTOS_LOGIN_MISMO_USUARIO)) 
		{
			Traza.auditoria(getClass(), "CAPTCHA", idUsuario + " - Hubo un intento de fuerza bruta o robots por error de login");
			
			map.addAttribute("mostrarCaptcha", true);
			map.addAttribute("usarCaptcha", SiNoEnum.SI);
			
		}
		
		map.addAttribute("error", true);
		map.addAttribute("errorMessage", Mensajes.USUARIO_INCORRECTO);
		
		limpiarValidar(request);
		return PAGINA_INGRESAR;
	}
	
	
	/**
	 * Error de Captcha
	 *  
	 * @param request
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/errorJcaptcha")
	public String errorJcaptcha(HttpServletRequest request, ModelMap map) throws Exception {
		String idUsuario = request.getParameter(Constantes.SECURITY_USERNAME);
		if (Validaciones.isNullOrEmpty(idUsuario)) {
			idUsuario = (String) request.getSession().getAttribute(Constantes.SECURITY_USERNAME);
		}
		Traza.auditoria(getClass(), "CAPTCHA", idUsuario + " - Hubo un intento de fuerza bruta o robots por error de captcha");
		
		limpiarValidar(request);
		
		//Muestro el captcha
		map.addAttribute("mostrarCaptcha", true);
		map.addAttribute("usarCaptcha", SiNoEnum.SI);
		
		//Muestro el mensaje del error del captcha
		map.addAttribute("error", true);
		map.addAttribute("errorMessage", "El campo Codigo Verificador es obligatorio o Incorrecto");
		
		return PAGINA_INGRESAR;
	}
	
	/**
	 * Logout
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/salir")
	public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, ModelMap map) throws Exception {
		
		String usuario = Utilidad.getUsuarioSesion(httpServletRequest);
		Traza.auditoria(getClass(), "logout", Utilidad.reemplazarMensajes(Mensajes.USUARIO_LOGOUT, usuario));
		
		httpServletRequest.getSession().removeAttribute(Constantes.LOGIN);
		SecurityContextHolder.clearContext();
		httpServletRequest.getSession().invalidate();
		
		/**
	     * Delete Cookies
	     */
	    Cookie cookie = new Cookie("JSESSIONID", null);
	    cookie.setMaxAge(0);
	    cookie.setPath(httpServletRequest.getContextPath().length() > 0 ? httpServletRequest.getContextPath() : "/");
	    httpServletResponse.addCookie(cookie);
	    
	    cookie = new Cookie("SPRING_SECURITY_REMEMBER_ME_COOKIE", null);
	    cookie.setMaxAge(0);
	    cookie.setPath(httpServletRequest.getContextPath().length() > 0 ? httpServletRequest.getContextPath() : "/");
	    httpServletResponse.addCookie(cookie);
	    
	    //return ingresar(httpServletRequest, map);
	    return "redirect:index.jsp";
	}
	
	/**
	 * Notifica al usuario por el rango horario no permitido
	 * @param request
	 * @param map
	 * @return
	 */
	private String rangoHorarioNoPermitido(HttpServletRequest request, ModelMap map, String resultadoRango) {
		request.getSession().invalidate();
		
		map.addAttribute("error", true);
		map.addAttribute("errorMessage", resultadoRango);
		
		return PAGINA_EXPIRADA;	
	}
	
	/**
	 * Cuando el usuario en otra sesion se loguea,
	 * esta sesion queda expirada y se dirigue a la pagina expirada
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/expiredLogin", method = RequestMethod.GET)
	public String expirarLogin(HttpServletRequest request, ModelMap map) {
		request.getSession().invalidate();
		
		map.addAttribute("error", true);
		map.addAttribute("errorMessage", Mensajes.EXPIRADA_LOGUEADO);
		
		return PAGINA_EXPIRADA;	
	}
	
	/**
	 * Cuando se pasa un cierto tiempo, la pagina se expira
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/expiredSesion", method = RequestMethod.GET)
	public String expirarSesion(HttpServletRequest request, ModelMap map) {
		request.getSession().invalidate();
		
		map.addAttribute("error", true);
		map.addAttribute("errorMessage", Mensajes.EXPIRADA_SESION);
		
		return PAGINA_EXPIRADA;	
	}
	
	/**
	 * Cuando vemos un usuario con un role no autorizado
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/usuarioSinPerfiles", method = RequestMethod.GET)
	public String usuarioSinPerfiles(HttpServletRequest request, ModelMap map) {
		request.getSession().invalidate();
		
		map.addAttribute("error", true);
		map.addAttribute("errorMessage", Mensajes.SIN_PERFILES);
		
		return PAGINA_EXPIRADA;	
	}
	
	/**
	 * Cuando vemos un usuario con un role no autorizado
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/usuarioNoAutorizado", method = RequestMethod.GET)
	public String usuarioNoAutorizado(HttpServletRequest request, ModelMap map) {
		request.getSession().invalidate();
		
		map.addAttribute("error", true);
		map.addAttribute("errorMessage", Mensajes.NO_AUTORIZADA);
		
		return PAGINA_EXPIRADA;	
	}
	
	/**
	 * Desde spring-security, cuando hay un acceso no autorizado, se 
	 * redirecciona a la pagina de acceso no autorizado
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/denegarAcceso")
	public String denegarAcceso(HttpServletRequest request) {

		Utilidad.tracearErrorRequest(getClass(), request);
		return PAGINA_DENEGADA;	
	}
	
	
	/**
	 * Get the users details for the 'personal' page
	 */
	@RequestMapping(value="/error")
	private ModelAndView error(HttpServletRequest request) {
		String mensaje = "";
		
		Throwable e = null;
		ModelAndView mv = new ModelAndView(Constantes.EXCEPCION_PAGINA);
		Date date = new Date();
		
		try {
			if (request != null) {
				e = (Throwable) request.getAttribute("javax.servlet.error.exception");
				if (e != null) {
					mensaje = e.getMessage();
					Traza.error(getClass(), mensaje, e, date.getTime());
			
					StringBuilder s = new StringBuilder();
			        s.append(e+"<br>");
			        for (StackTraceElement stackTraceEl : e.getStackTrace()) {
			            s.append("	at: "+stackTraceEl.toString()+"<br>");
			        }
			        
			        request.setAttribute("errorSpecified", e.getMessage());
					request.setAttribute("error", s);
					request.setAttribute("errorFechaHora", Utilidad.formatDateAndTimeFull(date));
					request.setAttribute("timeError", String.valueOf(date.getTime()));
					return mv;
				}
			}
		} catch (Exception e1) {}
		
		if (Validaciones.isNullOrEmpty(mensaje)) {
			mensaje =  (request.getAttribute("javax.servlet.error.message") != null 
					&& !Validaciones.isNullOrEmpty((String)request.getAttribute("javax.servlet.error.message")))
					? ": " + (String)request.getAttribute("javax.servlet.error.message"): "";
		}
		
		Exception e2 = new Exception(Validaciones.isNullOrEmpty(mensaje)?"Se produjo la excepcion desconocida":mensaje);
		Traza.error(getClass(), e2.getMessage(), e2);
		
		request.setAttribute("errorSpecified", e2.getMessage());
		request.setAttribute("error", Utilidad.stackTraceToString(e2));
		request.setAttribute("errorFechaHora", Utilidad.formatDateAndTimeFull(date));
		request.setAttribute("timeError", String.valueOf(date.getTime()));
		return mv;
	}
	
	
	/***********************************************
	* Setters & Getters
	************************************************/ 
	public MultiTypeCaptchaService getCaptchaService() {
		return captchaService;
	}

	public void setCaptchaService(MultiTypeCaptchaService captchaService) {
		this.captchaService = captchaService;
	}
	
	
}
