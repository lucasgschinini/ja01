
package ar.com.telecom.shiva.presentacion.wsdl.siebel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.ClienteCRM;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}clienteCRM"/>
 *       &lt;/all>
 *       &lt;attribute name="crmClienteID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="secuenciaNovedad" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="tipoNovedad" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tipoSolicitud" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="nroSolicitud" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="fechaNovedad" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="estado" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "publicarNovedadClienteDiariaReq")
public class PublicarNovedadClienteDiariaReq {

    @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", required = true)
    protected ClienteCRM clienteCRM;
    @XmlAttribute(name = "crmClienteID", required = true)
    protected String crmClienteID;
    @XmlAttribute(name = "secuenciaNovedad", required = true)
    protected long secuenciaNovedad;
    @XmlAttribute(name = "tipoNovedad", required = true)
    protected String tipoNovedad;
    @XmlAttribute(name = "tipoSolicitud", required = true)
    protected String tipoSolicitud;
    @XmlAttribute(name = "nroSolicitud", required = true)
    protected String nroSolicitud;
    @XmlAttribute(name = "fechaNovedad", required = true)
    protected String fechaNovedad;
    @XmlAttribute(name = "estado")
    protected String estado;

    /**
     * Gets the value of the clienteCRM property.
     * 
     * @return
     *     possible object is
     *     {@link ClienteCRM }
     *     
     */
    public ClienteCRM getClienteCRM() {
        return clienteCRM;
    }

    /**
     * Sets the value of the clienteCRM property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClienteCRM }
     *     
     */
    public void setClienteCRM(ClienteCRM value) {
        this.clienteCRM = value;
    }

    /**
     * Gets the value of the crmClienteID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmClienteID() {
        return crmClienteID;
    }

    /**
     * Sets the value of the crmClienteID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmClienteID(String value) {
        this.crmClienteID = value;
    }

    /**
     * Gets the value of the secuenciaNovedad property.
     * 
     */
    public long getSecuenciaNovedad() {
        return secuenciaNovedad;
    }

    /**
     * Sets the value of the secuenciaNovedad property.
     * 
     */
    public void setSecuenciaNovedad(long value) {
        this.secuenciaNovedad = value;
    }

    /**
     * Gets the value of the tipoNovedad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoNovedad() {
        return tipoNovedad;
    }

    /**
     * Sets the value of the tipoNovedad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoNovedad(String value) {
        this.tipoNovedad = value;
    }

    /**
     * Gets the value of the tipoSolicitud property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    /**
     * Sets the value of the tipoSolicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSolicitud(String value) {
        this.tipoSolicitud = value;
    }

    /**
     * Gets the value of the nroSolicitud property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroSolicitud() {
        return nroSolicitud;
    }

    /**
     * Sets the value of the nroSolicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroSolicitud(String value) {
        this.nroSolicitud = value;
    }

    /**
     * Gets the value of the fechaNovedad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNovedad() {
        return fechaNovedad;
    }

    /**
     * Sets the value of the fechaNovedad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNovedad(String value) {
        this.fechaNovedad = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

}
