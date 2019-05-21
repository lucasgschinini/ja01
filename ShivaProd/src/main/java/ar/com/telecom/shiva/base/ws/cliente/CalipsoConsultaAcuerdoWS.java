package ar.com.telecom.shiva.base.ws.cliente;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.jaxws.JaxWsSoapFaultException;

import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceFormatoMensajeExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoConsultaAcuerdoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoConsultaAcuerdoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.ResultadoAcuerdo;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.consultaAcuerdo.WebServiceCalipsoConsultaAcuerdoPortType;

public class CalipsoConsultaAcuerdoWS {
	
	@Autowired
	WebServiceCalipsoConsultaAcuerdoPortType webServiceCalipsoConsultaAcuerdoPortType;
	
	private final String MENSAJE = "WS CalipsoConsultaAcuerdo";
	
	/**
	 * Objetivo: Permite al consumidor del servicio consultar el estado del acuedo de facturacion
	 * Proveedor: Calipso
	 * 
	 * @param entrada
	 * @return
	 * @throws NegocioExcepcion 
	 */
	public SalidaCalipsoConsultaAcuerdoWS consultarAcuerdoCalipso(EntradaCalipsoConsultaAcuerdoWS entrada) 
			throws NegocioExcepcion {
		
		try {
			
			BigInteger acuerdo = entrada.getAcuerdoFacturacion();
			ar.com.telecom.shiva.presentacion.wsdl.calipso.consultaAcuerdo.ResultadoAcuerdo respuesta = new ar.com.telecom.shiva.presentacion.wsdl.calipso.consultaAcuerdo.ResultadoAcuerdo();
			
			respuesta = webServiceCalipsoConsultaAcuerdoPortType.consultarAcuerdosCalipso(acuerdo);
			
			if (!Validaciones.isObjectNull(respuesta)) {
				
				SalidaCalipsoConsultaAcuerdoWS salida = new SalidaCalipsoConsultaAcuerdoWS();
				if (!Validaciones.isObjectNull(respuesta.getAcuerdo())) {
					ResultadoAcuerdo salidaAcuerdo = new ResultadoAcuerdo();
					salidaAcuerdo.setIdClienteLegado(
						Validaciones.isNullEmptyOrDash(respuesta.getAcuerdo().getIdClienteLegado()) ? null : new BigInteger(respuesta.getAcuerdo().getIdClienteLegado())
					);
					salidaAcuerdo.setEstado(
						Validaciones.isNullEmptyOrDash(respuesta.getAcuerdo().getEstado()) ? null :  EstadoAcuerdoFacturacionEnum.getEnumByName(respuesta.getAcuerdo().getEstado())
					);
					salidaAcuerdo.setMoneda(
						Validaciones.isNullEmptyOrDash(respuesta.getAcuerdo().getMoneda()) ? null : MonedaEnum.getEnumBySigno2((respuesta.getAcuerdo().getMoneda()))
					);
					salida.setAcuerdo(salidaAcuerdo);
					Traza.error(getClass(), salidaAcuerdo.toString());
				}
				
				if(!Validaciones.isObjectNull(respuesta.getResultadoProcesamiento())){
					Resultado salidaResultado = new Resultado();
					salidaResultado.setCodigoError(respuesta.getResultadoProcesamiento().getCodigoerror());
					salidaResultado.setDescripcionError(respuesta.getResultadoProcesamiento().getDescripcionerror());
					salidaResultado.setResultado(respuesta.getResultadoProcesamiento().getResultadoProceso());
					salida.setResultado(salidaResultado);
				}
				
				return salida;
			}else{
				throw new WebServiceExcepcion(MENSAJE + ": Se ha producido un error en el webservice");
			}	
			
		} catch (JaxWsSoapFaultException e) {
			throw new WebServiceFormatoMensajeExcepcion(MENSAJE + " - Error de formato: " + e);	
		} catch (RemoteAccessException e) {
			throw new WebServiceExcepcion(MENSAJE + ": Falla de conexion: " + e);
		}
	}
	
}
