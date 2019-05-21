
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
    "legado"
})
@XmlRootElement(name = "oficinasComercialesDeUnClienteReq")
public class OficinasComercialesDeUnClienteReq {

    protected OficinasComercialesDeUnClienteReq.Crm crm;
    protected OficinasComercialesDeUnClienteReq.Legado legado;

    /**
     * Gets the value of the crm property.
     * 
     * @return
     *     possible object is
     *     {@link OficinasComercialesDeUnClienteReq.Crm }
     *     
     */
    public OficinasComercialesDeUnClienteReq.Crm getCrm() {
        return crm;
    }

    /**
     * Sets the value of the crm property.
     * 
     * @param value
     *     allowed object is
     *     {@link OficinasComercialesDeUnClienteReq.Crm }
     *     
     */
    public void setCrm(OficinasComercialesDeUnClienteReq.Crm value) {
        this.crm = value;
    }

    /**
     * Gets the value of the legado property.
     * 
     * @return
     *     possible object is
     *     {@link OficinasComercialesDeUnClienteReq.Legado }
     *     
     */
    public OficinasComercialesDeUnClienteReq.Legado getLegado() {
        return legado;
    }

    /**
     * Sets the value of the legado property.
     * 
     * @param value
     *     allowed object is
     *     {@link OficinasComercialesDeUnClienteReq.Legado }
     *     
     */
    public void setLegado(OficinasComercialesDeUnClienteReq.Legado value) {
        this.legado = value;
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
