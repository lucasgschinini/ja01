package ar.com.telecom.shiva.negocio.dto;

import java.util.Date;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.utils.Utilidad;

public class ArchivoAVCDto extends DTO {

	private static final long serialVersionUID = 1L;

	private String nombreArchivo;
	private String usuarioProcesamiento;
	private Date fechaProcesamiento;
	private String logProcesamiento;
	
	private Long registrosProcesados;
	private Long registrosConciliados;
	private Long registrosConciliacionSugerida;
	private Long registrosSinConciliar;
	private Long registrosAnulados;
	private String nroAcuerdo;
	private String descripcionArchivo;
	private byte[] archivoAdjunto;
	
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getUsuarioProcesamiento() {
		return usuarioProcesamiento;
	}
	public void setUsuarioProcesamiento(String usuarioProcesamiento) {
		this.usuarioProcesamiento = usuarioProcesamiento;
	}
	public Date getFechaProcesamiento() {
		return fechaProcesamiento;
	}
	public void setFechaProcesamiento(Date fechaProcesamiento) {
		this.fechaProcesamiento = fechaProcesamiento;
	}
	public String getLogProcesamiento() {
		return logProcesamiento;
	}
	public void setLogProcesamiento(String logProcesamiento) {
		this.logProcesamiento = logProcesamiento;
	}
	public Long getRegistrosProcesados() {
		return registrosProcesados;
	}
	public void setRegistrosProcesados(Long registrosProcesados) {
		this.registrosProcesados = registrosProcesados;
	}
	public Long getRegistrosConciliados() {
		return registrosConciliados;
	}
	public void setRegistrosConciliados(Long registrosConciliados) {
		this.registrosConciliados = registrosConciliados;
	}
	public Long getRegistrosConciliacionSugerida() {
		return registrosConciliacionSugerida;
	}
	public void setRegistrosConciliacionSugerida(Long registrosConciliacionSugerida) {
		this.registrosConciliacionSugerida = registrosConciliacionSugerida;
	}
	public Long getRegistrosSinConciliar() {
		return registrosSinConciliar;
	}
	public void setRegistrosSinConciliar(Long registrosSinConciliar) {
		this.registrosSinConciliar = registrosSinConciliar;
	}
	public Long getRegistrosAnulados() {
		return registrosAnulados;
	}
	public void setRegistrosAnulados(Long registrosAnulados) {
		this.registrosAnulados = registrosAnulados;
	}
	public String getNroAcuerdo() {
		return nroAcuerdo;
	}
	public void setNroAcuerdo(String nroAcuerdo) {
		this.nroAcuerdo = nroAcuerdo;
	}
	public String getDescripcionArchivo() {
		return descripcionArchivo;
	}
	public void setDescripcionArchivo(String descripcionArchivo) {
		this.descripcionArchivo = descripcionArchivo;
	}
	public byte[] getArchivoAdjunto() {
		return archivoAdjunto;
	}
	public void setArchivoAdjunto(byte[] archivoAdjunto) {
		this.archivoAdjunto = archivoAdjunto;
	}
	
	public String getLogProcesamientoFormateado() {
		if(logProcesamiento.length()>34){
			return logProcesamiento.substring(0, 35) + "...";
		}else{
			return logProcesamiento;
		}
	}
	
	public String getFechaProcesamientoFormateado(){
		return Utilidad.formatDateAndTimeFull(fechaProcesamiento);
	}
}
