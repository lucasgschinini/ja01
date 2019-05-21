package ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateUtils;

import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoCargoGeneradoEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;

/**
 * 
 * @author Pablo M. Ibarrola
 *
 */
@Entity
@Table(name = "SHV_COB_MED_PAG_DEB_PROX_CLP")
@PrimaryKeyJoinColumn(name="ID_MEDIO_PAGO")
public class ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion extends ShvCobMedioPagoUsuarioSinOperacion {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="FECHA")
	private Date fecha;

	@Column(name="ACUERDO_TRASLADO_CARGO")
	private String acuerdoTrasladoCargo;

	@Column(name="ACUERDO_TRASLADO_CARGO_ORIGNAL")
	private String acuerdoTrasladoCargoOriginal;

	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO_ACUERDO_TRASLADO_CARGO", nullable = true)
	private EstadoAcuerdoFacturacionEnum estadoAcuerdoTrasladoCargo;

	@Column(name="ID_CLIENTE_ACUERDO_TRAS_CARGO")
	private Long idClienteLegadoAcuerdoTrasladoCargo;
	
	@Column(name="FECHA_VENCIMIENTO_FACTURA")
	private Date fechaVencimientoFactura;
	
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
	private BigDecimal cobradorIntereseGenerados;
	
	@Column(name="COBRADOR_INTERESES_TRASLADADOS")
	private BigDecimal cobradorInteresesTrasladados;

	@Column(name="COBRADOR_INTERESES_BONIFICADOS")
	private BigDecimal cobradorInteresesBonificados;
	
	@Column(name="COBRADOR_MONTO_A_CUENTA")
	private BigDecimal cobradorMontoACuenta;
	
	@Column(name="COBRADOR_ID_MOV_MER")
	private Long cobradorIdMovMer;
	
	//
	// Descobros, sprint 7, u573005, fabio.giaquinta.ruiz
	//
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO_CARGO_GENERADO", nullable = true)
	private EstadoCargoGeneradoEnum estadoCargoGenerado;
	
	@Column(name="COBRADOR_INTERES_REAL_TRAS", nullable = true)
	private BigDecimal cobradorInteresesRealesTrasladados;
	
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
	
	@Override
	public Date getFechaValor() {
		return DateUtils.truncate(fecha, Calendar.DATE);
	}
	
	@Override
	public String getReferencia() {
		return new String("");
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the acuerdoTrasladoCargo
	 */
	public String getAcuerdoTrasladoCargo() {
		return acuerdoTrasladoCargo;
	}

	/**
	 * @param acuerdoTrasladoCargo the acuerdoTrasladoCargo to set
	 */
	public void setAcuerdoTrasladoCargo(String acuerdoTrasladoCargo) {
		this.acuerdoTrasladoCargo = acuerdoTrasladoCargo;
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
	 * @return the idClienteAcuerdoFacturacion
	 */
	public Long getIdClienteLegadoAcuerdoTrasladoCargo() {
		return idClienteLegadoAcuerdoTrasladoCargo;
	}

	/**
	 * @param idClienteAcuerdoFacturacion the idClienteAcuerdoFacturacion to set
	 */
	public void setIdClienteLegadoAcuerdoTrasladoCargo(Long idClienteAcuerdoFacturacion) {
		this.idClienteLegadoAcuerdoTrasladoCargo = idClienteAcuerdoFacturacion;
	}

	/**
	 * @return the fechaVencimientoFactura
	 */
	public Date getFechaVencimientoFactura() {
		return fechaVencimientoFactura;
	}

	/**
	 * @param fechaVencimientoFactura the fechaVencimientoFactura to set
	 */
	public void setFechaVencimientoFactura(Date fechaVencimientoFactura) {
		this.fechaVencimientoFactura = fechaVencimientoFactura;
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
	 * @return the cobradorIntereseGenerados
	 */
	public BigDecimal getCobradorIntereseGenerados() {
		return cobradorIntereseGenerados;
	}

	/**
	 * @param cobradorIntereseGenerados the cobradorIntereseGenerados to set
	 */
	public void setCobradorIntereseGenerados(BigDecimal cobradorIntereseGenerados) {
		this.cobradorIntereseGenerados = cobradorIntereseGenerados;
	}

	/**
	 * @return the cobradorInteresesBonificados
	 */
	public BigDecimal getCobradorInteresesBonificados() {
		return cobradorInteresesBonificados;
	}

	/**
	 * @param cobradorInteresesBonificados the cobradorInteresesBonificados to set
	 */
	public void setCobradorInteresesBonificados(
			BigDecimal cobradorInteresesBonificados) {
		this.cobradorInteresesBonificados = cobradorInteresesBonificados;
	}

	/**
	 * @return the cobradorMontoACuenta
	 */
	public BigDecimal getCobradorMontoACuenta() {
		return cobradorMontoACuenta;
	}

	/**
	 * @param cobradorMontoACuenta the cobradorMontoACuenta to set
	 */
	public void setCobradorMontoACuenta(BigDecimal cobradorMontoACuenta) {
		this.cobradorMontoACuenta = cobradorMontoACuenta;
	}

	/**
	 * @return the cobradorIdMovMer
	 */
	public Long getCobradorIdMovMer() {
		return cobradorIdMovMer;
	}

	/**
	 * @param cobradorIdMovMer the cobradorIdMovMer to set
	 */
	public void setCobradorIdMovMer(Long cobradorIdMovMer) {
		this.cobradorIdMovMer = cobradorIdMovMer;
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

	public EstadoCargoGeneradoEnum getEstadoCargoGenerado() {
		return estadoCargoGenerado;
	}

	public void setEstadoCargoGenerado(EstadoCargoGeneradoEnum estadoCargoGenerado) {
		this.estadoCargoGenerado = estadoCargoGenerado;
	}


	public SistemaEnum getSistemaAcuerdoContracargo() {
		return sistemaAcuerdoContracargo;
	}

	public void setSistemaAcuerdoContracargo(SistemaEnum sistemaAcuerdoContracargo) {
		this.sistemaAcuerdoContracargo = sistemaAcuerdoContracargo;
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
	
	public BigDecimal getCobradorInteresesTrasladados() {
		return cobradorInteresesTrasladados;
	}

	public void setCobradorInteresesTrasladados(
			BigDecimal cobradorInteresesTrasladados) {
		this.cobradorInteresesTrasladados = cobradorInteresesTrasladados;
	}

	public OrigenCargoEnum getOrigenInteres() {
		return origenInteres;
	}

	public void setOrigenInteres(OrigenCargoEnum origenInteres) {
		this.origenInteres = origenInteres;
	}

}
