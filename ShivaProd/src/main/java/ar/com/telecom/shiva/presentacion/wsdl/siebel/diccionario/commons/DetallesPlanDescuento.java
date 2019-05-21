
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

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
 *         &lt;element name="detallePlanDescuento" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="numeroElegido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="tipoServicioRedID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="fechaInicioVigenciaNumeroElegido" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   &lt;element name="fechaFinVigenciaNumeroElegido" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   &lt;element name="nombreContactoAsociado" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "detallePlanDescuento"
})
@XmlRootElement(name = "detallesPlanDescuento")
public class DetallesPlanDescuento {

    protected List<DetallesPlanDescuento.DetallePlanDescuento> detallePlanDescuento;

    /**
     * Gets the value of the detallePlanDescuento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detallePlanDescuento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetallePlanDescuento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetallesPlanDescuento.DetallePlanDescuento }
     * 
     * 
     */
    public List<DetallesPlanDescuento.DetallePlanDescuento> getDetallePlanDescuento() {
        if (detallePlanDescuento == null) {
            detallePlanDescuento = new ArrayList<DetallesPlanDescuento.DetallePlanDescuento>();
        }
        return this.detallePlanDescuento;
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
     *         &lt;element name="numeroElegido" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="tipoServicioRedID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="fechaInicioVigenciaNumeroElegido" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         &lt;element name="fechaFinVigenciaNumeroElegido" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         &lt;element name="nombreContactoAsociado" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class DetallePlanDescuento {

        @XmlElement(required = true)
        protected String numeroElegido;
        @XmlElement(required = true)
        protected String tipoServicioRedID;
        @XmlElement(required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar fechaInicioVigenciaNumeroElegido;
        @XmlElement(required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar fechaFinVigenciaNumeroElegido;
        @XmlElement(required = true)
        protected String nombreContactoAsociado;

        /**
         * Gets the value of the numeroElegido property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroElegido() {
            return numeroElegido;
        }

        /**
         * Sets the value of the numeroElegido property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroElegido(String value) {
            this.numeroElegido = value;
        }

        /**
         * Gets the value of the tipoServicioRedID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipoServicioRedID() {
            return tipoServicioRedID;
        }

        /**
         * Sets the value of the tipoServicioRedID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipoServicioRedID(String value) {
            this.tipoServicioRedID = value;
        }

        /**
         * Gets the value of the fechaInicioVigenciaNumeroElegido property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getFechaInicioVigenciaNumeroElegido() {
            return fechaInicioVigenciaNumeroElegido;
        }

        /**
         * Sets the value of the fechaInicioVigenciaNumeroElegido property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setFechaInicioVigenciaNumeroElegido(XMLGregorianCalendar value) {
            this.fechaInicioVigenciaNumeroElegido = value;
        }

        /**
         * Gets the value of the fechaFinVigenciaNumeroElegido property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getFechaFinVigenciaNumeroElegido() {
            return fechaFinVigenciaNumeroElegido;
        }

        /**
         * Sets the value of the fechaFinVigenciaNumeroElegido property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setFechaFinVigenciaNumeroElegido(XMLGregorianCalendar value) {
            this.fechaFinVigenciaNumeroElegido = value;
        }

        /**
         * Gets the value of the nombreContactoAsociado property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombreContactoAsociado() {
            return nombreContactoAsociado;
        }

        /**
         * Sets the value of the nombreContactoAsociado property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombreContactoAsociado(String value) {
            this.nombreContactoAsociado = value;
        }

    }

}
