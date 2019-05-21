package ar.com.telecom.shiva.base.jms.util;

public class JmsBase {
	private boolean servidorMQconectada;
	private boolean queueManagerAbierta;
	
	public boolean isServidorMQconectada() {
		return servidorMQconectada;
	}
	public void setServidorMQconectada(boolean servidorMQconectada) {
		this.servidorMQconectada = servidorMQconectada;
	}
	public boolean isQueueManagerAbierta() {
		return queueManagerAbierta;
	}
	public void setQueueManagerAbierta(boolean queueManagerAbierta) {
		this.queueManagerAbierta = queueManagerAbierta;
	}
}
