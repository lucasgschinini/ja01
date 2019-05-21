
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}codigoLocalidad"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}codigoProvincia"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}tecnologia"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}topologia" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "codigoLocalidad",
    "codigoProvincia",
    "tecnologia",
    "topologia"
})
@XmlRootElement(name = "datosParque")
public class DatosParque {

    @XmlElement(required = true)
    protected String codigoLocalidad;
    @XmlElement(required = true)
    protected String codigoProvincia;
    @XmlElement(required = true)
    protected String tecnologia;
    protected String topologia;

    /**
     * Gets the value of the codigoLocalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoLocalidad() {
        return codigoLocalidad;
    }

    /**
     * Sets the value of the codigoLocalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoLocalidad(String value) {
        this.codigoLocalidad = value;
    }

    /**
     * Gets the value of the codigoProvincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoProvincia() {
        return codigoProvincia;
    }

    /**
     * Sets the value of the codigoProvincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoProvincia(String value) {
        this.codigoProvincia = value;
    }

    /**
     * Gets the value of the tecnologia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTecnologia() {
        return tecnologia;
    }

    /**
     * Sets the value of the tecnologia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTecnologia(String value) {
        this.tecnologia = value;
    }

    /**
     * Gets the value of the topologia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopologia() {
        return topologia;
    }

    /**
     * Sets the value of the topologia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopologia(String value) {
        this.topologia = value;
    }

}
