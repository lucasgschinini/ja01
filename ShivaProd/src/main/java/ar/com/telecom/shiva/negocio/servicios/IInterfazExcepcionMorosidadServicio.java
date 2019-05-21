package ar.com.telecom.shiva.negocio.servicios;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;


public interface IInterfazExcepcionMorosidadServicio {
	
	
	
	/**
	 * Busca en la vista SHV_SOP_EXCEPCION_MOROSIDAD los registros a procesar filtrando por la fecha hasta
	 * y genera el archivo para enviar a GEO.
	 */
	public void generarArchivoExcepcionMorosidad(String fecha) throws NegocioExcepcion;
}
