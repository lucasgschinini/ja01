package ar.com.telecom.shiva.presentacion.bean.dto.json;

import java.util.List;

import ar.com.telecom.shiva.presentacion.bean.dto.DescobroTransaccionDto;

public class DescobroTransaccionesJsonResponse extends JsonResponse {

	

	public boolean transaccionesOK;
	
	private List<DescobroTransaccionDto> aaData;
	
	private EstadoCobroJsonResponse estado = new EstadoCobroJsonResponse();
	
	private List<SelectOptionJsonResponse> listaSistemaTransaccion;
	
	
	
	/**
	 * @return the transaccionesOK
	 */
	public boolean isTransaccionesOK() {
		return transaccionesOK;
	}

	/**
	 * @param transaccionesOK the transaccionesOK to set
	 */
	public void setTransaccionesOK(boolean transaccionesOK) {
		this.transaccionesOK = transaccionesOK;
	}

	/**
	 * @return the aaData
	 */
	public List<DescobroTransaccionDto> getAaData() {
		return aaData;
	}

	/**
	 * @param aaData the aaData to set
	 */
	public void setAaData(List<DescobroTransaccionDto> aaData) {
		this.aaData = aaData;
	}

	/**
	 * @return the estado
	 */
	public EstadoCobroJsonResponse getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoCobroJsonResponse estado) {
		this.estado = estado;
	}

	/**
	 * @return the listaSistemaTransaccion
	 */
	public List<SelectOptionJsonResponse> getListaSistemaTransaccion() {
		return listaSistemaTransaccion;
	}

	/**
	 * @param listaSistemaTransaccion the listaSistemaTransaccion to set
	 */
	public void setListaSistemaTransaccion(List<SelectOptionJsonResponse> listaSistemaTransaccion) {
		this.listaSistemaTransaccion = listaSistemaTransaccion;
	}
	
}
