
package ar.com.telecom.shiva.presentacion.wsdl.sap.s4.consultaProveedores;

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
 *         &lt;element name="IMP_VENDOR_DATA" type="{urn:sap-com:document:sap:rfc:functions}ZFITTSH001_I"/>
 *         &lt;element name="I_COMPANYCODE" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
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
    "impvendordata",
    "icompanycode"
})
@XmlRootElement(name = "ZFI_SHIVA_VENDOR_FIND")
public class ZFISHIVAVENDORFIND {

    @XmlElement(name = "IMP_VENDOR_DATA", required = true)
    protected ZFITTSH001I impvendordata;
    @XmlElement(name = "I_COMPANYCODE", required = true)
    protected String icompanycode;

    /**
     * Gets the value of the impvendordata property.
     * 
     * @return
     *     possible object is
     *     {@link ZFITTSH001I }
     *     
     */
    public ZFITTSH001I getIMPVENDORDATA() {
        return impvendordata;
    }

    /**
     * Sets the value of the impvendordata property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZFITTSH001I }
     *     
     */
    public void setIMPVENDORDATA(ZFITTSH001I value) {
        this.impvendordata = value;
    }

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

}
