
package ar.com.telecom.shiva.presentacion.wsdl.calipso.consultadeudaycredito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListaDebitosresultado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListaDebitosresultado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ListaDebitos" type="{urn:WSDL}Listadebitosdetalle"/>
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
@XmlType(name = "ListaDebitosresultado", propOrder = {
    "listaDebitos",
    "controlPaginado",
    "resultadoProcesamiento"
})
public class ListaDebitosresultado {

    @XmlElement(name = "ListaDebitos", required = true)
    protected Listadebitosdetalle listaDebitos;
    @XmlElement(name = "ControlPaginado", required = true)
    protected Listapaginado controlPaginado;
    @XmlElement(name = "ResultadoProcesamiento", required = true)
    protected Listaresultado resultadoProcesamiento;

    /**
     * Gets the value of the listaDebitos property.
     * 
     * @return
     *     possible object is
     *     {@link Listadebitosdetalle }
     *     
     */
    public Listadebitosdetalle getListaDebitos() {
        return listaDebitos;
    }

    /**
     * Sets the value of the listaDebitos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Listadebitosdetalle }
     *     
     */
    public void setListaDebitos(Listadebitosdetalle value) {
        this.listaDebitos = value;
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
