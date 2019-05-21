
package ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for apropiacionSalida complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="apropiacionSalida">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idShiva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detFac" type="{urn:WSDL}detFacArrayApropiacionResponse"/>
 *         &lt;element name="listaCreDeb" type="{urn:WSDL}listaCreDebArrayApropiacionResponse"/>
 *         &lt;element name="listaCtaCre" type="{urn:WSDL}listaCtaCreArrayApropiacionResponse"/>
 *         &lt;element name="resultado" type="{urn:WSDL}listaresultado"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "apropiacionSalida", propOrder = {
    "idShiva",
    "detFac",
    "listaCreDeb",
    "listaCtaCre",
    "resultado"
})
public class ApropiacionSalida {

    @XmlElement(required = true)
    protected String idShiva;
    @XmlElement(required = true)
    protected DetFacArrayApropiacionResponse detFac;
    @XmlElement(required = true)
    protected ListaCreDebArrayApropiacionResponse listaCreDeb;
    @XmlElement(required = true)
    protected ListaCtaCreArrayApropiacionResponse listaCtaCre;
    @XmlElement(required = true)
    protected Listaresultado resultado;

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
     * Gets the value of the detFac property.
     * 
     * @return
     *     possible object is
     *     {@link DetFacArrayApropiacionResponse }
     *     
     */
    public DetFacArrayApropiacionResponse getDetFac() {
        return detFac;
    }

    /**
     * Sets the value of the detFac property.
     * 
     * @param value
     *     allowed object is
     *     {@link DetFacArrayApropiacionResponse }
     *     
     */
    public void setDetFac(DetFacArrayApropiacionResponse value) {
        this.detFac = value;
    }

    /**
     * Gets the value of the listaCreDeb property.
     * 
     * @return
     *     possible object is
     *     {@link ListaCreDebArrayApropiacionResponse }
     *     
     */
    public ListaCreDebArrayApropiacionResponse getListaCreDeb() {
        return listaCreDeb;
    }

    /**
     * Sets the value of the listaCreDeb property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaCreDebArrayApropiacionResponse }
     *     
     */
    public void setListaCreDeb(ListaCreDebArrayApropiacionResponse value) {
        this.listaCreDeb = value;
    }

    /**
     * Gets the value of the listaCtaCre property.
     * 
     * @return
     *     possible object is
     *     {@link ListaCtaCreArrayApropiacionResponse }
     *     
     */
    public ListaCtaCreArrayApropiacionResponse getListaCtaCre() {
        return listaCtaCre;
    }

    /**
     * Sets the value of the listaCtaCre property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaCtaCreArrayApropiacionResponse }
     *     
     */
    public void setListaCtaCre(ListaCtaCreArrayApropiacionResponse value) {
        this.listaCtaCre = value;
    }

    /**
     * Gets the value of the resultado property.
     * 
     * @return
     *     possible object is
     *     {@link Listaresultado }
     *     
     */
    public Listaresultado getResultado() {
        return resultado;
    }

    /**
     * Sets the value of the resultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Listaresultado }
     *     
     */
    public void setResultado(Listaresultado value) {
        this.resultado = value;
    }

}
