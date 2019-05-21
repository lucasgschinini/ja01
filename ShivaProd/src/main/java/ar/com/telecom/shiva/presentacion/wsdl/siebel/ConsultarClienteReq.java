
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
 *                 &lt;attribute name="legadoClienteID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="crmHoldingID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="crmAgenciaNegocio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="crmClienteCUIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/choice>
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
    "legado",
    "crmHoldingID",
    "crmAgenciaNegocio",
    "crmClienteCUIT"
})
@XmlRootElement(name = "consultarClienteReq")
public class ConsultarClienteReq {

    protected ConsultarClienteReq.Crm crm;
    protected ConsultarClienteReq.Legado legado;
    protected String crmHoldingID;
    protected String crmAgenciaNegocio;
    protected String crmClienteCUIT;

    /**
     * Gets the value of the crm property.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarClienteReq.Crm }
     *     
     */
    public ConsultarClienteReq.Crm getCrm() {
        return crm;
    }

    /**
     * Sets the value of the crm property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarClienteReq.Crm }
     *     
     */
    public void setCrm(ConsultarClienteReq.Crm value) {
        this.crm = value;
    }

    /**
     * Gets the value of the legado property.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarClienteReq.Legado }
     *     
     */
    public ConsultarClienteReq.Legado getLegado() {
        return legado;
    }

    /**
     * Sets the value of the legado property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarClienteReq.Legado }
     *     
     */
    public void setLegado(ConsultarClienteReq.Legado value) {
        this.legado = value;
    }

    /**
     * Gets the value of the crmHoldingID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmHoldingID() {
        return crmHoldingID;
    }

    /**
     * Sets the value of the crmHoldingID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmHoldingID(String value) {
        this.crmHoldingID = value;
    }

    /**
     * Gets the value of the crmAgenciaNegocio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmAgenciaNegocio() {
        return crmAgenciaNegocio;
    }

    /**
     * Sets the value of the crmAgenciaNegocio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmAgenciaNegocio(String value) {
        this.crmAgenciaNegocio = value;
    }

    /**
     * Gets the value of the crmClienteCUIT property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmClienteCUIT() {
        return crmClienteCUIT;
    }

    /**
     * Sets the value of the crmClienteCUIT property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmClienteCUIT(String value) {
        this.crmClienteCUIT = value;
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

        @XmlAttribute(name = "legadoClienteID", required = true)
        protected String legadoClienteID;

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
