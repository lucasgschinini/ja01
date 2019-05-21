
package ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ar.com.telecom.shiva.base.soa.xmlAdapters.BigIntegerXmlAdapter;


/**
 * <p>Java class for detalleoperacionrelacionadalegado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="detalleoperacionrelacionadalegado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detalleoperacionrelacionadalegado", propOrder = {
    "idCobranza"
})
public class Detalleoperacionrelacionadalegado {

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    @XmlSchemaType(name = "integer")
    protected BigInteger idCobranza;

    /**
     * Gets the value of the idCobranza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigInteger getIdCobranza() {
        return idCobranza;
    }

    /**
     * Sets the value of the idCobranza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCobranza(BigInteger value) {
        this.idCobranza = value;
    }

}
