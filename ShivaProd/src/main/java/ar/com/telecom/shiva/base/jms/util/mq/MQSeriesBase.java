package ar.com.telecom.shiva.base.jms.util.mq;

import ar.com.telecom.shiva.base.jms.util.JmsBase;

import com.ibm.mq.jms.MQQueue;
import com.ibm.mq.jms.MQQueueConnectionFactory;

public class MQSeriesBase extends JmsBase {

	private MQQueueConnectionFactory connectionFactory;
	private MQQueue queueReceptor;
	private MQQueue queueEmisor;
	private MQAutenticacion autenticacion;
	
	/**
	 * Toma las propiedades para conectar a la cola situado en el servidor MQ
	 * correspondiente.
	 * @return connectionFactory
	 */
	public MQQueueConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(MQQueueConnectionFactory qcf) {
		connectionFactory = qcf;
	}

	public MQQueue getQueueReceptor() {
		return queueReceptor;
	}

	public void setQueueReceptor(MQQueue queueReceptor) {
		this.queueReceptor = queueReceptor;
	}

	public MQQueue getQueueEmisor() {
		return queueEmisor;
	}

	public void setQueueEmisor(MQQueue queueEmisor) {
		this.queueEmisor = queueEmisor;
	}

	public MQAutenticacion getAutenticacion() {
		return autenticacion;
	}

	public void setAutenticacion(MQAutenticacion autenticacion) {
		this.autenticacion = autenticacion;
	}

}
