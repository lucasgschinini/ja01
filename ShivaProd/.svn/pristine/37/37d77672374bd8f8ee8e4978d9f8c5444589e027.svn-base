package ar.com.telecom.shiva.base.jms.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;


@Service
@Scope("prototype")
public class JmsMessageSender {

	@Autowired JmsTemplate jmsTemplate;
	
	public void enviarMensaje(String mensaje, String contrato, JmsPropiedades propiedades) throws JmsExcepcion {
		try {
			propiedades.setContrato(contrato);
			jmsTemplate.send(new JmsMensaje(new StringBuffer(mensaje), propiedades));
			
		} catch (JmsException e) {
			throw new JmsExcepcion(e.getMessage(), e);
		}
	}
}
