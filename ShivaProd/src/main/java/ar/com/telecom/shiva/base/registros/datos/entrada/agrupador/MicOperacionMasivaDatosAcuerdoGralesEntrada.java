package ar.com.telecom.shiva.base.registros.datos.entrada.agrupador;

import ar.com.telecom.shiva.base.dto.REG;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;

@SuppressWarnings("serial")
public class MicOperacionMasivaDatosAcuerdoGralesEntrada extends REG {
	private Long codigoCliente;
	private Long acuerdoFacturacion;
	private Long linea;
	private EstadoAcuerdoFacturacionEnum estadoAcuerdoFacturacion;

	/**
	 * @return the codigoCliente
	 */
	public Long getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return the acuerdoFacturacion
	 */
	public Long getAcuerdoFacturacion() {
		return acuerdoFacturacion;
	}
	/**
	 * @param acuerdoFacturacion the acuerdoFacturacion to set
	 */
	public void setAcuerdoFacturacion(Long acuerdoFacturacion) {
		this.acuerdoFacturacion = acuerdoFacturacion;
	}
	/**
	 * @return the linea
	 */
	public Long getLinea() {
		return linea;
	}
	/**
	 * @param linea the linea to set
	 */
	public void setLinea(Long linea) {
		this.linea = linea;
	}
	/**
	 * @return the estadoAcuerdoFacturacion
	 */
	public EstadoAcuerdoFacturacionEnum getEstadoAcuerdoFacturacion() {
		return estadoAcuerdoFacturacion;
	}
	/**
	 * @param estadoAcuerdoFacturacion the estadoAcuerdoFacturacion to set
	 */
	public void setEstadoAcuerdoFacturacion(
			EstadoAcuerdoFacturacionEnum estadoAcuerdoFacturacion) {
		this.estadoAcuerdoFacturacion = estadoAcuerdoFacturacion;
	}
}
