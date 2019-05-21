package ar.com.telecom.shiva.negocio.servicios;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;

public interface ICobroBatchSoporteImputacionContabilidadServicio {
	
	/**
	 * 
	 * @param factura
	 * @param esCobro
	 * @param usuarioAnalista
	 * @param idCobro
	 * @param transaccion
	 * @param analistaCobro
	 * @throws NegocioExcepcion
	 */
	public void contabilizarFactura(ShvCobFactura factura, boolean esCobro, String usuarioAnalista, Long idCobro, String transaccion, String analistaCobro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param factura
	 * @param esCobro
	 * @param usuarioAnalista
	 * @param idCobro
	 * @param transaccion
	 * @param analistaCobro
	 * @throws NegocioExcepcion
	 */
	public void contabilizarFacturaEnMonedaOrigen(ShvCobFacturaCalipso factura, boolean esCobro, String usuarioAnalista, Long idCobro, String transaccion, String analistaCobro) throws NegocioExcepcion;

	/**
	 * 
	 * @param medioPago
	 * @param cobro
	 * @param usuarioAnalista
	 * @param idCobro
	 * @param transaccion
	 * @param valor
	 * @param hayDiferenciaCambio
	 * @throws NegocioExcepcion
	 */
	public void contabilizarMedioPagoAsociadoFactura(ShvCobMedioPago medioPago, boolean cobro, String usuarioAnalista, Long idCobro, String transaccion, VistaSoporteResultadoBusquedaValor valor, boolean hayDiferenciaCambio) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param medioPago
	 * @param esCobro
	 * @param usuarioAnalista
	 * @param cobro
	 * @param transaccion
	 * @param idDescobro
	 * @param valor
	 * @throws NegocioExcepcion
	 */
	public void contabilizarMedioPagoAsociadoTratamiento(ShvCobMedioPago medioPago, boolean esCobro, String usuarioAnalista, ShvCobCobro cobro, String transaccion, Long idDescobro, VistaSoporteResultadoBusquedaValor valor) throws NegocioExcepcion;

	/**
	 * 
	 * @param factura
	 * @param importeEnPesos
	 * @param idCobro
	 * @param transaccion
	 * @param usuarioAnalista
	 * @param esCobro
	 * @throws NegocioExcepcion
	 */
	public void contabilizarDocumentoEnDolar(ShvCobFactura factura, BigDecimal importeEnPesos, Long idCobro, String transaccion, String usuarioAnalista, boolean esCobro) throws NegocioExcepcion;

	/**
	 * 
	 * @param medioPago
	 * @return
	 * @throws NegocioExcepcion
	 */
	public BigDecimal calcularTipoCambioMedioPago(ShvCobMedioPago medioPago) throws NegocioExcepcion;

	/**
	 * 
	 * @param medioPago
	 * @param esCobro
	 * @param usuarioAnalista
	 * @param cobro
	 * @param transaccion
	 * @param idDescobro
	 * @param valor
	 * @throws NegocioExcepcion
	 */
	void contabilizarMedioPagoAsociadoFacturaUsuario(ShvCobMedioPago medioPago,
													 boolean esCobro, String usuarioAnalista, ShvCobCobro cobro,
													 String transaccion, Long idDescobro,
													 VistaSoporteResultadoBusquedaValor valor) throws NegocioExcepcion;

}
