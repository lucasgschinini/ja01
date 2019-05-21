package ar.com.telecom.shiva.persistencia.bean;

import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;

public class VistaSoporteResultadoBusquedaLegajoCobrosRelacionado extends VistaSoporteResultadoBusqueda implements Bean {
	
	private static final long serialVersionUID = 20170623L;
	private String 	sistema;
	private String	idOperacion;
	private String	tipoDocumento;
	private String	numeroLegal;
	private String	numeroReferencia; //SOLO MIC
	private String	convenioFinanciacion; //SOLO DEIMOS
	private String	cuotaFinanciacion; //SOLO DEIMOS
	private String	clienteLegado;
	private String	importeTotalDocumento;
	private String	importeTotalCheque; //SOLO ICE
	private String	importeTotalEfectivo; //SOLO ICE
	private String	importeTotalRetenciones; //SOLO ICE
	private String	importeTotalBonos; //SOLO ICE
	private String	importeTotalOtrasMonedas; //SOLO ICE
	private Date	fechaImputacion;
	private String	idDescobro; //SOLO SHIVA
	private String	idOperacionDescobro; //SOLO SHIVA
	private String	analista;
	private String	copropietario;
	private String	analistaTeamComercial;
	private String	idAnalista;
	private String	idCopropietario;
	private String	idAnalistaTeamComercial;
	private String	idChequeRechazadoCobro;
	private Date	fechaReversion;
	private String	nombreArchivoReversion;
	private String	estadoReversionShiva;
	private String	estadoReversionIce;
	private SistemaEnum sistemaOrigen;
	
	
	
	public String getSistema() {
		return sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public String getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroLegal() {
		return numeroLegal;
	}
	public void setNumeroLegal(String numeroLegal) {
		this.numeroLegal = numeroLegal;
	}
	public String getNumeroReferencia() {
		return numeroReferencia;
	}
	public void setNumeroReferencia(String numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}
	public String getConvenioFinanciacion() {
		return convenioFinanciacion;
	}
	public void setConvenioFinanciacion(String convenioFinanciacion) {
		this.convenioFinanciacion = convenioFinanciacion;
	}
	public String getCuotaFinanciacion() {
		return cuotaFinanciacion;
	}
	public void setCuotaFinanciacion(String cuotaFinanciacion) {
		this.cuotaFinanciacion = cuotaFinanciacion;
	}
	public String getClienteLegado() {
		return clienteLegado;
	}
	public void setClienteLegado(String clienteLegado) {
		this.clienteLegado = clienteLegado;
	}
	public String getImporteTotalDocumento() {
		return importeTotalDocumento;
	}
	public void setImporteTotalDocumento(String importeTotalDocumento) {
		this.importeTotalDocumento = importeTotalDocumento;
	}
	public String getImporteTotalCheque() {
		return importeTotalCheque;
	}
	public void setImporteTotalCheque(String importeTotalCheque) {
		this.importeTotalCheque = importeTotalCheque;
	}
	public String getImporteTotalEfectivo() {
		return importeTotalEfectivo;
	}
	public void setImporteTotalEfectivo(String importeTotalEfectivo) {
		this.importeTotalEfectivo = importeTotalEfectivo;
	}
	public String getImporteTotalRetenciones() {
		return importeTotalRetenciones;
	}
	public void setImporteTotalRetenciones(String importeTotalRetenciones) {
		this.importeTotalRetenciones = importeTotalRetenciones;
	}
	public String getImporteTotalBonos() {
		return importeTotalBonos;
	}
	public void setImporteTotalBonos(String importeTotalBonos) {
		this.importeTotalBonos = importeTotalBonos;
	}
	public String getImporteTotalOtrasMonedas() {
		return importeTotalOtrasMonedas;
	}
	public void setImporteTotalOtrasMonedas(String importeTotalOtrasMonedas) {
		this.importeTotalOtrasMonedas = importeTotalOtrasMonedas;
	}
	public Date getFechaImputacion() {
		return fechaImputacion;
	}
	public void setFechaImputacion(Date fechaImputacion) {
		this.fechaImputacion = fechaImputacion;
	}
	public String getIdDescobro() {
		return idDescobro;
	}
	public void setIdDescobro(String idDescobro) {
		this.idDescobro = idDescobro;
	}
	public String getAnalista() {
		return analista;
	}
	public void setAnalista(String analista) {
		this.analista = analista;
	}
	public String getCopropietario() {
		return copropietario;
	}
	public void setCopropietario(String copropietario) {
		this.copropietario = copropietario;
	}
	public String getAnalistaTeamComercial() {
		return analistaTeamComercial;
	}
	public void setAnalistaTeamComercial(String analistaTeamComercial) {
		this.analistaTeamComercial = analistaTeamComercial;
	}
	public String getIdAnalista() {
		return idAnalista;
	}
	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}
	public String getIdCopropietario() {
		return idCopropietario;
	}
	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}
	public String getIdAnalistaTeamComercial() {
		return idAnalistaTeamComercial;
	}
	public void setIdAnalistaTeamComercial(String idAnalistaTeamComercial) {
		this.idAnalistaTeamComercial = idAnalistaTeamComercial;
	}
	public String getIdChequeRechazadoCobro() {
		return idChequeRechazadoCobro;
	}
	public void setIdChequeRechazadoCobro(String idChequeRechazadoCobro) {
		this.idChequeRechazadoCobro = idChequeRechazadoCobro;
	}
	public Date getFechaReversion() {
		return fechaReversion;
	}
	public void setFechaReversion(Date fechaReversion) {
		this.fechaReversion = fechaReversion;
	}
	public String getNombreArchivoReversion() {
		return nombreArchivoReversion;
	}
	public void setNombreArchivoReversion(String nombreArchivoReversion) {
		this.nombreArchivoReversion = nombreArchivoReversion;
	}
	public String getEstadoReversionShiva() {
		return estadoReversionShiva;
	}
	public void setEstadoReversionShiva(String estadoReversionShiva) {
		this.estadoReversionShiva = estadoReversionShiva;
	}
	public String getEstadoReversionIce() {
		return estadoReversionIce;
	}
	public void setEstadoReversionIce(String estadoReversionIce) {
		this.estadoReversionIce = estadoReversionIce;
	}
	/**
	 * @return the sistemaOrigen
	 */
	public SistemaEnum getSistemaOrigen() {
		return sistemaOrigen;
	}
	/**
	 * @param sistemaOrigen the sistemaOrigen to set
	 */
	public void setSistemaOrigen(SistemaEnum sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}
	/**
	 * @return the idOperacionDescobro
	 */
	public String getIdOperacionDescobro() {
		return idOperacionDescobro;
	}
	/**
	 * @param idOperacionDescobro the idOperacionDescobro to set
	 */
	public void setIdOperacionDescobro(String idOperacionDescobro) {
		this.idOperacionDescobro = idOperacionDescobro;
	}
}
