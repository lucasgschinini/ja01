
package ar.com.telecom.shiva.presentacion.wsdl.siebel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="crmClienteID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ack" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "inactivarClienteEnLegadosResp", namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
public class InactivarClienteEnLegadosResp {

    @XmlAttribute(name = "crmClienteID")
    protected String crmClienteID;
    @XmlAttribute(name = "ack", required = true)
    protected boolean ack;

    /**
     * Gets the value of the crmClienteID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmClienteID() {
        return crmClienteID;
    }

    /**
     * Sets the value of the crmClienteID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmClienteID(String value) {
        this.crmClienteID = value;
    }

    /**
     * Gets the value of the ack property.
     * 
     */
    public boolean isAck() {
        return ack;
    }

    /**
     * Sets the value of the ack property.
     * 
     */
    public void setAck(boolean value) {
        this.ack = value;
    }

}
