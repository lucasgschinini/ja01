
package ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for detalleoperacionrelacionadashiva complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="detalleoperacionrelacionadashiva">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idOperacionShiva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idTransaccionShiva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detalleoperacionrelacionadashiva", propOrder = {
    "idOperacionShiva",
    "idTransaccionShiva"
})
public class Detalleoperacionrelacionadashiva {

    @XmlElement(required = true)
    protected String idOperacionShiva;
    @XmlElement(required = true)
    protected String idTransaccionShiva;

    /**
     * Gets the value of the idOperacionShiva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdOperacionShiva() {
        return idOperacionShiva;
    }

    /**
     * Sets the value of the idOperacionShiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdOperacionShiva(String value) {
        this.idOperacionShiva = value;
    }

    /**
     * Gets the value of the idTransaccionShiva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTransaccionShiva() {
        return idTransaccionShiva;
    }

    /**
     * Sets the value of the idTransaccionShiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTransaccionShiva(String value) {
        this.idTransaccionShiva = value;
    }

}
