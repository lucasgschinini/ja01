
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdDocumentoMIC complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdDocumentoMIC">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumeroReferenciaMIC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdDocumentoMIC", propOrder = {
    "numeroReferenciaMIC"
})
public class IdDocumentoMIC {

    @XmlElement(name = "NumeroReferenciaMIC", nillable = true)
    protected String numeroReferenciaMIC;

    /**
     * Gets the value of the numeroReferenciaMIC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroReferenciaMIC() {
        return numeroReferenciaMIC;
    }

    /**
     * Sets the value of the numeroReferenciaMIC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroReferenciaMIC(String value) {
        this.numeroReferenciaMIC = value;
    }

}
