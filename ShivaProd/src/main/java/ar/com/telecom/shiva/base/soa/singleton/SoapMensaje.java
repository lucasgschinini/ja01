package ar.com.telecom.shiva.base.soa.singleton;

import java.util.Date;

public class SoapMensaje {
	
	private String contenidoMensaje;
	private Date timeStartSesion;
	
	public String getContenidoMensaje() {
		return contenidoMensaje;
	}
	public void setContenidoMensaje(String contenidoMensaje) {
		this.contenidoMensaje = contenidoMensaje;
	}
	public Date getTimeStartSesion() {
		return timeStartSesion;
	}
	public void setTimeStartSesion(Date timeStartSesion) {
		this.timeStartSesion = timeStartSesion;
	}
}
