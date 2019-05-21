package ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * 
 * 		
 *
 * This class was generated by Apache CXF 2.7.6
 * 2019-05-21T12:28:50.874-03:00
 * Generated source version: 2.7.6
 * 
 */
@WebService(targetNamespace = "urn:sap-com:document:sap:rfc:functions", name = "ZFI_SHIVA_GETOPENITEMS_WS")
@XmlSeeAlso({ObjectFactory.class})
public interface ZFISHIVAGETOPENITEMSWS {

    @RequestWrapper(localName = "ZFI_SHIVA_GETOPENITEMS", targetNamespace = "urn:sap-com:document:sap:rfc:functions", className = "ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.ZFISHIVAGETOPENITEMS")
    @WebMethod(operationName = "ZFI_SHIVA_GETOPENITEMS", action = "urn:sap-com:document:sap:rfc:functions:ZFI_SHIVA_GETOPENITEMS_WS:ZFI_SHIVA_GETOPENITEMSRequest")
    @ResponseWrapper(localName = "ZFI_SHIVA_GETOPENITEMSResponse", targetNamespace = "urn:sap-com:document:sap:rfc:functions", className = "ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.ZFISHIVAGETOPENITEMSResponse")
    public void zfiSHIVAGETOPENITEMS(
        @WebParam(name = "I_COMPANYCODE", targetNamespace = "")
        java.lang.String iCOMPANYCODE,
        @WebParam(name = "I_FECHA_SHIVA", targetNamespace = "")
        java.lang.String iFECHASHIVA,
        @WebParam(name = "S_CURRENCY", targetNamespace = "")
        ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.URTSELOPT sCURRENCY,
        @WebParam(name = "S_DOC_DATE", targetNamespace = "")
        ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.URTSELOPT sDOCDATE,
        @WebParam(name = "S_DOC_NO", targetNamespace = "")
        ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.URTSELOPT sDOCNO,
        @WebParam(name = "S_DOC_TYPE", targetNamespace = "")
        ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.URTSELOPT sDOCTYPE,
        @WebParam(name = "S_PMNT_BLOCK", targetNamespace = "")
        ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.URTSELOPT sPMNTBLOCK,
        @WebParam(name = "S_REF_DOC_NO", targetNamespace = "")
        ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.URTSELOPT sREFDOCNO,
        @WebParam(name = "S_VENDOR", targetNamespace = "")
        ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.URTSELOPT sVENDOR,
        @WebParam(mode = WebParam.Mode.OUT, name = "ST_RETURN", targetNamespace = "")
        javax.xml.ws.Holder<ZSRETURN> stRETURN,
        @WebParam(mode = WebParam.Mode.OUT, name = "T_LINEITEMS", targetNamespace = "")
        javax.xml.ws.Holder<ZFITTSH004> tLINEITEMS,
        @WebParam(mode = WebParam.Mode.OUT, name = "T_RETURN", targetNamespace = "")
        javax.xml.ws.Holder<ZTTRETURN> tRETURN
    );
}
