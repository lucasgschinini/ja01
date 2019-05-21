
package ar.com.telecom.shiva.presentacion.wsdl.delta.header.request;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.telecom.shiva.presentacion.wsdl.delta.header.request package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.telecom.shiva.presentacion.wsdl.delta.header.request
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HeaderRequest }
     * 
     */
    public HeaderRequest createHeaderRequest() {
        return new HeaderRequest();
    }

    /**
     * Create an instance of {@link HeaderRequest.Properties }
     * 
     */
    public HeaderRequest.Properties createHeaderRequestProperties() {
        return new HeaderRequest.Properties();
    }

    /**
     * Create an instance of {@link HeaderRequest.Consumer }
     * 
     */
    public HeaderRequest.Consumer createHeaderRequestConsumer() {
        return new HeaderRequest.Consumer();
    }

    /**
     * Create an instance of {@link HeaderRequest.Transport }
     * 
     */
    public HeaderRequest.Transport createHeaderRequestTransport() {
        return new HeaderRequest.Transport();
    }

    /**
     * Create an instance of {@link HeaderRequest.Message }
     * 
     */
    public HeaderRequest.Message createHeaderRequestMessage() {
        return new HeaderRequest.Message();
    }

    /**
     * Create an instance of {@link HeaderRequest.Service }
     * 
     */
    public HeaderRequest.Service createHeaderRequestService() {
        return new HeaderRequest.Service();
    }

    /**
     * Create an instance of {@link HeaderRequest.Properties.Property }
     * 
     */
    public HeaderRequest.Properties.Property createHeaderRequestPropertiesProperty() {
        return new HeaderRequest.Properties.Property();
    }

}
