package ar.com.telecom.shiva.presentacion.controlador;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.Excepcion;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ProcesoTransaccionesExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;

import com.google.common.collect.ImmutableMap;

@ControllerAdvice
public class GeneralController {
	
	@Autowired JdbcTemplate template;
	@Autowired ApplicationContext context;
	
	/**
	 * Get the users details for the 'personal' page
	 */
	private ModelAndView errorModelAndView(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		Excepcion e = new Excepcion(ex);
		Traza.error(getClass(), "Request: " + request.getRequestURL());
		Traza.error(getClass(), e.getMessage(), e);
		
		ModelAndView mv = new ModelAndView(Constantes.EXCEPCION_PAGINA);
		request.setAttribute("errorSpecified", ex.getMessage());
		request.setAttribute("error", Utilidad.stackTraceToString(ex));
		request.setAttribute("errorFechaHora", Utilidad.formatDateAndTimeFull(new Date()));
		
		request.setAttribute("timeError", String.valueOf(e.getTimeError()));
		
		String mensajeAuxiliar = null;
		if (ex!=null) {
			if (ex instanceof NegocioExcepcion) {
				mensajeAuxiliar = ((NegocioExcepcion) ex).getMensajeAuxiliar(); 
			}
		}
		request.setAttribute("mensajeAuxiliar", 
				(!Validaciones.isNullOrEmpty(mensajeAuxiliar))?mensajeAuxiliar:"");
		
		//Unicamente para ajax
		if (Utilidad.isAjax(request)) {
			response.setStatus(HttpStatus.CONFLICT.value()); //error 409
			return handleJsonExcepcion(ex, request, response, e.getTimeError());
		}
		
		return mv;
	}
  	
	/**
  	 * Catch ProcesoTransaccionesExcepcion
  	 * @param e
  	 * @param request
  	 * @return
  	 */
	@ExceptionHandler(ProcesoTransaccionesExcepcion.class)
	public ModelAndView handleException(ProcesoTransaccionesExcepcion ex, HttpServletRequest request) {
		Excepcion e = new Excepcion(ex);
		Traza.error(getClass(), e.getMessage(), e);
		request.setAttribute("errorFechaHora", Utilidad.formatDateAndTimeFull(Utilidad.obtenerFechaActual()));
		request.setAttribute("timeError", String.valueOf(e.getTimeError()));
		return new ModelAndView(Constantes.EXCEPCION_TRANSACCION_PAGINA);
	}
	
  	/**
  	 * Catch Exception
  	 * @param e
  	 * @param request
  	 * @return
  	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		return errorModelAndView(ex, request, response);
	}

	/**
	 * Preparo los errores para mapear a json
	 * @param ex
	 * @param request
	 * @param response
	 * @return
	 */
	private ModelAndView handleJsonExcepcion(Exception ex, HttpServletRequest request, HttpServletResponse response, long timeError) {
		Excepcion e = new Excepcion(ex);
		
		String mensajeAuxiliar = null;
		if (ex!=null) {
			if (ex instanceof NegocioExcepcion) {
				mensajeAuxiliar = ((NegocioExcepcion) ex).getMensajeAuxiliar(); 
			}
		}

		Map<String, String> bigMap = ImmutableMap.<String, String>builder()
				.put("titulo", Propiedades.MENSAJES_PROPIEDADES.getString("error.titulo"))
				.put("errorSpecified", (!Validaciones.isNullOrEmpty(e.getMessage()))?e.getMessage():
											(!Validaciones.isNullOrEmpty(ex.getMessage()))?ex.getMessage():"")
				.put("errorFechaHora", Utilidad.formatDateAndTimeFull(new Date()))
				.put("timeError", String.valueOf(timeError))
				.put("mensajeAuxiliar", (!Validaciones.isNullOrEmpty(mensajeAuxiliar))?mensajeAuxiliar:"")
				.put("esPerfilMantenimiento", ((UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN)).esPerfilMantenimiento().toString())
				.put("error", request.getAttribute("error").toString())
				.put("errorDescripcion", Propiedades.MENSAJES_PROPIEDADES.getString("error.descripcion"))
				.put("errorDetalle", Propiedades.MENSAJES_PROPIEDADES.getString("error.detalles"))
				.build();
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
		return new ModelAndView(jsonView, bigMap);
    }
}
