
package ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for detalleacuerdocontrato complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="detalleacuerdocontrato">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EstadoAcuerdo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EstadoCargo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detalleacuerdocontrato", propOrder = {
    "estadoAcuerdo",
    "estadoCargo"
})
public class Detalleacuerdocontrato {

    @XmlElement(name = "EstadoAcuerdo", required = true)
    protected String estadoAcuerdo;
    @XmlElement(name = "EstadoCargo", required = true)
    protected String estadoCargo;

    /**
     * Gets the value of the estadoAcuerdo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoAcuerdo() {
        return estadoAcuerdo;
    }

    /**
     * Sets the value of the estadoAcuerdo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoAcuerdo(String value) {
        this.estadoAcuerdo = value;
    }

    /**
     * Gets the value of the estadoCargo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoCargo() {
        return estadoCargo;
    }

    /**
     * Sets the value of the estadoCargo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoCargo(String value) {
        this.estadoCargo = value;
    }

}
