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
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.Proveedor;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.ResultadoInvocacion;
import ar.com.telecom.shiva.presentacion.wsdl.sap.s4.consultaProveedores.ZFISHIVAVENDORFINDWS;
import ar.com.telecom.shiva.presentacion.wsdl.sap.s4.consultaProveedores.ZFISSH001;
import ar.com.telecom.shiva.presentacion.wsdl.sap.s4.consultaProveedores.ZFISSH001I;
import ar.com.telecom.shiva.presentacion.wsdl.sap.s4.consultaProveedores.ZFITTSH001;
import ar.com.telecom.shiva.presentacion.wsdl.sap.s4.consultaProveedores.ZFITTSH001I;
import ar.com.telecom.shiva.presentacion.wsdl.sap.s4.consultaProveedores.ZSRETURN;
import ar.com.telecom.shiva.presentacion.wsdl.sap.s4.consultaProveedores.ZTTRETURN;

public class SapS4ConsultaProveedoresWS extends SapConsultasWS {
	
	private final String CABECERA_MENSAJE_SERVICIO_WS = "WS SapConsultaProveedores";
	
	@Autowired
	ZFISHIVAVENDORFINDWS webServiceSapS4ConsultaProveedoresClient;

	/**
	 * Permite realizar la busqueda de proveedores en SAP, por CUIT
	 * 
	 * @param entrada
	 * @return
	 */
	public SalidaSapConsultaProveedoresWS consultarProveedores(EntradaSapConsultaProveedoresWS entrada) throws NegocioExcepcion {
		Traza.auditoria(getClass(), "Se usa el WebService de S4");
		SalidaSapConsultaProveedoresWS salida = new SalidaSapConsultaProveedoresWS();

		try {
	    	
			// Mapeo la entrada: lista de Cuits
	    	ZFITTSH001I contenedorEntradaCuits = new ZFITTSH001I();
	    	
	    	for (String itemCuit : entrada.getListaCuits()) {
	    		ZFISSH001I contenedorObjetoCuit = new ZFISSH001I();
	    		contenedorObjetoCuit.setTAXNO1(itemCuit);

	    		contenedorEntradaCuits.getItem().add(contenedorObjetoCuit);
	    	}
	    	
	    	// Mapeo la entrada: codigo de sociedad
	    	String contenedorEntradaIdSociedad = SAP_WS_CODIGO_SOCIEDAD_TELECOM_ARGENTINA_S4;

	 		// Mapeo la salida: info proveedor, lista de cuits no encontrados, y resultado de la invocación
	 		Holder<ZFITTSH001> contenedorSalidaInfoProveedor = new Holder<ZFITTSH001>();
	 		Holder<ZSRETURN>   contenedorSalidaResultadoProcesamiento = new Holder<ZSRETURN>();
	 		Holder<ZTTRETURN>  contenedorSalidaCuitsNoEncontrados = new Holder<ZTTRETURN>();
	 		
	 		webServiceSapS4ConsultaProveedoresClient.zfiSHIVAVENDORFIND(contenedorEntradaCuits, 
																		contenedorEntradaIdSociedad, 
																		contenedorSalidaInfoProveedor, 
																		contenedorSalidaResultadoProcesamiento, 
																		contenedorSalidaCuitsNoEncontrados);

			if (!Validaciones.isObjectNull(contenedorSalidaInfoProveedor) && 
					!Validaciones.isObjectNull(contenedorSalidaInfoProveedor.value)) {
				
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
		 		
		 		Traza.transaccionWS(this.getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		 		Traza.transaccionWS(this.getClass(), "~~ Resultado de la llamada a la consulta de proveedores ");
		 		Traza.transaccionWS(this.getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	
	 			for (ZFISSH001 dataProveedor : contenedorSalidaInfoProveedor.value.getItem()) {
	 				Traza.transaccionWS(this.getClass(), "CUIT:                            " + dataProveedor.getTAXNO1());
	 				Traza.transaccionWS(this.getClass(), "Sociedad:                        " + dataProveedor.getCOMPCODE());
	 				Traza.transaccionWS(this.getClass(), "Id proveedor SAP:                " + dataProveedor.getVENDOR());
	 				Traza.transaccionWS(this.getClass(), "Bloqueo de pago obtenido:        " + dataProveedor.getPMNTBLOCK());
	 				Traza.transaccionWS(this.getClass(), "Descripción del bloqueo de pago: " + dataProveedor.getTEXTL());
	 				Traza.transaccionWS(this.getClass(), "");
	 				
	 				Proveedor proveedor = new Proveedor();
	 				proveedor.setCuit(dataProveedor.getTAXNO1());
	 				proveedor.setIdSociedad(dataProveedor.getCOMPCODE());
	 				proveedor.setIdProveedor(dataProveedor.getVENDOR());
	 				proveedor.setIdBloqueo(dataProveedor.getPMNTBLOCK());
	 				proveedor.setDescripcionBloqueo(dataProveedor.getTEXTL());
	 				
	 				salida.getListaProveedores().add(proveedor);
	 			}
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
	 		Traza.transaccionWS(this.getClass(), "~~ Cuits no encontrados ");
	 		Traza.transaccionWS(this.getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

	 		if (!Validaciones.isObjectNull(contenedorSalidaCuitsNoEncontrados.value)) {
	 			for (ZSRETURN dataCuitNoEncontrado : contenedorSalidaCuitsNoEncontrados.value.getItem()) {

	 				//Traza.transaccionWS(this.getClass(), "Resultado procesamiento: " + dataCuitNoEncontrado.getTYPE());
	 				//Traza.transaccionWS(this.getClass(), "Codigo de error:         " + dataCuitNoEncontrado.getNUMBER());
	 				Traza.transaccionWS(this.getClass(), "Descripción del error: " + dataCuitNoEncontrado.getMESSAGE());
	 				//Traza.transaccionWS(this.getClass(), "");
	 				
	 				salida.getListaErroresCuitNoEncontrados().add(dataCuitNoEncontrado.getMESSAGE());
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
