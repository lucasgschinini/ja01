package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoCajaHistorialEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;

@Entity
@Table (name = "SHV_CAJ_CAJA_HISTORIAL")
public class ShvCajCajaHistorial extends Modelo {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_CAJ_CAJA_HISTORIAL")
	@SequenceGenerator (name = "SEQ_SHV_CAJ_CAJA_HISTORIAL", sequenceName = "SEQ_SHV_CAJ_CAJA_HISTORIAL", allocationSize = 1)
	@Column (name = "ID_CAJA_HISTORIAL")
	private Long idCajaHistorial;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_CAJA") 
	private ShvCajCaja caja;

	@Enumerated(EnumType.STRING)
	@Column (name = "ESTADO")
	private EstadoCajaHistorialEnum estado;
	
	@Column (name = "FECHA_APERTURA")
	private Date fechaApertura;
	
	@Column (name = "FECHA_CIERRE")
	private Date fechaCierre;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "CONTABILIDAD")
	private SiNoEnum contabilidad;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "DACOTA")
	private SiNoEnum dacota;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "MOROSIDAD")
	private SiNoEnum morosidad;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "GEO")
	private SiNoEnum geo;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "SCARD")
	private SiNoEnum scard;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "SIGMA")
	private SiNoEnum sigma;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "TAGETIK")
	private SiNoEnum tagetik;

	/**
	 * @return the caja
	 */
	public ShvCajCaja getCaja() {
		return caja;
	}

	/**
	 * @param caja the caja to set
	 */
	public void setCaja(ShvCajCaja caja) {
		this.caja = caja;
	}

	/**
	 * @return the idCajaHistorial
	 */
	public Long getIdCajaHistorial() {
		return idCajaHistorial;
	}

	/**
	 * @param idCajaHistorial the idCajaHistorial to set
	 */
	public void setIdCajaHistorial(Long idCajaHistorial) {
		this.idCajaHistorial = idCajaHistorial;
	}

	/**
	 * @return the estado
	 */
	public EstadoCajaHistorialEnum getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoCajaHistorialEnum estado) {
		this.estado = estado;
	}

	/**
	 * @return the fechaApertura
	 */
	public Date getFechaApertura() {
		return fechaApertura;
	}

	/**
	 * @param fechaApertura the fechaApertura to set
	 */
	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	/**
	 * @return the fechaCierre
	 */
	public Date getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * @param fechaCierre the fechaCierre to set
	 */
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * @return the contabilidad
	 */
	public SiNoEnum getContabilidad() {
		return contabilidad;
	}

	/**
	 * @param contabilidad the contabilidad to set
	 */
	public void setContabilidad(SiNoEnum contabilidad) {
		this.contabilidad = contabilidad;
	}

	/**
	 * @return the dacota
	 */
	public SiNoEnum getDacota() {
		return dacota;
	}

	/**
	 * @param dacota the dacota to set
	 */
	public void setDacota(SiNoEnum dacota) {
		this.dacota = dacota;
	}

	/**
	 * @return the morosidad
	 */
	public SiNoEnum getMorosidad() {
		return morosidad;
	}

	/**
	 * @param morosidad the morosidad to set
	 */
	public void setMorosidad(SiNoEnum morosidad) {
		this.morosidad = morosidad;
	}

	/**
	 * @return the geo
	 */
	public SiNoEnum getGeo() {
		return geo;
	}

	/**
	 * @param geo the geo to set
	 */
	public void setGeo(SiNoEnum geo) {
		this.geo = geo;
	}

	/**
	 * @return the scard
	 */
	public SiNoEnum getScard() {
		return scard;
	}

	/**
	 * @param scard the scard to set
	 */
	public void setScard(SiNoEnum scard) {
		this.scard = scard;
	}

	/**
	 * @return the sigma
	 */
	public SiNoEnum getSigma() {
		return sigma;
	}

	/**
	 * @param sigma the sigma to set
	 */
	public void setSigma(SiNoEnum sigma) {
		this.sigma = sigma;
	}

	/**
	 * @return the tagetik
	 */
	public SiNoEnum getTagetik() {
		return tagetik;
	}

	/**
	 * @param tagetik the tagetik to set
	 */
	public void setTagetik(SiNoEnum tagetik) {
		this.tagetik = tagetik;
	}
	
}
