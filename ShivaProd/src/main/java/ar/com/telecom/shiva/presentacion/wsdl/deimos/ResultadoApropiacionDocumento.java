
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoApropiacionDocumento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoApropiacionDocumento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResultadoApropiacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescripcionError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="getDetalleError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoApropiacionDocumento", propOrder = {
    "resultadoApropiacion",
    "codigoError",
    "descripcionError",
    "getDetalleError"
})
public class ResultadoApropiacionDocumento {

    @XmlElement(name = "ResultadoApropiacion", required = true)
    protected String resultadoApropiacion;
    @XmlElement(name = "CodigoError", nillable = true)
    protected String codigoError;
    @XmlElement(name = "DescripcionError", nillable = true)
    protected String descripcionError;
    protected String getDetalleError;

    /**
     * Gets the value of the resultadoApropiacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultadoApropiacion() {
        return resultadoApropiacion;
    }

    /**
     * Sets the value of the resultadoApropiacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultadoApropiacion(String value) {
        this.resultadoApropiacion = value;
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
     * Gets the value of the getDetalleError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetDetalleError() {
        return getDetalleError;
    }

    /**
     * Sets the value of the getDetalleError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetDetalleError(String value) {
        this.getDetalleError = value;
    }

}
