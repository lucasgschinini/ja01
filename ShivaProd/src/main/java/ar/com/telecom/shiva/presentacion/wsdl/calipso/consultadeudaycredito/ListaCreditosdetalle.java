
package ar.com.telecom.shiva.presentacion.wsdl.calipso.consultadeudaycredito;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ar.com.telecom.shiva.base.soa.xmlAdapters.BigIntegerXmlAdapter;


/**
 * <p>Java class for ListaCreditosdetalle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListaCreditosdetalle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreditoCalipso" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="IdClienteLegado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="IdDocumento" type="{urn:WSDL}IdDocumentoAgru"/>
 *                   &lt;element name="MonedaOriginalFactura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ImporteMonedaOrigen" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="ImportePesificado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="SaldoMonedaOrigen" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="SaldoPesificado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="FechaEmision" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FechaUltimoMovimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TipoCambioActual" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="IdDocCtasCob" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="EstadoMorosidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MarcaReclamo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MarcaMigradoDeimos" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="InformacionAdicionalTagetikCalipso" type="{urn:WSDL}informaciontagetikcalipso"/>
 *                   &lt;element name="InformacionAdicionalDacota" type="{urn:WSDL}informaciondacota"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListaCreditosdetalle", propOrder = {
    "creditoCalipso"
})
public class ListaCreditosdetalle {

    @XmlElement(name = "CreditoCalipso", required = true)
    protected List<ListaCreditosdetalle.CreditoCalipso> creditoCalipso;

    /**
     * Gets the value of the creditoCalipso property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the creditoCalipso property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreditoCalipso().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ListaCreditosdetalle.CreditoCalipso }
     * 
     * 
     */
    public List<ListaCreditosdetalle.CreditoCalipso> getCreditoCalipso() {
        if (creditoCalipso == null) {
            creditoCalipso = new ArrayList<ListaCreditosdetalle.CreditoCalipso>();
        }
        return this.creditoCalipso;
    }


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
     *         &lt;element name="IdClienteLegado" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="IdDocumento" type="{urn:WSDL}IdDocumentoAgru"/>
     *         &lt;element name="MonedaOriginalFactura" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ImporteMonedaOrigen" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="ImportePesificado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="SaldoMonedaOrigen" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="SaldoPesificado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="FechaEmision" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="FechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="FechaUltimoMovimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TipoCambioActual" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="IdDocCtasCob" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="EstadoMorosidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MarcaReclamo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MarcaMigradoDeimos" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="InformacionAdicionalTagetikCalipso" type="{urn:WSDL}informaciontagetikcalipso"/>
     *         &lt;element name="InformacionAdicionalDacota" type="{urn:WSDL}informaciondacota"/>
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
        "idClienteLegado",
        "idDocumento",
        "monedaOriginalFactura",
        "importeMonedaOrigen",
        "importePesificado",
        "saldoMonedaOrigen",
        "saldoPesificado",
        "fechaEmision",
        "fechaVencimiento",
        "fechaUltimoMovimiento",
        "tipoCambioActual",
        "idDocCtasCob",
        "estadoMorosidad",
        "marcaReclamo",
        "marcaMigradoDeimos",
        "informacionAdicionalTagetikCalipso",
        "informacionAdicionalDacota"
    })
    public static class CreditoCalipso {

        @XmlElement(name = "IdClienteLegado", required = true)
        protected String idClienteLegado;
        @XmlElement(name = "IdDocumento", required = true)
        protected IdDocumentoAgru idDocumento;
        @XmlElement(name = "MonedaOriginalFactura", required = true)
        protected String monedaOriginalFactura;
        @XmlElement(name = "ImporteMonedaOrigen", required = true)
        protected BigDecimal importeMonedaOrigen;
        @XmlElement(name = "ImportePesificado", required = true)
        protected BigDecimal importePesificado;
        @XmlElement(name = "SaldoMonedaOrigen", required = true)
        protected BigDecimal saldoMonedaOrigen;
        @XmlElement(name = "SaldoPesificado", required = true)
        protected BigDecimal saldoPesificado;
        @XmlElement(name = "FechaEmision", required = true)
        protected String fechaEmision;
        @XmlElement(name = "FechaVencimiento", required = true)
        protected String fechaVencimiento;
        @XmlElement(name = "FechaUltimoMovimiento", required = true)
        protected String fechaUltimoMovimiento;
        @XmlElement(name = "TipoCambioActual", required = true)
        protected BigDecimal tipoCambioActual;
        @XmlElement(name = "IdDocCtasCob", required = true, type = String.class)
        @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
        @XmlSchemaType(name = "integer")
        protected BigInteger idDocCtasCob;
        @XmlElement(name = "EstadoMorosidad", required = true)
        protected String estadoMorosidad;
        @XmlElement(name = "MarcaReclamo", required = true)
        protected String marcaReclamo;
        @XmlElement(name = "MarcaMigradoDeimos", required = true)
        protected String marcaMigradoDeimos;
        @XmlElement(name = "InformacionAdicionalTagetikCalipso", required = true)
        protected Informaciontagetikcalipso informacionAdicionalTagetikCalipso;
        @XmlElement(name = "InformacionAdicionalDacota", required = true)
        protected Informaciondacota informacionAdicionalDacota;

        /**
         * Gets the value of the idClienteLegado property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdClienteLegado() {
            return idClienteLegado;
        }

        /**
         * Sets the value of the idClienteLegado property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdClienteLegado(String value) {
            this.idClienteLegado = value;
        }

        /**
         * Gets the value of the idDocumento property.
         * 
         * @return
         *     possible object is
         *     {@link IdDocumentoAgru }
         *     
         */
        public IdDocumentoAgru getIdDocumento() {
            return idDocumento;
        }

        /**
         * Sets the value of the idDocumento property.
         * 
         * @param value
         *     allowed object is
         *     {@link IdDocumentoAgru }
         *     
         */
        public void setIdDocumento(IdDocumentoAgru value) {
            this.idDocumento = value;
        }

        /**
         * Gets the value of the monedaOriginalFactura property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMonedaOriginalFactura() {
            return monedaOriginalFactura;
        }

        /**
         * Sets the value of the monedaOriginalFactura property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMonedaOriginalFactura(String value) {
            this.monedaOriginalFactura = value;
        }

        /**
         * Gets the value of the importeMonedaOrigen property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getImporteMonedaOrigen() {
            return importeMonedaOrigen;
        }

        /**
         * Sets the value of the importeMonedaOrigen property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setImporteMonedaOrigen(BigDecimal value) {
            this.importeMonedaOrigen = value;
        }

        /**
         * Gets the value of the importePesificado property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getImportePesificado() {
            return importePesificado;
        }

        /**
         * Sets the value of the importePesificado property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setImportePesificado(BigDecimal value) {
            this.importePesificado = value;
        }

        /**
         * Gets the value of the saldoMonedaOrigen property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getSaldoMonedaOrigen() {
            return saldoMonedaOrigen;
        }

        /**
         * Sets the value of the saldoMonedaOrigen property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setSaldoMonedaOrigen(BigDecimal value) {
            this.saldoMonedaOrigen = value;
        }

        /**
         * Gets the value of the saldoPesificado property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getSaldoPesificado() {
            return saldoPesificado;
        }

        /**
         * Sets the value of the saldoPesificado property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setSaldoPesificado(BigDecimal value) {
            this.saldoPesificado = value;
        }

        /**
         * Gets the value of the fechaEmision property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaEmision() {
            return fechaEmision;
        }

        /**
         * Sets the value of the fechaEmision property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaEmision(String value) {
            this.fechaEmision = value;
        }

        /**
         * Gets the value of the fechaVencimiento property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaVencimiento() {
            return fechaVencimiento;
        }

        /**
         * Sets the value of the fechaVencimiento property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaVencimiento(String value) {
            this.fechaVencimiento = value;
        }

        /**
         * Gets the value of the fechaUltimoMovimiento property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaUltimoMovimiento() {
            return fechaUltimoMovimiento;
        }

        /**
         * Sets the value of the fechaUltimoMovimiento property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaUltimoMovimiento(String value) {
            this.fechaUltimoMovimiento = value;
        }

        /**
         * Gets the value of the tipoCambioActual property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTipoCambioActual() {
            return tipoCambioActual;
        }

        /**
         * Sets the value of the tipoCambioActual property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTipoCambioActual(BigDecimal value) {
            this.tipoCambioActual = value;
        }

        /**
         * Gets the value of the idDocCtasCob property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public BigInteger getIdDocCtasCob() {
            return idDocCtasCob;
        }

        /**
         * Sets the value of the idDocCtasCob property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdDocCtasCob(BigInteger value) {
            this.idDocCtasCob = value;
        }

        /**
         * Gets the value of the estadoMorosidad property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEstadoMorosidad() {
            return estadoMorosidad;
        }

        /**
         * Sets the value of the estadoMorosidad property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEstadoMorosidad(String value) {
            this.estadoMorosidad = value;
        }

        /**
         * Gets the value of the marcaReclamo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMarcaReclamo() {
            return marcaReclamo;
        }

        /**
         * Sets the value of the marcaReclamo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMarcaReclamo(String value) {
            this.marcaReclamo = value;
        }

        /**
         * Gets the value of the marcaMigradoDeimos property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMarcaMigradoDeimos() {
            return marcaMigradoDeimos;
        }

        /**
         * Sets the value of the marcaMigradoDeimos property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMarcaMigradoDeimos(String value) {
            this.marcaMigradoDeimos = value;
        }

        /**
         * Gets the value of the informacionAdicionalTagetikCalipso property.
         * 
         * @return
         *     possible object is
         *     {@link Informaciontagetikcalipso }
         *     
         */
        public Informaciontagetikcalipso getInformacionAdicionalTagetikCalipso() {
            return informacionAdicionalTagetikCalipso;
        }

        /**
         * Sets the value of the informacionAdicionalTagetikCalipso property.
         * 
         * @param value
         *     allowed object is
         *     {@link Informaciontagetikcalipso }
         *     
         */
        public void setInformacionAdicionalTagetikCalipso(Informaciontagetikcalipso value) {
            this.informacionAdicionalTagetikCalipso = value;
        }

        /**
         * Gets the value of the informacionAdicionalDacota property.
         * 
         * @return
         *     possible object is
         *     {@link Informaciondacota }
         *     
         */
        public Informaciondacota getInformacionAdicionalDacota() {
            return informacionAdicionalDacota;
        }

        /**
         * Sets the value of the informacionAdicionalDacota property.
         * 
         * @param value
         *     allowed object is
         *     {@link Informaciondacota }
         *     
         */
        public void setInformacionAdicionalDacota(Informaciondacota value) {
            this.informacionAdicionalDacota = value;
        }

    }

}
