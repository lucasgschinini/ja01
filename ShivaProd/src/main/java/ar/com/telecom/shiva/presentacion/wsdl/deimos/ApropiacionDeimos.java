
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ApropiacionDeimos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ApropiacionDeimos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdOperacionShiva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UsuarioCobrador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Transaccion" type="{http://shiva.ws.intelap.com/}Transaccion" minOccurs="0"/>
 *         &lt;element name="ModoOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApropiacionDeimos", propOrder = {
    "idOperacionShiva",
    "usuarioCobrador",
    "transaccion",
    "modoOperacion"
})
public class ApropiacionDeimos {

    @XmlElement(name = "IdOperacionShiva", required = true)
    protected String idOperacionShiva;
    @XmlElement(name = "UsuarioCobrador", required = true)
    protected String usuarioCobrador;
    @XmlElement(name = "Transaccion", nillable = true)
    protected Transaccion transaccion;
    @XmlElement(name = "ModoOperacion", nillable = true)
    protected String modoOperacion;

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
     * Gets the value of the usuarioCobrador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioCobrador() {
        return usuarioCobrador;
    }

    /**
     * Sets the value of the usuarioCobrador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioCobrador(String value) {
        this.usuarioCobrador = value;
    }

    /**
     * Gets the value of the transaccion property.
     * 
     * @return
     *     possible object is
     *     {@link Transaccion }
     *     
     */
    public Transaccion getTransaccion() {
        return transaccion;
    }

    /**
     * Sets the value of the transaccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Transaccion }
     *     
     */
    public void setTransaccion(Transaccion value) {
        this.transaccion = value;
    }

    /**
     * Gets the value of the modoOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModoOperacion() {
        return modoOperacion;
    }

    /**
     * Sets the value of the modoOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModoOperacion(String value) {
        this.modoOperacion = value;
    }

}
