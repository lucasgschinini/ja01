
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RespuestaDesapropiacionDeimos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RespuestaDesapropiacionDeimos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdOperacionShiva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ResultadoProcesamiento" type="{http://shiva.ws.intelap.com/}ResultadoProcesamiento"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaDesapropiacionDeimos", propOrder = {
    "idOperacionShiva",
    "resultadoProcesamiento"
})
public class RespuestaDesapropiacionDeimos {

    @XmlElement(name = "IdOperacionShiva", required = true)
    protected String idOperacionShiva;
    @XmlElement(name = "ResultadoProcesamiento", required = true)
    protected ResultadoProcesamiento resultadoProcesamiento;

    /**
     * Gets the value of the idOperacionShiva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdOperacionShiva() {
        return idOperacionShiva;
    }

    /**
     * Sets the value of the idOperacionShiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdOperacionShiva(String value) {
        this.idOperacionShiva = value;
    }

    /**
     * Gets the value of the resultadoProcesamiento property.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoProcesamiento }
     *     
     */
    public ResultadoProcesamiento getResultadoProcesamiento() {
        return resultadoProcesamiento;
    }

    /**
     * Sets the value of the resultadoProcesamiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoProcesamiento }
     *     
     */
    public void setResultadoProcesamiento(ResultadoProcesamiento value) {
        this.resultadoProcesamiento = value;
    }

}
