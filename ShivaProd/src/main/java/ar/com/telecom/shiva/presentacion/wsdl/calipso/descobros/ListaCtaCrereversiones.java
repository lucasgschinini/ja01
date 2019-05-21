
package ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ar.com.telecom.shiva.base.soa.xmlAdapters.BigIntegerXmlAdapter;


/**
 * <p>Java class for listaCtaCrereversiones complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listaCtaCrereversiones">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="detalle" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="idCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
@XmlType(name = "listaCtaCrereversiones", propOrder = {
    "detalle"
})
public class ListaCtaCrereversiones {

    @XmlElement(required = true)
    protected List<ListaCtaCrereversiones.Detalle> detalle;

    /**
     * Gets the value of the detalle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ListaCtaCrereversiones.Detalle }
     * 
     * 
     */
    public List<ListaCtaCrereversiones.Detalle> getDetalle() {
        if (detalle == null) {
            detalle = new ArrayList<ListaCtaCrereversiones.Detalle>();
        }
        return this.detalle;
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
     *         &lt;element name="idCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
        "idCobranza"
    })
    public static class Detalle {

        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
        @XmlSchemaType(name = "integer")
        protected BigInteger idCobranza;

        /**
         * Gets the value of the idCobranza property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public BigInteger getIdCobranza() {
            return idCobranza;
        }

        /**
         * Sets the value of the idCobranza property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdCobranza(BigInteger value) {
            this.idCobranza = value;
        }

    }

}
