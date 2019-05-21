package ar.com.telecom.shiva.persistencia.bean;

import java.util.Date;

import ar.com.telecom.shiva.negocio.servicios.bean.Bean;

public class VistaSoporteResultadoBusquedaLegajoChequeRechazado extends VistaSoporteResultadoBusqueda implements Bean {
	
	private static final long serialVersionUID = 20170623L;
	private String empresa;
	private String segmento;
	private Long idLegajo;
	private String cuit;
	private String cliente;
	private String bancoOrigen;
	private String nroCheque;
	private String estado;
	private String motivo;
	private Date fechaUltimoEstado;
	private String analista;
	private String copropietario;
	private String analistaTeamComercial;
	private String analistaCobranzas;
	private String analistaCobranzasCopropietario;
	private String importe;
	private Date fechaAlta;
	private Date fechaNotificacion;
	private Date fechaCierre;
	private Long idValor;
	private Date fechaNotificacionDelRechazo;
	private Date fechaRechazo;
	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getSegmento() {
		return segmento;
	}
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	public Long getIdLegajo() {
		return idLegajo;
	}
	public void setIdLegajo(Long idLegajo) {
		this.idLegajo = idLegajo;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getBancoOrigen() {
		return bancoOrigen;
	}
	public void setBancoOrigen(String bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}
	public String getNroCheque() {
		return nroCheque;
	}
	public void setNroCheque(String nroCheque) {
		this.nroCheque = nroCheque;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Date getFechaUltimoEstado() {
		return fechaUltimoEstado;
	}
	public void setFechaUltimoEstado(Date fechaUltimoEstado) {
		this.fechaUltimoEstado = fechaUltimoEstado;
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
	public String getAnalistaCobranzas() {
		return analistaCobranzas;
	}
	public void setAnalistaCobranzas(String analistaCobranzas) {
		this.analistaCobranzas = analistaCobranzas;
	}
	public String getAnalistaCobranzasCopropietario() {
		return analistaCobranzasCopropietario;
	}
	public void setAnalistaCobranzasCopropietario(
			String analistaCobranzasCopropietario) {
		this.analistaCobranzasCopropietario = analistaCobranzasCopropietario;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Date getFechaNotificacion() {
		return fechaNotificacion;
	}
	public void setFechaNotificacion(Date fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	/**
	 * @return the idValor
	 */
	public Long getIdValor() {
		return idValor;
	}
	/**
	 * @param idValor the idValor to set
	 */
	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}
	/**
	 * @return the fechaNotificacionDelRechazo
	 */
	public Date getFechaNotificacionDelRechazo() {
		return fechaNotificacionDelRechazo;
	}
	/**
	 * @param fechaNotificacionDelRechazo the fechaNotificacionDelRechazo to set
	 */
	public void setFechaNotificacionDelRechazo(Date fechaNotificacionDelRechazo) {
		this.fechaNotificacionDelRechazo = fechaNotificacionDelRechazo;
	}
	/**
	 * @return the fechaRechazo
	 */
	public Date getFechaRechazo() {
		return fechaRechazo;
	}
	/**
	 * @param fechaRechazo the fechaRechazo to set
	 */
	public void setFechaRechazo(Date fechaRechazo) {
		this.fechaRechazo = fechaRechazo;
	}
	
}
