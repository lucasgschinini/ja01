
package ar.com.telecom.shiva.presentacion.wsdl.ice.consultaCheques;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultaChequesSalida complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultaChequesSalida">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantidadDeRegistrosTotales" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="cantidadRegistrosDevueltos" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="cheques" type="{http://beans.ice.teco.com.ar/xsd}Cheque" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="codigoRetorno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionRetorno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quedanRegistros" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resultadoInvocacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaChequesSalida", namespace = "http://cheques.consulta.dto.ws.ice.teco.com.ar/xsd", propOrder = {
    "cantidadDeRegistrosTotales",
    "cantidadRegistrosDevueltos",
    "cheques",
    "codigoRetorno",
    "descripcionRetorno",
    "quedanRegistros",
    "resultadoInvocacion"
})
public class ConsultaChequesSalida {

    @XmlElement(nillable = true)
    protected Integer cantidadDeRegistrosTotales;
    @XmlElement(nillable = true)
    protected Integer cantidadRegistrosDevueltos;
    @XmlElement(nillable = true)
    protected List<Cheque> cheques;
    @XmlElement(nillable = true)
    protected String codigoRetorno;
    @XmlElement(nillable = true)
    protected String descripcionRetorno;
    @XmlElement(nillable = true)
    protected String quedanRegistros;
    @XmlElement(nillable = true)
    protected String resultadoInvocacion;

    /**
     * Gets the value of the cantidadDeRegistrosTotales property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantidadDeRegistrosTotales() {
        return cantidadDeRegistrosTotales;
    }

    /**
     * Sets the value of the cantidadDeRegistrosTotales property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantidadDeRegistrosTotales(Integer value) {
        this.cantidadDeRegistrosTotales = value;
    }

    /**
     * Gets the value of the cantidadRegistrosDevueltos property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantidadRegistrosDevueltos() {
        return cantidadRegistrosDevueltos;
    }

    /**
     * Sets the value of the cantidadRegistrosDevueltos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantidadRegistrosDevueltos(Integer value) {
        this.cantidadRegistrosDevueltos = value;
    }

    /**
     * Gets the value of the cheques property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cheques property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCheques().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Cheque }
     * 
     * 
     */
    public List<Cheque> getCheques() {
        if (cheques == null) {
            cheques = new ArrayList<Cheque>();
        }
        return this.cheques;
    }

    /**
     * Gets the value of the codigoRetorno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoRetorno() {
        return codigoRetorno;
    }

    /**
     * Sets the value of the codigoRetorno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoRetorno(String value) {
        this.codigoRetorno = value;
    }

    /**
     * Gets the value of the descripcionRetorno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionRetorno() {
        return descripcionRetorno;
    }

    /**
     * Sets the value of the descripcionRetorno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionRetorno(String value) {
        this.descripcionRetorno = value;
    }

    /**
     * Gets the value of the quedanRegistros property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuedanRegistros() {
        return quedanRegistros;
    }

    /**
     * Sets the value of the quedanRegistros property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuedanRegistros(String value) {
        this.quedanRegistros = value;
    }

    /**
     * Gets the value of the resultadoInvocacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultadoInvocacion() {
        return resultadoInvocacion;
    }

    /**
     * Sets the value of the resultadoInvocacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultadoInvocacion(String value) {
        this.resultadoInvocacion = value;
    }

}
