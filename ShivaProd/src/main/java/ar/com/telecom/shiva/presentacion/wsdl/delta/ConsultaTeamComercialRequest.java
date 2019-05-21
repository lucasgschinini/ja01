
package ar.com.telecom.shiva.presentacion.wsdl.delta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.telecom.shiva.presentacion.wsdl.delta.header.request.HeaderRequest;


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
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/requestHeader}header"/>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/consultarTeamComercialRequest}consultarTeamComercialClienteReq"/>
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
@XmlRootElement(name = "consultaTeamComercialRequest")
public class ConsultaTeamComercialRequest {

    @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/requestHeader", required = true)
    protected HeaderRequest header;
    @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/consultarTeamComercialRequest", required = true)
    protected ConsultarTeamComercialClienteReq consultarTeamComercialClienteReq;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link HeaderRequest }
     *     
     */
    public HeaderRequest getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeaderRequest }
     *     
     */
    public void setHeader(HeaderRequest value) {
        this.header = value;
    }

    /**
     * Gets the value of the consultarTeamComercialClienteReq property.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarTeamComercialClienteReq }
     *     
     */
    public ConsultarTeamComercialClienteReq getConsultarTeamComercialClienteReq() {
        return consultarTeamComercialClienteReq;
    }

    /**
     * Sets the value of the consultarTeamComercialClienteReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarTeamComercialClienteReq }
     *     
     */
    public void setConsultarTeamComercialClienteReq(ConsultarTeamComercialClienteReq value) {
        this.consultarTeamComercialClienteReq = value;
    }

}
