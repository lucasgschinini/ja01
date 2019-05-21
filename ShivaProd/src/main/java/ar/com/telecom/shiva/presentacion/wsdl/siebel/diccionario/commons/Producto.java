
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
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}propiedades" minOccurs="0"/>
 *         &lt;element name="servicios" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="servicio" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}propiedades"/>
 *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}serviciosAdicionales" minOccurs="0"/>
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
    "propiedades",
    "servicios"
})
@XmlRootElement(name = "producto")
public class Producto {

    protected Propiedades propiedades;
    protected Producto.Servicios servicios;

    /**
     * Gets the value of the propiedades property.
     * 
     * @return
     *     possible object is
     *     {@link Propiedades }
     *     
     */
    public Propiedades getPropiedades() {
        return propiedades;
    }

    /**
     * Sets the value of the propiedades property.
     * 
     * @param value
     *     allowed object is
     *     {@link Propiedades }
     *     
     */
    public void setPropiedades(Propiedades value) {
        this.propiedades = value;
    }

    /**
     * Gets the value of the servicios property.
     * 
     * @return
     *     possible object is
     *     {@link Producto.Servicios }
     *     
     */
    public Producto.Servicios getServicios() {
        return servicios;
    }

    /**
     * Sets the value of the servicios property.
     * 
     * @param value
     *     allowed object is
     *     {@link Producto.Servicios }
     *     
     */
    public void setServicios(Producto.Servicios value) {
        this.servicios = value;
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
     *         &lt;element name="servicio" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}propiedades"/>
     *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}serviciosAdicionales" minOccurs="0"/>
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
        "servicio"
    })
    public static class Servicios {

        @XmlElement(required = true)
        protected List<Producto.Servicios.Servicio> servicio;

        /**
         * Gets the value of the servicio property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the servicio property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getServicio().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Producto.Servicios.Servicio }
         * 
         * 
         */
        public List<Producto.Servicios.Servicio> getServicio() {
            if (servicio == null) {
                servicio = new ArrayList<Producto.Servicios.Servicio>();
            }
            return this.servicio;
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
         *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}propiedades"/>
         *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}serviciosAdicionales" minOccurs="0"/>
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
            "propiedades",
            "serviciosAdicionales"
        })
        public static class Servicio {

            @XmlElement(required = true)
            protected Propiedades propiedades;
            protected ServiciosAdicionales serviciosAdicionales;

            /**
             * Gets the value of the propiedades property.
             * 
             * @return
             *     possible object is
             *     {@link Propiedades }
             *     
             */
            public Propiedades getPropiedades() {
                return propiedades;
            }

            /**
             * Sets the value of the propiedades property.
             * 
             * @param value
             *     allowed object is
             *     {@link Propiedades }
             *     
             */
            public void setPropiedades(Propiedades value) {
                this.propiedades = value;
            }

            /**
             * Gets the value of the serviciosAdicionales property.
             * 
             * @return
             *     possible object is
             *     {@link ServiciosAdicionales }
             *     
             */
            public ServiciosAdicionales getServiciosAdicionales() {
                return serviciosAdicionales;
            }

            /**
             * Sets the value of the serviciosAdicionales property.
             * 
             * @param value
             *     allowed object is
             *     {@link ServiciosAdicionales }
             *     
             */
            public void setServiciosAdicionales(ServiciosAdicionales value) {
                this.serviciosAdicionales = value;
            }

        }

    }

}
