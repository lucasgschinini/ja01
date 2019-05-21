
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
 *       &lt;choice>
 *         &lt;element name="crm">
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
 *         &lt;element name="legado">
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
@XmlRootElement(name = "asociarIDsClienteReq")
public class AsociarIDsClienteReq {

    protected AsociarIDsClienteReq.Crm crm;
    protected AsociarIDsClienteReq.Legado legado;
    @XmlAttribute(name = "literal")
    protected Boolean literal;

    /**
     * Gets the value of the crm property.
     * 
     * @return
     *     possible object is
     *     {@link AsociarIDsClienteReq.Crm }
     *     
     */
    public AsociarIDsClienteReq.Crm getCrm() {
        return crm;
    }

    /**
     * Sets the value of the crm property.
     * 
     * @param value
     *     allowed object is
     *     {@link AsociarIDsClienteReq.Crm }
     *     
     */
    public void setCrm(AsociarIDsClienteReq.Crm value) {
        this.crm = value;
    }

    /**
     * Gets the value of the legado property.
     * 
     * @return
     *     possible object is
     *     {@link AsociarIDsClienteReq.Legado }
     *     
     */
    public AsociarIDsClienteReq.Legado getLegado() {
        return legado;
    }

    /**
     * Sets the value of the legado property.
     * 
     * @param value
     *     allowed object is
     *     {@link AsociarIDsClienteReq.Legado }
     *     
     */
    public void setLegado(AsociarIDsClienteReq.Legado value) {
        this.legado = value;
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
