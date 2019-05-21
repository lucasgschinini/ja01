
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultaEstadoDeuda complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultaEstadoDeuda">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdDocumentoMIC" type="{http://shiva.ws.intelap.com/}IdDocumentoMIC" minOccurs="0"/>
 *         &lt;element name="IdDocumentoCalipso" type="{http://shiva.ws.intelap.com/}IdDocumentoCalipso" minOccurs="0"/>
 *         &lt;element name="InformacionGeneralApropiacion" type="{http://shiva.ws.intelap.com/}InformacionGeneralApropiacion" minOccurs="0"/>
 *         &lt;element name="ResultadoProcesamiento" type="{http://shiva.ws.intelap.com/}ResultadoProcesamiento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultaEstadoDeuda", propOrder = {
    "idDocumentoMIC",
    "idDocumentoCalipso",
    "informacionGeneralApropiacion",
    "resultadoProcesamiento"
})
public class ResultadoConsultaEstadoDeuda {

    @XmlElement(name = "IdDocumentoMIC", nillable = true)
    protected IdDocumentoMIC idDocumentoMIC;
    @XmlElement(name = "IdDocumentoCalipso", nillable = true)
    protected IdDocumentoCalipso idDocumentoCalipso;
    @XmlElement(name = "InformacionGeneralApropiacion", nillable = true)
    protected InformacionGeneralApropiacion informacionGeneralApropiacion;
    @XmlElement(name = "ResultadoProcesamiento", nillable = true)
    protected ResultadoProcesamiento resultadoProcesamiento;

    /**
     * Gets the value of the idDocumentoMIC property.
     * 
     * @return
     *     possible object is
     *     {@link IdDocumentoMIC }
     *     
     */
    public IdDocumentoMIC getIdDocumentoMIC() {
        return idDocumentoMIC;
    }

    /**
     * Sets the value of the idDocumentoMIC property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdDocumentoMIC }
     *     
     */
    public void setIdDocumentoMIC(IdDocumentoMIC value) {
        this.idDocumentoMIC = value;
    }

    /**
     * Gets the value of the idDocumentoCalipso property.
     * 
     * @return
     *     possible object is
     *     {@link IdDocumentoCalipso }
     *     
     */
    public IdDocumentoCalipso getIdDocumentoCalipso() {
        return idDocumentoCalipso;
    }

    /**
     * Sets the value of the idDocumentoCalipso property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdDocumentoCalipso }
     *     
     */
    public void setIdDocumentoCalipso(IdDocumentoCalipso value) {
        this.idDocumentoCalipso = value;
    }

    /**
     * Gets the value of the informacionGeneralApropiacion property.
     * 
     * @return
     *     possible object is
     *     {@link InformacionGeneralApropiacion }
     *     
     */
    public InformacionGeneralApropiacion getInformacionGeneralApropiacion() {
        return informacionGeneralApropiacion;
    }

    /**
     * Sets the value of the informacionGeneralApropiacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link InformacionGeneralApropiacion }
     *     
     */
    public void setInformacionGeneralApropiacion(InformacionGeneralApropiacion value) {
        this.informacionGeneralApropiacion = value;
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
