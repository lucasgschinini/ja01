
package ar.com.telecom.shiva.presentacion.wsdl.ice.consultaCheques;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="consultaChequesEntrada" type="{http://cheques.consulta.dto.ws.ice.teco.com.ar/xsd}ConsultaChequesEntrada" minOccurs="0"/>
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
    "consultaChequesEntrada"
})
@XmlRootElement(name = "consultaCheques")
public class ConsultaCheques {

    @XmlElement(nillable = true)
    protected ConsultaChequesEntrada consultaChequesEntrada;

    /**
     * Gets the value of the consultaChequesEntrada property.
     * 
     * @return
     *     possible object is
     *     {@link ConsultaChequesEntrada }
     *     
     */
    public ConsultaChequesEntrada getConsultaChequesEntrada() {
        return consultaChequesEntrada;
    }

    /**
     * Sets the value of the consultaChequesEntrada property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultaChequesEntrada }
     *     
     */
    public void setConsultaChequesEntrada(ConsultaChequesEntrada value) {
        this.consultaChequesEntrada = value;
    }

}
