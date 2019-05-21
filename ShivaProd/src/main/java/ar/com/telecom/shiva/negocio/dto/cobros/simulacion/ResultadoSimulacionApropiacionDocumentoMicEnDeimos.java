/**
 * 
 */
package ar.com.telecom.shiva.negocio.dto.cobros.simulacion;

import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;


/**
 * @author u564027
 *
 */
public class ResultadoSimulacionApropiacionDocumentoMicEnDeimos extends ResultadoSimulacion {

	private String numeroReferenciaMic;

	public String getIdBusquedaRespuestaCobrador() {
		String idBusqueraRespuestaCobrador = TipoMedioPagoEnum.NC.name() + numeroReferenciaMic;
		return idBusqueraRespuestaCobrador;
	}

	/**
	 * @return the numeroReferenciaMic
	 */
	public String getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}

	/**
	 * @param numeroReferenciaMic the numeroReferenciaMic to set
	 */
	public void setNumeroReferenciaMic(String numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
	}
}
