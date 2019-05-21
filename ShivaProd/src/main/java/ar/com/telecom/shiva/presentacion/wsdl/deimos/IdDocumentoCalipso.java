
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdDocumentoCalipso complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdDocumentoCalipso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoComprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClaseComprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalComprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroComprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdDocumentoCalipso", propOrder = {
    "tipoComprobante",
    "claseComprobante",
    "sucursalComprobante",
    "numeroComprobante"
})
public class IdDocumentoCalipso {

    @XmlElement(name = "TipoComprobante", nillable = true)
    protected String tipoComprobante;
    @XmlElement(name = "ClaseComprobante", nillable = true)
    protected String claseComprobante;
    @XmlElement(name = "SucursalComprobante", nillable = true)
    protected String sucursalComprobante;
    @XmlElement(name = "NumeroComprobante", nillable = true)
    protected String numeroComprobante;

    /**
     * Gets the value of the tipoComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoComprobante() {
        return tipoComprobante;
    }

    /**
     * Sets the value of the tipoComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoComprobante(String value) {
        this.tipoComprobante = value;
    }

    /**
     * Gets the value of the claseComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaseComprobante() {
        return claseComprobante;
    }

    /**
     * Sets the value of the claseComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaseComprobante(String value) {
        this.claseComprobante = value;
    }

    /**
     * Gets the value of the sucursalComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSucursalComprobante() {
        return sucursalComprobante;
    }

    /**
     * Sets the value of the sucursalComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSucursalComprobante(String value) {
        this.sucursalComprobante = value;
    }

    /**
     * Gets the value of the numeroComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
     * Sets the value of the numeroComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroComprobante(String value) {
        this.numeroComprobante = value;
    }

}
