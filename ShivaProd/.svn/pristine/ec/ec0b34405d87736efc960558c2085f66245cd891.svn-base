package ar.com.telecom.shiva.persistencia.modelo.param;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_TIPO_COMPROBANTE")
public class ShvParamTipoComprobante extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_TIPO_COMPROBANTE", nullable = false)
	private String idTipoComprobante;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Enumerated(EnumType.STRING)
	@Column(name = "HABILITADA", nullable = false)
	private SiNoEnum habilitada;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH}, mappedBy = "tipoComprobante")
	private Set<ShvParamTipoComprobanteUso> usos = new HashSet<ShvParamTipoComprobanteUso>();
	
	
	public String getIdTipoComprobante() {
		return idTipoComprobante;
	}

	public void setIdTipoComprobante(String idTipoComprobante) {
		this.idTipoComprobante = idTipoComprobante;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public SiNoEnum getHabilitada() {
		return habilitada;
	}

	public void setHabilitada(SiNoEnum habilitada) {
		this.habilitada = habilitada;
	}

	/**
	 * @return the usos
	 */
	public Set<ShvParamTipoComprobanteUso> getUsos() {
		return usos;
	}

	/**
	 * @param usos the usos to set
	 */
	public void setUsos(Set<ShvParamTipoComprobanteUso> usos) {
		this.usos = usos;
	}

}
