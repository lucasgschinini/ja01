
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="domicilioFinca" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}agenciaGestionaria" minOccurs="0"/>
 *                   &lt;element name="tipoZona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}tipoDomicilio" minOccurs="0"/>
 *                   &lt;element name="provincia" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="localidad" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="zona" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="propiedades" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="propiedad" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="codigo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="nombre" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="valor" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="tipo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "domicilioFinca"
})
@XmlRootElement(name = "domiciliosFinca")
public class DomiciliosFinca {

    protected List<DomiciliosFinca.DomicilioFinca> domicilioFinca;

    /**
     * Gets the value of the domicilioFinca property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the domicilioFinca property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDomicilioFinca().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DomiciliosFinca.DomicilioFinca }
     * 
     * 
     */
    public List<DomiciliosFinca.DomicilioFinca> getDomicilioFinca() {
        if (domicilioFinca == null) {
            domicilioFinca = new ArrayList<DomiciliosFinca.DomicilioFinca>();
        }
        return this.domicilioFinca;
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
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}agenciaGestionaria" minOccurs="0"/>
     *         &lt;element name="tipoZona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}tipoDomicilio" minOccurs="0"/>
     *         &lt;element name="provincia" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="localidad" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="zona" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="propiedades" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="propiedad" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="codigo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="nombre" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="valor" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" />
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
     *       &lt;attribute name="tipo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "agenciaGestionaria",
        "tipoZona",
        "tipoDomicilio",
        "provincia",
        "localidad",
        "zona",
        "propiedades"
    })
    public static class DomicilioFinca {

        protected String agenciaGestionaria;
        protected String tipoZona;
        protected String tipoDomicilio;
        protected DomiciliosFinca.DomicilioFinca.Provincia provincia;
        protected DomiciliosFinca.DomicilioFinca.Localidad localidad;
        protected DomiciliosFinca.DomicilioFinca.Zona zona;
        protected DomiciliosFinca.DomicilioFinca.Propiedades propiedades;
        @XmlAttribute(name = "tipo", required = true)
        protected String tipo;

        /**
         * Gets the value of the agenciaGestionaria property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAgenciaGestionaria() {
            return agenciaGestionaria;
        }

        /**
         * Sets the value of the agenciaGestionaria property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAgenciaGestionaria(String value) {
            this.agenciaGestionaria = value;
        }

        /**
         * Gets the value of the tipoZona property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipoZona() {
            return tipoZona;
        }

        /**
         * Sets the value of the tipoZona property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipoZona(String value) {
            this.tipoZona = value;
        }

        /**
         * ejemplo: "rancho"
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipoDomicilio() {
            return tipoDomicilio;
        }

        /**
         * Sets the value of the tipoDomicilio property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipoDomicilio(String value) {
            this.tipoDomicilio = value;
        }

        /**
         * Gets the value of the provincia property.
         * 
         * @return
         *     possible object is
         *     {@link DomiciliosFinca.DomicilioFinca.Provincia }
         *     
         */
        public DomiciliosFinca.DomicilioFinca.Provincia getProvincia() {
            return provincia;
        }

        /**
         * Sets the value of the provincia property.
         * 
         * @param value
         *     allowed object is
         *     {@link DomiciliosFinca.DomicilioFinca.Provincia }
         *     
         */
        public void setProvincia(DomiciliosFinca.DomicilioFinca.Provincia value) {
            this.provincia = value;
        }

        /**
         * Gets the value of the localidad property.
         * 
         * @return
         *     possible object is
         *     {@link DomiciliosFinca.DomicilioFinca.Localidad }
         *     
         */
        public DomiciliosFinca.DomicilioFinca.Localidad getLocalidad() {
            return localidad;
        }

        /**
         * Sets the value of the localidad property.
         * 
         * @param value
         *     allowed object is
         *     {@link DomiciliosFinca.DomicilioFinca.Localidad }
         *     
         */
        public void setLocalidad(DomiciliosFinca.DomicilioFinca.Localidad value) {
            this.localidad = value;
        }

        /**
         * Gets the value of the zona property.
         * 
         * @return
         *     possible object is
         *     {@link DomiciliosFinca.DomicilioFinca.Zona }
         *     
         */
        public DomiciliosFinca.DomicilioFinca.Zona getZona() {
            return zona;
        }

        /**
         * Sets the value of the zona property.
         * 
         * @param value
         *     allowed object is
         *     {@link DomiciliosFinca.DomicilioFinca.Zona }
         *     
         */
        public void setZona(DomiciliosFinca.DomicilioFinca.Zona value) {
            this.zona = value;
        }

        /**
         * Gets the value of the propiedades property.
         * 
         * @return
         *     possible object is
         *     {@link DomiciliosFinca.DomicilioFinca.Propiedades }
         *     
         */
        public DomiciliosFinca.DomicilioFinca.Propiedades getPropiedades() {
            return propiedades;
        }

        /**
         * Sets the value of the propiedades property.
         * 
         * @param value
         *     allowed object is
         *     {@link DomiciliosFinca.DomicilioFinca.Propiedades }
         *     
         */
        public void setPropiedades(DomiciliosFinca.DomicilioFinca.Propiedades value) {
            this.propiedades = value;
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
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Localidad {

            @XmlAttribute(name = "codigo")
            protected String codigo;
            @XmlAttribute(name = "descripcion")
            protected String descripcion;

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
         *         &lt;element name="propiedad" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="codigo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="nombre" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="valor" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" />
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

            protected List<DomiciliosFinca.DomicilioFinca.Propiedades.Propiedad> propiedad;

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
             * {@link DomiciliosFinca.DomicilioFinca.Propiedades.Propiedad }
             * 
             * 
             */
            public List<DomiciliosFinca.DomicilioFinca.Propiedades.Propiedad> getPropiedad() {
                if (propiedad == null) {
                    propiedad = new ArrayList<DomiciliosFinca.DomicilioFinca.Propiedades.Propiedad>();
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
             *       &lt;attribute name="codigo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="nombre" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="valor" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Propiedad {

                @XmlAttribute(name = "codigo", required = true)
                protected String codigo;
                @XmlAttribute(name = "nombre", required = true)
                protected String nombre;
                @XmlAttribute(name = "valor", required = true)
                protected String valor;
                @XmlAttribute(name = "descripcion")
                protected String descripcion;

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

            }

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
         *       &lt;attribute name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Provincia {

            @XmlAttribute(name = "codigo")
            protected String codigo;
            @XmlAttribute(name = "descripcion")
            protected String descripcion;

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
         *       &lt;attribute name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Zona {

            @XmlAttribute(name = "codigo")
            protected String codigo;
            @XmlAttribute(name = "descripcion")
            protected String descripcion;

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

        }

    }

}
