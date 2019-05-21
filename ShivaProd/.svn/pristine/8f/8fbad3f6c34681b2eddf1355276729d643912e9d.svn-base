/**
 * 
 */
package ar.com.telecom.shiva.negocio.dto.cobros.simulacion;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoCalipso;

/**
 * @author u564027
 *
 */
public class ResultadoSimulacionApropiacionFacturaCalipso extends ResultadoSimulacion {

	private TipoComprobanteEnum tipoComprobante;
	private TipoClaseComprobanteEnum claseComprobante;
	private String sucursalComprobante;
	private String numeroComprobante;
	
	private ShvCobFacturaCalipso notaDebitoPorDiferenciaCambio;
	private ShvCobMedioPagoNotaCreditoCalipso notaCreditoPorDiferenciaCambio;
	
	private BigDecimal interesesTrasladados;
	private BigDecimal interesesBonificados;

	private BigDecimal tipoDeCambioFechaEmision;
	private BigDecimal tipoDeCambioFechaCobro;
	private BigDecimal importeAplicadoAFechaEmisionMonedaPesos;
	private BigDecimal importeAplicadoAFechaEmisionMonedaOrigen;

	private SiNoEnum migradoDeimos = SiNoEnum.NO;
	private boolean requiereBuscarAcuerdoActivo = false;
	private boolean requiereHabilitaraSinDiferenciaCambio = false;

	/**
	 * @return the tipoComprobante
	 */
	public TipoComprobanteEnum getTipoComprobante() {
		return tipoComprobante;
	}
	/**
	 * @param tipoComprobante the tipoComprobante to set
	 */
	public void setTipoComprobante(TipoComprobanteEnum tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	/**
	 * @return the claseComprobante
	 */
	public TipoClaseComprobanteEnum getClaseComprobante() {
		return claseComprobante;
	}
	/**
	 * @param claseComprobante the claseComprobante to set
	 */
	public void setClaseComprobante(TipoClaseComprobanteEnum claseComprobante) {
		this.claseComprobante = claseComprobante;
	}
	/**
	 * @return the sucursalComprobante
	 */
	public String getSucursalComprobante() {
		return sucursalComprobante;
	}
	/**
	 * @param sucursalComprobante the sucursalComprobante to set
	 */
	public void setSucursalComprobante(String sucursalComprobante) {
		this.sucursalComprobante = sucursalComprobante;
	}
	/**
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}
	/**
	 * @param numeroComprobante the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	/**
	 * @return the notaDebitoPorDiferenciaCambio
	 */
	public ShvCobFacturaCalipso getNotaDebitoPorDiferenciaCambio() {
		return notaDebitoPorDiferenciaCambio;
	}
	/**
	 * @param notaDebitoPorDiferenciaCambio the notaDebitoPorDiferenciaCambio to set
	 */
	public void setNotaDebitoPorDiferenciaCambio(
			ShvCobFacturaCalipso notaDebitoPorDiferenciaCambio) {
		this.notaDebitoPorDiferenciaCambio = notaDebitoPorDiferenciaCambio;
	}
	/**
	 * @return the notaCreditoPorDiferenciaCambio
	 */
	public ShvCobMedioPagoNotaCreditoCalipso getNotaCreditoPorDiferenciaCambio() {
		return notaCreditoPorDiferenciaCambio;
	}
	/**
	 * @param notaCreditoPorDiferenciaCambio the notaCreditoPorDiferenciaCambio to set
	 */
	public void setNotaCreditoPorDiferenciaCambio(
			ShvCobMedioPagoNotaCreditoCalipso notaCreditoPorDiferenciaCambio) {
		this.notaCreditoPorDiferenciaCambio = notaCreditoPorDiferenciaCambio;
	}
	
	/**
	 * @return the interesesGenerados
	 */
	public BigDecimal getInteresesTrasladados() {
		return interesesTrasladados;
	}
	/**
	 * @param interesesGenerados the interesesGenerados to set
	 */
	public void setInteresesTrasladados(BigDecimal interesesGenerados) {
		this.interesesTrasladados = interesesGenerados;
	}
	/**
	 * @return the interesesBonificados
	 */
	public BigDecimal getInteresesBonificados() {
		return interesesBonificados;
	}
	/**
	 * @param interesesBonificados the interesesBonificados to set
	 */
	public void setInteresesBonificados(BigDecimal interesesBonificados) {
		this.interesesBonificados = interesesBonificados;
	}
	/**
	 * @return the tipoDeCambioFechaEmision
	 */
	public BigDecimal getTipoDeCambioFechaEmision() {
		return tipoDeCambioFechaEmision;
	}
	/**
	 * @param tipoDeCambioFechaEmision the tipoDeCambioFechaEmision to set
	 */
	public void setTipoDeCambioFechaEmision(BigDecimal tipoDeCambioFechaEmision) {
		this.tipoDeCambioFechaEmision = tipoDeCambioFechaEmision;
	}
	/**
	 * @return the tipoDeCambioFechaCobro
	 */
	public BigDecimal getTipoDeCambioFechaCobro() {
		return tipoDeCambioFechaCobro;
	}
	/**
	 * @param tipoDeCambioFechaCobro the tipoDeCambioFechaCobro to set
	 */
	public void setTipoDeCambioFechaCobro(BigDecimal tipoDeCambioFechaCobro) {
		this.tipoDeCambioFechaCobro = tipoDeCambioFechaCobro;
	}
	/**
	 * @return the importeAplicadoAFechaEmisionMonedaPesos
	 */
	public BigDecimal getImporteAplicadoAFechaEmisionMonedaPesos() {
		return importeAplicadoAFechaEmisionMonedaPesos;
	}
	/**
	 * @param importeAplicadoAFechaEmisionMonedaPesos the importeAplicadoAFechaEmisionMonedaPesos to set
	 */
	public void setImporteAplicadoAFechaEmisionMonedaPesos(
			BigDecimal importeAplicadoAFechaEmisionMonedaPesos) {
		this.importeAplicadoAFechaEmisionMonedaPesos = importeAplicadoAFechaEmisionMonedaPesos;
	}
	/**
	 * @return the importeAplicadoAFechaEmisionMonedaOrigen
	 */
	public BigDecimal getImporteAplicadoAFechaEmisionMonedaOrigen() {
		return importeAplicadoAFechaEmisionMonedaOrigen;
	}
	/**
	 * @param importeAplicadoAFechaEmisionMonedaOrigen the importeAplicadoAFechaEmisionMonedaOrigen to set
	 */
	public void setImporteAplicadoAFechaEmisionMonedaOrigen(
			BigDecimal importeAplicadoAFechaEmisionMonedaOrigen) {
		this.importeAplicadoAFechaEmisionMonedaOrigen = importeAplicadoAFechaEmisionMonedaOrigen;
	}
	/**
	 * @return the migradoDeimos
	 */
	public SiNoEnum getMigradoDeimos() {
		return migradoDeimos;
	}
	/**
	 * @param migradoDeimos the migradoDeimos to set
	 */
	public void setMigradoDeimos(SiNoEnum migradoDeimos) {
		this.migradoDeimos = migradoDeimos;
	}
	/**
	 * @param requiereBuscarAcuerdoActivo the requiereBuscarAcuerdoActivo to set
	 */
	public void setRequiereBuscarAcuerdoActivo(boolean requiereBuscarAcuerdoActivo) {
		this.requiereBuscarAcuerdoActivo = requiereBuscarAcuerdoActivo;
	}

	/**
	 * @return the requiereBuscarAcuerdoActivo
	 */
	public boolean isRequiereBuscarAcuerdoActivo() {
		return requiereBuscarAcuerdoActivo;
	}
	/**
	 * @return the requiereHabilitaraSinDiferenciaCambio
	 */
	public boolean isRequiereHabilitaraSinDiferenciaCambio() {
		return requiereHabilitaraSinDiferenciaCambio;
	}
	/**
	 * @param requiereHabilitaraSinDiferenciaCambio the requiereHabilitaraSinDiferenciaCambio to set
	 */
	public void setRequiereHabilitaraSinDiferenciaCambio(
			boolean requiereHabilitaraSinDiferenciaCambio) {
		this.requiereHabilitaraSinDiferenciaCambio = requiereHabilitaraSinDiferenciaCambio;
	}
}
