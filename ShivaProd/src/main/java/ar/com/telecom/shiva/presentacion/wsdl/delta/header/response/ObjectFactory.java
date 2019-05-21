
package ar.com.telecom.shiva.presentacion.wsdl.delta.header.response;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.telecom.shiva.presentacion.wsdl.delta.header.response package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.telecom.shiva.presentacion.wsdl.delta.header.response
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HeaderResponse }
     * 
     */
    public HeaderResponse createHeaderResponse() {
        return new HeaderResponse();
    }

    /**
     * Create an instance of {@link HeaderResponse.Properties }
     * 
     */
    public HeaderResponse.Properties createHeaderResponseProperties() {
        return new HeaderResponse.Properties();
    }

    /**
     * Create an instance of {@link HeaderResponse.Consumer }
     * 
     */
    public HeaderResponse.Consumer createHeaderResponseConsumer() {
        return new HeaderResponse.Consumer();
    }

    /**
     * Create an instance of {@link HeaderResponse.Transport }
     * 
     */
    public HeaderResponse.Transport createHeaderResponseTransport() {
        return new HeaderResponse.Transport();
    }

    /**
     * Create an instance of {@link HeaderResponse.Message }
     * 
     */
    public HeaderResponse.Message createHeaderResponseMessage() {
        return new HeaderResponse.Message();
    }

    /**
     * Create an instance of {@link HeaderResponse.Service }
     * 
     */
    public HeaderResponse.Service createHeaderResponseService() {
        return new HeaderResponse.Service();
    }

    /**
     * Create an instance of {@link HeaderResponse.Properties.Property }
     * 
     */
    public HeaderResponse.Properties.Property createHeaderResponsePropertiesProperty() {
        return new HeaderResponse.Properties.Property();
    }

}
