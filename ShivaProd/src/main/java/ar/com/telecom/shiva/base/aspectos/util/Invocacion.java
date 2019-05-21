package ar.com.telecom.shiva.base.aspectos.util;

import java.util.Date;

public class Invocacion {
	
	private Integer cantidad;
	
	private Date fechaPrimeraInvocacion;

	public Invocacion(Integer cantidad, Date fechaPrimeraInvocacion) {
		super();
		this.cantidad = cantidad;
		this.fechaPrimeraInvocacion = fechaPrimeraInvocacion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaPrimeraInvocacion() {
		return fechaPrimeraInvocacion;
	}

	public void setFechaPrimeraInvocacion(Date fechaPrimeraInvocacion) {
		this.fechaPrimeraInvocacion = fechaPrimeraInvocacion;
	}
	
	public void incrementarCantidad() {
		++this.cantidad;
	}
	
}