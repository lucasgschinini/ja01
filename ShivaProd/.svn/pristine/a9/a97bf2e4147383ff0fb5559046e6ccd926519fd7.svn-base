/**
 * 
 */
package ar.com.telecom.shiva.negocio.servicios;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.jms.util.runnable.ConsultaSaldoACobradoresChequeRechazadoThread;
import ar.com.telecom.shiva.negocio.bean.ValidacionesAuxiliar;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.negocio.workflow.definicion.EdicionTipoEnum;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoCobrosRelacionado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoDetalleDocumento;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjChequeRechazado;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjLegajoChequeRechazado;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjChequeRechazadoDetalleDocumentoSimplificado;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro;

/**
 * @author u564030
 *
 */
public interface ILegajoChequeRechazadoServicio {

	/**
	 * 
	 * @param filtro
	 * @param validacionesAuxiliar
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<Bean> listarCheques(Filtro filtro, ValidacionesAuxiliar validacionesAuxiliar) throws NegocioExcepcion;
	/**
	 * 
	 * @param filtro
	 * @param validacionesAuxiliar
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<Bean> paginarCheques(Filtro filtro, ValidacionesAuxiliar validacionesAuxiliar) throws NegocioExcepcion;
	/**
	 * 
	 * @param legajoChequeRechazado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public Modelo crear(Modelo modelo) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<Bean> listar(Filtro filtro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param legajoChequeRechazado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public Modelo modificar(Modelo modelo) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @throws NegocioExcepcion
	 */
	public void entregarCheque(Long idLegajoChequeRechazado, String idUsuario) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @return 
	 * @throws NegocioExcepcion
	 */
	public ShvLgjLegajoChequeRechazado reembolsarCheque(Long idLegajoChequeRechazado, String idUsuario) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idValor
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean validarChequeAplicadoEnOperacion(Long idValor) throws NegocioExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<Estado> listarComboEstados() throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @return
	 */
	public Modelo buscar(Long idLegajoChequeRechazado) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @return
	 */
	public List<Bean> listarBusqueda(Filtro filtro) throws NegocioExcepcion;

	/**
	 * 
	 * @param modelo
	 * @param userSesion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public EdicionTipoEnum validarEdicionSegunPerfilEstado(String idAnalista, String idCopropietario, Estado estado, UsuarioSesion userSesion) throws NegocioExcepcion;

	/**
	 * 
	 * @param filtro
	 * @throws NegocioExcepcion
	 */
	public Set<String> completarFiltroConClientes(ClienteFiltro filtro) throws NegocioExcepcion;

	/**
	 * 
	 * @param modelo
	 * @return
	 * @throws NegocioExcepcion
	 */
	public Map<String, BigDecimal> obtenerMontos(ShvLgjChequeRechazado modelo) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idLegajo
	 * @param sistema
	 * @param estado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public Map<String, String> obtenerMontos(Long idLegajo, SistemaEnum sistema, Estado estado) throws NegocioExcepcion;
	/**
	 * 
	 * @param idLegajo
	 * @param estado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public Map<String, String> obtenerMontos(Long idLegajo, SistemaEnum sistema) throws NegocioExcepcion;
	/**
	 * 
	 * @param legajoChequeRechazadoDto
	 * @return
	 * @throws NegocioExcepcion
	 * @throws ShivaExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listarCobrosRelacionados(VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro filtro) throws NegocioExcepcion, ShivaExcepcion;
	
	/**
	 * 
	 * @param legajoChequeRechazadoDto
	 * @return
	 * @throws NegocioExcepcion
	 * @throws ShivaExcepcion
	 */
	
	
	/**
	 * 
	 * @param legajoChequeDto
	 * @param userSesion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public void revertirCobrosRelacionadosIce(List<Long> listaIdDetCobro, Estado estadoWorkflow, Integer idWorkflow, UsuarioSesion userSesion) throws NegocioExcepcion;

	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 */
	public void anular(Long idLegajoChequeRechazado, String idUsuario) throws NegocioExcepcion;

	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @throws NegocioExcepcion
	 */
	public ShvLgjLegajoChequeRechazado cerrar(Long idLegajoChequeRechazado, String idUsuario) throws NegocioExcepcion;

	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @throws NegocioExcepcion
	 */
	public ShvLgjLegajoChequeRechazado desistir(Long idLegajoChequeRechazado, String idUsuario) throws NegocioExcepcion;

	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @throws NegocioExcepcion
	 */
	public ShvLgjLegajoChequeRechazado enviarALegales(Long idLegajoChequeRechazado, String idUsuario) throws NegocioExcepcion;

	/**
	 * 
	 * @param legajoChequeRechazado
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 */
	public void contabilizarReversionCobranzasLegajoChequeRechazadoCircuitoCobranzaBancariaIce(ShvLgjLegajoChequeRechazado legajoChequeRechazado, String idUsuario) throws NegocioExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado obtenerLegajoChequeRechazadoCobrosRelacionadosEstadoReversionShivaBy(Long idOperacion) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param legajoAdjunto
	 * @return ShvLgjAdjunto
	 * @throws NegocioExcepcion
	 */
	public ComprobanteDto insertarDocumentoAdjunto(Long idLegajo, ComprobanteDto comprobante) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idComprobante
	 */
	public void eliminarDocumentoAdjuntoDelLegajo(Long idComprobante) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ComprobanteDto> listarComprobantes(Long idLegajo) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idAdjunto
	 * @return
	 */
	public ComprobanteDto buscarAdjuntoLegajo(Long idAdjunto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param listaCobrosRelacionados
	 * @param estado
	 * @param idWorkflow
	 * @param sistema
	 * @param userSesion
	 * @throws NegocioExcepcion
	 */
	public void confirmarFinReversion(Long idLegajo, List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listaCobrosRelacionados, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idWorkflow
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvWfWorkflow buscarWorkflowActual(Integer idWorkflow) throws NegocioExcepcion;
	
	public void solicitarReversionDeCobrosrelacionados(Estado estado, Integer idWorkflow, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param estado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean legajoEsAnulable(List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listaCobrosRelacionados,Estado estado) throws NegocioExcepcion;

	/**
	 * 
	 * @param modelo
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaLegajoDetalleDocumento> listarCobroRelacionadoDocumentosBy(Long idLegajoChequeRechazado) throws NegocioExcepcion;
	
	/**
	 * 
	 * @throws NegocioExcepcion
	 */
	public void actualizarSaldosDocumentosReembolso() throws NegocioExcepcion;
	
	/**
	 * 
	 * @param detDocumento
	 * @param consultarCobradores
	 * @throws NegocioExcepcion
	 */
	public void obtenerDetDocumentoActualizado(ShvLgjChequeRechazadoDetalleDocumentoSimplificado detDocumento,ConsultaSaldoACobradoresChequeRechazadoThread consultarCobradores) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public Modelo buscarSimplificadoConWorkFlow(Long idLegajoChequeRechazado) throws NegocioExcepcion;
	
	public HSSFWorkbook exportarDetalleLegajo(LegajoChequeRechazadoDto legajoChequeRechazadoDto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param listaDeValores
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<Bean> listaBusqueda(Set<Long> listaDeValores) throws NegocioExcepcion;
}