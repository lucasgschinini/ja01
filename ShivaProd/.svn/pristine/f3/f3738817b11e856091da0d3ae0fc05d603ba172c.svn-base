package ar.com.telecom.shiva.presentacion.controlador;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.com.telecom.shiva.base.anotaciones.ControlProcesoTransacciones;
import ar.com.telecom.shiva.base.comparador.ComparatorShvParamAcuerdo;
import ar.com.telecom.shiva.base.comparador.ComparatorShvParamBanco;
import ar.com.telecom.shiva.base.comparador.ComparatorShvParamBancoCuenta;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.CriterioBusquedaClienteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ConcurrenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.ArchivoAVCDto;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.negocio.servicios.IArchivoAVCServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IConciliacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IRegistroAVCServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorPorReversionServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValReversionValor;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCuenta;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrigen;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoValor;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ClienteJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.filtro.ArchivoAVCFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteRegistrosAvcSinConciliarFiltro;
import ar.com.telecom.shiva.presentacion.bean.validacion.ArchivoAvcValidacionWeb;
import ar.com.telecom.shiva.presentacion.bean.validacion.BoletaConValorValidacionWeb;
import ar.com.telecom.shiva.presentacion.bean.validacion.RegistroAvcValidacionWeb;
import ar.com.telecom.shiva.presentacion.bean.validacion.RegistroAvcValorValidacionWeb;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.CurrencyEditor;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.CustomBindingErrorProcessor;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.IntegerEditor;
import ar.com.telecom.shiva.presentacion.facade.IClienteFacade;

@Controller
public class ConciliacionController extends Controlador {

	private static final String CONFIRMAR_ALTA_REGISTRO_AVC_SIN_CONCILIAR_DETALLE_VIEW  = "conciliacion/conciliacion-confirmar-alta-registros-avc-sin-conciliar-detalle";
	private static final String CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_VIEW   	   	    = "conciliacion/conciliacion-consultar-registros-avc-sin-conciliar";
	private static final String CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_DETALLE_VIEW      = "conciliacion/conciliacion-consultar-registros-avc-sin-conciliar-detalle";
	private static final String GESTIONAR_RESULTADO_CONCILIACION_VIEW  				    = "conciliacion/conciliacion-gestionar-resultado-conciliacion";
	private static final String RESULTADO_CONCILIACION_VIEW   	       				    = "conciliacion/conciliacion-resultado-conciliacion";
	private static final String DESHACER_CONCILIACION_OK_VIEW						    = "conciliacion/conciliacion-actualizacion-exitosa";
	
	private static final String LISTA_ARCHIVOS  = "listaArchivos";
	private static final String LISTA_CONCILIACION_SUGERIDA = "listaConciliacionSugeridaDto";
	private static final String LISTA_REGISTROS_SIN_CONCILIAR = "listaRegistrosSinConciliar";
	private static final String ARCHIVO_AVC_FILTRO = "archivoAvcFiltro";
	
	private static final String VALOR_DTO = "valorDto";
	
	private static final String SELECT_ACUERDOS = "acuerdos";
	private static final String SELECT_BANCOS = "bancos";
	private static final String SELECT_CUENTAS = "cuentas";	
	
	@Autowired
	private ArchivoAvcValidacionWeb archivoAvcValidacionWeb;

	@Autowired
	private RegistroAvcValorValidacionWeb registroAvcValorValidacionWeb;
	
	@Autowired
	private BoletaConValorValidacionWeb boletaConValorValidacionWeb;

	@Autowired
	private IConciliacionServicio conciliacionServicio;
	
	@Autowired
	private IArchivoAVCServicio archivoAVCServicio;

	@Autowired
	private IRegistroAVCServicio registroAVCServicio;

	@Autowired
	private IValorPorReversionServicio valorPorReversionServicio;

	@Autowired
	private ICobroImputacionServicio cobroServicio;
	
	@Autowired
	ITareaServicio tareaServicio;
	
	@Autowired
	private IValorServicio valorServicio;
	
	@Autowired
	private RegistroAvcValidacionWeb registroAvcValidacionWeb;

	@Autowired
	private IClienteFacade clienteFacade;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.setBindingErrorProcessor(new CustomBindingErrorProcessor());

		DateFormat dateFormat = new SimpleDateFormat(Utilidad.DATE_FORMAT);
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
		binder.registerCustomEditor(BigDecimal.class, new CurrencyEditor());
		binder.registerCustomEditor(Integer.class, new IntegerEditor());

		if (binder.getTarget() instanceof ArchivoAVCFiltro){
			binder.setValidator(archivoAvcValidacionWeb);
		} 
		if (binder.getTarget() instanceof RegistroAVCDto){
			binder.setValidator(registroAvcValidacionWeb);
		}
		if (binder.getTarget() instanceof ValorDto){
			binder.setValidator(boletaConValorValidacionWeb);
		}

	}

	
/***************************************************************************
 * Resultado de Conciliacion
 * ************************************************************************/
	
	/**
	 * Método para cargar la pagina de Resultados Conciliacion.
	 * @param request
	 * @param archivoAvcFiltro
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/conciliacion-resultado-conciliacion")
	@ControlProcesoTransacciones
	public ModelAndView conciliacionResultadoConciliacion(HttpServletRequest request, ArchivoAVCFiltro archivoAvcFiltro, BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		archivoAvcFiltro.setUsuarioLogeado(userSesion);
		
		ModelAndView mv=new ModelAndView(RESULTADO_CONCILIACION_VIEW);
		
		if (userSesion.getVolviendoAResultadoConciliacion() && !Validaciones.isObjectNull(userSesion.getArchivoAVCFiltro())) {
			//Cuando vuelve de otra pagina
			//Recupero el filtro
			mv.addObject(LISTA_ARCHIVOS, archivoAVCServicio.listar(userSesion.getArchivoAVCFiltro()));
			mv.addObject(ARCHIVO_AVC_FILTRO, userSesion.getArchivoAVCFiltro());
			userSesion.setVolviendoAResultadoConciliacion(false);
		} else {
			if(Validaciones.isNullOrEmpty(archivoAvcFiltro.getFechaDesde())
					&& Validaciones.isNullOrEmpty(archivoAvcFiltro.getFechaHasta())){
				//Cuando accede desde el menu
				mv.addObject(LISTA_ARCHIVOS, archivoAVCServicio.listar(archivoAvcFiltro));
				mv.addObject(ARCHIVO_AVC_FILTRO, archivoAvcFiltro);
				userSesion.setArchivoAVCFiltro(archivoAvcFiltro);
			}else{
				//Filtro invalido
				mv.addObject(ARCHIVO_AVC_FILTRO, archivoAvcFiltro);
				userSesion.setArchivoAVCFiltro(archivoAvcFiltro);
			}
		}
		return mv;
		
	}

	/**
	 * Metodo para abrir un archivo Avc de la tabla de resultados de conciliacion.
	 * @param request
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/conciliacion-procesar-abrir-archivo")
	public void	procesarAbrirArchivo(@ModelAttribute("archivoAvcFiltro") ArchivoAVCFiltro archivoAvcFiltro,
			HttpServletResponse resp) throws Exception {

		String idArchivo = archivoAvcFiltro.getIdArchivoAvcSelect();
		
		ArchivoAVCDto archivoAVCDto = (ArchivoAVCDto) archivoAVCServicio.buscar(Integer.valueOf(idArchivo));
		
		// Muestra el popup para exportar el pdf. Setea los Numeros de boletas
		// en el nombre del archivo
		byte[] archivo = archivoAVCDto.getArchivoAdjunto();
		String nombreArchivo = archivoAVCDto.getNombreArchivo();
		
		ControlArchivo.descargarComoPDF(archivo, nombreArchivo, resp);
	}
	
	
/***************************************************************************
 * Gestionar Conciliacion Sugerida
 * ************************************************************************/
	
	/**
	 * Método para ejecutar la Busqueda de archivos Avc.    
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/conciliacion-archivos-busqueda")
	public ModelAndView buscarArchivos(HttpServletRequest request, 
			@ModelAttribute("archivoAvcFiltro") @Valid ArchivoAVCFiltro archivoAvcFiltro, BindingResult result) throws Exception{
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		if(result.hasErrors()){
			return conciliacionResultadoConciliacion(request,archivoAvcFiltro,result);
		}

		ModelAndView mv=new ModelAndView(RESULTADO_CONCILIACION_VIEW);
		
		archivoAvcFiltro.setUsuarioLogeado(userSesion);
		userSesion.setArchivoAVCFiltro(archivoAvcFiltro);
		
		mv.addObject(LISTA_ARCHIVOS, archivoAVCServicio.listar(archivoAvcFiltro));
		mv.addObject(ARCHIVO_AVC_FILTRO,archivoAvcFiltro);
		return mv;
		
	}
	
	/**
	 * Método para cargar la pantalla Gestionar Resultado Conciliado
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/conciliacion-gestionar-resultado-conciliacion")
	@ControlProcesoTransacciones
	public ModelAndView conciliacionGestionarResultadoConciliacion(HttpServletRequest request, 
			@ModelAttribute("registroDto") RegistroAVCDto registroDto, BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		userSesion.setVolviendoAResultadoConciliacion(true);
		
		ModelAndView mv=new ModelAndView(GESTIONAR_RESULTADO_CONCILIACION_VIEW);
		mv.addObject(LISTA_CONCILIACION_SUGERIDA, conciliacionServicio.listarConciliacionesSugeridas());
		VistaSoporteRegistrosAvcSinConciliarFiltro filtro = new VistaSoporteRegistrosAvcSinConciliarFiltro();
		List<String> estados = new ArrayList<String>();
		estados.add(Estado.AVC_PENDIENTE.toString());
		estados.add(Estado.AVC_ALTA_VALOR_RECHAZADA.toString());
		filtro.setListaEstados(estados);
		
		mv.addObject(LISTA_REGISTROS_SIN_CONCILIAR, registroAVCServicio.listarRegistrosAVCSinConciliar(filtro, false));
		
		return mv;
	}
	
	/**
	 * Método para confirmar las conciliaciones sugeridas seleccionadas en la tabla
	 * "Conciliacion Sugerida". 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/conciliacion-confirmar-conciliacion-sugerida")
	@ControlProcesoTransacciones
	public ModelAndView confirmarConciliacionSugerida(HttpServletRequest request,
			@ModelAttribute("registroDto") RegistroAVCDto registroDto, BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		String conciliacionesSugeridasAConfirmar = registroDto.getConciliacionesSelected();
		
		ModelAndView mv=new ModelAndView(DESHACER_CONCILIACION_OK_VIEW);
		
		if (!Validaciones.isNullOrEmpty(conciliacionesSugeridasAConfirmar)) {
			String[] listaConciliacionesSugeridas = conciliacionesSugeridasAConfirmar.split(",");
			try {
			
				conciliacionServicio.confirmarConciliacionesSugeridas(listaConciliacionesSugeridas, userSesion.getUsuario());
				mv.addObject("mensaje",Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.confirmado.x.conciliaciones.sugeridad"), String.valueOf(conciliacionServicio.cantRegistros())));
				
			} catch (ValidacionExcepcion e) {
				mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionNO.mensaje"));
				mv.addObject("error", true);
				mv.addObject("errorMensaje", e.getMessage());
				
			} catch (ConcurrenciaExcepcion e) {
				
				userSesion.setVolviendoAResultadoConciliacion(true);
				ModelAndView mvError = new ModelAndView(ERROR_CONCURRENCIA_VIEW);
				mvError.addObject("lista", true);
				mvError.addObject("mensaje", 
					Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.concurrencia.lista"), 
							", para las conciliaciones ["+e.getIdInconcurrente()+"]"));
				
				mvError.addObject("url","conciliacion-gestionar-resultado-conciliacion");
				
				return mvError;
			} 
		} else {
			mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionNO.mensaje"));
			mv.addObject("error", true);
			mv.addObject("errorMensaje", "Se deberá seleccionar alguna conciliacion sugerida");
		}
		
		mv.addObject("url","conciliacion-gestionar-resultado-conciliacion");
		return mv;
	}
	
	/**
	 * Método para deshacer las conciliaciones sugeridas seleccionadas en la tabla
	 * "Conciliacion Sugerida". 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/conciliacion-deshacer-conciliacion-sugerida")
	@ControlProcesoTransacciones
	public ModelAndView deshacerConciliacionSugerida(HttpServletRequest request,
		@ModelAttribute("registroDto") RegistroAVCDto registroDto, BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		String conciliacionesSugeridasADeshacer = registroDto.getConciliacionesSelected();
				
		ModelAndView mv=new ModelAndView(DESHACER_CONCILIACION_OK_VIEW);
		
		if (!Validaciones.isNullOrEmpty(conciliacionesSugeridasADeshacer)) {
			String[] listaConciliacionesSugeridas = conciliacionesSugeridasADeshacer.split(",");
			try { 
				
				conciliacionServicio.deshacerConciliacionesSugeridas(listaConciliacionesSugeridas, userSesion.getUsuario());
				mv.addObject("mensaje",Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.desecho.x.conciliaciones.sugeridad"), String.valueOf(conciliacionServicio.cantRegistros())));
				
			} catch (ConcurrenciaExcepcion e) {
				
				userSesion.setVolviendoAResultadoConciliacion(true);
				ModelAndView mvError = new ModelAndView(ERROR_CONCURRENCIA_VIEW);
				mvError.addObject("lista", true);
				mvError.addObject("mensaje", 
					Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.concurrencia.lista"), 
							", para las conciliaciones ["+e.getIdInconcurrente()+"]"));
				
				mvError.addObject("url","conciliacion-gestionar-resultado-conciliacion");
				return mvError;
			}
			
		}  else {
			mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionNO.mensaje"));
			mv.addObject("error", true);
			mv.addObject("errorMensaje", "Se deberá seleccionar alguna conciliacion sugerida");
		}
		
		mv.addObject("url","conciliacion-gestionar-resultado-conciliacion");
		return mv;
	}
	
	/**
	 * Metodo utilizado para anular un registro avc desde la
	 * pagina de Gestionar conliaciones sugeridas
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/conciliacion-gestionar-conci-suge-anular-registro-avc")
	@ControlProcesoTransacciones
	public ModelAndView conciliacionGestionarConciSugeAnularRegistroAvc(HttpServletRequest request, 
			@ModelAttribute("registroDto") @Valid RegistroAVCDto registroDto, BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		if (result.hasErrors()) {
			return conciliacionGestionarResultadoConciliacion(request, registroDto, result);
		}
		
		String idRegistro = registroDto.getIdRegistroSelected();
		String observaciones = registroDto.getObservacionAnulacion();
		
		registroAVCServicio.anularRegistroAVCSinConciliar(idRegistro, observaciones, userSesion);
		
		ModelAndView mv=new ModelAndView(DESHACER_CONCILIACION_OK_VIEW);
		mv.addObject("mensaje", "Se ha anulado correctamente el registro.");
		mv.addObject("url", "conciliacion-gestionar-resultado-conciliacion");
		return mv;
	}
	
	/**
	 * Anulación masiva de registros AVC sin conciliar
	 * @param request
	 * @param registroDto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/anular-registros-avc")
	public ModelAndView conciliacionGestionarAnularRegistrosAvc(HttpServletRequest request, 
			@ModelAttribute("registroDto") @Valid RegistroAVCDto registroDto, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return conciliacionGestionarResultadoConciliacion(request, registroDto, result);
		}
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		registroAVCServicio.anularRegistrosAVCSinConciliar(registroDto.getRegistrosAVCAAnularSelected(), 
				registroDto.getObservacionAnulacion(), userSesion.getIdUsuario());
		ModelAndView mv=new ModelAndView(DESHACER_CONCILIACION_OK_VIEW);
		mv.addObject("mensaje", "Se ha anulado correctamente los registros.");
		mv.addObject("url", "conciliacion-gestionar-resultado-conciliacion");
		return mv;
	}
	
	/**
	 * Método para volver a la pagina de Resultado Conciliacion.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/conciliacion-volver-resultado-conciliacion")
	public ModelAndView volverResultadoConciliacion(HttpServletRequest request, 
			@ModelAttribute("registroDto") RegistroAVCDto registroDto, BindingResult result) throws Exception {
		
		return conciliacionGestionarResultadoConciliacion(request, registroDto, result);
	}
	
/***************************************************************************
 * Alta valor a partir de registro AVC sin conciliar
 * ************************************************************************/
	/**
	 * Método para ir a la pagina consultar registros avc sin conciliar.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/conciliacion-consultar-registros-avc-sin-conciliar")
	@ControlProcesoTransacciones
	public ModelAndView conciliacionConsultarRegistrosAVCSinConciliar(HttpServletRequest request, 
			 @ModelAttribute("registroAVCDto") RegistroAVCDto registroAVCDto, BindingResult result) throws Exception {
		// Medicion de tiempo al inicio del procesamiento
		double fechaHoraInicioNanoTime = System.nanoTime();
		Date fechaHoraInicio = new Date();
		ModelAndView mv=new ModelAndView(CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_VIEW);
		cargarComboParaEstadosRegistroAVC(mv);
		//envio el dto vacio para borrar datos que quedaban guardados al cancelar
		mv.addObject(VALOR_DTO,new ValorDto());
		// Lista todos los registros avc en estados 'Pendientes de conciliar' o 
		// 'Pendiente de Confirmar Alta de Valor' o 'Alta de Valor Rechazada'.
		if(Constantes.DETAIL.equals(registroAVCDto.getOperation())){
			List<RegistroAVCDto> listaRegistrosAvc = registroAVCServicio.listarRegistrosAVCSinConciliar(new VistaSoporteRegistrosAvcSinConciliarFiltro(), true);
			mv.addObject("listaRegistrosDto",listaRegistrosAvc);
			// Logueamos info de procesamiento (tiempos)
			Traza.loguearInfoProcesamiento(this.getClass(), 
					"conciliacionConsultarRegistrosAVCSinConciliar", fechaHoraInicio, fechaHoraInicioNanoTime, listaRegistrosAvc.size());
		}
		
		return mv;
	} 
	
	@RequestMapping("/conciliacion-buscar")
	@ControlProcesoTransacciones
	public ModelAndView conciliacionBuscar(HttpServletRequest request, 
			@Valid @ModelAttribute("registroAVCDto") RegistroAVCDto registroAVCDto, BindingResult result) throws Exception {
		
//		ModelAndView mv = conciliacionConsultarRegistrosAVCSinConciliar(request, registroAVCDto, result);
		ModelAndView mv=new ModelAndView(CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_VIEW);
		cargarComboParaEstadosRegistroAVC(mv);
		mv.addObject(VALOR_DTO,new ValorDto());
		mv.addObject("registroAVCDto", registroAVCDto);
		if(result.hasErrors()){
			if (Utilidad.getErrorGeneral(result)!=null) {
				throw new ShivaExcepcion(Utilidad.getErrorGeneral(result));
			}
			return mv;
		}
		VistaSoporteRegistrosAvcSinConciliarFiltro filtro = new VistaSoporteRegistrosAvcSinConciliarFiltro();
		filtro.setEstado(registroAVCDto.getEstadoFormateado());
		filtro.setFechaAltaDesdeFormateada(registroAVCDto.getFechaAltaDesdeFormateada());
		filtro.setFechaAltaHastaFormateada(registroAVCDto.getFechaAltaHastaFormateada());
		filtro.setFechaDerivacionDesdeFormateada(registroAVCDto.getFechaDerivacionDesdeFormateada());
		filtro.setFechaDerivacionHastaFormateada(registroAVCDto.getFechaDerivacionHastaFormateada());
		filtro.setNroBoleta(registroAVCDto.getNroBoletaFiltro());
		filtro.setReferenciaDelValor(registroAVCDto.getReferenciaDelValorFiltro());
		filtro.setImporteDesde(registroAVCDto.getImporteDesde());
		filtro.setImporteHasta(registroAVCDto.getImporteHasta());
		request.getSession().setAttribute("busquedaFiltro", filtro);
		List<RegistroAVCDto> listaRegistrosAvc = registroAVCServicio.listarRegistrosAVCSinConciliar(filtro, true);
		
		mv.addObject("listaRegistrosDto",listaRegistrosAvc);
		return mv;
	}
	
	@RequestMapping(value = "/conciliacion-eliminar-tarea-aceptacion-alta-valor")
	public ModelAndView eliminarTarea(HttpServletRequest request,
			@ModelAttribute("valorDto") ValorDto valorDto, BindingResult result) throws Exception {
		
		UsuarioSesion usuarioSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		if (!Validaciones.isNullOrEmpty(valorDto.getValorPorReversion())) {
			valorPorReversionServicio.eliminarTareaAceptarAltaValorPorReversion(valorDto, usuarioSesion);
		} else {
			valorDto.setIdRegistroAvc(valorDto.getIdRegistroAvcSelected());
			registroAVCServicio.eliminarTareaAceptarAltaValorAPartirRegistroAvc(valorDto, usuarioSesion);
		}
		
		ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("eliminarTareaOK.registroAVC.mensaje"));
		mv.addObject("url","bandeja-de-entrada");
		
		return mv;
	}
	
	@RequestMapping(value = "/conciliacion-eliminar-tarea-revision-anulacion-registro-avc")
	public ModelAndView eliminarTareaRevisionAnulacionRegistroAVC(HttpServletRequest request,
			@ModelAttribute("registroDto") RegistroAVCDto registroDto, BindingResult result) throws Exception {
		
		UsuarioSesion usuarioSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		registroAVCServicio.eliminarTareaRevisarAnulacionRegistroAVC(registroDto.getIdRegistroSelected(), usuarioSesion);
		ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("eliminarTareaOK.registroAVC.mensaje"));
		mv.addObject("url","bandeja-de-entrada");
		return mv;
	
	}
	
	/**
	 * Método para ir a la pagina alta de valor a partir de registros avc sin conciliar.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/conciliacion-consultar-registros-avc-sin-conciliar-detalle")
	@ControlProcesoTransacciones
	public ModelAndView conciliacionConsultarRegistrosAVCSinConciliarDetalle(HttpServletRequest request, 
			@ModelAttribute("valorDto") ValorDto valorDto, BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String pantallaRegreso = valorDto.getPantallaRegreso();
		Boolean volverBandeja = Constantes.DESTINO_BANDEJA_ENTRADA.equalsIgnoreCase(pantallaRegreso);
		
		if(!Validaciones.isNullOrEmpty(valorDto.getValorPorReversion())){
			String idValorPorReversion = valorDto.getValorPorReversion();
			valorDto = valorPorReversionServicio.buscarValorCreadoAPartirReversion(idValorPorReversion);
			if (Validaciones.isObjectNull(valorDto)){
				valorDto = valorPorReversionServicio.buscarValorPorReversion(idValorPorReversion);
			}
			valorDto.setValorPorReversion(idValorPorReversion);
		} else {
			String idRegistro = valorDto.getIdRegistroAvcSelected();
			
			//Traigo el valor ya creado a partir del registro Avc
			valorDto = valorServicio.buscarValorCreadoAPartirRegistroAvc(idRegistro);
			
			//Si es null es que no exite ya creado un valor a partir del registro avc
			if(Validaciones.isObjectNull(valorDto)){
				//Creo el valor a partir del resgistro Avc
				valorDto = registroAVCServicio.buscarRegistroAvcYMapearValorDto(idRegistro);
			}
			RegistroAVCDto regAux = registroAVCServicio.buscarRegistroAVC(idRegistro);
			valorDto.setObservacionRegistroAvc(regAux.getObservacion());
			valorDto.setIdRegistroAvc(idRegistro);
			valorDto.setEstadoRegistroAvc(regAux.getEstadoFormateado());
		}
		valorDto.setValorNuevo(false);
		valorDto.setOperation(Constantes.CREAR_VALOR_REGISTRO_AVC);
		valorDto.setIdAnalista(userSesion.getIdUsuario());
		valorDto.setNombreAnalista(userSesion.getNombreCompleto());
		valorDto.setPantallaRegreso(pantallaRegreso);
		valorDto.setVolverBandeja(volverBandeja);
		
		ModelAndView mv=new ModelAndView(CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_DETALLE_VIEW);
		//cargarCodigoLegadoSiebel(mv, valorServicio.listar10CodigosClienteLegadoAviso(userSesion.getIdUsuario()));
		mv.addObject("criterioBusquedaCliente", Arrays.asList(CriterioBusquedaClienteEnum.values()));
		enviarListasCombosAlCargarConciliacion(mv, userSesion, valorDto);
		mv.addObject("imprimibleArchivo", false);
		mv.addObject(VALOR_DTO, valorDto);
		
		/**
		 * Req 67475 Se agrega el bloque de busqueda de clientes
		 * u576779
		 */
		
		
		ClienteFiltro clienteFiltro = new ClienteFiltro();
		clienteFiltro.setBusqueda(valorDto.getCodCliente());
		clienteFiltro.setCriterio(CriterioBusquedaClienteEnum.BUSQUEDA_POR_CLIENTE_LEGADO.getNombre());
		
		ClienteJsonResponse clienteJson = new ClienteJsonResponse();
		
		clienteJson = buscarClientes(clienteFiltro);
		if (!Validaciones.isObjectNull(clienteJson)){
			if(Validaciones.isCollectionNotEmpty(clienteJson.getClientes())){
				valorDto.setCliente(clienteJson.getClientes().get(0));
			}
		}
			
		
		//TipoValor
		ObjectMapper mapper = new ObjectMapper();
		mv.addObject("TipoValorEnum", mapper.writeValueAsString(TipoValorEnum.convertirAMap()));
				
		
		return mv;
	}

	private ClienteJsonResponse buscarClientes(ClienteFiltro clienteFiltro) {
		
		ClienteJsonResponse rta = new ClienteJsonResponse();
		List<ClienteDto> clientesDto = new ArrayList<ClienteDto>();

		try {
			clientesDto = clienteFacade.consultarClienteSiebel(clienteFiltro);
			
			List<? extends DTO> guionesNullClientes = Utilidad.guionesNull(new ArrayList<>(clientesDto));
			List<ClienteDto> listClienteAux = new ArrayList<ClienteDto>();
			for (DTO dto : guionesNullClientes) {
				listClienteAux.add((ClienteDto) dto);
			}
			clientesDto.clear();
			for (ClienteDto clienteDto : listClienteAux) {
				clientesDto.add(clienteDto);
			}
			
			for (ClienteDto cliente : clientesDto) {
				if (cliente.getIdClienteLegado().length() <= 10) {
					cliente.setIdClienteLegado(cliente.getIdClienteLegadoString());
				}
			}
			// Para mantener compatibildiad con el desarrollo
			if (clientesDto.isEmpty()) {
				rta.setSuccess(false);
				rta.setCampoError("#errorBusqueda");
				rta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("valor.error.siebelVacio"));
			} else {
				rta.getClientes().addAll(clientesDto);
				rta.setSuccess(true);
			}
		} catch (Throwable ex) {
			
			if ((ex instanceof ValidacionExcepcion)
					|| (ex.getCause()!=null && ex.getCause() instanceof ValidacionExcepcion)) 
			{
				ValidacionExcepcion ve = null;
				if (ex instanceof ValidacionExcepcion) {
					ve = (ValidacionExcepcion)ex;
				} else {
					ve = (ValidacionExcepcion)ex.getCause();
				}
				rta.setClientes(clientesDto);
				rta.setSuccess(false);
				
				if (!Validaciones.isNullOrEmpty(ve.getCampoError())) {
					rta.setCampoError(ve.getCampoError());
				} else {
					rta.setCampoError("#errorBusqueda"); 
				}
				rta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString(ve.getLeyenda()));
				return rta;
			}
			
			//Otros errores que no sean de la validacion
			Traza.error(getClass(), ex.getMessage(), ex);
			if (ex.getCause()!=null && ex.getCause() instanceof RemoteAccessException) {
				rta.setClientes(clientesDto);
				rta.setSuccess(false);
				rta.setCampoError("#errorBusqueda");
				rta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("boleta.alta.mensaje.siebel.ws.caida"));
				return rta;
			} 
			
			rta.setClientes(clientesDto);
			rta.setSuccess(false);
			rta.setCampoError("#errorBusqueda");
			rta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("boleta.alta.mensaje.siebel.ws.error"));
		}
		return rta;
		
	}


	/**
	 * Metodo llamado al aceptar el alta de un valor a partir de un registro AVC
	 * @param request
	 * @param valor
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/conciliacion-aceptar-alta-valor")
	@ControlProcesoTransacciones
	public ModelAndView conciliacionAceptarAltaValor(HttpServletRequest request, HttpServletResponse res,
			@ModelAttribute("valorDto") @Valid ValorDto valorDto, BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String pantallaRegreso = valorDto.getPantallaRegreso();
		valorDto.setUsuarioModificacion(userSesion.getIdUsuario());
		userSesion.setVolviendoAResultadoConciliacion(true);
		
		if(result.hasErrors() || valorDto.isErrorUnicidadRegistro() || valorDto.isErrorComprobanteVacioModif() || valorServicio.validarRecibo(valorDto).isExisteReciboError()){
			if (Utilidad.getErrorGeneral(result)!=null) {
				throw new ShivaExcepcion(Utilidad.getErrorGeneral(result));
			}
			ModelAndView mv=new ModelAndView(CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_DETALLE_VIEW);
			//mantenerDatosGestionarAltaValor(userSesion, mv,valorDto);
			completarAlProcesArchivo(userSesion, mv,valorDto);
			mv.addObject("criterioBusquedaCliente", Arrays.asList(CriterioBusquedaClienteEnum.values()));
			mv.addObject(VALOR_DTO,valorDto);
			
			return mv;
		}
		try {
			if (Validaciones.isNullOrEmpty(valorDto.getValorPorReversion())){
				registroAVCServicio.generarTarea(valorDto, userSesion);
			} else {
				valorPorReversionServicio.procesarValorPorReversion(valorDto,userSesion);
			}
		} catch (ConcurrenciaExcepcion e) {
			ModelAndView mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
			completarAlProcesArchivo(userSesion, mv,valorDto);
			mv.addObject("url","conciliacion-consultar-registros-avc-sin-conciliar");
			return mv;
		}
			
		ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		
		// Si se accedio desde la bandeja de entrada, se retorna a la misma
		if (Constantes.DESTINO_BANDEJA_ENTRADA.equals(pantallaRegreso)) {
			mv.addObject("url","bandeja-de-entrada");
		} else {
			mv.addObject("url","conciliacion-cancelar-alta-valor");
		}
		return mv;
	}



	/**
	 * Setea los combos de la pagina gestionar alta de valor a partir de registro avc.
	 * Ademas setea la variable imprimibleArchivo en false.
	 * @param userSesion
	 * @param mv
	 * @throws ShivaExcepcion
	 */
	private void mantenerDatosGestionarAltaValor(UsuarioSesion userSesion,ModelAndView mv, ValorDto valorDto) throws ShivaExcepcion, Exception {
		mv.addObject("criterioBusquedaCliente", Arrays.asList(CriterioBusquedaClienteEnum.values()));
		ObjectMapper mapper = new ObjectMapper();
		mv.addObject("TipoValorEnum", mapper.writeValueAsString(TipoValorEnum.convertirAMap()));
		if (Constantes.ORIGEN_REVERSION_ID.equals(valorDto.getIdOrigen())) {
			
			this.enviarListasCombosAlCargarConciliacion(mv,userSesion,valorDto);
			
			
			
			if (Validaciones.isObjectNull(mv.getModel().get(SELECT_ACUERDOS))) {
				valorDto.setComboAcuerdo(true);
			}
			if (Validaciones.isObjectNull(mv.getModel().get(SELECT_CUENTAS))) {
				valorDto.setComboCuenta(true);
			}
			if (Validaciones.isObjectNull(mv.getModel().get(SELECT_BANCOS))) {
				valorDto.setComboBanco(true);
			}
		} else {
			this.enviarListasCombosAlCargar(mv,userSesion,valorDto);
		}
		mv.addObject("imprimibleArchivo", false);
	}
	protected void cargarListaCombosConciliacion (ModelAndView mv,UsuarioSesion userSesion, ValorDto valorDto) throws ShivaExcepcion {
		try {
			// CARGA DE COMBOS AL INGRESAR
			cargarMotivos(mv, valorDto);
			cargarBancoOrigen(mv, valorDto);
			mv.addObject("criterioBusquedaCliente", Arrays.asList(CriterioBusquedaClienteEnum.values()));
			ObjectMapper mapper = new ObjectMapper();
			mv.addObject("TipoValorEnum", mapper.writeValueAsString(TipoValorEnum.convertirAMap()));
			
			List<ShvParamEmpresa> listaEmpresas = (List<ShvParamEmpresa>) combosServicio.listarEmpresasPorUsuario(userSesion);
			String idEmpresa = (listaEmpresas.size() == 1)?listaEmpresas.get(0).getIdEmpresa():valorDto.getIdEmpresa();
			List<ShvParamSegmento> listaSegmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(idEmpresa, userSesion);
			String idSegmento = (listaSegmentos.size() == 1)?listaSegmentos.get(0).getIdSegmento():valorDto.getIdSegmento();
			valorDto.setIdEmpresa(idEmpresa);
			valorDto.setIdSegmento(idSegmento);

			if (listaSegmentos.size() == 1) {
				valorDto.setComboSegmento(false);
			} else {
				valorDto.setComboSegmento(true);
			}
			mv.addObject(SELECT_SEGMENTOS, listaSegmentos);
			if (listaEmpresas.size() == 1) {
				valorDto.setComboEmpresa(false);
			} else {
				valorDto.setComboEmpresa(true);
			}
			mv.addObject(SELECT_EMPRESAS, listaEmpresas);
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		} catch ( JsonGenerationException e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		} catch ( IOException e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	protected void enviarListasCombosAlCargarConciliacion(ModelAndView mv,UsuarioSesion userSesion, ValorDto valorDto) throws ShivaExcepcion {
		try {
			// CARGA DE COMBOS AL INGRESAR
			cargarMotivos(mv, valorDto);
			if (Constantes.ORIGEN_REVERSION_ID.equals(valorDto.getIdOrigen())) {
				cargaEmpresaDinamicaConciliacion(userSesion, mv,valorDto);
			} else {
				cargaEmpresaDinamica(userSesion, mv,valorDto );
			}
			cargarBancoOrigen(mv, valorDto);
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	protected List<ShvParamEmpresa> cargaEmpresaDinamicaConciliacion(UsuarioSesion userSesion, ModelAndView mv, ValorDto valorDto) throws ShivaExcepcion {
		try {
			List<ShvParamEmpresa> listaEmpresas = (List<ShvParamEmpresa>) combosServicio.listarEmpresasPorUsuario(userSesion);

			if (listaEmpresas.size() == 1) {
				cargaSegmentoDinamicaConciliacion(userSesion,mv, valorDto,listaEmpresas);
				valorDto.setComboEmpresa(false);
				
			} else {
				if (!Validaciones.isNullOrEmpty((valorDto.getIdEmpresa()))) {
					cargaSegmentoDinamicaConciliacion(userSesion,mv, valorDto,listaEmpresas);
				}
				valorDto.setComboEmpresa(true);
			}
			mv.addObject(SELECT_EMPRESAS, listaEmpresas);
			return listaEmpresas;
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	/***
	 * 
	 * @param userSesion
	 * @param mv
	 * @param valorDto
	 * @param listaEmpresa
	 * @return
	 * @throws ShivaExcepcion
	 */
	private List<ShvParamSegmento> cargaSegmentoDinamicaConciliacion(UsuarioSesion userSesion, ModelAndView mv, ValorDto valorDto, List<ShvParamEmpresa> listaEmpresa) throws ShivaExcepcion {

		try {
			String idEmpresa = (listaEmpresa.size() == 1)?listaEmpresa.get(0).getIdEmpresa():valorDto.getIdEmpresa();
			List<ShvParamSegmento> listaSegmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(idEmpresa, userSesion);
			String idSegmento = (listaSegmentos.size() == 1)?listaSegmentos.get(0).getIdSegmento():valorDto.getIdSegmento();
			valorDto.setIdEmpresa(idEmpresa);
			valorDto.setIdSegmento(idSegmento);

			if (listaSegmentos.size() == 1) {
				//cargaOrigenDinamica(userSesion, mv,boletaDto );
				cargaTipoValorDinamicaConciliancion(userSesion, mv,valorDto );
				valorDto.setComboSegmento(false);
			} else {
				if(!Validaciones.isNullOrEmpty((valorDto.getIdSegmento()))){
					//cargaOrigenDinamica(userSesion, mv,boletaDto );
					cargaTipoValorDinamicaConciliancion(userSesion, mv,valorDto );
				}
				valorDto.setComboSegmento(true);
			}
			mv.addObject(SELECT_SEGMENTOS, listaSegmentos);
			return listaSegmentos;
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	/**
	 * Me permite cargar los tipos de valores en forma dinamica
	 */
	private List<ShvParamTipoValor> cargaTipoValorDinamicaConciliancion(UsuarioSesion userSesion, ModelAndView mv, ValorDto valorDto ) throws ShivaExcepcion {
		List<ShvParamTipoValor> listaTipoValor = null;

		valorDto.setComboTipoValor(true);
		cargaOrigenDinamicaConciliacion(userSesion, mv, valorDto);
		mv.addObject(SELECT_TIPO_VALOR, listaTipoValor);
		return listaTipoValor;

	}
	
	protected List<ShvParamOrigen> cargaOrigenDinamicaConciliacion(UsuarioSesion userSesion, ModelAndView mv, ValorDto valorDto ) throws ShivaExcepcion {

		try {
			List<ShvParamOrigen> listaOrigenes = new ArrayList<ShvParamOrigen>();
			List<ShvParamAcuerdo> listaAcuerdo = null;

			// PARA BOLETA CON VALOR
			if (!Validaciones.isNullOrEmpty((valorDto.getIdOrigen()))) {
				ShvParamOrigen origen = combosServicio.buscarOrigenPorId(valorDto.getIdOrigen());
				listaOrigenes.add(origen);
				
				listaAcuerdo = combosServicio.actualizarAcuerdoPorIdOrigenValor(
					valorDto.getIdEmpresa(),
					valorDto.getIdSegmento(),
					valorDto.getIdOrigen(),
					valorDto.getIdTipoValor()
				);
				
				if (listaAcuerdo.size() == 1) {
					valorDto.setIdAcuerdo(listaAcuerdo.get(0).getIdAcuerdo().toString());
					valorDto.setComboAcuerdo(false);
				} else {
					valorDto.setComboAcuerdo(true);
				}
				Collections.sort(listaAcuerdo, new ComparatorShvParamAcuerdo());
				mv.addObject(SELECT_ACUERDOS, listaAcuerdo);
			}
			
			cargarBancoCuentaDinamicoConciliacion(mv, listaAcuerdo, valorDto);
			Collection<String> usuariosExcluidos = new ArrayList<String>();
			usuariosExcluidos.add(valorDto.getIdAnalista());

			mv.addObject(SELECT_COPROPIETARIOS, combosServicio.listarCopropietarioPorEmpresaYSegmento(valorDto.getIdEmpresa(), valorDto.getIdSegmento(), usuariosExcluidos));
			mv.addObject("comboCopropietario", true);

			
			return listaOrigenes;
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	protected void cargarBancoCuentaDinamicoConciliacion(ModelAndView mv,List<ShvParamAcuerdo> listaAcuerdo, ValorDto valorDto) throws NegocioExcepcion {
		
		//CUENTA
		Set<ShvParamBancoCuenta> setCuentas = new HashSet<ShvParamBancoCuenta>();
		//listaCuentas = combosServicio.listarCuentaNoConciliables(listaAcuerdo);
		
			
		//BANCO
		Set<ShvParamBanco> setBanco = new HashSet<ShvParamBanco>();
		for (ShvParamAcuerdo acuerdo : listaAcuerdo) {
			setCuentas.add(acuerdo.getBancoCuenta());
			setBanco.add(acuerdo.getBancoCuenta().getBanco());
		}
		List<ShvParamBancoCuenta> listaCuentas = new ArrayList<ShvParamBancoCuenta>(setCuentas);
		List<ShvParamBanco> listaBanco = new ArrayList<ShvParamBanco>(setBanco);
		
		Collections.sort(listaBanco, new ComparatorShvParamBanco());
		Collections.sort(listaCuentas, new ComparatorShvParamBancoCuenta());
		mv.addObject(SELECT_BANCOS, listaBanco);
		
		mv.addObject(SELECT_CUENTAS, listaCuentas);	
		if (listaBanco.size() == 1) {
			valorDto.setIdBancoDeposito(listaBanco.get(0).getIdBanco());
			valorDto.setComboBanco(false);
		} else {
			valorDto.setComboBanco(true);
		}
		if(listaCuentas.size() == 1){
			valorDto.setIdNroCuenta(listaCuentas.get(0).getIdBancoCuenta().toString());
			valorDto.setComboCuenta(false);
		} else {
			valorDto.setComboCuenta(true);
		}				
	}
	/**
	 * Metodo llamado al cancelar el alta de un valor a partir de un registro AVC
	 * @param request
	 * @param valor
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/conciliacion-cancelar-alta-valor")
	public ModelAndView conciliacionCancelarAltaValor(HttpServletRequest request, RegistroAVCDto registroAVCDto, BindingResult result) throws Exception {
		if(!Validaciones.isObjectNull(request.getSession().getAttribute("busquedaFiltro"))){
			VistaSoporteRegistrosAvcSinConciliarFiltro registroAVCDtoSession = (VistaSoporteRegistrosAvcSinConciliarFiltro) request.getSession().getAttribute("busquedaFiltro");
			registroAVCDto.setOperation(Constantes.DETAIL);
			if(!Validaciones.isObjectNull(registroAVCDtoSession)){
				registroAVCDto.setEstadoFormateado(registroAVCDtoSession.getEstado());
				registroAVCDto.setFechaAltaDesdeFormateada(registroAVCDtoSession.getFechaAltaDesdeFormateada());
				registroAVCDto.setFechaAltaHastaFormateada(registroAVCDtoSession.getFechaAltaHastaFormateada());
				registroAVCDto.setFechaDerivacionDesdeFormateada(registroAVCDtoSession.getFechaDerivacionDesdeFormateada());
				registroAVCDto.setFechaDerivacionHastaFormateada(registroAVCDtoSession.getFechaDerivacionHastaFormateada());
				registroAVCDto.setNroBoletaFiltro(registroAVCDtoSession.getNroBoleta());
				registroAVCDto.setReferenciaDelValorFiltro(registroAVCDtoSession.getReferenciaDelValor());
				registroAVCDto.setImporteDesde(registroAVCDtoSession.getImporteDesde());
				registroAVCDto.setImporteHasta(registroAVCDtoSession.getImporteHasta());
			}			
			ModelAndView mv = conciliacionBuscar(request, registroAVCDto, result);
			return mv;
		} else {
			ModelAndView mv = conciliacionConsultarRegistrosAVCSinConciliar(request, new RegistroAVCDto(), result);
			return mv;
		}
	}
	
	
	private void completarAlProcesArchivo(UsuarioSesion userSesion,ModelAndView mv, ValorDto valorDto) throws ShivaExcepcion, Exception {
		if (Constantes.ORIGEN_REVERSION_ID.equals(valorDto.getIdOrigen())) {
			if (
					!Validaciones.isNullEmptyOrDash(valorDto.getIdAcuerdo()) ||
					!Validaciones.isNullEmptyOrDash(valorDto.getNumeroCuenta()) ||
					!Validaciones.isNullEmptyOrDash(valorDto.getIdBancoDeposito())
				) {
					this.cargarListaCombosConciliacion(mv, userSesion, valorDto);
					if (!Validaciones.isNullEmptyOrDash(valorDto.getIdAcuerdo())) {

//						valorDto.setIdNroCuenta(String.valueOf(bancoCuenta.getIdBancoCuenta()));
						valorDto.setComboBanco(false);
						valorDto.setComboCuenta(false);
						this.cargarCombosAcuerdoYCopropietario(valorDto, userSesion, mv);
					} else if (!Validaciones.isNullEmptyOrDash(valorDto.getNumeroCuenta())) {
			
//						valorDto.setIdBancoDeposito(bancoCuentaAux.getBanco().getIdBanco());
//						valorDto.setIdAcuerdo(String.valueOf(acuerdoAux.getIdAcuerdo()));
						this.cargarCombosAcuerdoYCopropietario(valorDto, userSesion, mv);
					} else {
						List<ShvParamAcuerdo> listaAcuerdos =  combosServicio.actualizarAcuerdoPorIdOrigen(valorDto.getIdEmpresa(),valorDto.getIdSegmento(),String.valueOf(valorDto.getIdOrigen()));
						Iterator<ShvParamAcuerdo> iteradorAcuerdo = listaAcuerdos.iterator();

						if(!Validaciones.isEmptyString(valorDto.getIdBancoDeposito())){
							while(iteradorAcuerdo.hasNext()){
								ShvParamAcuerdo acuerdo= iteradorAcuerdo.next();
								if (!acuerdo.getBancoCuenta().getBanco().getIdBanco().equals(valorDto.getIdBancoDeposito())){
									iteradorAcuerdo.remove();
								}
							}
						}
						Collections.sort(listaAcuerdos, new ComparatorShvParamAcuerdo());
						mv.addObject(SELECT_ACUERDOS,listaAcuerdos );
						List<ShvParamBancoCuenta> listaCuentas = combosServicio.actualizarCuentaPorIdOrigen(valorDto.getIdEmpresa(),valorDto.getIdSegmento(),String.valueOf(valorDto.getIdOrigen()));
						Iterator<ShvParamBancoCuenta> iteradorCuentas = listaCuentas.iterator();
						/**@author fabio.giaquinta.ruiz
						 * 16-09-2014
						 * se agrego un if para que no excluya ningun acuerdo cuando el idBanco es empty
						 */
						if(!Validaciones.isEmptyString(valorDto.getIdBancoDeposito())){
							while(iteradorCuentas.hasNext()){
								ShvParamBancoCuenta cuenta= iteradorCuentas.next();
								if (!cuenta.getBanco().getIdBanco().equals(valorDto.getIdBancoDeposito())){
									iteradorCuentas.remove();
								}
							}
						}
						List<ShvParamBanco> listaBancos = combosServicio.actualizarBancoPorIdOrigen(valorDto.getIdEmpresa(),valorDto.getIdSegmento(),String.valueOf(valorDto.getIdOrigen()));
						Collections.sort(listaBancos, new ComparatorShvParamBanco());
						Collections.sort(listaCuentas, new ComparatorShvParamBancoCuenta());
						mv.addObject(SELECT_CUENTAS, listaCuentas);
						mv.addObject(SELECT_BANCOS, listaBancos);
						
						
						
						
						//mantenerDatosGestionarAltaValor(userSesion, mv,valorDto);
						//cargarCodigoLegadoSiebel(mv, valorServicio.listar10CodigosClienteLegadoAviso(userSesion.getIdUsuario()));
						mv.addObject(VALOR_DTO, valorDto);
						mv.setViewName(CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_DETALLE_VIEW);
						mv.addObject("imprimibleArchivo", false);
					}
					
					//if (Validaciones.isNullEmptyOrDash(valorDto.getIdAcuerdo())) {
						valorDto.setComboAcuerdo(true);
					//}
					//if (Validaciones.isNullEmptyOrDash(valorDto.getNumeroCuenta())) {
						valorDto.setComboAcuerdo(true);
					//}
					//if (Validaciones.isNullEmptyOrDash(valorDto.getIdBancoDeposito())) {
						valorDto.setComboBanco(true);
					//}
				} else {
					this.mantenerDatosGestionarAltaValor(userSesion, mv,valorDto);	
				}
		} else {
			this.mantenerDatosGestionarAltaValor(userSesion, mv,valorDto);	
		}
		mv.addObject("imprimibleArchivo", false);
	} 
	/**
	 * Metodo para adjuntar un archivo a la tabla.
	 */
	@RequestMapping("/conciliacion-alta-valor-alta-comprobante-conciliacion")
	public ModelAndView conciliacionAltaValorAltaComprobanteAvisoPago(HttpServletRequest request,
			@ModelAttribute("valorDto") @Valid ValorDto valorDto, 
			BindingResult result) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String descripcionComprobante = request.getParameter("descripcionComprobante");

		MultipartFile file;
		file = valorDto.getFileComprobanteModificacion();
		ModelAndView mv = new ModelAndView(CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_DETALLE_VIEW);
		this.cargarCopropietario(mv, valorDto);
		if(result.hasErrors()){
			completarAlProcesArchivo(userSesion, mv,valorDto);
			mv.addObject(VALOR_DTO,valorDto);
			return mv;
		}
		
		if (!(Validaciones.isNullOrEmpty(descripcionComprobante)|| Validaciones.isNullOrEmpty(file.getOriginalFilename()) || file.isEmpty())){
			ComprobanteDto comprobanteDto = new ComprobanteDto();
			comprobanteDto.setDescripcionArchivo(descripcionComprobante);
			comprobanteDto.setUsuarioCreacion(userSesion.getIdUsuario());
			comprobanteDto.setNombreArchivo(file.getOriginalFilename());
			comprobanteDto.setDocumento(file.getBytes());
//			if(comprobanteDto.getDocumento().length==0){
//				result.addError(new FieldError("errorArchivoVacio", "valor.error.archivoVacio","default"));
//			}
	
			valorDto.getListaComprobantes().add(comprobanteDto);
	
			if(Validaciones.isCollectionNotEmpty(valorDto.getListaComprobantes())){
				int i = 0;
				Iterator<ComprobanteDto> it = valorDto.getListaComprobantes().iterator();
				while (it.hasNext()) {
					ComprobanteDto comp = it.next();
					comp.setId(i);
					i++;
				}
			}
		}
		valorDto.setDescripcionComprobante("");
		
		this.completarAlProcesArchivo(userSesion, mv,valorDto);	
		
		mv.addObject("imprimibleArchivo", false);
		mv.addObject(VALOR_DTO, valorDto);	
		return mv;
	}
		
	/**
	 * Metodo para eliminar un documento de la tabla
	 * @param request
	 * @param transferenciaDto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/conciliacion-alta-valor-eliminar-comprobante-conciliacion")
	public ModelAndView conciliacionAltaValorEliminarComprobanteAvisoPago(HttpServletRequest request,
			@ModelAttribute("valorDto") ValorDto valorDto, BindingResult result) throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String idComprobante = valorDto.getIdComprobanteSelected();
		ModelAndView mv = new ModelAndView(CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_DETALLE_VIEW);
		
		Iterator<ComprobanteDto> it = valorDto.getListaComprobantes().iterator();
		while (it.hasNext()) {
			ComprobanteDto comp = it.next();
			if (idComprobante.equals(comp.getId().toString())) {
				it.remove();
			}
		}
		
		completarAlProcesArchivo(userSesion, mv,valorDto);
		mv.addObject(VALOR_DTO, valorDto);	
		return mv;
	}
 
	/**
	 * Metodo para abrir el documento 
	 * @param request
	 * @param res
	 * @param transferenciaDto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/conciliacion-alta-valor-abrir-comprobante-conciliacion")
	public ModelAndView conciliacionAltaValorAbrirComprobanteConciliacion(HttpServletRequest request, HttpServletResponse res,
			@ModelAttribute("valorDto") ValorDto valorDto, BindingResult result) throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String idComprobante = valorDto.getIdComprobanteSelected();
		ModelAndView mv = new ModelAndView(CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_DETALLE_VIEW);
		
		completarAlProcesArchivo(userSesion, mv,valorDto);
		
		
		if(Validaciones.isCollectionNotEmpty(valorDto.getListaComprobantes())){
			Iterator<ComprobanteDto> it = valorDto.getListaComprobantes().iterator();
			while (it.hasNext()) {
				ComprobanteDto comp = it.next();
				if (idComprobante.equals(comp.getId().toString())) {
					request.getSession().setAttribute( "documentoImprimible" , comp.getDocumento());
					request.getSession().setAttribute( "nombreDocumento" , comp.getNombreArchivo());
					mv.addObject("imprimibleArchivo", true);	
				}
			}
		}
		mv.addObject(VALOR_DTO, valorDto);	
		return mv;
	}
	
	/**
	 * Metodo que se ejecuta cuando seleccionan un segmento.
	 * @param request
	 * @param valorDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/conciliacion-alta-valor-selecciono-segmento")
	public ModelAndView seleccionoSegmento(HttpServletRequest request,  @ModelAttribute("valorDto") ValorDto valorDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		//valorDto.setIdAcuerdo(null);
		//valorDto.setIdBancoDeposito(null);
		//valorDto.setNumeroCuenta(null);
		ModelAndView mv = new ModelAndView(CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_DETALLE_VIEW);
		mantenerDatosGestionarAltaValor(userSesion, mv,valorDto);
		//cargarCodigoLegadoSiebel(mv, valorServicio.listar10CodigosClienteLegadoAviso(userSesion.getIdUsuario()));
		mv.addObject(VALOR_DTO, valorDto);
		return mv;
	}


/***************************************************************************
 * Confirmar Alta de valor a partir de registro AVC sin conciliar
 * ************************************************************************/
	
	/**
	 * Método utilizado por la bandeja de entrada para Confirmar alta de valor a partir 
	 * de registro avc sin conciliar.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/conciliacion-confirmar-alta-valor")
	public ModelAndView confirmarAltaValorAPartirRegistroAvc(HttpServletRequest request) throws Exception {
		ValorDto valorDto = new ValorDto();
		String idRegistroAvc = request.getParameter("idRegistroBandeja");
		String valorPorReversion = request.getParameter("idValorPorReversion");
		if (!Validaciones.isNullOrEmpty(idRegistroAvc)) {
			ShvAvcRegistroAvcValor registroValor = registroAVCServicio.buscarRegistroAVCValor(idRegistroAvc);
			valorDto = valorServicio.buscarValorModificacion(registroValor.getShvAvcRegistroAvcValorPK().getValor());
			RegistroAVCDto regAux = registroAVCServicio.buscarRegistroAVC(idRegistroAvc);
			valorDto.setObservacionRegistroAvc(regAux.getObservacion());
			valorDto.setIdRegistroAvc(idRegistroAvc);
		} else if (!Validaciones.isNullOrEmpty(valorPorReversion)) {
			ShvValReversionValor reversionValor = valorPorReversionServicio.buscarReversionValor(valorPorReversion);
			valorDto = valorServicio.buscarValorModificacion(reversionValor.getShvValReversionValorPK().getValor());
			valorDto.setValorPorReversion(valorPorReversion);
			valorDto.setTimeStamp(String.valueOf(reversionValor.getShvValReversionValorPK().getValorPorReversion().getWorkflow().getFechaUltimaModificacion().getTime()));
		}
	
		valorDto.setOperation(Constantes.CONFIRMAR_VALOR_REGISTRO_AVC);
		ModelAndView mv = new ModelAndView(CONFIRMAR_ALTA_REGISTRO_AVC_SIN_CONCILIAR_DETALLE_VIEW);
		mv.addObject(VALOR_DTO, valorDto);
		mv.addObject("imprimibleArchivo", false);
		return mv;
	}
	
	
	/**
	 * Metodo que se llama al hacer click en el boton Confirmar en la pantalla 
	 * "Confirmar alta de valor a partir de registro AVC".
	 * @param request
	 * @param valorDto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/conciliacion-aceptar-tarea-alta-registro-avc-sin-conciliar")
	public ModelAndView confirmarTareaAltaRegistroAvcSinConciliar(HttpServletRequest request,
			@ModelAttribute("valorDto") @Valid ValorDto valorDto, BindingResult result) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		valorDto.setUsuarioModificacion(userSesion.getUsuario());
		try {
			if (Validaciones.isNullOrEmpty(valorDto.getValorPorReversion())){
				registroAVCServicio.confirmarTareaAltaValorAPartirRegistroAvc(valorDto);
			} else {
				valorPorReversionServicio.confirmarTareaAltaValor(valorDto);
			}
		}catch (ConcurrenciaExcepcion e) {
			ModelAndView mv = new ModelAndView(ERROR_CONCURRENCIA_VIEW);
			mv.addObject("url", BANDEJA_ENTRADA_VIEW_GET);
			return mv;
		}
		
		ModelAndView mv = new ModelAndView(ACTUALIZACION_OK_VIEW);			
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		mv.addObject("url", BANDEJA_ENTRADA_VIEW_GET);
		return mv;
	}
	
	/**
	 * Metodo que se llama al hacer click en el boton rechazar en la pantalla 
	 * "Confirmar alta de valor a partir de registro AVC".
	 * @param request
	 * @param valorDto
	 * @param result
	 * @return
	 */
	@RequestMapping("/conciliacion-rechazar-tarea-alta-registro-avc-sin-conciliar")
	public ModelAndView rechazarTareaAltaRegistroAvcSinConciliar(HttpServletRequest request,
				@ModelAttribute("valorDto") @Valid ValorDto valorDto, BindingResult result) throws Exception{
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView(CONFIRMAR_ALTA_REGISTRO_AVC_SIN_CONCILIAR_DETALLE_VIEW);
			mv.addObject(VALOR_DTO, valorDto);
			mv.addObject("imprimibleArchivo", false);
			return mv;
		}
		
		valorDto.setUsuarioModificacion(userSesion.getUsuario());
		try {
			if (Validaciones.isNullOrEmpty(valorDto.getValorPorReversion())){
				registroAVCServicio.rechazarTareaAltaValorAPartirRegistroAvc(valorDto, userSesion);
			} else {
				valorPorReversionServicio.rechazarTareaAltaValor(valorDto);
			}
		}catch (ConcurrenciaExcepcion e) {
			ModelAndView mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
			
//						if (Constantes.DESTINO_BANDEJA_ENTRADA.equalsIgnoreCase(valorDto.getPantallaDestino())) {
			mv.addObject("url",BANDEJA_ENTRADA_VIEW_GET);
//						} else {
//						mv.addObject("url",CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_VIEW);
//						}
			return mv;
		}
		ModelAndView mv = new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		mv.addObject("url", "bandeja-de-entrada");
		return mv;
	}
	
	/**
	 * Metodo para exportar el archivo seleccionado.
	 * @param request
	 * @param valorDto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/procesar-abrir-comprobante-alta-registro-avc-sin-conciliar")
	public ModelAndView procesarAbrirComprobanteAltaRegistroAvcSinConciliar(HttpServletRequest request,
			@ModelAttribute("valorDto") ValorDto valorDto,
			BindingResult result) throws Exception {

		String idComprobante = valorDto.getIdComprobanteSelected();
		
		ModelAndView mv = new ModelAndView(CONFIRMAR_ALTA_REGISTRO_AVC_SIN_CONCILIAR_DETALLE_VIEW);

		Iterator<ComprobanteDto> it = valorDto.getListaComprobantes().iterator();
		while (it.hasNext()) {
			ComprobanteDto comp = it.next();
			if (idComprobante.equals(comp.getId().toString())) {
				request.getSession().setAttribute( "documentoImprimible" , comp.getDocumento());
				request.getSession().setAttribute( "nombreDocumento" , comp.getNombreArchivo());
			    mv.addObject("imprimibleArchivo", true);
			}
		}
		mv.addObject(VALOR_DTO, valorDto);
		return mv;
	}
	
	public TipoTareaGestionaEnum getTipoTareaGestionaPorIdTipoValor(Long idTipoValor) {
		switch (TipoValorEnum.getEnumByIdTipoValor(idTipoValor)) {
	    case CHEQUE:
	    	return TipoTareaGestionaEnum.VALOR_CHEQUE;
	    case CHEQUE_DIFERIDO:
	    	return TipoTareaGestionaEnum.VALOR_CHEQUE_DIFERIDO;
	    case EFECTIVO:
	    	return TipoTareaGestionaEnum.VALOR_EFECTIVO;
	    case INTERDEPÓSITO:
	    	return TipoTareaGestionaEnum.VALOR_INTERDEPOSITO;
	    case TRANSFERENCIA:
	    	return TipoTareaGestionaEnum.VALOR_TRANSF;
		default:
	    	break;
		};
		
		return null;
	}
	
	/***************************************************************************
	 *                                  ACUERDO                                *
	 * ************************************************************************/
	
	@RequestMapping("/seleccionoAcuerdoConciliacion")
	public ModelAndView seleccionoAcuerdo(HttpServletRequest request,  @ModelAttribute("valorDto") ValorDto valorDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		if ("".equalsIgnoreCase(valorDto.getIdAcuerdo())) {
			//selecciono "Seleccione un item.."
			valorDto.setIdBancoDeposito("");
			valorDto.setIdNroCuenta("");
		} else {
			//Traigo la cuenta que coincide con el acuerdo selecionado
			ShvParamBancoCuenta bancoCuenta = combosServicio.buscarBancoCuentaPorAcuerdo(valorDto.getIdAcuerdo());
			valorDto.setIdBancoDeposito(bancoCuenta.getBanco().getIdBanco());
			valorDto.setIdNroCuenta(String.valueOf(bancoCuenta.getIdBancoCuenta()));
			valorDto.setComboBanco(false);
			valorDto.setComboCuenta(false);
		}
		ModelAndView mv = new ModelAndView(CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_DETALLE_VIEW);
		//enviarListasCombosAlCargar(mv,userSesion,valorDto);
		this.cargarListaCombosConciliacion(mv, userSesion, valorDto);
		cargarCombosAcuerdoYCopropietario(valorDto, userSesion, mv);
		mv.addObject("imprimibleArchivo", false);
		
		//cargarCodigoLegadoSiebel(mv, valorServicio.listar10CodigosClienteLegadoAviso(userSesion.getIdUsuario()));
		mv.addObject(VALOR_DTO, valorDto);
		mv.setViewName(CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_DETALLE_VIEW);
		return mv;
	}


	
	
	private void cargarCombosAcuerdoYCopropietario(ValorDto valorDto,UsuarioSesion userSesion, ModelAndView mv) throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(valorDto.getIdEmpresa()) && !Validaciones.isNullOrEmpty(valorDto.getIdSegmento())) {
			Collection<String> usuariosExcluidos = new ArrayList<String>();
			usuariosExcluidos.add(userSesion.getIdUsuario());
			usuariosExcluidos.add(valorDto.getIdAnalista());
			mv.addObject(SELECT_COPROPIETARIOS, combosServicio.listarCopropietarioPorEmpresaYSegmento(valorDto.getIdEmpresa(),valorDto.getIdSegmento(),usuariosExcluidos));
			mv.addObject("comboCopropietario", true);
			
			
			List<ShvParamAcuerdo> listaAcuerdos =  combosServicio.actualizarAcuerdoPorIdOrigen(valorDto.getIdEmpresa(),valorDto.getIdSegmento(),String.valueOf(valorDto.getIdOrigen()));
			List<ShvParamBanco> listaBancos = combosServicio.actualizarBancoPorIdOrigen(valorDto.getIdEmpresa(),valorDto.getIdSegmento(),String.valueOf(valorDto.getIdOrigen()));
			List<ShvParamBancoCuenta> listaCuentas = combosServicio.actualizarCuentaPorIdOrigen(valorDto.getIdEmpresa(),valorDto.getIdSegmento(),String.valueOf(valorDto.getIdOrigen()));
			
			Collections.sort(listaAcuerdos, new ComparatorShvParamAcuerdo());
			Collections.sort(listaBancos, new ComparatorShvParamBanco());
			Collections.sort(listaCuentas, new ComparatorShvParamBancoCuenta());
			mv.addObject(SELECT_ACUERDOS, listaAcuerdos);
			mv.addObject(SELECT_BANCOS, listaBancos);
			mv.addObject(SELECT_CUENTAS, listaCuentas);
		
		
			if(listaAcuerdos.size()>1){
				valorDto.setComboAcuerdo(true);
			} else {
				valorDto.setComboAcuerdo(false);
			}
			if(listaCuentas.size()>1){
				valorDto.setComboCuenta(true);
			} else {
				valorDto.setComboCuenta(false);
			}
			valorDto.setComboBanco(true);
			
		}
	}
	
	@RequestMapping("/seleccionoCuentaConciliacion")
	public ModelAndView seleccionoCuenta(HttpServletRequest request,  @ModelAttribute("valorDto") ValorDto valorDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		if(!Validaciones.isEmptyString(valorDto.getIdNroCuenta())){
			//Traigo la cuenta que coincide con el acuerdo selecionado
			ShvParamAcuerdo acuerdoAux = combosServicio.buscarAcuerdoPorIdCuenta(valorDto.getIdNroCuenta());
			ShvParamBancoCuenta  bancoCuentaAux = combosServicio.buscarBancoCuentaPorCuenta(valorDto.getIdNroCuenta());
			valorDto.setIdBancoDeposito(bancoCuentaAux.getBanco().getIdBanco());
			valorDto.setIdAcuerdo(String.valueOf(acuerdoAux.getIdAcuerdo()));
		}else{
			valorDto.setIdBancoDeposito("");
			valorDto.setIdAcuerdo("");
		}

		ModelAndView mv = new ModelAndView(CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_DETALLE_VIEW);
		//enviarListasCombosAlCargar(mv,userSesion,valorDto);
		this.cargarCombosAcuerdoYCopropietario(valorDto, userSesion, mv);
		
		//cargarCodigoLegadoSiebel(mv, valorServicio.listar10CodigosClienteLegadoAviso(userSesion.getIdUsuario()));
		this.cargarListaCombosConciliacion(mv, userSesion, valorDto);
		mv.addObject(VALOR_DTO, valorDto);
		mv.setViewName(CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_DETALLE_VIEW);
		mv.addObject("imprimibleArchivo", false);
		return mv;
	}
	
	@RequestMapping("/seleccionoBancoConciliacion")
	public ModelAndView seleccionoBanco(HttpServletRequest request,  @ModelAttribute("valorDto") ValorDto valorDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		ModelAndView mv = new ModelAndView(CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_DETALLE_VIEW);
		//this.enviarListasCombosAlCargar(mv, userSesion, valorDto);
//		Collection<String> usuariosExcluidos = new ArrayList<String>();
//		usuariosExcluidos.add(userSesion.getIdUsuario());
//		usuariosExcluidos.add(valorDto.getIdAnalista());
//		mv.addObject(SELECT_COPROPIETARIOS, combosServicio.listarCopropietarioPorEmpresaYSegmento(valorDto.getIdEmpresa(),valorDto.getIdSegmento(),usuariosExcluidos));
//		mv.addObject("comboCopropietario", true);
	
		
		
		List<ShvParamAcuerdo> listaAcuerdos =  combosServicio.actualizarAcuerdoPorIdOrigen(valorDto.getIdEmpresa(),valorDto.getIdSegmento(),String.valueOf(valorDto.getIdOrigen()));
		Iterator<ShvParamAcuerdo> iteradorAcuerdo = listaAcuerdos.iterator();
		
		/**@author fabio.giaquinta.ruiz
		 * se agrego un if para que no excluya ningun acuerdo cuando el idBanco es empty
		 * 16-09-2014
		 */
		if(!Validaciones.isEmptyString(valorDto.getIdBancoDeposito())){
			while(iteradorAcuerdo.hasNext()){
				ShvParamAcuerdo acuerdo= iteradorAcuerdo.next();
				if (!acuerdo.getBancoCuenta().getBanco().getIdBanco().equals(valorDto.getIdBancoDeposito())){
					iteradorAcuerdo.remove();
				}
			}
		}
		Collections.sort(listaAcuerdos, new ComparatorShvParamAcuerdo());
		mv.addObject(SELECT_ACUERDOS,listaAcuerdos );
		List<ShvParamBancoCuenta> listaCuentas = combosServicio.actualizarCuentaPorIdOrigen(valorDto.getIdEmpresa(),valorDto.getIdSegmento(),String.valueOf(valorDto.getIdOrigen()));
		Iterator<ShvParamBancoCuenta> iteradorCuentas = listaCuentas.iterator();
		/**@author fabio.giaquinta.ruiz
		 * 16-09-2014
		 * se agrego un if para que no excluya ningun acuerdo cuando el idBanco es empty
		 */
		if(!Validaciones.isEmptyString(valorDto.getIdBancoDeposito())){
			while(iteradorCuentas.hasNext()){
				ShvParamBancoCuenta cuenta= iteradorCuentas.next();
				if (!cuenta.getBanco().getIdBanco().equals(valorDto.getIdBancoDeposito())){
					iteradorCuentas.remove();
				}
			}
		}
		
		List<ShvParamBanco> listaBancos = combosServicio.actualizarBancoPorIdOrigen(valorDto.getIdEmpresa(),valorDto.getIdSegmento(),String.valueOf(valorDto.getIdOrigen()));
		Collections.sort(listaBancos, new ComparatorShvParamBanco());
		Collections.sort(listaCuentas, new ComparatorShvParamBancoCuenta());
		mv.addObject(SELECT_CUENTAS, listaCuentas);
		mv.addObject(SELECT_BANCOS, listaBancos);
		
		if(listaAcuerdos.size()>1){
			valorDto.setIdAcuerdo("");
			valorDto.setComboAcuerdo(true);
		} else {
			valorDto.setIdAcuerdo(String.valueOf(listaAcuerdos.get(0).getIdAcuerdo()));
			valorDto.setComboAcuerdo(false);
		}
		if(listaCuentas.size()>1){
			valorDto.setIdNroCuenta("");
			valorDto.setComboCuenta(true);
		} else {
			valorDto.setIdNroCuenta(String.valueOf(listaCuentas.get(0).getIdBancoCuenta()));
			valorDto.setComboCuenta(false);
		}
		this.cargarCopropietario(mv, valorDto);
		this.cargarListaCombosConciliacion(mv, userSesion, valorDto);
		//mantenerDatosGestionarAltaValor(userSesion, mv,valorDto);
		//cargarCodigoLegadoSiebel(mv, valorServicio.listar10CodigosClienteLegadoAviso(userSesion.getIdUsuario()));
		mv.addObject(VALOR_DTO, valorDto);
		mv.setViewName(CONSULTAR_REGISTROS_AVC_SIN_CONCILIAR_DETALLE_VIEW);
		mv.addObject("imprimibleArchivo", false);
		return mv;
	}
	@RequestMapping(value = "conciliacion-valor-alta/busquedaClientes", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ClienteJsonResponse buscarClientes(@RequestBody final ClienteFiltro clienteFiltro, HttpServletRequest request) throws Exception {
		return buscarClientes(clienteFiltro);
	}
    
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	
	public IConciliacionServicio getConciliacionServicio() {
		return conciliacionServicio;
	}

	public void setConciliacionServicio(IConciliacionServicio conciliacionServicio) {
		this.conciliacionServicio = conciliacionServicio;
	}

	public IArchivoAVCServicio getArchivoAVCServicio() {
		return archivoAVCServicio;
	}

	public void setArchivoAVCServicio(IArchivoAVCServicio archivoAVCServicio) {
		this.archivoAVCServicio = archivoAVCServicio;
	}

	public ICobroImputacionServicio getCobroServicio() {
		return cobroServicio;
	}

	public void setCobroServicio(ICobroImputacionServicio cobroServicio) {
		this.cobroServicio = cobroServicio;
	}

	public RegistroAvcValorValidacionWeb getRegistroAvcValorValidacionWeb() {
		return registroAvcValorValidacionWeb;
	}


	public void setRegistroAvcValorValidacionWeb(
			RegistroAvcValorValidacionWeb registroAvcValorValidacionWeb) {
		this.registroAvcValorValidacionWeb = registroAvcValorValidacionWeb;
	}


	public IValorServicio getValorServicio() {
		return valorServicio;
	}


	public void setValorServicio(IValorServicio valorServicio) {
		this.valorServicio = valorServicio;
	}


	public BoletaConValorValidacionWeb getBoletaConValorValidacionWeb() {
		return boletaConValorValidacionWeb;
	}


	public void setBoletaConValorValidacionWeb(
			BoletaConValorValidacionWeb boletaConValorValidacionWeb) {
		this.boletaConValorValidacionWeb = boletaConValorValidacionWeb;
	}

}
