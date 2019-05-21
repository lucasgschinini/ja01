
package ar.com.telecom.shiva.presentacion.wsdl.siebel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nivelConsulta">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Linea"/>
 *               &lt;enumeration value="Acuerdo"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="tipoDeuda">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Corriente"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}identificador"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nivelConsulta",
    "tipoDeuda",
    "identificador"
})
@XmlRootElement(name = "consultarMorosidadClienteReq")
public class ConsultarMorosidadClienteReq {

    @XmlElement(required = true)
    protected String nivelConsulta;
    @XmlElement(required = true)
    protected String tipoDeuda;
    @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", required = true)
    protected String identificador;

    /**
     * Gets the value of the nivelConsulta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivelConsulta() {
        return nivelConsulta;
    }

    /**
     * Sets the value of the nivelConsulta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivelConsulta(String value) {
        this.nivelConsulta = value;
    }

    /**
     * Gets the value of the tipoDeuda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDeuda() {
        return tipoDeuda;
    }

    /**
     * Sets the value of the tipoDeuda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDeuda(String value) {
        this.tipoDeuda = value;
    }

    /**
     * Identificador Linea o Acuerdo segun elemento de consulta ( ver nivelConsulta )
     * 
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Sets the value of the identificador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificador(String value) {
        this.identificador = value;
    }

}
