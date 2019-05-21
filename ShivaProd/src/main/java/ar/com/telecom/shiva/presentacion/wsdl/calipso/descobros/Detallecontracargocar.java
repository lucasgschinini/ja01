
package ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for detallecontracargocar complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="detallecontracargocar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="facturarContracargo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="acuerdo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detallecontracargocar", propOrder = {
    "facturarContracargo",
    "acuerdo"
})
public class Detallecontracargocar {

    @XmlElement(required = true)
    protected String facturarContracargo;
    @XmlElement(required = true)
    protected String acuerdo;

    /**
     * Gets the value of the facturarContracargo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFacturarContracargo() {
        return facturarContracargo;
    }

    /**
     * Sets the value of the facturarContracargo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFacturarContracargo(String value) {
        this.facturarContracargo = value;
    }

    /**
     * Gets the value of the acuerdo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcuerdo() {
        return acuerdo;
    }

    /**
     * Sets the value of the acuerdo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcuerdo(String value) {
        this.acuerdo = value;
    }

}
