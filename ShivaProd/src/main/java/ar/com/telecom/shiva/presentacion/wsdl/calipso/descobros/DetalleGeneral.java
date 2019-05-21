
package ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ar.com.telecom.shiva.base.soa.xmlAdapters.BigIntegerXmlAdapter;


/**
 * <p>Java class for detalleGeneral complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="detalleGeneral">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="importe" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="importeCapital" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="importeImpuestos" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="idDocumento" type="{urn:WSDL}documento"/>
 *         &lt;element name="idDocumentoCuentasCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="vencimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaImputacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importeAplicado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="origenDelDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detalleGeneral", propOrder = {
    "idCobranza",
    "importe",
    "importeCapital",
    "importeImpuestos",
    "idDocumento",
    "idDocumentoCuentasCobranza",
    "vencimiento",
    "fechaImputacion",
    "importeAplicado",
    "origenDelDocumento"
})
public class DetalleGeneral {

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    @XmlSchemaType(name = "integer")
    protected BigInteger idCobranza;
    @XmlElement(required = true)
    protected BigDecimal importe;
    @XmlElement(required = true)
    protected BigDecimal importeCapital;
    @XmlElement(required = true)
    protected BigDecimal importeImpuestos;
    @XmlElement(required = true)
    protected Documento idDocumento;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    @XmlSchemaType(name = "integer")
    protected BigInteger idDocumentoCuentasCobranza;
    @XmlElement(required = true)
    protected String vencimiento;
    @XmlElement(required = true)
    protected String fechaImputacion;
    @XmlElement(required = true)
    protected BigDecimal importeAplicado;
    @XmlElement(required = true)
    protected String origenDelDocumento;

    /**
     * Gets the value of the idCobranza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigInteger getIdCobranza() {
        return idCobranza;
    }

    /**
     * Sets the value of the idCobranza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCobranza(BigInteger value) {
        this.idCobranza = value;
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
     * Gets the value of the importeCapital property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteCapital() {
        return importeCapital;
    }

    /**
     * Sets the value of the importeCapital property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteCapital(BigDecimal value) {
        this.importeCapital = value;
    }

    /**
     * Gets the value of the importeImpuestos property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteImpuestos() {
        return importeImpuestos;
    }

    /**
     * Sets the value of the importeImpuestos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteImpuestos(BigDecimal value) {
        this.importeImpuestos = value;
    }

    /**
     * Gets the value of the idDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link Documento }
     *     
     */
    public Documento getIdDocumento() {
        return idDocumento;
    }

    /**
     * Sets the value of the idDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Documento }
     *     
     */
    public void setIdDocumento(Documento value) {
        this.idDocumento = value;
    }

    /**
     * Gets the value of the idDocumentoCuentasCobranza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigInteger getIdDocumentoCuentasCobranza() {
        return idDocumentoCuentasCobranza;
    }

    /**
     * Sets the value of the idDocumentoCuentasCobranza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDocumentoCuentasCobranza(BigInteger value) {
        this.idDocumentoCuentasCobranza = value;
    }

    /**
     * Gets the value of the vencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVencimiento() {
        return vencimiento;
    }

    /**
     * Sets the value of the vencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVencimiento(String value) {
        this.vencimiento = value;
    }

    /**
     * Gets the value of the fechaImputacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaImputacion() {
        return fechaImputacion;
    }

    /**
     * Sets the value of the fechaImputacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaImputacion(String value) {
        this.fechaImputacion = value;
    }

    /**
     * Gets the value of the importeAplicado property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteAplicado() {
        return importeAplicado;
    }

    /**
     * Sets the value of the importeAplicado property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteAplicado(BigDecimal value) {
        this.importeAplicado = value;
    }

    /**
     * Gets the value of the origenDelDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigenDelDocumento() {
        return origenDelDocumento;
    }

    /**
     * Sets the value of the origenDelDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigenDelDocumento(String value) {
        this.origenDelDocumento = value;
    }

}
