package ar.com.telecom.shiva.negocio.servicios;

import org.hibernate.stat.Statistics;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

public interface IParametroServicio {

	String getValorTexto(String clave) throws NegocioExcepcion;
	
	void setValorTexto(String clave, String valorTexto) throws NegocioExcepcion;
	
	Long getValorNumerico(String clave) throws NegocioExcepcion;
	
	Statistics getEstadisticas() throws NegocioExcepcion;
	
	Long getLimiteParaExportarBusquedaValores() throws NegocioExcepcion;
	
	Long getLimiteParaExportarBusquedaCobros() throws NegocioExcepcion;

	Long getCantRegistrosAProcesar() throws NegocioExcepcion;

	Long getCantRegistrosPorVuelta() throws NegocioExcepcion;
}