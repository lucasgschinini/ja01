package ar.com.telecom.shiva.base.ws.servidor.servicios;

import ar.com.telecom.shiva.base.mapeadores.MapeadorWS;

public abstract class WsServicio {
	
	protected MapeadorWS mapeadorWs;

	public MapeadorWS getMapeadorWs() {
		return mapeadorWs;
	}

	public void setMapeadorWs(MapeadorWS mapeadorWs) {
		this.mapeadorWs = mapeadorWs;
	}

	
}
