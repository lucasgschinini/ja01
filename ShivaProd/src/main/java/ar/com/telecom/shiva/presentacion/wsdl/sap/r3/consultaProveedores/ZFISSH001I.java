
package ar.com.telecom.shiva.presentacion.wsdl.sap.r3.consultaProveedores;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZFISSH001_I complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZFISSH001_I">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TAX_NO_1" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZFISSH001_I", propOrder = {
    "taxno1"
})
public class ZFISSH001I {

    @XmlElement(name = "TAX_NO_1", required = true)
    protected String taxno1;

    /**
     * Gets the value of the taxno1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTAXNO1() {
        return taxno1;
    }

    /**
     * Sets the value of the taxno1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTAXNO1(String value) {
        this.taxno1 = value;
    }

}
