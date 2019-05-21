/**
 * 
 */
package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoCargoGeneradoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;

/**
 * @author u564030
 *
 */
@Entity
@Table(name = "SHV_COB_TRATAMIENTO_DIFERENCIA")
@Inheritance(strategy=InheritanceType.JOINED)
public class ShvCobTratamientoDiferencia extends Modelo {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_TRATAMIENTO_DIFER")
    @SequenceGenerator(name="SEQ_SHV_COB_TRATAMIENTO_DIFER", sequenceName="SEQ_SHV_COB_TRATAMIENTO_DIFER", allocationSize = 1)
	@Column(name="ID_TRATAMIENTO_DIFERENCIA")
	private Integer idTratamientoDiferencia;
	
	@Transient
	private Integer idTratamientoDiferenciaCobro;

	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="ID_TRANSACCION", referencedColumnName="ID_TRANSACCION") 	
	private ShvCobTransaccion transaccion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO")
	private EstadoFacturaMedioPagoEnum estado;

	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_TRATAMIENTO_DIFERENCIA")
	private TipoTratamientoDiferenciaEnum tipoTratamientoDiferencia;

	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_TIPO_MEDIO_PAGO") 
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamTipoMedioPago tipoMedioPago;
	
	@Column(name="IMPORTE")
	private BigDecimal importe;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA_ORIGEN", nullable = true)
	private SistemaEnum sistemaOrigen;
	
	@Column(name = "FECHA_VALOR")
	private Date fechaValor;
	
	@Column(name="NUMERO_TRAMITE_REINTEGRO")
	private Long numeroTramiteReintegro;

	@Column(name="FECHA_TRAMITE_REINTEGRO")
	private Date fechaTramiteReintegro;
	
	@Column(name="ACUERDO_TRASLADO_CARGO")
	private String acuerdoTrasladoCargo;

	@Column(name="ACUERDO_TRASLADO_CARGO_ORIGNAL")
	private String acuerdoTrasladoCargoOriginal;

	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO_ACUERDO_TRASLADO_CARGO", nullable = true)
	private EstadoAcuerdoFacturacionEnum estadoAcuerdoTrasladoCargo;

	@Column(name="ID_CLIENTE_ACUERDO_TRAS_CARGO")
	private Long idClienteLegadoAcuerdoTrasladoCargo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CALCULAR_FECHA_HASTA")
	private SiNoEnum calcularFechaHasta;
	
	@Enumerated(EnumType.STRING)
	@Column(name="QUE_HACER_CON_INTERESES")
	private TratamientoInteresesEnum queHacerConIntereses;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CHECK_TRASLADAR_INTERESES")
	private SiNoEnum trasladarIntereses;

	@Column(name="PORCENTAJE_BONIF_INTERESES")
	private Integer porcentajeBonifIntereses;
	
	@Column(name="IMPORTE_BONIF_INTERESES")
	private BigDecimal importeBonificacionIntereses;

	@Enumerated(EnumType.STRING)
	@Column(name="ORIGEN_CARGO")
	private OrigenCargoEnum origenCargo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ORIGEN_INTERES")
	private OrigenCargoEnum origenInteres;
	
	@Column(name="LEYENDA_FACTURA_CARGO")
	private String leyendaFacturaCargo;
	
	@Column(name="LEYENDA_FACTURA_INTERES")
	private String leyendaFacturaInteres;
	
	@Column(name="COBRADOR_INTERESES_GENERADOS")
	private BigDecimal cobradorInteresesGenerados;
	
	@Column(name="COBRADOR_INTERESES_TRASLADADOS")
	private BigDecimal cobradorInteresesTrasladados;

	@Column(name="COBRADOR_INTERESES_BONIFICADOS")
	private BigDecimal cobradorInteresesBonificados;
	
	@Column(name="COBRADOR_MONTO_A_CUENTA")
	private BigDecimal montoACuentaCobrador;

	@Column(name="COBRADOR_ID_MOV_MER")
	private Long idMovMerCobrador;
	
	@Column(name="MENSAJE_ESTADO")
	private String mensajeEstado;

	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_MENSAJE_ESTADO")
	private TipoMensajeEstadoEnum tipoMensajeEstado;

	//
	// Descobros, sprint 7, u573005, fabio.giaquinta.ruiz
	//
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO_CARGO_GENERADO", nullable = true)
	private EstadoCargoGeneradoEnum estadoCargoGenerado;
	
	@Column(name="COBRADOR_INTERES_REAL_TRAS", nullable = true)
	private BigDecimal cobradorInteresesRealesTrasladados;
	
	@Column(name="COBRADOR_INTERES_REAL_NRG_TRAS", nullable = true)
	private BigDecimal cobradorInteresesRealesNoReguladosTrasladados;
	
	@Column(name="COBRADOR_INTERES_REAL_RG_TRAS", nullable = true)
	private BigDecimal cobradorInteresesRealesReguladosTrasladados;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA_ACUERDO_CONTRACARGO", nullable = true)
	private SistemaEnum sistemaAcuerdoContracargo;
	
	@Column(name="ACUERDO_CONTRACARGO", nullable = true)
	private String  acuerdoContracargo;

	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO_ACUERDO_CONTRACARGO", nullable = true)
	private EstadoAcuerdoFacturacionEnum estadoAcuerdoContracargo;

	@Column(name="ID_CLIENTE_ACUERDO_CONTRACARGO", nullable = true)
	private Long idClienteLegadoAcuerdoContracargo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA")
	private MonedaEnum moneda;
	
	@Column(name="REFERENCIA")
	private String referencia;
	
	/**
	 * @return the idTratamientoDiferencia
	 */
	public Integer getIdTratamientoDiferencia() {
		return idTratamientoDiferencia;
	}

	/**
	 * @param idTratamientoDiferencia the idTratamientoDiferencia to set
	 */
	public void setIdTratamientoDiferencia(Integer idReintegro) {
		this.idTratamientoDiferencia = idReintegro;
	}

	/**
	 * @return the transaccion
	 */
	public ShvCobTransaccion getTransaccion() {
		return transaccion;
	}

	/**
	 * @param transaccion the transaccion to set
	 */
	public void setTransaccion(ShvCobTransaccion transaccion) {
		this.transaccion = transaccion;
	}

	/**
	 * @return the estado
	 */
	public EstadoFacturaMedioPagoEnum getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoFacturaMedioPagoEnum estado) {
		this.estado = estado;
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
	public void setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum tipoReintegro) {
		this.tipoTratamientoDiferencia = tipoReintegro;
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
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * @return the numeroTramite
	 */
	public Long getNumeroTramiteReintegro() {
		return numeroTramiteReintegro;
	}

	/**
	 * @param numeroTramite the numeroTramite to set
	 */
	public void setNumeroTramiteReintegro(Long numeroTramite) {
		this.numeroTramiteReintegro = numeroTramite;
	}

	/**
	 * @return the fechaTramite
	 */
	public Date getFechaTramiteReintegro() {
		return fechaTramiteReintegro;
	}

	/**
	 * @param fechaTramite the fechaTramite to set
	 */
	public void setFechaTramiteReintegro(Date fechaTramite) {
		this.fechaTramiteReintegro = fechaTramite;
	}

	/**
	 * @return the acuerdoTrasladoCargo
	 */
	public String getAcuerdoTrasladoCargo() {
		return acuerdoTrasladoCargo;
	}

	/**
	 * @return the acuerdoTrasladoCargoOriginal
	 */
	public String getAcuerdoTrasladoCargoOriginal() {
		return acuerdoTrasladoCargoOriginal;
	}

	/**
	 * @param acuerdoTrasladoCargoOriginal the acuerdoTrasladoCargoOriginal to set
	 */
	public void setAcuerdoTrasladoCargoOriginal(String acuerdoTrasladoCargoOriginal) {
		this.acuerdoTrasladoCargoOriginal = acuerdoTrasladoCargoOriginal;
	}

	/**
	 * @param acuerdoTrasladoCargo the acuerdoTrasladoCargo to set
	 */
	public void setAcuerdoTrasladoCargo(String acuerdoTrasladoCargo) {
		this.acuerdoTrasladoCargo = acuerdoTrasladoCargo;
	}

	/**
	 * @return the calcularFechaHasta
	 */
	public SiNoEnum getCalcularFechaHasta() {
		return calcularFechaHasta;
	}

	/**
	 * @param calcularFechaHasta the calcularFechaHasta to set
	 */
	public void setCalcularFechaHasta(SiNoEnum calcularFechaHasta) {
		this.calcularFechaHasta = calcularFechaHasta;
	}

	/**
	 * @return the queHacerConIntereses
	 */
	public TratamientoInteresesEnum getQueHacerConIntereses() {
		return queHacerConIntereses;
	}

	/**
	 * @param queHacerConIntereses the queHacerConIntereses to set
	 */
	public void setQueHacerConIntereses(
			TratamientoInteresesEnum queHacerConIntereses) {
		this.queHacerConIntereses = queHacerConIntereses;
	}

	/**
	 * @return the origenCargo
	 */
	public OrigenCargoEnum getOrigenCargo() {
		return origenCargo;
	}

	/**
	 * @param origenCargo the origenCargo to set
	 */
	public void setOrigenCargo(OrigenCargoEnum origenCargo) {
		this.origenCargo = origenCargo;
	}

	/**
	 * @return the interesesGeneradosCobrador
	 */
	public BigDecimal getCobradorInteresesGenerados() {
		return cobradorInteresesGenerados;
	}

	/**
	 * @param interesesGeneradosCobrador the interesesGeneradosCobrador to set
	 */
	public void setCobradorInteresesGenerados(BigDecimal interesesGeneradosCobrador) {
		this.cobradorInteresesGenerados = interesesGeneradosCobrador;
	}

	/**
	 * @return the interesesBonificadosCobrador
	 */
	public BigDecimal getCobradorInteresesBonificados() {
		return cobradorInteresesBonificados;
	}

	/**
	 * @param interesesBonificadosCobrador the interesesBonificadosCobrador to set
	 */
	public void setCobradorInteresesBonificados(
			BigDecimal interesesBonificadosCobrador) {
		this.cobradorInteresesBonificados = interesesBonificadosCobrador;
	}

	/**
	 * @return the montoACuentaCobrador
	 */
	public BigDecimal getMontoACuentaCobrador() {
		return montoACuentaCobrador;
	}

	/**
	 * @param montoACuentaCobrador the montoACuentaCobrador to set
	 */
	public void setMontoACuentaCobrador(BigDecimal montoACuentaCobrador) {
		this.montoACuentaCobrador = montoACuentaCobrador;
	}

	/**
	 * @return the idMovMerCobrador
	 */
	public Long getIdMovMerCobrador() {
		return idMovMerCobrador;
	}

	/**
	 * @param idMovMerCobrador the idMovMerCobrador to set
	 */
	public void setIdMovMerCobrador(Long idMovMerCobrador) {
		this.idMovMerCobrador = idMovMerCobrador;
	}

	/**
	 * @return the mensajeEstado
	 */
	public String getMensajeEstado() {
		return mensajeEstado;
	}

	/**
	 * @param mensajeEstado the mensajeEstado to set
	 */
	public void setMensajeEstado(String mensajeEstado) {
		this.mensajeEstado = mensajeEstado;
	}

	/**
	 * @return the idClienteAcuerdoFacturacion
	 */
	public Long getIdClienteLegadoAcuerdoTrasladoCargo() {
		return idClienteLegadoAcuerdoTrasladoCargo;
	}

	/**
	 * @param idClienteAcuerdoFacturacion the idClienteAcuerdoFacturacion to set
	 */
	public void setIdClienteLegadoAcuerdoTrasladoCargo(Long idClienteAcuerdoFacturacion) {
		idClienteLegadoAcuerdoTrasladoCargo = idClienteAcuerdoFacturacion;
	}

	/**
	 * @return the trasladarIntereses
	 */
	public SiNoEnum getTrasladarIntereses() {
		return trasladarIntereses;
	}

	/**
	 * @param trasladarIntereses the trasladarIntereses to set
	 */
	public void setTrasladarIntereses(SiNoEnum trasladarIntereses) {
		this.trasladarIntereses = trasladarIntereses;
	}

	/**
	 * @return the porcentajeBonifIntereses
	 */
	public Integer getPorcentajeBonifIntereses() {
		return porcentajeBonifIntereses;
	}

	/**
	 * @param porcentajeBonifIntereses the porcentajeBonifIntereses to set
	 */
	public void setPorcentajeBonifIntereses(Integer porcentajeBonifIntereses) {
		this.porcentajeBonifIntereses = porcentajeBonifIntereses;
	}

	/**
	 * @return the importeBonificacionIntereses
	 */
	public BigDecimal getImporteBonificacionIntereses() {
		return importeBonificacionIntereses;
	}

	/**
	 * @param importeBonificacionIntereses the importeBonificacionIntereses to set
	 */
	public void setImporteBonificacionIntereses(
			BigDecimal importeBonificacionIntereses) {
		this.importeBonificacionIntereses = importeBonificacionIntereses;
	}

	/**
	 * @return the leyendaFacturaCargo
	 */
	public String getLeyendaFacturaCargo() {
		return leyendaFacturaCargo;
	}

	/**
	 * @param leyendaFacturaCargo the leyendaFacturaCargo to set
	 */
	public void setLeyendaFacturaCargo(String leyendaFacturaCargo) {
		this.leyendaFacturaCargo = leyendaFacturaCargo;
	}

	/**
	 * @return the leyendaFacturaInteres
	 */
	public String getLeyendaFacturaInteres() {
		return leyendaFacturaInteres;
	}

	/**
	 * @param leyendaFacturaInteres the leyendaFacturaInteres to set
	 */
	public void setLeyendaFacturaInteres(String leyendaFacturaInteres) {
		this.leyendaFacturaInteres = leyendaFacturaInteres;
	}

	/**
	 * @return the estadoAcuerdoTrasladoCargo
	 */
	public EstadoAcuerdoFacturacionEnum getEstadoAcuerdoTrasladoCargo() {
		return estadoAcuerdoTrasladoCargo;
	}

	/**
	 * @param estadoAcuerdoTrasladoCargo the estadoAcuerdoTrasladoCargo to set
	 */
	public void setEstadoAcuerdoTrasladoCargo(
			EstadoAcuerdoFacturacionEnum estadoAcuerdoTrasladoCargo) {
		this.estadoAcuerdoTrasladoCargo = estadoAcuerdoTrasladoCargo;
	}

	/**
	 * @return the tipoMensajeEstado
	 */
	public TipoMensajeEstadoEnum getTipoMensajeEstado() {
		return tipoMensajeEstado;
	}

	/**
	 * @param tipoMensajeEstado the tipoMensajeEstado to set
	 */
	public void setTipoMensajeEstado(TipoMensajeEstadoEnum tipoMensajeEstado) {
		this.tipoMensajeEstado = tipoMensajeEstado;
	}

	/**
	 * @return the fechaValor
	 */
	public Date getFechaValor() {
		return fechaValor;
	}

	/**
	 * @param fechaValor the fechaValor to set
	 */
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
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
	 * @return the cobradorInteresesTrasladados
	 */
	public BigDecimal getCobradorInteresesTrasladados() {
		return cobradorInteresesTrasladados;
	}

	/**
	 * @param cobradorInteresesTrasladados the cobradorInteresesTrasladados to set
	 */
	public void setCobradorInteresesTrasladados(
			BigDecimal cobradorInteresesTrasladados) {
		this.cobradorInteresesTrasladados = cobradorInteresesTrasladados;
	}

	/**
	 * @return the sistemaOrigen
	 */
	public SistemaEnum getSistemaOrigen() {
		return sistemaOrigen;
	}

	/**
	 * @param sistemaOrigen the sistemaOrigen to set
	 */
	public void setSistemaOrigen(SistemaEnum sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}
	
	public EstadoCargoGeneradoEnum getEstadoCargoGenerado() {
		return estadoCargoGenerado;
	}

	public void setEstadoCargoGenerado(EstadoCargoGeneradoEnum estadoCargoGenerado) {
		this.estadoCargoGenerado = estadoCargoGenerado;
	}

	public SistemaEnum getSistemaAcuerdoContracargo() {
		return sistemaAcuerdoContracargo;
	}

	public void setSistemaAcuerdoContracargo(SistemaEnum sistemaContracargo) {
		this.sistemaAcuerdoContracargo = sistemaContracargo;
	}

	public String getAcuerdoContracargo() {
		return acuerdoContracargo;
	}

	public void setAcuerdoContracargo(String acuerdoContracargo) {
		this.acuerdoContracargo = acuerdoContracargo;
	}

	public EstadoAcuerdoFacturacionEnum getEstadoAcuerdoContracargo() {
		return estadoAcuerdoContracargo;
	}

	public void setEstadoAcuerdoContracargo(
			EstadoAcuerdoFacturacionEnum estadoAcuerdoContracargo) {
		this.estadoAcuerdoContracargo = estadoAcuerdoContracargo;
	}

	public Long getIdClienteLegadoAcuerdoContracargo() {
		return idClienteLegadoAcuerdoContracargo;
	}

	public void setIdClienteLegadoAcuerdoContracargo(
			Long idClienteLegadoAcuerdoContracargo) {
		this.idClienteLegadoAcuerdoContracargo = idClienteLegadoAcuerdoContracargo;
	}

	public BigDecimal getCobradorInteresesRealesTrasladados() {
		return cobradorInteresesRealesTrasladados;
	}

	public void setCobradorInteresesRealesTrasladados(BigDecimal cobradorInteresesRealesTrasladados) {
		this.cobradorInteresesRealesTrasladados = cobradorInteresesRealesTrasladados;
	}

	public BigDecimal getCobradorInteresesRealesNoReguladosTrasladados() {
		return cobradorInteresesRealesNoReguladosTrasladados;
	}

	public void setCobradorInteresesRealesNoReguladosTrasladados(
			BigDecimal cobradorInteresesRealesNoReguladosTrasladados) {
		this.cobradorInteresesRealesNoReguladosTrasladados = cobradorInteresesRealesNoReguladosTrasladados;
	}

	public BigDecimal getCobradorInteresesRealesReguladosTrasladados() {
		return cobradorInteresesRealesReguladosTrasladados;
	}

	public void setCobradorInteresesRealesReguladosTrasladados(
			BigDecimal cobradorInteresesRealesReguladosTrasladados) {
		this.cobradorInteresesRealesReguladosTrasladados = cobradorInteresesRealesReguladosTrasladados;
	}

	public OrigenCargoEnum getOrigenInteres() {
		return origenInteres;
	}

	public void setOrigenInteres(OrigenCargoEnum origenInteres) {
		this.origenInteres = origenInteres;
	}

	public Integer getIdTratamientoDiferenciaCobro() {
		return idTratamientoDiferenciaCobro;
	}

	public void setIdTratamientoDiferenciaCobro(Integer idTratamientoDiferenciaCobro) {
		this.idTratamientoDiferenciaCobro = idTratamientoDiferenciaCobro;
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