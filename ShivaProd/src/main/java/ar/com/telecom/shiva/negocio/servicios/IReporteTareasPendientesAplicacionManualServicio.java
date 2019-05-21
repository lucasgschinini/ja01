package ar.com.telecom.shiva.negocio.servicios;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

/**
 * 
 * @author u610512 MDB
 *
 */
public interface IReporteTareasPendientesAplicacionManualServicio {
	
	public void buscarTareasPendientesAplicacionManual(String fechaHasta) throws NegocioExcepcion;
}
