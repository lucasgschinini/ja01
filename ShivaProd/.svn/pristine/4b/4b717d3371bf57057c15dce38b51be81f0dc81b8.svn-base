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
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class IceConsultaChequesWSInterceptor implements HandlerResolver {
	
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
        	}
        	 
        	try {
        		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        	    message.writeTo(baos);
        	    
                String mensaje = baos.toString();
        	    
                //Inicio dummies
//        	    if(!outboundProperty.booleanValue()){
//                	
//                	try {
//                		
//                		if("0100005.00002".equals(idTransaccion)){
//                			//simulacion
//                			//contracargo cargo error
//                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:generacionCargoResponse><RespuestaConsulta><idTransaccionShiva>0100005.00002</idTransaccionShiva><idMovMer/><montoCalculadoMora/><montoACuenta/><interesesBonificados>0</interesesBonificados><Resultado><resultado>NOK</resultado><codigoerror>1002</codigoerror><descripcionerror>Error en la grabacion de movmercleinterefeoseimporte1.5codigocpfSHIVAsubtipoitemCPFtipocargoUcodigoi</descripcionerror></Resultado></RespuestaConsulta></ns1:generacionCargoResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//                			//contracargo interes error
////                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:generacionCargoResponse><RespuestaConsulta><idTransaccionShiva>0100005.00002</idTransaccionShiva><idMovMer/><montoCalculadoMora/><montoACuenta/><interesesBonificados>0</interesesBonificados><Resultado><resultado>NOK</resultado><codigoerror>1002</codigoerror><descripcionerror>Error en la grabacion de movmercleinterefeoseimporte0.04codigocpfSHIVAsubtipoitemCPFtipocargoUcodig</descripcionerror></Resultado></RespuestaConsulta></ns1:generacionCargoResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//                			//contracargo cargo e interes ok
////                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:generacionCargoResponse><RespuestaConsulta><idTransaccionShiva>0100005.00002</idTransaccionShiva><idMovMer/><montoCalculadoMora/><montoACuenta/><interesesBonificados>0</interesesBonificados><Resultado><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></Resultado></RespuestaConsulta></ns1:generacionCargoResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//                		} 
//
//                		
//    	        	    MimeHeaders headers = new MimeHeaders();
//    					MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
//    					byte[] bytes = mensaje.getBytes();
//    					InputStream is = new ByteArrayInputStream(bytes);
//    					
//    					SOAPMessage msg = msgFactory.createMessage(headers, is); 
//    					smc.setMessage(msg);
//    					
//    					message = smc.getMessage();
//    					
//    					message.writeTo(baos);
//                		
//    				} catch (SOAPException | IOException e) {
//    					Traza.error(getClass(), e.getMessage(), e);
//    				}
//                }
                //Fin dummies
                String traza = "";
                if  (mensaje.contains("<password>")){
                	traza =mensaje.replaceAll("<password>[\\s\\S]*?<\\/password>", "<password>******</password>");
                }else{
                	traza = mensaje;
                }
        	    traceo.append(traza);
        	    
        	    Traza.transaccionWS(getClass(), traceo.toString());
                
                String key = "";
                
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
