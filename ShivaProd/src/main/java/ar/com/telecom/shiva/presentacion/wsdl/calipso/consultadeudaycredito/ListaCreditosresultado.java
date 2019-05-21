
package ar.com.telecom.shiva.presentacion.wsdl.calipso.consultadeudaycredito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListaCreditosresultado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListaCreditosresultado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ListaCreditos" type="{urn:WSDL}ListaCreditosdetalle"/>
 *         &lt;element name="ControlPaginado" type="{urn:WSDL}listapaginado"/>
 *         &lt;element name="ResultadoProcesamiento" type="{urn:WSDL}listaresultado"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListaCreditosresultado", propOrder = {
    "listaCreditos",
    "controlPaginado",
    "resultadoProcesamiento"
})
public class ListaCreditosresultado {

    @XmlElement(name = "ListaCreditos", required = true)
    protected ListaCreditosdetalle listaCreditos;
    @XmlElement(name = "ControlPaginado", required = true)
    protected Listapaginado controlPaginado;
    @XmlElement(name = "ResultadoProcesamiento", required = true)
    protected Listaresultado resultadoProcesamiento;

    /**
     * Gets the value of the listaCreditos property.
     * 
     * @return
     *     possible object is
     *     {@link ListaCreditosdetalle }
     *     
     */
    public ListaCreditosdetalle getListaCreditos() {
        return listaCreditos;
    }

    /**
     * Sets the value of the listaCreditos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaCreditosdetalle }
     *     
     */
    public void setListaCreditos(ListaCreditosdetalle value) {
        this.listaCreditos = value;
    }

    /**
     * Gets the value of the controlPaginado property.
     * 
     * @return
     *     possible object is
     *     {@link Listapaginado }
     *     
     */
    public Listapaginado getControlPaginado() {
        return controlPaginado;
    }

    /**
     * Sets the value of the controlPaginado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Listapaginado }
     *     
     */
    public void setControlPaginado(Listapaginado value) {
        this.controlPaginado = value;
    }

    /**
     * Gets the value of the resultadoProcesamiento property.
     * 
     * @return
     *     possible object is
     *     {@link Listaresultado }
     *     
     */
    public Listaresultado getResultadoProcesamiento() {
        return resultadoProcesamiento;
    }

    /**
     * Sets the value of the resultadoProcesamiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Listaresultado }
     *     
     */
    public void setResultadoProcesamiento(Listaresultado value) {
        this.resultadoProcesamiento = value;
    }

}
