package ar.com.telecom.shiva.presentacion.bean.dto.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.presentacion.bean.dto.LegajoBusquedaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoCobroRelacionadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoNotificacionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto;
/**
 * @author u578936 M.A.Uehara
 *
 */
public class LegajoChequeRechazadoJsonResponse extends JsonResponse {
	private List<LegajoBusquedaDto> resultados = new ArrayList<LegajoBusquedaDto>();
	private LegajoChequeRechazadoDto resultado = new LegajoChequeRechazadoDto();
	private String errorMensaje;
	private ArrayList<ErrorJson> errores;
	private boolean mostrarPopUpLegajo;
	private List<LegajoChequeRechazadoCobroRelacionadoDto> listaCobrosRelacionados = new ArrayList<LegajoChequeRechazadoCobroRelacionadoDto>();
	private List<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto> listaChequeDocumentos = new ArrayList<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto>();
	private Map<String, String> montos = new HashMap<String, String>();
	private List<LegajoChequeRechazadoNotificacionDto> listaLegajoChequeRechazadoNotificacionDto = new ArrayList<LegajoChequeRechazadoNotificacionDto>();
	public LegajoChequeRechazadoJsonResponse() {}
	
	/**
	 * @return the errorMensaje
	 */
	public String getErrorMensaje() {
		return errorMensaje;
	}
	/**
	 * @return the resultado
	 */
	public LegajoChequeRechazadoDto getResultado() {
		return resultado;
	}
	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(LegajoChequeRechazadoDto resultado) {
		this.resultado = resultado;
	}
	/**
	 * @param errorMensaje the errorMensaje to set
	 */
	public void setErrorMensaje(String errorMensaje) {
		this.errorMensaje = errorMensaje;
	}
	/**
	 * @return the errores
	 */
	public ArrayList<ErrorJson> getErrores() {
		return errores;
	}
	/**
	 * @param errores the errores to set
	 */
	public void setErrores(ArrayList<ErrorJson> errores) {
		this.errores = errores;
	}
	/**
	 * @return the mostrarPopUpLegajo
	 */
	public boolean isMostrarPopUpLegajo() {
		return mostrarPopUpLegajo;
	}
	/**
	 * @param mostrarPopUpLegajo the mostrarPopUpLegajo to set
	 */
	public void setMostrarPopUpLegajo(boolean mostrarPopUpLegajo) {
		this.mostrarPopUpLegajo = mostrarPopUpLegajo;
	}
	/**
	 * @return the resultados
	 */
	public List<LegajoBusquedaDto> getResultados() {
		return resultados;
	}
	/**
	 * @param resultados the resultados to set
	 */
	public void setResultados(List<LegajoBusquedaDto> resultados) {
		this.resultados = resultados;
	}

	public List<LegajoChequeRechazadoCobroRelacionadoDto> getListaCobrosRelacionados() {
		return listaCobrosRelacionados;
	}

	public void setListaCobrosRelacionados(List<LegajoChequeRechazadoCobroRelacionadoDto> listaCobrosRelacionados) {
		this.listaCobrosRelacionados = listaCobrosRelacionados;
	}

	/**
	 * @return the montos
	 */
	public Map<String, String> getMontos() {
		return montos;
	}

	/**
	 * @param montos the montos to set
	 */
	public void setMontos(Map<String, String> montos) {
		this.montos = montos;
	}

	/**
	 * @return the listaChequeDocumentos
	 */
	public List<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto> getListaChequeDocumentos() {
		return listaChequeDocumentos;
	}

	/**
	 * @param listaChequeDocumentos the listaChequeDocumentos to set
	 */
	public void setListaChequeDocumentos(
			List<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto> listaChequeDocumentos) {
		this.listaChequeDocumentos = listaChequeDocumentos;
	}

	public List<LegajoChequeRechazadoNotificacionDto> getListaLegajoChequeRechazadoNotificacionDto() {
		return listaLegajoChequeRechazadoNotificacionDto;
	}

	public void setListaLegajoChequeRechazadoNotificacionDto(
			List<LegajoChequeRechazadoNotificacionDto> listaLegajoChequeRechazadoNotificacionDto) {
		this.listaLegajoChequeRechazadoNotificacionDto = listaLegajoChequeRechazadoNotificacionDto;
	}
}