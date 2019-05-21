
package ar.com.telecom.shiva.presentacion.wsdl.sap.r3.consultaProveedores;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZSRETURN complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZSRETURN">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TYPE" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="NUMBER" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
 *         &lt;element name="MESSAGE" type="{urn:sap-com:document:sap:rfc:functions}char220"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZSRETURN", propOrder = {
    "type",
    "number",
    "message"
})
public class ZSRETURN {

    @XmlElement(name = "TYPE", required = true)
    protected String type;
    @XmlElement(name = "NUMBER", required = true)
    protected String number;
    @XmlElement(name = "MESSAGE", required = true)
    protected String message;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTYPE() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTYPE(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUMBER() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUMBER(String value) {
        this.number = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMESSAGE() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMESSAGE(String value) {
        this.message = value;
    }

}
