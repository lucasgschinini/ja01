package ar.com.telecom.shiva.base.soa.interceptores;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import ar.com.telecom.shiva.base.utils.logs.Traza;

import com.greenbird.xml.prettyprinter.PrettyPrinter;
import com.greenbird.xml.prettyprinter.PrettyPrinterFactory;

public class SoapLoggingInInterceptor extends
		AbstractPhaseInterceptor<Message> {

	private static final String LOCAL_NAME = "MessageID";
    private static final int PROPERTIES_SIZE = 128;
    protected PrettyPrinter prettyPrinter = null;
    
    public SoapLoggingInInterceptor() {
    	super(Phase.RECEIVE);
    }
    
    @SuppressWarnings("resource")
	@Override
	public void handleMessage(Message message) throws Fault {
		try {
			
//			/*********************** Comienzo Dummy **************************************/ 
//			String mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\">"
//        	    		+ "<SOAP-ENV:Body><ns1:ConsultaDeudaFiltroCalipsoResponse><ListaDebitosSalida><ListaDebitos><Debito>"
//        	    		+ "<IdClienteLegado>9032</IdClienteLegado><IdDocumento><TipoComprobante>FAC</TipoComprobante>"
//        	    		+ "<ClaseComprobante>B</ClaseComprobante><SucursalComprobante>0308</SucursalComprobante>"
//        	    		+ "<NumeroComprobante>00040632</NumeroComprobante></IdDocumento><FechaVencimiento>20050509</FechaVencimiento>"
//        	    		+ "<MonedaOriginalFactura>$</MonedaOriginalFactura><Importe1erVencimientoMonedaOrigen>457.1100</Importe1erVencimientoMonedaOrigen>"
//        	    		+ "<Importe1erVencimientoPesificado>457.1100</Importe1erVencimientoPesificado>"
//        	    		+ "<Saldo1erVencimientoMonedaOrigen>457.1100</Saldo1erVencimientoMonedaOrigen><SaldoPesificado>457.1100</SaldoPesificado>"
//        	    		+ "<CodigoAcuerdoFacturacion>13299</CodigoAcuerdoFacturacion><EstadoAcuerdoFacturacion>FACTURABLE</EstadoAcuerdoFacturacion>"
//        	    		+ "<EstadoMorosidad>DEUDA CANCELADA</EstadoMorosidad><MarcaReclamo>N</MarcaReclamo><MarcaMigradoDeimos>N</MarcaMigradoDeimos>"
//        	    		+ "<TipoCambioActual>0.3427005</TipoCambioActual><FechaEmision>20050318</FechaEmision><FechaUltimoPagoParcial />"
//        	    		+ "<IdDocCtasCob>1615665</IdDocCtasCob><InformacionAdicionalTagetikCalipso><RazonSocialCliente>PEREZ PEREZ</RazonSocialCliente>"
//        	    		+ "<TipoCliente>GO</TipoCliente><CUIT>30628540787</CUIT></InformacionAdicionalTagetikCalipso><InformacionAdicionalDacota>"
//        	    		+ "<UnidadOperativa>UGC</UnidadOperativa><SubTipo>MIG</SubTipo><Holding /></InformacionAdicionalDacota></Debito></ListaDebitos>"
//        	    		+ "<ControlPaginado><CantidadRegistrosRetornados>1</CantidadRegistrosRetornados>"
//        	    		+ "<CantidadRegistrosTotales>1</CantidadRegistrosTotales></ControlPaginado><ResultadoProcesamiento>"
//        	    		+ "<ResultadoImputacion>OK</ResultadoImputacion><codigoerror /><descripcionerror /></ResultadoProcesamiento>"
//        	    		+ "</ListaDebitosSalida></ns1:ConsultaDeudaFiltroCalipsoResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//        	    
//			byte[] bytes = mensaje.getBytes();
//			InputStream is = new ByteArrayInputStream(bytes);
//			message.setContent(InputStream.class, is);
//			org.apache.commons.io.IOUtils.closeQuietly(is);
//			/*********************** Fin Dummy **************************************/
			
			PrettyPrinterFactory factory = new PrettyPrinterFactory();
        	prettyPrinter = factory.newPrettyPrinter();
			CachedOutputStream cache = new CachedOutputStream();
			InputStream in = message.getContent(InputStream.class);
            if (in == null) {
                return;
            }
	        
            InputStream origIn = in;
            IOUtils.copy(in, cache);
            if (cache.size() > 0) {
                in = cache.getInputStream();
            } else {
                in = new ByteArrayInputStream(new byte[0]);
            }

            // set the inputstream back as message payload
            message.setContent(InputStream.class, in);

            cache.close();
            origIn.close();

            int contentSize = (int) cache.size();
            StringBuilder buffer = new StringBuilder(contentSize + PROPERTIES_SIZE);
            cache.writeCacheTo(buffer, "UTF-8");
            
            // decode chars from bytes
            char[] chars = new char[buffer.length()];
            buffer.getChars(0, chars.length, chars, 0);

            // reuse buffer
            buffer.setLength(0);
            logProperties(buffer, message);
            buffer.append("Entrada:\n");
            buffer.append(chars);
        	// pretty print XML
            //if(!prettyPrinter.process(chars, 0, chars.length, buffer)) {
                // something unexpected - log as exception
            //    buffer.append(" was unable to format XML:\n");
            //    buffer.append(chars); // unmodified XML
            //}
            Traza.transaccionWS(getClass(), buffer.toString());
            
    	} catch (Exception e) {
    		Traza.error(getClass(), e.getMessage(), e);
    	}
    }
	
	/**
     * Gets theMessageID header in the list of headers.
     */
    protected String getIdHeader(Message message) {
    	return getHeader(message, LOCAL_NAME);
    }

    @SuppressWarnings("unchecked")
	protected String getHeader(Message message, String name) {
        List<Header> headers = (List<Header>) message.get(Header.HEADER_LIST);

        if(headers != null) {
	        for(Header header:headers) {
	            if(header.getName().getLocalPart().equalsIgnoreCase(name)) {
	                return header.getObject().toString();
	            }
	        }
        }
        return null;
    }       
    
	/**
     * Method intended for use within subclasses. Log custom field here.
     * @param message message
    */

    protected void logProperties(StringBuilder buffer, Message message) {
        final String messageId = getIdHeader(message);
        if(messageId != null) {
            buffer.append(" MessageId=");
            buffer.append(messageId);
            buffer.append("\n");
        }
    }
}
