package ar.com.telecom.shiva.negocio.dto.cobros.simulacion;

import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;



public class ResultadoSimulacionDescobroMedioPagoCalipso extends ResultadoSimulacionDescobroMedioPago {

	private TipoComprobanteEnum tipoComprobante;
	private TipoClaseComprobanteEnum claseComprobante;
	private String sucursalComprobante;
	private String numeroComprobante;
	private Long idDocumentoCuentaCobranza;

	public Long getIdDocumentoCuentaCobranza() {
		return idDocumentoCuentaCobranza;
	}

	public void setIdDocumentoCuentaCobranza(Long idDocumentoCuentaCobranza) {
		this.idDocumentoCuentaCobranza = idDocumentoCuentaCobranza;
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
	
	@Override
	public String getIdBusquedaRespuestaCobrador() {
		String idBusquedaRespuestaCobrador = "";
		
		if(!Validaciones.isObjectNull(tipoComprobante) && !Validaciones.isObjectNull(claseComprobante)){
			idBusquedaRespuestaCobrador = tipoComprobante.name() + claseComprobante.name() + sucursalComprobante + numeroComprobante;		
		}
		
		return idBusquedaRespuestaCobrador;
	}
	
}
