
package ar.com.telecom.shiva.presentacion.wsdl.sap.s4.consultaProveedores;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZFISSH001 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZFISSH001">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TAX_NO_1" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="COMP_CODE" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="VENDOR" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="PMNT_BLOCK" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="TEXTL" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZFISSH001", propOrder = {
    "taxno1",
    "compcode",
    "vendor",
    "pmntblock",
    "textl"
})
public class ZFISSH001 {

    @XmlElement(name = "TAX_NO_1", required = true)
    protected String taxno1;
    @XmlElement(name = "COMP_CODE", required = true)
    protected String compcode;
    @XmlElement(name = "VENDOR", required = true)
    protected String vendor;
    @XmlElement(name = "PMNT_BLOCK", required = true)
    protected String pmntblock;
    @XmlElement(name = "TEXTL", required = true)
    protected String textl;

    /**
     * Gets the value of the taxno1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTAXNO1() {
        return taxno1;
    }

    /**
     * Sets the value of the taxno1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTAXNO1(String value) {
        this.taxno1 = value;
    }

    /**
     * Gets the value of the compcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOMPCODE() {
        return compcode;
    }

    /**
     * Sets the value of the compcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOMPCODE(String value) {
        this.compcode = value;
    }

    /**
     * Gets the value of the vendor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVENDOR() {
        return vendor;
    }

    /**
     * Sets the value of the vendor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVENDOR(String value) {
        this.vendor = value;
    }

    /**
     * Gets the value of the pmntblock property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPMNTBLOCK() {
        return pmntblock;
    }

    /**
     * Sets the value of the pmntblock property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPMNTBLOCK(String value) {
        this.pmntblock = value;
    }

    /**
     * Gets the value of the textl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTEXTL() {
        return textl;
    }

    /**
     * Sets the value of the textl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTEXTL(String value) {
        this.textl = value;
    }

}
