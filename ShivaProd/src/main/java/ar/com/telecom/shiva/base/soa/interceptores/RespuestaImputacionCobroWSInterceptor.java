package ar.com.telecom.shiva.base.soa.interceptores;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.PortInfo;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import ar.com.telecom.shiva.base.soa.singleton.SoapMensajesSingleton;
import ar.com.telecom.shiva.base.utils.ControlXml;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class RespuestaImputacionCobroWSInterceptor  implements HandlerResolver {
	
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
        	
        	StringBuffer traceo = new StringBuffer("");
        	if (outboundProperty.booleanValue()) {
        		traceo.append("Salida:\n");
        	} else {
        		traceo.append("Entrada:\n");
        	}
        	 
        	SOAPMessage message = smc.getMessage();
        	try {
        		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        	    message.writeTo(baos);
        	    String mensaje = baos.toString();
        	    traceo.append(mensaje);
        	    
        	    Traza.transaccionWS(getClass(), traceo.toString());
        	    
        	    
        	    if (outboundProperty.booleanValue()) {
        	    	String numeroCaso = Utilidad.rellenarCerosIzquierda(ControlXml.buscarElemento("Caso", baos.toByteArray()).trim(), 10);
                    String numeroPlanilla = Utilidad.rellenarCerosIzquierda(ControlXml.buscarElemento("PlanillaMovika", baos.toByteArray()).trim(), 10);
                    
        	    	SoapMensajesSingleton.addMensajeSalida(numeroPlanilla + "." + numeroCaso, mensaje);
        	    } else {
        	    	String numeroCaso = Utilidad.rellenarCerosIzquierda(ControlXml.buscarElemento("NumeroCaso", baos.toByteArray()).trim(), 10);
                    String numeroPlanilla = Utilidad.rellenarCerosIzquierda(ControlXml.buscarElemento("NumeroPlanilla", baos.toByteArray()).trim(), 10);
                    
        	    	SoapMensajesSingleton.addMensajeEntrada(numeroPlanilla + "." + numeroCaso, mensaje);
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
