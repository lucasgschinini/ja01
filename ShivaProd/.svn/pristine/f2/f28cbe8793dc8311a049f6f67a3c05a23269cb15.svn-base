package ar.com.telecom.shiva.presentacion.controlador;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
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
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import ar.com.telecom.shiva.base.anotaciones.ControlProcesoTransacciones;
import ar.com.telecom.shiva.base.comparador.UsuarioLdapDtoComparator;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.CriterioBusquedaClienteEnum;
import ar.com.telecom.shiva.base.enumeradores.EnviarMailBoletaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.ImprimirBoletaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoSuspensionEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ConcurrenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;
import ar.com.telecom.shiva.negocio.dto.BancosParametroDto;
import ar.com.telecom.shiva.negocio.dto.ComboAcuerdoBancoCuentaGralDto;
import ar.com.telecom.shiva.negocio.dto.TipoValorDto;
import ar.com.telecom.shiva.negocio.reportes.modulos.ValoresReportsUtils;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoChequeRechazado;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCuenta;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoSuspension;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrigen;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoValor;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ActualizacionExitosaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ResultadoBusqValoresJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorBusquedaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorHistoricoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValoresDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ClienteJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.CobroJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ComprobanteJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.SelectOptionJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ValorJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaConValorFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.validacion.BoletaConValorValidacionWeb;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.CurrencyEditor;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.CustomBindingErrorProcessor;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.IntegerEditor;
import ar.com.telecom.shiva.presentacion.facade.IClienteFacade;
import ar.com.telecom.shiva.presentacion.facade.ICombosFacade;

@Controller
public class ValorController extends Controlador {

	private static final String ALTA_BOLETA_VIEW = "valor/valores-boletas-alta";
	private static final String ALTA_AVISO_VIEW = "valor/valores-avisos-alta";
	private static final String BUSQUEDA_VIEW = "valor/valores-busqueda";
	private static final String MODIFICACION_VIEW = "valor/valores-modificacion";
	private static final String HISTORIAL_VIEW = "valor/valores-historial-cambios";
	private static final String AUTORIZACION_VIEW = "valor/valores-autorizacion";
	private static final String CAMBIO_ESTADO_VIEW = "valor/valores-cambio-estado";
	private static final String CAMBIO_ESTADO_OK_VIEW = "valor/valores-cambio-estado-exitosa";
	private static final String CONFIRMACION_AVISO_PAGO_VIEW = "valor/valores-confimacion-aviso-pago";
	
	private static final String SELECT_EMPRESAS = "empresas";
	private static final String OBJECT_COMMAND = "valoresDto";
	
	private static final String VALOR_PARA_BOLETA_CHEQUE = TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValorString();
	private static final String VALOR_PARA_BOLETA_CHEQUE_PD = TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValorString();
	private static final String VALOR_PARA_BOLETA_EFECTIVO = TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValorString();
	private static final String VALOR_CHEQUE = TipoValorEnum.CHEQUE.getIdTipoValorString();
	private static final String VALOR_CHEQUE_PD = TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValorString();
	private static final String VALOR_PARA_TRANSFERENCIA = TipoValorEnum.TRANSFERENCIA.getIdTipoValorString();
	private static final String VALOR_PARA_INTERDEPOSITO = TipoValorEnum.INTERDEPÓSITO.getIdTipoValorString();
	private static final String VALOR_PARA_RETENCION = TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValorString();
	
	private static final String MODIFICACION_RECHAZADA_SUPERVISOR = "Modificacion Rechazada Supervisor";
	private static final String MODIFICACION_RECHAZADA = "Modificacion Rechazada";
	private static final String MODIFICACION = "Modificacion";
	private static final String MODIFICACION_SUSPENDIDO_ADMINISTRADOR = "Modificacion Suspendida Administrador";
	private static final String MODIFICACION_ADMINISTRADOR = "Modificacion No Suspendida Administrador";
	private static final String MODIFICACION_VACIA = "Ninguna Modificacion";
	
	@Autowired
	private IValorServicio boletaConValorServicio;
	@Autowired
	private BoletaConValorValidacionWeb boletaConValorValidacionWeb;
	@Autowired
	private IClienteSiebelServicio clienteConsultarSiebelServicio;
	@Autowired 
	private ILdapServicio ldapServicio;
	@Autowired   
	private ValoresReportsUtils valoresReportsUtils;
	@Autowired
	private ILegajoChequeRechazadoServicio legajoChequeRechazadoServicio;
	@Autowired
	private IClienteFacade clienteFacade;
	@Autowired
	private ICombosFacade combosFacade;

	/***
	 * INIT BINDER
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.setBindingErrorProcessor(new CustomBindingErrorProcessor());
		DateFormat dateFormat = new SimpleDateFormat(Utilidad.DATE_FORMAT);
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
		binder.registerCustomEditor(BigDecimal.class, new CurrencyEditor());
		binder.registerCustomEditor(Integer.class, new IntegerEditor());
		if (binder.getTarget() instanceof ValorDto
				|| binder.getTarget() instanceof BoletaConValorFiltro
				|| binder.getTarget() instanceof ValoresDto) {
			binder.setValidator(boletaConValorValidacionWeb);
		}
	}

	/***
	 * DISPLAY
	 */
	@RequestMapping("/valores-boletas-alta")
	public ModelAndView boletasAlta(HttpServletRequest request, ValoresDto valoresDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);

		ValorDto valorDto = valoresDto.getValorDto();
		ModelAndView mv = new ModelAndView(ALTA_BOLETA_VIEW);
		ObjectMapper mapper = new ObjectMapper();
		
		valorDto.setOperation(Constantes.CREATE);
		valorDto.setIdAnalista(userSesion.getUsuario());
		valorDto.setNombreAnalista(userSesion.getNombreCompleto());

		List<ShvParamMotivo> motivos = this.listarMotivosSinChequesRechazados();
		List<ShvParamEmpresa> empresas = (List<ShvParamEmpresa>) combosServicio.listarEmpresasPorUsuario(userSesion);
		List<ShvParamSegmento> segmentos = new ArrayList<ShvParamSegmento>();
		List<UsuarioLdapDto> copropietarios = new ArrayList<UsuarioLdapDto>();
		BancosParametroDto bancosParametroDto = combosFacade.listaBancosAgrupadosPorDescipcion(empresas.get(0).getIdEmpresa());
		
		
		if (empresas.size() == 1) {
			ShvParamEmpresa empresa = empresas.get(0);
			segmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(empresa.getIdEmpresa(), userSesion);
			if (segmentos.size() == 1) {
				ShvParamSegmento segmento = segmentos.get(0);
				Collection<String> usuariosExcluidos = new ArrayList<String>();
				usuariosExcluidos.add(userSesion.getIdUsuario());
				copropietarios = (List<UsuarioLdapDto>) combosServicio.listarCopropietarioLegajoChequeRechazadoPorEmpresaYSegmento(
					empresa.getIdEmpresa(),
					segmento.getIdSegmento(), usuariosExcluidos);
				Collections.sort(
					copropietarios,
					new UsuarioLdapDtoComparator()
				);
			} 
		} 

		//enviarListasCombosAlCargar(mv, userSesion, valorDto);
//		cargarClienteSiebelConFormato(mv, userSesion);
		mv.addObject("criterioBusquedaCliente", Arrays.asList(CriterioBusquedaClienteEnum.values()));
		valorDto.setCajeroPagador(userSesion.esCajeroPagador());
		mv.addObject("esAnalista", userSesion.esAnalista());

		if(Validaciones.isNullOrEmpty(valorDto.getIdSegmento())){
			mv.addObject("comboCopropietario", false);
		}

		valoresDto.setValorDto(valorDto);
		validarCheckGenerarConstancia(valoresDto, mv);
		mv.addObject("valoresDto", valoresDto);

		// ComboEmpresa
		mv.addObject("empresas", empresas);
		mv.addObject("comboEmpresa", (empresas.size() == 0 || empresas.size() > 1));
		// ComboSegmentos
		mv.addObject("segmentos", segmentos);
		mv.addObject("comboSegmento", (segmentos.size() == 0 || segmentos.size() > 1));
		// ComboCopropietario
		mv.addObject("copropietarios", copropietarios);
		mv.addObject("comboCopropietarios", (copropietarios.size() == 0 || copropietarios.size() > 1));
		// nombre apellido Analista
		mv.addObject("nombreCompletoUsuario", userSesion.getNombreCompleto());
		// ComboMotivo
		mv.addObject(SELECT_MOTIVOS, motivos);
		mv.addObject("comboMotivos", (motivos.size() == 0 || motivos.size() > 1));
		// Es cajero pagador
		
		mv.addObject("leyendaComboSeleccionar", Propiedades.MENSAJES_PROPIEDADES.getString("combo.seleccionar"));

		//TipoValor
		mv.addObject("TipoValorEnum", mapper.writeValueAsString(TipoValorEnum.convertirAMap()));
		
		// TODO METER EN EL FACADE! O en el Servico
			List<TipoValorDto> tiposValor = combosFacade.buscarTiposValor("TA", "BCV");
			Set<Integer> listaIdTipoValor = new HashSet<Integer>();
			for (TipoValorDto dto : tiposValor) {
				listaIdTipoValor.add(dto.getIdTipoValor());
			}
			mv.addObject(SELECT_TIPO_VALOR, mapper.writeValueAsString(tiposValor));
			
			List<TipoValorEnum> listaTipoValores = new ArrayList<TipoValorEnum>();
			for(Integer id : listaIdTipoValor) {
				listaTipoValores.add(TipoValorEnum.getEnumByIdTipoValor(id.longValue()));
			}
			
			
			// SHV_PARAM_TIPO_GESTION
			ComboAcuerdoBancoCuentaGralDto paramTipoGestion = combosFacade.listarShvParamTipoGestion("TA", null, listaTipoValores, null);
			// TODO METER EN EL FACADE! O en el Servico
			
			mv.addObject("paramTipoGestion", mapper.writeValueAsString(paramTipoGestion));
			mv.addObject("leyendaComboSeleccionar", Propiedades.MENSAJES_PROPIEDADES.getString("combo.seleccionar"));
			
			
			mv.addObject("bancoOrigenes", bancosParametroDto.getListaBancos());
			mv.addObject("comboBancoOrigen", (bancosParametroDto.getListaBancos().size() == 0 || bancosParametroDto.getListaBancos().size() > 1));
			mv.addObject("bancoOrigenesDescripcion", bancosParametroDto.getAgrupadores());
			bancosParametroDto.setListaBancos(null);
			mv.addObject("bancoDescripcion", mapper.writeValueAsString(bancosParametroDto));
		return mv;
	}

	@RequestMapping("/valores-avisos-alta")
	public ModelAndView avisosAlta(HttpServletRequest request, ValoresDto valoresDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ValorDto valorDto = valoresDto.getValorDto();
		ModelAndView mv = new ModelAndView(ALTA_AVISO_VIEW);
		ObjectMapper mapper = new ObjectMapper();

		valorDto.setOperation(Constantes.CREATE);
		valorDto.setIdAnalista(userSesion.getUsuario());
		valorDto.setNombreAnalista(userSesion.getNombreCompleto());

		// Combos
		List<ShvParamEmpresa> empresas = (List<ShvParamEmpresa>) combosServicio.listarEmpresasPorUsuario(userSesion);
		List<ShvParamSegmento> segmentos = new ArrayList<ShvParamSegmento>();
		List<UsuarioLdapDto> copropietarios = new ArrayList<UsuarioLdapDto>();
		List<ShvParamMotivo> motivos = this.listarMotivosSinChequesRechazados();
		BancosParametroDto bancosParametroDto = combosFacade.listaBancosAgrupadosPorDescipcion(empresas.get(0).getIdEmpresa());
		
		
		if (empresas.size() == 1) {
			ShvParamEmpresa empresa = empresas.get(0);
			segmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(empresa.getIdEmpresa(), userSesion);
			if (segmentos.size() == 1) {
				ShvParamSegmento segmento = segmentos.get(0);
				Collection<String> usuariosExcluidos = new ArrayList<String>();
				usuariosExcluidos.add(userSesion.getIdUsuario());
				copropietarios = (List<UsuarioLdapDto>) combosServicio.listarCopropietarioLegajoChequeRechazadoPorEmpresaYSegmento(
					empresa.getIdEmpresa(),
					segmento.getIdSegmento(), usuariosExcluidos);
				Collections.sort(
					copropietarios,
					new UsuarioLdapDtoComparator()
				);
			} 
		} 

		cargarCombosAvisos(mv, userSesion, valorDto);
//		cargarClienteSiebelConFormato(mv, userSesion);
		mv.addObject("criterioBusquedaCliente", Arrays.asList(CriterioBusquedaClienteEnum.values()));
//		cargaComboChequeAreemplazar(mv,valorDto);
		//cargarCopropietario(mv, valorDto);
		if (Validaciones.isNullOrEmpty(valorDto.getIdSegmento())) {
			mv.addObject("comboCopropietario", false);
		}
		
		if (valorDto.getIdNroCuenta() == "") {
			valorDto.setNumeroCuenta(null);
			valorDto.setIdNroCuenta(null);
		}
		if (valorDto.getIdTipoImpuesto() == "") {
			valorDto.setIdTipoImpuesto(null);
			valorDto.setTipoImpuesto(null);
		}

		valoresDto.setValorDto(valorDto);
		mv.addObject("valoresDto", valoresDto);
		mv.addObject("imprimibleArchivo", false);

		// ComboEmpresa
		mv.addObject("empresas", empresas);
		mv.addObject("comboEmpresa", (empresas.size() == 0 || empresas.size() > 1));
		// ComboSegmentos
		mv.addObject("segmentos", segmentos);
		mv.addObject("comboSegmento", (segmentos.size() == 0 || segmentos.size() > 1));
		// ComboCopropietario
		mv.addObject("copropietarios", copropietarios);
		mv.addObject("comboCopropietarios", (copropietarios.size() == 0 || copropietarios.size() > 1));
		// nombre apellido Analista
		mv.addObject("nombreCompletoUsuario", userSesion.getNombreCompleto());
		// ComboMotivo
		mv.addObject(SELECT_MOTIVOS, motivos);
		mv.addObject("comboMotivos", (motivos.size() == 0 || motivos.size() > 1));
		// ComboBancoOrigen
		mv.addObject("bancoOrigenes", bancosParametroDto.getListaBancos());
		mv.addObject("comboBancoOrigen", (bancosParametroDto.getListaBancos().size() == 0 || bancosParametroDto.getListaBancos().size() > 1));
		mv.addObject("bancoOrigenesDescripcion", bancosParametroDto.getAgrupadores());
		bancosParametroDto.setListaBancos(null);
		mv.addObject("bancoDescripcion", mapper.writeValueAsString(bancosParametroDto));
		
		
		//TipoValor
		mv.addObject("TipoValorEnum", mapper.writeValueAsString(TipoValorEnum.convertirAMap()));
		
		// TODO METER EN EL FACADE! O en el Servico
		List<TipoValorDto> tiposValor = combosFacade.buscarTiposValor("TA", "AVS");
		Set<Integer> listaIdTipoValor = new HashSet<Integer>();
		for (TipoValorDto dto : tiposValor) {
			listaIdTipoValor.add(dto.getIdTipoValor());
		}
		mv.addObject(SELECT_TIPO_VALOR, mapper.writeValueAsString(tiposValor));
		
		List<TipoValorEnum> listaTipoValores = new ArrayList<TipoValorEnum>();
		for(Integer id : listaIdTipoValor) {
			listaTipoValores.add(TipoValorEnum.getEnumByIdTipoValor(id.longValue()));
		}
		
		
		// SHV_PARAM_TIPO_GESTION
		ComboAcuerdoBancoCuentaGralDto paramTipoGestion = combosFacade.listarShvParamTipoGestion("TA", 6, listaTipoValores, SiNoEnum.NO);
		// TODO METER EN EL FACADE! O en el Servico
		
		mv.addObject("paramTipoGestion", mapper.writeValueAsString(paramTipoGestion));
		mv.addObject("leyendaComboSeleccionar", Propiedades.MENSAJES_PROPIEDADES.getString("combo.seleccionar"));

		// ComboAcuerdo
		
		return mv;
	}

	@RequestMapping(value = "/valores-busqueda")
	public ModelAndView valoresBusqueda(HttpServletRequest request,
			BoletaConValorFiltro boletaConValorFiltro, BindingResult result)
					throws Exception {
		ModelAndView mv = new ModelAndView(BUSQUEDA_VIEW);

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		boletaConValorFiltro.setUsuarioLogeado(userSesion);	

		cargarCombosParaBusquedaValores(mv, userSesion, boletaConValorFiltro);
		//		cargarCodigoLegadoSiebel(mv, boletaConValorServicio.listar10CodigosClienteLegadoUsuario(userSesion.getIdUsuario()));
		cargarComboAnalista(mv, boletaConValorFiltro);

		if(userSesion.esAnalista()){
			mv.addObject("usuarioSessionAnalista", true);
		}else{
			mv.addObject("usuarioSessionAnalista", false);
		}
		if(userSesion.esSupervisor()){
			mv.addObject("usuarioSessionSupervisor", true );
		}else{
			mv.addObject("usuarioSessionSupervisor", false );
		}
		if(userSesion.esAdminValor()){
			mv.addObject("usuarioSessionAdministrador", true );
		}else{
			mv.addObject("usuarioSessionAdministrador", false);
		}
		if(userSesion.esPerfilConsulta()){
			mv.addObject("usuarioSessionConsulta", true );
		}else{
			mv.addObject("usuarioSessionConsulta", false);
		}
		if(userSesion.esCajeroPagador()){
			mv.addObject("usuarioSessionCajeroPagador", true );
		}else{
			mv.addObject("usuarioSessionCajeroPagador", false);
		}

		request.getSession().setAttribute("busquedaFiltro", boletaConValorFiltro);
		mv.addObject("usuarioSessionIdUsuario",userSesion.getIdUsuario());
		mv.addObject("criterioBusquedaCliente", Arrays.asList(CriterioBusquedaClienteEnum.values()));
		mv.addObject("imprimible", false);
		mv.addObject("realizarBusqueda", false);

		if (!Validaciones.isNullOrEmpty(boletaConValorFiltro.getSelectCriterio())) {

			ClienteFiltro clienteFiltro = new ClienteFiltro();

			clienteFiltro.setCriterio(boletaConValorFiltro.getSelectCriterio());
			clienteFiltro.setBusqueda(boletaConValorFiltro.getTextCriterio());

			ClienteJsonResponse clienteResponse = this.clienteFacade.consultarCliente(clienteFiltro);

			if (!clienteResponse.isSuccess()) {

				List<String> campoError = new ArrayList<String>();
				List<String> codigoLeyenda = new ArrayList<String>();

				campoError.add(clienteResponse.getCampoError());
				codigoLeyenda.add(clienteResponse.getDescripcionError());
				mv.addObject("tieneError", true);
				mv.addObject("campoError", campoError.get(0));
				mv.addObject("codigoLeyenda", codigoLeyenda.get(0));
//				throw new ValidacionExcepcion(campoError,codigoLeyenda);

			}else {
				boletaConValorFiltro.setListaClientesSegunFiltros(clienteResponse.getClientes());
			}
		}else{
			if(!Validaciones.isNullOrEmpty(boletaConValorFiltro.getTextCriterio())){
				mv.addObject("tieneError2", true);
				String errorSelectCriterio = "errorSelectCriterio";
				mv.addObject("campoError2", errorSelectCriterio);
				String errorSelectCriterioDesc = Propiedades.MENSAJES_PROPIEDADES.getString("error.obligatorio");
				mv.addObject("codigoLeyenda2", errorSelectCriterioDesc);
				mv.addObject("realizarBusqueda", false);
				mv.addObject("imprimible", false);
			}
		}

		return mv;
	}
	
	@RequestMapping(value = "/buscarAnalistas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String buscarAnalistas(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idEmpresa = request.getParameter("idEmpresa");
		String idSegmento = request.getParameter("idSegmento");
		BoletaConValorFiltro filtro = new BoletaConValorFiltro();
		filtro.setEmpresa(idEmpresa);
		filtro.setSegmento(idSegmento);
		List<UsuarioLdapDto> listaAnalistaFiltroResultadoFinal = new ArrayList<UsuarioLdapDto>();
		List<UsuarioLdapDto> buscarUsuariosAnalistaPorPerfilSegmento = buscarUsuariosPorPerfilSegmento(filtro, TipoPerfilEnum.ANALISTA.nombreLdap());
		List<UsuarioLdapDto> buscarUsuariosCajeroPagadorPorPerfilSegmento = buscarUsuariosPorPerfilSegmento(filtro, TipoPerfilEnum.CAJERO_PAGADOR.nombreLdap());
		listaAnalistaFiltroResultadoFinal.addAll(buscarUsuariosPorPerfilSegmento(filtro, TipoPerfilEnum.ANALISTA.nombreLdap()));
		listaAnalistaFiltroResultadoFinal.addAll(buscarUsuariosPorPerfilSegmento(filtro, TipoPerfilEnum.CAJERO_PAGADOR.nombreLdap()));
		listaAnalistaFiltroResultadoFinal = removerUsuariosDuplicados(buscarUsuariosAnalistaPorPerfilSegmento, buscarUsuariosCajeroPagadorPorPerfilSegmento, listaAnalistaFiltroResultadoFinal);
		StringBuffer str = new StringBuffer();
		str.append("[");
		if (!listaAnalistaFiltroResultadoFinal.isEmpty()) {
			for (UsuarioLdapDto analista : listaAnalistaFiltroResultadoFinal) {
				str.append("{\"text\": \""+ analista.getTuid() + " - " + analista.getNombreCompleto() +"\", \"value\": \""+ analista.getTuid() +"\"},");
			}
			str.deleteCharAt(str.lastIndexOf(","));
		}
		str.append("]");
		
		response.setCharacterEncoding("ISO-8859-1");
		return str.toString();
	}

	/************************************************************************************/
	/** Modulo - Valores Autorizar
	/************************************************************************************/
	@RequestMapping("/valores-autorizacion")
	public ModelAndView valoresAutorizacion(HttpServletRequest request,BoletaConValorFiltro boletaConValorFiltro, BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		ModelAndView mv = new ModelAndView(AUTORIZACION_VIEW);
		boletaConValorFiltro.setUsuarioLogeado(userSesion);
		
		boolean entroDesdeBandejaEntrada=false;
		
		String idEmpresa = request.getParameter("idEmpresa");
		String idSegmento = request.getParameter("idSegmento");
		if (!Validaciones.isNullOrEmpty(idEmpresa)){
			boletaConValorFiltro.setEmpresa(idEmpresa);
		}
		if (!Validaciones.isNullOrEmpty(idSegmento)){
			boletaConValorFiltro.setSegmento(idSegmento);
		}
		
		if (boletaConValorFiltro.getPantallaDestino() != null 
				&& Constantes.DESTINO_BANDEJA_ENTRADA.equals(boletaConValorFiltro.getPantallaDestino())) {
			
			entroDesdeBandejaEntrada = true;
			mv.addObject("pantallaDestino", boletaConValorFiltro.getPantallaDestino());
			if (!Validaciones.isNullOrEmpty(boletaConValorFiltro.getIdValorBandeja())) { 
				boletaConValorFiltro.setIdValorBandeja(boletaConValorFiltro.getIdValorBandeja());
			} else {
				// para que muestre vacio
				boletaConValorFiltro.setIdValorBandeja("0");
			}
		} else {
			entroDesdeBandejaEntrada = false;
			boletaConValorFiltro.setIdValorBandeja("");
		}
		
		if (boletaConValorFiltro.isObservacionFormatoError() || boletaConValorFiltro.isObservacionObligatorioError()) {
			if (boletaConValorFiltro.isObservacionFormatoError()) {
				mv.addObject("observacionesFormatoError", true);
			}
			if (boletaConValorFiltro.isObservacionObligatorioError()) {
				mv.addObject("observacionesObligatorioError", true);
			}
		} 
		
		if(entroDesdeBandejaEntrada){
			mv.addObject("botonCancelarDisplay", true);
			mv.addObject("botonBuscarDisplay", false);
			
		} else {
			prepararPantallaAutorizacionDesdeMenu(mv, request, boletaConValorFiltro);
		}
		
		ValoresDto valoresDto = new ValoresDto();
		valoresDto.setListaValoresDto(boletaConValorServicio.buscarValoresParaAutorizacion(boletaConValorFiltro.getUsuarioLogeado(), boletaConValorFiltro));
		mv.addObject("listaValoresDto",  Utilidad.guionesNull((List<? extends DTO>) valoresDto.getListaValoresDto()));
		mv.addObject("boletaConValorFiltro",boletaConValorFiltro);
		return mv;
	}

	private void prepararPantallaAutorizacionDesdeMenu(ModelAndView mv, HttpServletRequest request, BoletaConValorFiltro boletaConValorFiltro) 
			throws ShivaExcepcion, NegocioExcepcion {
		
		mv.addObject("botonCancelarDisplay", false);
		mv.addObject("botonBuscarDisplay", true);
		
		cargarCombosParaBusquedaAutorizacionValores(mv,boletaConValorFiltro.getUsuarioLogeado(), boletaConValorFiltro);
	}
	
	@RequestMapping("/buscar-autorizar")
	public ModelAndView buscarAutorizar(HttpServletRequest request,@ModelAttribute("boletaConValorFiltro") @Valid BoletaConValorFiltro boletaConValorFiltro,BindingResult result) throws Exception {

		if (result.hasErrors()) {
			 return valoresAutorizacion(request, boletaConValorFiltro,
			 result);
		}
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		request.setAttribute("boletaConValorFiltro", boletaConValorFiltro);	
		
		ModelAndView mv = new ModelAndView(AUTORIZACION_VIEW);
		
		ValoresDto valoresDto = new ValoresDto();
		valoresDto.setListaValoresDto(boletaConValorServicio.buscarValoresParaAutorizacion(	userSesion, boletaConValorFiltro));
		cargarCombosParaBusquedaAutorizacionValores(mv,userSesion, boletaConValorFiltro );
		mv.addObject("listaValoresDto",  Utilidad.guionesNull((List<? extends DTO>) valoresDto.getListaValoresDto()));
		
		mv.addObject("botonCancelarDisplay", false);
		mv.addObject("botonBuscarDisplay", true);
		return mv;
	}
	
	
	
	@RequestMapping("/autorizaciones-autorizar")
	public ModelAndView autorizacionAutorizar(HttpServletRequest request,
			@ModelAttribute("boletaConValorFiltro") BoletaConValorFiltro boletaConValorFiltro,
			BindingResult result) throws Exception {
		
		String valoresSelected = Constantes.UNDEFINED.equalsIgnoreCase(boletaConValorFiltro.getValoresSelected())?"":boletaConValorFiltro.getValoresSelected();
		
		if (!Validaciones.isNullOrEmpty(boletaConValorFiltro.getObservaciones())) {
			if (!Validaciones.esFormatoTexto(boletaConValorFiltro.getObservaciones())) {
				boletaConValorFiltro.setObservacionFormatoError(true);			
				return valoresAutorizacion(request, boletaConValorFiltro, result);
			}
		}
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()	.getAttribute(Constantes.LOGIN);
		
		ModelAndView mv = new ModelAndView(VALOR_AUTORIZACION_OK_VIEW);
		
		try {
			List<String> mensajesMostrarEnvioMail = boletaConValorServicio.autorizarBoleta(userSesion, valoresSelected, boletaConValorFiltro.getObservaciones());
			mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
			mv.addObject("mensajesMostrarEnvioMail", mensajesMostrarEnvioMail);	
			
			if (Constantes.DESTINO_BANDEJA_ENTRADA.equalsIgnoreCase(boletaConValorFiltro.getPantallaDestino())) {
				mv.addObject("url",BANDEJA_ENTRADA_VIEW_POST);
			} else {
				mv.addObject("url", "valores-autorizacion");	
				mv.addObject("empresaModel", boletaConValorFiltro.getEmpresa());
				mv.addObject("segmentoModel", boletaConValorFiltro.getSegmento());
				mv.addObject("botonCancelarDisplay", false);
				mv.addObject("botonBuscarDisplay", true);
			}
			
		} catch (ConcurrenciaExcepcion e) {
			
			mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
			mv.addObject("lista", true);
			mv.addObject("mensaje", 
				Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.concurrencia.lista"), 
						", para las boletas ["+e.getListaIdsInconcurrentes()+"]"));	
			
			if (Constantes.DESTINO_BANDEJA_ENTRADA.equalsIgnoreCase(boletaConValorFiltro.getPantallaDestino())) {
				mv.addObject("url",BANDEJA_ENTRADA_VIEW_GET);
			} else {
				mv.addObject("url", "valores-autorizacion");	
				mv.addObject("empresaModel", boletaConValorFiltro.getEmpresa());
				mv.addObject("segmentoModel", boletaConValorFiltro.getSegmento());
				mv.addObject("botonCancelarDisplay", false);
				mv.addObject("botonBuscarDisplay", true);
			}
		} 
		
		return mv;
	}
	
	@RequestMapping("/autorizar-rechazar")
	public ModelAndView autorizacionRechazar(HttpServletRequest request,
			@ModelAttribute("boletaConValorFiltro") BoletaConValorFiltro boletaConValorFiltro,
			BindingResult result) throws Exception {
		
		String valoresSelected = Constantes.UNDEFINED.equalsIgnoreCase(boletaConValorFiltro.getValoresSelected())?"":boletaConValorFiltro.getValoresSelected();
		
		if (Validaciones.isNullOrEmpty(boletaConValorFiltro.getObservaciones())) {
			boletaConValorFiltro.setObservacionObligatorioError(true);			
			return valoresAutorizacion(request, boletaConValorFiltro, result);
		} else {
			if (!Validaciones.esFormatoTexto(boletaConValorFiltro.getObservaciones())) {
				boletaConValorFiltro.setObservacionFormatoError(true);			
				return valoresAutorizacion(request, boletaConValorFiltro, result);
			}
		}
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()	.getAttribute(Constantes.LOGIN);
		
		ModelAndView mv = new ModelAndView(VALOR_AUTORIZACION_OK_VIEW);
		
		try {
			boletaConValorServicio.rechazarAutorizacionBoleta(userSesion, valoresSelected, boletaConValorFiltro.getObservaciones());
			mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
			
			if (Constantes.DESTINO_BANDEJA_ENTRADA.equalsIgnoreCase(boletaConValorFiltro.getPantallaDestino())) {
				mv.addObject("url",BANDEJA_ENTRADA_VIEW_POST);
			} else {
				mv.addObject("url", "valores-autorizacion");	
				mv.addObject("empresaModel", boletaConValorFiltro.getEmpresa());
				mv.addObject("segmentoModel", boletaConValorFiltro.getSegmento());
				mv.addObject("botonCancelarDisplay", false);
				mv.addObject("botonBuscarDisplay", true);
			}
			
		} catch (ConcurrenciaExcepcion e) {
			mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
			mv.addObject("lista", true);
			mv.addObject("mensaje", 
				Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.concurrencia.lista"), 
						", para las boletas ["+e.getListaIdsInconcurrentes()+"]"));
			
			if (Constantes.DESTINO_BANDEJA_ENTRADA.equalsIgnoreCase(boletaConValorFiltro.getPantallaDestino())) {
				mv.addObject("url",BANDEJA_ENTRADA_VIEW_GET);
			} else {
				mv.addObject("url", "valores-autorizacion");	
				mv.addObject("empresaModel", boletaConValorFiltro.getIdEmpresa());
				mv.addObject("segmentoModel", boletaConValorFiltro.getIdSegmento());
				mv.addObject("botonCancelarDisplay", false);
				mv.addObject("botonBuscarDisplay", true);
			}
		} 
		
		return mv;
	}
	
	@RequestMapping("/volver-autorizar")
	public ModelAndView volverAutorizacion(HttpServletRequest request, 
			BoletaConValorFiltro boletaConValorFiltro, BindingResult result) throws Exception {
			
		boletaConValorFiltro.setEmpresa(request.getParameter("idEmpresa"));
		boletaConValorFiltro.setSegmento(request.getParameter("idSegmento"));
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		ModelAndView mv = new ModelAndView(AUTORIZACION_VIEW);
		mv.addObject("boletaConValorFiltro", boletaConValorFiltro);
		
		ValoresDto valoresDto = new ValoresDto();
		valoresDto.setListaValoresDto(boletaConValorServicio.buscarValoresParaAutorizacion(	userSesion, boletaConValorFiltro));
		cargarCombosParaBusquedaAutorizacionValores(mv,userSesion, boletaConValorFiltro );
		mv.addObject("listaValoresDto",  Utilidad.guionesNull((List<? extends DTO>) valoresDto.getListaValoresDto()));
		mv.addObject("botonCancelarDisplay", false);
		mv.addObject("botonBuscarDisplay", true);
		
		return mv;
	}	
	/************************************************************************************/
	/** Fin Modulo - Valores Autorizar
	/************************************************************************************/
	
	@RequestMapping("/valores-cambio-estado")
	@ControlProcesoTransacciones
	public ModelAndView valoresCambioEstado(HttpServletRequest request,
			@ModelAttribute("boletaConValorFiltro") BoletaConValorFiltro boletaConValorFiltro, ValidacionExcepcion error) throws Exception {

		if (error==null || error.getMessage()== null) {
			request.getSession().setAttribute("boletaConValorFiltro", boletaConValorFiltro);
		}
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		ModelAndView mv = new ModelAndView(CAMBIO_ESTADO_VIEW);
		List<Long> idValores = new ArrayList<Long>();
		String[] idValoresAModificar = boletaConValorFiltro.getValoresSelected().split(",");
		for (String idValor: idValoresAModificar) {
			String id = idValor.split("_")[0];
			idValores.add(new Long(id));
		}
		ArrayList<ValorDto>  listaDeValores =  boletaConValorServicio.buscarParaCambioEstado(userSesion, idValores);
		
		ValoresDto valoresDto = new ValoresDto();
		valoresDto.setListaValoresDto(valoresDto.getListaValoresDto());
		mv.addObject("listaValoresDto",  Utilidad.guionesNull((List<? extends DTO>) listaDeValores));
		
		//Preparo las concurrencias
		String idModificarCompleto = "";
		int w = 0;
		if (Validaciones.isCollectionNotEmpty(listaDeValores)) {
			for (ValorDto ob : listaDeValores) {
				String id = ob.getIdValor().toString();
		    	String timeStamp = ob.getTimeStamp();
		    	String valor = id+"_"+timeStamp;
		    	
		    	String coma = (w==0)?"":",";
		    	idModificarCompleto += coma+valor;
		    	w++;
			}
		}
		mv.addObject("idAmodificarEnvios", idModificarCompleto);
		
		List<String> listaEstado = new ArrayList<String>();
		String  estadoDeLosValores = listaDeValores.get(0).getEstadoValor();
		if (Estado.VAL_DISPONIBLE.descripcion().equals(estadoDeLosValores)
				|| Estado.VAL_USADO.descripcion().equals(estadoDeLosValores)) {
			
			listaEstado.add(Estado.VAL_SUSPENDIDO.descripcion());
			mv.addObject("motivosSuspension", listaMotivoSuspension());
			mv.addObject("comboMotivoSuspension", true);
			
		} else 
		if (Estado.VAL_SUSPENDIDO.descripcion().equals(estadoDeLosValores)){
			listaEstado.add(Estado.VAL_ANULADO.descripcion());
			listaEstado.add(Estado.VAL_DISPONIBLE.descripcion());
			listaEstado.add(Estado.VAL_USADO.descripcion());
			mv.addObject("comboEstado", true);
		} else 
		if (Estado.VAL_BOLETA_PENDIENTE_CONCILIACION.descripcion().equalsIgnoreCase(estadoDeLosValores) || 
				Estado.VAL_AVISO_PAGO_RECHAZADO.descripcion().equalsIgnoreCase(estadoDeLosValores) || 
				Estado.VAL_BOLETA_RECHAZADA.descripcion().equalsIgnoreCase(estadoDeLosValores)) {
			listaEstado.add(Estado.VAL_ANULADO.descripcion());
		}
		mv.addObject("estados", listaEstado);
		
		if (error!=null && error.getMessage()!= null) {
			mv.addObject("errorSaldoReversado", true);
			mv.addObject("errorMensaje", error.getMessage());
		}
		
		return mv;
	}
	
	
	protected List<ShvParamMotivoSuspension> listaMotivoSuspension() throws NegocioExcepcion{
	
		List<ShvParamMotivoSuspension> listaMotivoSuspension  = combosServicio.listarMotivoSuspension();
		List<ShvParamMotivoSuspension> listaAux = new ArrayList<ShvParamMotivoSuspension>();
		for (ShvParamMotivoSuspension motivoSuspension: listaMotivoSuspension){
			if(MotivoSuspensionEnum.CHEQUE_RECHAZADO.codigo() != Integer.parseInt(motivoSuspension.getIdMotivoSuspension())){
				listaAux.add(motivoSuspension);
			}
		}
		
		return listaAux;
	}
	
	@RequestMapping("/valores-cambio-estado-update")
	public ModelAndView valoresCambioEstadoUpdate(HttpServletRequest request, 
			@ModelAttribute("boletaConValorFiltro") @Valid BoletaConValorFiltro boletaConValorFiltro,
			BindingResult result) throws Exception {

		if (result.hasErrors()) {
			return valoresCambioEstado(request, boletaConValorFiltro, new ValidacionExcepcion());
		}
		
		String idAModificar = boletaConValorFiltro.getValoresSelected();
		String estadoAModificarse = boletaConValorFiltro.getEstadoAEnviar();
		String motivo = boletaConValorFiltro.getMotivoAEnviar();		
		String obserVacion = boletaConValorFiltro.getObservacionesAEnviar();
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()	.getAttribute(Constantes.LOGIN);
		boletaConValorFiltro.setSelectEstado(estadoAModificarse);
		boletaConValorFiltro.setIdMotivoSuspension(motivo);
		boletaConValorFiltro.setIdsParaModificar(idAModificar);
		boletaConValorFiltro.setObservaciones(obserVacion);
		boletaConValorFiltro.setUsuarioLogeado(userSesion);
		
		ModelAndView mv = new ModelAndView(CAMBIO_ESTADO_OK_VIEW);
		try {
			boletaConValorServicio.actualizarEstado(boletaConValorFiltro,  userSesion);
			mv.addObject("mensaje",  Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
			
		} catch (ValidacionExcepcion e1) {
			return valoresCambioEstado(request, boletaConValorFiltro, e1);
		} catch (ConcurrenciaExcepcion e2) {
			mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
		} 
		mv.addObject("url", BUSQUEDA_VIEW);
		
		return mv;
	}
	
	
	@RequestMapping("/valores-historial-modificacion")
	public ModelAndView valoresHistorial(HttpServletRequest request,
			ValorDto valorDto) throws Exception {

		ModelAndView mv = new ModelAndView(HISTORIAL_VIEW);

		List<ValorHistoricoDto> historicoDto = boletaConValorServicio.obtenerHistorial(valorDto);
		
		if(Validaciones.isCollectionNotEmpty(valorDto.getListaComprobantes())){
			int i = 0;
			Iterator<ComprobanteDto> it = valorDto.getListaComprobantes().iterator();
			while (it.hasNext()) {
				ComprobanteDto comp = it.next();
				comp.setId(i);
				i++;
			}
		}
		
		mv.addObject("valorDto", valorDto);
		mv.addObject("historicoDto", historicoDto);
		mv.addObject("imprimibleArchivo", false);
		return mv;
	}

	@RequestMapping("/valores-modificacion")
	public ModelAndView valoresModificacion(HttpServletRequest request, ValorDto valorDto) throws Exception {

		ModelAndView mv = new ModelAndView(MODIFICACION_VIEW);

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);

		String pantallaRegreso = valorDto.getPantallaRegreso();
		valorDto.setPantallaRegreso(pantallaRegreso);
		valorDto.setVolverBandeja(pantallaRegreso != null && Constantes.DESTINO_BANDEJA_ENTRADA.equals(pantallaRegreso));
		
		valorDto.setOperation(Constantes.UPDATE);
		limpiarFechas(valorDto);
		
		if (valorDto.getEstadoValor().equals(Estado.VAL_BOLETA_RECHAZADA.descripcion()) || valorDto.getEstadoValor().equals(Estado.VAL_AVISO_PAGO_RECHAZADO.descripcion())){
			enviarListasCombosAlCargar(mv, userSesion, valorDto);
		} else {
			cargarCombosDesdeDto(mv, valorDto);
		}
		cargarCombosAvisos(mv, userSesion, valorDto);
		cargarClienteSiebelConFormato(mv, userSesion);

		String tipoModificacion = (validarTipoModificacion(valorDto, userSesion));
		valorDto.setModifSupRecha(tipoModificacion.equals(MODIFICACION_RECHAZADA_SUPERVISOR));
		valorDto.setModifAnaRecha(tipoModificacion.equals(MODIFICACION_RECHAZADA));
		valorDto.setModifAnaNoRecha(tipoModificacion.equals(MODIFICACION));
		valorDto.setModifAdmRecha(tipoModificacion.equals(MODIFICACION_SUSPENDIDO_ADMINISTRADOR));
		valorDto.setModifAdmNoRecha(tipoModificacion.equals(MODIFICACION_ADMINISTRADOR));
		valorDto.setModifUltimoMes(validarUltimoMes(valorDto));

//		cargaComboChequeAreemplazar(mv,valorDto);

		if (valorDto.getIdNroCuenta() == "") {
			valorDto.setNumeroCuenta(null);
			valorDto.setIdNroCuenta(null);
		}
		boletaConValorServicio.arreglarFechasCortas(valorDto);
		
		if(Validaciones.isCollectionNotEmpty(valorDto.getListaComprobantes())){
			int i = 0;
			Iterator<ComprobanteDto> ite = valorDto.getListaComprobantes().iterator();
			while (ite.hasNext()) {
				ComprobanteDto comp = ite.next();
				comp.setId(i);
				i++;
			}
		}
		
		if(!Validaciones.isNullOrEmpty(valorDto.getIdAnalista())){
			UsuarioLdapDto usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(valorDto.getIdAnalista());
			valorDto.setNombreAnalista(usuarioLdapAnalista.getNombreCompleto());
		}

		mv.addObject("valorDto", valorDto);
		mv.addObject("ultimoMes", false);
		mv.addObject("imprimibleArchivo", false);
		mv.addObject("criterioBusquedaCliente", Arrays.asList(CriterioBusquedaClienteEnum.values()));
		return mv;
	}

	/***
	 * Metodo que valida duplicidad al querer dar de alta dos valores a la vez.
	 */
	public ValoresDto validarAltaValoresSimultaneo(ValoresDto valoresDto) throws Exception {
		ValorDto elValorDto = valoresDto.getValorDto();
		ValorDto valorDto = limpiarCombos(elValorDto);
		valoresDto.setValorDto(valorDto);
		valoresDto = boletaConValorServicio.validarAltaValoresSimultaneo(valoresDto);
		return valoresDto;
	}

//	@RequestMapping("/procesar-alta-tabla")
//	public ModelAndView procesarAltaTabla(HttpServletRequest request,
//			@ModelAttribute("valoresDto") @Valid ValoresDto valoresDto,
//			BindingResult result) throws Exception {
//
//		if (result.hasErrors()) {
//		
//			return boletasAlta(request, valoresDto);
//		}
//		
//		ValoresDto valorcreado = validarAltaValoresSimultaneo(valoresDto);
//		if (!valorcreado.isDuplicado()) {
//			List<ShvParamTipoValor> listaTipoValor = (List<ShvParamTipoValor>) combosServicio.buscarTipoValorBoletaConValor(valoresDto.getValorDto());
//			valoresDto.getValorDto().setIdTipoValor(String.valueOf(listaTipoValor.get(0).getIdTipoValor()));
//		}
//		
//		return boletasAlta(request, valorcreado);
//	}

//	@RequestMapping("/procesar-alta-tabla-aviso")
//	public ModelAndView procesarAltaTablaAviso(HttpServletRequest request,
//			@ModelAttribute("valoresDto") @Valid ValoresDto valoresDto,
//			BindingResult result) throws Exception {
//		
//		if(valoresDto.getValorDto().getListaComprobantes().size() == 0){
//			valoresDto.getValorDto().setComboComprobante(true);
//		} else {
//			valoresDto.getValorDto().setComboComprobante(false);
//		}
//
//		if (result.hasErrors()) {
//			return avisosAlta(request, valoresDto);
//		}
//		
//		ValoresDto valorcreado = validarAltaValoresSimultaneo(valoresDto);
//		if (!valorcreado.isDuplicado()) {
//			List<ShvParamTipoValor> listaTipoValor = (List<ShvParamTipoValor>) combosServicio.buscarTipoValorAvisoPago(valoresDto.getValorDto());
//			valoresDto.getValorDto().setIdTipoValor(String.valueOf(listaTipoValor.get(0).getIdTipoValor()));
//		}
//		
//		return avisosAlta(request, valorcreado);
//	}

//	@RequestMapping("/procesar-eliminar-tabla")
//	public ModelAndView procesarEliminarTabla(HttpServletRequest request,
//			@ModelAttribute("valoresDto") ValoresDto valoresDto)
//			throws Exception {
//
//		String idValor = valoresDto.getIdValorSelected();
//
//		Iterator<ValorDto> it = valoresDto.getListaValoresDto().iterator();
//		while (it.hasNext()) {
//			ValorDto val = it.next();
//			if (idValor.equals(val.getId().toString())) {
//				it.remove();
//			}
//		}
//		return boletasAlta(request, valoresDto);
//	}

//	@RequestMapping("/procesar-eliminar-tabla-aviso")
//	public ModelAndView procesarEliminarTablaAviso(HttpServletRequest request,
//			@ModelAttribute("valoresDto") ValoresDto valoresDto,
//			BindingResult result) throws Exception {
//
//		String idValor = valoresDto.getIdValorSelected();
//
//		Iterator<ValorDto> it = valoresDto.getListaValoresDto().iterator();
//		while (it.hasNext()) {
//			ValorDto val = it.next();
//			if (idValor.equals(val.getId().toString())) {
//				it.remove();
//			}
//		}
//		return avisosAlta(request, valoresDto);
//	}

//	@RequestMapping("/procesar-editar-tabla")
//	public ModelAndView procesarEditarTabla(HttpServletRequest request,
//			@ModelAttribute("valoresDto") ValoresDto valoresDto,
//			BindingResult result) throws Exception {
//
//		String idValor = valoresDto.getIdValorSelected();
//
//		Iterator<ValorDto> it = valoresDto.getListaValoresDto().iterator();
//		while (it.hasNext()) {
//			ValorDto val = it.next();
//			if (idValor.equals(val.getId().toString())) {
//				val.setImporte(Utilidad.formatCurrencySacarPesos(val.getImporte()));
//				valoresDto.setValorDto(val);
//				it.remove();
//			}
//		}
//		return boletasAlta(request, valoresDto);
//	}

//	@RequestMapping("/procesar-editar-tabla-aviso")
//	public ModelAndView procesarEditarTablaAviso(HttpServletRequest request,
//			@ModelAttribute("valoresDto") ValoresDto valoresDto,
//			BindingResult result) throws Exception {
//
//		String idValor = valoresDto.getIdValorSelected();
//
//		Iterator<ValorDto> it = valoresDto.getListaValoresDto().iterator();
//		while (it.hasNext()) {
//			ValorDto val = it.next();
//			if (idValor.equals(val.getId().toString())) {
//				val.setImporte(Utilidad.formatCurrencySacarPesos(val.getImporte()));
//				valoresDto.setValorDto(val);
//				it.remove();
//			}
//		}
//		
//		if(valoresDto.getValorDto().getListaComprobantes().size() == 0){
//			valoresDto.getValorDto().setComboComprobante(true);
//		} else {
//			valoresDto.getValorDto().setComboComprobante(false);
//		}	
//		
//		return avisosAlta(request, valoresDto);
//	}

	/***
	 * ALTAS
	 */
	
	 
	 
//	 @RequestMapping("/procesar-alta-valor")
//	@ControlProcesoTransacciones
//	public ModelAndView procesarAltaBoleta(HttpServletRequest request, @ModelAttribute("valoresDto") ValoresDto valoresDto, BindingResult result, HttpServletResponse res) throws Exception {
//		
//		if(!Validaciones.isCollectionNotEmpty(valoresDto.getListaValoresDto())){
//			valoresDto.setErrorListaVacia(true);
//			return boletasAlta(request, valoresDto);
//		}
//
//		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
//		
//		ArrayList<ValorDto> listavalorDTO = valoresDto.getListaValoresDto();
//		ArrayList<ValorDto> listaAux = new ArrayList<ValorDto>();
//	
//		for(ValorDto valor :listavalorDTO){
//			listaAux.add(valor);
//		}
//		valoresDto.setListaValoresDto(listaAux);
//
//		ModelAndView mv = new ModelAndView(VALOR_ACTUALIZACION_OK_VIEW);
//		if(boletaConValorServicio.validarDuplicidadAlta(valoresDto, userSesion)) {
//			return boletasAlta(request, valoresDto);
//		}
//		if(boletaConValorServicio.validarUnicidadRecibo(valoresDto, userSesion)){
//			valoresDto.setErrorRecibo(true);
//			return boletasAlta(request, valoresDto);
//		}
//		ActualizacionExitosaDto actualizacionDto = boletaConValorServicio.administrarValores(valoresDto, userSesion);	
//		
//		mv.addObject("mensajeAlta", actualizacionDto.getNumerosBoletasAlta());
//		mv.addObject("mensajeAltaDuplicado", actualizacionDto.getNumerosBoletasDuplicados());
//		mv.addObject("mensajesMostrarEnvioMail", actualizacionDto.getMensajesMostrarEnvioMail());
//		mv.addObject("url", "valores-boletas-alta");
//		
//		//Armado de Constancias.
//		logicaArchivosImprimibles(request, actualizacionDto, mv);
//		return mv;
//	}
	 @RequestMapping("valor/crear-boleta-con-valor")
	@ControlProcesoTransacciones
	public @ResponseBody ValorJsonResponse crearBoletaConValor(HttpServletRequest request, @RequestBody final ValoresDto valoresDto) throws Exception {
		ValorJsonResponse response = new ValorJsonResponse();
		if (!Validaciones.isCollectionNotEmpty(valoresDto.getListaValoresDto())) {
			response.setErrorListaVacia(true);
		} else {
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			ValoresDto valoresValidadcionDto = new ValoresDto();
			BeanPropertyBindingResult errors = new BeanPropertyBindingResult(valoresValidadcionDto, "test");

			for(ValorDto valor : valoresDto.getListaValoresDto()) {
				valor.setOperation(Constantes.CREATE);
				valoresValidadcionDto.setValorDto(valor);
				boletaConValorValidacionWeb.validarValoresDto(valoresValidadcionDto, errors);
				if (
					Validaciones.isCollectionNotEmpty(valor.getListaComprobantes()) &&
					Validaciones.isCollectionNotEmpty(userSesion.getComprobantesAGuardar())
				) {
					for (ComprobanteDto comprobanteDto : valor.getListaComprobantes()) {
						int i = userSesion.getComprobantesAGuardar().indexOf(comprobanteDto);
						if (i > -1) {
							comprobanteDto.setDocumento(userSesion.getComprobantesAGuardar().get(i).getDocumento());
							comprobanteDto.setUsuarioCreacion(userSesion.getIdUsuario());
							comprobanteDto.setFechaAlta(new Date());
						}
					}
				}
			}
			
			if (errors.hasErrors()) {
				// TODO VER SI LO DEJO TEMPORAL O NO.
				Traza.error(getClass(), "Error no se estan validado bien los datos o no se estan mapeado");
				throw new NegocioExcepcion("Error no se estan validado bien los datos o no se estan mapeado");
			}
			//valoresDto.setListaValoresDto(listaAux);

			if (boletaConValorServicio.validarDuplicidadAlta(valoresDto, userSesion)) {
				response.setErrorValorDuplicado(true);
				response.setSuccess(false);

				
				for (String mensajes : valoresDto.getErrorValorDuplicadoMensaje()) {
					response.getListaErrores().add(Utilidad.formateadoVista(mensajes));
				}
			} else if(boletaConValorServicio.validarUnicidadRecibo(valoresDto, userSesion)) {
				response.setErrorRecibo(true);
				response.setSuccess(false);
	
				for (ValorDto valor : valoresDto.getListaValoresDto()) {
					response.getListaErrores().add(Utilidad.formateadoVista(valor.getMensajeReciboError()));
				}
			} else {
				ActualizacionExitosaDto actualizacionDto = boletaConValorServicio.administrarValores(valoresDto, userSesion);
				response.setSuccess(true);
				if (Validaciones.isCollectionNotEmpty(actualizacionDto.getNumerosBoletasAlta())) {
					response.setMensajeAlta(StringUtils.join(actualizacionDto.getNumerosBoletasAlta(), '-'));
				}
				if (Validaciones.isCollectionNotEmpty(actualizacionDto.getNumerosBoletasDuplicados())) {
					response.setMensajeAltaDuplicado(StringUtils.join(actualizacionDto.getNumerosBoletasDuplicados(), '-'));
				}
				if (Validaciones.isCollectionNotEmpty(actualizacionDto.getMensajesMostrarEnvioMail())) {
					response.setMensajesMostrarEnvioMail(StringUtils.join(actualizacionDto.getMensajesMostrarEnvioMail(), '-'));
				}
				response.setUrl("valores-boletas-alta");
				response.setAction(VALOR_ACTUALIZACION_OK_AC);
				response.setImprimible(logicaArchivosImprimibles(request, actualizacionDto, null));
			}
		}
		return response;
	}
//	@RequestMapping("/procesar-alta-valor-aviso")
//	@ControlProcesoTransacciones
//	public ModelAndView procesarAltaValor(HttpServletRequest request, @ModelAttribute("valoresDto") ValoresDto valoresDto, BindingResult result) throws Exception {
//		
//		if(!Validaciones.isCollectionNotEmpty(valoresDto.getListaValoresDto())){
//			valoresDto.setErrorListaVacia(true);
//			return avisosAlta(request, valoresDto);
//		}
//
//		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
//				
//		ArrayList<ValorDto> listavalorDTO = valoresDto.getListaValoresDto();
//		ArrayList<ValorDto> listaAux = new ArrayList<ValorDto>();
//		
//		for(ValorDto valor :listavalorDTO){
//			/** @author fabio.giaquinta.ruiz 
//			 * Agregado para que ya se setee el campo
//			 * Documentacion Original Recibida en NO
//			 * para que no genere modificaciones luego
//			 */
//			listavalorDTO.get(listavalorDTO.indexOf(valor)).setDocumentacionOriginalRecibida(SiNoEnum.NO.getDescripcion());
//			
//			/** @author fabio.giaquinta.ruiz 
//			 * Se agrego la copia del IdValor del cheque a reemplazar
//			 * ya que se usa el metodo getIdValorAsociado al cheque a reemplazar
//			 * en el mapeador para guardar la relación
//			 */
//			listavalorDTO.get(listavalorDTO.indexOf(valor)).setIdValorAsociadoAlChequeAReemplazar(valor.getNroChequeAReemplazar());
//			
//			listaAux.add(valor);
//		}
//		valoresDto.setListaValoresDto(listaAux);
//
//		ModelAndView mv = new ModelAndView(VALOR_ACTUALIZACION_OK_VIEW);
//		
//		
//		if(boletaConValorServicio.validarDuplicidadAlta(valoresDto, userSesion)){
//			return avisosAlta(request, valoresDto);
//		}		
//		if(boletaConValorServicio.validarUnicidadRecibo(valoresDto, userSesion)){
//			valoresDto.setErrorRecibo(true);
//			return avisosAlta(request, valoresDto);
//		}
//		ActualizacionExitosaDto actualizacionDto = boletaConValorServicio.administrarValores(valoresDto, userSesion);
//
//		mv.addObject("mensajeAlta", actualizacionDto.getNumerosBoletasAlta());
//		mv.addObject("mensajeAltaDuplicado", actualizacionDto.getNumerosBoletasDuplicados());
//		mv.addObject("url", "valores-avisos-alta");
//
//		return mv;
//	}

	/**
	 * Procesa el alta de un asviso de pago. En la version modificada del avista
	 * @param request
	 * @param valoresDto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="valor/crear-aviso-de-pago", method=RequestMethod.POST)
	@ControlProcesoTransacciones
	public @ResponseBody ValorJsonResponse crearAvisoDePago(HttpServletRequest request, @RequestBody final ValoresDto valoresDto) throws Exception {
		ValorJsonResponse response = new ValorJsonResponse();
		if (!Validaciones.isCollectionNotEmpty(valoresDto.getListaValoresDto())) {
			response.setErrorListaVacia(true);
		} else {
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			ValoresDto valoresValidadcionDto = new ValoresDto();
			BeanPropertyBindingResult errors = new BeanPropertyBindingResult(valoresValidadcionDto, "test");
			
			for(ValorDto valor : valoresDto.getListaValoresDto()) {
				valor.setOperation(Constantes.CREATE);
				valoresValidadcionDto.setValorDto(valor);
				boletaConValorValidacionWeb.validarValoresDto(valoresValidadcionDto, errors);
				/** @author fabio.giaquinta.ruiz 
				 * Agregado para que ya se setee el campo
				 * Documentacion Original Recibida en NO
				 * para que no genere modificaciones luego
				 */
				//listavalorDTO.get(listavalorDTO.indexOf(valor)).setDocumentacionOriginalRecibida(SiNoEnum.NO.getDescripcion());
				valor.setDocumentacionOriginalRecibida(SiNoEnum.NO.getDescripcion());
				/** @author fabio.giaquinta.ruiz 
				 * Se agrego la copia del IdValor del cheque a reemplazar
				 * ya que se usa el metodo getIdValorAsociado al cheque a reemplazar
				 * en el mapeador para guardar la relación
				 */
				//listavalorDTO.get(listavalorDTO.indexOf(valor)).setIdValorAsociadoAlChequeAReemplazar(valor.getNroChequeAReemplazar());
				valor.setIdValorAsociadoAlChequeAReemplazar(valor.getNroChequeAReemplazar());
				//listaAux.add(valor);
				if (
					Validaciones.isCollectionNotEmpty(valor.getListaComprobantes()) &&
					Validaciones.isCollectionNotEmpty(userSesion.getComprobantesAGuardar())
				) {
					for (ComprobanteDto comprobanteDto : valor.getListaComprobantes()) {
						int i = userSesion.getComprobantesAGuardar().indexOf(comprobanteDto);
						if (i > -1) {
							comprobanteDto.setDocumento(userSesion.getComprobantesAGuardar().get(i).getDocumento());
							comprobanteDto.setUsuarioCreacion(userSesion.getIdUsuario());
							comprobanteDto.setFechaAlta(new Date());
						}
					}
				}
			}
			if (errors.hasErrors()) {
				// TODO VER SI LO DEJO TEMPORAL O NO.
				Traza.error(getClass(), "Error no se estan validado bien los datos o no se estan mapeado");
				throw new NegocioExcepcion("Error no se estan validado bien los datos o no se estan mapeado");
			}
			//valoresDto.setListaValoresDto(listaAux);

			if (boletaConValorServicio.validarDuplicidadAlta(valoresDto, userSesion)) {
				response.setErrorValorDuplicado(true);
				response.setSuccess(false);
				for (String mensajes : valoresDto.getErrorValorDuplicadoMensaje()) {
					response.getListaErrores().add(Utilidad.formateadoVista(mensajes));
				}
				
			} else if(boletaConValorServicio.validarUnicidadRecibo(valoresDto, userSesion)){
				response.setErrorRecibo(true);
				response.setSuccess(false);
				
				for (ValorDto valor : valoresDto.getListaValoresDto()) {
					response.getListaErrores().add(Utilidad.formateadoVista(valor.getMensajeReciboError()));
				}
			} else {
				ActualizacionExitosaDto actualizacionDto = boletaConValorServicio.administrarValores(valoresDto, userSesion);
				response.setSuccess(true);
				if (Validaciones.isCollectionNotEmpty(actualizacionDto.getNumerosBoletasAlta())) {
					response.setMensajeAlta(StringUtils.join(actualizacionDto.getNumerosBoletasAlta(), '-'));
				}
				if (Validaciones.isCollectionNotEmpty(actualizacionDto.getNumerosBoletasDuplicados())) {
					response.setMensajeAltaDuplicado(StringUtils.join(actualizacionDto.getNumerosBoletasDuplicados(), '-'));
				}
				response.setUrl("valores-avisos-alta");
				response.setAction(VALOR_ACTUALIZACION_OK_AC);
			}
		}
		return response;
	}
	@RequestMapping(value="/valores-actualizacion-ac", method=RequestMethod.POST)
	public ModelAndView valoresActualizacionAc(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView(VALOR_ACTUALIZACION_OK_VIEW);

		String mensajeAlta = request.getParameter("mensajeAlta");
		String mensajeAltaDuplicado = request.getParameter("mensajeAltaDuplicado");
		String imprimible = request.getParameter("imprimible");
		String mensajesMostrarEnvioMail = request.getParameter("mensajesMostrarEnvioMail");

		if (!Validaciones.isNullEmptyOrDash(mensajeAlta)) {
			mv.addObject("mensajeAlta", mensajeAlta.split("-"));
		}
		if (!Validaciones.isNullEmptyOrDash(mensajeAltaDuplicado)) {
			mv.addObject("mensajeAltaDuplicado", mensajeAltaDuplicado.split("-"));
		}
		if (!Validaciones.isNullEmptyOrDash(imprimible)) {
			mv.addObject("imprimible", Boolean.parseBoolean(imprimible));
		}
		if (!Validaciones.isNullEmptyOrDash(imprimible)) {
			mv.addObject("imprimible", Boolean.parseBoolean(imprimible));
		}
		if (!Validaciones.isNullEmptyOrDash(mensajesMostrarEnvioMail)) {
			mv.addObject("mensajesMostrarEnvioMail", mensajesMostrarEnvioMail.split("-"));
		}
		mv.addObject("url", request.getParameter("url"));

		return mv;
	}
//	@RequestMapping("/procesar-alta-comprobante")
//	public ModelAndView procesarAltaComprobante(HttpServletRequest request,
//			@ModelAttribute("valoresDto") @Valid ValoresDto valoresDto,
//			BindingResult result) throws Exception {
//
//		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
//		MultipartFile file;
//		file = valoresDto.getFileComprobante();
//
//		if (result.hasErrors()) {
//			ModelAndView mv = new ModelAndView();
//			mv = avisosAlta(request, valoresDto);
//			return mv;
//		}
//
//		ValorDto valorDto = valoresDto.getValorDto();
//		ComprobanteDto comprobanteDto = new ComprobanteDto();
//		comprobanteDto.setDescripcionArchivo(valorDto.getDescripcionComprobante());
//		comprobanteDto.setUsuarioCreacion(userSesion.getIdUsuario());
//		comprobanteDto.setNombreArchivo(file.getOriginalFilename());
//		comprobanteDto.setDocumento(file.getBytes());
//
//		valorDto.getListaComprobantes().add(comprobanteDto);
//		int i = 0;
//
//		Iterator<ComprobanteDto> it = valorDto.getListaComprobantes().iterator();
//		while (it.hasNext()) {
//			ComprobanteDto comp = it.next();
//			comp.setId(i);
//			i++;
//		}
//		if(valorDto.getListaComprobantes().size() == 0){
//			valorDto.setComboComprobante(true);
//		} else {
//			valorDto.setComboComprobante(false);
//		}
//		valorDto.setDescripcionComprobante("");
//		valoresDto.setValorDto(valorDto);
//		return avisosAlta(request, valoresDto);
//	}
	
	@RequestMapping("/procesar-alta-comprobante-modificacion")
	public ModelAndView procesarAltaComprobanteModificacion(HttpServletRequest request,
			@ModelAttribute("valorDto") @Valid ValorDto valorDto,
			BindingResult result) throws Exception {

		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView(MODIFICACION_VIEW);
			mv.addObject("valorDto", valorDto);
			mv.addObject("ultimoMes", false);
			mv.addObject("imprimibleArchivo", false);
			return mv;
		}
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		limpiarFechas(valorDto);
		String descripcionComprobante = request.getParameter("descripcionComprobante");
		
		MultipartFile file;
		file = valorDto.getFileComprobanteModificacion();
		
		if (Validaciones.isNullOrEmpty(descripcionComprobante)|| Validaciones.isNullOrEmpty(file.getOriginalFilename())) {
			ModelAndView mv = new ModelAndView();
			mv = valoresModificacion(request, valorDto);
			if (Validaciones.isNullOrEmpty(descripcionComprobante)) {
				valorDto.setFileComprobanteModificacion(file);
				valorDto.setComprobanteDescripcionVacioModif(true);			
			}
			if (Validaciones.isNullOrEmpty(file.getOriginalFilename())) {
				valorDto.setComprobantePathVacioModif(true);
			}
			return mv;
		}

		ComprobanteDto comprobanteDto = new ComprobanteDto();
		comprobanteDto.setDescripcionArchivo(descripcionComprobante);
		comprobanteDto.setUsuarioCreacion(userSesion.getIdUsuario());
		comprobanteDto.setNombreArchivo(file.getOriginalFilename());
		comprobanteDto.setDocumento(file.getBytes());
		
		valorDto.getListaComprobantes().add(comprobanteDto);
		int i = 0;

		Iterator<ComprobanteDto> it = valorDto.getListaComprobantes().iterator();
		while (it.hasNext()) {
			ComprobanteDto comp = it.next();
			comp.setId(i);
			i++;
		}
		if(valorDto.getListaComprobantes().size() == 0){
			valorDto.setComboComprobante(true);
		} else {
			valorDto.setComboComprobante(false);
		}
		valorDto.setDescripcionComprobante("");
		return valoresModificacion(request, valorDto);
	}

	@RequestMapping("/procesar-eliminar-comprobante")
	public ModelAndView procesarEliminarComprobante(HttpServletRequest request,
			@ModelAttribute("valoresDto") ValoresDto valoresDto,
			BindingResult result) throws Exception {

		String idComprobante = valoresDto.getIdComprobanteSelected();
		ValorDto valorDto = valoresDto.getValorDto();

		Iterator<ComprobanteDto> it = valorDto.getListaComprobantes().iterator();
		while (it.hasNext()) {
			ComprobanteDto comp = it.next();
			if (idComprobante.equals(comp.getId().toString())) {
				it.remove();
			}
		}
		if(valorDto.getListaComprobantes().size() == 0){
			valorDto.setComboComprobante(true);
		} else {
			valorDto.setComboComprobante(false);
		}
		
		valoresDto.setValorDto(valorDto);
		return avisosAlta(request, valoresDto);
	}
	
	@RequestMapping("/procesar-abrir-comprobante")
	public ModelAndView procesarAbrirComprobante(HttpServletRequest request,
			@ModelAttribute("valoresDto") ValoresDto valoresDto,
			BindingResult result) throws Exception {

		String idComprobante = valoresDto.getIdComprobanteSelected();
		ValorDto valorDto = valoresDto.getValorDto();

		ModelAndView mv = avisosAlta(request, valoresDto);
		
		Iterator<ComprobanteDto> it = valorDto.getListaComprobantes().iterator();
		while (it.hasNext()) {
			ComprobanteDto comp = it.next();
			if (idComprobante.equals(comp.getId().toString())) {
				request.getSession().setAttribute( "documentoImprimible" , comp.getDocumento());
				request.getSession().setAttribute( "nombreDocumento" , comp.getNombreArchivo());
			    mv.addObject("imprimibleArchivo", true);
			}
		}
		if(valorDto.getListaComprobantes().size() == 0){
			valorDto.setComboComprobante(true);
		} else {
			valorDto.setComboComprobante(false);
		}
		valoresDto.setValorDto(valorDto);
		return mv;
	}
	
	@RequestMapping("/procesar-eliminar-comprobante-modificacion")
	public ModelAndView procesarEliminarComprobanteModificacion(HttpServletRequest request,
			@ModelAttribute("valorDto") ValorDto valorDto,
			BindingResult result) throws Exception {

		String idComprobante = valorDto.getIdComprobanteSelected();

		Iterator<ComprobanteDto> it = valorDto.getListaComprobantes().iterator();
		while (it.hasNext()) {
			ComprobanteDto comp = it.next();
			if (idComprobante.equals(comp.getId().toString())) {
				it.remove();
			}
		}
		if(valorDto.getListaComprobantes().size() == 0){
			valorDto.setComboComprobante(true);
		} else {
			valorDto.setComboComprobante(false);
		}
		
		return valoresModificacion(request, valorDto);
	}
	
	@RequestMapping("/procesar-abrir-comprobante-modificacion")
	public ModelAndView procesarAbrirComprobanteModificacion(HttpServletRequest request,
			@ModelAttribute("valorDto") ValorDto valorDto,
			BindingResult result) throws Exception {

		String idComprobante = valorDto.getIdComprobanteSelected();
		
		ModelAndView mv = valoresModificacion(request, valorDto);

		Iterator<ComprobanteDto> it = valorDto.getListaComprobantes().iterator();
		while (it.hasNext()) {
			ComprobanteDto comp = it.next();
			if (idComprobante.equals(comp.getId().toString())) {
				request.getSession().setAttribute( "documentoImprimible" , comp.getDocumento());
				request.getSession().setAttribute( "nombreDocumento" , comp.getNombreArchivo());
			    mv.addObject("imprimibleArchivo", true);
			}
		}
		if(valorDto.getListaComprobantes().size() == 0){
			valorDto.setComboComprobante(true);
		} else {
			valorDto.setComboComprobante(false);
		}
		return mv;
	}
	
	@RequestMapping("/procesar-abrir-comprobante-historial")
	public ModelAndView procesarAbrirComprobanteHistorial(HttpServletRequest request,
			@ModelAttribute("valorDto") ValorDto valorDto,
			BindingResult result) throws Exception {

		String idComprobante = valorDto.getIdComprobanteSelected();
		
		ValorDto valorDTO = boletaConValorServicio.buscarValorModificacion(valorDto.getIdValor().toString());
		ModelAndView mv =  valoresHistorial(request, valorDTO);

		Iterator<ComprobanteDto> it = valorDTO.getListaComprobantes().iterator();
		while (it.hasNext()) {
			ComprobanteDto comp = it.next();
			if (idComprobante.equals(comp.getId().toString())) {
				request.getSession().setAttribute( "documentoImprimible" , comp.getDocumento());
				request.getSession().setAttribute( "nombreDocumento" , comp.getNombreArchivo());
			    mv.addObject("imprimibleArchivo", true);
			}
		}
		if(valorDTO.getListaComprobantes().size() == 0){
			valorDTO.setComboComprobante(true);
		} else {
			valorDTO.setComboComprobante(false);
		}
		return mv;
	}

	@RequestMapping("/procesar-modificacion-valor")
	public ModelAndView procesarModificacionValor(HttpServletRequest request,@ModelAttribute("valorDto") ValorDto valorDto, BindingResult result) throws Exception {
		//Para la concurrencia
		String timeStamp = valorDto.getTimeStamp();
		String pantallaRegreso = valorDto.getPantallaRegreso();
		
		valorDto.setImporte(Utilidad.formatCurrencySacarPesos(valorDto.getImporte()));
		limpiarCombos(valorDto);
		limpiarFechas(valorDto);
		boletaConValorValidacionWeb.validarValorDto(valorDto, result);

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(VALOR_ACTUALIZACION_OK_VIEW);

 		if (result.hasErrors()){
 			String mensajeNoHayModificacion = Propiedades.MENSAJES_PROPIEDADES.getString("error.noHayModificacion");
 			if(mensajeNoHayModificacion.equals(result.getAllErrors().get(0).getCode())){
 				
 				ValorDto valorGuardadoDto = boletaConValorServicio.buscarValorModificacion(valorDto.getIdValor().toString());
 				if(valorGuardadoDto.getListaComprobantes().size() == valorDto.getListaComprobantes().size()) {
 					return valoresModificacion(request, valorDto);
 				} else {
 					valorDto.setErrorNingunaModificacion(false);
 				}
 				
 			} else {
 				
 				if (Validaciones.isNullOrEmpty(valorDto.getTimeStamp())) {
 					valorDto.setTimeStamp(timeStamp);
 				}
 				return valoresModificacion(request, valorDto);
 			}
		}
 		
 		//TODO: IM0484864 - Es comentario temporal - no definitivo - Descomentar cuando resolvamos por completo
// 		if(!(VALOR_PARA_BOLETA_CHEQUE.equals(valorDto.getIdTipoValor()) 
// 				|| VALOR_PARA_BOLETA_CHEQUE_PD.equals(valorDto.getIdTipoValor()) 
// 				|| VALOR_PARA_BOLETA_EFECTIVO.equals(valorDto.getIdTipoValor())
// 				|| TipoValorEnum.INTERDEPÓSITO.getIdTipoValorString().equals(valorDto.getIdTipoValor())))
// 		{
// 			if(!Validaciones.isCollectionNotEmpty(valorDto.getListaComprobantes())){
// 				valorDto.setErrorComprobanteVacioModif(true);
// 				
// 				if (Validaciones.isNullOrEmpty(valorDto.getTimeStamp())) {
// 					valorDto.setTimeStamp(timeStamp);
// 				}
// 				return valoresModificacion(request, valorDto);
// 			}
// 		}
 		
 		valorDto.setUsuarioModificacion(userSesion.getIdUsuario());
 		valorDto.setErrorNingunaModificacion(false);
 		
 		//Manejo de Duplicidad en Recibo
 		ValoresDto valoresDto = new ValoresDto();
 		valoresDto.getListaValoresDto().add(valorDto);
 			
 		if(!esValorOriginalAModificar(valorDto)){
 			if(boletaConValorServicio.validarDuplicidadAlta(valoresDto, userSesion)){
 				if (Validaciones.isNullOrEmpty(valorDto.getTimeStamp())) {
 					valorDto.setTimeStamp(timeStamp);
 				}
 				
 				//Se hace esto para no perder el mv.
 	 			mv = valoresModificacion(request, valorDto);
 				mv.addObject("errorValorDuplicado", true);
 	 			mv.addObject("errorValorDuplicadoMensaje", valoresDto.getErrorValorDuplicadoMensaje());
 	 			
 	 			return mv;
 			}
 		}
 		/**
 		 * U562163 - IM0531696 - Se quita la validacion de recibos en la modificacion del cliente de un valor suspendido.
 		 */
 		
		// CHEQUEAR EL TEMA DEL SUSPENDIDO, PARA Q CAMBIE SOOLO EL CLIENTE Y NO VALIDE RECIBO
 		
 		if(!Validaciones.isNullOrEmpty(valorDto.getReciboPreImpreso())) {

 			if(boletaConValorServicio.validarUnicidadRecibo(valoresDto, userSesion)){

 				if (Validaciones.isNullOrEmpty(valorDto.getTimeStamp())) {
 					valorDto.setTimeStamp(timeStamp);
 				}
 				return valoresModificacion(request, valorDto);
 			}
 		}
		// 		
 		//Tratamiento de concurrencias
 		try {
 			
 			//Administracion de la Modificacion
 			ActualizacionExitosaDto actualizacionDto = boletaConValorServicio.administrarModificar(valorDto, userSesion);
 			if(!actualizacionDto.getArchivosImprimirConstancia().isEmpty()){
 				//Armado de Constancias.
 				logicaArchivosImprimibles(request, actualizacionDto, mv);
 			}
 			
		} catch (ConcurrenciaExcepcion e) {
			userSesion.setVolviendoABusqueda(true);
			mv=new ModelAndView(ERROR_CONCURRENCIA_VIEW);
			mv.addObject("url","vuelta-busqueda");
			return mv;
		} 
		
		userSesion.setVolviendoABusqueda(true);
		
		mv.addObject("mensajeAlta", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		// Si se accedio desde la bandeja de entrada, se retorna a la misma
		if (Constantes.DESTINO_BANDEJA_ENTRADA.equals(pantallaRegreso)) {
			mv.addObject("url","bandeja-de-entrada");
		} else if (Constantes.DESTINO_VUELTA_BUSQUEDA.equals(pantallaRegreso)) {
			mv.addObject("url",Constantes.DESTINO_VUELTA_BUSQUEDA);
		} else {
			mv.addObject("url","conciliacion-cancelar-alta-valor");
		}
		
		return mv;
	}

	/**
	 * @param valorDto
	 * @return
	 * @throws NegocioExcepcion
	 */
	private boolean esValorOriginalAModificar(ValorDto valorDto)
			throws NegocioExcepcion {
		ValorDto valorGuardadoDto = boletaConValorServicio.buscarValorModificacion(valorDto.getIdValor().toString());
 		boolean valorOriginal = false;
 		
 		if(VALOR_PARA_BOLETA_CHEQUE.equals(valorDto.getIdTipoValor())){
 			BigDecimal bigDecimal;	
			bigDecimal = (BigDecimal) Utilidad.stringToBigDecimal(valorDto.getImporte());
			
			if (valorGuardadoDto.getIdBancoOrigen().equals(valorDto.getIdBancoOrigen())
					&& valorGuardadoDto.getNumeroCheque().equals(valorDto.getNumeroCheque())
					&& valorGuardadoDto.getImporte().equals(Utilidad.formatCurrency(bigDecimal, 2))) {
				valorOriginal = true;
			}
 		}
		if(VALOR_PARA_BOLETA_CHEQUE_PD.equals(valorDto.getIdTipoValor())){
			BigDecimal bigDecimal;	
			bigDecimal = (BigDecimal) Utilidad.stringToBigDecimal(valorDto.getImporte());
			
			if (valorGuardadoDto.getIdBancoOrigen().equals(valorDto.getIdBancoOrigen())
					&& valorGuardadoDto.getNumeroCheque().equals(valorDto.getNumeroCheque())
					&& valorGuardadoDto.getFechaVencimiento().equals(Utilidad.cambiarFormatoFechaDDMMAA(valorDto.getFechaVencimiento()))
					&& valorGuardadoDto.getImporte().equals(Utilidad.formatCurrency(bigDecimal, 2))) {
				valorOriginal = true;
			}
		}
		if(VALOR_CHEQUE.equals(valorDto.getIdTipoValor())){
			BigDecimal bigDecimal;	
			bigDecimal = (BigDecimal) Utilidad.stringToBigDecimal(valorDto.getImporte());
			
			if (valorGuardadoDto.getIdBancoOrigen().equals(valorDto.getIdBancoOrigen())
					&& valorGuardadoDto.getNumeroCheque().equals(valorDto.getNumeroCheque())
					&& valorGuardadoDto.getFechaDeposito().equals(Utilidad.cambiarFormatoFechaDDMMAA(valorDto.getFechaDeposito()))
					&& valorGuardadoDto.getImporte().equals(Utilidad.formatCurrency(bigDecimal, 2))) {
				valorOriginal = true;
			}
		}
		if(VALOR_CHEQUE_PD.equals(valorDto.getIdTipoValor())){
			BigDecimal bigDecimal;	
			bigDecimal = (BigDecimal) Utilidad.stringToBigDecimal(valorDto.getImporte());
			
			if (valorGuardadoDto.getIdBancoOrigen().equals(valorDto.getIdBancoOrigen())
					&& valorGuardadoDto.getNumeroCheque().equals(valorDto.getNumeroCheque())
					&& valorGuardadoDto.getFechaVencimiento().equals(Utilidad.cambiarFormatoFechaDDMMAA(valorDto.getFechaVencimiento()))
					&& valorGuardadoDto.getFechaDeposito().equals(Utilidad.cambiarFormatoFechaDDMMAA(valorDto.getFechaDeposito()))
					&& valorGuardadoDto.getImporte().equals(Utilidad.formatCurrency(bigDecimal, 2))) {
				valorOriginal = true;
			}
		}
		if(VALOR_PARA_TRANSFERENCIA.equals(valorDto.getIdTipoValor())){
			BigDecimal bigDecimal;	
			bigDecimal = (BigDecimal) Utilidad.stringToBigDecimal(valorDto.getImporte());
			
			if (valorGuardadoDto.getNumeroReferencia().equals(valorDto.getNumeroReferencia())
					&& valorGuardadoDto.getFechaTransferencia().equals(Utilidad.cambiarFormatoFechaDDMMAA(valorDto.getFechaTransferencia()))
					&& valorGuardadoDto.getImporte().equals(Utilidad.formatCurrency(bigDecimal, 2))) {
				valorOriginal = true;
			}
		}
		if(VALOR_PARA_INTERDEPOSITO.equals(valorDto.getIdTipoValor())){
			if (valorGuardadoDto.getNumeroInterdepositoCdif().equals(valorDto.getNumeroInterdepositoCdif())
					&& valorGuardadoDto.getFechaInterdeposito().equals(Utilidad.cambiarFormatoFechaDDMMAA(valorDto.getFechaInterdeposito()))
					&& valorGuardadoDto.getCodCliente().equals(valorDto.getCodCliente())) {
				valorOriginal = true;
			}
		}
		if(VALOR_PARA_RETENCION.equals(valorDto.getIdTipoValor())){
			if (valorGuardadoDto.getIdTipoImpuesto().equals(valorDto.getIdTipoImpuesto())
					&& valorGuardadoDto.getNroConstancia().equals(valorDto.getNroConstancia())) {
				valorOriginal = true;
			}
		}
		return valorOriginal;
	}
	
	@RequestMapping("/vuelta-busqueda")
	public ModelAndView vueltaBusqueda(HttpServletRequest request, BoletaConValorFiltro boletaConValorFiltro, BindingResult result) throws Exception {

		BoletaConValorFiltro boletaConValorFiltroSession = (BoletaConValorFiltro) request.getSession().getAttribute("busquedaFiltro");
		ModelAndView mv = buscarValores(request, boletaConValorFiltroSession, result);
		mv.addObject("boletaConValorFiltro", boletaConValorFiltroSession);
		return mv;
	}

	@RequestMapping("/vuelta-busqueda-cambioestado")
	public ModelAndView vueltaBusquedaCambioEstado(HttpServletRequest request, BoletaConValorFiltro boletaConValorFiltro, BindingResult result) throws Exception {

		BoletaConValorFiltro boletaConValorFiltroSession = (BoletaConValorFiltro) request.getSession().getAttribute("busquedaFiltro");
		ModelAndView mv = buscarValores(request, boletaConValorFiltroSession, result);
		mv.addObject("boletaConValorFiltro", boletaConValorFiltroSession);
		return mv;
	}
	
	
	
	/***
	 * BUSQUEDAS
	 */
	@RequestMapping("/buscar-valores")
	@ControlProcesoTransacciones
	public ModelAndView buscarValores(HttpServletRequest request,
			@ModelAttribute("boletaConValorFiltro") @Valid BoletaConValorFiltro boletaConValorFiltro,
			BindingResult result) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		if (result.hasErrors()) {
			return valoresBusqueda(request, boletaConValorFiltro, result);
		}

		ModelAndView mv = new ModelAndView(BUSQUEDA_VIEW);
		try {
			if(!Validaciones.isNullOrEmpty(boletaConValorFiltro.getIdCobroShivaFiltro())){
				String idCobroShivaFiltroFormateado = Utilidad.rellenarCerosIzquierda(boletaConValorFiltro.getIdCobroShivaFiltro(),7);
				boletaConValorFiltro.setIdCobroShivaFiltro(idCobroShivaFiltroFormateado);
			}

			if (userSesion.esSupervisor()) {
				boletaConValorFiltro.setUsuarioLogeado(userSesion);
			}
			mv = valoresBusqueda(request, boletaConValorFiltro, result);

			request.getSession().setAttribute("busquedaFiltro", boletaConValorFiltro);

			mv.addObject("fechaFiltro", true);
			mv.addObject("realizarBusqueda", true);
			mv.addObject("imprimible", false);
		} catch (Throwable ex) {
			mv.addObject("fechaFiltro", true);
			mv.addObject("imprimible", false);
			mv.addObject("realizarBusqueda", false);
			//Otros errores que no sean de la validacion
			Traza.error(getClass(), ex.getMessage(), ex);
			if (ex.getCause()!=null && ex.getCause() instanceof RemoteAccessException) {

				mv.addObject("errorBusqueda", Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.ws.caida"));
			} 
		}
		if(!Validaciones.isObjectNull(mv.getModel().get("tieneError"))){
			if((boolean) mv.getModel().get("tieneError")){
				String codigoLeyenda = (String) mv.getModel().get("codigoLeyenda");
				mv.addObject("errorBusqueda", codigoLeyenda);
				mv.addObject("realizarBusqueda", false);
			}else{
				mv.addObject("errorBusqueda", "");
			}
		}
		if(!Validaciones.isObjectNull(mv.getModel().get("tieneError2"))){
			if((boolean) mv.getModel().get("tieneError2")){
				String codigoLeyenda2 = (String) mv.getModel().get("codigoLeyenda2");
				mv.addObject("errorSelectCriterio", codigoLeyenda2);
				mv.addObject("realizarBusqueda", false);
			}else{
				mv.addObject("errorSelectCriterio", "");
			}
		}
		
		
		return mv;
	}
	
	/**
	 * Exporta el resultado de la busqueda de valores. Cuando supera un limite parametrizado
	 * u562163
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportandoBusquedaValores")
	public void exportacionDetalleCobro(HttpServletRequest request, HttpServletResponse response) throws Exception {
	//Descomentar cuando lo quieran usar
//		List<ValorDto> listaValores = (List<ValorDto>)  request.getSession().getAttribute("valores");
//
//		valoresReportsUtils.writeInResponse(IReportsUtils.POI_XLS, 
//		                                  response, listaValores, "Busqueda de Valores", null);

//		exportacionBusquedaValores.generarExportacionBusquedaValores(response, listaValores);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/jsonBuscarValores", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResultadoBusqValoresJsonResponse jsonBuscarValores(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoletaConValorFiltro boletaConValorFiltro = (BoletaConValorFiltro) request.getSession().getAttribute("busquedaFiltro");
		// Medicion de tiempo al inicio del procesamiento
		double fechaHoraInicioNanoTime = System.nanoTime();
		Date fechaHoraInicio = new Date();
		//TODO ACA
		ArrayList<ValorDto> listaValores = boletaConValorServicio.buscarValores(boletaConValorFiltro);	
//		request.getSession().setAttribute("valores",listaValores);
		ResultadoBusqValoresJsonResponse json = new ResultadoBusqValoresJsonResponse();
		
		listaValores = (ArrayList<ValorDto>) Utilidad.guionesNull(listaValores);
		ValorBusquedaDto dto = null;
		List<ValorBusquedaDto> valores = new ArrayList<ValorBusquedaDto>();
		for (ValorDto valor : listaValores) {
			dto = new ValorBusquedaDto(); 
			dto.setIdValor(valor.getIdValor().toString());
			dto.setEmpresa(valor.getEmpresa());
			dto.setEmpresasAsociadas(valor.getEmpresasAsociadas());
			dto.setFechaNtifDisponRetiroVal(valor.getFechaNtifDisponRetiroVal());
			dto.setCuitCliente(valor.getCuitCliente());
			dto.setSegmento(valor.getSegmento());
			dto.setRazonSocialClienteAgrupador(valor.getRazonSocialClienteAgrupador());
			dto.setCodCliente(valor.getCodCliente());
			dto.setFechaValorFormateado(valor.getFechaValorFormateado());
			dto.setTipoValor(valor.getTipoValor());
			dto.setIdTipoValor(valor.getIdTipoValor());
			dto.setImporte(valor.getImporte());
			dto.setSaldoDisponible(valor.getSaldoDisponible());
			dto.setEstadoValor(valor.getEstadoValor());
			dto.setIdEstadoValor(valor.getIdEstadoValor());
			dto.setNombreAnalista(valor.getNombreAnalista());
			dto.setUrlFotoAnalista((valor.getIdAnalista() != null && !valor.getIdAnalista().trim().isEmpty()) ? valor.urlFotoUsuario(valor.getIdAnalista()) : "");
			dto.setIdAnalista(valor.getIdAnalista());
//			dto.setMailAnalista(valor.getMailAnalista());
			dto.setCopropietario(valor.getCopropietario());
			dto.setUrlFotoCopropietario((valor.getIdCopropietario() != null && !valor.getIdCopropietario().trim().isEmpty()) ? valor.urlFotoUsuario(valor.getIdCopropietario()) : "");
			dto.setIdCopropietario(valor.getIdCopropietario());
//			dto.setMailCopropietario(valor.getMailCopropietario());
			dto.setUsuarioAutorizacion(valor.getUsuarioAutorizacion());
			dto.setUrlFotoUsuarioAutorizacion((valor.getIdUsuarioAutorizacion() != null && !valor.getIdUsuarioAutorizacion().trim().isEmpty()) ? valor.urlFotoUsuario(valor.getIdUsuarioAutorizacion()) : "");
//			dto.setMailUsuarioAutorizacion(valor.getMailUsuarioAutorizacion());
			dto.setUsuarioEjecutivo(valor.getUsuarioEjecutivo());
			dto.setUrlFotoUsuarioEjecutivo((valor.getIdUsuarioEjecutivo() != null && !valor.getIdUsuarioEjecutivo().trim().isEmpty()) ? valor.urlFotoUsuario(valor.getIdUsuarioEjecutivo()) : "");
//			dto.setMailUsuarioEjecutivo(valor.getMailUsuarioEjecutivo());
			dto.setUsuarioAsistente(valor.getUsuarioAsistente());
			dto.setUrlFotoUsuarioAsistente((valor.getIdUsuarioAsistente() != null && !valor.getIdUsuarioAsistente().trim().isEmpty()) ? valor.urlFotoUsuario(valor.getIdUsuarioAsistente()) : "");
//			dto.setMailUsuarioAsistente(valor.getMailUsuarioAsistente());
			dto.setUsuarioAnalistaTC(valor.getUsuarioAnalistaTeamComercial());
			dto.setIdAnalistaTC(valor.getIdAnalistaTeamComercial());
			dto.setUrlFotoAnalistaTC((valor.getIdAnalistaTeamComercial() != null && !valor.getIdAnalistaTeamComercial().trim().isEmpty()) ? valor.urlFotoUsuario(valor.getIdAnalistaTeamComercial()) : "");
//			dto.setMailUsuarioAnalistaTC(valor.getMailUsuarioAnalistaTeamComercial());
			dto.setUsuarioSupervisorTC(valor.getUsuarioSupervisorTeamComercial());
			dto.setUrlFotoSupervisorTC((valor.getIdSupervisorTeamComercial() != null && !valor.getIdSupervisorTeamComercial().trim().isEmpty()) ? valor.urlFotoUsuario(valor.getIdSupervisorTeamComercial()) : "");
//			dto.setMailusuarioSupervisorTC(valor.getMailUsuarioSupervisorTeamComercial());
			dto.setUsuarioGerenteRegionalTC(valor.getUsuarioGerenteRegionalTeamComercial());
//			dto.setMailUsuarioGerenteRegionalTC(valor.getMailUsuarioGerenteRegionalTeamComercial());
			dto.setUrlFotoGerenteRegionalTC((valor.getIdGerenteRegionalTeamComercial() != null && !valor.getIdGerenteRegionalTeamComercial().trim().isEmpty()) ? valor.urlFotoUsuario(valor.getIdGerenteRegionalTeamComercial()) : "");
			dto.setId(valor.getId().toString());
			dto.setOrigen(valor.getOrigen());
			dto.setIdOrigen(valor.getIdOrigen());
			dto.setBancoDeposito(valor.getBancoDeposito());
			dto.setIdAcuerdo(valor.getIdAcuerdo());
			dto.setNroBoleta(valor.getNroBoleta());
			dto.setReferenciaValor(valor.getReferenciaValor());
			dto.setBancoOrigen(valor.getBancoOrigen());
			dto.setTipoRetencion(valor.getTipoRetencion());
			dto.setProvincia(valor.getProvincia());
			dto.setNroCuitRetencion(valor.getNroCuitRetencion());
			dto.setCodigoOrganismo(valor.getCodOrganismo());
			dto.setReciboPreImpreso(valor.getReciboPreImpreso());
			dto.setConstancia(valor.getConstancia());
			dto.setOperacionAsociada(valor.getOperacionAsociada());
			dto.setFacturaRelacionada(valor.getFacturaRelacionada());
			dto.setDocumentacionOriginalRecibida(valor.getDocumentacionOriginalRecibida());
			dto.setMotivo(valor.getMotivo());
//			dto.setValorPadre(valor.getValorPadre());
			dto.setNumeroDocumentoContable(valor.getNumeroDocumentoContable());
			dto.setMotivoSuspension(valor.getMotivoSuspension());
			dto.setIdLegajoChequeRechazado(valor.getIdLegajoChequeRechazado());
			dto.setFechaNotificacionRechazo(valor.getFechaNotificacionRechazo());
			dto.setFechaRechazo(valor.getFechaRechazo());
			dto.setArchivoValoresAconciliar(valor.getArchivoValoresAconciliar());
			dto.setFechaAltaValor(valor.getFechaAltaValor());
			dto.setFechaIngresoRecibo(valor.getFechaIngresoRecibo());
			dto.setFechaEmision(valor.getFechaEmision());
			dto.setFechaEmisionCheque(valor.getFechaEmisionCheque());
			dto.setFechaVencimiento(valor.getFechaVencimiento());
			dto.setFechaTransferencia(valor.getFechaTransferencia());
			dto.setFechaDeposito(valor.getFechaDeposito());
			dto.setFechaDisponible(valor.getFechaDisponible());
			dto.setFechaUltimoEstado(valor.getFechaUltimoEstado());
			dto.setBoletaImpresaEstado((valor.getBoletaImpresaEstado() != null) ? valor.getBoletaImpresaEstado().getDescripcion() : "-");
			dto.setBoletaEnviadaMailEstado((valor.getBoletaEnviadaMailEstado() != null) ? valor.getBoletaEnviadaMailEstado().getDescripcion() : "-");
			if(!Validaciones.isNullOrEmpty(valor.getObservaciones())){
				dto.setObservaciones(valor.getObservaciones().replace("\r\n", " </br>"));
			}			
			dto.setCobroFormateado(valor.getCobroFormateado());
			dto.setEsSupervisorEmpresaSegmento(valor.getEsSupervisorEmpresaSegmento());
			valores.add(dto);
		}
		json.setAaData(valores);
		// Logueamos info de procesamiento (tiempos)
		Traza.loguearInfoProcesamiento(this.getClass(),"buscarValores", fechaHoraInicio, fechaHoraInicioNanoTime, json.getAaData().size());
		return json;	
	}
	
	/***
	 * BUSQUEDA BOTONES
	 */
	@RequestMapping("/procesar-valores-modificacion")
	public ModelAndView procesarModificacion(HttpServletRequest request,
			@ModelAttribute("valorDto") ValorDto valorDtoABuscar,
			BindingResult result) throws Exception {

		String pantallaRegreso = valorDtoABuscar.getPantallaDestino();
		String idValor = valorDtoABuscar.getIdValor().toString();
		
		
		ValorDto valorDTO = new ValorDto();
		valorDTO = boletaConValorServicio.buscarValorModificacion(idValor);
		valorDTO.setPantallaRegreso(pantallaRegreso);
		valorDTO.setVolverBandeja(pantallaRegreso != null && Constantes.DESTINO_BANDEJA_ENTRADA.equals(pantallaRegreso));
		
		if(!Validaciones.isNullOrEmpty(valorDTO.getCuitIbb())){
			valorDTO.setCuitIbb(Utilidad.formatearCuit(valorDTO.getCuitIbb()));
		}
		
		return valoresModificacion(request, valorDTO);
	}

	@RequestMapping("/buscar-historial-valor")
	public ModelAndView buscarHistorialValor(HttpServletRequest request, 
			@ModelAttribute("valorDto") ValorDto valorDtoABuscar,
			BindingResult result) throws Exception {

		String idValor = valorDtoABuscar.getIdValor().toString();
		
		ValorDto valorDTO = new ValorDto();
		valorDTO = boletaConValorServicio.buscarValorModificacion(idValor);
		
		if (
			MotivoSuspensionEnum.CHEQUE_RECHAZADO.codigo() == valorDTO.getIdMotivoSuspension()
		) {
			Set<Long> parametro = new HashSet<Long>();
			parametro.add(valorDTO.getIdValor());
			List<Bean> lista = legajoChequeRechazadoServicio.listaBusqueda(parametro);
			
			if (!lista.isEmpty()) {
				VistaSoporteResultadoBusquedaLegajoChequeRechazado vistaSoporteResultadoBusquedaLegajoChequeRechazado =
					(VistaSoporteResultadoBusquedaLegajoChequeRechazado) lista.get(0);
				valorDTO.setIdLegajoChequeRechazado(vistaSoporteResultadoBusquedaLegajoChequeRechazado.getIdLegajo().toString());
				valorDTO.setMotivoRechazo(vistaSoporteResultadoBusquedaLegajoChequeRechazado.getMotivo());
				valorDTO.setFechaNotificacionRechazo(
					Utilidad.formatDatePicker(vistaSoporteResultadoBusquedaLegajoChequeRechazado.getFechaNotificacion())
				);
				valorDTO.setFechaRechazo(
					Utilidad.formatDatePicker(vistaSoporteResultadoBusquedaLegajoChequeRechazado.getFechaRechazo())
				);
			}
		}
		
	
		return valoresHistorial(request, valorDTO);
	}
	
	@RequestMapping("/busqueda-mailbd")
	@ControlProcesoTransacciones
	public ModelAndView busquedaMailBD(HttpServletRequest request,  
			@ModelAttribute("boletaConValorFiltro") BoletaConValorFiltro boletaConValorFiltro, 
			HttpServletResponse res, BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()	.getAttribute(Constantes.LOGIN);
		request.setAttribute("boletaConValorFiltro", boletaConValorFiltro);		
		
		List<Long> idValores = new ArrayList<Long>();
		String[] idValoresAModificar = boletaConValorFiltro.getValoresSelected().split(",");
		for (String idValor: idValoresAModificar) {
			idValores.add(new Long(idValor));
		}
//		ArrayList<ValorDto>  listaDeValores =  boletaConValorServicio.buscarParaCambioEstado(userSesion, idValores);
		
		ModelAndView mv = new ModelAndView(VALOR_ACTUALIZACION_BUSQUEDA_OK_VIEW);
		mv.addObject("url", "vuelta-busqueda");
		mv.addObject("mensajesMostrarEnvioMail", boletaConValorServicio.busquedaMailBD(idValores, userSesion.getIdUsuario()));
		return mv;
	}
	
	@RequestMapping("/busqueda-Imprimirbd")
	@ControlProcesoTransacciones
	public ModelAndView busquedaImprimirBD(HttpServletRequest request,  
			@ModelAttribute("boletaConValorFiltro") BoletaConValorFiltro boletaConValorFiltro, 
			HttpServletResponse res, BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()	.getAttribute(Constantes.LOGIN);
		List<Long> idValores = new ArrayList<Long>();
		String[] idValoresAModificar = boletaConValorFiltro.getValoresSelected().split(",");
		for (String idValor: idValoresAModificar) {
			idValores.add(new Long(idValor));
		}
		
		ActualizacionExitosaDto actualizacionDto = new ActualizacionExitosaDto();
		actualizacionDto = boletaConValorServicio.busquedaImprimirBD(idValores, userSesion.getUsuario());
		
		BoletaConValorFiltro boletaConValorFiltroSession = (BoletaConValorFiltro) request.getSession().getAttribute("busquedaFiltro");
		ModelAndView mv = buscarValores(request, boletaConValorFiltroSession, result);
		
		logicaArchivosImprimibles(request, actualizacionDto, mv);
		
		mv.addObject("boletaConValorFiltro", boletaConValorFiltroSession);
		return mv;
	}
	
	@RequestMapping("/busqueda-Imprimir-constancia")
	@ControlProcesoTransacciones
	public ModelAndView busquedaImprimirConstancia(HttpServletRequest request, 
			@ModelAttribute("boletaConValorFiltro") BoletaConValorFiltro boletaConValorFiltro, 
			HttpServletResponse res, BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		List<Long> idValores = new ArrayList<Long>();
		String[] idValoresAModificar = boletaConValorFiltro.getValoresSelected().split(",");
		for (String idValor: idValoresAModificar) {
			idValores.add(new Long(idValor));
		}
		ArrayList<ValorDto>  listaDeValores =  boletaConValorServicio.buscarParaCambioEstado(userSesion, idValores);
		
		ActualizacionExitosaDto actualizacionDto = new ActualizacionExitosaDto();
		actualizacionDto = boletaConValorServicio.busquedaImprimirConstancia(listaDeValores);
		
		BoletaConValorFiltro boletaConValorFiltroSession = (BoletaConValorFiltro) request.getSession().getAttribute("busquedaFiltro");
		ModelAndView mv = buscarValores(request, boletaConValorFiltroSession, result);

		logicaArchivosImprimibles(request, actualizacionDto, mv);

		mv.addObject("boletaConValorFiltro", boletaConValorFiltroSession);
		return mv;
	}

	/************************************************************************************/
	/** Modulo - Confirmacion aviso pago
	/************************************************************************************/
	@RequestMapping("/ver-confirmacion-aviso-pago")
	public ModelAndView verConfirmacionAvisoPago(HttpServletRequest request, 
			@ModelAttribute("valorDto") ValorDto valorDto, BindingResult result) throws Exception {
		
		if (request.getParameter("idValorBandeja") != null) {
			String idValor = request.getParameter("idValorBandeja");
			valorDto = new ValorDto();
			valorDto = boletaConValorServicio.buscarValorModificacion(idValor);
		}
		if(Validaciones.isCollectionNotEmpty(valorDto.getListaComprobantes())){
			int i = 0;
			Iterator<ComprobanteDto> it = valorDto.getListaComprobantes().iterator();
			while (it.hasNext()) {
				ComprobanteDto comp = it.next();
				comp.setId(i);
				i++;
			}
		}
		
		
		ModelAndView mv = new ModelAndView(CONFIRMACION_AVISO_PAGO_VIEW);
		mv.addObject("valorDto", valorDto);	
		
		return mv;
	}
	
	@RequestMapping("/procesar-alta-comprobante-aviso-pago")
	public ModelAndView procesarAltaComprobanteAvisoPago(HttpServletRequest request,
			@ModelAttribute("valorDto") @Valid ValorDto valorDto,
			BindingResult result) throws Exception {

		limpiarFechas(valorDto);
		String descripcionComprobante = request.getParameter("descripcionComprobante");
		
		MultipartFile file;
		file = valorDto.getFileComprobanteModificacion();
		
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView();
			mv = verConfirmacionAvisoPago(request, valorDto, result);
			return mv;
		}
		
		if (Validaciones.isNullOrEmpty(descripcionComprobante)|| Validaciones.isNullOrEmpty(file.getOriginalFilename())) {
			ModelAndView mv = new ModelAndView();
			mv = verConfirmacionAvisoPago(request, valorDto, result);
			if (Validaciones.isNullOrEmpty(descripcionComprobante)) {
				valorDto.setFileComprobanteModificacion(file);
				valorDto.setComprobanteDescripcionVacioModif(true);			
			}
			if (Validaciones.isNullOrEmpty(file.getOriginalFilename())) {
				valorDto.setComprobantePathVacioModif(true);
			}
			return mv;
		}

		ComprobanteDto comprobanteDto = new ComprobanteDto();
		comprobanteDto.setDescripcionArchivo(descripcionComprobante);

		comprobanteDto.setNombreArchivo(file.getOriginalFilename());
		comprobanteDto.setDocumento(file.getBytes());

		valorDto.getListaComprobantes().add(comprobanteDto);
		int i = 0;

		Iterator<ComprobanteDto> it = valorDto.getListaComprobantes().iterator();
		while (it.hasNext()) {
			ComprobanteDto comp = it.next();
			comp.setId(i);
			i++;
		}
		if(valorDto.getListaComprobantes().size() == 0){
			valorDto.setComboComprobante(true);
		} else {
			valorDto.setComboComprobante(false);
		}
		valorDto.setDescripcionComprobante("");
		return verConfirmacionAvisoPago(request, valorDto, result);
	}
	
	
	@RequestMapping("/procesar-eliminar-comprobante-aviso-pago")
	public ModelAndView procesarEliminarComprobanteAvisoPago(HttpServletRequest request,
			@ModelAttribute("valorDto") ValorDto valorDto,
			BindingResult result) throws Exception {

		limpiarFechas(valorDto);
		String idComprobante = valorDto.getIdComprobanteSelected();

		Iterator<ComprobanteDto> it = valorDto.getListaComprobantes().iterator();
		while (it.hasNext()) {
			ComprobanteDto comp = it.next();
			if (idComprobante.equals(comp.getId().toString())) {
				it.remove();
			}
		}
		if(valorDto.getListaComprobantes().size() == 0){
			valorDto.setComboComprobante(true);
		} else {
			valorDto.setComboComprobante(false);
		}
		
		return verConfirmacionAvisoPago(request, valorDto, result);
	}
	
	@RequestMapping("/procesar-abrir-comprobante-aviso-pago")
	public ModelAndView procesarAbrirComprobanteAvisoPago(HttpServletRequest request, @ModelAttribute("valorDto") ValorDto valorDto, 
			BindingResult result) throws Exception {

		String idComprobante = valorDto.getIdComprobanteSelected();
		
		ValorDto valorDTO = boletaConValorServicio.buscarValorModificacion(valorDto.getIdValor().toString());
		ModelAndView mv =  verConfirmacionAvisoPago(request, valorDTO, result);
		
		if(Validaciones.isCollectionNotEmpty(valorDto.getListaComprobantes())){
			Iterator<ComprobanteDto> it = valorDTO.getListaComprobantes().iterator();
			while (it.hasNext()) {
				ComprobanteDto comp = it.next();
				if (idComprobante.equals(comp.getId().toString())) {
					request.getSession().setAttribute( "documentoImprimible" , comp.getDocumento());
					request.getSession().setAttribute( "nombreDocumento" , comp.getNombreArchivo());
					mv.addObject("imprimibleArchivo", "true");
				}
			}
		}
		if(valorDTO.getListaComprobantes().size() == 0){
			valorDTO.setComboComprobante(true);
		} else {
			valorDTO.setComboComprobante(false);
		}
		return mv;
	}

	@RequestMapping("/procesar-confirmacion-aviso-pago")
	@ControlProcesoTransacciones
	public ModelAndView procesarConfirmacionAvisoPago(HttpServletRequest request, 
			@ModelAttribute("valorDto") @Valid ValorDto valorDto, BindingResult result) throws Exception {
	
		//Para la concurrencia
		String timeStamp = valorDto.getTimeStamp();
				
		if (result.hasErrors()) {
			if (Validaciones.isNullOrEmpty(valorDto.getTimeStamp())) {
					valorDto.setTimeStamp(timeStamp);
			}
			return verConfirmacionAvisoPago(request, valorDto, result);
		} 
		
		if(!Validaciones.isCollectionNotEmpty(valorDto.getListaComprobantes())){
			
			if (Validaciones.isNullOrEmpty(valorDto.getTimeStamp())) {
				valorDto.setTimeStamp(timeStamp);
			}
			valorDto.setErrorComprobanteVacioModif(true);
			return verConfirmacionAvisoPago(request, valorDto, result);
		}
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()	.getAttribute(Constantes.LOGIN);
		valorDto.setUsuarioModificacion(userSesion.getIdUsuario());
		
		//Tratamiento de concurrencias
 		try {
 			boletaConValorServicio.confirmarAvisoDePago(valorDto);
		} catch (ConcurrenciaExcepcion e) {
			userSesion.setVolviendoABusqueda(true);
			ModelAndView mv = new ModelAndView(ERROR_CONCURRENCIA_VIEW);
			mv.addObject("url","bandeja-de-entrada");
			return mv;
		} catch (ValidacionExcepcion e){
			valorDto.setMensajeDuplicadoError(e.getMessage());
			return verConfirmacionAvisoPago(request, valorDto, result);
		}
		
		ModelAndView mv = new ModelAndView(ACTUALIZACION_OK_VIEW);		
		mv.addObject("mensaje",  Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		mv.addObject("url","bandeja-de-entrada");
		return mv;
	}
	
	@RequestMapping("/procesar-rechazo-aviso-pago")
	@ControlProcesoTransacciones
	public ModelAndView procesarRechazoAvisoPago(HttpServletRequest request, 
			@ModelAttribute("valorDto") @Valid ValorDto valorDto, BindingResult result) throws Exception {
	
		//Para la concurrencia
		String timeStamp = valorDto.getTimeStamp();
		
		if (result.hasErrors()) {
			if (Validaciones.isNullOrEmpty(valorDto.getTimeStamp())) {
				valorDto.setTimeStamp(timeStamp);
			}
			return verConfirmacionAvisoPago(request, valorDto, result);
		} 
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()	.getAttribute(Constantes.LOGIN);
		valorDto.setUsuarioModificacion(userSesion.getIdUsuario());
		
		//Tratamiento de concurrencias
 		try {
 			boletaConValorServicio.rechazarAvisoDePago(valorDto, userSesion);
 		} catch (ConcurrenciaExcepcion e) {
			userSesion.setVolviendoABusqueda(true);
			ModelAndView mv = new ModelAndView(ERROR_CONCURRENCIA_VIEW);
			mv.addObject("url","bandeja-de-entrada");
			return mv;
		} 
		
		ModelAndView mv = new ModelAndView(ACTUALIZACION_OK_VIEW);		
		mv.addObject("mensaje",  Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		mv.addObject("url","bandeja-de-entrada");
		return mv;
	}
		
	@RequestMapping(value = "/procesar-eliminar-tarea-anular-aviso-de-pago")
	public ModelAndView eliminarTareaYAnularAvisoDePago(HttpServletRequest request,
			@ModelAttribute("valorDto") ValorDto valorDto, BindingResult result) throws Exception {
		
		UsuarioSesion usuarioSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		valorDto.setUsuarioModificacion(usuarioSesion.getIdUsuario());
		boletaConValorServicio.anularAvisoDePago(valorDto);
		
		ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("eliminarTareaOK.avisoPago.mensaje"));
		mv.addObject("url","bandeja-de-entrada");
		
		return mv;
	}
	
	@RequestMapping(value = "/procesar-eliminar-tarea-anular-boleta")
	public ModelAndView eliminarTareaYAnularBoleta(HttpServletRequest request,
			@ModelAttribute("valorDto") ValorDto valorDto, BindingResult result) throws Exception {
		
		UsuarioSesion usuarioSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		valorDto.setUsuarioModificacion(usuarioSesion.getIdUsuario());
		boletaConValorServicio.anularBoleta(valorDto);
		
		ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("eliminarTareaOK.boleta.mensaje"));
		mv.addObject("url","bandeja-de-entrada");
		
		return mv;
	}
	
	@RequestMapping(value="/abrir-documento-plano", method=RequestMethod.GET)
	public void abrirDocumentoPlano(HttpServletResponse res,
			HttpServletRequest request) throws Exception {
	
		byte[] archivo = (byte[]) request.getSession().getAttribute("documentoImprimible");
		String nombreDocumento = (String) request.getSession().getAttribute("nombreDocumento");
		
		ControlArchivo.descargarComoArchivo(archivo, nombreDocumento, res);
	}
	/************************************************************************************/
	/** Fin - Confirmacion aviso pago
	/************************************************************************************/
	
	
	/************************************************************************************/
	/** Metodos - AJAX **/
	/************************************************************************************/
	@RequestMapping("/consultarClienteSiebelValor")
	public @ResponseBody String consultarClienteSiebel(HttpServletRequest request) {
		String str = "";
		try {
			String codCliente = request.getParameter("codCliente");

			if (!Validaciones.isNumeric(codCliente)) {
				return Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico");
			}

			str = this.clienteFacade.consultarClienteString(codCliente);
			/*cheque a reemplazar*/
			if (!"".equals(str)) {
				str +="|";
			}
			List<ValorDto> chequesReemplazar=null;
			if (Validaciones.isNullEmptyOrDash(str)) {
				 chequesReemplazar = boletaConValorServicio.buscarChequesReemplazar(codCliente);
				for (ValorDto val : chequesReemplazar) {
					str +=String.valueOf(val.getChequeReemplazarId())
									+ ";"
									+ String.valueOf(val
											.getChequeReemplazarNumero() + ";");
				}
			}
			if(!Validaciones.isNullOrEmpty(String.valueOf(chequesReemplazar))){
				if(chequesReemplazar.size()>1){
					str +="si";
				}
			}
			/*    cheque a reemplazar*/
		} catch (NegocioExcepcion ex) {
			Traza.error(getClass(), ex.getMessage(), ex);
			return Propiedades.MENSAJES_PROPIEDADES.getString("boleta.alta.mensaje.siebel.ws.error");
		}
		return str;
	}

	@RequestMapping(value = "valor/adjuntarComprobante", method = RequestMethod.POST)
	public void adjuntarComprobante(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String descripcion = request.getParameter("descripcion");
		
		Iterator<String> itr =  request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		ComprobanteJsonResponse json = new ComprobanteJsonResponse();
		try {
			
			boletaConValorValidacionWeb.validarComprobantes(file, descripcion);
			
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
			
			comprobantesAGuardarFinal.add(comprobante);
			userSesion.setComprobantesAGuardar(comprobantesAGuardarFinal);
			
			json.setSuccess(true);
			json.setIdComprobante(fechaID);
			json.setDescripcion(descripcion);
			json.setFileName(file.getOriginalFilename());
		} catch (ValidacionExcepcion e) {
			json.setSuccess(false);
			json.setCampoError(e.getCampoError());
			json.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString(e.getLeyenda()));
		}
		
		ControlArchivo.responderJSON(json, response);
	}
	
	@RequestMapping(value="valor/descargarComprobante", method=RequestMethod.GET)
	public void descargarComprobante(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String id = request.getParameter("id");
		List<ComprobanteDto> buscarAdjuntoPorIdAdjunto = null;

		if (!Validaciones.isNullOrEmpty(id)) {
			ComprobanteDto comprobanteADescargar = new ComprobanteDto();

			buscarAdjuntoPorIdAdjunto = userSesion.getComprobantesAGuardar();

			for (ComprobanteDto comprobante : buscarAdjuntoPorIdAdjunto) {
				if (comprobante.getIdComprobante().toString().equals(id)) {
					comprobanteADescargar = comprobante;
				} else if (!Validaciones.isObjectNull(comprobante.getIdPantallaComprobante())) {
					if (comprobante.getIdPantallaComprobante().toString().equals(id)) {
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

	@RequestMapping(value="valor/eliminarComprobante", method=RequestMethod.POST)
	@ResponseBody
	public ComprobanteJsonResponse eliminarComprobante(@RequestBody final ComprobanteDto comprobante, HttpServletRequest request) throws Exception {
		ComprobanteJsonResponse json = new ComprobanteJsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);

		List<ComprobanteDto> comprobantesAGuardar = userSesion.getComprobantesAGuardar();
		
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
	/************************************************************************************/
	/** FIN Metodos - AJAX **/
	/************************************************************************************/

	/************************************************************************************/
	/** Metodos para los combos **/
	/************************************************************************************/

	/***
	 * EMPRESA
	 */
	@RequestMapping("/seleccionoEmpresaValor")
	public ModelAndView seleccionoEmpresa(HttpServletRequest request,
			@ModelAttribute("valoresDto") ValoresDto boletaConValoresDto)
			throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
				.getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(ALTA_BOLETA_VIEW);
		mv = seleccionoEmpresaGlobal(boletaConValoresDto, userSesion, mv);
		mv.setViewName(ALTA_BOLETA_VIEW);
		validarCheckGenerarConstancia(boletaConValoresDto, mv);
		return mv;
	}

//	@RequestMapping("/seleccionoEmpresaValorAviso")
//	public ModelAndView seleccionoEmpresaAviso(HttpServletRequest request,
//			@ModelAttribute("valoresDto") ValoresDto boletaConValoresDto)
//			throws Exception {
//
//		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
//				.getAttribute(Constantes.LOGIN);
//		ModelAndView mv = new ModelAndView(ALTA_AVISO_VIEW);
//		mv = seleccionoEmpresaGlobal(boletaConValoresDto, userSesion, mv);
//		cargarCombosAvisos(mv, userSesion, boletaConValoresDto.getValorDto());
//		mv.setViewName(ALTA_AVISO_VIEW);
//		mv.addObject("imprimibleArchivo", false);
//		return mv;
//	}

	@RequestMapping("/seleccionoEmpresaModificacion")
	public ModelAndView seleccionoEmpresaModificacion(
			HttpServletRequest request,
			@ModelAttribute("valorDto") ValorDto boletaConValorDto)
			throws Exception {

		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
				.getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(MODIFICACION_VIEW);
		
		limpiarFechas(boletaConValorDto);

		if (boletaConValorDto.getIdNroCuenta() == "") {
			boletaConValorDto.setNumeroCuenta(null);
			boletaConValorDto.setIdNroCuenta(null);
		}

		enviarListasCombosAlCargar(mv, userSesion, boletaConValorDto);
		
//		cargaComboChequeAreemplazar(mv,boletaConValorDto);
		
		cargarCopropietario(mv, boletaConValorDto);
		cargarClienteSiebelConFormato(mv, userSesion);

		mv.addObject("valorDto", boletaConValorDto);		
		cargarCombosAvisos(mv, userSesion, boletaConValorDto);
		mv.addObject("imprimibleArchivo", false);
		return mv;
	}

	
	
	@RequestMapping("/seleccionoEmpresaEnAutorizacionValor")
	public ModelAndView seleccionoEmpresaEnAutorizacionValor(
			HttpServletRequest request,
			@ModelAttribute("boletaConValorFiltro") BoletaConValorFiltro boletaConValorFiltro)
			throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
				.getAttribute(Constantes.LOGIN);
		boletaConValorFiltro.setSegmento("");

		ModelAndView mv = new ModelAndView(AUTORIZACION_VIEW);
		cargarCombosParaBusquedaAutorizacionValores(mv, userSesion, boletaConValorFiltro);
		mv.addObject(OBJECT_COMMAND, boletaConValorFiltro);
		return mv;
	}

	@RequestMapping("/seleccionoEmpresaEnBusquedaValor")
	public ModelAndView seleccionoEmpresaEnBusquedaValor(
			HttpServletRequest request,
			@ModelAttribute("boletaConValorFiltro") BoletaConValorFiltro boletaConValorFiltro)
			throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
				.getAttribute(Constantes.LOGIN);
		boletaConValorFiltro.setSegmento("");

		ModelAndView mv = new ModelAndView(BUSQUEDA_VIEW);
		cargarCombosParaBusquedaValores(mv, userSesion, boletaConValorFiltro);
		mv.addObject(OBJECT_COMMAND, boletaConValorFiltro);
		return mv;
	}

	public ModelAndView seleccionoEmpresaGlobal(ValoresDto boletaConValoresDto,
			UsuarioSesion userSesion, ModelAndView mv) throws Exception {

		ValorDto boletaConValorDto = boletaConValoresDto.getValorDto();

		boletaConValorDto.setIdSegmento("");
		boletaConValorDto.setIdBancoDeposito("");
		boletaConValorDto.setIdOrigen("");

		if (boletaConValorDto.getIdNroCuenta() == "") {
			boletaConValorDto.setNumeroCuenta(null);
			boletaConValorDto.setIdNroCuenta(null);
		}

		enviarListasCombosAlCargar(mv, userSesion, boletaConValorDto);
//		cargaComboChequeAreemplazar(mv,boletaConValorDto);
		cargarCopropietario(mv, boletaConValorDto);
		cargarClienteSiebelConFormato(mv, userSesion);

		boletaConValoresDto.setValorDto(boletaConValorDto);

		mv.addObject("valoresDto", boletaConValoresDto);
		return mv;
	}

	/***
	 * SEGMENTO
	 */
	@RequestMapping("/seleccionoSegmentoValor")
	public ModelAndView seleccionoSegmento(HttpServletRequest request,
			@ModelAttribute("valoresDto") ValoresDto boletaConValoresDto)
			throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
				.getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(ALTA_BOLETA_VIEW);
		mv = seleccionoSegmentoGlobal(boletaConValoresDto, userSesion, mv);
		mv.setViewName(ALTA_BOLETA_VIEW);
		validarCheckGenerarConstancia(boletaConValoresDto, mv);
		return mv;

	}

//	@RequestMapping("/seleccionoSegmentoValorAviso")
//	public ModelAndView seleccionoSegmentoAviso(HttpServletRequest request,
//			@ModelAttribute("valoresDto") ValoresDto boletaConValoresDto)
//			throws Exception {
//
//		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
//				.getAttribute(Constantes.LOGIN);
//		ModelAndView mv = new ModelAndView(ALTA_AVISO_VIEW);
//		ValorDto valor = boletaConValoresDto.getValorDto();
//		valor.setIdNroCuenta("");
//		valor.setIdAcuerdo("");
//		valor.setIdBancoDeposito("");
//		boletaConValoresDto.setValorDto(valor);
//	
//		mv = seleccionoSegmentoGlobal(boletaConValoresDto, userSesion, mv);
//		cargarCombosAvisos(mv, userSesion, boletaConValoresDto.getValorDto());
//		mv.setViewName(ALTA_AVISO_VIEW);
//		mv.addObject("imprimibleArchivo", false);
//		return mv;
//	}

	@RequestMapping(value = "valor/buscarCopropietarios", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SelectOptionJsonResponse> buscarCopropietarios(HttpServletRequest request) throws Exception {
		String idEmpresa = request.getParameter("idEmpresa");
		String idSegmento = request.getParameter("idSegmento");
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		Collection<String> usuariosExcluidos = new ArrayList<String>();
		usuariosExcluidos.add(userSesion.getIdUsuario());
		
		return combosFacade.listarCopropietarioPorEmpresaYSegmento(idEmpresa, idSegmento, usuariosExcluidos);
	}
	
	@RequestMapping("/seleccionoSegmentoModificacion")
	public ModelAndView seleccionoSegmentoModificacion(
			HttpServletRequest request,
			@ModelAttribute("valorDto") ValorDto boletaConValorDto)
			throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
				.getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(MODIFICACION_VIEW);
		
		limpiarFechas(boletaConValorDto);

		boletaConValorDto.setIdOrigen("");
		if (boletaConValorDto.getIdNroCuenta() == "") {
			boletaConValorDto.setNumeroCuenta(null);
			boletaConValorDto.setIdNroCuenta(null);
		}
		
		enviarListasCombosAlCargar(mv, userSesion, boletaConValorDto);
		
//		cargaComboChequeAreemplazar(mv,boletaConValorDto);
		cargarCopropietario(mv, boletaConValorDto);
		cargarClienteSiebelConFormato(mv, userSesion);

		cargarCombosAvisos(mv, userSesion, boletaConValorDto);
		
		mv.addObject("valorDto", boletaConValorDto);
		mv.addObject("imprimibleArchivo", false);
		return mv;
	}

	/**
	 * 
	 * @param boletaConValoresDto
	 * @param userSesion
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView seleccionoSegmentoGlobal(
			ValoresDto boletaConValoresDto, UsuarioSesion userSesion,
			ModelAndView mv) throws Exception {

		ValorDto boletaConValorDto = boletaConValoresDto.getValorDto();

		boletaConValorDto.setIdOrigen("");
		if (boletaConValorDto.getIdNroCuenta() == "") {
			boletaConValorDto.setNumeroCuenta(null);
			boletaConValorDto.setIdNroCuenta(null);
		}

		enviarListasCombosAlCargar(mv, userSesion, boletaConValorDto);	
//		cargaComboChequeAreemplazar(mv,boletaConValorDto);
	
		cargarCopropietario(mv, boletaConValorDto);
		cargarClienteSiebelConFormato(mv, userSesion);
		
		if (mv.getModel().get(SELECT_TIPO_VALOR)!=null) {
			List<ShvParamTipoValor> listaTipoValor = (List<ShvParamTipoValor>) mv.getModel().get(SELECT_TIPO_VALOR);
			if (Validaciones.isCollectionNotEmpty(listaTipoValor)) { 
				boletaConValorDto.setIdTipoValor(listaTipoValor.get(0).getIdTipoValor().toString());
				cargaOrigenDinamica(userSesion, mv, boletaConValorDto);
			}
		}
		boletaConValoresDto.setValorDto(boletaConValorDto);
		
		mv.addObject("valoresDto", boletaConValoresDto);
		return mv;
	}
	
	/***
	 * ACUERDO
	 */
	@RequestMapping("/seleccionoAcuerdoValor")
	public ModelAndView seleccionoAcuerdo(HttpServletRequest request,
			@ModelAttribute("valoresDto") ValoresDto boletaConValoresDto)
			throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
				.getAttribute(Constantes.LOGIN);
		
		ModelAndView mv = new ModelAndView(ALTA_BOLETA_VIEW);
		mv = seleccionoAcuerdoGlobal(boletaConValoresDto, userSesion, mv);
		mv.setViewName(ALTA_BOLETA_VIEW);
		validarCheckGenerarConstancia(boletaConValoresDto, mv);
		return mv;

	}
	
	
	@RequestMapping("/seleccionoBancoValor")
	public ModelAndView seleccionoBanco(HttpServletRequest request,
			@ModelAttribute("valoresDto") ValoresDto boletaConValoresDto)
			throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
				.getAttribute(Constantes.LOGIN);
		
		ModelAndView mv = new ModelAndView(ALTA_BOLETA_VIEW);
		mv = seleccionoBancoGlobal(boletaConValoresDto, userSesion, mv, Constantes.ACUERDO_CONCILIABLE);
		mv.setViewName(ALTA_BOLETA_VIEW);
		return mv;

	}
	
	@RequestMapping("/seleccionoCuentaValor")
	public ModelAndView seleccionoCuenta(HttpServletRequest request,
			@ModelAttribute("valoresDto") ValoresDto boletaConValoresDto)
			throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
				.getAttribute(Constantes.LOGIN);
		
		ModelAndView mv = new ModelAndView(ALTA_BOLETA_VIEW);
		mv = seleccionoCuentaGlobal(boletaConValoresDto, userSesion, mv, Constantes.ACUERDO_CONCILIABLE);
		mv.setViewName(ALTA_BOLETA_VIEW);
		return mv;

	}
	
	
//	@RequestMapping("/seleccionoAcuerdoValorAviso")
//	public ModelAndView seleccionoAcuerdoAviso(HttpServletRequest request,
//			@ModelAttribute("valoresDto") ValoresDto boletaConValoresDto)
//			throws Exception {
//
//		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
//				.getAttribute(Constantes.LOGIN);
//		ModelAndView mv = new ModelAndView(ALTA_AVISO_VIEW);
//		mv = seleccionoAcuerdoGlobal(boletaConValoresDto, userSesion, mv);
//		cargarCombosAvisos(mv, userSesion, boletaConValoresDto.getValorDto());
//		mv.setViewName(ALTA_AVISO_VIEW);
//		mv.addObject("imprimibleArchivo", false);
//		return mv;
//	}
	
	
//	@RequestMapping("/seleccionoCuentaValorAviso") 
//	public ModelAndView seleccionoCuentaAviso(HttpServletRequest request,
//			@ModelAttribute("valoresDto") ValoresDto boletaConValoresDto)
//			throws Exception {
//
//		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
//				.getAttribute(Constantes.LOGIN);
//		ModelAndView mv = new ModelAndView(ALTA_AVISO_VIEW);
//		mv = seleccionoCuentaGlobal(boletaConValoresDto, userSesion, mv, Constantes.ACUERDO_NO_CONCILIABLE);
//		cargarCombosAvisos(mv, userSesion, boletaConValoresDto.getValorDto());
//		mv.setViewName(ALTA_AVISO_VIEW);
//		mv.addObject("imprimibleArchivo", false);
//		return mv;
//	}
	
//	@RequestMapping("/seleccionoBancoValorAviso") 
//	public ModelAndView seleccionoBancoAviso(HttpServletRequest request,
//			@ModelAttribute("valoresDto") ValoresDto boletaConValoresDto)
//			throws Exception {
//
//		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
//				.getAttribute(Constantes.LOGIN);
//		ModelAndView mv = new ModelAndView(ALTA_AVISO_VIEW);
//		mv = seleccionoBancoGlobal(boletaConValoresDto, userSesion, mv, Constantes.ACUERDO_NO_CONCILIABLE);
//		cargarCombosAvisos(mv, userSesion, boletaConValoresDto.getValorDto());
//		mv.setViewName(ALTA_AVISO_VIEW);
//		mv.addObject("imprimibleArchivo", false);
//		return mv;
//	}
	
//	@RequestMapping("/seleccionoTipoValorAviso") 
//	public ModelAndView seleccionoTipoValorAviso(HttpServletRequest request,
//			@ModelAttribute("valoresDto") ValoresDto boletaConValoresDto)
//			throws Exception {
//
//		ValorDto valor = boletaConValoresDto.getValorDto();
//		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
//		ModelAndView mv = new ModelAndView(ALTA_AVISO_VIEW);
//	
//		
//		valor.setIdNroCuenta("");
//		valor.setIdAcuerdo("");
//		valor.setIdBancoDeposito("");
//		
//		enviarListasCombosAlCargar(mv, userSesion, valor);
////		cargaComboChequeAreemplazar(mv,valor);
//		cargarCopropietario(mv, valor);
//		cargarClienteSiebelConFormato(mv, userSesion);
//		boletaConValoresDto.setValorDto(valor);
//		cargarCombosAvisos(mv, userSesion, boletaConValoresDto.getValorDto());
//		
//		mv.addObject("valoresDto", boletaConValoresDto);
//		mv.setViewName(ALTA_AVISO_VIEW);
//		mv.addObject("imprimibleArchivo", false);
//		return mv;
//		
//	}
//		
	/********************************************************************************************
	 * Globales - Altas (boletas con valor o avisos) -- Combos 
	 ********************************************************************************************/
	public ModelAndView seleccionoBancoGlobal(ValoresDto boletaConValoresDto, UsuarioSesion userSesion, ModelAndView mv, String esConciliable) throws Exception {

		ValorDto boletaConValorDto = boletaConValoresDto.getValorDto();
		String idNroCuentaAux = boletaConValorDto.getIdNroCuenta();
		String idAcuerdoAux = boletaConValorDto.getIdAcuerdo();
		boletaConValorDto.setIdNroCuenta("");
		boletaConValorDto.setIdAcuerdo("");
		enviarListasCombosAlCargar(mv, userSesion, boletaConValorDto);
		
		if(!Validaciones.isNullOrEmpty(boletaConValorDto.getIdBancoDeposito())){
			
			List<ShvParamBancoCuenta> listaCuentas = combosServicio.listarCuentaPorIdBanco(boletaConValorDto.getIdBancoDeposito(), esConciliable);			
			List<ShvParamAcuerdo> listaAcuerdo = combosServicio.listarAcuerdoNoConciliablePorIdBanco(boletaConValorDto.getIdBancoDeposito(), esConciliable);		

			mv.addObject(SELECT_ACUERDOS, listaAcuerdo);
			mv.addObject(SELECT_CUENTAS, listaCuentas);
			
			if(Validaciones.isCollectionNotEmpty(listaAcuerdo)){
				for (ShvParamAcuerdo shvParamAcuerdo : listaAcuerdo) {
					if(idAcuerdoAux.equals(String.valueOf(shvParamAcuerdo.getIdAcuerdo()))){
						boletaConValorDto.setIdAcuerdo(idAcuerdoAux);
					}
				}
				if(listaAcuerdo.size() == 1){				
					boletaConValorDto.setComboAcuerdo(false);
				} else {
					boletaConValorDto.setComboAcuerdo(true);
				}
			} else {
				boletaConValorDto.setComboAcuerdo(false);
			}
			
			if(Validaciones.isCollectionNotEmpty(listaCuentas)){
				for (ShvParamBancoCuenta shvParamBancoCuenta : listaCuentas) {
					if(idNroCuentaAux.equals(String.valueOf(shvParamBancoCuenta.getIdBancoCuenta()))){
						boletaConValorDto.setIdNroCuenta(idNroCuentaAux);
					}
				}
				if(listaCuentas.size() == 1){				
					boletaConValorDto.setComboCuenta(false);
				} else {
					boletaConValorDto.setComboCuenta(true);
				}
			} else {
				boletaConValorDto.setComboCuenta(false);
			}
			
		}
//		cargaComboChequeAreemplazar(mv,boletaConValorDto);
		cargarCopropietario(mv, boletaConValorDto);
		cargarClienteSiebelConFormato(mv, userSesion);
		boletaConValoresDto.setValorDto(boletaConValorDto);

		mv.addObject("valoresDto", boletaConValoresDto);
		return mv;
	}
	
	public ModelAndView seleccionoCuentaGlobal(ValoresDto boletaConValoresDto, UsuarioSesion userSesion, ModelAndView mv, String esConciliable) throws Exception {

		ValorDto boletaConValorDto = boletaConValoresDto.getValorDto();
		boletaConValorDto.setIdBancoDeposito("");
		boletaConValorDto.setIdAcuerdo("");		
		enviarListasCombosAlCargar(mv, userSesion, boletaConValorDto);
		
		if(!Validaciones.isNullOrEmpty(boletaConValorDto.getIdNroCuenta())){

			ShvParamAcuerdo listaAcuerdo = combosServicio.buscarAcuerdoNoConciliablesYconciliablesPorIdCuenta(boletaConValorDto.getIdNroCuenta(), esConciliable);
			ShvParamBanco listaBanco = combosServicio.buscarBancoPorIdCuenta(boletaConValorDto.getIdNroCuenta());	
			
			if(!Validaciones.isObjectNull(listaAcuerdo)){
				boletaConValorDto.setIdAcuerdo(String.valueOf(listaAcuerdo.getIdAcuerdo()));		
			}
			if(!Validaciones.isObjectNull(listaBanco)){
				boletaConValorDto.setIdBancoDeposito(String.valueOf(listaBanco.getIdBanco()));
			}
			
		}
//		cargaComboChequeAreemplazar(mv,boletaConValorDto);
		cargarCopropietario(mv, boletaConValorDto);
		cargarClienteSiebelConFormato(mv, userSesion);

		boletaConValoresDto.setValorDto(boletaConValorDto);
		mv.addObject("valoresDto", boletaConValoresDto);
		return mv;
	}
	
	
	
	public ModelAndView seleccionoAcuerdoGlobal(ValoresDto valoresDto, UsuarioSesion userSesion, ModelAndView mv) throws Exception {

		ValorDto valorDto = valoresDto.getValorDto();
		String idNroCuentaAux = valorDto.getIdNroCuenta();
		String idBancoDepositoAux = valorDto.getIdBancoDeposito();
		valorDto.setIdNroCuenta("");
		valorDto.setIdBancoDeposito("");
		
		enviarListasCombosAlCargar(mv, userSesion, valorDto);
		
		if(!Validaciones.isNullOrEmpty(valorDto.getIdAcuerdo())){
			List<ShvParamBanco> listaBanco = combosServicio.actualizarBancoPorIdAcuerdo(valorDto.getIdAcuerdo());							
			List<ShvParamBancoCuenta> listaCuentas = combosServicio.actualizarCuentaPorAcuerdo(valorDto.getIdAcuerdo());
			
			mv.addObject(SELECT_BANCOS, listaBanco);
			mv.addObject(SELECT_CUENTAS, listaCuentas);
		
			if(Validaciones.isCollectionNotEmpty(listaBanco)){
				for (ShvParamBanco shvParamBanco : listaBanco) {
					if(idBancoDepositoAux.equals(String.valueOf(shvParamBanco.getIdBanco()))){
						valorDto.setIdBancoDeposito(idBancoDepositoAux);
					}
				}
				if(listaBanco.size() == 1){				
					valorDto.setComboBanco(false);
				} else {
					valorDto.setComboBanco(true);
				}
			} else {
				valorDto.setComboBanco(false);
			}
			
			if(Validaciones.isCollectionNotEmpty(listaCuentas)){
				for (ShvParamBancoCuenta shvParamBancoCuenta : listaCuentas) {
					if(idNroCuentaAux.equals(String.valueOf(shvParamBancoCuenta.getIdBancoCuenta()))){
						valorDto.setIdNroCuenta(idNroCuentaAux);
					}
				}
				if(listaCuentas.size() == 1){				
					valorDto.setComboCuenta(false);
				} else {
					valorDto.setComboCuenta(true);
				}
			} else {
				valorDto.setComboCuenta(false);
			}
			
		}
//		cargaComboChequeAreemplazar(mv,valorDto);
		cargarCopropietario(mv, valorDto);
		cargarClienteSiebelConFormato(mv, userSesion);

		valoresDto.setValorDto(valorDto);

		mv.addObject("valoresDto", valoresDto);
		return mv;
	}
	/*************************************************************************************************/
	
	/**************************************************************************************************
	 * Valores - Modificacion - Combos
	 **************************************************************************************************/
	@RequestMapping("/seleccionoAcuerdoValorModificacion")
	public ModelAndView seleccionoAcuerdoModificacion(HttpServletRequest request, @ModelAttribute("valorDto") ValorDto valorDto)
			throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(MODIFICACION_VIEW);
		
		limpiarFechas(valorDto);

		String idNroCuentaAux = valorDto.getIdNroCuenta();
		String idBancoDepositoAux = valorDto.getIdBancoDeposito();
		valorDto.setIdNroCuenta("");
		valorDto.setIdBancoDeposito("");
		
		enviarListasCombosAlCargar(mv, userSesion, valorDto);
		
		if(!Validaciones.isNullOrEmpty(valorDto.getIdAcuerdo())){
			List<ShvParamBanco> listaBanco = combosServicio.actualizarBancoPorIdAcuerdo(valorDto.getIdAcuerdo());							
			List<ShvParamBancoCuenta> listaCuentas = combosServicio.actualizarCuentaPorAcuerdo(valorDto.getIdAcuerdo());
			
			mv.addObject(SELECT_BANCOS, listaBanco);
			mv.addObject(SELECT_CUENTAS, listaCuentas);
		
			if(Validaciones.isCollectionNotEmpty(listaBanco)){
				for (ShvParamBanco shvParamBanco : listaBanco) {
					if(idBancoDepositoAux.equals(String.valueOf(shvParamBanco.getIdBanco()))){
						valorDto.setIdBancoDeposito(idBancoDepositoAux);
					}
				}
				if(listaBanco.size() == 1){				
					valorDto.setComboBanco(false);
				} else {
					valorDto.setComboBanco(true);
				}
			} else {
				valorDto.setComboBanco(false);
			}
			
			if(Validaciones.isCollectionNotEmpty(listaCuentas)){
				for (ShvParamBancoCuenta shvParamBancoCuenta : listaCuentas) {
					if(idNroCuentaAux.equals(String.valueOf(shvParamBancoCuenta.getIdBancoCuenta()))){
						valorDto.setIdNroCuenta(idNroCuentaAux);
					}
				}
				if(listaCuentas.size() == 1){				
					valorDto.setComboCuenta(false);
				} else {
					valorDto.setComboCuenta(true);
				}
			} else {
				valorDto.setComboCuenta(false);
			}
			
		}
//		cargaComboChequeAreemplazar(mv,valorDto);
		cargarCopropietario(mv, valorDto);
		cargarClienteSiebelConFormato(mv, userSesion);

		mv.addObject("valorDto", valorDto);
		mv.addObject("imprimibleArchivo", false);
		return mv;
	}
	
	@RequestMapping("/seleccionoBancoValorModificacion")
	public ModelAndView seleccionoBancoModificacion(HttpServletRequest request, @ModelAttribute("valorDto") ValorDto valorDto)
			throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(MODIFICACION_VIEW);
		
		limpiarFechas(valorDto);
		
		String esConciliable;
		String tipoValor = valorDto.getIdTipoValor();
		if(VALOR_PARA_BOLETA_CHEQUE.equals(tipoValor) || VALOR_PARA_BOLETA_CHEQUE_PD.equals(tipoValor) || VALOR_PARA_BOLETA_EFECTIVO.equals(tipoValor)){
			esConciliable = Constantes.ACUERDO_CONCILIABLE;
		} else {
			esConciliable = Constantes.ACUERDO_NO_CONCILIABLE;
		}
		
		String idNroCuentaAux = valorDto.getIdNroCuenta();
		String idAcuerdoAux = valorDto.getIdAcuerdo();
		valorDto.setIdNroCuenta("");
		valorDto.setIdAcuerdo("");
		enviarListasCombosAlCargar(mv, userSesion, valorDto);
		
		if(!Validaciones.isNullOrEmpty(valorDto.getIdBancoDeposito())){
			
			List<ShvParamBancoCuenta> listaCuentas = combosServicio.listarCuentaPorIdBanco(valorDto.getIdBancoDeposito(), esConciliable);			
			List<ShvParamAcuerdo> listaAcuerdo = combosServicio.listarAcuerdoNoConciliablePorIdBanco(valorDto.getIdBancoDeposito(), esConciliable);		

			mv.addObject(SELECT_ACUERDOS, listaAcuerdo);
			mv.addObject(SELECT_CUENTAS, listaCuentas);
			
			if(Validaciones.isCollectionNotEmpty(listaAcuerdo)){
				for (ShvParamAcuerdo shvParamAcuerdo : listaAcuerdo) {
					if(idAcuerdoAux.equals(String.valueOf(shvParamAcuerdo.getIdAcuerdo()))){
						valorDto.setIdAcuerdo(idAcuerdoAux);
					}
				}
				if(listaAcuerdo.size() == 1){				
					valorDto.setComboAcuerdo(false);
				} else {
					valorDto.setComboAcuerdo(true);
				}
			} else {
				valorDto.setComboAcuerdo(false);
			}
			
			if(Validaciones.isCollectionNotEmpty(listaCuentas)){
				for (ShvParamBancoCuenta shvParamBancoCuenta : listaCuentas) {
					if(idNroCuentaAux.equals(String.valueOf(shvParamBancoCuenta.getIdBancoCuenta()))){
						valorDto.setIdNroCuenta(idNroCuentaAux);
					}
				}
				if(listaCuentas.size() == 1){				
					valorDto.setComboCuenta(false);
				} else {
					valorDto.setComboCuenta(true);
				}
			} else {
				valorDto.setComboCuenta(false);
			}
			
		}
//		cargaComboChequeAreemplazar(mv,valorDto);
		cargarCopropietario(mv, valorDto);
		cargarClienteSiebelConFormato(mv, userSesion);

		mv.addObject("valorDto", valorDto);
		mv.addObject("imprimibleArchivo", false);
		return mv;
	}
	
	@RequestMapping("/seleccionoCuentaValorModificacion")
	public ModelAndView seleccionoCuentaModificacion(HttpServletRequest request, @ModelAttribute("valorDto") ValorDto valorDto)
			throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(MODIFICACION_VIEW);
		
		limpiarFechas(valorDto);
		
		String esConciliable;
		String tipoValor = valorDto.getIdTipoValor();
		if(VALOR_PARA_BOLETA_CHEQUE.equals(tipoValor) || VALOR_PARA_BOLETA_CHEQUE_PD.equals(tipoValor) || VALOR_PARA_BOLETA_EFECTIVO.equals(tipoValor)){
			esConciliable = Constantes.ACUERDO_CONCILIABLE;
		} else {
			esConciliable = Constantes.ACUERDO_NO_CONCILIABLE;
		}

		valorDto.setIdBancoDeposito("");
		valorDto.setIdAcuerdo("");		
		enviarListasCombosAlCargar(mv, userSesion, valorDto);
		
		if(!Validaciones.isNullOrEmpty(valorDto.getIdNroCuenta())){

			ShvParamAcuerdo listaAcuerdo = combosServicio.buscarAcuerdoNoConciliablesYconciliablesPorIdCuenta(valorDto.getIdNroCuenta(), esConciliable);
			ShvParamBanco listaBanco = combosServicio.buscarBancoPorIdCuenta(valorDto.getIdNroCuenta());	
			
			if(!Validaciones.isObjectNull(listaAcuerdo)){
				valorDto.setIdAcuerdo(String.valueOf(listaAcuerdo.getIdAcuerdo()));		
			}
			if(!Validaciones.isObjectNull(listaBanco)){
				valorDto.setIdBancoDeposito(String.valueOf(listaBanco.getIdBanco()));
			}
			
		}
//		cargaComboChequeAreemplazar(mv,valorDto);
		cargarCopropietario(mv, valorDto);
		cargarClienteSiebelConFormato(mv, userSesion);

		mv.addObject("valorDto", valorDto);
		mv.addObject("imprimibleArchivo", false);
		return mv;
	}
	/*************************************************************************************************/
	
	/***
	 * TIPO VALOR
	 */
	@RequestMapping("/seleccionoTipoValor")
	public ModelAndView seleccionoTipoValor(HttpServletRequest request,
			@ModelAttribute("ValoresDto") ValoresDto boletaConValoresDto)
			throws Exception {
		ValorDto boletaConValorDto = boletaConValoresDto.getValorDto();

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
				.getAttribute(Constantes.LOGIN);
		boletaConValorDto.setIdAcuerdo("");

		ModelAndView mv = new ModelAndView(ALTA_BOLETA_VIEW);
		if (!Validaciones.isNullOrEmpty(boletaConValorDto.getIdEmpresa())&& !Validaciones.isNullOrEmpty(boletaConValorDto.getIdSegmento())) {

			cargarCopropietario(mv, boletaConValorDto);
			boletaConValorDto.setIdOrigen("");
			enviarListasCombosAlCargar(mv, userSesion, boletaConValorDto);
			cargarClienteSiebelConFormato(mv, userSesion);
			mv.addObject(OBJECT_COMMAND, boletaConValorDto);
						
			if(Validaciones.isNullOrEmpty(boletaConValorDto.getIdOrigen())){
				List<ShvParamAcuerdo> listaAcuerdo= null;
				List<ShvParamBanco> listaBanco = null;
				List<ShvParamBancoCuenta> listaCuentas = null;
				mv.addObject("acuerdos", listaAcuerdo);
				mv.addObject("bancos", listaBanco);
				mv.addObject("cuentas", listaCuentas);
				boletaConValorDto.setIdOrigen("");
				boletaConValorDto.setComboOrigen(true);	
				boletaConValorDto.setComboAcuerdo(false);
			}
			
		} else {

			List<ShvParamEmpresa> listaEmpresas = (List<ShvParamEmpresa>) combosServicio.listarEmpresasPorUsuario(userSesion);
			mv.addObject(SELECT_EMPRESAS, listaEmpresas);
			
			List<ShvParamSegmento> listaSegmentos=null;
			
			if (listaEmpresas.size() == 1) {
				listaSegmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(listaEmpresas.get(0).getIdEmpresa(), userSesion);		
				boletaConValorDto.setComboEmpresa(false);
			} else {
				if(!Validaciones.isNullOrEmpty((boletaConValorDto.getIdEmpresa()))){
					 listaSegmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(boletaConValorDto.getIdEmpresa(), userSesion);		
				}
				boletaConValorDto.setComboEmpresa(true);
			}
			boletaConValorDto.setComboSegmento(true);
			mv.addObject(SELECT_SEGMENTOS, listaSegmentos);
			
			
			
			cargarMotivos(mv, boletaConValorDto);
			cargarBancoOrigen(mv, boletaConValorDto);
			cargarTipoValor(mv, boletaConValorDto);
		}
//		cargaComboChequeAreemplazar(mv,boletaConValorDto);

		cargarClienteSiebelConFormato(mv, userSesion);			
		boletaConValoresDto.setValorDto(boletaConValorDto);
		

		mv.addObject("valoresDto", boletaConValoresDto);
		validarCheckGenerarConstancia(boletaConValoresDto, mv);
		return mv;
	}

	/***
	 * ORIGEN
	 */
	@RequestMapping("/seleccionoOrigenValor")
	public ModelAndView seleccionoOrigen(HttpServletRequest request,
			@ModelAttribute("ValoresDto") ValoresDto boletaConValoresDto)
			throws Exception {

		ValorDto boletaConValorDto = boletaConValoresDto.getValorDto();

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()
				.getAttribute(Constantes.LOGIN);
		boletaConValorDto.setIdAcuerdo("");

		ModelAndView mv = new ModelAndView(ALTA_BOLETA_VIEW);
		if (!Validaciones.isNullOrEmpty(boletaConValorDto.getIdOrigen())) {

			enviarListasCombosAlCargar(mv, userSesion, boletaConValorDto);

		} else {
			enviarListasCombosAlCargar(mv, userSesion, boletaConValorDto);
		}
//		cargaComboChequeAreemplazar(mv,boletaConValorDto);

		cargarCopropietario(mv, boletaConValorDto);
		cargarClienteSiebelConFormato(mv, userSesion);

		boletaConValoresDto.setValorDto(boletaConValorDto);
		mv.addObject("valoresDto", boletaConValoresDto);
		validarCheckGenerarConstancia(boletaConValoresDto, mv);
		return mv;
	}
	
	@RequestMapping("/seleccionoOrigenModificacion")
	public ModelAndView seleccionoOrigenModificacion(HttpServletRequest request,
			@ModelAttribute("ValoresDto") ValorDto boletaConValorDto)
			throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		boletaConValorDto.setIdAcuerdo("");
		
		limpiarFechas(boletaConValorDto);

		ModelAndView mv = new ModelAndView(MODIFICACION_VIEW);
		if (!Validaciones.isNullOrEmpty(boletaConValorDto.getIdOrigen())) {

			enviarListasCombosAlCargar(mv, userSesion, boletaConValorDto);

		} else {
			List<ShvParamEmpresa> listaEmpresas = (List<ShvParamEmpresa>) combosServicio.listarEmpresasPorUsuario(userSesion);
			mv.addObject(SELECT_EMPRESAS, listaEmpresas);
			cargarMotivos(mv, boletaConValorDto);
			cargarBancoOrigen(mv, boletaConValorDto);
			cargarTipoValor(mv, boletaConValorDto);
		}
//		cargaComboChequeAreemplazar(mv,boletaConValorDto);

		cargarCopropietario(mv, boletaConValorDto);
		cargarClienteSiebelConFormato(mv, userSesion);

		mv.addObject("valorDto", boletaConValorDto);
		mv.addObject("imprimibleArchivo", false);
		return mv;
	}

	/***
	 * UTILIDAD
	 */
	
	/**
	 * Permite limpiar los combos
	 * @param valorDto
	 * @return
	 */
	public ValorDto limpiarCombos(ValorDto valorDto) {

		if (Validaciones.isNullOrEmpty(valorDto.getIdMotivo())) {
			valorDto.setMotivo(null);
		}
		if (Validaciones.isNullOrEmpty(valorDto.getIdCopropietario())) {
			valorDto.setCopropietario(null);
			
		}
		if (Validaciones.isNullOrEmpty(valorDto.getIdOrigen())) {
			valorDto.setOrigen(null);
			
		}
		if (valorDto.isCheckImprimirBoleta()) {
			valorDto.setBoletaImpresaEstado(ImprimirBoletaEstadoEnum.SI);
		} else {
			valorDto.setBoletaImpresaEstado(ImprimirBoletaEstadoEnum.NO);
		}
		if (valorDto.isCheckEnviarMailBoleta()) {
			valorDto.setBoletaEnviadaMailEstado(EnviarMailBoletaEstadoEnum.SI);
		} else {
			valorDto.setBoletaEnviadaMailEstado(EnviarMailBoletaEstadoEnum.NO);
		}
		if (Validaciones.isNullOrEmpty(valorDto.getIdAcuerdo())) {
			valorDto.setAcuerdo(null);
		}
		if (Validaciones.isNullOrEmpty(valorDto.getIdBancoDeposito())) {
			valorDto.setBancoDeposito(null);
		}
		if (Validaciones.isNullOrEmpty(valorDto.getIdNroCuenta())) {
			valorDto.setNumeroCuenta(null);
		}
		if (Validaciones.isNullOrEmpty(valorDto.getIdProvincia())) {
			valorDto.setProvincia(null);
		}
		if (Validaciones.isNullOrEmpty(valorDto.getIdLetraComprobante())) {
			valorDto.setLetraComprobante(null);
		}
		if (Validaciones.isNullOrEmpty(valorDto.getIdBancoOrigen())) {
			valorDto.setBancoOrigen(null);
		}
		if (Validaciones.isNullOrEmpty(valorDto.getIdTipoComprobante())) {
			valorDto.setTipoComprobante(null);
		}
		if (Validaciones.isNullOrEmpty(valorDto.getIdTipoImpuesto())) {
			valorDto.setTipoImpuesto(null);
		}
		return valorDto;
	}

	/**
	 * 
	 * @param mv
	 * @param userSesion
	 * @throws NegocioExcepcion
	 */
	public void cargarClienteSiebelConFormato(ModelAndView mv, UsuarioSesion userSesion) throws NegocioExcepcion {
		if(mv.getViewName().equals(ALTA_BOLETA_VIEW)){
			cargarCodigoLegadoSiebel(mv, boletaConValorServicio.listar10CodigosClienteLegadoBoleta(userSesion.getIdUsuario()));
		} else if (mv.getViewName().equals(ALTA_AVISO_VIEW)) {
			cargarCodigoLegadoSiebel(mv, boletaConValorServicio.listar10CodigosClienteLegadoAviso(userSesion.getIdUsuario()));
		} else {
			cargarCodigoLegadoSiebel(mv, boletaConValorServicio.listar10CodigosClienteLegadoUsuario(userSesion.getIdUsuario()));
		}
	}

	/**
	 * Permite verificar tipo de modificaciones pudiendo habilitar campos/botones de los valores
	 * @param valorDto
	 * @param userSesion
	 * @return
	 */
	public String validarTipoModificacion(ValorDto valorDto,
			UsuarioSesion userSesion) {

		String respuesta = MODIFICACION_VACIA;
		String estado = valorDto.getIdEstadoValor();
		
		boolean perfilAnalista = userSesion.esAnalista();
		boolean perfilSupervisor = userSesion.esSupervisor();
		boolean perfilAdministrador = userSesion.esAdminValor();
		boolean perfilCajeroPagador = userSesion.esCajeroPagador();
		
		if ((estado.equals(Estado.VAL_BOLETA_RECHAZADA.name()) && (perfilAnalista || perfilCajeroPagador))
				|| (estado.equals(Estado.VAL_AVISO_PAGO_RECHAZADO.name()) && (perfilSupervisor || perfilAnalista || perfilCajeroPagador))) {
			respuesta = MODIFICACION_RECHAZADA;
		} else {
			if ((estado.equals(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION.name()) && (perfilSupervisor || perfilAnalista || perfilCajeroPagador))
					|| (estado.equals(Estado.VAL_DISPONIBLE.name()) && (perfilSupervisor || perfilAnalista || perfilCajeroPagador))
					|| (estado.equals(Estado.VAL_USADO.name()) && (perfilSupervisor || perfilAnalista || perfilCajeroPagador))) {
				respuesta = MODIFICACION;
			} else {
				if (estado.equals(Estado.VAL_SUSPENDIDO.name()) && perfilAdministrador) {
					respuesta = MODIFICACION_SUSPENDIDO_ADMINISTRADOR;
				} else {
					if ((estado.equals(Estado.VAL_DISPONIBLE.name()) && perfilAdministrador)
							|| (estado.equals(Estado.VAL_USADO.name()) && perfilAdministrador)) {
						respuesta = MODIFICACION_ADMINISTRADOR;
					} else {
						if ((estado.equals(Estado.VAL_BOLETA_RECHAZADA.name()) && (perfilSupervisor))) {
							respuesta = MODIFICACION_RECHAZADA_SUPERVISOR;
						}
					}
				}
			}
		}
		return respuesta;
	}
	
	/**
	 * Permite validar com ultimo mes
	 * @param valorDto
	 * @return
	 */
	public boolean validarUltimoMes(ValorDto valorDto) {
		
		if(TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValorString().equalsIgnoreCase(valorDto.getIdTipoValor())){
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			try {
				//Formateo la fecha a yyyyMMdd
				Date dateAlta = df.parse(valorDto.getFechaAltaValor());
				Date dateActual = new Date();
				
				Double diferencia = 0.0;
				//Tomo la Diferencia entre las Fechas, siempre que el TO sea mayor al FROM
				if(dateAlta.getTime() < dateActual.getTime()) {
					diferencia = Double.valueOf(dateActual.getTime() - dateAlta.getTime());
				} else {
					return false;
				}
				
				//Calculo Diferencia en meses y dias de las fechas
				Double dias =  diferencia / (1000.0 * 60.0 * 60.0 * 24.0);
				
				//Valido que se cumpla la diferencia de 12 Meses.
				if(dias < 30){
					return true;
				}
			} catch (ParseException e) {
				Traza.error(getClass(), "Se ha producido el error de parseo", e);
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param valoresDto
	 * @param mv
	 */
	public void validarCheckGenerarConstancia(ValoresDto valoresDto, ModelAndView mv){
		int contadorConstanciaAutomatica = 0;
		for (ValorDto valorDto : valoresDto.getListaValoresDto()) {
			if(valorDto.getIdOrigen().equals("4")){
				contadorConstanciaAutomatica++;
			}
		}
		if(contadorConstanciaAutomatica > 1){
			mv.addObject("checkConstanciaDesabilitado", false);
		} else {
			mv.addObject("checkConstanciaDesabilitado", true);
		}
	}
	
	/**
	 * Permite Generar Zip
	 * @param actualizacionDto
	 * @return
	 * @throws IOException
	 */
	private byte[] generarZip(ActualizacionExitosaDto actualizacionDto)
			throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ZipOutputStream zipfile = new ZipOutputStream(bos);
		ZipEntry zipentry = null;
		byte[] archivo = null;
		if(actualizacionDto.getArchivosImprimirConstancia().size() > 0){
			List<byte[]> files = actualizacionDto.getArchivosImprimirConstancia();
			Iterator<byte[]> it = files.iterator();
			int num = 0;
			Set<String> constancias = new TreeSet<String>();
			while (it.hasNext()) {
				archivo = it.next();
				if(!constancias.contains(actualizacionDto.getNumeroConstanciaArchivo().get(num))){
				    zipentry = new ZipEntry("Constancia Nro. " + actualizacionDto.getNumeroConstanciaArchivo().get(num) + ".pdf");
				    zipfile.putNextEntry(zipentry);
				    zipfile.write((byte[]) archivo);
				    constancias.add(actualizacionDto.getNumeroConstanciaArchivo().get(num));
				}
			    num++;
			}
		}
		if(actualizacionDto.getArchivosImprimirBoleta().size() > 0){
			List<byte[]> files = actualizacionDto.getArchivosImprimirBoleta();
			Iterator<byte[]> it = files.iterator();
			while (it.hasNext()) {
				archivo = it.next();
				String boletasReemplazo = actualizacionDto.getNumeroBoletaArchivo().toString().replace("[", "");
				String boletasFinales = boletasReemplazo.replace("]", "");
			    zipentry = new ZipEntry("Boleta Nro. " + boletasFinales + ".pdf");
			    zipfile.putNextEntry(zipentry);
			    zipfile.write((byte[]) archivo);
			}
		}
		zipfile.close();
		byte[] zipArmado = bos.toByteArray();
		return zipArmado;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/abrir-pdf")
	public void abrirPdf(HttpServletResponse res,
			HttpServletRequest request) throws Exception {
		
		byte[] archivo = (byte[]) request.getSession().getAttribute("archivoImpresionFinal");
		String tipo =  (String) request.getSession().getAttribute("tipoArchivo");
		
		ServletOutputStream sos = null;
		sos = res.getOutputStream();
		res.setContentType("application/pdf");

		// Para solventar bug IE8 sobre puerto seguro no abre PDF
		res.setHeader("Cache-Control", "private");
		res.setHeader("Pragma", "private");
		
		if(tipo.equals("pdfConstancia")){
			List<String> numerosConstancia =  (ArrayList<String>) request.getSession().getAttribute("numeroConstanciaPdf");
			String nombre = "Constancia Nro. " + numerosConstancia.get(0) + ".pdf";
			res.addHeader("Content-Disposition", "attachment; filename=" + nombre);
		} else {
			if(tipo.equals("pdfBoleta")){
				List<String> numerosBoleta =  (ArrayList<String>) request.getSession().getAttribute("numeroBoletaPdf");
				String boletasReemplazo = numerosBoleta.toString().replace("[", "");
				String boletasFinales = boletasReemplazo.replace("]", "");
				String nombre = "Boleta Nro. " + boletasFinales + ".pdf";
				// Reemplazo en el nombre las comas (",") por que Google Chorme. toma la coma como separaro de valores.
				// O sea un  Content-Disposition = attachment; filename=Boleta_Nro._886,_887.pdf
				// Lo interpreta como Content-Disposition = attachment; filename=Boleta_Nro._886 u Content-Disposition = _887.pdf
				res.addHeader("Content-Disposition", "attachment; filename=" + nombre.replace(", ", "_"));
			} else {
				if(tipo.equals("zipContancia")){
					res.addHeader("Content-Disposition", "attachment; filename=Constancias.zip");
				} else {
					res.addHeader("Content-Disposition", "attachment; filename=Constancias y Boleta.zip");
				}
			}
		}
		
		int length = (int) archivo.length;
		res.setContentLength(length);
		
		res.setStatus(HttpServletResponse.SC_OK);
		sos.write(archivo);
		sos.flush();
		sos.close();
		
	}
	
	@RequestMapping("/abrir-documento")
	public void abrirDocumento(HttpServletResponse res,
			HttpServletRequest request) throws Exception {
		
		byte[] archivo = (byte[]) request.getSession().getAttribute("documentoImprimible");
		String nombreDocumento = (String) request.getSession().getAttribute("nombreDocumento");
		
		ControlArchivo.descargarComoPDF(archivo, nombreDocumento, res);
	}
	
	/**
	 * Permite preparar archivos a imprimir
	 * @param request
	 * @param actualizacionDto
	 * @param mv
	 * @throws Exception
	 */
	public boolean logicaArchivosImprimibles(HttpServletRequest request, ActualizacionExitosaDto actualizacionDto, ModelAndView mv) throws Exception {
		boolean imprimible = false;
		if (actualizacionDto.getArchivosImprimirConstancia().size() > 0) {
			if (actualizacionDto.getArchivosImprimirBoleta().size() > 0) {
				/*Una o + Boleta, Una o + Constancia*/
				byte[] zipArmado = generarZip(actualizacionDto);
				request.getSession().setAttribute( "archivoImpresionFinal" , zipArmado);
				request.getSession().setAttribute( "tipoArchivo", "zipConjunto");
				if (!Validaciones.isObjectNull(mv)) {
					mv.addObject("imprimible", true);
				} else {
					imprimible = true;
				}
			} else {
				if (actualizacionDto.getArchivosImprimirConstancia().size() == 1) {
					/*Solo Una Constancia*/
					request.getSession().setAttribute( "archivoImpresionFinal" , actualizacionDto.getArchivosImprimirConstancia().get(0));
					request.getSession().setAttribute( "tipoArchivo", "pdfConstancia");
					request.getSession().setAttribute( "numeroConstanciaPdf", actualizacionDto.getNumeroConstanciaArchivo());
					if (!Validaciones.isObjectNull(mv)) {
						mv.addObject("imprimible", true);
					} else {
						imprimible = true;
					}
				} else {
					/*Solo Una o + Constancias*/
					byte[] zipArmado = generarZip(actualizacionDto);
					request.getSession().setAttribute( "archivoImpresionFinal" , zipArmado);
					request.getSession().setAttribute( "tipoArchivo", "zipContancia");
					if (!Validaciones.isObjectNull(mv)) {
						mv.addObject("imprimible", true);
					} else {
						imprimible = true;
					}
				}
			}
		} else {
			if (actualizacionDto.getArchivosImprimirBoleta().size() > 0) {
				/*Solo Boleta (Siempre Una)*/
				request.getSession().setAttribute( "archivoImpresionFinal" , actualizacionDto.getArchivosImprimirBoleta().get(0));
				request.getSession().setAttribute( "tipoArchivo", "pdfBoleta");
				request.getSession().setAttribute( "numeroBoletaPdf", actualizacionDto.getNumeroBoletaArchivo());
				if (!Validaciones.isObjectNull(mv)) {
					mv.addObject("imprimible", true);
				} else {
					imprimible = true;
				}
			} else {
				/*No Archivos*/
				if (!Validaciones.isObjectNull(mv)) {
					mv.addObject("imprimible", false);
				} else {
					imprimible = false;
				}
			}
		}
		return imprimible;
	}
	
	/**
	 * Permite cargar el combo de cheque a reemplazar
	 * @param mv
	 * @param valorDto
	 * @throws NegocioExcepcion
	 */
	public void cargaComboChequeAreemplazar (ModelAndView mv, ValorDto valorDto) throws NegocioExcepcion {
		
		if (!Validaciones.isNullOrEmpty(valorDto.getCodCliente()) && Validaciones.isNumeric(valorDto.getCodCliente())) {
			List<ValorDto> chequesReemplazar;
			
			chequesReemplazar = boletaConValorServicio.buscarChequesReemplazar(valorDto.getCodCliente());
			mv.addObject("chequesReemplazar", chequesReemplazar);
			
			if(chequesReemplazar.size()>0){
				valorDto.setComboChequeReemplazar(true);
			}				
			if(!Validaciones.isNullOrEmpty(valorDto.getNroChequeAReemplazar())){
				valorDto.setComboChequeReemplazar(false);
			}
		}
	}
	
	/**
	 * Limpia Fechas del dto
	 * @param valorDto
	 */
	public void  limpiarFechas(ValorDto valorDto){
		if(valorDto.getFechaConstancia() != null){
			if(valorDto.getFechaConstancia().contains(",")){
				StringTokenizer token = new StringTokenizer(valorDto.getFechaConstancia(), ",");
				if(token.hasMoreTokens()){
					valorDto.setFechaConstancia(token.nextToken());
				} else {
					valorDto.setFechaConstancia(valorDto.getFechaConstancia().replace(",", ""));
				}
			}
		}
		if(valorDto.getFechaDeposito() != null){
			if(valorDto.getFechaDeposito().contains(",")){
				StringTokenizer token = new StringTokenizer(valorDto.getFechaDeposito(), ",");
				if(token.hasMoreTokens()){
					valorDto.setFechaDeposito(token.nextToken());
				} else {
					valorDto.setFechaDeposito(valorDto.getFechaDeposito().replace(",", ""));
				}
			}
		}
		if(valorDto.getFechaEmision() != null){
			if(valorDto.getFechaEmision().contains(",")){
				StringTokenizer token = new StringTokenizer(valorDto.getFechaEmision(), ",");
				if(token.hasMoreTokens()){
					valorDto.setFechaEmision(token.nextToken());
				} else {
					valorDto.setFechaEmision(valorDto.getFechaEmision().replace(",", ""));
				}
			}
		}
		if(valorDto.getFechaIngresoRecibo() != null){
			if(valorDto.getFechaIngresoRecibo().contains(",")){
				StringTokenizer token = new StringTokenizer(valorDto.getFechaIngresoRecibo(), ",");
				if(token.hasMoreTokens()){
					valorDto.setFechaIngresoRecibo(token.nextToken());
				} else {
					valorDto.setFechaIngresoRecibo(valorDto.getFechaIngresoRecibo().replace(",", ""));
				}
			}
		}
		if(valorDto.getFechaInterdeposito() != null){
			if(valorDto.getFechaInterdeposito().contains(",")){
				StringTokenizer token = new StringTokenizer(valorDto.getFechaInterdeposito(), ",");
				if(token.hasMoreTokens()){
					valorDto.setFechaInterdeposito(token.nextToken());
				} else {
					valorDto.setFechaInterdeposito(valorDto.getFechaInterdeposito().replace(",", ""));
				}
			}
		}
		if(valorDto.getFechaTramiteCMS() != null){
			if(valorDto.getFechaTramiteCMS().contains(",")){
				StringTokenizer token = new StringTokenizer(valorDto.getFechaTramiteCMS(), ",");
				if(token.hasMoreTokens()){
					valorDto.setFechaTramiteCMS(token.nextToken());
				} else {
					valorDto.setFechaTramiteCMS(valorDto.getFechaTramiteCMS().replace(",", ""));
				}
			}
		}
		if(valorDto.getFechaTransferencia() != null){
			if(valorDto.getFechaTransferencia().contains(",")){
				StringTokenizer token = new StringTokenizer(valorDto.getFechaTransferencia(), ",");
				if(token.hasMoreTokens()){
					valorDto.setFechaTransferencia(token.nextToken());
				} else {
					valorDto.setFechaTransferencia(valorDto.getFechaTransferencia().replace(",", ""));
				}
			}
		}
		if(valorDto.getFechaVencimiento() != null){
			if(valorDto.getFechaVencimiento().contains(",")){
				StringTokenizer token = new StringTokenizer(valorDto.getFechaVencimiento(), ",");
				if(token.hasMoreTokens()){
					valorDto.setFechaVencimiento(token.nextToken());
				} else {
					valorDto.setFechaVencimiento(valorDto.getFechaVencimiento().replace(",", ""));
				}
			}
		}
	}
	
	
	protected void cargarCombosParaBusquedaValores(ModelAndView mv,	UsuarioSesion userSesion, BoletaConValorFiltro filtro) throws ShivaExcepcion {
		try {
			
			List<ShvParamEmpresa> listaEmpresas = (List<ShvParamEmpresa>) combosServicio
					.listarEmpresasPorUsuario(userSesion);
			List<ShvParamSegmento> listaSegmentos = new ArrayList<ShvParamSegmento>();
			ShvParamSegmento todos = new ShvParamSegmento();
			todos.setIdSegmento(Constantes.TODOS_LOS_SEGMENTOS);
			todos.setId(Constantes.TODOS_LOS_SEGMENTOS);
			todos.setDescripcion("Todos");
			
			if(userSesion.esAnalista() || userSesion.esSupervisor()){
				
				if((listaEmpresas.size() > 0) && (Validaciones.isNullOrEmpty(filtro.getEmpresa()))){
					String idEmpresa = listaEmpresas.get(0).getIdEmpresa();
					filtro.setEmpresa(idEmpresa);
					listaSegmentos = combosServicio	.listarSegmentoPorEmpresaYUsuario(idEmpresa,	userSesion);
					mv.addObject("comboEmpresa", false);			
				}
			}else if(userSesion.esAdminValor()){								
				mv.addObject("comboEmpresa", true);				
			}
		
			if (listaEmpresas.size() == 1) {
				String idEmpresa = listaEmpresas.get(0).getIdEmpresa();
				filtro.setEmpresa(idEmpresa);
				listaSegmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(idEmpresa,userSesion);

				mv.addObject("comboEmpresa", false);
			} else {
				if (!Validaciones.isNullOrEmpty(filtro.getEmpresa())) {
					listaSegmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(	filtro.getEmpresa(), userSesion);
				}				
			}
			
			if (Validaciones.isCollectionNotEmpty(listaSegmentos)) {
				if (listaSegmentos.size() == 1) {
					if(Validaciones.isNullOrEmpty(filtro.getSegmento())){
						filtro.setSegmento(listaSegmentos.get(0).getIdSegmento());
					}
					mv.addObject("comboSegmento", false);	
				}else{
					mv.addObject("comboSegmento", true);
				}				
			}			
			
			listaSegmentos.add(todos);
			mv.addObject(SELECT_SEGMENTOS, listaSegmentos);
			mv.addObject(SELECT_EMPRESAS, listaEmpresas);
			
			List<Estado> listaEstados = Estado.listarEstados("VAL");
			
			List<BoletaConValorFiltro> listaDescripcion = new ArrayList<BoletaConValorFiltro>();
			for(Estado  e : listaEstados){
				BoletaConValorFiltro  bol = new BoletaConValorFiltro();
					if(!Estado.VAL_NO_DISPONIBLE.name().equals( e.name()) &&
							!Estado.VAL_BOLETA_PENDIENTE_AUTORIZAR.name().equals( e.name()) ){
					bol.setiDestado(e.name().toString());
					bol.setSelectEstado(e.descripcion());
					listaDescripcion.add(bol);
					}

			}
			List<BoletaConValorFiltro> listaDescripcionAuxiliar = new ArrayList<BoletaConValorFiltro>();
			
			for(BoletaConValorFiltro bol :listaDescripcion ){
					listaDescripcionAuxiliar.add(bol);
			}
			
			/*
			for(BoletaConValorFiltro bol :listaDescripcion ){
				if(bol.getiDestado().equals("VAL_NO_DISPONIBLE")){
					listaDescripcionAuxiliar.add(bol);
				}
			}
			*/
			
			if("".equalsIgnoreCase(filtro.getSelectEstado())){
				mv.addObject("evaluarEstado", true);
				Estado  estado = Estado.getEnumByName(Estado.VAL_DISPONIBLE.name());
				filtro.setEstadoDescripcion(estado.descripcion());
				filtro.setSelectEstadoDos(estado.name());
			} else if(filtro.getSelectEstado() != null){
				mv.addObject("evaluarEstado", false);
				Estado  estadoParaDescripcion=Estado.getEnumByName(filtro.getSelectEstado());
				filtro.setEstadoDescripcion(estadoParaDescripcion.descripcion());
				filtro.setSelectEstadoDos(estadoParaDescripcion.name());
				mv.addObject("comboEstado", false);
			}else{
				mv.addObject("evaluarEstado", true);
				Estado  estado = Estado.getEnumByName(Estado.VAL_DISPONIBLE.name());
				filtro.setEstadoDescripcion(estado.descripcion());
				filtro.setSelectEstadoDos(estado.name());
				filtro.setSelectEstado("");
			}
			mv.addObject(SELECT_ESTADOS, listaDescripcionAuxiliar);
			
			if(Validaciones.isNullOrEmpty(filtro.getSelectEstado())){				
				mv.addObject("comboEstado", true);
			}else{			
				mv.addObject("comboEstado", false);
			}
			mv.addObject("comboEstado", false);			
		
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	
	
	protected void cargarComboAnalista(ModelAndView mv, BoletaConValorFiltro filtro) throws ShivaExcepcion {
		try {
			
			List<UsuarioLdapDto> listaAnalistaFiltroResultadoFinal = new ArrayList<UsuarioLdapDto>();
			
			if(!Validaciones.isNullOrEmpty(filtro.getEmpresa())
					&& !Validaciones.isNullOrEmpty(filtro.getSegmento())){
				UsuarioLdapDto dto = new UsuarioLdapDto();
				listaAnalistaFiltroResultadoFinal.add(dto);
				List<UsuarioLdapDto> buscarUsuariosAnalistaPorPerfilSegmento = buscarUsuariosPorPerfilSegmento(filtro, TipoPerfilEnum.ANALISTA.nombreLdap());
				List<UsuarioLdapDto> buscarUsuariosCajeroPagadorPorPerfilSegmento = buscarUsuariosPorPerfilSegmento(filtro, TipoPerfilEnum.CAJERO_PAGADOR.nombreLdap());
				listaAnalistaFiltroResultadoFinal.addAll(buscarUsuariosPorPerfilSegmento(filtro, TipoPerfilEnum.ANALISTA.nombreLdap()));
				listaAnalistaFiltroResultadoFinal.addAll(buscarUsuariosPorPerfilSegmento(filtro, TipoPerfilEnum.CAJERO_PAGADOR.nombreLdap()));
				listaAnalistaFiltroResultadoFinal = removerUsuariosDuplicados(buscarUsuariosAnalistaPorPerfilSegmento, buscarUsuariosCajeroPagadorPorPerfilSegmento, listaAnalistaFiltroResultadoFinal);
				mv.addObject("comboAnalistaFiltro", true);
			}
			mv.addObject("listaAnalistaFiltro", listaAnalistaFiltroResultadoFinal);
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}

	/**
	 * @author guido.n.settecerze u572487
	 * @param listaAnalistaFiltro
	 * @param listaCajeroPagadorFiltro
	 * @param listaAnalistaFiltroResultadoFinal
	 * @return 
	 */
	public List<UsuarioLdapDto> removerUsuariosDuplicados(List<UsuarioLdapDto> listaAnalistaFiltro, List<UsuarioLdapDto> listaCajeroPagadorFiltro, List<UsuarioLdapDto> listaAnalistaFiltroResultadoFinal){
		List<UsuarioLdapDto> listaAnalistaFiltroConRepetidos = new ArrayList<UsuarioLdapDto>();
		//busco los repetidos
		for (int x=0;x<listaAnalistaFiltro.size();x++) {
			for (int y=0;y<listaCajeroPagadorFiltro.size();y++) {
				if (listaAnalistaFiltro.get(x).getNombreCompleto().trim().equals(listaCajeroPagadorFiltro.get(y).getNombreCompleto().trim())){
					listaAnalistaFiltroConRepetidos.add(listaAnalistaFiltro.get(x));
				}
			}
		}
		//limpio los repetidos
		for (int x=0;x<listaAnalistaFiltroResultadoFinal.size();x++) {
			for (int y=0;y<listaAnalistaFiltroConRepetidos.size();y++) {
				if (!(Validaciones.isNullOrEmpty(listaAnalistaFiltroResultadoFinal.get(x).getNombreCompleto())) && (listaAnalistaFiltroResultadoFinal.get(x).getNombreCompleto().trim().equals(listaAnalistaFiltroConRepetidos.get(y).getNombreCompleto().trim()))){
					listaAnalistaFiltroResultadoFinal.remove(listaAnalistaFiltroResultadoFinal.get(x));
					listaAnalistaFiltroConRepetidos.remove(listaAnalistaFiltroConRepetidos.get(y));
				}
			}
		}
		return listaAnalistaFiltroResultadoFinal;
	}
	/**
	 * @param filtro
	 * @param perfil
	 * @return
	 * @throws LdapExcepcion
	 */
	private List<UsuarioLdapDto> buscarUsuariosPorPerfilSegmento(BoletaConValorFiltro filtro, String perfil) throws LdapExcepcion {
		String perfilABuscar;
		List<UsuarioLdapDto> listaAnalistaFiltro = new ArrayList<UsuarioLdapDto>();
		if(Constantes.TODOS_LOS_SEGMENTOS.equals(filtro.getSegmento())){
			perfilABuscar = perfil + "_" + filtro.getEmpresa();
		}else{
			perfilABuscar = perfil + "_" + filtro.getEmpresa() + "_" + filtro.getSegmento();
		}
		Collection<UsuarioLdapDto> listaLdap =  ldapServicio.buscarUsuariosPorPerfilEnMemoria(perfilABuscar);
		
		for(UsuarioLdapDto usuario : listaLdap){
			listaAnalistaFiltro.add(usuario);
		}
		return listaAnalistaFiltro;
	}
	
	
	/**
	 * 
	 * @param mv
	 * @param userSesion
	 * @param filtro
	 * @throws ShivaExcepcion
	 */
	private void cargarCombosParaBusquedaAutorizacionValores(ModelAndView mv,
			UsuarioSesion userSesion,Filtro filtro) throws ShivaExcepcion {
		
		try {
			List<ShvParamEmpresa> listaEmpresas = (List<ShvParamEmpresa>) combosServicio.listarEmpresasPorUsuario(userSesion);
			List<ShvParamSegmento> listaSegmentos = new ArrayList<ShvParamSegmento>();

			if (listaEmpresas.size() == 1) {
				String idEmpresa = listaEmpresas.get(0).getIdEmpresa();
				filtro.setEmpresa(idEmpresa);
				listaSegmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(idEmpresa,	userSesion);
				mv.addObject("comboEmpresa", false);
			} else {
				if (!Validaciones.isNullOrEmpty(filtro.getEmpresa())) {
					listaSegmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(filtro.getEmpresa(), userSesion);
				}
				mv.addObject("comboEmpresa", true);
			}
			
			mv.addObject(SELECT_SEGMENTOS, listaSegmentos);
			mv.addObject("comboSegmento", false);
		
			if (listaSegmentos.size() >= 1) {
				if(Validaciones.isNullOrEmpty(filtro.getSegmento())){
					filtro.setSegmento(listaSegmentos.get(0).getIdSegmento());
				}
			}
			mv.addObject(SELECT_EMPRESAS, listaEmpresas);

		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}

	private void cargarCombosDesdeDto(ModelAndView mv, ValorDto valorDto) throws NegocioExcepcion{
		List<ShvParamEmpresa> listaEmpresa = new ArrayList<ShvParamEmpresa>();
		listaEmpresa.add(combosServicio.buscarEmpresa(valorDto.getIdEmpresa()));
		mv.addObject(SELECT_EMPRESAS, listaEmpresa);
		List<ShvParamSegmento> listaSegmento = new ArrayList<ShvParamSegmento>();
		listaSegmento.add(combosServicio.buscarPorId(ShvParamSegmento.class,"idSegmento",valorDto.getIdSegmento()));
		mv.addObject(SELECT_SEGMENTOS, listaSegmento);
		cargarMotivos(mv, valorDto);
		List<ShvParamOrigen> listaOrigen = new ArrayList<ShvParamOrigen>();
		listaOrigen.add(combosServicio.buscarOrigenPorId(valorDto.getIdOrigen()));
		mv.addObject(SELECT_ORIGENES, listaOrigen);
		List<ShvParamBanco> listaBanco = new ArrayList<ShvParamBanco>();
		listaBanco.add(combosServicio.buscarBanco(valorDto.getIdBancoOrigen()));
		mv.addObject(SELECT_BANCO_ORIGENES, listaBanco);
		cargarCopropietario(mv,valorDto);
		mv.addObject("comboSegmento", false);
		mv.addObject("comboEmpresa", false);
		mv.addObject("comboOrigen", false);
		mv.addObject("comboAcuerdo", false);
		mv.addObject("comboCuenta", false);
		mv.addObject("comboBancoOrigen", false);
		
	}
	
	@RequestMapping(value="/isAliveValores", method=RequestMethod.POST)
	public @ResponseBody CobroJsonResponse isAliveValores(@RequestBody final CobroDto cobro, HttpServletRequest request) throws Exception {
		CobroJsonResponse json = new CobroJsonResponse();
		json.setSuccess(true);
		return json;
		
	}
	
	@RequestMapping(value = "valores-aviso-alta/busquedaClientes", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ClienteJsonResponse buscarClientes(@RequestBody final ClienteFiltro clienteFiltro, HttpServletRequest request) throws Exception {
		ClienteJsonResponse rta = new ClienteJsonResponse();
		List<ClienteDto> clientesDto = new ArrayList<ClienteDto>();

		try {
			clientesDto = clienteFacade.consultarClienteSiebel(clienteFiltro);

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
	
	/************************************************
	 * GETTER & SETTER
	 ************************************************/
	public IValorServicio getBoletaConValorServicio() {
		return boletaConValorServicio;
	}

	public void setBoletaConValorServicio(IValorServicio boletaConValorServicio) {
		this.boletaConValorServicio = boletaConValorServicio;
	}

	public BoletaConValorValidacionWeb getBoletaConValorValidacionWeb() {
		return boletaConValorValidacionWeb;
	}

	public void setBoletaConValorValidacionWeb(
			BoletaConValorValidacionWeb boletaConValorValidacionWeb) {
		this.boletaConValorValidacionWeb = boletaConValorValidacionWeb;
	}
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
}
