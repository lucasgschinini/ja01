package ar.com.telecom.shiva.base.ws.cliente.servicios;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapRegistrarCompensacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapVerificacionPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapRegistrarCompensacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapVerificacionPartidasWS;

public interface IClienteSapServicio {
	
	/**
	 * El servicio de registrar compensaciones de SAP es exclusivo para simular
	 * 
	 * @param entrada
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaSapVerificacionPartidasWS verificarPartidas(EntradaSapVerificacionPartidasWS entrada) throws NegocioExcepcion;

	/**
	 * El servicio de registrar compensaciones de SAP es exclusivo para imputar
	 * 
	 * @param entrada
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaSapRegistrarCompensacionWS registrarCompensacion (EntradaSapRegistrarCompensacionWS entrada) throws NegocioExcepcion ;

	/**
	 * Este metodo de consulta de proveedores en SAP posee un tratamiento especial para la simulacion.
	 * Este método no genera mensajería en la tabla de mensajes del cobro, ni tampoco posee logica de reintentos
	 * 
	 * @param entrada
	 * @param idOperacion
	 * @param esSimulacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaSapConsultaProveedoresWS consultarProveedoresParaSimulacion(
			EntradaSapConsultaProveedoresWS entrada, Long idOperacion) throws NegocioExcepcion;

	/**
	 * Este metodo de consulta de proveedores en SAP posee un tratamiento especial para la imputación.
	 * Este método genera mensajería en la tabla de mensajes del cobro, y posee logica de reintentos
	 * 
	 * @param entrada
	 * @param idOperacion
	 * @param esSimulacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaSapConsultaProveedoresWS consultarProveedoresParaImputacion(
			EntradaSapConsultaProveedoresWS entrada, Long idOperacion) throws NegocioExcepcion;
}
