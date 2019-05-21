package ar.com.telecom.shiva.negocio.servicios;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

public interface IReporteRegistrosAVCPendienteConciliarServicio {

	public void generarArchivoReporteAvcPendienteConciliar(String fechaHasta) throws NegocioExcepcion;
}
