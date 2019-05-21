
package ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for informaciondacota complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="informaciondacota">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UnidadOperativa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SubTipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Holding" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "informaciondacota", propOrder = {
    "unidadOperativa",
    "subTipo",
    "holding"
})
public class Informaciondacota {

    @XmlElement(name = "UnidadOperativa", required = true)
    protected String unidadOperativa;
    @XmlElement(name = "SubTipo", required = true)
    protected String subTipo;
    @XmlElement(name = "Holding", required = true)
    protected String holding;

    /**
     * Gets the value of the unidadOperativa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadOperativa() {
        return unidadOperativa;
    }

    /**
     * Sets the value of the unidadOperativa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadOperativa(String value) {
        this.unidadOperativa = value;
    }

    /**
     * Gets the value of the subTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubTipo() {
        return subTipo;
    }

    /**
     * Sets the value of the subTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubTipo(String value) {
        this.subTipo = value;
    }

    /**
     * Gets the value of the holding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHolding() {
        return holding;
    }

    /**
     * Sets the value of the holding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHolding(String value) {
        this.holding = value;
    }

}
