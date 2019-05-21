package ar.com.telecom.shiva.presentacion.bean.dto;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;

public class LegajoBusquedaDto extends DTO {

	private static final long serialVersionUID = 1L;

	private Long idLegajo;
	private String empresa;
	private String segmento;
	private String cuit;
	private String bancoOrigen;
	private String nroCheque;
	private String estado;
	private String motivo;
	private String fechaUltimoEstado;
	private String analista;
	private String idAnalista;
	private String mailAnalista;
	private String urlFotoAnalista;
	private String copropietario;
	private String idCopropietario;
	private String mailCopropietario;
	private String urlFotoCopropietario;
	private String analistaTeamComercial;
	private String mailAnalistaTeamComercial;
	private String urlFotoAnalistaTeamComercial;
	private String analistaCobranzas;
	private String mailAnalistaCobranzas;
	private String urlFotoAnalistaCobranzas;
	private String analistaCobranzasCopropietario;
	private String mailAnalistaCobranzasCopropietario;
	private String urlFotoAnalistaCobranzasCopropietario;
	private String importe;
	private String fechaAltaString;
	private String fechaNotificacion;
	private String fechaCierre;
	
	private String cliente;
	
	private boolean esModificablePorUsuario;
	
	private boolean esAnulable;
	public LegajoBusquedaDto () {
	}

	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	/**
	 * @return the idLegajo
	 */
	public Long getIdLegajo() {
		return idLegajo;
	}

	/**
	 * @param idLegajo the idLegajo to set
	 */
	public void setIdLegajo(Long idLegajo) {
		this.idLegajo = idLegajo;
	}

	/**
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * @param cuit the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * @return the bancoOrigen
	 */
	public String getBancoOrigen() {
		return bancoOrigen;
	}

	/**
	 * @param bancoOrigen the bancoOrigen to set
	 */
	public void setBancoOrigen(String bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}

	/**
	 * @return the nroCheque
	 */
	public String getNroCheque() {
		return nroCheque;
	}

	/**
	 * @param nroCheque the nroCheque to set
	 */
	public void setNroCheque(String nroCheque) {
		this.nroCheque = nroCheque;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the fechaUltimoEstado
	 */
	public String getFechaUltimoEstado() {
		return fechaUltimoEstado;
	}

	/**
	 * @param fechaUltimoEstado the fechaUltimoEstado to set
	 */
	public void setFechaUltimoEstado(String fechaUltimoEstado) {
		this.fechaUltimoEstado = fechaUltimoEstado;
	}

	/**
	 * @return the analista
	 */
	public String getAnalista() {
		return analista;
	}

	/**
	 * @param analista the analista to set
	 */
	public void setAnalista(String analista) {
		this.analista = analista;
	}

	/**
	 * @return the copropietario
	 */
	public String getCopropietario() {
		return copropietario;
	}

	/**
	 * @param copropietario the copropietario to set
	 */
	public void setCopropietario(String copropietario) {
		this.copropietario = copropietario;
	}

	/**
	 * @return the analistaTeamComercial
	 */
	public String getAnalistaTeamComercial() {
		return analistaTeamComercial;
	}

	/**
	 * @param analistaTeamComercial the analistaTeamComercial to set
	 */
	public void setAnalistaTeamComercial(String analistaTeamComercial) {
		this.analistaTeamComercial = analistaTeamComercial;
	}

	/**
	 * @return the analistaCobranzas
	 */
	public String getAnalistaCobranzas() {
		return analistaCobranzas;
	}

	/**
	 * @param analistaCobranzas the analistaCobranzas to set
	 */
	public void setAnalistaCobranzas(String analistaCobranzas) {
		this.analistaCobranzas = analistaCobranzas;
	}

	/**
	 * @return the analistaCobranzasCopropietario
	 */
	public String getAnalistaCobranzasCopropietario() {
		return analistaCobranzasCopropietario;
	}

	/**
	 * @param analistaCobranzasCopropietario the analistaCobranzasCopropietario to set
	 */
	public void setAnalistaCobranzasCopropietario(
			String analistaCobranzasCopropietario) {
		this.analistaCobranzasCopropietario = analistaCobranzasCopropietario;
	}

	/**
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * @return the fechaAlta
	 */
	public String getFechaAltaString() {
		return fechaAltaString;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAltaString(String fechaAlta) {
		this.fechaAltaString = fechaAlta;
	}

	/**
	 * @return the fechaNotificacion
	 */
	public String getFechaNotificacion() {
		return fechaNotificacion;
	}

	/**
	 * @param fechaNotificacion the fechaNotificacion to set
	 */
	public void setFechaNotificacion(String fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}

	/**
	 * @return the fechaCierre
	 */
	public String getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * @param fechaCierre the fechaCierre to set
	 */
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the mailAnalista
	 */
	public String getMailAnalista() {
		return mailAnalista;
	}

	/**
	 * @param mailAnalista the mailAnalista to set
	 */
	public void setMailAnalista(String mailAnalista) {
		this.mailAnalista = mailAnalista;
	}

	/**
	 * @return the urlFotoAnalista
	 */
	public String getUrlFotoAnalista() {
		return urlFotoAnalista;
	}

	/**
	 * @param urlFotoAnalista the urlFotoAnalista to set
	 */
	public void setUrlFotoAnalista(String urlFotoAnalista) {
		this.urlFotoAnalista = urlFotoAnalista;
	}

	/**
	 * @return the mailCopropietario
	 */
	public String getMailCopropietario() {
		return mailCopropietario;
	}

	/**
	 * @param mailCopropietario the mailCopropietario to set
	 */
	public void setMailCopropietario(String mailCopropietario) {
		this.mailCopropietario = mailCopropietario;
	}

	/**
	 * @return the urlFotoCopropietario
	 */
	public String getUrlFotoCopropietario() {
		return urlFotoCopropietario;
	}

	/**
	 * @param urlFotoCopropietario the urlFotoCopropietario to set
	 */
	public void setUrlFotoCopropietario(String urlFotoCopropietario) {
		this.urlFotoCopropietario = urlFotoCopropietario;
	}

	/**
	 * @return the mailAnalistaTeamComercial
	 */
	public String getMailAnalistaTeamComercial() {
		return mailAnalistaTeamComercial;
	}

	/**
	 * @param mailAnalistaTeamComercial the mailAnalistaTeamComercial to set
	 */
	public void setMailAnalistaTeamComercial(String mailAnalistaTeamComercial) {
		this.mailAnalistaTeamComercial = mailAnalistaTeamComercial;
	}

	/**
	 * @return the urlFotoAnalistaTeamComercial
	 */
	public String getUrlFotoAnalistaTeamComercial() {
		return urlFotoAnalistaTeamComercial;
	}

	/**
	 * @param urlFotoAnalistaTeamComercial the urlFotoAnalistaTeamComercial to set
	 */
	public void setUrlFotoAnalistaTeamComercial(String urlFotoAnalistaTeamComercial) {
		this.urlFotoAnalistaTeamComercial = urlFotoAnalistaTeamComercial;
	}

	/**
	 * @return the mailAnalistaCobranzas
	 */
	public String getMailAnalistaCobranzas() {
		return mailAnalistaCobranzas;
	}

	/**
	 * @param mailAnalistaCobranzas the mailAnalistaCobranzas to set
	 */
	public void setMailAnalistaCobranzas(String mailAnalistaCobranzas) {
		this.mailAnalistaCobranzas = mailAnalistaCobranzas;
	}

	/**
	 * @return the urlFotoAnalistaCobranzas
	 */
	public String getUrlFotoAnalistaCobranzas() {
		return urlFotoAnalistaCobranzas;
	}

	/**
	 * @param urlFotoAnalistaCobranzas the urlFotoAnalistaCobranzas to set
	 */
	public void setUrlFotoAnalistaCobranzas(String urlFotoAnalistaCobranzas) {
		this.urlFotoAnalistaCobranzas = urlFotoAnalistaCobranzas;
	}

	/**
	 * @return the mailAnalistaCobranzasCopropietario
	 */
	public String getMailAnalistaCobranzasCopropietario() {
		return mailAnalistaCobranzasCopropietario;
	}

	/**
	 * @param mailAnalistaCobranzasCopropietario the mailAnalistaCobranzasCopropietario to set
	 */
	public void setMailAnalistaCobranzasCopropietario(
			String mailAnalistaCobranzasCopropietario) {
		this.mailAnalistaCobranzasCopropietario = mailAnalistaCobranzasCopropietario;
	}

	/**
	 * @return the urlFotoAnalistaCobranzasCopropietario
	 */
	public String getUrlFotoAnalistaCobranzasCopropietario() {
		return urlFotoAnalistaCobranzasCopropietario;
	}

	/**
	 * @param urlFotoAnalistaCobranzasCopropietario the urlFotoAnalistaCobranzasCopropietario to set
	 */
	public void setUrlFotoAnalistaCobranzasCopropietario(
			String urlFotoAnalistaCobranzasCopropietario) {
		this.urlFotoAnalistaCobranzasCopropietario = urlFotoAnalistaCobranzasCopropietario;
	}

	/**
	 * @return the estadoDescripcion
	 */
	public String getEstadoDescripcion() {
		return Estado.getEnumByName(estado).descripcion();
	}

	/**
	 * @return the idAnalista
	 */
	public String getIdAnalista() {
		return idAnalista;
	}

	/**
	 * @param idAnalista the idAnalista to set
	 */
	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}

	/**
	 * @return the idCopropietario
	 */
	public String getIdCopropietario() {
		return idCopropietario;
	}

	/**
	 * @param idCopropietario the idCopropietario to set
	 */
	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}

	public void setEsModificablePorUsuario(boolean esModificablePorUsuario) {
		this.esModificablePorUsuario = esModificablePorUsuario;
	}
	
	public boolean isEsModificablePorUsuario(){
		return esModificablePorUsuario;
	}

	public boolean isEsAnulable() {
		return esAnulable;
	}

	public void setEsAnulable(boolean esAnulable) {
		this.esAnulable = esAnulable;
	}
}
