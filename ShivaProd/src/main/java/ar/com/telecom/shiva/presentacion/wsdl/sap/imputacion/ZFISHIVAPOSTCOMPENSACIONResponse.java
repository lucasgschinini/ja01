
package ar.com.telecom.shiva.presentacion.wsdl.sap.imputacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *       &lt;sequence>
 *         &lt;element name="E_COMPENSACION" type="{urn:sap-com:document:sap:rfc:functions}ZFISSH008"/>
 *         &lt;element name="E_IDOPERACIONSHIVA" type="{urn:sap-com:document:sap:rfc:functions}numeric7"/>
 *         &lt;element name="ST_RETURN" type="{urn:sap-com:document:sap:rfc:functions}ZSRETURN"/>
 *         &lt;element name="T_RETURN" type="{urn:sap-com:document:sap:rfc:functions}ZTTRETURN"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ecompensacion",
    "eidoperacionshiva",
    "streturn",
    "treturn"
})
@XmlRootElement(name = "ZFI_SHIVA_POST_COMPENSACIONResponse")
public class ZFISHIVAPOSTCOMPENSACIONResponse {

    @XmlElement(name = "E_COMPENSACION", required = true)
    protected ZFISSH008 ecompensacion;
    @XmlElement(name = "E_IDOPERACIONSHIVA", required = true)
    protected String eidoperacionshiva;
    @XmlElement(name = "ST_RETURN", required = true)
    protected ZSRETURN streturn;
    @XmlElement(name = "T_RETURN", required = true)
    protected ZTTRETURN treturn;

    /**
     * Gets the value of the ecompensacion property.
     * 
     * @return
     *     possible object is
     *     {@link ZFISSH008 }
     *     
     */
    public ZFISSH008 getECOMPENSACION() {
        return ecompensacion;
    }

    /**
     * Sets the value of the ecompensacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZFISSH008 }
     *     
     */
    public void setECOMPENSACION(ZFISSH008 value) {
        this.ecompensacion = value;
    }

    /**
     * Gets the value of the eidoperacionshiva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEIDOPERACIONSHIVA() {
        return eidoperacionshiva;
    }

    /**
     * Sets the value of the eidoperacionshiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEIDOPERACIONSHIVA(String value) {
        this.eidoperacionshiva = value;
    }

    /**
     * Gets the value of the streturn property.
     * 
     * @return
     *     possible object is
     *     {@link ZSRETURN }
     *     
     */
    public ZSRETURN getSTRETURN() {
        return streturn;
    }

    /**
     * Sets the value of the streturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZSRETURN }
     *     
     */
    public void setSTRETURN(ZSRETURN value) {
        this.streturn = value;
    }

    /**
     * Gets the value of the treturn property.
     * 
     * @return
     *     possible object is
     *     {@link ZTTRETURN }
     *     
     */
    public ZTTRETURN getTRETURN() {
        return treturn;
    }

    /**
     * Sets the value of the treturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZTTRETURN }
     *     
     */
    public void setTRETURN(ZTTRETURN value) {
        this.treturn = value;
    }

}
