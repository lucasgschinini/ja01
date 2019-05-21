
package ar.com.telecom.shiva.presentacion.wsdl.calipso.consultadeudaycredito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listaresultado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listaresultado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResultadoImputacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "listaresultado", propOrder = {
    "resultadoImputacion",
    "codigoerror",
    "descripcionerror"
})
public class Listaresultado {

    @XmlElement(name = "ResultadoImputacion", required = true)
    protected String resultadoImputacion;
    @XmlElement(required = true)
    protected String codigoerror;
    @XmlElement(required = true)
    protected String descripcionerror;

    /**
     * Gets the value of the resultadoImputacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultadoImputacion() {
        return resultadoImputacion;
    }

    /**
     * Sets the value of the resultadoImputacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultadoImputacion(String value) {
        this.resultadoImputacion = value;
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
