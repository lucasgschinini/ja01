
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
 *         &lt;element name="producto" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="spn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="idAsset" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "producto"
})
@XmlRootElement(name = "productosComerciales")
public class ProductosComerciales {

    protected List<ProductosComerciales.Producto> producto;

    /**
     * Gets the value of the producto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the producto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProducto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductosComerciales.Producto }
     * 
     * 
     */
    public List<ProductosComerciales.Producto> getProducto() {
        if (producto == null) {
            producto = new ArrayList<ProductosComerciales.Producto>();
        }
        return this.producto;
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
     *       &lt;attribute name="spn" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="idAsset" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    public static class Producto {

        @XmlAttribute(name = "spn")
        protected String spn;
        @XmlAttribute(name = "idAsset")
        protected String idAsset;
        @XmlAttribute(name = "descripcion")
        protected String descripcion;

        /**
         * Gets the value of the spn property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSpn() {
            return spn;
        }

        /**
         * Sets the value of the spn property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSpn(String value) {
            this.spn = value;
        }

        /**
         * Gets the value of the idAsset property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdAsset() {
            return idAsset;
        }

        /**
         * Sets the value of the idAsset property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdAsset(String value) {
            this.idAsset = value;
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
