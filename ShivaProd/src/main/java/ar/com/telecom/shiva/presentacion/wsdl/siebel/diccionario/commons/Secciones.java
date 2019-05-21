
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ar.com.telecom.shiva.base.soa.xmlAdapters.BigIntegerXmlAdapter;


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
 *         &lt;element name="seccion" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}codigo"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}descripcion"/>
 *                   &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="totalHoras" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="totalMinutos" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="totalSegundos" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
    "seccion"
})
@XmlRootElement(name = "secciones")
public class Secciones {

    protected List<Secciones.Seccion> seccion;

    /**
     * Gets the value of the seccion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the seccion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSeccion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Secciones.Seccion }
     * 
     * 
     */
    public List<Secciones.Seccion> getSeccion() {
        if (seccion == null) {
            seccion = new ArrayList<Secciones.Seccion>();
        }
        return this.seccion;
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
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}codigo"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}descripcion"/>
     *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="totalHoras" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="totalMinutos" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="totalSegundos" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
    public static class Seccion {

        @XmlElement(required = true)
        protected String codigo;
        @XmlElement(required = true)
        protected String descripcion;
        @XmlElement(required = true)
        protected BigDecimal monto;
        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
        @XmlSchemaType(name = "integer")
        protected BigInteger totalHoras;
        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
        @XmlSchemaType(name = "integer")
        protected BigInteger totalMinutos;
        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
        @XmlSchemaType(name = "integer")
        protected BigInteger totalSegundos;

        /**
         * Gets the value of the codigo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigo() {
            return codigo;
        }

        /**
         * Sets the value of the codigo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigo(String value) {
            this.codigo = value;
        }

        /**
         * Gets the value of the descripcion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcion() {
            return descripcion;
        }

        /**
         * Sets the value of the descripcion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcion(String value) {
            this.descripcion = value;
        }

        /**
         * Gets the value of the monto property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getMonto() {
            return monto;
        }

        /**
         * Sets the value of the monto property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setMonto(BigDecimal value) {
            this.monto = value;
        }

        /**
         * Gets the value of the totalHoras property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public BigInteger getTotalHoras() {
            return totalHoras;
        }

        /**
         * Sets the value of the totalHoras property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTotalHoras(BigInteger value) {
            this.totalHoras = value;
        }

        /**
         * Gets the value of the totalMinutos property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public BigInteger getTotalMinutos() {
            return totalMinutos;
        }

        /**
         * Sets the value of the totalMinutos property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTotalMinutos(BigInteger value) {
            this.totalMinutos = value;
        }

        /**
         * Gets the value of the totalSegundos property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public BigInteger getTotalSegundos() {
            return totalSegundos;
        }

        /**
         * Sets the value of the totalSegundos property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTotalSegundos(BigInteger value) {
            this.totalSegundos = value;
        }

    }

}
