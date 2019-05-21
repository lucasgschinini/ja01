
package ar.com.telecom.shiva.presentacion.wsdl.delta;

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
 *         &lt;element name="miembros" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="miembro" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;all>
 *                             &lt;element name="codigoRol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="descripcionRol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="legajo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="nombreApellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/all>
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
 *       &lt;attribute name="legadoClienteID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlRootElement(name = "consultarTeamComercialClienteResp", namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/consultarTeamComercialResponse")
public class ConsultarTeamComercialClienteResp {

    @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/consultarTeamComercialResponse")
    protected ConsultarTeamComercialClienteResp.Miembros miembros;
    @XmlAttribute(name = "legadoClienteID", required = true)
    protected String legadoClienteID;

    /**
     * Gets the value of the miembros property.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarTeamComercialClienteResp.Miembros }
     *     
     */
    public ConsultarTeamComercialClienteResp.Miembros getMiembros() {
        return miembros;
    }

    /**
     * Sets the value of the miembros property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarTeamComercialClienteResp.Miembros }
     *     
     */
    public void setMiembros(ConsultarTeamComercialClienteResp.Miembros value) {
        this.miembros = value;
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
     *         &lt;element name="miembro" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;all>
     *                   &lt;element name="codigoRol" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="descripcionRol" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="legajo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="nombreApellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/all>
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
        "miembro"
    })
    public static class Miembros {

        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/consultarTeamComercialResponse", required = true)
        protected List<ConsultarTeamComercialClienteResp.Miembros.Miembro> miembro;

        /**
         * Gets the value of the miembro property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the miembro property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMiembro().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ConsultarTeamComercialClienteResp.Miembros.Miembro }
         * 
         * 
         */
        public List<ConsultarTeamComercialClienteResp.Miembros.Miembro> getMiembro() {
            if (miembro == null) {
                miembro = new ArrayList<ConsultarTeamComercialClienteResp.Miembros.Miembro>();
            }
            return this.miembro;
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
         *       &lt;all>
         *         &lt;element name="codigoRol" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="descripcionRol" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="legajo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="nombreApellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/all>
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
        public static class Miembro {

            @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/consultarTeamComercialResponse", required = true)
            protected String codigoRol;
            @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/consultarTeamComercialResponse", required = true)
            protected String descripcionRol;
            @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/consultarTeamComercialResponse", required = true)
            protected String legajo;
            @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/consultarTeamComercialResponse", required = true)
            protected String nombreApellido;

            /**
             * Gets the value of the codigoRol property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodigoRol() {
                return codigoRol;
            }

            /**
             * Sets the value of the codigoRol property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodigoRol(String value) {
                this.codigoRol = value;
            }

            /**
             * Gets the value of the descripcionRol property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDescripcionRol() {
                return descripcionRol;
            }

            /**
             * Sets the value of the descripcionRol property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDescripcionRol(String value) {
                this.descripcionRol = value;
            }

            /**
             * Gets the value of the legajo property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLegajo() {
                return legajo;
            }

            /**
             * Sets the value of the legajo property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLegajo(String value) {
                this.legajo = value;
            }

            /**
             * Gets the value of the nombreApellido property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNombreApellido() {
                return nombreApellido;
            }

            /**
             * Sets the value of the nombreApellido property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNombreApellido(String value) {
                this.nombreApellido = value;
            }

        }

    }

}
