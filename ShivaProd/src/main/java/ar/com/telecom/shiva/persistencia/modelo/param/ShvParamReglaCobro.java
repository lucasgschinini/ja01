package ar.com.telecom.shiva.persistencia.modelo.param;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.base.enumeradores.AccionesReglaCobroEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoReglaCobroEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_REGLA_COBRO")
public class ShvParamReglaCobro extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_REGLA")
	private Long idRegla;

	@Enumerated(EnumType.STRING)
	@Column (name="TIPO_REGLA")
	private TipoReglaCobroEnum tipoRegla;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_EMPRESA") 
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamEmpresa empresa;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_SEGMENTO") 
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamSegmento segmento;
	
	@Column (name="PORC_MINIMO")
	private BigDecimal porcentajeMinimo;
	
	@Column (name="PORC_MAXIMO")
	private BigDecimal porcentajeMaximo;
	
	@Enumerated(EnumType.STRING)
	@Column (name="MONEDA")
	private MonedaEnum moneda;
	
	@Column (name="MONTO_MINIMO")
	private BigDecimal montoMinimo;
	
	@Column (name="MONTO_MAXIMO")
	private BigDecimal montoMaximo;
	
	@Enumerated(EnumType.STRING)
	@Column (name="ACCION")
	private AccionesReglaCobroEnum accion;

	/**
	 * @return the idRegla
	 */
	public Long getIdRegla() {
		return idRegla;
	}

	/**
	 * @param idRegla the idRegla to set
	 */
	public void setIdRegla(Long idRegla) {
		this.idRegla = idRegla;
	}

	/**
	 * @return the tipoRegla
	 */
	public TipoReglaCobroEnum getTipoRegla() {
		return tipoRegla;
	}

	/**
	 * @param tipoRegla the tipoRegla to set
	 */
	public void setTipoRegla(TipoReglaCobroEnum tipoRegla) {
		this.tipoRegla = tipoRegla;
	}

	/**
	 * @return the empresa
	 */
	public ShvParamEmpresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(ShvParamEmpresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the segmento
	 */
	public ShvParamSegmento getSegmento() {
		return segmento;
	}

	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(ShvParamSegmento segmento) {
		this.segmento = segmento;
	}

	/**
	 * @return the porcentajeMinimo
	 */
	public BigDecimal getPorcentajeMinimo() {
		return porcentajeMinimo;
	}

	/**
	 * @param porcentajeMinimo the porcentajeMinimo to set
	 */
	public void setPorcentajeMinimo(BigDecimal porcentajeMinimo) {
		this.porcentajeMinimo = porcentajeMinimo;
	}

	/**
	 * @return the porcentajeMaximo
	 */
	public BigDecimal getPorcentajeMaximo() {
		return porcentajeMaximo;
	}

	/**
	 * @param porcentajeMaximo the porcentajeMaximo to set
	 */
	public void setPorcentajeMaximo(BigDecimal porcentajeMaximo) {
		this.porcentajeMaximo = porcentajeMaximo;
	}

	/**
	 * @return the moneda
	 */
	public MonedaEnum getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the montoMinimo
	 */
	public BigDecimal getMontoMinimo() {
		return montoMinimo;
	}

	/**
	 * @param montoMinimo the montoMinimo to set
	 */
	public void setMontoMinimo(BigDecimal montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	/**
	 * @return the montoMaximo
	 */
	public BigDecimal getMontoMaximo() {
		return montoMaximo;
	}

	/**
	 * @param montoMaximo the montoMaximo to set
	 */
	public void setMontoMaximo(BigDecimal montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

	/**
	 * @return the accion
	 */
	public AccionesReglaCobroEnum getAccion() {
		return accion;
	}

	/**
	 * @param accion the accion to set
	 */
	public void setAccion(AccionesReglaCobroEnum accion) {
		this.accion = accion;
	}
	
	
}
