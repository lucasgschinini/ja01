package ar.com.telecom.shiva.negocio.servicios.validacion;

import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoFiltro;

public interface ILegajoChequeRechazadoValidacionServicio {
	
	/**
	 * Permite validar el filtro para la busqueda de cheque
	 * @param confCobroClienteFiltro
	 * @throws ValidacionExcepcion
	 */
	public void validarFiltroCheques(VistaSoporteBusquedaValoresFiltro vistaSoporteBusquedaValoresFiltro) throws ValidacionExcepcion;

	/**
	 * 
	 * @param vistaSoporteLegajoChequeRechazadoFiltro
	 * @throws ValidacionExcepcion
	 */
	public void validarFiltroBusquedaLegajos(VistaSoporteLegajoChequeRechazadoFiltro vistaSoporteLegajoChequeRechazadoFiltro) throws ValidacionExcepcion;
	
	/**
	 * 
	 * @param file
	 * @param descripcion
	 * @throws ValidacionExcepcion 
	 * @throws NegocioExcepcion 
	 */
	public void validarComprobantes(MultipartFile file, String descripcion)throws ValidacionExcepcion, NegocioExcepcion;
	
}
