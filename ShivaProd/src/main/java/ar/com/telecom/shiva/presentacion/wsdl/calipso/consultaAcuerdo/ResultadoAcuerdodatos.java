
package ar.com.telecom.shiva.presentacion.wsdl.calipso.consultaAcuerdo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoAcuerdodatos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoAcuerdodatos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdClienteLegado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoAcuerdodatos", propOrder = {
    "idClienteLegado",
    "estado",
    "moneda"
})
public class ResultadoAcuerdodatos {

    @XmlElement(name = "IdClienteLegado", required = true)
    protected String idClienteLegado;
    @XmlElement(name = "Estado", required = true)
    protected String estado;
    @XmlElement(name = "Moneda", required = true)
    protected String moneda;

    /**
     * Gets the value of the idClienteLegado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdClienteLegado() {
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
    public void setIdClienteLegado(String value) {
        this.idClienteLegado = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Gets the value of the moneda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Sets the value of the moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneda(String value) {
        this.moneda = value;
    }

}
