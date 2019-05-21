package ar.com.telecom.shiva.base.registros.datos.entrada.agrupador;

import ar.com.telecom.shiva.base.dto.REG;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;

@SuppressWarnings("serial")
public class MicOperacionMasivaDatosCreditoAplicadoRemanenteEntrada extends REG {
	private TipoRemanenteEnum tipoRemanente;

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
}
