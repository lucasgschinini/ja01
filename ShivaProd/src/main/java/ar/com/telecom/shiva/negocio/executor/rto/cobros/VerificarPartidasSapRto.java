package ar.com.telecom.shiva.negocio.executor.rto.cobros;

import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapVerificacionPartidasWS;

public class VerificarPartidasSapRto {
	
	private String operacionFormateado;
	private EntradaSapVerificacionPartidasWS entradaSapVerificacionPartidas;
	

	/**
	 * @return the entradaSapVerificacionPartidas
	 */
	public EntradaSapVerificacionPartidasWS getEntradaSapVerificacionPartidas() {
		return entradaSapVerificacionPartidas;
	}

	/**
	 * @param entradaSapVerificacionPartidas the entradaSapVerificacionPartidas to set
	 */
	public void setEntradaSapVerificacionPartidas(
			EntradaSapVerificacionPartidasWS entradaSapVerificacionPartidas) {
		this.entradaSapVerificacionPartidas = entradaSapVerificacionPartidas;
	}

	/**
	 * @return the operacionFormateado
	 */
	public String getOperacionFormateado() {
		return operacionFormateado;
	}

	/**
	 * @param operacionFormateado the operacionFormateado to set
	 */
	public void setOperacionFormateado(String operacionFormateado) {
		this.operacionFormateado = operacionFormateado;
	}
}
