
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
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}identificador"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}categoria"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}nombre" minOccurs="0"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}apellido" minOccurs="0"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}tratamiento" minOccurs="0"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}comentario" minOccurs="0"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}telefono" minOccurs="0"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}condicionIVA" minOccurs="0"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}cuit" minOccurs="0"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}documento" minOccurs="0"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}oficinaGestion" minOccurs="0"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}marcaVIP" minOccurs="0"/>
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
    "identificador",
    "categoria",
    "nombre",
    "apellido",
    "tratamiento",
    "comentario",
    "telefono",
    "condicionIVA",
    "cuit",
    "documento",
    "oficinaGestion",
    "marcaVIP"
})
@XmlRootElement(name = "cliente")
public class Cliente {

    @XmlElement(required = true)
    protected String identificador;
    @XmlElement(required = true)
    protected String categoria;
    protected String nombre;
    protected String apellido;
    protected String tratamiento;
    protected String comentario;
    protected String telefono;
    protected String condicionIVA;
    protected String cuit;
    protected Documento documento;
    protected String oficinaGestion;
    protected String marcaVIP;

    /**
     * Identidicador/Numero del Cliente
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Sets the value of the identificador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificador(String value) {
        this.identificador = value;
    }

    /**
     * Categoria de Cliente
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Sets the value of the categoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoria(String value) {
        this.categoria = value;
    }

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
     * Comentario al Nombre del Cliente
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Sets the value of the comentario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentario(String value) {
        this.comentario = value;
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
     * Gets the value of the condicionIVA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondicionIVA() {
        return condicionIVA;
    }

    /**
     * Sets the value of the condicionIVA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondicionIVA(String value) {
        this.condicionIVA = value;
    }

    /**
     * Gets the value of the cuit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuit() {
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
    public void setCuit(String value) {
        this.cuit = value;
    }

    /**
     * Gets the value of the documento property.
     * 
     * @return
     *     possible object is
     *     {@link Documento }
     *     
     */
    public Documento getDocumento() {
        return documento;
    }

    /**
     * Sets the value of the documento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Documento }
     *     
     */
    public void setDocumento(Documento value) {
        this.documento = value;
    }

    /**
     * Gets the value of the oficinaGestion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOficinaGestion() {
        return oficinaGestion;
    }

    /**
     * Sets the value of the oficinaGestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOficinaGestion(String value) {
        this.oficinaGestion = value;
    }

    /**
     * Gets the value of the marcaVIP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarcaVIP() {
        return marcaVIP;
    }

    /**
     * Sets the value of the marcaVIP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarcaVIP(String value) {
        this.marcaVIP = value;
    }

}
