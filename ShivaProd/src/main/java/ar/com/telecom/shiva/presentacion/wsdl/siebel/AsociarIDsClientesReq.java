
package ar.com.telecom.shiva.presentacion.wsdl.siebel;

import java.util.ArrayList;
import java.util.List;
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
 *       &lt;choice>
 *         &lt;element name="crm" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="crmClienteID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="legadoID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="legadoOfID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="legado" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="legadoID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="legadoOfID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="legadoClienteID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *       &lt;attribute name="literal" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "crm",
    "legado"
})
@XmlRootElement(name = "asociarIDsClientesReq")
public class AsociarIDsClientesReq {

    protected List<AsociarIDsClientesReq.Crm> crm;
    protected List<AsociarIDsClientesReq.Legado> legado;
    @XmlAttribute(name = "literal")
    protected Boolean literal;

    /**
     * Gets the value of the crm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AsociarIDsClientesReq.Crm }
     * 
     * 
     */
    public List<AsociarIDsClientesReq.Crm> getCrm() {
        if (crm == null) {
            crm = new ArrayList<AsociarIDsClientesReq.Crm>();
        }
        return this.crm;
    }

    /**
     * Gets the value of the legado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the legado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLegado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AsociarIDsClientesReq.Legado }
     * 
     * 
     */
    public List<AsociarIDsClientesReq.Legado> getLegado() {
        if (legado == null) {
            legado = new ArrayList<AsociarIDsClientesReq.Legado>();
        }
        return this.legado;
    }

    /**
     * Gets the value of the literal property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isLiteral() {
        if (literal == null) {
            return false;
        } else {
            return literal;
        }
    }

    /**
     * Sets the value of the literal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLiteral(Boolean value) {
        this.literal = value;
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
     *       &lt;attribute name="crmClienteID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="legadoID" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="legadoOfID" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Crm {

        @XmlAttribute(name = "crmClienteID", required = true)
        protected String crmClienteID;
        @XmlAttribute(name = "legadoID")
        protected String legadoID;
        @XmlAttribute(name = "legadoOfID")
        protected String legadoOfID;

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
     *       &lt;attribute name="legadoID" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="legadoOfID" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    public static class Legado {

        @XmlAttribute(name = "legadoID")
        protected String legadoID;
        @XmlAttribute(name = "legadoOfID")
        protected String legadoOfID;
        @XmlAttribute(name = "legadoClienteID", required = true)
        protected String legadoClienteID;

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

}
