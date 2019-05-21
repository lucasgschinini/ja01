
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultaEstadoDocumento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultaEstadoDocumento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsultaEstadoDeudaFiltroDeimos" type="{http://shiva.ws.intelap.com/}ConsultaEstadoDeudaFiltroDeimos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaEstadoDocumento", propOrder = {
    "consultaEstadoDeudaFiltroDeimos"
})
public class ConsultaEstadoDocumento {

    @XmlElement(name = "ConsultaEstadoDeudaFiltroDeimos")
    protected ConsultaEstadoDeudaFiltroDeimos consultaEstadoDeudaFiltroDeimos;

    /**
     * Gets the value of the consultaEstadoDeudaFiltroDeimos property.
     * 
     * @return
     *     possible object is
     *     {@link ConsultaEstadoDeudaFiltroDeimos }
     *     
     */
    public ConsultaEstadoDeudaFiltroDeimos getConsultaEstadoDeudaFiltroDeimos() {
        return consultaEstadoDeudaFiltroDeimos;
    }

    /**
     * Sets the value of the consultaEstadoDeudaFiltroDeimos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultaEstadoDeudaFiltroDeimos }
     *     
     */
    public void setConsultaEstadoDeudaFiltroDeimos(ConsultaEstadoDeudaFiltroDeimos value) {
        this.consultaEstadoDeudaFiltroDeimos = value;
    }

}
