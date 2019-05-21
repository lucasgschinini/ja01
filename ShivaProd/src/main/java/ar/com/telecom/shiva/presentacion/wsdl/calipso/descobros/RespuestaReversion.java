
package ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RespuestaReversion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RespuestaReversion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTransaccionShiva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoOperacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DetResultadoServicio" type="{urn:WSDL}resultadoservicio"/>
 *         &lt;element name="DetFactura" type="{urn:WSDL}detallefactura"/>
 *         &lt;element name="ListaDetMediosPago" type="{urn:WSDL}listadetallemediospago"/>
 *         &lt;element name="DetCargo" type="{urn:WSDL}detallecargos"/>
 *         &lt;element name="ListaDetOpRelacionadas" type="{urn:WSDL}listadetalleoperacionesrelacionadas"/>
 *         &lt;element name="listaCreDeb" type="{urn:WSDL}listacreditosydebitos"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaReversion", propOrder = {
    "idTransaccionShiva",
    "tipoOperacion",
    "detResultadoServicio",
    "detFactura",
    "listaDetMediosPago",
    "detCargo",
    "listaDetOpRelacionadas",
    "listaCreDeb"
})
public class RespuestaReversion {

    @XmlElement(required = true)
    protected String idTransaccionShiva;
    @XmlElement(required = true)
    protected String tipoOperacion;
    @XmlElement(name = "DetResultadoServicio", required = true)
    protected Resultadoservicio detResultadoServicio;
    @XmlElement(name = "DetFactura", required = true)
    protected Detallefactura detFactura;
    @XmlElement(name = "ListaDetMediosPago", required = true)
    protected Listadetallemediospago listaDetMediosPago;
    @XmlElement(name = "DetCargo", required = true)
    protected Detallecargos detCargo;
    @XmlElement(name = "ListaDetOpRelacionadas", required = true)
    protected Listadetalleoperacionesrelacionadas listaDetOpRelacionadas;
    @XmlElement(required = true)
    protected Listacreditosydebitos listaCreDeb;

    /**
     * Gets the value of the idTransaccionShiva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTransaccionShiva() {
        return idTransaccionShiva;
    }

    /**
     * Sets the value of the idTransaccionShiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTransaccionShiva(String value) {
        this.idTransaccionShiva = value;
    }

    /**
     * Gets the value of the tipoOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Sets the value of the tipoOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoOperacion(String value) {
        this.tipoOperacion = value;
    }

    /**
     * Gets the value of the detResultadoServicio property.
     * 
     * @return
     *     possible object is
     *     {@link Resultadoservicio }
     *     
     */
    public Resultadoservicio getDetResultadoServicio() {
        return detResultadoServicio;
    }

    /**
     * Sets the value of the detResultadoServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Resultadoservicio }
     *     
     */
    public void setDetResultadoServicio(Resultadoservicio value) {
        this.detResultadoServicio = value;
    }

    /**
     * Gets the value of the detFactura property.
     * 
     * @return
     *     possible object is
     *     {@link Detallefactura }
     *     
     */
    public Detallefactura getDetFactura() {
        return detFactura;
    }

    /**
     * Sets the value of the detFactura property.
     * 
     * @param value
     *     allowed object is
     *     {@link Detallefactura }
     *     
     */
    public void setDetFactura(Detallefactura value) {
        this.detFactura = value;
    }

    /**
     * Gets the value of the listaDetMediosPago property.
     * 
     * @return
     *     possible object is
     *     {@link Listadetallemediospago }
     *     
     */
    public Listadetallemediospago getListaDetMediosPago() {
        return listaDetMediosPago;
    }

    /**
     * Sets the value of the listaDetMediosPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link Listadetallemediospago }
     *     
     */
    public void setListaDetMediosPago(Listadetallemediospago value) {
        this.listaDetMediosPago = value;
    }

    /**
     * Gets the value of the detCargo property.
     * 
     * @return
     *     possible object is
     *     {@link Detallecargos }
     *     
     */
    public Detallecargos getDetCargo() {
        return detCargo;
    }

    /**
     * Sets the value of the detCargo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Detallecargos }
     *     
     */
    public void setDetCargo(Detallecargos value) {
        this.detCargo = value;
    }

    /**
     * Gets the value of the listaDetOpRelacionadas property.
     * 
     * @return
     *     possible object is
     *     {@link Listadetalleoperacionesrelacionadas }
     *     
     */
    public Listadetalleoperacionesrelacionadas getListaDetOpRelacionadas() {
        return listaDetOpRelacionadas;
    }

    /**
     * Sets the value of the listaDetOpRelacionadas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Listadetalleoperacionesrelacionadas }
     *     
     */
    public void setListaDetOpRelacionadas(Listadetalleoperacionesrelacionadas value) {
        this.listaDetOpRelacionadas = value;
    }

    /**
     * Gets the value of the listaCreDeb property.
     * 
     * @return
     *     possible object is
     *     {@link Listacreditosydebitos }
     *     
     */
    public Listacreditosydebitos getListaCreDeb() {
        return listaCreDeb;
    }

    /**
     * Sets the value of the listaCreDeb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Listacreditosydebitos }
     *     
     */
    public void setListaCreDeb(Listacreditosydebitos value) {
        this.listaCreDeb = value;
    }

}
