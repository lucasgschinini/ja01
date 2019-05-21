
package ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ar.com.telecom.shiva.base.soa.xmlAdapters.BigIntegerXmlAdapter;


/**
 * <p>Java class for detalledoc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="detalledoc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdDocumento" type="{urn:WSDL}mediodepago"/>
 *         &lt;element name="idDocumentoCuentasCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="IdClienteLegado" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="importe" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fechaCob" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detalledoc", propOrder = {
    "idDocumento",
    "idDocumentoCuentasCobranza",
    "idClienteLegado",
    "importe",
    "fechaCob"
})
public class Detalledoc {

    @XmlElement(name = "IdDocumento", required = true)
    protected Mediodepago idDocumento;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    @XmlSchemaType(name = "integer")
    protected BigInteger idDocumentoCuentasCobranza;
    @XmlElement(name = "IdClienteLegado", required = true, type = String.class)
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    @XmlSchemaType(name = "integer")
    protected BigInteger idClienteLegado;
    @XmlElement(required = true)
    protected BigDecimal importe;
    @XmlElement(required = true)
    protected String fechaCob;

    /**
     * Gets the value of the idDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link Mediodepago }
     *     
     */
    public Mediodepago getIdDocumento() {
        return idDocumento;
    }

    /**
     * Sets the value of the idDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mediodepago }
     *     
     */
    public void setIdDocumento(Mediodepago value) {
        this.idDocumento = value;
    }

    /**
     * Gets the value of the idDocumentoCuentasCobranza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigInteger getIdDocumentoCuentasCobranza() {
        return idDocumentoCuentasCobranza;
    }

    /**
     * Sets the value of the idDocumentoCuentasCobranza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDocumentoCuentasCobranza(BigInteger value) {
        this.idDocumentoCuentasCobranza = value;
    }

    /**
     * Gets the value of the idClienteLegado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigInteger getIdClienteLegado() {
        return idClienteLegado;
    }

    /**
     * Sets the value of the idClienteLegado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdClienteLegado(BigInteger value) {
        this.idClienteLegado = value;
    }

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
     * Gets the value of the fechaCob property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCob() {
        return fechaCob;
    }

    /**
     * Sets the value of the fechaCob property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCob(String value) {
        this.fechaCob = value;
    }

}
