package ar.com.telecom.shiva.negocio.servicios;

import java.util.GregorianCalendar;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ReintentoExcepcion;
import ar.com.telecom.shiva.negocio.batch.bean.SubdiarioBatch;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ImputacionCobroRto;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaCobrosPendientes;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobCobroSimple;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobCobroSimpleSinWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;

public interface ICobroImputacionServicio extends IServicio {
	
	public void imputarCobro(ImputacionCobroRto datosEntrada) throws NegocioExcepcion;
	
	void reenviarConfirmacionCobros(Long idOperacion) throws NegocioExcepcion;
		
	ShvCobCobro buscarCobroPorIdOperacion(Long idOperacion) throws NegocioExcepcion;
	
	ShvCobCobroSimple buscarCobroSimplePorIdOperacion(Long idOperacion) throws NegocioExcepcion;
	
	ShvCobCobroSimple modificar(ShvCobCobroSimple cobroModelo) throws NegocioExcepcion;
	
	ShvCobCobroSimpleSinWorkflow buscarCobroSimpleSinWorkflowPorIdOperacion(Long idOperacion) throws NegocioExcepcion;
	
	ShvCobCobroSimpleSinWorkflow modificar(ShvCobCobroSimpleSinWorkflow cobroModelo) throws NegocioExcepcion;
	
	List<ShvCobFactura> buscarFacturaParaReporteRetencion(List<String> idRetenciones) throws NegocioExcepcion;

	List<SubdiarioBatch> listarCobrosParaSubdiario(GregorianCalendar fechaUltimoDiaMes) throws NegocioExcepcion;
	
	ShvCobMedioPago getMedioPagoShivaPorIdValor(ShvCobCobro cobro, Long idValor);
	
	/**
	 * Me devuelve la lista de cobros pendientes de imputacion
	 * @return
	 * @throws NegocioExcepcion
	 */
//	public List<Map<String,Object>> listarCobrosPendientesImputacion() throws NegocioExcepcion;

	/**
	 * Me permite buscar un cobro
	 * @param idCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvCobCobro buscarCobro(Long idCobro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idCobrosADesapropiar
	 * @param usuarioId
	 * @throws NegocioExcepcion
	 */
	public void solicitarEnviarDesapropiar(List<String> idCobrosADesapropiar, String usuarioId) throws NegocioExcepcion;
	
	/**
	 * Chequea que las transacciones NO posear facturas, medios de pago o tratamientos MIC
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	public boolean contieneTransaccionesMic(ShvCobCobro cobro) throws NegocioExcepcion;

	/**
	 * 
	 * @param tipoImputacionEnum
	 * @return
	 * @throws NegocioExcepcion
	 */
	public Integer hayHilosCobrosVivos(TipoImputacionEnum tipoImputacionEnum) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param tipoImputacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ResultadoBusquedaCobrosPendientes> listarCobrosPendientesImputacionManual(TipoImputacionEnum tipoImputacion) throws NegocioExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @param usuario
	 * @param e
	 * @param errorEnApropiacion
	 * @param transaccion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvCobCobro setearEstadoCobroPorCancelacion(Long idOperacion, String usuario, ReintentoExcepcion e, boolean errorEnApropiacion,
			ShvCobTransaccionSinOperacion transaccion) throws NegocioExcepcion;
}
