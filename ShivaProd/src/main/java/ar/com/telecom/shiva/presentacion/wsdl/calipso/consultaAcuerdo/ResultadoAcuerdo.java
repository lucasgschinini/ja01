
package ar.com.telecom.shiva.presentacion.wsdl.calipso.consultaAcuerdo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoAcuerdo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoAcuerdo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Acuerdo" type="{urn:WSDL}ResultadoAcuerdodatos"/>
 *         &lt;element name="ResultadoProcesamiento" type="{urn:WSDL}ResultadoProceso"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoAcuerdo", propOrder = {
    "acuerdo",
    "resultadoProcesamiento"
})
public class ResultadoAcuerdo {

    @XmlElement(name = "Acuerdo", required = true)
    protected ResultadoAcuerdodatos acuerdo;
    @XmlElement(name = "ResultadoProcesamiento", required = true)
    protected ResultadoProceso resultadoProcesamiento;

    /**
     * Gets the value of the acuerdo property.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoAcuerdodatos }
     *     
     */
    public ResultadoAcuerdodatos getAcuerdo() {
        return acuerdo;
    }

    /**
     * Sets the value of the acuerdo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoAcuerdodatos }
     *     
     */
    public void setAcuerdo(ResultadoAcuerdodatos value) {
        this.acuerdo = value;
    }

    /**
     * Gets the value of the resultadoProcesamiento property.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoProceso }
     *     
     */
    public ResultadoProceso getResultadoProcesamiento() {
        return resultadoProcesamiento;
    }

    /**
     * Sets the value of the resultadoProcesamiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoProceso }
     *     
     */
    public void setResultadoProcesamiento(ResultadoProceso value) {
        this.resultadoProcesamiento = value;
    }

}
