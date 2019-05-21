package ar.com.telecom.shiva.presentacion.bean.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.dto.DTO;

public class ValorHistoricoDto extends DTO {

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	
	private String idUsuarioModificacion;
	private String mailUsuarioModificacion;
	private String estadoModificacion;
	private String fechaModificacion;
	private List<String> camposModificados = new ArrayList<String>();
	private List<String> valoresOriginales = new ArrayList<String>();
	private List<String> valoresModificados = new ArrayList<String>();

	public String getEstadoModificacion() {
		return estadoModificacion;
	}
	public void setEstadoModificacion( String estadoModificacion) {
		this.estadoModificacion = estadoModificacion;
	}
	
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	public List<String> getCamposModificados() {
		return camposModificados;
	}
	public void setCamposModificados(List<String> camposModificados) {
		this.camposModificados = camposModificados;
	}
	
	public List<String> getValoresOriginales() {
		return valoresOriginales;
	}
	public void setValoresOriginales(List<String> valoresOriginales) {
		this.valoresOriginales = valoresOriginales;
	}
	
	public List<String> getValoresModificados() {
		return valoresModificados;
	}
	public void setValoresModificados(List<String> valoresModificados) {
		this.valoresModificados = valoresModificados;
	}
	
	public String getMailUsuarioModificacion() {
		return mailUsuarioModificacion;
	}
	public void setMailUsuarioModificacion(String mailUsuarioModificacion) {
		this.mailUsuarioModificacion = mailUsuarioModificacion;
	}
	
	public String getIdUsuarioModificacion() {
		return idUsuarioModificacion;
	}
	public void setIdUsuarioModificacion(String idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}

}
