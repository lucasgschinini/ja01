package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;


@Entity
@Table(name = "SHV_COB_ED_TRATAMIENTO_DIF")
public class ShvCobEdTratamientoDiferencia extends Modelo  {
	private static final long serialVersionUID = 1L;

	@Id
	private Long idCobro;
	
	@MapsId 
	@OneToOne
	@JoinColumn(name = "ID_COBRO")
	private ShvCobEdCobro cobro;

	@Column(name="TIPO_TRATAMIENTO_DIFERENCIA")
	@Enumerated(EnumType.STRING)
	private TipoTratamientoDiferenciaEnum tipoTratamientoDiferencia;

	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_TIPO_MEDIO_PAGO") 
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamTipoMedioPago tipoMedioPago;

	@Column(name="IMPORTE")
	private BigDecimal importe;

	@Enumerated(EnumType.STRING)
	@Column(name="CON_CALCULO_INTERES")
	private SiNoEnum conCalculoInteres;

	@Column(name="NUMERO_TRAMITE_REINTEGRO")
	private Long numeroTramiteReintegro;
	
	@Column(name="FECHA_ALTA_TRAMITE_REINTEGRO")
	private Date fechaAltaTramiteReintegro;

	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA_DESTINO")		
	private SistemaEnum sistemaDestino;

	@Column(name="LINEA")
	private Long linea;

	@Column(name="ACUERDO_FACTURACION")
	private Long acuerdoFacturacion;
	
	@Column(name="ID_CLIENTE_ACUERDO_FACTURACION")
	private Long IdClienteAcuerdoFacturacion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO_ACUERDO_FACTURACION")
	private EstadoAcuerdoFacturacionEnum estadoAcuerdoFacturacion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA")
	private MonedaEnum moneda;
	
	@Column(name="REFERENCIA")
	private String referencia;
	
	public ShvCobEdTratamientoDiferencia() {
	}

	/**
	 * @return the idCobro
	 */
	public Long getIdCobro() {
		return idCobro;
	}

	/**
	 * @param idCobro the idCobro to set
	 */
	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}

	/**
	 * @return the cobro
	 */
	public ShvCobEdCobro getCobro() {
		return cobro;
	}

	/**
	 * @param cobro the cobro to set
	 */
	public void setCobro(ShvCobEdCobro cobro) {
		this.cobro = cobro;
	}

	/**
	 * @return the tipoTratamientoDiferencia
	 */
	public TipoTratamientoDiferenciaEnum getTipoTratamientoDiferencia() {
		return tipoTratamientoDiferencia;
	}

	/**
	 * @param tipoTratamientoDiferencia the tipoTratamientoDiferencia to set
	 */
	public void setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum tipoTratamientoDiferencia) {
		this.tipoTratamientoDiferencia = tipoTratamientoDiferencia;
	}

	/**
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importeCobrar) {
		this.importe = importeCobrar;
	}

	/**
	 * @return the conCalculoInteres
	 */
	public SiNoEnum getConCalculoInteres() {
		return conCalculoInteres;
	}

	/**
	 * @param conCalculoInteres the conCalculoInteres to set
	 */
	public void setConCalculoInteres(SiNoEnum conCalculoInteres) {
		this.conCalculoInteres = conCalculoInteres;
	}

	/**
	 * @return the numeroTramiteReintegro
	 */
	public Long getNumeroTramiteReintegro() {
		return numeroTramiteReintegro;
	}

	/**
	 * @param numeroTramiteReintegro the numeroTramiteReintegro to set
	 */
	public void setNumeroTramiteReintegro(Long numeroTramiteReintegro) {
		this.numeroTramiteReintegro = numeroTramiteReintegro;
	}

	/**
	 * @return the fechaAltaTramiteReintegro
	 */
	public Date getFechaAltaTramiteReintegro() {
		return fechaAltaTramiteReintegro;
	}

	/**
	 * @param fechaAltaTramiteReintegro the fechaAltaTramiteReintegro to set
	 */
	public void setFechaAltaTramiteReintegro(Date fechaAltaTramiteReintegro) {
		this.fechaAltaTramiteReintegro = fechaAltaTramiteReintegro;
	}

	/**
	 * @return the sistemaDestino
	 */
	public SistemaEnum getSistemaDestino() {
		return sistemaDestino;
	}

	/**
	 * @param sistemaDestino the sistemaDestino to set
	 */
	public void setSistemaDestino(SistemaEnum sistemaDestino) {
		this.sistemaDestino = sistemaDestino;
	}

	/**
	 * @return the linea
	 */
	public Long getLinea() {
		return linea;
	}

	/**
	 * @param linea the linea to set
	 */
	public void setLinea(Long linea) {
		this.linea = linea;
	}

	/**
	 * @return the acuerdoFacturacion
	 */
	public Long getAcuerdoFacturacion() {
		return acuerdoFacturacion;
	}

	/**
	 * @param acuerdoFacturacion the acuerdoFacturacion to set
	 */
	public void setAcuerdoFacturacion(Long acuerdoFacturacion) {
		this.acuerdoFacturacion = acuerdoFacturacion;
	}

	/**
	 * @return the idClienteAcuerdoFacturacion
	 */
	public Long getIdClienteAcuerdoFacturacion() {
		return IdClienteAcuerdoFacturacion;
	}

	/**
	 * @param idClienteAcuerdoFacturacion the idClienteAcuerdoFacturacion to set
	 */
	public void setIdClienteAcuerdoFacturacion(Long idClienteAcuerdoFacturacion) {
		IdClienteAcuerdoFacturacion = idClienteAcuerdoFacturacion;
	}

	public EstadoAcuerdoFacturacionEnum getEstadoAcuerdoFacturacion() {
		return estadoAcuerdoFacturacion;
	}

	public void setEstadoAcuerdoFacturacion(
			EstadoAcuerdoFacturacionEnum estadoAcuerdoFacturacion) {
		this.estadoAcuerdoFacturacion = estadoAcuerdoFacturacion;
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

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
}