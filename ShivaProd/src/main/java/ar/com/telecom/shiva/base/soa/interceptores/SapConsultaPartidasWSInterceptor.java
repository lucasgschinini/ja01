/**
 * 
 */
package ar.com.telecom.shiva.base.soa.interceptores;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.commons.lang.text.StrSubstitutor;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.soa.singleton.SoapMensajesSingleton;
import ar.com.telecom.shiva.base.utils.ControlXml;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;

/**
 * @author u586743
 *
 */
public class SapConsultaPartidasWSInterceptor implements SOAPHandler<SOAPMessageContext> {
	
	
	@Override
	public void close(MessageContext arg0) {
		
	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {

		logToSystemOut(arg0);
        return true;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext arg0) {
		
		logToSystemOut(arg0);
        return true;
	}

	@Override
	public Set<QName> getHeaders() {
		 return new TreeSet<QName>();
	}	
	
    /**
     * Traceo los mensajes SOAP
     * @param smc
     */
    private void logToSystemOut(SOAPMessageContext smc) {
    	Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
    	SOAPMessage message = smc.getMessage();
    	
    	StringBuffer traceo = new StringBuffer("");
    	if (outboundProperty.booleanValue()) {

    	    String endpointAddress = (String) smc.get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
    	    traceo.append("Endpoint (url del servicio): " + endpointAddress + "\n");    		
    		traceo.append("Salida:\n");
    	} else {
    		traceo.append("Entrada:\n");
    		
    		// Comienzo Dummy  
    		//cargarDatosDummy(smc);
			// Fin Dummy
    	}
    	 
    	try {
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	    message.writeTo(baos);
    	    String mensaje = baos.toString();
    	    traceo.append(mensaje);
    	    
    	    Traza.transaccionWS(getClass(), traceo.toString());
    	    
    	    if (outboundProperty.booleanValue()) {
            	SoapMensajesSingleton.addMensajeSalida("", mensaje);
    	    } else {
    	        SoapMensajesSingleton.addMensajeEntrada("", mensaje);
    	    }
    	    
    	} catch (Exception e) {
    		Traza.error(getClass(), e.getMessage(), e);
    	}
    }
    
    /**
     * 
     * @param smc
     */
    @SuppressWarnings("unused")
	private void cargarDatosDummy(SOAPMessageContext smc) {
		
    	SOAPMessage messageDummy = smc.getMessage();
    	StringBuffer traceo = new StringBuffer("");
    	
    	try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	    messageDummy.writeTo(baos);
    	    String mensaje = baos.toString();
    	    traceo.append(mensaje);
    	    
    	    Traza.transaccionWS(getClass(), traceo.toString());
    	    
    	    String idOperacion = ControlXml.buscarElemento("idShiva", baos.toByteArray());
            String idTransaccion = ControlXml.buscarElemento("idTransaccion", baos.toByteArray());
            String key = idOperacion;
            if (!idOperacion.contains(".")) {
            	key += "." + (Validaciones.isNullOrEmpty(idTransaccion)?"":idTransaccion);
            }
//			
//			//8750090.00001
//			if("8750090.00001".equals(key)){
//				mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header /><SOAP-ENV:Body><ns1:ApropiacionResponse><respuestaapropiacion><idShiva>8750090.00001</idShiva><detFac><idCobranza/><importemora>.11</importemora><montocta>.11</montocta><tipoCambioFechaEmision>3.6400</tipoCambioFechaEmision><tipoCambioFechaCobro>3.1000</tipoCambioFechaCobro><importeAplicadoFechaEmisionPesos>5.7500</importeAplicadoFechaEmisionPesos><importeAplicadoMonedaOrigen>1.5797</importeAplicadoMonedaOrigen><listaResultadoApropiacion><resultadoApropiacion><resultado>OK</resultado><codigoerror/><descripcionerror/></resultadoApropiacion></listaResultadoApropiacion></detFac><listaCreDeb><detalle><idCobranza/><importe>.85</importe><importeCapital>.67</importeCapital><importeImpuestos>.18</importeImpuestos><moneda>$</moneda><idDocctascob>4659914</idDocctascob><tipo>CRE</tipo><clase>A</clase><sucursal>0780</sucursal><numero>00000022</numero><idDocumentoCuentasCobranza/><vencimiento>2016/03/31</vencimiento><importeAplicado>.85</importeAplicado><origenDelDocumento>DC</origenDelDocumento><InformacionAdicionalTagetikCalipso><RazonSocialCliente>PEREZ PEREZ</RazonSocialCliente><TipoCliente>R1</TipoCliente><CUIT>30577922620</CUIT></InformacionAdicionalTagetikCalipso><InformacionAdicionalDacota><UnidadOperativa>UGC</UnidadOperativa><SubTipo>DIF</SubTipo><Holding/></InformacionAdicionalDacota></detalle></listaCreDeb><listaCtaCre><detalle><idCobranza/><idDocumento>1370008</idDocumento><tipo>CRE</tipo><clase>A</clase><sucursal>0308</sucursal><numero>00009159</numero><tipoCambioFechaEmision>1.0000</tipoCambioFechaEmision><tipoCambioFechaCobro>2.0000</tipoCambioFechaCobro><importeAplicadoFechaEmisionPesos>3.0000</importeAplicadoFechaEmisionPesos><importeAplicadoMonedaOrigen>4.0000</importeAplicadoMonedaOrigen><detallenewcta/><resultadoApropiacion><resultado>OK</resultado><codigoerror/><descripcionerror/></resultadoApropiacion></detalle></listaCtaCre><resultado><idShiva>8750090.00001</idShiva><Resultado><resultado>OK</resultado><codigoerror/><descripcionerror/></Resultado></resultado></respuestaapropiacion></ns1:ApropiacionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//			}
//
//			//8750090.00002
//			if("8750090.00002".equals(key)){
//				mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header /><SOAP-ENV:Body><ns1:ApropiacionResponse><respuestaapropiacion><idShiva>8750090.00002</idShiva><detFac><idCobranza/><importemora>.02</importemora><montocta>.02</montocta><tipoCambioFechaEmision>3.6400</tipoCambioFechaEmision><tipoCambioFechaCobro>3.0770</tipoCambioFechaCobro><importeAplicadoFechaEmisionPesos>.1200</importeAplicadoFechaEmisionPesos><importeAplicadoMonedaOrigen>.0330</importeAplicadoMonedaOrigen><listaResultadoApropiacion><resultadoApropiacion><resultado>OK</resultado><codigoerror/><descripcionerror/></resultadoApropiacion></listaResultadoApropiacion></detFac><listaCreDeb><detalle><idCobranza/><importe>.02</importe><importeCapital>.02</importeCapital><importeImpuestos>0</importeImpuestos><moneda>$</moneda><idDocctascob>4659915</idDocctascob><tipo>CRE</tipo><clase>A</clase><sucursal>0780</sucursal><numero>00000022</numero><idDocumentoCuentasCobranza/><vencimiento>2016/03/31</vencimiento><importeAplicado>.02</importeAplicado><origenDelDocumento>DC</origenDelDocumento><InformacionAdicionalTagetikCalipso><RazonSocialCliente>PEREZPEREZ</RazonSocialCliente><TipoCliente>R1</TipoCliente><CUIT>30577922620</CUIT></InformacionAdicionalTagetikCalipso><InformacionAdicionalDacota><UnidadOperativa>UGC</UnidadOperativa><SubTipo>DIF</SubTipo><Holding/></InformacionAdicionalDacota></detalle></listaCreDeb><listaCtaCre><detalle><idCobranza/><idDocumento>914057</idDocumento><tipo>CTA</tipo><clase>X</clase><sucursal>0780</sucursal><numero>00012322</numero><tipoCambioFechaEmision>5.0000</tipoCambioFechaEmision><tipoCambioFechaCobro>6.0000</tipoCambioFechaCobro><importeAplicadoFechaEmisionPesos>7.0000</importeAplicadoFechaEmisionPesos><importeAplicadoMonedaOrigen>8.0000</importeAplicadoMonedaOrigen><detallenewcta><importe>49.09</importe><moneda>$</moneda><idDocumento/><tipo>CTA</tipo><clase>X</clase><sucursal>0780</sucursal><numero>00012322</numero><idDocumentoCuentasCobranza/><fechaoriginalcta>2007/06/08</fechaoriginalcta></detallenewcta><resultadoApropiacion><resultado>OK</resultado><codigoerror/><descripcionerror/></resultadoApropiacion></detalle></listaCtaCre><resultado><idShiva>8750090.00002</idShiva><Resultado><resultado>OK</resultado><codigoerror/><descripcionerror/></Resultado></resultado></respuestaapropiacion></ns1:ApropiacionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//			}
//			
//			//8750090.00003
//			if("8750090.00003".equals(key)){
//				mensaje = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header /><SOAP-ENV:Body><ns1:ApropiacionResponse><respuestaapropiacion><idShiva>8750090.00003</idShiva><detFac><idCobranza/><importemora>.33</importemora><montocta>.33</montocta><tipoCambioFechaEmision>3.5400</tipoCambioFechaEmision><tipoCambioFechaCobro>3.0770</tipoCambioFechaCobro><importeAplicadoFechaEmisionPesos>2.2000</importeAplicadoFechaEmisionPesos><importeAplicadoMonedaOrigen>.6215</importeAplicadoMonedaOrigen><listaResultadoApropiacion><resultadoApropiacion><resultado>OK</resultado><codigoerror/><descripcionerror/></resultadoApropiacion></listaResultadoApropiacion></detFac><listaCreDeb><detalle><idCobranza/><importe>.29</importe><importeCapital>.23</importeCapital><importeImpuestos>.06</importeImpuestos><moneda>$</moneda><idDocctascob>4659917</idDocctascob><tipo>CRE</tipo><clase>A</clase><sucursal>0780</sucursal><numero>00000022</numero><idDocumentoCuentasCobranza/><vencimiento>2016/04/01</vencimiento><importeAplicado>.29</importeAplicado><origenDelDocumento>DC</origenDelDocumento><InformacionAdicionalTagetikCalipso><RazonSocialCliente>PEREZPEREZ</RazonSocialCliente><TipoCliente>R1</TipoCliente><CUIT>30577922620</CUIT></InformacionAdicionalTagetikCalipso><InformacionAdicionalDacota><UnidadOperativa>UGC</UnidadOperativa><SubTipo>DIF</SubTipo><Holding/></InformacionAdicionalDacota></detalle></listaCreDeb><listaCtaCre><detalle><idCobranza/><idDocumento>914057</idDocumento><tipo>CTA</tipo><clase>X</clase><sucursal>0780</sucursal><numero>00012322</numero><tipoCambioFechaEmision>9.0000</tipoCambioFechaEmision><tipoCambioFechaCobro>10.0000</tipoCambioFechaCobro><importeAplicadoFechaEmisionPesos>11.0000</importeAplicadoFechaEmisionPesos><importeAplicadoMonedaOrigen>12</importeAplicadoMonedaOrigen><detallenewcta><importe>47.18</importe><moneda>$</moneda><idDocumento/><tipo>CTA</tipo><clase>X</clase><sucursal>0780</sucursal><numero>00012322</numero><idDocumentoCuentasCobranza/><fechaoriginalcta>2007/06/08</fechaoriginalcta></detallenewcta><resultadoApropiacion><resultado>OK</resultado><codigoerror/><descripcionerror/></resultadoApropiacion></detalle></listaCtaCre><resultado><idShiva>8750090.00003</idShiva><Resultado><resultado>OK</resultado><codigoerror/><descripcionerror/></Resultado></resultado></respuestaapropiacion></ns1:ApropiacionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
//			}
//			
//			//8750090.00004
//			if("8750090.00004".equals(key)){
            StringBuilder str = new StringBuilder();
            String codigoVendedor = "0001005371";
            
            
            str.append("<soap-env:Envelope xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap-env:Header/><soap-env:Body><n0:ZFI_SHIVA_GETOPENITEMSResponse xmlns:n0=\"urn:sap-com:document:sap:rfc:functions\">");
            str.append("<ST_RETURN><TYPE>S</TYPE><NUMBER>000</NUMBER><MESSAGE>Proceso OK</MESSAGE></ST_RETURN>");
            str.append("<T_LINEITEMS>");
//            str.append("<item><COMP_CODE>3200</COMP_CODE><VENDOR>" +codigoVendedor+ "</VENDOR><ALLOC_NMBR/><FISC_YEAR>2016</FISC_YEAR><DOC_NO>3600008615</DOC_NO><ITEM_NUM>001</ITEM_NUM><PSTNG_DATE>2016-12-26</PSTNG_DATE><DOC_DATE>2016-12-26</DOC_DATE><ENTRY_DATE>2016-12-26</ENTRY_DATE><CURRENCY>ARS</CURRENCY><LOC_CURRCY>ARS</LOC_CURRCY><ZUKURS>16.14</ZUKURS><REF_DOC_NO>0001A98765433</REF_DOC_NO><DOC_TYPE>KB</DOC_TYPE><FIS_PERIOD>12</FIS_PERIOD><POST_KEY>31</POST_KEY><DB_CR_IND>H</DB_CR_IND><BUS_AREA>0001</BUS_AREA><LC_AMOUNT>-100.0</LC_AMOUNT><AMT_DOCCUR>-100.0</AMT_DOCCUR><ITEM_TEXT/><BLINE_DATE>2016-12-26</BLINE_DATE><PMNTTRMS>FE01</PMNTTRMS><PYMT_METH>T</PYMT_METH><PMNT_BLOCK>F</PMNT_BLOCK><PMNT_BLOCK_TEXTL>Pdte compensación</PMNT_BLOCK_TEXTL><INV_REF/><INV_YEAR>0000</INV_YEAR><INV_ITEM>000</INV_ITEM><REF_KEY_1/><REF_KEY_2/><ZSTATUS/><ZFECHA_SHIVA>2017-01-01</ZFECHA_SHIVA><ZTIPO_CAMBIO_SH>16.14</ZTIPO_CAMBIO_SH><ZIMPORTE_ARS>-100.0</ZIMPORTE_ARS><ZIMPORTE_USD>-6.2</ZIMPORTE_USD><ZIMPORTE_TOTAL_MD>-600.0</ZIMPORTE_TOTAL_MD><ZIMPORTE_TOTAL_ML>-600.0</ZIMPORTE_TOTAL_ML><ZIMPORTE_TOTAL_ARS>-600.0</ZIMPORTE_TOTAL_ARS><ZIMPORTE_TOTAL_USD>-37.18</ZIMPORTE_TOTAL_USD></item>");
//            str.append("<item><COMP_CODE>3200</COMP_CODE><VENDOR>" +codigoVendedor+ "</VENDOR><ALLOC_NMBR/><FISC_YEAR>2016</FISC_YEAR><DOC_NO>3600008615</DOC_NO><ITEM_NUM>002</ITEM_NUM><PSTNG_DATE>2016-12-26</PSTNG_DATE><DOC_DATE>2016-12-26</DOC_DATE><ENTRY_DATE>2016-12-26</ENTRY_DATE><CURRENCY>ARS</CURRENCY><LOC_CURRCY>ARS</LOC_CURRCY><ZUKURS>16.14</ZUKURS><REF_DOC_NO>0001A98765433</REF_DOC_NO><DOC_TYPE>KB</DOC_TYPE><FIS_PERIOD>12</FIS_PERIOD><POST_KEY>31</POST_KEY><DB_CR_IND>H</DB_CR_IND><BUS_AREA>0001</BUS_AREA><LC_AMOUNT>-200.0</LC_AMOUNT><AMT_DOCCUR>-200.0</AMT_DOCCUR><ITEM_TEXT/><BLINE_DATE>2016-12-26</BLINE_DATE><PMNTTRMS>FE01</PMNTTRMS><PYMT_METH>T</PYMT_METH><PMNT_BLOCK>F</PMNT_BLOCK><PMNT_BLOCK_TEXTL>Pdte compensación</PMNT_BLOCK_TEXTL><INV_REF/><INV_YEAR>0000</INV_YEAR><INV_ITEM>000</INV_ITEM><REF_KEY_1/><REF_KEY_2/><ZSTATUS/><ZFECHA_SHIVA>2017-01-01</ZFECHA_SHIVA><ZTIPO_CAMBIO_SH>16.14</ZTIPO_CAMBIO_SH><ZIMPORTE_ARS>-200.0</ZIMPORTE_ARS><ZIMPORTE_USD>-12.39</ZIMPORTE_USD><ZIMPORTE_TOTAL_MD>-600.0</ZIMPORTE_TOTAL_MD><ZIMPORTE_TOTAL_ML>-600.0</ZIMPORTE_TOTAL_ML><ZIMPORTE_TOTAL_ARS>-600.0</ZIMPORTE_TOTAL_ARS><ZIMPORTE_TOTAL_USD>-37.18</ZIMPORTE_TOTAL_USD></item>");
//            str.append("<item><COMP_CODE>3200</COMP_CODE><VENDOR>" +codigoVendedor+ "</VENDOR><ALLOC_NMBR/><FISC_YEAR>2016</FISC_YEAR><DOC_NO>3600008615</DOC_NO><ITEM_NUM>003</ITEM_NUM><PSTNG_DATE>2016-12-26</PSTNG_DATE><DOC_DATE>2016-12-26</DOC_DATE><ENTRY_DATE>2016-12-26</ENTRY_DATE><CURRENCY>ARS</CURRENCY><LOC_CURRCY>ARS</LOC_CURRCY><ZUKURS>16.14</ZUKURS><REF_DOC_NO>0001A98765433</REF_DOC_NO><DOC_TYPE>KB</DOC_TYPE><FIS_PERIOD>12</FIS_PERIOD><POST_KEY>31</POST_KEY><DB_CR_IND>H</DB_CR_IND><BUS_AREA>0001</BUS_AREA><LC_AMOUNT>-300.0</LC_AMOUNT><AMT_DOCCUR>-300.0</AMT_DOCCUR><ITEM_TEXT/><BLINE_DATE>2016-12-26</BLINE_DATE><PMNTTRMS>FE01</PMNTTRMS><PYMT_METH>T</PYMT_METH><PMNT_BLOCK>F</PMNT_BLOCK><PMNT_BLOCK_TEXTL>Pdte compensación</PMNT_BLOCK_TEXTL><INV_REF/><INV_YEAR>0000</INV_YEAR><INV_ITEM>000</INV_ITEM><REF_KEY_1/><REF_KEY_2/><ZSTATUS/><ZFECHA_SHIVA>2017-01-01</ZFECHA_SHIVA><ZTIPO_CAMBIO_SH>16.14</ZTIPO_CAMBIO_SH><ZIMPORTE_ARS>-300.0</ZIMPORTE_ARS><ZIMPORTE_USD>-18.59</ZIMPORTE_USD><ZIMPORTE_TOTAL_MD>-600.0</ZIMPORTE_TOTAL_MD><ZIMPORTE_TOTAL_ML>-600.0</ZIMPORTE_TOTAL_ML><ZIMPORTE_TOTAL_ARS>-600.0</ZIMPORTE_TOTAL_ARS><ZIMPORTE_TOTAL_USD>-37.18</ZIMPORTE_TOTAL_USD></item>");
//            
//            str.append("<item><COMP_CODE>M650</COMP_CODE><VENDOR>" +codigoVendedor+ "</VENDOR><ALLOC_NMBR/><FISC_YEAR>2016</FISC_YEAR><DOC_NO>3700000590</DOC_NO><ITEM_NUM>001</ITEM_NUM><PSTNG_DATE>2016-12-27</PSTNG_DATE><DOC_DATE>2016-12-27</DOC_DATE><ENTRY_DATE>2016-12-26</ENTRY_DATE><CURRENCY>USD</CURRENCY><LOC_CURRCY>ARS</LOC_CURRCY><ZUKURS>16.14</ZUKURS><REF_DOC_NO>0001A98765435</REF_DOC_NO><DOC_TYPE>KC</DOC_TYPE><FIS_PERIOD>12</FIS_PERIOD><POST_KEY>31</POST_KEY><DB_CR_IND>H</DB_CR_IND><BUS_AREA>0001</BUS_AREA><LC_AMOUNT>-1614.0</LC_AMOUNT><AMT_DOCCUR>-100.0</AMT_DOCCUR><ITEM_TEXT/><BLINE_DATE>2016-12-26</BLINE_DATE><PMNTTRMS>FE01</PMNTTRMS><PYMT_METH>T</PYMT_METH><PMNT_BLOCK>F</PMNT_BLOCK><PMNT_BLOCK_TEXTL>Pdte compensación</PMNT_BLOCK_TEXTL><INV_REF>3600008615</INV_REF><INV_YEAR>2016</INV_YEAR><INV_ITEM>001</INV_ITEM><REF_KEY_1/><REF_KEY_2/><ZSTATUS/><ZFECHA_SHIVA>2017-01-01</ZFECHA_SHIVA><ZTIPO_CAMBIO_SH>16.14</ZTIPO_CAMBIO_SH><ZIMPORTE_ARS>-100.0</ZIMPORTE_ARS><ZIMPORTE_USD>-100.0</ZIMPORTE_USD><ZIMPORTE_TOTAL_MD>-100.0</ZIMPORTE_TOTAL_MD><ZIMPORTE_TOTAL_ML>-1614.0</ZIMPORTE_TOTAL_ML><ZIMPORTE_TOTAL_ARS>-26049.96</ZIMPORTE_TOTAL_ARS><ZIMPORTE_TOTAL_USD>-100.0</ZIMPORTE_TOTAL_USD></item>");
//            str.append("<item><COMP_CODE>M650</COMP_CODE><VENDOR>" +codigoVendedor+ "</VENDOR><ALLOC_NMBR/><FISC_YEAR>2016</FISC_YEAR><DOC_NO>3700000591</DOC_NO><ITEM_NUM>001</ITEM_NUM><PSTNG_DATE>2016-12-26</PSTNG_DATE><DOC_DATE>2016-12-26</DOC_DATE><ENTRY_DATE>2016-12-26</ENTRY_DATE><CURRENCY>USD</CURRENCY><LOC_CURRCY>ARS</LOC_CURRCY><ZUKURS>16.14</ZUKURS><REF_DOC_NO>0001A98765436</REF_DOC_NO><DOC_TYPE>KC</DOC_TYPE><FIS_PERIOD>12</FIS_PERIOD><POST_KEY>31</POST_KEY><DB_CR_IND>H</DB_CR_IND><BUS_AREA>0001</BUS_AREA><LC_AMOUNT>-3228.0</LC_AMOUNT><AMT_DOCCUR>-200.0</AMT_DOCCUR><ITEM_TEXT/><BLINE_DATE>2016-12-26</BLINE_DATE><PMNTTRMS>FE01</PMNTTRMS><PYMT_METH>T</PYMT_METH><PMNT_BLOCK>F</PMNT_BLOCK><PMNT_BLOCK_TEXTL>Pdte compensación</PMNT_BLOCK_TEXTL><INV_REF>3600008615</INV_REF><INV_YEAR>2016</INV_YEAR><INV_ITEM>001</INV_ITEM><REF_KEY_1/><REF_KEY_2/><ZSTATUS/><ZFECHA_SHIVA>2017-01-01</ZFECHA_SHIVA><ZTIPO_CAMBIO_SH>16.14</ZTIPO_CAMBIO_SH><ZIMPORTE_ARS>-500.0</ZIMPORTE_ARS><ZIMPORTE_USD>-200.0</ZIMPORTE_USD><ZIMPORTE_TOTAL_MD>-200.0</ZIMPORTE_TOTAL_MD><ZIMPORTE_TOTAL_ML>-3228.0</ZIMPORTE_TOTAL_ML><ZIMPORTE_TOTAL_ARS>-52099.92</ZIMPORTE_TOTAL_ARS><ZIMPORTE_TOTAL_USD>-200.0</ZIMPORTE_TOTAL_USD></item>");
//           
//            
//            // Dolares
//            str.append("<item><COMP_CODE>M650</COMP_CODE><VENDOR>" +codigoVendedor+ "</VENDOR><ALLOC_NMBR>Test fer</ALLOC_NMBR><FISC_YEAR>2010</FISC_YEAR><DOC_NO>3500000878</DOC_NO><ITEM_NUM>001</ITEM_NUM><PSTNG_DATE>2010-10-20</PSTNG_DATE><DOC_DATE>2010-10-15</DOC_DATE><ENTRY_DATE>2010-10-20</ENTRY_DATE><CURRENCY>USD</CURRENCY><LOC_CURRCY>ARS</LOC_CURRCY><ZUKURS>16.13997</ZUKURS><REF_DOC_NO>4441A12345655</REF_DOC_NO><DOC_TYPE>KA</DOC_TYPE><FIS_PERIOD>10</FIS_PERIOD><POST_KEY>21</POST_KEY><DB_CR_IND>S</DB_CR_IND><BUS_AREA>0001</BUS_AREA><LC_AMOUNT>320.0</LC_AMOUNT><AMT_DOCCUR>101.81</AMT_DOCCUR><ITEM_TEXT></ITEM_TEXT><BLINE_DATE>2010-10-20</BLINE_DATE><PMNTTRMS>FE01</PMNTTRMS><PYMT_METH>C</PYMT_METH><PMNT_BLOCK/><PMNT_BLOCK_TEXTL>Autorizado el pago</PMNT_BLOCK_TEXTL><INV_REF>3600003991</INV_REF><INV_YEAR>2010</INV_YEAR><INV_ITEM>001</INV_ITEM><REF_KEY_1/><REF_KEY_2/><ZSTATUS/><ZFECHA_SHIVA>2017-01-01</ZFECHA_SHIVA><ZTIPO_CAMBIO_SH>16.14</ZTIPO_CAMBIO_SH><ZIMPORTE_ARS>1643.21</ZIMPORTE_ARS><ZIMPORTE_USD>101.81</ZIMPORTE_USD><ZIMPORTE_TOTAL_MD>101.81</ZIMPORTE_TOTAL_MD><ZIMPORTE_TOTAL_ML>320.0</ZIMPORTE_TOTAL_ML><ZIMPORTE_TOTAL_ARS>19.83</ZIMPORTE_TOTAL_ARS><ZIMPORTE_TOTAL_USD>101.81</ZIMPORTE_TOTAL_USD></item>");
//            str.append("<item><COMP_CODE>M650</COMP_CODE><VENDOR>" +codigoVendedor+ "</VENDOR><ALLOC_NMBR>Test fer</ALLOC_NMBR><FISC_YEAR>2010</FISC_YEAR><DOC_NO>3600003991</DOC_NO><ITEM_NUM>001</ITEM_NUM><PSTNG_DATE>2010-10-20</PSTNG_DATE><DOC_DATE>2010-10-15</DOC_DATE><ENTRY_DATE>2010-10-20</ENTRY_DATE><CURRENCY>USD</CURRENCY><LOC_CURRCY>ARS</LOC_CURRCY><ZUKURS>16.13997</ZUKURS><REF_DOC_NO>4441A12345655</REF_DOC_NO><DOC_TYPE>KB</DOC_TYPE><FIS_PERIOD>10</FIS_PERIOD><POST_KEY>31</POST_KEY><DB_CR_IND>H</DB_CR_IND><BUS_AREA>0001</BUS_AREA><LC_AMOUNT>-320.0</LC_AMOUNT><AMT_DOCCUR>-101.81</AMT_DOCCUR><ITEM_TEXT/><BLINE_DATE>2010-10-20</BLINE_DATE><PMNTTRMS>FE01</PMNTTRMS><PYMT_METH>C</PYMT_METH><PMNT_BLOCK/><PMNT_BLOCK_TEXTL>Autorizado el pago</PMNT_BLOCK_TEXTL><INV_REF/><INV_YEAR>0000</INV_YEAR><INV_ITEM>000</INV_ITEM><REF_KEY_1/><REF_KEY_2/><ZSTATUS/><ZFECHA_SHIVA>2017-01-01</ZFECHA_SHIVA><ZTIPO_CAMBIO_SH>16.14</ZTIPO_CAMBIO_SH><ZIMPORTE_ARS>-300.21</ZIMPORTE_ARS><ZIMPORTE_USD>-101.81</ZIMPORTE_USD><ZIMPORTE_TOTAL_MD>-101.81</ZIMPORTE_TOTAL_MD><ZIMPORTE_TOTAL_ML>-320.0</ZIMPORTE_TOTAL_ML><ZIMPORTE_TOTAL_ARS>-19.83</ZIMPORTE_TOTAL_ARS><ZIMPORTE_TOTAL_USD>-101.81</ZIMPORTE_TOTAL_USD></item>");
////            
//            str.append("<item><COMP_CODE>M650</COMP_CODE><VENDOR>" +codigoVendedor+ "</VENDOR><ALLOC_NMBR/><FISC_YEAR>2010</FISC_YEAR><DOC_NO>3500000877</DOC_NO><ITEM_NUM>001</ITEM_NUM><PSTNG_DATE>2010-10-15</PSTNG_DATE><DOC_DATE>2010-09-30</DOC_DATE><ENTRY_DATE>2010-10-18</ENTRY_DATE><CURRENCY>ARS</CURRENCY><LOC_CURRCY>ARS</LOC_CURRCY><ZUKURS>16.17647</ZUKURS><REF_DOC_NO>6669A25254145</REF_DOC_NO><DOC_TYPE>KA</DOC_TYPE><FIS_PERIOD>10</FIS_PERIOD><POST_KEY>21</POST_KEY><DB_CR_IND>S</DB_CR_IND><BUS_AREA>0001</BUS_AREA><LC_AMOUNT>5.5</LC_AMOUNT><AMT_DOCCUR>5.5</AMT_DOCCUR><ITEM_TEXT>fd</ITEM_TEXT><BLINE_DATE>2010-10-15</BLINE_DATE><PMNTTRMS>FE01</PMNTTRMS><PYMT_METH>T</PYMT_METH><PMNT_BLOCK>K</PMNT_BLOCK><PMNT_BLOCK_TEXTL>Aprobacin CAP</PMNT_BLOCK_TEXTL><INV_REF>3600003947</INV_REF><INV_YEAR>2010</INV_YEAR><INV_ITEM>001</INV_ITEM><REF_KEY_1/><REF_KEY_2/><ZSTATUS/><ZFECHA_SHIVA>2017-01-01</ZFECHA_SHIVA><ZTIPO_CAMBIO_SH>16.14</ZTIPO_CAMBIO_SH><ZIMPORTE_ARS>5.5</ZIMPORTE_ARS><ZIMPORTE_USD>0.34</ZIMPORTE_USD><ZIMPORTE_TOTAL_MD>5.5</ZIMPORTE_TOTAL_MD><ZIMPORTE_TOTAL_ML>5.5</ZIMPORTE_TOTAL_ML><ZIMPORTE_TOTAL_ARS>5.5</ZIMPORTE_TOTAL_ARS><ZIMPORTE_TOTAL_USD>0.34</ZIMPORTE_TOTAL_USD></item><item><COMP_CODE>M650</COMP_CODE><VENDOR>" +codigoVendedor+ "</VENDOR><ALLOC_NMBR/><FISC_YEAR>2010</FISC_YEAR><DOC_NO>3500000880</DOC_NO><ITEM_NUM>001</ITEM_NUM><PSTNG_DATE>2010-10-21</PSTNG_DATE><DOC_DATE>2010-10-10</DOC_DATE><ENTRY_DATE>2010-10-21</ENTRY_DATE><CURRENCY>USD</CURRENCY><LOC_CURRCY>ARS</LOC_CURRCY><ZUKURS>16.14</ZUKURS><REF_DOC_NO>0001A98741333</REF_DOC_NO><DOC_TYPE>KA</DOC_TYPE><FIS_PERIOD>10</FIS_PERIOD><POST_KEY>21</POST_KEY><DB_CR_IND>S</DB_CR_IND><BUS_AREA>0001</BUS_AREA><LC_AMOUNT>309.59</LC_AMOUNT><AMT_DOCCUR>98.5</AMT_DOCCUR><ITEM_TEXT>MR8M</ITEM_TEXT><BLINE_DATE>2010-10-20</BLINE_DATE><PMNTTRMS>FE01</PMNTTRMS><PYMT_METH>C</PYMT_METH><PMNT_BLOCK/><PMNT_BLOCK_TEXTL>Autorizado el pago</PMNT_BLOCK_TEXTL><INV_REF>3600003994</INV_REF><INV_YEAR>2010</INV_YEAR><INV_ITEM>001</INV_ITEM><REF_KEY_1/><REF_KEY_2/><ZSTATUS/><ZFECHA_SHIVA>2017-01-01</ZFECHA_SHIVA><ZTIPO_CAMBIO_SH>16.14</ZTIPO_CAMBIO_SH><ZIMPORTE_ARS>1589.79</ZIMPORTE_ARS><ZIMPORTE_USD>98.5</ZIMPORTE_USD><ZIMPORTE_TOTAL_MD>98.5</ZIMPORTE_TOTAL_MD><ZIMPORTE_TOTAL_ML>309.59</ZIMPORTE_TOTAL_ML><ZIMPORTE_TOTAL_ARS>19.18</ZIMPORTE_TOTAL_ARS><ZIMPORTE_TOTAL_USD>98.5</ZIMPORTE_TOTAL_USD></item>");
//            str.append("<item><COMP_CODE>M650</COMP_CODE><VENDOR>" +codigoVendedor+ "</VENDOR><ALLOC_NMBR/><FISC_YEAR>2010</FISC_YEAR><DOC_NO>3600003994</DOC_NO><ITEM_NUM>001</ITEM_NUM><PSTNG_DATE>2010-10-20</PSTNG_DATE><DOC_DATE>2010-10-10</DOC_DATE><ENTRY_DATE>2010-10-21</ENTRY_DATE><CURRENCY>USD</CURRENCY><LOC_CURRCY>ARS</LOC_CURRCY><ZUKURS>16.14</ZUKURS><REF_DOC_NO>0001A98741333</REF_DOC_NO><DOC_TYPE>KB</DOC_TYPE><FIS_PERIOD>10</FIS_PERIOD><POST_KEY>31</POST_KEY><DB_CR_IND>H</DB_CR_IND><BUS_AREA>0001</BUS_AREA><LC_AMOUNT>-309.59</LC_AMOUNT><AMT_DOCCUR>-98.5</AMT_DOCCUR><ITEM_TEXT/><BLINE_DATE>2010-10-20</BLINE_DATE><PMNTTRMS>FE01</PMNTTRMS><PYMT_METH>C</PYMT_METH><PMNT_BLOCK/><PMNT_BLOCK_TEXTL>Autorizado el pago</PMNT_BLOCK_TEXTL><INV_REF/><INV_YEAR>0000</INV_YEAR><INV_ITEM>000</INV_ITEM><REF_KEY_1/><REF_KEY_2/><ZSTATUS/><ZFECHA_SHIVA>2017-01-01</ZFECHA_SHIVA><ZTIPO_CAMBIO_SH>16.14</ZTIPO_CAMBIO_SH><ZIMPORTE_ARS>-1589.79</ZIMPORTE_ARS><ZIMPORTE_USD>-98.5</ZIMPORTE_USD><ZIMPORTE_TOTAL_MD>-98.5</ZIMPORTE_TOTAL_MD><ZIMPORTE_TOTAL_ML>-309.59</ZIMPORTE_TOTAL_ML><ZIMPORTE_TOTAL_ARS>-19.18</ZIMPORTE_TOTAL_ARS><ZIMPORTE_TOTAL_USD>-98.5</ZIMPORTE_TOTAL_USD></item>");
////            
////            
//            str.append("<item><COMP_CODE>M650</COMP_CODE><VENDOR>" +codigoVendedor+ "</VENDOR><ALLOC_NMBR/><FISC_YEAR>2014</FISC_YEAR><DOC_NO>3700000578</DOC_NO><ITEM_NUM>001</ITEM_NUM><PSTNG_DATE>2014-05-25</PSTNG_DATE><DOC_DATE>2014-05-25</DOC_DATE><ENTRY_DATE>2014-05-26</ENTRY_DATE><CURRENCY>ARS</CURRENCY><LOC_CURRCY>ARS</LOC_CURRCY><ZUKURS>16.1406</ZUKURS><REF_DOC_NO>0001A33636278</REF_DOC_NO><DOC_TYPE>KC</DOC_TYPE><FIS_PERIOD>05</FIS_PERIOD><POST_KEY>21</POST_KEY><DB_CR_IND>S</DB_CR_IND><BUS_AREA>0001</BUS_AREA><LC_AMOUNT>900.0</LC_AMOUNT><AMT_DOCCUR>900.0</AMT_DOCCUR><ITEM_TEXT/><BLINE_DATE>2014-05-25</BLINE_DATE><PMNTTRMS>FE01</PMNTTRMS><PYMT_METH>I</PYMT_METH><PMNT_BLOCK/><PMNT_BLOCK_TEXTL>Autorizado el pago</PMNT_BLOCK_TEXTL><INV_REF>3600007128</INV_REF><INV_YEAR>2014</INV_YEAR><INV_ITEM>001</INV_ITEM><REF_KEY_1/><REF_KEY_2/><ZSTATUS/><ZFECHA_SHIVA>2017-01-01</ZFECHA_SHIVA><ZTIPO_CAMBIO_SH>16.14</ZTIPO_CAMBIO_SH><ZIMPORTE_ARS>900.0</ZIMPORTE_ARS><ZIMPORTE_USD>55.76</ZIMPORTE_USD><ZIMPORTE_TOTAL_MD>900.0</ZIMPORTE_TOTAL_MD><ZIMPORTE_TOTAL_ML>900.0</ZIMPORTE_TOTAL_ML><ZIMPORTE_TOTAL_ARS>900.0</ZIMPORTE_TOTAL_ARS><ZIMPORTE_TOTAL_USD>55.76</ZIMPORTE_TOTAL_USD></item>");
//            str.append("<item><COMP_CODE>M650</COMP_CODE><VENDOR>" +codigoVendedor+ "</VENDOR><ALLOC_NMBR>BOOM!</ALLOC_NMBR><FISC_YEAR>2014</FISC_YEAR><DOC_NO>3700000576</DOC_NO><ITEM_NUM>002</ITEM_NUM><PSTNG_DATE>2014-05-25</PSTNG_DATE><DOC_DATE>2014-05-25</DOC_DATE><ENTRY_DATE>2014-05-26</ENTRY_DATE><CURRENCY>ARS</CURRENCY><LOC_CURRCY>ARS</LOC_CURRCY><ZUKURS>16.13944</ZUKURS><REF_DOC_NO>0001A33636252</REF_DOC_NO><DOC_TYPE>KC</DOC_TYPE><FIS_PERIOD>05</FIS_PERIOD><POST_KEY>21</POST_KEY><DB_CR_IND>S</DB_CR_IND><BUS_AREA>0001</BUS_AREA><LC_AMOUNT>250.0</LC_AMOUNT><AMT_DOCCUR>250.0</AMT_DOCCUR><ITEM_TEXT/><BLINE_DATE>2014-05-25</BLINE_DATE><PMNTTRMS>FE01</PMNTTRMS><PYMT_METH>I</PYMT_METH><PMNT_BLOCK/><PMNT_BLOCK_TEXTL>Autorizado el pago</PMNT_BLOCK_TEXTL><INV_REF>3600007128</INV_REF><INV_YEAR>2014</INV_YEAR><INV_ITEM>001</INV_ITEM><REF_KEY_1/><REF_KEY_2/><ZSTATUS/><ZFECHA_SHIVA>2017-01-01</ZFECHA_SHIVA><ZTIPO_CAMBIO_SH>16.14</ZTIPO_CAMBIO_SH><ZIMPORTE_ARS>250.0</ZIMPORTE_ARS><ZIMPORTE_USD>15.49</ZIMPORTE_USD><ZIMPORTE_TOTAL_MD>250.0</ZIMPORTE_TOTAL_MD><ZIMPORTE_TOTAL_ML>250.0</ZIMPORTE_TOTAL_ML><ZIMPORTE_TOTAL_ARS>250.0</ZIMPORTE_TOTAL_ARS><ZIMPORTE_TOTAL_USD>15.49</ZIMPORTE_TOTAL_USD></item>");
//            str.append("<item><COMP_CODE>M650</COMP_CODE><VENDOR>" +codigoVendedor+ "</VENDOR><ALLOC_NMBR>BOOM!</ALLOC_NMBR><FISC_YEAR>2014</FISC_YEAR><DOC_NO>3700000577</DOC_NO><ITEM_NUM>001</ITEM_NUM><PSTNG_DATE>2014-05-25</PSTNG_DATE><DOC_DATE>2014-05-25</DOC_DATE><ENTRY_DATE>2014-05-26</ENTRY_DATE><CURRENCY>ARS</CURRENCY><LOC_CURRCY>ARS</LOC_CURRCY><ZUKURS>16.13944</ZUKURS><REF_DOC_NO>0001A33636256</REF_DOC_NO><DOC_TYPE>KC</DOC_TYPE><FIS_PERIOD>05</FIS_PERIOD><POST_KEY>21</POST_KEY><DB_CR_IND>S</DB_CR_IND><BUS_AREA>0001</BUS_AREA><LC_AMOUNT>1000.0</LC_AMOUNT><AMT_DOCCUR>1000.0</AMT_DOCCUR><ITEM_TEXT/><BLINE_DATE>2014-05-25</BLINE_DATE><PMNTTRMS>FE01</PMNTTRMS><PYMT_METH>I</PYMT_METH><PMNT_BLOCK/><PMNT_BLOCK_TEXTL>Autorizado el pago</PMNT_BLOCK_TEXTL><INV_REF>3600007128</INV_REF><INV_YEAR>2014</INV_YEAR><INV_ITEM>001</INV_ITEM><REF_KEY_1/><REF_KEY_2/><ZSTATUS/><ZFECHA_SHIVA>2017-01-01</ZFECHA_SHIVA><ZTIPO_CAMBIO_SH>16.14</ZTIPO_CAMBIO_SH><ZIMPORTE_ARS>1000.0</ZIMPORTE_ARS><ZIMPORTE_USD>61.96</ZIMPORTE_USD><ZIMPORTE_TOTAL_MD>1000.0</ZIMPORTE_TOTAL_MD><ZIMPORTE_TOTAL_ML>1000.0</ZIMPORTE_TOTAL_ML><ZIMPORTE_TOTAL_ARS>1000.0</ZIMPORTE_TOTAL_ARS><ZIMPORTE_TOTAL_USD>61.96</ZIMPORTE_TOTAL_USD></item>");
           
            
         
            str.append(this.generarCap("M650", "0001005371", "3600008611", "KB", "F", "000", "", "0001A98765401"));
            str.append(this.generarCap("M650", "0001005371", "3600008612", "KB", "F", "000", "", "0001A98765402"));
            str.append(this.generarCap("M650", "0001005371", "3600008613", "KB", "F", "000", "", "0001A98765403"));
            str.append(this.generarCap("M650", "0001005371", "3600008614", "KB", "F", "000", "", "0001A98765404"));
            str.append(this.generarCap("M650", "0001005371", "3600008615", "KB", "F", "000", "", "0001A98765405"));
            str.append(this.generarCap("M650", "0001005371", "3600008616", "KB", "F", "000", "", "0001A98765406"));
            str.append(this.generarCap("M650", "0001005371", "3600008617", "KB", "F", "000", "", "0001A98765407"));
            str.append(this.generarCap("M650", "0001005371", "3600008618", "KB", "F", "000", "", "0001A98765408"));
            str.append(this.generarCap("M650", "0001005371", "3600008620", "KB", "F", "000", "", "0001A98765409"));
            str.append(this.generarCap("M650", "0001005371", "3600008621", "KB", "F", "000", "", "0001A98765410"));
            str.append(this.generarCap("M650", "0001005371", "3600008622", "KB", "F", "000", "", "0001A98765411"));
            str.append(this.generarCap("M650", "0001005371", "3600008624", "KB", "F", "000", "", "0001A98765412"));
            str.append(this.generarCap("M650", "0001005371", "3600008625", "KB", "F", "000", "", "0001A98765413"));
            str.append(this.generarCap("M650", "0001005371", "3600008626", "KB", "F", "000", "", "0001A98765414"));
            str.append(this.generarCap("M650", "0001005371", "3600008627", "KB", "F", "000", "", "0001A98765415"));
            str.append(this.generarCap("M650", "0001005371", "3600008629", "KB", "F", "000", "", "0001A98765416"));
            
            str.append("</T_LINEITEMS><T_RETURN /></n0:ZFI_SHIVA_GETOPENITEMSResponse></soap-env:Body></soap-env:Envelope>");
        
            mensaje = str.toString();
            Traza.advertencia(getClass(), mensaje);
			baos = new ByteArrayOutputStream();
    	    messageDummy.writeTo(baos);

    	    MimeHeaders headers = new MimeHeaders();
			MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
			byte[] bytes = mensaje.getBytes();
			InputStream is = new ByteArrayInputStream(bytes);
			
			SOAPMessage msg = msgFactory.createMessage(headers, is); 
			smc.setMessage(msg);
			
			messageDummy = smc.getMessage();
		} catch (SOAPException | IOException | NegocioExcepcion e) {
			Traza.error(getClass(), e.getMessage(), e);
		}   
    }
    
    private String generarCap(String comp_code, String VENDOR, String DOC_NO, String DOC_TYPE, String PMNT_BLOCK, String INV_ITEM, String INV_REF, String REF_DOC_NO) {
    	StringBuffer str = new StringBuffer();
    	Map<String, String> valuesMap = new HashMap<String, String>();
    	
		valuesMap.put("COMP_CODE", comp_code);
		valuesMap.put("VENDOR", VENDOR);
		valuesMap.put("DOC_NO", DOC_NO);
		valuesMap.put("ITEM_NUM", "001");
		valuesMap.put("DOC_TYPE", DOC_TYPE);
		valuesMap.put("PMNT_BLOCK", PMNT_BLOCK);
		valuesMap.put("INV_ITEM", INV_ITEM);
		valuesMap.put("INV_REF", INV_REF);
		valuesMap.put("REF_DOC_NO", REF_DOC_NO);

		
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		

		str.append("<item><COMP_CODE>${COMP_CODE}</COMP_CODE><VENDOR>${VENDOR}</VENDOR><ALLOC_NMBR/>");
		str.append("<FISC_YEAR>2017</FISC_YEAR><DOC_NO>${DOC_NO}</DOC_NO><ITEM_NUM>${ITEM_NUM}</ITEM_NUM>");
		str.append("<PSTNG_DATE>2017-01-18</PSTNG_DATE><DOC_DATE>2016-01-25</DOC_DATE><ENTRY_DATE>2017-01-18</ENTRY_DATE>");
		str.append("<CURRENCY>ARS</CURRENCY><LOC_CURRCY>ARS</LOC_CURRCY><ZUKURS>16.16129</ZUKURS><REF_DOC_NO>${REF_DOC_NO}</REF_DOC_NO>");
		str.append("<DOC_TYPE>${DOC_TYPE}</DOC_TYPE><FIS_PERIOD>01</FIS_PERIOD><POST_KEY>21</POST_KEY><DB_CR_IND>S</DB_CR_IND><BUS_AREA>0001</BUS_AREA>");
		str.append("<LC_AMOUNT>-5.01</LC_AMOUNT><AMT_DOCCUR>-5.01</AMT_DOCCUR><ITEM_TEXT/><BLINE_DATE>2017-12-31</BLINE_DATE><PMNTTRMS>FF04</PMNTTRMS><PYMT_METH/>");
		str.append("<PMNT_BLOCK>${PMNT_BLOCK}</PMNT_BLOCK><PMNT_BLOCK_TEXTL>AprobaciÃ³n CAP</PMNT_BLOCK_TEXTL>");
		str.append("<INV_REF>${INV_REF}</INV_REF><INV_YEAR>2017</INV_YEAR><INV_ITEM>${INV_ITEM}</INV_ITEM>");
		str.append("<REF_KEY_1/><REF_KEY_2>20180130</REF_KEY_2><ZSTATUS/><ZFECHA_SHIVA>2018-05-12</ZFECHA_SHIVA><ZTIPO_CAMBIO_SH>20.27</ZTIPO_CAMBIO_SH><ZIMPORTE_ARS>-5.01</ZIMPORTE_ARS>");
		str.append("<ZIMPORTE_USD>0.25</ZIMPORTE_USD>");
		str.append("<ZIMPORTE_TOTAL_MD>-5.01</ZIMPORTE_TOTAL_MD>");
		str.append("<ZIMPORTE_TOTAL_ML>-5.01</ZIMPORTE_TOTAL_ML>");
		str.append("<ZIMPORTE_TOTAL_ARS>-5.01</ZIMPORTE_TOTAL_ARS>");
		str.append("<ZIMPORTE_TOTAL_USD>0.25</ZIMPORTE_TOTAL_USD>");
		str.append("<ZIMPORTE_USD_EMISION>0.31</ZIMPORTE_USD_EMISION>");
		str.append("</item>");

		return sub.replace(str.toString());
    }
}
