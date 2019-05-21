/**
 * 
 */
package ar.com.telecom.shiva.presentacion.controlador;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ConcurrenciaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.dto.DepositoDto;
import ar.com.telecom.shiva.negocio.dto.InterdepositoDto;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.negocio.dto.TransferenciaDto;
import ar.com.telecom.shiva.negocio.servicios.IRegistroAVCServicio;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.validacion.RegistroAvcValidacionWeb;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.CurrencyEditor;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.CustomBindingErrorProcessor;
import ar.com.telecom.shiva.presentacion.bean.validacion.editor.IntegerEditor;

/**
 * @author pablo.m.ibarrola
 *
 */
@Controller
public class RegistroAvcController extends Controlador {

	private static final String EDITAR_REGISTRO_AVC_DETALLE_VIEW = "conciliacion/conciliacion-editar-registro-avc-detalle";
	private static final String CONFIRMAR_ANULACION_REGISTRO_AVC_VIEW = "conciliacion/conciliacion-confirmar-anulacion-registro-avc";
	private static final String PAGINA_DEFAULT = "bandeja-de-entrada";
	
	private static final String TRANSFERENCIA_DTO = "transDto";
	
	@Autowired
	private RegistroAvcValidacionWeb registroAvcValidacionWeb;
	@Autowired
	private IRegistroAVCServicio registroAVCServicio;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.setBindingErrorProcessor(new CustomBindingErrorProcessor());

		DateFormat dateFormat = new SimpleDateFormat(Utilidad.DATE_FORMAT);
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
		binder.registerCustomEditor(BigDecimal.class, new CurrencyEditor());
		binder.registerCustomEditor(Integer.class, new IntegerEditor());

		if (binder.getTarget() instanceof RegistroAVCDto ){
			binder.setValidator(registroAvcValidacionWeb);
		}

	}
	
	/**
	 * Método para aceptar en la pagina de Editar Transferencia.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/conciliacion-aceptar-modificacion")
	public ModelAndView aceptarModificacion(HttpServletRequest request, 
			@ModelAttribute("transDto") @Valid TransferenciaDto transferenciaDto, BindingResult result) throws Exception {
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		userSesion.setVolviendoAResultadoConciliacion(true);
		
		if(result.hasErrors()){
			if (Utilidad.getErrorGeneral(result)!=null) {
				throw new ShivaExcepcion(Utilidad.getErrorGeneral(result));
			}else{
				ModelAndView mv=new ModelAndView(EDITAR_REGISTRO_AVC_DETALLE_VIEW);
				mv.addObject(TRANSFERENCIA_DTO,transferenciaDto);
				mv.addObject("imprimibleArchivo", false);
				return mv;
			}
		}
		
		ModelAndView mv=new ModelAndView(ACTUALIZACION_OK_VIEW);
		
		try { 
			transferenciaDto.setUsuarioModificacion(userSesion.getUsuario());
			registroAVCServicio.actualizarCuitYObservacionDeTransferencia(transferenciaDto);
			
		} catch (ConcurrenciaExcepcion e) {
			userSesion.setVolviendoABusqueda(true);
			ModelAndView mvError = new ModelAndView(ERROR_CONCURRENCIA_VIEW);
			mvError.addObject("lista", true);
			mvError.addObject("mensaje", 
				Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.concurrencia.lista"), 
						", para las conciliaciones ["+e.getListaIdsInconcurrentes()+"]"));
			
			mvError.addObject("url","conciliacion-gestionar-resultado-conciliacion");
			return mvError;
		}
		
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		mv.addObject("url","conciliacion-gestionar-resultado-conciliacion");
	
		return mv;
	}

	@RequestMapping("/conciliacion-editar-registro-avc-detalle")
	public ModelAndView conciliacionEditarRegistrosAVCDetalle(HttpServletRequest request,
			@ModelAttribute("registroDto") RegistroAVCDto registroDto, BindingResult result) throws Exception {
	
		String idRegistro = registroDto.getIdRegistroSelected();
		
		TransferenciaDto transDto = (TransferenciaDto) registroAVCServicio.buscarRegistroAVC(idRegistro);
		transDto.setOperation(Constantes.EDITAR_TRANSFERENCIA);
		transDto.setListaComprobantes(registroAVCServicio.buscarDocumentosAdjuntosPorIdTransferencia(idRegistro));
		 
		ModelAndView mv=new ModelAndView(EDITAR_REGISTRO_AVC_DETALLE_VIEW);
		mv.addObject(TRANSFERENCIA_DTO, transDto);
		
		mv.addObject("imprimibleArchivo", false);
		return mv;
	}
	
	@RequestMapping("/procesar-alta-comprobante-conciliacion")
	public ModelAndView procesarAltaComprobanteAvisoPago(HttpServletRequest request,
			@ModelAttribute("transDto") @Valid TransferenciaDto transferenciaDto, 
			BindingResult result) throws Exception {
		
		String descripcionComprobante = request.getParameter("descripcionComprobante");
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView(EDITAR_REGISTRO_AVC_DETALLE_VIEW);
			mv.addObject("imprimibleArchivo", false);
			mv.addObject(TRANSFERENCIA_DTO, transferenciaDto);	
			return mv;
		}
		
		MultipartFile file;
		file = transferenciaDto.getFileComprobanteModificacion();
		ModelAndView mv = new ModelAndView(EDITAR_REGISTRO_AVC_DETALLE_VIEW);

		ComprobanteDto comprobanteDto = new ComprobanteDto();
		comprobanteDto.setDescripcionArchivo(descripcionComprobante);
		comprobanteDto.setUsuarioCreacion(userSesion.getIdUsuario());
		comprobanteDto.setNombreArchivo(file.getOriginalFilename());
		comprobanteDto.setDocumento(file.getBytes());
		
		if(comprobanteDto.getDocumento().length==0){
			ObjectError error = new ObjectError("errorArchivoVacio", "valor.error.archivoVacio");
			result.addError(error);
		}

		transferenciaDto.getListaComprobantes().add(comprobanteDto);

		if(Validaciones.isCollectionNotEmpty(transferenciaDto.getListaComprobantes())){
			int i = 0;
			Iterator<ComprobanteDto> it = transferenciaDto.getListaComprobantes().iterator();
			while (it.hasNext()) {
				ComprobanteDto comp = it.next();
				comp.setId(i);
				i++;
			}
		}
		transferenciaDto.setDescripcionComprobante("");
		mv.addObject("imprimibleArchivo", false);
		mv.addObject(TRANSFERENCIA_DTO, transferenciaDto);	
		return mv;
	}
		

	@RequestMapping("/procesar-eliminar-comprobante-conciliacion")
	public ModelAndView procesarEliminarComprobanteAvisoPago(HttpServletRequest request,
			@ModelAttribute("transDto") TransferenciaDto transferenciaDto, 
			BindingResult result) throws Exception {

		String idComprobante = transferenciaDto.getIdComprobanteSelected();
		ModelAndView mv = new ModelAndView(EDITAR_REGISTRO_AVC_DETALLE_VIEW);
		
		Iterator<ComprobanteDto> it = transferenciaDto.getListaComprobantes().iterator();
		while (it.hasNext()) {
			ComprobanteDto comp = it.next();
			if (idComprobante.equals(comp.getId().toString())) {
				it.remove();
			}
		}
		
		mv.addObject("imprimibleArchivo", false);
		mv.addObject("transDto", transferenciaDto);	
		return mv;
	}
 
	
	@RequestMapping("/procesar-abrir-comprobante-conciliacion")
	public ModelAndView procesarAbrirComprobanteConciliacion(HttpServletRequest request, HttpServletResponse res,
			@ModelAttribute("transDto") TransferenciaDto transferenciaDto, 
			BindingResult result) throws Exception {

		String idComprobante = transferenciaDto.getIdComprobanteSelected();
		ModelAndView mv = new ModelAndView(EDITAR_REGISTRO_AVC_DETALLE_VIEW);
		
		if(Validaciones.isCollectionNotEmpty(transferenciaDto.getListaComprobantes())){
			Iterator<ComprobanteDto> it = transferenciaDto.getListaComprobantes().iterator();
			while (it.hasNext()) {
				ComprobanteDto comp = it.next();
				if (idComprobante.equals(comp.getId().toString())) {
					request.getSession().setAttribute( "documentoImprimible" , comp.getDocumento());
					request.getSession().setAttribute( "nombreDocumento" , comp.getNombreArchivo());
					mv.addObject("imprimibleArchivo", true);	
				}
			}
		}
		mv.addObject("transDto", transferenciaDto);	
		return mv;
	}
	
	@RequestMapping("/abrir-documento-comprobante")
	public void abrirDocumentoComprobante(HttpServletResponse res,
			HttpServletRequest request) throws Exception {
		
		byte[] archivo = (byte[]) request.getSession().getAttribute("documentoImprimible");
		String nombreDocumento = (String) request.getSession().getAttribute("nombreDocumento");
		
		ControlArchivo.descargarComoArchivo(archivo, nombreDocumento, res); 
	}
	
	@RequestMapping("/procesar-confirmacion-conciliacion")
	public ModelAndView procesarConfirmacionConciliacion(HttpServletRequest request, 
			@ModelAttribute("transDto") @Valid TransferenciaDto transferenciaDto, 
			BindingResult result) throws Exception {
	
		if(!Validaciones.isCollectionNotEmpty(transferenciaDto.getListaComprobantes())){
			transferenciaDto.setErrorComprobanteVacioModif(true);
		}
		
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession()	.getAttribute(Constantes.LOGIN);
		transferenciaDto.setUsuarioModificacion(userSesion.getIdUsuario());
		
		ModelAndView mv = new ModelAndView(ACTUALIZACION_OK_VIEW);		
		mv.addObject("mensaje",  Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		mv.addObject("url","bandeja-de-entrada");
		return mv;
	}
	
	/***************************************************************************
	 * Confirmar Anulacion
	 * ************************************************************************/
	
	@RequestMapping("/conciliacion-confirmar-pedido-anulacion-transferencia")
	public ModelAndView confirmarPedidoAnulacionConciliacionTransferencia(HttpServletRequest request,
			@ModelAttribute("registroDto") @Valid TransferenciaDto transferenciaDto, 
			BindingResult result) throws Exception {
	return confirmarPedidoAnulacionConciliacion(request, transferenciaDto, result);
	}
	@RequestMapping("/conciliacion-confirmar-pedido-anulacion-interdeposito")
	public ModelAndView confirmarPedidoAnulacionConciliacionInterdeposito(HttpServletRequest request,
			@ModelAttribute("registroDto") @Valid InterdepositoDto interdepositoDto, 
			BindingResult result) throws Exception {
		return confirmarPedidoAnulacionConciliacion(request, interdepositoDto, result);
	}
	@RequestMapping("/conciliacion-confirmar-pedido-anulacion-deposito")
	public ModelAndView confirmarPedidoAnulacionConciliacionDeposito(HttpServletRequest request,
			@ModelAttribute("registroDto") @Valid DepositoDto depositoDto, 
			BindingResult result) throws Exception {
		return confirmarPedidoAnulacionConciliacion(request, depositoDto, result);
	}
	
	/**
	 * Método para aceptar la anulación del registro Avc.
	 * @param request
	 * @return
	 * @throws Exception
	 * @author hernan 
	 */

	public ModelAndView confirmarPedidoAnulacionConciliacion(HttpServletRequest request,
			@ModelAttribute("registroDto") RegistroAVCDto registroDto, 
			BindingResult result) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);

		// Valida que el campo observacion no este vacio
		if (result.hasErrors()) {
			return anularRegistroAvc(request, registroDto);
		}

		ModelAndView mv = new ModelAndView(ACTUALIZACION_OK_VIEW);	
 
		try { 
			registroDto.setUsuarioModificacion(userSesion.getIdUsuario());
			registroAVCServicio.confirmarAnulacionRegistroAvc(registroDto);
			
		} catch (ConcurrenciaExcepcion e) {
			ModelAndView mvError = new ModelAndView(ERROR_CONCURRENCIA_VIEW);
			mvError.addObject("lista", true);
			mvError.addObject("mensaje", 
				Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.concurrencia.lista"), 
						", para las conciliaciones ["+e.getListaIdsInconcurrentes()+"]"));
			
			mvError.addObject("url",CONFIRMAR_ANULACION_REGISTRO_AVC_VIEW);
			mvError.addObject("registroDto", registroDto);
			return mvError;
		}
		
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		mv.addObject("url",PAGINA_DEFAULT);
			
		return mv;
	}

	/***************************************************************************
	 * Rechazar Anulacion
	 * ************************************************************************/
	

	
	@RequestMapping("/conciliacion-rechazar-pedido-anulacion-transferencia")
	public ModelAndView rechazarPedidoAnulacionConciliacionTransferencia(HttpServletRequest request,
			@ModelAttribute("registroDto") @Valid TransferenciaDto transferenciaDto, 
			BindingResult result) throws Exception {
	return rechazarPedidoAnulacionConciliacion(request, transferenciaDto, result);
	}
	@RequestMapping("/conciliacion-rechazar-pedido-anulacion-interdeposito")
	public ModelAndView rechazarPedidoAnulacionConciliacionInterdeposito(HttpServletRequest request,
			@ModelAttribute("registroDto") @Valid InterdepositoDto interdepositoDto, 
			BindingResult result) throws Exception {
		return rechazarPedidoAnulacionConciliacion(request, interdepositoDto, result);
	}
	@RequestMapping("/conciliacion-rechazar-pedido-anulacion-deposito")
	public ModelAndView rechazarPedidoAnulacionConciliacionDeposito(HttpServletRequest request,
			@ModelAttribute("registroDto") @Valid DepositoDto depositoDto, 
			BindingResult result) throws Exception {
		return rechazarPedidoAnulacionConciliacion(request, depositoDto, result);
	}
	
	/**
	 * Método para rechazar el pedido de confirmacion de la anulación del registro Avc.
	 * @param request
	 * @return
	 * @throws Exception
	 * @author hernan 
	 */
	public ModelAndView rechazarPedidoAnulacionConciliacion(HttpServletRequest request,
			@ModelAttribute("registroDto") RegistroAVCDto registroDto, 
			BindingResult result) throws Exception {
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);

		// Valida que el campo observacion no este vacio
		if (result.hasErrors()) {
			return anularRegistroAvc(request, registroDto);
		}
		
		try { 
			registroDto.setUsuarioModificacion(userSesion.getIdUsuario());
			registroAVCServicio.rechazarPedidoDeAnulacionRegistroAvc(registroDto);
		} catch (ConcurrenciaExcepcion e) {
			ModelAndView mvError = new ModelAndView(ERROR_CONCURRENCIA_VIEW);
			mvError.addObject("lista", true);
			mvError.addObject("mensaje", 
				Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.concurrencia.lista"), 
						", para las conciliaciones ["+e.getListaIdsInconcurrentes()+"]"));
			
			mvError.addObject("url",CONFIRMAR_ANULACION_REGISTRO_AVC_VIEW);
			mvError.addObject("registroDto", registroDto);
			return mvError;
		}
		
		ModelAndView mv = new ModelAndView(ACTUALIZACION_OK_VIEW);	
		mv.addObject("mensaje", Propiedades.MENSAJES_PROPIEDADES.getString("actualizacionOK.mensaje"));
		mv.addObject("url",PAGINA_DEFAULT);

		return mv;
	}
	
	/***************************************************************************
	 * Anular Registro Avc
	 * ************************************************************************/
		
		/**
		 * Método utilizado por la bandeja de entrada para confirmar la anulacion un registro AVC.
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/conciliacion-anular-registro-avc")
		public ModelAndView anularRegistroAvc(HttpServletRequest request, RegistroAVCDto registroDto) throws Exception {
			
			if (request.getParameter("idRegistroBandeja") != null) {
				String idRegistro = request.getParameter("idRegistroBandeja");
				registroDto = registroAVCServicio.buscarRegistroAVC(idRegistro);
				registroDto.setOperation(Constantes.UPDATE);
			}
				
			ModelAndView mv=new ModelAndView(CONFIRMAR_ANULACION_REGISTRO_AVC_VIEW);
			mv.addObject("registroDto", registroDto);
			return mv;
		}
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	
	public RegistroAvcValidacionWeb getRegistroAvcValidacionWeb() {
		return registroAvcValidacionWeb;
	}

	public void setRegistroAvcValidacionWeb(
			RegistroAvcValidacionWeb registroAvcValidacionWeb) {
		this.registroAvcValidacionWeb = registroAvcValidacionWeb;
	}
}
