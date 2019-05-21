
package ar.com.telecom.shiva.presentacion.wsdl.siebel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="legados">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="legado" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="legadoID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="legadoOfID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="legadoClienteID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
@XmlRootElement(name = "oficinasComercialesDeUnClienteResp", namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
public class OficinasComercialesDeUnClienteResp {

    @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage", required = true)
    protected OficinasComercialesDeUnClienteResp.Legados legados;
    @XmlAttribute(name = "crmClienteID", required = true)
    protected String crmClienteID;

    /**
     * Gets the value of the legados property.
     * 
     * @return
     *     possible object is
     *     {@link OficinasComercialesDeUnClienteResp.Legados }
     *     
     */
    public OficinasComercialesDeUnClienteResp.Legados getLegados() {
        return legados;
    }

    /**
     * Sets the value of the legados property.
     * 
     * @param value
     *     allowed object is
     *     {@link OficinasComercialesDeUnClienteResp.Legados }
     *     
     */
    public void setLegados(OficinasComercialesDeUnClienteResp.Legados value) {
        this.legados = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="legado" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="legadoID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="legadoOfID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="legadoClienteID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "legado"
    })
    public static class Legados {

        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected List<OficinasComercialesDeUnClienteResp.Legados.Legado> legado;

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
         * {@link OficinasComercialesDeUnClienteResp.Legados.Legado }
         * 
         * 
         */
        public List<OficinasComercialesDeUnClienteResp.Legados.Legado> getLegado() {
            if (legado == null) {
                legado = new ArrayList<OficinasComercialesDeUnClienteResp.Legados.Legado>();
            }
            return this.legado;
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
        public static class Legado {

            @XmlAttribute(name = "legadoID", required = true)
            protected String legadoID;
            @XmlAttribute(name = "legadoOfID", required = true)
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

}
