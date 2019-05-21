package ar.com.telecom.shiva.presentacion.controlador;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.telecom.shiva.base.anotaciones.ControlProcesoTransacciones;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ConcurrenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.ICombosServicio;
import ar.com.telecom.shiva.negocio.servicios.IReciboPreImpresoServicio;
import ar.com.telecom.shiva.negocio.servicios.ITalonarioServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ReciboPreImpresoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TalonarioDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.TalonarioFiltro;
import ar.com.telecom.shiva.presentacion.bean.validacion.TalonarioValidacionWeb;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.CurrencyEditor;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.CustomBindingErrorProcessor;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.IntegerEditor;

@Controller
public class TalonarioController extends Controlador {

	
	private static final String ALTA_VIEW = "talonario/talonario-alta";
	private static final String BUSQUEDA_VIEW = "talonario/talonario-busqueda";
	private static final String MODIFICACION_RECIBO_VIEW = "talonario/talonario-modificacion-recibo";
	private static final String MODIFICACION_TALONARIO_VIEW = "talonario/talonario-modificacion";
	private static final String AUTORIZACION_VIEW = "talonario/talonario-autorizacion-rendicion";
	private static final String REVISION_RENDICION_VIEW = "talonario/talonario-revision-rendicion";
	private static final String ACTUALIZACION_OK_VIEW = "template/actualizacion-exitosa";
	
	private static final String OBJECT_COMMAND = "talonarioDto";
	private static final String RECIBO_DTO = "reciboDto";
	private static final String TALONARIO_FILTRO = "talonarioFiltro";
	private static final String LISTA_GESTORES = "listaGestores";
	private static final String LISTA_TALONARIO_DTO = "listaTalonariosDto";
	private static final String SELECT_EMPRESAS = "empresas";
	private static final String SELECT_SEGMENTOS = "segmentos";
	private static final String TIMESTAMPAUX = "timeStampAux";
	
	@Autowired
	private ITalonarioServicio talonarioServicio;
	@Autowired
	private IReciboPreImpresoServicio reciboServicio;
	@Autowired
	private ICombosServicio combosServicio;
	@Autowired
	private TalonarioValidacionWeb talonarioValidacionWeb;

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.setBindingErrorProcessor(new CustomBindingErrorProcessor());

		DateFormat dateFormat = new SimpleDateFormat(Utilidad.DATE_FORMAT);
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
		binder.registerCustomEditor(BigDecimal.class, new CurrencyEditor());
		binder.registerCustomEditor(Integer.class, new IntegerEditor());

		if (binder.getTarget() instanceof TalonarioDto || binder.getTarget() instanceof TalonarioFiltro){
			binder.setValidator(talonarioValidacionWeb);
		}
	}
	
	
	/***
	 * Alta
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/talonario-alta")
	public ModelAndView talonarioAlta(HttpServletRequest request, TalonarioDto talonarioDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		talonarioDto.setOperation(Constantes.CREATE);
		
		ModelAndView mv=new ModelAndView(ALTA_VIEW);
		enviarListasCombosAlCargar(mv,userSesion,talonarioDto);
		mv.addObject(OBJECT_COMMAND, talonarioDto);
		
		return mv;
	}

	@RequestMapping("/procesar-alta-talonario")
	@ControlProcesoTransacciones
	public ModelAndView procesarAltaTalonario(HttpServletRequest request, @ModelAttribute("talonarioDto") @Valid TalonarioDto talonarioDto, 
			BindingResult result) throws Exception{					
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		if(result.hasErrors()){
			if (Utilidad.getErrorGeneral(result)!=null) {
				throw new ShivaExcepcion(Utilidad.getErrorGeneral(result));
			}
			return talonarioAlta(request, talonarioDto);

		}
		talonarioDto.setUsuarioAlta(userSesion.getUsuario());
		talonarioServicio.crear(talonarioDto);
		
		ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("mensaje",Propiedades.MENSAJES_PROPIEDADES.getString("recibo.alta.mensaje.crear.ok"));
		mv.addObject("url","talonario-alta");
		return mv;
	}
	
	/***
	 * Método para cargar la página de Busqueda de talonarios
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/talonario-busqueda")
	public ModelAndView talonarioBusqueda(HttpServletRequest request, TalonarioFiltro talonarioFiltro, TalonarioDto talonarioDto, BindingResult result) throws Exception{
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		talonarioFiltro.setUsuarioLogeado(userSesion);
		
		ModelAndView mv=new ModelAndView(BUSQUEDA_VIEW);
		talonarioServicio.cargarCombosTalonarios(mv, userSesion, talonarioFiltro);
		//cargarCombosParaBusqueda(mv,userSesion,talonarioFiltro);
		cargarComboParaEstados(mv);
		
		mv.addObject(TALONARIO_FILTRO, talonarioFiltro);
		userSesion.setTalonarioFiltro(talonarioFiltro);
		
		return mv;
	}
	
	/**
	 * Clase privada que carga la pagina de busqueda sin el DTO
	 * @param request
	 * @param talonarioFiltro
	 * @param result
	 * @return
	 * @throws Exception
	 */
	private ModelAndView talonarioBusqueda(HttpServletRequest request, TalonarioFiltro talonarioFiltro, BindingResult result) throws Exception{
		TalonarioDto talonarioDto = new TalonarioDto();
		return talonarioBusqueda(request, talonarioFiltro, talonarioDto, result);
	}
	
	/**
	 * Método para buscar Talonarios
	 * @param request
	 * @param talonarioFiltro
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/buscar-talonarios")
	@ControlProcesoTransacciones
	public ModelAndView buscarTalonarios(HttpServletRequest request, 
			@ModelAttribute("talonarioFiltro") @Valid TalonarioFiltro talonarioFiltro, BindingResult result, 
			TalonarioDto talonarioDto) throws Exception {	
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		if (result != null){
			if (result.hasErrors() 
					&& (talonarioDto.getErrorRechazo() == null
					    && talonarioDto.getErrorNingunaModificacion() == null)){
				
				return talonarioBusqueda(request,talonarioFiltro,result);
			}
		}
		
		// Medicion de tiempo al inicio del procesamiento
		double fechaHoraInicioNanoTime = System.nanoTime();
		Date fechaHoraInicio = new Date();
				
		ModelAndView mv=new ModelAndView(BUSQUEDA_VIEW);
		talonarioFiltro.setUsuarioLogeado(userSesion);
		
		List<TalonarioDto> lista = talonarioServicio.buscarTalonarios(talonarioFiltro);
		lista = (List<TalonarioDto>) Utilidad.guionesNull(lista);
		mv.addObject(LISTA_TALONARIO_DTO, lista);
		talonarioServicio.cargarCombosTalonarios(mv, userSesion, talonarioFiltro);
//		cargarCombosParaBusqueda(mv,userSesion,talonarioFiltro);
		cargarComboParaEstados(mv);
		
		mv.addObject(TALONARIO_FILTRO, talonarioFiltro);
		userSesion.setTalonarioFiltro(talonarioFiltro);
		
		if((talonarioDto.getErrorRechazo()!= null && (talonarioDto.getErrorRechazo()))
				|| (talonarioDto.getErrorNingunaModificacion()!= null && (talonarioDto.getErrorNingunaModificacion())) || (result == null)) {
			mv.addObject(OBJECT_COMMAND,talonarioDto);	
		}
		
		mv.addObject(TIMESTAMPAUX,new Date().getTime());

		// Logueamos info de procesamiento (tiempos)
		Traza.loguearInfoProcesamiento(this.getClass(), 
			"buscarTalonarios", fechaHoraInicio, fechaHoraInicioNanoTime, lista.size());
		
		return mv;
	}
	
	/**
	 * Rechaza el talonario seleccionado
	 * @param request
	 * @param talonarioFiltro
	 * @param talonarioId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rechazar-talonario")
	public ModelAndView rechazarTalonario (HttpServletRequest request, @ModelAttribute("talonarioDto") @Valid TalonarioDto talonarioDto, 
			BindingResult result) throws Exception{
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
		if (result.hasErrors()) {
			talonarioDto.setErrorRechazo(true);
			return buscarTalonarios(request, userSesion.getTalonarioFiltro(), result, talonarioDto);
		} else {
			//guardo el id en el filtro para que lo tome volver-busqueda
			TalonarioFiltro talonarioFiltro = userSesion.getTalonarioFiltro();
			talonarioFiltro.setIdTalonario(talonarioDto.getIdTalonario());
			try{
				talonarioDto.setUsuarioModificacion(userSesion.getIdUsuario());
				talonarioServicio.rechazoTalonario(talonarioDto);
			}catch(ConcurrenciaExcepcion e){
				mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
				mv.addObject("url","talonario-busqueda");
				return mv;
			}
		}
		mv.addObject("mensaje",Propiedades.MENSAJES_PROPIEDADES.getString("recibo.modificacion.mensaje.ok"));
		mv.addObject("url","volver-busqueda");
		return mv;
	}
	
	
	@RequestMapping(value = "/eliminar-tarea-revision-talonario")
	public ModelAndView eliminarTareaRevisionTalonario(HttpServletRequest request,
			@ModelAttribute ("talonarioDto")TalonarioDto talonarioDto, BindingResult result)  throws Exception {
		
		UsuarioSesion usuarioSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		talonarioServicio.eliminarTareaRevisionTalonario(talonarioDto, usuarioSesion);
		
		ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("eliminarTareaOK.registroAVC.mensaje"));
		mv.addObject("url","bandeja-de-entrada");
		return mv;
	}
	
	
	@RequestMapping("/rendir-talonario")
	public ModelAndView rendirTalonario (HttpServletRequest request, @ModelAttribute("talonarioDto") @Valid TalonarioDto talonarioDto, 
			BindingResult result) throws Exception{
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		if (result.hasErrors()) {
			return buscarTalonarios(request, userSesion.getTalonarioFiltro(), result, talonarioDto);
		}
		//guardo el id en el filtro para que lo tome volver-busqueda
		TalonarioFiltro talonarioFiltro = userSesion.getTalonarioFiltro();
		talonarioFiltro.setIdTalonario(talonarioDto.getIdTalonario());
		
		talonarioDto.setUsuarioRendicion(userSesion.getIdUsuario());
		ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
		
		String errorFechasIngresoNulas = talonarioServicio.validarFechaIngresoNula(talonarioDto);
		/**
		 * @author u573005, 
		 * se corrige IM al rendir talonario
		 * shiva muestra un error pero en realidad no hay ningun error
		 */
		if (!Validaciones.isNullOrEmpty(errorFechasIngresoNulas)){
			talonarioDto.setErrorRendicion(true);
			talonarioDto.setErrorRendicionMensaje(errorFechasIngresoNulas);
			return buscarTalonarios(request, userSesion.getTalonarioFiltro(), result, talonarioDto);
		} else {
			String errorFecha = talonarioServicio.validarFechaIngresoRecibos(talonarioDto.getIdTalonario());
			if (errorFecha != null){
				talonarioDto.setErrorRendicion(true);
				talonarioDto.setErrorRendicionMensaje(errorFecha);
				return buscarTalonarios(request, userSesion.getTalonarioFiltro(), result, talonarioDto);
			} else {
				try{
					talonarioServicio.rendirTalonario(talonarioDto);
				}catch(ConcurrenciaExcepcion e){
					mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
					mv.addObject("lista", true);
					mv.addObject("mensaje", 
						Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.concurrencia.lista"), 
								", para los recibos ["+e.getListaIdsInconcurrentes()+"]"));
	  			if (Constantes.DESTINO_BANDEJA_ENTRADA.equalsIgnoreCase(talonarioDto.getPantallaDestino())) {
 					mv.addObject("url",BANDEJA_ENTRADA_VIEW_GET);
 				} else {
 					mv.addObject("url","talonario-busqueda");
 				}
				return mv;
				}
			}
		}
		mv.addObject("mensaje",Propiedades.MENSAJES_PROPIEDADES.getString("recibo.modificacion.mensaje.ok"));
		if (Constantes.DESTINO_BANDEJA_ENTRADA.equalsIgnoreCase(talonarioDto.getPantallaDestino())) {
			mv.addObject("url",BANDEJA_ENTRADA_VIEW_GET);
		} else {
			mv.addObject("url","volver-busqueda");
		}
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/talonario-autorizacion-rendicion")
	@ControlProcesoTransacciones
	public ModelAndView talonarioAutorizacionRendicion(HttpServletRequest request)  throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv=new ModelAndView(AUTORIZACION_VIEW);
		TalonarioFiltro talonarioFiltro = new TalonarioFiltro();
		talonarioFiltro.setIdEstado(Estado.TAL_ASIGNADO_ADMINISTRADOR.name());
		talonarioFiltro.setUsuarioLogeado(userSesion);
		
		String pantallaDestino = request.getParameter("pantallaDestino");
		if (pantallaDestino != null 
				&& Constantes.DESTINO_BANDEJA_ENTRADA.equals(pantallaDestino)) {
			mv.addObject("pantallaDestino", pantallaDestino);
			mv.addObject("mostrarBotonCancelar", true);
			
			String idTalonario = request.getParameter("idTalonario");
			if (!Validaciones.isNullOrEmpty(idTalonario)) { 
				talonarioFiltro.setIdTalonarioBandeja(idTalonario);
			} else {
				// para que muestre vacio
				talonarioFiltro.setIdTalonarioBandeja("0");
			}
		} else {
			mv.addObject("mostrarBotonCancelar", false);
			mv.addObject("pantallaDestino", "");
		}
		
		List<TalonarioDto> lista = talonarioServicio.buscarTalonarios(talonarioFiltro);
		lista = (List<TalonarioDto>) Utilidad.guionesNull(lista);
		mv.addObject(LISTA_TALONARIO_DTO, lista);
		
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/talonario-revision-rendicion")
	@ControlProcesoTransacciones
	public ModelAndView talonarioRevisionRendicion(HttpServletRequest request)  throws Exception {
		
		String idTalonario = request.getParameter("idTalonario");
		
		TalonarioDto talonarioDto = (TalonarioDto) talonarioServicio.buscar(Integer.valueOf(idTalonario));
		talonarioDto.setPantallaDestino(Constantes.DESTINO_BANDEJA_ENTRADA);
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		TalonarioFiltro talonarioFiltro = new TalonarioFiltro();
		talonarioFiltro.setIdEmpresa(talonarioDto.getIdEmpresa());
		talonarioFiltro.setIdSegmento(talonarioDto.getIdSegmento());
		talonarioFiltro.setIdTalonario(idTalonario);
		talonarioFiltro.setIdTalonarioBandeja(idTalonario);
		talonarioFiltro.setUsuarioLogeado(userSesion);
		
		
		ModelAndView mv=new ModelAndView(REVISION_RENDICION_VIEW);
		
		List<TalonarioDto> lista = talonarioServicio.buscarTalonarios(talonarioFiltro);
		lista = (List<TalonarioDto>) Utilidad.guionesNull(lista);
		mv.addObject(LISTA_TALONARIO_DTO, lista);
		mv.addObject(OBJECT_COMMAND,talonarioDto);	
		mv.addObject(TIMESTAMPAUX,new Date().getTime());
		userSesion.setTalonarioFiltro(talonarioFiltro);
		return mv;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/volver-revision-rendicion")
	public ModelAndView volverTalonarioRevisionRendicion(HttpServletRequest request) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		TalonarioFiltro talonarioFiltro = new TalonarioFiltro();
		talonarioFiltro.setIdEmpresa(userSesion.getTalonarioFiltro().getIdEmpresa());
		talonarioFiltro.setIdSegmento(userSesion.getTalonarioFiltro().getIdEmpresa());
		talonarioFiltro.setIdTalonario(userSesion.getTalonarioFiltro().getIdTalonario());
		talonarioFiltro.setIdTalonarioBandeja(userSesion.getTalonarioFiltro().getIdTalonario());
		talonarioFiltro.setUsuarioLogeado(userSesion);
		
		TalonarioDto talonarioDto = (TalonarioDto) talonarioServicio.buscar(Integer.valueOf(talonarioFiltro.getIdTalonario()));
		talonarioDto.setPantallaDestino(Constantes.DESTINO_BANDEJA_ENTRADA);
		ModelAndView mv = new ModelAndView(REVISION_RENDICION_VIEW);
		
		List<TalonarioDto> lista = talonarioServicio.buscarTalonarios(talonarioFiltro);
		lista = (List<TalonarioDto>) Utilidad.guionesNull(lista);
		mv.addObject(LISTA_TALONARIO_DTO, lista);
		mv.addObject(OBJECT_COMMAND,talonarioDto);	
		mv.addObject(TIMESTAMPAUX,new Date().getTime());
		userSesion.setTalonarioFiltro(talonarioFiltro);
		return mv;
	}
	
	
	/**
	 * Metodo privado para casos de errores
	 * @param request
	 * @param talonarioDto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	private ModelAndView talonarioAutorizacionRendicion(HttpServletRequest request, TalonarioDto talonarioDto, BindingResult result)  throws Exception {
		ModelAndView mv = talonarioAutorizacionRendicion (request);
		mv.addObject(OBJECT_COMMAND,talonarioDto);
		return mv;
	}
	
	@RequestMapping("/rechazar-rendicion-talonario")
	@ControlProcesoTransacciones
	public ModelAndView rechazarRendicionTalonario(HttpServletRequest request, @ModelAttribute("talonarioDto") @Valid TalonarioDto talonarioDto, 
			BindingResult result) throws Exception{
		ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		if (result.hasErrors()) {
			return talonarioAutorizacionRendicion(request, talonarioDto, result);
		}
		
		talonarioServicio.rechazarRendicionTalonario(talonarioDto, userSesion);
		mv.addObject("mensaje",Propiedades.MENSAJES_PROPIEDADES.getString("recibo.modificacion.mensaje.ok"));
		
		if (Constantes.DESTINO_BANDEJA_ENTRADA.equalsIgnoreCase(talonarioDto.getPantallaDestino())) {
			mv.addObject("url",BANDEJA_ENTRADA_VIEW_GET);
		} else {
			mv.addObject("url","talonario-autorizacion-rendicion");
		}
		
		return mv;
	}
	
	@RequestMapping("/autorizar-rendicion-talonario")
	@ControlProcesoTransacciones
	public ModelAndView autorizarRendicionTalonario(HttpServletRequest request, @ModelAttribute("talonarioDto") TalonarioDto talonarioDto, 
			BindingResult result) throws Exception{
		ModelAndView mv;
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		try {
			talonarioServicio.autorizarRendicionTalonario(talonarioDto, userSesion);
		} catch (NegocioExcepcion ne) {
			talonarioDto.setErrorRendicion(true);
			return talonarioAutorizacionRendicion(request);
		}
		mv = new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("mensaje",Propiedades.MENSAJES_PROPIEDADES.getString("recibo.modificacion.mensaje.ok"));
		if (Constantes.DESTINO_BANDEJA_ENTRADA.equalsIgnoreCase(talonarioDto.getPantallaDestino())) {
			mv.addObject("url",BANDEJA_ENTRADA_VIEW_GET);
		} else {
			mv.addObject("url","talonario-autorizacion-rendicion");
		}
		return mv;
	}
	
	/**
	 * Abre la pantalla de modificacion con del talonario seleccionado
	 * @param request
	 * @param idTalonario
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/talonario-modificacion")
	public ModelAndView talonarioModificacion (HttpServletRequest request,
			@ModelAttribute ("talonarioDto")TalonarioDto talonarioDto, BindingResult result)  throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String timeStampAux = talonarioDto.getTimeStampAux();
		
		ModelAndView mv=new ModelAndView(MODIFICACION_TALONARIO_VIEW);
		if(!result.hasErrors()){
			String idTalonario=request.getParameter("idTalonario");
			talonarioDto = (TalonarioDto)talonarioServicio.buscar(Integer.valueOf(idTalonario));
		}
		enviarListasCombosAlCargar(mv,userSesion,talonarioDto);
		cargarSegmento(mv, talonarioDto, userSesion);
		cargarComboParaEstados(mv);
		talonarioDto.setTimeStampAux(timeStampAux);
		
		String pantallaDestino = request.getParameter("pantallaDestino");
		if (pantallaDestino != null 
				&& Constantes.DESTINO_BANDEJA_ENTRADA.equals(pantallaDestino)) 
		{
			talonarioDto.setPantallaDestino(pantallaDestino);
		}
		
		mv.addObject(LISTA_GESTORES, talonarioServicio.comboGestores(talonarioDto));
		mv.addObject(OBJECT_COMMAND,talonarioDto);
		return mv;
	}

	/**
	 * Procesa la modificacion de talonarios
	 * @param request
	 * @param talonarioDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/procesar-modificacion")
	@ControlProcesoTransacciones
	public ModelAndView procesarModificacion (HttpServletRequest request, 
			@ModelAttribute ("talonarioDto") @Valid TalonarioDto talonarioDto, BindingResult result)  throws Exception{
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		if (result.hasErrors()) {
			return talonarioModificacion(request, talonarioDto, result);
		}
		
		//guardo el id en el filtro para que lo tome volver-busqueda
		TalonarioFiltro talonarioFiltro = userSesion.getTalonarioFiltro();
		if (Constantes.DESTINO_BANDEJA_ENTRADA.equalsIgnoreCase(talonarioDto.getPantallaDestino())) {	
			talonarioFiltro = new TalonarioFiltro();
		}
		talonarioFiltro.setIdTalonario(talonarioDto.getIdTalonario());
		
 		talonarioDto.setUsuarioModificacion(userSesion.getUsuario());
 		
 		talonarioDto.setErrorNingunaModificacion(false);
 		if (userSesion.getEsAdminTalonario()){
 			try{
 				talonarioServicio.modificar(talonarioDto);
 			}catch (ConcurrenciaExcepcion e) {
 				ModelAndView mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
 				
 				if (Constantes.DESTINO_BANDEJA_ENTRADA.equalsIgnoreCase(talonarioDto.getPantallaDestino())) {
 					mv.addObject("url",BANDEJA_ENTRADA_VIEW_GET);
 				} else {
 					mv.addObject("url","volver-busqueda");
 				}
 				
 				return mv;
 			}
 		} else {
 			if (!Validaciones.isNullOrEmpty(talonarioDto.getUsuarioGestor())) {
 				String[] split = talonarioDto.getUsuarioGestor().split("_");
 				talonarioDto.setIdUsuarioGestor(split[0]);
 				talonarioDto.setUsuarioGestor(split[1]);
 				try{
 					
 					talonarioServicio.asignarGestor(talonarioDto);
 					
	 			}catch (ConcurrenciaExcepcion e) {
	 				ModelAndView mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
	 				
	 				if (Constantes.DESTINO_BANDEJA_ENTRADA.equalsIgnoreCase(talonarioDto.getPantallaDestino())) {
	 					mv.addObject("url",BANDEJA_ENTRADA_VIEW_GET);
	 				} else {
	 					mv.addObject("url","volver-busqueda");
	 				}
	 				return mv;
	 			}
 			}
 		}
 		
		ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("mensaje",Propiedades.MENSAJES_PROPIEDADES.getString("recibo.modificacion.mensaje.ok"));
		if (Constantes.DESTINO_BANDEJA_ENTRADA.equalsIgnoreCase(talonarioDto.getPantallaDestino())) {
			mv.addObject("url",BANDEJA_ENTRADA_VIEW_GET);
		} else {
			mv.addObject("url","volver-busqueda");
		}
		
		return mv;
	}
	
	@RequestMapping("/volver-busqueda")
	public ModelAndView volverBusqueda(HttpServletRequest request, 
			@ModelAttribute ("talonarioDto") TalonarioDto talonario) throws Exception{
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		TalonarioDto talonarioDto = new TalonarioDto();
		
		if (talonario == null || Validaciones.isNullOrEmpty(talonario.getIdTalonario())){
			TalonarioFiltro talonarioFiltro = userSesion.getTalonarioFiltro();
			talonarioDto.setIdTalonario(talonarioFiltro.getIdTalonario());
		}else{
			talonarioDto.setIdTalonario(talonario.getIdTalonario());
		}
		
		BindingResult result = null;
		return buscarTalonarios(request, userSesion.getTalonarioFiltro(), result, talonarioDto);
	}
	
	@RequestMapping("/talonario-modificacion-recibo")
	public ModelAndView modificacionRecibo(HttpServletRequest request, 
			@ModelAttribute("reciboDto") ReciboPreImpresoDto reciboDto) throws Exception {
		
		boolean volverPantallaRevision = reciboDto.isVolverPantallaRendicion();
		String idTalonarioSelected = reciboDto.getIdTalonarioSelected();
		String idRecibo = (String) reciboDto.getId();
		reciboDto = talonarioServicio.buscarRecibo(idRecibo);
		reciboDto.setTimeStamp(String.valueOf((new Date().getTime())));
		reciboDto.setVolverPantallaRendicion(volverPantallaRevision);
		reciboDto.setIdTalonarioSelected(idTalonarioSelected);
		
		if (!volverPantallaRevision) {
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			//guardo el id en el filtro para que lo tome volver-busqueda
			TalonarioFiltro talonarioFiltro = userSesion.getTalonarioFiltro();
			talonarioFiltro.setIdTalonario(reciboDto.getNroTalonario());
		}
		
		ModelAndView mv=new ModelAndView(MODIFICACION_RECIBO_VIEW);
		mv.addObject(RECIBO_DTO, reciboDto);
		
		return mv;
	}
	
	@RequestMapping("/procesar-modificacion-recibo")
	public ModelAndView procesarModificacionRecibo(HttpServletRequest request, @ModelAttribute("reciboDto") ReciboPreImpresoDto reciboDto) throws NegocioExcepcion{
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String url = null;
		if (reciboDto.isVolverPantallaRendicion()) {
			url = "volver-revision-rendicion";
		} else {
			url = "volver-busqueda";
			//guardo el id en el filtro para que lo tome volver-busqueda
			TalonarioFiltro talonarioFiltro = userSesion.getTalonarioFiltro();
			talonarioFiltro.setIdTalonario(reciboDto.getNroTalonario());
		}
		
		try {
			reciboServicio.guardarCompensaciones(reciboDto, userSesion.getIdUsuario());
		} catch (ConcurrenciaExcepcion e) {
			ModelAndView mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
			mv.addObject("url", url);
			return mv;
		}
		ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
		String validarFecha = reciboServicio.validarFechaIngreso(reciboDto);
		if (!Validaciones.isNullEmptyOrDash(validarFecha)) {
			mv.addObject("error", true);
			mv.addObject("errorMensaje",validarFecha);
		}
		mv.addObject("url",url);
		mv.addObject("mensaje",Propiedades.MENSAJES_PROPIEDADES.getString("recibo.modificacion.recibo.mensaje.ok"));
		return mv;
	}
	
	@RequestMapping("/anular-recibo")
	public ModelAndView anularRecibo(HttpServletRequest request, @ModelAttribute("reciboDto") ReciboPreImpresoDto reciboDto) throws NegocioExcepcion {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		String listaRecibos = reciboDto.getRecibosAAnularSelected();
		String idTalonario = reciboDto.getIdTalonarioSelected();
		String timeStamp = reciboDto.getTimeStamp();

		String url = null; 
		if (reciboDto.isVolverPantallaRendicion()) {
			url = "volver-revision-rendicion";
		} else {
			url = "volver-busqueda";
			//guardo el id en el filtro para que lo tome volver-busqueda
			TalonarioFiltro talonarioFiltro = userSesion.getTalonarioFiltro();
			talonarioFiltro.setIdTalonario(idTalonario);
		}
		
		try{
			reciboServicio.anularRecibos(listaRecibos, userSesion, idTalonario, timeStamp);
		}catch (ConcurrenciaExcepcion e) {
			ModelAndView mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
			mv.addObject("lista", true);
			mv.addObject("mensaje", 
				Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.concurrencia.lista"), 
						", para los recibos ["+e.getListaIdsInconcurrentes()+"]"));
			mv.addObject("url",url);
			return mv;
		}
		
		ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("url",url);
		mv.addObject("mensaje",Propiedades.MENSAJES_PROPIEDADES.getString("recibo.anular.mensaje.ok"));
		
		return mv;
	}
	
	/************************************************************************************/
	/** Metodos para los combos **/
	/************************************************************************************/
	@RequestMapping("/talonario-alta-seleccionoEmpresa")
	public ModelAndView seleccionoEmpresa(HttpServletRequest request,  @ModelAttribute("talonarioDto") TalonarioDto talonarioDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		talonarioDto.setIdSegmento("");
		
		ModelAndView mv = new ModelAndView(ALTA_VIEW);
		enviarListasCombosAlCargar(mv,userSesion,talonarioDto);
		if(!"".equalsIgnoreCase(talonarioDto.getIdEmpresa())){
			
			List<ShvParamSegmento> listaSegmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(talonarioDto.getIdEmpresa(), userSesion);
			mv.addObject(SELECT_SEGMENTOS,listaSegmentos);
			if(listaSegmentos.size()==1){
				talonarioDto.setIdSegmento(listaSegmentos.get(0).getIdSegmento());
				talonarioDto.setComboSegmento(false);
			}else{
				talonarioDto.setComboSegmento(true);
			}
		}
		mv.addObject(OBJECT_COMMAND, talonarioDto);
		return mv;
	}
	
	
	@RequestMapping("/talonario-modificacion-seleccionoEmpresa")
	public ModelAndView seleccionoEmpresaEnModificacion(HttpServletRequest request,  
			@ModelAttribute("talonarioDto") TalonarioDto talonarioDto) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		talonarioDto.setIdSegmento("");
		talonarioDto.setSegmento("");
		talonarioDto.setIdUsuarioGestor("");
		talonarioDto.setObservaciones("");
		String url=talonarioDto.getUrl();
		
		ModelAndView mv = new ModelAndView(url);
		enviarListasCombosAlCargar(mv, userSesion, talonarioDto);
		cargarSegmento(mv, talonarioDto, userSesion);
		cargarComboParaEstados(mv);
		mv.addObject(OBJECT_COMMAND, talonarioDto);
		
		return mv;
	}
	
	@RequestMapping("/talonario-busqueda-seleccionoEmpresa")
	public ModelAndView seleccionoEmpresaEnBusqueda(HttpServletRequest request,  
			@ModelAttribute("talonarioFiltro") TalonarioFiltro talonarioFiltro) throws Exception 
	{
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		talonarioFiltro.setSegmento("");
		talonarioFiltro.setObservaciones("");
		talonarioFiltro.setRangoDesde("");
		talonarioFiltro.setRangoHasta("");
		
		ModelAndView mv = new ModelAndView(talonarioFiltro.getUrl());
		cargarCombosParaBusqueda(mv, userSesion, talonarioFiltro);
		cargarSegmento(mv, talonarioFiltro, userSesion);
		cargarComboParaEstados(mv);
		mv.addObject(TALONARIO_FILTRO, talonarioFiltro);
		
		return mv;
	}
	
	
	/**
	 * Carga el combo de Gestores acorde al segmento seleccionado
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/comboGestor")
	public ModelAndView seleccionoSegmento (HttpServletRequest request,@ModelAttribute ("talonarioDto") TalonarioDto talonarioDto) throws Exception{
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv=new ModelAndView(MODIFICACION_TALONARIO_VIEW);
		talonarioDto.setIdUsuarioGestor("");
		enviarListasCombosAlCargar(mv,userSesion,talonarioDto);
		cargarSegmento(mv, talonarioDto, userSesion);
		mv.addObject(LISTA_GESTORES, talonarioServicio.comboGestores(talonarioDto));
		mv.addObject(OBJECT_COMMAND,talonarioDto);
		
		return mv;
	}

	/**
	 * LLamar al cargar la pagina de Alta de Talonarios.
	 * Carga la lista "empresas" y la lista "segmentos".
	 * De haber una sola empresa, busca los segmentos y los carga en el combo.
	 * @param mv
	 * @throws ShivaExcepcion
	 */
	private void enviarListasCombosAlCargar(ModelAndView mv, UsuarioSesion userSesion, TalonarioDto talonarioDto) throws ShivaExcepcion {
		try {
			List<ShvParamEmpresa> listaEmpresas = (List<ShvParamEmpresa>) combosServicio.listarEmpresasPorUsuario(userSesion);
			
			List<ShvParamSegmento> listaSegmentos = new ArrayList<ShvParamSegmento>();
			if (listaEmpresas.size() == 1) {
				talonarioDto.setComboEmpresa(false);
				String idEmpresa = listaEmpresas.get(0).getIdEmpresa();
				listaSegmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(idEmpresa, userSesion);
			} else {
				if (!Validaciones.isNullOrEmpty(talonarioDto.getIdEmpresa())) {
					listaSegmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(talonarioDto.getIdEmpresa(), userSesion);
				}
				talonarioDto.setComboEmpresa(true);
			}
			
			mv.addObject(SELECT_SEGMENTOS,listaSegmentos);
			if (listaSegmentos.size()>1){
				talonarioDto.setComboSegmento(true);
			}else{
				talonarioDto.setComboSegmento(false);
			}
			
			mv.addObject(SELECT_EMPRESAS, listaEmpresas);
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	
	/************************************************************************************/
	/** Metodos - AJAX **/
	/************************************************************************************/
	
	
	@RequestMapping("/validacionFechaIngreso")
	public @ResponseBody boolean validacionFechaIngreso(HttpServletRequest request) throws Exception {
		String fechaIngreso = request.getParameter("fechaIngreso");
		boolean validacion = Validaciones.validarFecha(fechaIngreso);
		return validacion;
	}
	
	@RequestMapping("/consultarRecibosTalonario")
	public void consultarRecibosTalonario(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ServletOutputStream outputStream = null;
		StringBuffer outputStringBuffer = new StringBuffer();
		boolean habilitarRendicion = true;
		boolean habilitarRendicionEnviado = false;
		try {
			response.reset();
		    response.setHeader("Pragma", "no-cache");
		    response.setDateHeader("Expires", -1);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("ISO-8859-1");
		    
		    boolean autorizacion = ((request.getParameter("tabla")!=null)&&("autorizacion".equalsIgnoreCase(request.getParameter("tabla"))))?true:false;
		    String idTalonario = request.getParameter("talonarioId");
		    String estado = "";
		    if (!autorizacion){
		    	estado = request.getParameter("estado");
		    }
		    
			@SuppressWarnings("unchecked")
			List<ReciboPreImpresoDto> lista = (List<ReciboPreImpresoDto>) Utilidad.guionesNull(talonarioServicio.buscarRecibos(idTalonario));
		    
			outputStream = response.getOutputStream();
			
			// Verificacion para habilitar Rendir Talonario 
			if (!(estado.equalsIgnoreCase(Estado.TAL_ASIGNADO_GESTOR.name()) || estado.equalsIgnoreCase(Estado.TAL_RENDICION_RECHAZADA.name()))){
				habilitarRendicion = false;
			}
			for (ReciboPreImpresoDto rec: lista){
				if (rec.getEstado().equalsIgnoreCase(Estado.REC_PENDIENTE.descripcion())){
						habilitarRendicion = false;
						break;
				}
			}
			
			
			outputStringBuffer.append("{");
			/**
			 * @author u562163, IM de prod. 
			 */	
			// Se agrega atributo habilitarRendicion para habilitar el boton de la pantalla de busqueda.
			if (!autorizacion) { 
				outputStringBuffer.append("\"habilitarRendicion\":");
				outputStringBuffer.append("\""+habilitarRendicion+"\",");
			}
			
			//Se agrega bloque para completar la tabla de recibos
			outputStringBuffer.append("\"aaData\":[ ");
			
			for (ReciboPreImpresoDto reciboPreImpresoDto: lista) {
		    	outputStringBuffer.append("[");
		    	if (!autorizacion) { 
		    		if (reciboPreImpresoDto.getEstado().equalsIgnoreCase(Estado.REC_PENDIENTE.descripcion())){
		    			outputStringBuffer.append("\"<input type='checkbox' class='opcion anulable' id='opcion' name='opcion' onclick='check()' value='"+reciboPreImpresoDto.getId()+"'/>\",");
		    		} else {
		    			if ((!reciboPreImpresoDto.getEstado().equalsIgnoreCase(Estado.REC_ANULADO.descripcion()))
		    					&& (!reciboPreImpresoDto.getEstado().equalsIgnoreCase(Estado.REC_COMPENSACION.descripcion())))
		    			{	
		    				outputStringBuffer.append("\"<input type='checkbox' class='opcion noAnulable' id='opcion' onclick='check()' name='opcion' value='"+reciboPreImpresoDto.getId()+"'/>\",");
		    			} else {
		    				outputStringBuffer.append("\"\",");
		    			}
		    		}
		    		if (userSesion.getPuedeModificarReciboPreImpreso() 
		    				&& (estado.equalsIgnoreCase(Estado.TAL_ASIGNADO_GESTOR.name()) || estado.equalsIgnoreCase(Estado.TAL_RENDICION_RECHAZADA.name())) 
		    				&& !reciboPreImpresoDto.getEstado().equalsIgnoreCase(Estado.REC_ANULADO.descripcion())){
		    			outputStringBuffer.append("\"<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'> <button id='btnEditar' class='btn btn-xs btn-link' title='Editar' onclick='javascript:modificarRecibo("+reciboPreImpresoDto.getId()+");'> <i class='icon-edit bigger-120'></i> </button> </div>\",");
		    		} else {
		    			outputStringBuffer.append("\"\",");
		    		}
		    	}
		    	outputStringBuffer.append("\"").append(reciboPreImpresoDto.getNroRecibo()).append("\",");
				if (!reciboPreImpresoDto.getValores().isEmpty()){
						outputStringBuffer.append("\""+reciboPreImpresoDto.getListaValores()).append("\",");
				} else { 
					outputStringBuffer.append("\"-\",");
				}
				if (!reciboPreImpresoDto.getListaCompensaciones().equalsIgnoreCase("<p style='line-height:12pt; font-size: 11px;' ></p>")){
					outputStringBuffer.append("\"<div style='text-align: left;'>").append(reciboPreImpresoDto.getListaCompensaciones()).append("</div>\",");
				} else { 
					outputStringBuffer.append("\"-\",");
				}
				outputStringBuffer.append("\"").append(reciboPreImpresoDto.getImporte()).append("\",");
				outputStringBuffer.append("\"").append(reciboPreImpresoDto.getEstado()).append("\",");
				if (!Validaciones.isNullEmptyOrDash(reciboPreImpresoDto.getUsuarioAnulacion())){
			    	outputStringBuffer.append("\"<div class='contenedor-columna' style='text-align: left;'><div style='width: 200px;'> "+
							"<img alt='Usuario' class='bloqueUsuario' src='"+reciboPreImpresoDto.urlFotoUsuario(reciboPreImpresoDto.getUsuarioAnulacion())+"'"+ 
							"style='cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;'").append( 
							" onerror='javascript:bloqueUsuario()'>"+
							reciboPreImpresoDto.getNombreUsuarioAnulacion()+" <br>"+
							"<a href='sip:"+reciboPreImpresoDto.getMailUsuarioAnulacion()+"'  title='Chat'><i class='icon-comment' style=' margin-top: 3px'></i></a>"+
							"<a href='mailto:"+reciboPreImpresoDto.getMailUsuarioAnulacion()+"'  title='Email'><i class='icon-envelope' style=' margin-left: 3px; margin-top: 2px'></i></a>"+
				 		    "</div></div>"+"<div class='contenedor-columna'style='text-align: left;'></div>\",");
				} else {
					outputStringBuffer.append("\"-\",");
				}
				outputStringBuffer.append("\"").append(reciboPreImpresoDto.getFechaAnulacion()).append("\",");
				outputStringBuffer.append("\"").append(reciboPreImpresoDto.getFechaIngreso()).append("\",");
				outputStringBuffer.append("\"").append(reciboPreImpresoDto.getSegmento()).append("\",");
				outputStringBuffer.append("\"").append(reciboPreImpresoDto.getEmpresa()).append("\"");
				outputStringBuffer.append("],");    	
			}	         
		    
		    String buffer = outputStringBuffer.toString();
		    
		    //se quita la ultima ,
		    outputStringBuffer = new StringBuffer(buffer.substring(0, buffer.length()-1));
		    outputStringBuffer.append("]}");
		    

		    outputStream.print(outputStringBuffer.toString());
			
		} catch (Throwable ex) {
			Traza.error(getClass(), ex.getMessage(), ex);
			
			outputStringBuffer.append("{\"aaData\":[ ");
			outputStringBuffer.append("[ error: ");
			outputStringBuffer.append(ex.getMessage());
			outputStringBuffer.append("]");
			outputStringBuffer.append("]}");
			
			outputStream.print(outputStringBuffer.toString());
		} 
	}
}
