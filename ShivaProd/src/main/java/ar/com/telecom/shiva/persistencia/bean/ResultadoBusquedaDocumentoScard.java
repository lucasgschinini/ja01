package ar.com.telecom.shiva.persistencia.bean;

import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;

public class ResultadoBusquedaDocumentoScard {

	private String idAnalista;
	private Date fechaUltimaModificacion;
	private Long idOperacionMasiva;
	private MonedaEnum monedaOperacion;
	private Long idClienteLegado;
	private String razonSocial;
	private String cuit;
	
	public String getIdAnalista() {
		return idAnalista;
	}

	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public Long getIdOperacionMasiva() {
		return idOperacionMasiva;
	}

	public void setIdOperacionMasiva(Long idOperacionMasiva) {
		this.idOperacionMasiva = idOperacionMasiva;
	}

	public MonedaEnum getMonedaOperacion() {
		return monedaOperacion;
	}

	public void setMonedaOperacion(MonedaEnum monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
	}

	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}


}
