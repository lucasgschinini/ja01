
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="vinculo" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="arriba">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}equipoId"/>
 *                             &lt;element name="extremo">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}central"/>
 *                                       &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}zona"/>
 *                                       &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}equipo"/>
 *                                       &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}cable"/>
 *                                       &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}par"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="abajo">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}equipoId"/>
 *                             &lt;element name="extremo">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}central"/>
 *                                       &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}zona"/>
 *                                       &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}equipo"/>
 *                                       &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}cable"/>
 *                                       &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}par"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}estado" minOccurs="0"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}precalificacion" minOccurs="0"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}destino" minOccurs="0"/>
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
    "vinculo"
})
@XmlRootElement(name = "vinculos")
public class Vinculos {

    protected List<Vinculos.Vinculo> vinculo;

    /**
     * Gets the value of the vinculo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vinculo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVinculo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Vinculos.Vinculo }
     * 
     * 
     */
    public List<Vinculos.Vinculo> getVinculo() {
        if (vinculo == null) {
            vinculo = new ArrayList<Vinculos.Vinculo>();
        }
        return this.vinculo;
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
     *         &lt;element name="arriba">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}equipoId"/>
     *                   &lt;element name="extremo">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}central"/>
     *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}zona"/>
     *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}equipo"/>
     *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}cable"/>
     *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}par"/>
     *                           &lt;/sequence>
     *                           &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="abajo">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}equipoId"/>
     *                   &lt;element name="extremo">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}central"/>
     *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}zona"/>
     *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}equipo"/>
     *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}cable"/>
     *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}par"/>
     *                           &lt;/sequence>
     *                           &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}estado" minOccurs="0"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}precalificacion" minOccurs="0"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}destino" minOccurs="0"/>
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
        "arriba",
        "abajo",
        "estado",
        "precalificacion",
        "destino"
    })
    public static class Vinculo {

        @XmlElement(required = true)
        protected Vinculos.Vinculo.Arriba arriba;
        @XmlElement(required = true)
        protected Vinculos.Vinculo.Abajo abajo;
        protected String estado;
        protected String precalificacion;
        protected String destino;

        /**
         * Gets the value of the arriba property.
         * 
         * @return
         *     possible object is
         *     {@link Vinculos.Vinculo.Arriba }
         *     
         */
        public Vinculos.Vinculo.Arriba getArriba() {
            return arriba;
        }

        /**
         * Sets the value of the arriba property.
         * 
         * @param value
         *     allowed object is
         *     {@link Vinculos.Vinculo.Arriba }
         *     
         */
        public void setArriba(Vinculos.Vinculo.Arriba value) {
            this.arriba = value;
        }

        /**
         * Gets the value of the abajo property.
         * 
         * @return
         *     possible object is
         *     {@link Vinculos.Vinculo.Abajo }
         *     
         */
        public Vinculos.Vinculo.Abajo getAbajo() {
            return abajo;
        }

        /**
         * Sets the value of the abajo property.
         * 
         * @param value
         *     allowed object is
         *     {@link Vinculos.Vinculo.Abajo }
         *     
         */
        public void setAbajo(Vinculos.Vinculo.Abajo value) {
            this.abajo = value;
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

        /**
         * Gets the value of the precalificacion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPrecalificacion() {
            return precalificacion;
        }

        /**
         * Sets the value of the precalificacion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrecalificacion(String value) {
            this.precalificacion = value;
        }

        /**
         * Gets the value of the destino property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDestino() {
            return destino;
        }

        /**
         * Sets the value of the destino property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDestino(String value) {
            this.destino = value;
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
         *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}equipoId"/>
         *         &lt;element name="extremo">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}central"/>
         *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}zona"/>
         *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}equipo"/>
         *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}cable"/>
         *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}par"/>
         *                 &lt;/sequence>
         *                 &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
            "equipoId",
            "extremo"
        })
        public static class Abajo {

            @XmlElement(required = true)
            protected String equipoId;
            @XmlElement(required = true)
            protected Vinculos.Vinculo.Abajo.Extremo extremo;

            /**
             * Gets the value of the equipoId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEquipoId() {
                return equipoId;
            }

            /**
             * Sets the value of the equipoId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEquipoId(String value) {
                this.equipoId = value;
            }

            /**
             * Gets the value of the extremo property.
             * 
             * @return
             *     possible object is
             *     {@link Vinculos.Vinculo.Abajo.Extremo }
             *     
             */
            public Vinculos.Vinculo.Abajo.Extremo getExtremo() {
                return extremo;
            }

            /**
             * Sets the value of the extremo property.
             * 
             * @param value
             *     allowed object is
             *     {@link Vinculos.Vinculo.Abajo.Extremo }
             *     
             */
            public void setExtremo(Vinculos.Vinculo.Abajo.Extremo value) {
                this.extremo = value;
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
             *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}central"/>
             *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}zona"/>
             *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}equipo"/>
             *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}cable"/>
             *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}par"/>
             *       &lt;/sequence>
             *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "central",
                "zona",
                "equipo",
                "cable",
                "par"
            })
            public static class Extremo {

                @XmlElement(required = true)
                protected String central;
                @XmlElement(required = true)
                protected String zona;
                @XmlElement(required = true)
                protected String equipo;
                @XmlElement(required = true)
                protected String cable;
                @XmlElement(required = true)
                protected String par;
                @XmlAttribute(name = "id", required = true)
                protected String id;

                /**
                 * Gets the value of the central property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCentral() {
                    return central;
                }

                /**
                 * Sets the value of the central property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCentral(String value) {
                    this.central = value;
                }

                /**
                 * Gets the value of the zona property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getZona() {
                    return zona;
                }

                /**
                 * Sets the value of the zona property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setZona(String value) {
                    this.zona = value;
                }

                /**
                 * Gets the value of the equipo property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getEquipo() {
                    return equipo;
                }

                /**
                 * Sets the value of the equipo property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setEquipo(String value) {
                    this.equipo = value;
                }

                /**
                 * Gets the value of the cable property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCable() {
                    return cable;
                }

                /**
                 * Sets the value of the cable property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCable(String value) {
                    this.cable = value;
                }

                /**
                 * Gets the value of the par property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getPar() {
                    return par;
                }

                /**
                 * Sets the value of the par property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setPar(String value) {
                    this.par = value;
                }

                /**
                 * Gets the value of the id property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getId() {
                    return id;
                }

                /**
                 * Sets the value of the id property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setId(String value) {
                    this.id = value;
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
         *       &lt;sequence>
         *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}equipoId"/>
         *         &lt;element name="extremo">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}central"/>
         *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}zona"/>
         *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}equipo"/>
         *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}cable"/>
         *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}par"/>
         *                 &lt;/sequence>
         *                 &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
            "equipoId",
            "extremo"
        })
        public static class Arriba {

            @XmlElement(required = true)
            protected String equipoId;
            @XmlElement(required = true)
            protected Vinculos.Vinculo.Arriba.Extremo extremo;

            /**
             * Gets the value of the equipoId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEquipoId() {
                return equipoId;
            }

            /**
             * Sets the value of the equipoId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEquipoId(String value) {
                this.equipoId = value;
            }

            /**
             * Gets the value of the extremo property.
             * 
             * @return
             *     possible object is
             *     {@link Vinculos.Vinculo.Arriba.Extremo }
             *     
             */
            public Vinculos.Vinculo.Arriba.Extremo getExtremo() {
                return extremo;
            }

            /**
             * Sets the value of the extremo property.
             * 
             * @param value
             *     allowed object is
             *     {@link Vinculos.Vinculo.Arriba.Extremo }
             *     
             */
            public void setExtremo(Vinculos.Vinculo.Arriba.Extremo value) {
                this.extremo = value;
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
             *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}central"/>
             *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}zona"/>
             *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}equipo"/>
             *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}cable"/>
             *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}par"/>
             *       &lt;/sequence>
             *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "central",
                "zona",
                "equipo",
                "cable",
                "par"
            })
            public static class Extremo {

                @XmlElement(required = true)
                protected String central;
                @XmlElement(required = true)
                protected String zona;
                @XmlElement(required = true)
                protected String equipo;
                @XmlElement(required = true)
                protected String cable;
                @XmlElement(required = true)
                protected String par;
                @XmlAttribute(name = "id", required = true)
                protected String id;

                /**
                 * Gets the value of the central property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCentral() {
                    return central;
                }

                /**
                 * Sets the value of the central property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCentral(String value) {
                    this.central = value;
                }

                /**
                 * Gets the value of the zona property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getZona() {
                    return zona;
                }

                /**
                 * Sets the value of the zona property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setZona(String value) {
                    this.zona = value;
                }

                /**
                 * Gets the value of the equipo property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getEquipo() {
                    return equipo;
                }

                /**
                 * Sets the value of the equipo property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setEquipo(String value) {
                    this.equipo = value;
                }

                /**
                 * Gets the value of the cable property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCable() {
                    return cable;
                }

                /**
                 * Sets the value of the cable property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCable(String value) {
                    this.cable = value;
                }

                /**
                 * Gets the value of the par property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getPar() {
                    return par;
                }

                /**
                 * Sets the value of the par property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setPar(String value) {
                    this.par = value;
                }

                /**
                 * Gets the value of the id property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getId() {
                    return id;
                }

                /**
                 * Sets the value of the id property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setId(String value) {
                    this.id = value;
                }

            }

        }

    }

}
