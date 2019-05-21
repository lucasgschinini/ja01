package ar.com.telecom.shiva.base.soa.interceptores;

import java.io.OutputStream;
import java.util.List;

import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.StaxOutInterceptor;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import ar.com.telecom.shiva.base.utils.logs.Traza;

import com.greenbird.xml.prettyprinter.PrettyPrinter;
import com.greenbird.xml.prettyprinter.PrettyPrinterFactory;

public class SoapLoggingOutInterceptor extends
		AbstractPhaseInterceptor<Message> {

	private static final String LOCAL_NAME = "MessageID";
    private static final int PROPERTIES_SIZE = 128;
    protected PrettyPrinter prettyPrinter = null;
    
    public SoapLoggingOutInterceptor() {
    	super(Phase.PRE_STREAM);
        addBefore(StaxOutInterceptor.class.getName());
    }
    
	@Override
	public void handleMessage(Message message) throws Fault {
    	try {
	    	final OutputStream os = message.getContent(OutputStream.class);
	        if (os == null) {
	            return;
	        }
	
	        // Write the output while caching it for the log message
	        final CacheAndWriteOutputStream newOut = new CacheAndWriteOutputStream(os);
	        message.setContent(OutputStream.class, newOut);
	
	        int contentSize = -1;
	        String contentSizeString = getHeader(message, "Content-Size");
	        if(contentSizeString != null) {
	        	contentSize = Integer.parseInt(contentSizeString);
	        }
	        if(contentSize == -1) {
	            contentSize = 8 * 1024;
	        }
	        
	        StringBuilder buffer = new StringBuilder(contentSize + PROPERTIES_SIZE);
	        
	        logProperties(buffer, message);
	        
	        newOut.registerCallback(new SimpleLoggingCallback(buffer));
	        
    	} catch (Exception e) {
    		Traza.error(getClass(), e.getMessage(), e);
    	}
    }
    
	/**
     * Method intended for use within subclasses. Log custom field here.
     * 
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

    private class SimpleLoggingCallback implements CachedOutputStreamCallback {
        private StringBuilder buffer;

        public SimpleLoggingCallback(StringBuilder buffer) {
            this.buffer = buffer;;
        }

        public void onFlush(CachedOutputStream cos) {

        }

        public void onClose(CachedOutputStream cos) {
        	PrettyPrinterFactory factory = new PrettyPrinterFactory();
        	prettyPrinter = factory.newPrettyPrinter();
        	
        	int length = buffer.length();
            try {
                cos.writeCacheTo(buffer);
            } catch (Exception ex) {
                // ignore
            }
            
            // decode chars from bytes
            char[] chars = new char[buffer.length() - length];
            buffer.getChars(length, buffer.length(), chars, 0);
            buffer.setLength(length);
            buffer.ensureCapacity(length + chars.length);
            
            // print XML
            buffer.append("Salida:\n");
            buffer.append(chars);
            // pretty print XML
            //if(!prettyPrinter.process(chars, 0, chars.length, buffer)) {
                // something unexpected - log as exception
            //    buffer.append(" was unable to format XML\n");
            //    buffer.append(chars); // unmodified XML
            //}
            Traza.transaccionWS(getClass(), buffer.toString());
        }
    }
    
    /**
     * Gets theMessageID header in the list of headers.
     *
     */
    protected String getIdHeader(Message message) {
    	return getHeader(message, LOCAL_NAME);
    }

    /**
     * 
     * @param message
     * @param name
     * @return
     */
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
}
