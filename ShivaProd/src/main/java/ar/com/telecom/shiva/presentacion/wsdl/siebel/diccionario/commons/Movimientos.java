
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
 *       &lt;sequence>
 *         &lt;element name="movimiento" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="codigoCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="codigoCuenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="fechaAltaRemanente" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="codigoTipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="descripcionTipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="importeConcepto" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="importeRemanente" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="referencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/all>
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
@XmlType(name = "", propOrder = {
    "movimiento"
})
@XmlRootElement(name = "movimientos")
public class Movimientos {

    protected List<Movimientos.Movimiento> movimiento;

    /**
     * Gets the value of the movimiento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the movimiento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMovimiento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Movimientos.Movimiento }
     * 
     * 
     */
    public List<Movimientos.Movimiento> getMovimiento() {
        if (movimiento == null) {
            movimiento = new ArrayList<Movimientos.Movimiento>();
        }
        return this.movimiento;
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
     *       &lt;all>
     *         &lt;element name="codigoCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="codigoCuenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="fechaAltaRemanente" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="codigoTipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="descripcionTipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="importeConcepto" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="importeRemanente" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="referencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    public static class Movimiento {

        @XmlElement(required = true)
        protected String codigoCliente;
        @XmlElement(required = true)
        protected String codigoCuenta;
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar fechaAltaRemanente;
        @XmlElement(required = true)
        protected String codigoTipo;
        protected String descripcionTipo;
        @XmlElement(required = true)
        protected BigDecimal importeConcepto;
        @XmlElement(required = true)
        protected BigDecimal importeRemanente;
        protected String referencia;

        /**
         * Gets the value of the codigoCliente property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoCliente() {
            return codigoCliente;
        }

        /**
         * Sets the value of the codigoCliente property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoCliente(String value) {
            this.codigoCliente = value;
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
         * Gets the value of the fechaAltaRemanente property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getFechaAltaRemanente() {
            return fechaAltaRemanente;
        }

        /**
         * Sets the value of the fechaAltaRemanente property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setFechaAltaRemanente(XMLGregorianCalendar value) {
            this.fechaAltaRemanente = value;
        }

        /**
         * Gets the value of the codigoTipo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoTipo() {
            return codigoTipo;
        }

        /**
         * Sets the value of the codigoTipo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoTipo(String value) {
            this.codigoTipo = value;
        }

        /**
         * Gets the value of the descripcionTipo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionTipo() {
            return descripcionTipo;
        }

        /**
         * Sets the value of the descripcionTipo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionTipo(String value) {
            this.descripcionTipo = value;
        }

        /**
         * Gets the value of the importeConcepto property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getImporteConcepto() {
            return importeConcepto;
        }

        /**
         * Sets the value of the importeConcepto property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setImporteConcepto(BigDecimal value) {
            this.importeConcepto = value;
        }

        /**
         * Gets the value of the importeRemanente property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getImporteRemanente() {
            return importeRemanente;
        }

        /**
         * Sets the value of the importeRemanente property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setImporteRemanente(BigDecimal value) {
            this.importeRemanente = value;
        }

        /**
         * Gets the value of the referencia property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReferencia() {
            return referencia;
        }

        /**
         * Sets the value of the referencia property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReferencia(String value) {
            this.referencia = value;
        }

    }

}
