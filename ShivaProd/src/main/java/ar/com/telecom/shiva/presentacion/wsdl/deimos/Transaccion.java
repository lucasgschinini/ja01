
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Transaccion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Transaccion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdSecuencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ListaDocumentos" type="{http://shiva.ws.intelap.com/}ListaDocumentos"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Transaccion", propOrder = {
    "idSecuencia",
    "listaDocumentos"
})
public class Transaccion {

    @XmlElement(name = "IdSecuencia", required = true)
    protected String idSecuencia;
    @XmlElement(name = "ListaDocumentos", required = true)
    protected ListaDocumentos listaDocumentos;

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
     * Gets the value of the listaDocumentos property.
     * 
     * @return
     *     possible object is
     *     {@link ListaDocumentos }
     *     
     */
    public ListaDocumentos getListaDocumentos() {
        return listaDocumentos;
    }

    /**
     * Sets the value of the listaDocumentos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaDocumentos }
     *     
     */
    public void setListaDocumentos(ListaDocumentos value) {
        this.listaDocumentos = value;
    }

}
