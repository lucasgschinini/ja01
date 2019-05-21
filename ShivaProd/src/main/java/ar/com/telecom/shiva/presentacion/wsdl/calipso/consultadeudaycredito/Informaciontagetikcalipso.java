
package ar.com.telecom.shiva.presentacion.wsdl.calipso.consultadeudaycredito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for informaciontagetikcalipso complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="informaciontagetikcalipso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RazonSocialCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TipoCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CUIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "informaciontagetikcalipso", propOrder = {
    "razonSocialCliente",
    "tipoCliente",
    "cuit"
})
public class Informaciontagetikcalipso {

    @XmlElement(name = "RazonSocialCliente", required = true)
    protected String razonSocialCliente;
    @XmlElement(name = "TipoCliente", required = true)
    protected String tipoCliente;
    @XmlElement(name = "CUIT", required = true)
    protected String cuit;

    /**
     * Gets the value of the razonSocialCliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocialCliente() {
        return razonSocialCliente;
    }

    /**
     * Sets the value of the razonSocialCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocialCliente(String value) {
        this.razonSocialCliente = value;
    }

    /**
     * Gets the value of the tipoCliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCliente() {
        return tipoCliente;
    }

    /**
     * Sets the value of the tipoCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCliente(String value) {
        this.tipoCliente = value;
    }

    /**
     * Gets the value of the cuit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUIT() {
        return cuit;
    }

    /**
     * Sets the value of the cuit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUIT(String value) {
        this.cuit = value;
    }

}
