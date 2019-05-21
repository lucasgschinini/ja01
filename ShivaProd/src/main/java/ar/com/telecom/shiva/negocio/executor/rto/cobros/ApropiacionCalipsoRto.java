package ar.com.telecom.shiva.negocio.executor.rto.cobros;

import java.util.List;

import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoApropiacionWS;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;

public class ApropiacionCalipsoRto {
	
	private EntradaCalipsoApropiacionWS entradaCalipsoApropiacionWS;
	private List<ShvCobMedioPago> listaMediosPagoAEnviar;
	private ShvCobFacturaCalipso factura;
	
	//Otras Variables utiles
	private ShvParamTipoMedioPago tipoMedioPagoNotaCredito;

	/*************************************************************
	 * Constructores
	 *************************************************************/
	public ApropiacionCalipsoRto() {
	}
	
	/*************************************************************
	 * Setters && getters
	 *************************************************************/
	public EntradaCalipsoApropiacionWS getEntradaCalipsoApropiacionWS() {
		return entradaCalipsoApropiacionWS;
	}
	public void setEntradaCalipsoApropiacionWS(
			EntradaCalipsoApropiacionWS entradaCalipsoApropiacionWS) {
		this.entradaCalipsoApropiacionWS = entradaCalipsoApropiacionWS;
	}
	public List<ShvCobMedioPago> getListaMediosPagoAEnviar() {
		return listaMediosPagoAEnviar;
	}
	public void setListaMediosPagoAEnviar(
			List<ShvCobMedioPago> listaMediosPagoAEnviar) {
		this.listaMediosPagoAEnviar = listaMediosPagoAEnviar;
	}
	public ShvCobFacturaCalipso getFactura() {
		return factura;
	}
	public void setFactura(ShvCobFacturaCalipso factura) {
		this.factura = factura;
	}
	public ShvParamTipoMedioPago getTipoMedioPagoNotaCredito() {
		return tipoMedioPagoNotaCredito;
	}
	public void setTipoMedioPagoNotaCredito(
			ShvParamTipoMedioPago tipoMedioPagoNotaCredito) {
		this.tipoMedioPagoNotaCredito = tipoMedioPagoNotaCredito;
	}
}
