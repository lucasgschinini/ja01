
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
 * <p>Java class for listaCtaCreArrayApropiacion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listaCtaCreArrayApropiacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="detalle" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="importe" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="montoAcumuladoSimulacion" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="clase" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="sucursal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="idDocumentoCuentasCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
@XmlType(name = "listaCtaCreArrayApropiacion", propOrder = {
    "detalle"
})
public class ListaCtaCreArrayApropiacion {

    @XmlElement(required = true)
    protected List<ListaCtaCreArrayApropiacion.Detalle> detalle;

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
     * {@link ListaCtaCreArrayApropiacion.Detalle }
     * 
     * 
     */
    public List<ListaCtaCreArrayApropiacion.Detalle> getDetalle() {
        if (detalle == null) {
            detalle = new ArrayList<ListaCtaCreArrayApropiacion.Detalle>();
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
     *         &lt;element name="importe" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="montoAcumuladoSimulacion" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="clase" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="sucursal" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="idDocumentoCuentasCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
        "montoAcumuladoSimulacion",
        "idDocumento",
        "tipo",
        "clase",
        "sucursal",
        "numero",
        "idDocumentoCuentasCobranza"
    })
    public static class Detalle {

        @XmlElement(required = true)
        protected BigDecimal importe;
        @XmlElement(required = true)
        protected BigDecimal montoAcumuladoSimulacion;
        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
        @XmlSchemaType(name = "integer")
        protected BigInteger idDocumento;
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
         * Gets the value of the montoAcumuladoSimulacion property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getMontoAcumuladoSimulacion() {
            return montoAcumuladoSimulacion;
        }

        /**
         * Sets the value of the montoAcumuladoSimulacion property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setMontoAcumuladoSimulacion(BigDecimal value) {
            this.montoAcumuladoSimulacion = value;
        }

        /**
         * Gets the value of the idDocumento property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public BigInteger getIdDocumento() {
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
        public void setIdDocumento(BigInteger value) {
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

    }

}
