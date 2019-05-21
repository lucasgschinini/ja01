package ar.com.telecom.shiva.negocio.dto.cobros;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.OrigenCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClienteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoIvaEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;

@SuppressWarnings("serial")
public class MicDetalleGeneracionCargosDto extends JMS {

	private String acuerdoFacturacion;
	private BigDecimal importeNoRegulado;
	private BigDecimal importeRegulado;
	private String fechaDesde; /**AAAAMMDD*/
	private String fechaHasta; /**AAAAMMDD*/
	private SiNoEnum calcularFechaHasta;
	private TratamientoInteresesEnum queHacerConLosIntereses;
	private BigDecimal importeBonificacionInteresesNoRegulado;
	private BigDecimal importeBonificacionInteresesRegulado;
	private TipoClienteEnum tipoCliente;
	private TipoIvaEnum tipoIva;
	private OrigenCargoEnum origenCargo;
	private String leyendaFacturaCargo;
	private String leyendaFacturaInteres;
	
	public String getAcuerdoFacturacion() {
		return acuerdoFacturacion;
	}
	public void setAcuerdoFacturacion(String acuerdoFacturacion) {
		this.acuerdoFacturacion = acuerdoFacturacion;
	}
	public BigDecimal getImporteNoRegulado() {
		return importeNoRegulado;
	}
	public void setImporteNoRegulado(BigDecimal importeNoRegulado) {
		this.importeNoRegulado = importeNoRegulado;
	}
	public BigDecimal getImporteRegulado() {
		return importeRegulado;
	}
	public void setImporteRegulado(BigDecimal importeRegulado) {
		this.importeRegulado = importeRegulado;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public SiNoEnum getCalcularFechaHasta() {
		return calcularFechaHasta;
	}
	public void setCalcularFechaHasta(SiNoEnum calcularFechaHasta) {
		this.calcularFechaHasta = calcularFechaHasta;
	}
	public TratamientoInteresesEnum getQueHacerConLosIntereses() {
		return queHacerConLosIntereses;
	}
	public void setQueHacerConLosIntereses(
			TratamientoInteresesEnum queHacerConLosIntereses) {
		this.queHacerConLosIntereses = queHacerConLosIntereses;
	}
	public BigDecimal getImporteBonificacionInteresesNoRegulado() {
		return importeBonificacionInteresesNoRegulado;
	}
	public void setImporteBonificacionInteresesNoRegulado(
			BigDecimal importeBonificacionInteresesNoRegulado) {
		this.importeBonificacionInteresesNoRegulado = importeBonificacionInteresesNoRegulado;
	}
	public BigDecimal getImporteBonificacionInteresesRegulado() {
		return importeBonificacionInteresesRegulado;
	}
	public void setImporteBonificacionInteresesRegulado(
			BigDecimal importeBonificacionInteresesRegulado) {
		this.importeBonificacionInteresesRegulado = importeBonificacionInteresesRegulado;
	}
	public TipoClienteEnum getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(TipoClienteEnum tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public TipoIvaEnum getTipoIva() {
		return tipoIva;
	}
	public void setTipoIva(TipoIvaEnum tipoIva) {
		this.tipoIva = tipoIva;
	}
	public OrigenCargoEnum getOrigenCargo() {
		return origenCargo;
	}
	public void setOrigenCargo(OrigenCargoEnum origenCargo) {
		this.origenCargo = origenCargo;
	}
	public String getLeyendaFacturaCargo() {
		return leyendaFacturaCargo;
	}
	public void setLeyendaFacturaCargo(String leyendaFacturaCargo) {
		this.leyendaFacturaCargo = leyendaFacturaCargo;
	}
	public String getLeyendaFacturaInteres() {
		return leyendaFacturaInteres;
	}
	public void setLeyendaFacturaInteres(String leyendaFacturaInteres) {
		this.leyendaFacturaInteres = leyendaFacturaInteres;
	}
	
}
