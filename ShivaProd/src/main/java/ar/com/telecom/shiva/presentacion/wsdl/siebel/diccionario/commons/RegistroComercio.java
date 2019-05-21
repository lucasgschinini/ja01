
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="tomo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="libro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="folio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "tomo",
    "libro",
    "folio"
})
@XmlRootElement(name = "registroComercio")
public class RegistroComercio {

    protected String tomo;
    protected String libro;
    protected String folio;

    /**
     * Gets the value of the tomo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTomo() {
        return tomo;
    }

    /**
     * Sets the value of the tomo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTomo(String value) {
        this.tomo = value;
    }

    /**
     * Gets the value of the libro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLibro() {
        return libro;
    }

    /**
     * Sets the value of the libro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLibro(String value) {
        this.libro = value;
    }

    /**
     * Gets the value of the folio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Sets the value of the folio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolio(String value) {
        this.folio = value;
    }

}
