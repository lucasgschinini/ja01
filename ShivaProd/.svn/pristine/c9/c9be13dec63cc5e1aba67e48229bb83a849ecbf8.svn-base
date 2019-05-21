package ar.com.telecom.shiva.negocio.servicios;

import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ReintentoExcepcion;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCargosWS;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoCTASinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTratamientoDiferenciaSinOperacion;

public interface ICobroBatchSoporteImputacionCalipsoServicio {
	
	/**
	 * 
	 * @param listaMediosPagoAEnviar
	 * @param factura
	 * @param transaccion
	 * @param fechaCobranza
	 * @param monedaOperacion
	 * @return
	 * @throws NegocioExcepcion
	 * @throws ReintentoExcepcion
	 * @throws PersistenciaExcepcion
	 */
	public Boolean apropiacionCalipso(List<ShvCobMedioPagoSinOperacion> listaMediosPagoAEnviar, 
			ShvCobFacturaSinOperacion factura, ShvCobTransaccionSinOperacion transaccion, Date fechaCobranza, MonedaEnum monedaOperacion) 
					throws NegocioExcepcion, ReintentoExcepcion, PersistenciaExcepcion;
	
	/**
	 * 
	 * @param mpProxFactura
	 * @param tratamientoDiferencia
	 * @param entradaCalipsoCargo
	 * @return
	 * @throws NegocioExcepcion
	 * @throws ReintentoExcepcion
	 */
	public boolean generarCargoReintegroCalipso(ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion mpProxFactura,
			ShvCobTratamientoDiferenciaSinOperacion tratamientoDiferencia, EntradaCalipsoCargosWS entradaCalipsoCargo) throws NegocioExcepcion, ReintentoExcepcion;
	
	/**
	 * 
	 * @param listaOperacionTransacciones
	 * @param cobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public String confirmacionCalipso(List<String> listaOperacionTransacciones, ShvCobCobro cobro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param listaOperacionTransacciones
	 * @param cobro
	 * @param listaIdMovMer
	 * @return
	 * @throws NegocioExcepcion
	 */
	public String desapropiacionCalipso(List<String> listaOperacionTransacciones, ShvCobCobro cobro, List<String> listaIdMovMer)  throws NegocioExcepcion;
	
	/**
	 * 
	 * @param listaTransacciones
	 * @param listaIdMovMer
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public String desapropiacionParcialCalipso(List<ShvCobTransaccionSinOperacion> listaTransacciones,	List<String> listaIdMovMer, Long idOperacion) throws NegocioExcepcion;

	/**
	 * 
	 * @param idOperacion
	 * @param numeroTransaccion
	 * @param medPagoCta
	 * @param medPagoCtaNueva
	 * @throws NegocioExcepcion
	 */
	public void setearIdCalipsoALosDemasMediosPago(Long idOperacion, Integer numeroTransaccion, ShvCobMedioPagoCTASinOperacion medPagoCta, ShvCobMedioPagoCTASinOperacion medPagoCtaNueva) throws NegocioExcepcion;

}
