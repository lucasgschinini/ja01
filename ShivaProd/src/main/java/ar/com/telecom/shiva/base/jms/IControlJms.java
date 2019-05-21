package ar.com.telecom.shiva.base.jms;

import javax.jms.Message;

import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.jms.util.JmsPropiedades;

public interface IControlJms {
	
	/**
	 * Enviar Mensaje usando jmsTemplate de spring
	 */
	public void enviarMensaje(String contenido, String contrato) 
			throws JmsExcepcion;
	
	/**
	 * Realizo una consulta de un mensaje usando jmsTemplate de Spring
	 * @return
	 * @throws JmsExcepcion
	 */
	public String consultar(String contenido, String contrato) throws JmsExcepcion;
	
	
	/**
	 * Enviar Mensaje a la Recepcion usando jmsTemplate de spring
	 */
	public Message recibirMensaje() throws JmsExcepcion;
	
	/**
	 * Obtengo el objeto JmsPropiedades
	 * @return
	 */
	public JmsPropiedades getJmsPropiedadesContexto();
	
}
