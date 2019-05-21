
package ar.com.telecom.shiva.presentacion.wsdl.sap.imputacion;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZFISSH005 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZFISSH005">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="COMP_CODE" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="VENDOR" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="FISC_YEAR" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="DOC_NO" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ITEM_NUM" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
 *         &lt;element name="CURRENCY" type="{urn:sap-com:document:sap:rfc:functions}cuky5"/>
 *         &lt;element name="DOC_TYPE" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="DOC_DATE" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="FIS_PERIOD" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/>
 *         &lt;element name="DB_CR_IND" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="AMT_DOCCUR" type="{urn:sap-com:document:sap:rfc:functions}decimal23.4"/>
 *         &lt;element name="PMNT_BLOCK" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="INV_REF" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="INV_YEAR" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="INV_ITEM" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
 *         &lt;element name="REF_KEY_2" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="ITEM_TEXT" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="ZIMPORTE_ARS" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/>
 *         &lt;element name="ZIMPORTE_USD" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/>
 *         &lt;element name="ZSHIVA_MONEDA" type="{urn:sap-com:document:sap:rfc:functions}cuky5"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZFISSH005", propOrder = {
    "compcode",
    "vendor",
    "fiscyear",
    "docno",
    "itemnum",
    "currency",
    "doctype",
    "docdate",
    "fisperiod",
    "dbcrind",
    "amtdoccur",
    "pmntblock",
    "invref",
    "invyear",
    "invitem",
    "refkey2",
    "itemtext",
    "zimportears",
    "zimporteusd",
    "zshivamoneda"
})
public class ZFISSH005 {

    @XmlElement(name = "COMP_CODE", required = true)
    protected String compcode;
    @XmlElement(name = "VENDOR", required = true)
    protected String vendor;
    @XmlElement(name = "FISC_YEAR", required = true)
    protected String fiscyear;
    @XmlElement(name = "DOC_NO", required = true)
    protected String docno;
    @XmlElement(name = "ITEM_NUM", required = true)
    protected String itemnum;
    @XmlElement(name = "CURRENCY", required = true)
    protected String currency;
    @XmlElement(name = "DOC_TYPE", required = true)
    protected String doctype;
    @XmlElement(name = "DOC_DATE", required = true)
    protected String docdate;
    @XmlElement(name = "FIS_PERIOD", required = true)
    protected String fisperiod;
    @XmlElement(name = "DB_CR_IND", required = true)
    protected String dbcrind;
    @XmlElement(name = "AMT_DOCCUR", required = true)
    protected BigDecimal amtdoccur;
    @XmlElement(name = "PMNT_BLOCK", required = true)
    protected String pmntblock;
    @XmlElement(name = "INV_REF", required = true)
    protected String invref;
    @XmlElement(name = "INV_YEAR", required = true)
    protected String invyear;
    @XmlElement(name = "INV_ITEM", required = true)
    protected String invitem;
    @XmlElement(name = "REF_KEY_2", required = true)
    protected String refkey2;
    @XmlElement(name = "ITEM_TEXT", required = true)
    protected String itemtext;
    @XmlElement(name = "ZIMPORTE_ARS", required = true)
    protected BigDecimal zimportears;
    @XmlElement(name = "ZIMPORTE_USD", required = true)
    protected BigDecimal zimporteusd;
    @XmlElement(name = "ZSHIVA_MONEDA", required = true)
    protected String zshivamoneda;

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

    /**
     * Gets the value of the itemnum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getITEMNUM() {
        return itemnum;
    }

    /**
     * Sets the value of the itemnum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setITEMNUM(String value) {
        this.itemnum = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCURRENCY() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCURRENCY(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the doctype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOCTYPE() {
        return doctype;
    }

    /**
     * Sets the value of the doctype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOCTYPE(String value) {
        this.doctype = value;
    }

    /**
     * Gets the value of the docdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOCDATE() {
        return docdate;
    }

    /**
     * Sets the value of the docdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOCDATE(String value) {
        this.docdate = value;
    }

    /**
     * Gets the value of the fisperiod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFISPERIOD() {
        return fisperiod;
    }

    /**
     * Sets the value of the fisperiod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFISPERIOD(String value) {
        this.fisperiod = value;
    }

    /**
     * Gets the value of the dbcrind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDBCRIND() {
        return dbcrind;
    }

    /**
     * Sets the value of the dbcrind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDBCRIND(String value) {
        this.dbcrind = value;
    }

    /**
     * Gets the value of the amtdoccur property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAMTDOCCUR() {
        return amtdoccur;
    }

    /**
     * Sets the value of the amtdoccur property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAMTDOCCUR(BigDecimal value) {
        this.amtdoccur = value;
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
     * Gets the value of the invref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINVREF() {
        return invref;
    }

    /**
     * Sets the value of the invref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINVREF(String value) {
        this.invref = value;
    }

    /**
     * Gets the value of the invyear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINVYEAR() {
        return invyear;
    }

    /**
     * Sets the value of the invyear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINVYEAR(String value) {
        this.invyear = value;
    }

    /**
     * Gets the value of the invitem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINVITEM() {
        return invitem;
    }

    /**
     * Sets the value of the invitem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINVITEM(String value) {
        this.invitem = value;
    }

    /**
     * Gets the value of the refkey2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFKEY2() {
        return refkey2;
    }

    /**
     * Sets the value of the refkey2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFKEY2(String value) {
        this.refkey2 = value;
    }

    /**
     * Gets the value of the itemtext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getITEMTEXT() {
        return itemtext;
    }

    /**
     * Sets the value of the itemtext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setITEMTEXT(String value) {
        this.itemtext = value;
    }

    /**
     * Gets the value of the zimportears property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZIMPORTEARS() {
        return zimportears;
    }

    /**
     * Sets the value of the zimportears property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZIMPORTEARS(BigDecimal value) {
        this.zimportears = value;
    }

    /**
     * Gets the value of the zimporteusd property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZIMPORTEUSD() {
        return zimporteusd;
    }

    /**
     * Sets the value of the zimporteusd property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZIMPORTEUSD(BigDecimal value) {
        this.zimporteusd = value;
    }

    /**
     * Gets the value of the zshivamoneda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZSHIVAMONEDA() {
        return zshivamoneda;
    }

    /**
     * Sets the value of the zshivamoneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZSHIVAMONEDA(String value) {
        this.zshivamoneda = value;
    }

}
