package ar.com.telecom.shiva.base.jms;

import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.jms.util.JmsBase;
import ar.com.telecom.shiva.base.jms.util.JmsPropiedades;

public interface ITesterJms {
	
	/**
	 * Para testear enviando mensaje a la Recepcion usando jmsTemplate de spring
	 */
	public void enviarMensajeRecepcion(String contenido, String contrato) 
			throws JmsExcepcion;
	
	
	/****************************************************************************************
	 * Metodos con libreria MQ
	 ***************************************************************************************/
	/**
	 * Verifico que si hay conexion al MQ
	 * @param bean
	 * @return
	 * @throws JmsExcepcion
	 */
	public JmsBase verificarConexion() 
			throws JmsExcepcion;
	
	/**
	 * Verifico las propiedades del mensaje
	 * @param bean
	 * @return
	 * @throws JmsExcepcion
	 */
	public JmsBase verificarPropiedadesJms() 
			throws JmsExcepcion;
	
	
	/**
	 * Obtengo el objeto JmsPropiedades
	 * @return
	 */
	public JmsPropiedades getJmsPropiedadesContexto();
	
}
