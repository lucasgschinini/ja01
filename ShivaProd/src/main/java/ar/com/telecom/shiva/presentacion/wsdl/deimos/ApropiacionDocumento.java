
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ApropiacionDocumento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ApropiacionDocumento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ApropiacionDeimos" type="{http://shiva.ws.intelap.com/}ApropiacionDeimos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApropiacionDocumento", propOrder = {
    "apropiacionDeimos"
})
public class ApropiacionDocumento {

    @XmlElement(name = "ApropiacionDeimos")
    protected ApropiacionDeimos apropiacionDeimos;

    /**
     * Gets the value of the apropiacionDeimos property.
     * 
     * @return
     *     possible object is
     *     {@link ApropiacionDeimos }
     *     
     */
    public ApropiacionDeimos getApropiacionDeimos() {
        return apropiacionDeimos;
    }

    /**
     * Sets the value of the apropiacionDeimos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApropiacionDeimos }
     *     
     */
    public void setApropiacionDeimos(ApropiacionDeimos value) {
        this.apropiacionDeimos = value;
    }

}
