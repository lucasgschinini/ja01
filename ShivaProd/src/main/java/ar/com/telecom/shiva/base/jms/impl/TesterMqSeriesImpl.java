package ar.com.telecom.shiva.base.jms.impl;

import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;

import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.jms.ITesterJms;
import ar.com.telecom.shiva.base.jms.util.JmsBase;
import ar.com.telecom.shiva.base.jms.util.JmsMensaje;
import ar.com.telecom.shiva.base.jms.util.JmsPropiedades;
import ar.com.telecom.shiva.base.jms.util.mq.MQSeriesBase;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;
import com.ibm.msg.client.wmq.WMQConstants;

public class TesterMqSeriesImpl implements ITesterJms {

	private JmsTemplate jmsTemplate;
	
	private MQSeriesBase mqServidor;
	
	private JmsPropiedades jmsPropiedades;
	
	private MQQueueManager qManager = null;
	
	@SuppressWarnings("unused")
	private MQQueue colaEmisor = null;
	@SuppressWarnings("unused")
	private MQQueue colaReceptor = null;
	
	MQGetMessageOptions gmo;
	MQPutMessageOptions pmo;
	
	private String queueNameReenvio = null;
	private String queueManagerNameReenvio = null;
	
	
	/**
	 * Constructor
	 */
	public TesterMqSeriesImpl() {
	}
	
	@Override
	public void enviarMensajeRecepcion(String contenido, String contrato) throws JmsExcepcion {
		try {
			Traza.transaccionMQ(getClass(), "Pre-Salida Recepcion: "+contenido);
			
			com.ibm.mq.jms.MQQueue mqDest = mqServidor.getQueueReceptor();
			mqDest.setMQMDWriteEnabled(true);
			mqDest.setMQMDReadEnabled(true);
			mqDest.setMQMDMessageContext(WMQConstants.WMQ_MDCTX_SET_ALL_CONTEXT);
			
			jmsTemplate.setDefaultDestination(mqDest);
			this.jmsPropiedades.setContrato(contrato);
			jmsTemplate.send(new JmsMensaje(new StringBuffer(contenido), this.jmsPropiedades));
			
			Traza.transaccionMQ(getClass(), "Salida Exitosa al receptor:\n"+contenido);
		} catch (JmsException | JMSException e) {
			Traza.error(getClass(),Mensajes.MQ_ERROR_ENVIAR_MENSAJE, e);
			throw new JmsExcepcion(Mensajes.MQ_ERROR_ENVIAR_MENSAJE, e);
		}
	}

	/**
	 * Probarlo
	 * @param queueName
	 * @return
	 */
	protected int getMessagesInQueue(String queueName) {
        return this.jmsTemplate.browse(queueName, new BrowserCallback<Integer>() {
            @Override
            public Integer doInJms(Session session, QueueBrowser browser) throws JMSException {
                Enumeration<?> messages = browser.getEnumeration();
                int total = 0;
                while (messages.hasMoreElements()) {
                    messages.nextElement();
                    total++;
                }
                
                return total;
            }
        });
    }
	
	@Override
	public JmsPropiedades getJmsPropiedadesContexto() {
		return this.jmsPropiedades;
	}
	/****************************************************************************************
	 * Metodos con libreria MQ
	 ***************************************************************************************/
	@Override
	public JmsBase verificarPropiedadesJms() throws JmsExcepcion {
		return getMqServidor();
	}

	@Override
	public JmsBase verificarConexion() throws JmsExcepcion {
		
		try {
			conectarJms();
			
			if (!Validaciones.isObjectNull(qManager)) {
				mqServidor.setServidorMQconectada(qManager.isConnected());
				mqServidor.setQueueManagerAbierta(qManager.isOpen());
				
				return mqServidor;
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
	 * Tomo la configuracion de la clase mqServidor desde el bean
	 * @param bean
	 * @throws JMSException
	 */
	private void configurarDatosJms() throws JMSException {
		if (mqServidor == null) {
			Traza.error(getClass(), Mensajes.MQ_ERROR_CONFIGURACION_SERVIDOR);
			throw new JMSException(Mensajes.MQ_ERROR_CONFIGURACION_SERVIDOR);
		} 
	}

	/**
	 * Creo la cola receptor
	 * @throws JMSException
	 */
	private void crearColaReceptor() throws JMSException {
		if (mqServidor.getQueueReceptor() != null) {
			try {
				colaReceptor = qManager.accessQueue(
					mqServidor.getQueueReceptor().getBaseQueueName(), MQConstants.MQOO_INPUT_SHARED);
				
			} catch (MQException e) {
				throw new JMSException(e.getMessage());
			}
			
		} else {
			throw new JMSException(Mensajes.MQ_ERROR_CONFIGURACION_COLA_RECEPTOR);
		}
	}

	/**
	 * Creo la cola emisor
	 * @throws JMSException
	 */
	private void crearColaEmisor() throws JMSException {
		if (mqServidor.getQueueEmisor() != null) {
			try {
				colaEmisor = qManager.accessQueue(
						mqServidor.getQueueReceptor().getBaseQueueName(), 
							MQConstants.MQOO_OUTPUT | MQConstants.MQOO_INQUIRE);
			} catch (MQException e) {
				throw new JMSException(e.getMessage());
			}
		} else {
			throw new JMSException(Mensajes.MQ_ERROR_CONFIGURACION_COLA_EMISOR);
		}
	}

	/**
	 * Realizo la conexion
	 * @param bean
	 * @throws JMSException
	 */
	@SuppressWarnings("static-access")
	private void conectarJms() throws JMSException {
		try {
			Traza.auditoria_MQ(getClass(), Mensajes.MQ_REALIZANDO_CONEXION);
			
			this.configurarDatosJms();
			
			// Establecer el ambiente mqServidor
			MQEnvironment mqEnvironment = new MQEnvironment();
			mqEnvironment.hostname = mqServidor.getConnectionFactory().getHostName();
			mqEnvironment.channel = mqServidor.getConnectionFactory().getChannel();
			mqEnvironment.port = mqServidor.getConnectionFactory().getPort();
			mqEnvironment.disableTracing();
			
			// Realizo una conexión al mqServidor
			if (mqServidor.getAutenticacion() != null) {
				if (!Validaciones.isNullOrEmpty(mqServidor.getAutenticacion().getUser())) {
					mqEnvironment.userID = mqServidor.getAutenticacion().getUser();
				}
				if (!Validaciones.isNullOrEmpty(mqServidor.getAutenticacion().getPass())) {
					mqEnvironment.password = mqServidor.getAutenticacion().getPass();
				}
			} 
			
			setQueueManagerNameReenvio(mqServidor.getConnectionFactory().getQueueManager());
			 
			// Crear la conexion a MQManager
			qManager = new MQQueueManager(mqServidor.getConnectionFactory().getQueueManager());
			
			gmo = new MQGetMessageOptions();
			pmo = new MQPutMessageOptions();
			
			//Creo las colas
			this.crearColaEmisor();
			this.crearColaReceptor();
			
			Traza.auditoria_MQ(getClass(), Mensajes.MQ_CONECTADO);
		
		} catch (MQException e) {
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
				qManager.disconnect();
				
				Traza.auditoria_MQ(getClass(), Mensajes.MQ_DESCONECTADO);
			} catch (MQException e) {
				throw new JMSException(
						"Error al Desconectar a la Cola. CompletitionCode: "
								+ e.completionCode + ".ReasonCode: " + e.reasonCode);
			}
		} else {
			Traza.auditoria_MQ(getClass(), Mensajes.MQ_DESCONECTADO_VACIO);
		}
	}

	/************************************************************************
	 * Getters/Setters
	 ***********************************************************************/
	public String getQueueNameReenvio() {
		return queueNameReenvio;
	}
	public String getQueueManagerNameReenvio() {
		return queueManagerNameReenvio;
	}
	
	public void setQueueNameReenvio(String queueNameReenvio) {
		this.queueNameReenvio = queueNameReenvio;
	}
	public void setQueueManagerNameReenvio(String queueManagerNameReenvio) {
		this.queueManagerNameReenvio = queueManagerNameReenvio;
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public MQSeriesBase getMqServidor() {
		return mqServidor;
	}
	public void setMqServidor(MQSeriesBase mqServidor) {
		this.mqServidor = mqServidor;
	}

	public void setJmsPropiedades(JmsPropiedades jmsPropiedades) {
		this.jmsPropiedades = jmsPropiedades;
	}
	public JmsPropiedades getJmsPropiedades() {
		return jmsPropiedades;
	}
}
