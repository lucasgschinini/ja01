package ar.com.telecom.shiva.base.jms.util;

import java.util.Date;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;

import com.ibm.msg.client.jms.JmsConstants;

public class JmsMensaje implements MessageCreator {

	private Message message = null;
	private Destination replyToQueue = null;
	
	private StringBuffer contenido;
	private String correlationId;
	private JmsPropiedades propiedades; 
	
	public JmsMensaje (StringBuffer contenido, JmsPropiedades propiedades) {
		this.contenido = contenido;
		this.propiedades = propiedades;  
		this.correlationId = null;
		this.replyToQueue = null;
	}
	
	public JmsMensaje (StringBuffer contenido, JmsPropiedades propiedades, Destination replyToQueue) {
		this.contenido = contenido;
		this.propiedades = propiedades;  
		this.correlationId = null;
		this.replyToQueue = replyToQueue;
	}
	
	public JmsMensaje (StringBuffer contenido, JmsPropiedades propiedades, String correlationId) {
		this.contenido = contenido;
		this.propiedades = propiedades; 
		this.correlationId = correlationId;
		this.replyToQueue = null;
	}
	
	@Override
	public Message createMessage(Session session) throws JMSException {
		String mensaje = this.contenido.toString();
		message = session.createTextMessage(mensaje);
		
		if (!Validaciones.isNullOrEmpty(propiedades.getContrato())) {
			message.setStringProperty(JmsConstants.JMS_IBM_MQMD_APPLIDENTITYDATA, 
					Utilidad.rellenarEspaciosDerecha(propiedades.getContrato(), 30));
		}
		message.setStringProperty(JmsConstants.JMS_IBM_MQMD_PUTAPPLNAME, 
				Utilidad.rellenarEspaciosDerecha(propiedades.getAplicacion(), 30));
		
		message.setStringProperty(JmsConstants.JMS_IBM_MQMD_FORMAT, propiedades.getFormatoMensaje());
		message.setIntProperty(JmsConstants.JMS_IBM_MQMD_PUTAPPLTYPE, propiedades.getPutApplType());
		message.setIntProperty(JmsConstants.JMS_IBM_MQMD_PERSISTENCE, propiedades.getPersistence());
		
		Date fecha = new Date();
		message.setStringProperty(JmsConstants.JMS_IBM_MQMD_PUTDATE, Utilidad.formatDateAAAAMMDD(fecha)); 
		message.setStringProperty(JmsConstants.JMS_IBM_MQMD_PUTTIME, Utilidad.formatDateHHMMSSTH(fecha));
		
		message.setJMSCorrelationID(correlationId);
		if (replyToQueue != null) {
			message.setJMSReplyTo(replyToQueue);
		}
		
		Traza.transaccionMQ(getClass(), "Salida - Propiedades del Mensaje MQ: "+message.toString());
		
		return message;
	}
	
	/**
	 * Me devuelve el id del mensaje
	 * @return
	 * @throws JMSException
	 */
	public String getJMSMessageId() throws JMSException {
		if (message != null) {
			return message.getJMSMessageID();
		}
		return null;
	}
	
	/**
	 * Getters & Setters
	 */

	public StringBuffer getContenido() {
		return contenido;
	}

	public String getCorrelationId() {
		return correlationId;
	}
	
	
}
