package ar.com.telecom.shiva.negocio.servicios.validacion;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteOperacionMasivaFiltro;


public interface IOperacionMasivaValidacionServicio {

	void validacionEmpresa(String empresa) throws ValidacionExcepcion;
	void validacionSegmento(String segmento) throws ValidacionExcepcion;
	void validacionMotivo(String motivo) throws ValidacionExcepcion;
	
	OperacionMasivaDto validarDuplicidad(OperacionMasivaDto operacionMasiva) throws NegocioExcepcion;
	
	
	/**
	 * Permite validar el filtro para la busqueda de operacionesMasivas
	 * @param operacionMasivaFiltro
	 * @throws ValidacionExcepcion
	 */
	void validarFiltroBusquedaOperacionesMasivas(VistaSoporteOperacionMasivaFiltro operacioMasivaFiltro) throws ValidacionExcepcion;
	
	 /**
	  * 
	  * @param operacionMasivaFiltro
	  * @param b
	  * @return
	  * @throws NegocioExcepcion
	  */
	 public List<OperacionMasivaDto> listarOperacionesMasivas(VistaSoporteOperacionMasivaFiltro operacionMasivaFiltro) throws NegocioExcepcion; // buscar cobros

}
