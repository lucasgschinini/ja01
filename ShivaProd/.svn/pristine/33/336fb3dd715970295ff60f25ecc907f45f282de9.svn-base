package ar.com.telecom.shiva.base.ws.cliente.servicios;

import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoConsultaAcuerdoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCreditoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDescobroWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoConsultaAcuerdoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCreditoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDescobroWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDeudaWS;

public interface IClienteCalipsoServicio {

	/**
	 * WS: CalipsoCobranzas (apropiacion)
	 * Proveedor: Calipso
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaCalipsoApropiacionWS apropiarCobro(EntradaCalipsoApropiacionWS entradaCalipsoApropiacionWS) throws NegocioExcepcion;
	
	
	/**
	 * WS: CalipsoCobranzas (confirmacion)
	 * Proveedor: Calipso
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaCalipsoConfirmacionWS confirmarCobro(EntradaCalipsoConfirmacionWS entradaCalipsoConfirmacionWS) throws NegocioExcepcion;
	
	/**
	 * WS: CalipsoCobranzas (desapropiacion)
	 * Proveedor: Calipso
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaCalipsoDesapropiacionWS desapropiarOperacion(EntradaCalipsoDesapropiacionWS entradaCalipsoDesapropiacionWS) throws NegocioExcepcion;
	
	/**
	 * WS: CalipsoConsultaDebitoyCredito (debito)
	 * Proveedor: Calipso
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaCalipsoDeudaWS consultaDebito(EntradaCalipsoDeudaWS entradaCalipsoDebitoWS) throws NegocioExcepcion;
	
	/**
	 * WS: CalipsoConsultaDebitoyCredito (credito)
	 * Proveedor: Calipso
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaCalipsoCreditoWS consultaCredito(EntradaCalipsoCreditoWS entradaCalipsoCreditoWS) throws NegocioExcepcion;
	
	
	/*******************************************************************************
	 * TEST
	 */
	/**
	 * WS: CalipsoCobranzas (apropiacion)
	 * Proveedor: Calipso
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaCalipsoApropiacionWS testApropiarCobro(EntradaCalipsoApropiacionWS entradaCalipsoApropiacionWS) throws NegocioExcepcion;
	
	
	/**
	 * WS: CalipsoCobranzas (confirmacion)
	 * Proveedor: Calipso
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaCalipsoConfirmacionWS testConfirmarCobro(EntradaCalipsoConfirmacionWS entradaCalipsoConfirmacionWS) throws NegocioExcepcion;
	
	/**
	 * WS: CalipsoCobranzas (desapropiacion)
	 * Proveedor: Calipso
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaCalipsoDesapropiacionWS testDesapropiarOperacion(EntradaCalipsoDesapropiacionWS entradaCalipsoDesapropiacionWS) throws NegocioExcepcion;
	
	/**
	 * WS: CalipsoConsultaAcuerdo 
	 * Proveedor: Calipso
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaCalipsoConsultaAcuerdoWS consultaAcuerdo(EntradaCalipsoConsultaAcuerdoWS entrada) throws NegocioExcepcion;
	
	/**
	 * WS: CalipsoCargos
	 * Proveedor: Calipso
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaCalipsoCargosWS generarCargoCalipso(EntradaCalipsoCargosWS entrada, TipoProcesoEnum tipoProceso) throws NegocioExcepcion;
	
	/**
	 * WS: ReversionCobros
	 * Proveedor: Calipso
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaCalipsoDescobroWS descobrarCobro(EntradaCalipsoDescobroWS entrada) throws NegocioExcepcion;
}
