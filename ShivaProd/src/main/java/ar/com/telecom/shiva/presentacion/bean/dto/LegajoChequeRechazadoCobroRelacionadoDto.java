package ar.com.telecom.shiva.presentacion.bean.dto;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoChequeRechazadoDetalleCobroEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;

public class LegajoChequeRechazadoCobroRelacionadoDto extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String 	sistema;
	private String	idOperacion;
	private String	tipoDocumento;
	private String	tipoDocumentoDescripcion;
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
	private String	fechaImputacion;
	private String	idDescobro; //SOLO SHIVA
	private String	idOperacionDescobro; //SOLO SHIVA
	
	private String	analista;
	private String	copropietario;
	private String	analistaTeamComercial;
	private String	idAnalista;
	private String	idCopropietario;
	private String	idAnalistaTeamComercial;
	private String	fechaReversion;
	private String	nombreArchivoConReversion;
	
	
	

	private EstadoChequeRechazadoDetalleCobroEnum estadoIce;
	private String	estadoIceDescripcion;
	private Estado	estadoShiva;
	private String	estadoShivaDescripcion;

	private SistemaEnum sistemaOrigen;
	
	private Long 	idChequeRechazadoCobro;
	
	
	public String getEstadoCobro() {
		String estado = null;
	
		if (SistemaEnum.SHIVA.equals(this.getSistemaOrigen())) {
			if (Validaciones.isObjectNull(this.getEstadoShiva())) {
				estado = EstadoChequeRechazadoDetalleCobroEnum.COBRADO.name();
			} else {
				estado = this.getEstadoShiva().name();
			}
		} else if (SistemaEnum.ICE.equals(this.getSistemaOrigen())) {
			if (Validaciones.isObjectNull(this.getEstadoIce())) {
				estado = EstadoChequeRechazadoDetalleCobroEnum.COBRADO.name();
			} else {
				estado = this.getEstadoIce().name();
			}
		}
		return estado;
	}

	public String getEstadoCobroDescripcion() {
		String estado = null;

		if (SistemaEnum.SHIVA.equals(this.getSistemaOrigen())) {
			if (Validaciones.isObjectNull(this.getEstadoShiva())) {
				estado = EstadoChequeRechazadoDetalleCobroEnum.COBRADO.getDescripcion();
			} else {
				estado = this.getEstadoShivaDescripcion();
			}
		} else if (SistemaEnum.ICE.equals(this.getSistemaOrigen())) {
			if (Validaciones.isObjectNull(this.getEstadoIce())) {
				estado = EstadoChequeRechazadoDetalleCobroEnum.COBRADO.getDescripcion();
			} else {
				estado = this.getEstadoIceDescripcion();
			}
		}
		return estado;
	}
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
	public String getFechaImputacion() {
		return fechaImputacion;
	}
	public void setFechaImputacion(String fechaImputacion) {
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
	/**
	 * @return the estado
	 */
	public EstadoChequeRechazadoDetalleCobroEnum getEstadoIce() {
		return estadoIce;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstadoIce(EstadoChequeRechazadoDetalleCobroEnum estado) {
		this.estadoIce = estado;
	}
	/**
	 * @return the idChequeRechazadoCobro
	 */
	public Long getIdChequeRechazadoCobro() {
		return idChequeRechazadoCobro;
	}
	/**
	 * @param idChequeRechazadoCobro the idChequeRechazadoCobro to set
	 */
	public void setIdChequeRechazadoCobro(Long idChequeRechazadoCobro) {
		this.idChequeRechazadoCobro = idChequeRechazadoCobro;
	}
	/**
	 * @return the estadoShiva
	 */
	public Estado getEstadoShiva() {
		return estadoShiva;
	}
	/**
	 * @param estadoShiva the estadoShiva to set
	 */
	public void setEstadoShiva(Estado estadoShiva) {
		this.estadoShiva = estadoShiva;
	}
	/**
	 * @return the estadoIceDescripcion
	 */
	public String getEstadoIceDescripcion() {
		return estadoIceDescripcion;
	}
	/**
	 * @param estadoIceDescripcion the estadoIceDescripcion to set
	 */
	public void setEstadoIceDescripcion(String estadoIceDescripcion) {
		this.estadoIceDescripcion = estadoIceDescripcion;
	}
	/**
	 * @return the estadoShivaDescripcion
	 */
	public String getEstadoShivaDescripcion() {
		return estadoShivaDescripcion;
	}
	/**
	 * @param estadoShivaDescripcion the estadoShivaDescripcion to set
	 */
	public void setEstadoShivaDescripcion(String estadoShivaDescripcion) {
		this.estadoShivaDescripcion = estadoShivaDescripcion;
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
	 * @return the fechaReversion
	 */
	public String getFechaReversion() {
		return fechaReversion;
	}

	/**
	 * @param fechaReversion the fechaReversion to set
	 */
	public void setFechaReversion(String fechaReversion) {
		this.fechaReversion = fechaReversion;
	}

	/**
	 * @return the nombreArchivoConReversion
	 */
	public String getNombreArchivoConReversion() {
		return nombreArchivoConReversion;
	}

	/**
	 * @param nombreArchivoConReversion the nombreArchivoConReversion to set
	 */
	public void setNombreArchivoConReversion(String nombreArchivoConReversion) {
		this.nombreArchivoConReversion = nombreArchivoConReversion;
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

	public String getTipoDocumentoDescripcion() {
		return tipoDocumentoDescripcion;
	}

	public void setTipoDocumentoDescripcion(String tipoDocumentoDescripcion) {
		this.tipoDocumentoDescripcion = tipoDocumentoDescripcion;
	}
}
