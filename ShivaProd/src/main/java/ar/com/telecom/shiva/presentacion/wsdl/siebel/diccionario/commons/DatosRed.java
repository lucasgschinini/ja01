
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
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}vinculos"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}equipos"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}ebos"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}central"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}zona"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}centralNoAtendida"/>
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
    "vinculos",
    "equipos",
    "ebos",
    "central",
    "zona",
    "centralNoAtendida"
})
@XmlRootElement(name = "datosRed")
public class DatosRed {

    @XmlElement(required = true)
    protected Vinculos vinculos;
    @XmlElement(required = true)
    protected Equipos equipos;
    @XmlElement(required = true)
    protected String ebos;
    @XmlElement(required = true)
    protected String central;
    @XmlElement(required = true)
    protected String zona;
    @XmlElement(required = true)
    protected String centralNoAtendida;

    /**
     * Gets the value of the vinculos property.
     * 
     * @return
     *     possible object is
     *     {@link Vinculos }
     *     
     */
    public Vinculos getVinculos() {
        return vinculos;
    }

    /**
     * Sets the value of the vinculos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vinculos }
     *     
     */
    public void setVinculos(Vinculos value) {
        this.vinculos = value;
    }

    /**
     * Gets the value of the equipos property.
     * 
     * @return
     *     possible object is
     *     {@link Equipos }
     *     
     */
    public Equipos getEquipos() {
        return equipos;
    }

    /**
     * Sets the value of the equipos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Equipos }
     *     
     */
    public void setEquipos(Equipos value) {
        this.equipos = value;
    }

    /**
     * Gets the value of the ebos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEbos() {
        return ebos;
    }

    /**
     * Sets the value of the ebos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEbos(String value) {
        this.ebos = value;
    }

    /**
     * Gets the value of the central property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentral() {
        return central;
    }

    /**
     * Sets the value of the central property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentral(String value) {
        this.central = value;
    }

    /**
     * Gets the value of the zona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZona() {
        return zona;
    }

    /**
     * Sets the value of the zona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZona(String value) {
        this.zona = value;
    }

    /**
     * Gets the value of the centralNoAtendida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentralNoAtendida() {
        return centralNoAtendida;
    }

    /**
     * Sets the value of the centralNoAtendida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentralNoAtendida(String value) {
        this.centralNoAtendida = value;
    }

}
