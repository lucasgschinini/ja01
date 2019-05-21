
package ar.com.telecom.shiva.presentacion.wsdl.calipso.cargos;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for detCargo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="detCargo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acuerdo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importe" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fechaDesde" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaHasta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoMora" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="algoritmoMora" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importeBonificacionIntereses" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="origenCargo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="leyendaFacturaCargo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="leyendaFacturaInteres" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="monedaCargo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detCargo", propOrder = {
    "acuerdo",
    "importe",
    "fechaDesde",
    "fechaHasta",
    "tipoMora",
    "algoritmoMora",
    "importeBonificacionIntereses",
    "origenCargo",
    "leyendaFacturaCargo",
    "leyendaFacturaInteres",
    "monedaCargo"
})
public class DetCargo {

    @XmlElement(required = true)
    protected String acuerdo;
    @XmlElement(required = true)
    protected BigDecimal importe;
    @XmlElement(required = true)
    protected String fechaDesde;
    @XmlElement(required = true)
    protected String fechaHasta;
    @XmlElement(required = true)
    protected String tipoMora;
    @XmlElement(required = true)
    protected String algoritmoMora;
    @XmlElement(required = true)
    protected BigDecimal importeBonificacionIntereses;
    @XmlElement(required = true)
    protected String origenCargo;
    @XmlElement(required = true)
    protected String leyendaFacturaCargo;
    @XmlElement(required = true)
    protected String leyendaFacturaInteres;
    @XmlElement(required = true)
    protected String monedaCargo;

    /**
     * Gets the value of the acuerdo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcuerdo() {
        return acuerdo;
    }

    /**
     * Sets the value of the acuerdo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcuerdo(String value) {
        this.acuerdo = value;
    }

    /**
     * Gets the value of the importe property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporte() {
        return importe;
    }

    /**
     * Sets the value of the importe property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporte(BigDecimal value) {
        this.importe = value;
    }

    /**
     * Gets the value of the fechaDesde property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaDesde() {
        return fechaDesde;
    }

    /**
     * Sets the value of the fechaDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaDesde(String value) {
        this.fechaDesde = value;
    }

    /**
     * Gets the value of the fechaHasta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaHasta() {
        return fechaHasta;
    }

    /**
     * Sets the value of the fechaHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaHasta(String value) {
        this.fechaHasta = value;
    }

    /**
     * Gets the value of the tipoMora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoMora() {
        return tipoMora;
    }

    /**
     * Sets the value of the tipoMora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoMora(String value) {
        this.tipoMora = value;
    }

    /**
     * Gets the value of the algoritmoMora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlgoritmoMora() {
        return algoritmoMora;
    }

    /**
     * Sets the value of the algoritmoMora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgoritmoMora(String value) {
        this.algoritmoMora = value;
    }

    /**
     * Gets the value of the importeBonificacionIntereses property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteBonificacionIntereses() {
        return importeBonificacionIntereses;
    }

    /**
     * Sets the value of the importeBonificacionIntereses property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteBonificacionIntereses(BigDecimal value) {
        this.importeBonificacionIntereses = value;
    }

    /**
     * Gets the value of the origenCargo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigenCargo() {
        return origenCargo;
    }

    /**
     * Sets the value of the origenCargo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigenCargo(String value) {
        this.origenCargo = value;
    }

    /**
     * Gets the value of the leyendaFacturaCargo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeyendaFacturaCargo() {
        return leyendaFacturaCargo;
    }

    /**
     * Sets the value of the leyendaFacturaCargo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeyendaFacturaCargo(String value) {
        this.leyendaFacturaCargo = value;
    }

    /**
     * Gets the value of the leyendaFacturaInteres property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeyendaFacturaInteres() {
        return leyendaFacturaInteres;
    }

    /**
     * Sets the value of the leyendaFacturaInteres property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeyendaFacturaInteres(String value) {
        this.leyendaFacturaInteres = value;
    }

    /**
     * Gets the value of the monedaCargo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaCargo() {
        return monedaCargo;
    }

    /**
     * Sets the value of the monedaCargo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaCargo(String value) {
        this.monedaCargo = value;
    }

}
