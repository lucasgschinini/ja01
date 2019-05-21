package ar.com.telecom.shiva.base.registros.datos.entrada.agrupador;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.dto.REG;

@SuppressWarnings("serial")
public class MicOperacionMasivaDatosDebitoImputadoSaldoTercerosEntrada extends REG {
	private BigDecimal saldoTerceroFinanciableNOTransferible;
	private BigDecimal saldoTerceroFinanciableTransferible;
	private BigDecimal saldoTerceroNOFinanciableTransferible;

	/**
	 * @return the saldoTerceroFinanciableNOTransferible
	 */
	public BigDecimal getSaldoTerceroFinanciableNOTransferible() {
		return saldoTerceroFinanciableNOTransferible;
	}
	/**
	 * @param saldoTerceroFinanciableNOTransferible the saldoTerceroFinanciableNOTransferible to set
	 */
	public void setSaldoTerceroFinanciableNOTransferible(
			BigDecimal saldoTerceroFinanciableNOTransferible) {
		this.saldoTerceroFinanciableNOTransferible = saldoTerceroFinanciableNOTransferible;
	}
	/**
	 * @return the saldoTerceroFinanciableTransferible
	 */
	public BigDecimal getSaldoTerceroFinanciableTransferible() {
		return saldoTerceroFinanciableTransferible;
	}
	/**
	 * @param saldoTerceroFinanciableTransferible the saldoTerceroFinanciableTransferible to set
	 */
	public void setSaldoTerceroFinanciableTransferible(
			BigDecimal saldoTerceroFinanciableTransferible) {
		this.saldoTerceroFinanciableTransferible = saldoTerceroFinanciableTransferible;
	}
	/**
	 * @return the saldoTerceroNOFinanciableTransferible
	 */
	public BigDecimal getSaldoTerceroNOFinanciableTransferible() {
		return saldoTerceroNOFinanciableTransferible;
	}
	/**
	 * @param saldoTerceroNOFinanciableTransferible the saldoTerceroNOFinanciableTransferible to set
	 */
	public void setSaldoTerceroNOFinanciableTransferible(
			BigDecimal saldoTerceroNOFinanciableTransferible) {
		this.saldoTerceroNOFinanciableTransferible = saldoTerceroNOFinanciableTransferible;
	}
}
