
package ar.com.telecom.shiva.presentacion.wsdl.siebel;

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
 *         &lt;element name="error">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}errorCode"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}descripcion" minOccurs="0"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ok">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}descripcion" minOccurs="0"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
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
@XmlType(name = "", propOrder = {
    "error",
    "ok"
})
@XmlRootElement(name = "resultadoActualizacionClienteReq")
public class ResultadoActualizacionClienteReq {

    protected ResultadoActualizacionClienteReq.Error error;
    protected ResultadoActualizacionClienteReq.Ok ok;
    @XmlAttribute(name = "legadoID", required = true)
    protected String legadoID;
    @XmlAttribute(name = "legadoOfID", required = true)
    protected String legadoOfID;
    @XmlAttribute(name = "legadoClienteID", required = true)
    protected String legadoClienteID;

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoActualizacionClienteReq.Error }
     *     
     */
    public ResultadoActualizacionClienteReq.Error getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoActualizacionClienteReq.Error }
     *     
     */
    public void setError(ResultadoActualizacionClienteReq.Error value) {
        this.error = value;
    }

    /**
     * Gets the value of the ok property.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoActualizacionClienteReq.Ok }
     *     
     */
    public ResultadoActualizacionClienteReq.Ok getOk() {
        return ok;
    }

    /**
     * Sets the value of the ok property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoActualizacionClienteReq.Ok }
     *     
     */
    public void setOk(ResultadoActualizacionClienteReq.Ok value) {
        this.ok = value;
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
     *       &lt;all>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}errorCode"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}descripcion" minOccurs="0"/>
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
    public static class Error {

        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", required = true)
        protected String errorCode;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common")
        protected String descripcion;

        /**
         * Gets the value of the errorCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getErrorCode() {
            return errorCode;
        }

        /**
         * Sets the value of the errorCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setErrorCode(String value) {
            this.errorCode = value;
        }

        /**
         * Gets the value of the descripcion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcion() {
            return descripcion;
        }

        /**
         * Sets the value of the descripcion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcion(String value) {
            this.descripcion = value;
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
     *       &lt;all>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}descripcion" minOccurs="0"/>
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
    public static class Ok {

        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common")
        protected String descripcion;

        /**
         * Gets the value of the descripcion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcion() {
            return descripcion;
        }

        /**
         * Sets the value of the descripcion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcion(String value) {
            this.descripcion = value;
        }

    }

}
