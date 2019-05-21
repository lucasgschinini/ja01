
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FELoteFACTType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FELoteFACTType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}id_lote_fact"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cant_cbte" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}FEcuit"/>
 *         &lt;element name="facturador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reproceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FELoteFACTType", propOrder = {
    "idLoteFact",
    "nombre",
    "locacion",
    "cantCbte",
    "qty",
    "fEcuit",
    "facturador",
    "reproceso"
})
public class FELoteFACTType {

    @XmlElement(name = "id_lote_fact")
    protected long idLoteFact;
    @XmlElement(required = true)
    protected String nombre;
    @XmlElement(required = true)
    protected String locacion;
    @XmlElement(name = "cant_cbte")
    @XmlSchemaType(name = "unsignedInt")
    protected long cantCbte;
    @XmlSchemaType(name = "unsignedInt")
    protected long qty;
    @XmlElement(name = "FEcuit")
    protected long fEcuit;
    protected String facturador;
    protected String reproceso;

    /**
     * Gets the value of the idLoteFact property.
     * 
     */
    public long getIdLoteFact() {
        return idLoteFact;
    }

    /**
     * Sets the value of the idLoteFact property.
     * 
     */
    public void setIdLoteFact(long value) {
        this.idLoteFact = value;
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
     * Gets the value of the locacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocacion() {
        return locacion;
    }

    /**
     * Sets the value of the locacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocacion(String value) {
        this.locacion = value;
    }

    /**
     * Gets the value of the cantCbte property.
     * 
     */
    public long getCantCbte() {
        return cantCbte;
    }

    /**
     * Sets the value of the cantCbte property.
     * 
     */
    public void setCantCbte(long value) {
        this.cantCbte = value;
    }

    /**
     * Gets the value of the qty property.
     * 
     */
    public long getQty() {
        return qty;
    }

    /**
     * Sets the value of the qty property.
     * 
     */
    public void setQty(long value) {
        this.qty = value;
    }

    /**
     * Gets the value of the fEcuit property.
     * 
     */
    public long getFEcuit() {
        return fEcuit;
    }

    /**
     * Sets the value of the fEcuit property.
     * 
     */
    public void setFEcuit(long value) {
        this.fEcuit = value;
    }

    /**
     * Gets the value of the facturador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFacturador() {
        return facturador;
    }

    /**
     * Sets the value of the facturador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFacturador(String value) {
        this.facturador = value;
    }

    /**
     * Gets the value of the reproceso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReproceso() {
        return reproceso;
    }

    /**
     * Sets the value of the reproceso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReproceso(String value) {
        this.reproceso = value;
    }

}
