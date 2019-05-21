
package ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas;

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
 * <p>Java class for listaCreDebArrayApropiacionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listaCreDebArrayApropiacionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="detalle" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="idCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="importe" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="importeCapital" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="importeImpuestos" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="moneda" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="idDocctascob" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="clase" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="sucursal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="idDocumentoCuentasCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="vencimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="importeAplicado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="origenDelDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "listaCreDebArrayApropiacionResponse", propOrder = {
    "detalle"
})
public class ListaCreDebArrayApropiacionResponse {

    @XmlElement(required = true)
    protected List<ListaCreDebArrayApropiacionResponse.Detalle> detalle;

    /**
     * Gets the value of the detalle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ListaCreDebArrayApropiacionResponse.Detalle }
     * 
     * 
     */
    public List<ListaCreDebArrayApropiacionResponse.Detalle> getDetalle() {
        if (detalle == null) {
            detalle = new ArrayList<ListaCreDebArrayApropiacionResponse.Detalle>();
        }
        return this.detalle;
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
     *         &lt;element name="idCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="importe" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="importeCapital" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="importeImpuestos" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="moneda" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="idDocctascob" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="clase" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="sucursal" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="idDocumentoCuentasCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="vencimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="importeAplicado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="origenDelDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "idCobranza",
        "importe",
        "importeCapital",
        "importeImpuestos",
        "moneda",
        "idDocctascob",
        "tipo",
        "clase",
        "sucursal",
        "numero",
        "idDocumentoCuentasCobranza",
        "vencimiento",
        "importeAplicado",
        "origenDelDocumento",
        "informacionAdicionalTagetikCalipso",
        "informacionAdicionalDacota"
    })
    public static class Detalle {

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
        protected String moneda;
        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
        @XmlSchemaType(name = "integer")
        protected BigInteger idDocctascob;
        @XmlElement(required = true)
        protected String tipo;
        @XmlElement(required = true)
        protected String clase;
        @XmlElement(required = true)
        protected String sucursal;
        @XmlElement(required = true)
        protected String numero;
        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
        @XmlSchemaType(name = "integer")
        protected BigInteger idDocumentoCuentasCobranza;
        @XmlElement(required = true)
        protected String vencimiento;
        @XmlElement(required = true)
        protected BigDecimal importeAplicado;
        @XmlElement(required = true)
        protected String origenDelDocumento;
        @XmlElement(name = "InformacionAdicionalTagetikCalipso", required = true)
        protected Informaciontagetikcalipso informacionAdicionalTagetikCalipso;
        @XmlElement(name = "InformacionAdicionalDacota", required = true)
        protected Informaciondacota informacionAdicionalDacota;

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
         * Gets the value of the moneda property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMoneda() {
            return moneda;
        }

        /**
         * Sets the value of the moneda property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMoneda(String value) {
            this.moneda = value;
        }

        /**
         * Gets the value of the idDocctascob property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public BigInteger getIdDocctascob() {
            return idDocctascob;
        }

        /**
         * Sets the value of the idDocctascob property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdDocctascob(BigInteger value) {
            this.idDocctascob = value;
        }

        /**
         * Gets the value of the tipo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipo() {
            return tipo;
        }

        /**
         * Sets the value of the tipo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipo(String value) {
            this.tipo = value;
        }

        /**
         * Gets the value of the clase property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getClase() {
            return clase;
        }

        /**
         * Sets the value of the clase property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setClase(String value) {
            this.clase = value;
        }

        /**
         * Gets the value of the sucursal property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSucursal() {
            return sucursal;
        }

        /**
         * Sets the value of the sucursal property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSucursal(String value) {
            this.sucursal = value;
        }

        /**
         * Gets the value of the numero property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumero() {
            return numero;
        }

        /**
         * Sets the value of the numero property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumero(String value) {
            this.numero = value;
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
