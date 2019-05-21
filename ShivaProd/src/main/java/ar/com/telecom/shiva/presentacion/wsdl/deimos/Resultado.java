
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Resultado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Resultado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Documento" type="{http://shiva.ws.intelap.com/}Documento"/>
 *         &lt;element name="ResultadoApropiacionDocumento" type="{http://shiva.ws.intelap.com/}ResultadoApropiacionDocumento"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Resultado", propOrder = {
    "documento",
    "resultadoApropiacionDocumento"
})
public class Resultado {

    @XmlElement(name = "Documento", required = true)
    protected Documento documento;
    @XmlElement(name = "ResultadoApropiacionDocumento", required = true)
    protected ResultadoApropiacionDocumento resultadoApropiacionDocumento;

    /**
     * Gets the value of the documento property.
     * 
     * @return
     *     possible object is
     *     {@link Documento }
     *     
     */
    public Documento getDocumento() {
        return documento;
    }

    /**
     * Sets the value of the documento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Documento }
     *     
     */
    public void setDocumento(Documento value) {
        this.documento = value;
    }

    /**
     * Gets the value of the resultadoApropiacionDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoApropiacionDocumento }
     *     
     */
    public ResultadoApropiacionDocumento getResultadoApropiacionDocumento() {
        return resultadoApropiacionDocumento;
    }

    /**
     * Sets the value of the resultadoApropiacionDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoApropiacionDocumento }
     *     
     */
    public void setResultadoApropiacionDocumento(ResultadoApropiacionDocumento value) {
        this.resultadoApropiacionDocumento = value;
    }

}
