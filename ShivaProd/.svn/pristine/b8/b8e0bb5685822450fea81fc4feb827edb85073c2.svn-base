package ar.com.telecom.shiva.presentacion.facade;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.negocio.bean.ArchivoByteArray;
import ar.com.telecom.shiva.negocio.bean.ValidacionesAuxiliar;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ConsultaSoporteResultadoBusquedaChequeRechazadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoBusquedaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoNotificacionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.JsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.LegajoChequeRechazadoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConsultaSoporteBusquedaChequeRechazadoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;


public interface ILegajoChequeRechazadoFacade {
	/**
	 * 
	 * @param chequeRechazadoFiltro
	 * @param validacionesAuxiliar
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ConsultaSoporteResultadoBusquedaChequeRechazadoDto> listarCheques(ConsultaSoporteBusquedaChequeRechazadoFiltro chequeRechazadoFiltro, ValidacionesAuxiliar validacionesAuxiliar) throws NegocioExcepcion;
		
	/**
	 * 
	 * @param chequeRechazadoFiltro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ConsultaSoporteResultadoBusquedaChequeRechazadoDto> paginarCheques(ConsultaSoporteBusquedaChequeRechazadoFiltro chequeRechazadoFiltro, ValidacionesAuxiliar validacionesAuxiliar) throws NegocioExcepcion;

	/**
	 * 
	 * @param chequeDto
	 * @return
	 * @throws NegocioExcepcion
	 */
	public LegajoChequeRechazadoJsonResponse crear(LegajoChequeRechazadoDto legajoChequeDto, UsuarioSesion userSesion) throws NegocioExcepcion;

	/**
	 * 
	 * @param empresa
	 * @param segmento
	 * @param perfil
	 * @return
	 * @throws LdapExcepcion
	 */
	public List<UsuarioLdapDto> buscarUsuariosPorPerfilSegmento(String empresa, String segmento, String perfil) throws LdapExcepcion;
	
	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public LegajoChequeRechazadoDto buscar(Long idLegajoChequeRechazado, UsuarioSesion usuarioSession) throws NegocioExcepcion;
	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public LegajoChequeRechazadoDto buscar(Long idLegajoChequeRechazado) throws NegocioExcepcion;
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<LegajoBusquedaDto> listar(Filtro filtro, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public void anular(Long idLegajoChequeRechazado, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param legajoChequeDto
	 * @param userSesion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public LegajoChequeRechazadoJsonResponse modificar(LegajoChequeRechazadoDto legajoChequeDto, UsuarioSesion userSesion) throws NegocioExcepcion;

	/**
	 * 
	 * @param valueOf
	 * @param comprobante
	 * @return
	 */
	public ComprobanteDto crearAdjuntoLegajo(Long idLegajo, ComprobanteDto comprobante) throws NegocioExcepcion;

	/**
	 * 
	 * @param idComprobante
	 */
	public void eliminarAdjuntoLegajo(Long idComprobante) throws NegocioExcepcion;

	/**
	 * 
	 * @param idLegajo
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ComprobanteDto buscarAdjuntoLegajo(Long idLegajo) throws NegocioExcepcion; 
	
	
	/**
	 * 
	 * @param legajoChequeDto
	 * @param userSesion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public LegajoChequeRechazadoJsonResponse revertirCobrosRelacionadosIce(LegajoChequeRechazadoDto legajoChequeDto, UsuarioSesion userSesion) throws NegocioExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public JsonResponse verificarReversionShivaEnProceso(Long idOperacion) throws NegocioExcepcion;

	/**
	 * 
	 * @param legajoChequeDto
	 * @param userSesion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public LegajoChequeRechazadoJsonResponse confirmarFinReversion(LegajoChequeRechazadoDto legajoChequeDto, UsuarioSesion userSesion) throws NegocioExcepcion;

	/**
	 * 
	 * @param idLegajo
	 * @param sistema
	 * @return
	 * @throws NegocioExcepcion
	 */
	public Map<String, String> obtenerMontos(long idLegajo, SistemaEnum sistema, Estado estado) throws NegocioExcepcion;
	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 */
	public LegajoChequeRechazadoDto enviarALegales(Long idLegajoChequeRechazado, UsuarioSesion userSesion) throws NegocioExcepcion;

	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @param userSesion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public LegajoChequeRechazadoDto reembolsarCheque(Long idLegajoChequeRechazado, UsuarioSesion userSesion) throws NegocioExcepcion;

	/**
	 * 
	 * @param legajoChequeDto
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto> listarDocumentos(LegajoChequeRechazadoDto legajoChequeDto) throws NegocioExcepcion;
	

	/**
	 * 
	 * @param id
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ArchivoByteArray generarPdfCartaLegajo(Long id) throws NegocioExcepcion;
	
	
	public LegajoChequeRechazadoJsonResponse guardarContacto(LegajoChequeRechazadoNotificacionDto legajoChequeRechazadoNotificacionDto, UsuarioSesion userSesion) throws NegocioExcepcion;
	/**
	 * 
	 * @param legajoChequeDto
	 * @return
	 * @throws NegocioExcepcion
	 */
	public LegajoChequeRechazadoJsonResponse desistir(Long idLegajoChequeRechazado, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param legajoChequeDto
	 * @return
	 * @throws NegocioExcepcion
	 */
	public LegajoChequeRechazadoJsonResponse cerrar(Long idLegajoChequeRechazado, UsuarioSesion userSesion) throws NegocioExcepcion;

	/**
	 * 
	 * @param idNotificacion
	 * @throws NegocioExcepcion
	 */
	public LegajoChequeRechazadoJsonResponse anularContacto(Long idNotificacion, Long idLegajoChequeRechazado) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @throws NegocioExcepcion
	 */
	public void exportarDetalleLegajo(HttpServletResponse response,Long idLegajoChequeRechazado) throws NegocioExcepcion;
	
}
