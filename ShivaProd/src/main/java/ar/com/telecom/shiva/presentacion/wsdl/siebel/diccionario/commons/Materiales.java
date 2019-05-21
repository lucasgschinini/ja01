
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="material" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}codigo"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}cantidad" minOccurs="0"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}unidad" minOccurs="0"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}tipo" minOccurs="0"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}marca" minOccurs="0"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}modelo" minOccurs="0"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}numeroSerie" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="estado" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
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
    "material"
})
@XmlRootElement(name = "materiales")
public class Materiales {

    @XmlElement(required = true)
    protected List<Materiales.Material> material;

    /**
     * Gets the value of the material property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the material property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMaterial().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Materiales.Material }
     * 
     * 
     */
    public List<Materiales.Material> getMaterial() {
        if (material == null) {
            material = new ArrayList<Materiales.Material>();
        }
        return this.material;
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
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}codigo"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}cantidad" minOccurs="0"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}unidad" minOccurs="0"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}tipo" minOccurs="0"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}marca" minOccurs="0"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}modelo" minOccurs="0"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}numeroSerie" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="estado" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
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
        "cantidad",
        "unidad",
        "tipo",
        "marca",
        "modelo",
        "numeroSerie"
    })
    public static class Material {

        @XmlElement(required = true)
        protected String codigo;
        @XmlElement(type = String.class)
        @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
        @XmlSchemaType(name = "integer")
        protected BigInteger cantidad;
        protected String unidad;
        protected String tipo;
        protected String marca;
        protected String modelo;
        protected String numeroSerie;
        @XmlAttribute(name = "estado", required = true)
        @XmlSchemaType(name = "anySimpleType")
        protected String estado;

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

        /**
         * Gets the value of the unidad property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUnidad() {
            return unidad;
        }

        /**
         * Sets the value of the unidad property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUnidad(String value) {
            this.unidad = value;
        }

        /**
         * Codigo de tipo de aparato
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
         * Marca del aparato
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMarca() {
            return marca;
        }

        /**
         * Sets the value of the marca property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMarca(String value) {
            this.marca = value;
        }

        /**
         * Modelo de aparato
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getModelo() {
            return modelo;
        }

        /**
         * Sets the value of the modelo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setModelo(String value) {
            this.modelo = value;
        }

        /**
         * nro de serie del aparato
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroSerie() {
            return numeroSerie;
        }

        /**
         * Sets the value of the numeroSerie property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroSerie(String value) {
            this.numeroSerie = value;
        }

        /**
         * Gets the value of the estado property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEstado() {
            return estado;
        }

        /**
         * Sets the value of the estado property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEstado(String value) {
            this.estado = value;
        }

    }

}
