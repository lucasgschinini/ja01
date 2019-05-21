
package ar.com.telecom.shiva.presentacion.wsdl.delta;

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
 *         &lt;element name="legado">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="legadoClienteID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "legado"
})
@XmlRootElement(name = "consultarTeamComercialClienteReq", namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/consultarTeamComercialRequest")
public class ConsultarTeamComercialClienteReq {

    @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/consultarTeamComercialRequest", required = true)
    protected ConsultarTeamComercialClienteReq.Legado legado;

    /**
     * Gets the value of the legado property.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarTeamComercialClienteReq.Legado }
     *     
     */
    public ConsultarTeamComercialClienteReq.Legado getLegado() {
        return legado;
    }

    /**
     * Sets the value of the legado property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarTeamComercialClienteReq.Legado }
     *     
     */
    public void setLegado(ConsultarTeamComercialClienteReq.Legado value) {
        this.legado = value;
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
     *       &lt;attribute name="legadoClienteID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Legado {

        @XmlAttribute(name = "legadoClienteID", required = true)
        protected String legadoClienteID;

        /**
         * Gets the value of the legadoClienteID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLegadoClienteID() {
            return legadoClienteID;
        }

        /**
         * Sets the value of the legadoClienteID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLegadoClienteID(String value) {
            this.legadoClienteID = value;
        }

    }

}
