package ar.com.telecom.shiva.presentacion.controlador;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.excepciones.otros.ConcurrenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.JsonResponse;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteTareasPendientesFiltro;

@Controller
public class BandejaDeEntradaController extends Controlador {
	
	private static final String PAGINA_DEFAULT = "bandeja-de-entrada/bandeja-de-entrada";
	private static final String LISTA_TAREAS = "listaTareas";
	private static final String FILTROS = "filtros";
	private static final String ID_FILTRO = "idFiltro";
	private static final String TIMESTAMPAUX = "timeStampAux";
	
	@Autowired
	ITareaServicio tareaServicio;
	@Autowired ICobroBatchServicio cobroBatchServicio;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
	}

	@RequestMapping(value="/bandeja-de-entrada", method = RequestMethod.GET)
	public ModelAndView bandejaEntrada(HttpServletRequest request) throws Exception {
		if (Boolean.valueOf(request.getParameter("clear"))) {
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			userSesion.setTareasFiltro(null);
		}
		return this.buscarTareas(request);
	}
	
	@RequestMapping(value="/regresar-bandeja-de-entrada", method = RequestMethod.POST)
	public ModelAndView regresarBandejaEntrada(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		userSesion.getCaminoDeVuelta().clear();
		return this.buscarTareas(request);
	}
	
	@RequestMapping(value="/filtrar-bandeja-de-entrada", method = RequestMethod.POST)
	public ModelAndView filtrarBandejaEntrada(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		VistaSoporteTareasPendientesFiltro filtro = new VistaSoporteTareasPendientesFiltro();
		filtro.setIdFiltroSeleccionado(request.getParameter("filtroBandeja"));
		filtro.setUsuarioLogeado(userSesion);
		userSesion.setTareasFiltro(filtro);
		return this.buscarTareas(request);
	}
	/**
	 * Metodo para tomar una tarea de la bandeja de entrada
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/tomar-tarea-bandeja-de-entrada", method = RequestMethod.POST)
	public ModelAndView tomarTareaBandejaEntrada(HttpServletRequest request) throws Exception {
	
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			try {
				
				String tipoTarea = request.getParameter("tipoTareaformTarea");
				String idWorkflow = request.getParameter("idWorkflow");
				SociedadEnum sociedad = SociedadEnum.getEnumByName(request.getParameter("sociedadTarea"));
				SistemaEnum sistema = SistemaEnum.getEnumByName(request.getParameter("sistemaTarea"));
				TipoTareaEnum tarea = TipoTareaEnum.getEnumByName(tipoTarea);
				int idWorkflows= Integer.parseInt(idWorkflow);
				tareaServicio.tomarTarea(tarea, idWorkflows,sociedad, sistema, userSesion.getUsuario());	
				
			} catch (ConcurrenciaExcepcion e) {
				
				ModelAndView mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
				mv.addObject("url",BANDEJA_ENTRADA_VIEW_GET);
				return mv;
				
			}
			return this.buscarTareas(request);
	}
	/**
	 * Metodo para liberar una tarea de la bandeja de entrada
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/liberar-tarea-bandeja-de-entrada", method = RequestMethod.POST)
	public ModelAndView liberarTareaBandejaEntrada(HttpServletRequest request) throws Exception {
		
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			userSesion.setTareasFiltro(null);
			String idWorkflow = request.getParameter("idWorkflow");
			String tipoTarea = request.getParameter("tipoTarea");
			SociedadEnum sociedad = SociedadEnum.getEnumByName(request.getParameter("sociedad"));
			SistemaEnum sistema = SistemaEnum.getEnumByName(request.getParameter("sistema"));
			TipoTareaEnum tarea = TipoTareaEnum.getEnumByName(tipoTarea);
			int idWorkflows= Integer.parseInt(idWorkflow);
			tareaServicio.liberarTarea(tarea,idWorkflows,sistema, sociedad, userSesion.getUsuario());
		return this.buscarTareas(request);
	}
	@RequestMapping(value="/verificarTareasParaAnularCobro", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse verificarTareasParaAnularCobro(@RequestBody final TareaDto tarea, HttpServletRequest request) throws Exception {
		
		JsonResponse json = new JsonResponse();
		
		boolean salida =  cobroBatchServicio.verificarTareasParaAnularCobro(tarea.getIdCobro(), tarea.getUsuarioAsignacion(), tarea.getIdTarea());
		
		if (salida) {
			json.setInformacionMensaje(Propiedades.MENSAJES_PROPIEDADES.getString("eliminarTarea.lightbox.cobros"));
		} else{
			json.setInformacionMensaje(Propiedades.MENSAJES_PROPIEDADES.getString("eliminarTarea.lightbox.cobros.tarea"));
		}
	
		json.setSuccess(true);
		
		return json;
	}  //cambiarEstadoSegunEstadoDeTareas
	/**
	 * Metodo Generico
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ModelAndView buscarTareas(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);

		if (Validaciones.isObjectNull(userSesion.getTareasFiltro())) {
			VistaSoporteTareasPendientesFiltro filtro = new VistaSoporteTareasPendientesFiltro();
			filtro.setIdFiltroSeleccionado(Constantes.BANDEJA_ENTRADA_ID_FILTRO_MIS_TAREAS); 
			filtro.setUsuarioLogeado(userSesion);
			userSesion.setTareasFiltro(filtro);
		}

		// Medicion de tiempo al inicio del procesamiento
		double fechaHoraInicioNanoTime = System.nanoTime();
		Date fechaHoraInicio = new Date();

		Collection<DTO> tareas = tareaServicio.listarTareasPendientes(userSesion.getTareasFiltro());
		// Logueamos info de procesamiento (tiempos)
		Traza.loguearInfoProcesamiento(this.getClass(), 
				"listarTareasPendientes", fechaHoraInicio, fechaHoraInicioNanoTime, tareas.size());

		ModelAndView mv = new ModelAndView(PAGINA_DEFAULT);
		mv.addObject(LISTA_TAREAS, Utilidad.guionesNull((List<? extends DTO>) tareas));
		mv.addObject(FILTROS, userSesion.getTareasFiltro().getFiltros());
		mv.addObject(ID_FILTRO, userSesion.getTareasFiltro().getIdFiltroSeleccionado());
		mv.addObject(TIMESTAMPAUX,new Date().getTime());

		//Brian Botones volver
		userSesion.getRetorno().clear();
		userSesion.getRetorno().add("/regresar-bandeja-de-entrada");
		mv.addObject("idVolver", "/regresar-bandeja-de-entrada");	


		return mv;
	}
}
