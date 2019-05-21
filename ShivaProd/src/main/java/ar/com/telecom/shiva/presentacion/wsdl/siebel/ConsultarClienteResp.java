
package ar.com.telecom.shiva.presentacion.wsdl.siebel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.cliente.ClienteCRM;


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
 *         &lt;element name="clientesCRM" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/clienteDicc}clienteCRM" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/all>
 *       &lt;attribute name="crmClienteID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="legadoID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="legadoOfID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="legadoClienteID" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlRootElement(name = "consultarClienteResp", namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
public class ConsultarClienteResp {

    @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
    protected ConsultarClienteResp.ClientesCRM clientesCRM;
    @XmlAttribute(name = "crmClienteID", required = true)
    protected String crmClienteID;
    @XmlAttribute(name = "legadoID")
    protected String legadoID;
    @XmlAttribute(name = "legadoOfID")
    protected String legadoOfID;
    @XmlAttribute(name = "legadoClienteID")
    protected String legadoClienteID;

    /**
     * Gets the value of the clientesCRM property.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarClienteResp.ClientesCRM }
     *     
     */
    public ConsultarClienteResp.ClientesCRM getClientesCRM() {
        return clientesCRM;
    }

    /**
     * Sets the value of the clientesCRM property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarClienteResp.ClientesCRM }
     *     
     */
    public void setClientesCRM(ConsultarClienteResp.ClientesCRM value) {
        this.clientesCRM = value;
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
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/clienteDicc}clienteCRM" maxOccurs="unbounded" minOccurs="0"/>
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
        "clienteCRM"
    })
    public static class ClientesCRM {

        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/clienteDicc")
        protected List<ClienteCRM> clienteCRM;

        /**
         * Gets the value of the clienteCRM property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the clienteCRM property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getClienteCRM().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ClienteCRM }
         * 
         * 
         */
        public List<ClienteCRM> getClienteCRM() {
            if (clienteCRM == null) {
                clienteCRM = new ArrayList<ClienteCRM>();
            }
            return this.clienteCRM;
        }

    }

}
