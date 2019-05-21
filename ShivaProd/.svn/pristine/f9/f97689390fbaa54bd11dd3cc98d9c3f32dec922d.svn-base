package ar.com.telecom.shiva.negocio.servicios;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaGeneracionCargoSalida;
import ar.com.telecom.shiva.base.jms.util.runnable.ConsultaDebitosACobradoresThread;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.util.IDatosComunesEntrada;
import ar.com.telecom.shiva.negocio.bean.ArchivoByteArray;
import ar.com.telecom.shiva.negocio.bean.BuilderConsultaSap;
import ar.com.telecom.shiva.negocio.bean.TotalAcumuladoresTransacciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebito;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoComprobante;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroHistoricoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroOtrosDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTransaccionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CodigoOperacionExternaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoGestionableDTO;
import ar.com.telecom.shiva.presentacion.bean.dto.GestionabilidadDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.CobroJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.DeimosJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ImportarDebitoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.TransaccionesJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaCobroHistorialFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteCobroOnlineFiltro;
import ar.com.telecom.shiva.presentacion.bean.paginacion.salida.ShivaConsultaCreditoSalida;

/**
 * Servicio para el manejo de los cobros Onlines
 * 
 */
public interface ICobroOnlineServicio extends IServicio {

	/**
	 * 
	 * @param cobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvCobEdCobro crear(ShvCobEdCobro cobro) throws NegocioExcepcion;

//	/**
//	 * 
//	 * @param cobro
//	 * @param adjuntos
//	 * @return
//	 * @throws NegocioExcepcion
//	 */
//	public ShvCobEdCobro crear(ShvCobEdCobro cobro, List<ShvCobEdCobroAdjunto> adjuntos) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public CobroDto buscarCobro(Long idCobro) throws NegocioExcepcion;
	
	/**
	 *  
	 * @param multipartFile
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ImportarDebitoJsonResponse procesarRegistrosImportarDebitos(MultipartFile multipartFile, String string, Long idCobro) throws NegocioExcepcion;
	 
	 /**
	  * 
	  * @param file
	  * @return
	 * @throws NegocioExcepcion 
	  */
	 public ImportarDebitoJsonResponse validarRestriccionesArchivo(MultipartFile file) throws NegocioExcepcion;
	 
	 /**
	  * 
	  * @param file
	  * @return
	 * @throws NegocioExcepcion 
	  */
	 public ImportarDebitoJsonResponse resultadoValidaciones(MultipartFile file, String string, Long idCobro) throws NegocioExcepcion;
	 
	 /**
	  * 
	  * @return
	 * @throws PersistenciaExcepcion 
	  */
	 public List<ShvCobEdDebito> listarDebitosEnEdicionPendienteValidacion() throws PersistenciaExcepcion;
	 
	 /**
	  * 
	  * @return
	  * @throws NegocioExcepcion
	  */
	 public boolean procesarDebitosAValidar() throws NegocioExcepcion;
	 
	 /**
	  * 
	  * @param debito
	  */
	 public void cambiarEstadoDebitoValidado(ShvCobEdDebito debito, ConsultaDebitosACobradoresThread consultarCobradores);
	 
	 /**
	  * Retorna un semaforo segun la evaluacion de un CobroDebitoDto
	  * Documento "Gestionabilidad de deuda.xls"
	  * @param debito
	  * @return
	  * @throws ShivaExcepcion
	  */
	 public GestionabilidadDto determinarGestionabilidadDeDeuda(CobroDebitoDto debito) throws NegocioExcepcion;
	 
	 /**
	  * Retorna un semaforo segun la evaluacion de un CobroCreditoDto
	  * Documento "Gestionabilidad de deuda.xls"
	  * @param credito
	  * @return
	  * @throws ShivaExcepcion
	  */
	 public GestionabilidadDto determinarGestionabilidadDeCredito(CobroCreditoDto credito) throws NegocioExcepcion;
	 
	 public BigDecimal obtenerSaldoMaximoDebito(CobroDebitoDto debitoDto, String motivo);
	 
	 public BigDecimal obtenerImporteAUtilizarDebito(CobroDebitoDto debitoDto, String motivo);

	 public BigDecimal obtenerImporteAUtilizarCredito(CobroCreditoDto creditoDto);

	 /**
	  * @author u573005, Sprint 4
	  * Invoca al webservice de deimos para validar el estado en deimos y devolver
	  * en que semaforo queda. 
	  * @return
	  * @throws NegocioExcepcion
	  */
	 public DeimosJsonResponse validarEstadoDeimos(List<IDatosComunesEntrada> listaDebitosCreditos, EmpresaEnum empresa, List<IDatosComunesEntrada> listaDebitosCreditosYaSeleccionados) throws NegocioExcepcion;
	 /**
	  * Busco el adjunto elegido del cobro
	  * @param idAdjunto
	  * @return
	  */
	 public ComprobanteDto buscarAdjuntoCobro(Long idAdjunto) throws NegocioExcepcion;
	 
	 /**
	  * Busco el adjunto del Conjunto de Debitos seleccionado
	  * @param idAdjunto
	  * @return
	  * @throws NegocioExcepcion
	  */
	 public CobroOtrosDebitoDto buscarAdjunto(Long idAdjunto) throws NegocioExcepcion;
	 /**
	  * Creo un adjunto del cobro
	  * @param comprobanteDto
	  * @return
	  * @throws NegocioExcepcion
	  */
	 public ComprobanteDto crearAdjuntoCobro(Long idCobro, ComprobanteDto comprobanteDto) throws NegocioExcepcion;
	 
	 /**
	  * Elimino un adjunto del cobro
	  * @param idAdjunto
	  * @throws NegocioExcepcion
	  */
	 public void eliminarAdjuntoCobro(Long idAdjunto) throws NegocioExcepcion;

	 /**
	  * buscoCreditosShiva
	  * @param VistaSoporteBusquedaValoresFiltro
	  * @throws NegocioExcepcion
	  */
	 public ShivaConsultaCreditoSalida buscarCreditosShiva(VistaSoporteBusquedaValoresFiltro VistaSoporteBusquedaValoresFiltro) throws NegocioExcepcion;
	 
	 /**
	  * 
	  * @return
	  * @throws NegocioExcepcion
	  */
	 public List<Estado> listarComboEstados() throws NegocioExcepcion;
	 
	 /**
	  * 
	  * @param cobroFiltro
	  * @param b
	  * @return
	  * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion 
	 * @throws NumberFormatException 
	  */
	 public List<CobroDto> listarCobrosOnline(VistaSoporteCobroOnlineFiltro cobroFiltro) throws NegocioExcepcion, NumberFormatException, PersistenciaExcepcion; // buscar cobros

	 /**
	  * @author u572487 Guido.Settecerze
	  * @param cobroDto
	  * @param userSesion 
	  * @throws NegocioExcepcion
	  */
	 public void validarAprobacionCobroEnviarMail(CobroDto cobroDto, UsuarioSesion userSesion) throws NegocioExcepcion;
	 
	 /**
	  * @author u572487 Guido.Settecerze
	  * @param cobroDto
	  * @param userSesion 
	  * @throws NegocioExcepcion
	  */
	 public void imputarSinAprobacion(CobroDto cobroDto, UsuarioSesion userSesion) throws NegocioExcepcion;
	 
	 /**
	  * @param idCobro
	  * @param userSesion
	  * @return
	  * @throws NegocioExcepcion
	  */
	 public ShvCobEdCobro imputarSinAprobacion(Long idCobro, String idUsuario) throws NegocioExcepcion;
		 
	 /**
	  * @author u572487 Guido.Settecerze
	  * @param cobroDto
	  * @param userSesion 
	  * @throws NegocioExcepcion
	  */
	 public String validarEdicionSegunEstadoMarca(CobroDto cobroDto, UsuarioSesion userSesion) throws NegocioExcepcion;
	 
	 /**
	  * @author u572487 Guido.Settecerze
	  * @param cobroDto
	  * @throws NegocioExcepcion
	  */
	 public boolean validarSiRequiereAprobacion(CobroDto cobroDto);
	 
	 /**
	  * @author u572487 Guido.Settecerze
	  * @param idCobro
	  * @param userSesion
	  * @throws NegocioExcepcion
	  */
	 public void anularTarea(Long idCobro, UsuarioSesion userSesion) throws NegocioExcepcion;
	 
	 /**
	  * @author u572487 Guido.Settecerze
	  * @param idCobro
	  * @param userSesion
	  * @throws NegocioExcepcion
	  */
	 public void anularTareaErrorDesapropiacion(Long idCobro, UsuarioSesion userSesion) throws NegocioExcepcion;
	 
	 /**
	  * @author u572487 Guido.Settecerze
	  * @param idCobro
	  * @param userSesion
	  * @throws NegocioExcepcion
	  */
	 public void anularTareaErrorConfirmacion(Long idCobro, UsuarioSesion userSesion) throws NegocioExcepcion;
	 
	 /**
	  * @author u572487 Guido.Settecerze
	  * @param cobroDto
	  * @param userSesion
	  * @throws NegocioExcepcion
	  */
	 public void aprobarCobroCambiarEstadoWorkFlow(CobroDto cobroDto, UsuarioSesion userSesion) throws NegocioExcepcion;
	 
	 /**
	  * @param cobroDto
	  * @author u579607
	  * @throws NegocioExcepcion
	  */
	public List<CobroHistoricoDto> obtenerHistorialCobro(VistaSoporteBusquedaCobroHistorialFiltro filtro) throws NegocioExcepcion;

	/**
	 * @author u572487 Guido.Settecerze
	 * 
	 * @param idCobro
	 * @param userSesion
	 * @param observaciones
	 * @param sociedad 
	 * @param sistemaDestino 
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion 
	 * @throws IOException 
	 */
	public void rechazarTareaDeAprobacionCobroOConfirmacionAplicacionManual(Long idCobro, UsuarioSesion userSesion, String observaciones, SistemaEnum sistemaDestino, SociedadEnum sociedad) throws NegocioExcepcion, PersistenciaExcepcion, IOException;

	 /**
	  * @author u572487 Guido.Settecerze
	  * @param idCobro
	  * @throws NegocioExcepcion
	  */
	 public boolean validarTransacciones(String idCobro) throws NegocioExcepcion;

	 /**
	  * @author u573005, Fabio Giaquinta
	  * @param idCobro
	  * @throws NegocioExcepcion
	  */
	 public void simularCobro(Long idCobro) throws NegocioExcepcion;

	 /**
	  * Se buscan las transacciones armadas por la simulacion y se valida si supera un limite para mostrar
	  * la grilla en solo lectura o no
	  * @author u573005, Fabio Giaquinta
	  * @param idCobro
	 * @param validarSimulacionBatchEnProceso 
	  * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion 
	  */
	 public TransaccionesJsonResponse buscarTransacciones(Long idCobro, boolean simulacionBatchEnProceso) throws NegocioExcepcion, PersistenciaExcepcion;
	 
	 
	 
		/**
		 * Genera y exporta Excel de un cobro
		 * @author u579607
		 * @throws PersistenciaExcepcion 
		 */	 
	 public void exportarCobro (HttpServletResponse response, Long idCobro) throws NegocioExcepcion, PersistenciaExcepcion;

		/**
		 * Genera Excel de un cobro
		 * @author u579607
		 * @throws PersistenciaExcepcion 
		 */
	 public HSSFWorkbook exportarCobro(Long idCobro) throws NegocioExcepcion, PersistenciaExcepcion;

	 /**
	  * 
	  * @param idCobro
	  * @param estadoCobro
	  * @return
	  * @throws NegocioExcepcion
	  * @throws PersistenciaExcepcion
	  */
	 public HSSFWorkbook exportarCobro(Long idCobro, Estado estadoCobro) throws NegocioExcepcion, PersistenciaExcepcion;
	 /**
	  * Se guardan las transacciones modificadas en la pantalla
	  * @author u573005, Fabio Giaquinta
	  * @param CobroDto cobro con idOperacion y la lista de transacciones
	  * @throws NegocioExcepcion
	  */
	 public void modificarTransaccionesConIntereses(CobroDto cobro) throws NegocioExcepcion;

	 /**
	  * @author u572487 Guido.Settecerze
	  * @param idCobro
	  * @param cobroModelo
	  * @param usuarioModificacion
	  * @throws NegocioExcepcion
	  */
	 void eliminarTarea(DTO dto, Long idTarea) throws NegocioExcepcion;
	 
	 /**
	  * @author u572487 Guido.Settecerze
	  * @param idCobro
	  * @param cobroModelo
	  * @param usuarioModificacion
	  * @throws NegocioExcepcion
	  */
	 void eliminarTareasAlEditar(Long idCobro, ShvCobEdCobro cobroModelo, UsuarioSesion usuarioModificacion) throws NegocioExcepcion;
	 
	 /**
	  * @author u572487 Guido.Settecerze
	  * @param idCobro
	  * @param cobroModelo
	  * @param usuarioModificacion
	  * @throws NegocioExcepcion
	  */
	 void eliminarTareasAlImputar(Long idCobro, ShvCobEdCobro cobroModelo, UsuarioSesion usuarioModificacion) throws NegocioExcepcion;
	 
	 /**
	  * @author u572487 Guido.Settecerze
	  * @param idCobro
	  * @return
	  * @throws NegocioExcepcion
	  */
	 public boolean validarSimulacionBatchEnProceso(Long idCobro) throws NegocioExcepcion;
	 
	 /**
	  * @author u572487 Guido.Settecerze
	  * @param idCobro
	  * @return
	  * @throws NegocioExcepcion
	  */
	 public String validarComprobanteObligatorio(CobroDto cobroDto) throws NegocioExcepcion, ValidacionExcepcion;
	 
	 /**
	  * @author u572487 Guido.Settecerze
	  * @param idCobro
	  * @param usuario
	 * @param esReedicionParcial 
	  * @return
	  * @throws NegocioExcepcion
	  */
	 public CobroDto logicaAlGuardarErrorCobroErrorApropiacion(CobroDto cobroDto, UsuarioSesion usuario, boolean esReedicionParcial) throws NegocioExcepcion;
	 
	 /**
	  * @author u572487 Guido.Settecerze
	  * @param idCobro
	  * @return
	  * @throws NegocioExcepcion
	  */
	 public ShvCobEdCobro obtenerModeloCobro(Long idCobro) throws NegocioExcepcion;
	 /**
	  * Valida si el cobro es imputable en base al estado del cobro
	  * @author u578936 Max Uehara
	  * @param idCobro
	  * @return 
	  * @throws NegocioExcepcion
	  */
	 public boolean validarEsImputable(Long idCobro) throws NegocioExcepcion;
	 /**
	 * Persisto el checkSum en el cobro
	 * @param idCobro
	 * @return void
	 * @throws NegocioExcepcion
	 */
	 public void persistirCheckSum(Long idCobro, String hash, String usuarioModificacion, Date fechaModificacion) throws NegocioExcepcion;
	 /**
	  * Genero el checkSum de un cobro. Se utiliza el SH1-512 
	  * Los datos tenidos en cuenta son los Debitos, Creditos, MediosDePago y TratamientoDeLaDiferencia 
	  * @author u578936 Max Uehara
	  * @param cobroModelo
	  * @return String de 128 caracteres
	  * @throws NegocioExcepcion
	  */
	 public String generarCheckSum(Long idCobro) throws NegocioExcepcion;
	 /**
	 * Valida si la simulacion realizada sobre el cobro. representa a los datos actuales del cobro
	 * retorna false. si los datos actuales del cobro fueron modificados en relacion a los del momento de
	 * la simulacion. Los datos tomados son debitos, creditos, medios de pago y tratamientodeladiferencia
	 * @param idCobro
	 * @return boolean.
	 * @throws NegocioExcepcion
	 */
	 public boolean validarSimulacion(long idCobro, String userSession) throws NegocioExcepcion;
	 /**
	  * Obtiene las ultimas marcas de un cobro
	  * @param idCobro
	  * @param soloUltima  si es true retorna la ultima marca si no retorna las ultimas marcas segun el tipo
	  * @param prefijo 
	  * @param separador
	  * @return
	  * @throws NegocioExcepcion
	  */
	 public List<MarcaEnum> obtenerUltimasMarcas(long idCobro, ShvCobEdCobro cobroModelo, boolean soloUltima) throws NegocioExcepcion;
	 /**
	  * Obtiene las ultimas marcas de un cobro y el estado del cobro
	  * @param idCobro
	  * @param soloUltima
	  * @param prefijo
	  * @param separador
	  * @return
	  * @throws NegocioExcepcion
	  */
	 public CobroJsonResponse obtenerEstados(long idCobro, boolean soloUltima, String prefijo, String separador) throws NegocioExcepcion;
	 
	 
	/**
	 * 
	 * @param idDebitoCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public String getAnalistaCobroDebito(String idDebitoCobro, String idCobro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idCreditoCobro
	 * @param idCobro
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public String getAnalistaCobroCredito(String idCreditoCobro, String idCobro) throws NegocioExcepcion;
	/**
	 * Setea en la tranasccion si puede transladarInteres en base a la fecha de vencimiento primera, segunda y CobrarAl2doVenc
	 * @param transacciones
	 * @return
	 * @throws NegocioExcepcion
	 */
	public CobroTransaccionDto calcularSiEsTransaccionTraladarInteresesFechas(CobroTransaccionDto transaccion) throws NegocioExcepcion;
	/**
	 * 
	 * @param cobro
	 * @return
	 */
	public TotalAcumuladoresTransacciones calculaTotalInteresesTransacciones(CobroDto cobro);
	
	
	public void setearUsuarioYFechaImputacionYUltimaMdoficiacion(Long idCobro, Date FechaImputacionYUltimaMdoficiacion, boolean cobroCobrado) throws NegocioExcepcion;
	/**
	 * Valida si el cobro tiene o no un descobro asociado
	 * @param idCobro
	 * @return boolean
	 * @throws PersistenciaExcepcion
	 */
	public boolean puedeRevertir (Long idCobro) throws PersistenciaExcepcion;
	/**
	 * Setea a blanco los mensajes de las transacciones de un cobro
	 * @param idCobro
	 * @throws NegocioExcepcion 
	 */
	public void blanquearMensajesTransacciones(Long idCobro) throws NegocioExcepcion;
	/**
	 * Este metodo se utiliza para mostra habilitado o no un campo checkBox de la transacciones
	 * @param transaccion
	 * @return
	 */
	public int validarHabilitacionCampo(CobroTransaccionDto transaccion);
	
	/**
	 * Busca un ShvCobEdCliente por IdCobro y IdCreditoOrigen.
	 * U562163
	 */
	public ShvCobEdCliente buscarClientePorIdCobroYIdClienteLegado(Long idCobro, Long idClienteLegado) throws NegocioExcepcion;
	/**
	 * 
	 * @param cobroDto
	 * @param cobroModelo
	 * @return
	 * @throws NegocioExcepcion
	 */
	public String obtenerObseHistorial(CobroDto cobroDto, ShvCobEdCobro cobroModelo) throws NegocioExcepcion;
	/**
	 * 
	 * @param estado
	 * @return
	 */
	public boolean esPersistirObservacionEnEstado(Estado estado);
	/**
	 * si estadoDesc no encuentra Estado retorna false
	 * @param estadoDesc
	 * @return
	 */
	public boolean esPersistirObservacionEnEstado(String estadoDesc);
	
	public CobroDebitoDto mapearDebito(ShvCobEdDebito debito) throws NegocioExcepcion;
		
	public Set<CobroDebitoDto> formatearImporteACobrar(Set<CobroDebitoDto> debitos);
	
	public Set<CobroDebitoDto> formatearReclamoYMigradoEnOrigen(Set<CobroDebitoDto> debitos);
	/**
	 * 
	 * @param idCreditoCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean obtenerMarcaCreditoEnCobrosPendienteProceso(String idCreditoCobro) throws NegocioExcepcion;
	/**
	 * 
	 * @param idDebitoCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean obtenerMarcaDebitoEnCobrosPendienteProceso(String idDebitoCobro) throws NegocioExcepcion;
	
	/**
	 * Devuelte una lista de los clientes del cobro
	 * @param idCobro
	 * @return
	 */
	public List<ClienteDto> listarClientesCobro(Long idCobro) throws NegocioExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public CobroDto buscarCobroPorIdOperacion(Long idOperacion) throws NegocioExcepcion;

	/**
	 * 
	 * @param idCreditoCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean obtenerMarcaCreditoEnDescobrosPendienteProceso(String idCreditoCobro) throws NegocioExcepcion;
	/**
	 * 
	 * @param idDebitoCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean obtenerMarcaDebitoEnDescobrosPendienteProceso(String idDebitoCobro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idDebitoCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean obtenerMarcaReversionDeCobroEdicion(DocumentoGestionableDTO dto) throws NegocioExcepcion;

	/**
	 * Setea los campos 'opeAsocAnalista' y 'marcaPagoCompensacionEnProcesoShiva'
	 * @param debitoDto
	 * @return
	 */
	public CobroDebitoDto setearAtributosPorConsulta(CobroDebitoDto debitoDto, String idCobro) throws NegocioExcepcion;
	/**
	 * Setea los campos 'opeAsocAnalista' y 'marcaPagoCompensacionEnProcesoShiva'
	 * @param debitoDto
	 * @return
	 */
	public CobroCreditoDto setearAtributosPorConsulta(CobroCreditoDto creditoDto, String idCobro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param nroReferenciaMic
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean obtenerMarcaCreditoEnCobrosPendienteProcesoByNumeroRefMic(Long nroReferenciaMic) throws NegocioExcepcion;
	/**
	 * 
	 * 
	 */
	public boolean obtenerMarcaDebitoEnCobrosPendienteProcesoByNumeroRefMic(Long nroReferenciaMic) throws NegocioExcepcion;
	/**
	 * 
	 * @param nroReferenciaMic
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean obtenerMarcaCreditoEnDescobrosPendienteProcesoByNumeroRefMic(Long nroReferenciaMic) throws NegocioExcepcion;
	
	public boolean obtenerMarcaDebitoEnDescobrosPendienteProcesoByNumeroRefMic(Long nroReferenciaMic) throws NegocioExcepcion;

	public SalidaCalipsoCargosWS actualizarInteresesDebitoAProxCalipso(Integer idMedioPago, String acuerdoFacturacion) throws NegocioExcepcion;
	
	public MicRespuestaGeneracionCargoSalida actualizarInteresesDebitoAProxMic(Integer idMedioPago, String acuerdoFacturacion) throws NegocioExcepcion;

	public Set<CobroCreditoDto> formatearImportesCredito(Set<CobroCreditoDto> creditos) throws NegocioExcepcion;

	public List<CobroTransaccionDto> formatearImportesTransacciones(List<CobroTransaccionDto> transacciones, String monedaOperacion) throws NegocioExcepcion;
	
	public List<ComprobanteDto> listarComprobantes(Long idCobro) throws NegocioExcepcion;

	public BuilderConsultaSap consultaDocumentosCap(BuilderConsultaSap builder) throws NegocioExcepcion, ParseException;

	public BuilderConsultaSap consultaProveedoresSap(BuilderConsultaSap builder) throws NegocioExcepcion;
	
	public DocumentoCapDto setearAtributosPorConsulta(DocumentoCapDto documetoCapDto, String idCobro) throws NegocioExcepcion;
	
	public BuilderConsultaSap determinarGestiobabilidadSap(BuilderConsultaSap builder, String idCobro) throws NegocioExcepcion;
	
	public GestionabilidadDto determinarGestionabilidadDeSap(DocumentoCapDto documentoCaDto) throws NegocioExcepcion;
	
	public String getAnalistaCobroDocumentoCap(String idCapCobro, String idCobro) throws NegocioExcepcion;
	
	public boolean obtenerMarcaDocumentoEnCobrosPendienteProceso(String idCapCobro) throws NegocioExcepcion;
	
	public boolean obtenerMarcaDocumentoEnDescobrosPendienteProceso(String idCapCobro) throws NegocioExcepcion;
	/**
	 * 
	 * @param idCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ArchivoByteArray generarPdfCartaCompensacion(Long idCobro) throws NegocioExcepcion;

	/**
	 * 
	 * @param idCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ArchivoByteArray generarPdfResumenCompensacion(Long idCobro) throws NegocioExcepcion;
	/**
	 * 
	 * @param lista
	 * @return
	 */
	public CobroDto prepararDocumentosCapParaSuVisualizacion(CobroDto cobroDto);
	
	/**
	 * 
	 * @param cobroDto
	 * @return
	 */
	public boolean mostrarBtnGenerarResumen (CobroDto cobroDto);
	
	/**
	 * 
	 * @param cobroDto
	 * @return
	 */
	public boolean mostrarBtnGenerarCartayResumen (CobroDto cobroDto);

	/**
	 * 
	 * @param cobro
	 * @throws ValidacionExcepcion
	 * @throws NegocioExcepcion 
	 */
	void validarDisponibilidadSaldoValoresEnProceso(CobroDto cobro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param clientes
	 * @return
	 * @throws NegocioExcepcion
	 */
	public String buscarCuentaContablePorDefault(Set<ClienteDto> clientes) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idAdjunto
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ComprobanteDto> buscarAdjuntoPorIdAdjunto(Long idAdjunto) throws NegocioExcepcion;

	/**
	 * 
	 * @param idCobro
	 * @param userSesion
	 * @param observacion
	 * @param sociedad
	 * @param sistema
	 * @throws NegocioExcepcion
	 */
	void confirmaAplicacionManual(Long idCobro, UsuarioSesion userSesion,String observacion, SociedadEnum sociedad, SistemaEnum sistema, Set<CodigoOperacionExternaDto> listaCodigoOperacionesExternas) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idCobro
	 * @param userSesion
	 * @param observacion
	 * @param sociedad
	 * @param sistema
	 * @throws NegocioExcepcion
	 */
	public void informaDesapropiacionAplicacionManual(Long idCobro, UsuarioSesion userSesion, String observacion, SociedadEnum sociedad, SistemaEnum sistema) throws NegocioExcepcion;
    
	/**
     * 
     * @param cobro
     * @return
     * @throws PersistenciaExcepcion
     * @throws NegocioExcepcion
     */
	public TransaccionesJsonResponse buscarTransaccionesApliManual(CobroDto cobro) throws PersistenciaExcepcion, NegocioExcepcion;
	
	/**
	 * 
	 * @param idCobroPadre
	 * @param idTarea
	 * @param usuarioModificacion
	 * @throws NegocioExcepcion
	 */
	public void eliminarTarea(long idCobroPadre, Long idTarea, String usuarioModificacion) throws NegocioExcepcion;

	/**
	 * 
	 * @param cobroDto
	 * @return
	 * @throws NegocioExcepcion
	 */
	public String obtenerObservacionHistorialYObservacionTarea(CobroDto cobroDto) throws NegocioExcepcion;

	/**
	 * 
	 * @param comprobante
	 * @return
	 * @throws NegocioExcepcion 
	 */
	public ShvParamTipoComprobante buscarComprobante(TipoComprobanteEnum comprobante) throws NegocioExcepcion;
}