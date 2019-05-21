package ar.com.telecom.shiva.negocio.dto.cobros;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.dto.DTO;

@SuppressWarnings("serial")
public class MicDetalleDescobroFacturaDto extends DTO {
	
	private MicResultadoDto resultadoDescobroFactura;
	private String codigoEstadoCargoGenerado;
	private String detalleEstadoCargoGenerado;
	private String codigoEstadoAcuerdoFacturacion;
	private String descripcionEstadoAcuerdoFacturacion;
	private BigDecimal interesesRealesNoReguladosTrasladados;
	private BigDecimal interesesRealesReguladosTrasladados;	
	
	public String toString() {
		String str = "[resultadoDescobroFactura:"+String.valueOf(resultadoDescobroFactura)+"]," 
				+ "[codigoEstadoCargoGenerado:" + codigoEstadoCargoGenerado + "],"
				+ "[detalleEstadoCargoGenerado:" + detalleEstadoCargoGenerado + "],"
				+ "[codigoEstadoAcuerdoFacturacion:" + codigoEstadoAcuerdoFacturacion + "],"
				+ "[descripcionEstadoAcuerdoFacturacion:" + descripcionEstadoAcuerdoFacturacion + "],"
				+ "[interesesRealesNoReguladosTrasladados:" + String.valueOf(interesesRealesNoReguladosTrasladados) + "],"
				+ "[interesesRealesReguladosTrasladados:" + String.valueOf(interesesRealesReguladosTrasladados) + "]";
		return str;
	}

	public String getCodigoEstadoCargoGenerado() {
		return codigoEstadoCargoGenerado;
	}

	public void setCodigoEstadoCargoGenerado(String codigoEstadoCargoGenerado) {
		this.codigoEstadoCargoGenerado = codigoEstadoCargoGenerado;
	}

	public String getDetalleEstadoCargoGenerado() {
		return detalleEstadoCargoGenerado;
	}

	public void setDetalleEstadoCargoGenerado(String detalleEstadoCargoGenerado) {
		this.detalleEstadoCargoGenerado = detalleEstadoCargoGenerado;
	}

	public String getCodigoEstadoAcuerdoFacturacion() {
		return codigoEstadoAcuerdoFacturacion;
	}

	public void setCodigoEstadoAcuerdoFacturacion(
			String codigoEstadoAcuerdoFacturacion) {
		this.codigoEstadoAcuerdoFacturacion = codigoEstadoAcuerdoFacturacion;
	}

	public String getDescripcionEstadoAcuerdoFacturacion() {
		return descripcionEstadoAcuerdoFacturacion;
	}

	public void setDescripcionEstadoAcuerdoFacturacion(
			String descripcionEstadoAcuerdoFacturacion) {
		this.descripcionEstadoAcuerdoFacturacion = descripcionEstadoAcuerdoFacturacion;
	}

	public MicResultadoDto getResultadoDescobroFactura() {
		return resultadoDescobroFactura;
	}

	public void setResultadoDescobroFactura(
			MicResultadoDto resultadoDescobroFactura) {
		this.resultadoDescobroFactura = resultadoDescobroFactura;
	}
	
	public BigDecimal getInteresesRealesNoReguladosTrasladados() {
		return interesesRealesNoReguladosTrasladados;
	}

	public void setInteresesRealesNoReguladosTrasladados(
			BigDecimal interesesRealesNoReguladosTrasladados) {
		this.interesesRealesNoReguladosTrasladados = interesesRealesNoReguladosTrasladados;
	}

	public BigDecimal getInteresesRealesReguladosTrasladados() {
		return interesesRealesReguladosTrasladados;
	}

	public void setInteresesRealesReguladosTrasladados(
			BigDecimal interesesRealesReguladosTrasladados) {
		this.interesesRealesReguladosTrasladados = interesesRealesReguladosTrasladados;
	}
}
