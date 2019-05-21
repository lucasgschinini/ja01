package ar.com.telecom.shiva.base.jms.datos.salida.agrupador;

import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;


public class MicInformacionNotaCredito {

	private TipoComprobanteEnum			tipoComprobante;
	private TipoClaseComprobanteEnum 	claseComprobante;
	private String 						sucursalComprobante;
	private String 						numeroComprobante;
	private String					    numeroReferenciaMic;
	
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
	public String getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}
	public void setNumeroReferenciaMic(String numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
	}
	
}
