
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

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
 *         &lt;element name="domicilioFincaFijo">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="tipoZona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="tipoDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoLocalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoZona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="altura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="alturaInterna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="alturaRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="alturaKM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoCalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoCalleRuta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoCalleAtras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoCalleInterna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="circunscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="comentarioDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="distancia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoDistAltura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoDistAlturaRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoEntreCalle1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoEntreCalle2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoEntreRio1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoEntreRio2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="escalera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="latitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="longitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="lote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="manzana" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="muelle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="municipioPartido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nombreEdificio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="establecimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nombreLocal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="numeroEdificio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="numeroLocal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="numeroGrupoEdificio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="numeroPiso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="numeroSector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoOrientacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoPlanta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoRefGPS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoRio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoRioAtras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoSeccionDelta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoTipoCalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoTipoEdificio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoTipoGrupoEdificio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoTipoLocal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoTipoPiso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoTipoSector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoTipoSitio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoUbicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="zonaBarrial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="kilometro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoSeccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoPostal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="alturaKmRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nombreCalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="barrioNuevo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="alturaViaPublica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="alturaInternaRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/all>
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
    "domicilioFincaFijo"
})
@XmlRootElement(name = "domiciliosFincaFijo")
public class DomiciliosFincaFijo {

    @XmlElement(required = true)
    protected DomiciliosFincaFijo.DomicilioFincaFijo domicilioFincaFijo;

    /**
     * Gets the value of the domicilioFincaFijo property.
     * 
     * @return
     *     possible object is
     *     {@link DomiciliosFincaFijo.DomicilioFincaFijo }
     *     
     */
    public DomiciliosFincaFijo.DomicilioFincaFijo getDomicilioFincaFijo() {
        return domicilioFincaFijo;
    }

    /**
     * Sets the value of the domicilioFincaFijo property.
     * 
     * @param value
     *     allowed object is
     *     {@link DomiciliosFincaFijo.DomicilioFincaFijo }
     *     
     */
    public void setDomicilioFincaFijo(DomiciliosFincaFijo.DomicilioFincaFijo value) {
        this.domicilioFincaFijo = value;
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
     *         &lt;element name="tipoZona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="tipoDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoLocalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoZona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="altura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="alturaInterna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="alturaRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="alturaKM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoCalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoCalleRuta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoCalleAtras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoCalleInterna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="circunscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="comentarioDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="distancia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoDistAltura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoDistAlturaRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoEntreCalle1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoEntreCalle2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoEntreRio1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoEntreRio2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="escalera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="latitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="longitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="lote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="manzana" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="muelle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="municipioPartido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nombreEdificio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="establecimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nombreLocal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="numeroEdificio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="numeroLocal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="numeroGrupoEdificio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="numeroPiso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="numeroSector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoOrientacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoPlanta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoRefGPS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoRio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoRioAtras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoSeccionDelta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoTipoCalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoTipoEdificio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoTipoGrupoEdificio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoTipoLocal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoTipoPiso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoTipoSector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoTipoSitio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoUbicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="zonaBarrial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="kilometro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoSeccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoPostal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="alturaKmRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nombreCalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="barrioNuevo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="alturaViaPublica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="alturaInternaRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/all>
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

    })
    public static class DomicilioFincaFijo {

        protected String tipoZona;
        protected String tipoDomicilio;
        protected String codigoLocalidad;
        protected String codigoZona;
        protected String altura;
        protected String alturaInterna;
        protected String alturaRef;
        protected String alturaKM;
        protected String area;
        protected String codigoCalle;
        protected String codigoCalleRuta;
        protected String codigoCalleAtras;
        protected String codigoCalleInterna;
        protected String circunscripcion;
        protected String comentarioDomicilio;
        protected String departamento;
        protected String distancia;
        protected String codigoDistAltura;
        protected String codigoDistAlturaRef;
        protected String codigoEntreCalle1;
        protected String codigoEntreCalle2;
        protected String codigoEntreRio1;
        protected String codigoEntreRio2;
        protected String escalera;
        protected String latitud;
        protected String longitud;
        protected String lote;
        protected String manzana;
        protected String muelle;
        protected String municipioPartido;
        protected String nombreEdificio;
        protected String establecimiento;
        protected String nombreLocal;
        protected String numeroEdificio;
        protected String numeroLocal;
        protected String numeroGrupoEdificio;
        protected String numeroPiso;
        protected String numeroSector;
        protected String codigoOrientacion;
        protected String codigoPlanta;
        protected String codigoRefGPS;
        protected String codigoRegion;
        protected String codigoRio;
        protected String codigoRioAtras;
        protected String codigoSeccionDelta;
        protected String codigoTipoCalle;
        protected String codigoTipoEdificio;
        protected String codigoTipoGrupoEdificio;
        protected String codigoTipoLocal;
        protected String codigoTipoPiso;
        protected String codigoTipoSector;
        protected String codigoTipoSitio;
        protected String codigoUbicacion;
        protected String zonaBarrial;
        protected String kilometro;
        protected String codigoSeccion;
        protected String codigoPostal;
        protected String alturaKmRef;
        protected String nombreCalle;
        protected String barrioNuevo;
        protected String alturaViaPublica;
        protected String alturaInternaRef;
        protected String codigoProvincia;
        @XmlAttribute(name = "tipo", required = true)
        protected String tipo;

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
         * Gets the value of the tipoDomicilio property.
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
         * Gets the value of the codigoLocalidad property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoLocalidad() {
            return codigoLocalidad;
        }

        /**
         * Sets the value of the codigoLocalidad property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoLocalidad(String value) {
            this.codigoLocalidad = value;
        }

        /**
         * Gets the value of the codigoZona property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoZona() {
            return codigoZona;
        }

        /**
         * Sets the value of the codigoZona property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoZona(String value) {
            this.codigoZona = value;
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
         * Gets the value of the alturaInterna property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAlturaInterna() {
            return alturaInterna;
        }

        /**
         * Sets the value of the alturaInterna property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAlturaInterna(String value) {
            this.alturaInterna = value;
        }

        /**
         * Gets the value of the alturaRef property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAlturaRef() {
            return alturaRef;
        }

        /**
         * Sets the value of the alturaRef property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAlturaRef(String value) {
            this.alturaRef = value;
        }

        /**
         * Gets the value of the alturaKM property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAlturaKM() {
            return alturaKM;
        }

        /**
         * Sets the value of the alturaKM property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAlturaKM(String value) {
            this.alturaKM = value;
        }

        /**
         * Gets the value of the area property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getArea() {
            return area;
        }

        /**
         * Sets the value of the area property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setArea(String value) {
            this.area = value;
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
         * Gets the value of the codigoCalleRuta property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoCalleRuta() {
            return codigoCalleRuta;
        }

        /**
         * Sets the value of the codigoCalleRuta property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoCalleRuta(String value) {
            this.codigoCalleRuta = value;
        }

        /**
         * Gets the value of the codigoCalleAtras property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoCalleAtras() {
            return codigoCalleAtras;
        }

        /**
         * Sets the value of the codigoCalleAtras property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoCalleAtras(String value) {
            this.codigoCalleAtras = value;
        }

        /**
         * Gets the value of the codigoCalleInterna property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoCalleInterna() {
            return codigoCalleInterna;
        }

        /**
         * Sets the value of the codigoCalleInterna property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoCalleInterna(String value) {
            this.codigoCalleInterna = value;
        }

        /**
         * Gets the value of the circunscripcion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCircunscripcion() {
            return circunscripcion;
        }

        /**
         * Sets the value of the circunscripcion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCircunscripcion(String value) {
            this.circunscripcion = value;
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
         * Gets the value of the departamento property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDepartamento() {
            return departamento;
        }

        /**
         * Sets the value of the departamento property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDepartamento(String value) {
            this.departamento = value;
        }

        /**
         * Gets the value of the distancia property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDistancia() {
            return distancia;
        }

        /**
         * Sets the value of the distancia property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDistancia(String value) {
            this.distancia = value;
        }

        /**
         * Gets the value of the codigoDistAltura property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoDistAltura() {
            return codigoDistAltura;
        }

        /**
         * Sets the value of the codigoDistAltura property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoDistAltura(String value) {
            this.codigoDistAltura = value;
        }

        /**
         * Gets the value of the codigoDistAlturaRef property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoDistAlturaRef() {
            return codigoDistAlturaRef;
        }

        /**
         * Sets the value of the codigoDistAlturaRef property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoDistAlturaRef(String value) {
            this.codigoDistAlturaRef = value;
        }

        /**
         * Gets the value of the codigoEntreCalle1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoEntreCalle1() {
            return codigoEntreCalle1;
        }

        /**
         * Sets the value of the codigoEntreCalle1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoEntreCalle1(String value) {
            this.codigoEntreCalle1 = value;
        }

        /**
         * Gets the value of the codigoEntreCalle2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoEntreCalle2() {
            return codigoEntreCalle2;
        }

        /**
         * Sets the value of the codigoEntreCalle2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoEntreCalle2(String value) {
            this.codigoEntreCalle2 = value;
        }

        /**
         * Gets the value of the codigoEntreRio1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoEntreRio1() {
            return codigoEntreRio1;
        }

        /**
         * Sets the value of the codigoEntreRio1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoEntreRio1(String value) {
            this.codigoEntreRio1 = value;
        }

        /**
         * Gets the value of the codigoEntreRio2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoEntreRio2() {
            return codigoEntreRio2;
        }

        /**
         * Sets the value of the codigoEntreRio2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoEntreRio2(String value) {
            this.codigoEntreRio2 = value;
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
         * Gets the value of the latitud property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLatitud() {
            return latitud;
        }

        /**
         * Sets the value of the latitud property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLatitud(String value) {
            this.latitud = value;
        }

        /**
         * Gets the value of the longitud property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLongitud() {
            return longitud;
        }

        /**
         * Sets the value of the longitud property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLongitud(String value) {
            this.longitud = value;
        }

        /**
         * Gets the value of the lote property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLote() {
            return lote;
        }

        /**
         * Sets the value of the lote property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLote(String value) {
            this.lote = value;
        }

        /**
         * Gets the value of the manzana property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getManzana() {
            return manzana;
        }

        /**
         * Sets the value of the manzana property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setManzana(String value) {
            this.manzana = value;
        }

        /**
         * Gets the value of the muelle property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMuelle() {
            return muelle;
        }

        /**
         * Sets the value of the muelle property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMuelle(String value) {
            this.muelle = value;
        }

        /**
         * Gets the value of the municipioPartido property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMunicipioPartido() {
            return municipioPartido;
        }

        /**
         * Sets the value of the municipioPartido property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMunicipioPartido(String value) {
            this.municipioPartido = value;
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
         * Gets the value of the establecimiento property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEstablecimiento() {
            return establecimiento;
        }

        /**
         * Sets the value of the establecimiento property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEstablecimiento(String value) {
            this.establecimiento = value;
        }

        /**
         * Gets the value of the nombreLocal property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombreLocal() {
            return nombreLocal;
        }

        /**
         * Sets the value of the nombreLocal property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombreLocal(String value) {
            this.nombreLocal = value;
        }

        /**
         * Gets the value of the numeroEdificio property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroEdificio() {
            return numeroEdificio;
        }

        /**
         * Sets the value of the numeroEdificio property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroEdificio(String value) {
            this.numeroEdificio = value;
        }

        /**
         * Gets the value of the numeroLocal property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroLocal() {
            return numeroLocal;
        }

        /**
         * Sets the value of the numeroLocal property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroLocal(String value) {
            this.numeroLocal = value;
        }

        /**
         * Gets the value of the numeroGrupoEdificio property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroGrupoEdificio() {
            return numeroGrupoEdificio;
        }

        /**
         * Sets the value of the numeroGrupoEdificio property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroGrupoEdificio(String value) {
            this.numeroGrupoEdificio = value;
        }

        /**
         * Gets the value of the numeroPiso property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroPiso() {
            return numeroPiso;
        }

        /**
         * Sets the value of the numeroPiso property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroPiso(String value) {
            this.numeroPiso = value;
        }

        /**
         * Gets the value of the numeroSector property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroSector() {
            return numeroSector;
        }

        /**
         * Sets the value of the numeroSector property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroSector(String value) {
            this.numeroSector = value;
        }

        /**
         * Gets the value of the codigoOrientacion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoOrientacion() {
            return codigoOrientacion;
        }

        /**
         * Sets the value of the codigoOrientacion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoOrientacion(String value) {
            this.codigoOrientacion = value;
        }

        /**
         * Gets the value of the codigoPlanta property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoPlanta() {
            return codigoPlanta;
        }

        /**
         * Sets the value of the codigoPlanta property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoPlanta(String value) {
            this.codigoPlanta = value;
        }

        /**
         * Gets the value of the codigoRefGPS property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoRefGPS() {
            return codigoRefGPS;
        }

        /**
         * Sets the value of the codigoRefGPS property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoRefGPS(String value) {
            this.codigoRefGPS = value;
        }

        /**
         * Gets the value of the codigoRegion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoRegion() {
            return codigoRegion;
        }

        /**
         * Sets the value of the codigoRegion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoRegion(String value) {
            this.codigoRegion = value;
        }

        /**
         * Gets the value of the codigoRio property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoRio() {
            return codigoRio;
        }

        /**
         * Sets the value of the codigoRio property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoRio(String value) {
            this.codigoRio = value;
        }

        /**
         * Gets the value of the codigoRioAtras property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoRioAtras() {
            return codigoRioAtras;
        }

        /**
         * Sets the value of the codigoRioAtras property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoRioAtras(String value) {
            this.codigoRioAtras = value;
        }

        /**
         * Gets the value of the codigoSeccionDelta property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoSeccionDelta() {
            return codigoSeccionDelta;
        }

        /**
         * Sets the value of the codigoSeccionDelta property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoSeccionDelta(String value) {
            this.codigoSeccionDelta = value;
        }

        /**
         * Gets the value of the codigoTipoCalle property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoTipoCalle() {
            return codigoTipoCalle;
        }

        /**
         * Sets the value of the codigoTipoCalle property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoTipoCalle(String value) {
            this.codigoTipoCalle = value;
        }

        /**
         * Gets the value of the codigoTipoEdificio property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoTipoEdificio() {
            return codigoTipoEdificio;
        }

        /**
         * Sets the value of the codigoTipoEdificio property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoTipoEdificio(String value) {
            this.codigoTipoEdificio = value;
        }

        /**
         * Gets the value of the codigoTipoGrupoEdificio property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoTipoGrupoEdificio() {
            return codigoTipoGrupoEdificio;
        }

        /**
         * Sets the value of the codigoTipoGrupoEdificio property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoTipoGrupoEdificio(String value) {
            this.codigoTipoGrupoEdificio = value;
        }

        /**
         * Gets the value of the codigoTipoLocal property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoTipoLocal() {
            return codigoTipoLocal;
        }

        /**
         * Sets the value of the codigoTipoLocal property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoTipoLocal(String value) {
            this.codigoTipoLocal = value;
        }

        /**
         * Gets the value of the codigoTipoPiso property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoTipoPiso() {
            return codigoTipoPiso;
        }

        /**
         * Sets the value of the codigoTipoPiso property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoTipoPiso(String value) {
            this.codigoTipoPiso = value;
        }

        /**
         * Gets the value of the codigoTipoSector property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoTipoSector() {
            return codigoTipoSector;
        }

        /**
         * Sets the value of the codigoTipoSector property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoTipoSector(String value) {
            this.codigoTipoSector = value;
        }

        /**
         * Gets the value of the codigoTipoSitio property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoTipoSitio() {
            return codigoTipoSitio;
        }

        /**
         * Sets the value of the codigoTipoSitio property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoTipoSitio(String value) {
            this.codigoTipoSitio = value;
        }

        /**
         * Gets the value of the codigoUbicacion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoUbicacion() {
            return codigoUbicacion;
        }

        /**
         * Sets the value of the codigoUbicacion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoUbicacion(String value) {
            this.codigoUbicacion = value;
        }

        /**
         * Gets the value of the zonaBarrial property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZonaBarrial() {
            return zonaBarrial;
        }

        /**
         * Sets the value of the zonaBarrial property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZonaBarrial(String value) {
            this.zonaBarrial = value;
        }

        /**
         * Gets the value of the kilometro property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKilometro() {
            return kilometro;
        }

        /**
         * Sets the value of the kilometro property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKilometro(String value) {
            this.kilometro = value;
        }

        /**
         * Gets the value of the codigoSeccion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoSeccion() {
            return codigoSeccion;
        }

        /**
         * Sets the value of the codigoSeccion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoSeccion(String value) {
            this.codigoSeccion = value;
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
         * Gets the value of the alturaKmRef property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAlturaKmRef() {
            return alturaKmRef;
        }

        /**
         * Sets the value of the alturaKmRef property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAlturaKmRef(String value) {
            this.alturaKmRef = value;
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
         * Gets the value of the barrioNuevo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBarrioNuevo() {
            return barrioNuevo;
        }

        /**
         * Sets the value of the barrioNuevo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBarrioNuevo(String value) {
            this.barrioNuevo = value;
        }

        /**
         * Gets the value of the alturaViaPublica property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAlturaViaPublica() {
            return alturaViaPublica;
        }

        /**
         * Sets the value of the alturaViaPublica property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAlturaViaPublica(String value) {
            this.alturaViaPublica = value;
        }

        /**
         * Gets the value of the alturaInternaRef property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAlturaInternaRef() {
            return alturaInternaRef;
        }

        /**
         * Sets the value of the alturaInternaRef property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAlturaInternaRef(String value) {
            this.alturaInternaRef = value;
        }

        /**
         * Gets the value of the codigoProvincia property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoProvincia() {
            return codigoProvincia;
        }

        /**
         * Sets the value of the codigoProvincia property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoProvincia(String value) {
            this.codigoProvincia = value;
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
