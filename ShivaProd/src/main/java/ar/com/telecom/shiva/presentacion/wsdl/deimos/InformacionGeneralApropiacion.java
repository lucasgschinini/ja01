
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InformacionGeneralApropiacion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InformacionGeneralApropiacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Apropiada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoEstadoTramite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescripcionEstadoTramite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Convenio" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CantidadCuotas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CantidadCuotasPagas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InformacionGeneralApropiacion", propOrder = {
    "apropiada",
    "codigoEstadoTramite",
    "descripcionEstadoTramite",
    "convenio",
    "cantidadCuotas",
    "cantidadCuotasPagas"
})
public class InformacionGeneralApropiacion {

    @XmlElement(name = "Apropiada", nillable = true)
    protected String apropiada;
    @XmlElement(name = "CodigoEstadoTramite", nillable = true)
    protected String codigoEstadoTramite;
    @XmlElement(name = "DescripcionEstadoTramite", nillable = true)
    protected String descripcionEstadoTramite;
    @XmlElement(name = "Convenio", required = true, type = Long.class, nillable = true)
    protected Long convenio;
    @XmlElement(name = "CantidadCuotas", required = true, type = Integer.class, nillable = true)
    protected Integer cantidadCuotas;
    @XmlElement(name = "CantidadCuotasPagas", required = true, type = Integer.class, nillable = true)
    protected Integer cantidadCuotasPagas;

    /**
     * Gets the value of the apropiada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApropiada() {
        return apropiada;
    }

    /**
     * Sets the value of the apropiada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApropiada(String value) {
        this.apropiada = value;
    }

    /**
     * Gets the value of the codigoEstadoTramite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEstadoTramite() {
        return codigoEstadoTramite;
    }

    /**
     * Sets the value of the codigoEstadoTramite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEstadoTramite(String value) {
        this.codigoEstadoTramite = value;
    }

    /**
     * Gets the value of the descripcionEstadoTramite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionEstadoTramite() {
        return descripcionEstadoTramite;
    }

    /**
     * Sets the value of the descripcionEstadoTramite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionEstadoTramite(String value) {
        this.descripcionEstadoTramite = value;
    }

    /**
     * Gets the value of the convenio property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getConvenio() {
        return convenio;
    }

    /**
     * Sets the value of the convenio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setConvenio(Long value) {
        this.convenio = value;
    }

    /**
     * Gets the value of the cantidadCuotas property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantidadCuotas() {
        return cantidadCuotas;
    }

    /**
     * Sets the value of the cantidadCuotas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantidadCuotas(Integer value) {
        this.cantidadCuotas = value;
    }

    /**
     * Gets the value of the cantidadCuotasPagas property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantidadCuotasPagas() {
        return cantidadCuotasPagas;
    }

    /**
     * Sets the value of the cantidadCuotasPagas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantidadCuotasPagas(Integer value) {
        this.cantidadCuotasPagas = value;
    }

}
