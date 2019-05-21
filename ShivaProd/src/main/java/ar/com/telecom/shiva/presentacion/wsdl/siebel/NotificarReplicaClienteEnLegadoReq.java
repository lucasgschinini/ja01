
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
 *       &lt;attribute name="crmClienteID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="legadoID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="legadoOfID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="legadoClienteID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "notificarReplicaClienteEnLegadoReq")
public class NotificarReplicaClienteEnLegadoReq {

    @XmlAttribute(name = "crmClienteID", required = true)
    protected String crmClienteID;
    @XmlAttribute(name = "legadoID", required = true)
    protected String legadoID;
    @XmlAttribute(name = "legadoOfID", required = true)
    protected String legadoOfID;
    @XmlAttribute(name = "legadoClienteID", required = true)
    protected String legadoClienteID;

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
     * Gets the value of the legadoID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegadoID() {
        return legadoID;
    }

    /**
     * Sets the value of the legadoID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegadoID(String value) {
        this.legadoID = value;
    }

    /**
     * Gets the value of the legadoOfID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegadoOfID() {
        return legadoOfID;
    }

    /**
     * Sets the value of the legadoOfID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegadoOfID(String value) {
        this.legadoOfID = value;
    }

    /**
     * Gets the value of the legadoClienteID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegadoClienteID() {
        return legadoClienteID;
    }

    /**
     * Sets the value of the legadoClienteID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegadoClienteID(String value) {
        this.legadoClienteID = value;
    }

}
