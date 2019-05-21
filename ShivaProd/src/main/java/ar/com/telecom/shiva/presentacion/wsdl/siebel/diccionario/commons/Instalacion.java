
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
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}tipo"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}modelo"/>
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
    "tipo",
    "modelo",
    "tecnologia",
    "topologia"
})
@XmlRootElement(name = "instalacion")
public class Instalacion {

    @XmlElement(required = true)
    protected String tipo;
    @XmlElement(required = true)
    protected String modelo;
    @XmlElement(required = true)
    protected String tecnologia;
    protected String topologia;

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the modelo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Sets the value of the modelo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelo(String value) {
        this.modelo = value;
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
