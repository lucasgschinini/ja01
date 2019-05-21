
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}identificador"/>
 *         &lt;element name="propiedades" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="propiedad" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
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
    "identificador",
    "propiedades"
})
@XmlRootElement(name = "detalleServicio")
public class DetalleServicio {

    @XmlElement(required = true)
    protected String identificador;
    protected DetalleServicio.Propiedades propiedades;

    /**
     * Identificador univoco de servicio
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Sets the value of the identificador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificador(String value) {
        this.identificador = value;
    }

    /**
     * Gets the value of the propiedades property.
     * 
     * @return
     *     possible object is
     *     {@link DetalleServicio.Propiedades }
     *     
     */
    public DetalleServicio.Propiedades getPropiedades() {
        return propiedades;
    }

    /**
     * Sets the value of the propiedades property.
     * 
     * @param value
     *     allowed object is
     *     {@link DetalleServicio.Propiedades }
     *     
     */
    public void setPropiedades(DetalleServicio.Propiedades value) {
        this.propiedades = value;
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
     *         &lt;element name="propiedad" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
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
        "propiedad"
    })
    public static class Propiedades {

        @XmlElement(required = true)
        protected List<DetalleServicio.Propiedades.Propiedad> propiedad;

        /**
         * Gets the value of the propiedad property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the propiedad property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPropiedad().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DetalleServicio.Propiedades.Propiedad }
         * 
         * 
         */
        public List<DetalleServicio.Propiedades.Propiedad> getPropiedad() {
            if (propiedad == null) {
                propiedad = new ArrayList<DetalleServicio.Propiedades.Propiedad>();
            }
            return this.propiedad;
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
         *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
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
            "nombre",
            "valor",
            "descripcion"
        })
        public static class Propiedad {

            @XmlElement(required = true)
            protected String nombre;
            @XmlElement(required = true)
            protected String valor;
            @XmlElement(required = true)
            protected Object descripcion;

            /**
             * Gets the value of the nombre property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNombre() {
                return nombre;
            }

            /**
             * Sets the value of the nombre property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNombre(String value) {
                this.nombre = value;
            }

            /**
             * Gets the value of the valor property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValor() {
                return valor;
            }

            /**
             * Sets the value of the valor property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValor(String value) {
                this.valor = value;
            }

            /**
             * Gets the value of the descripcion property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getDescripcion() {
                return descripcion;
            }

            /**
             * Sets the value of the descripcion property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setDescripcion(Object value) {
                this.descripcion = value;
            }

        }

    }

}
