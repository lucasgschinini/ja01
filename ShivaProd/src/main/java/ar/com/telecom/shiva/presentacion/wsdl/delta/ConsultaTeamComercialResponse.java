
package ar.com.telecom.shiva.presentacion.wsdl.delta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.telecom.shiva.presentacion.wsdl.delta.header.response.HeaderResponse;


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
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/responseHeader}header"/>
 *         &lt;element name="body">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/consultarTeamComercialResponse}consultarTeamComercialClienteResp"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/errorMessage}error"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
@XmlRootElement(name = "consultaTeamComercialResponse")
public class ConsultaTeamComercialResponse {

    @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/responseHeader", required = true)
    protected HeaderResponse header;
    @XmlElement(namespace = "", required = true)
    protected ConsultaTeamComercialResponse.Body body;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link HeaderResponse }
     *     
     */
    public HeaderResponse getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeaderResponse }
     *     
     */
    public void setHeader(HeaderResponse value) {
        this.header = value;
    }

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link ConsultaTeamComercialResponse.Body }
     *     
     */
    public ConsultaTeamComercialResponse.Body getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultaTeamComercialResponse.Body }
     *     
     */
    public void setBody(ConsultaTeamComercialResponse.Body value) {
        this.body = value;
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
     *       &lt;choice>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/consultarTeamComercialResponse}consultarTeamComercialClienteResp"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/errorMessage}error"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "consultarTeamComercialClienteResp",
        "error"
    })
    public static class Body {

        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/consultarTeamComercialResponse")
        protected ConsultarTeamComercialClienteResp consultarTeamComercialClienteResp;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/errorMessage")
        protected Error error;

        /**
         * Gets the value of the consultarTeamComercialClienteResp property.
         * 
         * @return
         *     possible object is
         *     {@link ConsultarTeamComercialClienteResp }
         *     
         */
        public ConsultarTeamComercialClienteResp getConsultarTeamComercialClienteResp() {
            return consultarTeamComercialClienteResp;
        }

        /**
         * Sets the value of the consultarTeamComercialClienteResp property.
         * 
         * @param value
         *     allowed object is
         *     {@link ConsultarTeamComercialClienteResp }
         *     
         */
        public void setConsultarTeamComercialClienteResp(ConsultarTeamComercialClienteResp value) {
            this.consultarTeamComercialClienteResp = value;
        }

        /**
         * Gets the value of the error property.
         * 
         * @return
         *     possible object is
         *     {@link Error }
         *     
         */
        public Error getError() {
            return error;
        }

        /**
         * Sets the value of the error property.
         * 
         * @param value
         *     allowed object is
         *     {@link Error }
         *     
         */
        public void setError(Error value) {
            this.error = value;
        }

    }

}
