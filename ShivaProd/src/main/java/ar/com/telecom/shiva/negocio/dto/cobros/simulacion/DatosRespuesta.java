/**
 * 
 */
package ar.com.telecom.shiva.negocio.dto.cobros.simulacion;

import java.math.BigDecimal;

/**
 * @author u564027
 *
 */
public class DatosRespuesta {

	private BigDecimal interesesGenerados;
	private BigDecimal interesesBonificados;

	private BigDecimal tipoDeCambioFechaEmision;
	private BigDecimal tipoDeCambioFechaCobro;
	private BigDecimal importeAplicadoAFechaEmisionMonedaPesos;
	private BigDecimal importeAplicadoAFechaEmisionMonedaOrigen;

	/**
	 * @return the interesesGenerados
	 */
	public BigDecimal getInteresesGenerados() {
		return interesesGenerados;
	}
	/**
	 * @param interesesGenerados the interesesGenerados to set
	 */
	public void setInteresesGenerados(BigDecimal interesesGenerados) {
		this.interesesGenerados = interesesGenerados;
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
}
