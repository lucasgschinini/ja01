
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
 *         &lt;element name="I_IDOPERACIONSHIVA" type="{urn:sap-com:document:sap:rfc:functions}numeric7" minOccurs="0"/>
 *         &lt;element name="PDF_FILE" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="T_LINEITEMS" type="{urn:sap-com:document:sap:rfc:functions}ZFITTSH005"/>
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
    "pdffile",
    "tlineitems"
})
@XmlRootElement(name = "ZFI_SHIVA_POST_COMPENSACION")
public class ZFISHIVAPOSTCOMPENSACION {

    @XmlElement(name = "I_IDOPERACIONSHIVA")
    protected String iidoperacionshiva;
    @XmlElement(name = "PDF_FILE")
    protected byte[] pdffile;
    @XmlElement(name = "T_LINEITEMS", required = true)
    protected ZFITTSH005 tlineitems;

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
     * Gets the value of the pdffile property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPDFFILE() {
        return pdffile;
    }

    /**
     * Sets the value of the pdffile property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPDFFILE(byte[] value) {
        this.pdffile = value;
    }

    /**
     * Gets the value of the tlineitems property.
     * 
     * @return
     *     possible object is
     *     {@link ZFITTSH005 }
     *     
     */
    public ZFITTSH005 getTLINEITEMS() {
        return tlineitems;
    }

    /**
     * Sets the value of the tlineitems property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZFITTSH005 }
     *     
     */
    public void setTLINEITEMS(ZFITTSH005 value) {
        this.tlineitems = value;
    }

}
