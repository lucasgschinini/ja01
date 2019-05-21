
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ApropiacionDocumentoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ApropiacionDocumentoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RespuestaApropiacionDeimos" type="{http://shiva.ws.intelap.com/}RespuestaApropiacionDeimos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApropiacionDocumentoResponse", propOrder = {
    "respuestaApropiacionDeimos"
})
public class ApropiacionDocumentoResponse {

    @XmlElement(name = "RespuestaApropiacionDeimos")
    protected RespuestaApropiacionDeimos respuestaApropiacionDeimos;

    /**
     * Gets the value of the respuestaApropiacionDeimos property.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaApropiacionDeimos }
     *     
     */
    public RespuestaApropiacionDeimos getRespuestaApropiacionDeimos() {
        return respuestaApropiacionDeimos;
    }

    /**
     * Sets the value of the respuestaApropiacionDeimos property.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaApropiacionDeimos }
     *     
     */
    public void setRespuestaApropiacionDeimos(RespuestaApropiacionDeimos value) {
        this.respuestaApropiacionDeimos = value;
    }

}
