package ar.com.telecom.shiva.base.ws.cliente;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Formatter;

import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceFormatoMensajeExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapConsultaPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapConsultaPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.Partida;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.ResultadoInvocacion;
import ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.UR00SSELOPT;
import ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.URTSELOPT;
import ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.ZFISHIVAGETOPENITEMSWS;
import ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.ZFISSH004;
import ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.ZFITTSH004;
import ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.ZSRETURN;
import ar.com.telecom.shiva.presentacion.wsdl.sap.consultaPartidas.ZTTRETURN;


/**
 * @author u586743
 *
 */
public class SapConsultaPartidasWS extends SapConsultasWS {
	
	private final String CABECERA_MENSAJE_SERVICIO_WS = "WS SapConsultaPartidas";
	
	@Autowired
	ZFISHIVAGETOPENITEMSWS webServiceSapConsultaPartidasPortType;

	@SuppressWarnings("resource")
	public SalidaSapConsultaPartidasWS consultarPartidas(EntradaSapConsultaPartidasWS entrada) throws NegocioExcepcion, ParseException {
		
		SalidaSapConsultaPartidasWS salida = new SalidaSapConsultaPartidasWS();
		
		try {
			
	    	// Mapeo la entrada: codigo de sociedad
			String contenedorEntradaIdSociedad = SAP_WS_CODIGO_SOCIEDAD_TELECOM_ARGENTINA_S4;
			
			// Mapeo la entrada: fecha de tipo de cambio
			DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			String contentedorEntradaFechaTipoCambio = formatoFecha.format(entrada.getFechaTipoCambio());
			
			// Mapeo la entrada: lista de proveedores
			URTSELOPT contenedorEntradaListaProveedores = null;
			
			if (entrada.getListaIdProveedores().size() > 0) {
				contenedorEntradaListaProveedores = new URTSELOPT();
				
				for (String idProveedor : entrada.getListaIdProveedores()) {
					UR00SSELOPT itemEntradaProveedor = new UR00SSELOPT();
					itemEntradaProveedor.setSIGN(SAP_WS_CAMPO_SIGN_VALOR_INCLUIR);
					itemEntradaProveedor.setOPTION(SAP_WS_CAMPO_OPTION_VALOR_IGUAL);
					itemEntradaProveedor.setLOW((new Formatter().format("%010d", new Long(idProveedor))).toString());
					
					contenedorEntradaListaProveedores.getItem().add(itemEntradaProveedor);
				}
			}
			
			// Mapeo la entrada: clase de documento
			URTSELOPT contenedorEntradaClaseDocumento = null;
			
			if (!Validaciones.isObjectNull(entrada.getTipoDocumentoCap())) {
				contenedorEntradaClaseDocumento = new URTSELOPT();
				
				UR00SSELOPT itemEntradaClaseDocumento = new UR00SSELOPT();
				itemEntradaClaseDocumento.setSIGN(SAP_WS_CAMPO_SIGN_VALOR_INCLUIR);
				itemEntradaClaseDocumento.setOPTION(SAP_WS_CAMPO_OPTION_VALOR_IGUAL);
				itemEntradaClaseDocumento.setLOW(entrada.getTipoDocumentoCap().name());
				
				contenedorEntradaClaseDocumento.getItem().add(itemEntradaClaseDocumento);
			} /*else {
				
				contenedorEntradaClaseDocumento = new URTSELOPT();
				for (TipoDocumentoCapEnum tipoDocumentoCap : TipoDocumentoCapEnum.getEnums()) {
					
					UR00SSELOPT itemEntradaClaseDocumento = new UR00SSELOPT();
					itemEntradaClaseDocumento.setSIGN(SAP_WS_CAMPO_SIGN_VALOR_INCLUIR);
					itemEntradaClaseDocumento.setOPTION(SAP_WS_CAMPO_OPTION_VALOR_IGUAL);
					itemEntradaClaseDocumento.setLOW(tipoDocumentoCap.name());
					
					contenedorEntradaClaseDocumento.getItem().add(itemEntradaClaseDocumento);
				}
			}
			*/

			// Mapeo la entrada: moneda
			URTSELOPT contenedorEntradaMoneda = null;

			if (!Validaciones.isObjectNull(entrada.getMoneda())) {
				contenedorEntradaMoneda = new URTSELOPT();
				
				UR00SSELOPT itemEntradaMoneda = new UR00SSELOPT();
				itemEntradaMoneda.setSIGN(SAP_WS_CAMPO_SIGN_VALOR_INCLUIR);
				itemEntradaMoneda.setOPTION(SAP_WS_CAMPO_OPTION_VALOR_IGUAL);
				itemEntradaMoneda.setLOW(entrada.getMoneda().getSigla());  
				
				contenedorEntradaMoneda.getItem().add(itemEntradaMoneda);
			}
			
			// Mapeo la entrada: numero de documento Sap
			URTSELOPT contenedorEntradaNumeroDocumentoSap = null;

			if (!Validaciones.isObjectNull(entrada.getIdDocumentoCap())) {
				contenedorEntradaNumeroDocumentoSap = new URTSELOPT();
				
				UR00SSELOPT itemEntradaNumeroDocumentoSap = new UR00SSELOPT();
				itemEntradaNumeroDocumentoSap.setSIGN(SAP_WS_CAMPO_SIGN_VALOR_INCLUIR);
				itemEntradaNumeroDocumentoSap.setOPTION(SAP_WS_CAMPO_OPTION_VALOR_IGUAL);
				itemEntradaNumeroDocumentoSap.setLOW((new Formatter().format("%010d", new Long(entrada.getIdDocumentoCap()))).toString());  
				
				contenedorEntradaNumeroDocumentoSap.getItem().add(itemEntradaNumeroDocumentoSap);
			}

			// Mapeo la entrada: numero de documento legal 
			URTSELOPT contenedorEntradaNumeroLegal = null;

			if (!Validaciones.isObjectNull(entrada.getNumeroDocumentoLegal())) {
				contenedorEntradaNumeroLegal = new URTSELOPT();
				
				UR00SSELOPT itemEntradaNumeroDocumentoLegal = new UR00SSELOPT();
				itemEntradaNumeroDocumentoLegal.setSIGN(SAP_WS_CAMPO_SIGN_VALOR_INCLUIR);
				itemEntradaNumeroDocumentoLegal.setOPTION(SAP_WS_CAMPO_OPTION_VALOR_IGUAL);
				itemEntradaNumeroDocumentoLegal.setLOW(entrada.getNumeroDocumentoLegal());  
				
				contenedorEntradaNumeroLegal.getItem().add(itemEntradaNumeroDocumentoLegal);
			}

			// Mapeo la entrada: rango de fechas de emision
			URTSELOPT contenedorEntradaRangoFechaEmision = null;
			
			if (!Validaciones.isObjectNull(entrada.getFechaEmisionDesde()) || !Validaciones.isObjectNull(entrada.getFechaEmisionHasta())) {
			
				contenedorEntradaRangoFechaEmision = new URTSELOPT();
				
				UR00SSELOPT itemEntradaRangoFechaEmision = new UR00SSELOPT();
				itemEntradaRangoFechaEmision.setSIGN(SAP_WS_CAMPO_SIGN_VALOR_INCLUIR);
				
				if (!Validaciones.isObjectNull(entrada.getFechaEmisionDesde()) && !Validaciones.isObjectNull(entrada.getFechaEmisionHasta())) {
					// TODO: ver el formato de entrada esperado para la fecha, cuando SAP responda los mails
					itemEntradaRangoFechaEmision.setOPTION(SAP_WS_CAMPO_OPTION_VALOR_RANGO);
					itemEntradaRangoFechaEmision.setLOW(entrada.getFechaEmisionDesde().toString());
					itemEntradaRangoFechaEmision.setHIGH(entrada.getFechaEmisionHasta().toString());
					
				} else if (!Validaciones.isObjectNull(entrada.getFechaEmisionDesde())) {
					// TODO: ver el formato de entrada esperado para la fecha, cuando SAP responda los mails
					itemEntradaRangoFechaEmision.setOPTION(SAP_WS_CAMPO_OPTION_VALOR_MAYOR_O_IGUAL);
					itemEntradaRangoFechaEmision.setLOW(entrada.getFechaEmisionDesde().toString());
					
				} else if (!Validaciones.isObjectNull(entrada.getFechaEmisionHasta())) {
					// TODO: ver el formato de entrada esperado para la fecha, cuando SAP responda los mails
					itemEntradaRangoFechaEmision.setOPTION(SAP_WS_CAMPO_OPTION_VALOR_MENOR_O_IGUAL);
					itemEntradaRangoFechaEmision.setHIGH(entrada.getFechaEmisionHasta().toString());
				}
			}
			
			// Mapeo la entrada: bloqueo de pago
			URTSELOPT contenedorEntradaBloqueoPago = null;

			if (!Validaciones.isObjectNull(entrada.getBloqueoPago())) {
				contenedorEntradaBloqueoPago = new URTSELOPT();
				
				UR00SSELOPT itemEntradaBloqueoPago = new UR00SSELOPT();
				itemEntradaBloqueoPago.setSIGN(SAP_WS_CAMPO_SIGN_VALOR_INCLUIR);
				itemEntradaBloqueoPago.setOPTION(SAP_WS_CAMPO_OPTION_VALOR_IGUAL);
				itemEntradaBloqueoPago.setLOW(entrada.getBloqueoPago());  
				
				contenedorEntradaBloqueoPago.getItem().add(itemEntradaBloqueoPago);
			}

			
	 		// Mapeo la salida: info proveedor, lista de cuits no encontrados, y resultado de la invocación
	 		Holder<ZSRETURN>   contenedorSalidaResultadoProcesamiento = new Holder<ZSRETURN>();
	 		Holder<ZFITTSH004> contenedorSalidaPartidasAbiertas = new Holder<ZFITTSH004>();
	 		Holder<ZTTRETURN>  contenedorSalidaErroresEncontrados = new Holder<ZTTRETURN>();

			
			webServiceSapConsultaPartidasPortType.zfiSHIVAGETOPENITEMS(contenedorEntradaIdSociedad,
																	   contentedorEntradaFechaTipoCambio, 
																	   contenedorEntradaMoneda, 
																	   contenedorEntradaRangoFechaEmision, 
																	   contenedorEntradaNumeroDocumentoSap, 
																	   contenedorEntradaClaseDocumento, 
																	   contenedorEntradaBloqueoPago, 
																	   contenedorEntradaNumeroLegal,
																	   contenedorEntradaListaProveedores, 
																	   contenedorSalidaResultadoProcesamiento, 
																	   contenedorSalidaPartidasAbiertas, 
																	   contenedorSalidaErroresEncontrados);

			if (!Validaciones.isObjectNull(contenedorSalidaPartidasAbiertas) && 
					!Validaciones.isObjectNull(contenedorSalidaPartidasAbiertas.value)) {
				
				// TODO: Actualizar !!!!
		 		// Aqui llega el elemento "EXP_VENDOR_DATA" que contiene una lista "<item>" de info de los CUITs encontrados como proveedores
				//    <EXP_VENDOR_DATA>
				//       <item>
				//          <TAX_NO_1>23113965169</TAX_NO_1>
				//          <COMP_CODE>M650</COMP_CODE>
				//          <VENDOR>0001001349</VENDOR>
				//          <PMNT_BLOCK>1</PMNT_BLOCK>
				//          <TEXTL>Créd retenido temp.</TEXTL>
				//       </item>
				//    </EXP_VENDOR_DATA>
		 		// 
		 		
				for (ZFISSH004 dataPartida: contenedorSalidaPartidasAbiertas.value.getItem()) {
					
					Partida partida = new Partida();

					partida.setIdSociedad(dataPartida.getCOMPCODE());
					partida.setIdProveedor(dataPartida.getVENDOR());
					partida.setAsignacion(dataPartida.getALLOCNMBR());
					partida.setEjercicioFiscalDocSAP(dataPartida.getFISCYEAR());
					partida.setNumeroDocSAP(dataPartida.getDOCNO());
					partida.setPosicionDocSAP(dataPartida.getITEMNUM());
					partida.setFechaContableDocSAP(Utilidad.formatStringconDashtoDate(dataPartida.getPSTNGDATE()));
					partida.setFechaEmision(Utilidad.formatStringconDashtoDate(dataPartida.getDOCDATE()));
					partida.setFechaRegistroSAP(Utilidad.formatStringconDashtoDate(dataPartida.getENTRYDATE()));
					partida.setMonedaDocProveedor(dataPartida.getCURRENCY());
					partida.setMonedaLocal(dataPartida.getLOCCURRCY());
					partida.setTipoCambioAFechaEmision(dataPartida.getZUKURS());
					partida.setNumeroLegalDocProveedor(dataPartida.getREFDOCNO());
					partida.setClaseDocSAP(dataPartida.getDOCTYPE());
					partida.setMesFiscalDocSAP(dataPartida.getFISPERIOD());
					partida.setClaveContabilizacionSAP(dataPartida.getPOSTKEY());
					partida.setIndicador(dataPartida.getDBCRIND());
					partida.setDivision(dataPartida.getBUSAREA());
					partida.setImporteRenglonPesosAFechaEmision(dataPartida.getLCAMOUNT());
					partida.setImporteRenglonMonedaOrigenAFechaEmision(dataPartida.getAMTDOCCUR());
					partida.setTextoPosicion(dataPartida.getITEMTEXT());
					partida.setImporteRenglonMonedaDolarAFechaEmision(dataPartida.getZIMPORTEUSDEMISION());
					
					if (!Validaciones.isNullEmptyOrDash(dataPartida.getBLINEDATE())) {
						try {
							
							partida.setFechaBase(formatoFecha.parse(dataPartida.getBLINEDATE()));
							
						} catch (ParseException e) {

							String mensajeError = Utilidad.reemplazarMensajes(
							
									Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.formato.detalle.formatoFechaIncorrecto"), CABECERA_MENSAJE_SERVICIO_WS);

							throw new WebServiceException (mensajeError);
						}
					}else {
						partida.setFechaBase(null);
					}
					
					partida.setCondPago(dataPartida.getPMNTTRMS());
					partida.setViaPago(dataPartida.getPYMTMETH());
					partida.setBloqueoPago(dataPartida.getPMNTBLOCK());
					partida.setDescripcionBloqueo(dataPartida.getPMNTBLOCKTEXTL());
					partida.setNumeroDocSAPVinculado(dataPartida.getINVREF());
					partida.setEjercicioFiscalDocSAPVinculado(dataPartida.getINVYEAR());
					partida.setPosicionDocSAPVinculado(dataPartida.getINVITEM());
					partida.setClaveRef(dataPartida.getREFKEY1());
					partida.setClaveRef2(dataPartida.getREFKEY2());
					partida.setFechaTipoCambio(Utilidad.formatStringconDashtoDate(dataPartida.getZFECHASHIVA()));
					partida.setTipoCambioAFechaTipoCambio(dataPartida.getZTIPOCAMBIOSH());
					partida.setEstadoDocSAP(dataPartida.getZSTATUS());
					partida.setImporteRenglonPesosAFechaShiva(dataPartida.getZIMPORTEARS());
					partida.setImporteRenglonMonedaDolarAFechaShiva(dataPartida.getZIMPORTEUSD());
					partida.setImporteTotalDocumentoMonedaOrigenAFechaEmision(dataPartida.getZIMPORTETOTALMD());
					partida.setImporteTotalDocumentoPesosAFechaEmision(dataPartida.getZIMPORTETOTALML());
					partida.setImporteTotalDocumentoPesosAFechaShiva(dataPartida.getZIMPORTETOTALARS());
					partida.setImporteTotalDocumentoMonedaDolarAFechaShiva(dataPartida.getZIMPORTETOTALUSD());
					
					salida.getListaPartidas().add(partida);
				}
				
				Traza.transaccionWS(this.getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		 		Traza.transaccionWS(this.getClass(), "~~ Resultado de la llamada a la consulta de partidas ");
		 		Traza.transaccionWS(this.getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	
//	 			for (ZFISSH004 dataProveedor : contenedorSalidaPartidasAbiertas.value.getItem()) {
//	 				Traza.transaccionWS(this.getClass(), "CUIT:                            " + dataProveedor.getTAXNO1());
//	 				Traza.transaccionWS(this.getClass(), "Sociedad:                        " + dataProveedor.getCOMPCODE());
//	 				Traza.transaccionWS(this.getClass(), "Id proveedor SAP:                " + dataProveedor.getVENDOR());
//	 				Traza.transaccionWS(this.getClass(), "Bloqueo de pago obtenido:        " + dataProveedor.getPMNTBLOCK());
//	 				Traza.transaccionWS(this.getClass(), "Descripción del bloqueo de pago: " + dataProveedor.getTEXTL());
//	 				Traza.transaccionWS(this.getClass(), "");
//	 				
//	 				Proveedor proveedor = new Proveedor();
//	 				proveedor.setCuit(dataProveedor.getTAXNO1());
//	 				proveedor.setIdSociedad(dataProveedor.getCOMPCODE());
//	 				proveedor.setIdProveedor(dataProveedor.getVENDOR());
//	 				proveedor.setIdBloqueo(dataProveedor.getPMNTBLOCK());
//	 				proveedor.setDescripcionBloqueo(dataProveedor.getTEXTL());
//	 				
//	 				salida.getListaProveedores().add(proveedor);
//	 			}
			}
			 		
	 		// Aqui llega el elemento "<T_RETURN>" que contiene una lista "<item>" de info de los CUITs que no fueron encontrados 
		    //     <T_RETURN>
		    //        <item>
		    //           <TYPE>E</TYPE>
		    //           <NUMBER>012</NUMBER>
		    //           <MESSAGE>El CUIT 27 no está asociado a ningún proveedor SAP</MESSAGE>
		    //        </item>
		    //        <item>
		    //           <TYPE>E</TYPE>
		    //           <NUMBER>012</NUMBER>
		    //           <MESSAGE>El CUIT 28 no está asociado a ningún proveedor SAP</MESSAGE>
		    //        </item>
		    //     </T_RETURN>
	 		//

	 		Traza.transaccionWS(this.getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	 		Traza.transaccionWS(this.getClass(), "~~ Errores retornados... ?? ");
	 		Traza.transaccionWS(this.getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

	 		// TODO: Ya mande a preguntar por esta lista, porque a mi entender no tiene sentido...
	 		
	 		if (!Validaciones.isObjectNull(contenedorSalidaErroresEncontrados.value)) {
	 			for (ZSRETURN otrosErrores : contenedorSalidaErroresEncontrados.value.getItem()) {

	 				Traza.transaccionWS(this.getClass(), "Resultado procesamiento: " + otrosErrores.getTYPE());
	 				Traza.transaccionWS(this.getClass(), "Codigo de error:         " + otrosErrores.getNUMBER());
	 				Traza.transaccionWS(this.getClass(), "Descripción del error:   " + otrosErrores.getMESSAGE());
	 				Traza.transaccionWS(this.getClass(), "");
	 				
	 				//salida.getListaErroresFuncionales().add(otrosErrores.getMESSAGE());
	 			}
	 		}
	 		
	 		// Aqui llega "<ST_RETURN>" que contiene el resultado general de la llamada al servicio
	        //     <ST_RETURN>
	        //        <TYPE>S</TYPE>
	        //        <NUMBER>000</NUMBER>
	        //        <MESSAGE>Proceso OK</MESSAGE>
	        //     </ST_RETURN>
	 		//

	 		Traza.transaccionWS(this.getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	 		Traza.transaccionWS(this.getClass(), "~~ Resultado general de la invocación ");
	 		Traza.transaccionWS(this.getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

	 		if (!Validaciones.isObjectNull(contenedorSalidaResultadoProcesamiento) && !Validaciones.isObjectNull(contenedorSalidaResultadoProcesamiento.value)) {
 				Traza.transaccionWS(this.getClass(), "Resultado procesamiento: " + contenedorSalidaResultadoProcesamiento.value.getTYPE());
 				Traza.transaccionWS(this.getClass(), "Codigo de error:         " + contenedorSalidaResultadoProcesamiento.value.getNUMBER());
 				Traza.transaccionWS(this.getClass(), "Descripción del error:   " + contenedorSalidaResultadoProcesamiento.value.getMESSAGE());
 				Traza.transaccionWS(this.getClass(), "");
 				
 				ResultadoInvocacion resultado = new ResultadoInvocacion();
 				
 				resultado.setResultadoInvocacion(contenedorSalidaResultadoProcesamiento.value.getTYPE());
 				resultado.setCodigoError(contenedorSalidaResultadoProcesamiento.value.getNUMBER());
 				resultado.setDescripcionError(contenedorSalidaResultadoProcesamiento.value.getMESSAGE());
 				
 				salida.setResultadoInvocacion(resultado);

	 		} else {

	 			ResultadoInvocacion resultado = new ResultadoInvocacion();
 				
 				resultado.setResultadoInvocacion(contenedorSalidaResultadoProcesamiento.value.getTYPE());
 				resultado.setCodigoError(Constantes.ERROR_INVOCACION_WS_RESPUESTA_INCONSISTENTE);
 				resultado.setDescripcionError(CABECERA_MENSAJE_SERVICIO_WS + ": Se ha producido un error en el webservice");
 				
 				salida.setResultadoInvocacion(resultado);
	 		}			

		} catch (SOAPFaultException e) {

			String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.formato.detalle"), CABECERA_MENSAJE_SERVICIO_WS);
			
			Traza.error(this.getClass(), Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.titulo.error") + mensajeError);
			Traza.error(this.getClass(), Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.titulo.causa") + Utilidad.descError(e));
			Traza.error(this.getClass(), Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.titulo.traza") + Utilidad.getStackTrace(e));

			throw new WebServiceFormatoMensajeExcepcion(mensajeError);

		} catch (WebServiceException e) {
			
			String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.conexion.detalle"), CABECERA_MENSAJE_SERVICIO_WS);
			
			Traza.error(this.getClass(), Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.titulo.error") + mensajeError);
			Traza.error(this.getClass(), Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.titulo.causa") + Utilidad.descError(e));
			Traza.error(this.getClass(), Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.titulo.traza") + Utilidad.getStackTrace(e));

			throw new WebServiceExcepcion(mensajeError);
		}
		 		
		return salida;
	}
}