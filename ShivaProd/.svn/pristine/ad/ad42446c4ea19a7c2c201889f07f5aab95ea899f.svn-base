package ar.com.telecom.shiva.base.soa.interceptores;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.PortInfo;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.soa.singleton.SoapMensajesSingleton;
import ar.com.telecom.shiva.base.utils.ControlXml;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class CalipsoWSInterceptorDummy  implements HandlerResolver {
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Handler> getHandlerChain(PortInfo arg0) {
		List<Handler> handlerList = new ArrayList<Handler>();
        handlerList.add(new TrazaWsHandler());
        return handlerList;
	}

	
	class TrazaWsHandler implements SOAPHandler<SOAPMessageContext> {

        @Override
        public boolean handleMessage(SOAPMessageContext context) {
        	logToSystemOut(context);
            return true;
        }

        @Override
        public boolean handleFault(SOAPMessageContext context) {
        	logToSystemOut(context);
            return true;
        }

        /**
         * Traceo los mensajes SOAP
         * @param smc
         */
        private void logToSystemOut(SOAPMessageContext smc) {
        	Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        	SOAPMessage message = smc.getMessage();
        	StringBuffer traceo = new StringBuffer("");
        	
        	if (outboundProperty.booleanValue()) {
        		traceo.append("Salida:\n");
        	} else {
        		traceo.append("Entrada:\n");
        		
        		/*********************** Comienzo Dummy **************************************/ 
				try {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        	    message.writeTo(baos);
	        	    String idOperacion = ControlXml.buscarElemento("idShiva", baos.toByteArray());
	        	    String mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:ApropiacionResponse><respuestaapropiacion><idShiva>0000908.00002</idShiva><detFac><idcob>5985661</idcob><importemora>0</importemora><montocta>0</montocta><interesesbonificados/></detFac><listaCreDeb/><listaCtaCre/><resultado><idShiva>0001097.00002</idShiva><Resultado><resultado>OK</resultado><codigoerror/><descripcionerror/></Resultado></resultado></respuestaapropiacion></ns1:ApropiacionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
					if (!idOperacion.contains(".")) {
						//Es confirmacion o desapropiacion
						mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:DesapropiacionResponse><respuestadesapropiacion><idShiva>908</idShiva><Resultado><resultado>OK</resultado><codigoerror/><descripcionerror/></Resultado></respuestadesapropiacion></ns1:DesapropiacionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
					}
					
	                MimeHeaders headers = new MimeHeaders();
					MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
					byte[] bytes = mensaje.getBytes();
					InputStream is = new ByteArrayInputStream(bytes);
					
					SOAPMessage msg = msgFactory.createMessage(headers, is); 
					smc.setMessage(msg);
					
				} catch (SOAPException | IOException | NegocioExcepcion e) {
					Traza.error(getClass(), e.getMessage(), e);
				}
				/*********************** Fin Dummy **************************************/
        	}
        	 
        	
        	try {
        		message = smc.getMessage();
        		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        	    message.writeTo(baos);
        	    String mensaje = baos.toString();
        	    traceo.append(mensaje);
        	    
        	    Traza.transaccionWS(getClass(), traceo.toString());
        	    
        	    String idOperacion = ControlXml.buscarElemento("idShiva", baos.toByteArray());
                String idTransaccion = ControlXml.buscarElemento("idTransaccion", baos.toByteArray());
                String key = idOperacion;
                if (!idOperacion.contains(".")) {
                	key += "." + (Validaciones.isNullOrEmpty(idTransaccion)?"":idTransaccion);
                }
                
        	    if (outboundProperty.booleanValue()) {
                	SoapMensajesSingleton.addMensajeSalida(key, mensaje);
        	    } else {
        	        SoapMensajesSingleton.addMensajeEntrada(key, mensaje);
        	    }
        	    
        	} catch (Exception e) {
        		Traza.error(getClass(), e.getMessage(), e);
        	}
        }
        
        @Override
        public void close(MessageContext context) {}

        @Override
        public Set<QName> getHeaders() {
            return new TreeSet<QName>();
        }        
    }
}
