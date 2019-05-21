package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_TIPO_COMPROBANTE_USO")
public class ShvParamTipoComprobanteUso extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column (name="ID_TIPO_COMPROBANTE_USO")
	private Long idTipoComprobanteUso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_COMPROBANTE", nullable = false, insertable = false, updatable = false)
	private ShvParamTipoComprobante tipoComprobante;	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SOCIEDAD", nullable = false)
	private SociedadEnum sociedad;

	@Enumerated(EnumType.STRING)
	@Column(name = "SISTEMA_ORIGEN", nullable = false)
	private SistemaEnum sistema;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ACTIVO", nullable = false)
	private SiNoEnum habilitada;

	@Enumerated(EnumType.STRING)
	@Column(name = "GENERA_INTERESES", nullable = false)
	private SiNoEnum generaIntereses;

	@Column(name = "CLAVE_ORDENAMIENTO")
	private String clave;

	@Column(name = "FECHA_CLAVE")
	private String fechaClave;

	@Column(name = "ORDEN_IMPUTACION")
	private Long ordenImputacion;

	public SiNoEnum getHabilitada() {
		return habilitada;
	}

	public void setHabilitada(SiNoEnum habilitada) {
		this.habilitada = habilitada;
	}

	/**
	 * @return the generaIntereses
	 */
	public SiNoEnum getGeneraIntereses() {
		return generaIntereses;
	}

	/**
	 * @param generaIntereses
	 *            the generaIntereses to set
	 */
	public void setGeneraIntereses(SiNoEnum generaIntereses) {
		this.generaIntereses = generaIntereses;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave
	 *            the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the ordenImputacion
	 */
	public Long getOrdenImputacion() {
		return ordenImputacion;
	}

	/**
	 * @param ordenImputacion
	 *            the ordenImputacion to set
	 */
	public void setOrdenImputacion(Long ordenImputacion) {
		this.ordenImputacion = ordenImputacion;
	}

	/**
	 * @return the fechaClave
	 */
	public String getFechaClave() {
		return fechaClave;
	}

	/**
	 * @param fechaClave
	 *            the fechaClave to set
	 */
	public void setFechaClave(String fechaClave) {
		this.fechaClave = fechaClave;
	}

	/**
	 * @return the idTipoComprobanteUso
	 */
	public Long getIdTipoComprobanteUso() {
		return idTipoComprobanteUso;
	}

	/**
	 * @param idTipoComprobanteUso the idTipoComprobanteUso to set
	 */
	public void setIdTipoComprobanteUso(Long idTipoComprobanteUso) {
		this.idTipoComprobanteUso = idTipoComprobanteUso;
	}

	/**
	 * @return the tipoComprobante
	 */
	public ShvParamTipoComprobante getTipoComprobante() {
		return tipoComprobante;
	}

	/**
	 * @param tipoComprobante the tipoComprobante to set
	 */
	public void setTipoComprobante(ShvParamTipoComprobante tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	/**
	 * @return the sociedad
	 */
	public SociedadEnum getSociedad() {
		return sociedad;
	}

	/**
	 * @param sociedad the sociedad to set
	 */
	public void setSociedad(SociedadEnum sociedad) {
		this.sociedad = sociedad;
	}

	/**
	 * @return the sistema
	 */
	public SistemaEnum getSistema() {
		return sistema;
	}

	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}
}
