package ar.com.telecom.shiva.negocio.dto.cobros.imputacion;

public class ResultadoImputacionDescobroTratamientoDiferencia extends ResultadoImputacion {
	
	private Integer idTratamientoDiferencia;

	@Override
	public String getIdBusquedaRespuestaCobrador() {
		return this.idTratamientoDiferencia.toString();
	}

	/**
	 * @return the idTratamientoDiferencia
	 */
	public Integer getIdTratamientoDiferencia() {
		return idTratamientoDiferencia;
	}

	/**
	 * @param idTratamientoDiferencia the idTratamientoDiferencia to set
	 */
	public void setIdTratamientoDiferencia(Integer idTratamientoDiferencia) {
		this.idTratamientoDiferencia = idTratamientoDiferencia;
	}
}
