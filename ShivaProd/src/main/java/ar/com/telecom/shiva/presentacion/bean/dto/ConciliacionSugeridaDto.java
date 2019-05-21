package ar.com.telecom.shiva.presentacion.bean.dto;

import ar.com.telecom.shiva.base.dto.DTO;

public class ConciliacionSugeridaDto extends DTO {

	private static final long serialVersionUID = 1L;

	private String registroId;
	private String boletaId;
	
	private String registroNroAcuerdo;
	private String registroNroCliente;
	private String registroNroBoleta;
	private String registroNroCheque;
	private String registroImporte;
	
	private String boletaNroAcuerdo;
	private String boletaNroCliente;
	private String boletaNroBoleta;
	private String boletaNroCheque;
	private String boletaImporte;
	
	private String nombreArchivo;
	
	private Boolean diferenciaNroAcuerdo;
	private Boolean diferenciaNroCliente;
	private Boolean diferenciaNroBoleta;
	private Boolean diferenciaNroCheque;
	private Boolean diferenciaImporte;
	
	private String fechaOrdenamiento;
	
	public String getRegistroNroAcuerdo() {
		return registroNroAcuerdo;
	}
	public void setRegistroNroAcuerdo(String registroNroAcuerdo) {
		this.registroNroAcuerdo = registroNroAcuerdo;
	}
	public String getRegistroNroCliente() {
		return registroNroCliente;
	}
	public void setRegistroNroCliente(String registroNroCliente) {
		this.registroNroCliente = registroNroCliente;
	}
	public String getRegistroNroBoleta() {
		return registroNroBoleta;
	}
	public void setRegistroNroBoleta(String registroNroBoleta) {
		this.registroNroBoleta = registroNroBoleta;
	}
	public String getRegistroNroCheque() {
		return registroNroCheque;
	}
	public void setRegistroNroCheque(String registroNroCheque) {
		this.registroNroCheque = registroNroCheque;
	}
	public String getRegistroImporte() {
		return registroImporte;
	}
	public void setRegistroImporte(String registroImporte) {
		this.registroImporte = registroImporte;
	}
	public String getBoletaNroAcuerdo() {
		return boletaNroAcuerdo;
	}
	public void setBoletaNroAcuerdo(String boletaNroAcuerdo) {
		this.boletaNroAcuerdo = boletaNroAcuerdo;
	}
	public String getBoletaNroCliente() {
		return boletaNroCliente;
	}
	public void setBoletaNroCliente(String boletaNroCliente) {
		this.boletaNroCliente = boletaNroCliente;
	}
	public String getBoletaNroBoleta() {
		return boletaNroBoleta;
	}
	public void setBoletaNroBoleta(String boletaNroBoleta) {
		this.boletaNroBoleta = boletaNroBoleta;
	}
	public String getBoletaNroCheque() {
		return boletaNroCheque;
	}
	public void setBoletaNroCheque(String boletaNroCheque) {
		this.boletaNroCheque = boletaNroCheque;
	}
	public String getBoletaImporte() {
		return boletaImporte;
	}
	public void setBoletaImporte(String boletaImporte) {
		this.boletaImporte = boletaImporte;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public Boolean getDiferenciaNroAcuerdo() {
		return diferenciaNroAcuerdo;
	}
	public void setDiferenciaNroAcuerdo(Boolean diferenciaNroAcuerdo) {
		this.diferenciaNroAcuerdo = diferenciaNroAcuerdo;
	}
	public Boolean getDiferenciaNroCliente() {
		return diferenciaNroCliente;
	}
	public void setDiferenciaNroCliente(Boolean diferenciaNroCliente) {
		this.diferenciaNroCliente = diferenciaNroCliente;
	}
	public Boolean getDiferenciaNroBoleta() {
		return diferenciaNroBoleta;
	}
	public void setDiferenciaNroBoleta(Boolean diferenciaNroBoleta) {
		this.diferenciaNroBoleta = diferenciaNroBoleta;
	}
	public Boolean getDiferenciaNroCheque() {
		return diferenciaNroCheque;
	}
	public void setDiferenciaNroCheque(Boolean diferenciaNroCheque) {
		this.diferenciaNroCheque = diferenciaNroCheque;
	}
	public Boolean getDiferenciaImporte() {
		return diferenciaImporte;
	}
	public void setDiferenciaImporte(Boolean diferenciaImporte) {
		this.diferenciaImporte = diferenciaImporte;
	}
	public String getRegistroId() {
		return registroId;
	}
	public void setRegistroId(String registroId) {
		this.registroId = registroId;
	}
	public String getBoletaId() {
		return boletaId;
	}
	public void setBoletaId(String boletaId) {
		this.boletaId = boletaId;
	}
	
	public String getIdCompuesto(){
		return registroId + "-" + boletaId;
	}
	public String getFechaOrdenamiento() {
		return fechaOrdenamiento;
	}
	public void setFechaOrdenamiento(String fechaOrdenamiento) {
		this.fechaOrdenamiento = fechaOrdenamiento;
	}
}
