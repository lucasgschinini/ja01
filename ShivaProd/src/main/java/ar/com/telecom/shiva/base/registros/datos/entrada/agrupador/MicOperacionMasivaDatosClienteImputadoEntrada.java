package ar.com.telecom.shiva.base.registros.datos.entrada.agrupador;

import ar.com.telecom.shiva.base.dto.REG;

@SuppressWarnings("serial")
public class MicOperacionMasivaDatosClienteImputadoEntrada extends REG {
	Long codigoCliente;

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
}
