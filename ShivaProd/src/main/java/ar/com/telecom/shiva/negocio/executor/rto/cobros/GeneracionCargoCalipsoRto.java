package ar.com.telecom.shiva.negocio.executor.rto.cobros;

import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCargosWS;

public class GeneracionCargoCalipsoRto {
	
	private EntradaCalipsoCargosWS entradaCalipsoGeneracionCargosWs;
	private String operacionTransaccionFormateado;
	private boolean tipoDebito;
	
	public GeneracionCargoCalipsoRto() {
	}

	public String getOperacionTransaccionFormateado() {
		return operacionTransaccionFormateado;
	}

	public void setOperacionTransaccionFormateado(
			String operacionTransaccionFormateado) {
		this.operacionTransaccionFormateado = operacionTransaccionFormateado;
	}

	public EntradaCalipsoCargosWS getEntradaCalipsoGeneracionCargosWs() {
		return entradaCalipsoGeneracionCargosWs;
	}

	public void setEntradaCalipsoGeneracionCargosWs(
			EntradaCalipsoCargosWS entradaCalipsoGeneracionCargosWs) {
		this.entradaCalipsoGeneracionCargosWs = entradaCalipsoGeneracionCargosWs;
	}

	public boolean isTipoDebito() {
		return tipoDebito;
	}

	public void setTipoDebito(boolean tipoDebito) {
		this.tipoDebito = tipoDebito;
	}
}
