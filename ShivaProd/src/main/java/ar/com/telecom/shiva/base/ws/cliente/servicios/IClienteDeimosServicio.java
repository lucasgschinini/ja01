package ar.com.telecom.shiva.base.ws.cliente.servicios;

import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosConsultaEstadoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosConsultaEstadoDocumentoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.util.IDatosComunesEntrada;

public interface IClienteDeimosServicio {

	/**
	 * WS: Deimos 
	 * Proveedor: Deimos
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaDeimosConsultaEstadoDocumentoWS consultarEstadoDocumento(EntradaDeimosConsultaEstadoDeudaWS entradaWS) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param entradaWS
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaDeimosConsultaEstadoDocumentoWS consultarDeimos(IDatosComunesEntrada entradaDeimos, EmpresaEnum empresa) throws NegocioExcepcion;

	/**
	 * 
	 * @param entrada
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaDeimosApropiacionWS apropiarDocumento(EntradaDeimosApropiacionWS entrada) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param entrada
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaDeimosDesapropiacionWS desapropiarDocumento(EntradaDeimosDesapropiacionWS entrada) throws NegocioExcepcion;
	
}
