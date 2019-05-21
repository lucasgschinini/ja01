
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
 * <p>Java class for Listadebitosdetalle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Listadebitosdetalle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Debito" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="IdClienteLegado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="IdDocumento" type="{urn:WSDL}IdDocumentoAgru"/>
 *                   &lt;element name="FechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MonedaOriginalFactura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Importe1erVencimientoMonedaOrigen" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="Importe1erVencimientoPesificado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="Importe1erVencimientoPesificadoFechaCotizacion" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="Saldo1erVencimientoMonedaOrigen" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="SaldoPesificado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="SaldoPesificadoFechaCotizacion" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="CodigoAcuerdoFacturacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="EstadoAcuerdoFacturacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="EstadoMorosidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MarcaReclamo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MarcaMigradoDeimos" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TipoCambioActual" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="TipoCambioFechaCotizacion" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="FechaEmision" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FechaUltimoPagoParcial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="IdDocCtasCob" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
@XmlType(name = "Listadebitosdetalle", propOrder = {
    "debito"
})
public class Listadebitosdetalle {

    @XmlElement(name = "Debito", required = true)
    protected List<Listadebitosdetalle.Debito> debito;

    /**
     * Gets the value of the debito property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the debito property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDebito().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Listadebitosdetalle.Debito }
     * 
     * 
     */
    public List<Listadebitosdetalle.Debito> getDebito() {
        if (debito == null) {
            debito = new ArrayList<Listadebitosdetalle.Debito>();
        }
        return this.debito;
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
     *         &lt;element name="FechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MonedaOriginalFactura" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Importe1erVencimientoMonedaOrigen" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="Importe1erVencimientoPesificado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="Importe1erVencimientoPesificadoFechaCotizacion" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="Saldo1erVencimientoMonedaOrigen" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="SaldoPesificado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="SaldoPesificadoFechaCotizacion" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="CodigoAcuerdoFacturacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="EstadoAcuerdoFacturacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="EstadoMorosidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MarcaReclamo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MarcaMigradoDeimos" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TipoCambioActual" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="TipoCambioFechaCotizacion" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="FechaEmision" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="FechaUltimoPagoParcial" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="IdDocCtasCob" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
        "fechaVencimiento",
        "monedaOriginalFactura",
        "importe1ErVencimientoMonedaOrigen",
        "importe1ErVencimientoPesificado",
        "importe1ErVencimientoPesificadoFechaCotizacion",
        "saldo1ErVencimientoMonedaOrigen",
        "saldoPesificado",
        "saldoPesificadoFechaCotizacion",
        "codigoAcuerdoFacturacion",
        "estadoAcuerdoFacturacion",
        "estadoMorosidad",
        "marcaReclamo",
        "marcaMigradoDeimos",
        "tipoCambioActual",
        "tipoCambioFechaCotizacion",
        "fechaEmision",
        "fechaUltimoPagoParcial",
        "idDocCtasCob",
        "informacionAdicionalTagetikCalipso",
        "informacionAdicionalDacota"
    })
    public static class Debito {

        @XmlElement(name = "IdClienteLegado", required = true)
        protected String idClienteLegado;
        @XmlElement(name = "IdDocumento", required = true)
        protected IdDocumentoAgru idDocumento;
        @XmlElement(name = "FechaVencimiento", required = true)
        protected String fechaVencimiento;
        @XmlElement(name = "MonedaOriginalFactura", required = true)
        protected String monedaOriginalFactura;
        @XmlElement(name = "Importe1erVencimientoMonedaOrigen", required = true)
        protected BigDecimal importe1ErVencimientoMonedaOrigen;
        @XmlElement(name = "Importe1erVencimientoPesificado", required = true)
        protected BigDecimal importe1ErVencimientoPesificado;
        @XmlElement(name = "Importe1erVencimientoPesificadoFechaCotizacion", required = true)
        protected BigDecimal importe1ErVencimientoPesificadoFechaCotizacion;
        @XmlElement(name = "Saldo1erVencimientoMonedaOrigen", required = true)
        protected BigDecimal saldo1ErVencimientoMonedaOrigen;
        @XmlElement(name = "SaldoPesificado", required = true)
        protected BigDecimal saldoPesificado;
        @XmlElement(name = "SaldoPesificadoFechaCotizacion", required = true)
        protected BigDecimal saldoPesificadoFechaCotizacion;
        @XmlElement(name = "CodigoAcuerdoFacturacion", required = true)
        protected String codigoAcuerdoFacturacion;
        @XmlElement(name = "EstadoAcuerdoFacturacion", required = true)
        protected String estadoAcuerdoFacturacion;
        @XmlElement(name = "EstadoMorosidad", required = true)
        protected String estadoMorosidad;
        @XmlElement(name = "MarcaReclamo", required = true)
        protected String marcaReclamo;
        @XmlElement(name = "MarcaMigradoDeimos", required = true)
        protected String marcaMigradoDeimos;
        @XmlElement(name = "TipoCambioActual", required = true)
        protected BigDecimal tipoCambioActual;
        @XmlElement(name = "TipoCambioFechaCotizacion", required = true)
        protected BigDecimal tipoCambioFechaCotizacion;
        @XmlElement(name = "FechaEmision", required = true)
        protected String fechaEmision;
        @XmlElement(name = "FechaUltimoPagoParcial", required = true)
        protected String fechaUltimoPagoParcial;
        @XmlElement(name = "IdDocCtasCob", required = true, type = String.class)
        @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
        @XmlSchemaType(name = "integer")
        protected BigInteger idDocCtasCob;
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
         * Gets the value of the importe1ErVencimientoMonedaOrigen property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getImporte1ErVencimientoMonedaOrigen() {
            return importe1ErVencimientoMonedaOrigen;
        }

        /**
         * Sets the value of the importe1ErVencimientoMonedaOrigen property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setImporte1ErVencimientoMonedaOrigen(BigDecimal value) {
            this.importe1ErVencimientoMonedaOrigen = value;
        }

        /**
         * Gets the value of the importe1ErVencimientoPesificado property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getImporte1ErVencimientoPesificado() {
            return importe1ErVencimientoPesificado;
        }

        /**
         * Sets the value of the importe1ErVencimientoPesificado property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setImporte1ErVencimientoPesificado(BigDecimal value) {
            this.importe1ErVencimientoPesificado = value;
        }

        /**
         * Gets the value of the importe1ErVencimientoPesificadoFechaCotizacion property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getImporte1ErVencimientoPesificadoFechaCotizacion() {
            return importe1ErVencimientoPesificadoFechaCotizacion;
        }

        /**
         * Sets the value of the importe1ErVencimientoPesificadoFechaCotizacion property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setImporte1ErVencimientoPesificadoFechaCotizacion(BigDecimal value) {
            this.importe1ErVencimientoPesificadoFechaCotizacion = value;
        }

        /**
         * Gets the value of the saldo1ErVencimientoMonedaOrigen property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getSaldo1ErVencimientoMonedaOrigen() {
            return saldo1ErVencimientoMonedaOrigen;
        }

        /**
         * Sets the value of the saldo1ErVencimientoMonedaOrigen property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setSaldo1ErVencimientoMonedaOrigen(BigDecimal value) {
            this.saldo1ErVencimientoMonedaOrigen = value;
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
         * Gets the value of the saldoPesificadoFechaCotizacion property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getSaldoPesificadoFechaCotizacion() {
            return saldoPesificadoFechaCotizacion;
        }

        /**
         * Sets the value of the saldoPesificadoFechaCotizacion property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setSaldoPesificadoFechaCotizacion(BigDecimal value) {
            this.saldoPesificadoFechaCotizacion = value;
        }

        /**
         * Gets the value of the codigoAcuerdoFacturacion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoAcuerdoFacturacion() {
            return codigoAcuerdoFacturacion;
        }

        /**
         * Sets the value of the codigoAcuerdoFacturacion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoAcuerdoFacturacion(String value) {
            this.codigoAcuerdoFacturacion = value;
        }

        /**
         * Gets the value of the estadoAcuerdoFacturacion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEstadoAcuerdoFacturacion() {
            return estadoAcuerdoFacturacion;
        }

        /**
         * Sets the value of the estadoAcuerdoFacturacion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEstadoAcuerdoFacturacion(String value) {
            this.estadoAcuerdoFacturacion = value;
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
         * Gets the value of the tipoCambioFechaCotizacion property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTipoCambioFechaCotizacion() {
            return tipoCambioFechaCotizacion;
        }

        /**
         * Sets the value of the tipoCambioFechaCotizacion property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTipoCambioFechaCotizacion(BigDecimal value) {
            this.tipoCambioFechaCotizacion = value;
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
         * Gets the value of the fechaUltimoPagoParcial property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaUltimoPagoParcial() {
            return fechaUltimoPagoParcial;
        }

        /**
         * Sets the value of the fechaUltimoPagoParcial property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaUltimoPagoParcial(String value) {
            this.fechaUltimoPagoParcial = value;
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
