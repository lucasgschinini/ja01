package ar.com.telecom.shiva.base.jms.util.mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import ar.com.telecom.shiva.base.jms.util.JmsBase;

public class MQActiveBase extends JmsBase {

	private ActiveMQConnectionFactory connectionFactory;
	private ActiveMQQueue queueReceptor;
	private ActiveMQQueue queueEmisor;
	private MQAutenticacion autenticacion;
		
	/**
	 * Toma las propiedades para conectar a la cola situado en el servidor MQ
	 * correspondiente.
	 * @return connectionFactory
	 */
	public ActiveMQConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ActiveMQConnectionFactory qcf) {
		connectionFactory = qcf;
	}

	public ActiveMQQueue getQueueReceptor() {
		return queueReceptor;
	}

	public void setQueueReceptor(ActiveMQQueue queueReceptor) {
		this.queueReceptor = queueReceptor;
	}

	public ActiveMQQueue getQueueEmisor() {
		return queueEmisor;
	}

	public void setQueueEmisor(ActiveMQQueue queueEmisor) {
		this.queueEmisor = queueEmisor;
	}

	public MQAutenticacion getAutenticacion() {
		return autenticacion;
	}

	public void setAutenticacion(MQAutenticacion autenticacion) {
		this.autenticacion = autenticacion;
	}

}
