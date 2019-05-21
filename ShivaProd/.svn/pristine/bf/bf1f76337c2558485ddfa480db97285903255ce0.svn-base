package ar.com.telecom.shiva.base.soa.interceptores;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import ar.com.telecom.shiva.base.utils.logs.Traza;

public class RecepcionNovedadesDocumentosSAPWSInterceptor  implements SOAPHandler<SOAPMessageContext> {
	
    public boolean handleMessage(SOAPMessageContext messageContext) {
    	 procesarInterceptacion(messageContext);
         return true;
    }

    public Set<QName> getHeaders() {
        return Collections.emptySet();
    }

    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }

    public void close(MessageContext context) {
    }
    
    
    /**
     * Traceo el mensaje y guardo en la Memoria
     * @param smc
     */
    private void procesarInterceptacion(SOAPMessageContext smc) {
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

    	} catch (Exception e) {
    		Traza.error(getClass(), e.getMessage(), e);
    	}
    }


}
