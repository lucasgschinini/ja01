package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.AcuerdoLegado;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroOperacionRelacionada;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobOperacion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoDescobro;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTransaccionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CodigoOperacionExternaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroHistoricoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroOperacionesRelacionadasDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroTransaccionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.DescobrosJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaDescobroHistorialFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroFiltro;

public interface IDescobroServicio extends IServicio{

	ShvCobDescobro buscarDescobroPorIdOperacion(Long idOperacion) throws NegocioExcepcion;
		
	Set <TipoCreditoEnum> listarMediosPagoBusquedaDescobros();
	
	List<DescobroDto> listarDescobros(VistaSoporteDescobroFiltro descobroFiltro) throws NegocioExcepcion;
	
	public ShvCobDescobro crearDescobroDto(DTO dto, Long idCobro , UsuarioSesion userSesion) throws NegocioExcepcion;

	ComprobanteDto crearAdjuntoDescobro(Long idDescobro, ComprobanteDto comprobante) throws NegocioExcepcion;

	ComprobanteDto buscarAdjuntoDescobro(Long idAdjunto) throws NegocioExcepcion;

	DescobroDto buscarDescobroPorIdDescobro(Long idDescobro) throws NegocioExcepcion;

//	List<Object[]> listarDescobrosParaSubdiario(Filtro subsidiarioFiltro) throws NegocioExcepcion;
	
	public void iniciarDescobro(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public void descobrarCobro(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public void guardarDescobroEnEdicion(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public void solicitarDescobro(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public void solicitarAnulacionDeDescobro(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public void aplicarDescobro(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public void registrarDescobroEnError(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public void reintentarDescobroEnError(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public void registrarErrorEnPrimerDescobro(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public void revertirDescobroEnErrorEnPrimerDescobro(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public void anularDescobroEnErrorEnPrimerDescobro(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion;

	boolean validaAnular(DescobroDto descobroDto);
	
	boolean validaModificar(DescobroDto descobroDto, UsuarioSesion userSesion) throws LdapExcepcion;
	
	public List<String> listarEstadosBusquedaDescobro();
	
	public List<DescobroHistoricoDto> obtenerHistorialDescobro (VistaSoporteBusquedaDescobroHistorialFiltro filtro) throws NegocioExcepcion;

	public List<ShvCobDescobro> buscarDescobrosPendienteSimulacionBatch() throws NegocioExcepcion;
	
	public String validarEdicionSegunEstadoMarca(DescobroDto descobroDto, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public boolean validarBtnSimular(DescobroDto descobroDto, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public ShvCobDescobro pasarObservacionEnEstado(ShvCobDescobro descobro) throws NegocioExcepcion;
	
	public String obtenerObseHistorial(DescobroDto descobroDto, ShvCobDescobro descobroModelo) throws NegocioExcepcion;
	
	public boolean validarMostrarObservacionAnterior(Long idDescobro) throws NegocioExcepcion;
	
	public List<ShvCobDescobroOperacionRelacionada> buscarOperacionRelacionadaDescobroPorIdOperacionCobro(Long idOperacionCobro) throws NegocioExcepcion;
	
	public boolean validarEstadoIdReversionPadre(Long idOperacionDescobro) throws NegocioExcepcion;
	
	public boolean validarSiExisteElIdReversion(Long idOperacionDescobro) throws NegocioExcepcion;
	
	public DescobrosJsonResponse obtenerEstados(long idDescobro, boolean soloUltima, String prefijo, String separador) throws NegocioExcepcion;
	
	public void revertirDescobrosCheckeados(List<String> list, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public List<String> buscarIdsOperacionDescobroConIdsDescobro(List<String> ids) throws NegocioExcepcion;
	
	public void eliminarTareasEnErrorDescobro(Long idDescobro, ShvCobDescobro descobroModelo, String idUsuario) throws NegocioExcepcion;
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * Recibo todos los datos para guardar el descobro, lo guardo y seteo la marca de simulacion batch pendiente de proceso
	 * @param descobro
	 * @param comprobantesAGuardar
	 * @param userSesion
	 * @return DescobroDto
	 * @throws NegocioExcepcion
	 */
	public DescobroDto simularDescobro(DescobroDto descobro, List<ComprobanteDto> comprobantesAGuardar, UsuarioSesion userSesion) throws NegocioExcepcion;

	public ShvCobDescobro copioOperacionDeCobroADescobro(ShvCobCobro cobro) throws NegocioExcepcion;
	
	

	public List<DescobroTransaccionDto> buscarTransacciones(Long idDescobro) throws NegocioExcepcion, PersistenciaExcepcion;

	public boolean verificaErrorEnMensajeTransaccion(Set<DescobroTransaccionDto> transacciones);
	
	
	/**
	 * Guardo el descobro y luego lo exporto a Excel 
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7 
	 * @param descobroDto
	 * @param comprobantesAGuardar
	 * @param userSesion
	 * @param response
	 * @throws NegocioExcepcion
	 */
	public void exportarDescobro (DescobroDto descobroDto, List<ComprobanteDto> comprobantesAGuardar, UsuarioSesion userSesion, HttpServletResponse response) throws NegocioExcepcion;
	
	/**
	 * Genera y exporta Excel de un descobro
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param idDescobro
	 * @param idCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
 	public HSSFWorkbook exportarDescobro(Long idDescobro, Long idCobro) throws NegocioExcepcion;
 
 	/**
 	 * 
 	 * @param transaccion
 	 * @return
 	 */
	public int validarHabilitacionCampo(DescobroTransaccionDto transaccion);
  
	 /**
	  * Guarda el descobro con los datos de la pantalla
	  * @author u573005, fabio.giaquinta.ruiz, sprint 7
	  * @param descobro
	  * @param comprobantesAGuardar
	  * @param userSesion
	  * @return DescobroDto
	  * @throws NegocioExcepcion
	  */
	 public DescobroDto guardarDescobro(DescobroDto descobroDto, List<ComprobanteDto> comprobantesAGuardar, UsuarioSesion userSesion) throws NegocioExcepcion;
		
	 
	/**
	 * 
	 * @author u579607
	 * @throws NegocioExcepcion
	 */
	public AcuerdoLegado validarAcuerdoContraCliente (String sistemaIngresado, String acuerdoIngresado, List<String> listaIdClientesLegado) throws NegocioExcepcion;
 
 /**
  * obtener ultimas marcas
  * @author u573005, fabio.giaquinta.ruiz, sprint 7
  * @param idDescobro
  * @param descobroModelo
  * @param soloUltima
  * @return List<MarcaEnum>
  * @throws NegocioExcepcion
  */
 public List<MarcaEnum> obtenerUltimasMarcas(Long idDescobro, ShvCobDescobro descobroModelo, boolean soloUltima) throws NegocioExcepcion;
 
 /**
  * @author u573005, fabio.giaquinta.ruiz, sprint 7
  * @param idDescobro
  * @param descobroModelo
  * @param idUsuario
  * @throws NegocioExcepcion
  */
 public void eliminarTareasSimulacion(Long idDescobro, ShvCobDescobro descobroModelo, String idUsuario) throws NegocioExcepcion;
 
 public void eliminarTareasErrorPrimerDescobro(Long idDescobro, ShvCobDescobro descobroModelo, String idUsuario) throws NegocioExcepcion;
 
 /**
  * Se elimina la tarea y se anula el descobro
  * @author u573005, fabio.giaquinta.ruiz, sprint 7
  * @param idDescobro
  * @param usuarioModificacion
  * @throws NegocioExcepcion
  */
 public void eliminarTareaAnularDescobro(Long idDescobro, String usuarioModificacion) throws NegocioExcepcion;
 
 public void eliminarTareaErrorPrimerDescobroAnularDescobro(Long idDescobro, String usuarioModificacion) throws NegocioExcepcion;
 
 public AcuerdoLegado validarEstadoAcuerdoMic(Long numeroAcuerdo) throws NegocioExcepcion;

 public ShvCobDescobro modificarTransaccion(DescobroDto descobroDto, ShvCobDescobro descobro) throws NegocioExcepcion;
 
 public void eliminarAdjuntoDescobro(Long idAdjunto) throws NegocioExcepcion;
 
 public List<ComprobanteDto> buscarAdjuntoPorIdAdjunto(Long idAdjunto) throws NegocioExcepcion;

 public List<DescobroTransaccionDto> transaccionesMapeo(List<CobroTransaccionDto> cobroTransacciones) throws NegocioExcepcion;

 public List<DescobroOperacionesRelacionadasDto> buscarOperacionesRelacionadas(Long idDescobro) throws NegocioExcepcion;
 

 /**
  * @author u573005, fabio.giaquinta.ruiz, sprint 7
  * @param descobroDto
  * @param userSesion
 * @return DescobroDto
  * @throws NegocioExcepcion
  */
 public DescobroDto imputarDescobro(DescobroDto descobroDto, UsuarioSesion userSesion) throws NegocioExcepcion;
 
 
 //utilizada para un test - Brian Keller u579607	
 public ShvCobOperacion inicializarOperacionDeDescobro (ShvCobOperacion operacionVieja) throws NegocioExcepcion;
 /**
  * 
  * @param descobro
  * @throws NegocioExcepcion
  */
 public void informarAContabilidadYScard(ShvCobDescobro descobro) throws NegocioExcepcion;
 /**
  * 
  * @param transacciones
  * @param monedaOperacion
  * @return
  */
 public Set <DescobroTransaccionDto> formatearImportesTransacciones(Set<DescobroTransaccionDto> transacciones, String monedaOperacion) ;
 /**
  * 
  * @param listaMotivos
  * @param userSesion
  * @param reviertoDesdeBusqueda
  * @param analista
  * @param Copropietario
  * @return
  */
 public List<ShvParamMotivoDescobro> filtrarMotivoSegunPerfilYOrigenReversion(List<ShvParamMotivoDescobro> listaMotivos, UsuarioSesion userSesion, boolean reviertoDesdeBusqueda, String analista, String Copropietario);
 /**
  * 
  * @param listaMotivos
  * @param userSesion
  * @param reviertoDesdeBusqueda
  * @param analista
  * @param Copropietario
  * @param idMotivoSeleccionado
  * @return
  */
 public List<ShvParamMotivoDescobro> filtrarMotivoSegunPerfilYOrigenReversion(List<ShvParamMotivoDescobro> listaMotivos, UsuarioSesion userSesion, boolean reviertoDesdeBusqueda, String analista, String Copropietario, String idMotivoSeleccionado);

 /**
  * 
  * @param codigoOperacionExterna
  * @return
  * @throws NegocioExcepcion
  */
 public void crearCodigoOperacionExterna(ShvCobDescobro shvCobDescobro, List<CodigoOperacionExternaDto> codigoOperacionExterna);

 /**
  * 
  * @param idDescobro
  * @param userSesion
  * @throws NegocioExcepcion
  */
 public void registrarDescobroPendienteConfirmacionManualAEnProceso(ShvCobDescobro descobroModelo, UsuarioSesion userSesion) throws NegocioExcepcion;
 
 /**
  * 
  * @param idDescobro
  * @param userSesion
  * @return
  * @throws NegocioExcepcion
  */
 public void confirmaAplicacionManual(Long idDescobro, UsuarioSesion userSesion, String observacion, SociedadEnum sociedad, SistemaEnum sistema) throws NegocioExcepcion, PersistenciaExcepcion;
}
