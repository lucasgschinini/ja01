
package ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for resultadoservicio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="resultadoservicio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoerror" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descripcionerror" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultadoservicio", propOrder = {
    "resultado",
    "codigoerror",
    "descripcionerror"
})
public class Resultadoservicio {

    @XmlElement(required = true)
    protected String resultado;
    @XmlElement(required = true)
    protected String codigoerror;
    @XmlElement(required = true)
    protected String descripcionerror;

    /**
     * Gets the value of the resultado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * Sets the value of the resultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultado(String value) {
        this.resultado = value;
    }

    /**
     * Gets the value of the codigoerror property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoerror() {
        return codigoerror;
    }

    /**
     * Sets the value of the codigoerror property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoerror(String value) {
        this.codigoerror = value;
    }

    /**
     * Gets the value of the descripcionerror property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionerror() {
        return descripcionerror;
    }

    /**
     * Sets the value of the descripcionerror property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionerror(String value) {
        this.descripcionerror = value;
    }

}
