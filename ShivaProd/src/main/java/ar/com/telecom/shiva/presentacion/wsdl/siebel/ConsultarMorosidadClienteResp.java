
package ar.com.telecom.shiva.presentacion.wsdl.siebel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="esMoroso" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "esMoroso"
})
@XmlRootElement(name = "consultarMorosidadClienteResp", namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
public class ConsultarMorosidadClienteResp {

    @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
    protected boolean esMoroso;

    /**
     * Gets the value of the esMoroso property.
     * 
     */
    public boolean isEsMoroso() {
        return esMoroso;
    }

    /**
     * Sets the value of the esMoroso property.
     * 
     */
    public void setEsMoroso(boolean value) {
        this.esMoroso = value;
    }

}
