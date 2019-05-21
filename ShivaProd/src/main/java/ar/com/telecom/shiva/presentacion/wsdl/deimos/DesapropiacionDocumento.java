
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DesapropiacionDocumento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DesapropiacionDocumento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DesapropiacionDeimos" type="{http://shiva.ws.intelap.com/}DesapropiacionDeimos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DesapropiacionDocumento", propOrder = {
    "desapropiacionDeimos"
})
public class DesapropiacionDocumento {

    @XmlElement(name = "DesapropiacionDeimos")
    protected DesapropiacionDeimos desapropiacionDeimos;

    /**
     * Gets the value of the desapropiacionDeimos property.
     * 
     * @return
     *     possible object is
     *     {@link DesapropiacionDeimos }
     *     
     */
    public DesapropiacionDeimos getDesapropiacionDeimos() {
        return desapropiacionDeimos;
    }

    /**
     * Sets the value of the desapropiacionDeimos property.
     * 
     * @param value
     *     allowed object is
     *     {@link DesapropiacionDeimos }
     *     
     */
    public void setDesapropiacionDeimos(DesapropiacionDeimos value) {
        this.desapropiacionDeimos = value;
    }

}
