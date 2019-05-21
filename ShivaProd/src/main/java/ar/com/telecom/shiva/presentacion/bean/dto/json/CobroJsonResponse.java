package ar.com.telecom.shiva.presentacion.bean.dto.json;

import java.util.Set;

import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;


public class CobroJsonResponse extends JsonResponse {

	private boolean primerCobro;
	private Long idCobro;
	private Long idCobroPadre;
	private Long idOperacion;
	private EstadoCobroJsonResponse estado = new EstadoCobroJsonResponse();
	private String observacion;
	private String observacionAnterior;
	private String edicionSegunEstadoMarca = "";
	private boolean esPerfilSupervisorCobranza = false;
	private Set<CobroDebitoDto> debitos;
	private boolean eliminarComprobanteAplicacionManualPrev = false;
	private boolean vengoReedicion;

	public boolean isPrimerCobro() {
		return primerCobro;
	}

	public void setPrimerCobro(boolean primerCobro) {
		this.primerCobro = primerCobro;
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

	/**
	 * @return the estado
	 */
	public EstadoCobroJsonResponse getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoCobroJsonResponse estado) {
		this.estado = estado;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the observacionAnterior
	 */
	public String getObservacionAnterior() {
		return observacionAnterior;
	}

	/**
	 * @param observacionAnterior the observacionAnterior to set
	 */
	public void setObservacionAnterior(String observacionAnterior) {
		this.observacionAnterior = observacionAnterior;
	}

	/**
	 * @return the edicionSegunEstadoMarca
	 */
	public String getEdicionSegunEstadoMarca() {
		return edicionSegunEstadoMarca;
	}

	/**
	 * @param edicionSegunEstadoMarca the edicionSegunEstadoMarca to set
	 */
	public void setEdicionSegunEstadoMarca(String edicionSegunEstadoMarca) {
		this.edicionSegunEstadoMarca = edicionSegunEstadoMarca;
	}

	/**
	 * @return the esPerfilSupervisorCobranza
	 */
	public boolean isEsPerfilSupervisorCobranza() {
		return esPerfilSupervisorCobranza;
	}

	/**
	 * @param esPerfilSupervisorCobranza the esPerfilSupervisorCobranza to set
	 */
	public void setEsPerfilSupervisorCobranza(boolean esPerfilSupervisorCobranza) {
		this.esPerfilSupervisorCobranza = esPerfilSupervisorCobranza;
	}

	/**
	 * @return the idCobroPadre
	 */
	public Long getIdCobroPadre() {
		return idCobroPadre;
	}

	/**
	 * @param idCobroPadre the idCobroPadre to set
	 */
	public void setIdCobroPadre(Long idCobroPadre) {
		this.idCobroPadre = idCobroPadre;
	}

	public void setDebitos(Set<CobroDebitoDto> debitos) {
		this.debitos = debitos;
	}

	public Set<CobroDebitoDto> getDebitos() {
		return debitos;
	}

	/**
	 * @return the eliminarComprobanteAplicacionManualPrev
	 */
	public boolean isEliminarComprobanteAplicacionManualPrev() {
		return eliminarComprobanteAplicacionManualPrev;
	}

	/**
	 * @param eliminarComprobanteAplicacionManualPrev the eliminarComprobanteAplicacionManualPrev to set
	 */
	public void setEliminarComprobanteAplicacionManualPrev(
			boolean eliminarComprobanteAplicacionManualPrev) {
		this.eliminarComprobanteAplicacionManualPrev = eliminarComprobanteAplicacionManualPrev;
	}

	/**
	 * @return the vengoReedicion
	 */
	public boolean isVengoReedicion() {
		return vengoReedicion;
	}

	/**
	 * @param vengoReedicion the vengoReedicion to set
	 */
	public void setVengoReedicion(boolean vengoReedicion) {
		this.vengoReedicion = vengoReedicion;
	}
	
}