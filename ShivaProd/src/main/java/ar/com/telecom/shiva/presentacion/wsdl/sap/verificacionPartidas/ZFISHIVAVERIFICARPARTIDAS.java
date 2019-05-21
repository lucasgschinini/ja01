
package ar.com.telecom.shiva.presentacion.wsdl.sap.verificacionPartidas;

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
 *         &lt;element name="I_IDOPERACIONSHIVA" type="{urn:sap-com:document:sap:rfc:functions}numeric7" minOccurs="0"/>
 *         &lt;element name="T_LINEITEMS" type="{urn:sap-com:document:sap:rfc:functions}ZFITTSH002"/>
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
    "iidoperacionshiva",
    "tlineitems"
})
@XmlRootElement(name = "ZFI_SHIVA_VERIFICAR_PARTIDAS")
public class ZFISHIVAVERIFICARPARTIDAS {

    @XmlElement(name = "I_IDOPERACIONSHIVA")
    protected String iidoperacionshiva;
    @XmlElement(name = "T_LINEITEMS", required = true)
    protected ZFITTSH002 tlineitems;

    /**
     * Gets the value of the iidoperacionshiva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIIDOPERACIONSHIVA() {
        return iidoperacionshiva;
    }

    /**
     * Sets the value of the iidoperacionshiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIIDOPERACIONSHIVA(String value) {
        this.iidoperacionshiva = value;
    }

    /**
     * Gets the value of the tlineitems property.
     * 
     * @return
     *     possible object is
     *     {@link ZFITTSH002 }
     *     
     */
    public ZFITTSH002 getTLINEITEMS() {
        return tlineitems;
    }

    /**
     * Sets the value of the tlineitems property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZFITTSH002 }
     *     
     */
    public void setTLINEITEMS(ZFITTSH002 value) {
        this.tlineitems = value;
    }

}
