
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
 *       &lt;sequence>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}serviceCode"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}consumerCode"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}providerCode"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}messageID"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}conversationID"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}operation"/>
 *         &lt;element name="dataMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}description" minOccurs="0"/>
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
    "serviceCode",
    "consumerCode",
    "providerCode",
    "messageID",
    "conversationID",
    "operation",
    "dataMessage",
    "description"
})
@XmlRootElement(name = "transaction")
public class Transaction {

    @XmlElement(required = true)
    protected String serviceCode;
    @XmlElement(required = true)
    protected String consumerCode;
    @XmlElement(required = true)
    protected String providerCode;
    @XmlElement(required = true)
    protected String messageID;
    @XmlElement(required = true)
    protected String conversationID;
    @XmlElement(required = true)
    protected String operation;
    protected String dataMessage;
    protected String description;

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
     * Gets the value of the consumerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerCode() {
        return consumerCode;
    }

    /**
     * Sets the value of the consumerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerCode(String value) {
        this.consumerCode = value;
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
     * Gets the value of the messageID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageID() {
        return messageID;
    }

    /**
     * Sets the value of the messageID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageID(String value) {
        this.messageID = value;
    }

    /**
     * Gets the value of the conversationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConversationID() {
        return conversationID;
    }

    /**
     * Sets the value of the conversationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConversationID(String value) {
        this.conversationID = value;
    }

    /**
     * Gets the value of the operation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Sets the value of the operation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation(String value) {
        this.operation = value;
    }

    /**
     * Gets the value of the dataMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataMessage() {
        return dataMessage;
    }

    /**
     * Sets the value of the dataMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataMessage(String value) {
        this.dataMessage = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
