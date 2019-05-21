package ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;

/**
 * @author Pablo M. Ibarrola
 * 
 * Esta clase fue creada solo a efectos de poder usar el medio de pago "Debito a Proxima Factura" de manera
 * generica en la simulacion.
 * No aplica sobre el modelo de datos real.
 */
public class ShvCobMedioPagoDebitoProximaFacturaSinOperacion extends ShvCobMedioPagoUsuarioSinOperacion {
	
	private static final long serialVersionUID = 1L;
	
	private Date fecha;
	private String acuerdoTrasladoCargo;
	private String acuerdoTrasladoCargoOriginal;
	private EstadoAcuerdoFacturacionEnum estadoAcuerdoTrasladoCargo;
	private Long idClienteLegadoAcuerdoTrasladoCargo;
	private Date fechaVencimientoFactura;
	private SiNoEnum calcularFechaHasta;
	private TratamientoInteresesEnum queHacerConIntereses;
	private SiNoEnum trasladarIntereses;
	private Integer porcentajeBonifIntereses;
	private BigDecimal importeBonificacionIntereses;
	private OrigenCargoEnum origenCargo;
	private OrigenCargoEnum origenInteres;
	private String leyendaFacturaCargo;
	private String leyendaFacturaInteres;
	private BigDecimal cobradorIntereseGenerados;
	private BigDecimal cobradorInteresesTrasladados;
	private BigDecimal cobradorInteresesBonificados;
	private BigDecimal cobradorMontoACuenta;
	private Long cobradorIdMovMer;
	
	public Date getFechaValor() {
		return DateUtils.truncate(fecha, Calendar.DATE);
	}
	
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

	public OrigenCargoEnum getOrigenInteres() {
		return origenInteres;
	}

	public void setOrigenInteres(OrigenCargoEnum origenInteres) {
		this.origenInteres = origenInteres;
	}
	
}
