
package ar.com.telecom.shiva.presentacion.wsdl.siebel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.ClienteCRM;


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
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}clienteCRM"/>
 *       &lt;/all>
 *       &lt;attribute name="crmClienteID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlRootElement(name = "novedadAltaClienteReq")
public class NovedadAltaClienteReq {

    @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", required = true)
    protected ClienteCRM clienteCRM;
    @XmlAttribute(name = "crmClienteID", required = true)
    protected String crmClienteID;

    /**
     * Gets the value of the clienteCRM property.
     * 
     * @return
     *     possible object is
     *     {@link ClienteCRM }
     *     
     */
    public ClienteCRM getClienteCRM() {
        return clienteCRM;
    }

    /**
     * Sets the value of the clienteCRM property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClienteCRM }
     *     
     */
    public void setClienteCRM(ClienteCRM value) {
        this.clienteCRM = value;
    }

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

}
