package ar.com.telecom.shiva.presentacion.controlador;

import java.math.BigDecimal;
import java.math.BigInteger;
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

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import ar.com.telecom.shiva.base.comparador.ComparatorDocumentoCap;
import ar.com.telecom.shiva.base.comparador.ComparatorDocumentoCapPostAgrupacion;
import ar.com.telecom.shiva.base.comparador.UsuarioLdapDtoComparator;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.CriterioBusquedaClienteEnum;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDebitoImportadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoAdjuntoEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDebitoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.SubTipoCompensacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoAccionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoCapEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoSimularDisabled;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.SimulacionCobroExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceFormatoMensajeExcepcion;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaAcuerdoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaAcuerdoSalida.AcuerdoFacturacion;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaClienteSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaGeneracionCargoSalida;
import ar.com.telecom.shiva.base.jms.servicios.IFacJmsSyncServicio;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoConsultaAcuerdoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoConsultaAcuerdoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;
import ar.com.telecom.shiva.negocio.bean.ArchivoByteArray;
import ar.com.telecom.shiva.negocio.bean.BuilderConsultaSap;
import ar.com.telecom.shiva.negocio.bean.TotalAcumuladoresTransacciones;
import ar.com.telecom.shiva.negocio.reportes.IReportsUtils;
import ar.com.telecom.shiva.negocio.reportes.modulos.CobrosReportsUtils;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineBusquedaYPaginadoServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.ICotizacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IExcelBusquedaCobroServicio;
import ar.com.telecom.shiva.negocio.servicios.IExcelServicio;
import ar.com.telecom.shiva.negocio.servicios.IProveedorCapServicio;
import ar.com.telecom.shiva.negocio.servicios.ITeamComercialServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.IClienteValidacionServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.ICobroOnlineValidacionServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.IParametroConfReglaCampoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvParamCotizacion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoCobro;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamProvincia;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroHistoricoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroMedioDePagoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroOtrosDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTransaccionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTratamientoDiferenciaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CodigoOperacionExternaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.AcuerdoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.CapJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ClienteJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.CobroEdicionJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.CobroJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.CobrosJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.CodigoOperacionExternaJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ComprobanteJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.CotizacionJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.CreditoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.CuentaContableJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.DebitoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.DeimosJsonRequest;
import ar.com.telecom.shiva.presentacion.bean.dto.json.DeimosJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ErrorJson;
import ar.com.telecom.shiva.presentacion.bean.dto.json.HabilitacionBtnSimularJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ImportarDebitoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ImportarOtrosDebitoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.JsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.SelectOptionJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.TransaccionesJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroCreditoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroDebitoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroDocCapFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.CotizacionFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaCobroHistorialFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteCobroOnlineFiltro;
import ar.com.telecom.shiva.presentacion.bean.paginacion.CreditosControlPaginacion;
import ar.com.telecom.shiva.presentacion.facade.IClienteFacade;
import ar.com.telecom.shiva.presentacion.facade.ICombosFacade;

@Controller
public class CobroOnlineController extends Controlador {
	
	private static final String CONF_COBRO = "cobro/cobros-configuracion";
	private static final String DETALLE_APROBACION_VIEW = "cobro/cobro-detalle-aprobacion";
	private static final String MANUAL_APROBACION_VIEW = "cobro/cobro-confirmar-aplicacion-manual";
	private static final String BUSQUEDA_VIEW = "cobro/cobro-busqueda";
	private static final String ACTUALIZACION_EXITOSA_VIEW = "cobro/cobro-actualizacion-exitosa";
	private static final String HISTORIAL_VIEW = "cobro/cobro-historial";
	
	@Autowired
	private ICobroOnlineServicio cobroOnlineServicio;
	
	@Autowired
	private ICobroOnlineSoporteServicio cobroOnlineSoporteServicio;
	
	@Autowired 
	private ICobroOnlineBusquedaYPaginadoServicio busquedaYPaginadoServicio;
	
	@Autowired
	private IFacJmsSyncServicio facJmsSyncServicio;

	@Autowired
	private IClienteSiebelServicio clienteConsultarSiebelServicio;
	
	@Autowired
	private IExcelServicio exportacionDetalleCobro;
	
	@Autowired
	private ICobroOnlineValidacionServicio cobroOnlineValidacionServicio;
	
	@Autowired
	private IClienteValidacionServicio clienteValidacionServicio;
	
	@Autowired
	private IClienteFacade clienteFacade;
	
	@Autowired
	private ILdapServicio ldapServicio;

	@Autowired
	IClienteCalipsoServicio clienteCalipsoServicio;
	
	@Autowired
	private ICobroImputacionServicio cobroServicio;
	
	@Autowired   
	private CobrosReportsUtils cobrosReportsUtils;
	
	@Autowired   
	private IExcelBusquedaCobroServicio exportacionBusquedaCobros;
	
	@Autowired
	private ITeamComercialServicio teamComercialServicio;
	
	@Autowired
	private ICotizacionServicio cotizacionServicio;

	@Autowired
	private IProveedorCapServicio proveedorCapServicio;
	@Autowired
	private ICombosFacade combosFacade;
	
	@Autowired
	private ICobroBatchServicio cobroBatchServicio;
	@Autowired
	private IParametroConfReglaCampoDao parametroConfReglaCampoDaoImpl;
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cobros-configuracion")
	public ModelAndView cobrosConfiguracion(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(CONF_COBRO);
		
		List<ShvParamEmpresa> empresas = (List<ShvParamEmpresa>) combosServicio.listarEmpresasPorUsuario(userSesion);
		List<ShvParamSegmento> segmentos = new ArrayList<ShvParamSegmento>(); 
		List<UsuarioLdapDto> copropietarios = new ArrayList<UsuarioLdapDto>();
		ObjectMapper mapper = new ObjectMapper();
//		List<MonedaEnum> monedasSoloPesos = new ArrayList<MonedaEnum>();
//		monedasSoloPesos.add(MonedaEnum.PES);
		if (empresas.size() == 1) {
			ShvParamEmpresa empresa = empresas.get(0);
			segmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(empresa.getIdEmpresa(),userSesion);
			if (segmentos.size() == 1) {
				ShvParamSegmento segmento = segmentos.get(0);
				Collection<String> usuariosExcluidos = new ArrayList<String>();
				usuariosExcluidos.add(userSesion.getIdUsuario());
				copropietarios = (List<UsuarioLdapDto>) combosServicio.listarCopropietarioCobroPorEmpresaYSegmento(empresa.getIdEmpresa(), segmento.getIdSegmento(), usuariosExcluidos);
				Collections.sort(
					copropietarios,
					new UsuarioLdapDtoComparator()
				);
			} 
		}
		
		List<ShvParamMotivoCobro> motivos = (List<ShvParamMotivoCobro>) combosServicio.listarMotivosConfiguracionCobro();
		List<ShvParamProvincia> listaProvincia = (List<ShvParamProvincia>) combosServicio.listarProvincias();
		
		
		// TAB CLIENTES
		mv.addObject("empresas", empresas);
		mv.addObject("comboEmpresa", (empresas.size() == 0 || empresas.size() > 1));
		mv.addObject("segmentos", segmentos);
		mv.addObject("comboSegmento", (segmentos.size() == 0 || segmentos.size() > 1));
		mv.addObject("monedasOperacion", MonedaEnum.getEnum$yU$S());
//		mv.addObject("comboMonedaOperacion", (MonedaEnum.getEnum$yU$S().size() == 0 || MonedaEnum.getEnum$yU$S().size() > 1));
		mv.addObject("copropietarios", copropietarios);
		mv.addObject("comboCopropietarios", (copropietarios.size() == 0 || copropietarios.size() > 1));
		mv.addObject("motivos", motivos);
		mv.addObject("comboMotivo", (motivos.size() == 0 || motivos.size() > 1));
		mv.addObject("idUsuario", userSesion.getIdUsuario());
		mv.addObject("nombreCompletoUsuario", userSesion.getNombreCompleto());
		mv.addObject("criterioBusquedaCliente", Arrays.asList(CriterioBusquedaClienteEnum.values()));
		mv.addObject("listaDebitoTipoDocumentos", TipoComprobanteEnum.valoresParaDebito());
		mv.addObject("listaCreditoSistema", SistemaEnum.getEnumComboCreditoSistema());
		mv.addObject("listaDebitosSistema", SistemaEnum.getEnumMICyCLP());
		mv.addObject("sistemas", SistemaEnum.getEnumMICyCLP());
		mv.addObject("monedas", MonedaEnum.getEnum$yU$S());
		mv.addObject("monedasBusquedaCap", MonedaEnum.getEnum$yU$SEuros());
		mv.addObject("mediosPago", TipoCreditoEnum.getEnumCreditos());
		
		// TAB MEDIO DE PAGO
		mv.addObject("mediosPagoAgregar", TipoCreditoEnum.getEnumMediosPagos());
		mv.addObject("subTipoCompensaciones", SubTipoCompensacionEnum.getEnums());
		mv.addObject("tiposDeDocumentosACompensar", TipoDocumentoCapEnum.obtenerEnumParaCombo());

		mv.addObject("provincia", listaProvincia);
		mv.addObject("comboProvincia", (listaProvincia.size() == 0 || listaProvincia.size() > 1));

		//Tratamiento diferencia
		mv.addObject("reintegrosDebito", TipoTratamientoDiferenciaEnum.getEnumComboTratamientoDiferenciaDebito());
		
		
		mv.addObject("reintegrosCredito", combosFacade.listarComboTratamientoDiferenciaCred());
		mv.addObject("sistemaAplicacionManual", SistemaEnum.getEnumAplicacionManual());
		mv.addObject("cobroEditable", false);

		mv.addObject("confOtrosDebitos", mapper.writeValueAsString(combosFacade.listarCombosCamposOtrosDebito(null)));
		mv.addObject("confComboSociedad", mapper.writeValueAsString(SociedadEnum.convertirAMap()));
		mv.addObject("confComboSistema", mapper.writeValueAsString(SistemaEnum.convertirAMapOtrosDebito()));
		mv.addObject("confComboTipoComprobante", mapper.writeValueAsString(TipoComprobanteEnum.convertirAMap()));
		mv.addObject("confComboMoneda", mapper.writeValueAsString(MonedaEnum.convertirAMap()));
		mv.addObject("leyendaComboSeleccionar", Propiedades.MENSAJES_PROPIEDADES.getString("combo.seleccionar"));
	
		return mv;
	}

	/**
	 * Spring 5
	 * Editar cobro en la pantalla de configuracion de cobro, se accede desde la pantalla de busqueda de cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cobros-configuracion-edicion")
	public ModelAndView cobrosConfiguracionEdicion(HttpServletRequest request) throws Exception {
		ModelAndView mv = this.cobrosConfiguracion(request);
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String idCobro = "";
		String idCobroPadre = "";
		boolean vengoReedicion = false;
		long idTarea = 0;
		String vengoReedicionStr = request.getParameter("vengoReedicion");
		if(!Validaciones.isNullEmptyOrDash(vengoReedicionStr)) {
			vengoReedicion = Boolean.valueOf(vengoReedicionStr);
		}
		String idTareaStr = request.getParameter("idTarea");
		if(!Validaciones.isNullEmptyOrDash(idTareaStr)) {
			idTarea = Long.valueOf(idTareaStr);
		}
		
		String idCobroStr = request.getParameter("idCobro");
		if(!Validaciones.isNullEmptyOrDash(idCobroStr)) {
			idCobro = idCobroStr;
		}
		
		if(!Validaciones.isNullEmptyOrDash(idCobro) && !vengoReedicion){
			cobroOnlineServicio.eliminarTareasAlEditar(Long.parseLong(idCobro), null, userSesion);
		}
		
		mv.addObject("idCobro", idCobro);
		mv.addObject("idTarea", idTarea);
		mv.addObject("idCobroPadre", idCobroPadre);
		mv.addObject("cobroEditable", true);
		mv.addObject("showButton", true);
		mv.addObject("vengoReedicion", vengoReedicion);
		
		//Brian Botones volver
		if(!Validaciones.isNullOrEmpty(request.getParameter("goBack"))) {
			userSesion.getRetorno().remove(0);
			mv.addObject("idVolver", userSesion.getRetorno().get(0));
		}else if (!Validaciones.isNullOrEmpty(request.getParameter("volver"))){
			if(!userSesion.getRetorno().get(0).equals(request.getParameter("volver"))) {
				userSesion.getRetorno().add(0, request.getParameter("volver"));
				mv.addObject("idVolver", request.getParameter("volver"));	
			}else {
				mv.addObject("idVolver", request.getParameter("volver"));
			}
		}

		return mv;
	}
	
	/**
	 * Spring 5
	 * Eliminar / Anular tarea desde la bandeja de entrada del Analista Cobranza.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cobros-configuracion-eliminar-tarea")
	public ModelAndView cobrosConfiguracionEliminarTarea(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String idCobro = request.getParameter("idCobro");
		
		cobroOnlineServicio.anularTarea(Long.parseLong(idCobro), userSesion);
		
		ModelAndView mv = new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("eliminarTareaOK.cobro.mensaje"));
		mv.addObject("url","bandeja-de-entrada");
		return mv;
	}
	
	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/eliminar-tarea-error-desapropiacion")
	public ModelAndView eliminarTareaRevCobEnErrorEnDesapro(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String idCobro = request.getParameter("idCobro");
		
		cobroOnlineServicio.anularTareaErrorDesapropiacion(Long.parseLong(idCobro), userSesion);
		
		ModelAndView mv = new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("eliminarTareaOK.cobro.mensaje.soloTarea"));
		mv.addObject("url","bandeja-de-entrada");
		return mv;
	}
	
	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/eliminar-tarea-error-confirmacion")
	public ModelAndView eliminarTareaRevCobEnErrorEnConfirmacion(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String idCobro = request.getParameter("idCobro");
		
		cobroOnlineServicio.anularTareaErrorConfirmacion(Long.parseLong(idCobro), userSesion);
		
		ModelAndView mv = new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("eliminarTareaOK.cobro.mensaje.soloTarea"));
		mv.addObject("url","bandeja-de-entrada");
		return mv;
	}
	
	/**
	 * Sprint5 
	 * Metodo que carga la pantalla de cobro-detalle-aprobacion
	 * Pasar por parametro a traves del request el "idCobro" y "opcion", podiendo ser D = Detalle, A= Aprobacion
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/cobro-detalle-aprobacion")
	public ModelAndView cobroDetalleAprobacion(HttpServletRequest request) throws Exception {
		
		ModelAndView mv=new ModelAndView(DETALLE_APROBACION_VIEW);
		CobroDto cobroDto = new CobroDto();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);

		if(!Validaciones.isNullOrEmpty(request.getParameter("idOperacionRelacionada"))) {
			cobroDto = cobroOnlineServicio.buscarCobroPorIdOperacion(Utilidad.stringToBigDecimal(request.getParameter("idOperacionRelacionada")).longValue());
			mv.addObject("volviendoA", request.getParameter("volver"));
		}else if(!Validaciones.isNullOrEmpty(request.getParameter("idCobro"))){
			cobroDto = cobroOnlineServicio.buscarCobro(Utilidad.stringToBigDecimal(request.getParameter("idCobro")).longValue());
		}
		
//		Logica para la navegacion desde Legajos
		mv.addObject("idLegajo", request.getParameter("idLeg"));
		mv.addObject("solapa", request.getParameter("solapa"));
		mv.addObject("idOperacionRelacionada", request.getParameter("idOperacionRelacionada"));
		
		mv.addObject("idDescobro", request.getParameter("idDescobro"));
		mv.addObject("idCobroFormatiado", cobroDto.getIdCobro());
		mv.addObject("idCobro", cobroDto.getIdCobro());
		mv.addObject("idCobroPadre", cobroDto.getIdCobroPadre());

		if(!Validaciones.isObjectNull(cobroDto)){
			
			if(!Validaciones.isNullOrEmpty(request.getParameter("vuelvoBandeja"))){
				mv.addObject("vuelvoBandeja", request.getParameter("vuelvoBandeja"));
			}
			String volverAPantalla = request.getParameter("volverAPantalla");
			if(!Validaciones.isNullEmptyOrDash(volverAPantalla)
					&& Constantes.DESTINO_BUSQUEDA_COBRO.equals(volverAPantalla)){
				mv.addObject("volverAPantalla", volverAPantalla);
			}
			String idCobro = request.getParameter("idCobro");
			for (CobroDebitoDto debito : cobroDto.getDebitos()) {
				// Completo los atributos 'opeAsocAnalista' y 'marcaPagoCompensacionEnProcesoShiva
				cobroOnlineServicio.setearAtributosPorConsulta(debito, idCobro);
			}
			for (CobroCreditoDto credito : cobroDto.getCreditos()) {
				// Completo los atributos 'opeAsocAnalista' y 'marcaPagoCompensacionEnProcesoShiva
				cobroOnlineServicio.setearAtributosPorConsulta(credito, idCobro);
			}
			CobroJsonResponse cobroJsonResponse = cobroOnlineServicio.obtenerEstados(
				cobroDto.getIdCobro(),
				true,
				"",
				"|"
			);
			
			//formatea el importe a cobrar a $99.99..
			cobroDto.setDebitos(cobroOnlineServicio.formatearImporteACobrar(cobroDto.getDebitos()));
			cobroDto.setCreditos(cobroOnlineServicio.formatearImportesCredito(cobroDto.getCreditos()));
			
			//formatea el reclamo y migrado en origen: si esta en NULL lo pasa a NO.
			cobroDto.setDebitos(cobroOnlineServicio.formatearReclamoYMigradoEnOrigen(cobroDto.getDebitos()));
			
			// Ordeno los documentos caps y seteo los colores de renglon
			cobroOnlineServicio.prepararDocumentosCapParaSuVisualizacion(cobroDto);
			
			mv.addObject("controlPaginacion", cobroDto.getControlPaginacion());
			
			mv.addObject("prevEmpresa", cobroDto.getEmpresa());
			mv.addObject("prevSegmento", cobroDto.getSegmento());
			mv.addObject("prevMonedaOperacion", MonedaEnum.getEnumBySigno2(cobroDto.getMonedaOperacion()).getDescripcion());
			mv.addObject("nombreCompletoUsuario", cobroDto.getNombreAnalista());//usar ldap traer nombre completo (cobroDto.getIdAnalista()) mapeador
			mv.addObject("prevCopropietario", cobroDto.getNombreCopropietario());//usar ldap traer nombre completo (ver de hacer en el mapeador y crear campo nuevo en dto de tipo usuario).(cobroDto.getIdCopropietario())
			mv.addObject("prevMotivo", cobroDto.getDescripcionMotivoCobro());//crear un motivoCobroServicio para buscar el objeto motivo qe me da la descripcion usando metodo del dao MotivoCobroDao.
			mv.addObject("listaClientes", ((ArrayList<ClienteDto>) Utilidad.guionesNull(new ArrayList<>(cobroDto.getClientes()))));
			mv.addObject("listaDebitos", ((ArrayList<CobroDebitoDto>) Utilidad.guionesNull(new ArrayList<>(cobroDto.getDebitos()))));
			mv.addObject("idMotivoCobro", cobroDto.getIdMotivoCobro());
			mv.addObject("idOperacion", cobroDto.getIdOperacionFormateado());
			mv.addObject("listaCreditos", ((ArrayList<CobroCreditoDto>) Utilidad.guionesNull(new ArrayList<>(cobroDto.getCreditos()))));
			mv.addObject("listaMediosPagos", ((ArrayList<CobroMedioDePagoDto>) Utilidad.guionesNull(new ArrayList<>(cobroDto.getMedios()))));
			
			/*Agrego los comprobantes de importación de otros débitos a los comprobantes de cobro*/
			List<ComprobanteDto> comprobantes = new ArrayList<ComprobanteDto>(cobroDto.getListaComprobantes());
			String nombreArchivoOtrosDeb = "Archivo Importacion de Otros Debitos.";
			
			for (ComprobanteDto comprobante : cobroDto.getListaComprobanteOtrosDebito()) {
				if (nombreArchivoOtrosDeb.equals(comprobante.getDescripcionArchivo())){
					comprobantes.add(comprobante);
				}
			}
			
			mv.addObject("listaComprobantes", comprobantes);
			mv.addObject("listaOtrosDebitos", ((ArrayList<CobroOtrosDebitoDto>) Utilidad.guionesNull(new ArrayList<> (cobroDto.getOtrosDebitos()))));
			
			if (Validaciones.isCollectionNotEmpty(cobroDto.getListaCodigoOperacionesExternas())) {
				mv.addObject("listaCodigoOperacionesExterna", ((ArrayList<CodigoOperacionExternaDto>) Utilidad.guionesNull(new ArrayList<>(cobroDto.getListaCodigoOperacionesExternas()))));
			}
			mv.addObject("idReferencia", cobroDto.getTratamientoDiferencia().getReferencia());
			mv.addObject("listaComprobanteAplicacionManual", cobroDto.getListaComprobanteAplicacionManual());

			mv.addObject("prevSumDebitos_p4", cobroDto.getMonedaOperacion() + Utilidad.formatCurrency(cobroDto.getSumaImpDebitos().add(cobroDto.getSumaImpOtrosDebitos()), false, true));
			mv.addObject("prevSumCreditos_p4", cobroDto.getMonedaOperacion() + Utilidad.formatCurrency(cobroDto.getSumaImpCreditos().add(cobroDto.getSumaImpMediosDePago()), false, true));
			mv.addObject("prevTotal_p4", cobroDto.getMonedaOperacion() + Utilidad.formatCurrency(cobroDto.getSumaImpDebitos().add(cobroDto.getSumaImpOtrosDebitos()).subtract(cobroDto.getSumaImpCreditos().add(cobroDto.getSumaImpMediosDePago())), false, true));
			
			mv.addObject("prevSumInteresesTraslados", "$0,00");
			mv.addObject("prevSumInteresesBonificados", "$0,00");
			mv.addObject("prevSumInteresesReintegro", "$0,00");
			mv.addObject("prevSumInteresesTrasladosDolares", "U$S0,00");
			mv.addObject("prevSumInteresesBonificadosDolares", "U$S0,00");
			mv.addObject("prevSumInteresesReintegroDolares", "U$S0,00");
			
			
			
			if(!Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia())){
				if (!Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {
					mv.addObject("selectEnvio", TipoTratamientoDiferenciaEnum.getEnumByName(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia()).getDescripcion());
				} else {
					mv.addObject("selectEnvio", null);
				}
				
				mv.addObject("tramReintInput", cobroDto.getTratamientoDiferencia().getNumeroTramiteReintegro());
				if(!Validaciones.isNullOrEmpty(cobroDto.getTratamientoDiferencia().getFechaAltaTramiteReintegro())){
					String[] split = (cobroDto.getTratamientoDiferencia().getFechaAltaTramiteReintegro()).split(" ");
					mv.addObject("fechaAltaTramReint", split[0]);
				}
				if(!Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia().getSistemaDestino())){
					mv.addObject("selectSistDestino", SistemaEnum.getEnumByDescripcionCorta(cobroDto.getTratamientoDiferencia().getSistemaDestino()).getDescripcion());
				} else {
					mv.addObject("selectSistDestino", null);
				}
				mv.addObject("lineaInput", cobroDto.getTratamientoDiferencia().getLinea());
				mv.addObject("acuerdoFactInput", cobroDto.getTratamientoDiferencia().getAcuerdoFacturacion());
				mv.addObject("estadoAcuerdoFaturacion", cobroDto.getTratamientoDiferencia().getEstadoAcuerdoFacturacion());
				
			}
			String observacionAnterior = cobroOnlineServicio.obtenerObservacionHistorialYObservacionTarea(cobroDto);
			if (!Validaciones.isNullOrEmpty(observacionAnterior)) {
				observacionAnterior = Utilidad.formateadoVista(
						observacionAnterior
				);
			}
			mv.addObject("idOperacionMasiva", cobroDto.getIdOperacionMasiva());
			mv.addObject("nombreArchivo", request.getParameter("nombreArchivo"));

			//Brian Botones volver
			if(!Validaciones.isNullOrEmpty(request.getParameter("goBack"))) {
				userSesion.getRetorno().remove(0);
				mv.addObject("idVolver", userSesion.getRetorno().get(0));
			}else if (!Validaciones.isNullOrEmpty(request.getParameter("volver"))){
				if(!userSesion.getRetorno().get(0).equals(request.getParameter("volver"))) {
					userSesion.getRetorno().add(0, request.getParameter("volver"));
					mv.addObject("idVolver", request.getParameter("volver"));	
				}else {
					mv.addObject("idVolver", request.getParameter("volver"));
				}
			}
			
			if("A".equals(request.getParameter("opcion"))){
				mv.addObject("esPantallaDetalle", false);
				mv.addObject("detalleDoAprobacionA", "A");
			}else {
				mv.addObject("detalleDoAprobacionA", "D");
				mv.addObject("esPantallaDetalle", true);
			}
			
			mv.addObject("prevObservTextAnterior", observacionAnterior);
			mv.addObject("prevObservText2", cobroDto.getObservacionFormateado());
			if (!Validaciones.isNullEmptyOrDash(observacionAnterior)) {
				mv.addObject("mostrarObservTextAnterior", true);
			}
			mv.addObject("estadoCobro", cobroJsonResponse.getEstado().getEstadoDescripcion());
			mv.addObject("marcaEstado", cobroJsonResponse.getEstado().getMarcaDescripcion());
			mv.addObject("monedaOperacion", cobroDto.getMonedaOperacion());
		
			mv.addObject("mostrarBtnCartaYResumen", cobroOnlineServicio.mostrarBtnGenerarCartayResumen(cobroDto));
			mv.addObject("mostrarBtnResumen", cobroOnlineServicio.mostrarBtnGenerarResumen(cobroDto));
			
		}
		
		return mv;
	}
	
	/**
	 * Proyecto Fusión
	 * 
	 * Metodo que carga la pantalla de cobro-confirmar-aplicacion-manual
	 * Pasar por parametro a traves del request el "idCobro"
	 * 
	 * @author u587496 Guido.Settecerze
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/cobro-confirmar-aplicacion-manual")
	public ModelAndView cobroManualAprobacion(HttpServletRequest request) throws Exception {
		
		ModelAndView mv=new ModelAndView(MANUAL_APROBACION_VIEW);
		CobroDto cobroDto = new CobroDto();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);

		if(!Validaciones.isNullOrEmpty(request.getParameter("idOperacionRelacionada"))) {
			cobroDto = cobroOnlineServicio.buscarCobroPorIdOperacion(Utilidad.stringToBigDecimal(request.getParameter("idOperacionRelacionada")).longValue());
			mv.addObject("volviendoA", request.getParameter("volver"));
		}else if(!Validaciones.isNullOrEmpty(request.getParameter("idCobro"))){
			cobroDto = cobroOnlineServicio.buscarCobro(Utilidad.stringToBigDecimal(request.getParameter("idCobro")).longValue());
		}

		
//		Logica para la navegacion desde Legajos
		mv.addObject("idLegajo", request.getParameter("idLeg"));
		mv.addObject("solapa", request.getParameter("solapa"));
		mv.addObject("idOperacionRelacionada", request.getParameter("idOperacionRelacionada"));
		mv.addObject("sistema", request.getParameter("sistema"));
		mv.addObject("sociedad", request.getParameter("sociedad"));
		mv.addObject("idDescobro", request.getParameter("idDescobro"));
		mv.addObject("idCobroFormatiado", cobroDto.getIdCobro());
		mv.addObject("idCobro", cobroDto.getIdCobro());
		mv.addObject("idCobroPadre", cobroDto.getIdCobroPadre());
		
		if (Validaciones.isCollectionNotEmpty(cobroDto.getListaCodigoOperacionesExternas())) {
			mv.addObject("listaCodigoOperacionesExterna", ((ArrayList<CodigoOperacionExternaDto>) Utilidad.guionesNull(new ArrayList<>(cobroDto.getListaCodigoOperacionesExternas()))));
		}

		if(!Validaciones.isObjectNull(cobroDto)){
			
			if(!Validaciones.isNullOrEmpty(request.getParameter("vuelvoBandeja"))){
				mv.addObject("vuelvoBandeja", request.getParameter("vuelvoBandeja"));
			}
			String volverAPantalla = request.getParameter("volverAPantalla");
			if(!Validaciones.isNullEmptyOrDash(volverAPantalla)
					&& Constantes.DESTINO_BUSQUEDA_COBRO.equals(volverAPantalla)){
				mv.addObject("volverAPantalla", volverAPantalla);
			}
			String idCobro = request.getParameter("idCobro");
			if(!Validaciones.isObjectNull(cobroDto.getDebitos())) {
				for (CobroDebitoDto debito : cobroDto.getDebitos()) {
					// Completo los atributos 'opeAsocAnalista' y 'marcaPagoCompensacionEnProcesoShiva
					cobroOnlineServicio.setearAtributosPorConsulta(debito, idCobro);
				}
			}
			if(!Validaciones.isObjectNull(cobroDto.getCreditos())) {
				for (CobroCreditoDto credito : cobroDto.getCreditos()) {
					// Completo los atributos 'opeAsocAnalista' y 'marcaPagoCompensacionEnProcesoShiva
					cobroOnlineServicio.setearAtributosPorConsulta(credito, idCobro);
				}
			}
			CobroJsonResponse cobroJsonResponse = cobroOnlineServicio.obtenerEstados(
				cobroDto.getIdCobro(),
				true,
				"",
				"|"
			);
			
			//formatea el importe a cobrar a $99.99..
			cobroDto.setDebitos(cobroOnlineServicio.formatearImporteACobrar(cobroDto.getDebitos()));
			cobroDto.setCreditos(cobroOnlineServicio.formatearImportesCredito(cobroDto.getCreditos()));
			
			//formatea el reclamo y migrado en origen: si esta en NULL lo pasa a NO.
			cobroDto.setDebitos(cobroOnlineServicio.formatearReclamoYMigradoEnOrigen(cobroDto.getDebitos()));
			
			// Ordeno los documentos caps y seteo los colores de renglon
			cobroOnlineServicio.prepararDocumentosCapParaSuVisualizacion(cobroDto);
			
			mv.addObject("controlPaginacion", cobroDto.getControlPaginacion());
			
			mv.addObject("prevEmpresa", cobroDto.getEmpresa());
			mv.addObject("prevSegmento", cobroDto.getSegmento());
			mv.addObject("prevMonedaOperacion", MonedaEnum.getEnumBySigno2(cobroDto.getMonedaOperacion()).getDescripcion());
			mv.addObject("nombreCompletoUsuario", cobroDto.getNombreAnalista());//usar ldap traer nombre completo (cobroDto.getIdAnalista()) mapeador
			mv.addObject("prevCopropietario", cobroDto.getNombreCopropietario());//usar ldap traer nombre completo (ver de hacer en el mapeador y crear campo nuevo en dto de tipo usuario).(cobroDto.getIdCopropietario())
			mv.addObject("prevMotivo", cobroDto.getDescripcionMotivoCobro());//crear un motivoCobroServicio para buscar el objeto motivo qe me da la descripcion usando metodo del dao MotivoCobroDao.
			mv.addObject("listaClientes", ((ArrayList<ClienteDto>) Utilidad.guionesNull(new ArrayList<>(cobroDto.getClientes()))));
			mv.addObject("listaDebitos", ((ArrayList<CobroDebitoDto>) Utilidad.guionesNull(new ArrayList<>(cobroDto.getDebitos()))));
			mv.addObject("idMotivoCobro", cobroDto.getIdMotivoCobro());
			mv.addObject("idOperacion", cobroDto.getIdOperacionFormateado());
			mv.addObject("listaCreditos", ((ArrayList<CobroCreditoDto>) Utilidad.guionesNull(new ArrayList<>(cobroDto.getCreditos()))));
			mv.addObject("listaMediosPagos", ((ArrayList<CobroMedioDePagoDto>) Utilidad.guionesNull(new ArrayList<>(cobroDto.getMedios()))));
			
			/*Agrego los comprobantes de importación de otros débitos a los comprobantes de cobro*/
			List<ComprobanteDto> comprobantes = new ArrayList<ComprobanteDto>(cobroDto.getListaComprobantes());
			String nombreArchivoOtrosDeb = "Archivo Importacion de Otros Debitos.";
			
			for (ComprobanteDto comprobante : cobroDto.getListaComprobanteOtrosDebito()) {
				if (nombreArchivoOtrosDeb.equals(comprobante.getDescripcionArchivo())){
					comprobantes.add(comprobante);
				}
			}
			
			mv.addObject("listaComprobantes", comprobantes);
			mv.addObject("listaComprobanteAplicacionManual", cobroDto.getListaComprobanteAplicacionManual());
			mv.addObject("listaOtrosDebitos", ((ArrayList<CobroOtrosDebitoDto>) Utilidad.guionesNull(new ArrayList<> (cobroDto.getOtrosDebitos()))));

			mv.addObject("prevSumDebitos_p4", cobroDto.getMonedaOperacion() + Utilidad.formatCurrency(cobroDto.getSumaImpDebitos().add(cobroDto.getSumaImpOtrosDebitos()), false, true));
			mv.addObject("prevSumCreditos_p4", cobroDto.getMonedaOperacion() + Utilidad.formatCurrency(cobroDto.getSumaImpCreditos().add(cobroDto.getSumaImpMediosDePago()), false, true));
			mv.addObject("prevTotal_p4", cobroDto.getMonedaOperacion() + Utilidad.formatCurrency(cobroDto.getSumaImpDebitos().add(cobroDto.getSumaImpOtrosDebitos()).subtract(cobroDto.getSumaImpCreditos().add(cobroDto.getSumaImpMediosDePago())), false, true));
			
			mv.addObject("prevSumInteresesTraslados", "$0,00");
			mv.addObject("prevSumInteresesBonificados", "$0,00");
			mv.addObject("prevSumInteresesReintegro", "$0,00");
			mv.addObject("prevSumInteresesTrasladosDolares", "U$S0,00");
			mv.addObject("prevSumInteresesBonificadosDolares", "U$S0,00");
			mv.addObject("prevSumInteresesReintegroDolares", "U$S0,00");
			
			
			
			if(!Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia())){
				if (!Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {
					mv.addObject("selectEnvio", TipoTratamientoDiferenciaEnum.getEnumByName(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia()).getDescripcion());
				} else {
					mv.addObject("selectEnvio", null);
				}
				
				if(!Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia().getSistemaDestino())){
					mv.addObject("selectSistDestino", SistemaEnum.getEnumByDescripcionCorta(cobroDto.getTratamientoDiferencia().getSistemaDestino()).getDescripcion());
				} else {
					mv.addObject("selectSistDestino", null);
				}
				mv.addObject("idReferencia", cobroDto.getTratamientoDiferencia().getReferencia());
			}
			
			String observacionAnterior = cobroOnlineServicio.obtenerObseHistorial(cobroDto, null);
			if (!Validaciones.isNullOrEmpty(observacionAnterior)) {
				observacionAnterior = Utilidad.formateadoVista(
						observacionAnterior
				);
			}
			mv.addObject("idOperacionMasiva", cobroDto.getIdOperacionMasiva());
			mv.addObject("nombreArchivo", request.getParameter("nombreArchivo"));

			//Brian Botones volver
			if(!Validaciones.isNullOrEmpty(request.getParameter("goBack"))) {
				userSesion.getRetorno().remove(0);
				mv.addObject("idVolver", userSesion.getRetorno().get(0));
			}else if (!Validaciones.isNullOrEmpty(request.getParameter("volver"))){
				if(!userSesion.getRetorno().get(0).equals(request.getParameter("volver"))) {
					userSesion.getRetorno().add(0, request.getParameter("volver"));
					mv.addObject("idVolver", request.getParameter("volver"));	
				}else {
					mv.addObject("idVolver", request.getParameter("volver"));
				}
			}
			
			if("A".equals(request.getParameter("opcion"))){
				mv.addObject("esPantallaDetalle", false);
				mv.addObject("detalleDoAprobacionA", "A");
			}else {
				mv.addObject("detalleDoAprobacionA", "D");
				mv.addObject("esPantallaDetalle", true);
			}
//			mv.addObject("prevObservTextAnterior", observacionAnterior);
			mv.addObject("prevObservText2", cobroDto.getObservacionFormateado());
//			if (!Validaciones.isNullEmptyOrDash(observacionAnterior)) {
//				mv.addObject("mostrarObservTextAnterior", true);
//			}
			mv.addObject("estadoCobro", cobroJsonResponse.getEstado().getEstadoDescripcion());
			mv.addObject("marcaEstado", cobroJsonResponse.getEstado().getMarcaDescripcion());
			mv.addObject("monedaOperacion", cobroDto.getMonedaOperacion());
		
			mv.addObject("mostrarBtnCartaYResumen", cobroOnlineServicio.mostrarBtnGenerarCartayResumen(cobroDto));
			mv.addObject("mostrarBtnResumen", cobroOnlineServicio.mostrarBtnGenerarResumen(cobroDto));
			
		}
		return mv;
	}

	
	@RequestMapping("/cobro-informar-desapropiacion-aplicacion-manual")
	public ModelAndView cobroInformarDesapropiacionManualAprobacion(HttpServletRequest request) throws Exception {
		ModelAndView mv = this.cobroManualAprobacion(request);

		mv.addObject("idTarea", request.getParameter("idTarea"));
		
		mv.addObject("tipoTarea", request.getParameter("tipoTarea"));
		
		mv.addObject("tipoTareaName", TipoTareaEnum.COB_DESAPRO_APLI_MANUAL.name());
		
		return mv;
	}
	
	/**
	 * Exportacion del id cobro al excel
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobroDto
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("cobro-detalle-aprobacion/exportacionDetalleCobro")
	public ModelAndView exportacionDetalleCobro(HttpServletRequest request, HttpServletResponse response, CobroDto cobroDto) throws Exception {
		if(cobroDto.getIdCobroFormatiadoJSPDetalle()!=null){
			cobroOnlineServicio.exportarCobro(response, Utilidad.stringToBigDecimal(cobroDto.getIdCobroFormatiadoJSPDetalle()).longValue());
		}else{
			cobroOnlineServicio.exportarCobro(response, cobroDto.getIdCobro());
		}
		if(!Validaciones.isNullOrEmpty(request.getParameter("opcion"))){
			return cobroDetalleAprobacion(request);
		}else{
			return null;
		}
		
	}
	
	
	/**
	 * Pasar por parametro a traves del request el "idCobro" y "opcion", podiendo ser D = Detalle, A= Aprobacion
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("cobro-detalle-aprobacion/cobro-detalle")
	public ModelAndView cobroDetalle(HttpServletRequest request) throws Exception {
		
		ModelAndView cobroDetalleAprobacion = cobroDetalleAprobacion(request);
		
		return cobroDetalleAprobacion;
		
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="cobro-detalle-aprobacion/descargarComprobante")
	public void cobroDetalleDescargarComprobante(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		ComprobanteDto comprobante = cobroOnlineServicio.buscarAdjuntoCobro(Long.valueOf(id));
		
		if (comprobante!=null) {
			byte[] file = comprobante.getDocumento();
			String fileName = comprobante.getNombreArchivo();
			ControlArchivo.descargarComoArchivo(file, fileName, response);
		}
	}
	
	/**
	 * Mar
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "cobro-detalle-aprobacion/descargarAdjunto")
	public void otrosDebitosDescargarAdjunto(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		CobroOtrosDebitoDto adjunto = cobroOnlineServicio.buscarAdjunto(Long.valueOf(id));
		
		if(adjunto != null){
			byte[] file = adjunto.getAdjunto();
			String fileName = adjunto.getNombreAdjunto();
			ControlArchivo.descargarComoArchivo(file, fileName, response);
		}
	}
	
	/********************************************************************************************************
	 * Via AJAX
	 ********************************************************************************************************/
	/**
	 * Guardo un cobro
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/guardarConfCobro", method=RequestMethod.POST)
	public @ResponseBody CobroJsonResponse guardarConfCobro(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		CobroJsonResponse cobroJsonResponse = new CobroJsonResponse();
		Date fecha = new Date();
		cobro.setFechaUltimaModificacion(fecha);
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		cobro.setUsuarioModificacion(Utilidad.getUsuarioSesion(request));
		cobro.setUsuarioModificacion(userSesion.getUsuario());
		
		if (
			Validaciones.isObjectNull(cobro.getIdCobro()) &&
			Validaciones.isObjectNull(cobro.getIdOperacion())
		) {
			cobro.setOperation(ConstantesCobro.NUEVO_COBRO);
			cobro.setIdAnalista(Utilidad.getUsuarioSesion(request));
			cobro.setFechaAlta(fecha);
			cobro.setUsuarioAlta(Utilidad.getUsuarioSesion(request));

			if (!Validaciones.isObjectNull(cobro.getClientes()) && !cobro.getClientes().isEmpty()) {
				UsuarioLdapDto usuarioAnalistaTeamComercial = teamComercialServicio.obtenerAnalistaTeamComercial(cobro.getClientes());
				if (!Validaciones.isObjectNull(usuarioAnalistaTeamComercial)) {
					cobro.setNombreAnalistaTeamComercial(usuarioAnalistaTeamComercial.getNombreCompleto());
					cobro.setIdAnalistaTeamComercial(usuarioAnalistaTeamComercial.getTuid());
				}
			} else {
				cobro.setNombreAnalistaTeamComercial(Utilidad.EMPTY_STRING);
				cobro.setIdAnalistaTeamComercial(null);
			}	
			//aca guarda el cobro
			Long idCobroNuevo = cobroOnlineServicio.crear(cobro);
			CobroDto cobroResultado = cobroOnlineServicio.buscarCobro(idCobroNuevo);

			cobroJsonResponse.setPrimerCobro(true);
			cobroJsonResponse.setIdCobro(cobroResultado.getIdCobro());
			cobroJsonResponse.setIdCobroPadre(cobroResultado.getIdCobroPadre());
			cobroJsonResponse.setIdOperacion(cobroResultado.getIdOperacion());
			cobroJsonResponse.setInformacionMensaje(cobroResultado.getEstado().descripcion());
			cobroJsonResponse.setSuccess(true);
			
			if ("true".equalsIgnoreCase(cobro.getVengoReedicion())) {
				cobro.setIdCobro(cobroResultado.getIdCobro());
				cobro.setIdOperacion(cobroResultado.getIdOperacion());
			}
		}
		if (
				!Validaciones.isObjectNull(cobro.getIdCobro()) && 
				!Validaciones.isObjectNull(cobro.getIdOperacion())
		) {
			CobroDto cobroResultado = cobroOnlineServicio.buscarCobro(cobro.getIdCobro());
	
			// Seteo los datos que se modifican en la vista
			cobroResultado.setFechaUltimaModificacion(fecha);
			cobroResultado.setUsuarioModificacion(Utilidad.getUsuarioSesion(request));
			cobroResultado.setMonedaOperacion(cobro.getMonedaOperacion());
			cobroResultado.setIdEmpresa(cobro.getIdEmpresa());
			cobroResultado.setIdSegmento(cobro.getIdSegmento());
			cobroResultado.setIdMotivoCobro(cobro.getIdMotivoCobro());
			cobroResultado.setBtnGuardar(cobro.isBtnGuardar());
			if (cobroOnlineServicio.esPersistirObservacionEnEstado(cobroResultado.getEstado())) {
				if (cobro.isBtnGuardar()) {
					cobroResultado.setObservacion(cobro.getObservacion());
				}
			} else {
				cobroResultado.setObservacion(cobro.getObservacion());
			}
				// Si el llamado es atraves del cambio de solapa y si la solapa destino es 5
				// y si el tipo de tratamiento actual es Aplciacion manual
				// y si el tratamiento modificaco es diferencte de apliacion manula
				// Elimino los comprobantes

			if (
				!Validaciones.isNullEmptyOrDash(cobro.getIdAdjuntoApliacionManualPrev())
				) {
					if (
						!Validaciones.isObjectNull(cobroResultado.getTratamientoDiferencia()) &&
						TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.name().equals(cobroResultado.getTratamientoDiferencia().getTipoTratamientoDiferencia())
					) {
						if (
							Validaciones.isObjectNull(cobro.getTratamientoDiferencia()) ||
							(
								!Validaciones.isObjectNull(cobro.getTratamientoDiferencia()) &&
								!TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.name().equals(cobro.getTratamientoDiferencia().getTipoTratamientoDiferencia())
							)
						) {
							//cobroOnlineServicio.eliminarAdjuntoCobro(cobro.getListaComprobanteAplicacionManual().get(0).getIdComprobante());
							//cobroJsonResponse.setEliminarComprobanteAplicacionManualPrev(true);
							cobroResultado.setBorrarAdjuntoAplicacionManal(true);
							cobroResultado.setIdAdjuntoApliacionManualPrev(cobro.getIdAdjuntoApliacionManualPrev());
							cobroJsonResponse.setEliminarComprobanteAplicacionManualPrev(true);
						}
					}
				}
				
				if (!cobro.getListaIdsAdjuntosOtrosDebitos().isEmpty()) {
					cobroResultado.setEliminarTodosAdjuntosOtrosDeb(true);
					cobroResultado.setListaIdsAdjuntosOtrosDebitos(cobro.getListaIdsAdjuntosOtrosDebitos());
				}
				
				cobroResultado.setIdAnalista(cobro.getIdAnalista());
				cobroResultado.setIdCopropietario(cobro.getIdCopropietario());
				cobroResultado.setTratamientoDiferencia(cobro.getTratamientoDiferencia());
				
				Set<CobroDebitoDto> debitos = null;
				if ("true".equalsIgnoreCase(cobro.getVengoReedicion())) {
					cobroResultado.setDebitos(cobro.getDebitos());
				} else {
					debitos = actualizacionListaDebitosConBaseDatos(cobro.getDebitos(),cobroResultado.getDebitos());
					completarCamposDebitoDto(cobroResultado,  cobro.getIdCobro().toString());
					cobroResultado.setDebitos(debitos);
				}

				cobroResultado.setOtrosDebitos(cobro.getOtrosDebitos());
				cobroResultado.setCreditos(cobro.getCreditos());
				cobroResultado.setMedios(cobro.getMedios());
				cobroResultado.setFechaTipoCambio(cobro.getFechaTipoCambio());
				cobroResultado.setTipoCambio(cobro.getTipoCambio());

				//System.out.println(!(cobro.getListaIdClientesLegadoRazonSocialSolo()).equalsIgnoreCase(cobroResultado.getListaIdClientesLegadoRazonSocialSolo()));
				if (!(cobro.getListaIdClientesLegado()).equalsIgnoreCase(cobroResultado.getListaIdClientesLegado())) {
					UsuarioLdapDto usuarioAnalistaTeamComercial = teamComercialServicio.obtenerAnalistaTeamComercial(cobro.getClientes());
					if (!Validaciones.isObjectNull(usuarioAnalistaTeamComercial)) {
						cobroResultado.setNombreAnalistaTeamComercial(usuarioAnalistaTeamComercial.getNombreCompleto());
						//cobroResultado.setMailAnalistaTeamComercial(usuarioAnalistaTeamComercial.getMail());
						cobroResultado.setIdAnalistaTeamComercial(usuarioAnalistaTeamComercial.getTuid());
						//if (!ConstantesCobro.GRUPO_TEAM_COMERCIAL.equals(cobroResultado.getNombreAnalistaTeamComercial())) {
						//	cobroResultado.setUrlFotoAnalistaTeamComercial(cobroResultado.urlFotoUsuario(cobroResultado.getNombreAnalistaTeamComercial()));
						//}
					
					} else  {
						cobroResultado.setNombreAnalistaTeamComercial(Utilidad.EMPTY_STRING);
						cobroResultado.setIdAnalistaTeamComercial(Utilidad.EMPTY_STRING);
					}
				}
				if (ConstantesCobro.GRUPO_TEAM_COMERCIAL.equalsIgnoreCase(cobroResultado.getNombreAnalistaTeamComercial())){
					cobroResultado.setIdAnalistaTeamComercial(Utilidad.EMPTY_STRING);
				}
				cobroResultado.setClientes(cobro.getClientes());
			
				if (!esReeditarCobroNoBtnGuardar(cobroResultado)) {
	
					cobroOnlineServicio.modificar(cobroResultado);
				}
				
				if ("true".equalsIgnoreCase(cobro.getVengoReedicion())) {
					cobroOnlineServicio.modificar(cobroResultado);
					cobroJsonResponse.setVengoReedicion(true);
					cobroJsonResponse.setPrimerCobro(true);
					
					cobroOnlineServicio.eliminarTarea(cobroResultado.getIdCobroPadre(), cobro.getIdTarea(), cobroResultado.getUsuarioModificacion());
				}
				cobroJsonResponse.setIdCobro(cobroResultado.getIdCobro());
				cobroJsonResponse.setIdOperacion(cobroResultado.getIdOperacion());
				cobroJsonResponse.setInformacionMensaje(cobroResultado.getEstado().name());
				cobroJsonResponse.setDebitos(debitos);
				cobroJsonResponse.setPrimerCobro(false);
			
			cobroJsonResponse.setSuccess(true);
		}
		
		if (cobroJsonResponse.isSuccess()) {
			CobroJsonResponse rta = cobroOnlineServicio.obtenerEstados(
				cobroJsonResponse.getIdCobro(),
				false,
				"",
				"|"
			);
			cobroJsonResponse.getEstado().setEstadoDescripcion(rta.getEstado().getEstadoDescripcion());
			cobroJsonResponse.getEstado().setMarcaDescripcion(rta.getEstado().getMarcaDescripcion());
//			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			if (Validaciones.isObjectNull(cobro.getIdCobro())) {
				cobro.setIdCobro(cobroJsonResponse.getIdCobro()); 
			}
			cobroJsonResponse.setEdicionSegunEstadoMarca(
				cobroOnlineServicio.validarEdicionSegunEstadoMarca(
					cobro,
					userSesion
			));
			cobroJsonResponse.setEsPerfilSupervisorCobranza(
				userSesion.getEsPerfilSupervisorCobranza()
			);
			
			if (cobroOnlineServicio.esPersistirObservacionEnEstado(rta.getEstado().getEstadoDescripcion())) {
				cobroJsonResponse.setObservacion(Utilidad.EMPTY_STRING);
				cobroJsonResponse.setObservacionAnterior(Utilidad.formateadoVista(cobroOnlineServicio.obtenerObseHistorial(cobro, null)));
			}
		}
		return cobroJsonResponse;
	}
	private boolean esReeditarCobro(CobroDto cobro, UsuarioSesion userSesion) {
		return (
			cobro.isBtnGuardar() &&
				(
					Estado.COB_ERROR_COBRO.equals(cobro.getEstado()) ||
					Estado.COB_ERROR_APROPIACION.equals(cobro.getEstado()) ||
					Estado.COB_ERROR_CONFIRMACION_PARCIAL.equals(cobro.getEstado())
				) &&
			userSesion.esPerfilAnalistaCobranza()
		);
				
	}
	private boolean esReeditarCobroNoBtnGuardar(CobroDto cobro) {
		return (
			Estado.COB_ERROR_COBRO.equals(cobro.getEstado()) ||
			Estado.COB_ERROR_APROPIACION.equals(cobro.getEstado())
		);
				
	}
	private Set<CobroDebitoDto> actualizacionListaDebitosConBaseDatos(Set<CobroDebitoDto> debitosOnline, Set<CobroDebitoDto> debitosBaseDeDatos) {
		Set<CobroDebitoDto> debitosFinal = new HashSet<CobroDebitoDto>();
		for (CobroDebitoDto debitoOnline : debitosOnline) {
			
			if(OrigenDebitoEnum.IMPORTACION.equals(debitoOnline.getOrigen()) &&  EstadoDebitoImportadoEnum.PENDIENTE.equals(debitoOnline.getResultadoValidacionEstado())){
				CobroDebitoDto debitoAux = null;
				for (CobroDebitoDto debitoBaseDatos : debitosBaseDeDatos) {
					
					if(compararDebitos(debitoOnline, debitoBaseDatos)){
						debitoAux=debitoBaseDatos;
						break;
					}
				}
				if(!Validaciones.isObjectNull(debitoAux)){
					debitosFinal.add(debitoAux);
				} else {
					debitosFinal.add(debitoOnline);
				}
				
			} else {
				debitosFinal.add(debitoOnline);
			}
		}
		return debitosFinal;
	}
	
	private boolean compararDebitos(CobroDebitoDto debitoOnline, CobroDebitoDto debitoBaseDatos) {

		if (!Validaciones.isObjectNull(debitoOnline.getNumeroReferenciaMic()) && !debitoOnline.getNumeroReferenciaMic().equals("-")) {
			if (!debitoOnline.getNumeroReferenciaMic().equals(debitoBaseDatos.getNumeroReferenciaMic())) {
				return false;
			} 
		} 

		if (!Validaciones.isObjectNull(debitoOnline.getTipoComprobanteEnum())) {
			if (!debitoOnline.getTipoComprobanteEnum().equals(debitoBaseDatos.getTipoComprobanteEnum())) {
				return false;
			} 
		} 

		if (!Validaciones.isObjectNull(debitoOnline.getClaseComprobante())) {
			if (!debitoOnline.getClaseComprobante().equals(debitoBaseDatos.getClaseComprobante())) {
				return false;
			} 
		} 

		if (!Validaciones.isObjectNull(debitoOnline.getSucursalComprobante())) {
			if (!debitoOnline.getSucursalComprobante().equals(debitoBaseDatos.getSucursalComprobante())) {
				return false;
			} 
		} 

		if (!Validaciones.isObjectNull(debitoOnline.getNumeroComprobante())) {
			if (!debitoOnline.getNumeroComprobante().equals(debitoBaseDatos.getNumeroComprobante())) {
				return false;
			}
		}

		return true;
	}


	/**
	 * Guardo un cobro
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/modificarTransaccionesConIntereses", method=RequestMethod.POST)
	public @ResponseBody CobroJsonResponse modificarTransaccionesConIntereses(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		CobroJsonResponse cobroJsonResponse = new CobroJsonResponse();
		Date fecha = new Date();
		cobro.setFechaUltimaModificacion(fecha);
		cobro.setUsuarioModificacion(Utilidad.getUsuarioSesion(request));
		
		cobroJsonResponse.setIdCobro(cobro.getIdCobro());
		cobroJsonResponse.setIdOperacion(cobro.getIdOperacion());
		
		if (!Validaciones.isObjectNull(cobro.getIdCobro())) {
	
			cobroOnlineServicio.modificarTransaccionesConIntereses(cobro);
			cobroJsonResponse.setSuccess(true);
		} else {
			//Notificar el error
			cobroJsonResponse.setSuccess(false);
		}
		
		return cobroJsonResponse;
	}
	
	/**
	 * Metodo que trabaja con el cobro ERROR y cobro ERROR en APROPIACION al guardar.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logicaAlGuardarErrorCobroErrorApropiacion")
	public ModelAndView logicaAlGuardarErrorCobroErrorApropiacion(CobroDto cobro, HttpServletRequest request) throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		if (userSesion.esPerfilAnalistaCobranza()) {
			CobroDto cobroDto = cobroOnlineServicio.buscarCobro(cobro.getIdCobro());
			request.setAttribute("idCobro", cobroDto.getIdCobro());

			ModelAndView mv = cobrosConfiguracion(request);
			mv.addObject("idCobro", cobroDto.getIdCobro());
			mv.addObject("cobroEditable", true);
			return mv;

		} else {

			ModelAndView mv = cobrosConfiguracion(request);
			mv.addObject("idCobro", cobro.getIdCobro());
			mv.addObject("cobroEditable", true);
			return mv;
		}
	}
	
	/**
	 * Metodo que valida si se debe habilitar el btn Simular.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/habilitarBtnSimular", method=RequestMethod.POST)
	public @ResponseBody HabilitacionBtnSimularJsonResponse habilitarBtnSimular(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		//Boolean success1 = false;
		Boolean porEstadoSuccess2 = false;
		Boolean tratamientoPosivoSuccess3 = false;	//DEBITO_PROX_FAC
		Boolean tratamientoNegativoSuccess4 = false;
		
		Boolean errorDebitoCreditoSuccess5 = true;
		Boolean grillasVaciasSuccess6 = true; // Validacion de que la lista de creditos, debitos y medio de pagos no sean simulataneamente Vacias.

		Boolean tratamientoCeroSuccess7 = false;
		
		Boolean validarSimulacionBatchEnProceso = true;
		HabilitacionBtnSimularJsonResponse jsonResponse = new HabilitacionBtnSimularJsonResponse();
		
		if (!Validaciones.isObjectNull(cobro) && !Validaciones.isObjectNull(cobro.getIdCobro())) {
			validarSimulacionBatchEnProceso = cobroOnlineServicio.validarSimulacionBatchEnProceso(cobro.getIdCobro());
			if(!validarSimulacionBatchEnProceso){
				CobroDto cobroDto = cobroOnlineServicio.buscarCobro(cobro.getIdCobro());
				if (!Validaciones.isObjectNull(cobroDto)) {
					BigDecimal sumaDebitos = cobroDto.getSumaImpDebitos().add(cobroDto.getSumaImpOtrosDebitos());
					BigDecimal sumaCreditos = cobroDto.getSumaImpCreditos().add(cobroDto.getSumaImpMediosDePago());
					BigDecimal diferenciaTotal = sumaDebitos.subtract(sumaCreditos);

					Set<CobroDebitoDto> debitos = cobroDto.getDebitos();
					Set<CobroCreditoDto> creditos = cobroDto.getCreditos();

					if (
						cobroDto.getDebitos().isEmpty() &&
						cobroDto.getCreditos().isEmpty() &&
						cobroDto.getMedios().isEmpty() &&
						cobroDto.getOtrosDebitos().isEmpty()
					) {
						grillasVaciasSuccess6 = false;
					}

					if (diferenciaTotal.compareTo(BigDecimal.ZERO) > 0) {
						if (TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.getName().equals(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia())
								|| TipoTratamientoDiferenciaEnum.SALDO_A_COBRAR.getName().equals(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia())){
							tratamientoPosivoSuccess3 = true;
						}
					}else{
						tratamientoPosivoSuccess3 = false;
					}

					if ((diferenciaTotal.compareTo(BigDecimal.ZERO) < 0) && !Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia())) {
						if(TipoTratamientoDiferenciaEnum.REINTEGRO_POR_CHEQUE.getName().equals(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia()) 
								|| TipoTratamientoDiferenciaEnum.REINTEGRO_POR_CHEQUE.getName().equals(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia())
								|| TipoTratamientoDiferenciaEnum.REINTEGRO_PAGO_CUENTA_TERCEROS.getName().equals(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia())
								|| TipoTratamientoDiferenciaEnum.REINTEGRO_CREDITO_RED_INTEL.getName().equals(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia())
								|| TipoTratamientoDiferenciaEnum.REINTEGRO_TRANSFERENCIA_BAN.getName().equals(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia())
								|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.getName().equals(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia())
								|| TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS.getName().equals(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia())
								|| TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.getName().equals(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia())
								|| TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.getName().equals(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia())){
							tratamientoNegativoSuccess4 = true;
						}
					} else {
						tratamientoNegativoSuccess4 = false;
					}
					if (
						(diferenciaTotal.compareTo(BigDecimal.ZERO) == 0) && 
						Validaciones.isNullOrEmpty(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia())
					) {
						tratamientoCeroSuccess7 = true;
					} else {
						tratamientoCeroSuccess7 = false;
					}
					List<Estado> estados = Arrays.asList(
						new Estado[] {
							Estado.COB_EN_EDICION,
							Estado.COB_RECHAZADO
						}
					);
					if (estados.contains(cobroDto.getEstado())) {
						porEstadoSuccess2 = true;
					} else {
						porEstadoSuccess2 = false;
					}
					if (Validaciones.isCollectionNotEmpty(debitos)) {
						for (CobroDebitoDto cobroDebitoDto : debitos) {
							if(EstadoDebitoImportadoEnum.VALIDACION_ERROR.equals(cobroDebitoDto.getResultadoValidacionEstado())){
								errorDebitoCreditoSuccess5 = false;
							}
						}
					}
					if (Validaciones.isCollectionNotEmpty(creditos)) {
						for (CobroCreditoDto cobroCreditoDto : creditos) {
							if(EstadoDebitoImportadoEnum.VALIDACION_ERROR.equals(cobroCreditoDto.getResultadoValidacionEstado())){
								errorDebitoCreditoSuccess5 = false;
							}
						}
					}
				}
			}
			
		}

		// Success6 => es true si las grillas no estan vacias
		// Success2 
		if (
			(tratamientoPosivoSuccess3 || tratamientoNegativoSuccess4 || tratamientoCeroSuccess7) && 
			errorDebitoCreditoSuccess5 && porEstadoSuccess2 && grillasVaciasSuccess6
		) {
			jsonResponse.setSuccess(true);
		} else {
			jsonResponse.setSuccess(false);
		}
		
		if (!errorDebitoCreditoSuccess5) {
			jsonResponse.setCausa(TipoSimularDisabled.ERROR);
		} else if (!(tratamientoPosivoSuccess3 || tratamientoNegativoSuccess4 || tratamientoCeroSuccess7)) {
			jsonResponse.setCausa(TipoSimularDisabled.TRATAMIENTO);
		}
		return jsonResponse;
	}
	
	/**
	 * Metodo que valida si se debe habilitar el btn Imputar.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/habilitarBtnImputarYCargarGrillaTransacciones", method=RequestMethod.POST)
	public @ResponseBody TransaccionesJsonResponse habilitarBtnImputarYCargarGrillaTransacciones(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		TransaccionesJsonResponse transaccionesJsonResponse = new TransaccionesJsonResponse();
		
		boolean validarSimulacionBatchEnProceso = true;

		validarSimulacionBatchEnProceso = cobroOnlineServicio.validarSimulacionBatchEnProceso(cobro.getIdCobro());
		
		transaccionesJsonResponse = cobroOnlineServicio.buscarTransacciones(cobro.getIdCobro(), validarSimulacionBatchEnProceso);
		// No se utiliza la descripcion de error provista por cobroOnlineServicio.buscarTransacciones
		transaccionesJsonResponse.setDescripcionError(Utilidad.EMPTY_STRING);	
		if(!validarSimulacionBatchEnProceso){
			transaccionesJsonResponse.setSimulacionBatchOK(transaccionesJsonResponse.getTransaccionesOK());
			transaccionesJsonResponse.setSimulacionEnBatch(false);
		}else{
			transaccionesJsonResponse.setSimulacionEnBatch(true);
		}
		transaccionesJsonResponse.setImputable(cobroOnlineServicio.validarEsImputable(cobro.getIdCobro()));
		List<Estado> estados = Arrays.asList(
			new Estado[] {
				Estado.COB_EN_EDICION,
				Estado.COB_RECHAZADO
			}
		);
		CobroDto cobroDto = cobroOnlineServicio.buscarCobro(cobro.getIdCobro());
		transaccionesJsonResponse.setSimulablePorEstado(estados.contains(cobroDto.getEstado()));
		
		CobroJsonResponse rta = cobroOnlineServicio.obtenerEstados(cobro.getIdCobro(), true, "", "|");
		transaccionesJsonResponse.getEstado().setEstadoDescripcion(rta.getEstado().getEstadoDescripcion());
		transaccionesJsonResponse.getEstado().setMarcaDescripcion(rta.getEstado().getMarcaDescripcion());
		// Si esta la marca SIMULACION_BATCH_EN_PROCESO. No se chequea el checkSum del cobro dado
		// que la simulacion no se realizo.
		if (
			transaccionesJsonResponse.isImputable() && 
			!MarcaEnum.SIMULACION_BATCH_EN_PROCESO.descripcion().equals(rta.getEstado().getMarcaDescripcion())
		) {
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			if (!cobroOnlineServicio.validarSimulacion(cobro.getIdCobro(), userSesion.getIdUsuario())) {
				// Valido si la simulacion corresponde a los valores actuales del cobro
				transaccionesJsonResponse.setSuccess(false);
				transaccionesJsonResponse.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.error.simulacion.no.validad"));
				rta = cobroOnlineServicio.obtenerEstados(cobro.getIdCobro(), true, "", "|");
				transaccionesJsonResponse.getEstado().setEstadoDescripcion(rta.getEstado().getEstadoDescripcion());
				transaccionesJsonResponse.getEstado().setMarcaDescripcion(rta.getEstado().getMarcaDescripcion());
			}
		}

		return transaccionesJsonResponse;
	}
	
	/**
	 * Metodo que valida con que campos se puede interactuar en la edicion segun Estado y Marca.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/validarEdicionCobro", method=RequestMethod.POST)
	public @ResponseBody CobroJsonResponse validarEdicionCobro(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		CobroJsonResponse cobroJsonResponse = new CobroJsonResponse();

		String validarEdicionSegunEstadoMarca = cobroOnlineServicio.validarEdicionSegunEstadoMarca(cobro, userSesion);

		cobroJsonResponse.setInformacionMensaje(validarEdicionSegunEstadoMarca);
		
		return cobroJsonResponse;
	}
	
	/**
	 * Metodo que valida que se permite editar segun perfil.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/validarEdicionCobroSegunUsuario", method=RequestMethod.POST)
	public @ResponseBody CobroJsonResponse validarEdicionCobroSegunUsuario(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		CobroJsonResponse cobroJsonResponse = new CobroJsonResponse();
		
		cobroJsonResponse.setSuccess(userSesion.getEsPerfilSupervisorCobranza());
		
		return cobroJsonResponse;
	}
	
	/**
	 * Metodo que imputa el cobro con aprobación.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/imputarConAprobacion")
	public ModelAndView validarAprobacionCobro(CobroDto cobro, HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		StringBuilder str = new StringBuilder();

		ModelAndView mv = new ModelAndView(ACTUALIZACION_EXITOSA_VIEW);
		cobroOnlineServicio.blanquearMensajesTransacciones(cobro.getIdCobro());
		cobroOnlineServicio.validarAprobacionCobroEnviarMail(cobro, userSesion);
		cobroOnlineServicio.eliminarTareasAlImputar(cobro.getIdCobro(), null, userSesion);

		str.append("Se ha generado el cobro ");
		str.append(request.getParameter("idOperacionFormateado"));
		str.append(" exitosamente - ");
		str.append(cobro.getEstado().descripcion());
		str.append(".");

		mv.addObject("mensaje", str.toString() /*Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje")*/);
		mv.addObject("url", BANDEJA_ENTRADA_VIEW_GET);
		return mv;
	}
	
	/**
	 * Metodo que imputa el cobro sin aprobación.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/imputarSinAprobacion")
	public ModelAndView imputarSinAprobacion(CobroDto cobro, HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		StringBuilder str = new StringBuilder();

		ModelAndView mv = new ModelAndView(ACTUALIZACION_EXITOSA_VIEW);
		cobroOnlineServicio.blanquearMensajesTransacciones(cobro.getIdCobro());
		cobroOnlineServicio.imputarSinAprobacion(cobro, userSesion);
		cobroOnlineServicio.eliminarTareasAlImputar(cobro.getIdCobro(), null, userSesion);

		str.append("Se ha generado el cobro ");
		str.append(request.getParameter("idOperacionFormateado"));
		str.append(" exitosamente - ");
		str.append(cobro.getEstado().descripcion());
		str.append(".");
		
		mv.addObject("mensaje", str.toString() /* Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje")*/);
		mv.addObject("url", BANDEJA_ENTRADA_VIEW_GET);
		return mv;
	}
	
	/**
	 * Metodo que valida las transacciones.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/validarTransacciones", method=RequestMethod.POST)
	public @ResponseBody CobroJsonResponse validarTransacciones(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		boolean observacionObligatoria = cobroOnlineServicio.validarTransacciones(cobro.getIdCobroFormatiado());
		CobroJsonResponse cobroJsonResponse = new CobroJsonResponse();
		if(observacionObligatoria){
			cobroJsonResponse.setSuccess(true);
		}else{
			cobroJsonResponse.setSuccess(false);
		}
		return cobroJsonResponse;
	}
	
	/**
	 * Metodo que valida las transacciones.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/validarComprobanteObligatorio", method=RequestMethod.POST)
	public @ResponseBody CobroJsonResponse validarComprobanteObligatorio(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		CobroJsonResponse cobroJsonResponse = new CobroJsonResponse();
		try {
			String validarComprobanteObligatorio = cobroOnlineServicio.validarComprobanteObligatorio(cobro);
			if (!Validaciones.isNullEmptyOrDash(validarComprobanteObligatorio)) {
				cobroJsonResponse.setSuccess(false);
				
				if (validarComprobanteObligatorio.equals(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.name())){
					cobroJsonResponse.setCampoError("Aplicacion");
					cobroJsonResponse.setInformacionMensaje(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.imputacion.validacion.comprobante.aplicacion.manual"));
				} else {
					cobroJsonResponse.setCampoError("Otro");
					cobroJsonResponse.setInformacionMensaje(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.imputacion.validacion.comprobante"));
				}
				
			} else {
				cobroJsonResponse.setSuccess(true);
			}
		} catch (ValidacionExcepcion e) {
			cobroJsonResponse.setSuccess(false);
			cobroJsonResponse.setErrors(e.getMessage());
		}
		
		
		return cobroJsonResponse;
	}
	
	@RequestMapping(value="configuracion-cobro/validarDisponibilidadSaldoValoresEnProceso", method=RequestMethod.POST)
	public @ResponseBody CobroJsonResponse validarDisponibilidadSaldoValoresEnProceso(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		
		CobroJsonResponse cobroJsonResponse = new CobroJsonResponse();
		cobroJsonResponse.setSuccess(true);

		
		try {
			cobroOnlineServicio.validarDisponibilidadSaldoValoresEnProceso(cobro);
		} catch (ValidacionExcepcion e) {
			cobroJsonResponse.setSuccess(false);
			cobroJsonResponse.setErrors(e.getMessage());
			
		} catch (NegocioExcepcion e) {
			
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return cobroJsonResponse;
	}
	
	/**
	 * Metodo que valida si requiere aprobacion.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/validarSiRequiereAprobacion", method=RequestMethod.POST)
	public @ResponseBody CobroJsonResponse validarSiRequiereAprobacion(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		CobroDto cobroDto = cobroOnlineServicio.buscarCobro(cobro.getIdCobro());
		boolean requiereAprob = cobroOnlineServicio.validarSiRequiereAprobacion(cobroDto);
		CobroJsonResponse cobroJsonResponse = new CobroJsonResponse();
		if(requiereAprob){
			cobroJsonResponse.setSuccess(true);
		}else{
			cobroJsonResponse.setSuccess(false);
		}
		return cobroJsonResponse;
	}
	
	/**
	 * Me permite buscar los clientes 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "configuracion-cobro/busquedaClientes", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ClienteJsonResponse buscarClientes(@RequestBody final ClienteFiltro confCobroClienteFiltro, HttpServletRequest request) throws Exception {
		ClienteJsonResponse rta = new ClienteJsonResponse();
		List<ClienteDto> clientesDto = new ArrayList<ClienteDto>();

		try {
			clientesDto = clienteFacade.consultarClienteSiebel(confCobroClienteFiltro);
			
			/*Lista Clientes*/
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
				rta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.vacio"));
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
				rta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.ws.caida"));
				return rta;
			} 
			
			rta.setClientes(clientesDto);
			rta.setSuccess(false);
			rta.setCampoError("#errorBusqueda");
			rta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.ws.error"));
		}
		return rta;
	}
	
	/**********
	 * Debitos
	 **********/
	/**
	 * Busco los debitos via Caplipso/MIC
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/buscarDebitos", method=RequestMethod.POST, produces = "application/json")
	public @ResponseBody DebitoJsonResponse buscarDebitos(@RequestBody final ConfCobroDebitoFiltro confCobroDebitoFiltro, HttpServletRequest request) 
			throws Exception {
		
		try {
			cobroOnlineValidacionServicio.validarFiltroDebitos(confCobroDebitoFiltro);
			confCobroDebitoFiltro.setUsuarioLogeado((UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN));
					
			DebitoJsonResponse rta = busquedaYPaginadoServicio.buscarDebitos(confCobroDebitoFiltro); 
			rta.setSuccess(true);
			
			return rta;
			
		} catch (ValidacionExcepcion ex) {
			DebitoJsonResponse rta = new DebitoJsonResponse();
			rta.setControlPaginacion(null);
			rta.setResultado(null);
			rta.setSuccess(false);
			rta.setCampoError(ex.getCampoError());
			rta.setDescripcionError(
					Propiedades.MENSAJES_PROPIEDADES.getString(ex.getLeyenda()));
			return rta;
		}	
	}
	
	/**
	 * Pagino debitos
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/paginarDebitos", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody DebitoJsonResponse paginarDebitos(HttpServletRequest request) 
			throws Exception {
		
		//Paginacion: anterior o siguiente
		String accion = request.getParameter("accion");

		//Ids clientes seleccionados y debitos seleccionados
		String idsClientesLegado = request.getParameter("idsClientesLegado");
		String idsDoc = request.getParameter("idsDocCtasCob");
					
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		DebitoJsonResponse result = busquedaYPaginadoServicio.paginarDebitos(userSesion.getDebPaginado(), idsClientesLegado, idsDoc, accion);
		return result;
	}
	
	/**
	 * Importo los debitos
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "configuracion-cobro/importarDebitos", method = RequestMethod.POST)
	public void importarDebitos(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		String cantDebitos = ""; 
		cantDebitos = request.getParameter("cantidadDebitosEnGrilla");
		Long idCobro = Long.valueOf(request.getParameter("idCobro"));
		Iterator<String> itr =  request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());
		
		ImportarDebitoJsonResponse result = cobroOnlineServicio.resultadoValidaciones(mpf, cantDebitos, idCobro);
		if (result.getClientes() != null) {
			result.setClientes((List<ClienteDto>) Utilidad.guionesNull(result.getClientes()));;
		}
		if (result.getDebitos() != null) {
			result.setDebitos((List<CobroDebitoDto>) Utilidad.guionesNull(result.getDebitos()));;
		}
		
		if (result.isSuccess()){
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			ComprobanteJsonResponse comprobanteJsonResponse = new ComprobanteJsonResponse();
			ComprobanteDto comprobante = new ComprobanteDto();
			comprobante.setDescripcionArchivo("Archivo Importacion de Debitos.");
			comprobante.setUsuarioCreacion(userSesion.getIdUsuario());
			comprobante.setNombreArchivo(mpf.getOriginalFilename());
			comprobante.setDocumento(mpf.getBytes());
			comprobante.setMotivoAdjunto(MotivoAdjuntoEnum.COMPROBANTE_COBRO.name());

			comprobante = cobroOnlineServicio.crearAdjuntoCobro(idCobro, comprobante);

			comprobanteJsonResponse.setDescripcion(comprobante.getDescripcionArchivo());
			comprobanteJsonResponse.setFileName(comprobante.getNombreArchivo());
			comprobanteJsonResponse.setId(Integer.parseInt(comprobante.getIdComprobante().toString()));
			result.setComprobanteJsonResponse(comprobanteJsonResponse);
		}
		
		ControlArchivo.responderJSON(result, response);
	}
	
	/**
	 * Importo los otros debitos
	 * 
	 * @author U587496 Guido.Settecerze
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "configuracion-cobro/importarOtrosDebitos", method = RequestMethod.POST)
	public void importarOtrosDebitos(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		String cantDebitos = ""; 
		cantDebitos = request.getParameter("cantidadOtrosDebitosEnGrilla");
		Long idCobro = Long.valueOf(request.getParameter("idCobro"));
		MonedaEnum monedaCobro = MonedaEnum.getEnumByName(request.getParameter("monedaCobro"));
		Iterator<String> itr =  request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());
		
		
				
		
		ImportarOtrosDebitoJsonResponse result = cobroOnlineSoporteServicio.resultadoValidacionesOtrosDebitos(mpf, cantDebitos, idCobro, monedaCobro);
		if (result.getClientes() != null) {
			result.setClientes((List<ClienteDto>) Utilidad.guionesNull(result.getClientes()));;
		}
//		if (result.getDebitos() != null) { ESTO VA PERO CON OTROS DEBITOS
//			result.setDebitos((List<CobroDebitoDto>) Utilidad.guionesNull(result.getDebitos()));;
//		}
		
		if (result.isSuccess()) {
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			ComprobanteJsonResponse comprobanteJsonResponse = new ComprobanteJsonResponse();
			ComprobanteDto comprobante = new ComprobanteDto();
			comprobante.setDescripcionArchivo("Archivo Importacion de Otros Debitos.");
			comprobante.setUsuarioCreacion(userSesion.getIdUsuario());
			comprobante.setNombreArchivo(mpf.getOriginalFilename());
			comprobante.setDocumento(mpf.getBytes());
			comprobante.setMotivoAdjunto(MotivoAdjuntoEnum.OTROS_DEBITO.name());

			comprobante = cobroOnlineServicio.crearAdjuntoCobro(idCobro, comprobante);

			comprobanteJsonResponse.setDescripcion(comprobante.getDescripcionArchivo());
			comprobanteJsonResponse.setFileName(comprobante.getNombreArchivo());
			comprobanteJsonResponse.setId(Integer.parseInt(comprobante.getIdComprobante().toString()));
			result.setComprobanteJsonResponse(comprobanteJsonResponse);
		}
		
		ControlArchivo.responderJSON(result, response);
	}
	
	/***********
	 * Creditos
	 ***********/
	/**
	 * Busco los creditos via Caplipso/MIC
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "configuracion-cobro/buscarCreditos", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CreditoJsonResponse buscarCreditos(@RequestBody final ConfCobroCreditoFiltro confCobroCreditoFiltro, HttpServletRequest request) 
		throws Exception {
		
		try {
			cobroOnlineValidacionServicio.validarFiltroCreditos(confCobroCreditoFiltro);
			confCobroCreditoFiltro.setUsuarioLogeado((UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN));

			CreditoJsonResponse result = busquedaYPaginadoServicio.buscarCreditos(confCobroCreditoFiltro); 
			result.setSuccess(true);
			return result;
			
		} catch (ValidacionExcepcion ex) {
			CreditoJsonResponse rta = new CreditoJsonResponse();
			rta.setControlPaginacion(null);
			rta.setResultado(null);
			rta.setSuccess(false);
			rta.setCampoError(ex.getCampoError());
			rta.setDescripcionError(
					Propiedades.MENSAJES_PROPIEDADES.getString(ex.getLeyenda()));
			return rta;
		}
	}
	
	@RequestMapping(value = "/paginarCreditos", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CreditoJsonResponse paginarCreditos(HttpServletRequest request) 
			throws Exception {
		// Paginacion: anterior o siguiente
		String accion = request.getParameter("accion");
		
		//Ids clientes seleccionados y debitos seleccionados
		String idsClientesLegado = request.getParameter("idsClientesLegado");
		String idsDoc = request.getParameter("idsDoc");
	
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		CreditoJsonResponse result = busquedaYPaginadoServicio.paginarCreditos(userSesion.getCredPaginado(), idsClientesLegado, idsDoc, accion);
		return result;
	}
	
	/***************
	 * Comprobantes
	 ***************/
	 
	/**
	 * Me permite adjuntar los comprobantes
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "configuracion-cobro/adjuntarComprobante", method = RequestMethod.POST)
	public void adjuntarComprobante(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {                 
		
		String idCobro = request.getParameter("idCobro");
		String descripcion = request.getParameter("descripcion");
		String motivoAdjunto = request.getParameter("motivoAdjunto");

		Iterator<String> itr =  request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		
		ComprobanteJsonResponse json = new ComprobanteJsonResponse();
		try {
			cobroOnlineValidacionServicio.validarComprobantes(file, descripcion);
			
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			
			ComprobanteDto comprobante = new ComprobanteDto();
			comprobante.setDescripcionArchivo(descripcion);
			comprobante.setUsuarioCreacion(userSesion.getIdUsuario());
			comprobante.setNombreArchivo(file.getOriginalFilename());
			comprobante.setDocumento(file.getBytes());
			
			comprobante.setMotivoAdjunto(motivoAdjunto);
			
			comprobante = cobroOnlineServicio.crearAdjuntoCobro(Long.valueOf(idCobro), comprobante);
			
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
	
	@RequestMapping(value = "configuracion-cobro/adjuntarComprobanteConfirmacion", method = RequestMethod.POST)
	public void adjuntarComprobanteConfirmacion(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String descripcion = request.getParameter("descripcion");
		String motivoAdjunto = request.getParameter("motivoAdjunto");
		
		Iterator<String> itr =  request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		ComprobanteJsonResponse json = new ComprobanteJsonResponse();
		try {
			cobroOnlineValidacionServicio.validarComprobantes(file, descripcion);
			
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			List<ComprobanteDto> comprobantesAGuardarFinal = userSesion.getComprobantesAGuardar();
			//Usamos el fechaID como idComprobante
			long fechaID = new Date().getTime();
			ComprobanteDto comprobante = new ComprobanteDto();
			comprobante.setIdComprobante(fechaID);
			comprobante.setDescripcionArchivo(descripcion);
			comprobante.setUsuarioCreacion(userSesion.getIdUsuario());
			comprobante.setNombreArchivo(file.getOriginalFilename());
			comprobante.setDocumento(file.getBytes());
			comprobante.setMotivoAdjunto(motivoAdjunto);
			
			comprobantesAGuardarFinal.add(comprobante);
			userSesion.setComprobantesAGuardar(comprobantesAGuardarFinal);
			
			json.setSuccess(true);
			json.setIdComprobante(fechaID);
			json.setDescripcion(descripcion);
			json.setMotivoAdjunto(motivoAdjunto);
			json.setFileName(file.getOriginalFilename());
		} catch (ValidacionExcepcion e) {
			json.setSuccess(false);
			json.setCampoError(e.getCampoError());
			json.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString(e.getLeyenda()));
		}
		
		ControlArchivo.responderJSON(json, response);
	}
	
	
//	/**
//	 * 
//	 * @param comprobante
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value="configuracion-cobro/eliminarComprobante", method=RequestMethod.POST)
//	@ResponseBody
//	public ComprobanteJsonResponse eliminarComprobante(@RequestBody final ComprobanteDto comprobante, HttpServletRequest request) throws Exception {
//		
//		cobroOnlineServicio.eliminarAdjuntoCobro(comprobante.getIdComprobante());
//		
//		ComprobanteJsonResponse json = new ComprobanteJsonResponse();
//		json.setSuccess(true);
//		json.setId(Integer.parseInt(comprobante.getIdComprobante().toString()));
//		return json;
//	}
	
	@RequestMapping(value="configuracion-cobro/eliminarComprobante", method=RequestMethod.POST)
	@ResponseBody
	public ComprobanteJsonResponse eliminarComprobante(@RequestBody final ComprobanteDto comprobante, HttpServletRequest request) throws Exception {
		ComprobanteJsonResponse json = new ComprobanteJsonResponse();
		boolean usoElIdPantallaDelComprobante = false;
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		List<ComprobanteDto> buscarAdjuntoPorIdAdjunto = cobroOnlineServicio.buscarAdjuntoPorIdAdjunto(comprobante.getIdComprobante());
		if(buscarAdjuntoPorIdAdjunto.isEmpty()){
			buscarAdjuntoPorIdAdjunto = userSesion.getComprobantesConIdPantalla();
			usoElIdPantallaDelComprobante = true;
		}
		if(!buscarAdjuntoPorIdAdjunto.isEmpty()){
			if(usoElIdPantallaDelComprobante){
				for (ComprobanteDto comprobanteDto : buscarAdjuntoPorIdAdjunto) {
					if(comprobanteDto.getIdPantallaComprobante().compareTo(comprobante.getIdComprobante())==0){
						cobroOnlineServicio.eliminarAdjuntoCobro(comprobanteDto.getIdComprobante());
					}
				}
			}else{
				cobroOnlineServicio.eliminarAdjuntoCobro(comprobante.getIdComprobante());
			}
		}
		List<ComprobanteDto> comprobantesAGuardar = userSesion.getComprobantesAGuardar();
		
//		Se comento debido a que tira un ConcurrentModificationException
		
//		for (ComprobanteDto comprobanteDto : comprobantesAGuardar) {
//			if(comprobanteDto.getIdComprobante().compareTo(comprobante.getIdComprobante())==0){
//				comprobantesAGuardar.remove(comprobanteDto);
//			}
//		}
		
		Iterator<ComprobanteDto> comprobantesAGuardarIter = userSesion.getComprobantesAGuardar().iterator();

		while (comprobantesAGuardarIter.hasNext()) {
		    ComprobanteDto comprobanteDto = comprobantesAGuardarIter.next();

		    if (comprobanteDto.getIdComprobante().compareTo(comprobante.getIdComprobante())==0){
		    	comprobantesAGuardarIter.remove();
		    }
		}
		
		userSesion.setComprobantesAGuardar(comprobantesAGuardar);
		json.setSuccess(true);
		json.setIdComprobante(comprobante.getIdComprobante());
		return json;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/descargarComprobante", method=RequestMethod.GET)
	public void descargarComprobante(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		ComprobanteDto comprobante = cobroOnlineServicio.buscarAdjuntoCobro(Long.valueOf(id));
		
		if (comprobante!=null) {
			byte[] file = comprobante.getDocumento();
			String fileName = comprobante.getNombreArchivo();
			ControlArchivo.descargarComoArchivo(file, fileName, response);
		}
	}
	
	
	/**
	 * Busco los segmentos
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "configuracion-cobro/buscarSegmentos", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SelectOptionJsonResponse> buscarSegmentos(HttpServletRequest request) throws Exception {
//		String idEmpresa = request.getParameter("idEmpresa");
//		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
//		List<SelectOptionJsonResponse> result = new ArrayList<SelectOptionJsonResponse>();
//		SelectOptionJsonResponse jsonResp = null;
//		for (ShvParamSegmento segmento : combosServicio.listarSegmentoPorEmpresaYUsuario(idEmpresa, userSesion)) {
//			jsonResp = new SelectOptionJsonResponse();
//			jsonResp.setValue(segmento.getIdSegmento());
//			jsonResp.setText(segmento.getDescripcion());
//			result.add(jsonResp);
//		}
//		return result;
		return super.buscarSegmentos(request);
	}
	
	/**
	 * Busco los copropietarios
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "configuracion-cobro/buscarCopropietarios", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SelectOptionJsonResponse> buscarCopropietarios(HttpServletRequest request) throws Exception {
//		String idEmpresa = request.getParameter("idEmpresa");
//		String idSegmento = request.getParameter("idSegmento");
//		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
//		
//		Collection<String> usuariosExcluidos = new ArrayList<String>();
//		usuariosExcluidos.add(userSesion.getIdUsuario());
//		
//		List<SelectOptionJsonResponse> result = buscarCopropietarios(idEmpresa, idSegmento, usuariosExcluidos);
//		return result;
		return super.buscarCopropietarios(request);
	}
	
	
	
	/**
	 * Busco los acuerdos por linea
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param request
	 * @return
	 */
	@RequestMapping(value="configuracion-cobro/buscarAcuerdoPorLinea", method=RequestMethod.POST)
	@ResponseBody
	public AcuerdoJsonResponse buscarAcuerdoPorLinea(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		AcuerdoJsonResponse respuesta = new AcuerdoJsonResponse();
		try {
			String numeroLinea = cobro.getNumeroLinea();
			
			
			if(!Validaciones.isNumeric(numeroLinea)){
				respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
				return respuesta;
			}

			FacConsultaAcuerdoSalida datos = facJmsSyncServicio.consultarAcuerdoxLinea(numeroLinea);
			
			if(!Validaciones.isObjectNull(datos)){
				Traza.auditoria(getClass(), "Se ha obtenido el acuerdo de la linea: " + numeroLinea + ": " + datos.getRetorno().toString());
				respuesta = buscarPrimerAcuerdoActivo(datos, respuesta);
				return respuesta;
			} 
			
			respuesta.setSuccess(false);
			respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.lineaInexistente"));
			return respuesta;
			
		} catch (Throwable ex) {
			Traza.error(getClass(), ex.getMessage(), ex);
			if (ex instanceof JmsExcepcion) {
				respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.mic"));
				return respuesta;
			} else {
				throw new NegocioExcepcion(ex.getMessage(), ex);
			}	
		}
	}
	
	/**
	 * Busco el primer acuerdo Activo
	 * @param datos
	 * @param respuesta
	 * @return
	 * @throws NegocioExcepcion
	 */
	private AcuerdoJsonResponse buscarPrimerAcuerdoActivo(FacConsultaAcuerdoSalida datos, AcuerdoJsonResponse respuesta) throws NegocioExcepcion {
		
		String numeroAcuerdoFinal = null;
		List<AcuerdoFacturacion> listaAcuerdos = datos.getListaAcuerdoFacturacion();
		if (Validaciones.isCollectionNotEmpty(listaAcuerdos)) {
			int cantAcuerdos = listaAcuerdos.size();
			Traza.auditoria(getClass(), "Se ha obtenido la lista de "+ cantAcuerdos + " acuerdos");
			
			int contador = 0;
			//Recorro el/los acuerdos para validar su estado y quedarme con el primero que cumpla
			for(AcuerdoFacturacion acuerdo : listaAcuerdos){
				contador++;
				Traza.auditoria(getClass(), contador + " de "+ cantAcuerdos + ": " + acuerdo.toString());
				
				if(!Validaciones.isObjectNull(acuerdo)){
					
					//Mejora Performance
					//Si estos dos cumplen con este criterio, puedo ir a consultar por el acuerdo (no borrar los espacios)
					if (!Validaciones.isNullOrEmpty(acuerdo.getFechaFinalizacionAcuerdoFactura())
							&& !Validaciones.isNullOrEmpty(acuerdo.getFechaUltimaFactura())
							&& "9999365".equalsIgnoreCase(acuerdo.getFechaFinalizacionAcuerdoFactura().trim())
							&& !"0001001".equalsIgnoreCase(acuerdo.getFechaUltimaFactura().trim())) {
					
						//valido si el acuerdo esta activo, pero no devuelvo el nro de acuerdo
						respuesta = validarEstadoAcuerdoMic(acuerdo.getNumeroAcuerdo(), respuesta);

						if(respuesta.isSuccess()){
							numeroAcuerdoFinal = acuerdo.getNumeroAcuerdo().toString();
							if(!Validaciones.isNullOrEmpty(numeroAcuerdoFinal)){
								respuesta.setNroAcuerdo(numeroAcuerdoFinal);
								return respuesta;
							}
						}
					} else {
						Traza.auditoria(getClass(), "Este acuerdo no se consultara por el servicio por no cumplir los criterios");
						respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoInactivo"));
					}
				} else {
					Traza.auditoria(getClass(), "Acuerdo Vacio (Null)");
					respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoInexistente"));
				}
			}
		} else {
			Traza.auditoria(getClass(), "Este cliente no tiene acuerdos");
			respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.ningunCliente"));
		}
		
		
		return respuesta;
	}
	
	/**
	 * Valido el nro de linea contra clientes
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/validarAcuerdoContraClienteMic", method=RequestMethod.POST)
	@ResponseBody
	public AcuerdoJsonResponse validarAcuerdoContraClienteMic(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		String numeroCliente="";
		AcuerdoJsonResponse respuesta = new AcuerdoJsonResponse();
		try {
			String acuerdoIngresado = cobro.getNumeroAcuerdo();
			Integer idMedioPago = null;
			MicRespuestaGeneracionCargoSalida interesesActualizados = null;
			boolean contains = false;
			respuesta.setDebitoAProx(cobro.isDebitoAProx());

			if (!Validaciones.isNullOrEmpty(acuerdoIngresado) && Validaciones.isNumeric(acuerdoIngresado)) {
				//valido el estado del acuerdo
				respuesta = validarEstadoAcuerdoMic(new Long(acuerdoIngresado), respuesta);
				
				if (respuesta.isSuccess()) {
					FacConsultaClienteSalida datos = facJmsSyncServicio.consultarClientexAcuerdo(acuerdoIngresado);
					if (!Validaciones.isObjectNull(datos) && !Validaciones.isObjectNull(datos.getNumeroCliente())) {
						numeroCliente = datos.getNumeroCliente().toString();
					}
					List<String> listaIdClientesLegado = cobro.listaIdClientesLegado();
					if(!Validaciones.isNullOrEmpty(numeroCliente) && !listaIdClientesLegado.isEmpty()){
						contains = listaIdClientesLegado.contains(Utilidad.rellenarCerosIzquierda(numeroCliente,10)) || listaIdClientesLegado.contains(numeroCliente);
						respuesta.setClienteAcuerdo(numeroCliente);
					}
					respuesta.setSuccess(contains);
					if(contains)  {
						if(cobro.isDebitoAProx()) {
							idMedioPago = new Integer (cobro.getIdMedioPago());
							interesesActualizados = cobroOnlineServicio.actualizarInteresesDebitoAProxMic(idMedioPago,acuerdoIngresado);
							respuesta.setInteresesActualizados(Utilidad.formatCurrency(interesesActualizados.getDetalleInteresesGenerados().getInteresesGeneradosRegulados(),false,false));
							//seteo los bonificados con el total de los intereses, ya que en debito a proxima, no hay bonificaciones parciales.
							respuesta.setBonificacionInteresesActualizados(Utilidad.formatCurrency(interesesActualizados.getDetalleInteresesGenerados().getInteresesGeneradosRegulados(),false,false));
						}
					}else {
						respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoNoRelacionado"));
					}
					return respuesta;
				} else {
					return respuesta;
				}
			} else {
				respuesta.setSuccess(false);
				respuesta.setDescripcionError("Este campo debe ser numerico.");
				return respuesta;
			}
		} catch (Throwable ex) {
			Traza.error(getClass(), ex.getMessage(), ex);
			if (ex instanceof JmsExcepcion) {
				respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.mic"));
				respuesta.setSuccess(false);
				return respuesta;
			} else {
				throw new NegocioExcepcion(ex.getMessage(), ex);
			}
		}
	}
	
	/**
	 * Valido el nro de acuerdo contra clientes 
	 * @author u573005, fabio.giaquinta.ruiz
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/validarAcuerdoContraClienteCLP", method=RequestMethod.POST)
	@ResponseBody
	public AcuerdoJsonResponse validarAcuerdoContraClienteCLP(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		String numeroCliente="";
		AcuerdoJsonResponse respuesta = new AcuerdoJsonResponse();
		MonedaEnum monedaAcuerdo = null;
		try {
			String numeroAcuerdo = cobro.getNumeroAcuerdo();
			Integer idMedioPago = null;
			if (!Validaciones.isObjectNull(cobro.getIdMedioPago())) {
				idMedioPago=new Integer(cobro.getIdMedioPago());
			}

			SalidaCalipsoCargosWS interesesActualizados = null;
			SalidaCalipsoConsultaAcuerdoWS datos = null;

			boolean contains = false;
			respuesta.setDebitoAProx(cobro.isDebitoAProx());

			if (!Validaciones.isNullOrEmpty(numeroAcuerdo)) {
				EntradaCalipsoConsultaAcuerdoWS entrada = new EntradaCalipsoConsultaAcuerdoWS();
				entrada.setAcuerdoFacturacion(new BigInteger(numeroAcuerdo));
				datos = clienteCalipsoServicio.consultaAcuerdo(entrada);
				
				if (!Validaciones.isObjectNull(datos)) {
					Resultado resultado = datos.getResultado();
					if (!Validaciones.isObjectNull(resultado)) {
						if (TipoResultadoEnum.OK.toString().equals(resultado.getResultado())) {
							numeroCliente = datos.getAcuerdo().getIdClienteLegado().toString();
							monedaAcuerdo = datos.getAcuerdo().getMoneda();
						} else {
							respuesta.setSuccess(false);
							respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoInexistente"));
							return respuesta;
						} 
					} 
				}
			} else {
				respuesta.setSuccess(false);
				respuesta.setDescripcionError("Este campo debe ser nulo.");
				return respuesta;
			}
			
			List<String> listaIdClientesLegado = cobro.listaIdClientesLegado();
			if (!Validaciones.isNullOrEmpty(numeroCliente) && !listaIdClientesLegado.isEmpty()) {
				contains = listaIdClientesLegado.contains(Utilidad.rellenarCerosIzquierda(numeroCliente,10)) || listaIdClientesLegado.contains(numeroCliente);
				respuesta.setClienteAcuerdo(numeroCliente);
			}
			
			respuesta.setSuccess(contains);
			if (contains) {
				if(cobro.isDebitoAProx()) {
					interesesActualizados = cobroOnlineServicio.actualizarInteresesDebitoAProxCalipso(idMedioPago,numeroAcuerdo);
					respuesta.setInteresesActualizados(Utilidad.formatCurrency(interesesActualizados.getMontoCalculadoMora(),false,false));
					//seteo los bonificados con el total de los intereses, ya que en debito a proxima, no hay bonificaciones parciales.
					respuesta.setBonificacionInteresesActualizados(Utilidad.formatCurrency(interesesActualizados.getMontoCalculadoMora(),false,false));
				}
				// Si se generan cargo por mora tengo que tener en cuenta la moneda de la operacion
				// TODO CON LA MODIFICACION PENDIENTE SOLO ENTRARIA A Esta metodo cuando hay mora
				if (!cobro.isEsTratamientoDiferencia()) {
					if (monedaAcuerdo == null) {
						Traza.error(getClass(), "La moneda del acuerdo es null o es otro valor diferente de PESOS o DOLARES");
					}
					if (MonedaEnum.PES.equals(MonedaEnum.getEnumBySigno2(cobro.getMonedaOperacion()))) {
						if (!MonedaEnum.getEnumBySigno2(cobro.getMonedaDocumento()).equals(monedaAcuerdo)) {
							respuesta.setMensajeWrn(Propiedades.MENSAJES_PROPIEDADES.getString("warning.sistemaDestino.acuerdo.moneda.diferente.input"));
						} else {
							respuesta.setMensajeWrn(Utilidad.EMPTY_STRING);
						}
					}
				}
				respuesta.setEstadoAcuerdo(datos.getAcuerdo().getEstado().descripcion());
				respuesta.setEstadoAcuerdoFacturacion(datos.getAcuerdo().getEstado().name());	
				return respuesta;
			} else {
				respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoNoRelacionado"));
				return respuesta;
			}
		} catch (Throwable ex) {
			Traza.error(getClass(), ex.getMessage(), ex);
			if (ex instanceof WebServiceExcepcion || ex instanceof WebServiceFormatoMensajeExcepcion) {
				respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.calipso"));
				respuesta.setSuccess(false);
				return respuesta;
			} else {
				throw new NegocioExcepcion(ex.getMessage(), ex);
			}	
		}
	}
	
	
	

	
	/**
	 * Valido si existe el acuerdo contra CLP.
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/validarAcuerdoExistenteCLP", method=RequestMethod.POST)
	@ResponseBody
	public AcuerdoJsonResponse validarAcuerdoExistenteCLP(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		AcuerdoJsonResponse respuesta = new AcuerdoJsonResponse();
		
		try {
			EntradaCalipsoConsultaAcuerdoWS entrada = new EntradaCalipsoConsultaAcuerdoWS();
			entrada.setAcuerdoFacturacion(new BigInteger(cobro.getNumeroAcuerdo()));
			SalidaCalipsoConsultaAcuerdoWS consultarAcuerdo = clienteCalipsoServicio.consultaAcuerdo(entrada);
			
			if(!Validaciones.isObjectNull(consultarAcuerdo)){
				Resultado resultado = consultarAcuerdo.getResultado();
				if(!Validaciones.isObjectNull(resultado)){
					if(TipoResultadoEnum.OK.toString().equals(resultado.getResultado())){
						respuesta.setEstadoAcuerdo(consultarAcuerdo.getAcuerdo().getEstado().descripcion());
						respuesta.setEstadoAcuerdoFacturacion(consultarAcuerdo.getAcuerdo().getEstado().name());
						respuesta.setSuccess(true);
						
					} else{
						respuesta.setSuccess(false);
						respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoInexistente"));
					} 
				}
			} else {
				respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoInexistente"));
			}
			return respuesta;
		} catch (Throwable ex) {
			Traza.error(getClass(), ex.getMessage(), ex);
			if (ex instanceof WebServiceExcepcion || ex instanceof WebServiceFormatoMensajeExcepcion) {
				respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.calipso"));
				return respuesta;
			} else {
				throw new NegocioExcepcion(ex.getMessage(), ex);
			}	
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/autoCompletarCampoAcuerdo", method=RequestMethod.POST)
	@ResponseBody
	public AcuerdoJsonResponse autoCompletarCampoAcuerdo(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		AcuerdoJsonResponse respuesta = new AcuerdoJsonResponse();
		try {
			List<String> listaIdClientesLegado = cobro.listaIdClientesLegado();
			FacConsultaAcuerdoSalida consultarAcuerdoxCliente = null;
			
			for (String nroCliente : listaIdClientesLegado) {
				// obtengo el acuerdo a partir del cliente
				consultarAcuerdoxCliente = facJmsSyncServicio.consultarAcuerdoxCliente(new Long(nroCliente));
				if(!Validaciones.isObjectNull(consultarAcuerdoxCliente)){
					Traza.auditoria(getClass(), "Se ha obtenido el acuerdo del cliente: " + nroCliente + ": " + consultarAcuerdoxCliente.getRetorno().toString());
					respuesta.setClienteAcuerdo(nroCliente);
					respuesta = buscarPrimerAcuerdoActivo(consultarAcuerdoxCliente, respuesta);
					if (Validaciones.isNullOrEmpty(respuesta.getDescripcionError())) {
						return respuesta;
					}
					respuesta = new AcuerdoJsonResponse();
				}
			}
			respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.ningunCliente"));
			return respuesta;
			
		} catch (Throwable ex) {
			Traza.error(getClass(), ex.getMessage(), ex);
			if (ex instanceof JmsExcepcion) {
				respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.mic"));
				return respuesta;
			} else {
				throw new NegocioExcepcion(ex.getMessage(), ex);
			}			
		}
	}
	
	/**
     * @author u573005, sprint 4 y 5 se extrae el metodo
     * @param numeroAcuerdo
     * @param respuesta
     * @return
     * @throws NegocioExcepcion
     */
     private AcuerdoJsonResponse validarEstadoAcuerdoMic(Long numeroAcuerdo, AcuerdoJsonResponse respuesta) throws NegocioExcepcion{
           boolean tieneAcuerdoActivo = false;
           FacConsultaAcuerdoSalida salida = facJmsSyncServicio.consultarAcuerdo(numeroAcuerdo);
           EstadoAcuerdoFacturacionEnum estadoAcuerdo = null;
           
           if(!Validaciones.isObjectNull(salida)){
        	  estadoAcuerdo = salida.getPrimerAcuerdoFacturacion().getEstadoAcuerdo();
        	  if (estadoAcuerdo!=null) {
        		  if(EstadoAcuerdoFacturacionEnum.ACTIVO.codigo().equals(estadoAcuerdo.codigo())
	                   || EstadoAcuerdoFacturacionEnum.INCOMUNICADO.codigo().equals(estadoAcuerdo.codigo())){
	                     tieneAcuerdoActivo = true;
	              }
        	  } else {
        		  Traza.auditoria(getClass(), " Nro Acuerdo: " + numeroAcuerdo + ", sin estadoAcuerdo");
        		  
                  respuesta.setSuccess(false);
                  respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoInexistente"));
                  return respuesta;
               }
           } else {
        	  Traza.auditoria(getClass(), " Nro Acuerdo: " + numeroAcuerdo + ", respuesta vacia");
        	  respuesta.setSuccess(false);
              respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoInexistente"));
              return respuesta;
           }
           
           respuesta.setSuccess(tieneAcuerdoActivo);
           //Tengo que limpiar la descripcion del error por si ya hubo un error previo
    	   respuesta.setDescripcionError(Utilidad.EMPTY_STRING);
    	   respuesta.setEstadoAcuerdo(estadoAcuerdo.descripcion());
    	   respuesta.setEstadoAcuerdoFacturacion(estadoAcuerdo.name());
    	   
           if (tieneAcuerdoActivo){
        	   Traza.auditoria(getClass(), " Nro Acuerdo: " + numeroAcuerdo + ", " + estadoAcuerdo.descripcion());
           } else {
        	   Traza.auditoria(getClass(), " Nro Acuerdo: " + numeroAcuerdo + ", INACTIVO");
        	   respuesta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoInactivo"));
           }
    	   return respuesta;
     }

	/**
	 * @author u573005, sprint 4
	 * Valido el estado del cobro contra Deimos para actualizar el semaforo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "configuracion-cobro/validarEstadoDeimos", method = RequestMethod.POST)
	@ResponseBody
	public DeimosJsonResponse validarEstadoDeimos(@RequestBody final DeimosJsonRequest creditosDebitos, HttpServletRequest request) throws Exception {
		DeimosJsonResponse result = null;
		try{
			result = cobroOnlineServicio.validarEstadoDeimos (
				creditosDebitos.getListaIdatosComunesEntrada(),
				EmpresaEnum.getEnumByName(creditosDebitos.getEmpresaCodigo()),
				creditosDebitos.getListaIdatosYaSeleccionados()
			);
		}catch(NegocioExcepcion e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return result;		
	}	
	/*******************************************************************************************
	 * Getters & Setters
	 ******************************************************************************************/
	/**
	 * @return the clienteConsultarSiebelServicio
	 */
	public IClienteSiebelServicio getClienteConsultarSiebelServicio() {
		return clienteConsultarSiebelServicio;
	}

	/**
	 * @param clienteConsultarSiebelServicio the clienteConsultarSiebelServicio to set
	 */
	public void setClienteConsultarSiebelServicio(
			IClienteSiebelServicio clienteConsultarSiebelServicio) {
		this.clienteConsultarSiebelServicio = clienteConsultarSiebelServicio;
	}
	
	/**
	 * 
	 * @return
	 */
	public ICobroOnlineServicio getCobroOnlineServicio() {
		return cobroOnlineServicio;
	}

	/**
	 * 
	 * @param cobroOnlineServicio
	 */
	public void setCobroOnlineServicio(ICobroOnlineServicio cobroOnlineServicio) {
		this.cobroOnlineServicio = cobroOnlineServicio;
	}

	/**
	 * JUAN I. Spring 5
	 */

	@RequestMapping(value = "/cobro-busqueda")
	public ModelAndView cobroBusqueda(HttpServletRequest request)
			throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(BUSQUEDA_VIEW);
		
		/*
		 * Valida si es una busqueda normal o vuelve de un detallado 
		 */
		if (!Validaciones.isNullOrEmpty(request.getParameter("idVolver"))) {
			userSesion.setVolviendoABusqueda(true);
		} else if (!Validaciones.isNullOrEmpty(request.getParameter("idTareaVerDetalle"))) {
			// valida si es una busqueda lanzada desde la bandeja de entrada
			VistaSoporteCobroOnlineFiltro cobroFiltro = new VistaSoporteCobroOnlineFiltro();
			cobroFiltro.setIdOpCobro(request.getParameter("idOperacionCob"));
			cobroFiltro.setIdEmpresa(request.getParameter("empresaTareaVerDetalle"));
			// cobro-busqueda se tiene que comportar igual que cuando se vuelve desde 
			// el detalle de un cobro o de la edicion de un cobro
			userSesion.setVolviendoABusqueda(true);
			userSesion.setCobroFiltro(cobroFiltro);
			userSesion.getCaminoDeVuelta().push("regresar-bandeja-de-entrada");
		}
		else{
			userSesion.setCobroFiltro(new VistaSoporteCobroOnlineFiltro());
			userSesion.setVolviendoABusqueda(false);
		}
		if (userSesion.getVolviendoABusqueda()) {
			mv.addObject("volviendoABusqueda", true);  	//para volver a busqeuda, que setee TRUE userSesion.getVolviendoABusqueda() y que guarde el ultimo filtro en userSesion asi aca lo toma.
			mv.addObject("cobroBusquedaFiltro", userSesion.getCobroFiltro());
		}else{

			mv.addObject("volviendoABusqueda", false);
		}
	
		if (userSesion.getCaminoDeVuelta().size() > 0) {
			mv.addObject("caminoDeVuelta", userSesion.getCaminoDeVuelta().peek());
		}
		/*
		 * CARGA COMBOS
		 */
		List<ShvParamEmpresa> empresas = (List<ShvParamEmpresa>) combosServicio.listarEmpresasPorUsuario(userSesion);
		List<ShvParamSegmento> segmentos = new ArrayList<ShvParamSegmento>();

		if (empresas.size() == 1) {
			ShvParamEmpresa empresa = empresas.get(0);
			segmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(empresa.getIdEmpresa(), userSesion);
		}

		//cargarComboParaEstadosCobro(mv); // Pertenece a Controlador.java y Carga
										 // listaEstados

		List<String> listaEstados = listarEstadosParaBusquedaCobro();
		
		List<ShvParamMotivoCobro> motivos = (List<ShvParamMotivoCobro>) combosServicio.listarMotivosBusquedaCobros();
	
		
		HashSet<TipoCreditoEnum> listaMediosDePagoSinRepetidosFinal = new HashSet<TipoCreditoEnum>();
		HashSet<String> descripcionEnums = new HashSet<String>();
		List<TipoCreditoEnum> listaMediosDePago = Arrays.asList(TipoCreditoEnum.values());
		for (TipoCreditoEnum tipoCreditoEnum : listaMediosDePago) {
			
			if(!TipoCreditoEnum.SALDO_A_COBRAR.equals(tipoCreditoEnum)) {
				descripcionEnums.add(tipoCreditoEnum.getDescripcion());
			}
		}		
		for (String descripcionEnum : descripcionEnums) {
			listaMediosDePagoSinRepetidosFinal.add(TipoCreditoEnum.getEnumByDescripcion(descripcionEnum));
		}
		
		List<SubTipoCompensacionEnum> listaSubtipoCompensacion = Arrays.asList(SubTipoCompensacionEnum.values());
		
		
		
		List<TipoTratamientoDiferenciaEnum> listaTipoTratamientoCompleta = Arrays.asList(TipoTratamientoDiferenciaEnum.values());
		
		List<TipoTratamientoDiferenciaEnum> listaTipoTratamiento = new ArrayList<TipoTratamientoDiferenciaEnum>();
		
		for (TipoTratamientoDiferenciaEnum tratam : listaTipoTratamientoCompleta) {
			if(!TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_CLP.equals(tratam) 
					&& !TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_MIC.equals(tratam)
					&& !TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratam)
					&& !TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratam)
					&& !TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(tratam)
					&& !TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(tratam)
					&& !TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION.equals(tratam)
					&& !TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL.equals(tratam)
					&& !TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL.equals(tratam)) {
				listaTipoTratamiento.add(tratam);
			}
		}
		
		List<SistemaEnum> listaSistemaDestino = new ArrayList<SistemaEnum>();
		
		listaSistemaDestino.add(SistemaEnum.CALIPSO);
		listaSistemaDestino.add(SistemaEnum.MIC);
		
		/*
		 * AGREGO LISTAS AL MV
		 */

		if(("/operacion-masiva-busqueda").equals(request.getParameter("volver"))){
			VistaSoporteCobroOnlineFiltro cobroFiltro = new VistaSoporteCobroOnlineFiltro();

			userSesion.setVolviendoABusqueda(false);
			
			cobroFiltro.setIdOperacionMasiva(request.getParameter("idOperacionMasiva"));
			cobroFiltro.setEmpresa(request.getParameter("selectEmpresa"));
			mv.addObject("cobroBusquedaFiltro", cobroFiltro);
			mv.addObject("idVolver", request.getParameter("volver"));
		}
		
		//Brian Botones volver
		if(!Validaciones.isNullOrEmpty(request.getParameter("goBack"))) {
			userSesion.getRetorno().remove(0);
			mv.addObject("idVolver", userSesion.getRetorno().get(0));
		}else if (!Validaciones.isNullOrEmpty(request.getParameter("volver"))){
			if(!userSesion.getRetorno().get(0).equals(request.getParameter("volver"))) {
				userSesion.getRetorno().add(0, request.getParameter("volver"));
				mv.addObject("idVolver", request.getParameter("volver"));	
			}else {
				mv.addObject("idVolver", request.getParameter("volver"));
			}
		}
		
		//COMBOS APROBADORES
		List<UsuarioLdapDto> listaReferentesCobranza = buscarUsuariosPorPerfil(TipoPerfilEnum.REFERENTE_COBRANZA.nombreLdap());
		List<UsuarioLdapDto> listaReferentesCaja = buscarUsuariosPorPerfil(TipoPerfilEnum.REFERENTE_CAJA.nombreLdap());
		List<UsuarioLdapDto> listaReferentesOperacionesExternas = buscarUsuariosPorPerfil(TipoPerfilEnum.REFERENTE_OPERACIONES_EXTERNAS.nombreLdap());
		List<UsuarioLdapDto> listaAprobadoresOperacionesEspeciales = buscarUsuariosPorPerfil(TipoPerfilEnum.SUPERVISOR_OPERACIONES_ESPECIALES.nombreLdap());
		
		mv.addObject("empresas", empresas);
		mv.addObject("comboEmpresa",(empresas.size() == 0 || empresas.size() > 1));
		mv.addObject("segmentos", segmentos);
		mv.addObject("comboSegmento",(segmentos.size() == 0 || segmentos.size() > 0));
		mv.addObject("estados",listaEstados);
		mv.addObject("comboEstado", (listaEstados.size() == 0 || listaEstados.size() > 1));
		mv.addObject("motivos", motivos);
		mv.addObject("comboMotivo",(motivos.size() == 0 || motivos.size() > 1));
		mv.addObject("mediosPago", listaMediosDePagoSinRepetidosFinal);
		mv.addObject("comboTMP",(listaMediosDePagoSinRepetidosFinal.size() == 0 || listaMediosDePagoSinRepetidosFinal.size() > 1));
		mv.addObject("subtipoCompensacion", listaSubtipoCompensacion);
		mv.addObject("comboSTCOMP",(listaSubtipoCompensacion.size() == 0 || listaSubtipoCompensacion.size() > 1));
		mv.addObject("tipoTratamiento", listaTipoTratamiento);
		mv.addObject("comboTipoTratamiento",(listaTipoTratamiento.size() == 0 || listaTipoTratamiento.size() > 1));
		mv.addObject("criterioBusquedaCliente", Arrays.asList(CriterioBusquedaClienteEnum.values()));
		mv.addObject("comboSistemaDestino",(listaSistemaDestino.size() == 0 || listaSistemaDestino.size() > 1));
		mv.addObject("comboSistemaDestinoAplManual",(listaSistemaDestino.size() == 0 || listaSistemaDestino.size() > 1));
		mv.addObject("tipoSistemaDestino", listaSistemaDestino);
		mv.addObject("tipoSistemaDestinoAplManual", SistemaEnum.getEnumAplicacionManual());
		ObjectMapper mapper = new ObjectMapper();
		mv.addObject("referentesCobranza", mapper.writeValueAsString(listaReferentesCobranza));
		mv.addObject("referentesCaja", mapper.writeValueAsString(listaReferentesCaja));
		mv.addObject("referentesOperacionesExternas", mapper.writeValueAsString(listaReferentesOperacionesExternas));
		mv.addObject("aprobadoresOperacionesEspeciales", mapper.writeValueAsString(listaAprobadoresOperacionesEspeciales));


		return mv;

	}
	/**
	 * @author U572487 Guido.Settecerze (CSS' assistant).
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/autorizacion-aprobacion-cobro")
	public ModelAndView autorizarTareaAprobacionCobro(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(VALOR_AUTORIZACION_OK_VIEW);
		CobroDto cobroDto = cobroOnlineServicio.buscarCobro(Utilidad.stringToBigDecimal(request.getParameter("idCobroFormatiado")).longValue());
		String observacion = request.getParameter("prevObservText2");
		if(Validaciones.isNullOrEmpty(observacion))
			observacion = Utilidad.EMPTY_STRING;
		cobroDto.setObservacion(observacion);
		
		cobroDto.setUsuarioModificacion(userSesion.getIdUsuario());
		cobroDto.setFechaUltimaModificacion(new Date());

		cobroOnlineServicio.modificar(cobroDto);
		cobroOnlineServicio.aprobarCobroCambiarEstadoWorkFlow(cobroDto, userSesion);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));

		mv.addObject("url",BANDEJA_ENTRADA_VIEW_POST);

		return mv;
	}
	
	/**
	 * @author u587496 Guido.Settecerze
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/autorizacion-aprobacion-cobro-manual")
	public ModelAndView confirmarTareaImputacionAplicacionManual(HttpServletRequest request) throws Exception {
//		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(VALOR_AUTORIZACION_OK_VIEW);
		
//		String observacion = request.getParameter("observacion");
//		Long idCobro = new Long(request.getParameter("idCobroFormatiado"));
//		SociedadEnum sociedad =SociedadEnum.getEnumByName(request.getParameter("sociedad"));
//		SistemaEnum sistema = SistemaEnum.getEnumByName(request.getParameter("sistema"));

//		cobroOnlineServicio.confirmaAplicacionManual(idCobro, userSesion, observacion,sociedad,sistema);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));

		mv.addObject("url",BANDEJA_ENTRADA_VIEW_POST);

		return mv;
	}
	
	@RequestMapping(value = "/informa-desapropiacion-cobro-manual")
	public ModelAndView informaTareaDesapropiacionAplicacionManual(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(VALOR_AUTORIZACION_OK_VIEW);
		
		String observacion = request.getParameter("observacion");
		Long idCobro = new Long(request.getParameter("idCobroFormatiado"));
		SociedadEnum sociedad =SociedadEnum.getEnumByName(request.getParameter("sociedad"));
		SistemaEnum sistema = SistemaEnum.getEnumByName(request.getParameter("sistema"));

		cobroOnlineServicio.informaDesapropiacionAplicacionManual(idCobro, userSesion, observacion,sociedad,sistema);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));

		mv.addObject("url",BANDEJA_ENTRADA_VIEW_POST);

		return mv;
	}
	/**
	 * @author U572487 Guido.Settecerze (CSS' assistant).
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rechazar-aprobacion-cobro")
	public ModelAndView rechazarTareaAprobacionCobro(HttpServletRequest request) throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(VALOR_AUTORIZACION_OK_VIEW);
		
		String observaciones = request.getParameter("prevObservText2");
		Long idCobro = Utilidad.stringToBigDecimal(request.getParameter("idCobroFormatiado")).longValue();
		SistemaEnum sistema = null;
		SociedadEnum sociedad = null;
		cobroOnlineServicio.rechazarTareaDeAprobacionCobroOConfirmacionAplicacionManual(idCobro, userSesion, observaciones, sistema, sociedad);

		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		mv.addObject("url",BANDEJA_ENTRADA_VIEW_POST);
		
		return mv;
	}
	
	/**
	 * @author U587496 Guido.Settecerze
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rechazar-aprobacion-cobro-manual")
	public ModelAndView rechazarTareaConfirmacionAplicacionManual(HttpServletRequest request) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(VALOR_AUTORIZACION_OK_VIEW);
		
		String observaciones = request.getParameter("observacion");
		Long idCobro = Utilidad.stringToBigDecimal(request.getParameter("idCobroFormatiado")).longValue();
		SociedadEnum sociedad =SociedadEnum.getEnumByName(request.getParameter("sociedad"));
		SistemaEnum sistema = SistemaEnum.getEnumByName(request.getParameter("sistema"));
		cobroOnlineServicio.rechazarTareaDeAprobacionCobroOConfirmacionAplicacionManual(idCobro, userSesion, observaciones, sistema, sociedad);
		cobroBatchServicio.actualizarReferenciaMediosDePagoCTA(idCobro, sistema, sociedad,null);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		mv.addObject("url", BANDEJA_ENTRADA_VIEW_POST);
		
		return mv;
	}
	
	@RequestMapping(value = "cobro-busqueda/buscarSegmentosCobros", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	List<SelectOptionJsonResponse> buscarSegmentosCobros(
			HttpServletRequest request) throws Exception {
		String idEmpresa = request.getParameter("idEmpresa");
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
				.getAttribute(Constantes.LOGIN);
		List<SelectOptionJsonResponse> result = new ArrayList<SelectOptionJsonResponse>();
		SelectOptionJsonResponse jsonResp = null;
		for (ShvParamSegmento segmento : combosServicio
				.listarSegmentoPorEmpresaYUsuario(idEmpresa, userSesion)) {
			jsonResp = new SelectOptionJsonResponse();
			jsonResp.setValue(segmento.getIdSegmento());
			jsonResp.setText(segmento.getDescripcion());
			result.add(jsonResp);
		}
		return result;
	}

	/**
	 * Devuelve una lista de analistas segun el segmento seleccionado
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cobro-busqueda/buscarAnalistaCobros", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<SelectOptionJsonResponse> buscarAnalistaCobros(HttpServletRequest request) throws Exception {
		
		String idEmpresa = request.getParameter("idEmpresa");
		String idSegmento = request.getParameter("idSegmento");

		List<SelectOptionJsonResponse> result = new ArrayList<SelectOptionJsonResponse>();
		
		List<UsuarioLdapDto> listaDeAnalistasCobranza = buscarUsuariosPorPerfilSegmento(idEmpresa, idSegmento,TipoPerfilEnum.ANALISTA_COBRANZA.nombreLdap());
		
		for (UsuarioLdapDto analista : listaDeAnalistasCobranza) {
			SelectOptionJsonResponse jsonResp = new SelectOptionJsonResponse();
			jsonResp.setValue(analista.getTuid());
			jsonResp.setText(analista.getNombreCompleto());
			/* valido para no devolver duplicados */
			if (!Validaciones.isCollectionNotEmpty(result)){
				result.add(jsonResp);
			} else {
				Boolean encontrado = false;
				for (SelectOptionJsonResponse a : result) {
					if (a.getValue().equalsIgnoreCase(jsonResp.getValue())){
						encontrado = true;
					}
				}
				if (!encontrado){
					result.add(jsonResp);
				}
			}
		}
		
		List<UsuarioLdapDto> listaDeAnalistasOperacionMasiva = buscarUsuariosPorPerfilSegmento(idEmpresa, idSegmento,TipoPerfilEnum.ANALISTA_OPERACION_MASIVA.nombreLdap());
		for (UsuarioLdapDto analista : listaDeAnalistasOperacionMasiva) {
			SelectOptionJsonResponse jsonResp = new SelectOptionJsonResponse();
			jsonResp.setValue(analista.getTuid());
			jsonResp.setText(analista.getNombreCompleto());
			/* valido para no devolver duplicados */
			if (!Validaciones.isCollectionNotEmpty(result)){
				result.add(jsonResp);
			} else {
				Boolean encontrado = false;
				for (SelectOptionJsonResponse a : result) {
					if (a.getValue().equalsIgnoreCase(jsonResp.getValue())){
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
	
	/**
	 * 
	 * @return
	 */
	private List<String> listarEstadosParaBusquedaCobro(){
		
		String enEdicion = "En Edición";
		String enProceso = "En Proceso";
		String pendienteAprobacion = "Pendiente de aprobación";
		String rechazado = "Rechazado";
		String cobrado = "Cobrado";
		String conError = "Con error";
		String anulado = "Anulado";
		
		List<String> lista = new ArrayList<String>();
		lista.add(enEdicion);
		lista.add(enProceso);
		lista.add(anulado);
		lista.add(pendienteAprobacion);
		lista.add(rechazado);
		lista.add(cobrado);
		lista.add(conError);
		
		return lista;
	}


	private List<UsuarioLdapDto> buscarUsuariosPorPerfilSegmento(String empresa, String segmento, String perfil) throws LdapExcepcion {
		String perfilABuscar;
		List<UsuarioLdapDto> listaAnalistaFiltro = new ArrayList<UsuarioLdapDto>();
		if (Constantes.TODOS_LOS_SEGMENTOS.equals(segmento)) {
			perfilABuscar = perfil + "_" + empresa;
		} else {
			perfilABuscar = perfil + "_" + empresa + "_" + segmento;
		}
		Collection<UsuarioLdapDto> listaLdap = ldapServicio.buscarUsuariosPorPerfilEnMemoria(perfilABuscar);

		for (UsuarioLdapDto usuario : listaLdap) {
			listaAnalistaFiltro.add(usuario);
		}
		return listaAnalistaFiltro;
	}
	
	private List<UsuarioLdapDto> buscarUsuariosPorPerfil(String perfil) throws LdapExcepcion {
		List<UsuarioLdapDto> listaAnalistaFiltro = new ArrayList<UsuarioLdapDto>();

		Collection<UsuarioLdapDto> listaLdap = ldapServicio.buscarUsuariosPorPerfilEnMemoria(perfil);
		
		for (UsuarioLdapDto usuario : listaLdap) {
			listaAnalistaFiltro.add(usuario);
		}
		return listaAnalistaFiltro;
	}

	public ILdapServicio getLdapServicio() {
		return ldapServicio;
	}

	public void setLdapServicio(ILdapServicio ldapServicio) {
		this.ldapServicio = ldapServicio;
	}

	/**
	 * Busqueda de cobros - Sprint 5
	 * @param request
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "cobro-busqueda/buscar-cobros", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CobrosJsonResponse buscarCobros(@RequestBody VistaSoporteCobroOnlineFiltro cobroFiltro, HttpServletRequest request) throws Exception {

		CobrosJsonResponse rta = new CobrosJsonResponse();
		
		
		try {
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			
			if(userSesion.getVolviendoABusqueda()){
				cobroFiltro = userSesion.getCobroFiltro();
				userSesion.setVolviendoABusqueda(false);
			}else{
				cobroFiltro.setUsuarioLogeado((UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN));
				userSesion.setCobroFiltro(cobroFiltro);
			}
			
			cobroOnlineValidacionServicio.validarFiltroBusquedaCobros(cobroFiltro);
			List<CobroDto> listaCobrosOnline = new ArrayList<CobroDto>();
			
			if (!Validaciones.isNullOrEmpty(cobroFiltro.getIdEstado())){
				List<Estado> listaEstados = listarEstadosParaBusqueda(cobroFiltro.getIdEstado());
				//TODO - a Fabio zCORRECCION 1 agregar logica al objeto filtro
				cobroFiltro.setListaIdEstados(listaEstados);
			}
			
			if (!Validaciones.isNullOrEmpty(cobroFiltro.getSelectCliente())){
				
				ClienteFiltro clienteFiltro = new ClienteFiltro();
				
				clienteFiltro.setCriterio(cobroFiltro.getSelectCliente());
				clienteFiltro.setBusqueda(cobroFiltro.getTextCliente());
				
				ClienteJsonResponse clienteResponse = this.buscarClientes(clienteFiltro, request);
				
				if (!clienteResponse.isSuccess()) {
					
					List<String> campoError = new ArrayList<String>();
					List<String> codigoLeyenda = new ArrayList<String>();
					
					campoError.add(clienteResponse.getCampoError());
					codigoLeyenda.add(clienteResponse.getDescripcionError());
					
					throw new ValidacionExcepcion(campoError,codigoLeyenda);
					
				}else {
					cobroFiltro.setListaClientesSegunFiltros(clienteResponse.getClientes());
				}
			}
			//ACA
			listaCobrosOnline = cobroOnlineServicio.listarCobrosOnline(cobroFiltro);
			//U562163 - Lo guardo en la sesion para poder comparar y exportar si supera el limite
			request.getSession().setAttribute("cobros",listaCobrosOnline);
			
			
			for (CobroDto cobroDto : listaCobrosOnline) {
				
				cobroDto.setUsuarioModificacion(userSesion.getIdUsuario()); //para workflow de anulacion de cobro
				if ((userSesion.getIdUsuario().equals(cobroDto.getIdAnalista()) || userSesion.getIdUsuario().equals(cobroDto.getIdCopropietario()))){
					cobroDto.setMostrarBotonAnular(validaAnular(cobroDto));
				}else{
					cobroDto.setMostrarBotonAnular(false);
				}				
				
				if (userSesion.getIdUsuario().equals(cobroDto.getIdAnalista())
								|| userSesion.getIdUsuario().equals(cobroDto.getIdCopropietario())
								|| userSesion.getIdUsuario().equals(cobroDto.getIdAnalistaTeamComercial())
								|| userSesion.getEsAnalistaLegajosChequesRechazados()) {
					cobroDto.setMostrarBotonRevertir(validaRevertir(cobroDto));
				} else {
					cobroDto.setMostrarBotonRevertir(false);
				}
				
				if (userSesion.getIdUsuario().equals(cobroDto.getIdAnalista())
						|| userSesion.getIdUsuario().equals(cobroDto.getIdCopropietario())
						|| userSesion.getIdUsuario().equals(cobroDto.getIdAnalistaTeamComercial())
						|| (userSesion.getEsPerfilSupervisorCobranza() && userSesion.getIdSegmentos().contains(cobroDto.getSegmento()))){
					cobroDto.setMostrarBotonModificar(validaModificar(cobroDto));
				} else {
					cobroDto.setMostrarBotonModificar(false);
				}
				
				if(!Estado.DES_DESCOBRADO.equals(cobroDto.getEstadoReversion())) {
					cobroDto.setIdOperacionReversion(null);
				} else if(!SistemaEnum.SHIVA.equals(cobroDto.getOrigenDescobro())) {
					cobroDto.setIdOperacionReversion("SI");
				}
				
				
				//u578936
				if (
					(Estado.COB_ERROR_DESAPROPIACION.equals(cobroDto.getEstado())|| Estado.COB_ERROR_DESAPROPIACION_PARCIAL.equals(cobroDto.getEstado())) &&
					(
						userSesion.getIdUsuario().equals(cobroDto.getIdAnalista()) 
						|| userSesion.getIdUsuario().equals(cobroDto.getIdCopropietario())
						|| userSesion.getIdUsuario().equals(cobroDto.getIdAnalistaTeamComercial())
					) 
				) {
					cobroDto.setDesapropiacionHabilitada(true);
				} else {
					cobroDto.setDesapropiacionHabilitada(false);
				}
				//&&
				//userSesion.getIdUsuario().equals(cobroDto.getIdAnalistaTeamComercial())
				//u578936 FIN
			}
			Utilidad.guionesNull(listaCobrosOnline);
			rta.setAaData(listaCobrosOnline);
			rta.setSuccess(true);
			return rta;	

		} catch (Throwable ex) {
			
			if (ex instanceof ValidacionExcepcion) {
				rta.setAaData(null);
				rta.setSuccess(false);
				
				List<ErrorJson> erroresJson = new ArrayList<ErrorJson>();
				for (int i = 0; i < ((ValidacionExcepcion) ex).getCamposError().size(); i++) {
					ErrorJson eJson = new ErrorJson(((ValidacionExcepcion) ex).getCamposError().get(i),((ValidacionExcepcion) ex).getCodigosLeyenda().get(i));
					erroresJson.add(eJson);
				}
				
				rta.setErrores((ArrayList<ErrorJson>) erroresJson);
//				rta.setCampoError(((ValidacionExcepcion) ex).getCampoError());
//				rta.setDescripcionError(
//						Propiedades.MENSAJES_PROPIEDADES.getString(((ValidacionExcepcion) ex).getLeyenda()));
				
				return rta;
			}
			
			//Otros errores que no sean de la validacion
			Traza.error(getClass(), ex.getMessage(), ex);
			if (ex.getCause()!=null && ex.getCause() instanceof RemoteAccessException) {

				rta.setAaData(null);
				rta.setSuccess(false);
				rta.setCampoError("#errorBusqueda");
				rta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.ws.caida"));
				return rta;
			} 

			rta.setAaData(null);
			rta.setSuccess(false);
			rta.setCampoError("#errorBusqueda");
			rta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.ws.error"));
			return rta;
		}
	}
	
	@RequestMapping("/cobro-anulacion")
	public ModelAndView cobroAnulacion (HttpServletRequest request) throws NegocioExcepcion {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		userSesion.setVolviendoABusqueda(true);// refresca la busqueda al final del proceso
		
		CobroDto cobroDto = new CobroDto(); 
		cobroDto.setIdCobro(new Long(request.getParameter("idCobro")));
		cobroDto.setUsuarioModificacion(userSesion.getIdUsuario());
		
		cobroOnlineServicio.anular(cobroDto);
		ModelAndView mv = new ModelAndView(ACTUALIZACION_EXITOSA_VIEW);
		
		mv.addObject("mensaje",Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobro.busqueda.mensaje.anulacionExitosa"), cobroDto.getIdOperacionFormateado()));
		mv.addObject("url","cobro-busqueda");

		return mv;
	}
	
	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param request
	 * @return
	 * @throws NegocioExcepcion
	 */
	@RequestMapping("/tarea-eliminar")
	public ModelAndView eliminarTareaSegunTipoTarea(HttpServletRequest request) throws NegocioExcepcion {
		
		Long idTarea = null;
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		CobroDto cobroDto = new CobroDto(); 
		cobroDto.setIdCobro(new Long(request.getParameter("idCobro")));
		cobroDto.setUsuarioModificacion(userSesion.getIdUsuario());
		
		if(!Validaciones.isNullOrEmpty(request.getParameter("idTarea"))) {
			 idTarea = Long.valueOf(request.getParameter("idTarea"));
		}
		
		cobroOnlineServicio.eliminarTarea(cobroDto, idTarea);
		
		ModelAndView mv = new ModelAndView(ACTUALIZACION_OK_VIEW);
		
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("eliminarTareaOK.cobro.mensaje.soloTarea"));
		mv.addObject("url","bandeja-de-entrada");
		return mv;
	}
	
	private List<Estado> listarEstadosParaBusqueda(String entrada) {
		List<Estado> l = new ArrayList<Estado>();
		//TODO - a Fabio zCORRECCION 1 Pasar adentro del filtro
		if ("En Edición".contentEquals(entrada)) {
			l.addAll(Estado.obtenerEstadoporFiltro("enEdicion"));
		} else if (entrada.contentEquals("En Proceso")) {
			l.addAll(Estado.obtenerEstadoporFiltro("enProceso"));
		} else if ("Pendiente de aprobación".contentEquals(entrada)) {
			l.addAll(Estado.obtenerEstadoporFiltro("pendienteDeAprobacion"));
		} else if ("Rechazado".contentEquals(entrada)) {
			l.addAll(Estado.obtenerEstadoporFiltro("rechazado"));
		} else if ("Cobrado".contentEquals(entrada)) {
			l.addAll(Estado.obtenerEstadoporFiltro("cobrado"));
		} else if ("Con error".contentEquals(entrada)) {
			l.addAll(Estado.obtenerEstadoporFiltro("conError"));
		} else if ("Anulado".contentEquals(entrada)){
			l.addAll(Estado.obtenerEstadoporFiltro("anulado"));
		}
		return l;
	}
	//ERROR DE CONFIRMACION PARCIAL "con Error"
	
	/**
	 * buscar un cobro para cargar la pantalla de configuracion de cobros
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/buscarConfCobro", method=RequestMethod.POST)
	public @ResponseBody CobroEdicionJsonResponse buscarConfCobro(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		CobroEdicionJsonResponse cobroJsonResponse = new CobroEdicionJsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		boolean esReedicionParcial = false;
		CobroDto cobroResultado = null;
		cobroResultado = cobroOnlineServicio.buscarCobro(cobro.getIdCobro());

		if (!Validaciones.isObjectNull(cobro.getVengoReedicion()) && "TRUE".equalsIgnoreCase(cobro.getVengoReedicion())) {
			esReedicionParcial = true;
			if (!Validaciones.isObjectNull(cobro.getIdTarea()) && cobro.getIdTarea() != 0) {
				cobroResultado.setIdTarea(cobro.getIdTarea());
			}
			cobroResultado = cobroOnlineServicio.logicaAlGuardarErrorCobroErrorApropiacion(cobroResultado, userSesion, esReedicionParcial);
		}
		

	
		cobroResultado.setObservacion(!Validaciones.isNullEmptyOrDash(cobroResultado.getObservacion())?cobroResultado.getObservacion().replaceAll("<br>", "\r\n"):"");
		cobroResultado.setObservacionAnterior(
			Utilidad.formateadoVista(
				cobroOnlineServicio.obtenerObseHistorial(
					cobroResultado,
					null
		)));
		/* Cargo los copropietarios segun empresa y segmento para mostar en el combo */	
		Collection<String> usuariosExcluidos = new ArrayList<String>();
		usuariosExcluidos.add(cobroResultado.getIdAnalista());
		List<SelectOptionJsonResponse> listaCopropietarios =  
				buscarCopropietarios(cobroResultado.getIdEmpresa(), cobroResultado.getIdSegmento(), usuariosExcluidos);
		cobroJsonResponse.setListaCopropietarios(listaCopropietarios);
		/*Semaforo Debitos*/
		String idCobro = cobro.getIdCobro().toString();
		completarCamposDebitoDto(cobroResultado, idCobro);
		/*Semaforo Creditos*/
		for (CobroCreditoDto credito : cobroResultado.getCreditos()) {
			// Completo los atributos 'opeAsocAnalista' y 'marcaPagoCompensacionEnProcesoShiva
			cobroOnlineServicio.setearAtributosPorConsulta(credito, idCobro);
			credito.setSemaforo(cobroOnlineServicio.determinarGestionabilidadDeCredito(credito));
			cobroOnlineServicio.obtenerImporteAUtilizarCredito(credito);
		}
		/*Lista Clientes*/
		List<? extends DTO> guionesNullClientes = Utilidad.guionesNull(new ArrayList<>(cobroResultado.getClientes()));
		Set<ClienteDto> setClienteAux = new HashSet<ClienteDto>();
		for (DTO dto : guionesNullClientes) {
			setClienteAux.add((ClienteDto) dto);
		}
		cobroResultado.setClientes(setClienteAux);
		
		/*Lista Creditos*/
		List<? extends DTO> guionesNull = Utilidad.guionesNull(new ArrayList<>(cobroResultado.getCreditos()));
		Set<CobroCreditoDto> setCobroAux = new HashSet<CobroCreditoDto>();
		for (DTO dto : guionesNull) {
			setCobroAux.add((CobroCreditoDto) dto);
		}
		cobroResultado.setCreditos(setCobroAux);
		
		/*Lista Medios de Pago*/
		guionesNull = Utilidad.guionesNull(new ArrayList<>(cobroResultado.getMedios()));
		Set<CobroMedioDePagoDto> setMediosAux = new HashSet<CobroMedioDePagoDto>();
		for (DTO medioDePagoDto : guionesNull) {
			setMediosAux.add((CobroMedioDePagoDto) medioDePagoDto);
		}
		
		cobroResultado.setMedios(setMediosAux);

		// Ordeno los documentos caps y seteo los colores de renglon
		cobroOnlineServicio.prepararDocumentosCapParaSuVisualizacion(cobroResultado);

		CobroJsonResponse cobroEstado = cobroOnlineServicio.obtenerEstados(
			cobro.getIdCobro(),
			true,
			"",
			"|"
		); 
		cobroResultado.setDescripcionMarca(cobroEstado.getEstado().getMarcaDescripcion());
		
//		if (esReedicionParcial) {
//			
//			cobroResultado.setBtnGuardar(true);
//			//cobroResultado.setSetearFechaAlta(true);
//			idCobroNuevo = cobroOnlineServicio.logicaAlGuardarErrorCobroErrorApropiacion(cobroResultado, userSesion, esReedicionParcial);
//			//cobroResultado = cobroOnlineServicio.buscarCobro(idCobroNuevo);
//			//cobroJsonResponse.setSuccess(true);
//			// Seteo los datos que se modifican en la vista
//			//cobroResultado.setFechaUltimaModificacion(new Date());
//			//cobroResultado.setUsuarioModificacion(Utilidad.getUsuarioSesion(request));
//		} else {
//			
//		}
		cobroJsonResponse.setCobro(cobroResultado);
		cobroJsonResponse.setSuccess(true);
		return cobroJsonResponse;
	}

	private void completarCamposDebitoDto(CobroDto cobroResultado, String idCobro) throws NegocioExcepcion, ShivaExcepcion {
		for (CobroDebitoDto debito : cobroResultado.getDebitos()) {
			// Completo los atributos 'opeAsocAnalista' y 'marcaPagoCompensacionEnProcesoShiva
			cobroOnlineServicio.setearAtributosPorConsulta(debito, idCobro);
			debito.setSemaforo(cobroOnlineServicio.determinarGestionabilidadDeDeuda(debito));
			//cobroOnlineServicio.obtenerSaldoMaximoDebito(debito);
			cobroOnlineServicio.obtenerImporteAUtilizarDebito(debito, cobroResultado.getIdMotivoCobro());
		}
		/*Lista Debitos*/
		List<? extends DTO> guionesNull = Utilidad.guionesNull(new ArrayList<>(cobroResultado.getDebitos()));
		Set<CobroDebitoDto> setDebitoAux = new HashSet<CobroDebitoDto>();
		for (DTO dto : guionesNull) {
			setDebitoAux.add((CobroDebitoDto) dto);
		}
		cobroResultado.setDebitos(setDebitoAux);
	}
	
	private boolean validaAnular(CobroDto cobroDto) {
		
		boolean result = false;
		
		if(Estado.COB_EN_EDICION.equals(cobroDto.getEstado())){
			result = true;
			if (cobroDto.getMarcas().contains(MarcaEnum.SIMULACION_BATCH_EN_PROCESO)) {
				
				result = false;
				if(cobroDto.getMarcas().contains(MarcaEnum.SIMULACION_ONLINE_FINALIZADA_CON_ERROR) || cobroDto.getMarcas().contains(MarcaEnum.SIMULACION_ONLINE_FINALIZADA_CON_EXITO)) {
					result = true;
				}
			}
		}else if (Estado.COB_RECHAZADO.equals(cobroDto.getEstado()) 
					|| Estado.COB_ERROR_COBRO.equals(cobroDto.getEstado()) 
					|| Estado.COB_ERROR_APROPIACION.equals(cobroDto.getEstado())){
			result = true;
		}
		return result;
	}
	
	private boolean validaModificar(CobroDto cobroDto) {
		
		boolean result = true;
		
		if (Estado.COB_PEND_REFERENTE_COBRANZA.equals(cobroDto.getEstado()) || Estado.COB_ANULADO.equals(cobroDto.getEstado()) || Estado.COB_PEND_SUPERVISOR_OPERACIONES_ESPECIALES.equals(cobroDto.getEstado()) ){
			result = false;
		}
		return result;
	}
	
	private boolean validaRevertir(CobroDto cobroDto) {
		
		boolean result = false;
		
		if(Estado.obtenerEstadoporFiltro("cobrado").contains(cobroDto.getEstado())){
			result = true;
		}
		
		if(result && 
				( cobroDto.getTieneDuc() 
						|| ( ( TipoCreditoEnum.LIQUIDOPRODUCTO.getIdTipoMedioPago().equals(cobroDto.getIdTipoMedioPago())	
						|| TipoCreditoEnum.PROVEEDORES.getIdTipoMedioPago().equals(cobroDto.getIdTipoMedioPago()) ) 
						&& Validaciones.isNullEmptyOrDash(cobroDto.getIdContradocumentoCap()) ) ) ) {
			result = false;
		}

		return result;
	}
	
	/**
	 * Busqueda de cobros - Spring 5
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "configuracion-cobro/simularCobro", method = RequestMethod.POST)
	@ResponseBody
	public TransaccionesJsonResponse simularCobro(@RequestBody final CobroDto cobroDto, HttpServletRequest request) throws Exception {

		TransaccionesJsonResponse rta = new TransaccionesJsonResponse();
		try {
			//Disparo la simulacion
			cobroOnlineServicio.simularCobro(cobroDto.getIdCobro());
			boolean validarSimulacionBatchEnProceso = true;
			validarSimulacionBatchEnProceso = cobroOnlineServicio.validarSimulacionBatchEnProceso(cobroDto.getIdCobro());

			//Busco el cobro luego de que la simulacion lo guardo en shv_cob_cobro y pregunto cuantas transacciones tiene
			return cobroOnlineServicio.buscarTransacciones(cobroDto.getIdCobro(), validarSimulacionBatchEnProceso);
		} catch (SimulacionCobroExcepcion e) {
			Traza.error(getClass(), e.getMensajeAuxiliar());
			rta.setDescripcionError(e.getMensajeAuxiliar());
			rta.setSuccess(false);
			return rta;
		} catch (Exception e) {
			Traza.error(getClass(), e.getMessage());
			throw new Exception(e);
		} 
	}	
	@RequestMapping(value = "/cobro-historial")
	public ModelAndView cobroHistorial(HttpServletRequest request)
			throws Exception {
				
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		ModelAndView mv = new ModelAndView(HISTORIAL_VIEW);
		VistaSoporteBusquedaCobroHistorialFiltro filtro = new VistaSoporteBusquedaCobroHistorialFiltro();
		
		//Brian Botones volver
		if(!Validaciones.isNullOrEmpty(request.getParameter("goBack"))) {
			userSesion.getRetorno().remove(0);
			mv.addObject("idVolver", userSesion.getRetorno().get(0));
		}else if (!Validaciones.isNullOrEmpty(request.getParameter("volver"))){
			if(!userSesion.getRetorno().get(0).equals(request.getParameter("volver"))) {
				userSesion.getRetorno().add(0, request.getParameter("volver"));
				mv.addObject("idVolver", request.getParameter("volver"));	
			}else {
				mv.addObject("idVolver", request.getParameter("volver"));
			}
		}
		
		filtro.setIdCobroPadre(request.getParameter("idCobroPadre"));
		List<CobroHistoricoDto> hist = cobroOnlineServicio.obtenerHistorialCobro(filtro);
		
		mv.addObject("idCobro", request.getParameter("idCobro"));
		mv.addObject("idCobroPadre", request.getParameter("idCobroPadre"));
		mv.addObject("idDescobro", request.getParameter("idDescobro"));
		mv.addObject("opcion", request.getParameter("detalleDoAprobacionA"));
		mv.addObject("idOperacionFormateado", request.getParameter("idOperacionFormateado"));
		mv.addObject("listaHistorialCobroDto", Utilidad.guionesNull(hist));
		mv.addObject("nombreArchivo", request.getParameter("nombreArchivo"));
		
//		Logica para Legajos
		mv.addObject("idLegajo", request.getParameter("idLeg"));
		mv.addObject("solapa", request.getParameter("solapa"));
		mv.addObject("idOperacionRelacionada", request.getParameter("idOperacionRelacionada"));
		
		if(!Validaciones.isNullOrEmpty(request.getParameter("formOrigen"))) {
			mv.addObject("formOrigen", request.getParameter("formOrigen"));
		}
		if(!Validaciones.isNullOrEmpty(request.getParameter("idTarea"))) {
			mv.addObject("idTarea", request.getParameter("idTarea"));
			mv.addObject("tipoTarea", request.getParameter("tipoTarea"));

		}
			
//		if(!("").equals(request.getParameter("idVolver")) && !Validaciones.isObjectNull(request.getParameter("idVolver"))){
//			mv.addObject("idVolver", request.getParameter("volver"));
//			mv.addObject("idVolverPrev", request.getParameter("idVolver"));
//			mv.addObject("idOperacionFormateado", request.getParameter("idOperacion"));
//		}else {
//			mv.addObject("idVolver", request.getParameter("volver")); 
//		}
		return mv;
	}
	
	/**
	 * verifico si un cobro esta con marcas Simulación batch finalizada con exito o Simulación online finalizada con exito - Spring 5
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "configuracion-cobro/verficiarSimulacionOk", method = RequestMethod.POST)
	public @ResponseBody CobroJsonResponse verficiarSimulacionOk(@RequestBody final CobroDto cobroDto, HttpServletRequest request) throws Exception {
		CobroJsonResponse rta = new CobroJsonResponse();
		rta.setSuccess(false);
		List<MarcaEnum> marcas = cobroOnlineServicio.obtenerUltimasMarcas(cobroDto.getIdCobro(), null, true);
		List<MarcaEnum> simulacionOk = Arrays.asList( new MarcaEnum[] {
			MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_EXITO,
			MarcaEnum.SIMULACION_ONLINE_FINALIZADA_CON_EXITO
		});
 		for (MarcaEnum marca : marcas) {
			if (simulacionOk.contains(marca)) {
				rta.setSuccess(true);
				break;
			}
		}
 		return rta;
	}
	/**
	 * verifico si un cobro esta con marcas Simulación batch finalizada con exito o Simulación online finalizada con exito - Spring 5
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "configuracion-cobro/obtenerEstados", method = RequestMethod.POST)
	public @ResponseBody CobroJsonResponse obtenerEstados(@RequestBody final CobroDto cobroDto, HttpServletRequest request) throws Exception {
		CobroJsonResponse rta = cobroOnlineServicio.obtenerEstados(cobroDto.getIdCobro(), false, "", "|");
		rta.setSuccess(true);
 		return rta;
	}
	
	/**
	 * Guardo el tratamiento de la diferencia. y verifico si la simulacion del cobro es valida
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/validarSimulacion", method=RequestMethod.POST)
	public @ResponseBody CobroJsonResponse validaSimulacion(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		CobroJsonResponse cobroJsonResponse = new CobroJsonResponse();
		if (Validaciones.isObjectNull(cobro.getIdCobro()) && Validaciones.isObjectNull(cobro.getIdOperacion())) {
			throw new NegocioExcepcion("No se suminitra el id de Operacion del cobro");
		}
		CobroDto cobroResultado = cobroOnlineServicio.buscarCobro(cobro.getIdCobro());
		CobroTratamientoDiferenciaDto cobroTratamientoDiferenciaDto = cobroResultado.getTratamientoDiferencia();
		cobroResultado.setTratamientoDiferencia(cobro.getTratamientoDiferencia());
		
		cobroResultado.setFechaUltimaModificacion(new Date());
		cobroResultado.setUsuarioModificacion(Utilidad.getUsuarioSesion(request));
		
		cobroOnlineServicio.modificar(cobroResultado);

		if (
			!Validaciones.isObjectNull(cobroTratamientoDiferenciaDto.getTipoTratamientoDiferencia()) &&
			TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.name().equals(cobroTratamientoDiferenciaDto.getTipoTratamientoDiferencia())
		) {
			if (
				Validaciones.isObjectNull(cobro.getTratamientoDiferencia()) ||
				(
					!Validaciones.isObjectNull(cobro.getTratamientoDiferencia()) &&
					!TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.name().equals(cobro.getTratamientoDiferencia().getTipoTratamientoDiferencia())
				)
			) {
				if (Validaciones.isCollectionNotEmpty(cobro.getListaComprobanteAplicacionManual())) {
					cobroOnlineServicio.eliminarAdjuntoCobro(cobro.getListaComprobanteAplicacionManual().iterator().next().getIdComprobante());
					cobroJsonResponse.setEliminarComprobanteAplicacionManualPrev(true);
				}
			}
		}

		List<Estado> estados = Arrays.asList(
			new Estado[] {
				Estado.COB_EN_EDICION,
				Estado.COB_RECHAZADO
			}
		);
		if (estados.contains(cobroResultado.getEstado())) {
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			if (!cobroOnlineServicio.validarSimulacion(cobroResultado.getIdCobro(), userSesion.getIdUsuario())) {
				// Valido si la simulacion corresponde a los valores actuales del cobro
				cobroJsonResponse.setSuccess(false);
				cobroJsonResponse.setDescripcionError(
					Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.error.simulacion.no.validad")
				);
			} else {
				cobroJsonResponse.setSuccess(true);
			}
		} else {
			cobroJsonResponse.setSuccess(true);
		}
		cobroJsonResponse.setIdCobro(cobro.getIdCobro());
		cobroJsonResponse.setIdOperacion(cobro.getIdOperacion());
		cobroJsonResponse.setInformacionMensaje(cobroResultado.getEstado().name());
		cobroJsonResponse.setPrimerCobro(false);
		CobroJsonResponse rta = cobroOnlineServicio.obtenerEstados(
			cobroJsonResponse.getIdCobro(),
				false,
				"",
				"|"
		);
		cobroJsonResponse.getEstado().setEstadoDescripcion(rta.getEstado().getEstadoDescripcion());
		cobroJsonResponse.getEstado().setMarcaDescripcion(rta.getEstado().getMarcaDescripcion());
		return cobroJsonResponse;
	}
	
	/**
	 * 
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/persistirDebitos", method=RequestMethod.POST)
	public @ResponseBody CobroJsonResponse persistirDebitos(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		CobroJsonResponse cobroJsonResponse = new CobroJsonResponse();
		if (Validaciones.isObjectNull(cobro.getIdCobro()) && Validaciones.isObjectNull(cobro.getIdOperacion())) {
			throw new NegocioExcepcion("No se suminitra el id de Operacion del cobro");
		}
		CobroDto cobroResultado = cobroOnlineServicio.buscarCobro(cobro.getIdCobro());
		cobroResultado.setDebitos(cobro.getDebitos());
		cobroResultado.setFechaUltimaModificacion(new Date());
		cobroResultado.setUsuarioModificacion(Utilidad.getUsuarioSesion(request));

		cobroOnlineServicio.modificar(cobroResultado);

		cobroJsonResponse.setSuccess(true);
		return cobroJsonResponse;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cobro-busqueda/validarReversion", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody JsonResponse validarReversion(HttpServletRequest request) throws Exception {
	
		JsonResponse rta = new JsonResponse();
		rta.setSuccess(true);

		if(!cobroOnlineServicio.puedeRevertir(Long.parseLong(request.getParameter("idCobro")))){
			rta.setSuccess(false);
			rta.setCampoError("#errorAlRevertirCobro");
			rta.setDescripcionError("El cobro seleccionado ya se encuentra asociado a una reversión en curso");
		}
		return rta;
	}
	
	/**
	 * @author U587496 Guido.Settecerze
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/persistirOtrosDebitos", method=RequestMethod.POST)
	public @ResponseBody CobroJsonResponse persistirOtrosDebitos(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		CobroJsonResponse cobroJsonResponse = new CobroJsonResponse();
		if (Validaciones.isObjectNull(cobro.getIdCobro()) && Validaciones.isObjectNull(cobro.getIdOperacion())) {
			throw new NegocioExcepcion("No se suminitra el id de Operacion del cobro");
		}
		CobroDto cobroResultado = cobroOnlineServicio.buscarCobro(cobro.getIdCobro());
		cobroResultado.setOtrosDebitos(cobro.getOtrosDebitos());
		cobroResultado.setFechaUltimaModificacion(new Date());
		cobroResultado.setUsuarioModificacion(Utilidad.getUsuarioSesion(request));

		cobroOnlineServicio.modificar(cobroResultado);

		cobroJsonResponse.setSuccess(true);
		return cobroJsonResponse;
	}
	
	/**
	 * Modifica el estado del cobro y de sus transacciones para que se puedan enviar a desapropiar
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/desapropiar", method = RequestMethod.POST)
	public ModelAndView desapropiar(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		StringBuilder str = new StringBuilder();
		List<String> idCobroADesapropiar = Arrays.asList(request.getParameter("idsDesapropiar").split(","));
		cobroServicio.solicitarEnviarDesapropiar(idCobroADesapropiar, userSesion.getIdUsuario());
		ModelAndView mv = new ModelAndView(ACTUALIZACION_EXITOSA_VIEW);
		str.append("Se han solicitado enviar a desapropiar los cobros (");
		str.append(request.getParameter("idsOperacion"));
		str.append(") ");
		str.append(" exitosamente ");
		str.append(".");
		mv.addObject("mensaje", str.toString());
		// TODO Maxi. tendria que enviar idVolver por POST pero en la vista ACTUALIZACION EXITOSA
		// es un link el boton de redireccion.  habria que modificar para que el link ejecute un form
		mv.addObject("url", "cobro-busqueda?idVolver=true");
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("cobro-busqueda/exportandoBusquedaCobros")
	public void exportacionDetalleCobro(HttpServletRequest request, HttpServletResponse response) throws Exception {
	//Descomentar cuando lo quieran usar
		List<CobroDto> listaValores = (List<CobroDto>)  request.getSession().getAttribute("cobros");

		cobrosReportsUtils.writeInResponse(IReportsUtils.POI_XLS, response, listaValores, "Busqueda de Cobros", null);
		
		//exportacionBusquedaCobros.generarExportacionBusquedaCobros(response, listaValores);
	}
	
	@RequestMapping(value="detalle-cobro/buscarTransacciones", method=RequestMethod.POST)
	public @ResponseBody TransaccionesJsonResponse buscarTransaccionescobro(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		
		TransaccionesJsonResponse transaccionesResponse = new TransaccionesJsonResponse();

		transaccionesResponse = cobroOnlineServicio.buscarTransacciones(cobro.getIdCobro(), false);
		CobroDto cobroDto = new CobroDto();
		Set<CobroTransaccionDto> listaTransacciones = new HashSet<CobroTransaccionDto>(transaccionesResponse.getAaData());
		cobroDto.setTransacciones(listaTransacciones);
	
		//Muestra sumatoria intereses
		for (CobroTransaccionDto transaccion : transaccionesResponse.getAaData()) {
			// Se copia el comportamiento del front-end definido en shiva-conf-cobro
			if (transaccion.getTrasladarIntereses()) {
				transaccion.setTrasladarInteresesFormateado("SI");
			} else {
				transaccion.setTrasladarInteresesFormateado("NO");
			}
			int resultado = cobroOnlineServicio.validarHabilitacionCampo(transaccion);
			
			if (resultado <= 0) {
				transaccion.setTrasladarInteresesFormateado("-");
				transaccion.setPorcABonificar("-");
				transaccion.setImporteABonificar("-");
			} else if (resultado == 3 || resultado == 4) {
				transaccion.setTrasladarInteresesFormateado("-");
				transaccion.setPorcABonificar("-");
				transaccion.setImporteABonificar("-");
			} else if (resultado == 2 && transaccion.isFechaCobroMenorIgualFechaVto()) {
				transaccion.setTrasladarInteresesFormateado("-");
				transaccion.setPorcABonificar("-");
				transaccion.setImporteABonificar("-");
			} else {
				if(transaccion.getTrasladarIntereses()){
					transaccion.setTrasladarInteresesFormateado("SI");
				} else {
					transaccion.setTrasladarInteresesFormateado("NO");
				}
			}
			//Acuerdo destino cargo
			// Destino Cargo Comienzo
			String data = transaccion.getAcuerdoDestinoCargos();
			if (resultado > 0) {
				
				if (resultado == 3 || resultado == 4) {
					data = transaccion.getAcuerdoDestinoCargos();
					if (resultado == 3) {
						data = "-";
					}
				}
				if ("-".equals(data)) {
					data = "-";
				}
			} else {
				// si es reintegro NO a proxima  factura, se grisa y se borra  el campo
				data = "-";
			}
			
			transaccion.setAcuerdoDestinoCargos(data);
			//Acuerdo destino cargo fin
			//Estado acuerdo
			String dataEstadoAcuerdo = transaccion.getEstadoAcuerdoDestinoCargos();
			 if (resultado > 0) {
//					  cuando el interes es cero, se muestra un guion
				  if(resultado == 3 ||resultado == 4){
					  if(resultado == 3){
						  dataEstadoAcuerdo = "-";
					  }
				  }
			  } else {
//					  si es reintegro NO a proxima factura, se muestra un guion	 
				 
				  dataEstadoAcuerdo = "-";
			}
			transaccion.setEstadoAcuerdoDestinoCargos(dataEstadoAcuerdo); 
			// Es
			TotalAcumuladoresTransacciones intereses = cobroOnlineServicio.calculaTotalInteresesTransacciones(cobroDto);
			
			transaccionesResponse.setTotal(intereses);
		}		

		transaccionesResponse.setAaData(cobroOnlineServicio.formatearImportesTransacciones(transaccionesResponse.getAaData(), cobro.getMonedaOperacion()));
		return transaccionesResponse;
	}
	
	@RequestMapping(value="detalle-cobro/buscarTransaccionesApliManual", method=RequestMethod.POST)
	public @ResponseBody TransaccionesJsonResponse buscarTransaccionesAplicacionManual(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		
		TransaccionesJsonResponse transaccionesFiltradasResponse = new TransaccionesJsonResponse();
		transaccionesFiltradasResponse = cobroOnlineServicio.buscarTransaccionesApliManual(cobro);
		CobroDto cobroDto = new CobroDto();
		Set<CobroTransaccionDto> listaTransacciones = new HashSet<CobroTransaccionDto>(transaccionesFiltradasResponse.getAaData());
		cobroDto.setTransacciones(listaTransacciones);
		
		for (CobroTransaccionDto transaccion : transaccionesFiltradasResponse.getAaData()) {
			// Se copia el comportamiento del front-end definido en shiva-conf-cobro
			if (transaccion.getTrasladarIntereses()) {
				transaccion.setTrasladarInteresesFormateado("SI");
			} else {
				transaccion.setTrasladarInteresesFormateado("NO");
			}
			int resultado = cobroOnlineServicio.validarHabilitacionCampo(transaccion);
			
			if (resultado <= 0) {
				transaccion.setTrasladarInteresesFormateado("-");
				transaccion.setPorcABonificar("-");
				transaccion.setImporteABonificar("-");
			} else if (resultado == 3 || resultado == 4) {
				transaccion.setTrasladarInteresesFormateado("-");
				transaccion.setPorcABonificar("-");
				transaccion.setImporteABonificar("-");
			} else if (resultado == 2 && transaccion.isFechaCobroMenorIgualFechaVto()) {
				transaccion.setTrasladarInteresesFormateado("-");
				transaccion.setPorcABonificar("-");
				transaccion.setImporteABonificar("-");
			} else {
				if(transaccion.getTrasladarIntereses()){
					transaccion.setTrasladarInteresesFormateado("SI");
				} else {
					transaccion.setTrasladarInteresesFormateado("NO");
				}
			}
			//Acuerdo destino cargo
			// Destino Cargo Comienzo
			String data = transaccion.getAcuerdoDestinoCargos();
			if (resultado > 0) {
				
				if (resultado == 3 || resultado == 4) {
					data = transaccion.getAcuerdoDestinoCargos();
					if (resultado == 3) {
						data = "-";
					}
				}
				if ("-".equals(data)) {
					data = "-";
				}
			} else {
				// si es reintegro NO a proxima  factura, se grisa y se borra  el campo
				data = "-";
			}
			
			transaccion.setAcuerdoDestinoCargos(data);
			//Acuerdo destino cargo fin
			//Estado acuerdo
			String dataEstadoAcuerdo = transaccion.getEstadoAcuerdoDestinoCargos();
			 if (resultado > 0) {
//								  cuando el interes es cero, se muestra un guion
				  if(resultado == 3 ||resultado == 4){
					  if(resultado == 3){
						  dataEstadoAcuerdo = "-";
					  }
				  }
			  } else {
//								  si es reintegro NO a proxima factura, se muestra un guion	 
				 
				  dataEstadoAcuerdo = "-";
			}
			transaccion.setEstadoAcuerdoDestinoCargos(dataEstadoAcuerdo); 
			// Es
			TotalAcumuladoresTransacciones intereses = cobroOnlineServicio.calculaTotalInteresesTransacciones(cobroDto);
			
			transaccionesFiltradasResponse.setTotal(intereses);
		}		

		transaccionesFiltradasResponse.setAaData(cobroOnlineServicio.formatearImportesTransacciones(transaccionesFiltradasResponse.getAaData(), cobro.getMonedaOperacion()));
		return transaccionesFiltradasResponse;
	}
	
	@RequestMapping(value="configuracion-cobro/obtenerCotizacion", method=RequestMethod.POST)
	public @ResponseBody CotizacionJsonResponse obtenerCotizacion(@RequestBody final CotizacionFiltro filtro, HttpServletRequest request) throws Exception {
		CotizacionJsonResponse response = new CotizacionJsonResponse();
	
		if (!filtro.validar()) {
			response.setSuccess(false);
			response.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.cotizacion.validacion.input"));
			Traza.error(
				getClass(), 
				Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.cotizacion.validacion.error"), 
					"'obtenerCotizacion'",
					filtro.toString(),
					response.getDescripcionError()
			));
		} else {
			ArrayList<ShvParamCotizacion> listaCotizaciones = new ArrayList<ShvParamCotizacion>();
			
			for (MonedaEnum moneda : MonedaEnum.getEnumU$SyEuros()) {
				filtro.setMonedaProcedencia(moneda);
				List<ShvParamCotizacion> cotizaciones = cotizacionServicio.listar(filtro);
				
				if(!Validaciones.isObjectNull(cotizaciones)) {
					listaCotizaciones.add(cotizaciones.get(0));
				}
			}			
								
			if (listaCotizaciones.isEmpty()) {
				response.setSuccess(false);
				response.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.cotizacion.validacion.noExiste"));
				Traza.error(
					getClass(), 
					Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.cotizacion.validacion.error"), 
						"'obtenerCotizacion'",
						filtro.toString(),
						response.getDescripcionError()
				));
			} else {
				String mensaje = "";
				for (ShvParamCotizacion cotizacion : listaCotizaciones) {
					mensaje += Validaciones.isNullEmptyOrDash(mensaje) ? Utilidad.formatDecimales(cotizacion.getTipoDeCambio(), 7) + " | " + Utilidad.formatDatePicker(cotizacion.getFechaValidezDesde()) + " | " + cotizacion.getMonedaProcedencia().getDescripcion() 
																: "\n" + Utilidad.formatDecimales(cotizacion.getTipoDeCambio(), 7) + " | " + Utilidad.formatDatePicker(cotizacion.getFechaValidezDesde()) + " | " + cotizacion.getMonedaProcedencia().getDescripcion();
				}
				response.setSuccess(true);
				response.setCotizacion(mensaje);
			}
		}
		return response;
	}
	/**********
	 * Documentos CAP
	 **********/
	/**
	 * Busco los documentos CAP
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-cobro/buscarDocumentosCap", method=RequestMethod.POST, produces = "application/json")
	public @ResponseBody CapJsonResponse buscarDocumentosCap(@RequestBody final ConfCobroDocCapFiltro confCobroDocCapFiltro, HttpServletRequest request) throws Exception {
		CapJsonResponse rta = new CapJsonResponse();
		try {
			this.cobroOnlineValidacionServicio.validarFiltroDocumentosCap(confCobroDocCapFiltro);

			// Utilizo esta clase para crar los mensajes de entrada a los servcios sap
			// Para el proceso y agrupado de las partidas en documentos / renglones
			BuilderConsultaSap builder = new BuilderConsultaSap();
			builder.setProveedorCapServicio(proveedorCapServicio);
			builder.addListaClientes(confCobroDocCapFiltro.getClientes());
			builder = this.cobroOnlineServicio.consultaProveedoresSap(builder);

			if (!TipoResultadoEnum.OK.equals(TipoResultadoEnum.getEnumByDescripcionSap(builder.getResultadoProveedor().getResultadoInvocacion()))) {
				rta.setSuccess(false);
				rta.setErrors(builder.getResultadoProveedor().getDescripcionError());
			} else if (builder.getEstado() ==  BuilderConsultaSap.PROVEEDORES_NO_ENCONTRADOS) {
				// No fallo el servicio pero todos los cuit que se enviaron no retornaron proveedores!!!
				rta.setSuccess(false);
				rta.setProveedoresNoEncontrados(builder.getListaErroresCuitNoEncontrados());
			} else {
				rta.setProveedoresNoEncontrados(builder.getListaErroresCuitNoEncontrados());
				confCobroDocCapFiltro.setUsuarioLogeado((UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN));
				builder.setFiltro(confCobroDocCapFiltro);

				// Consulta de partidas
				builder = this.cobroOnlineServicio.consultaDocumentosCap(builder);
				this.cobroOnlineServicio.determinarGestiobabilidadSap(builder, confCobroDocCapFiltro.getIdCobro());
//				Ordeno las partidas documentos - semaforos - renglones - renglones vinculados
				Collections.sort(builder.getListaDosCap(), new ComparatorDocumentoCap());
				builder.setListaDosCap(builder.calcularRenglones(builder.getListaDosCap()));
				// Mapeo valores a los agrupadores, asigno un numero de ordern interno al documento y seteo los colores
				builder.setValoresVisualesinicial();
				// Ordeno los documentos caps por fecha emision desendente y numero de documento
				Collections.sort(builder.getListaDosCap(), new ComparatorDocumentoCapPostAgrupacion());
				// Corro nuevamente para setear colores de linea // Verificar si hace falta
				int cantida = builder.setValoresVisualesinicial();
				// Completo los campos
				rta.setResultado(builder.getListaDosCap());
				List<? extends DTO> guionesNull = Utilidad.guionesNull(rta.getResultado());
				rta.setResultado(new ArrayList<DocumentoCapDto>());
				for (DTO dto : guionesNull) {
					rta.getResultado().add((DocumentoCapDto) dto);
				}
				CreditosControlPaginacion controlPaginacion = new CreditosControlPaginacion();
				controlPaginacion.setCantPorPagina(1);
				controlPaginacion.setCantRegistrosMostrados(cantida);
				controlPaginacion.setTotalRegistros(cantida);
				controlPaginacion.setPaginaActual(1);
				//
				rta.setControlPaginacion(controlPaginacion);
				rta.setSuccess(true);
					
			}
		} catch (ValidacionExcepcion ex) {
			rta.setResultado(null);
			rta.setSuccess(false);
			List<ErrorJson> erroresJson = new ArrayList<ErrorJson>();
			for (int i = 0; i < ((ValidacionExcepcion) ex).getCamposError().size(); i++) {
				ErrorJson eJson = new ErrorJson(((ValidacionExcepcion) ex).getCamposError().get(i),((ValidacionExcepcion) ex).getCodigosLeyenda().get(i));
				erroresJson.add(eJson);
			}
			rta.setErrores((ArrayList<ErrorJson>) erroresJson);
		} catch (NegocioExcepcion ex) {
			rta.setResultado(null);
			rta.setSuccess(false);
			rta.setErrorMensaje(ex.getMessage());
		}
		return rta;
	}
	@RequestMapping("cobro-detalle-aprobacion/generarPdfResumenCompensacion")
	public void generarPdfResumenCompensacio(HttpServletRequest request, HttpServletResponse response, CobroDto cobroDto) throws Exception {
		long idCobro;
		if (cobroDto.getIdCobroFormatiadoJSPDetalle() != null) {
			idCobro = Utilidad.stringToBigDecimal(cobroDto.getIdCobroFormatiadoJSPDetalle()).longValue();
		} else {
			idCobro =  cobroDto.getIdCobro();
		}
		ArchivoByteArray archivo = cobroOnlineServicio.generarPdfResumenCompensacion(idCobro);
		ControlArchivo.descargarComoPDF(
			archivo.getByteArray(),
			archivo.getNombreArchivo(),
			response
		);
		
	}
	@RequestMapping("cobro-detalle-aprobacion/generarPdfCartaCompensacion")
	public void generarPdfCartaCompensacio(HttpServletRequest request, HttpServletResponse response, CobroDto cobroDto) throws Exception {
		long idCobro;
		if (cobroDto.getIdCobroFormatiadoJSPDetalle() != null) {
			idCobro = Utilidad.stringToBigDecimal(cobroDto.getIdCobroFormatiadoJSPDetalle()).longValue();
		} else {
			idCobro =  cobroDto.getIdCobro();
		}
		
		ArchivoByteArray archivo = cobroOnlineServicio.generarPdfCartaCompensacion(idCobro);
		ControlArchivo.descargarComoPDF(
			archivo.getByteArray(),
			archivo.getNombreArchivo(),
			response
		);
	}

	@RequestMapping("cobro/validarCodigoYComprobante")
	public @ResponseBody JsonResponse validarCodigoYComprobante(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		JsonResponse json = new JsonResponse();
		json.setSuccess(Validaciones.isCollectionNotEmpty(userSesion.getComprobantesAGuardar())) ;
//			|| Validaciones.isCollectionNotEmpty(userSesion.getCodigosOperacionesExternasAGuardar())
//		);
		return json;
	}
	

	@RequestMapping(value="configuracion-cobro/buscarCuentaContablePorDefault", method=RequestMethod.POST)
	@ResponseBody
	public CuentaContableJsonResponse buscarCuentaContablePorDefault(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		
		CuentaContableJsonResponse respuesta = new CuentaContableJsonResponse();
		
		String cuentaContable =  cobroOnlineServicio.buscarCuentaContablePorDefault(cobro.getClientes());
		
		if(!Validaciones.isNullOrEmpty(cuentaContable)){
			Traza.auditoria(getClass(), "Se ha obtenido el siguiente número de cuenta contable: " + cuentaContable);
		} else{
			Traza.auditoria(getClass(), "No se ha obtenido número de cuenta contable por default");
		}
		
		respuesta.setCuentaContable(cuentaContable);
		respuesta.setSuccess(true);
		
		return respuesta;
	}
	
	
	
	/**
	 * Me permite adjuntar los comprobantes
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/adjuntarComprobante", method = RequestMethod.POST)
	public void adjuntarComprobante1(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String idCobro = request.getParameter("idCobro");
		String descripcion = request.getParameter("descripcion");
		String motivoAdjunto = request.getParameter("motivoAdjunto");

		Iterator<String> itr =  request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		
		ComprobanteJsonResponse json = new ComprobanteJsonResponse();
		try {
			cobroOnlineValidacionServicio.validarComprobantes(file, descripcion);
			
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			
			ComprobanteDto comprobante = new ComprobanteDto();
			comprobante.setDescripcionArchivo(descripcion);
			comprobante.setUsuarioCreacion(userSesion.getIdUsuario());
			comprobante.setNombreArchivo(file.getOriginalFilename());
			comprobante.setDocumento(file.getBytes());
			
			comprobante.setMotivoAdjunto(motivoAdjunto);
			
			comprobante = cobroOnlineServicio.crearAdjuntoCobro(Long.valueOf(idCobro), comprobante);
			
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
	

	@RequestMapping("cobros/eliminar-codigo-operacion-externa")
	public @ResponseBody CodigoOperacionExternaJsonResponse eliminarCodigoAplicacionManual(@RequestBody final CodigoOperacionExternaDto codigoOperacionExterna, HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		List<CodigoOperacionExternaDto> codigosOperacionesExternasAGuardar = userSesion.getCodigosOperacionesExternasAGuardar();
		CodigoOperacionExternaJsonResponse json = new CodigoOperacionExternaJsonResponse();
		
		Iterator<CodigoOperacionExternaDto> codigosOperacionesAGuardarIter = userSesion.getCodigosOperacionesExternasAGuardar().iterator();

		while (codigosOperacionesAGuardarIter.hasNext()) {
			CodigoOperacionExternaDto codigoOperacionExternaDto = codigosOperacionesAGuardarIter.next();

		    if (codigoOperacionExternaDto.getIdCobCobroCodOperExt().compareTo(codigoOperacionExterna.getIdCobCobroCodOperExt())==0){
		    	codigosOperacionesAGuardarIter.remove();
		    }
		}
		
		userSesion.setCodigosOperacionesExternasAGuardar(codigosOperacionesExternasAGuardar);
		json.setSuccess(true);
		return json;
		
	}
	

	@RequestMapping("cobros/agregar-codigo-operacion-externa")
	public @ResponseBody CodigoOperacionExternaJsonResponse agregarCodigoAplicacionManual(@RequestBody final CodigoOperacionExternaDto codigoOperacionExternaDto, HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		List<CodigoOperacionExternaDto> codigosOperacionesAGuardarFinal = userSesion.getCodigosOperacionesExternasAGuardar();
		
		CodigoOperacionExternaJsonResponse json = new CodigoOperacionExternaJsonResponse();
		
		long fechaID = new Date().getTime();
		codigoOperacionExternaDto.setIdCobCobroCodOperExt(fechaID);
		codigosOperacionesAGuardarFinal.add(codigoOperacionExternaDto);
		
		
		json.setSuccess(true);
		json.setIdCobCobroCodOperExt(fechaID);
		json.setCodigoOperacionExterna(codigoOperacionExternaDto.getCodigoOperacionExterna());
		return json;
		
	}
	@RequestMapping(value="/isAlive", method=RequestMethod.POST)
	public @ResponseBody CobroJsonResponse isAlive(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		CobroJsonResponse json = new CobroJsonResponse();
		json.setSuccess(true);
		return json;
		
	}
	
	@RequestMapping(value="/confirmar-aplicacion-manual", method=RequestMethod.POST)
	public @ResponseBody CodigoOperacionExternaJsonResponse confirmarCobroApliManual(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		CodigoOperacionExternaJsonResponse json = new CodigoOperacionExternaJsonResponse();
		json.setSuccess(true);
		
//		String observacion = request.getParameter("observacion");
//		Long idCobro = new Long(request.getParameter("idCobroFormatiado"));
//		SociedadEnum sociedad =SociedadEnum.getEnumByName(request.getParameter("sociedad"));
//		SistemaEnum sistema = SistemaEnum.getEnumByName(request.getParameter("sistema"));
		
		SistemaEnum sistema = SistemaEnum.getEnumByName(cobro.getSistemaDestinoDescripcion());
		SociedadEnum sociedad = SociedadEnum.getEnumByName(cobro.getSociedad());

		cobroOnlineServicio.confirmaAplicacionManual(cobro.getIdCobro(), userSesion, cobro.getObservacion(),sociedad,sistema,cobro.getListaCodigoOperacionesExternas());
		return json;
	}
	

}