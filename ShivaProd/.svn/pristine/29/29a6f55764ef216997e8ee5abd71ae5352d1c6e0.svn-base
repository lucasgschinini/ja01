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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
import ar.com.telecom.shiva.base.comparador.UsuarioLdapDtoComparator;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.CriterioBusquedaClienteEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.IOperacionMasivaValidacionServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasivaArchivo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoCobro;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ArchivoOperacionMasivaProcesadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaArchivoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaHistoricaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ErrorJson;
import ar.com.telecom.shiva.presentacion.bean.dto.json.JsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.OperacionesMasivasJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.SelectOptionJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteCobroOnlineFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteOperacionMasivaFiltro;
import ar.com.telecom.shiva.presentacion.bean.validacion.OperacionMasivaValidacionWeb;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.CurrencyEditor;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.CustomBindingErrorProcessor;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.IntegerEditor;

@Controller
public class OperacionMasivaController extends Controlador {

	private static final String OPERACION_MASIVA_ALTA = "operacion-masiva/operacion-masiva-alta";
	private static final String OPERACION_MASIVA_APROBACION = "operacion-masiva/operacion-masiva-detalle-aprobacion";
	private static final String OBJECT_COMMAND = "operacionMasivaDto";
	private static final String OPERACION_MASIVA_BUSQUEDA = "operacion-masiva/operacion-masiva-busqueda";
	private static final String OPERACION_MASIVA_HISTORIAL_VIEW = "operacion-masiva/operacion-masiva-historial";
	private static final String OPERACION_MASIVA_EDICION = "operacion-masiva/operacion-masiva-editar";
	private static final String ACTUALIZACION_EXITOSA_VIEW = "operacion-masiva/operacion-masiva-actualizacion-exitosa";
	
	@Autowired
	private OperacionMasivaValidacionWeb operacionMasivaValidacionWeb;
	@Autowired
	private IOperacionMasivaServicio operacionMasivaServicio;
	@Autowired
	private IOperacionMasivaValidacionServicio operacionMasivaValidacionServicio;
	@Autowired
	private ILdapServicio ldapServicio;
	@Autowired
	ITareaServicio tareaServicio;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.setBindingErrorProcessor(new CustomBindingErrorProcessor());

		DateFormat dateFormat = new SimpleDateFormat(Utilidad.DATE_FORMAT);
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
		binder.registerCustomEditor(BigDecimal.class, new CurrencyEditor());
		binder.registerCustomEditor(Integer.class, new IntegerEditor());
		if (binder.getTarget() instanceof OperacionMasivaDto) {
			binder.setValidator(operacionMasivaValidacionWeb);
		}
	}
	
	/***************************************************************************
	 * Operaciones masivas
	 * ************************************************************************/
	/**
	 * Método para ir a la pagina de operaciones masivas.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/operacion-masiva-alta")
	public ModelAndView operacionMasivaAlta(HttpServletRequest request, OperacionMasivaDto operacionMasivaDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		
		if (Validaciones.isObjectNull(operacionMasivaDto)) {
			operacionMasivaDto = new OperacionMasivaDto();
			userSesion.setArchivoOperacionMasiva(null);
			userSesion.setFileBytes(null);
		}
		
		// Cargo analista logueado
		operacionMasivaDto.setIdAnalista(userSesion.getUsuario());
		operacionMasivaDto.setNombreAnalista(userSesion.getNombreCompleto());
		
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_ALTA);
		mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);
		mv.addObject("imprimibleArchivo", false);
		
		return mv;
	}		
	
	/**
	 * Metodo que se llama al subir un archivo de una operacion masiva
	 * @param request
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/procesar-operacion-masiva-alta")
	@ControlProcesoTransacciones
	public ModelAndView procesarOperacionMasivaAlta(HttpServletRequest request, 
			@ModelAttribute("operacionMasivaDto") OperacionMasivaDto operacionMasivaDto,
			BindingResult result) throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		this.guardarArchivoOperacionMasivaEnDto(operacionMasivaDto, request);
		this.moverSessionComprobante(operacionMasivaDto, operacionMasivaDto.getFileNameOperacionMasiva());
		
		// Validaciones
		this.getOperacionMasivaValidacionWeb().validate(operacionMasivaDto, result);
		
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_ALTA);
		operacionMasivaDto.setIdOperacionMasiva(null);
		operacionMasivaDto.setDescripcionComprobante("");
		operacionMasivaDto.setUsuarioModificacion(userSesion.getUsuario());
		operacionMasivaDto.setFechaUltimaModificacion(new Date());
		
		if ((result.hasErrors() || !Validaciones.isNullOrEmpty(operacionMasivaDto.getResultadoValidaciones())) &&
				!Mensajes.VALIDACION_OK_STRING.equals(operacionMasivaDto.getResultadoValidaciones())) {

			if (Utilidad.getErrorGeneral(result)!=null) {
				throw new ShivaExcepcion(Utilidad.getErrorGeneral(result));
			}
			return operacionMasivaAlta(request, operacionMasivaDto);
		}
		
		if (operacionMasivaDto.isDuplicado()) { // pasar a result // validaciones
			return operacionMasivaAlta(request, operacionMasivaDto);
		}
		
		ArchivoOperacionMasivaProcesadoDto archivoProcesado = operacionMasivaServicio.procesarArchivo(operacionMasivaDto, false);
		
		if (archivoProcesado!=null) {
			operacionMasivaDto.getArchivosPendientes().add(archivoProcesado);
			
		}
		
		operacionMasivaDto.setArchivosPendientes((List<ArchivoOperacionMasivaProcesadoDto>) Utilidad.guionesNull(operacionMasivaDto.getArchivosPendientes()));
		
		//operacionMasivaDto.setLastFileOperacionMasiva(null);
		operacionMasivaDto.setFileNameOperacionMasiva("");
		operacionMasivaDto.setMoverSeccionComprobante(false);
		userSesion.setArchivoOperacionMasiva(null);
		userSesion.setFileBytes(null);
				
		List<ComprobanteDto> listaComprobantes = new ArrayList<ComprobanteDto>();
		if(operacionMasivaDto.isMantenerComprobantesAdjuntos()){
			listaComprobantes.addAll(operacionMasivaDto.getListaComprobantes());
		}
		operacionMasivaDto.setListaComprobantes(listaComprobantes);
		
		
		if(operacionMasivaDto.getResultadoValidaciones().equals(Mensajes.VALIDACION_OK_STRING)){
			operacionMasivaDto.setIdEmpresa(null);
			operacionMasivaDto.setIdSegmento(null);
			operacionMasivaDto.setIdMotivo(null);
		}
		
		mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);
		mv.addObject("imprimibleArchivo", false);
		mv.addObject("operacionMasivaEditable", false);
		
		return mv;
	}
	
	/**
	 * Spring 8
	 * Anula operacion masiva, se accede desde la pantalla de alta de operaciones masivas
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/operacion-masiva-anular-alta")
	public ModelAndView operacionMasivaAnularAlta(HttpServletRequest request, @ModelAttribute("operacionMasivaDto") OperacionMasivaDto operacionMasivaDto) throws Exception {

		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String idOperacionMasivaAAnular = request.getParameter("idOperacionMasiva");
		
		operacionMasivaServicio.anularOperacionMasiva(idOperacionMasivaAAnular, userSesion.getIdUsuario());

		operacionMasivaDto = this.actualizarListadoArchivosPendientes(operacionMasivaDto, idOperacionMasivaAAnular); 
		
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_ALTA);
		operacionMasivaDto.setDescripcionComprobante("");
		operacionMasivaDto.setUsuarioModificacion(userSesion.getUsuario());
		
		//operacionMasivaDto.setLastFileOperacionMasiva(null);
		operacionMasivaDto.setFileNameOperacionMasiva("");
		operacionMasivaDto.setResultadoValidaciones("");
		operacionMasivaDto.setMoverSeccionComprobante(false);
		userSesion.setArchivoOperacionMasiva(null);
		userSesion.setFileBytes(null);
				
		List<ComprobanteDto> listaComprobantes = new ArrayList<ComprobanteDto>();
		if(operacionMasivaDto.isMantenerComprobantesAdjuntos()){
			listaComprobantes.addAll(operacionMasivaDto.getListaComprobantes());
		}
		operacionMasivaDto.setListaComprobantes(listaComprobantes);
		
		operacionMasivaDto.setIdEmpresa(null);
		operacionMasivaDto.setIdSegmento(null);
		operacionMasivaDto.setIdMotivo(null);
		
		mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);
		mv.addObject("imprimibleArchivo", false);
		mv.addObject("operacionMasivaEditable", false);
		
		return mv;
	}
	
	
	private OperacionMasivaDto actualizarListadoArchivosPendientes(OperacionMasivaDto operacionMasivaDto,
			String idOperacionMasivaAAnular) {
		
		List<ArchivoOperacionMasivaProcesadoDto> archivosPendientes = operacionMasivaDto.getArchivosPendientes();
		for (ArchivoOperacionMasivaProcesadoDto archivo : archivosPendientes) {
			if (archivo.getIdOperacionMasiva() == new Long(idOperacionMasivaAAnular).longValue()) {
				archivosPendientes.remove(archivo);
				break;
			}
		}
		
		operacionMasivaDto.setArchivosPendientes(archivosPendientes);
		return operacionMasivaDto;
	}

	/**
	 * Spring 8
	 * Editar operacion masiva en la pantalla de configuracion de operacion masiva, se accede desde la pantalla de busqueda de Operacion Masiva
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/operacion-masiva-edicion")
	public ModelAndView operacionMasivaEdicion(HttpServletRequest request, 
			@ModelAttribute("operacionMasivaDto") OperacionMasivaDto operacionMasivaDto) throws Exception {
		
		ModelAndView mv = operacionMasivaEditar(request, operacionMasivaDto);
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String idOperacionMasiva = request.getParameter("idOperacionMasiva");
		String nombreArchivo = request.getParameter("nombreArchivo");
		userSesion.setActivarBoton(false);
		
		if(operacionMasivaDto.getIdOperacionMasiva() == null){
			
			mv.addObject("operacionMasivaFiltro", userSesion.getOperacionMasivaFiltro());
			mv.addObject("operacionMasivaDto", userSesion.getOperacionMasivaDto());
			return mv;
		}
		
		mv.addObject("idOperacionMasiva", idOperacionMasiva);
		mv.addObject("nombreArchivo", nombreArchivo);

		OperacionMasivaArchivoDto archivoDto = null;
		if(!Validaciones.isNullEmptyOrDash(nombreArchivo)){
			archivoDto = operacionMasivaServicio.buscarListaComprobantesXNombreArchivo(nombreArchivo);
		}else{
			archivoDto = operacionMasivaServicio.buscarListaComprobantesXIdArchivo(idOperacionMasiva);
		}
		
		OperacionMasivaDto operacionMasiva = operacionMasivaServicio.buscarOperacionMasiva(Long.parseLong(idOperacionMasiva));
		
		
		if(Validaciones.isCollectionNotEmpty(operacionMasiva.getListaComprobantes())){
			Long i = 0l;
			for (int e = 0; e < operacionMasiva.getListaComprobantes().size(); e++) {
				operacionMasiva.getListaComprobantes().get(e).setOrdenTabla(i);
				i++;
			}
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
		
		VistaSoporteOperacionMasivaFiltro opMasFiltro = new VistaSoporteOperacionMasivaFiltro();

		opMasFiltro.setIdCopropietarioEdicion(operacionMasiva.getIdCopropietario());
		opMasFiltro.setIdMotivoEdicion(operacionMasiva.getIdMotivo());
		opMasFiltro.setObservaciones(Utilidad.formateadoVista(operacionMasivaServicio.obtenerObseHistorial(operacionMasiva,null)));
		
		mv.addObject("operacionMasivaFiltro", opMasFiltro);
		
		operacionMasiva.setNombreArchivo(archivoDto.getNombreArchivo());
		List<ArchivoOperacionMasivaProcesadoDto> listaArchivo = new ArrayList<ArchivoOperacionMasivaProcesadoDto>();
		ArchivoOperacionMasivaProcesadoDto archivoProcesadoDto = new ArchivoOperacionMasivaProcesadoDto();
		
		archivoProcesadoDto.setIdOperacionMasiva(Long.parseLong(idOperacionMasiva));
		archivoProcesadoDto.setNombreArchivo(archivoDto.getNombreArchivo());
		if(operacionMasiva.getTipoOperacionMasiva()!= null){
			archivoProcesadoDto.setTipoOperacion(operacionMasiva.getTipoOperacionMasiva().getDescripcion());
		}
		archivoProcesadoDto.setCantRegistros(archivoDto.getCantidadRegistros().intValue());
		archivoProcesadoDto.setEstado(operacionMasiva.getDescripcionEstado());
		archivoProcesadoDto.setImporte(Utilidad.formatCurrency(archivoDto.getImporteTotal(), 2));
		
		archivoProcesadoDto.setIdAnalista(operacionMasiva.getIdAnalista());
		archivoProcesadoDto.setNombreAnalista(operacionMasiva.getNombreAnalista());
		archivoProcesadoDto.setMailAnalista(operacionMasiva.getMailAnalista());
		archivoProcesadoDto.setUrlFotoAnalista(operacionMasiva.getUrlFotoAnalista());
		
		archivoProcesadoDto.setIdCopropietario(operacionMasiva.getIdCopropietario());
		archivoProcesadoDto.setNombreCopropietario(operacionMasiva.getNombreCopropietario());
		archivoProcesadoDto.setMailCopropietario(operacionMasiva.getMailCopropietario());
		archivoProcesadoDto.setUrlFotoCopropietario(operacionMasiva.getUrlFotoCopropietario());
		
		archivoProcesadoDto.setMotivo(operacionMasiva.getMotivo());

		listaArchivo.add(archivoProcesadoDto);
		operacionMasiva.setArchivosPendientes(listaArchivo);
		
		operacionMasiva.setRegistrosIngresados(archivoDto.getCantidadRegistros().toString());
		
		if(userSesion.getEsPerfilSupervisorOperacionMasiva() && userSesion.getIdSegmentos().contains(operacionMasiva.getIdSegmento())) {
			mv.addObject("perfilEsSupervisor", true);
		} else {
			mv.addObject("perfilEsSupervisor", false);
		}
		
		List<UsuarioLdapDto> copropietarios = new ArrayList<UsuarioLdapDto>();
		
		Collection<String> usuariosExcluidos = new ArrayList<String>();
		usuariosExcluidos.add(userSesion.getIdUsuario());
		copropietarios = (List<UsuarioLdapDto>) combosServicio.listarCopropietarioOperacionMasivaPorEmpresaYSegmento(operacionMasiva.getIdEmpresa(),operacionMasiva.getIdSegmento(), usuariosExcluidos);
		
		userSesion.setArchivoOperacionMasiva(null);
		userSesion.setFileBytes(null);
		String observacionAnterior = Utilidad.formateadoVista(operacionMasivaServicio.obtenerObseHistorial(operacionMasiva,null));
		operacionMasiva.setObservacionAnterior(observacionAnterior);
		
		this.moverSessionComprobante(operacionMasiva, operacionMasiva.getNombreArchivo());

		
		mv.addObject("operacionMasivaDto", operacionMasiva);
		userSesion.setOperacionMasivaDto(operacionMasiva);
		mv.addObject("observacionAnterior", observacionAnterior);
		if(Validaciones.isNullEmptyOrDash(observacionAnterior)){
			mv.addObject("observacion", operacionMasiva.getObservacion());
		}else{
			mv.addObject("observacion", "");
		}
		mv.addObject("selectEmpresa", operacionMasivaDto.getEmpresa());
		mv.addObject("nombreAnalista", operacionMasivaDto.getNombreAnalista());
		Collections.sort(copropietarios, new UsuarioLdapDtoComparator());
		mv.addObject("copropietarios", copropietarios);
		if(!Validaciones.isNullOrEmpty(operacionMasivaDto.getIdMotivo())){
			mv.addObject("idMotivoSeleccionado", operacionMasivaDto.getMotivoOMFormateado(operacionMasivaDto.getIdMotivo()));
		}
//		mv.addObject("motivos", listaMotivos);
		mv.addObject("idOperacionMasiva", operacionMasivaDto.getIdOperacionMasiva());
		mv.addObject("listaComprobantes", operacionMasiva.getListaComprobantes());
		mv.addObject("listaArchivosPendientes", ((ArrayList<ArchivoOperacionMasivaProcesadoDto>) Utilidad.guionesNull(new ArrayList<>(operacionMasiva.getArchivosPendientes()))));
		mv.addObject("archivoValidado", true);
		mv.addObject("operacionMasivaEditable", true);
		
		
		return mv;
	}
	
	/**
	 * Método para ir a la pagina de edicion de operaciones masivas.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/operacion-masiva-editar")
	public ModelAndView operacionMasivaEditar(HttpServletRequest request, OperacionMasivaDto operacionMasivaDto) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		if (Validaciones.isObjectNull(operacionMasivaDto)) {
			operacionMasivaDto = new OperacionMasivaDto();
			userSesion.setArchivoOperacionMasiva(null);
			userSesion.setFileBytes(null);
		}
		
		// Cargo analista logueado
		operacionMasivaDto.setIdAnalista(userSesion.getUsuario());
		operacionMasivaDto.setNombreAnalista(userSesion.getNombreCompleto());
		
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_EDICION);
		mv.addObject("operacionMasivaFiltro", userSesion.getOperacionMasivaFiltro());
		mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);
		mv.addObject("imprimibleArchivo", false);
		
		return mv;
	}
	
	/**
	 * Metodo que se llama al subir un archivo de una operacion masiva
	 * @param request
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/procesar-operacion-masiva-alta-edicion")
	@ControlProcesoTransacciones
	public ModelAndView procesarOperacionMasivaAltaEdicion(HttpServletRequest request, 
			@ModelAttribute("operacionMasivaDto") OperacionMasivaDto operacionMasivaDto,
			BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		actualizarFiltro(operacionMasivaDto,request);
		this.guardarArchivoOperacionMasivaEnDto(operacionMasivaDto, request);

		this.moverSessionComprobante(operacionMasivaDto, operacionMasivaDto.getFileNameOperacionMasiva());
		
		// Validaciones
		this.getOperacionMasivaValidacionWeb().validate(operacionMasivaDto, result);
		
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_EDICION);
		operacionMasivaDto.setUsuarioModificacion(userSesion.getUsuario());
		operacionMasivaDto.setFechaUltimaModificacion(new Date());
		mv.addObject("operacionMasivaFiltro", userSesion.getOperacionMasivaFiltro());
		if ((result.hasErrors() || !Validaciones.isNullOrEmpty(operacionMasivaDto.getResultadoValidaciones())) &&
				!Mensajes.VALIDACION_OK_STRING.equals(operacionMasivaDto.getResultadoValidaciones())) {

			if (Utilidad.getErrorGeneral(result)!=null) {
				throw new ShivaExcepcion(Utilidad.getErrorGeneral(result));
			}
			return operacionMasivaEditar(request, operacionMasivaDto);
		}
		
		if(operacionMasivaDto.isDuplicado()){ // pasar a result // validaciones
			return operacionMasivaEditar(request, operacionMasivaDto);
		}
		
		ArchivoOperacionMasivaProcesadoDto archivoProcesado = operacionMasivaServicio.procesarArchivo(operacionMasivaDto, false);
		if(archivoProcesado!=null){
			operacionMasivaDto.getArchivosPendientes().clear();
			operacionMasivaDto.getArchivosPendientes().add(archivoProcesado);
		}	
		
		operacionMasivaDto.setArchivoValidado(true);
		//operacionMasivaDto.setLastFileOperacionMasiva(null);
		operacionMasivaDto.setFileNameOperacionMasiva("");
		userSesion.setArchivoOperacionMasiva(null);
		userSesion.setFileBytes(null);
		this.guardarArchivoOperacionMasivaEnDto(operacionMasivaDto, request);
		this.moverSessionComprobante(operacionMasivaDto, operacionMasivaDto.getFileNameOperacionMasiva());
		
		mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);
		
		mv.addObject("archivoValidado", true);
		mv.addObject("imprimibleArchivo", false);
		
		userSesion.setArchivoValidado(true);
		userSesion.setActivarBoton(false);
		
		return mv;
	}
	
	/**
	 * Metodo que se al apretan el boton guardar
	 * @param request
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/operacion-masiva-edicion-guardar")
	@ControlProcesoTransacciones
	public ModelAndView guardarOperacionMasivaEdicion(HttpServletRequest request, 
			@ModelAttribute("operacionMasivaDto") OperacionMasivaDto operacionMasivaDto,
			BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(ACTUALIZACION_EXITOSA_VIEW);
		
		if(userSesion.getArchivoValidado()){
			this.guardarArchivoOperacionMasivaEnDto(operacionMasivaDto, request);
			operacionMasivaDto.setArchivoValidado(userSesion.getArchivoValidado());
			this.moverSessionComprobante(operacionMasivaDto, operacionMasivaDto.getFileNameOperacionMasiva());
			
			// Validaciones
			this.getOperacionMasivaValidacionWeb().validate(operacionMasivaDto, result);


			operacionMasivaDto.setDescripcionComprobante("");
			operacionMasivaDto.setUsuarioModificacion(userSesion.getUsuario());
			operacionMasivaDto.setFechaUltimaModificacion(new Date());
			
			List<ComprobanteDto> listaComprobantes = new ArrayList<ComprobanteDto>();
			if(operacionMasivaDto.isMantenerComprobantesAdjuntos()){
				listaComprobantes.addAll(operacionMasivaDto.getListaComprobantes());
			}
			operacionMasivaDto.setListaComprobantes(listaComprobantes);

			if ((result.hasErrors() || !Validaciones.isNullOrEmpty(operacionMasivaDto.getResultadoValidaciones())) &&
				!Mensajes.VALIDACION_OK_STRING.equals(operacionMasivaDto.getResultadoValidaciones())) {
	
				if (Utilidad.getErrorGeneral(result)!=null) {
					throw new ShivaExcepcion(Utilidad.getErrorGeneral(result));
				}
				return operacionMasivaEditar(request, operacionMasivaDto);
			}

			if(operacionMasivaDto.isDuplicado()){ // pasar a result // validaciones
				return operacionMasivaEditar(request, operacionMasivaDto);
			}

			ArchivoOperacionMasivaProcesadoDto archivoProcesado = operacionMasivaServicio.procesarArchivo(operacionMasivaDto, true);
			if(archivoProcesado!=null){
				operacionMasivaDto.getArchivosPendientes().clear();
				operacionMasivaDto.getArchivosPendientes().add(archivoProcesado);
			}
			
		} else{ 
			operacionMasivaDto.setUsuarioModificacion(userSesion.getIdUsuario());
			operacionMasivaValidacionWeb.validarCamposBanales(operacionMasivaDto,result);
			if (result.hasErrors() || !Validaciones.isNullOrEmpty(operacionMasivaDto.getResultadoValidaciones())) {
				if (Utilidad.getErrorGeneral(result)!=null) {
					throw new ShivaExcepcion(Utilidad.getErrorGeneral(result));
				}
				return operacionMasivaEditar(request, operacionMasivaDto);
			}
			operacionMasivaServicio.cargarDatosBanales(operacionMasivaDto);
			
		}
		
		//operacionMasivaDto.setLastFileOperacionMasiva(null);
		operacionMasivaDto.setFileNameOperacionMasiva("");
		operacionMasivaDto.setMoverSeccionComprobante(false);
		userSesion.setArchivoOperacionMasiva(null);
		userSesion.setFileBytes(null);
		mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);
		mv.addObject("imprimibleArchivo", false);
		
		
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("operacionMasivaEdicionOK.operacionMasiva.mensaje"));
		
		if("/regresar-bandeja-de-entrada".equals(request.getParameter("idVolver"))){
			mv.addObject("url", "bandeja-de-entrada");
		}
		else
		{			
			mv.addObject("url", "operacion-masiva-busqueda?idVolver=busqueda&volver=true&opMas=" + operacionMasivaDto.getIdOperacionMasiva());
		}
		
		
		return mv;
	}
	
	/***
	 * EMPRESA
	 */
	@RequestMapping("/seleccionoEmpresaOperacionMasiva")
	public ModelAndView seleccionoEmpresa(HttpServletRequest request,
			@ModelAttribute("operacionMasivaDto") OperacionMasivaDto operacionMasivaDto) throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		this.guardarArchivoOperacionMasivaEnDto(operacionMasivaDto, request);
		this.moverSessionComprobante(operacionMasivaDto, operacionMasivaDto.getFileNameOperacionMasiva());
		
		operacionMasivaDto.setIdSegmento("");
		
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_ALTA);
		mv = cargarModelAndView(mv, userSesion, operacionMasivaDto);
		mv.addObject("archivoValidado", operacionMasivaDto.isArchivoValidado());
		mv.addObject("imprimibleArchivo", false);
		
		return mv;
	}
	
	/***
	 * SEGMENTO
	 */
	@RequestMapping("/seleccionoSegmentoOperacionMasiva")
	public ModelAndView seleccionoSegmento(HttpServletRequest request,
			@ModelAttribute("operacionMasivaDto") OperacionMasivaDto operacionMasivaDto) throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		this.guardarArchivoOperacionMasivaEnDto(operacionMasivaDto, request);
		this.moverSessionComprobante(operacionMasivaDto, operacionMasivaDto.getFileNameOperacionMasiva());
		
		operacionMasivaDto.setIdCopropietario("");
		
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_ALTA);
		
		mv = cargarModelAndView(mv, userSesion, operacionMasivaDto);
		mv.addObject("operacionMasivaDto", operacionMasivaDto);
		mv.addObject("archivoValidado", operacionMasivaDto.isArchivoValidado());
		mv.addObject("imprimibleArchivo", false);
		
		return mv;
	}
	
	/***
	 * SEGMENTO
	 */
	@RequestMapping("/seleccionoSegmentoOperacionMasivaEdicion")
	public ModelAndView seleccionoSegmentoEdicion(HttpServletRequest request,
			@ModelAttribute("operacionMasivaDto") OperacionMasivaDto operacionMasivaDto) throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		this.guardarArchivoOperacionMasivaEnDto(operacionMasivaDto, request);
		this.moverSessionComprobante(operacionMasivaDto, operacionMasivaDto.getFileNameOperacionMasiva());
		
		operacionMasivaDto.setIdCopropietario("");
		actualizarFiltro(operacionMasivaDto,request);
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_EDICION);
		
		mv = cargarModelAndView(mv, userSesion, operacionMasivaDto);
		mv.addObject("volver", request.getParameter("volver"));
		mv.addObject("idVolver", request.getParameter("idVolver"));
		mv.addObject("idVolverPrev", request.getParameter("idVolverPrev"));
		
		mv.addObject("operacionMasivaFiltro", userSesion.getOperacionMasivaFiltro());
		mv.addObject("operacionMasivaDto", operacionMasivaDto);
		mv.addObject("selectCopropietario", request.getParameter("idCopropietario"));
		mv.addObject("observacionAnterior", userSesion.getOperacionMasivaFiltro().getObservaciones());
		mv.addObject("archivoValidado", operacionMasivaDto.isArchivoValidado());
		mv.addObject("imprimibleArchivo", false);
		
		return mv;
	}
	
	/***************************************************************************
	 * Comprobantes
	 * ************************************************************************/
	
	/**
	 * Metodo que se llama al adjuntar un comprobante
	 * @param request
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adjuntar-comprobante-operacion-masiva")
	public ModelAndView adjuntarComprobanteOperacionMasiva(HttpServletRequest request,
			@ModelAttribute("operacionMasivaDto") @Valid OperacionMasivaDto operacionMasivaDto,
			BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		this.guardarArchivoOperacionMasivaEnDto(operacionMasivaDto, request);
		this.moverSessionComprobante(operacionMasivaDto, operacionMasivaDto.getFileNameOperacionMasiva());
		
		if (result.hasErrors()) {
			
			VistaSoporteOperacionMasivaFiltro operacionMasivaFiltro = new VistaSoporteOperacionMasivaFiltro();
			
			ModelAndView mv = new ModelAndView(OPERACION_MASIVA_ALTA);
			
			operacionMasivaFiltro.setDescripcionComprobante(operacionMasivaDto.getDescripcionComprobante());
			mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);
			mv.addObject("imprimibleArchivo", false);
			mv.addObject(OBJECT_COMMAND, operacionMasivaDto);
			mv.addObject("operacionMasivaFiltro",operacionMasivaFiltro);
			return mv;
		}
		
		String descripcionComprobante = request.getParameter("descripcionComprobante");
		
		MultipartFile file;
		file = operacionMasivaDto.getFileComprobanteModificacion();
		
		ComprobanteDto comprobanteDto = new ComprobanteDto();
		comprobanteDto.setDescripcionArchivo(descripcionComprobante);

		comprobanteDto.setNombreArchivo(file.getOriginalFilename());
		comprobanteDto.setDocumento(file.getBytes());
		
		if(comprobanteDto.getDocumento().length==0){
			ObjectError error = new ObjectError("errorArchivoVacio", "valor.error.archivoVacio");
			result.addError(error);
		}

		operacionMasivaDto.getListaComprobantes().add(comprobanteDto);

		if(Validaciones.isCollectionNotEmpty(operacionMasivaDto.getListaComprobantes())){
			Long i = 0l;
			Iterator<ComprobanteDto> it = operacionMasivaDto.getListaComprobantes().iterator();
			while (it.hasNext()) {
				ComprobanteDto comp = it.next();
				comp.setOrdenTabla(i);
				i++;
			}
		}
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_ALTA);
		operacionMasivaDto.setDescripcionComprobante("");
				
		mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);
		mv.addObject("imprimibleArchivo", false);
		
		return mv;
	}
	
	/**
	 * Metodo que se llama al adjuntar un comprobante en la edicion
	 * @param request
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adjuntar-comprobante-operacion-masiva-edicion")
	public ModelAndView adjuntarComprobanteOperacionMasivaEdicion(HttpServletRequest request,
			@ModelAttribute("operacionMasivaDto") @Valid OperacionMasivaDto operacionMasivaDto,
			BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		this.guardarArchivoOperacionMasivaEnDto(operacionMasivaDto, request);
		this.moverSessionComprobante(operacionMasivaDto, operacionMasivaDto.getFileNameOperacionMasiva());
		actualizarFiltro(operacionMasivaDto, request);
		
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView(OPERACION_MASIVA_EDICION);
			mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);
			mv.addObject("imprimibleArchivo", false);
			mv.addObject("operacionMasivaFiltro", userSesion.getOperacionMasivaFiltro());
			mv.addObject(OBJECT_COMMAND, operacionMasivaDto);
			return mv;
		}
		
		String descripcionComprobante = request.getParameter("descripcionComprobante");
		
		MultipartFile file;
		file = operacionMasivaDto.getFileComprobanteModificacion();
		
		ComprobanteDto comprobanteDto = new ComprobanteDto();
		comprobanteDto.setDescripcionArchivo(descripcionComprobante);

		comprobanteDto.setNombreArchivo(file.getOriginalFilename());
		comprobanteDto.setDocumento(file.getBytes());
		
		if(comprobanteDto.getDocumento().length==0){
			ObjectError error = new ObjectError("errorArchivoVacio", "valor.error.archivoVacio");
			result.addError(error);
		}

		operacionMasivaDto.getListaComprobantes().add(comprobanteDto);

		if(Validaciones.isCollectionNotEmpty(operacionMasivaDto.getListaComprobantes())){
			Long i = 0l;
			Iterator<ComprobanteDto> it = operacionMasivaDto.getListaComprobantes().iterator();
			while (it.hasNext()) {
				ComprobanteDto comp = it.next();
				comp.setOrdenTabla(i);
				i++;
			}
		}
		
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_EDICION);
		
		userSesion.getOperacionMasivaFiltro().setDescripcionComprobante("");
		userSesion.getOperacionMasivaFiltro().setIdCopropietarioEdicion(operacionMasivaDto.getIdCopropietario());
		
		mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);
		mv.addObject("operacionMasivaFiltro", userSesion.getOperacionMasivaFiltro());
		mv.addObject("imprimibleArchivo", false);
		
		return mv;
	}
	
	
	
	@RequestMapping("/operacion-masiva-procesar-eliminar-comprobante")
	public ModelAndView procesarEliminarComprobante(HttpServletRequest request,
			@ModelAttribute("operacionMasivaDto") OperacionMasivaDto operacionMasivaDto, 
			BindingResult result) throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		this.guardarArchivoOperacionMasivaEnDto(operacionMasivaDto, request);
		this.moverSessionComprobante(operacionMasivaDto, operacionMasivaDto.getFileNameOperacionMasiva());
		
		String ordenTablaComprobante = operacionMasivaDto.getOrdenComprobanteSelected();
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_ALTA);
		
		Iterator<ComprobanteDto> it = operacionMasivaDto.getListaComprobantes().iterator();
		while (it.hasNext()) {
			ComprobanteDto comp = it.next();
			if (comp.getOrdenTabla().toString().equals(ordenTablaComprobante)) {
				it.remove();
			}
		}
		
		mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);
		mv.addObject("imprimibleArchivo", false);
		mv.addObject("operacionMasivaDto", operacionMasivaDto);
		return mv;
	}
	
	@RequestMapping("/operacion-masiva-procesar-eliminar-comprobante-edicion")
	public ModelAndView procesarEliminarComprobanteEdicion(HttpServletRequest request,
			@ModelAttribute("operacionMasivaDto") OperacionMasivaDto operacionMasivaDto, 
			BindingResult result) throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		this.guardarArchivoOperacionMasivaEnDto(operacionMasivaDto, request);
		this.moverSessionComprobante(operacionMasivaDto, operacionMasivaDto.getFileNameOperacionMasiva());
		
		String ordenTablaComprobante = operacionMasivaDto.getOrdenComprobanteSelected();
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_EDICION);
		actualizarFiltro(operacionMasivaDto, request);
		
		Iterator<ComprobanteDto> it = operacionMasivaDto.getListaComprobantes().iterator();
		while (it.hasNext()) {
			ComprobanteDto comp = it.next();
			if (comp.getOrdenTabla().toString().equals(ordenTablaComprobante)) {
				it.remove();
			}
		}
		
		
		userSesion.getOperacionMasivaFiltro().setIdCopropietarioEdicion(operacionMasivaDto.getIdCopropietario());
		mv.addObject("operacionMasivaFiltro", userSesion.getOperacionMasivaFiltro());
		mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);
		mv.addObject("imprimibleArchivo", false);
		mv.addObject("operacionMasivaDto", operacionMasivaDto);
		return mv;
	}
		
	@RequestMapping("/operacion-masiva-procesar-abrir-comprobante")
	public ModelAndView procesarAbrirComprobante(HttpServletRequest request, HttpServletResponse res,
			@ModelAttribute("operacionMasivaDto") OperacionMasivaDto operacionMasivaDto, 
			BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		this.guardarArchivoOperacionMasivaEnDto(operacionMasivaDto, request);
		this.moverSessionComprobante(operacionMasivaDto, operacionMasivaDto.getFileNameOperacionMasiva());
		 
		String ordenTablaComprobante = operacionMasivaDto.getOrdenComprobanteSelected();
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_ALTA);
		
		if(Validaciones.isCollectionNotEmpty(operacionMasivaDto.getListaComprobantes())){
			Iterator<ComprobanteDto> it = operacionMasivaDto.getListaComprobantes().iterator();
			while (it.hasNext()) {
				ComprobanteDto comp = it.next();
				if (ordenTablaComprobante.equals(comp.getOrdenTabla().toString())) {
					request.getSession().setAttribute( "documentoImprimible" , comp.getDocumento());
					request.getSession().setAttribute( "nombreDocumento" , comp.getNombreArchivo());
					mv.addObject("imprimibleArchivo", true);	
				}
			}
		}else{
			mv.addObject("imprimibleArchivo", false);
		}
				
		mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);		
		mv.addObject("operacionMasivaDto", operacionMasivaDto);
		return mv;
	}

	@RequestMapping("/operacion-masiva-procesar-abrir-comprobante-edicion")
	public ModelAndView procesarAbrirComprobanteEdicion(HttpServletRequest request, HttpServletResponse res,
			@ModelAttribute("operacionMasivaDto") OperacionMasivaDto operacionMasivaDto, 
			BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		this.guardarArchivoOperacionMasivaEnDto(operacionMasivaDto, request);
		this.moverSessionComprobante(operacionMasivaDto, operacionMasivaDto.getFileNameOperacionMasiva());
		 
		String ordenTablaComprobante = operacionMasivaDto.getOrdenComprobanteSelected();
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_EDICION);
		
		if(Validaciones.isCollectionNotEmpty(operacionMasivaDto.getListaComprobantes())){
			Iterator<ComprobanteDto> it = operacionMasivaDto.getListaComprobantes().iterator();
			while (it.hasNext()) {
				ComprobanteDto comp = it.next();
				if (ordenTablaComprobante.equals(comp.getOrdenTabla().toString())) {
					request.getSession().setAttribute( "documentoImprimible" , comp.getDocumento());
					request.getSession().setAttribute( "nombreDocumento" , comp.getNombreArchivo());
					mv.addObject("imprimibleArchivo", true);	
				}
			}
		}else{
			mv.addObject("imprimibleArchivo", false);
		}
				
		userSesion.getOperacionMasivaFiltro().setIdCopropietarioEdicion(operacionMasivaDto.getIdCopropietario());
		mv.addObject("operacionMasivaFiltro", userSesion.getOperacionMasivaFiltro());
		mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);		
		mv.addObject("operacionMasivaDto", operacionMasivaDto);
		return mv;
	}
	
	/**
	 * Mueve la seccion del comprobante en caso de que la operacion masiva sea DSIST
	 * @param operacionMasivaDto
	 * @param nombreArchivoOperacionMasiva
	 */
	private void moverSessionComprobante(OperacionMasivaDto operacionMasivaDto, String nombreArchivoOperacionMasiva) {
		if (!Validaciones.isNullEmptyOrDash(nombreArchivoOperacionMasiva)
				&& TipoArchivoOperacionMasivaEnum.DSIST.name().equalsIgnoreCase(nombreArchivoOperacionMasiva.split("_")[0])){
			operacionMasivaDto.setMoverSeccionComprobante(true);
		} else {
			operacionMasivaDto.setMoverSeccionComprobante(false);
		}
	}
//	/**
//	 * Para conservar el texto, conservo el file
//	 * @param operacionMasivaDto
//	 * @param request
//	 */
	private void guardarArchivoOperacionMasivaEnDto(OperacionMasivaDto operacionMasivaDto, HttpServletRequest request) throws NegocioExcepcion {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		if (!Validaciones.isObjectNull(operacionMasivaDto.getFileOperacionMasiva())) {
			if (operacionMasivaDto.getFileOperacionMasiva().getContentType().equals("text/plain")) {
				userSesion.setArchivoOperacionMasiva(operacionMasivaDto.getFileOperacionMasiva());
				// u578936 - M.A.Uehara
				// Al parecer Sprint guarda los archivos del upload en un temporario. Cuando se sale del request los borra
				// de tal manera que no se puede almacenar el objeto 
				// userSesion.setArchivoOperacionMasiva(operacionMasivaDto.getFileOperacionMasiva());
				try {
					userSesion.setFileBytes(operacionMasivaDto.getFileOperacionMasiva().getBytes());
					operacionMasivaDto.setFileBytes(userSesion.getFileBytes());
				} catch (IOException e) {
					throw new NegocioExcepcion("Error al procesar el archivo: " + operacionMasivaDto.getFileOperacionMasiva().getOriginalFilename(), e);
				}
				//operacionMasivaDto.setLastFileOperacionMasiva(operacionMasivaDto.getFileOperacionMasiva());
				operacionMasivaDto.setFileNameOperacionMasiva(operacionMasivaDto.getFileOperacionMasiva().getOriginalFilename());
				operacionMasivaDto.setFileNameOperacionMasiva(operacionMasivaDto.getFileOperacionMasiva().getOriginalFilename());
			} else {
				if (operacionMasivaDto.getFileOperacionMasiva().getContentType().equals("application/octet-stream")) {
					Object fileSessionAttribute = userSesion.getArchivoOperacionMasiva();
					
					if (!Validaciones.isObjectNull(fileSessionAttribute)) {
						MultipartFile tempFile = (MultipartFile) fileSessionAttribute;
						operacionMasivaDto.setFileBytes(userSesion.getFileBytes());
						operacionMasivaDto.setFileOperacionMasiva(tempFile);
						operacionMasivaDto.setFileNameOperacionMasiva(tempFile.getOriginalFilename());
						operacionMasivaDto.setFileBytes(userSesion.getFileBytes());
					}
				}
			}
		}
	}
	
	/**
	 * LLamar al carga la pagina de Alta de Operaciones masivas. Carga la lista "motivos",
	 * la lista "empresas" y la lista "segmentos". De haber una sola empresa,
	 * busca los segmentos y los carga en el combo. Luego si hay un solo
	 * segmento, busca los origenes y los carga. Luego si hay un solo origen,
	 * busca los acuerdos, los bancos y las cuentas y carga los respectivos
	 * combos.
	 * 
	 * @param mv
	 * @throws ShivaExcepcion
	 */
	@SuppressWarnings("unchecked")
	private void enviarListasCombosAlCargar(ModelAndView mv,
			UsuarioSesion userSesion, OperacionMasivaDto operacionMasivaDto)
			throws ShivaExcepcion {
		try {
			List<ShvParamEmpresa> listaEmpresas = (List<ShvParamEmpresa>) combosServicio.listarEmpresasPorUsuario(userSesion);
			
			if (listaEmpresas.size() == 1) {
				String idEmpresa = listaEmpresas.get(0).getIdEmpresa();
				List<ShvParamSegmento> listaSegmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(idEmpresa, userSesion);
				
				mv.addObject(SELECT_SEGMENTOS, listaSegmentos);
				if (listaSegmentos.size() == 1) {
					Collection<String> usuariosExcluidos = new ArrayList<String>();
					usuariosExcluidos.add(userSesion.getIdUsuario());
					usuariosExcluidos.add(operacionMasivaDto.getIdAnalista());
					List<UsuarioLdapDto> copropietarios = (List<UsuarioLdapDto>) combosServicio.listarCopropietarioOperacionMasivaPorEmpresaYSegmento(
						idEmpresa,
						listaSegmentos.get(0).getIdSegmento(),
						usuariosExcluidos
					);
					Collections.sort(
							copropietarios,
							new UsuarioLdapDtoComparator()
					);
					mv.addObject(
						SELECT_COPROPIETARIOS,
						copropietarios
					);
							
					
					mv.addObject("comboCopropietario", true);
					operacionMasivaDto.setComboSegmento(false);
				} else {
					operacionMasivaDto.setComboSegmento(true);
				}
				operacionMasivaDto.setComboEmpresa(false);
				
			} else {
				
				operacionMasivaDto.setComboEmpresa(true);
				cargarSegmento(mv, operacionMasivaDto, userSesion);
			}
			
			//Listar Empresas
			mv.addObject(SELECT_EMPRESAS, listaEmpresas);
			
			//Listar motivos para operaciones maisvas
			List<ShvParamMotivoCobro> listaMotivosOperacionMasiva = (List<ShvParamMotivoCobro>) combosServicio.listarMotivosOperacionMasiva();
			mv.addObject(SELECT_MOTIVOS, listaMotivosOperacionMasiva);

		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Metodo Generico que me permite armar los combos
	 * @param mv
	 * @param userSesion
	 * @param operacionMasivaDto
	 * @return
	 * @throws Exception
	 */
	private ModelAndView cargarModelAndView (ModelAndView mv,UsuarioSesion userSesion,
			OperacionMasivaDto operacionMasivaDto) throws Exception{
		
		mv.addObject(OBJECT_COMMAND, operacionMasivaDto);
		enviarListasCombosAlCargar(mv, userSesion, operacionMasivaDto);
		armarComboCopropietario(mv, operacionMasivaDto);
		
		//mv.addObject("imprimibleArchivo", false);
		return mv;
	}
	
	/**
	 * Carga el combo Copropietario. Siempre habilita la opcion
	 * "Seleccione un item..."
	 * 
	 * @param mv
	 * @param operacionMasivaDto
	 */
	private void armarComboCopropietario(ModelAndView mv,
			OperacionMasivaDto operacionMasivaDto) throws NegocioExcepcion 
	{
		if (!Validaciones.isNullOrEmpty(operacionMasivaDto.getIdSegmento())) {

			Collection<String> usuariosExcluidos = new ArrayList<String>();
			usuariosExcluidos.add(operacionMasivaDto.getIdAnalista());

			List<UsuarioLdapDto> copropietarios = (List<UsuarioLdapDto>) combosServicio.listarCopropietarioOperacionMasivaPorEmpresaYSegmento(
				operacionMasivaDto.getIdEmpresa(),
				operacionMasivaDto.getIdSegmento(),
				usuariosExcluidos
			);
			Collections.sort(
				copropietarios,
				new UsuarioLdapDtoComparator()
			);
			mv.addObject(SELECT_COPROPIETARIOS, copropietarios);
		}
	}
	
	/**
	 * Método para ir a la pagina de busqueda de operaciones masivas.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/operacion-masiva-busqueda")
	public ModelAndView operacionMasivaBusqueda(HttpServletRequest request, OperacionMasivaDto operacionMasivaDto) throws Exception {
		if (Validaciones.isObjectNull(operacionMasivaDto)) {
			operacionMasivaDto = new OperacionMasivaDto();
		}
		
		// Cargo analista logueado
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		operacionMasivaDto.setIdAnalista(userSesion.getUsuario());
		operacionMasivaDto.setNombreAnalista(userSesion.getNombreCompleto());
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_BUSQUEDA);
		
		if (!Validaciones.isNullOrEmpty(request.getParameter("idVolver")) || "/regresar-bandeja-de-entrada".equals(request.getParameter("fbd_volver"))) {
			
			if(request.getParameter("idOperacionMasiva2") != null){
				
				VistaSoporteOperacionMasivaFiltro filtroOpMas = new VistaSoporteOperacionMasivaFiltro();
				filtroOpMas.setIdOperacionMasiva(request.getParameter("idOperacionMasiva2"));
				filtroOpMas.setIdEmpresa("TA");
				userSesion.setOperacionMasivaFiltro(filtroOpMas);
			}
			userSesion.setVolviendoABusqueda(true);
		}
		
		if(Validaciones.isNullOrEmpty(request.getParameter("volver")) && !("/regresar-bandeja-de-entrada".equals(request.getParameter("fbd_volver")))){
			userSesion.setVolviendoABusqueda(false);
		}
		if (userSesion.getVolviendoABusqueda()) {
			mv.addObject("volviendoABusqueda", true);  	//para volver a busqeuda, que setee TRUE userSesion.getVolviendoABusqueda() y que guarde el ultimo filtro en userSesion asi aca lo toma.
			mv.addObject("operacionMasivaBusquedaFiltro", userSesion.getOperacionMasivaFiltro());
		}else{
			mv.addObject("volviendoABusqueda", false);
		}
		
//		if(!userSesion.getVolviendoABusqueda()){
//				userSesion.setVolviendoABusqueda(true);
////				mv.addObject("idOperacionMasiva", idOperacionMasiva2);
//		}
		
		mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);
		
		mv.addObject("estados", EstadoOperacionMasivaEnum.values());
		mv.addObject("tiposOperacionesMasivas", TipoArchivoOperacionMasivaEnum.values());
		mv.addObject("imprimibleArchivo", false);
		return mv;
	}	
	
	/**
	 * Devuelve una lista de analistas segun el segmento seleccionado
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "operacion-masiva-busqueda/buscarAnalistaOperacionesMasivas", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<SelectOptionJsonResponse> buscarAnalistaOperacionesMasivas(HttpServletRequest request) throws Exception {
		String idEmpresa = request.getParameter("idEmpresa");
		String idSegmento = request.getParameter("idSegmento");
		List<SelectOptionJsonResponse> result = new ArrayList<SelectOptionJsonResponse>();
		List<UsuarioLdapDto> listaDeAnalistasCobranza = 
				buscarUsuariosPorPerfilSegmento(idEmpresa, idSegmento,TipoPerfilEnum.ANALISTA_COBRANZA.nombreLdap());
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
		List<UsuarioLdapDto> listaDeAnalistasOperacionMasiva = 
				buscarUsuariosPorPerfilSegmento(idEmpresa, idSegmento,TipoPerfilEnum.ANALISTA_OPERACION_MASIVA.nombreLdap());
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
	private List<UsuarioLdapDto> buscarUsuariosPorPerfilSegmento(
			String empresa, String segmento, String perfil)
			throws LdapExcepcion {
		String perfilABuscar;
		List<UsuarioLdapDto> listaAnalistaFiltro = new ArrayList<UsuarioLdapDto>();
		if (Constantes.TODOS_LOS_SEGMENTOS.equals(segmento)) {
			perfilABuscar = perfil + "_" + empresa;
		} else {
			perfilABuscar = perfil + "_" + empresa + "_" + segmento;
		}
		Collection<UsuarioLdapDto> listaLdap = ldapServicio
				.buscarUsuariosPorPerfilEnMemoria(perfilABuscar);

		for (UsuarioLdapDto usuario : listaLdap) {
			listaAnalistaFiltro.add(usuario);
		}
		return listaAnalistaFiltro;
	}
	
	@RequestMapping(value = "operacion-masiva-busqueda/buscarSegmentosOperacionesMasivas", method = RequestMethod.POST, produces = "application/json")
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
	 * Busqueda de Operaciones Masivas
	 * @param request
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "operacion-masiva-busqueda/buscar-OperacionesMasivas", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody OperacionesMasivasJsonResponse buscarOperacionesMasivas(@RequestBody VistaSoporteOperacionMasivaFiltro operacionMasivaFiltro, HttpServletRequest request) throws Exception {

		OperacionesMasivasJsonResponse rta = new OperacionesMasivasJsonResponse();
		
		
		try {
			
			UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
			
			
			
			if(userSesion.getVolviendoABusqueda()){
				if(userSesion.getOperacionMasivaFiltro()!=null){
					operacionMasivaFiltro = userSesion.getOperacionMasivaFiltro();
				}
				userSesion.setVolviendoABusqueda(false);
			}else{
				operacionMasivaFiltro.setUsuarioLogeado(userSesion);
				userSesion.setOperacionMasivaFiltro(operacionMasivaFiltro);
			}
			
			if(!Validaciones.isNullOrEmpty(request.getParameter("opMas")))
			{
				operacionMasivaFiltro.setIdOperacionMasiva(request.getParameter("opMas"));
			}


			
			
			operacionMasivaValidacionServicio.validarFiltroBusquedaOperacionesMasivas(operacionMasivaFiltro);
			List<OperacionMasivaDto> listaOperacionesMasivas = new ArrayList<OperacionMasivaDto>();
			
			listaOperacionesMasivas = operacionMasivaServicio.listarOperacionesMasivas(operacionMasivaFiltro);

			for (OperacionMasivaDto operacionMasivaDto : listaOperacionesMasivas) {
				if (userSesion.getIdUsuario().equals(operacionMasivaDto.getIdAnalista())
						|| userSesion.getIdUsuario().equals(operacionMasivaDto.getIdCopropietario())
						|| (userSesion.getEsPerfilSupervisorOperacionMasiva() && userSesion.getIdSegmentos().contains(operacionMasivaDto.getSegmento()))){
					operacionMasivaDto.setMostrarBotonModificar(validaModificar(operacionMasivaDto));
				} else {
					operacionMasivaDto.setMostrarBotonModificar(false);
				}

				if (userSesion.getIdUsuario().equals(operacionMasivaDto.getIdAnalista())
						|| userSesion.getIdUsuario().equals(operacionMasivaDto.getIdCopropietario())){
					operacionMasivaDto.setMostrarBotonAnular(validaAnular(operacionMasivaDto));
				}else {
					operacionMasivaDto.setMostrarBotonAnular(false);
				}	
				
				operacionMasivaDto.setMostrarBotonVerCobro(validaVerCobro(operacionMasivaDto));
				
					
			}
			
			Utilidad.guionesNull(listaOperacionesMasivas);
			rta.setAaData(listaOperacionesMasivas);
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
	

	public ILdapServicio getLdapServicio() {
		return ldapServicio;
	}

	public void setLdapServicio(ILdapServicio ldapServicio) {
		this.ldapServicio = ldapServicio;
	}
	/**
	 * @author U572487 Guido.Settecerze
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/autorizacion-aprobacion-operacion-masiva")
	public ModelAndView autorizacionAprobacionOperacionMasiva(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(VALOR_AUTORIZACION_OK_VIEW);
		OperacionMasivaDto operacionMasivaDto = operacionMasivaServicio.buscarOperacionMasiva(Utilidad.stringToBigDecimal(request.getParameter("idOperacionMasiva")).longValue());
		String observacion = request.getParameter("observacion");
		if (Validaciones.isNullOrEmpty(observacion)) {
			observacion = Utilidad.EMPTY_STRING;
		}
		
		operacionMasivaServicio.aprobarOperacionMasivaCambiarEstadoWorkFlow(operacionMasivaDto, userSesion, observacion);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));

		mv.addObject("url",BANDEJA_ENTRADA_VIEW_POST);

		return mv;
	}
	
	/**
	 * Metodo que carga la pantalla de operacion-masiva-detalle-aprobacion DETALLE
	 * Pasar por parametro a traves del request el "idOperacion" y "opcion" en D.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/operacion-masiva-detalle-aprobacion")
	public ModelAndView operacionMasivaDetalleAprobacion(HttpServletRequest request) throws Exception {
		ModelAndView mv=new ModelAndView(OPERACION_MASIVA_APROBACION);
		OperacionMasivaDto operacionMasivaDto = new OperacionMasivaDto();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		if(!Validaciones.isNullOrEmpty(request.getParameter("idOperacionMasiva"))) {
			operacionMasivaDto = operacionMasivaServicio.buscarOperacionMasiva(Utilidad.stringToBigDecimal(request.getParameter("idOperacionMasiva")).longValue());
			mv.addObject("volviendoA", "busqueda");
			mv.addObject("idOperacionMasiva", request.getParameter("idOperacionMasiva"));
		}
		
		//logica botones "volver". Brian
		if("D".equals(request.getParameter("opcion"))){
			if(!("").equals(request.getParameter("idVolverPrev")) && !Validaciones.isObjectNull(request.getParameter("idVolverPrev"))) {
				mv.addObject("idVolver", request.getParameter("idVolverPrev"));
				mv.addObject("idVolverPrev", request.getParameter("idVolverPrev2"));
				mv.addObject("idCobro", request.getParameter("idCobro"));
			}else if(!Validaciones.isObjectNull(request.getParameter("idVolver"))) {
				mv.addObject("idVolver", request.getParameter("volver"));
				mv.addObject("idVolverPrev", request.getParameter("idVolver"));
				mv.addObject("idCobro", request.getParameter("idCobro"));
			}else {
				userSesion.setVolviendoABusqueda(true);
				mv.addObject("idVolver", request.getParameter("volver")); 
			}
		}else {
			userSesion.setVolviendoABusqueda(false);
		}
		
		if(!Validaciones.isObjectNull(operacionMasivaDto)){
			if(!Validaciones.isNullOrEmpty(request.getParameter("opcion"))){
				mv.addObject("detalleDoAprobacionA", request.getParameter("opcion"));
			}
			if(!Validaciones.isNullOrEmpty(request.getParameter("vuelvoBandeja"))){
				mv.addObject("vuelvoBandeja", "NO");
			}

			OperacionMasivaArchivoDto archivoDto = operacionMasivaServicio.buscarListaComprobantesXIdArchivo(request.getParameter("idOperacionMasiva"));
			
			List<ArchivoOperacionMasivaProcesadoDto> listaArchivo = new ArrayList<ArchivoOperacionMasivaProcesadoDto>();
			ArchivoOperacionMasivaProcesadoDto archivoProcesadoDto = new ArchivoOperacionMasivaProcesadoDto();
			
			archivoProcesadoDto.setIdOperacionMasiva(operacionMasivaDto.getIdOperacionMasiva());
			archivoProcesadoDto.setNombreArchivo(archivoDto.getNombreArchivo());
			archivoProcesadoDto.setTipoOperacion(operacionMasivaDto.getTipoOperacionMasiva().getDescripcion());
			archivoProcesadoDto.setCantRegistros(archivoDto.getCantidadRegistros().intValue());
			archivoProcesadoDto.setEstado(operacionMasivaDto.getDescripcionEstado());
			archivoProcesadoDto.setImporte(Utilidad.formatCurrency(archivoDto.getImporteTotal(), 2));
			
			archivoProcesadoDto.setIdAnalista(operacionMasivaDto.getIdAnalista());
			archivoProcesadoDto.setNombreAnalista(operacionMasivaDto.getNombreAnalista());
			archivoProcesadoDto.setMailAnalista(operacionMasivaDto.getMailAnalista());
			archivoProcesadoDto.setUrlFotoAnalista(operacionMasivaDto.getUrlFotoAnalista());
			
			archivoProcesadoDto.setIdCopropietario(operacionMasivaDto.getIdCopropietario());
			archivoProcesadoDto.setNombreCopropietario(operacionMasivaDto.getNombreCopropietario());
			archivoProcesadoDto.setMailCopropietario(operacionMasivaDto.getMailCopropietario());
			archivoProcesadoDto.setUrlFotoCopropietario(operacionMasivaDto.getUrlFotoCopropietario());
			
			archivoProcesadoDto.setMotivo(operacionMasivaDto.getMotivo());
			
			listaArchivo.add(archivoProcesadoDto);
			operacionMasivaDto.setArchivosPendientes(listaArchivo);
			
			if(!Validaciones.isObjectNull(archivoProcesadoDto)){
				mv.addObject("nombreArchivo", archivoProcesadoDto.getNombreArchivo());
			}
			
			
			mv.addObject("prevEmpresa", operacionMasivaDto.getEmpresa());
			mv.addObject("prevSegmento", operacionMasivaDto.getSegmento());
			mv.addObject("nombreCompletoUsuario", operacionMasivaDto.getNombreAnalista());
			mv.addObject("prevCopropietario", operacionMasivaDto.getNombreCopropietario());
			mv.addObject("prevMotivo", operacionMasivaDto.getMotivoOMFormateado(operacionMasivaDto.getIdMotivo()));
			mv.addObject("idOperacionMasiva", operacionMasivaDto.getIdOperacionMasiva());
			mv.addObject("listaComprobantes", operacionMasivaDto.getListaComprobantes());
			mv.addObject("listaArchivosPendientes", ((ArrayList<ArchivoOperacionMasivaProcesadoDto>) Utilidad.guionesNull(new ArrayList<>(operacionMasivaDto.getArchivosPendientes()))));
			if(!Validaciones.isObjectNull(operacionMasivaDto.getEstadoOperacionMasiva())){
				mv.addObject("estadoOpeMas", operacionMasivaDto.getEstadoOperacionMasiva().descripcion());
			}

			String observacionAnterior = operacionMasivaServicio.obtenerObseHistorial(operacionMasivaDto, null);
			if (!Validaciones.isNullOrEmpty(observacionAnterior)) {
				observacionAnterior = Utilidad.formateadoVista(
						observacionAnterior
						);
			}

			
			if("D".equals(request.getParameter("opcion"))){
				if(!Validaciones.isNullEmptyOrDash(observacionAnterior)){
					mv.addObject("prevObservText2", observacionAnterior);
				}else{
					mv.addObject("prevObservText2", operacionMasivaDto.getObservacion());
				}
				mv.addObject("mostrarObservTextAnterior", false);
			}else{
				if (!Validaciones.isNullEmptyOrDash(observacionAnterior)) {
					mv.addObject("prevObservTextAnterior", observacionAnterior);
				}
				mv.addObject("mostrarObservTextAnterior", true);
			}
			mv.addObject("operacionMasivaDto", operacionMasivaDto);
		}

		return mv;
	}
	
	/**
	 * @author U572487 Guido.Settecerze
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/operacion-masiva-cargar-busqueda")
	public ModelAndView operacionMasivaBusquedaCargarBusqueda(HttpServletRequest request)
			throws Exception {

		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_BUSQUEDA);
		
		/*
		 * Valida si es una busqueda normal o vuelve de un detallado 
		 */
		if (!Validaciones.isNullOrEmpty(request.getParameter("idVolver"))) {
			userSesion.setVolviendoABusqueda(true);
		} else if (!Validaciones.isNullOrEmpty(request.getParameter("idTarea"))) {
			// valida si es una busqueda lanzada desde la bandeja de entrada
			VistaSoporteCobroOnlineFiltro cobroFiltro = new VistaSoporteCobroOnlineFiltro();
			cobroFiltro.setIdOpCobro(request.getParameter("idOperacionTarea"));
			cobroFiltro.setIdEmpresa(request.getParameter("empresaTarea"));
			// cobro-busqueda se tiene que comportar igual que cuando se vuelve desde 
			// el detalle de un cobro o de la edicion de un cobro
			userSesion.setVolviendoABusqueda(true);
			userSesion.setCobroFiltro(cobroFiltro);
			userSesion.getCaminoDeVuelta().push("regresar-bandeja-de-entrada");
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
		List<ShvParamEmpresa> empresas = (List<ShvParamEmpresa>) combosServicio
				.listarEmpresasPorUsuario(userSesion);
		List<ShvParamSegmento> segmentos = new ArrayList<ShvParamSegmento>();

		if (empresas.size() == 1) {
			ShvParamEmpresa empresa = empresas.get(0);
			segmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(
					empresa.getIdEmpresa(), userSesion);
		}

		//cargarComboParaEstadosCobro(mv); // Pertenece a Controlador.java y Carga
										 // listaEstados

		List<String> listaEstados = listarEstadosParaBusquedaOperacionMasiva();
		
		List<ShvParamMotivoCobro> listaMotivos = (List<ShvParamMotivoCobro>) combosServicio.listarMotivosOperacionMasiva();
		
		HashSet<TipoCreditoEnum> listaMediosDePagoSinRepetidosFinal = new HashSet<TipoCreditoEnum>();
		HashSet<String> descripcionEnums = new HashSet<String>();
		List<TipoCreditoEnum> listaMediosDePago = Arrays
				.asList(TipoCreditoEnum.values());
		for (TipoCreditoEnum tipoCreditoEnum : listaMediosDePago) {
			descripcionEnums.add(tipoCreditoEnum.getDescripcion());
		}		
		for (String descripcionEnum : descripcionEnums) {
			listaMediosDePagoSinRepetidosFinal.add(TipoCreditoEnum.getEnumByDescripcion(descripcionEnum));
		}
		
		/*
		 * AGREGO LISTAS AL MV
		 */

		mv.addObject("empresas", empresas);
		mv.addObject("comboEmpresa",
				(empresas.size() == 0 || empresas.size() > 1));
		mv.addObject("segmentos", segmentos);
		mv.addObject("comboSegmento",
				(segmentos.size() == 0 || segmentos.size() > 0));
		mv.addObject("estados",listaEstados);
		mv.addObject("comboEstado", (listaEstados.size() == 0 || listaEstados.size() > 1));
		mv.addObject("motivos", listaMotivos);
		mv.addObject("comboMotivo",
				(listaMotivos.size() == 0 || listaMotivos.size() > 1));
		mv.addObject("mediosPago", listaMediosDePagoSinRepetidosFinal);
		mv.addObject("comboTMP",
				(listaMediosDePagoSinRepetidosFinal.size() == 0 || listaMediosDePagoSinRepetidosFinal.size() > 1));
		mv.addObject("criterioBusquedaCliente", Arrays.asList(CriterioBusquedaClienteEnum.values()));


		return mv;

	}
	
	/**
	 * @author U572487 Guido.Settecerze
	 * @return
	 */
	private List<String> listarEstadosParaBusquedaOperacionMasiva(){

		String anulada = "Anulada";
		String enProceso = "En Proceso";
		String pendienteAprobacion = "Pendiente de aprobación";
		String rechazado = "Rechazado";
		String cobrado = "Cobrado";
		String conError = "En Error";
		String finalizada = "Finalizada";

		List<String> lista = new ArrayList<String>();
		lista.add(anulada);
		lista.add(enProceso);
		lista.add(finalizada);
		lista.add(pendienteAprobacion);
		lista.add(rechazado);
		lista.add(cobrado);
		lista.add(conError);

		return lista;
	}
	
	/**
	 * @author U572487 Guido.Settecerze
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rechazar-aprobacion-operacion-masiva")
	public ModelAndView rechazarAprobacionOperacionMasiva(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		ModelAndView mv = new ModelAndView(VALOR_AUTORIZACION_OK_VIEW);
		String nombreArchivo = request.getParameter("nombreArchivo");
		String observacion = request.getParameter("observacion");
		OperacionMasivaDto operacionMasivaDto = operacionMasivaServicio.buscarOperacionMasiva(Utilidad.stringToBigDecimal(request.getParameter("idOperacionMasiva")).longValue());
		operacionMasivaDto.setNombreArchivo(nombreArchivo);
		
		operacionMasivaServicio.rechazarAprobacionOperacionMasivaCambiarEstadoWorkFlow(operacionMasivaDto, userSesion, observacion);
		
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		mv.addObject("url",BANDEJA_ENTRADA_VIEW_POST);
		
		return mv;
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/descargarComprobanteOperacionMasiva")
	public void cobroDetalleDescargarComprobante(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		ComprobanteDto comprobante = operacionMasivaServicio.buscarAdjuntoOperacionMasiva(Long.valueOf(id));
		
		if (comprobante!=null) {
			byte[] file = comprobante.getDocumento();
			String fileName = comprobante.getNombreArchivo();
			ControlArchivo.descargarComoArchivo(file, fileName, response);
		}

	}
	
	
	/**
	 * Sprint 8
	 * Eliminar / Anular tarea desde la bandeja de entrada del Analista Cobranza.
	 * @author 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/operacion-masiva-setear-archivo-validado")
	public ModelAndView operacionMasivaSetearArchivoValidado(HttpServletRequest request, OperacionMasivaDto operacionMasivaDto) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		operacionMasivaDto.setArchivoValidado(false);
		userSesion.setArchivoValidado(false);
		userSesion.setActivarBoton(true);
		operacionMasivaDto.setArchivosPendientes(null);
		operacionMasivaDto.setResultadoValidaciones(null);
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_EDICION);
		mv = cargarModelAndView (mv, userSesion, operacionMasivaDto);
		actualizarFiltro(operacionMasivaDto, request);
		mv.addObject("volver", request.getParameter("volver"));
		mv.addObject("idVolver", request.getParameter("idVolver"));
		mv.addObject("idVolverPrev", request.getParameter("idVolverPrev"));
		mv.addObject("operacionMasivaFiltro", userSesion.getOperacionMasivaFiltro());
		mv.addObject("observacionAnterior", !Validaciones.isObjectNull(userSesion.getOperacionMasivaFiltro()) ? userSesion.getOperacionMasivaFiltro().getObservaciones() : Utilidad.EMPTY_STRING);
		mv.addObject("imprimibleArchivo", false);
		mv.addObject("archivoValidado", false);
		return mv;
	}
	
	/**
	 * Sprint 8
	 * Eliminar / Anular tarea desde la bandeja de entrada del Analista Cobranza.
	 * @author u572487 Guido.Settecerze
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/operacion-masiva-eliminar-tarea")
	public ModelAndView operacionMasivaConfiguracionEliminarTarea(HttpServletRequest request) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String idOperacionMasiva = request.getParameter("idOperacionMasiva");
		
		operacionMasivaServicio.anularTarea(Long.parseLong(idOperacionMasiva), userSesion);
		
		ModelAndView mv = new ModelAndView(ACTUALIZACION_OK_VIEW);
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("eliminarTareaOK.operacion.masiva.mensaje"));
		mv.addObject("url","bandeja-de-entrada");
		return mv;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operacion-masiva-historial")
	public ModelAndView operacionMasivaHistorial(HttpServletRequest request) throws Exception {
				
		ModelAndView mv = new ModelAndView(OPERACION_MASIVA_HISTORIAL_VIEW);
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		OperacionMasivaDto operacionDto = operacionMasivaServicio.buscarOperacionMasiva(new Long(request.getParameter("idOperacionMasiva")));
		OperacionMasivaArchivoDto archivoDto = operacionMasivaServicio.buscarListaComprobantesXIdArchivo(request.getParameter("idOperacionMasiva"));
		
		List<OperacionMasivaHistoricaDto> hist = operacionMasivaServicio.obtenerHistorialOperacionMasiva(request.getParameter("idOperacionMasiva"));
		//logica botones "volver". Brian
		if(!("").equals(request.getParameter("idVolverPrev")) && !Validaciones.isObjectNull(request.getParameter("idVolverPrev")))  {
			mv.addObject("idVolver", request.getParameter("volver"));
			mv.addObject("idVolverPrev", request.getParameter("idVolver"));
			mv.addObject("idVolverPrev2", request.getParameter("idVolverPrev"));
			mv.addObject("idCobro", request.getParameter("idCobro"));
		}else if(!("").equals(request.getParameter("idVolver")) && !Validaciones.isObjectNull(request.getParameter("idVolver"))){
			mv.addObject("idVolver", request.getParameter("volver"));
			mv.addObject("idVolverPrev", request.getParameter("idVolver"));
		}else {
			userSesion.setVolviendoABusqueda(true);
			mv.addObject("idVolver", request.getParameter("volver")); 
		}
		
		mv.addObject("idOperacionMasiva", request.getParameter("idOperacionMasiva"));
		mv.addObject("nombreArchivo", archivoDto.getNombreArchivo());
		mv.addObject("tipoOperacion", operacionDto.getTipoOperacionMasiva().getDescripcion());
		mv.addObject("listaHistorialOperacionMasivaDto", Utilidad.guionesNull(hist));

		return mv;
	}
	
	
	
//	/**
//	 * Sprint 5   --- u578936 Subio al Controller
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	private @ResponseBody List<SelectOptionJsonResponse> buscarCopropietarios(String idEmpresa, String idSegmento, Collection<String> usuariosExcluidos) throws Exception {
//		
//		List<SelectOptionJsonResponse> result = new ArrayList<SelectOptionJsonResponse>();
//		SelectOptionJsonResponse jsonResp = null;
//		for (UsuarioLdapDto copropietario : combosServicio.listarCopropietarioOperacionMasivaPorEmpresaYSegmento(idEmpresa, idSegmento, usuariosExcluidos)) {
//			jsonResp = new SelectOptionJsonResponse();
//			jsonResp.setValue(copropietario.getTuid());
//			jsonResp.setText(copropietario.getNombreCompleto());
//			result.add(jsonResp);
//		}
//		return result;
//	}
	
	
	/**
	 * Guardo una operacion masiva en sesion
	 * @author u579607 Brian.Keller
	 * @param operacion masiva
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="operacion-masiva/guardoOperacionMasivaEnSesion", method=RequestMethod.POST)
	public @ResponseBody JsonResponse guardoOperacionMasivaEnSesion(@RequestBody final VistaSoporteOperacionMasivaFiltro operacionMasivaFiltro , HttpServletRequest request) throws Exception {
		
		JsonResponse jsonResponse = new JsonResponse();
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		if(!Validaciones.isObjectNull(operacionMasivaFiltro)){
			//userSesion.setOperacionMasivaFiltro(operacionMasivaFiltro);
			userSesion.getOperacionMasivaFiltro().setIdSegmento(operacionMasivaFiltro.getIdSegmento());
			userSesion.getOperacionMasivaFiltro().setIdCopropietarioEdicion(operacionMasivaFiltro.getIdCopropietarioEdicion());
			userSesion.getOperacionMasivaFiltro().setIdMotivoEdicion(operacionMasivaFiltro.getIdMotivoEdicion());
			userSesion.getOperacionMasivaFiltro().setDescripcionComprobante(operacionMasivaFiltro.getDescripcionComprobante());
			userSesion.getOperacionMasivaFiltro().setObservaciones(operacionMasivaFiltro.getObservaciones());
			jsonResponse.setSuccess(true);
		}
		return jsonResponse;
		
		
	}
	
	/**
	 * 
	 * @param operacionMasivaDto
	 * @return
	 */
	private boolean validaModificar(OperacionMasivaDto operacionMasivaDto) {
		boolean result = false;
		if (Estado.MAS_ERROR.equals(operacionMasivaDto.getEstadoOperacionMasiva()) 
				|| Estado.MAS_RECHAZADA.equals(operacionMasivaDto.getEstadoOperacionMasiva())){
			result = true;
		}
		return result;
	}
	
	/**
	 * 
	 * @param operacionMasivaDto
	 * @return
	 */
	private boolean validaAnular(OperacionMasivaDto operacionMasivaDto) {
		boolean result = false;
		if (Estado.MAS_PENDIENTE_PROCESAR.equals(operacionMasivaDto.getEstadoOperacionMasiva()) 
				|| Estado.MAS_RECHAZADA.equals(operacionMasivaDto.getEstadoOperacionMasiva()) 
				|| Estado.MAS_ERROR.equals(operacionMasivaDto.getEstadoOperacionMasiva()))
		{
			result = true;
		}
		return result;
	}
	
	/**
	 * 
	 * @param operacionMasivaDto
	 * @return
	 */
	private boolean validaVerCobro(OperacionMasivaDto operacionMasivaDto) {
		boolean result = false;
		if (Estado.MAS_IMPUTADA.equals(operacionMasivaDto.getEstadoOperacionMasiva()) 
				|| Estado.MAS_PARCIALMENTE_IMPUTADA.equals(operacionMasivaDto.getEstadoOperacionMasiva()))
		{
			result = true;
		}
		return result;
	}
	
	/**
	 * Spring 8
	 * Anula operacion masiva, se accede desde la pantalla de busqueda de Operacion Masiva
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/operacion-masiva-anular")
	public ModelAndView operacionMasivaAnular(HttpServletRequest request, @ModelAttribute("operacionMasivaDto") OperacionMasivaDto operacionMasivaDto) throws Exception {

		ModelAndView mv = new ModelAndView(ACTUALIZACION_EXITOSA_VIEW);
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		String idOperacionMasiva = request.getParameter("idOperacionMasiva");
		if(request.getParameter("idOperacionTarea") != null){
			idOperacionMasiva = request.getParameter("idOperacionTarea");
		}
		
		operacionMasivaServicio.anularOperacionMasiva(idOperacionMasiva, userSesion.getIdUsuario());
		
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		mv.addObject("url", "operacion-masiva-busqueda?idVolver=busqueda&volver=true");
		return mv;
	}
	
	
	@RequestMapping("/operacion-masiva-descargar")
	public void operacionMasivaDescargar(HttpServletRequest request, @ModelAttribute("operacionMasivaDto") OperacionMasivaDto operacionMasivaDto,HttpServletResponse response) throws Exception {
		
		
		Long idOperacionMasiva = operacionMasivaDto.getIdOperacionMasiva();
		
		ShvMasOperacionMasivaArchivo archivoModelo = operacionMasivaServicio.buscarArchivoOperacionMasivaModelo(idOperacionMasiva);
		String contenido = operacionMasivaServicio.generarArchivoOperacionMasivaRespuesta(idOperacionMasiva);
		
		ControlArchivo.descargarTextoComoArchivo(contenido.getBytes("UTF-8"), archivoModelo.getNombreArchivo(), response);         
	}
	
	void actualizarFiltro(OperacionMasivaDto operacionMasivaDto,HttpServletRequest request){
		
		
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		
		userSesion.getOperacionMasivaFiltro().setIdEmpresa(operacionMasivaDto.getIdEmpresa());
		userSesion.getOperacionMasivaFiltro().setIdSegmento(operacionMasivaDto.getIdSegmento());
		userSesion.getOperacionMasivaFiltro().setIdCopropietarioEdicion(operacionMasivaDto.getIdCopropietario());
		userSesion.getOperacionMasivaFiltro().setIdMotivoEdicion(operacionMasivaDto.getIdMotivo());
		userSesion.getOperacionMasivaFiltro().setDescripcionComprobante(operacionMasivaDto.getDescripcionComprobante());
		userSesion.getOperacionMasivaFiltro().setObservaciones(operacionMasivaDto.getObservacionAnterior());
		
		
		
	}
	
	/************************************************************************************/
	/** Getter & setter
	/************************************************************************************/
	

	public IOperacionMasivaServicio getOperacionMasivaServicio() {
		return operacionMasivaServicio;
	}

	public OperacionMasivaValidacionWeb getOperacionMasivaValidacionWeb() {
		return operacionMasivaValidacionWeb;
	}

	public void setOperacionMasivaValidacionWeb(
			OperacionMasivaValidacionWeb operacionMasivaValidacionWeb) {
		this.operacionMasivaValidacionWeb = operacionMasivaValidacionWeb;
	}

	public void setOperacionMasivaServicio(
			IOperacionMasivaServicio operacionMasivaServicio) {
		this.operacionMasivaServicio = operacionMasivaServicio;
	}
		
}
