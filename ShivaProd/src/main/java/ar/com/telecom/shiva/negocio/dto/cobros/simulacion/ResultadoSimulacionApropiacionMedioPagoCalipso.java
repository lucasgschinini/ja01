package ar.com.telecom.shiva.negocio.dto.cobros.simulacion;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;

public class ResultadoSimulacionApropiacionMedioPagoCalipso extends ResultadoSimulacion {

	private TipoComprobanteEnum tipoComprobante;
	private TipoClaseComprobanteEnum claseComprobante;
	private String sucursalComprobante;
	private String numeroComprobante;
	
	private SiNoEnum migradoDeimos = SiNoEnum.NO;
	
	//Dolares
	protected BigDecimal tipoCambioFechaEmision;
	protected BigDecimal tipoCambioFechaCobro;
	protected BigDecimal importeAplicadoFechaEmisionPesos;
	protected BigDecimal importeAplicadoMonedaOrigen;
	protected MonedaEnum moneda;
	
	@Override
	public String getIdBusquedaRespuestaCobrador() {
		return tipoComprobante.name() + claseComprobante.name() + sucursalComprobante + numeroComprobante;		
	}

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

	public BigDecimal getTipoCambioFechaEmision() {
		return tipoCambioFechaEmision;
	}

	public void setTipoCambioFechaEmision(BigDecimal tipoCambioFechaEmision) {
		this.tipoCambioFechaEmision = tipoCambioFechaEmision;
	}

	public BigDecimal getTipoCambioFechaCobro() {
		return tipoCambioFechaCobro;
	}

	public void setTipoCambioFechaCobro(BigDecimal tipoCambioFechaCobro) {
		this.tipoCambioFechaCobro = tipoCambioFechaCobro;
	}

	public BigDecimal getImporteAplicadoFechaEmisionPesos() {
		return importeAplicadoFechaEmisionPesos;
	}

	public void setImporteAplicadoFechaEmisionPesos(
			BigDecimal importeAplicadoFechaEmisionPesos) {
		this.importeAplicadoFechaEmisionPesos = importeAplicadoFechaEmisionPesos;
	}

	public BigDecimal getImporteAplicadoMonedaOrigen() {
		return importeAplicadoMonedaOrigen;
	}

	public void setImporteAplicadoMonedaOrigen(
			BigDecimal importeAplicadoMonedaOrigen) {
		this.importeAplicadoMonedaOrigen = importeAplicadoMonedaOrigen;
	}

	public MonedaEnum getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}
	
}
