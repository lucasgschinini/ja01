package ar.com.telecom.shiva.presentacion.bean.dto;

import java.util.Date;

public class HistoricoDto {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String observacion;
	private Date fecha;
	private String usuario;
	private String nombreUsuario;
	private String datosModificados;
	
	/**
	 * 
	 * @param observacion
	 * @param datosModificados
	 * @param fecha
	 * @param usuario
	 */
	public HistoricoDto(String observacion, String datosModificados, Date fecha, String usuario) {
		this.observacion = observacion;
		this.fecha = fecha;
		this.usuario = usuario;
		this.datosModificados = datosModificados;
	}
	
	/**
	 * @return the datosModificados
	 */
	public String getDatosModificados() {
		return datosModificados;
	}

	/**
	 * @param datosModificados the datosModificados to set
	 */
	public void setDatosModificados(String datosModificados) {
		this.datosModificados = datosModificados;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}
	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	/**
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	
	

}
