
package ar.com.telecom.shiva.presentacion.wsdl.sap.imputacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZFISSH008 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZFISSH008">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="COMP_CODE" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="FISC_YEAR" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="DOC_NO" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZFISSH008", propOrder = {
    "compcode",
    "fiscyear",
    "docno"
})
public class ZFISSH008 {

    @XmlElement(name = "COMP_CODE", required = true)
    protected String compcode;
    @XmlElement(name = "FISC_YEAR", required = true)
    protected String fiscyear;
    @XmlElement(name = "DOC_NO", required = true)
    protected String docno;

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
     * Gets the value of the fiscyear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFISCYEAR() {
        return fiscyear;
    }

    /**
     * Sets the value of the fiscyear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFISCYEAR(String value) {
        this.fiscyear = value;
    }

    /**
     * Gets the value of the docno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOCNO() {
        return docno;
    }

    /**
     * Sets the value of the docno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOCNO(String value) {
        this.docno = value;
    }

}
