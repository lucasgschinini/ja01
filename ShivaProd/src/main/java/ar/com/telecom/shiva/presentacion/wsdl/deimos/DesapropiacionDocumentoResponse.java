
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DesapropiacionDocumentoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DesapropiacionDocumentoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RespuestaDesapropiacionDeimos" type="{http://shiva.ws.intelap.com/}RespuestaDesapropiacionDeimos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DesapropiacionDocumentoResponse", propOrder = {
    "respuestaDesapropiacionDeimos"
})
public class DesapropiacionDocumentoResponse {

    @XmlElement(name = "RespuestaDesapropiacionDeimos")
    protected RespuestaDesapropiacionDeimos respuestaDesapropiacionDeimos;

    /**
     * Gets the value of the respuestaDesapropiacionDeimos property.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaDesapropiacionDeimos }
     *     
     */
    public RespuestaDesapropiacionDeimos getRespuestaDesapropiacionDeimos() {
        return respuestaDesapropiacionDeimos;
    }

    /**
     * Sets the value of the respuestaDesapropiacionDeimos property.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaDesapropiacionDeimos }
     *     
     */
    public void setRespuestaDesapropiacionDeimos(RespuestaDesapropiacionDeimos value) {
        this.respuestaDesapropiacionDeimos = value;
    }

}
