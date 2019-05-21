
package ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZFISSH004 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZFISSH004">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="COMP_CODE" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="VENDOR" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ALLOC_NMBR" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="FISC_YEAR" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="DOC_NO" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ITEM_NUM" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
 *         &lt;element name="PSTNG_DATE" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="DOC_DATE" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="ENTRY_DATE" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="CURRENCY" type="{urn:sap-com:document:sap:rfc:functions}cuky5"/>
 *         &lt;element name="LOC_CURRCY" type="{urn:sap-com:document:sap:rfc:functions}cuky5"/>
 *         &lt;element name="ZUKURS" type="{urn:sap-com:document:sap:rfc:functions}decimal9.5"/>
 *         &lt;element name="REF_DOC_NO" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="DOC_TYPE" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="FIS_PERIOD" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/>
 *         &lt;element name="POST_KEY" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="DB_CR_IND" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="BUS_AREA" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="LC_AMOUNT" type="{urn:sap-com:document:sap:rfc:functions}decimal23.4"/>
 *         &lt;element name="AMT_DOCCUR" type="{urn:sap-com:document:sap:rfc:functions}decimal23.4"/>
 *         &lt;element name="ITEM_TEXT" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="BLINE_DATE" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="PMNTTRMS" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="PYMT_METH" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="PMNT_BLOCK" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="PMNT_BLOCK_TEXTL" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="INV_REF" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="INV_YEAR" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="INV_ITEM" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
 *         &lt;element name="REF_KEY_1" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="REF_KEY_2" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="ZSTATUS" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="ZFECHA_SHIVA" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="ZTIPO_CAMBIO_SH" type="{urn:sap-com:document:sap:rfc:functions}decimal9.5"/>
 *         &lt;element name="ZIMPORTE_ARS" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/>
 *         &lt;element name="ZIMPORTE_USD" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/>
 *         &lt;element name="ZIMPORTE_TOTAL_MD" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/>
 *         &lt;element name="ZIMPORTE_TOTAL_ML" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/>
 *         &lt;element name="ZIMPORTE_TOTAL_ARS" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/>
 *         &lt;element name="ZIMPORTE_TOTAL_USD" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/>
 *         &lt;element name="ZIMPORTE_USD_EMISION" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZFISSH004", propOrder = {
    "compcode",
    "vendor",
    "allocnmbr",
    "fiscyear",
    "docno",
    "itemnum",
    "pstngdate",
    "docdate",
    "entrydate",
    "currency",
    "loccurrcy",
    "zukurs",
    "refdocno",
    "doctype",
    "fisperiod",
    "postkey",
    "dbcrind",
    "busarea",
    "lcamount",
    "amtdoccur",
    "itemtext",
    "blinedate",
    "pmnttrms",
    "pymtmeth",
    "pmntblock",
    "pmntblocktextl",
    "invref",
    "invyear",
    "invitem",
    "refkey1",
    "refkey2",
    "zstatus",
    "zfechashiva",
    "ztipocambiosh",
    "zimportears",
    "zimporteusd",
    "zimportetotalmd",
    "zimportetotalml",
    "zimportetotalars",
    "zimportetotalusd",
    "zimporteusdemision"
})
public class ZFISSH004 {

    @XmlElement(name = "COMP_CODE", required = true)
    protected String compcode;
    @XmlElement(name = "VENDOR", required = true)
    protected String vendor;
    @XmlElement(name = "ALLOC_NMBR", required = true)
    protected String allocnmbr;
    @XmlElement(name = "FISC_YEAR", required = true)
    protected String fiscyear;
    @XmlElement(name = "DOC_NO", required = true)
    protected String docno;
    @XmlElement(name = "ITEM_NUM", required = true)
    protected String itemnum;
    @XmlElement(name = "PSTNG_DATE", required = true)
    protected String pstngdate;
    @XmlElement(name = "DOC_DATE", required = true)
    protected String docdate;
    @XmlElement(name = "ENTRY_DATE", required = true)
    protected String entrydate;
    @XmlElement(name = "CURRENCY", required = true)
    protected String currency;
    @XmlElement(name = "LOC_CURRCY", required = true)
    protected String loccurrcy;
    @XmlElement(name = "ZUKURS", required = true)
    protected BigDecimal zukurs;
    @XmlElement(name = "REF_DOC_NO", required = true)
    protected String refdocno;
    @XmlElement(name = "DOC_TYPE", required = true)
    protected String doctype;
    @XmlElement(name = "FIS_PERIOD", required = true)
    protected String fisperiod;
    @XmlElement(name = "POST_KEY", required = true)
    protected String postkey;
    @XmlElement(name = "DB_CR_IND", required = true)
    protected String dbcrind;
    @XmlElement(name = "BUS_AREA", required = true)
    protected String busarea;
    @XmlElement(name = "LC_AMOUNT", required = true)
    protected BigDecimal lcamount;
    @XmlElement(name = "AMT_DOCCUR", required = true)
    protected BigDecimal amtdoccur;
    @XmlElement(name = "ITEM_TEXT", required = true)
    protected String itemtext;
    @XmlElement(name = "BLINE_DATE", required = true)
    protected String blinedate;
    @XmlElement(name = "PMNTTRMS", required = true)
    protected String pmnttrms;
    @XmlElement(name = "PYMT_METH", required = true)
    protected String pymtmeth;
    @XmlElement(name = "PMNT_BLOCK", required = true)
    protected String pmntblock;
    @XmlElement(name = "PMNT_BLOCK_TEXTL", required = true)
    protected String pmntblocktextl;
    @XmlElement(name = "INV_REF", required = true)
    protected String invref;
    @XmlElement(name = "INV_YEAR", required = true)
    protected String invyear;
    @XmlElement(name = "INV_ITEM", required = true)
    protected String invitem;
    @XmlElement(name = "REF_KEY_1", required = true)
    protected String refkey1;
    @XmlElement(name = "REF_KEY_2", required = true)
    protected String refkey2;
    @XmlElement(name = "ZSTATUS", required = true)
    protected String zstatus;
    @XmlElement(name = "ZFECHA_SHIVA", required = true)
    protected String zfechashiva;
    @XmlElement(name = "ZTIPO_CAMBIO_SH", required = true)
    protected BigDecimal ztipocambiosh;
    @XmlElement(name = "ZIMPORTE_ARS", required = true)
    protected BigDecimal zimportears;
    @XmlElement(name = "ZIMPORTE_USD", required = true)
    protected BigDecimal zimporteusd;
    @XmlElement(name = "ZIMPORTE_TOTAL_MD", required = true)
    protected BigDecimal zimportetotalmd;
    @XmlElement(name = "ZIMPORTE_TOTAL_ML", required = true)
    protected BigDecimal zimportetotalml;
    @XmlElement(name = "ZIMPORTE_TOTAL_ARS", required = true)
    protected BigDecimal zimportetotalars;
    @XmlElement(name = "ZIMPORTE_TOTAL_USD", required = true)
    protected BigDecimal zimportetotalusd;
    @XmlElement(name = "ZIMPORTE_USD_EMISION", required = true)
    protected BigDecimal zimporteusdemision;

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
     * Gets the value of the allocnmbr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getALLOCNMBR() {
        return allocnmbr;
    }

    /**
     * Sets the value of the allocnmbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setALLOCNMBR(String value) {
        this.allocnmbr = value;
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
     * Gets the value of the pstngdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPSTNGDATE() {
        return pstngdate;
    }

    /**
     * Sets the value of the pstngdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPSTNGDATE(String value) {
        this.pstngdate = value;
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
     * Gets the value of the entrydate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENTRYDATE() {
        return entrydate;
    }

    /**
     * Sets the value of the entrydate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENTRYDATE(String value) {
        this.entrydate = value;
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
     * Gets the value of the loccurrcy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLOCCURRCY() {
        return loccurrcy;
    }

    /**
     * Sets the value of the loccurrcy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLOCCURRCY(String value) {
        this.loccurrcy = value;
    }

    /**
     * Gets the value of the zukurs property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZUKURS() {
        return zukurs;
    }

    /**
     * Sets the value of the zukurs property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZUKURS(BigDecimal value) {
        this.zukurs = value;
    }

    /**
     * Gets the value of the refdocno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFDOCNO() {
        return refdocno;
    }

    /**
     * Sets the value of the refdocno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFDOCNO(String value) {
        this.refdocno = value;
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
     * Gets the value of the postkey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOSTKEY() {
        return postkey;
    }

    /**
     * Sets the value of the postkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOSTKEY(String value) {
        this.postkey = value;
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
     * Gets the value of the busarea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBUSAREA() {
        return busarea;
    }

    /**
     * Sets the value of the busarea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBUSAREA(String value) {
        this.busarea = value;
    }

    /**
     * Gets the value of the lcamount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLCAMOUNT() {
        return lcamount;
    }

    /**
     * Sets the value of the lcamount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLCAMOUNT(BigDecimal value) {
        this.lcamount = value;
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
     * Gets the value of the blinedate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBLINEDATE() {
        return blinedate;
    }

    /**
     * Sets the value of the blinedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBLINEDATE(String value) {
        this.blinedate = value;
    }

    /**
     * Gets the value of the pmnttrms property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPMNTTRMS() {
        return pmnttrms;
    }

    /**
     * Sets the value of the pmnttrms property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPMNTTRMS(String value) {
        this.pmnttrms = value;
    }

    /**
     * Gets the value of the pymtmeth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPYMTMETH() {
        return pymtmeth;
    }

    /**
     * Sets the value of the pymtmeth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPYMTMETH(String value) {
        this.pymtmeth = value;
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
     * Gets the value of the pmntblocktextl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPMNTBLOCKTEXTL() {
        return pmntblocktextl;
    }

    /**
     * Sets the value of the pmntblocktextl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPMNTBLOCKTEXTL(String value) {
        this.pmntblocktextl = value;
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
     * Gets the value of the refkey1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFKEY1() {
        return refkey1;
    }

    /**
     * Sets the value of the refkey1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFKEY1(String value) {
        this.refkey1 = value;
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
     * Gets the value of the zstatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZSTATUS() {
        return zstatus;
    }

    /**
     * Sets the value of the zstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZSTATUS(String value) {
        this.zstatus = value;
    }

    /**
     * Gets the value of the zfechashiva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZFECHASHIVA() {
        return zfechashiva;
    }

    /**
     * Sets the value of the zfechashiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZFECHASHIVA(String value) {
        this.zfechashiva = value;
    }

    /**
     * Gets the value of the ztipocambiosh property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZTIPOCAMBIOSH() {
        return ztipocambiosh;
    }

    /**
     * Sets the value of the ztipocambiosh property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZTIPOCAMBIOSH(BigDecimal value) {
        this.ztipocambiosh = value;
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
     * Gets the value of the zimportetotalmd property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZIMPORTETOTALMD() {
        return zimportetotalmd;
    }

    /**
     * Sets the value of the zimportetotalmd property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZIMPORTETOTALMD(BigDecimal value) {
        this.zimportetotalmd = value;
    }

    /**
     * Gets the value of the zimportetotalml property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZIMPORTETOTALML() {
        return zimportetotalml;
    }

    /**
     * Sets the value of the zimportetotalml property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZIMPORTETOTALML(BigDecimal value) {
        this.zimportetotalml = value;
    }

    /**
     * Gets the value of the zimportetotalars property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZIMPORTETOTALARS() {
        return zimportetotalars;
    }

    /**
     * Sets the value of the zimportetotalars property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZIMPORTETOTALARS(BigDecimal value) {
        this.zimportetotalars = value;
    }

    /**
     * Gets the value of the zimportetotalusd property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZIMPORTETOTALUSD() {
        return zimportetotalusd;
    }

    /**
     * Sets the value of the zimportetotalusd property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZIMPORTETOTALUSD(BigDecimal value) {
        this.zimportetotalusd = value;
    }

    /**
     * Gets the value of the zimporteusdemision property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZIMPORTEUSDEMISION() {
        return zimporteusdemision;
    }

    /**
     * Sets the value of the zimporteusdemision property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZIMPORTEUSDEMISION(BigDecimal value) {
        this.zimporteusdemision = value;
    }

}
