/**
 * 
 */
package ar.com.telecom.shiva.base.soa.interceptores;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.soa.singleton.SoapMensajesSingleton;
import ar.com.telecom.shiva.base.utils.ControlXml;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;

/**
 * @author u586743
 *
 */
public class SapRegistrarCompensacionWSInterceptor implements SOAPHandler<SOAPMessageContext>  {
	
	
	@Override
	public void close(MessageContext arg0) {
		
	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {

		logToSystemOut(arg0);
        return true;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext arg0) {
		
		logToSystemOut(arg0);
        return true;
	}

	@Override
	public Set<QName> getHeaders() {
		 return new TreeSet<QName>();
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
    		String endpointAddress = (String) smc.get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
    	    traceo.append("Endpoint (url del servicio): " + endpointAddress + "\n");    		
    		traceo.append("Salida:\n");
    	} else {
    		traceo.append("Entrada:\n");
    		
    		// Comienzo Dummy  
    		// cargarDatosDummy(smc);
			// Fin Dummy
    	}
    	 
    	try {
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	    message.writeTo(baos);
    	    String mensaje = baos.toString();
    	    traceo.append(mensaje);
    	    
    	    Traza.transaccionWS(getClass(), traceo.toString());
    	    
    	    String keySalida = ControlXml.buscarElemento("I_IDOPERACIONSHIVA", baos.toByteArray());
            String keyEntrada = ControlXml.buscarElemento("E_IDOPERACIONSHIVA", baos.toByteArray());

    	    if (outboundProperty.booleanValue()) {
            	SoapMensajesSingleton.addMensajeSalida(keySalida, mensaje);
    	    } else {
    	        SoapMensajesSingleton.addMensajeEntrada(keyEntrada, mensaje);
    	    }
    	    
    	} catch (Exception e) {
    		Traza.error(getClass(), e.getMessage(), e);
    	}
    }
    
    /**
     * 
     * @param smc
     */
    private void cargarDatosDummy(SOAPMessageContext smc) {
		
    	SOAPMessage messageDummy = smc.getMessage();
    	StringBuffer traceo = new StringBuffer("");
    	
    	try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	    messageDummy.writeTo(baos);
    	    String mensaje = baos.toString();
    	    traceo.append(mensaje);
    	    
    	    Traza.transaccionWS(getClass(), traceo.toString());
    	    
    	    String idOperacion = ControlXml.buscarElemento("idShiva", baos.toByteArray());
            String idTransaccion = ControlXml.buscarElemento("idTransaccion", baos.toByteArray());
            String key = idOperacion;
            if (!idOperacion.contains(".")) {
            	key += "." + (Validaciones.isNullOrEmpty(idTransaccion)?"":idTransaccion);
            }
			
			//8750090.00001
			if("8750090.00001".equals(key)){
				mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header /><SOAP-ENV:Body><ns1:ApropiacionResponse><respuestaapropiacion><idShiva>8750090.00001</idShiva><detFac><idCobranza/><importemora>.11</importemora><montocta>.11</montocta><tipoCambioFechaEmision>3.6400</tipoCambioFechaEmision><tipoCambioFechaCobro>3.1000</tipoCambioFechaCobro><importeAplicadoFechaEmisionPesos>5.7500</importeAplicadoFechaEmisionPesos><importeAplicadoMonedaOrigen>1.5797</importeAplicadoMonedaOrigen><listaResultadoApropiacion><resultadoApropiacion><resultado>OK</resultado><codigoerror/><descripcionerror/></resultadoApropiacion></listaResultadoApropiacion></detFac><listaCreDeb><detalle><idCobranza/><importe>.85</importe><importeCapital>.67</importeCapital><importeImpuestos>.18</importeImpuestos><moneda>$</moneda><idDocctascob>4659914</idDocctascob><tipo>CRE</tipo><clase>A</clase><sucursal>0780</sucursal><numero>00000022</numero><idDocumentoCuentasCobranza/><vencimiento>2016/03/31</vencimiento><importeAplicado>.85</importeAplicado><origenDelDocumento>DC</origenDelDocumento><InformacionAdicionalTagetikCalipso><RazonSocialCliente>PEREZ PEREZ</RazonSocialCliente><TipoCliente>R1</TipoCliente><CUIT>30577922620</CUIT></InformacionAdicionalTagetikCalipso><InformacionAdicionalDacota><UnidadOperativa>UGC</UnidadOperativa><SubTipo>DIF</SubTipo><Holding/></InformacionAdicionalDacota></detalle></listaCreDeb><listaCtaCre><detalle><idCobranza/><idDocumento>1370008</idDocumento><tipo>CRE</tipo><clase>A</clase><sucursal>0308</sucursal><numero>00009159</numero><tipoCambioFechaEmision>1.0000</tipoCambioFechaEmision><tipoCambioFechaCobro>2.0000</tipoCambioFechaCobro><importeAplicadoFechaEmisionPesos>3.0000</importeAplicadoFechaEmisionPesos><importeAplicadoMonedaOrigen>4.0000</importeAplicadoMonedaOrigen><detallenewcta/><resultadoApropiacion><resultado>OK</resultado><codigoerror/><descripcionerror/></resultadoApropiacion></detalle></listaCtaCre><resultado><idShiva>8750090.00001</idShiva><Resultado><resultado>OK</resultado><codigoerror/><descripcionerror/></Resultado></resultado></respuestaapropiacion></ns1:ApropiacionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
			}

			//8750090.00002
			if("8750090.00002".equals(key)){
				mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header /><SOAP-ENV:Body><ns1:ApropiacionResponse><respuestaapropiacion><idShiva>8750090.00002</idShiva><detFac><idCobranza/><importemora>.02</importemora><montocta>.02</montocta><tipoCambioFechaEmision>3.6400</tipoCambioFechaEmision><tipoCambioFechaCobro>3.0770</tipoCambioFechaCobro><importeAplicadoFechaEmisionPesos>.1200</importeAplicadoFechaEmisionPesos><importeAplicadoMonedaOrigen>.0330</importeAplicadoMonedaOrigen><listaResultadoApropiacion><resultadoApropiacion><resultado>OK</resultado><codigoerror/><descripcionerror/></resultadoApropiacion></listaResultadoApropiacion></detFac><listaCreDeb><detalle><idCobranza/><importe>.02</importe><importeCapital>.02</importeCapital><importeImpuestos>0</importeImpuestos><moneda>$</moneda><idDocctascob>4659915</idDocctascob><tipo>CRE</tipo><clase>A</clase><sucursal>0780</sucursal><numero>00000022</numero><idDocumentoCuentasCobranza/><vencimiento>2016/03/31</vencimiento><importeAplicado>.02</importeAplicado><origenDelDocumento>DC</origenDelDocumento><InformacionAdicionalTagetikCalipso><RazonSocialCliente>PEREZPEREZ</RazonSocialCliente><TipoCliente>R1</TipoCliente><CUIT>30577922620</CUIT></InformacionAdicionalTagetikCalipso><InformacionAdicionalDacota><UnidadOperativa>UGC</UnidadOperativa><SubTipo>DIF</SubTipo><Holding/></InformacionAdicionalDacota></detalle></listaCreDeb><listaCtaCre><detalle><idCobranza/><idDocumento>914057</idDocumento><tipo>CTA</tipo><clase>X</clase><sucursal>0780</sucursal><numero>00012322</numero><tipoCambioFechaEmision>5.0000</tipoCambioFechaEmision><tipoCambioFechaCobro>6.0000</tipoCambioFechaCobro><importeAplicadoFechaEmisionPesos>7.0000</importeAplicadoFechaEmisionPesos><importeAplicadoMonedaOrigen>8.0000</importeAplicadoMonedaOrigen><detallenewcta><importe>49.09</importe><moneda>$</moneda><idDocumento/><tipo>CTA</tipo><clase>X</clase><sucursal>0780</sucursal><numero>00012322</numero><idDocumentoCuentasCobranza/><fechaoriginalcta>2007/06/08</fechaoriginalcta></detallenewcta><resultadoApropiacion><resultado>OK</resultado><codigoerror/><descripcionerror/></resultadoApropiacion></detalle></listaCtaCre><resultado><idShiva>8750090.00002</idShiva><Resultado><resultado>OK</resultado><codigoerror/><descripcionerror/></Resultado></resultado></respuestaapropiacion></ns1:ApropiacionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
			}
			
			//8750090.00003
			if("8750090.00003".equals(key)){
				mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header /><SOAP-ENV:Body><ns1:ApropiacionResponse><respuestaapropiacion><idShiva>8750090.00003</idShiva><detFac><idCobranza/><importemora>.33</importemora><montocta>.33</montocta><tipoCambioFechaEmision>3.5400</tipoCambioFechaEmision><tipoCambioFechaCobro>3.0770</tipoCambioFechaCobro><importeAplicadoFechaEmisionPesos>2.2000</importeAplicadoFechaEmisionPesos><importeAplicadoMonedaOrigen>.6215</importeAplicadoMonedaOrigen><listaResultadoApropiacion><resultadoApropiacion><resultado>OK</resultado><codigoerror/><descripcionerror/></resultadoApropiacion></listaResultadoApropiacion></detFac><listaCreDeb><detalle><idCobranza/><importe>.29</importe><importeCapital>.23</importeCapital><importeImpuestos>.06</importeImpuestos><moneda>$</moneda><idDocctascob>4659917</idDocctascob><tipo>CRE</tipo><clase>A</clase><sucursal>0780</sucursal><numero>00000022</numero><idDocumentoCuentasCobranza/><vencimiento>2016/04/01</vencimiento><importeAplicado>.29</importeAplicado><origenDelDocumento>DC</origenDelDocumento><InformacionAdicionalTagetikCalipso><RazonSocialCliente>PEREZPEREZ</RazonSocialCliente><TipoCliente>R1</TipoCliente><CUIT>30577922620</CUIT></InformacionAdicionalTagetikCalipso><InformacionAdicionalDacota><UnidadOperativa>UGC</UnidadOperativa><SubTipo>DIF</SubTipo><Holding/></InformacionAdicionalDacota></detalle></listaCreDeb><listaCtaCre><detalle><idCobranza/><idDocumento>914057</idDocumento><tipo>CTA</tipo><clase>X</clase><sucursal>0780</sucursal><numero>00012322</numero><tipoCambioFechaEmision>9.0000</tipoCambioFechaEmision><tipoCambioFechaCobro>10.0000</tipoCambioFechaCobro><importeAplicadoFechaEmisionPesos>11.0000</importeAplicadoFechaEmisionPesos><importeAplicadoMonedaOrigen>12</importeAplicadoMonedaOrigen><detallenewcta><importe>47.18</importe><moneda>$</moneda><idDocumento/><tipo>CTA</tipo><clase>X</clase><sucursal>0780</sucursal><numero>00012322</numero><idDocumentoCuentasCobranza/><fechaoriginalcta>2007/06/08</fechaoriginalcta></detallenewcta><resultadoApropiacion><resultado>OK</resultado><codigoerror/><descripcionerror/></resultadoApropiacion></detalle></listaCtaCre><resultado><idShiva>8750090.00003</idShiva><Resultado><resultado>OK</resultado><codigoerror/><descripcionerror/></Resultado></resultado></respuestaapropiacion></ns1:ApropiacionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
			}
			
			//8750090.00004
			if("8750090.00004".equals(key)){
				mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header /><SOAP-ENV:Body><ns1:ApropiacionResponse><respuestaapropiacion><idShiva>8750090.00004</idShiva><detFac><idCobranza/><importemora>.04</importemora><montocta>.04</montocta><tipoCambioFechaEmision>3.5400</tipoCambioFechaEmision><tipoCambioFechaCobro>10.0000</tipoCambioFechaCobro><importeAplicadoFechaEmisionPesos>.0300</importeAplicadoFechaEmisionPesos><importeAplicadoMonedaOrigen>.0085</importeAplicadoMonedaOrigen><listaResultadoApropiacion><resultadoApropiacion><resultado>OK</resultado><codigoerror/><descripcionerror/></resultadoApropiacion></listaResultadoApropiacion></detFac><listaCreDeb><detalle><idCobranza/><importe>.07</importe><importeCapital>.06</importeCapital><importeImpuestos>.01</importeImpuestos><idDocctascob>4659919</idDocctascob><tipo>DEB</tipo><clase>A</clase><sucursal>0780</sucursal><numero>80000030</numero><idDocumentoCuentasCobranza/><vencimiento>2016/04/01</vencimiento><importeAplicado>.06</importeAplicado><origenDelDocumento>DC</origenDelDocumento><InformacionAdicionalTagetikCalipso><RazonSocialCliente>PEREZPEREZ</RazonSocialCliente><TipoCliente>R1</TipoCliente><CUIT>30577922620</CUIT></InformacionAdicionalTagetikCalipso><InformacionAdicionalDacota><UnidadOperativa>UGC</UnidadOperativa><SubTipo>DIF</SubTipo><Holding/></InformacionAdicionalDacota></detalle></listaCreDeb><listaCtaCre><detalle><idCobranza/><idDocumento>910874</idDocumento><tipo>CTA</tipo><clase>X</clase><sucursal>0780</sucursal><numero>00000008</numero><tipoCambioFechaEmision>13.0000</tipoCambioFechaEmision><tipoCambioFechaCobro>14.0000</tipoCambioFechaCobro><importeAplicadoFechaEmisionPesos>15</importeAplicadoFechaEmisionPesos><importeAplicadoMonedaOrigen>16</importeAplicadoMonedaOrigen><detallenewcta/><resultadoApropiacion><resultado>OK</resultado><codigoerror/><descripcionerror/></resultadoApropiacion></detalle></listaCtaCre><resultado><idShiva>8750090.00004</idShiva><Resultado><resultado>OK</resultado><codigoerror/><descripcionerror/></Resultado></resultado></respuestaapropiacion></ns1:ApropiacionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
			}
			
			baos = new ByteArrayOutputStream();
    	    messageDummy.writeTo(baos);

    	    MimeHeaders headers = new MimeHeaders();
			MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
			byte[] bytes = mensaje.getBytes();
			InputStream is = new ByteArrayInputStream(bytes);
			
			SOAPMessage msg = msgFactory.createMessage(headers, is); 
			smc.setMessage(msg);
			
			messageDummy = smc.getMessage();
		} catch (SOAPException | IOException | NegocioExcepcion e) {
			Traza.error(getClass(), e.getMessage(), e);
		}   
    }



}
