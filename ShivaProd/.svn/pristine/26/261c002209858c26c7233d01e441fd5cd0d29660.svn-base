package ar.com.telecom.shiva.base.jms.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQQueueReceiver;
import org.apache.activemq.ActiveMQQueueSender;
import org.apache.activemq.ActiveMQQueueSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;

import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.jms.IControlJms;
import ar.com.telecom.shiva.base.jms.util.JmsBase;
import ar.com.telecom.shiva.base.jms.util.JmsMensaje;
import ar.com.telecom.shiva.base.jms.util.JmsPropiedades;
import ar.com.telecom.shiva.base.jms.util.mq.MQActiveBase;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class MicControlMqActiveImpl implements IControlJms {

	@Autowired 
	private JmsTemplate micJmsEnvioTemplate;
	
	@Autowired 
	private JmsTemplate micJmsRecepcionTemplate;
	
	@Autowired 
	private MQActiveBase micMqServidor;
	
	@Autowired
	private JmsPropiedades micJmsPropiedades;
	
	private ActiveMQConnection qManager = null;
	private ActiveMQQueueSession session = null;
	private ActiveMQQueueReceiver colaReceptor= null;
	private ActiveMQQueueSender colaEmisor=null;
	
	/**
	 * Constructor
	 */
	public MicControlMqActiveImpl() {
	}
	
	
	/****************************************************************************************
	 * Metodos con jmsTemplate
	 ***************************************************************************************/
	@Override
	public void enviarMensaje(String contenido, String contrato) throws JmsExcepcion {
		try {
			micJmsEnvioTemplate.setDefaultDestination(micMqServidor.getQueueEmisor());
			this.micJmsPropiedades.setContrato(contrato);
			micJmsEnvioTemplate.send(new JmsMensaje(new StringBuffer(contenido), this.micJmsPropiedades));
			
			Traza.transaccionMQ(getClass(), "Salida:\n"+contenido);
			
		} catch (JmsException e) {
			Traza.error(getClass(),Mensajes.MQ_ERROR_ENVIAR_MENSAJE, e);
			throw new JmsExcepcion(Mensajes.MQ_ERROR_ENVIAR_MENSAJE, e);
		}
	}

	@Override
	public void enviarMensajeRecepcion(String contenido, String contrato) throws JmsExcepcion {
		try {
			micJmsEnvioTemplate.setDefaultDestination(micMqServidor.getQueueReceptor());
			this.micJmsPropiedades.setContrato(contrato);
			micJmsEnvioTemplate.send(new JmsMensaje(new StringBuffer(contenido), this.micJmsPropiedades));
			
			Traza.transaccionMQ(getClass(), "Salida al receptor:\n"+contenido);
		} catch (JmsException e) {
			Traza.error(getClass(),Mensajes.MQ_ERROR_ENVIAR_MENSAJE, e);
			throw new JmsExcepcion(Mensajes.MQ_ERROR_ENVIAR_MENSAJE, e);
		}
	}
	
	@Override
	public Message recibirMensaje() throws JmsExcepcion {
		try {
			
			micJmsRecepcionTemplate.setReceiveTimeout(1000);
			Message message = micJmsRecepcionTemplate.receive();
			return message;
			
		} catch (JmsException e) {
			Traza.error(getClass(),Mensajes.MQ_ERROR_RECIBIR_MENSAJE, e);
			throw new JmsExcepcion(Mensajes.MQ_ERROR_RECIBIR_MENSAJE, e);
		}
	}
	
	
	@Override
	public JmsPropiedades getJmsPropiedades() {
		return micJmsPropiedades;
	}
	
	/****************************************************************************************
	 * Metodos con libreria MQ
	 ***************************************************************************************/
	@Override
	public JmsBase verificarPropiedadesJms() throws JmsExcepcion {
		return getMicMqServidor();
	}
	
	@Override
	public JmsBase verificarConexion() throws JmsExcepcion {
		
		try {
			conectarJms();
			
			if (!Validaciones.isObjectNull(qManager)) {
				micMqServidor.setServidorMQconectada(qManager.isStarted());
				micMqServidor.setQueueManagerAbierta(true);
				return micMqServidor;
			}
			
			return null;
			
		} catch (JMSException e) {
			Traza.error(getClass(),Mensajes.MQ_ERROR_VERIFICAR_CONEXION, e);
			throw new JmsExcepcion(Mensajes.MQ_ERROR_VERIFICAR_CONEXION, e);
			
		} finally {
			try {
				cerrarConexionJms();
			} catch (Exception e) {
				Traza.error(getClass(),Mensajes.MQ_ERROR_CERRAR_CONEXION, e);
				throw new JmsExcepcion(Mensajes.MQ_ERROR_CERRAR_CONEXION, e);
			}
		}
	}
	
	/**
	 * Tomo la configuracion de la clase micMqServidor desde el bean
	 * @param bean
	 * @throws JMSException
	 */
	private void configurarDatosJms() throws JMSException {
		if (micMqServidor == null) {
			Traza.error(getClass(), Mensajes.MQ_ERROR_CONFIGURACION_SERVIDOR);
			throw new JMSException(Mensajes.MQ_ERROR_CONFIGURACION_SERVIDOR);
		} 
	}

	/**
	 * Obtener la session para realizar una transaccion de un mensaje MQ
	 * @return
	 * @throws javax.jms.JMSException
	 */
	private ActiveMQQueueSession getQueueSession() throws javax.jms.JMSException {
		boolean transacted = false;
		this.session = (ActiveMQQueueSession) 
					qManager.createQueueSession(transacted, Session.AUTO_ACKNOWLEDGE);
		return this.session;
	}
	
	/**
	 * Creo la cola receptor
	 * @throws JMSException
	 */
	private void crearColaReceptor() throws JMSException {
		if (micMqServidor.getQueueReceptor() != null) {
			setColaReceptor((ActiveMQQueueReceiver) getQueueSession().createReceiver(
				micMqServidor.getQueueReceptor()));
		} else {
			throw new JMSException(Mensajes.MQ_ERROR_CONFIGURACION_COLA_RECEPTOR);
		}
	}

	/**
	 * Creo la cola emisor
	 * @throws JMSException
	 */
	private void crearColaEmisor() throws JMSException {
		if (micMqServidor.getQueueEmisor() != null) {
			setColaEmisor((ActiveMQQueueSender) getQueueSession().createSender(
				micMqServidor.getQueueEmisor()));
		} else {
			throw new JMSException(Mensajes.MQ_ERROR_CONFIGURACION_COLA_EMISOR);
		}
	}

	/**
	 * Realizo la conexion
	 * @param bean
	 * @throws JMSException
	 */
	private void conectarJms() throws JMSException {
		try {
			Traza.auditoria_MQ(getClass(), Mensajes.MQ_REALIZANDO_CONEXION);
			
			this.configurarDatosJms();
			
			// Crear la conexion a MQManager
			qManager = (ActiveMQConnection) micMqServidor.getConnectionFactory()
					.createQueueConnection();
			
			qManager.start();
			
			//Creo las colas
			this.crearColaEmisor();
			this.crearColaReceptor();
			
			Traza.auditoria_MQ(getClass(), Mensajes.MQ_CONECTADO);
		
		} catch (Exception e) {
			Traza.error(getClass(), "Error al conectar al MQ", e);
			throw new JMSException(e.getMessage());
		}
	}

	/**
	 * Cierro la conexion
	 * @throws JMSException
	 */
	private void cerrarConexionJms() throws JMSException {
		if (qManager != null) {
			try {
				qManager.close();
				
				Traza.auditoria_MQ(getClass(), Mensajes.MQ_DESCONECTADO);
			} catch (Exception e) {
				throw new JMSException(
						"Error al Desconectar a la Cola.");
			}
		} else {
			Traza.auditoria_MQ(getClass(), Mensajes.MQ_DESCONECTADO_VACIO);
		}
	}

	/************************************************************************
	 * Getters/Setters
	 ***********************************************************************/

	public MQActiveBase getMicMqServidor() {
		return micMqServidor;
	}

	public void setMicMqServidor(MQActiveBase mqServidor) {
		this.micMqServidor = mqServidor;
	}

	public JmsTemplate getMicJmsEnvioTemplate() {
		return micJmsEnvioTemplate;
	}


	public void setMicJmsEnvioTemplate(JmsTemplate micJmsEnvioTemplate) {
		this.micJmsEnvioTemplate = micJmsEnvioTemplate;
	}


	public JmsTemplate getMicJmsRecepcionTemplate() {
		return micJmsRecepcionTemplate;
	}


	public void setMicJmsRecepcionTemplate(JmsTemplate micJmsRecepcionTemplate) {
		this.micJmsRecepcionTemplate = micJmsRecepcionTemplate;
	}


	public ActiveMQQueueReceiver getColaReceptor() {
		return colaReceptor;
	}


	public void setColaReceptor(ActiveMQQueueReceiver colaReceptor) {
		this.colaReceptor = colaReceptor;
	}	
	
	public ActiveMQQueueSender getColaEmisor() {
		return colaEmisor;
	}


	public void setColaEmisor(ActiveMQQueueSender colaEmisor) {
		this.colaEmisor = colaEmisor;
	}
	
	public JmsPropiedades getMicJmsPropiedades() {
		return micJmsPropiedades;
	}


	public void setMicJmsPropiedades(JmsPropiedades micJmsPropiedades) {
		this.micJmsPropiedades = micJmsPropiedades;
	}


	@Override
	public String consultar(String contenido, String contrato)
			throws JmsExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

}
