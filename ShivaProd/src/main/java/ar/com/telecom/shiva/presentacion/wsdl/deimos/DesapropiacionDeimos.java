
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DesapropiacionDeimos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DesapropiacionDeimos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdOperacionShiva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UsuarioCobrador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DesapropiacionDeimos", propOrder = {
    "idOperacionShiva",
    "usuarioCobrador"
})
public class DesapropiacionDeimos {

    @XmlElement(name = "IdOperacionShiva", required = true)
    protected String idOperacionShiva;
    @XmlElement(name = "UsuarioCobrador", required = true)
    protected String usuarioCobrador;

    /**
     * Gets the value of the idOperacionShiva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdOperacionShiva() {
        return idOperacionShiva;
    }

    /**
     * Sets the value of the idOperacionShiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdOperacionShiva(String value) {
        this.idOperacionShiva = value;
    }

    /**
     * Gets the value of the usuarioCobrador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioCobrador() {
        return usuarioCobrador;
    }

    /**
     * Sets the value of the usuarioCobrador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioCobrador(String value) {
        this.usuarioCobrador = value;
    }

}
