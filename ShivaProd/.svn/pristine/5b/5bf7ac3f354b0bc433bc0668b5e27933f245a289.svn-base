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
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class CalipsoDescobrosWSInterceptor  implements HandlerResolver {
	
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
        	    
        	    String idOperacion = ControlXml.buscarElemento("idOperacionShiva", baos.toByteArray());
                String idTransaccion = ControlXml.buscarElemento("idTransaccionShiva", baos.toByteArray());
                
                String mensaje = baos.toString();
                
                //Inicio Dummies
//                if(!outboundProperty.booleanValue()){
//                	
//                	try {
//                		
//                		if("100004".equals(idTransaccion)){
//                			//simulacion
////                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:ReversionResponse><RespuestaConsulta><idTransaccionShiva>0100004.00001</idTransaccionShiva><tipoOperacion>09</tipoOperacion><DetResultadoServicio><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoServicio><DetFactura><DetResultadoFactura><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoFactura><DetAcuerdoContracargo><EstadoAcuerdo></EstadoAcuerdo><EstadoCargo></EstadoCargo></DetAcuerdoContracargo></DetFactura><ListaDetMediosPago><DetMedioPago><IdMedioPago><TipoComprobante></TipoComprobante><ClaseComprobante></ClaseComprobante><SucursalComprobante></SucursalComprobante><NumeroComprobante></NumeroComprobante></IdMedioPago><idDocumentoCuentasCobranza>1004317</idDocumentoCuentasCobranza><DetResultadoMedioPago><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoMedioPago></DetMedioPago></ListaDetMediosPago><DetCargo></DetCargo><ListaDetOpRelacionadas></ListaDetOpRelacionadas><listaCreDeb></listaCreDeb></RespuestaConsulta></ns1:ReversionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//                			//imputacion factura y medio de pago ok
////                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:ReversionResponse><RespuestaConsulta><idTransaccionShiva>0100004.00001</idTransaccionShiva><tipoOperacion>09</tipoOperacion><DetResultadoServicio><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoServicio><DetFactura><DetResultadoFactura><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoFactura><DetAcuerdoContracargo></DetAcuerdoContracargo></DetFactura><ListaDetMediosPago><DetMedioPago><IdMedioPago></IdMedioPago><idDocumentoCuentasCobranza>1004317</idDocumentoCuentasCobranza><DetResultadoMedioPago><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoMedioPago></DetMedioPago></ListaDetMediosPago><DetCargo></DetCargo><ListaDetOpRelacionadas></ListaDetOpRelacionadas><listaCreDeb></listaCreDeb></RespuestaConsulta></ns1:ReversionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//                			//error 8012 
//                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:ReversionResponse><RespuestaConsulta><idTransaccionShiva>0100004.00001</idTransaccionShiva><tipoOperacion>09</tipoOperacion><DetResultadoServicio><resultado>ERR</resultado><codigoerror>8012</codigoerror><descripcionerror>Se ha producido un error inesperado al realizar la gestion solicitada</descripcionerror></DetResultadoServicio><DetFactura></DetFactura><ListaDetMediosPago></ListaDetMediosPago><DetCargo></DetCargo><ListaDetOpRelacionadas></ListaDetOpRelacionadas><listaCreDeb></listaCreDeb></RespuestaConsulta></ns1:ReversionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//                            idTransaccion = "0100004.00001";
//                		} else if("100008".equals(idTransaccion)){
//                			//simulacion / imputacion Ok con estado cargo generado en FACTURADO
////                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:ReversionResponse><RespuestaConsulta><idTransaccionShiva>0100008.00001</idTransaccionShiva><tipoOperacion>09</tipoOperacion><DetResultadoServicio><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoServicio><DetFactura><DetResultadoFactura><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoFactura><DetAcuerdoContracargo><EstadoAcuerdo>ACTIVO</EstadoAcuerdo><EstadoCargo>FACTURADO</EstadoCargo></DetAcuerdoContracargo></DetFactura><ListaDetMediosPago><DetMedioPago><IdMedioPago></IdMedioPago><idDocumentoCuentasCobranza>1102871</idDocumentoCuentasCobranza><DetResultadoMedioPago><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoMedioPago></DetMedioPago></ListaDetMediosPago><DetCargo></DetCargo><ListaDetOpRelacionadas></ListaDetOpRelacionadas><listaCreDeb></listaCreDeb></RespuestaConsulta></ns1:ReversionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//                			//imputacion con error en primer descobro
//                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:ReversionResponse><RespuestaConsulta><idTransaccionShiva>0100008.00001</idTransaccionShiva><tipoOperacion>09</tipoOperacion><DetResultadoServicio><resultado>ERR</resultado><codigoerror>8012</codigoerror><descripcionerror>Se ha producido un error inesperado al realizar la gestion solicitada</descripcionerror></DetResultadoServicio><DetFactura></DetFactura><ListaDetMediosPago></ListaDetMediosPago><DetCargo></DetCargo><ListaDetOpRelacionadas></ListaDetOpRelacionadas><listaCreDeb></listaCreDeb></RespuestaConsulta></ns1:ReversionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//                			idTransaccion = "0100008.00001";
//                		} else if("100139".equals(idTransaccion)){
//                			//transaccion 2 OK simulacion / imputacion
////                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:ReversionResponse><RespuestaConsulta><idTransaccionShiva>0100139.00002</idTransaccionShiva><tipoOperacion>09</tipoOperacion><DetResultadoServicio><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoServicio><DetFactura><DetResultadoFactura><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoFactura><DetAcuerdoContracargo></DetAcuerdoContracargo></DetFactura><ListaDetMediosPago></ListaDetMediosPago><DetCargo></DetCargo><ListaDetOpRelacionadas></ListaDetOpRelacionadas><listaCreDeb></listaCreDeb></RespuestaConsulta></ns1:ReversionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
////                			idTransaccion = "0100139.00002";
//                			//transaccion 1 OK simulacion
////                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:ReversionResponse><RespuestaConsulta><idTransaccionShiva>0100139.00001</idTransaccionShiva><tipoOperacion>09</tipoOperacion><DetResultadoServicio><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoServicio><DetFactura><DetResultadoFactura><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoFactura><DetAcuerdoContracargo></DetAcuerdoContracargo></DetFactura><ListaDetMediosPago></ListaDetMediosPago><DetCargo></DetCargo><ListaDetOpRelacionadas></ListaDetOpRelacionadas><listaCreDeb></listaCreDeb></RespuestaConsulta></ns1:ReversionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//                			//transaccion 1 error imputacion
//                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:ReversionResponse><RespuestaConsulta><idTransaccionShiva>0100139.00001</idTransaccionShiva><tipoOperacion>09</tipoOperacion><DetResultadoServicio><resultado>ERR</resultado><codigoerror>8010</codigoerror><descripcionerror>Se ha producido un error que impide continuar con la gestion solicitada</descripcionerror></DetResultadoServicio><DetFactura><DetResultadoFactura><resultado>ERR</resultado><codigoerror>8010</codigoerror><descripcionerror>Se ha producido un error que impide continuar con la gestion solicitada</descripcionerror></DetResultadoFactura><DetAcuerdoContracargo></DetAcuerdoContracargo></DetFactura><ListaDetMediosPago></ListaDetMediosPago><DetCargo></DetCargo><ListaDetOpRelacionadas></ListaDetOpRelacionadas><listaCreDeb></listaCreDeb></RespuestaConsulta></ns1:ReversionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//                			idTransaccion = "0100139.00001";
//                		} else if("100160".equals(idTransaccion)){
//                			//transaccion 2 OK simulacion / imputacion
////                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:ReversionResponse><RespuestaConsulta><idTransaccionShiva>0100160.00002</idTransaccionShiva><tipoOperacion>09</tipoOperacion><DetResultadoServicio><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoServicio><DetFactura><DetResultadoFactura><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoFactura><DetAcuerdoContracargo><EstadoAcuerdo>ACTIVO</EstadoAcuerdo><EstadoCargo>FACTURADO</EstadoCargo></DetAcuerdoContracargo></DetFactura><ListaDetMediosPago><DetMedioPago><IdMedioPago></IdMedioPago><idDocumentoCuentasCobranza>3617296</idDocumentoCuentasCobranza><DetResultadoMedioPago><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoMedioPago></DetMedioPago></ListaDetMediosPago><DetCargo></DetCargo><ListaDetOpRelacionadas></ListaDetOpRelacionadas><listaCreDeb></listaCreDeb></RespuestaConsulta></ns1:ReversionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
////                			idTransaccion = "0100160.00002";
//                			
//                			//transaccion 1 OK simulacion
////                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:ReversionResponse><RespuestaConsulta><idTransaccionShiva>0100160.00001</idTransaccionShiva><tipoOperacion>09</tipoOperacion><DetResultadoServicio><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoServicio><DetFactura><DetResultadoFactura><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoFactura><DetAcuerdoContracargo><EstadoAcuerdo>ACTIVO</EstadoAcuerdo><EstadoCargo>FACTURADO</EstadoCargo></DetAcuerdoContracargo></DetFactura><ListaDetMediosPago><DetMedioPago><IdMedioPago></IdMedioPago><idDocumentoCuentasCobranza>3617296</idDocumentoCuentasCobranza><DetResultadoMedioPago><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoMedioPago></DetMedioPago></ListaDetMediosPago><DetCargo></DetCargo><ListaDetOpRelacionadas></ListaDetOpRelacionadas><listaCreDeb></listaCreDeb></RespuestaConsulta></ns1:ReversionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//                			
//                			//transaccion 1 error imputacion
//                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:ReversionResponse><RespuestaConsulta><idTransaccionShiva>0100160.00001</idTransaccionShiva><tipoOperacion>09</tipoOperacion><DetResultadoServicio><resultado>ERR</resultado><codigoerror>8010</codigoerror><descripcionerror>Se ha producido un error que impide continuar con la gestion solicitada</descripcionerror></DetResultadoServicio><DetFactura><DetResultadoFactura><resultado>ERR</resultado><codigoerror>8010</codigoerror><descripcionerror>Se ha producido un error que impide continuar con la gestion solicitada</descripcionerror></DetResultadoFactura><DetAcuerdoContracargo></DetAcuerdoContracargo></DetFactura><ListaDetMediosPago></ListaDetMediosPago><DetCargo></DetCargo><ListaDetOpRelacionadas></ListaDetOpRelacionadas><listaCreDeb></listaCreDeb></RespuestaConsulta></ns1:ReversionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//                			idTransaccion = "0100160.00001";
//                		} else if("100255".equals(idTransaccion)){
//                			//transaccion 1 OK simulacion / imputacion
//                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:ReversionResponse><RespuestaConsulta><idTransaccionShiva>0100255.00001</idTransaccionShiva><tipoOperacion>09</tipoOperacion><DetResultadoServicio><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoServicio><DetFactura><DetResultadoFactura><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoFactura><DetAcuerdoContracargo><EstadoAcuerdo></EstadoAcuerdo><EstadoCargo></EstadoCargo></DetAcuerdoContracargo></DetFactura><ListaDetMediosPago><DetMedioPago><IdMedioPago></IdMedioPago><idDocumentoCuentasCobranza>449737</idDocumentoCuentasCobranza><DetResultadoMedioPago><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoMedioPago></DetMedioPago></ListaDetMediosPago><DetCargo></DetCargo><ListaDetOpRelacionadas></ListaDetOpRelacionadas><listaCreDeb><detallecreditosydebitos><detalleGen><idCobranza>1111</idCobranza><importe>0.36</importe><importeCapital>0.3</importeCapital><importeImpuestos>0.03</importeImpuestos><idDocumento><Tipo>CRE</Tipo><Clase>B</Clase><Sucursal>0447</Sucursal><Numero>00005034</Numero></idDocumento><idDocumentoCuentasCobranza></idDocumentoCuentasCobranza><vencimiento>2015/12/31</vencimiento><fechaImputacion></fechaImputacion><importeAplicado>0.36</importeAplicado><origenDelDocumento>CDC</origenDelDocumento></detalleGen><detalleOri><idDocumento><Tipo>DEB</Tipo><Clase>A</Clase><Sucursal>0446</Sucursal><Numero>00005033</Numero></idDocumento><idDocumentoCuentasCobranza></idDocumentoCuentasCobranza></detalleOri></detallecreditosydebitos></listaCreDeb></RespuestaConsulta></ns1:ReversionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//                			idTransaccion = "0100255.00001";
//                		} else if("100284".equals(idTransaccion)){
//                			//transaccion 1 OK simulacion / imputacion
//                			mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:ReversionResponse><RespuestaConsulta><idTransaccionShiva>0100284.00001</idTransaccionShiva><tipoOperacion>09</tipoOperacion><DetResultadoServicio><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoServicio><DetFactura><DetResultadoFactura><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoFactura><DetAcuerdoContracargo><EstadoAcuerdo></EstadoAcuerdo><EstadoCargo></EstadoCargo></DetAcuerdoContracargo></DetFactura><ListaDetMediosPago><DetMedioPago><IdMedioPago></IdMedioPago><idDocumentoCuentasCobranza>3889031</idDocumentoCuentasCobranza><DetResultadoMedioPago><resultado>OK</resultado><codigoerror></codigoerror><descripcionerror></descripcionerror></DetResultadoMedioPago></DetMedioPago></ListaDetMediosPago><DetCargo></DetCargo><ListaDetOpRelacionadas></ListaDetOpRelacionadas><listaCreDeb><detallecreditosydebitos><detalleGen><idCobranza>1111</idCobranza><importe>21.81</importe><importeCapital>20</importeCapital><importeImpuestos>1.81</importeImpuestos><idDocumento><Tipo>CRE</Tipo><Clase>B</Clase><Sucursal>0440</Sucursal><Numero>00005030</Numero></idDocumento><idDocumentoCuentasCobranza></idDocumentoCuentasCobranza><vencimiento>2016/02/29</vencimiento><fechaImputacion></fechaImputacion><importeAplicado>21.81</importeAplicado><origenDelDocumento>CDC</origenDelDocumento></detalleGen><detalleOri><idDocumento><Tipo>DEB</Tipo><Clase>A</Clase><Sucursal>0446</Sucursal><Numero>00005034</Numero></idDocumento><idDocumentoCuentasCobranza></idDocumentoCuentasCobranza></detalleOri></detallecreditosydebitos></listaCreDeb></RespuestaConsulta></ns1:ReversionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//                			idTransaccion = "0100284.00001";
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
                //Fin Dummies  
                
        	    traceo.append(mensaje);
        	    
        	    Traza.transaccionWS(getClass(), traceo.toString());
                
                String key = "";
                if (!idTransaccion.contains(".")) {
                	String idOperacionConCeros = Utilidad.rellenarCerosIzquierda(idOperacion, 7);
        			String numeroTransaccion = Utilidad.rellenarCerosIzquierda(idTransaccion, 5);
                	key = (Validaciones.isNullOrEmpty(idOperacionConCeros)?"":idOperacionConCeros) + "." + (Validaciones.isNullOrEmpty(numeroTransaccion)?"":numeroTransaccion);
                } else {
                	key = idTransaccion; 
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
