
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="servicioAdicional" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}codigo" minOccurs="0"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}descripcion" minOccurs="0"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}fechaHora" minOccurs="0"/>
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
    "servicioAdicional"
})
@XmlRootElement(name = "serviciosAdicionales")
public class ServiciosAdicionales {

    protected List<ServiciosAdicionales.ServicioAdicional> servicioAdicional;

    /**
     * Gets the value of the servicioAdicional property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the servicioAdicional property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServicioAdicional().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiciosAdicionales.ServicioAdicional }
     * 
     * 
     */
    public List<ServiciosAdicionales.ServicioAdicional> getServicioAdicional() {
        if (servicioAdicional == null) {
            servicioAdicional = new ArrayList<ServiciosAdicionales.ServicioAdicional>();
        }
        return this.servicioAdicional;
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
        "fechaHora"
    })
    public static class ServicioAdicional {

        protected String codigo;
        protected String descripcion;
        protected String fechaHora;

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

    }

}
