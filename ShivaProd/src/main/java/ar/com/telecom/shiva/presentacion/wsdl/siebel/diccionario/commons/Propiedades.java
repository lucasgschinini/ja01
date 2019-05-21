
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
 *         &lt;element name="propiedad" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "propiedades")
public class Propiedades {

    @XmlElement(required = true)
    protected List<Propiedades.Propiedad> propiedad;

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
     * {@link Propiedades.Propiedad }
     * 
     * 
     */
    public List<Propiedades.Propiedad> getPropiedad() {
        if (propiedad == null) {
            propiedad = new ArrayList<Propiedades.Propiedad>();
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
     *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        protected String descripcion;

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
