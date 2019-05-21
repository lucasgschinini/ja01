package ar.com.telecom.shiva.persistencia.bean;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;

public class ResultadoBusquedaCobrosPendientes {
	
	private Long idCobro;
	private Long idOperacion;
	private Estado estadoCobro;
	private Integer cantidadTransacciones;
	private String fechaUltimoProcesamiento;
	private SiNoEnum contieneAplicacionManual;
	
	
	public SiNoEnum getContieneAplicacionManual() {
		return contieneAplicacionManual;
	}
	public void setContieneAplicacionManual(SiNoEnum contieneAplicacionManual) {
		this.contieneAplicacionManual = contieneAplicacionManual;
	}
	public Long getIdCobro() {
		return idCobro;
	}
	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}
	public Long getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}
	public Estado getEstadoCobro() {
		return estadoCobro;
	}
	public void setEstadoCobro(Estado estadoCobro) {
		this.estadoCobro = estadoCobro;
	}
	public Integer getCantidadTransacciones() {
		return cantidadTransacciones;
	}
	public void setCantidadTransacciones(Integer cantidadTransacciones) {
		this.cantidadTransacciones = cantidadTransacciones;
	}
	public String getFechaUltimoProcesamiento() {
		return fechaUltimoProcesamiento;
	}
	public void setFechaUltimoProcesamiento(String fechaUltimoProcesamiento) {
		this.fechaUltimoProcesamiento = fechaUltimoProcesamiento;
	}
	
	
	
	

}
