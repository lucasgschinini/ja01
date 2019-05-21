/**
 * 
 */
package ar.com.telecom.shiva.negocio.dto.cobros.simulacion;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;

/**
 * @author u564027
 *
 */
public class ResultadoSimulacionApropiacionMedioPagoMic extends ResultadoSimulacion {

	private TipoMedioPagoEnum tipoMedioPago;
	private TipoRemanenteEnum tipoRemanente;
	private Long cuentaRemanente;
	private Long numeroNC;

	private SiNoEnum migradoDeimos = SiNoEnum.NO;

	@Override
	public String getIdBusquedaRespuestaCobrador() {
		
		String idBusqueraRespuestaCobrador = "";
		
		if (TipoMedioPagoEnum.NC.equals(tipoMedioPago)) {
			idBusqueraRespuestaCobrador = tipoMedioPago.name() + numeroNC;
		} else {
			idBusqueraRespuestaCobrador = tipoMedioPago.name() + tipoRemanente.name() + cuentaRemanente;
		}
		return idBusqueraRespuestaCobrador;
	}
	
	/**
	 * @return the tipoMedioPago
	 */
	public TipoMedioPagoEnum getTipoMedioPago() {
		return tipoMedioPago;
	}
	/**
	 * @param tipoMedioPago the tipoMedioPago to set
	 */
	public void setTipoMedioPago(TipoMedioPagoEnum tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}
	/**
	 * @return the cuentaRemanente
	 */
	public Long getCuentaRemanente() {
		return cuentaRemanente;
	}
	/**
	 * @param cuentaRemanente the cuentaRemanente to set
	 */
	public void setCuentaRemanente(Long cuentaRemanente) {
		this.cuentaRemanente = cuentaRemanente;
	}
	/**
	 * @return the tipoRemanente
	 */
	public TipoRemanenteEnum getTipoRemanente() {
		return tipoRemanente;
	}
	/**
	 * @param tipoRemanente the tipoRemanente to set
	 */
	public void setTipoRemanente(TipoRemanenteEnum tipoRemanente) {
		this.tipoRemanente = tipoRemanente;
	}
	/**
	 * @return the numeroNC
	 */
	public Long getNumeroNC() {
		return numeroNC;
	}
	/**
	 * @param numeroNC the numeroNC to set
	 */
	public void setNumeroNC(Long numeroNC) {
		this.numeroNC = numeroNC;
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
}
