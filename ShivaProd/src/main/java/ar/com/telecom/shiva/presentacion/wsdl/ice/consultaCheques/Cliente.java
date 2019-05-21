
package ar.com.telecom.shiva.presentacion.wsdl.ice.consultaCheques;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Cliente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Cliente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idClienteLegado" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="razonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cliente", namespace = "http://beans.ice.teco.com.ar/xsd", propOrder = {
    "idClienteLegado",
    "razonSocial"
})
public class Cliente {

    @XmlElement(nillable = true)
    protected Long idClienteLegado;
    @XmlElement(nillable = true)
    protected String razonSocial;

    /**
     * Gets the value of the idClienteLegado property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdClienteLegado() {
        return idClienteLegado;
    }

    /**
     * Sets the value of the idClienteLegado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdClienteLegado(Long value) {
        this.idClienteLegado = value;
    }

    /**
     * Gets the value of the razonSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Sets the value of the razonSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocial(String value) {
        this.razonSocial = value;
    }

}
