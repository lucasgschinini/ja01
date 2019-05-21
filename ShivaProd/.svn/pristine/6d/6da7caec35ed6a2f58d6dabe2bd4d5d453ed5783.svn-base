package ar.com.telecom.shiva.base.jms.datos.entrada.agrupador;

import java.math.BigInteger;
import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class MicInformacionFactura {

	protected TipoComprobanteEnum 		tipoComprobante;
	protected TipoClaseComprobanteEnum  claseComprobante;
	protected String 					sucursalComprobante;
	protected String					numeroComprobante;
	protected Date						fechaVencimientoDesde;
	protected Date						fechaVencimientoHasta;
	protected String				numeroReferenciaMIC;
	protected BigInteger				acuerdo;
	
	public TipoComprobanteEnum getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(TipoComprobanteEnum tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public TipoClaseComprobanteEnum getClaseComprobante() {
		return claseComprobante;
	}
	public void setClaseComprobante(TipoClaseComprobanteEnum claseComprobante) {
		this.claseComprobante = claseComprobante;
	}
	public String getSucursalComprobante() {
		return sucursalComprobante;
	}
	public void setSucursalComprobante(String sucursalComprobante) {
		this.sucursalComprobante = sucursalComprobante;
	}
	public String getNumeroComprobante() {
		return numeroComprobante;
	}
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	public Date getFechaVencimientoDesde() {
		return fechaVencimientoDesde;
	}
	public void setFechaVencimientoDesde(Date fechaVencimientoDesde) {
		this.fechaVencimientoDesde = fechaVencimientoDesde;
	}
	public Date getFechaVencimientoHasta() {
		return fechaVencimientoHasta;
	}
	public void setFechaVencimientoHasta(Date fechaVencimientoHasta) {
		this.fechaVencimientoHasta = fechaVencimientoHasta;
	}
	public String getNumeroReferenciaMIC() {
		return numeroReferenciaMIC;
	}
	public void setNumeroReferenciaMIC(String numeroReferenciaMIC) {
		this.numeroReferenciaMIC = numeroReferenciaMIC;
	}
	public String getNumeroReferenciaMICFormateado() {
		if (Validaciones.isNumeric(this.numeroReferenciaMIC)) {
			// Si es un valor numerico, quiere decir es un numero de referencia de una factura MIC,
			// por lo que debemos completar ceros a la izquierda para que el valor viaje como numero.
			return Utilidad.rellenarCerosIzquierda(this.numeroReferenciaMIC, 15);
		} else {
			// Para el caso de referencia DUC, AB-nnnn-nnnnnnnnnnn, lo dejamos como está
			return this.numeroReferenciaMIC;	
		}
	}
	
	public BigInteger getAcuerdo() {
		return acuerdo;
	}
	public void setAcuerdo(BigInteger acuerdo) {
		this.acuerdo = acuerdo;
	}
	
	
	
}
