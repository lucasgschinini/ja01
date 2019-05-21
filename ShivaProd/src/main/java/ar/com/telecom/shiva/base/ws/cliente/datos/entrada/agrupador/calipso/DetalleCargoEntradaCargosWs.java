package ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.AlgoritmoMoraEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMoraEnum;

public class DetalleCargoEntradaCargosWs {
	
	protected String acuerdoFacturacion;
	protected BigDecimal importe;
	protected Date fechaDesde;
	protected Date fechaHasta;
	protected TipoMoraEnum tipoMora;
	protected AlgoritmoMoraEnum algoritmoMora;
	protected BigDecimal importeBonificacionIntereses;
	protected OrigenCargoEnum origenCargo;
	protected String leyendaFacturaCargo;
	protected String leyendaFacturaInteres;
	protected MonedaEnum monedaCargo;
	
	public String getAcuerdoFacturacion() {
		return acuerdoFacturacion;
	}

	public void setAcuerdoFacturacion(String acuerdoFacturacion) {
		this.acuerdoFacturacion = acuerdoFacturacion;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public TipoMoraEnum getTipoMora() {
		return tipoMora;
	}

	public void setTipoMora(TipoMoraEnum tipoMora) {
		this.tipoMora = tipoMora;
	}

	public AlgoritmoMoraEnum getAlgoritmoMora() {
		return algoritmoMora;
	}

	public void setAlgoritmoMora(AlgoritmoMoraEnum algoritmoMora) {
		this.algoritmoMora = algoritmoMora;
	}

	public BigDecimal getImporteBonificacionIntereses() {
		return importeBonificacionIntereses;
	}

	public void setImporteBonificacionIntereses(
			BigDecimal importeBonificacionIntereses) {
		this.importeBonificacionIntereses = importeBonificacionIntereses;
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

	public MonedaEnum getMonedaCargo() {
		return monedaCargo;
	}

	public void setMonedaCargo(MonedaEnum monedaCargo) {
		this.monedaCargo = monedaCargo;
	}
	
}
