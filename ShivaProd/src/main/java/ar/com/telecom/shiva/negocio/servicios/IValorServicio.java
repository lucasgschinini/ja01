package ar.com.telecom.shiva.negocio.servicios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MotivoSuspensionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.negocio.batch.bean.SaldoADescontarImputacionBatch;
import ar.com.telecom.shiva.negocio.dto.DepositoDto;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValValorSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ActualizacionExitosaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ContabilidadDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorHistoricoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValoresDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaConValorFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;


public interface IValorServicio extends IServicio {

	/**
	 * 
	 * @param valValorViejo
	 * @param agregar
	 * @throws NegocioExcepcion
	 */
	void actualizarEstadoRecibo(HashMap<String, List<List<ShvValValor>>> listaValoresRecibo, ShvValValor valValorViejo, boolean agregar) throws NegocioExcepcion;

	ShvValValor crearValor(ValorDto valorDto) throws NegocioExcepcion;

	public String listar10CodigosClienteLegadoUsuario(String usuarioLogueado) throws NegocioExcepcion;

	public String listar10CodigosClienteLegadoBoleta(String usuarioLogueado) throws NegocioExcepcion;
	
	public String listar10CodigosClienteLegadoAviso(String usuarioLogueado) throws NegocioExcepcion;

	public ActualizacionExitosaDto administrarValores(ValoresDto valoresDTO, UsuarioSesion userSesion) throws PersistenciaExcepcion, NegocioExcepcion;	
	
	public boolean validarUnicidadRecibo(ValoresDto valoresDTO, UsuarioSesion userSesion) throws PersistenciaExcepcion, NegocioExcepcion;	
	
	/**
	 * Req 67878 Cablevision Release 1 - Mejoras de performance en Valores - consultas para validacion de unicidad. 
	 * Este método se debe eliminar cuando se habilite la mejora en el próximo Release
	 * 
	 * @param valorDto
	 * @return
	 * @throws NegocioExcepcion
	 */
	boolean reValidarUnicidadValor(ValorDto valorDto) throws NegocioExcepcion;
	
	/**
	 * Req 67878 Cablevision Release 1 - Mejoras de performance en Valores - consultas para validacion de unicidad. 
	 * Este método es el que debe quedar cuando se habilite la mejora en el próximo Release, eliminando los anteriores.
	 * 
	 * @param valorDto
	 * @param incluirValorActualEnComparacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean validarUnicidadValor(ValorDto valorDto, boolean incluirValorActualEnComparacion) throws NegocioExcepcion;
	
	public ValorDto validarRecibo(ValorDto valorDTO) throws NegocioExcepcion;
	
	public ArrayList<ValorDto> buscarValores(BoletaConValorFiltro boletaFiltro) throws NegocioExcepcion;
	
	public ArrayList<ValorDto> buscarValores(UsuarioSesion userSesion, BoletaConValorFiltro boletaFiltro) throws NegocioExcepcion;	
	
	public ArrayList<ValorDto> buscarValoresParaAutorizacion(UsuarioSesion userSesion, BoletaConValorFiltro boletaFiltro) throws NegocioExcepcion;
	
	public List<String> autorizarBoleta(UsuarioSesion userSesion, String idAmodificar, String observaciones) throws NegocioExcepcion;
	
	public void rechazarAutorizacionBoleta(UsuarioSesion userSesion, String idAmodificar, String observaciones) throws NegocioExcepcion;

	public ArrayList<ValorDto> buscarParaCambioEstado(UsuarioSesion userSesion, List<Long> idValores) throws NegocioExcepcion;
	
	public ArrayList<ValorDto> buscarValoresEnVista(UsuarioSesion userSesion,List<Long> idValores) throws NegocioExcepcion;
	
	public void actualizarEstado(BoletaConValorFiltro boletaConValorFiltro, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public ValorDto buscarValorModificacion(String idValor) throws NegocioExcepcion;
	
	public ValorDto buscarValorModificacion(ShvValValor valorModelo) throws NegocioExcepcion;
	
	public ActualizacionExitosaDto administrarModificar(ValorDto DTO, UsuarioSesion user) throws NegocioExcepcion;

	public List<ValorHistoricoDto>  obtenerHistorial(ValorDto valorDto)throws NegocioExcepcion;

	boolean validarDuplicidadAlta(ValoresDto valoresDto, UsuarioSesion userSesion) throws NegocioExcepcion, PersistenciaExcepcion;
	
	String armarMensajeDuplicado(ValorDto valorDtoDuplicado) throws PersistenciaExcepcion;
	
	String armarMensajeDuplicadoZE(VistaSoporteResultadoBusquedaValor vistaSoporteResultadoBusquedaValor) throws PersistenciaExcepcion;
	
	ValorDto validarDuplicados(String usuarioModificacion, ValorDto valorDTO) throws PersistenciaExcepcion;
	
	public List<ValorDto> buscarChequesReemplazar(String codigoCliente) throws NegocioExcepcion;
	
	void arreglarFechasCortas(ValorDto valorDto) throws NegocioExcepcion;
	
	public List<ShvValValor> buscarValores(List<Long> idValores) throws NegocioExcepcion;

	public ShvValValorSimplificado actualizarSaldoPorApropiacion(ShvValValorSimplificado valor, SaldoADescontarImputacionBatch saldo) throws NegocioExcepcion;
	
	public List<String> busquedaMailBD(List<Long> idValores, String idUsuarioEnvio) throws NegocioExcepcion, NumberFormatException, PersistenciaExcepcion;
	
	
	public ActualizacionExitosaDto busquedaImprimirConstancia(ArrayList<ValorDto> listaDeValores) throws NegocioExcepcion;
	
	public void confirmarAvisoDePago(DTO dto) throws NegocioExcepcion;
	
	public void rechazarAvisoDePago(DTO dto, UsuarioSesion usuarioSesion) throws NegocioExcepcion;
	
	public void anularAvisoDePago(DTO dto) throws NegocioExcepcion;
	
	public void anularBoleta(DTO dto) throws NegocioExcepcion;
	
	public void migrarConstancia(Long id, String nroConstancia) throws NegocioExcepcion;
	
	public ShvValValorSimplificado revertirValoresPertenecientesATransaccion(ShvValValorSimplificado valor, BigDecimal importe, ShvCobCobro cobro, Integer numeroTransaccion, boolean desapropiacion) throws NegocioExcepcion;

	public void disponibilizarValorAsociadoBoleta(Long idValorAsociado, Date fechaDepositoRegistroAvc,HashMap<String, List<String>> cuerpoMailMap, Mail asunto) throws NegocioExcepcion;
	
	public void asociarValorConBoleta(ShvValValor valorModelo, Long idBoleta) throws NegocioExcepcion;
	
	public ShvValValor buscarValValorPorIdValor(Integer idValor)  throws NegocioExcepcion;

	public ShvValValor establecerValorConciliadoConDiferencia(BoletaSinValorDto boletaDto, String usuarioModificacion, DepositoDto registroAvc) throws NegocioExcepcion;
	
	public void armarYEnviarMailConfirmacionValor(ShvValValor shvValValor, String observaciones) throws NegocioExcepcion;
	
	public void mailValorConciliacion(ShvValValor shvValValor, HashMap<String, List<String>> cuerpoMailMap) throws NegocioExcepcion;
	
	public void enviarMailValor(String idAnalista, String idCopropietario,String teamComercial,String cuerpo, String asunto) throws NegocioExcepcion;
	
	public ShvValValor actualizarWorkflowDeValorADisponible(ValorDto valorDto, String usuarioModificacion) throws NegocioExcepcion;

	public List<ShvValValor> validarUnicidadValor(ValorDto valorDto) throws NegocioExcepcion;

	public ValorDto buscarValorCreadoAPartirRegistroAvc(String idRegistro)throws NegocioExcepcion;

//	void contabilidad(ShvValValor valor,boolean valorPorReversion) throws NegocioExcepcion;
	
	//ContabilidadDto contabilizarValorShiva(Long idValor) throws NegocioExcepcion;

	ContabilidadDto contabilizarValor(ShvValValor valorModelo) throws NegocioExcepcion;
	
	public Long crearValorAux(DTO dto) throws NegocioExcepcion;

	public void generarReporteMorosidad(String fechaHasta) throws NegocioExcepcion;
	
	public ShvValValor buscarValorPorIdBoleta(Long idBoleta) throws NegocioExcepcion;
	
	public ShvWfWorkflow anularValorBoletaPendienteConciliacion(ShvValValor valor, String datosModificados, String usuarioModificacion, HashMap<String,List<List<ShvValValor>>> listaValoresRecibo) throws WorkflowExcepcion;

	public List<ShvValValor> listarRetencionesParaReporte(Filtro retencionFiltro) throws NegocioExcepcion;
	
	List<List<ShvValValor>> valoresDeUnRecibo(String numeroRecibo) throws NegocioExcepcion;
	
	public Date calcularFechaValor(TipoValorEnum tipoValor, Integer idOrigen,  
			Date fechaAlta, Date fechaDeposito, Date fechaVencimiento, 
			Date fechaRecibo, Date fechaTransferencia, Date fechaNotificacionDisponibilidadRetiroValor, Date fechaEmision);
	
	public Date calcularFechaValor(ShvValValor valor,Date fechaDepositoRegistroAvc);
	
	public ShvValValor actualizarValor(ShvValValor valorModelo) throws NegocioExcepcion;
	
	public ShvValValor setearNumeroValor(ShvValValor valorViejo, ShvValValor valorNuevo) throws NegocioExcepcion;
	
	public List<ShvValValorSimplificado> listarValoresSimplificados(List<Long> idValores) throws NegocioExcepcion;
	
	public ShvValValorSimplificado buscarValorSimplificado(Long idValor) throws NegocioExcepcion;
	
	public ShvValValorSimplificado actualizarValorSimplificado(ShvValValorSimplificado valor) throws NegocioExcepcion;
	
	public ValoresDto validarAltaValoresSimultaneo(ValoresDto valoresDto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idValor
	 * @param observaciones
	 * @param idUsuario
	 * @param motivoSuspension
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	public ShvValValor suspenderValor(Long idValor, String observaciones, String idUsuario, MotivoSuspensionEnum motivoSuspension, Date fechaRechazo) throws NegocioExcepcion, PersistenciaExcepcion;
	
	/**
	 * 
	 * @param valorSimplificado
	 * @param observaciones
	 * @param idUsuario
	 * @param motivoSuspension
	 * @return
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	public ShvValValor suspenderValor(ShvValValor valor, String observaciones, String idUsuario, MotivoSuspensionEnum motivoSuspension, Date fechaRechazo) throws NegocioExcepcion, PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idValor
	 * @param observaciones
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	public ShvValValor anularValor(Long idValor, String observaciones, String idUsuario) throws NegocioExcepcion, PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idValor
	 * @param observaciones
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	public ShvValValor anularValor(Long idValor, String observaciones, String idUsuario, Date fechaRechazo) throws NegocioExcepcion, PersistenciaExcepcion;
	
	/**
	 * 
	 * @param val
	 * @param observaciones
	 * @param idUsuario
	 * @return
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	public ShvValValor anularValor(ShvValValor val, String observaciones, String idUsuario) throws NegocioExcepcion, PersistenciaExcepcion;
	
	/**
	 * 
	 * @param val
	 * @param observaciones
	 * @param idUsuario
	 * @param fechaRechazoLegajoChequeRechazado
	 * @return
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	public ShvValValor anularValor(ShvValValor val, String observaciones, String idUsuario, Date fechaRechazo) throws NegocioExcepcion, PersistenciaExcepcion;
	
	/**
	 * 
	 * @param idValor
	 * @param observaciones
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	public ShvValValor disoniblizarValor(Long idValor, String observaciones, String idUsuario) throws NegocioExcepcion, PersistenciaExcepcion;
	
	
	/**
	 * 
	 * @param idValor
	 * @param observaciones
	 * @param idUsuario
	 * @param fechaRechazo
	 * @return
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	public ShvValValor disoniblizarValor(Long idValor, String observaciones, String idUsuario, Date fechaRechazo) throws NegocioExcepcion, PersistenciaExcepcion;
	
	/**
	 * 
	 * @param val
	 * @param observaciones
	 * @param idUsuario
	 * @return
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	public ShvValValor disponibilizarValor(ShvValValor val, String observaciones, String idUsuario) throws NegocioExcepcion, PersistenciaExcepcion;	

	/**
	 * 
	 * @param val
	 * @param observaciones
	 * @param idUsuario
	 * @param fechaRechazo
	 * @return
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	public ShvValValor disponibilizarValor(ShvValValor val, String observaciones, String idUsuario, Date fechaRechazo) throws NegocioExcepcion, PersistenciaExcepcion;

	ActualizacionExitosaDto busquedaImprimirBD(List<Long> idValores,
			String usuario) throws NegocioExcepcion, NumberFormatException, PersistenciaExcepcion;
	
	/**
	 * 
	 * @param valor
	 * @param transaccion
	 * @param importe
	 * @param numeroTransaccion
	 * @param desapropiacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvValValorSimplificado revertirValoresPertenecientesAGrupo(ShvValValorSimplificado valor, ShvCobTransaccionSinOperacion transaccion, BigDecimal importe, Integer numeroTransaccion, boolean desapropiacion) throws NegocioExcepcion;
	
	/**
	 * @param factura
	 * @return
	 * @throws NegocioExcepcion
	 */
	public String generarIdFacturaParaMostrar(ShvCobFacturaSinOperacion factura) throws NegocioExcepcion;

}