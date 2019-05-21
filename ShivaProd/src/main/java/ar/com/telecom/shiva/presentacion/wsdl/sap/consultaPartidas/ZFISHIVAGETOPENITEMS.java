
package ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas;

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
 *         &lt;element name="I_COMPANYCODE" type="{urn:sap-com:document:sap:rfc:functions}char4" minOccurs="0"/>
 *         &lt;element name="I_FECHA_SHIVA" type="{urn:sap-com:document:sap:rfc:functions}date10" minOccurs="0"/>
 *         &lt;element name="S_CURRENCY" type="{urn:sap-com:document:sap:rfc:functions}UR_T_SELOPT" minOccurs="0"/>
 *         &lt;element name="S_DOC_DATE" type="{urn:sap-com:document:sap:rfc:functions}UR_T_SELOPT" minOccurs="0"/>
 *         &lt;element name="S_DOC_NO" type="{urn:sap-com:document:sap:rfc:functions}UR_T_SELOPT" minOccurs="0"/>
 *         &lt;element name="S_DOC_TYPE" type="{urn:sap-com:document:sap:rfc:functions}UR_T_SELOPT" minOccurs="0"/>
 *         &lt;element name="S_PMNT_BLOCK" type="{urn:sap-com:document:sap:rfc:functions}UR_T_SELOPT" minOccurs="0"/>
 *         &lt;element name="S_REF_DOC_NO" type="{urn:sap-com:document:sap:rfc:functions}UR_T_SELOPT" minOccurs="0"/>
 *         &lt;element name="S_VENDOR" type="{urn:sap-com:document:sap:rfc:functions}UR_T_SELOPT" minOccurs="0"/>
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
    "icompanycode",
    "ifechashiva",
    "scurrency",
    "sdocdate",
    "sdocno",
    "sdoctype",
    "spmntblock",
    "srefdocno",
    "svendor"
})
@XmlRootElement(name = "ZFI_SHIVA_GETOPENITEMS")
public class ZFISHIVAGETOPENITEMS {

    @XmlElement(name = "I_COMPANYCODE")
    protected String icompanycode;
    @XmlElement(name = "I_FECHA_SHIVA")
    protected String ifechashiva;
    @XmlElement(name = "S_CURRENCY")
    protected URTSELOPT scurrency;
    @XmlElement(name = "S_DOC_DATE")
    protected URTSELOPT sdocdate;
    @XmlElement(name = "S_DOC_NO")
    protected URTSELOPT sdocno;
    @XmlElement(name = "S_DOC_TYPE")
    protected URTSELOPT sdoctype;
    @XmlElement(name = "S_PMNT_BLOCK")
    protected URTSELOPT spmntblock;
    @XmlElement(name = "S_REF_DOC_NO")
    protected URTSELOPT srefdocno;
    @XmlElement(name = "S_VENDOR")
    protected URTSELOPT svendor;

    /**
     * Gets the value of the icompanycode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getICOMPANYCODE() {
        return icompanycode;
    }

    /**
     * Sets the value of the icompanycode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setICOMPANYCODE(String value) {
        this.icompanycode = value;
    }

    /**
     * Gets the value of the ifechashiva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFECHASHIVA() {
        return ifechashiva;
    }

    /**
     * Sets the value of the ifechashiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFECHASHIVA(String value) {
        this.ifechashiva = value;
    }

    /**
     * Gets the value of the scurrency property.
     * 
     * @return
     *     possible object is
     *     {@link URTSELOPT }
     *     
     */
    public URTSELOPT getSCURRENCY() {
        return scurrency;
    }

    /**
     * Sets the value of the scurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link URTSELOPT }
     *     
     */
    public void setSCURRENCY(URTSELOPT value) {
        this.scurrency = value;
    }

    /**
     * Gets the value of the sdocdate property.
     * 
     * @return
     *     possible object is
     *     {@link URTSELOPT }
     *     
     */
    public URTSELOPT getSDOCDATE() {
        return sdocdate;
    }

    /**
     * Sets the value of the sdocdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link URTSELOPT }
     *     
     */
    public void setSDOCDATE(URTSELOPT value) {
        this.sdocdate = value;
    }

    /**
     * Gets the value of the sdocno property.
     * 
     * @return
     *     possible object is
     *     {@link URTSELOPT }
     *     
     */
    public URTSELOPT getSDOCNO() {
        return sdocno;
    }

    /**
     * Sets the value of the sdocno property.
     * 
     * @param value
     *     allowed object is
     *     {@link URTSELOPT }
     *     
     */
    public void setSDOCNO(URTSELOPT value) {
        this.sdocno = value;
    }

    /**
     * Gets the value of the sdoctype property.
     * 
     * @return
     *     possible object is
     *     {@link URTSELOPT }
     *     
     */
    public URTSELOPT getSDOCTYPE() {
        return sdoctype;
    }

    /**
     * Sets the value of the sdoctype property.
     * 
     * @param value
     *     allowed object is
     *     {@link URTSELOPT }
     *     
     */
    public void setSDOCTYPE(URTSELOPT value) {
        this.sdoctype = value;
    }

    /**
     * Gets the value of the spmntblock property.
     * 
     * @return
     *     possible object is
     *     {@link URTSELOPT }
     *     
     */
    public URTSELOPT getSPMNTBLOCK() {
        return spmntblock;
    }

    /**
     * Sets the value of the spmntblock property.
     * 
     * @param value
     *     allowed object is
     *     {@link URTSELOPT }
     *     
     */
    public void setSPMNTBLOCK(URTSELOPT value) {
        this.spmntblock = value;
    }

    /**
     * Gets the value of the srefdocno property.
     * 
     * @return
     *     possible object is
     *     {@link URTSELOPT }
     *     
     */
    public URTSELOPT getSREFDOCNO() {
        return srefdocno;
    }

    /**
     * Sets the value of the srefdocno property.
     * 
     * @param value
     *     allowed object is
     *     {@link URTSELOPT }
     *     
     */
    public void setSREFDOCNO(URTSELOPT value) {
        this.srefdocno = value;
    }

    /**
     * Gets the value of the svendor property.
     * 
     * @return
     *     possible object is
     *     {@link URTSELOPT }
     *     
     */
    public URTSELOPT getSVENDOR() {
        return svendor;
    }

    /**
     * Sets the value of the svendor property.
     * 
     * @param value
     *     allowed object is
     *     {@link URTSELOPT }
     *     
     */
    public void setSVENDOR(URTSELOPT value) {
        this.svendor = value;
    }

}
