
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InfoAdicionalMedPagNoComisionables complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InfoAdicionalMedPagNoComisionables">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Importe" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="CodigoTipoMedioPago" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoAdicionalMedPagNoComisionables", propOrder = {
    "importe",
    "codigoTipoMedioPago"
})
public class InfoAdicionalMedPagNoComisionables {

    @XmlElement(name = "Importe", required = true)
    protected BigDecimal importe;
    @XmlElement(name = "CodigoTipoMedioPago")
    protected long codigoTipoMedioPago;

    /**
     * Gets the value of the importe property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporte() {
        return importe;
    }

    /**
     * Sets the value of the importe property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporte(BigDecimal value) {
        this.importe = value;
    }

    /**
     * Gets the value of the codigoTipoMedioPago property.
     * 
     */
    public long getCodigoTipoMedioPago() {
        return codigoTipoMedioPago;
    }

    /**
     * Sets the value of the codigoTipoMedioPago property.
     * 
     */
    public void setCodigoTipoMedioPago(long value) {
        this.codigoTipoMedioPago = value;
    }

}
