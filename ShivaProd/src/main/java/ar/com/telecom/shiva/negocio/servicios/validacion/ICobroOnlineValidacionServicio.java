package ar.com.telecom.shiva.negocio.servicios.validacion;

import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.negocio.bean.ImportacionDebitosAuxiliar;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ImportarDebitoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroCreditoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroDebitoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroDocCapFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteCobroOnlineFiltro;

public interface ICobroOnlineValidacionServicio {
	/**
	 * Permite validar el filtro para la busqueda de debitos
	 * @param confCobroClienteFiltro
	 * @throws ValidacionExcepcion
	 */
	void validarFiltroDebitos(ConfCobroDebitoFiltro confCobroDebitoFiltro) throws ValidacionExcepcion;
	
	/**
	 * Permite validar el filtro para la busqueda de creditos
	 * @param confCobroClienteFiltro
	 * @throws ValidacionExcepcion
	 */
	void validarFiltroCreditos(ConfCobroCreditoFiltro confCobroCreditoFiltro) throws ValidacionExcepcion;
	
	/**
	 * Permite validar los archivos
	 * @param file
	 * @throws ValidacionExcepcion
	 */
	void validarComprobantes(MultipartFile file, String descripcion) throws ValidacionExcepcion, NegocioExcepcion;
		
	/**
	 * Permite validar el filtro para la busqueda de cobros
	 * @param cobroFiltro
	 * @throws ValidacionExcepcion
	 */
	void validarFiltroBusquedaCobros(VistaSoporteCobroOnlineFiltro cobroFiltro) throws ValidacionExcepcion;
	
	/***********************************************************************************
	 * Otros
	 **********************************************************************************/
	/**
	 * 
	 * @param campos
	 * @param nroRegistro
	 * @return
	 */
	boolean validarDuplicidad (String[] campos, int nroRegistro, ImportacionDebitosAuxiliar importacionDebitosAuxiliar);
	/**
	 * 
	 * @param campos
	 * @param nroRegistro
	 * @return
	 */
	boolean validarDuplicidadEnCobro (String[] campos, int nroRegistro, ImportacionDebitosAuxiliar importacionDebitosAuxiliar);
	
	/**
	 * 
	 * @param campos
	 * @param nroRegistro
	 * @return
	 */
	boolean validarFormato (String[] campos, int nroRegistro, ImportacionDebitosAuxiliar importarDebitosAuxiliar);
	/**
	 * 
	 * @param campos
	 * @param nroRegistro
	 * @return
	 * @throws NegocioExcepcion
	 */
	boolean validarCamposArchivoImportarDebitos(String[] campos, int nroRegistro, ImportacionDebitosAuxiliar importarDebitosAuxiliar) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param file
	 * @return
	 * @throws NegocioExcepcion
	 */
	StringBuffer validarRestriccionesArchivo(MultipartFile file) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param importarDebitoDto
	 * @return
	 */
	ImportarDebitoJsonResponse setErrorsImportarDebitoDto(ImportarDebitoJsonResponse importarDebitoDto, ImportacionDebitosAuxiliar importacionDebitosAuxiliar);
	
	/**
	 * 
	 * @param nroRegistroAux
	 * @return
	 */
	boolean validacionCantDebitosMaximos(int nroRegistroAux, String string, ImportacionDebitosAuxiliar importarDebitosAuxiliar);
	
//	/**
//	 * 
//	 */
//	public void clinearListaDebitos();
//	
//	/**
//	 * 
//	 */
//	public void clinearListaErrores();
	
	public boolean validarRegistroDuc(String[] campos, int nroRegistro, ImportacionDebitosAuxiliar importacionDebitosAuxiliar);
	
	/**
	 * Permite validar el filtro para la busqueda de documentos CAP
	 * @param confCobroDocCapFiltro
	 * @throws ValidacionExcepcion
	 */
	public void validarFiltroDocumentosCap(ConfCobroDocCapFiltro confCobroDocCapFiltro) throws ValidacionExcepcion;
}
