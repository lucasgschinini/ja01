package ar.com.telecom.shiva.negocio.executor.rto.cobros;

import java.util.List;

import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCDto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;

public class ApropiacionMicRto {
	
	private MicTransaccionADCDto entradaMicApropiacionJms;
	private List<ShvCobMedioPago> listaMediosPagoAEnviar;
	private ShvCobFacturaMic factura;
	
	

	/*************************************************************
	 * Constructores
	 *************************************************************/
	public ApropiacionMicRto() {
	}
	
	/*************************************************************
	 * Setters && getters
	 *************************************************************/

	public List<ShvCobMedioPago> getListaMediosPagoAEnviar() {
		return listaMediosPagoAEnviar;
	}
	public void setListaMediosPagoAEnviar(
			List<ShvCobMedioPago> listaMediosPagoAEnviar) {
		this.listaMediosPagoAEnviar = listaMediosPagoAEnviar;
	}
	public ShvCobFacturaMic getFactura() {
		return factura;
	}
	public void setFactura(ShvCobFacturaMic factura) {
		this.factura = factura;
	}
	public MicTransaccionADCDto getEntradaMicApropiacionJms() {
		return entradaMicApropiacionJms;
	}

	public void setEntradaMicApropiacionJms(
			MicTransaccionADCDto entradaMicApropiacionJms) {
		this.entradaMicApropiacionJms = entradaMicApropiacionJms;
	}
}
