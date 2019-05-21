
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoProcesamiento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoProcesamiento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResultadoImputacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescripcionError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="detalleError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoProcesamiento", propOrder = {
    "resultadoImputacion",
    "codigoError",
    "descripcionError",
    "detalleError"
})
public class ResultadoProcesamiento {

    @XmlElement(name = "ResultadoImputacion", required = true)
    protected String resultadoImputacion;
    @XmlElement(name = "CodigoError", nillable = true)
    protected String codigoError;
    @XmlElement(name = "DescripcionError", nillable = true)
    protected String descripcionError;
    protected String detalleError;

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
     * Gets the value of the codigoError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoError() {
        return codigoError;
    }

    /**
     * Sets the value of the codigoError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoError(String value) {
        this.codigoError = value;
    }

    /**
     * Gets the value of the descripcionError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionError() {
        return descripcionError;
    }

    /**
     * Sets the value of the descripcionError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionError(String value) {
        this.descripcionError = value;
    }

    /**
     * Gets the value of the detalleError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetalleError() {
        return detalleError;
    }

    /**
     * Sets the value of the detalleError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetalleError(String value) {
        this.detalleError = value;
    }

}
