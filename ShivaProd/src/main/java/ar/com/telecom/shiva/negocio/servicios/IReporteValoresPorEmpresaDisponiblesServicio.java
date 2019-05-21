package ar.com.telecom.shiva.negocio.servicios;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

/**
 * 
 * @author U587496 Guido.Settecerze
 *
 */
public interface IReporteValoresPorEmpresaDisponiblesServicio extends IServicio {

	
	public void generarArchivoReporteValoresPorEmpresaDisponibles(String fechaHasta) throws NegocioExcepcion;


}