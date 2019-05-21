
package ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for detallefactura complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="detallefactura">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DetResultadoFactura" type="{urn:WSDL}resultadoservicio"/>
 *         &lt;element name="DetAcuerdoContracargo" type="{urn:WSDL}detalleacuerdocontrato"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detallefactura", propOrder = {
    "detResultadoFactura",
    "detAcuerdoContracargo"
})
public class Detallefactura {

    @XmlElement(name = "DetResultadoFactura", required = true)
    protected Resultadoservicio detResultadoFactura;
    @XmlElement(name = "DetAcuerdoContracargo", required = true)
    protected Detalleacuerdocontrato detAcuerdoContracargo;

    /**
     * Gets the value of the detResultadoFactura property.
     * 
     * @return
     *     possible object is
     *     {@link Resultadoservicio }
     *     
     */
    public Resultadoservicio getDetResultadoFactura() {
        return detResultadoFactura;
    }

    /**
     * Sets the value of the detResultadoFactura property.
     * 
     * @param value
     *     allowed object is
     *     {@link Resultadoservicio }
     *     
     */
    public void setDetResultadoFactura(Resultadoservicio value) {
        this.detResultadoFactura = value;
    }

    /**
     * Gets the value of the detAcuerdoContracargo property.
     * 
     * @return
     *     possible object is
     *     {@link Detalleacuerdocontrato }
     *     
     */
    public Detalleacuerdocontrato getDetAcuerdoContracargo() {
        return detAcuerdoContracargo;
    }

    /**
     * Sets the value of the detAcuerdoContracargo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Detalleacuerdocontrato }
     *     
     */
    public void setDetAcuerdoContracargo(Detalleacuerdocontrato value) {
        this.detAcuerdoContracargo = value;
    }

}
