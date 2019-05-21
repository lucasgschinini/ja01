package ar.com.telecom.shiva.persistencia.modelo.param;


import java.io.Serializable;
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
@Table(name = "SHV_PARAM_TIPO_MEDIO_PAGO")
public class ShvParamTipoMedioPago extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column (name="ID_TIPO_MEDIO_PAGO")
	private String idTipoMedioPago;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, mappedBy = "tipoMedioPago")
	private Set<ShvParamValorMedioPago> valorMedioPago = new HashSet<ShvParamValorMedioPago>(0);
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="ORDEN_IMPUTACION")
	private Integer ordenImputacion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="GENERA_INTERESES")
	private SiNoEnum generaIntereses;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH}, mappedBy = "tipoMedioPago")
	private Set<ShvParamTipoMedioPagoUso> usos = new HashSet<ShvParamTipoMedioPagoUso>();
	
	@Override
	public Serializable getId() {
		return this.getIdTipoMedioPago();
	}

	/**
	 * @return the idTipoMedioPago
	 */
	public String getIdTipoMedioPago() {
		return idTipoMedioPago;
	}

	/**
	 * @param idTipoMedioPago the idTipoMedioPago to set
	 */
	public void setIdTipoMedioPago(String idTipoMedioPago) {
		this.idTipoMedioPago = idTipoMedioPago;
	}

	/**
	 * @return the valorMedioPago
	 */
	public Set<ShvParamValorMedioPago> getValorMedioPago() {
		return valorMedioPago;
	}

	/**
	 * @param valorMedioPago the valorMedioPago to set
	 */
	public void setValorMedioPago(Set<ShvParamValorMedioPago> valorMedioPago) {
		this.valorMedioPago = valorMedioPago;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the ordenImputacion
	 */
	public Integer getOrdenImputacion() {
		return ordenImputacion;
	}

	/**
	 * @param ordenImputacion the ordenImputacion to set
	 */
	public void setOrdenImputacion(Integer ordenImputacion) {
		this.ordenImputacion = ordenImputacion;
	}

	/**
	 * @return the generaIntereses
	 */
	public SiNoEnum getGeneraIntereses() {
		return generaIntereses;
	}

	/**
	 * @param generaIntereses the generaIntereses to set
	 */
	public void setGeneraIntereses(SiNoEnum generaIntereses) {
		this.generaIntereses = generaIntereses;
	}

	/**
	 * @return the usos
	 */
	public Set<ShvParamTipoMedioPagoUso> getUsos() {
		return usos;
	}

	/**
	 * @param usos the usos to set
	 */
	public void setUsos(Set<ShvParamTipoMedioPagoUso> uso) {
		this.usos = uso;
	}
}
