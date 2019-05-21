
package ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listaresultado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listaresultado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idShiva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Resultado" type="{urn:WSDL}resultadoshiva"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaresultado", propOrder = {
    "idShiva",
    "resultado"
})
public class Listaresultado {

    @XmlElement(required = true)
    protected String idShiva;
    @XmlElement(name = "Resultado", required = true)
    protected Resultadoshiva resultado;

    /**
     * Gets the value of the idShiva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdShiva() {
        return idShiva;
    }

    /**
     * Sets the value of the idShiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdShiva(String value) {
        this.idShiva = value;
    }

    /**
     * Gets the value of the resultado property.
     * 
     * @return
     *     possible object is
     *     {@link Resultadoshiva }
     *     
     */
    public Resultadoshiva getResultado() {
        return resultado;
    }

    /**
     * Sets the value of the resultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Resultadoshiva }
     *     
     */
    public void setResultado(Resultadoshiva value) {
        this.resultado = value;
    }

}
