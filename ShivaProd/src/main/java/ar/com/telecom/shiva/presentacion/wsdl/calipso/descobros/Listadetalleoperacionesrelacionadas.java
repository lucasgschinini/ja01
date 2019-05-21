
package ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listadetalleoperacionesrelacionadas complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listadetalleoperacionesrelacionadas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OperacionRelacionado" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Sistema" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DetOperacionRelacionadaShiva" type="{urn:WSDL}detalleoperacionrelacionadashiva"/>
 *                   &lt;element name="DetOperacionRelacionadaLegado" type="{urn:WSDL}detalleoperacionrelacionadalegado"/>
 *                   &lt;element name="DetalleDocumento" type="{urn:WSDL}detalledoc"/>
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
@XmlType(name = "listadetalleoperacionesrelacionadas", propOrder = {
    "operacionRelacionado"
})
public class Listadetalleoperacionesrelacionadas {

    @XmlElement(name = "OperacionRelacionado", required = true)
    protected List<Listadetalleoperacionesrelacionadas.OperacionRelacionado> operacionRelacionado;

    /**
     * Gets the value of the operacionRelacionado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operacionRelacionado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperacionRelacionado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Listadetalleoperacionesrelacionadas.OperacionRelacionado }
     * 
     * 
     */
    public List<Listadetalleoperacionesrelacionadas.OperacionRelacionado> getOperacionRelacionado() {
        if (operacionRelacionado == null) {
            operacionRelacionado = new ArrayList<Listadetalleoperacionesrelacionadas.OperacionRelacionado>();
        }
        return this.operacionRelacionado;
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
     *         &lt;element name="Sistema" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="DetOperacionRelacionadaShiva" type="{urn:WSDL}detalleoperacionrelacionadashiva"/>
     *         &lt;element name="DetOperacionRelacionadaLegado" type="{urn:WSDL}detalleoperacionrelacionadalegado"/>
     *         &lt;element name="DetalleDocumento" type="{urn:WSDL}detalledoc"/>
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
        "sistema",
        "detOperacionRelacionadaShiva",
        "detOperacionRelacionadaLegado",
        "detalleDocumento"
    })
    public static class OperacionRelacionado {

        @XmlElement(name = "Sistema", required = true)
        protected String sistema;
        @XmlElement(name = "DetOperacionRelacionadaShiva", required = true)
        protected Detalleoperacionrelacionadashiva detOperacionRelacionadaShiva;
        @XmlElement(name = "DetOperacionRelacionadaLegado", required = true)
        protected Detalleoperacionrelacionadalegado detOperacionRelacionadaLegado;
        @XmlElement(name = "DetalleDocumento", required = true)
        protected Detalledoc detalleDocumento;

        /**
         * Gets the value of the sistema property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSistema() {
            return sistema;
        }

        /**
         * Sets the value of the sistema property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSistema(String value) {
            this.sistema = value;
        }

        /**
         * Gets the value of the detOperacionRelacionadaShiva property.
         * 
         * @return
         *     possible object is
         *     {@link Detalleoperacionrelacionadashiva }
         *     
         */
        public Detalleoperacionrelacionadashiva getDetOperacionRelacionadaShiva() {
            return detOperacionRelacionadaShiva;
        }

        /**
         * Sets the value of the detOperacionRelacionadaShiva property.
         * 
         * @param value
         *     allowed object is
         *     {@link Detalleoperacionrelacionadashiva }
         *     
         */
        public void setDetOperacionRelacionadaShiva(Detalleoperacionrelacionadashiva value) {
            this.detOperacionRelacionadaShiva = value;
        }

        /**
         * Gets the value of the detOperacionRelacionadaLegado property.
         * 
         * @return
         *     possible object is
         *     {@link Detalleoperacionrelacionadalegado }
         *     
         */
        public Detalleoperacionrelacionadalegado getDetOperacionRelacionadaLegado() {
            return detOperacionRelacionadaLegado;
        }

        /**
         * Sets the value of the detOperacionRelacionadaLegado property.
         * 
         * @param value
         *     allowed object is
         *     {@link Detalleoperacionrelacionadalegado }
         *     
         */
        public void setDetOperacionRelacionadaLegado(Detalleoperacionrelacionadalegado value) {
            this.detOperacionRelacionadaLegado = value;
        }

        /**
         * Gets the value of the detalleDocumento property.
         * 
         * @return
         *     possible object is
         *     {@link Detalledoc }
         *     
         */
        public Detalledoc getDetalleDocumento() {
            return detalleDocumento;
        }

        /**
         * Sets the value of the detalleDocumento property.
         * 
         * @param value
         *     allowed object is
         *     {@link Detalledoc }
         *     
         */
        public void setDetalleDocumento(Detalledoc value) {
            this.detalleDocumento = value;
        }

    }

}
