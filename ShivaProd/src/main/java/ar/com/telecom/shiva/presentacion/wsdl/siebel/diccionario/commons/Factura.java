
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="cidn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoCuenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoAcuerdo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoFacturasReq" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="claseFactura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroFactura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaEmision" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaFacturacion" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="codigoEstadoAcuerdo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoEstadoFactura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descEstadoFactura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaPrimerVenc" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaVencOriginal" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaSegundoVenc" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaCobro" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="importePrimerVenc" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="importeSegundoVenc" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="numeroRefFactura" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="numeroRefFacturaAsociada" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoMarcaReclamo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descMarcaReclamo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importeCobrado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fechaAcreditacion" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaCambioEstadoFactura" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="puntoDeCobro" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descFormaDePago" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="existeHistTransferencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="existeTransferenciaTerceros" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importeDeTerceros" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="importePagosParciales" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="codigoTipoContrib" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descTipoContrib" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "factura")
public class Factura {

    @XmlElement(required = true)
    protected String cidn;
    @XmlElement(required = true)
    protected String codigoCuenta;
    @XmlElement(required = true)
    protected String codigoAcuerdo;
    @XmlElement(required = true)
    protected String codigoFacturasReq;
    @XmlElement(required = true)
    protected String tipoDocumento;
    @XmlElement(required = true)
    protected String claseFactura;
    @XmlElement(required = true)
    protected String numeroFactura;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaEmision;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaFacturacion;
    @XmlElement(required = true)
    protected String codigoEstadoAcuerdo;
    @XmlElement(required = true)
    protected String codigoEstadoFactura;
    @XmlElement(required = true)
    protected String descEstadoFactura;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaPrimerVenc;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaVencOriginal;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaSegundoVenc;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaCobro;
    @XmlElement(required = true)
    protected BigDecimal importePrimerVenc;
    @XmlElement(required = true)
    protected BigDecimal importeSegundoVenc;
    @XmlElement(required = true)
    protected Object numeroRefFactura;
    @XmlElement(required = true)
    protected String numeroRefFacturaAsociada;
    @XmlElement(required = true)
    protected String codigoMarcaReclamo;
    @XmlElement(required = true)
    protected String descMarcaReclamo;
    @XmlElement(required = true)
    protected BigDecimal importeCobrado;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaAcreditacion;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaCambioEstadoFactura;
    @XmlElement(required = true)
    protected String puntoDeCobro;
    @XmlElement(required = true)
    protected String descFormaDePago;
    @XmlElement(required = true)
    protected String existeHistTransferencia;
    @XmlElement(required = true)
    protected String existeTransferenciaTerceros;
    @XmlElement(required = true)
    protected BigDecimal importeDeTerceros;
    @XmlElement(required = true)
    protected BigDecimal importePagosParciales;
    @XmlElement(required = true)
    protected String codigoTipoContrib;
    @XmlElement(required = true)
    protected String descTipoContrib;

    /**
     * Gets the value of the cidn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCidn() {
        return cidn;
    }

    /**
     * Sets the value of the cidn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCidn(String value) {
        this.cidn = value;
    }

    /**
     * Gets the value of the codigoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    /**
     * Sets the value of the codigoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCuenta(String value) {
        this.codigoCuenta = value;
    }

    /**
     * Gets the value of the codigoAcuerdo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoAcuerdo() {
        return codigoAcuerdo;
    }

    /**
     * Sets the value of the codigoAcuerdo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoAcuerdo(String value) {
        this.codigoAcuerdo = value;
    }

    /**
     * Gets the value of the codigoFacturasReq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoFacturasReq() {
        return codigoFacturasReq;
    }

    /**
     * Sets the value of the codigoFacturasReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoFacturasReq(String value) {
        this.codigoFacturasReq = value;
    }

    /**
     * Gets the value of the tipoDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Sets the value of the tipoDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocumento(String value) {
        this.tipoDocumento = value;
    }

    /**
     * Gets the value of the claseFactura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaseFactura() {
        return claseFactura;
    }

    /**
     * Sets the value of the claseFactura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaseFactura(String value) {
        this.claseFactura = value;
    }

    /**
     * Gets the value of the numeroFactura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * Sets the value of the numeroFactura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroFactura(String value) {
        this.numeroFactura = value;
    }

    /**
     * Gets the value of the fechaEmision property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Sets the value of the fechaEmision property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEmision(XMLGregorianCalendar value) {
        this.fechaEmision = value;
    }

    /**
     * Gets the value of the fechaFacturacion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFacturacion() {
        return fechaFacturacion;
    }

    /**
     * Sets the value of the fechaFacturacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFacturacion(XMLGregorianCalendar value) {
        this.fechaFacturacion = value;
    }

    /**
     * Gets the value of the codigoEstadoAcuerdo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEstadoAcuerdo() {
        return codigoEstadoAcuerdo;
    }

    /**
     * Sets the value of the codigoEstadoAcuerdo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEstadoAcuerdo(String value) {
        this.codigoEstadoAcuerdo = value;
    }

    /**
     * Gets the value of the codigoEstadoFactura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEstadoFactura() {
        return codigoEstadoFactura;
    }

    /**
     * Sets the value of the codigoEstadoFactura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEstadoFactura(String value) {
        this.codigoEstadoFactura = value;
    }

    /**
     * Gets the value of the descEstadoFactura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescEstadoFactura() {
        return descEstadoFactura;
    }

    /**
     * Sets the value of the descEstadoFactura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescEstadoFactura(String value) {
        this.descEstadoFactura = value;
    }

    /**
     * Gets the value of the fechaPrimerVenc property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaPrimerVenc() {
        return fechaPrimerVenc;
    }

    /**
     * Sets the value of the fechaPrimerVenc property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaPrimerVenc(XMLGregorianCalendar value) {
        this.fechaPrimerVenc = value;
    }

    /**
     * Gets the value of the fechaVencOriginal property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaVencOriginal() {
        return fechaVencOriginal;
    }

    /**
     * Sets the value of the fechaVencOriginal property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaVencOriginal(XMLGregorianCalendar value) {
        this.fechaVencOriginal = value;
    }

    /**
     * Gets the value of the fechaSegundoVenc property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaSegundoVenc() {
        return fechaSegundoVenc;
    }

    /**
     * Sets the value of the fechaSegundoVenc property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaSegundoVenc(XMLGregorianCalendar value) {
        this.fechaSegundoVenc = value;
    }

    /**
     * Gets the value of the fechaCobro property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaCobro() {
        return fechaCobro;
    }

    /**
     * Sets the value of the fechaCobro property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaCobro(XMLGregorianCalendar value) {
        this.fechaCobro = value;
    }

    /**
     * Gets the value of the importePrimerVenc property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportePrimerVenc() {
        return importePrimerVenc;
    }

    /**
     * Sets the value of the importePrimerVenc property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportePrimerVenc(BigDecimal value) {
        this.importePrimerVenc = value;
    }

    /**
     * Gets the value of the importeSegundoVenc property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteSegundoVenc() {
        return importeSegundoVenc;
    }

    /**
     * Sets the value of the importeSegundoVenc property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteSegundoVenc(BigDecimal value) {
        this.importeSegundoVenc = value;
    }

    /**
     * Gets the value of the numeroRefFactura property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getNumeroRefFactura() {
        return numeroRefFactura;
    }

    /**
     * Sets the value of the numeroRefFactura property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setNumeroRefFactura(Object value) {
        this.numeroRefFactura = value;
    }

    /**
     * Gets the value of the numeroRefFacturaAsociada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroRefFacturaAsociada() {
        return numeroRefFacturaAsociada;
    }

    /**
     * Sets the value of the numeroRefFacturaAsociada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroRefFacturaAsociada(String value) {
        this.numeroRefFacturaAsociada = value;
    }

    /**
     * Gets the value of the codigoMarcaReclamo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoMarcaReclamo() {
        return codigoMarcaReclamo;
    }

    /**
     * Sets the value of the codigoMarcaReclamo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoMarcaReclamo(String value) {
        this.codigoMarcaReclamo = value;
    }

    /**
     * Gets the value of the descMarcaReclamo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescMarcaReclamo() {
        return descMarcaReclamo;
    }

    /**
     * Sets the value of the descMarcaReclamo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescMarcaReclamo(String value) {
        this.descMarcaReclamo = value;
    }

    /**
     * Gets the value of the importeCobrado property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteCobrado() {
        return importeCobrado;
    }

    /**
     * Sets the value of the importeCobrado property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteCobrado(BigDecimal value) {
        this.importeCobrado = value;
    }

    /**
     * Gets the value of the fechaAcreditacion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAcreditacion() {
        return fechaAcreditacion;
    }

    /**
     * Sets the value of the fechaAcreditacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAcreditacion(XMLGregorianCalendar value) {
        this.fechaAcreditacion = value;
    }

    /**
     * Gets the value of the fechaCambioEstadoFactura property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaCambioEstadoFactura() {
        return fechaCambioEstadoFactura;
    }

    /**
     * Sets the value of the fechaCambioEstadoFactura property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaCambioEstadoFactura(XMLGregorianCalendar value) {
        this.fechaCambioEstadoFactura = value;
    }

    /**
     * Gets the value of the puntoDeCobro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuntoDeCobro() {
        return puntoDeCobro;
    }

    /**
     * Sets the value of the puntoDeCobro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuntoDeCobro(String value) {
        this.puntoDeCobro = value;
    }

    /**
     * Gets the value of the descFormaDePago property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescFormaDePago() {
        return descFormaDePago;
    }

    /**
     * Sets the value of the descFormaDePago property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescFormaDePago(String value) {
        this.descFormaDePago = value;
    }

    /**
     * Gets the value of the existeHistTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExisteHistTransferencia() {
        return existeHistTransferencia;
    }

    /**
     * Sets the value of the existeHistTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExisteHistTransferencia(String value) {
        this.existeHistTransferencia = value;
    }

    /**
     * Gets the value of the existeTransferenciaTerceros property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExisteTransferenciaTerceros() {
        return existeTransferenciaTerceros;
    }

    /**
     * Sets the value of the existeTransferenciaTerceros property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExisteTransferenciaTerceros(String value) {
        this.existeTransferenciaTerceros = value;
    }

    /**
     * Gets the value of the importeDeTerceros property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteDeTerceros() {
        return importeDeTerceros;
    }

    /**
     * Sets the value of the importeDeTerceros property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteDeTerceros(BigDecimal value) {
        this.importeDeTerceros = value;
    }

    /**
     * Gets the value of the importePagosParciales property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportePagosParciales() {
        return importePagosParciales;
    }

    /**
     * Sets the value of the importePagosParciales property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportePagosParciales(BigDecimal value) {
        this.importePagosParciales = value;
    }

    /**
     * Gets the value of the codigoTipoContrib property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoTipoContrib() {
        return codigoTipoContrib;
    }

    /**
     * Sets the value of the codigoTipoContrib property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoTipoContrib(String value) {
        this.codigoTipoContrib = value;
    }

    /**
     * Gets the value of the descTipoContrib property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescTipoContrib() {
        return descTipoContrib;
    }

    /**
     * Sets the value of the descTipoContrib property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescTipoContrib(String value) {
        this.descTipoContrib = value;
    }

}
