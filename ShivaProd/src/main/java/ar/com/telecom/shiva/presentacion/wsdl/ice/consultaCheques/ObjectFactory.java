
package ar.com.telecom.shiva.presentacion.wsdl.ice.consultaCheques;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.telecom.shiva.presentacion.wsdl.ice.consultaCheques package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.telecom.shiva.presentacion.wsdl.ice.consultaCheques
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaChequesEntrada }
     * 
     */
    public ConsultaChequesEntrada createConsultaChequesEntrada() {
        return new ConsultaChequesEntrada();
    }

    /**
     * Create an instance of {@link ConsultaChequesSalida }
     * 
     */
    public ConsultaChequesSalida createConsultaChequesSalida() {
        return new ConsultaChequesSalida();
    }

    /**
     * Create an instance of {@link Cliente }
     * 
     */
    public Cliente createCliente() {
        return new Cliente();
    }

    /**
     * Create an instance of {@link Cheque }
     * 
     */
    public Cheque createCheque() {
        return new Cheque();
    }

    /**
     * Create an instance of {@link ConsultaChequesResponse }
     * 
     */
    public ConsultaChequesResponse createConsultaChequesResponse() {
        return new ConsultaChequesResponse();
    }

    /**
     * Create an instance of {@link ConsultaCheques }
     * 
     */
    public ConsultaCheques createConsultaCheques() {
        return new ConsultaCheques();
    }

}
