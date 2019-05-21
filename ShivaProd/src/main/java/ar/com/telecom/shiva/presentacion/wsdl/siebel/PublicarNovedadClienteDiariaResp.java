
package ar.com.telecom.shiva.presentacion.wsdl.siebel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="clienteNovedadID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="ack" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "publicarNovedadClienteDiariaResp", namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
public class PublicarNovedadClienteDiariaResp {

    @XmlAttribute(name = "clienteNovedadID", required = true)
    protected long clienteNovedadID;
    @XmlAttribute(name = "ack", required = true)
    protected boolean ack;

    /**
     * Gets the value of the clienteNovedadID property.
     * 
     */
    public long getClienteNovedadID() {
        return clienteNovedadID;
    }

    /**
     * Sets the value of the clienteNovedadID property.
     * 
     */
    public void setClienteNovedadID(long value) {
        this.clienteNovedadID = value;
    }

    /**
     * Gets the value of the ack property.
     * 
     */
    public boolean isAck() {
        return ack;
    }

    /**
     * Sets the value of the ack property.
     * 
     */
    public void setAck(boolean value) {
        this.ack = value;
    }

}
