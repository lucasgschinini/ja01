package ar.com.telecom.shiva.negocio.executor.rto.cobros;

import javax.jms.Message;

import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.jms.IControlJms;

public class ImputacionRecepcionRto {
	
	private int count;
	private Message mensajeRecibido;
	private TipoProcesoEnum tipoProceso;
	private IControlJms micControlMQ;
	
	public ImputacionRecepcionRto(int count, Message mensajeRecibido, TipoProcesoEnum tipoProceso, IControlJms micControlMQ) {
		this.count = count;
		this.mensajeRecibido = mensajeRecibido;
		this.tipoProceso = tipoProceso;
		this.micControlMQ = micControlMQ;
	}

	public int getCount() {
		return count;
	}

	public Message getMensajeRecibido() {
		return mensajeRecibido;
	}

	public TipoProcesoEnum getTipoProceso() {
		return tipoProceso;
	}

	public IControlJms getMicControlMQ() {
		return micControlMQ;
	}
}
