
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

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
 *       &lt;all>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}serviceCode"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}providerCode"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}sendJndiName"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}connectionFactoryJndiName"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "jmsQueueConfig")
public class JmsQueueConfig {

    @XmlElement(required = true)
    protected String serviceCode;
    @XmlElement(required = true)
    protected String providerCode;
    @XmlElement(required = true)
    protected String sendJndiName;
    @XmlElement(required = true)
    protected String connectionFactoryJndiName;

    /**
     * Gets the value of the serviceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets the value of the serviceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCode(String value) {
        this.serviceCode = value;
    }

    /**
     * Gets the value of the providerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderCode() {
        return providerCode;
    }

    /**
     * Sets the value of the providerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderCode(String value) {
        this.providerCode = value;
    }

    /**
     * Gets the value of the sendJndiName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendJndiName() {
        return sendJndiName;
    }

    /**
     * Sets the value of the sendJndiName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendJndiName(String value) {
        this.sendJndiName = value;
    }

    /**
     * Gets the value of the connectionFactoryJndiName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectionFactoryJndiName() {
        return connectionFactoryJndiName;
    }

    /**
     * Sets the value of the connectionFactoryJndiName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectionFactoryJndiName(String value) {
        this.connectionFactoryJndiName = value;
    }

}
