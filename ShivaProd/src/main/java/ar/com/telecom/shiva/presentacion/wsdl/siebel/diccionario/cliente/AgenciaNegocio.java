
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.cliente;

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
 *         &lt;element name="nombreAgenciaNegocio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroAgenciaNegocio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cabeceraFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoAgenciaGestionaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionAgenciaGestionaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoCategoriaCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desccripcionCategoriaCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoCabecera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionCabecera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoCategClienteCompl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionCategClienteCompl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regionAgenciaNegocio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "nombreAgenciaNegocio",
    "numeroAgenciaNegocio",
    "cabeceraFlag",
    "codigoAgenciaGestionaria",
    "descripcionAgenciaGestionaria",
    "codigoCategoriaCliente",
    "desccripcionCategoriaCliente",
    "codigoCabecera",
    "descripcionCabecera",
    "codigoCategClienteCompl",
    "descripcionCategClienteCompl",
    "regionAgenciaNegocio"
})
@XmlRootElement(name = "agenciaNegocio")
public class AgenciaNegocio {

    protected String nombreAgenciaNegocio;
    protected String numeroAgenciaNegocio;
    protected String cabeceraFlag;
    protected String codigoAgenciaGestionaria;
    protected String descripcionAgenciaGestionaria;
    protected String codigoCategoriaCliente;
    protected String desccripcionCategoriaCliente;
    protected String codigoCabecera;
    protected String descripcionCabecera;
    protected String codigoCategClienteCompl;
    protected String descripcionCategClienteCompl;
    protected String regionAgenciaNegocio;

    /**
     * Gets the value of the nombreAgenciaNegocio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreAgenciaNegocio() {
        return nombreAgenciaNegocio;
    }

    /**
     * Sets the value of the nombreAgenciaNegocio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreAgenciaNegocio(String value) {
        this.nombreAgenciaNegocio = value;
    }

    /**
     * Gets the value of the numeroAgenciaNegocio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroAgenciaNegocio() {
        return numeroAgenciaNegocio;
    }

    /**
     * Sets the value of the numeroAgenciaNegocio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroAgenciaNegocio(String value) {
        this.numeroAgenciaNegocio = value;
    }

    /**
     * Gets the value of the cabeceraFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCabeceraFlag() {
        return cabeceraFlag;
    }

    /**
     * Sets the value of the cabeceraFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCabeceraFlag(String value) {
        this.cabeceraFlag = value;
    }

    /**
     * Gets the value of the codigoAgenciaGestionaria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoAgenciaGestionaria() {
        return codigoAgenciaGestionaria;
    }

    /**
     * Sets the value of the codigoAgenciaGestionaria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoAgenciaGestionaria(String value) {
        this.codigoAgenciaGestionaria = value;
    }

    /**
     * Gets the value of the descripcionAgenciaGestionaria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionAgenciaGestionaria() {
        return descripcionAgenciaGestionaria;
    }

    /**
     * Sets the value of the descripcionAgenciaGestionaria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionAgenciaGestionaria(String value) {
        this.descripcionAgenciaGestionaria = value;
    }

    /**
     * Gets the value of the codigoCategoriaCliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCategoriaCliente() {
        return codigoCategoriaCliente;
    }

    /**
     * Sets the value of the codigoCategoriaCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCategoriaCliente(String value) {
        this.codigoCategoriaCliente = value;
    }

    /**
     * Gets the value of the desccripcionCategoriaCliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesccripcionCategoriaCliente() {
        return desccripcionCategoriaCliente;
    }

    /**
     * Sets the value of the desccripcionCategoriaCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesccripcionCategoriaCliente(String value) {
        this.desccripcionCategoriaCliente = value;
    }

    /**
     * Gets the value of the codigoCabecera property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCabecera() {
        return codigoCabecera;
    }

    /**
     * Sets the value of the codigoCabecera property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCabecera(String value) {
        this.codigoCabecera = value;
    }

    /**
     * Gets the value of the descripcionCabecera property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionCabecera() {
        return descripcionCabecera;
    }

    /**
     * Sets the value of the descripcionCabecera property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionCabecera(String value) {
        this.descripcionCabecera = value;
    }

    /**
     * Gets the value of the codigoCategClienteCompl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCategClienteCompl() {
        return codigoCategClienteCompl;
    }

    /**
     * Sets the value of the codigoCategClienteCompl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCategClienteCompl(String value) {
        this.codigoCategClienteCompl = value;
    }

    /**
     * Gets the value of the descripcionCategClienteCompl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionCategClienteCompl() {
        return descripcionCategClienteCompl;
    }

    /**
     * Sets the value of the descripcionCategClienteCompl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionCategClienteCompl(String value) {
        this.descripcionCategClienteCompl = value;
    }

    /**
     * Gets the value of the regionAgenciaNegocio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegionAgenciaNegocio() {
        return regionAgenciaNegocio;
    }

    /**
     * Sets the value of the regionAgenciaNegocio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegionAgenciaNegocio(String value) {
        this.regionAgenciaNegocio = value;
    }

}
