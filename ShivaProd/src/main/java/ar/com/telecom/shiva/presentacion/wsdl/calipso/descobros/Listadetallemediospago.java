
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
 * <p>Java class for listadetallemediospago complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listadetallemediospago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DetMedioPago" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="IdMedioPago" type="{urn:WSDL}mediodepago"/>
 *                   &lt;element name="idDocumentoCuentasCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="DetResultadoMedioPago" type="{urn:WSDL}resultadoservicio"/>
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
@XmlType(name = "listadetallemediospago", propOrder = {
    "detMedioPago"
})
public class Listadetallemediospago {

    @XmlElement(name = "DetMedioPago", required = true)
    protected List<Listadetallemediospago.DetMedioPago> detMedioPago;

    /**
     * Gets the value of the detMedioPago property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detMedioPago property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetMedioPago().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Listadetallemediospago.DetMedioPago }
     * 
     * 
     */
    public List<Listadetallemediospago.DetMedioPago> getDetMedioPago() {
        if (detMedioPago == null) {
            detMedioPago = new ArrayList<Listadetallemediospago.DetMedioPago>();
        }
        return this.detMedioPago;
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
     *         &lt;element name="IdMedioPago" type="{urn:WSDL}mediodepago"/>
     *         &lt;element name="idDocumentoCuentasCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="DetResultadoMedioPago" type="{urn:WSDL}resultadoservicio"/>
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
        "idMedioPago",
        "idDocumentoCuentasCobranza",
        "detResultadoMedioPago"
    })
    public static class DetMedioPago {

        @XmlElement(name = "IdMedioPago", required = true)
        protected Mediodepago idMedioPago;
        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
        @XmlSchemaType(name = "integer")
        protected BigInteger idDocumentoCuentasCobranza;
        @XmlElement(name = "DetResultadoMedioPago", required = true)
        protected Resultadoservicio detResultadoMedioPago;

        /**
         * Gets the value of the idMedioPago property.
         * 
         * @return
         *     possible object is
         *     {@link Mediodepago }
         *     
         */
        public Mediodepago getIdMedioPago() {
            return idMedioPago;
        }

        /**
         * Sets the value of the idMedioPago property.
         * 
         * @param value
         *     allowed object is
         *     {@link Mediodepago }
         *     
         */
        public void setIdMedioPago(Mediodepago value) {
            this.idMedioPago = value;
        }

        /**
         * Gets the value of the idDocumentoCuentasCobranza property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public BigInteger getIdDocumentoCuentasCobranza() {
            return idDocumentoCuentasCobranza;
        }

        /**
         * Sets the value of the idDocumentoCuentasCobranza property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdDocumentoCuentasCobranza(BigInteger value) {
            this.idDocumentoCuentasCobranza = value;
        }

        /**
         * Gets the value of the detResultadoMedioPago property.
         * 
         * @return
         *     possible object is
         *     {@link Resultadoservicio }
         *     
         */
        public Resultadoservicio getDetResultadoMedioPago() {
            return detResultadoMedioPago;
        }

        /**
         * Sets the value of the detResultadoMedioPago property.
         * 
         * @param value
         *     allowed object is
         *     {@link Resultadoservicio }
         *     
         */
        public void setDetResultadoMedioPago(Resultadoservicio value) {
            this.detResultadoMedioPago = value;
        }

    }

}
