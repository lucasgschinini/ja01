
package ar.com.telecom.shiva.presentacion.wsdl.calipso.consultaAcuerdo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoProceso complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoProceso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResultadoProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "ResultadoProceso", propOrder = {
    "resultadoProceso",
    "codigoerror",
    "descripcionerror"
})
public class ResultadoProceso {

    @XmlElement(name = "ResultadoProceso", required = true)
    protected String resultadoProceso;
    @XmlElement(required = true)
    protected String codigoerror;
    @XmlElement(required = true)
    protected String descripcionerror;

    /**
     * Gets the value of the resultadoProceso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultadoProceso() {
        return resultadoProceso;
    }

    /**
     * Sets the value of the resultadoProceso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultadoProceso(String value) {
        this.resultadoProceso = value;
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
