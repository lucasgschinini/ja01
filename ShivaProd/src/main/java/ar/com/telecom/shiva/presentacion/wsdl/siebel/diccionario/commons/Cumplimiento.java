
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *       &lt;sequence>
 *         &lt;element name="fechaCX" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="(19|20)\d\d-(0[1-9]|10|11|12)-(0[1-9]|1\d|2\d|30|31)"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="fechaRG" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="(19|20)\d\d-(0[1-9]|10|11|12)-(0[1-9]|1\d|2\d|30|31)"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="fechaLinea" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="(19|20)\d\d-(0[1-9]|10|11|12)-(0[1-9]|1\d|2\d|30|31)"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="fechaCabinaTP" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="(19|20)\d\d-(0[1-9]|10|11|12)-(0[1-9]|1\d|2\d|30|31)"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="fechaISP" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="(19|20)\d\d-(0[1-9]|10|11|12)-(0[1-9]|1\d|2\d|30|31)"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="fechaAparatoTP" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="(19|20)\d\d-(0[1-9]|10|11|12)-(0[1-9]|1\d|2\d|30|31)"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="fechaBaja" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="(19|20)\d\d-(0[1-9]|10|11|12)-(0[1-9]|1\d|2\d|30|31)"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
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
    "fechaCX",
    "fechaRG",
    "fechaLinea",
    "fechaCabinaTP",
    "fechaISP",
    "fechaAparatoTP",
    "fechaBaja"
})
@XmlRootElement(name = "cumplimiento")
public class Cumplimiento {

    protected String fechaCX;
    protected String fechaRG;
    protected String fechaLinea;
    protected String fechaCabinaTP;
    protected String fechaISP;
    protected String fechaAparatoTP;
    protected String fechaBaja;

    /**
     * Gets the value of the fechaCX property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCX() {
        return fechaCX;
    }

    /**
     * Sets the value of the fechaCX property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCX(String value) {
        this.fechaCX = value;
    }

    /**
     * Gets the value of the fechaRG property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaRG() {
        return fechaRG;
    }

    /**
     * Sets the value of the fechaRG property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaRG(String value) {
        this.fechaRG = value;
    }

    /**
     * Gets the value of the fechaLinea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaLinea() {
        return fechaLinea;
    }

    /**
     * Sets the value of the fechaLinea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaLinea(String value) {
        this.fechaLinea = value;
    }

    /**
     * Gets the value of the fechaCabinaTP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCabinaTP() {
        return fechaCabinaTP;
    }

    /**
     * Sets the value of the fechaCabinaTP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCabinaTP(String value) {
        this.fechaCabinaTP = value;
    }

    /**
     * Gets the value of the fechaISP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaISP() {
        return fechaISP;
    }

    /**
     * Sets the value of the fechaISP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaISP(String value) {
        this.fechaISP = value;
    }

    /**
     * Gets the value of the fechaAparatoTP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAparatoTP() {
        return fechaAparatoTP;
    }

    /**
     * Sets the value of the fechaAparatoTP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAparatoTP(String value) {
        this.fechaAparatoTP = value;
    }

    /**
     * Gets the value of the fechaBaja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaBaja() {
        return fechaBaja;
    }

    /**
     * Sets the value of the fechaBaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaBaja(String value) {
        this.fechaBaja = value;
    }

}
