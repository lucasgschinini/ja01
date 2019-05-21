/**
 * 
 */
package ar.com.telecom.shiva.base.ws.cliente;

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
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapRegistrarCompensacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapRegistrarCompensacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.IdDocumentoCap;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.Partida;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.ResultadoInvocacion;
import ar.com.telecom.shiva.presentacion.wsdl.sap.imputacion.ZFISHIVAPOSTCOMPENSACIONWS;
import ar.com.telecom.shiva.presentacion.wsdl.sap.imputacion.ZFISSH005;
import ar.com.telecom.shiva.presentacion.wsdl.sap.imputacion.ZFISSH008;
import ar.com.telecom.shiva.presentacion.wsdl.sap.imputacion.ZFITTSH005;
import ar.com.telecom.shiva.presentacion.wsdl.sap.imputacion.ZSRETURN;
import ar.com.telecom.shiva.presentacion.wsdl.sap.imputacion.ZTTRETURN;

/**
 * @author u586743
 *
 */
public class SapRegistrarCompensacionWS  extends SapConsultasWS {

	private final String CABECERA_MENSAJE_SERVICIO_WS = "WS SapImputación";

	@Autowired
	ZFISHIVAPOSTCOMPENSACIONWS webServiceSapImputacionPortType;

	public SalidaSapRegistrarCompensacionWS imputar (EntradaSapRegistrarCompensacionWS entrada)  throws NegocioExcepcion  {

		SalidaSapRegistrarCompensacionWS salida = new SalidaSapRegistrarCompensacionWS();
		String idOperacionShiva = entrada.getIdOperacionShiva().toString();

		try {
			ZFITTSH005 contenedorPartidasAImputar = new ZFITTSH005();

			if(!Validaciones.isObjectNull(entrada))
			{
				for (Partida partidaEntrada : entrada.getListaPartidas()) {

					ZFISSH005 partidaWS = new ZFISSH005();
					partidaWS.setAMTDOCCUR(partidaEntrada.getImporteRenglonMonedaOrigenAFechaEmision());
					partidaWS.setCOMPCODE(partidaEntrada.getIdSociedad());
					
					partidaWS.setCURRENCY(!Validaciones.isObjectNull(
							partidaEntrada.getMonedaDocProveedor())? partidaEntrada.getMonedaDocProveedor() : "");
					
					partidaWS.setDOCNO(!Validaciones.isObjectNull(
							partidaEntrada.getNumeroDocSAP()) ? partidaEntrada.getNumeroDocSAP() : "");
					 
					partidaWS.setDBCRIND(!Validaciones.isObjectNull(
							partidaEntrada.getIndicador()) ? partidaEntrada.getIndicador() : "");
					
					partidaWS.setDOCTYPE(!Validaciones.isObjectNull(
							partidaEntrada.getClaseDocSAP()) ? partidaEntrada.getClaseDocSAP() : "");
					
					partidaWS.setFISCYEAR(!Validaciones.isObjectNull(
							partidaEntrada.getEjercicioFiscalDocSAP()) ? partidaEntrada.getEjercicioFiscalDocSAP() : "");
					
					partidaWS.setFISPERIOD(!Validaciones.isObjectNull(
							partidaEntrada.getMesFiscalDocSAP()) ? partidaEntrada.getMesFiscalDocSAP() : "");
					
					partidaWS.setINVITEM(!Validaciones.isObjectNull(
							partidaEntrada.getPosicionDocSAPVinculado()) ? partidaEntrada.getPosicionDocSAPVinculado() : "");
					
					partidaWS.setINVREF(!Validaciones.isObjectNull(
							partidaEntrada.getNumeroDocSAPVinculado()) ? partidaEntrada.getNumeroDocSAPVinculado() : "");
					
					partidaWS.setINVYEAR(!Validaciones.isObjectNull(
							partidaEntrada.getEjercicioFiscalDocSAPVinculado()) ? partidaEntrada.getEjercicioFiscalDocSAPVinculado() : "");
					
					partidaWS.setITEMNUM(!Validaciones.isObjectNull(
							partidaEntrada.getPosicionDocSAP()) ? partidaEntrada.getPosicionDocSAP() : "");
					
					partidaWS.setPMNTBLOCK(!Validaciones.isObjectNull(
							partidaEntrada.getBloqueoPago()) ? partidaEntrada.getBloqueoPago() : "");
					
					partidaWS.setREFKEY2(!Validaciones.isObjectNull(
							partidaEntrada.getClaveRef2()) ? partidaEntrada.getClaveRef2() : "");
					
					partidaWS.setVENDOR(!Validaciones.isObjectNull(
							partidaEntrada.getIdProveedor()) ? partidaEntrada.getIdProveedor() : "");
					
					partidaWS.setZSHIVAMONEDA(partidaEntrada.getMonedaCompensacion());
					partidaWS.setZIMPORTEARS(partidaEntrada.getImporteAplicadoMonedaPesos());
					partidaWS.setZIMPORTEUSD(partidaEntrada.getImporteAplicadoMonedaDolar());
					
					partidaWS.setAMTDOCCUR (partidaEntrada.getImporteRenglonMonedaOrigenAFechaEmision());
					
					partidaWS.setITEMTEXT(!Validaciones.isObjectNull(
							partidaEntrada.getTextoPosicion())? partidaEntrada.getTextoPosicion() : "");
					
					partidaWS.setDOCDATE(Utilidad.formatDateAAAAMMDDconDash(partidaEntrada.getFechaEmision()));
					
					contenedorPartidasAImputar.getItem().add(partidaWS);
				}
			}
			Holder<ZSRETURN> contenedorSalidaResultadoProcesamiento = new Holder<ZSRETURN>();
			Holder<ZTTRETURN> contenedorSalidaErroresEncontrados = new Holder<ZTTRETURN>();
			Holder<String> idOperacionShivaSap =  new Holder<String>();
			Holder<ZFISSH008> contenedorIdDocumentoCap = new Holder<ZFISSH008>();
			
			
			webServiceSapImputacionPortType.zfiSHIVAPOSTCOMPENSACION(idOperacionShiva,
																	entrada.getPdfFlie(),
																	contenedorPartidasAImputar,
																	contenedorIdDocumentoCap,
																	idOperacionShivaSap,
																	contenedorSalidaResultadoProcesamiento,
																	contenedorSalidaErroresEncontrados);
			
			if (!Validaciones.isObjectNull(contenedorIdDocumentoCap.value)) {
				IdDocumentoCap idDocCap = new IdDocumentoCap();
				if (!contenedorIdDocumentoCap.value.getFISCYEAR().equalsIgnoreCase("")) {
					idDocCap.setFiscyear(new Long (contenedorIdDocumentoCap.value.getFISCYEAR()));
				}
				if (!contenedorIdDocumentoCap.value.getDOCNO().equalsIgnoreCase("")) {
					idDocCap.setIdDocumento(contenedorIdDocumentoCap.value.getDOCNO());
				}
				if (!contenedorIdDocumentoCap.value.getCOMPCODE().equalsIgnoreCase("")) {
					idDocCap.setIdSociedad (contenedorIdDocumentoCap.value.getCOMPCODE());
				}
				
				salida.setIdDocumentoCap(idDocCap);
			}

			if (!Validaciones.isObjectNull(contenedorSalidaErroresEncontrados.value)) {
				for (ZSRETURN otrosErrores : contenedorSalidaErroresEncontrados.value.getItem()) {

					Traza.transaccionWS(this.getClass(), "Resultado procesamiento: " + otrosErrores.getTYPE());
					Traza.transaccionWS(this.getClass(), "Codigo de error:         " + otrosErrores.getNUMBER());
					Traza.transaccionWS(this.getClass(), "Descripción del error:   " + otrosErrores.getMESSAGE());
					
					ResultadoInvocacion resultado = new ResultadoInvocacion();
	 				
	 				resultado.setResultadoInvocacion(otrosErrores.getTYPE());
	 				resultado.setCodigoError(otrosErrores.getNUMBER());
	 				resultado.setDescripcionError(otrosErrores.getMESSAGE());
	 				
	 				salida.getListaverificaciones().add(resultado);
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

			if (!Validaciones.isObjectNull(contenedorSalidaResultadoProcesamiento))	{

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

	 			Traza.transaccionWS(this.getClass(), "Resultado procesamiento: " + contenedorSalidaResultadoProcesamiento.value.getTYPE());
	 			Traza.transaccionWS(this.getClass(), "");

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
