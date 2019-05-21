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
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapVerificacionPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapVerificacionPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.Partida;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.ResultadoInvocacion;
import ar.com.telecom.shiva.presentacion.wsdl.sap.verificacionPartidas.ZFISHIVAVERIFPARTIDASWS;
import ar.com.telecom.shiva.presentacion.wsdl.sap.verificacionPartidas.ZFISSH002;
import ar.com.telecom.shiva.presentacion.wsdl.sap.verificacionPartidas.ZFITTSH002;
import ar.com.telecom.shiva.presentacion.wsdl.sap.verificacionPartidas.ZSRETURN;
import ar.com.telecom.shiva.presentacion.wsdl.sap.verificacionPartidas.ZTTRETURN;

/**
 * @author u586743
 *
 */
public class SapVerificacionPartidasWS  extends SapConsultasWS {

	private final String CABECERA_MENSAJE_SERVICIO_WS = "WS SapVerificacionPartidas";

	@Autowired
	ZFISHIVAVERIFPARTIDASWS webServiceSapVerificacionPartidasPortType;

	public SalidaSapVerificacionPartidasWS verificarPartidas(EntradaSapVerificacionPartidasWS entrada) throws NegocioExcepcion {

		SalidaSapVerificacionPartidasWS salida = new SalidaSapVerificacionPartidasWS();
		String idOperacionShiva = entrada.getIdOperacionShiva().toString();

		try {
			ZFITTSH002 contenedorPartidasAVerificar = new ZFITTSH002();

			if(!Validaciones.isObjectNull(entrada))
			{
				for (Partida partidaEntrada : entrada.getListaPartidas()) {

					ZFISSH002 partidaWS = new ZFISSH002();

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
					
				

					contenedorPartidasAVerificar.getItem().add(partidaWS);
				}
			}


			Holder<ZSRETURN> contenedorSalidaResultadoProcesamiento = new Holder<ZSRETURN>();
			Holder<ZTTRETURN> contenedorSalidaErroresEncontrados = new Holder<ZTTRETURN>();
			Holder<String> idOperacionShivaSap =  new Holder<String>();

			webServiceSapVerificacionPartidasPortType.zfiSHIVAVERIFICARPARTIDAS(idOperacionShiva,
																				contenedorPartidasAVerificar, 
																				idOperacionShivaSap,
																				contenedorSalidaResultadoProcesamiento,
																				contenedorSalidaErroresEncontrados);
			
			if (!Validaciones.isObjectNull(contenedorSalidaErroresEncontrados.value)) {
				for (ZSRETURN otrosErrores : contenedorSalidaErroresEncontrados.value.getItem()) {

					Traza.transaccionWS(this.getClass(), "Resultado procesamiento: " + otrosErrores.getTYPE());
					Traza.transaccionWS(this.getClass(), "Codigo de error:         " + otrosErrores.getNUMBER());
					Traza.transaccionWS(this.getClass(), "Descripción del error:   " + otrosErrores.getMESSAGE());
					Traza.transaccionWS(this.getClass(), "");
					
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


			if(!Validaciones.isObjectNull(contenedorSalidaErroresEncontrados)) {

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
