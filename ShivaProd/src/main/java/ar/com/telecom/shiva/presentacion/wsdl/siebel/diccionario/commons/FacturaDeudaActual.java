
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
 *         &lt;element name="codigoClienteLegado" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *         &lt;element name="importePrimerVenc" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="importeSegundoVenc" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="numeroRefFacturaAsociada" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoMarcaReclamo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descMarcaReclamo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaAcreditacionCobro" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaCambioEstadoFactura" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="existeConceptosTercerosTransferidos" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estadoConceptosTercerosIncluidos" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importeDeTercerosTransferidos" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="importePagosParciales" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="codigoTipoContribuyente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descTipoContribuyente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoTipoRecibo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="motivoReversionCobro" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descMotivoReversionCobro" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="marcaConcursosQuiebras" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importeNetoRecargo" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="importeNoFinanciable" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="importeNetoRecargoMontoFinanciable" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
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
@XmlRootElement(name = "facturaDeudaActual")
public class FacturaDeudaActual {

    @XmlElement(required = true)
    protected String codigoClienteLegado;
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
    @XmlElement(required = true)
    protected BigDecimal importePrimerVenc;
    @XmlElement(required = true)
    protected BigDecimal importeSegundoVenc;
    @XmlElement(required = true)
    protected String numeroRefFacturaAsociada;
    @XmlElement(required = true)
    protected String codigoMarcaReclamo;
    @XmlElement(required = true)
    protected String descMarcaReclamo;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaAcreditacionCobro;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaCambioEstadoFactura;
    @XmlElement(required = true)
    protected String existeConceptosTercerosTransferidos;
    @XmlElement(required = true)
    protected String estadoConceptosTercerosIncluidos;
    @XmlElement(required = true)
    protected BigDecimal importeDeTercerosTransferidos;
    @XmlElement(required = true)
    protected BigDecimal importePagosParciales;
    @XmlElement(required = true)
    protected String codigoTipoContribuyente;
    @XmlElement(required = true)
    protected String descTipoContribuyente;
    @XmlElement(required = true)
    protected String codigoTipoRecibo;
    @XmlElement(required = true)
    protected String motivoReversionCobro;
    @XmlElement(required = true)
    protected String descMotivoReversionCobro;
    @XmlElement(required = true)
    protected String marcaConcursosQuiebras;
    @XmlElement(required = true)
    protected BigDecimal importeNetoRecargo;
    @XmlElement(required = true)
    protected BigDecimal importeNoFinanciable;
    @XmlElement(required = true)
    protected BigDecimal importeNetoRecargoMontoFinanciable;

    /**
     * Gets the value of the codigoClienteLegado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoClienteLegado() {
        return codigoClienteLegado;
    }

    /**
     * Sets the value of the codigoClienteLegado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoClienteLegado(String value) {
        this.codigoClienteLegado = value;
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
     * Gets the value of the fechaAcreditacionCobro property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAcreditacionCobro() {
        return fechaAcreditacionCobro;
    }

    /**
     * Sets the value of the fechaAcreditacionCobro property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAcreditacionCobro(XMLGregorianCalendar value) {
        this.fechaAcreditacionCobro = value;
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
     * Gets the value of the existeConceptosTercerosTransferidos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExisteConceptosTercerosTransferidos() {
        return existeConceptosTercerosTransferidos;
    }

    /**
     * Sets the value of the existeConceptosTercerosTransferidos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExisteConceptosTercerosTransferidos(String value) {
        this.existeConceptosTercerosTransferidos = value;
    }

    /**
     * Gets the value of the estadoConceptosTercerosIncluidos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoConceptosTercerosIncluidos() {
        return estadoConceptosTercerosIncluidos;
    }

    /**
     * Sets the value of the estadoConceptosTercerosIncluidos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoConceptosTercerosIncluidos(String value) {
        this.estadoConceptosTercerosIncluidos = value;
    }

    /**
     * Gets the value of the importeDeTercerosTransferidos property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteDeTercerosTransferidos() {
        return importeDeTercerosTransferidos;
    }

    /**
     * Sets the value of the importeDeTercerosTransferidos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteDeTercerosTransferidos(BigDecimal value) {
        this.importeDeTercerosTransferidos = value;
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
     * Gets the value of the codigoTipoContribuyente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoTipoContribuyente() {
        return codigoTipoContribuyente;
    }

    /**
     * Sets the value of the codigoTipoContribuyente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoTipoContribuyente(String value) {
        this.codigoTipoContribuyente = value;
    }

    /**
     * Gets the value of the descTipoContribuyente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescTipoContribuyente() {
        return descTipoContribuyente;
    }

    /**
     * Sets the value of the descTipoContribuyente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescTipoContribuyente(String value) {
        this.descTipoContribuyente = value;
    }

    /**
     * Gets the value of the codigoTipoRecibo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoTipoRecibo() {
        return codigoTipoRecibo;
    }

    /**
     * Sets the value of the codigoTipoRecibo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoTipoRecibo(String value) {
        this.codigoTipoRecibo = value;
    }

    /**
     * Gets the value of the motivoReversionCobro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoReversionCobro() {
        return motivoReversionCobro;
    }

    /**
     * Sets the value of the motivoReversionCobro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoReversionCobro(String value) {
        this.motivoReversionCobro = value;
    }

    /**
     * Gets the value of the descMotivoReversionCobro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescMotivoReversionCobro() {
        return descMotivoReversionCobro;
    }

    /**
     * Sets the value of the descMotivoReversionCobro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescMotivoReversionCobro(String value) {
        this.descMotivoReversionCobro = value;
    }

    /**
     * Gets the value of the marcaConcursosQuiebras property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarcaConcursosQuiebras() {
        return marcaConcursosQuiebras;
    }

    /**
     * Sets the value of the marcaConcursosQuiebras property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarcaConcursosQuiebras(String value) {
        this.marcaConcursosQuiebras = value;
    }

    /**
     * Gets the value of the importeNetoRecargo property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteNetoRecargo() {
        return importeNetoRecargo;
    }

    /**
     * Sets the value of the importeNetoRecargo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteNetoRecargo(BigDecimal value) {
        this.importeNetoRecargo = value;
    }

    /**
     * Gets the value of the importeNoFinanciable property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteNoFinanciable() {
        return importeNoFinanciable;
    }

    /**
     * Sets the value of the importeNoFinanciable property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteNoFinanciable(BigDecimal value) {
        this.importeNoFinanciable = value;
    }

    /**
     * Gets the value of the importeNetoRecargoMontoFinanciable property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteNetoRecargoMontoFinanciable() {
        return importeNetoRecargoMontoFinanciable;
    }

    /**
     * Sets the value of the importeNetoRecargoMontoFinanciable property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteNetoRecargoMontoFinanciable(BigDecimal value) {
        this.importeNetoRecargoMontoFinanciable = value;
    }

}
