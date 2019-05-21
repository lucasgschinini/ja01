package ar.com.telecom.shiva.presentacion.controlador;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.telecom.shiva.base.anotaciones.ControlProcesoTransacciones;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.OrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.otros.ConcurrenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;
import ar.com.telecom.shiva.negocio.dto.ComboAcuerdoBancoCuentaGralDto;
import ar.com.telecom.shiva.negocio.dto.TipoValorDto;
import ar.com.telecom.shiva.negocio.servicios.IBoletaSinValorServicio;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCuenta;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrigen;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ActualizacionExitosaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.SelectOptionJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ValorJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaSinValorFiltro;
import ar.com.telecom.shiva.presentacion.bean.validacion.BoletaSinValorValidacionWeb;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.CurrencyEditor;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.CustomBindingErrorProcessor;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.IntegerEditor;
import ar.com.telecom.shiva.presentacion.facade.IClienteFacade;
import ar.com.telecom.shiva.presentacion.facade.ICombosFacade;

@Controller
public class BoletaController extends Controlador {

	
	private static final String ALTA_VIEW = "boleta/boletas-alta";
	private static final String BUSQUEDA_VIEW = "boleta/boletas-busqueda";
	private static final String MODIFICACION_VIEW = "boleta/boletas-modificacion";
	private static final String BOLETA_AUTORIZACION_OK_VIEW = "boleta/boletas-actualizacion-exitosa";
	
	private static final String OBJECT_COMMAND = "boletaSinValorDto";
	private static final String BOLETA_FILTRO = "boletaSinValorFiltro";
	private static final String LISTA_BOLETAS_DTO = "listaBoletasDto";
	
	
	@Autowired
	private IBoletaSinValorServicio boletaSinValorServicio;
	@Autowired
	private BoletaSinValorValidacionWeb boletaSinValorValidacionWeb;
	@Autowired 
	private IClienteSiebelServicio clienteSiebelServicio;
	@Autowired
	private IClienteFacade clienteFacade;
	@Autowired
	private ICombosFacade combosFacade;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.setBindingErrorProcessor(new CustomBindingErrorProcessor());

		DateFormat dateFormat = new SimpleDateFormat(Utilidad.DATE_FORMAT);
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
		binder.registerCustomEditor(BigDecimal.class, new CurrencyEditor());
		binder.registerCustomEditor(Integer.class, new IntegerEditor());

		if (binder.getTarget() instanceof BoletaSinValorDto 
				|| binder.getTarget() instanceof BoletaSinValorFiltro) {
			binder.setValidator(boletaSinValorValidacionWeb);
		}
	}
	
	/***
	 * Alta
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/boletas-alta")
	public ModelAndView boletasAlta(HttpServletRequest request, BoletaSinValorDto boletaSinValorDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		boletaSinValorDto.setOperation(Constantes.CREATE);
		boletaSinValorDto.setIdAnalista(userSesion.getUsuario());
		boletaSinValorDto.setNombreAnalista(userSesion.getNombreCompleto());
		
		ModelAndView mv = new ModelAndView(ALTA_VIEW);
		ObjectMapper mapper = new ObjectMapper();
		
		//cargarAcuerdo(mv,boletaSinValorDto);
		//cargarBanco(mv,boletaSinValorDto);
	//	cargarCuenta(mv,boletaSinValorDto);
		//cargarOrigen(mv,boletaSinValorDto);
		cargarCopropietario(mv, boletaSinValorDto);
		enviarListasCombosAlCargar(mv, userSesion, boletaSinValorDto);
		//cargarCodigoLegadoSiebel(mv, boletaSinValorServicio.listar10CodigosClienteLegado(userSesion.getIdUsuario()));
		List<TipoValorDto> tiposValor = combosFacade.buscarTiposValor("TA", "BSV");
		Set<Integer> listaIdTipoValor = new HashSet<Integer>();
		for (TipoValorDto dto : tiposValor) {
			listaIdTipoValor.add(dto.getIdTipoValor());
		}
		mv.addObject(SELECT_TIPO_VALOR, mapper.writeValueAsString(tiposValor));
		mv.addObject(SELECT_ORIGENES, null);
		List<TipoValorEnum> listaTipoValores = new ArrayList<TipoValorEnum>();
		for(Integer id : listaIdTipoValor) {
			listaTipoValores.add(TipoValorEnum.getEnumByIdTipoValor(id.longValue()));
		}
		
		// SHV_PARAM_TIPO_GESTION
		ComboAcuerdoBancoCuentaGralDto paramTipoGestion = combosFacade.listarShvParamTipoGestion("TA", OrigenEnum.CLIENTE.codigo(), listaTipoValores, SiNoEnum.SI);
		// TODO METER EN EL FACADE! O en el Servico
		
		mv.addObject("esAnalista",userSesion.esAnalista());
		mv.addObject("paramTipoGestion", mapper.writeValueAsString(paramTipoGestion));
		mv.addObject(OBJECT_COMMAND, boletaSinValorDto);
		
		//TipoValor
		mv.addObject("TipoValorEnum", mapper.writeValueAsString(TipoValorEnum.convertirAMap()));
		
		mv.addObject("leyendaComboSeleccionar", Propiedades.MENSAJES_PROPIEDADES.getString("combo.seleccionar"));
		
		return mv;
	}
	
	@RequestMapping("/procesar-alta-boleta")
	@ControlProcesoTransacciones
	public  @ResponseBody ValorJsonResponse procesarAltaBoleta(HttpServletRequest request, @RequestBody final  BoletaSinValorDto boletaSinValorDto) throws Exception{					
		ValorJsonResponse response = new ValorJsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
//		if (result.hasErrors()) {
//			return boletasAlta(request, boletaSinValorDto);
//		}
		boletaSinValorDto.setOperation(Constantes.CREATE);
		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(boletaSinValorDto, "test");
		boletaSinValorValidacionWeb.validarBoletaSinValorDto(boletaSinValorDto, errors);
		
		boletaSinValorDto.setUsuarioModificacion(userSesion.getUsuario());
		Long numeroBoleta = boletaSinValorServicio.crear(boletaSinValorDto);
		BoletaSinValorDto boletaSinValor = boletaSinValorServicio.buscarPorNumeroBoleta(numeroBoleta);
		
		
		String[] listaIdBoletas = new String[] {boletaSinValor.getId().toString()};
		
		if(boletaSinValorDto.getCheckEnviarMailBoleta()){
			List<String> mensajesMostrarEnvioMail = boletaSinValorServicio.enviarMail(listaIdBoletas, userSesion);
			response.setMensajesMostrarEnvioMail(StringUtils.join(mensajesMostrarEnvioMail,'-'));
			response.setImprimible(false);
//			mv.addObject("imprimible", false);
//			mv.addObject("mensajesMostrarEnvioMail", mensajesMostrarEnvioMail);
		}else{
			if(boletaSinValorDto.getCheckImprimirBoleta()){
				ActualizacionExitosaDto actualizacionDto = boletaSinValorServicio.imprimirBoletas(listaIdBoletas, userSesion);
				List<String> numerosBoletasAlta = new ArrayList<String>();
				numerosBoletasAlta.add(String.valueOf(numeroBoleta));
				actualizacionDto.setNumerosBoletasAlta(numerosBoletasAlta );
				boolean imprimible = logicaArchivosImprimibles(request, actualizacionDto, null);
				response.setImprimible(imprimible);
			}
		}
		
		
		response.setMensajeAlta(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("boleta.alta.mensaje.crear.ok"), String.valueOf(numeroBoleta)));
		response.setSuccess(true);
		response.setUrl("boletas-alta");
		response.setAction(BOLETA_ACTUALIZACION_OK_AC);
		return response;
	}
	@RequestMapping(value="/boleta-actualizacion-ac", method=RequestMethod.POST)
	public ModelAndView valoresActualizacionAc(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView(BOLETA_AUTORIZACION_OK_VIEW);

		String mensajeAlta = request.getParameter("mensajeAlta");
		String mensajesMostrarEnvioMail = request.getParameter("mensajesMostrarEnvioMail");
		String imprimible = request.getParameter("imprimible");

		if (!Validaciones.isNullEmptyOrDash(mensajesMostrarEnvioMail)) {
			mv.addObject("mensajesMostrarEnvioMail", mensajesMostrarEnvioMail.split("-"));
		}
		if (!Validaciones.isNullEmptyOrDash(mensajeAlta)) {
			mv.addObject("mensajeAlta", mensajeAlta.split("-"));
		}
		if (!Validaciones.isNullEmptyOrDash(imprimible)) {
			mv.addObject("imprimible", Boolean.parseBoolean(imprimible));
		}
		mv.addObject("url", request.getParameter("url"));

		return mv;
	}
	/***
	 * Método para cargar la página de Busqueda de boletas
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/boletas-busqueda")
	public ModelAndView boletasBusqueda(HttpServletRequest request, BoletaSinValorFiltro boletaSinValorFiltro, BindingResult result) throws Exception{
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		boletaSinValorFiltro.setUsuarioLogeado(userSesion);
		
		ModelAndView mv=new ModelAndView(BUSQUEDA_VIEW);
		boletaSinValorServicio.cargarCombosParaBusquedaBoletaSinValor(mv,userSesion,boletaSinValorFiltro);
//		cargarCombosParaBusqueda(mv,userSesion,boletaSinValorFiltro);
		
		if (userSesion.getVolviendoABusqueda()) {
			//Recupero el filtro
			mv.addObject(LISTA_BOLETAS_DTO, boletaSinValorServicio.listar(userSesion.getBoletaSinValorFiltro()));
			mv.addObject(BOLETA_FILTRO,userSesion.getBoletaSinValorFiltro());
			userSesion.setVolviendoABusqueda(false);
		} else {
			if(!Validaciones.isNullOrEmpty(boletaSinValorFiltro.getSegmento())
					&& Validaciones.isNullOrEmpty(boletaSinValorFiltro.getImporteDesde())
					&& Validaciones.isNullOrEmpty(boletaSinValorFiltro.getImporteHasta())
					&& Validaciones.isNullOrEmpty(boletaSinValorFiltro.getFechaDesde())
					&& Validaciones.isNullOrEmpty(boletaSinValorFiltro.getFechaHasta())){
				
				
				mv.addObject(LISTA_BOLETAS_DTO, boletaSinValorServicio.listar(boletaSinValorFiltro));
			}
			//Rango de fechas por defecto
			if (Validaciones.isNullOrEmpty(boletaSinValorFiltro.getFechaDesde()) && Validaciones.isNullOrEmpty(boletaSinValorFiltro.getFechaHasta())){
				boletaSinValorFiltro.crearRangoFechas(Constantes.$2_SEMANAS_MS);
			}
			mv.addObject(BOLETA_FILTRO, boletaSinValorFiltro);
			userSesion.setBoletaSinValorFiltro(boletaSinValorFiltro);
		}
		return mv;
	}
	
	/**
	 * Método para buscar Boletas
	 * @param request
	 * @param boletaSinValorFiltro
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/buscar-boletas")
	@ControlProcesoTransacciones
	public ModelAndView buscarBoletas(HttpServletRequest request, 
			@ModelAttribute("boletaSinValorFiltro") @Valid BoletaSinValorFiltro boletaSinValorFiltro, BindingResult result) throws Exception{	
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		if(result.hasErrors()){
			return boletasBusqueda(request,boletaSinValorFiltro,result);
		}

		ModelAndView mv=new ModelAndView(BUSQUEDA_VIEW);
		boletaSinValorFiltro.setUsuarioLogeado(userSesion);
		mv.addObject(LISTA_BOLETAS_DTO,boletaSinValorServicio.listarBoletasSinValor(boletaSinValorFiltro, userSesion));
		//cargarCombosParaBusqueda(mv,userSesion,boletaSinValorFiltro);
		boletaSinValorServicio.cargarCombosParaBusquedaBoletaSinValor(mv,userSesion,boletaSinValorFiltro);
		
		mv.addObject(BOLETA_FILTRO, boletaSinValorFiltro);
		userSesion.setBoletaSinValorFiltro(boletaSinValorFiltro);
		
		return mv;
	}
	
	/***
	 * Modificacion
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/boletas-modificacion")
	public ModelAndView boletasModificacion(HttpServletRequest request,@ModelAttribute("boletaSinValorFiltro") BoletaSinValorFiltro boletaSinValorFiltro, 
			BoletaSinValorDto boletaSinValorDto, BindingResult result) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		String idBoleta=boletaSinValorFiltro.getIdBoleta();
		
		BoletaSinValorFiltro filtro = new BoletaSinValorFiltro(idBoleta);
		if(!result.hasErrors()){
			Collection<DTO> listaBoletaSinValor = boletaSinValorServicio.listar(filtro);
			boletaSinValorDto = (BoletaSinValorDto) listaBoletaSinValor.iterator().next(); 
			boletaSinValorDto.setOperation(Constantes.UPDATE);
			//Para la concurrencia
			boletaSinValorDto.setTimeStamp(boletaSinValorDto.getTimeStampDTO());
		}
		
		//HDY: Hago llamada a Siebel para los datos del holding
		//SalidaSiebelConsultarClienteWS datos = clienteSiebelServicio.consultarClienteSiebel(boletaSinValorDto.getCodCliente());
		ClienteDto clienteDto = clienteFacade.consultarCliente(boletaSinValorDto.getCodCliente());
	
		if (!Validaciones.isObjectNull(clienteDto)) {
			if(!Validaciones.isNullOrEmpty(clienteDto.getCodigoHolding()) && 
			   !Validaciones.isNullOrEmpty(clienteDto.getDescripcionHolding())) {
				boletaSinValorDto.setNumeroHolding(clienteDto.getCodigoHolding());
				boletaSinValorDto.setNombreHolding(clienteDto.getDescripcionHolding());
			}
		}
		
		ModelAndView mv=new ModelAndView(MODIFICACION_VIEW);
		mv.addObject(OBJECT_COMMAND,boletaSinValorDto);

		Collection <String> usuariosExcluidos = new ArrayList<String>();
		usuariosExcluidos.add(boletaSinValorDto.getIdAnalista());
		
		mv.addObject(SELECT_COPROPIETARIOS,combosServicio.listarCopropietarioPorEmpresaYSegmento(
				boletaSinValorDto.getIdEmpresa(),boletaSinValorDto.getIdSegmento(), usuariosExcluidos));

		mv.addObject(SELECT_MOTIVOS, listarMotivosSinChequesRechazados());
		userSesion.setVolviendoABusqueda(true);
		return mv;
	}
	
	/**
	 * Procesa las modificaciones
	 * @param request
	 * @param boletaSinValorDto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/procesar-modificacion-boleta")
	@ControlProcesoTransacciones
	public ModelAndView procesarModificacionBoleta(HttpServletRequest request, 
			@ModelAttribute("boletaSinValorDto") @Valid BoletaSinValorDto boletaSinValorDto, 
			BindingResult result) throws Exception {					
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
 		if(result.hasErrors()){
			return boletasModificacion(request, new BoletaSinValorFiltro(boletaSinValorDto.getId().toString()), boletaSinValorDto, result);
		}
 		
 		boletaSinValorDto.setUsuarioModificacion(userSesion.getUsuario());
 		boletaSinValorDto.setErrorNingunaModificacion(false);
 		
		try {
			
			boletaSinValorServicio.modificar(boletaSinValorDto);
			userSesion.setVolviendoABusqueda(true);
			
		} catch (ConcurrenciaExcepcion e) {
			
			userSesion.setVolviendoABusqueda(true);
			ModelAndView mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
			mv.addObject("url","boletas-busqueda");
			return mv;
		} 
		
		ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		mv.addObject("url","boletas-busqueda");
		return mv;
	}
	
	/**
	 * Método que se llama cuando se hace click en el boton "Ver/Impr. Boleta Deposito"
	 * en la pantalla de boletas-busqueda.
	 * @param request
	 * @param boletaSinValorFiltro
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/imprimir-boleta")
	public ModelAndView procesarModificacionBoletaImpresa(HttpServletRequest request, 
			@ModelAttribute("boletaSinValorFiltro") BoletaSinValorFiltro boletaSinValorFiltro) throws Exception  {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		String boletasAImprimirSelected = boletaSinValorFiltro.getBoletasAImprimirSelected();
		String[] listaBoletas = boletasAImprimirSelected.split(",");
		
		if (!Validaciones.isNullOrEmpty(boletasAImprimirSelected)) {
			
			ModelAndView mv=new ModelAndView(BOLETA_AUTORIZACION_OK_VIEW);
			try { 
				ActualizacionExitosaDto actualizacionDto = boletaSinValorServicio.imprimirBoletas(listaBoletas, userSesion);
				
				logicaArchivosImprimibles(request, actualizacionDto, mv);
				userSesion.setVolviendoABusqueda(true);
				
			} catch (ConcurrenciaExcepcion e) {
				userSesion.setVolviendoABusqueda(true);
				ModelAndView mvError = new ModelAndView(ERROR_CONCURRENCIA_VIEW);
				mvError.addObject("lista", true);
				mvError.addObject("mensaje", 
					Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.concurrencia.lista"), 
							", para las boletas ["+e.getListaIdsInconcurrentes()+"]"));
				
				mvError.addObject("url","boletas-busqueda");
				return mvError;
			}
			
			
			mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
			mv.addObject("url","boletas-busqueda");
		
			return mv;
		}
		
		return boletasBusqueda(request, boletaSinValorFiltro, null);
	}
	
	/**
	 * metodo que se llama cuando se hace click en el boton "Enviar Mail Boleta Deposito"
	 * en la pantalla de boletas-busqueda.
	 * @param request
	 * @param boletaSinValorFiltro
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/enviar-mail-boleta")
	public ModelAndView procesarModificacionBoletaMail(HttpServletRequest request,
			@ModelAttribute("boletaSinValorFiltro") BoletaSinValorFiltro boletaSinValorFiltro) throws Exception  {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		List<String> mensajesMostrarEnvioMail = new ArrayList<String>();
		
		String boletasAEnviarMailSelected = boletaSinValorFiltro.getBoletasAEnviarMailSelected();
		String[] listaIdBoletas = boletasAEnviarMailSelected.split(",");
		
		
		if (!Validaciones.isNullOrEmpty(boletasAEnviarMailSelected)) {
			
			try {
				mensajesMostrarEnvioMail = boletaSinValorServicio.enviarMail(listaIdBoletas, userSesion);
				userSesion.setVolviendoABusqueda(true);
			
			} catch (ConcurrenciaExcepcion e) {
				userSesion.setVolviendoABusqueda(true);
				ModelAndView mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
				mv.addObject("lista", true);
				mv.addObject("mensaje", 
					Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.concurrencia.lista"), 
							", para las boletas ["+e.getListaIdsInconcurrentes()+"]"));
				
				mv.addObject("url","boletas-busqueda");
				return mv;
			} 
			ModelAndView mv=new ModelAndView(BOLETA_AUTORIZACION_OK_VIEW);
			mv.addObject("mensajesMostrarEnvioMail", mensajesMostrarEnvioMail);
			mv.addObject("url","boletas-busqueda");
			
			return mv;
		}
		
		return boletasBusqueda(request, boletaSinValorFiltro, null);
	}

	/**
	 * Método para anular Boletas
	 * @param request
	 * @param boletaSinValorFiltro
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/boletas-anular")
	public ModelAndView anularBoletas(HttpServletRequest request,
			@ModelAttribute("boletaSinValorFiltro") BoletaSinValorFiltro boletaSinValorFiltro) throws Exception{	
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String boletasAAnularSelected = boletaSinValorFiltro.getBoletasAAnularSelected();
		
		
		if (!Validaciones.isNullOrEmpty(boletasAAnularSelected)) {
			
			try {
				
				boletaSinValorServicio.anularBoletas(boletasAAnularSelected, userSesion);
				userSesion.setVolviendoABusqueda(true);
				
			} catch (ConcurrenciaExcepcion e) {
				userSesion.setVolviendoABusqueda(true);
				ModelAndView mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
				mv.addObject("lista", true);
				mv.addObject("mensaje", 
					Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.concurrencia.lista"), 
							", para las boletas ["+e.getListaIdsInconcurrentes()+"]"));
				
				mv.addObject("url","boletas-busqueda");
				return mv;
			} 
			
			ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
			mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
			mv.addObject("url","boletas-busqueda");
			return mv;
		} 
		
		return boletasBusqueda(request, boletaSinValorFiltro, null);
	}
	
	
	/************************************************************************************/
	/** Metodos - AJAX **/
	/************************************************************************************/
	@RequestMapping("/consultarClienteSiebel")
	public @ResponseBody String consultarClienteSiebel(HttpServletRequest request) {
		String str="";
		try {
			String codCliente=request.getParameter("codCliente");
			
			if (!Validaciones.isNumeric(codCliente)) {
				return Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico");
			}

			str = this.clienteFacade.consultarClienteString(codCliente);
		} catch (Throwable ex) {
			Traza.error(getClass(), ex.getMessage(), ex);
			if (ex.getCause()!=null && ex.getCause() instanceof RemoteAccessException) {
				return Propiedades.MENSAJES_PROPIEDADES.getString("boleta.alta.mensaje.siebel.ws.caida");
			} 
			return Propiedades.MENSAJES_PROPIEDADES.getString("boleta.alta.mensaje.siebel.ws.error");
		}
		
		return str;
	}
	
	/************************************************************************************/
	/** Metodos para los combos **/
	/************************************************************************************/
	@RequestMapping("/seleccionoEmpresa")
	public ModelAndView seleccionoEmpresa(HttpServletRequest request,  @ModelAttribute("boletaSinValorDto") BoletaSinValorDto boletaSinValorDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		boletaSinValorDto.setIdSegmento("");
		boletaSinValorDto.setIdAcuerdo("");
		boletaSinValorDto.setIdBancoDeposito("");
		boletaSinValorDto.setIdNroCuenta("");
		boletaSinValorDto.setIdOrigen("");
		
		ModelAndView mv = new ModelAndView(ALTA_VIEW);
		enviarListasCombosAlCargar(mv, userSesion, boletaSinValorDto);
		
		if (!"".equalsIgnoreCase(boletaSinValorDto.getIdEmpresa())) {
			
			List<ShvParamSegmento> listaSegmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(boletaSinValorDto.getIdEmpresa(), userSesion);
					
			mv.addObject(SELECT_SEGMENTOS, listaSegmentos);
			if (listaSegmentos.size() == 1) {
				boletaSinValorDto.setIdSegmento(listaSegmentos.get(0).getIdSegmento());
				List<ShvParamOrigen> listaOrigenes = combosServicio.listarOrigenPorEmpresaSegmentoValor(boletaSinValorDto.getIdEmpresa(), listaSegmentos.get(0).getIdSegmento()
						,String.valueOf(TipoValorEnum.BOLETA_SIN_VALOR.getIdTipoValor()));
				mv.addObject(SELECT_ORIGENES,listaOrigenes);
				if (listaOrigenes.size() == 1) {
					boletaSinValorDto.setIdOrigen(String.valueOf(listaOrigenes.get(0).getIdOrigen()));
					cargarAcuerdo(mv,boletaSinValorDto);
					cargarBanco(mv,boletaSinValorDto);
					cargarCuenta(mv,boletaSinValorDto);
					boletaSinValorDto.setComboOrigen(false);
				} else {
					boletaSinValorDto.setComboOrigen(true);
				}
				boletaSinValorDto.setComboSegmento(false);
			} else {
				boletaSinValorDto.setComboSegmento(true);
			}
		}
		cargarCopropietario(mv, boletaSinValorDto);
		cargarCodigoLegadoSiebel(mv,boletaSinValorServicio.listar10CodigosClienteLegado(userSesion.getIdUsuario()));
		mv.addObject(OBJECT_COMMAND, boletaSinValorDto);
		return mv;
	}
	
	@RequestMapping("/seleccionoSegmento")
	public ModelAndView seleccionoSegmento(HttpServletRequest request,  @ModelAttribute("boletaSinValorDto") BoletaSinValorDto boletaSinValorDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		boletaSinValorDto.setIdAcuerdo("");
		boletaSinValorDto.setIdBancoDeposito("");
		boletaSinValorDto.setIdNroCuenta("");
		boletaSinValorDto.setIdOrigen("");
		
		ModelAndView mv = new ModelAndView(ALTA_VIEW);
		enviarListasCombosAlCargar(mv, userSesion, boletaSinValorDto);
		if (!"".equalsIgnoreCase(boletaSinValorDto.getIdSegmento())) {
			List<ShvParamOrigen> listaOrigenes = combosServicio.listarOrigenPorEmpresaSegmentoValor(boletaSinValorDto.getIdEmpresa(), boletaSinValorDto.getIdSegmento()
					,String.valueOf(TipoValorEnum.BOLETA_SIN_VALOR.getIdTipoValor()));
			mv.addObject(SELECT_ORIGENES,listaOrigenes);
			if(listaOrigenes.size()==1){
				boletaSinValorDto.setIdOrigen(String.valueOf(listaOrigenes.get(0).getIdOrigen()));
				cargarAcuerdo(mv,boletaSinValorDto);
				cargarBanco(mv,boletaSinValorDto);
				cargarCuenta(mv,boletaSinValorDto);
				boletaSinValorDto.setComboOrigen(false);
			}else{
				boletaSinValorDto.setComboOrigen(true);
			}
		}
		cargarCopropietario(mv, boletaSinValorDto);
		cargarCodigoLegadoSiebel(mv,boletaSinValorServicio.listar10CodigosClienteLegado(userSesion.getIdUsuario()));
		mv.addObject(OBJECT_COMMAND, boletaSinValorDto);
		return mv;
	}
	
	@RequestMapping("/seleccionoOrigen")
	public ModelAndView seleccionoOrigen(HttpServletRequest request,  @ModelAttribute("boletaSinValorDto") BoletaSinValorDto boletaSinValorDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		boletaSinValorDto.setIdAcuerdo("");
		boletaSinValorDto.setIdBancoDeposito("");
		boletaSinValorDto.setIdNroCuenta("");
		
		ModelAndView mv = new ModelAndView(ALTA_VIEW);
		if("".equalsIgnoreCase(boletaSinValorDto.getIdOrigen())){
			List<ShvParamOrigen> listaOrigenes = combosServicio.listarOrigenPorEmpresaSegmentoValor(boletaSinValorDto.getIdEmpresa(), boletaSinValorDto.getIdSegmento()
					,String.valueOf(TipoValorEnum.BOLETA_SIN_VALOR.getIdTipoValor()));
			mv.addObject(SELECT_ORIGENES,listaOrigenes);
			if(listaOrigenes.size()>1){
				boletaSinValorDto.setComboOrigen(true);
			}else{
				boletaSinValorDto.setComboOrigen(false);
			}
		}else{
			cargarAcuerdo(mv,boletaSinValorDto);
			cargarBanco(mv,boletaSinValorDto);
			cargarCuenta(mv,boletaSinValorDto);
			cargarOrigen(mv,boletaSinValorDto);
		}
		cargarCopropietario(mv, boletaSinValorDto);
		enviarListasCombosAlCargar(mv,userSesion,boletaSinValorDto);
		cargarCodigoLegadoSiebel(mv,boletaSinValorServicio.listar10CodigosClienteLegado(userSesion.getIdUsuario()));
		mv.addObject(OBJECT_COMMAND, boletaSinValorDto);
		return mv;
	}
	
	@RequestMapping("/seleccionoAcuerdo")
	public ModelAndView seleccionoAcuerdo(HttpServletRequest request,  @ModelAttribute("boletaSinValorDto") BoletaSinValorDto boletaSinValorDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		if("".equalsIgnoreCase(boletaSinValorDto.getIdAcuerdo())){
			//selecciono "Seleccione un item.."
			boletaSinValorDto.setIdBancoDeposito("");
			boletaSinValorDto.setIdNroCuenta("");
		}else{
			//Traigo la cuenta que coincide con el acuerdo sellecionado
			ShvParamBancoCuenta bancoCuenta = combosServicio.buscarBancoCuentaPorAcuerdo(boletaSinValorDto.getIdAcuerdo());
			boletaSinValorDto.setIdBancoDeposito(bancoCuenta.getBanco().getIdBanco());
			boletaSinValorDto.setIdNroCuenta(String.valueOf(bancoCuenta.getIdBancoCuenta()));
		}
		ModelAndView mv = new ModelAndView(ALTA_VIEW);
		cargarAcuerdo(mv,boletaSinValorDto);
		cargarBanco(mv,boletaSinValorDto);
		cargarCuenta(mv,boletaSinValorDto);
		
		cargarOrigen(mv,boletaSinValorDto);
		cargarCopropietario(mv, boletaSinValorDto);
		enviarListasCombosAlCargar(mv,userSesion,boletaSinValorDto);
		cargarCodigoLegadoSiebel(mv,boletaSinValorServicio.listar10CodigosClienteLegado(userSesion.getIdUsuario()));
		mv.addObject(OBJECT_COMMAND, boletaSinValorDto);
		return mv;
	}
	
	@RequestMapping("/seleccionoCuenta")
	public ModelAndView seleccionoCuenta(HttpServletRequest request,  @ModelAttribute("boletaSinValorDto") BoletaSinValorDto boletaSinValorDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		//Traigo la cuenta que coincide con el acuerdo sellecionado
		ShvParamAcuerdo acuerdoAux = combosServicio.buscarAcuerdoConciliablePorIdCuenta(boletaSinValorDto.getIdNroCuenta());
		ShvParamBancoCuenta  bancoCuentaAux = combosServicio.buscarBancoCuentaPorCuenta(boletaSinValorDto.getIdNroCuenta());
		
		boletaSinValorDto.setIdBancoDeposito(bancoCuentaAux.getBanco().getIdBanco());
		boletaSinValorDto.setIdAcuerdo(String.valueOf(acuerdoAux.getIdAcuerdo()));

		ModelAndView mv = new ModelAndView(ALTA_VIEW);
		cargarAcuerdo(mv,boletaSinValorDto);
		cargarBanco(mv,boletaSinValorDto);
		cargarCuenta(mv,boletaSinValorDto);
		cargarOrigen(mv,boletaSinValorDto);
		cargarCopropietario(mv, boletaSinValorDto);
		enviarListasCombosAlCargar(mv,userSesion,boletaSinValorDto);
		cargarCodigoLegadoSiebel(mv,boletaSinValorServicio.listar10CodigosClienteLegado(userSesion.getIdUsuario()));
		mv.addObject(OBJECT_COMMAND, boletaSinValorDto);
		return mv;
	}
	
	@RequestMapping("/seleccionoBanco")
	public ModelAndView seleccionoBanco(HttpServletRequest request,  @ModelAttribute("boletaSinValorDto") BoletaSinValorDto boletaSinValorDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		List<ShvParamAcuerdo> listaAcuerdo = (List<ShvParamAcuerdo>) combosServicio.listarAcuerdoPorBanco(boletaSinValorDto.getIdBancoDeposito());
		List<ShvParamBancoCuenta> listaCuenta = (List<ShvParamBancoCuenta>) combosServicio.listarCuentaPorIdBanco(boletaSinValorDto.getIdBancoDeposito(),Constantes.ACUERDO_CONCILIABLE);
		
		ModelAndView mv = new ModelAndView(ALTA_VIEW);
		cargarAcuerdo(mv,boletaSinValorDto);
		cargarBanco(mv,boletaSinValorDto);
		cargarCuenta(mv,boletaSinValorDto);
		cargarOrigen(mv,boletaSinValorDto);
		enviarListasCombosAlCargar(mv, userSesion, boletaSinValorDto);
		
		if(listaAcuerdo.size()>1){
			boletaSinValorDto.setIdAcuerdo("");
			boletaSinValorDto.setComboAcuerdo(true);
		}
		if(listaCuenta.size()>1){
			boletaSinValorDto.setIdNroCuenta("");
			boletaSinValorDto.setComboCuenta(true);
		}
		cargarCopropietario(mv, boletaSinValorDto);
		cargarCodigoLegadoSiebel(mv,boletaSinValorServicio.listar10CodigosClienteLegado(userSesion.getIdUsuario()));
		mv.addObject(OBJECT_COMMAND, boletaSinValorDto);
		return mv;
	}
	
	@RequestMapping("/seleccionoEmpresaEnBusqueda")
	public ModelAndView seleccionoEmpresaEnBusqueda(HttpServletRequest request,  @ModelAttribute("boletaSinValorFiltro") BoletaSinValorFiltro boletaSinValorFiltro) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		boletaSinValorFiltro.setSegmento("");
		
		ModelAndView mv = new ModelAndView(BUSQUEDA_VIEW);
		cargarCombosParaBusqueda(mv,userSesion,boletaSinValorFiltro);
		cargarCodigoLegadoSiebel(mv,boletaSinValorServicio.listar10CodigosClienteLegado(userSesion.getIdUsuario()));
		mv.addObject(OBJECT_COMMAND, boletaSinValorFiltro);
		
		return mv;
	}
	
	@RequestMapping(value = "boleta/buscarCopropietarios", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SelectOptionJsonResponse> buscarCopropietarios(HttpServletRequest request) throws Exception {
		String idEmpresa = request.getParameter("idEmpresa");
		String idSegmento = request.getParameter("idSegmento");
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		Collection<String> usuariosExcluidos = new ArrayList<String>();
		usuariosExcluidos.add(userSesion.getIdUsuario());
		
		return combosFacade.listarCopropietarioPorEmpresaYSegmento(idEmpresa, idSegmento, usuariosExcluidos);
	}
	/**
	 * Segun si hay boletas o no, decide si setear la variable imprimible en true o false.
	 * Luego los introduce al request para que los obtenga 
	 * el metodo AbrirPdf o AbrirDocumento
	 * @param request
	 * @param actualizacionDto
	 * @param mv
	 * @throws Exception
	 */
	public boolean logicaArchivosImprimibles(HttpServletRequest request, ActualizacionExitosaDto actualizacionDto, ModelAndView mv) throws Exception {
		if(actualizacionDto.getArchivosImprimirBoleta().size() == 1){
			/*Solo un pdf con una o varias boletas sin valor*/
			request.getSession().setAttribute( "archivoImpresionFinal" , actualizacionDto.getArchivosImprimirBoleta().get(0));
			
			if(Validaciones.isCollectionNotEmpty(actualizacionDto.getNumerosBoletasAlta())){
				String boletas = "";
				for(int i=0;i<actualizacionDto.getNumerosBoletasAlta().size()-1;i++) {
					if(boletas.equalsIgnoreCase("")){
						boletas = actualizacionDto.getNumerosBoletasAlta().get(i);
					}else{
						boletas += ","+actualizacionDto.getNumerosBoletasAlta().get(i);
					}
				}
				if(Validaciones.isNullOrEmpty(boletas)){
					boletas = actualizacionDto.getNumerosBoletasAlta().get(0);
				}else{
					boletas += " y " + actualizacionDto.getNumerosBoletasAlta().get(actualizacionDto.getNumerosBoletasAlta().size()-1);
				}
				request.getSession().setAttribute( "idBoleta", boletas);
			}
			if(!Validaciones.isObjectNull(mv)){
				mv.addObject("imprimible", true);
			}
			return true;
		} else {
			/*No Archivos*/
			if(!Validaciones.isObjectNull(mv)){
				mv.addObject("imprimible", false);
			}
			return false;
		}
	}
	
	
	/**
	 * Muestra el popup para exportar el pdf. Setea los Numeros de boletas
	 * en el nombre del archivo.
	 * @param fileId
	 * @param res
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/abrir-pdf-boleta")
	public void abrirPdf(HttpServletResponse res,
			HttpServletRequest request) throws Exception {
		
		byte[] archivo = (byte[]) request.getSession().getAttribute("archivoImpresionFinal");
		String idBoletas =  (String) request.getSession().getAttribute("idBoleta");
		String idBoletas2 =  (String) request.getSession().getAttribute("idBoleta");
		ServletOutputStream sos = null;
		sos = res.getOutputStream();
		byte[] b = archivo;
		res.setContentType("application/pdf");
		
		// Para solventar bug IE8 sobre puerto seguro no abre PDF
		res.setHeader("Cache-Control", "private");
		res.setHeader("Pragma", "private");
		
		if(!Validaciones.isNullOrEmpty(idBoletas)){
			if((idBoletas.split(",")).length == 1){
				res.addHeader("Content-Disposition", "attachment; filename=Boleta Nro. "+idBoletas+".pdf");
			}else{
				// Reemplazo en el nombre las comas (",") por que Google Chorme. toma la coma como separaro de valores.
				// O sea un  Content-Disposition = attachment; filename=Boleta_Nro._886,_887.pdf
				// Lo interpreta como Content-Disposition = attachment; filename=Boleta_Nro._886 u Content-Disposition = _887.pdf
				res.addHeader("Content-Disposition", "attachment; filename=Boletas Nro. "+idBoletas.replace(",", "_")+".pdf");
			}
		}
		
		int length = (int) b.length;
		res.setContentLength(length);
		
		res.setStatus(HttpServletResponse.SC_OK);
		sos.write(b);
		sos.flush();
		sos.close();
		
	}
}
