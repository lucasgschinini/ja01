
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.cliente;

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
 *         &lt;element name="domicilio" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="codigoFinca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoCiudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="descProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="descLocalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoMunicipio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="descMunicipio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="casillaCorreo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoCalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nombreCalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="altura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nombreEdificio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="escalera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="piso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="puerta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoPostal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ampliacionDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="comentarioDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "domicilio"
})
@XmlRootElement(name = "clDomicilios")
public class ClDomicilios {

    protected List<ClDomicilios.Domicilio> domicilio;

    /**
     * Gets the value of the domicilio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the domicilio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDomicilio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClDomicilios.Domicilio }
     * 
     * 
     */
    public List<ClDomicilios.Domicilio> getDomicilio() {
        if (domicilio == null) {
            domicilio = new ArrayList<ClDomicilios.Domicilio>();
        }
        return this.domicilio;
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
     *         &lt;element name="codigoFinca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoCiudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descLocalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoMunicipio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descMunicipio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="casillaCorreo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoCalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nombreCalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="altura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nombreEdificio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="escalera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="piso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="puerta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoPostal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ampliacionDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="comentarioDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "codigoFinca",
        "codigoCiudad",
        "descProvincia",
        "descLocalidad",
        "codigoMunicipio",
        "descMunicipio",
        "pais",
        "casillaCorreo",
        "codigoCalle",
        "nombreCalle",
        "altura",
        "nombreEdificio",
        "escalera",
        "piso",
        "puerta",
        "codigoPostal",
        "ampliacionDomicilio",
        "comentarioDomicilio"
    })
    public static class Domicilio {

        protected String codigoFinca;
        protected String codigoCiudad;
        protected String descProvincia;
        protected String descLocalidad;
        protected String codigoMunicipio;
        protected String descMunicipio;
        protected String pais;
        protected String casillaCorreo;
        protected String codigoCalle;
        protected String nombreCalle;
        protected String altura;
        protected String nombreEdificio;
        protected String escalera;
        protected String piso;
        protected String puerta;
        protected String codigoPostal;
        protected String ampliacionDomicilio;
        protected String comentarioDomicilio;
        @XmlAttribute(name = "tipo", required = true)
        protected String tipo;

        /**
         * Gets the value of the codigoFinca property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoFinca() {
            return codigoFinca;
        }

        /**
         * Sets the value of the codigoFinca property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoFinca(String value) {
            this.codigoFinca = value;
        }

        /**
         * Gets the value of the codigoCiudad property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoCiudad() {
            return codigoCiudad;
        }

        /**
         * Sets the value of the codigoCiudad property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoCiudad(String value) {
            this.codigoCiudad = value;
        }

        /**
         * Gets the value of the descProvincia property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescProvincia() {
            return descProvincia;
        }

        /**
         * Sets the value of the descProvincia property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescProvincia(String value) {
            this.descProvincia = value;
        }

        /**
         * Gets the value of the descLocalidad property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescLocalidad() {
            return descLocalidad;
        }

        /**
         * Sets the value of the descLocalidad property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescLocalidad(String value) {
            this.descLocalidad = value;
        }

        /**
         * Gets the value of the codigoMunicipio property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoMunicipio() {
            return codigoMunicipio;
        }

        /**
         * Sets the value of the codigoMunicipio property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoMunicipio(String value) {
            this.codigoMunicipio = value;
        }

        /**
         * Gets the value of the descMunicipio property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescMunicipio() {
            return descMunicipio;
        }

        /**
         * Sets the value of the descMunicipio property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescMunicipio(String value) {
            this.descMunicipio = value;
        }

        /**
         * Gets the value of the pais property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPais() {
            return pais;
        }

        /**
         * Sets the value of the pais property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPais(String value) {
            this.pais = value;
        }

        /**
         * Gets the value of the casillaCorreo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCasillaCorreo() {
            return casillaCorreo;
        }

        /**
         * Sets the value of the casillaCorreo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCasillaCorreo(String value) {
            this.casillaCorreo = value;
        }

        /**
         * Gets the value of the codigoCalle property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoCalle() {
            return codigoCalle;
        }

        /**
         * Sets the value of the codigoCalle property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoCalle(String value) {
            this.codigoCalle = value;
        }

        /**
         * Gets the value of the nombreCalle property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombreCalle() {
            return nombreCalle;
        }

        /**
         * Sets the value of the nombreCalle property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombreCalle(String value) {
            this.nombreCalle = value;
        }

        /**
         * Gets the value of the altura property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAltura() {
            return altura;
        }

        /**
         * Sets the value of the altura property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAltura(String value) {
            this.altura = value;
        }

        /**
         * Gets the value of the nombreEdificio property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombreEdificio() {
            return nombreEdificio;
        }

        /**
         * Sets the value of the nombreEdificio property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombreEdificio(String value) {
            this.nombreEdificio = value;
        }

        /**
         * Gets the value of the escalera property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEscalera() {
            return escalera;
        }

        /**
         * Sets the value of the escalera property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEscalera(String value) {
            this.escalera = value;
        }

        /**
         * Gets the value of the piso property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPiso() {
            return piso;
        }

        /**
         * Sets the value of the piso property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPiso(String value) {
            this.piso = value;
        }

        /**
         * Gets the value of the puerta property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPuerta() {
            return puerta;
        }

        /**
         * Sets the value of the puerta property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPuerta(String value) {
            this.puerta = value;
        }

        /**
         * Gets the value of the codigoPostal property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoPostal() {
            return codigoPostal;
        }

        /**
         * Sets the value of the codigoPostal property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoPostal(String value) {
            this.codigoPostal = value;
        }

        /**
         * Gets the value of the ampliacionDomicilio property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAmpliacionDomicilio() {
            return ampliacionDomicilio;
        }

        /**
         * Sets the value of the ampliacionDomicilio property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAmpliacionDomicilio(String value) {
            this.ampliacionDomicilio = value;
        }

        /**
         * Gets the value of the comentarioDomicilio property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getComentarioDomicilio() {
            return comentarioDomicilio;
        }

        /**
         * Sets the value of the comentarioDomicilio property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setComentarioDomicilio(String value) {
            this.comentarioDomicilio = value;
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

    }

}
