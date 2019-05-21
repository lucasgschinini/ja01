
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
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}nombre" minOccurs="0"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}apellido" minOccurs="0"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}tratamiento" minOccurs="0"/>
 *         &lt;element name="horario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "nombre",
    "apellido",
    "tratamiento",
    "horario",
    "telefono",
    "mail"
})
@XmlRootElement(name = "contacto")
public class Contacto {

    protected String nombre;
    protected String apellido;
    protected String tratamiento;
    protected String horario;
    protected String telefono;
    protected String mail;

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the apellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets the value of the apellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido(String value) {
        this.apellido = value;
    }

    /**
     * Gets the value of the tratamiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTratamiento() {
        return tratamiento;
    }

    /**
     * Sets the value of the tratamiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTratamiento(String value) {
        this.tratamiento = value;
    }

    /**
     * Gets the value of the horario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorario() {
        return horario;
    }

    /**
     * Sets the value of the horario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorario(String value) {
        this.horario = value;
    }

    /**
     * Gets the value of the telefono property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the value of the telefono property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono(String value) {
        this.telefono = value;
    }

    /**
     * Gets the value of the mail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets the value of the mail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMail(String value) {
        this.mail = value;
    }

}
