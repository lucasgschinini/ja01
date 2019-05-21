
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RespuestaApropiacionDeimos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RespuestaApropiacionDeimos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdSecuencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ListaResultados" type="{http://shiva.ws.intelap.com/}ListaResultados" minOccurs="0"/>
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
@XmlType(name = "RespuestaApropiacionDeimos", propOrder = {
    "idSecuencia",
    "listaResultados",
    "resultadoProcesamiento"
})
public class RespuestaApropiacionDeimos {

    @XmlElement(name = "IdSecuencia", required = true)
    protected String idSecuencia;
    @XmlElement(name = "ListaResultados", nillable = true)
    protected ListaResultados listaResultados;
    @XmlElement(name = "ResultadoProcesamiento", required = true)
    protected ResultadoProcesamiento resultadoProcesamiento;

    /**
     * Gets the value of the idSecuencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSecuencia() {
        return idSecuencia;
    }

    /**
     * Sets the value of the idSecuencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSecuencia(String value) {
        this.idSecuencia = value;
    }

    /**
     * Gets the value of the listaResultados property.
     * 
     * @return
     *     possible object is
     *     {@link ListaResultados }
     *     
     */
    public ListaResultados getListaResultados() {
        return listaResultados;
    }

    /**
     * Sets the value of the listaResultados property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaResultados }
     *     
     */
    public void setListaResultados(ListaResultados value) {
        this.listaResultados = value;
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
