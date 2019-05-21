
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
 * <p>Java class for listaCtaCreArrayApropiacionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listaCtaCreArrayApropiacionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="detalle" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="idCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="clase" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="sucursal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="tipoCambioFechaEmision" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="tipoCambioFechaCobro" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="importeAplicadoFechaEmisionPesos" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="importeAplicadoMonedaOrigen" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="detallenewcta" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="importe" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                             &lt;element name="moneda" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="clase" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="sucursal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="idDocumentoCuentasCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                             &lt;element name="fechaoriginalcta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="resultadoApropiacion" type="{urn:WSDL}resultadoshiva"/>
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
@XmlType(name = "listaCtaCreArrayApropiacionResponse", propOrder = {
    "detalle"
})
public class ListaCtaCreArrayApropiacionResponse {

    @XmlElement(required = true)
    protected List<ListaCtaCreArrayApropiacionResponse.Detalle> detalle;

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
     * {@link ListaCtaCreArrayApropiacionResponse.Detalle }
     * 
     * 
     */
    public List<ListaCtaCreArrayApropiacionResponse.Detalle> getDetalle() {
        if (detalle == null) {
            detalle = new ArrayList<ListaCtaCreArrayApropiacionResponse.Detalle>();
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
     *         &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="clase" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="sucursal" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="tipoCambioFechaEmision" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="tipoCambioFechaCobro" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="importeAplicadoFechaEmisionPesos" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="importeAplicadoMonedaOrigen" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="detallenewcta" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="importe" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *                   &lt;element name="moneda" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="clase" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="sucursal" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="idDocumentoCuentasCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *                   &lt;element name="fechaoriginalcta" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="resultadoApropiacion" type="{urn:WSDL}resultadoshiva"/>
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
        "idDocumento",
        "tipo",
        "clase",
        "sucursal",
        "numero",
        "tipoCambioFechaEmision",
        "tipoCambioFechaCobro",
        "importeAplicadoFechaEmisionPesos",
        "importeAplicadoMonedaOrigen",
        "detallenewcta",
        "resultadoApropiacion"
    })
    public static class Detalle {

        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
        @XmlSchemaType(name = "integer")
        protected BigInteger idCobranza;
        @XmlElement(required = true)
        protected String idDocumento;
        @XmlElement(required = true)
        protected String tipo;
        @XmlElement(required = true)
        protected String clase;
        @XmlElement(required = true)
        protected String sucursal;
        @XmlElement(required = true)
        protected String numero;
        @XmlElement(required = true)
        protected BigDecimal tipoCambioFechaEmision;
        @XmlElement(required = true)
        protected BigDecimal tipoCambioFechaCobro;
        @XmlElement(required = true)
        protected BigDecimal importeAplicadoFechaEmisionPesos;
        @XmlElement(required = true)
        protected BigDecimal importeAplicadoMonedaOrigen;
        @XmlElement(required = true)
        protected List<ListaCtaCreArrayApropiacionResponse.Detalle.Detallenewcta> detallenewcta;
        @XmlElement(required = true)
        protected Resultadoshiva resultadoApropiacion;

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
         * Gets the value of the idDocumento property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdDocumento() {
            return idDocumento;
        }

        /**
         * Sets the value of the idDocumento property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdDocumento(String value) {
            this.idDocumento = value;
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
         * Gets the value of the tipoCambioFechaEmision property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTipoCambioFechaEmision() {
            return tipoCambioFechaEmision;
        }

        /**
         * Sets the value of the tipoCambioFechaEmision property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTipoCambioFechaEmision(BigDecimal value) {
            this.tipoCambioFechaEmision = value;
        }

        /**
         * Gets the value of the tipoCambioFechaCobro property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTipoCambioFechaCobro() {
            return tipoCambioFechaCobro;
        }

        /**
         * Sets the value of the tipoCambioFechaCobro property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTipoCambioFechaCobro(BigDecimal value) {
            this.tipoCambioFechaCobro = value;
        }

        /**
         * Gets the value of the importeAplicadoFechaEmisionPesos property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getImporteAplicadoFechaEmisionPesos() {
            return importeAplicadoFechaEmisionPesos;
        }

        /**
         * Sets the value of the importeAplicadoFechaEmisionPesos property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setImporteAplicadoFechaEmisionPesos(BigDecimal value) {
            this.importeAplicadoFechaEmisionPesos = value;
        }

        /**
         * Gets the value of the importeAplicadoMonedaOrigen property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getImporteAplicadoMonedaOrigen() {
            return importeAplicadoMonedaOrigen;
        }

        /**
         * Sets the value of the importeAplicadoMonedaOrigen property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setImporteAplicadoMonedaOrigen(BigDecimal value) {
            this.importeAplicadoMonedaOrigen = value;
        }

        /**
         * Gets the value of the detallenewcta property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the detallenewcta property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDetallenewcta().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ListaCtaCreArrayApropiacionResponse.Detalle.Detallenewcta }
         * 
         * 
         */
        public List<ListaCtaCreArrayApropiacionResponse.Detalle.Detallenewcta> getDetallenewcta() {
            if (detallenewcta == null) {
                detallenewcta = new ArrayList<ListaCtaCreArrayApropiacionResponse.Detalle.Detallenewcta>();
            }
            return this.detallenewcta;
        }

        /**
         * Gets the value of the resultadoApropiacion property.
         * 
         * @return
         *     possible object is
         *     {@link Resultadoshiva }
         *     
         */
        public Resultadoshiva getResultadoApropiacion() {
            return resultadoApropiacion;
        }

        /**
         * Sets the value of the resultadoApropiacion property.
         * 
         * @param value
         *     allowed object is
         *     {@link Resultadoshiva }
         *     
         */
        public void setResultadoApropiacion(Resultadoshiva value) {
            this.resultadoApropiacion = value;
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
         *         &lt;element name="importe" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
         *         &lt;element name="moneda" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="clase" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="sucursal" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="idDocumentoCuentasCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
         *         &lt;element name="fechaoriginalcta" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "importe",
            "moneda",
            "idDocumento",
            "tipo",
            "clase",
            "sucursal",
            "numero",
            "idDocumentoCuentasCobranza",
            "fechaoriginalcta"
        })
        public static class Detallenewcta {

            @XmlElement(required = true)
            protected BigDecimal importe;
            @XmlElement(required = true)
            protected String moneda;
            @XmlElement(required = true)
            protected String idDocumento;
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
            protected String fechaoriginalcta;

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
             * Gets the value of the idDocumento property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIdDocumento() {
                return idDocumento;
            }

            /**
             * Sets the value of the idDocumento property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIdDocumento(String value) {
                this.idDocumento = value;
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
             * Gets the value of the fechaoriginalcta property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFechaoriginalcta() {
                return fechaoriginalcta;
            }

            /**
             * Sets the value of the fechaoriginalcta property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFechaoriginalcta(String value) {
                this.fechaoriginalcta = value;
            }

        }

    }

}
