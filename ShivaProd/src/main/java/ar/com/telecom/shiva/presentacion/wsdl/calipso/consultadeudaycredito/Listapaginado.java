
package ar.com.telecom.shiva.presentacion.wsdl.calipso.consultadeudaycredito;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ar.com.telecom.shiva.base.soa.xmlAdapters.BigIntegerXmlAdapter;


/**
 * <p>Java class for listapaginado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listapaginado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CantidadRegistrosRetornados" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="CantidadRegistrosTotales" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listapaginado", propOrder = {
    "cantidadRegistrosRetornados",
    "cantidadRegistrosTotales"
})
public class Listapaginado {

    @XmlElement(name = "CantidadRegistrosRetornados", required = true, type = String.class)
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    @XmlSchemaType(name = "integer")
    protected BigInteger cantidadRegistrosRetornados;
    @XmlElement(name = "CantidadRegistrosTotales", required = true, type = String.class)
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    @XmlSchemaType(name = "integer")
    protected BigInteger cantidadRegistrosTotales;

    /**
     * Gets the value of the cantidadRegistrosRetornados property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigInteger getCantidadRegistrosRetornados() {
        return cantidadRegistrosRetornados;
    }

    /**
     * Sets the value of the cantidadRegistrosRetornados property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantidadRegistrosRetornados(BigInteger value) {
        this.cantidadRegistrosRetornados = value;
    }

    /**
     * Gets the value of the cantidadRegistrosTotales property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigInteger getCantidadRegistrosTotales() {
        return cantidadRegistrosTotales;
    }

    /**
     * Sets the value of the cantidadRegistrosTotales property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantidadRegistrosTotales(BigInteger value) {
        this.cantidadRegistrosTotales = value;
    }

}
