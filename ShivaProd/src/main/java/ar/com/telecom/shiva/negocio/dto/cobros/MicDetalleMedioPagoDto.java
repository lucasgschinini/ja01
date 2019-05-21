package ar.com.telecom.shiva.negocio.dto.cobros;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;

@SuppressWarnings("serial")
public class MicDetalleMedioPagoDto extends JMS {

	private TipoMedioPagoEnum tipoMedioPago;
	private BigDecimal importeMedioPago;
	private Long cuentaRemanente;
	private TipoRemanenteEnum tipoRemanente;
	private Long numeroNC;
	private BigDecimal montoAcumuladoSimulacion;
	
	public TipoMedioPagoEnum getTipoMedioPago() {
		return tipoMedioPago;
	}
	public void setTipoMedioPago(TipoMedioPagoEnum tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}
	public BigDecimal getImporteMedioPago() {
		return importeMedioPago;
	}
	public void setImporteMedioPago(BigDecimal importeMedioPago) {
		this.importeMedioPago = importeMedioPago;
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
	public Long getNumeroNC() {
		return numeroNC;
	}
	public void setNumeroNC(Long numeroNC) {
		this.numeroNC = numeroNC;
	}
	/**
	 * @return the montoAcumuladoSimulacion
	 */
	public BigDecimal getMontoAcumuladoSimulacion() {
		return montoAcumuladoSimulacion;
	}
	/**
	 * @param montoAcumuladoSimulacion the montoAcumuladoSimulacion to set
	 */
	public void setMontoAcumuladoSimulacion(BigDecimal montoAcumuladoSimulacion) {
		this.montoAcumuladoSimulacion = montoAcumuladoSimulacion;
	}
}
