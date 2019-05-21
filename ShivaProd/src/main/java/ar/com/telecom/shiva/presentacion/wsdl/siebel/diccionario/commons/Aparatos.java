
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

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
 *         &lt;element name="aparato" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}codigo" minOccurs="0"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}descripcion" minOccurs="0"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}fechaHora" minOccurs="0"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}cantidad" minOccurs="0"/>
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
@XmlType(name = "", propOrder = {
    "aparato"
})
@XmlRootElement(name = "aparatos")
public class Aparatos {

    protected List<Aparatos.Aparato> aparato;

    /**
     * Gets the value of the aparato property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aparato property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAparato().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Aparatos.Aparato }
     * 
     * 
     */
    public List<Aparatos.Aparato> getAparato() {
        if (aparato == null) {
            aparato = new ArrayList<Aparatos.Aparato>();
        }
        return this.aparato;
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
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}codigo" minOccurs="0"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}descripcion" minOccurs="0"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}fechaHora" minOccurs="0"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}cantidad" minOccurs="0"/>
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
        "codigo",
        "descripcion",
        "fechaHora",
        "cantidad"
    })
    public static class Aparato {

        protected String codigo;
        protected String descripcion;
        protected String fechaHora;
        @XmlElement(type = String.class)
        @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
        @XmlSchemaType(name = "integer")
        protected BigInteger cantidad;

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
         * Gets the value of the fechaHora property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaHora() {
            return fechaHora;
        }

        /**
         * Sets the value of the fechaHora property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaHora(String value) {
            this.fechaHora = value;
        }

        /**
         * Gets the value of the cantidad property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public BigInteger getCantidad() {
            return cantidad;
        }

        /**
         * Sets the value of the cantidad property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCantidad(BigInteger value) {
            this.cantidad = value;
        }

    }

}
