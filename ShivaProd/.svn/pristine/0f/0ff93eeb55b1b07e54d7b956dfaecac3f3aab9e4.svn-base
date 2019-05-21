package ar.com.telecom.shiva.presentacion.controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.CriterioBusquedaClienteEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.SubTipoCompensacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoIdReversionPadreEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.AcuerdoLegado;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.ICobroOnlineValidacionServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.IDescobroValidacionServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroOperacionRelacionada;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoDescobro;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTransaccionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CodigoOperacionExternaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroDocumentoRelacionadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroHistoricoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroTransaccionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.AcuerdoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ClienteJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.CodigoOperacionExternaJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ComprobanteJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.DescobroOperacionesRelacionadasJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.DescobroTransaccionesJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.DescobrosJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ErrorJson;
import ar.com.telecom.shiva.presentacion.bean.dto.json.SelectOptionJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.TransaccionesJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaDescobroHistorialFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroFiltro;
import ar.com.telecom.shiva.presentacion.facade.IClienteFacade;

import com.google.common.collect.Lists;


@Controller
public class DescobroController extends Controlador {
	
	private static final String CONF_DESCOBRO = "descobro/descobro-configuracion";
	private static final String DETALLE_DESCOBRO = "descobro/descobro-detalle";
	private static final String BUSQUEDA_VIEW = "descobro/descobro-busqueda";
	private static final String ACTUALIZACION_EXITOSA_VIEW = "cobro/cobro-actualizacion-exitosa";
	private static final String HISTORIAL_VIEW = "descobro/descobro-historial";
	private static final String DES_CONFIRMACION_VIEW = "descobro/descobro-confirmacion-aplicacion-manual";
	
	@Autowired
	private ICobroOnlineServicio cobroOnlineServicio;
	
	@Autowired
	private ICobroOnlineValidacionServicio cobroOnlineValidacionServicio;
	
	@Autowired
	private IDescobroServicio descobroServicio;
	
	@Autowired
	private IDescobroValidacionServicio descobroValidacionServicio;
	
	@Autowired
	private IClienteSiebelServicio clienteConsultarSiebelServicio;
	
	@Autowired
	private IClienteFacade clienteConsulta;
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/descobro-busqueda")
	public ModelAndView descobroBusqueda(HttpServletRequest request)
			throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(BUSQUEDA_VIEW);
		
		/*
		 * Valida si es una busqueda normal o vuelve de un detallado 
		 */
		if(!Validaciones.isNullOrEmpty(request.getParameter("idVolver"))){
			userSesion.setVolviendoABusqueda(true);	
		} else if (!Validaciones.isNullOrEmpty(request.getParameter("idTarea"))) {
			// valida si es una busqueda lanzada desde la bandeja de entrada
			VistaSoporteDescobroFiltro descobroFiltro = new VistaSoporteDescobroFiltro();
			descobroFiltro.setIdOperacionReversion(request.getParameter("idOperacionDesc"));
			descobroFiltro.setIdEmpresa(request.getParameter("empresaTarea"));
			descobroFiltro.setIdTarea(request.getParameter("idTarea"));
			// descobro-busqueda se tiene que comportar igual que cuando se vuelve desde 
			// el detalle de un cobro o de la edicion de un cobro
			userSesion.setVolviendoABusqueda(true);
			userSesion.setDescobroFiltro(descobroFiltro);
			userSesion.getCaminoDeVuelta().push("regresar-bandeja-de-entrada");
		}
		else{
			userSesion.setDescobroFiltro(new VistaSoporteDescobroFiltro());
			userSesion.setVolviendoABusqueda(false);
		}
		
		if(userSesion.getVolviendoABusqueda()){
			mv.addObject("volviendoABusqueda", true);  	//para volver a busqeuda, que setee TRUE userSesion.getVolviendoABusqueda() y que guarde el ultimo filtro en userSesion asi aca lo toma.
			mv.addObject("descobroBusquedaFiltro", userSesion.getDescobroFiltro());
		}else{
			mv.addObject("volviendoABusqueda", false);
		}
		
		/*
		 * CARGA COMBOS
		 */
		
		List<ShvParamEmpresa> empresas = (List<ShvParamEmpresa>) combosServicio
				.listarEmpresasPorUsuario(userSesion);
		
		List<ShvParamSegmento> segmentos = new ArrayList<ShvParamSegmento>();

		if (empresas.size() == 1) {
			ShvParamEmpresa empresa = empresas.get(0);
			segmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(
					empresa.getIdEmpresa(), userSesion);
		}
		
		List<String> listaEstados = descobroServicio.listarEstadosBusquedaDescobro();
		
		List<ShvParamMotivoDescobro> listaMotivosCompleta = (List<ShvParamMotivoDescobro>) combosServicio
				.listar(ShvParamMotivoDescobro.class);

		List<ShvParamMotivoDescobro> listaMotivos = new ArrayList<ShvParamMotivoDescobro>();
		//Filtra los motivos con operaciones masivas
		for (ShvParamMotivoDescobro motivoCobroEncontrado : listaMotivosCompleta) {
			if (!motivoCobroEncontrado.getUsoOperacionMasiva().getEnum()){
				listaMotivos.add(motivoCobroEncontrado);
			}
		}
		
		Set <TipoCreditoEnum> listaMediosPago = descobroServicio.listarMediosPagoBusquedaDescobros();
		List <SistemaEnum> listaSistemas = SistemaEnum.getEnumMICyCLP();
		List<SubTipoCompensacionEnum> listaSubtipoCompensacion = Arrays.asList(SubTipoCompensacionEnum.values());
		List <TipoTratamientoDiferenciaEnum> listaTratamientoDiferencia = TipoTratamientoDiferenciaEnum.getEnumComboTratamientoDiferenciaCredito();
		listaTratamientoDiferencia.addAll(TipoTratamientoDiferenciaEnum.getEnumComboTratamientoDiferenciaDebito());
		

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
				
				
		mv.addObject("empresas", empresas);
		mv.addObject("comboEmpresa", (empresas.size() == 0 || empresas.size() > 1));
		mv.addObject("segmentos", segmentos);
		mv.addObject("comboSegmento", (segmentos.size() == 0 || segmentos.size() > 0));
		mv.addObject("estados",listaEstados);
		mv.addObject("comboEstado", (listaEstados.size() == 0 || listaEstados.size() > 1));
		mv.addObject("motivos", listaMotivos);
		mv.addObject("comboMotivo",	(listaMotivos.size() == 0 || listaMotivos.size() > 1));
		mv.addObject("mediosPago", listaMediosPago);
		mv.addObject("comboTMP", (listaMediosPago.size() == 0 || listaMediosPago.size() > 1));
		mv.addObject("criterioBusquedaCliente", Arrays.asList(CriterioBusquedaClienteEnum.values()));
		mv.addObject("tratamientoDiferencia", listaTratamientoDiferencia);
		mv.addObject("comboTD",	(listaTratamientoDiferencia.size() == 0 || listaTratamientoDiferencia.size() > 1));
		mv.addObject("sistemaDestino", listaSistemas);
		mv.addObject("comboSD",	(listaSistemas.size() == 0 || listaSistemas.size() > 1));
		mv.addObject("sistemaAplicacionManual", SistemaEnum.getEnumAplicacionManual());
		mv.addObject("comboSistemaAplicacionManual",(SistemaEnum.getEnumAplicacionManual().size() == 0 || SistemaEnum.getEnumAplicacionManual().size() > 1));
		mv.addObject("subtipoCompensacion", listaSubtipoCompensacion);
		mv.addObject("comboSTCOMP",(listaSubtipoCompensacion.size() == 0 || listaSubtipoCompensacion.size() > 1));
		
		return mv;
	}

	@RequestMapping(value = "descobro-busqueda/buscar-descobros", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody DescobrosJsonResponse buscarDescobros(@RequestBody VistaSoporteDescobroFiltro descobroFiltro, HttpServletRequest request) throws Exception {

		DescobrosJsonResponse rta = new DescobrosJsonResponse();
		String estadoParaFiltro = null;
		try {
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			
			if(userSesion.getVolviendoABusqueda()){
				descobroFiltro = userSesion.getDescobroFiltro();
				userSesion.setVolviendoABusqueda(false);
			}else{
				descobroFiltro.setUsuarioLogeado((UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN));
				userSesion.setDescobroFiltro(descobroFiltro);
			}
			
			descobroValidacionServicio.validarFiltroBusquedaDescobros(descobroFiltro);
			List<DescobroDto> listaDescobros = new ArrayList<DescobroDto>();


			if (!Validaciones.isNullOrEmpty(descobroFiltro.getIdEstado())){
				List<Estado> estado = listarEstadosParaBusqueda(descobroFiltro.getIdEstado());
				estadoParaFiltro = descobroFiltro.getIdEstado();
				descobroFiltro.setListaIdEstados(estado);
			}
			
			
			if (!Validaciones.isNullOrEmpty(descobroFiltro.getSelectCliente())){
				
				ClienteFiltro clienteFiltro = new ClienteFiltro();
				
				clienteFiltro.setCriterio(descobroFiltro.getSelectCliente());
				clienteFiltro.setBusqueda(descobroFiltro.getTextCliente());
				
				ClienteJsonResponse clienteResponse = clienteConsulta.consultarCliente(clienteFiltro);
				
				if (!clienteResponse.isSuccess()) {
					
					List<String> campoError = new ArrayList<String>();
					List<String> codigoLeyenda = new ArrayList<String>();
					
					campoError.add(clienteResponse.getCampoError());
					codigoLeyenda.add(clienteResponse.getDescripcionError());
					
					throw new ValidacionExcepcion(campoError,codigoLeyenda);
					
				}else {
					descobroFiltro.setListaClientesSegunFiltros(clienteResponse.getClientes());
				}
			}
			
			listaDescobros = descobroServicio.listarDescobros(descobroFiltro);
					
			for (DescobroDto descobroDto : listaDescobros) {

				descobroDto.setUsuarioModificacion(userSesion.getIdUsuario()); //para workflow de anulacion de cobro
				descobroDto.setMostrarBotonModificar(descobroServicio.validaModificar(descobroDto,userSesion));
				descobroDto.setMostrarBotonAnular(descobroServicio.validaAnular(descobroDto));
				if (Estado.DES_EN_ERROR.equals(descobroDto.getEstado()) &&
						(userSesion.getIdUsuario().equals(descobroDto.getIdAnalista()) 
						|| userSesion.getIdUsuario().equals(descobroDto.getIdCopropietario())
						|| userSesion.getIdUsuario().equals(descobroDto.getIdAnalistaTeamComercial()))) {
					descobroDto.setReversionHabilitada(true);
				} else {
					descobroDto.setReversionHabilitada(false);
				}
			}
			
			descobroFiltro.setIdEstado(estadoParaFiltro);
			
			Utilidad.guionesNull(listaDescobros);
			rta.setAaData(listaDescobros);
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

			throw new Exception(ex);
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/descobro-reversion-configuracion")
	public ModelAndView descobrosConfiguracion(HttpServletRequest request, CobroDto cobroDto, DescobroDto descobroDto,LegajoChequeRechazadoDto legajoChequeRechazadoDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(CONF_DESCOBRO);
		
		boolean reviertoDesdeBusqueda = true;
		if(!descobroDto.isVengoDeLaEdicion() && !Validaciones.isNullOrEmpty(request.getParameter("idCobro"))){
			long idCobro = Utilidad.stringToBigDecimal(request.getParameter("idCobro")).longValue();
			cobroDto = cobroOnlineServicio.buscarCobro(idCobro);
//		Logica para Legajos
		}else if (!Validaciones.isNullOrEmpty(request.getParameter("idOperacionRelacionada"))) {
			cobroDto = cobroOnlineServicio.buscarCobroPorIdOperacion(new Long(request.getParameter("idOperacionRelacionada")));
			mv.addObject("idLegajo", request.getParameter("idLeg"));
			mv.addObject("solapa", request.getParameter("solapa"));
			mv.addObject("idOperacionRelacionada", request.getParameter("idOperacionRelacionada"));
			reviertoDesdeBusqueda = false;
		}
		
		long idOperacionCobro = 0;
		if(!Validaciones.isObjectNull(descobroDto.getIdOperacionCobro())){
			idOperacionCobro = descobroDto.getIdOperacionCobro();
		}else if(!Validaciones.isObjectNull(cobroDto.getIdOperacion())){
			idOperacionCobro = cobroDto.getIdOperacion();
		}
		
		List<String> listaIdReversionPadre = new ArrayList<String>();
		List<ShvCobDescobroOperacionRelacionada> buscarOperacionRelacionadaDescobroPorIdOperacionCobro = 
				descobroServicio.buscarOperacionRelacionadaDescobroPorIdOperacionCobro(idOperacionCobro);
		for (ShvCobDescobroOperacionRelacionada shvCobDescobroOperacionRelacionada : buscarOperacionRelacionadaDescobroPorIdOperacionCobro) {
			if(!Validaciones.isObjectNull(shvCobDescobroOperacionRelacionada.getDescobro()) &&
					!Validaciones.isObjectNull(shvCobDescobroOperacionRelacionada.getDescobro().getOperacion()) &&
					!Validaciones.isObjectNull(shvCobDescobroOperacionRelacionada.getDescobro().getOperacion().getIdOperacion())){
				listaIdReversionPadre.add(shvCobDescobroOperacionRelacionada.getDescobro().getOperacion().getIdOperacion().toString());
			}
		}
		List<CobroDto> listaCobro = new ArrayList<CobroDto>();
		listaCobro.add(cobroDto);
		List<ShvParamEmpresa> empresas = (List<ShvParamEmpresa>) combosServicio.listarEmpresasPorUsuario(userSesion);
		List<ShvParamSegmento> segmentos = new ArrayList<ShvParamSegmento>(); 
		Collection<UsuarioLdapDto> copropietarios = new ArrayList<UsuarioLdapDto>();

		if (empresas.size() == 1) {
			ShvParamEmpresa empresa = empresas.get(0);
			segmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(empresa.getIdEmpresa(),userSesion);
			if (segmentos.size() == 1) {
				ShvParamSegmento segmento = segmentos.get(0);
				Collection<String> usuariosExcluidos = new ArrayList<String>();
				usuariosExcluidos.add(userSesion.getIdUsuario());
				copropietarios = combosServicio.listarCopropietarioCobroPorEmpresaYSegmento(empresa.getIdEmpresa(), segmento.getIdSegmento(), usuariosExcluidos);
			} 
		}

		List<ShvParamMotivoDescobro> listaMotivosCompleta = (List<ShvParamMotivoDescobro>) combosServicio
				.listar(ShvParamMotivoDescobro.class);

		List<ShvParamMotivoDescobro> listaMotivosFiltrados = descobroServicio.filtrarMotivoSegunPerfilYOrigenReversion(listaMotivosCompleta, userSesion, reviertoDesdeBusqueda, cobroDto.getIdAnalista(), cobroDto.getIdCopropietario());
		
		mv.addObject("empresas", empresas);
		mv.addObject("comboEmpresa", (empresas.size() == 0 || empresas.size() > 1));
		mv.addObject("segmentos", segmentos);
		mv.addObject("comboSegmento", (segmentos.size() == 0 || segmentos.size() > 1));
		mv.addObject("copropietarios", copropietarios);
		mv.addObject("comboCopropietarios", (copropietarios.size() == 0 || copropietarios.size() > 1));
		mv.addObject("motivos", listaMotivosFiltrados);
		mv.addObject("comboMotivo", (listaMotivosFiltrados.size() == 0 || listaMotivosFiltrados.size() > 1));
		mv.addObject("idUsuario", userSesion.getIdUsuario());
		mv.addObject("nombreCompletoUsuario", userSesion.getNombreCompleto());
		mv.addObject("comboIdReversionPadre", true);
		listaIdReversionPadre.add(TipoIdReversionPadreEnum.OTRO.getDescripcionCorta());
		//saco duplicados
		HashSet<String> hs = new HashSet<String>();
		hs.addAll(listaIdReversionPadre);
		listaIdReversionPadre.clear();
		boolean validarEstadoIdReversionPadre = false;
		for (String idOperacionDescobro : hs) {
			if(idOperacionDescobro!=TipoIdReversionPadreEnum.OTRO.getDescripcionCorta()){
				validarEstadoIdReversionPadre = descobroServicio.validarEstadoIdReversionPadre(new Long(idOperacionDescobro));
			}
			if(validarEstadoIdReversionPadre){
				listaIdReversionPadre.add(idOperacionDescobro);
			}
		}
		if(!listaIdReversionPadre.contains(TipoIdReversionPadreEnum.OTRO.getDescripcionCorta())){
			listaIdReversionPadre.add(TipoIdReversionPadreEnum.OTRO.getDescripcionCorta());
		}
		
		mv.addObject("monedaOperacion", MonedaEnum.getEnumBySigno2(cobroDto.getMonedaOperacion()).getDescripcion());
		
		mv.addObject("idReversionPadre", listaIdReversionPadre);
		if(!Validaciones.isObjectNull(cobroDto)){
			mv.addObject("idCobro", cobroDto.getIdCobro());
		}else{
			mv.addObject("idCobro", request.getParameter("idCobro"));
		}
		mv.addObject("simulado", "false");
		if(!Validaciones.isObjectNull(listaCobro)) {
			mv.addObject("listaCobro", ((ArrayList<CobroDto>) Utilidad.guionesNull(listaCobro)));
		}
		if(!descobroDto.isVengoDeLaEdicion()){
			mv.addObject("cobroEditable", false);
			if("/descobro-reversion-configuracion".equals(request.getParameter("idVolver"))) {
				mv.addObject("cobroEditable", true);
			}
		}else{
			mv.addObject("cobroEditable", true);
		}
		mv.addObject("prevObservText", descobroDto.getObservacion());
		
		if(!Validaciones.isObjectNull(descobroDto.getDocumentosRelacionados()) && !descobroDto.getDocumentosRelacionados().isEmpty()) {
			Set<DescobroDocumentoRelacionadoDto> listaDocumentosRelacionados = (Set<DescobroDocumentoRelacionadoDto>) Utilidad.guionesNullRecursivo(descobroDto.getDocumentosRelacionados());
			mv.addObject("listaDocRelac", listaDocumentosRelacionados);
		}

		if(!Validaciones.isObjectNull(descobroDto.getListaCodigoDeOperacionesExternas()) && !descobroDto.getListaCodigoDeOperacionesExternas().isEmpty()) {
			mv.addObject("listaCodigoOperacionesExternas", descobroDto.getListaCodigoDeOperacionesExternas());
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
		
		String volverAPantalla = request.getParameter("volverAPantalla");
		if(!Validaciones.isNullEmptyOrDash(volverAPantalla)
				&& Constantes.DESTINO_BUSQUEDA_COBRO.equals(volverAPantalla)){
			mv.addObject("volverAPantalla", volverAPantalla);
		}
		return mv;
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/descobros-reversion-detalle")
	public ModelAndView descobroDetalle(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(DETALLE_DESCOBRO);
		long idDescobro = Utilidad.stringToBigDecimal(request.getParameter("idDescobro")).longValue();
		DescobroDto descobro = descobroServicio.buscarDescobroPorIdDescobro(idDescobro);
		CobroDto cobroDto = cobroOnlineServicio.buscarCobro(descobro.getIdCobro());
		List<CobroDto> listaCobro = new ArrayList<CobroDto>();
		if(cobroDto!=null)
		listaCobro.add(cobroDto);
		DescobrosJsonResponse rta = descobroServicio.obtenerEstados(
				idDescobro,
					false,
					"",
					"|"
			);
		if(!Validaciones.isNullOrEmpty(request.getParameter("volverAPantalla"))){
			mv.addObject("vuelvoABandeja", request.getParameter("volverAPantalla"));
		}
		mv.addObject("estadoDescripcion", rta.getEstado().getEstadoDescripcion());
		mv.addObject("marcaDescripcion", rta.getEstado().getMarcaDescripcion());
		mv.addObject("idOperacionDescobro", descobro.getIdOperacionFormateado());
		mv.addObject("idReversion", descobro.getIdDescobroFormateado());
		mv.addObject("empresas", descobro.getEmpresa());
		mv.addObject("comboEmpresa", true);
		mv.addObject("segmentos", descobro.getSegmento());
		mv.addObject("comboSegmento", true);
		mv.addObject("monedaOperacionDesc", MonedaEnum.getEnumBySigno2(descobro.getMonedaOperacion()).getDescripcion());
		mv.addObject("monedaOperacion", descobro.getMonedaOperacion());
		mv.addObject("copropietarios", descobro.getNombreCopropietario());
		mv.addObject("motivos", descobro.getDescripcionMotivoReversion());
		mv.addObject("comboMotivo", true);
		mv.addObject("idUsuario", userSesion.getIdUsuario());
		mv.addObject("nombreCompletoUsuario", descobro.getNombreAnalista());
		mv.addObject("comboIdReversionPadre", true);
		mv.addObject("idReversionPadre", descobro.getIdDescobroPadre());
		mv.addObject("idReversion", descobro.getIdDescobroFormateado());
		mv.addObject("idCobro", descobro.getIdCobroFormateado());
		mv.addObject("idLegajo", descobro.getIdLegajo());
		
		if(Validaciones.isNullEmptyOrDash(descobro.getObservacion())){
			mv.addObject("prevObservText", Utilidad.formateadoVista(descobroServicio.obtenerObseHistorial(descobro,null)));
		}else{
			mv.addObject("prevObservText", descobro.getObservacion());
		}
		if(listaCobro!=null)
		mv.addObject("listaCobro", ((ArrayList<CobroDto>) Utilidad.guionesNull(listaCobro)));
		if(descobro.getTransacciones()!=null){
			mv.addObject("listaTransacciones", ((ArrayList<CobroTransaccionDto>) Utilidad.guionesNull(new ArrayList<>(descobro.getTransacciones()))));
		}
		if(descobro.getOperacionesRelacionadas() != null) {
			mv.addObject("operacRelac", true);
		}else{
			mv.addObject("operacRelac", false);
		}

		if (Validaciones.isCollectionNotEmpty(descobro.getListaComprobantes())) {
			mv.addObject("listaComprobantes", ((ArrayList<ComprobanteDto>) Utilidad.guionesNull(new ArrayList<>(descobro.getListaComprobantes()))));
		}

		if (Validaciones.isCollectionNotEmpty(descobro.getListaCodigoDeOperacionesExternas())) {
			mv.addObject("listaCodigoOperacionesExternas", ((ArrayList<CodigoOperacionExternaDto>) Utilidad.guionesNull(new ArrayList<>(descobro.getListaCodigoDeOperacionesExternas()))));
		}
		
		mv.addObject("cobroEditable", false);
		
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
	 * Editar cobro en la pantalla de configuracion de cobro, se accede desde la pantalla de busqueda de cobro
	 * @author u572487 Guido.Settecerze
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/descobros-configuracion-edicion")
	public ModelAndView descobrosConfiguracionEdicion(HttpServletRequest request) throws Exception {
		String idDescobro = request.getParameter("idDescobro");
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		DescobroDto descobroDtoDetalle = userSesion.getDescobroDtoDetalle();
		LegajoChequeRechazadoDto legajoDto = null;

		if(!Validaciones.isObjectNull(descobroDtoDetalle)){
			CobroDto cobroDto = cobroOnlineServicio.buscarCobro(descobroDtoDetalle.getIdCobro());
			descobroDtoDetalle.setVengoDeLaEdicion(true);
			ModelAndView mv = descobrosConfiguracion(request, cobroDto, descobroDtoDetalle, legajoDto);
			mv.addObject("idOperacionDescobro", descobroDtoDetalle.getIdOperacionFormateado());
			if(!Validaciones.isNullOrEmpty(idDescobro)){
				mv.addObject("idReversion", idDescobro);
			}
			mv.addObject("cobroEditable", true);

			@SuppressWarnings("unchecked")
			List<ShvParamMotivoDescobro> listaMotivosFiltrados = descobroServicio.filtrarMotivoSegunPerfilYOrigenReversion(
					(List<ShvParamMotivoDescobro>)combosServicio.listar(ShvParamMotivoDescobro.class),
					userSesion,
					true,
					descobroDtoDetalle.getIdAnalista(),
					descobroDtoDetalle.getIdCopropietario(),
					descobroDtoDetalle.getIdMotivoReversion()
			);
			
			mv.addObject("motivos", listaMotivosFiltrados);	
			mv.addObject("comboMotivo", (listaMotivosFiltrados.size() == 0 || listaMotivosFiltrados.size() > 1));
			
			
			
			return mv;
		}else{
			if(!Validaciones.isNullOrEmpty(idDescobro)){

				DescobroDto descobro = descobroServicio.buscarDescobroPorIdDescobro(new Long(idDescobro));
				CobroDto cobroDto = cobroOnlineServicio.buscarCobro(descobro.getIdCobro());
				descobro.setVengoDeLaEdicion(true);
				ModelAndView mv = descobrosConfiguracion(request, cobroDto, descobro, legajoDto);


				//siempre que edito el descobro elimino la tarea asociada si tuviera
				descobroServicio.eliminarTareasSimulacion(descobro.getIdDescobro(), null, userSesion.getIdUsuario());

				mv.addObject("idOperacionDescobro", descobro.getIdOperacionFormateado());
				mv.addObject("idReversion", idDescobro);
				mv.addObject("idLegajo", descobro.getIdLegajo());
				mv.addObject("cobroEditable", true);
				mv.addObject("monedaOperacion", MonedaEnum.getEnumBySigno2(descobro.getMonedaOperacion()).getDescripcion());

				
				@SuppressWarnings("unchecked")
				List<ShvParamMotivoDescobro> listaMotivosFiltrados = descobroServicio.filtrarMotivoSegunPerfilYOrigenReversion(
						(List<ShvParamMotivoDescobro>)combosServicio.listar(ShvParamMotivoDescobro.class),
						userSesion,
						true,
						descobro.getIdAnalista(),
						descobro.getIdCopropietario(),
						descobro.getIdMotivoReversion()
				);
				
				mv.addObject("motivos", listaMotivosFiltrados);	
				mv.addObject("comboMotivo", (listaMotivosFiltrados.size() == 0 || listaMotivosFiltrados.size() > 1));
				
				
				String volverAPantalla = request.getParameter("volverAPantalla");
				if(!Validaciones.isNullEmptyOrDash(volverAPantalla)
						&& Constantes.DESTINO_BANDEJA_ENTRADA.equals(volverAPantalla)){
					mv.addObject("volverAPantalla", volverAPantalla);
				}
				
				return mv;
			} else {
				String mensaje = "El Id Descobro esta vacio";
				Traza.error(getClass(), mensaje);
				throw new Exception(mensaje);
			}
		}
	}
	
	@RequestMapping("/eliminar-tarea-anular-descobro")
	public ModelAndView eliminarTareaAnularDescobro(HttpServletRequest request) throws Exception {
		String idDescobro = request.getParameter("idDescobro");
		
		if(!Validaciones.isNullOrEmpty(idDescobro)){
			
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			
			//Elimino la tarea y anulo el descobro
			descobroServicio.eliminarTareaAnularDescobro(new Long(idDescobro), userSesion.getIdUsuario());
			
			ModelAndView mv = new ModelAndView(ACTUALIZACION_OK_VIEW);
			mv.addObject("mensaje", Mensajes.TAREA_ELIMINADA_OK);
			mv.addObject("url","bandeja-de-entrada");
			return mv;
		} else {
			String mensaje = "El Id Descobro esta vacio";
			Traza.error(getClass(), mensaje);
			throw new Exception(mensaje);
		}
		
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/eliminar-tarea-error-primer-descobro-anular-descobro")
	public ModelAndView eliminarTareaErrorPrimerDescobroAnularDescobro(HttpServletRequest request) throws Exception {
		String idDescobro = request.getParameter("idDescobro");
		
		if(!Validaciones.isNullOrEmpty(idDescobro)){
			
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			
			//Elimino la tarea y anulo el descobro
			descobroServicio.eliminarTareaErrorPrimerDescobroAnularDescobro(new Long(idDescobro), userSesion.getIdUsuario());
			
			ModelAndView mv = new ModelAndView(ACTUALIZACION_OK_VIEW);
			mv.addObject("mensaje", Mensajes.TAREA_ELIMINADA_OK);
			mv.addObject("url","bandeja-de-entrada");
			return mv;
		} else {
			String mensaje = "El Id Descobro esta vacio";
			Traza.error(getClass(), mensaje);
			throw new Exception(mensaje);
		}
		
	}
	
	
	/**
	 * Guardo un descobro reversion
	 * @author u572487 Guido.Settecerze
	 * @param descobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="reversion-descobro/guardarRevDescobro", method=RequestMethod.POST)
	public @ResponseBody DescobrosJsonResponse guardarRevDescobro(@RequestBody final DescobroDto descobro, HttpServletRequest request) throws Exception {

		DescobrosJsonResponse descobrosJsonResponse = new DescobrosJsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);

		if(descobro.isSimulado()) {
			descobroServicio.modificarTransaccion(descobro, null);
		}
		DescobroDto descobroGuardado = descobroServicio.guardarDescobro(descobro, userSesion.getComprobantesAGuardar(), userSesion);
		userSesion.setComprobantesConIdPantalla(descobroGuardado.getListaComprobantes());
		//dejo vacia la lista de comprobantes
		if(descobroGuardado.getGuardadoOk()){
			userSesion.setComprobantesAGuardar(new ArrayList<ComprobanteDto>());
		}
		descobrosJsonResponse.setIdReversion(descobroGuardado.getIdDescobro());
		descobrosJsonResponse.setIdOperacion(descobroGuardado.getIdOperacionDescobro());
		descobrosJsonResponse.setIdCobro(descobroGuardado.getIdCobro());
		descobrosJsonResponse.setSuccess(descobroGuardado.getGuardadoOk());
		descobrosJsonResponse.setPrimerDescobro(descobroGuardado.getPrimerDescobro());
		descobrosJsonResponse.setEdicionSegunEstadoMarca(
				descobroServicio.validarEdicionSegunEstadoMarca(descobroGuardado, userSesion));
		
		descobrosJsonResponse.setEsPerfilSupervisorCobranza(userSesion.getEsPerfilSupervisorCobranza());
		
		DescobrosJsonResponse rta = descobroServicio.obtenerEstados(
				descobroGuardado.getIdDescobro(),
					false,
					"",
					"|"
			);
		descobrosJsonResponse.getEstado().setEstadoDescripcion(rta.getEstado().getEstadoDescripcion());
		descobrosJsonResponse.getEstado().setMarcaDescripcion(rta.getEstado().getMarcaDescripcion());
		return descobrosJsonResponse;

	}
	
	/**
	 * Valido habilitacion del boton simular.
	 * @author u572487 Guido.Settecerze
	 * @param descobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="reversion-descobro/validarBtnHabilitarSimular", method=RequestMethod.POST)
	public @ResponseBody DescobrosJsonResponse validarBtnHabilitarSimular(@RequestBody final DescobroDto descobroDto, HttpServletRequest request) throws Exception {
		
		DescobrosJsonResponse descobrosJsonResponse = new DescobrosJsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		if(!Validaciones.isObjectNull(descobroDto) && !Validaciones.isObjectNull(descobroDto.getIdDescobro())){
			
			descobrosJsonResponse.setSuccess(descobroServicio.validarBtnSimular(descobroDto, userSesion));
		}
		return descobrosJsonResponse;
		
	}
	
	/**
	 * Validar Estado y existencia Del IdReversion Otro.
	 * @author u572487 Guido.Settecerze
	 * @param descobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="reversion-descobro/validarEstadoYExistenciaDelIdReversionOtro", method=RequestMethod.POST)
	public @ResponseBody DescobrosJsonResponse validarEstadoYExistenciaDelIdReversionOtro(@RequestBody final DescobroDto descobroDto, HttpServletRequest request) throws Exception {
		
		DescobrosJsonResponse descobrosJsonResponse = new DescobrosJsonResponse();
		
		if(!Validaciones.isObjectNull(descobroDto) && !Validaciones.isObjectNull(descobroDto.getIdDescobro())){
			
			if(descobroServicio.validarSiExisteElIdReversion(descobroDto.getIdDescobro())){
				descobrosJsonResponse.setSuccess(true);
			}else{
				descobrosJsonResponse.setSuccess(false);
				descobrosJsonResponse.setDescripcionError(Mensajes.DESCOBROS_ERROR_ID_REVERSION_NO_EXISTENTE);
				return descobrosJsonResponse;
			}
			if(descobroServicio.validarEstadoIdReversionPadre(descobroDto.getIdDescobro())){
				descobrosJsonResponse.setSuccess(true);
			}else{
				descobrosJsonResponse.setSuccess(false);
				descobrosJsonResponse.setDescripcionError(Mensajes.DESCOBROS_ERROR_ESTADO);
			}
		}
		return descobrosJsonResponse;
		
	}
	
	/**
	 * Guardo un descobro reversion
	 * @author u572487 Guido.Settecerze
	 * @param descobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="reversion-descobro/guardoDescobroEnSesion", method=RequestMethod.POST)
	public @ResponseBody DescobrosJsonResponse guardoDescobroEnSesion(@RequestBody final DescobroDto descobroDto, HttpServletRequest request) throws Exception {
		
		DescobrosJsonResponse descobrosJsonResponse = new DescobrosJsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		if(!Validaciones.isObjectNull(descobroDto)){
			userSesion.setDescobroDtoDetalle(descobroDto);
			descobrosJsonResponse.setSuccess(true);
		}
		return descobrosJsonResponse;
		
	}
	
	/***************
	 * Comprobantes
	 ***************/
	 
	/**
	 * @author u572487 Guido.Settecerze
	 * Me permite adjuntar los comprobantes
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "configuracion-descobro/adjuntarComprobante", method = RequestMethod.POST)
	public void adjuntarComprobante(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-descobro/descargarComprobante", method=RequestMethod.GET)
	public void descargarComprobante(HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String id = request.getParameter("id");
		if(!Validaciones.isNullOrEmpty(id)){
			List<ComprobanteDto> buscarAdjuntoPorIdAdjunto = descobroServicio.buscarAdjuntoPorIdAdjunto(Long.valueOf(id));
			if(buscarAdjuntoPorIdAdjunto.isEmpty()){
				buscarAdjuntoPorIdAdjunto = userSesion.getComprobantesConIdPantalla();
			}
			if(buscarAdjuntoPorIdAdjunto.isEmpty()){
				buscarAdjuntoPorIdAdjunto = userSesion.getComprobantesAGuardar();
			}

			ComprobanteDto comprobanteADescargar = new ComprobanteDto();
			for (ComprobanteDto comprobante : buscarAdjuntoPorIdAdjunto) {
				if(comprobante.getIdComprobante().toString().equals(id)){
					comprobanteADescargar = comprobante;
				}else if(!Validaciones.isObjectNull(comprobante.getIdPantallaComprobante())){
					if(comprobante.getIdPantallaComprobante().toString().equals(id)){
						comprobanteADescargar = comprobante;
					}
				}
			}
			if (comprobanteADescargar!=null) {
				byte[] file = comprobanteADescargar.getDocumento();
				String fileName = comprobanteADescargar.getNombreArchivo();
				ControlArchivo.descargarComoArchivo(file, fileName, response);
			}
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param comprobante
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-descobro/eliminarComprobante", method=RequestMethod.POST)
	@ResponseBody
	public ComprobanteJsonResponse eliminarComprobante(@RequestBody final ComprobanteDto comprobante, HttpServletRequest request) throws Exception {
		ComprobanteJsonResponse json = new ComprobanteJsonResponse();
		boolean usoElIdPantallaDelComprobante = false;
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		List<ComprobanteDto> buscarAdjuntoPorIdAdjunto = descobroServicio.buscarAdjuntoPorIdAdjunto(comprobante.getIdComprobante());
		if(buscarAdjuntoPorIdAdjunto.isEmpty()){
			buscarAdjuntoPorIdAdjunto = userSesion.getComprobantesConIdPantalla();
			usoElIdPantallaDelComprobante = true;
		}
		if(!buscarAdjuntoPorIdAdjunto.isEmpty()){
			if(usoElIdPantallaDelComprobante){
				for (ComprobanteDto comprobanteDto : buscarAdjuntoPorIdAdjunto) {
					if(comprobanteDto.getIdPantallaComprobante().compareTo(comprobante.getIdComprobante())==0){
						descobroServicio.eliminarAdjuntoDescobro(comprobanteDto.getIdComprobante());
					}
				}
			}else{
				descobroServicio.eliminarAdjuntoDescobro(comprobante.getIdComprobante());
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
	
	/***************
	 *Fin Comprobantes
	 ***************/
	
	@RequestMapping("/descobro-anulacion")
	public ModelAndView descobroAnulacion (HttpServletRequest request) throws NegocioExcepcion {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		userSesion.setVolviendoABusqueda(true);// refresca la busqueda al final del proceso
		
		if(Estado.DES_ERROR_EN_PRIMER_DESCOBRO.name().equals(request.getParameter("idEstado"))){
			descobroServicio.anularDescobroEnErrorEnPrimerDescobro(Utilidad.stringToBigDecimal(request.getParameter("idDescobro")).longValue(), userSesion);
		}else{
			descobroServicio.solicitarAnulacionDeDescobro(Utilidad.stringToBigDecimal(request.getParameter("idDescobro")).longValue(), userSesion);
		}
		
		ModelAndView mv = new ModelAndView(ACTUALIZACION_EXITOSA_VIEW);
		
		mv.addObject("mensaje",Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.busqueda.mensaje.anulacionExitosa"), request.getParameter("idOperacionDescobro")));
		mv.addObject("url","descobro-busqueda");

		return mv;
	}
	
	
	@RequestMapping(value = "/descobro-historial")
	public ModelAndView descobroHistorial(HttpServletRequest request)
			throws Exception {
				
		ModelAndView mv = new ModelAndView(HISTORIAL_VIEW);
		VistaSoporteBusquedaDescobroHistorialFiltro filtro = new VistaSoporteBusquedaDescobroHistorialFiltro();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);

		filtro.setIdDescobro(request.getParameter("idDescobro"));
		List<DescobroHistoricoDto> hist = descobroServicio.obtenerHistorialDescobro(filtro);

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

		mv.addObject("idOperacionDescobro", request.getParameter("idOperacionDescobro"));
		mv.addObject("idDescobro", request.getParameter("idDescobro"));
		mv.addObject("idCobro", request.getParameter("idCobro"));
		mv.addObject("vuelvoABandeja", request.getParameter("vuelvoABandeja"));
		mv.addObject("listaHistorialDescobroDto", Utilidad.guionesNull(hist));
		
		return mv;
	}
	
	
	/**
	 * Simulacion de Descobros Sprint 7
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "configuracion-descobro/simularDescobro", method = RequestMethod.POST)
	@ResponseBody
	public DescobrosJsonResponse simularDescobro(@RequestBody final DescobroDto descobroDto, HttpServletRequest request) throws Exception {
		DescobrosJsonResponse respuesta = new DescobrosJsonResponse();

		try {
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			
			if(descobroDto.isSimulado()) {
				descobroServicio.modificarTransaccion(descobroDto, null);
			}
			
			DescobroDto descobroGuardado = descobroServicio.simularDescobro(descobroDto, userSesion.getComprobantesAGuardar(), userSesion);
			
			userSesion.setComprobantesConIdPantalla(descobroGuardado.getListaComprobantes());
			
			//dejo vacia la lista de comprobantes
			if(descobroGuardado.getGuardadoOk()){
				userSesion.setComprobantesAGuardar(new ArrayList<ComprobanteDto>());
			}
			
			//Siempre aviso al usuario que la simulacion se hace por batch
			//Mando success por si el dia de mañana se agrega una simulacion online
			respuesta.setDescobro(descobroGuardado);
			respuesta.setSuccess(true); 
			respuesta.setDescripcionError(descobroGuardado.getSimularDescobro());
			DescobrosJsonResponse rta = descobroServicio.obtenerEstados(
					descobroGuardado.getIdDescobro(),
						false,
						"",
						"|"
				);
			respuesta.getEstado().setEstadoDescripcion(rta.getEstado().getEstadoDescripcion());
			respuesta.getEstado().setMarcaDescripcion(rta.getEstado().getMarcaDescripcion());
			return respuesta;

		} catch (Exception e) {
			Traza.error(getClass(), e.getMessage());
			throw new Exception(e);
		} 
	}
	
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * Preparar imputacion de Descobros
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "reversion-descobro/prepararDescobro", method = RequestMethod.POST)
	@ResponseBody
	public DescobrosJsonResponse prepararDescobro(@RequestBody final DescobroDto descobroDto, HttpServletRequest request) throws Exception {
		DescobrosJsonResponse respuesta = new DescobrosJsonResponse();

		try {
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			
			if(descobroDto.isSimulado()) {
				descobroServicio.modificarTransaccion(descobroDto, null);
			}
			
			DescobroDto descobroGuardado = descobroServicio.guardarDescobro(descobroDto, userSesion.getComprobantesAGuardar(), userSesion);
			userSesion.setComprobantesConIdPantalla(descobroGuardado.getListaComprobantes());
			
			//dejo vacia la lista de comprobantes
			if(descobroGuardado.getGuardadoOk()){
				userSesion.setComprobantesAGuardar(new ArrayList<ComprobanteDto>());
			}
			
			respuesta.setSuccess(descobroGuardado.getGuardadoOk());
			respuesta.setDescobro(descobroGuardado);
			DescobrosJsonResponse rta = descobroServicio.obtenerEstados(
					descobroGuardado.getIdDescobro(),
						false,
						"",
						"|"
				);
			respuesta.getEstado().setEstadoDescripcion(rta.getEstado().getEstadoDescripcion());
			respuesta.getEstado().setMarcaDescripcion(rta.getEstado().getMarcaDescripcion());
			return respuesta;

		} catch (Exception e) {
			Traza.error(getClass(), e.getMessage());
			throw new Exception(e);
		} 
	}

	/**
	 * Metodo que imputa el cobro sin aprobación.
	 * 
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param DescobroDto
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/imputarDescobro")
	public ModelAndView imputarDescobro(DescobroDto descobroDto, HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		StringBuilder str = new StringBuilder();

		ModelAndView mv = new ModelAndView(ACTUALIZACION_EXITOSA_VIEW);
		
		descobroDto = descobroServicio.imputarDescobro(descobroDto, userSesion);

		str.append("Se ha generado la Reversión ");
		if(!Validaciones.isObjectNull(descobroDto.getIdOperacionDescobro())){
			str.append(descobroDto.getIdOperacionDescobro().toString());
		}
		str.append(" exitosamente - ");
		str.append(descobroDto.getEstado().descripcion());
		str.append(".");
		
		mv.addObject("mensaje", str.toString());
		mv.addObject("url", BANDEJA_ENTRADA_VIEW_GET);
		return mv;
	}
	
	/**
	 * Buscar un descobro para cargar la pantalla de configuracion de descobros /Edicion.
	 * 
	 * @author u572487 Guido.Settecerze
	 * @param cobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-descobro/buscarConfDescobro", method=RequestMethod.POST)
	public @ResponseBody DescobrosJsonResponse buscarConfDescobro(@RequestBody final DescobroDto descobroDto, HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);

		DescobrosJsonResponse descobroJsonResponse = new DescobrosJsonResponse();
		DescobroDto descobroResultado = new DescobroDto(); 
		DescobroDto descobroDtoDetalle = userSesion.getDescobroDtoDetalle();
		boolean logicaDetalle = false;
		boolean habilitarBtnSimular = true;
		
		if (!Validaciones.isObjectNull(descobroDtoDetalle)) {
			descobroResultado = descobroDtoDetalle;
			logicaDetalle = true;
			if(!Validaciones.isObjectNull(descobroResultado.getIdDescobro())) {
				descobroResultado.setIdDescobroFormateado(descobroResultado.getIdDescobro().toString());
			}
			
			Set<DescobroTransaccionDto> transacciones = descobroResultado.getTransacciones();
			if (transacciones != null && !transacciones.isEmpty()) {

				for (DescobroTransaccionDto descobroTransaccionDto : transacciones) {

					if(!descobroTransaccionDto.isHabilitarBtnSimular()){
						habilitarBtnSimular = false;
					}
				}
				descobroJsonResponse.setHabilitarBtnSimular(habilitarBtnSimular);
				descobroJsonResponse.setTransaccionesOK(descobroServicio.verificaErrorEnMensajeTransaccion(transacciones));
				
			}
		}else{

			descobroResultado = descobroServicio.buscarDescobroPorIdDescobro(descobroDto.getIdDescobro());
			Set<DescobroTransaccionDto> transacciones = descobroResultado.getTransacciones();
			descobroJsonResponse.setTransaccionesOK(descobroServicio.verificaErrorEnMensajeTransaccion(descobroResultado.getTransacciones()));

			boolean validarMostrarObservacionAnterior = descobroServicio.validarMostrarObservacionAnterior(descobroResultado.getIdDescobro());
			descobroResultado.setObservacion(!Validaciones.isNullEmptyOrDash(descobroResultado.getObservacion())?descobroResultado.getObservacion().replaceAll("<br>", "\r\n"):"");
			if(validarMostrarObservacionAnterior){
				descobroResultado.setObservacionAnterior(Utilidad.formateadoVista(descobroServicio.obtenerObseHistorial(descobroResultado,null)));
				descobroResultado.setObservacion("");
			}
			
			if(transacciones != null && !transacciones.isEmpty()){

				for (DescobroTransaccionDto descobroTransaccionDto : transacciones) {

					if(!descobroTransaccionDto.isHabilitarBtnSimular()){
						habilitarBtnSimular = false;
					}
				}
				descobroJsonResponse.setHabilitarBtnSimular(habilitarBtnSimular);
			}
		}
		if(!logicaDetalle){


		}
		//		else{
		//			if(descobroResultado.getTransacciones().isEmpty()){
		//				TransaccionesJsonResponse buscarTransacciones = cobroOnlineServicio.buscarTransacciones(descobroDto.getIdCobro(), false);
		//				Set<CobroTransaccionDto> transacciones = new HashSet<CobroTransaccionDto>(buscarTransacciones.getAaData());
		//				descobroResultado.setTransaccionesDelCobro(transacciones);
		//				descobroJsonResponse.setInformacionMensaje("Usar transacciones del cobro");
		//			}
		//		}

		if(MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_EXITO.equals(descobroResultado.getMarca()) 
				|| MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_ERROR.equals(descobroResultado.getMarca())) {
			descobroResultado.setSimulado(true);
		}

		descobroJsonResponse.setListaSistemaTransaccion(this.listarSistemasTransaccion());

		/* Cargo los copropietarios segun empresa y segmento para mostar en el combo */	
		Collection<String> usuariosExcluidos = new ArrayList<String>();
		usuariosExcluidos.add(descobroResultado.getIdAnalista());
		if(!Validaciones.isNullOrEmpty(descobroResultado.getIdSegmento())){
			List<SelectOptionJsonResponse> listaCopropietarios =  
					buscarCopropietarios(descobroResultado.getIdEmpresa(), descobroResultado.getIdSegmento(), usuariosExcluidos);
			descobroJsonResponse.setListaCopropietarios(listaCopropietarios);
		}

		
		if(!Validaciones.isObjectNull(descobroResultado.getIdDescobro())){
			
			DescobrosJsonResponse rta = descobroServicio.obtenerEstados(
					descobroDto.getIdDescobro(),
					false,
					"",
					"|"
					);
			descobroJsonResponse.getEstado().setEstadoDescripcion(rta.getEstado().getEstadoDescripcion());
			descobroJsonResponse.getEstado().setMarcaDescripcion(rta.getEstado().getMarcaDescripcion());
		}
		
		userSesion.setDescobroDtoDetalle(null);
		descobroJsonResponse.setDescobro(descobroResultado);
		descobroJsonResponse.setSuccess(true);
		return descobroJsonResponse;
	}
	
	/**
	 * Busco los copropietarios
	 * 
	 * @author u572487 Guido.Settecerze
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "configuracion-descobro/buscarCopropietarios", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SelectOptionJsonResponse> buscarCopropietarios(HttpServletRequest request) throws Exception {
		String idEmpresa = request.getParameter("idEmpresa");
		String idSegmento = request.getParameter("idSegmento");
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		Collection<String> usuariosExcluidos = new ArrayList<String>();
		usuariosExcluidos.add(userSesion.getIdUsuario());
		
		List<SelectOptionJsonResponse> result = buscarCopropietarios(idEmpresa, idSegmento, usuariosExcluidos);
		return result;
	}
	
	/**
	 * Logica para el checkbox de la busqueda de reversion, cambio de estado wf a pendiente.
	 * 
	 * @author u572487 Guido.Settecerze
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/revertir", method = RequestMethod.POST)
	public ModelAndView revertir(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		StringBuilder str = new StringBuilder();
		String ids[] = request.getParameter("idsReversion").split(",");

		ModelAndView mv = new ModelAndView(ACTUALIZACION_EXITOSA_VIEW);
		
		descobroServicio.revertirDescobrosCheckeados(Arrays.asList(ids), userSesion);
		List<String> buscarIdsOperacionDescobroConIdsDescobro = descobroServicio.buscarIdsOperacionDescobroConIdsDescobro(Arrays.asList(ids));
		str.append("Se han solicitado enviar a revertir el/los descobro/s (");
		Iterator<String> iterator = buscarIdsOperacionDescobroConIdsDescobro.iterator();
		boolean yaEntrePorSegundaVes = false;
		while(iterator.hasNext()){
			if(yaEntrePorSegundaVes){
				str.append(", ".concat(iterator.next()));
			}else{
				str.append(iterator.next());
			}
			yaEntrePorSegundaVes = true;
		}
		str.append(") ");
		str.append(" exitosamente");
		str.append(".");

		mv.addObject("mensaje", str.toString());
		mv.addObject("url", BUSQUEDA_DESCOBRO_VIEW_GET);
		return mv;
	}

	/**
	 * @author u572487 Guido.Settecerze // Subi la funcion al Controlador
	 * @param request
	 * @return
	 * @throws Exception
	 */
//	private @ResponseBody List<SelectOptionJsonResponse> buscarCopropietarios(String idEmpresa, String idSegmento, Collection<String> usuariosExcluidos) throws Exception {
//		
//		List<SelectOptionJsonResponse> result = new ArrayList<SelectOptionJsonResponse>();
//		SelectOptionJsonResponse jsonResp = null;
//		if(!Validaciones.isNullEmptyOrDash(idSegmento)){
//			for (UsuarioLdapDto copropietario : combosServicio.listarCopropietarioCobroPorEmpresaYSegmento(idEmpresa, idSegmento, usuariosExcluidos)) {
//				jsonResp = new SelectOptionJsonResponse();
//				jsonResp.setValue(copropietario.getTuid());
//				jsonResp.setText(copropietario.getNombreCompleto());
//				result.add(jsonResp);
//			}
//		}
//		return result;
//	}
	
	@RequestMapping("descobro-detalle-aprobacion/exportacionDetalleDescobro")
	public ModelAndView exportacionDetalleDescobro(HttpServletRequest request, HttpServletResponse response, DescobroDto descobroDto) throws Exception {
		
		if(!Validaciones.isObjectNull(descobroDto.getIdDescobro())
				&& !Validaciones.isObjectNull(descobroDto.getIdCobro())){
			
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			
			descobroServicio.exportarDescobro(descobroDto, userSesion.getComprobantesAGuardar(), userSesion, response);
		}
		
		return null;
	}
	
	
	/**
	 * @author u579607
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-descobro/validarAcuerdoContracargo", method=RequestMethod.POST)
	@ResponseBody
	public AcuerdoJsonResponse validarAcuerdoContracargo(@RequestBody final DescobroTransaccionDto transaccion, HttpServletRequest request) throws Exception {

		AcuerdoJsonResponse respuesta = new AcuerdoJsonResponse();
		AcuerdoLegado acuerdoLegado = new AcuerdoLegado();
		List<String> listaIdClientesLegado = new ArrayList<String>();
		
		if(!Validaciones.isNullEmptyOrDash(transaccion.getAcuerdoFactDestinoContracargos())) {
				if(Validaciones.isNumeric(transaccion.getAcuerdoFactDestinoContracargos())) {
					List<ClienteDto> listaClientes = cobroOnlineServicio.listarClientesCobro(transaccion.getIdCobro());
					
					for (ClienteDto lista : listaClientes) {
						listaIdClientesLegado.add(lista.getIdClienteLegado());
					}
					
					acuerdoLegado = descobroServicio.validarAcuerdoContraCliente(transaccion.getSistemaAcuerdoFactDestinoContracargos(),
							transaccion.getAcuerdoFactDestinoContracargos(),listaIdClientesLegado);
					
					//convertir acuerdoLegado a respuestaJSON
					if(Validaciones.isNullOrEmpty(acuerdoLegado.getMensaje())) {
						respuesta.setSuccess(true);
						respuesta.setEstadoAcuerdo(acuerdoLegado.getEstado().descripcion());
						respuesta.setClienteAcuerdo(acuerdoLegado.getIdClienteLegado().toString());
						respuesta.setDescripcionError("-");
					}else {
						respuesta.setSuccess(false);
						respuesta.setEstadoAcuerdo("-");
						respuesta.setClienteAcuerdo("-");
						respuesta.setDescripcionError(acuerdoLegado.getMensaje());
					}
				}else {
					respuesta.setSuccess(false);
					respuesta.setEstadoAcuerdo("-");
					respuesta.setClienteAcuerdo("-");
					respuesta.setDescripcionError("-");
					respuesta.setErrorCampoNull("Este campo debe ser numerico.");
				}
		}else {
			respuesta.setSuccess(false);
			respuesta.setEstadoAcuerdo("-");
			respuesta.setClienteAcuerdo("-");
			respuesta.setDescripcionError("-");
			respuesta.setErrorCampoNull("Este campo es requerido.");
		}
		return respuesta;
	}
	
	private List<SelectOptionJsonResponse> listarSistemasTransaccion (){
		
		List<SelectOptionJsonResponse> listaSistemas = new ArrayList <SelectOptionJsonResponse>();
		
		SelectOptionJsonResponse mic = new SelectOptionJsonResponse();
		mic.setValue(SistemaEnum.MIC.name());
		mic.setText(SistemaEnum.MIC.getDescripcion());
		
		SelectOptionJsonResponse clp = new SelectOptionJsonResponse();
		clp.setValue(SistemaEnum.CALIPSO.name());
		clp.setText(SistemaEnum.CALIPSO.getDescripcion());
		
		listaSistemas.add(mic);
		listaSistemas.add(clp);
		
		return listaSistemas;
	}
	
	/**
	 * Metodo que valida con que campos se puede interactuar en la edicion segun Estado y Marca.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param descobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-descobro/validarEdicionDescobro", method=RequestMethod.POST)
	public @ResponseBody DescobrosJsonResponse validarEdicionDescobro(@RequestBody final DescobroDto descobroDto, HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		DescobrosJsonResponse descobrosJsonResponse = new DescobrosJsonResponse();

		String validarEdicionSegunEstadoMarca = descobroServicio.validarEdicionSegunEstadoMarca(descobroDto, userSesion);

		descobrosJsonResponse.setInformacionMensaje(validarEdicionSegunEstadoMarca);
//		descobrosJsonResponse.getDescobro().setMarca(descobroDto.getMarca());
		
		return descobrosJsonResponse;
	}
	
	/**
	 * Metodo que valida que se permite editar segun perfil.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param descobro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="configuracion-descobro/validarEdicionDescobroSegunUsuario", method=RequestMethod.POST)
	public @ResponseBody DescobrosJsonResponse validarEdicionDescobroSegunUsuario(@RequestBody final DescobroDto descobro, HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		DescobrosJsonResponse descobroJsonResponse = new DescobrosJsonResponse();
		
		descobroJsonResponse.setSuccess(userSesion.getEsPerfilSupervisorCobranza());
		
		return descobroJsonResponse;
	}
	
	@RequestMapping(value="configuracion-descobro/buscarTransaccionesCobro", method=RequestMethod.POST)
	public @ResponseBody DescobroTransaccionesJsonResponse buscarTransaccionesCobro(@RequestBody final CobroDto cobroDto, HttpServletRequest request) throws Exception {
		
		DescobroTransaccionesJsonResponse descobroTransacciones = new DescobroTransaccionesJsonResponse();
		TransaccionesJsonResponse transacciones = cobroOnlineServicio.buscarTransacciones(cobroDto.getIdCobro(), false);

		descobroTransacciones.setListaSistemaTransaccion(this.listarSistemasTransaccion());
		descobroTransacciones.setAaData(descobroServicio.transaccionesMapeo(transacciones.getAaData()));
		descobroTransacciones.setSuccess(transacciones.isSuccess());
		return descobroTransacciones;
	}
	
	@RequestMapping(value="detalle-descobro/buscarOperacionesRelacionadas", method=RequestMethod.POST)
	public @ResponseBody DescobroOperacionesRelacionadasJsonResponse buscarOperacionesRelacionadasDescobro(@RequestBody final DescobroDto descobro, HttpServletRequest request) throws Exception {
		
		DescobroOperacionesRelacionadasJsonResponse descobroOperacRelac = new DescobroOperacionesRelacionadasJsonResponse();
		DescobroDto buscarDescobroPorIdDescobro = new DescobroDto();
		buscarDescobroPorIdDescobro = descobroServicio.buscarDescobroPorIdDescobro(descobro.getIdDescobro());
		descobroOperacRelac.setAaData(descobroServicio.buscarOperacionesRelacionadas(descobro.getIdDescobro()));
		descobroOperacRelac.setTransacciones(descobroServicio.formatearImportesTransacciones(buscarDescobroPorIdDescobro.getTransacciones(),descobro.getMonedaOperacion()));

		if(Validaciones.isCollectionNotEmpty(buscarDescobroPorIdDescobro.getDocumentosRelacionados())){
			descobroOperacRelac.setListaDocumentosRelacionados(Lists.newArrayList(buscarDescobroPorIdDescobro.getDocumentosRelacionados()));
		}
		descobroOperacRelac.setSuccess(true);
		return descobroOperacRelac;
	}
	
	private List<Estado> listarEstadosParaBusqueda(String entrada){
		List<Estado> estado = new ArrayList<Estado>();

		if ("En Edición".contentEquals(entrada)) {
			estado.add(Estado.DES_EN_EDICION);
		} else if (entrada.contentEquals("En Proceso")) {
			estado.add(Estado.DES_DESCOBRO_PROCESO);
			estado.add(Estado.DES_PEND_PROCESAR_MIC);
			estado.add(Estado.DES_PENDIENTE_MIC);
			estado.add(Estado.DES_PENDIENTE);
			estado.add(Estado.DES_PENDIENTE_CONFIRMACION_MANUAL);
		} else if ("Descobrada".contentEquals(entrada)) {
			estado.add(Estado.DES_DESCOBRADO);	
		} else if ("Con Error".contentEquals(entrada)) {
			estado.add(Estado.DES_EN_ERROR);
			estado.add(Estado.DES_ERROR_EN_PRIMER_DESCOBRO);
		} else if ("Anulada".contentEquals(entrada)) {
			estado.add(Estado.DES_ANULADO);
		}
		return estado;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/descobros-confirmacion-aplicacion-manual")
	public ModelAndView descobroConfirmacionManual(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(DES_CONFIRMACION_VIEW);
		long idDescobro = Utilidad.stringToBigDecimal(request.getParameter("idDescobro")).longValue();
		DescobroDto descobro = descobroServicio.buscarDescobroPorIdDescobro(idDescobro);
		CobroDto cobroDto = cobroOnlineServicio.buscarCobro(descobro.getIdCobro());
		List<CobroDto> listaCobro = new ArrayList<CobroDto>();
		if(cobroDto!=null)
		listaCobro.add(cobroDto);
		DescobrosJsonResponse rta = descobroServicio.obtenerEstados(
				idDescobro,
					false,
					"",
					"|"
			);
		if(!Validaciones.isNullOrEmpty(request.getParameter("volverAPantalla"))){
			mv.addObject("vuelvoABandeja", request.getParameter("volverAPantalla"));
		}
		mv.addObject("estadoDescripcion", rta.getEstado().getEstadoDescripcion());
		mv.addObject("marcaDescripcion", rta.getEstado().getMarcaDescripcion());
		mv.addObject("idOperacionDescobro", descobro.getIdOperacionFormateado());
		mv.addObject("idReversion", descobro.getIdDescobroFormateado());
		mv.addObject("sistema", request.getParameter("sistemaDescobro"));
		mv.addObject("sociedad", request.getParameter("sociedadDescobro"));
		mv.addObject("empresas", descobro.getEmpresa());
		mv.addObject("comboEmpresa", true);
		mv.addObject("segmentos", descobro.getSegmento());
		mv.addObject("comboSegmento", true);
		mv.addObject("monedaOperacionDesc", MonedaEnum.getEnumBySigno2(descobro.getMonedaOperacion()).getDescripcion());
		mv.addObject("monedaOperacion", descobro.getMonedaOperacion());
		mv.addObject("copropietarios", descobro.getNombreCopropietario());
		mv.addObject("motivos", descobro.getDescripcionMotivoReversion());
		mv.addObject("comboMotivo", true);
		mv.addObject("idUsuario", userSesion.getIdUsuario());
		mv.addObject("nombreCompletoUsuario", descobro.getNombreAnalista());
		mv.addObject("comboIdReversionPadre", true);
		mv.addObject("idReversionPadre", descobro.getIdDescobroPadre());
		mv.addObject("idReversion", descobro.getIdDescobroFormateado());
		mv.addObject("idCobro", descobro.getIdCobroFormateado());
		mv.addObject("idLegajo", descobro.getIdLegajo());
		
		if(Validaciones.isNullEmptyOrDash(descobro.getObservacion())){
			mv.addObject("prevObservText", Utilidad.formateadoVista(descobroServicio.obtenerObseHistorial(descobro,null)));
		}else{
			mv.addObject("prevObservText", descobro.getObservacion());
		}
		if(listaCobro!=null)
		mv.addObject("listaCobro", ((ArrayList<CobroDto>) Utilidad.guionesNull(listaCobro)));
		if(descobro.getTransacciones()!=null){
			mv.addObject("listaTransacciones", ((ArrayList<CobroTransaccionDto>) Utilidad.guionesNull(new ArrayList<>(descobro.getTransacciones()))));
		}
		if(descobro.getOperacionesRelacionadas() != null) {
			mv.addObject("operacRelac", true);
		}else{
			mv.addObject("operacRelac", false);
		}

		if(Validaciones.isCollectionNotEmpty(descobro.getListaComprobantes())){
			mv.addObject("listaComprobantes", ((ArrayList<ComprobanteDto>) Utilidad.guionesNull(new ArrayList<>(descobro.getListaComprobantes()))));
		}
		
		mv.addObject("cobroEditable", false);
		
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
	
	@RequestMapping(value="/confirmar-aplicacion-manual-descobro", method=RequestMethod.POST)
	public @ResponseBody DescobrosJsonResponse confirmarCobroApliManual(@RequestBody final DescobroDto descobro, HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		DescobrosJsonResponse json = new DescobrosJsonResponse();
		json.setSuccess(true);
		
		SistemaEnum sistema = SistemaEnum.getEnumByName(descobro.getSistemaDestinoDescripcion());
		SociedadEnum sociedad = SociedadEnum.getEnumByName(descobro.getSociedad());

		descobroServicio.confirmaAplicacionManual(descobro.getIdDescobro(), userSesion, descobro.getObservacion(),sociedad,sistema);
		return json;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/autorizacion-aprobacion-descobro-manual")
	public ModelAndView confirmarTareaImputacionAplicacionManual(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView(VALOR_AUTORIZACION_OK_VIEW);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		mv.addObject("url",BANDEJA_ENTRADA_VIEW_POST);

		return mv;
	}
	
	@RequestMapping("descobros/cancelar-aplicacion-manual")
	public @ResponseBody DescobrosJsonResponse cancelarAplicacionManual(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		DescobrosJsonResponse json = new DescobrosJsonResponse();
		userSesion.setComprobantesAGuardar(new ArrayList<ComprobanteDto>());
		userSesion.setCodigosOperacionesExternasAGuardar(new ArrayList<CodigoOperacionExternaDto>());
		json.setSuccess(true);
		return json;
		
	}

	@RequestMapping("descobros/agregar-codigo-operacion-externa")
	public @ResponseBody CodigoOperacionExternaJsonResponse agregarCodigoAplicacionManual(@RequestBody final CodigoOperacionExternaDto codigoOperacionExternaDto, HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		List<CodigoOperacionExternaDto> codigosOperacionesAGuardarFinal = userSesion.getCodigosOperacionesExternasAGuardar();
		
		CodigoOperacionExternaJsonResponse json = new CodigoOperacionExternaJsonResponse();
		
		long fechaID = new Date().getTime();
		codigoOperacionExternaDto.setIdCobDescobroCodOperExt(fechaID);
		codigosOperacionesAGuardarFinal.add(codigoOperacionExternaDto);
		
		
		json.setSuccess(true);
		json.setIdCobDescobroCodOperExt(fechaID);
		json.setCodigoOperacionExterna(codigoOperacionExternaDto.getCodigoOperacionExterna());
		return json;
		
	}
	
	@RequestMapping("descobros/eliminar-codigo-operacion-externa")
	public @ResponseBody CodigoOperacionExternaJsonResponse eliminarCodigoAplicacionManual(@RequestBody final CodigoOperacionExternaDto codigoOperacionExterna, HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		List<CodigoOperacionExternaDto> codigosOperacionesExternasAGuardar = userSesion.getCodigosOperacionesExternasAGuardar();
		CodigoOperacionExternaJsonResponse json = new CodigoOperacionExternaJsonResponse();
		
		Iterator<CodigoOperacionExternaDto> codigosOperacionesAGuardarIter = userSesion.getCodigosOperacionesExternasAGuardar().iterator();

		while (codigosOperacionesAGuardarIter.hasNext()) {
			CodigoOperacionExternaDto codigoOperacionExternaDto = codigosOperacionesAGuardarIter.next();

		    if (codigoOperacionExternaDto.getIdCobDescobroCodOperExt().compareTo(codigoOperacionExterna.getIdCobDescobroCodOperExt())==0){
		    	codigosOperacionesAGuardarIter.remove();
		    }
		}
		
		userSesion.setCodigosOperacionesExternasAGuardar(codigosOperacionesExternasAGuardar);
		json.setSuccess(true);
		return json;
		
	}
	
	@RequestMapping("descobro/validarCodigoYComprobante")
	public @ResponseBody DescobrosJsonResponse validarCodigoYComprobante(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		DescobrosJsonResponse json = new DescobrosJsonResponse();
		json.setSuccess(Validaciones.isCollectionNotEmpty(userSesion.getComprobantesAGuardar()) || Validaciones.isCollectionNotEmpty(userSesion.getCodigosOperacionesExternasAGuardar()));
		return json;
		
	}
	
}

