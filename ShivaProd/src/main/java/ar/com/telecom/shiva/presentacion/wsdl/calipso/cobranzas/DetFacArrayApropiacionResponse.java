
package ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas;

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
 * <p>Java class for detFacArrayApropiacionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="detFacArrayApropiacionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="importemora" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="montocta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoCambioFechaEmision" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="tipoCambioFechaCobro" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="importeAplicadoFechaEmisionPesos" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="importeAplicadoMonedaOrigen" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="listaResultadoApropiacion" type="{urn:WSDL}listaResultadoApropiacion"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detFacArrayApropiacionResponse", propOrder = {
    "idCobranza",
    "importemora",
    "montocta",
    "tipoCambioFechaEmision",
    "tipoCambioFechaCobro",
    "importeAplicadoFechaEmisionPesos",
    "importeAplicadoMonedaOrigen",
    "listaResultadoApropiacion"
})
public class DetFacArrayApropiacionResponse {

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    @XmlSchemaType(name = "integer")
    protected BigInteger idCobranza;
    @XmlElement(required = true)
    protected String importemora;
    @XmlElement(required = true)
    protected String montocta;
    @XmlElement(required = true)
    protected BigDecimal tipoCambioFechaEmision;
    @XmlElement(required = true)
    protected BigDecimal tipoCambioFechaCobro;
    @XmlElement(required = true)
    protected BigDecimal importeAplicadoFechaEmisionPesos;
    @XmlElement(required = true)
    protected BigDecimal importeAplicadoMonedaOrigen;
    @XmlElement(required = true)
    protected ListaResultadoApropiacion listaResultadoApropiacion;

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

    /**
     * Gets the value of the importemora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImportemora() {
        return importemora;
    }

    /**
     * Sets the value of the importemora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImportemora(String value) {
        this.importemora = value;
    }

    /**
     * Gets the value of the montocta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontocta() {
        return montocta;
    }

    /**
     * Sets the value of the montocta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontocta(String value) {
        this.montocta = value;
    }

    /**
     * Gets the value of the tipoCambioFechaEmision property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTipoCambioFechaEmision() {
        return tipoCambioFechaEmision;
    }

    /**
     * Sets the value of the tipoCambioFechaEmision property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTipoCambioFechaEmision(BigDecimal value) {
        this.tipoCambioFechaEmision = value;
    }

    /**
     * Gets the value of the tipoCambioFechaCobro property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTipoCambioFechaCobro() {
        return tipoCambioFechaCobro;
    }

    /**
     * Sets the value of the tipoCambioFechaCobro property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTipoCambioFechaCobro(BigDecimal value) {
        this.tipoCambioFechaCobro = value;
    }

    /**
     * Gets the value of the importeAplicadoFechaEmisionPesos property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteAplicadoFechaEmisionPesos() {
        return importeAplicadoFechaEmisionPesos;
    }

    /**
     * Sets the value of the importeAplicadoFechaEmisionPesos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteAplicadoFechaEmisionPesos(BigDecimal value) {
        this.importeAplicadoFechaEmisionPesos = value;
    }

    /**
     * Gets the value of the importeAplicadoMonedaOrigen property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteAplicadoMonedaOrigen() {
        return importeAplicadoMonedaOrigen;
    }

    /**
     * Sets the value of the importeAplicadoMonedaOrigen property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteAplicadoMonedaOrigen(BigDecimal value) {
        this.importeAplicadoMonedaOrigen = value;
    }

    /**
     * Gets the value of the listaResultadoApropiacion property.
     * 
     * @return
     *     possible object is
     *     {@link ListaResultadoApropiacion }
     *     
     */
    public ListaResultadoApropiacion getListaResultadoApropiacion() {
        return listaResultadoApropiacion;
    }

    /**
     * Sets the value of the listaResultadoApropiacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaResultadoApropiacion }
     *     
     */
    public void setListaResultadoApropiacion(ListaResultadoApropiacion value) {
        this.listaResultadoApropiacion = value;
    }

}
