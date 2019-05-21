
package ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for detallecargos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="detallecargos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EstadoAcuerdo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EstadoCargo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InteresesTrasladados" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="DetResultadoCargo" type="{urn:WSDL}resultadoservicio"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detallecargos", propOrder = {
    "estadoAcuerdo",
    "estadoCargo",
    "interesesTrasladados",
    "detResultadoCargo"
})
public class Detallecargos {

    @XmlElement(name = "EstadoAcuerdo", required = true)
    protected String estadoAcuerdo;
    @XmlElement(name = "EstadoCargo", required = true)
    protected String estadoCargo;
    @XmlElement(name = "InteresesTrasladados", required = true)
    protected BigDecimal interesesTrasladados;
    @XmlElement(name = "DetResultadoCargo", required = true)
    protected Resultadoservicio detResultadoCargo;

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

    /**
     * Gets the value of the interesesTrasladados property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInteresesTrasladados() {
        return interesesTrasladados;
    }

    /**
     * Sets the value of the interesesTrasladados property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInteresesTrasladados(BigDecimal value) {
        this.interesesTrasladados = value;
    }

    /**
     * Gets the value of the detResultadoCargo property.
     * 
     * @return
     *     possible object is
     *     {@link Resultadoservicio }
     *     
     */
    public Resultadoservicio getDetResultadoCargo() {
        return detResultadoCargo;
    }

    /**
     * Sets the value of the detResultadoCargo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Resultadoservicio }
     *     
     */
    public void setDetResultadoCargo(Resultadoservicio value) {
        this.detResultadoCargo = value;
    }

}
