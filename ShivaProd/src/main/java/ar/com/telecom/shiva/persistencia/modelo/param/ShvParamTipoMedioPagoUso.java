/**
 * 
 */
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

import ar.com.telecom.shiva.base.enumeradores.EstadoActivoInactivoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

/**
 * @author u564030
 *
 */
@Entity
@Table(name = "SHV_PARAM_TIPO_MEDIO_PAGO_USO")
public class ShvParamTipoMedioPagoUso extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column (name="ID_TIPO_MEDIO_PAGO_USO")
	private Long idTipoMedioPagoUso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_MEDIO_PAGO", nullable = false)
	private ShvParamTipoMedioPago tipoMedioPago;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ID_MONEDA")
	private MonedaEnum moneda;
	
	@Enumerated(EnumType.STRING)
	@Column(name="PERMITE_USO_APLICACION_MANUAL")
	private SiNoEnum permiteUsoAplicacionManual;

    @Enumerated(EnumType.STRING)
	@Column(name="PERMITE_USO_REINTEGRO")
	private SiNoEnum permiteUsoReintegro;

	@Enumerated(EnumType.STRING)
	@Column(name="PERMITE_USO_DUC")
	private SiNoEnum permiteUsoDUC;

	@Enumerated(EnumType.STRING)
	@Column(name="PERMITE_USO_COBRO")
	private SiNoEnum permiteUsoCobro;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO")
	private EstadoActivoInactivoEnum estado;

	@Column(name="AUD_REQUERIMIENTO_ORIGEN")
	private String audRequerimientoOrigen;

	/**
	 * @return the idTipoMedioPagoUso
	 */
	public Long getIdTipoMedioPagoUso() {
		return idTipoMedioPagoUso;
	}

	/**
	 * @param idTipoMedioPagoUso the idTipoMedioPagoUso to set
	 */
	public void setIdTipoMedioPagoUso(Long idTipoMedioPagoUso) {
		this.idTipoMedioPagoUso = idTipoMedioPagoUso;
	}

	/**
	 * @return the tipoMedioPago
	 */
	public ShvParamTipoMedioPago getTipoMedioPago() {
		return tipoMedioPago;
	}

	/**
	 * @param tipoMedioPago the tipoMedioPago to set
	 */
	public void setTipoMedioPago(ShvParamTipoMedioPago tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}

	/**
	 * @return the moneda
	 */
	public MonedaEnum getMoneda() {
		return moneda;
	}

	public SiNoEnum getPermiteUsoAplicacionManual() {
		return permiteUsoAplicacionManual;
	}

	public void setPermiteUsoAplicacionManual(SiNoEnum permiteUsoAplicacionManual) {
		this.permiteUsoAplicacionManual = permiteUsoAplicacionManual;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the permiteUsoReintegro
	 */
	public SiNoEnum getPermiteUsoReintegro() {
		return permiteUsoReintegro;
	}

	/**
	 * @param permiteUsoReintegro the permiteUsoReintegro to set
	 */
	public void setPermiteUsoReintegro(SiNoEnum usoPermitidoReintegro) {
		this.permiteUsoReintegro = usoPermitidoReintegro;
	}

	/**
	 * @return the permiteUsoDUC
	 */
	public SiNoEnum getPermiteUsoDUC() {
		return permiteUsoDUC;
	}

	/**
	 * @param permiteUsoDUC the permiteUsoDUC to set
	 */
	public void setPermiteUsoDUC(SiNoEnum usoPermitidoDUC) {
		this.permiteUsoDUC = usoPermitidoDUC;
	}

	/**
	 * @return the permiteUsoCobro
	 */
	public SiNoEnum getPermiteUsoCobro() {
		return permiteUsoCobro;
	}

	/**
	 * @param permiteUsoCobro the permiteUsoCobro to set
	 */
	public void setPermiteUsoCobro(SiNoEnum usoPermitidoCobro) {
		this.permiteUsoCobro = usoPermitidoCobro;
	}

	/**
	 * @return the estado
	 */
	public EstadoActivoInactivoEnum getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoActivoInactivoEnum estado) {
		this.estado = estado;
	}

	/**
	 * @return the audRequerimientoOrigen
	 */
	public String getAudRequerimientoOrigen() {
		return audRequerimientoOrigen;
	}

	/**
	 * @param audRequerimientoOrigen the audRequerimientoOrigen to set
	 */
	public void setAudRequerimientoOrigen(String audRequerimientoOrigen) {
		this.audRequerimientoOrigen = audRequerimientoOrigen;
	}
}
