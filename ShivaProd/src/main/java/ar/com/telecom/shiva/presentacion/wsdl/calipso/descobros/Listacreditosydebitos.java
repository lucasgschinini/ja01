
package ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listacreditosydebitos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listacreditosydebitos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="detallecreditosydebitos" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="detalleGen" type="{urn:WSDL}detalleGeneral"/>
 *                   &lt;element name="detalleOri" type="{urn:WSDL}detalleOriginal"/>
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
@XmlType(name = "listacreditosydebitos", propOrder = {
    "detallecreditosydebitos"
})
public class Listacreditosydebitos {

    @XmlElement(required = true)
    protected List<Listacreditosydebitos.Detallecreditosydebitos> detallecreditosydebitos;

    /**
     * Gets the value of the detallecreditosydebitos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detallecreditosydebitos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetallecreditosydebitos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Listacreditosydebitos.Detallecreditosydebitos }
     * 
     * 
     */
    public List<Listacreditosydebitos.Detallecreditosydebitos> getDetallecreditosydebitos() {
        if (detallecreditosydebitos == null) {
            detallecreditosydebitos = new ArrayList<Listacreditosydebitos.Detallecreditosydebitos>();
        }
        return this.detallecreditosydebitos;
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
     *         &lt;element name="detalleGen" type="{urn:WSDL}detalleGeneral"/>
     *         &lt;element name="detalleOri" type="{urn:WSDL}detalleOriginal"/>
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
        "detalleGen",
        "detalleOri"
    })
    public static class Detallecreditosydebitos {

        @XmlElement(required = true)
        protected DetalleGeneral detalleGen;
        @XmlElement(required = true)
        protected DetalleOriginal detalleOri;

        /**
         * Gets the value of the detalleGen property.
         * 
         * @return
         *     possible object is
         *     {@link DetalleGeneral }
         *     
         */
        public DetalleGeneral getDetalleGen() {
            return detalleGen;
        }

        /**
         * Sets the value of the detalleGen property.
         * 
         * @param value
         *     allowed object is
         *     {@link DetalleGeneral }
         *     
         */
        public void setDetalleGen(DetalleGeneral value) {
            this.detalleGen = value;
        }

        /**
         * Gets the value of the detalleOri property.
         * 
         * @return
         *     possible object is
         *     {@link DetalleOriginal }
         *     
         */
        public DetalleOriginal getDetalleOri() {
            return detalleOri;
        }

        /**
         * Sets the value of the detalleOri property.
         * 
         * @param value
         *     allowed object is
         *     {@link DetalleOriginal }
         *     
         */
        public void setDetalleOri(DetalleOriginal value) {
            this.detalleOri = value;
        }

    }

}
