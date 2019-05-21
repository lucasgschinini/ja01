
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Documento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Documento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Empresa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Sistema" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Importe" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="InfoAdicionalMedPagNoComisionables" type="{http://shiva.ws.intelap.com/}InfoAdicionalMedPagNoComisionables" minOccurs="0"/>
 *         &lt;element name="IdDocumentoMIC" type="{http://shiva.ws.intelap.com/}IdDocumentoMIC" minOccurs="0"/>
 *         &lt;element name="IdDocumentoCalipso" type="{http://shiva.ws.intelap.com/}IdDocumentoCalipso" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Documento", propOrder = {
    "empresa",
    "sistema",
    "importe",
    "infoAdicionalMedPagNoComisionables",
    "idDocumentoMIC",
    "idDocumentoCalipso"
})
public class Documento {

    @XmlElement(name = "Empresa", required = true)
    protected String empresa;
    @XmlElement(name = "Sistema", required = true)
    protected String sistema;
    @XmlElement(name = "Importe", required = true)
    protected BigDecimal importe;
    @XmlElement(name = "InfoAdicionalMedPagNoComisionables", nillable = true)
    protected InfoAdicionalMedPagNoComisionables infoAdicionalMedPagNoComisionables;
    @XmlElement(name = "IdDocumentoMIC", nillable = true)
    protected IdDocumentoMIC idDocumentoMIC;
    @XmlElement(name = "IdDocumentoCalipso", nillable = true)
    protected IdDocumentoCalipso idDocumentoCalipso;

    /**
     * Gets the value of the empresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Sets the value of the empresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmpresa(String value) {
        this.empresa = value;
    }

    /**
     * Gets the value of the sistema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSistema() {
        return sistema;
    }

    /**
     * Sets the value of the sistema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSistema(String value) {
        this.sistema = value;
    }

    /**
     * Gets the value of the importe property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporte() {
        return importe;
    }

    /**
     * Sets the value of the importe property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporte(BigDecimal value) {
        this.importe = value;
    }

    /**
     * Gets the value of the infoAdicionalMedPagNoComisionables property.
     * 
     * @return
     *     possible object is
     *     {@link InfoAdicionalMedPagNoComisionables }
     *     
     */
    public InfoAdicionalMedPagNoComisionables getInfoAdicionalMedPagNoComisionables() {
        return infoAdicionalMedPagNoComisionables;
    }

    /**
     * Sets the value of the infoAdicionalMedPagNoComisionables property.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoAdicionalMedPagNoComisionables }
     *     
     */
    public void setInfoAdicionalMedPagNoComisionables(InfoAdicionalMedPagNoComisionables value) {
        this.infoAdicionalMedPagNoComisionables = value;
    }

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

}
