package ar.com.telecom.shiva.presentacion.bean.dto.json;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;
import ar.com.telecom.shiva.presentacion.bean.paginacion.CreditosControlPaginacion;


public class CapJsonResponse extends JsonResponse {
		
	private List<DocumentoCapDto> resultado;
	private String errorMensaje;
	private ArrayList<ErrorJson> errores;
	private CreditosControlPaginacion controlPaginacion;
	private List<String> proveedoresNoEncontrados;

	public List<DocumentoCapDto> getResultado() {
		return resultado;
	}

	public void setResultado(List<DocumentoCapDto> resultado) {
		this.resultado = resultado;
	}
	
	public String getErrorMensaje() {
		return errorMensaje;
	}

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
	 * @return the controPaginacion
	 */
	public CreditosControlPaginacion getControlPaginacion() {
		return controlPaginacion;
	}

	/**
	 * @param controPaginacion the controPaginacion to set
	 */
	public void setControlPaginacion(CreditosControlPaginacion controlPaginacion) {
		this.controlPaginacion = controlPaginacion;
	}

	/**
	 * @return the proveedoresNoEncontrados
	 */
	public List<String> getProveedoresNoEncontrados() {
		return proveedoresNoEncontrados;
	}

	/**
	 * @param proveedoresNoEncontrados the proveedoresNoEncontrados to set
	 */
	public void setProveedoresNoEncontrados(List<String> proveedoresNoEncontrados) {
		this.proveedoresNoEncontrados = proveedoresNoEncontrados;
	}
	
}