package ar.com.telecom.shiva.presentacion.controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import ar.com.telecom.shiva.base.comparador.UsuarioLdapDtoComparator;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.AcuseReciboEnum;
import ar.com.telecom.shiva.base.enumeradores.CriterioBusquedaClienteEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoChequeEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoContactoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoNotificacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.UbicacionChequeEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionChequeAplicadoEnOperacionExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.bean.ArchivoByteArray;
import ar.com.telecom.shiva.negocio.bean.ValidacionesAuxiliar;
import ar.com.telecom.shiva.negocio.dto.BancosParametroDto;
import ar.com.telecom.shiva.negocio.dto.ComboAcuerdoBancoCuentaDto;
import ar.com.telecom.shiva.negocio.dto.ProvinciaParametroDto;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.IClienteValidacionServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.ILegajoChequeRechazadoValidacionServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.EdicionTipoEnum;
import ar.com.telecom.shiva.negocio.workflow.servicios.util.IObservacionesWorkflowServicio;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoLegajo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.EstadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoNotificacionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ChequeRechazadoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ClienteJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ComprobanteJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ErrorJson;
import ar.com.telecom.shiva.presentacion.bean.dto.json.JsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.LegajoChequeRechazadoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.SelectOptionJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConsultaSoporteBusquedaChequeRechazadoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoFiltro;
import ar.com.telecom.shiva.presentacion.bean.paginacion.ChequeRechazadoControlPaginacion;
import ar.com.telecom.shiva.presentacion.bean.paginacion.PaginadorAccion;
import ar.com.telecom.shiva.presentacion.facade.IClienteFacade;
import ar.com.telecom.shiva.presentacion.facade.ICombosFacade;
import ar.com.telecom.shiva.presentacion.facade.ILegajoChequeRechazadoFacade;

@Controller
public class LegajoChequeRechazadoController extends Controlador {

	private static final String CONF_CHEQUES 	= "legajo-cheque-rechazado/legajo-cheque-rechazado";
	private static final String BUSQUEDA_VIEW 	= "legajo-cheque-rechazado/legajo-cheque-rechazado-busqueda";
	private static final String ANULACION_EXITOSA_VIEW = "legajo-cheque-rechazado/legajo-cheque-rechazado-anulacion-ok";
	private static final String RESUMEN_VIEW = "legajo-cheque-rechazado/legajo-cheque-rechazado-detalle";
	
	@Autowired
	private ICombosFacade combosFacade;
	
	@Autowired
	private ILegajoChequeRechazadoFacade legajoChequeRechazadoFacade;

	@Autowired
	private IClienteFacade clienteFacade;
	
	@Autowired
	private ILegajoChequeRechazadoValidacionServicio legajoChequeRechazadoValidacionServicio;
	@Autowired
	private IObservacionesWorkflowServicio observacionesWorkflowServicio;
	@Autowired
	private ILegajoChequeRechazadoServicio legajoChequeRechazadoServicio;
	@Autowired
	private IClienteValidacionServicio clienteValidacionServicio;
	
	@RequestMapping("/cheques-rechazados-alta")
	public ModelAndView altaChequesRechazados(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(CONF_CHEQUES);
		
		
		List<ShvParamEmpresa> empresas = (List<ShvParamEmpresa>) combosServicio.listarEmpresasPorUsuario(userSesion);
		List<ShvParamSegmento> segmentos = new ArrayList<ShvParamSegmento>();
		List<UsuarioLdapDto> copropietarios = new ArrayList<UsuarioLdapDto>();
		List<SistemaEnum> sistemaGestor = new ArrayList<SistemaEnum>();
		
		List<TipoChequeEnum> tipoCheque = new ArrayList<TipoChequeEnum>();
		tipoCheque.add(TipoChequeEnum.CHEQUE);
		tipoCheque.add(TipoChequeEnum.CHEQUE_DIFERIDO);
		List<MonedaEnum> monedasSoloPesos = new ArrayList<MonedaEnum>();
		monedasSoloPesos.add(MonedaEnum.PES);
		
		sistemaGestor.add(SistemaEnum.SHIVA);
		sistemaGestor.add(SistemaEnum.ICE);
		List<ShvParamMotivoLegajo> motivosRechazo = new ArrayList<ShvParamMotivoLegajo>();
		motivosRechazo = combosServicio.listarMotivosConfiguracionLegajo();
		
		if (empresas.size() == 1) {
			ShvParamEmpresa empresa = empresas.get(0);
			segmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(empresa.getIdEmpresa(),userSesion);
			if (segmentos.size() == 1) {
				ShvParamSegmento segmento = segmentos.get(0);
				Collection<String> usuariosExcluidos = new ArrayList<String>();
				usuariosExcluidos.add(userSesion.getIdUsuario());
				copropietarios = (List<UsuarioLdapDto>) combosServicio.listarCopropietarioLegajoChequeRechazadoPorEmpresaYSegmento(empresa.getIdEmpresa(), segmento.getIdSegmento(), usuariosExcluidos);
				Collections.sort(
					copropietarios,
					new UsuarioLdapDtoComparator()
				);
			} 
		} 
		BancosParametroDto bancosParametroDto = combosFacade.listaBancosAgrupadosPorDescipcion(empresas.get(0).getIdEmpresa());
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		
		// TAB CHEQUES
		mv.addObject("empresas", empresas);
		mv.addObject("comboEmpresa", (empresas.size() == 0 || empresas.size() > 1));
		mv.addObject("segmentos", segmentos);
		mv.addObject("comboSegmento", (segmentos.size() == 0 || segmentos.size() > 1));
		mv.addObject("copropietarios", copropietarios);
		mv.addObject("comboCopropietarios", (copropietarios.size() == 0 || copropietarios.size() > 1));
		mv.addObject("idUsuario", userSesion.getIdUsuario());
		mv.addObject("nombreCompletoUsuario", userSesion.getNombreCompleto());
		mv.addObject("monedas", monedasSoloPesos);
		mv.addObject("motivoRechazo", motivosRechazo);
		mv.addObject("comboBancoOrigen", (bancosParametroDto.getListaBancos().size() >= 0));
		mv.addObject("bancoOrigenes", bancosParametroDto.getListaBancos());
		mv.addObject("bancoOrigenesDescripcion", bancosParametroDto.getAgrupadores());
		
		ComboAcuerdoBancoCuentaDto combo = combosFacade.listarAcuerdo("TA");
		mv.addObject("comboAcuerdoDeposito", (combo.getListaAcuerdos().size() >= 0));
		mv.addObject("comboBancoDeposito", bancosParametroDto.getListaBancos().size()>= 0);
		mv.addObject("combo", combo);
		mv.addObject("bancoOrigenes1", combo.getListaBancos());
		mv.addObject("acuerdoBancoCuenta", mapper.writeValueAsString(combo));
		mv.addObject("ubicacionCheque", UbicacionChequeEnum.listaUbicaciones());
		
		mv.addObject("criterioBusquedaCliente", Arrays.asList(CriterioBusquedaClienteEnum.values()));
		mv.addObject("sistemaGestor", sistemaGestor);
		mv.addObject("sistemaOrigenDefecto", SistemaEnum.USUARIO);
		mv.addObject("edTipoCheque", tipoCheque);
		// Seteo en null la lista de bancos porque no la voy nesesitar en el frontEnd
		bancosParametroDto.setListaBancos(null);
		mv.addObject("bancoDescripcion", mapper.writeValueAsString(bancosParametroDto));
		mv.addObject("sistemMap", mapper.writeValueAsString(SistemaEnum.convertirAMap()));
		mv.addObject("EdicionTipoMap", mapper.writeValueAsString(EdicionTipoEnum.convertirAMap()));
		
		//Tab notificaciones
		mv.addObject("tipoNotificacion",TipoNotificacionEnum.getEnums());
		mv.addObject("tipoContacto",TipoContactoEnum.getEnums());
		mv.addObject("acuseRecibo",AcuseReciboEnum.getEnums());
		mv.addObject("comboTipoNotificacion",TipoNotificacionEnum.getEnums().size() == 0 || TipoNotificacionEnum.getEnums().size() > 1);
		mv.addObject("comboTipoContacto",TipoContactoEnum.getEnums().size() == 0 || TipoContactoEnum.getEnums().size() > 1);
		mv.addObject("comboAcuseRecibo", AcuseReciboEnum.getEnums().size() == 0 || AcuseReciboEnum.getEnums().size() > 1);
		List<ProvinciaParametroDto> provincias = this.combosFacade.listarProvincias();
		mv.addObject("comboProvincias", provincias.size() == 0 || provincias.size() > 1);
		mv.addObject("provincias", provincias);
		
		return mv;
	}
	/***
	 * SEGMENTO
	 */
	@RequestMapping(value = "legajo-cheque-rechazado/buscarSegmentos", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SelectOptionJsonResponse> buscarSegmentos(HttpServletRequest request) throws Exception {
		return this.buscarSegmentos(request);
	}
	@RequestMapping(value = "legajo-cheque-rechazado/buscarCopropietarios", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SelectOptionJsonResponse> buscarCopropietarios(HttpServletRequest request) throws Exception {
		String idEmpresa = request.getParameter("idEmpresa");
		String idSegmento = request.getParameter("idSegmento");
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		Collection<String> usuariosExcluidos = new ArrayList<String>();
		usuariosExcluidos.add(userSesion.getIdUsuario());
		return combosFacade.listarCopropietarioLegajoChequeRechazadoPorEmpresaYSegmento(idEmpresa, idSegmento, usuariosExcluidos);
	}
	
	/***
	 * Cliente Siebel
	 */
	@RequestMapping(value = "legajo-cheque-rechazado/consultarCliente", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ClienteJsonResponse consultarCliente(@RequestBody final ClienteFiltro filtro, HttpServletRequest request) {
		ClienteJsonResponse clienteJsonResponse = new ClienteJsonResponse();

		try {
			clienteValidacionServicio.validarFiltroClientes(filtro);

			List<ClienteDto> clienteDto = clienteFacade.consultarClienteSiebel(filtro);
			if (!clienteDto.isEmpty() && Validaciones.isObjectNull(clienteDto.get(0).getIdClienteLegado())) {
				clienteJsonResponse.setSuccess(false);
				clienteJsonResponse.setCampoError("#errorEdInputCodCliente");
				clienteJsonResponse.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.vacio"));
			} else if (clienteDto.isEmpty()) {
				clienteJsonResponse.setSuccess(false);
				clienteJsonResponse.setCampoError("#errorEdInputCodCliente");
				clienteJsonResponse.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.vacio"));
			} else {
				clienteJsonResponse.getClientes().add(clienteDto.get(0));
				clienteJsonResponse.setSuccess(true);
			}
		} catch (ValidacionExcepcion ex) {
			clienteJsonResponse.setSuccess(false);
			if (!Validaciones.isNullOrEmpty(ex.getCampoError())) {
				clienteJsonResponse.setCampoError(ex.getCampoError());
			} else {
				clienteJsonResponse.setCampoError("#errorEdInputCodCliente"); 
			}
			clienteJsonResponse.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString(ex.getLeyenda()));
		} catch (NegocioExcepcion ex) {
			clienteJsonResponse.setSuccess(false);
			clienteJsonResponse.setCampoError("#errorEdInputCodCliente");
			clienteJsonResponse.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.ws.error"));
		}
		return clienteJsonResponse;
	}
	/**********
	 * Cheques
	 **********/
	@RequestMapping(value = "legajo-cheque-rechazado/buscarCheques", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ChequeRechazadoJsonResponse buscarCheques(@RequestBody final ConsultaSoporteBusquedaChequeRechazadoFiltro chequeRechazadoFiltro, HttpServletRequest request) throws Exception {
		try {
			ChequeRechazadoJsonResponse result = new ChequeRechazadoJsonResponse();
			ValidacionesAuxiliar validacionesAuxiliar = new ValidacionesAuxiliar();

			result.getResultado().addAll(legajoChequeRechazadoFacade.listarCheques(chequeRechazadoFiltro, validacionesAuxiliar));
			((UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN)).setControlPaginado(chequeRechazadoFiltro.getChequeRechazadoControlPaginacion());
			
			result.setControlPaginacion(chequeRechazadoFiltro.getChequeRechazadoControlPaginacion().clone());
			result.setSuccess(true);
			if (validacionesAuxiliar.getResultadoValidaciones().length() > 0) {
				result.setErrorMensaje(validacionesAuxiliar.getResultadoValidaciones().toString());				result.setCampoError("#errorRespuestaCheques");
				//result.setInformacionMensaje(validacionesAuxiliar.getResultadoValidaciones().toString());
			}
			return result;
//			
		} catch (ValidacionExcepcion ex) {
			ChequeRechazadoJsonResponse rta = new ChequeRechazadoJsonResponse();
//			rta.setControlPaginacion(null);
			rta.setSuccess(false);
			List<ErrorJson> erroresJson = new ArrayList<ErrorJson>();
			for (int i = 0; i < ((ValidacionExcepcion) ex).getCamposError().size(); i++) {
				ErrorJson eJson = new ErrorJson(((ValidacionExcepcion) ex).getCamposError().get(i),((ValidacionExcepcion) ex).getCodigosLeyenda().get(i));
				erroresJson.add(eJson);
			}
			rta.setErrores((ArrayList<ErrorJson>) erroresJson);
			
			return rta;
		} catch (NegocioExcepcion ex) {
			ChequeRechazadoJsonResponse rta = new ChequeRechazadoJsonResponse();
//			rta.setControlPaginacion(null);
			rta.setSuccess(false);
			rta.setCampoError("#errorRespuestaCheques");
			rta.setErrorMensaje(ex.getMessage());
			return rta;
		}
	}
	@RequestMapping(value = "/paginarCheques", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ChequeRechazadoJsonResponse paginarCheques(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ChequeRechazadoControlPaginacion chequeRechazadoControlPaginacion = (ChequeRechazadoControlPaginacion) userSesion.getControlPaginado();
		ValidacionesAuxiliar validacionesAuxiliar = new ValidacionesAuxiliar();
		
		ConsultaSoporteBusquedaChequeRechazadoFiltro consultaSoporteBusquedaChequeRechazadoFiltro = (ConsultaSoporteBusquedaChequeRechazadoFiltro) chequeRechazadoControlPaginacion.getFiltro();
		consultaSoporteBusquedaChequeRechazadoFiltro.setPaginadorAccion(PaginadorAccion.getEnumByEvento(request.getParameter("accion")));

		try {
			ChequeRechazadoJsonResponse result = new ChequeRechazadoJsonResponse();
			result.getResultado().addAll(legajoChequeRechazadoFacade.paginarCheques(
				consultaSoporteBusquedaChequeRechazadoFiltro,
				validacionesAuxiliar
			));
			((UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN)).setControlPaginado(
				consultaSoporteBusquedaChequeRechazadoFiltro.getChequeRechazadoControlPaginacion()
			);
	
			result.setControlPaginacion(consultaSoporteBusquedaChequeRechazadoFiltro.getChequeRechazadoControlPaginacion().clone());
			result.setSuccess(true);
			if (validacionesAuxiliar.getResultadoValidaciones().length() > 0) {
				result.setErrorMensaje(validacionesAuxiliar.getResultadoValidaciones().toString());
				//result.setInformacionMensaje(validacionesAuxiliar.getResultadoValidaciones().toString());
			}
			return result;
//			
		} catch (ValidacionExcepcion ex) {
			ChequeRechazadoJsonResponse rta = new ChequeRechazadoJsonResponse();
//			rta.setControlPaginacion(null);
			rta.setSuccess(false);
			List<ErrorJson> erroresJson = new ArrayList<ErrorJson>();
			for (int i = 0; i < ((ValidacionExcepcion) ex).getCamposError().size(); i++) {
				ErrorJson eJson = new ErrorJson(((ValidacionExcepcion) ex).getCamposError().get(i),((ValidacionExcepcion) ex).getCodigosLeyenda().get(i));
				erroresJson.add(eJson);
			}
			rta.setErrores((ArrayList<ErrorJson>) erroresJson);
			return rta;
		} catch (NegocioExcepcion ex) {
			ChequeRechazadoJsonResponse rta = new ChequeRechazadoJsonResponse();
//			rta.setControlPaginacion(null);
			rta.setSuccess(false);
			rta.setCampoError("#errorRespuestaCheques");
			rta.setErrorMensaje(ex.getMessage());
			return rta;
		} 
	}
	
	@RequestMapping(value = "/legajo-cheques-rechazados-crear", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody LegajoChequeRechazadoJsonResponse crear(HttpServletRequest request,@RequestBody final LegajoChequeRechazadoDto dto) throws Exception {
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		legajoChequeRechazadoJsonResponse.setMostrarPopUpLegajo(false);
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		try {
			legajoChequeRechazadoJsonResponse = legajoChequeRechazadoFacade.crear(dto, userSesion);
			legajoChequeRechazadoJsonResponse.setSuccess(true);
		} catch (ValidacionChequeAplicadoEnOperacionExcepcion e) {
			legajoChequeRechazadoJsonResponse.setSuccess(false);
			legajoChequeRechazadoJsonResponse.setMostrarPopUpLegajo(true);
			legajoChequeRechazadoJsonResponse.setCampoError("");
		} catch (NegocioExcepcion e) {
			Traza.error(LegajoChequeRechazadoController.class, e.getMessage());
			legajoChequeRechazadoJsonResponse.setSuccess(false);
			legajoChequeRechazadoJsonResponse.setCampoError("");
			legajoChequeRechazadoJsonResponse.setDescripcionError(e.getMessage());
		}
		return legajoChequeRechazadoJsonResponse;
	}

	@RequestMapping(value="/editar-legajo-cheque-rechazado", method=RequestMethod.POST)
	public ModelAndView editarLegajoChequesRechazados(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = this.altaChequesRechazados(request);
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);


		// Brian Botones volver
		if (!Validaciones.isNullOrEmpty(request.getParameter("goBack"))) {
			userSesion.getRetorno().remove(0);
			mv.addObject("idVolver", userSesion.getRetorno().get(0));
		} else if (!Validaciones.isNullOrEmpty(request.getParameter("volver"))) {
			if (!userSesion.getRetorno().get(0)
					.equals(request.getParameter("volver"))) {
				userSesion.getRetorno().add(0, request.getParameter("volver"));
				mv.addObject("idVolver", request.getParameter("volver"));
			} else {
				mv.addObject("idVolver", request.getParameter("volver"));
			}
		}
		String root = userSesion.getRetorno().get(0);
		
		if (
			!Validaciones.isObjectNull(root) &&
			!root.equals("/editar-legajo-cheque-rechazado")
		) {
			mv.addObject("showButton", true);
		}


		//mv.setViewName("legajo-cheque-rechazado");
		String id = request.getParameter("idLegajo");
		if (!Validaciones.isNumeric(id)) {
			throw new NegocioExcepcion("Error formato id incorrecto");
		} else {
			ObjectMapper mapper = new ObjectMapper();
			LegajoChequeRechazadoDto legajo = legajoChequeRechazadoFacade.buscar(
					Long.valueOf(id),
					userSesion
			);
			
			mv.addObject(
				"legajoChequeRechazadoDto",
				mapper.writeValueAsString(
					legajo
				)
			);
			
			mv.addObject(
				"montos",
				mapper.writeValueAsString(
					legajoChequeRechazadoFacade.obtenerMontos(Long.valueOf(id),SistemaEnum.getEnumByDescripcion(legajo.getSistemaOrigen()),legajo.getEstado())
				)
			);
			mv.addObject("edicion", true);
			mv.addObject("idLeg", id);
			if(!Validaciones.isNullOrEmpty(request.getParameter("solapa"))) {
				mv.addObject("solapa", request.getParameter("solapa"));
			}
		}
	

		mv.addObject("showButton", true);
		return mv;
	}
	@RequestMapping(value = "cheques-rechazados-busqueda")
	public ModelAndView chequeBusqueda(HttpServletRequest request) throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(BUSQUEDA_VIEW);
		
		/*
		 * Valida si es una busqueda normal o vuelve de un detallado 
		 */
		if (!Validaciones.isNullOrEmpty(request.getParameter("idVolver"))) {
			userSesion.setVolviendoABusqueda(true);
		} else{
			userSesion.setLegajoChqueRechazadoFiltro(new VistaSoporteLegajoChequeRechazadoFiltro());
			userSesion.setVolviendoABusqueda(false);
		}
		if (userSesion.getVolviendoABusqueda()) {
			mv.addObject("volviendoABusqueda", true);  	//para volver a busqeuda, que setee TRUE userSesion.getVolviendoABusqueda() y que guarde el ultimo filtro en userSesion asi aca lo toma.
			mv.addObject("cobroBusquedaFiltro", userSesion.getLegajoChqueRechazadoFiltro());
		}else{

			mv.addObject("volviendoABusqueda", false);
		}
		
		if (userSesion.getCaminoDeVuelta().size() > 0) {
			mv.addObject("caminoDeVuelta", userSesion.getCaminoDeVuelta().peek());
		}
		
		//Cargar combos
		List<ShvParamEmpresa> empresas = (List<ShvParamEmpresa>) combosServicio.listarEmpresasPorUsuario(userSesion);
		List<ShvParamSegmento> segmentos = new ArrayList<ShvParamSegmento>();
		List<UsuarioLdapDto> copropietarios = new ArrayList<UsuarioLdapDto>();
	
		if (empresas.size() == 1) {
			ShvParamEmpresa empresa = empresas.get(0);
			segmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(empresa.getIdEmpresa(),userSesion);
			if (segmentos.size() == 1) {
				ShvParamSegmento segmento = segmentos.get(0);
				Collection<String> usuariosExcluidos = new ArrayList<String>();
				usuariosExcluidos.add(userSesion.getIdUsuario());
				copropietarios = (List<UsuarioLdapDto>) combosServicio.listarCopropietarioLegajoChequeRechazadoPorEmpresaYSegmento(empresa.getIdEmpresa(), segmento.getIdSegmento(), usuariosExcluidos);
				Collections.sort(
					copropietarios,
					new UsuarioLdapDtoComparator()
				);
			} 
		}
		
		BancosParametroDto bancosParametroDto = combosFacade.listaBancosAgrupadosPorDescipcion(empresas.get(0).getIdEmpresa());
		List<EstadoDto> estados = combosFacade.listarEstadosLegajo();
		
		// Brian Botones volver
		if (!Validaciones.isNullOrEmpty(request.getParameter("goBack"))) {
			userSesion.getRetorno().remove(0);
			mv.addObject("idVolver", userSesion.getRetorno().get(0));
		} else if (!Validaciones.isNullOrEmpty(request.getParameter("volver"))) {
			if (!userSesion.getRetorno().get(0)
					.equals(request.getParameter("volver"))) {
				userSesion.getRetorno().add(0, request.getParameter("volver"));
				mv.addObject("idVolver", request.getParameter("volver"));
			} else {
				mv.addObject("idVolver", request.getParameter("volver"));
			}
		}
		
		mv.addObject("empresas", empresas);
		mv.addObject("comboEmpresa", (empresas.size() == 0 || empresas.size() > 1));
		mv.addObject("segmentos", segmentos);
		mv.addObject("comboSegmento", (segmentos.size() == 0 || segmentos.size() > 1));
		mv.addObject("estados", estados);
		mv.addObject("comboEstados", (estados.size() == 0 || estados.size() > 1));
		mv.addObject("bancoOrigenes", bancosParametroDto.getListaBancos());
		mv.addObject("comboBancoOrigen", (estados.size() == 0 || estados.size() > 1));
		mv.addObject("bancoOrigenesDescripcion", bancosParametroDto.getAgrupadores());
		mv.addObject("criterioBusquedaCliente", Arrays.asList(CriterioBusquedaClienteEnum.values()));
		bancosParametroDto.setListaBancos(null);
		ObjectMapper mapper = new ObjectMapper();
		mv.addObject("bancoDescripcion", mapper.writeValueAsString(bancosParametroDto));
		mv.addObject("legajoChequeRechazadoFiltro", mapper.writeValueAsString(userSesion.getLegajoChqueRechazadoFiltro()));
		
		
		return mv;
	}
	
	/**
	 * Devuelve una lista de analistas segun el segmento seleccionado
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "legajo-busqueda/buscarAnalistaLegajos", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<SelectOptionJsonResponse> buscarAnalistaCobros(HttpServletRequest request) throws Exception {
		
		String idEmpresa = request.getParameter("idEmpresa");
		String idSegmento = request.getParameter("idSegmento");

		List<SelectOptionJsonResponse> result = new ArrayList<SelectOptionJsonResponse>();
		
		List<UsuarioLdapDto> listaDeAnalistasCobranza = legajoChequeRechazadoFacade.buscarUsuariosPorPerfilSegmento(idEmpresa, idSegmento,TipoPerfilEnum.ANALISTA_COBRANZA.nombreLdap());
		
		for (UsuarioLdapDto analista : listaDeAnalistasCobranza) {
			SelectOptionJsonResponse jsonResp = new SelectOptionJsonResponse();
			jsonResp.setValue(analista.getTuid());
			jsonResp.setText(analista.getNombreCompleto());
			/* valido para no devolver duplicados */
			if (!Validaciones.isCollectionNotEmpty(result)) {
				result.add(jsonResp);
			} else {
				Boolean encontrado = false;
				for (SelectOptionJsonResponse a : result) {
					if (a.getValue().equalsIgnoreCase(jsonResp.getValue())) {
						encontrado = true;
					}
				}
				if (!encontrado){
					result.add(jsonResp);
				}
			}
		}
		
		return result;
	}

	@RequestMapping(value="legajo-cheque-rechazado-detalle", method=RequestMethod.POST)
	public ModelAndView detalle(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(RESUMEN_VIEW);
		
		LegajoChequeRechazadoJsonResponse legajoResponse = new LegajoChequeRechazadoJsonResponse();
		LegajoChequeRechazadoDto legajoDto = new LegajoChequeRechazadoDto();
		
        String id = request.getParameter("idLegajo");
        if (!Validaciones.isNumeric(id)) {
               throw new NegocioExcepcion("Error formato id incorrecto");
        } else {
        	legajoDto = legajoChequeRechazadoFacade.buscar(Long.valueOf(id));
        	legajoResponse.setSuccess(true);
        }
        
        
        mv.addObject("idLegajo", legajoDto.getIdLegajoChequeRechazado());
        mv.addObject("idLeg", legajoDto.getIdLegajoChequeRechazado());
        mv.addObject("estado", legajoDto.getEstadoDescripcion());
        mv.addObject("empresa", legajoDto.getEmpresa());
		mv.addObject("segmento", legajoDto.getSegmento());
		mv.addObject("nombreCompletoUsuario", legajoDto.getAnalista());
		mv.addObject("nombreCompletoUsuarioCopropietario", legajoDto.getCopropietario());
		mv.addObject("ubicacion", legajoDto.getUbicacionDesc());
		
		mv.addObject("fechaRechazo", legajoDto.getFechaRechazo());
		mv.addObject("motivoRechazo", legajoDto.getMotivoLegajoDescripcion());
		mv.addObject("fechaNotificacionRechazo", legajoDto.getFechaNotificacion());
		mv.addObject("observacionesAnterioresResumen", legajoDto.getObservacionesAnteriores());
		mv.addObject("prevObservText", legajoDto.getObservaciones());
		
		mv.addObject("sistemaOrigen",legajoDto.getSistemaOrigen());
		mv.addObject("tipoCheque",legajoDto.getTipoCheque());
		mv.addObject("edTipoCheque", legajoDto.getTipoChequeDescripcion());
		if (!Validaciones.isNullEmptyOrDash(legajoDto.getIdBancoOrigen()) && !Validaciones.isNullEmptyOrDash(legajoDto.getBancoOrigenDescripcion())){
			mv.addObject("comboBancoOrigen", legajoDto.getIdBancoOrigen() + " - " + legajoDto.getBancoOrigenDescripcion());
		} else {
			mv.addObject("comboBancoOrigen", "");
		}
		mv.addObject("bancoOrigenesDescripcion", legajoDto.getBancoOrigenDescripcion());
		
		mv.addObject("nroCheque", legajoDto.getNumeroCheque());
		mv.addObject("fechaEmisionResumen", legajoDto.getFechaEmision());
		mv.addObject("fechaDepositoResumen", legajoDto.getFechaDeposito());
		mv.addObject("fechaVencimientoResumen", legajoDto.getFechaVencimiento());
		mv.addObject("moneda", legajoDto.getMondeDesc());
		mv.addObject("ubicacionCheque", UbicacionChequeEnum.listaUbicaciones());
		
		
		mv.addObject("importeChequeResumen", legajoDto.getImporte());
		if (!Validaciones.isNullEmptyOrDash(legajoDto.getIdAcuerdo())){
			mv.addObject("comboAcuerdoDeposito", "Acuerdo " + legajoDto.getIdAcuerdo());
		} else {
			mv.addObject("comboAcuerdoDeposito",legajoDto.getIdAcuerdo());
		}
		mv.addObject("comboBancoDeposito", legajoDto.getBancoDepositoDescripcion());
		
		mv.addObject("cuentaDepositoResumen", legajoDto.getNumeroCuentaDeposito());
		mv.addObject("inputCodClienteResumen", legajoDto.getIdCliente());
		mv.addObject("razonSocialClienteLegadoResumen", legajoDto.getDescripcionCliente());
		
		Map<String, String> map = legajoChequeRechazadoFacade.obtenerMontos(
			legajoDto.getIdLegajoChequeRechazado(),
			SistemaEnum.getEnumByDescripcion(legajoDto.getSistemaOrigen()),
			legajoDto.getEstado()
		);
		
		if (!Validaciones.isNullEmptyOrDash(map.get("montoARevertir"))) {
			mv.addObject("montoARevertir", Utilidad.formatCurrency(Utilidad.stringToBigDecimal(map.get("montoARevertir")), true, true, 2));
		} else {
			mv.addObject("montoARevertir", "0,00");
		}
		if (!Validaciones.isNullEmptyOrDash(map.get("montoAReenvolsar"))) {
			mv.addObject("montoAReenvolsar", Utilidad.formatCurrency(Utilidad.stringToBigDecimal(map.get("montoAReenvolsar")), true, true, 2));
		} else {
			mv.addObject("montoAReenvolsar", "0,00");
		}
//		
		ObjectMapper mapper = new ObjectMapper();
		mv.addObject("legajoChequeRechazadoDto",mapper.writeValueAsString(legajoDto));
		
		// Brian Botones volver
		if (!Validaciones.isNullOrEmpty(request.getParameter("goBack"))) {
			userSesion.getRetorno().remove(0);
			mv.addObject("idVolver", userSesion.getRetorno().get(0));
		} else if (!Validaciones.isNullOrEmpty(request.getParameter("volver"))) {
			if (!userSesion.getRetorno().get(0)
					.equals(request.getParameter("volver"))) {
				userSesion.getRetorno().add(0, request.getParameter("volver"));
				mv.addObject("idVolver", request.getParameter("volver"));
			} else {
				mv.addObject("idVolver", request.getParameter("volver"));
			}
		}
		return mv;
	
	}
	@RequestMapping(value = "legajo-cheque-rechazado/buscarLegajos", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody LegajoChequeRechazadoJsonResponse buscarLegajos(@RequestBody final VistaSoporteLegajoChequeRechazadoFiltro filtro, HttpServletRequest request) throws Exception {
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		try {
			filtro.setUsuarioLogeado(userSesion);
			legajoChequeRechazadoJsonResponse.setResultados(legajoChequeRechazadoFacade.listar(filtro, userSesion));
			legajoChequeRechazadoJsonResponse.setSuccess(true);
			filtro.setUsuarioLogeado(null);
			userSesion.setLegajoChqueRechazadoFiltro(filtro);
		} catch (ValidacionExcepcion ex) {
			legajoChequeRechazadoJsonResponse.setSuccess(false);
			List<ErrorJson> erroresJson = new ArrayList<ErrorJson>();
			for (int i = 0; i < ((ValidacionExcepcion) ex).getCamposError().size(); i++) {
				ErrorJson eJson = new ErrorJson(((ValidacionExcepcion) ex).getCamposError().get(i),((ValidacionExcepcion) ex).getCodigosLeyenda().get(i));
				erroresJson.add(eJson);
			}
			legajoChequeRechazadoJsonResponse.setErrores((ArrayList<ErrorJson>) erroresJson);
		} catch (NegocioExcepcion ex) {
			legajoChequeRechazadoJsonResponse.setSuccess(false);
			legajoChequeRechazadoJsonResponse.setCampoError("#errorRespuestaLegajos");
			legajoChequeRechazadoJsonResponse.setErrorMensaje(ex.getMessage());
		} 
		return legajoChequeRechazadoJsonResponse;
	}
	
	@RequestMapping(value="/anularLegajo", method=RequestMethod.POST)
	public ModelAndView anular(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView(ANULACION_EXITOSA_VIEW);
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		String id = request.getParameter("idLegajo");
		if (!Validaciones.isNumeric(id)) {
			throw new NegocioExcepcion("Error formato id incorrecto");
		} else {	
			legajoChequeRechazadoFacade.anular(Long.valueOf(id), userSesion);
			
		}
		String mensaje = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.anulacion.ok"), id);
		mv.addObject("mensaje", mensaje);
		if (!Validaciones.isNullOrEmpty(request.getParameter("goBack"))) {
			userSesion.getRetorno().remove(0);
			mv.addObject("idVolver", userSesion.getRetorno().get(0));
		} else if (!Validaciones.isNullOrEmpty(request.getParameter("volver"))) {
			if (!userSesion.getRetorno().get(0)
					.equals(request.getParameter("volver"))) {
				userSesion.getRetorno().add(0, request.getParameter("volver"));
				mv.addObject("idVolver", request.getParameter("volver"));
			} else {
				mv.addObject("idVolver", request.getParameter("volver"));
			}
		}
//		mv.addObject("url", BUSQUEDA_VIEW);
		return mv;
	}
	
	@RequestMapping (value="legajo-cheque-rechazado/modificar-legajo", method=RequestMethod.POST)
	public @ResponseBody LegajoChequeRechazadoJsonResponse modificar (@RequestBody final LegajoChequeRechazadoDto dto, HttpServletRequest request) throws Exception {
		
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		try{
			
			legajoChequeRechazadoJsonResponse = legajoChequeRechazadoFacade.modificar(dto, userSesion);
			legajoChequeRechazadoJsonResponse.setSuccess(true);
			
		} catch (NegocioExcepcion e) {
			Traza.error(LegajoChequeRechazadoController.class, e.getMessage());
			legajoChequeRechazadoJsonResponse.setSuccess(false);
			legajoChequeRechazadoJsonResponse.setCampoError("");
			legajoChequeRechazadoJsonResponse.setDescripcionError(e.getMessage());
		}
		
		return legajoChequeRechazadoJsonResponse;
	
	}
	
	
	@RequestMapping (value="legajo-cheque-rechazado/revertir-cobros-relacionados", method=RequestMethod.POST)
	public @ResponseBody LegajoChequeRechazadoJsonResponse revertirCobrosRelacionadosIce (@RequestBody final LegajoChequeRechazadoDto dto, HttpServletRequest request) throws Exception {
		
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		try {
			
			legajoChequeRechazadoJsonResponse = legajoChequeRechazadoFacade.revertirCobrosRelacionadosIce(dto, userSesion);
			legajoChequeRechazadoJsonResponse.setSuccess(true);
			
		} catch (NegocioExcepcion e) {
			Traza.error(LegajoChequeRechazadoController.class, e.getMessage());
			legajoChequeRechazadoJsonResponse.setSuccess(false);
			legajoChequeRechazadoJsonResponse.setCampoError("");
			legajoChequeRechazadoJsonResponse.setDescripcionError(e.getMessage());
		}
		
		return legajoChequeRechazadoJsonResponse;
	
	}
	/**
	 * Me permite adjuntar los comprobantes
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "legajo-cheque-rechazado/adjuntarComprobante", method = RequestMethod.POST)
	public void adjuntarComprobante(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {                 
		
		String idLegajo = request.getParameter("idLegajo");
		String descripcion = request.getParameter("descripcion");
		
		Iterator<String> itr =  request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		
		ComprobanteJsonResponse json = new ComprobanteJsonResponse();
		try {
			legajoChequeRechazadoValidacionServicio.validarComprobantes(file, descripcion);
			
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			
			ComprobanteDto comprobante = new ComprobanteDto();
			comprobante.setDescripcionArchivo(descripcion);
			comprobante.setUsuarioCreacion(userSesion.getIdUsuario());
			comprobante.setNombreArchivo(file.getOriginalFilename());
			comprobante.setDocumento(file.getBytes());
			
			comprobante = legajoChequeRechazadoFacade.crearAdjuntoLegajo(Long.valueOf(idLegajo), comprobante);
			
			json.setSuccess(true);
			json.setDescripcion(descripcion);
			json.setFileName(file.getOriginalFilename());
			json.setId(Integer.parseInt(comprobante.getIdComprobante().toString()));
		} catch (ValidacionExcepcion e) {
			json.setSuccess(false);
			json.setCampoError(e.getCampoError());
			json.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString(e.getLeyenda()));
		}
		
		ControlArchivo.responderJSON(json, response);
	}
	
	/**
	 * 
	 * @param comprobante
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="legajo-cheque-rechazado/eliminarComprobante", method=RequestMethod.POST)
	@ResponseBody
	public ComprobanteJsonResponse eliminarComprobante(@RequestBody final ComprobanteDto comprobante, HttpServletRequest request) throws Exception {
		
		legajoChequeRechazadoFacade.eliminarAdjuntoLegajo(comprobante.getIdComprobante());
		
		ComprobanteJsonResponse json = new ComprobanteJsonResponse();
		json.setSuccess(true);
		json.setId(Integer.parseInt(comprobante.getIdComprobante().toString()));
		return json;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="legajo-cheque-rechazado/descargarComprobante", method=RequestMethod.GET)
	public void descargarComprobante(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		ComprobanteDto comprobante = legajoChequeRechazadoFacade.buscarAdjuntoLegajo(Long.valueOf(id));
		
		if (comprobante!=null) {
			byte[] file = comprobante.getDocumento();
			String fileName = comprobante.getNombreArchivo();
			ControlArchivo.descargarComoArchivo(file, fileName, response);
		}
	}
	@RequestMapping (value="legajo-cheque-rechazado/verificar-reversion-shiva-en-proceso", method=RequestMethod.POST)
	public @ResponseBody JsonResponse verificarReversionShivaEnProceso(@RequestBody final LegajoChequeRechazadoDto dto, HttpServletRequest request) throws Exception {
		JsonResponse jsonResponse = new JsonResponse();

		try {
			jsonResponse = legajoChequeRechazadoFacade.verificarReversionShivaEnProceso(dto.getIdOperacion());
		} catch (NegocioExcepcion e) {
			Traza.error(getClass(), e.getMessage());
			jsonResponse.setSuccess(false);
			jsonResponse.setErrors("Error al verificar reversion en proceso");
		}
		return jsonResponse;
	}
	
	@RequestMapping (value="legajo-cheque-rechazado/confirmar-fin-reversion", method=RequestMethod.POST)
	public @ResponseBody LegajoChequeRechazadoJsonResponse confirmarFinReversion (@RequestBody final LegajoChequeRechazadoDto dto, HttpServletRequest request) throws Exception {
		
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		try{
			
			legajoChequeRechazadoJsonResponse = legajoChequeRechazadoFacade.confirmarFinReversion(dto, userSesion);
			legajoChequeRechazadoJsonResponse.setSuccess(true);
			
		} catch (NegocioExcepcion e) {
			Traza.error(LegajoChequeRechazadoController.class, e.getMessage());
			legajoChequeRechazadoJsonResponse.setSuccess(false);
			legajoChequeRechazadoJsonResponse.setCampoError("");
			legajoChequeRechazadoJsonResponse.setDescripcionError(e.getMessage());
		}
		
		return legajoChequeRechazadoJsonResponse;
	
	}
	
	@RequestMapping (value="legajo-cheque-rechazado/enviar-a-legales", method=RequestMethod.POST)
	public @ResponseBody LegajoChequeRechazadoJsonResponse enviarALegales (@RequestBody final String id, HttpServletRequest request) throws Exception {
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		try{
			LegajoChequeRechazadoDto legajoChequeRechazadoDto = legajoChequeRechazadoFacade.enviarALegales(new Long(id),userSesion);
			legajoChequeRechazadoJsonResponse.setResultado(legajoChequeRechazadoDto);
			legajoChequeRechazadoJsonResponse.setSuccess(true);
		} catch (NegocioExcepcion e) {
			Traza.error(LegajoChequeRechazadoController.class,e.getMessage());
			legajoChequeRechazadoJsonResponse.setSuccess(false);
			legajoChequeRechazadoJsonResponse.setCampoError("");
			legajoChequeRechazadoJsonResponse.setDescripcionError(e.getMessage());
		}
		
		return legajoChequeRechazadoJsonResponse;
	}

	@RequestMapping (value="legajo-cheque-rechazado/reembolsar", method=RequestMethod.POST)
	public @ResponseBody LegajoChequeRechazadoJsonResponse reembolsar (@RequestBody final String id, HttpServletRequest request) throws Exception {
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		try{
			LegajoChequeRechazadoDto legajoChequeRechazadoDto = legajoChequeRechazadoFacade.reembolsarCheque(new Long(id),userSesion);
			legajoChequeRechazadoJsonResponse.setResultado(legajoChequeRechazadoDto);
			legajoChequeRechazadoJsonResponse.setSuccess(true);
		} catch (NegocioExcepcion e) {
			Traza.error(LegajoChequeRechazadoController.class,e.getMessage());
			legajoChequeRechazadoJsonResponse.setSuccess(false);
			legajoChequeRechazadoJsonResponse.setCampoError("");
			legajoChequeRechazadoJsonResponse.setDescripcionError(e.getMessage());
		}
		return legajoChequeRechazadoJsonResponse;
	}
	
	@RequestMapping("/generarPdfCartaLegajo")
	public void generarPdfCartaLegajo(HttpServletRequest request, HttpServletResponse response, LegajoChequeRechazadoNotificacionDto notificacion) throws Exception {
		
		ArchivoByteArray archivo = legajoChequeRechazadoFacade.generarPdfCartaLegajo(notificacion.getIdNotificacion());
		ControlArchivo.descargarComoPDF( 
			archivo.getByteArray(),
			archivo.getNombreArchivo(),
			response
		);
	}
	
	
	@RequestMapping (value="legajo-cheque-rechazado/guardar-contacto", method=RequestMethod.POST)
	public @ResponseBody LegajoChequeRechazadoJsonResponse guardarContacto (@RequestBody final LegajoChequeRechazadoNotificacionDto legajoChequeRechazadoNotificacionDto, HttpServletRequest request) throws Exception {
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		try{
			legajoChequeRechazadoJsonResponse = legajoChequeRechazadoFacade.guardarContacto(legajoChequeRechazadoNotificacionDto,userSesion);
			legajoChequeRechazadoJsonResponse.setSuccess(true);
		} catch (NegocioExcepcion e) {
			Traza.error(LegajoChequeRechazadoController.class,e.getMessage());
			legajoChequeRechazadoJsonResponse.setSuccess(false);
			legajoChequeRechazadoJsonResponse.setCampoError("");
			legajoChequeRechazadoJsonResponse.setDescripcionError(e.getMessage());
		}
		return legajoChequeRechazadoJsonResponse;
	}
	
	@RequestMapping (value="legajo-cheque-rechazado/desistir", method=RequestMethod.POST)
	public @ResponseBody LegajoChequeRechazadoJsonResponse desistir (@RequestBody final LegajoChequeRechazadoDto dto, HttpServletRequest request) throws Exception {
		
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		try{
			
			legajoChequeRechazadoJsonResponse = legajoChequeRechazadoFacade.desistir(new Long(dto.getIdLegajoChequeRechazado()), userSesion);
			legajoChequeRechazadoJsonResponse.setSuccess(true);
			
		} catch (NegocioExcepcion e) {
			Traza.error(LegajoChequeRechazadoController.class, e.getMessage());
			legajoChequeRechazadoJsonResponse.setSuccess(false);
			legajoChequeRechazadoJsonResponse.setCampoError("");
			legajoChequeRechazadoJsonResponse.setDescripcionError(e.getMessage());
		}
		
		return legajoChequeRechazadoJsonResponse;
	
	}
	
	@RequestMapping (value="legajo-cheque-rechazado/cerrar", method=RequestMethod.POST)
	public @ResponseBody LegajoChequeRechazadoJsonResponse cerrar (@RequestBody final LegajoChequeRechazadoDto dto, HttpServletRequest request) throws Exception {
		
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		try{
			
			legajoChequeRechazadoJsonResponse = legajoChequeRechazadoFacade.cerrar(new Long(dto.getIdLegajoChequeRechazado()), userSesion);
			legajoChequeRechazadoJsonResponse.setSuccess(true);
			
		} catch (NegocioExcepcion e) {
			Traza.error(LegajoChequeRechazadoController.class, e.getMessage());
			legajoChequeRechazadoJsonResponse.setSuccess(false);
			legajoChequeRechazadoJsonResponse.setCampoError("");
			legajoChequeRechazadoJsonResponse.setDescripcionError(e.getMessage());
		}
		
		return legajoChequeRechazadoJsonResponse;
	
	}
	
	@RequestMapping (value="legajo-cheque-rechazado/eliminar-notificacion", method=RequestMethod.POST)
	public @ResponseBody LegajoChequeRechazadoJsonResponse eliminarContacto (@RequestBody final LegajoChequeRechazadoNotificacionDto legajoChequeRechazadoNotificacionDto, HttpServletRequest request) throws Exception {
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		try{
			legajoChequeRechazadoJsonResponse = legajoChequeRechazadoFacade.anularContacto(legajoChequeRechazadoNotificacionDto.getIdNotificacion(), legajoChequeRechazadoNotificacionDto.getIdLegajoChequeRechazado());
			
			legajoChequeRechazadoJsonResponse.setSuccess(true);
		} catch (NegocioExcepcion e) {
			Traza.error(LegajoChequeRechazadoController.class,e.getMessage());
			legajoChequeRechazadoJsonResponse.setSuccess(false);
			legajoChequeRechazadoJsonResponse.setCampoError("");
			legajoChequeRechazadoJsonResponse.setDescripcionError(e.getMessage());
		}
		return legajoChequeRechazadoJsonResponse;
	}
	
	@RequestMapping("legajo-cheque-rechazado/exportacionDetalleLegajo")
	public ModelAndView exportacionDetalleLegajo(HttpServletRequest request, HttpServletResponse response, LegajoChequeRechazadoDto legajoDto) throws Exception {
		
		
		legajoChequeRechazadoFacade.exportarDetalleLegajo(response, new Long(request.getParameter("idLeg")));
		
		
		
		return null;
	}
}
