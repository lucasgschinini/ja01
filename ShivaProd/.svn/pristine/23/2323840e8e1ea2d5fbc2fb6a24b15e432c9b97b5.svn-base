package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.dto.InterdepositoDto;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.negocio.dto.TransferenciaDto;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamCuit;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteRegistrosAvcSinConciliarFiltro;

public interface IRegistroAVCServicio {

	/**
	 * 
	 * @param registroAvc
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvAvcRegistroAvc establecerRegistroConciliado(RegistroAVCDto registroAvc) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param registroAvc
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvAvcRegistroAvc establecerRegistroConciliacionSugerida(RegistroAVCDto registroAvc) throws NegocioExcepcion;

	/**
	 * 
	 * @param registroAvc
	 * @param codigoCliente
	 * @throws NegocioExcepcion
	 */
	void establecerRegistroConciliadoConDiferencia(RegistroAVCDto registroAvc, String codigoCliente) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param registroAvc
	 * @throws NegocioExcepcion
	 */
	void deshacerConciliacionSugerida(RegistroAVCDto registroAvc) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @param conReversion
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<RegistroAVCDto> listarRegistrosAVCSinConciliar(VistaSoporteRegistrosAvcSinConciliarFiltro filtro, boolean conReversion) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws NegocioExcepcion
	 */
	RegistroAVCDto buscarRegistroAVC(String id) throws NegocioExcepcion;

	/**
	 * 
	 * @param idRegistro
	 * @return
	 * @throws NegocioExcepcion
	 */
	ValorDto buscarRegistroAvcYMapearValorDto(String idRegistro) throws NegocioExcepcion;

	/**
	 * 
	 * @param transferenciaDto
	 * @throws NegocioExcepcion
	 */
	void actualizarCuitYObservacionDeTransferencia(TransferenciaDto transferenciaDto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idRegistro
	 * @param observaciones
	 * @param user
	 * @throws NegocioExcepcion
	 */
	void anularRegistroAVCSinConciliar(String idRegistro, String observaciones, UsuarioSesion user) throws NegocioExcepcion;
	
	/**
	 * Anulación masiva de registros AVC sin conciliar
	 * @param idsRegistros ids de los registros a anular separados por coma ","
	 * @param observacion observación del usuario
	 * @param usuario usuario que lleva a cabo la acción 
	 * @throws NegocioExcepcion
	 */
	void anularRegistrosAVCSinConciliar(String idsRegistros, String observacion, String usuario) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param registroDto
	 * @throws NegocioExcepcion
	 */
	void confirmarAnulacionRegistroAvc(RegistroAVCDto registroDto) throws NegocioExcepcion;

	/**
	 * 
	 * @param registroDto
	 * @throws NegocioExcepcion
	 */
	void rechazarPedidoDeAnulacionRegistroAvc(RegistroAVCDto registroDto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idRegistro
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ComprobanteDto> buscarDocumentosAdjuntosPorIdTransferencia(String idRegistro) throws NegocioExcepcion;

	/**
	 * 
	 * @param transferenciaDto
	 * @throws NegocioExcepcion
	 */
	void validacionModificacionTransferencia(TransferenciaDto transferenciaDto) throws NegocioExcepcion;

	/**
	 * 
	 * @param valorDto
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvValValor crearValorAPartirRegistroAvc(ValorDto valorDto) throws NegocioExcepcion;

	/**
	 * 
	 * @param registroAvc
	 * @throws NegocioExcepcion
	 */
	void solicitarReconfirmacionAltaValor(RegistroAVCDto registroAvc) throws NegocioExcepcion;

	/**
	 * 
	 * @param valorDto
	 * @param userSesion
	 * @throws NegocioExcepcion
	 */
	void generarTarea(ValorDto valorDto, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	/**
	 * Acciones desde la bandeja
	 */
	
	/**
	 * 
	 * @param valorDto
	 * @throws NegocioExcepcion
	 */
	void rechazarTareaAltaValorAPartirRegistroAvc(ValorDto valorDto, UsuarioSesion usuarioSesion) throws NegocioExcepcion;

	
	/**
	 * 
	 * @param valorDto
	 * @throws NegocioExcepcion
	 */
	void confirmarTareaAltaValorAPartirRegistroAvc(ValorDto valorDto) throws NegocioExcepcion;

	
	/**
	 * Acciones sobre un registro AVC
	 */
	
	/**
	 * 
	 * @param registroAvcDto
	 * @throws NegocioExcepcion
	 */
	void establecerRegistroAltaValorRechazada(RegistroAVCDto registroAvcDto) throws NegocioExcepcion;

	/**
	 * 
	 * @param registroAvcDto
	 * @throws NegocioExcepcion
	 */
	void establecerRegistroConciliadoSinBoleta(RegistroAVCDto registroAvcDto) throws NegocioExcepcion;

	/**
	 * 
	 * @param idRegistro
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvAvcRegistroAvcValor buscarRegistroAVCValor(String idRegistro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	void conciliarInterdepositoSinBoleta(InterdepositoDto dto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idRegistro
	 * @param valor
	 * @throws NegocioExcepcion
	 */
	void guardarRelacionConValor(Long idRegistro, ShvValValor valor) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param interdepositoDto
	 * @param valor
	 * @throws NegocioExcepcion
	 */
	void procesarInterdepositoValor(InterdepositoDto interdepositoDto, ShvValValor valor) throws NegocioExcepcion;

	/**
	 * 
	 * @param idRegistroAvc
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvAvcRegistroAvc buscarRegistroAVCModelo(String idRegistroAvc) throws NegocioExcepcion;

	/**
	 * 
	 * @param modelo
	 * @throws NegocioExcepcion
	 */
	void actualizarRegistroAvc(ShvAvcRegistroAvc modelo) throws NegocioExcepcion;

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<Estado> listarComboEstadosRegistrosAVC() throws NegocioExcepcion;

	/**
	 * 
	 * @param registroAvc
	 * @param usuarioModificacion
	 * @throws NegocioExcepcion
	 */
	void conciliarTransferenciaSinBoleta(ShvAvcRegistroAvc registroAvc, String usuarioModificacion) throws NegocioExcepcion;

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<RegistroAVCDto> listarRegistrosDepositoPendientesConciliar() throws NegocioExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<RegistroAVCDto> listarRegistrosInterdepositoYTransferenciasPendientesConciliar() throws NegocioExcepcion;

	/**
	 * 
	 * @param cuit
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvParamCuit buscarCuitTablaParametrica(String cuit) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param valorDto
	 * @param usuarioSesion
	 * @throws NegocioExcepcion
	 */
	public void eliminarTareaAceptarAltaValorAPartirRegistroAvc(ValorDto valorDto, UsuarioSesion usuarioSesion) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idRegistro
	 * @param usuarioSesion
	 * @throws NegocioExcepcion
	 */
	public void eliminarTareaRevisarAnulacionRegistroAVC(String idRegistro, UsuarioSesion usuarioSesion) throws NegocioExcepcion;

}
