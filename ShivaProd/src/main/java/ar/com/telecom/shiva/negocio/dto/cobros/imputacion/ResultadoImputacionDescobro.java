package ar.com.telecom.shiva.negocio.dto.cobros.imputacion;

import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;


public class ResultadoImputacionDescobro extends ResultadoImputacion {

	private TipoInvocacionEnum tipoInvocacion;

	public TipoInvocacionEnum getTipoInvocacion() {
		return tipoInvocacion;
	}

	public void setTipoInvocacion(TipoInvocacionEnum tipoInvocacion) {
		this.tipoInvocacion = tipoInvocacion;
	}
}
