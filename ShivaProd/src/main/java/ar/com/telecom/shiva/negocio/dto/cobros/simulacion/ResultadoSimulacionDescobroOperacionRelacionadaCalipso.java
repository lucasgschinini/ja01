package ar.com.telecom.shiva.negocio.dto.cobros.simulacion;

import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;


public class ResultadoSimulacionDescobroOperacionRelacionadaCalipso extends ResultadoSimulacionDescobroOperacionRelacionada {

	private Long idCobranza;
	private TipoComprobanteEnum tipoComprobante;	
	private TipoClaseComprobanteEnum claseComprobante;	
	private String  sucursalComprobante;	
	private String  numeroComprobante;	
	private Long idDocumentoCuentasCobranza;
	
	public Long getIdCobranza() {
		return idCobranza;
	}
	public void setIdCobranza(Long idCobranza) {
		this.idCobranza = idCobranza;
	}
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
	public Long getIdDocumentoCuentasCobranza() {
		return idDocumentoCuentasCobranza;
	}
	public void setIdDocumentoCuentasCobranza(Long idDocumentoCuentasCobranza) {
		this.idDocumentoCuentasCobranza = idDocumentoCuentasCobranza;
	}
	
}