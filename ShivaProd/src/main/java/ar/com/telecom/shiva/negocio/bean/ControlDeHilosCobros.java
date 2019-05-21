package ar.com.telecom.shiva.negocio.bean;

import java.util.Date;

import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;

public class ControlDeHilosCobros {
	
	private Long idOperacion;
	
	private Long diferenciaEnMilisegundos;
	
	private Long idHilo;
	
	private Estado estadoCobro;
	
	private Date fechaUltimaModificacionWF;
	
	private Date fechaInicioHilo;
	
	/**
	 * 
	 * Getters & Setters
	 * 
	 */
	
	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Long getDiferenciaEnMilisegundos() {
		return diferenciaEnMilisegundos;
	}

	public void setDiferenciaEnMilisegundos(Long diferenciaEnMilisegundos) {
		this.diferenciaEnMilisegundos = diferenciaEnMilisegundos;
	}

	public Long getIdHilo() {
		return idHilo;
	}

	public void setIdHilo(Long idHilo) {
		this.idHilo = idHilo;
	}
	
	public Estado getEstadoCobro() {
		return estadoCobro;
	}

	public void setEstadoCobro(Estado estadoCobro) {
		this.estadoCobro = estadoCobro;
	}

	public Date getFechaUltimaModificacionWF() {
		return fechaUltimaModificacionWF;
	}

	public void setFechaUltimaModificacionWF(Date fechaUltimaModificacionWF) {
		this.fechaUltimaModificacionWF = fechaUltimaModificacionWF;
	}

	public Date getFechaInicioHilo() {
		return fechaInicioHilo;
	}

	public void setFechaInicioHilo(Date fechaInicioHilo) {
		this.fechaInicioHilo = fechaInicioHilo;
	}
	

}
