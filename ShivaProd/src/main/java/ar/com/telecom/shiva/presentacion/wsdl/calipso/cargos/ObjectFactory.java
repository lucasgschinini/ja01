
package ar.com.telecom.shiva.presentacion.wsdl.calipso.cargos;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.telecom.shiva.presentacion.wsdl.calipso.cargos package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.telecom.shiva.presentacion.wsdl.calipso.cargos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Resultadocargo }
     * 
     */
    public Resultadocargo createResultadocargo() {
        return new Resultadocargo();
    }

    /**
     * Create an instance of {@link RespuestaCargo }
     * 
     */
    public RespuestaCargo createRespuestaCargo() {
        return new RespuestaCargo();
    }

    /**
     * Create an instance of {@link DetCargo }
     * 
     */
    public DetCargo createDetCargo() {
        return new DetCargo();
    }

}
