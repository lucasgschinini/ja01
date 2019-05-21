package ar.com.telecom.shiva.presentacion.bean.dto.json;

import java.util.List;


import java.util.Set;

import ar.com.telecom.shiva.presentacion.bean.dto.DescobroDocumentoRelacionadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroOperacionesRelacionadasDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroTransaccionDto;

public class DescobroOperacionesRelacionadasJsonResponse extends JsonResponse {

	
	private List<DescobroOperacionesRelacionadasDto> aaData;
	private List<DescobroDocumentoRelacionadoDto> listaDocumentosRelacionados;
	private Set<DescobroTransaccionDto> transacciones;
	
	/**
	 * @return the aaData
	 */
	public List<DescobroOperacionesRelacionadasDto> getAaData() {
		return aaData;
	}

	/**
	 * @param aaData the aaData to set
	 */
	public void setAaData(List<DescobroOperacionesRelacionadasDto> aaData) {
		this.aaData = aaData;
	}

	public List<DescobroDocumentoRelacionadoDto> getListaDocumentosRelacionados() {
		return listaDocumentosRelacionados;
	}

	public void setListaDocumentosRelacionados(
			List<DescobroDocumentoRelacionadoDto> listaDocumentosRelacionados) {
		this.listaDocumentosRelacionados = listaDocumentosRelacionados;
	}

	/**
	 * @return the transacciones
	 */
	public Set<DescobroTransaccionDto> getTransacciones() {
		return transacciones;
	}

	/**
	 * @param transacciones the transacciones to set
	 */
	public void setTransacciones(Set<DescobroTransaccionDto> transacciones) {
		this.transacciones = transacciones;
	}
	
	
}
