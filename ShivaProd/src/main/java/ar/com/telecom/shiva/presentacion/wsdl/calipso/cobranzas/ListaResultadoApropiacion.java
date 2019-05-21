
package ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listaResultadoApropiacion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listaResultadoApropiacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultadoApropiacion" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="resultado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="codigoerror" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="descripcionerror" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "listaResultadoApropiacion", propOrder = {
    "resultadoApropiacion"
})
public class ListaResultadoApropiacion {

    @XmlElement(required = true)
    protected List<ListaResultadoApropiacion.ResultadoApropiacion> resultadoApropiacion;

    /**
     * Gets the value of the resultadoApropiacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultadoApropiacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultadoApropiacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ListaResultadoApropiacion.ResultadoApropiacion }
     * 
     * 
     */
    public List<ListaResultadoApropiacion.ResultadoApropiacion> getResultadoApropiacion() {
        if (resultadoApropiacion == null) {
            resultadoApropiacion = new ArrayList<ListaResultadoApropiacion.ResultadoApropiacion>();
        }
        return this.resultadoApropiacion;
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
     *         &lt;element name="resultado" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="codigoerror" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="descripcionerror" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "resultado",
        "codigoerror",
        "descripcionerror"
    })
    public static class ResultadoApropiacion {

        @XmlElement(required = true)
        protected String resultado;
        @XmlElement(required = true)
        protected String codigoerror;
        @XmlElement(required = true)
        protected String descripcionerror;

        /**
         * Gets the value of the resultado property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getResultado() {
            return resultado;
        }

        /**
         * Sets the value of the resultado property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setResultado(String value) {
            this.resultado = value;
        }

        /**
         * Gets the value of the codigoerror property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoerror() {
            return codigoerror;
        }

        /**
         * Sets the value of the codigoerror property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoerror(String value) {
            this.codigoerror = value;
        }

        /**
         * Gets the value of the descripcionerror property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionerror() {
            return descripcionerror;
        }

        /**
         * Sets the value of the descripcionerror property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionerror(String value) {
            this.descripcionerror = value;
        }

    }

}
