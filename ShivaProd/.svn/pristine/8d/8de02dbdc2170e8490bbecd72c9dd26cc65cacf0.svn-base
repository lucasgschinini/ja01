package ar.com.telecom.shiva.negocio.dto.cobros.imputacion;

import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;


public class ResultadoImputacionDescobroMedioPagoMic extends ResultadoImputacionDescobroMedioPago {

	private TipoMedioPagoEnum tipoMedioPago;
	private Long cuentaRemanente;
	private TipoRemanenteEnum tipoRemanente;
	private Long numeroReferenciaMic;

	//Campo auxiliar para identificar el medio pago cuando se actualiza la base de datos
	private String numeroNotaCredito;
	private String idBusqueraRespuestaCobrador;
	
	@Override
	public String getIdBusquedaRespuestaCobrador() {
//		String idBusqueraRespuestaCobrador = TipoMedioPagoEnum.NC.name() + this.numeroNotaCredito;
		return idBusqueraRespuestaCobrador;
	}
	
	public TipoMedioPagoEnum getTipoMedioPago() {
		return tipoMedioPago;
	}
	public void setTipoMedioPago(TipoMedioPagoEnum tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}
	public Long getCuentaRemanente() {
		return cuentaRemanente;
	}
	public void setCuentaRemanente(Long cuentaRemanente) {
		this.cuentaRemanente = cuentaRemanente;
	}
	public TipoRemanenteEnum getTipoRemanente() {
		return tipoRemanente;
	}
	public void setTipoRemanente(TipoRemanenteEnum tipoRemanente) {
		this.tipoRemanente = tipoRemanente;
	}
	public Long getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}
	public void setNumeroReferenciaMic(Long numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
	}
	public String getNumeroNotaCredito() {
		return numeroNotaCredito;
	}
	public void setNumeroNotaCredito(String numeroNotaCredito) {
		this.numeroNotaCredito = numeroNotaCredito;
	}
	public void setIdBusqueraRespuestaCobrador(String idBusqueraRespuestaCobrador) {
		this.idBusqueraRespuestaCobrador = idBusqueraRespuestaCobrador;
	}
}
