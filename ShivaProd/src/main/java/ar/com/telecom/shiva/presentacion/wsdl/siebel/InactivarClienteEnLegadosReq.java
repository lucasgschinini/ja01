
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
 *       &lt;choice>
 *         &lt;element name="legados">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="legado" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="legadoID" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;whiteSpace value="collapse"/>
 *                                 &lt;minLength value="1"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="legadoOfID" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="legadoClienteID" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="crm">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="legadoID" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;whiteSpace value="collapse"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="legadoOfID" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;whiteSpace value="collapse"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
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
    "legados",
    "crm"
})
@XmlRootElement(name = "inactivarClienteEnLegadosReq")
public class InactivarClienteEnLegadosReq {

    protected InactivarClienteEnLegadosReq.Legados legados;
    protected InactivarClienteEnLegadosReq.Crm crm;
    @XmlAttribute(name = "crmClienteID", required = true)
    protected String crmClienteID;

    /**
     * Gets the value of the legados property.
     * 
     * @return
     *     possible object is
     *     {@link InactivarClienteEnLegadosReq.Legados }
     *     
     */
    public InactivarClienteEnLegadosReq.Legados getLegados() {
        return legados;
    }

    /**
     * Sets the value of the legados property.
     * 
     * @param value
     *     allowed object is
     *     {@link InactivarClienteEnLegadosReq.Legados }
     *     
     */
    public void setLegados(InactivarClienteEnLegadosReq.Legados value) {
        this.legados = value;
    }

    /**
     * Gets the value of the crm property.
     * 
     * @return
     *     possible object is
     *     {@link InactivarClienteEnLegadosReq.Crm }
     *     
     */
    public InactivarClienteEnLegadosReq.Crm getCrm() {
        return crm;
    }

    /**
     * Sets the value of the crm property.
     * 
     * @param value
     *     allowed object is
     *     {@link InactivarClienteEnLegadosReq.Crm }
     *     
     */
    public void setCrm(InactivarClienteEnLegadosReq.Crm value) {
        this.crm = value;
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
     *       &lt;attribute name="legadoID" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;whiteSpace value="collapse"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="legadoOfID" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;whiteSpace value="collapse"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
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

        @XmlAttribute(name = "legadoID", required = true)
        protected String legadoID;
        @XmlAttribute(name = "legadoOfID", required = true)
        protected String legadoOfID;

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
     *       &lt;sequence>
     *         &lt;element name="legado" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="legadoID" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;whiteSpace value="collapse"/>
     *                       &lt;minLength value="1"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="legadoOfID" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;minLength value="1"/>
     *                       &lt;whiteSpace value="collapse"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="legadoClienteID" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;minLength value="1"/>
     *                       &lt;whiteSpace value="collapse"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
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

        @XmlElement(required = true)
        protected List<InactivarClienteEnLegadosReq.Legados.Legado> legado;

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
         * {@link InactivarClienteEnLegadosReq.Legados.Legado }
         * 
         * 
         */
        public List<InactivarClienteEnLegadosReq.Legados.Legado> getLegado() {
            if (legado == null) {
                legado = new ArrayList<InactivarClienteEnLegadosReq.Legados.Legado>();
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
         *       &lt;attribute name="legadoID" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;whiteSpace value="collapse"/>
         *             &lt;minLength value="1"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="legadoOfID" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;minLength value="1"/>
         *             &lt;whiteSpace value="collapse"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="legadoClienteID" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;minLength value="1"/>
         *             &lt;whiteSpace value="collapse"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
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
