package ar.com.telecom.shiva.base.jms.datos.salida.agrupador;

import java.math.BigDecimal;

public class MicInformacionAdicionalSaldosTerceros {

	protected BigDecimal saldoTerceroFinanciableNOTransferible;
	protected BigDecimal saldoTerceroFinanciableTransferible;
	protected BigDecimal saldoTerceroNOFinanciableTransferible;
	
	public BigDecimal getSaldoTerceroFinanciableNOTransferible() {
		return saldoTerceroFinanciableNOTransferible;
	}
	public void setSaldoTerceroFinanciableNOTransferible(
			BigDecimal saldoTerceroFinanciableNOTransferible) {
		this.saldoTerceroFinanciableNOTransferible = saldoTerceroFinanciableNOTransferible;
	}
	public BigDecimal getSaldoTerceroFinanciableTransferible() {
		return saldoTerceroFinanciableTransferible;
	}
	public void setSaldoTerceroFinanciableTransferible(
			BigDecimal saldoTerceroFinanciableTransferible) {
		this.saldoTerceroFinanciableTransferible = saldoTerceroFinanciableTransferible;
	}
	public BigDecimal getSaldoTerceroNOFinanciableTransferible() {
		return saldoTerceroNOFinanciableTransferible;
	}
	public void setSaldoTerceroNOFinanciableTransferible(
			BigDecimal saldoTerceroNOFinanciableTransferible) {
		this.saldoTerceroNOFinanciableTransferible = saldoTerceroNOFinanciableTransferible;
	}

}
