
package ar.com.telecom.shiva.presentacion.wsdl.calipso.cargos;

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
 * <p>Java class for RespuestaCargo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RespuestaCargo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTransaccionShiva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idMovMer" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="montoCalculadoMora" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="montoACuenta" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="interesesBonificados" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="monedaIntereses" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Resultado" type="{urn:WSDL}resultadocargo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaCargo", propOrder = {
    "idTransaccionShiva",
    "idMovMer",
    "montoCalculadoMora",
    "montoACuenta",
    "interesesBonificados",
    "monedaIntereses",
    "resultado"
})
public class RespuestaCargo {

    @XmlElement(required = true)
    protected String idTransaccionShiva;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    @XmlSchemaType(name = "integer")
    protected BigInteger idMovMer;
    @XmlElement(required = true)
    protected BigDecimal montoCalculadoMora;
    @XmlElement(required = true)
    protected BigDecimal montoACuenta;
    @XmlElement(required = true)
    protected BigDecimal interesesBonificados;
    @XmlElement(required = true)
    protected String monedaIntereses;
    @XmlElement(name = "Resultado", required = true)
    protected Resultadocargo resultado;

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

    /**
     * Gets the value of the idMovMer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigInteger getIdMovMer() {
        return idMovMer;
    }

    /**
     * Sets the value of the idMovMer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMovMer(BigInteger value) {
        this.idMovMer = value;
    }

    /**
     * Gets the value of the montoCalculadoMora property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMontoCalculadoMora() {
        return montoCalculadoMora;
    }

    /**
     * Sets the value of the montoCalculadoMora property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMontoCalculadoMora(BigDecimal value) {
        this.montoCalculadoMora = value;
    }

    /**
     * Gets the value of the montoACuenta property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMontoACuenta() {
        return montoACuenta;
    }

    /**
     * Sets the value of the montoACuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMontoACuenta(BigDecimal value) {
        this.montoACuenta = value;
    }

    /**
     * Gets the value of the interesesBonificados property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInteresesBonificados() {
        return interesesBonificados;
    }

    /**
     * Sets the value of the interesesBonificados property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInteresesBonificados(BigDecimal value) {
        this.interesesBonificados = value;
    }

    /**
     * Gets the value of the monedaIntereses property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaIntereses() {
        return monedaIntereses;
    }

    /**
     * Sets the value of the monedaIntereses property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaIntereses(String value) {
        this.monedaIntereses = value;
    }

    /**
     * Gets the value of the resultado property.
     * 
     * @return
     *     possible object is
     *     {@link Resultadocargo }
     *     
     */
    public Resultadocargo getResultado() {
        return resultado;
    }

    /**
     * Sets the value of the resultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Resultadocargo }
     *     
     */
    public void setResultado(Resultadocargo value) {
        this.resultado = value;
    }

}
