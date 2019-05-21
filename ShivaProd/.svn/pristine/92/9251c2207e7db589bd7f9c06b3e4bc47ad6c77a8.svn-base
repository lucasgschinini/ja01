package ar.com.telecom.shiva.base.ws.cliente.servicios.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceFormatoMensajeExcepcion;
import ar.com.telecom.shiva.base.soa.singleton.SoapMensajesSingleton;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.CalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.CalipsoCobranzasWS;
import ar.com.telecom.shiva.base.ws.cliente.CalipsoConsultaAcuerdoWS;
import ar.com.telecom.shiva.base.ws.cliente.CalipsoConsultasWS;
import ar.com.telecom.shiva.base.ws.cliente.CalipsoDescobrosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoConsultaAcuerdoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCreditoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDescobroWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoConsultaAcuerdoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCreditoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDescobroWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ResultadoProcesamiento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class ClienteCalipsoServicio implements IClienteCalipsoServicio {

	CalipsoCobranzasWS calipsoCobranzasWS;
	CalipsoConsultasWS calipsoConsultasWS;
	CalipsoConsultaAcuerdoWS calipsoConsultaAcuerdoWS;
	CalipsoCargosWS calipsoCargosWS;
	CalipsoDescobrosWS calipsoDescobrosWS;
	
	@Autowired
	IMensajeriaTransaccionServicio mensajeriaTransaccionServicio;
	
	@Autowired
	IParametroServicio parametroServicio;
	
	private final String MENSAJE_AUXILIAR = "Se ha producido un error en el servicio CALIPSO.";
	private final String MENSAJE_ACUERDO = "Consulta Acuerdo";
	private final String MENSAJE_DESCOBROS = "Reversion Cobros";
	
	@Override
	public SalidaCalipsoDeudaWS consultaDebito(
			EntradaCalipsoDeudaWS entradaCalipsoDebitoWS)
			throws NegocioExcepcion {
		try {
		    SalidaCalipsoDeudaWS salidaDebitoWS = calipsoConsultasWS.consultarDeuda(entradaCalipsoDebitoWS);
		    				
		    ResultadoProcesamiento resultado = salidaDebitoWS.getResultadoProcesamiento();
			if (resultado!=null && !Validaciones.isNullOrEmpty(resultado.getResultadoImputacion())) {
				return salidaDebitoWS; 
			} else {
				Traza.error(getClass(), "Consulta Calipso Debito, Resultado Vacio.");
				throw new WebServiceExcepcion("Consulta Calipso Debito, Resultado Vacio", null, MENSAJE_AUXILIAR);
			}
			    			
		} catch (WebServiceFormatoMensajeExcepcion e3) {
			Traza.error(getClass(), "Error de Webservice Consulta Calipso Debito: ", e3);
		    throw new WebServiceExcepcion(e3.getMessage(), e3, MENSAJE_AUXILIAR);	
							
		} catch (WebServiceExcepcion e2) {
			Traza.error(getClass(), "Error de Webservice Consulta Calipso Debito: ", e2);
		    throw new WebServiceExcepcion(e2.getMessage(), e2, MENSAJE_AUXILIAR);	
		}
	}

	@Override
	public SalidaCalipsoCreditoWS consultaCredito(
			EntradaCalipsoCreditoWS entradaCalipsoCreditoWS)
			throws NegocioExcepcion {
		
		try {
		    SalidaCalipsoCreditoWS salidaCreditoWS  
				= calipsoConsultasWS.consultarCredito(entradaCalipsoCreditoWS);
		    				
		    ResultadoProcesamiento resultado = salidaCreditoWS.getResultadoProcesamiento();
			if (resultado!=null && !Validaciones.isNullOrEmpty(resultado.getResultadoImputacion())) {
				return salidaCreditoWS; 
			} else {
				Traza.error(getClass(), "Consulta Calipso Credito, Resultado Vacio.");
				throw new WebServiceExcepcion("Consulta Calipso Credito, Resultado Vacio", null, MENSAJE_AUXILIAR);
			}
			    			
		} catch (WebServiceFormatoMensajeExcepcion e3) {
			Traza.error(getClass(), "Error de Webservice Consulta Calipso Credito: ", e3);
		    throw new WebServiceExcepcion(e3.getMessage(), e3, MENSAJE_AUXILIAR);	
							
		} catch (WebServiceExcepcion e2) {
			Traza.error(getClass(), "Error de Webservice Consulta Calipso Credito: ", e2);
		    throw new WebServiceExcepcion(e2.getMessage(), e2, MENSAJE_AUXILIAR);	
		}
	}
	
	@Override
	public SalidaCalipsoApropiacionWS apropiarCobro(
			EntradaCalipsoApropiacionWS entradaCalipsoApropiacionWS)
			throws NegocioExcepcion {
		
		if (entradaCalipsoApropiacionWS.getTipoApropiacion() == null
				|| (!(MensajeServicioEnum.CLP_APROP_DEUDA.equals(entradaCalipsoApropiacionWS.getTipoApropiacion())
					|| MensajeServicioEnum.CLP_APROP_DEUDA_Y_MP.equals(entradaCalipsoApropiacionWS.getTipoApropiacion())
					|| MensajeServicioEnum.CLP_APROP_MP.equals(entradaCalipsoApropiacionWS.getTipoApropiacion())
					|| MensajeServicioEnum.CLP_APROP_DEUDA_SIM.equals(entradaCalipsoApropiacionWS.getTipoApropiacion())
					|| MensajeServicioEnum.CLP_APROP_DEUDA_Y_MP_SIM.equals(entradaCalipsoApropiacionWS.getTipoApropiacion())
					|| MensajeServicioEnum.CLP_APROP_MP_SIM.equals(entradaCalipsoApropiacionWS.getTipoApropiacion())))) {
			throw new NegocioExcepcion("El tipo de apropiacion se encuentra vacio o no "
					+ "corresponde a la operacion apropiacion de calipso: " + entradaCalipsoApropiacionWS.getTipoApropiacion());
		}

		SalidaCalipsoApropiacionWS salida = null;
		
		if (SiNoEnum.SI.equals(entradaCalipsoApropiacionWS.getModoOperacion())) {
			//Si es simulacion, no hay reintentos
			salida = (SalidaCalipsoApropiacionWS) invocarAlWebService(entradaCalipsoApropiacionWS, false, true);
		} else {
			salida = (SalidaCalipsoApropiacionWS) invocarAlWebService(entradaCalipsoApropiacionWS, true, false);
		}
		return salida;
	}
	
	@Override
	public SalidaCalipsoConfirmacionWS confirmarCobro(
			EntradaCalipsoConfirmacionWS entradaCalipsoConfirmacionWS)
			throws NegocioExcepcion {
		
		if (entradaCalipsoConfirmacionWS.getTipoApropiacion() == null
				|| (!MensajeServicioEnum.CLP_CONFIRMACION.equals(entradaCalipsoConfirmacionWS.getTipoApropiacion()))) {
			throw new NegocioExcepcion("El tipo de apropiacion se encuentra vacio o no "
					+ "corresponde a la operacion confirmacion de calipso: " + entradaCalipsoConfirmacionWS.getTipoApropiacion());
		}
		
		SalidaCalipsoConfirmacionWS salida = 
				(SalidaCalipsoConfirmacionWS) invocarAlWebService(entradaCalipsoConfirmacionWS, true, false);
		
		return salida;
	}

	
	@Override
	public SalidaCalipsoDesapropiacionWS desapropiarOperacion(
			EntradaCalipsoDesapropiacionWS entradaCalipsoDesapropiacionWS)
			throws NegocioExcepcion {
		
		if (entradaCalipsoDesapropiacionWS.getTipoApropiacion() == null
				|| (!MensajeServicioEnum.CLP_DESAPROPIACION.equals(entradaCalipsoDesapropiacionWS.getTipoApropiacion()))) {
			throw new NegocioExcepcion("El tipo de apropiacion se encuentra vacio o no "
					+ "corresponde a la operacion desapropiacion de calipso: " + entradaCalipsoDesapropiacionWS.getTipoApropiacion());
		}
		
		SalidaCalipsoDesapropiacionWS salida = 
				(SalidaCalipsoDesapropiacionWS) invocarAlWebService(entradaCalipsoDesapropiacionWS, true, false);
		
		return salida; 
				
	}
	
	@Override
	public SalidaCalipsoCargosWS generarCargoCalipso(EntradaCalipsoCargosWS entradaCalipsoCargosWS, TipoProcesoEnum tipoProceso) throws NegocioExcepcion {
		
		if (tipoProceso != null) {
			if (TipoProcesoEnum.COBRO.equals(tipoProceso)) {
				if (entradaCalipsoCargosWS.getTipoMensaje() == null
						|| (!(MensajeServicioEnum.CLP_GENERACION_CARGO_CREDITO.equals(entradaCalipsoCargosWS.getTipoMensaje())
								|| MensajeServicioEnum.CLP_GENERACION_CARGO_DEBITO.equals(entradaCalipsoCargosWS.getTipoMensaje())
								|| MensajeServicioEnum.CLP_GENERACION_CARGO_INTERES.equals(entradaCalipsoCargosWS.getTipoMensaje())
								|| MensajeServicioEnum.CLP_GENERACION_CARGO_DEBITO_SIM.equals(entradaCalipsoCargosWS.getTipoMensaje())
								|| MensajeServicioEnum.CLP_GENERACION_CARGO_CREDITO_SIM.equals(entradaCalipsoCargosWS.getTipoMensaje())
								|| MensajeServicioEnum.CLP_GENERACION_CARGO_INTERES_SIM.equals(entradaCalipsoCargosWS.getTipoMensaje())))) 
				{
					throw new NegocioExcepcion("El tipo de cargo se encuentra vacio o no "
							+ "corresponde a la operacion generacion de cargo (COBROS) de calipso: " + entradaCalipsoCargosWS.getTipoMensaje());
				}
			} else
			if (TipoProcesoEnum.DESCOBRO.equals(tipoProceso)) {
				if (entradaCalipsoCargosWS.getTipoMensaje() == null
						|| (!Validaciones.isObjectNull(entradaCalipsoCargosWS.getTipoMensaje()) 
								&& !entradaCalipsoCargosWS.getTipoMensaje().esMensajeDescobrosCalipso()))  
				{
					throw new NegocioExcepcion("El tipo de cargo se encuentra vacio o no "
							+ "corresponde a la operacion generacion de cargo (DESCOBROS) de calipso: " + entradaCalipsoCargosWS.getTipoMensaje());
				}
			} else {
				throw new NegocioExcepcion("El tipo de proceso tiene que ser COBRO o DESCOBRO");
			}
		} else {
			throw new NegocioExcepcion("El tipo de proceso es obligatorio");
		}
		boolean hayReintentos = false;
		boolean esTransaccionPropia = true; 
		
		if (SiNoEnum.NO.equals(entradaCalipsoCargosWS.getModoOperacion())) {
			hayReintentos = true; 			//Si no es simulacion, no hago reintentos
			esTransaccionPropia = false;	//Si no es simulacion, tiene transaccion propagada
		}
			
		SalidaCalipsoCargosWS salida = 
				(SalidaCalipsoCargosWS) invocarAlWebService(entradaCalipsoCargosWS, hayReintentos, esTransaccionPropia);
			
		return salida;
	}
	
	@Override
	public SalidaCalipsoConsultaAcuerdoWS consultaAcuerdo(
			EntradaCalipsoConsultaAcuerdoWS entrada)
			throws NegocioExcepcion {
		try {
			SalidaCalipsoConsultaAcuerdoWS salida  
				= calipsoConsultaAcuerdoWS.consultarAcuerdoCalipso(entrada);
		    				
		    Resultado resultado = salida.getResultado();
			if (!Validaciones.isObjectNull(resultado) && !Validaciones.isNullOrEmpty(resultado.getResultado())) {
				return salida; 				
			} else {
				throw new WebServiceExcepcion(MENSAJE_ACUERDO + ", Resultado Vacio");
			}
			    			
		} catch (WebServiceFormatoMensajeExcepcion e3) {
		    Traza.error(getClass(), "Error de Formato " + MENSAJE_ACUERDO + ": ", e3);
		    throw new WebServiceExcepcion(e3);
							
		} catch (WebServiceExcepcion e2) {
		    Traza.error(getClass(), "Error de Webservice " + MENSAJE_ACUERDO + ": ", e2);
		    throw new WebServiceExcepcion(e2);				
		}
	}
	
	
	@Override
	public SalidaCalipsoDescobroWS descobrarCobro(EntradaCalipsoDescobroWS entrada) throws NegocioExcepcion {
		
		String mensaje = "";
		
		try {
			
			//Seteo el tipo de operacion y el tipo de mensaje que siempre van a ser los mismos para el descobro
			entrada.setTipoOperacion(TipoInvocacionEnum.$09);
			
			if (entrada.getTipoMensaje() == null
					|| (!(MensajeServicioEnum.CLP_DESCOBRO.equals(entrada.getTipoMensaje())
							|| MensajeServicioEnum.CLP_DESCOBRO_SIM.equals(entrada.getTipoMensaje()))))  
			{
				throw new NegocioExcepcion("El tipo de descobro se encuentra vacio o no "
						+ "corresponde a la operacion descobro de calipso: " + entrada.getTipoMensaje());
			}
			
			boolean hayReintentos = false;
			boolean esTransaccionPropia = true; 
			
			if (SiNoEnum.NO.equals(entrada.getModoOperacion())) {
				hayReintentos = true; 			//Si no es simulacion, no hago reintentos
				esTransaccionPropia = false;	//Si no es simulacion, tiene transaccion propagada
			}
			
			SalidaCalipsoDescobroWS salida = 
					(SalidaCalipsoDescobroWS) invocarAlWebService(entrada, hayReintentos, esTransaccionPropia);
			
			if(!Validaciones.isObjectNull(salida)){
				
				Resultado resultado = salida.getResultadoLlamadaServicio();
				if (!Validaciones.isObjectNull(resultado) && !Validaciones.isNullOrEmpty(resultado.getResultado())) {
					
					return salida;			
				} else {
					mensaje = MENSAJE_DESCOBROS + ", Resultado Vacio";
					Traza.error(getClass(), mensaje, null);
					throw new WebServiceExcepcion(mensaje, null, MENSAJE_AUXILIAR);
				}
				
			} else {
				mensaje = MENSAJE_DESCOBROS + ", Resultado Vacio";
				Traza.error(getClass(), mensaje, null);
				throw new WebServiceExcepcion(mensaje, null, MENSAJE_AUXILIAR);
			}		    				
			    			
		} catch (WebServiceFormatoMensajeExcepcion e3) {
			mensaje = "Error de Formato " + MENSAJE_DESCOBROS + ": ";
		    Traza.error(getClass(), mensaje, e3);
		    throw new WebServiceExcepcion(e3.getMessage(), e3, MENSAJE_AUXILIAR);
							
		} catch (WebServiceExcepcion e2) {
			mensaje = "Error de Webservice " + MENSAJE_DESCOBROS + ": ";
		    Traza.error(getClass(), mensaje, e2);
		    throw new WebServiceExcepcion(e2.getMessage(), e2, MENSAJE_AUXILIAR);
		}
	}
	
	
	/********************************************************************************************************
	 * Invocaciones al servicio con mensajerias
	 ********************************************************************************************************/
	/**
	 * Invoco al webservice de calipso usando o no la politica de reintentos
	 * @param entradaWS
	 * @return
	 * @throws NegocioExcepcion
	 */
	private SalidaWS invocarAlWebService(EntradaWS entradaWS, boolean hayReintentos, boolean esTransaccionPropia) throws NegocioExcepcion {
		String key = obtenerKey(entradaWS);
		
		EntradaCalipsoApropiacionWS entradaApropiacionWS = null;
		EntradaCalipsoDesapropiacionWS entradaDesapropiacionWS = null;
		EntradaCalipsoConfirmacionWS entradaConfirmacionWS = null;
		EntradaCalipsoCargosWS entradaCargosWS = null;
		EntradaCalipsoDescobroWS entradaDescobroWS = null;
		
		try {
			int wsCantidadReintentos = 1;
			if (hayReintentos) {
				wsCantidadReintentos = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.WS_CANTIDAD_REINTENTOS).toString());
			}
			
			int wsTimeout = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.WS_CANTIDAD_TIMEOUT_SEGUNDOS).toString());
			wsTimeout = wsTimeout * 1000;
			
			// Apropiacion
			if (entradaWS instanceof EntradaCalipsoApropiacionWS) {
				entradaApropiacionWS = (EntradaCalipsoApropiacionWS) entradaWS;
				
				Long idOperacion = Long.valueOf(entradaApropiacionWS.getIdOperacion());
				Integer idTransaccion = Integer.valueOf(entradaApropiacionWS.getIdTransaccion());
				
				CobMensajeriaTransaccionDto mensajeriaDto = 
						(CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajePorIdOperacionTransaccion(
								idOperacion, idTransaccion, entradaApropiacionWS.getTipoApropiacion());
				
				//Reintentos
	    		for (int i=1; i <= wsCantidadReintentos; i++) {
	    			
	    			Traza.auditoria(getClass(), "Ejecutando el intento("+i+") de Apropiacion Calipso por WS - Key: " + key);
	    			boolean incrementarContadorEnBD = (i==1)?true:false;
	    			Date fechaEnvio = new Date();
	    			
	    			try {
	    				SalidaCalipsoApropiacionWS salidaApropiacionWS  
							= calipsoCobranzasWS.apropiarCobro(entradaApropiacionWS);
	    				
	    				mensajeriaDto = guardarMensajeCompletado(key, 
	    						idOperacion, idTransaccion, entradaApropiacionWS.getTipoApropiacion(), 
	    						fechaEnvio, new Date(), mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
	    				Resultado resultado = salidaApropiacionWS.getResultadoInvocacion();
						if (resultado!=null && !Validaciones.isNullOrEmpty(resultado.getResultado())) {
							return salidaApropiacionWS; 
						} else {
							throw new WebServiceExcepcion("Apropiacion, Resultado Vacio", true);
						}
		    			
	    			} catch (WebServiceFormatoMensajeExcepcion e3) {
	    				Traza.error(getClass(), "Error de Webservice Apropiacion (formato): ", e3);

	    				mensajeriaDto = guardarMensajeRecibidoConError(key,
	    						idOperacion, idTransaccion, entradaApropiacionWS.getTipoApropiacion(), 
	    						e3.getMessage(), fechaEnvio, new Date(), mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
						return null;
						
	    			} catch (WebServiceExcepcion e2) {
	    				Traza.error(getClass(), "Error de Webservice Apropiacion (conexión): ", e2);
	    				
	    				boolean mensajeVacio = (e2 instanceof WebServiceExcepcion  
	    						&& e2.isMensajeVacio()) ? true : false;  
	    				mensajeriaDto = guardarMensajePendienteNoRecibido(key, 
		    					idOperacion, idTransaccion, entradaApropiacionWS.getTipoApropiacion(), 
		    					fechaEnvio, null, mensajeriaDto, 
		    					incrementarContadorEnBD, esTransaccionPropia, mensajeVacio);
	    				
	    				if (i < wsCantidadReintentos) {
		    				try {
		    					Traza.auditoria(getClass(), "Nuevo reintento("+ (i+1) +") de Apropiacion Calipso por WS - En Espera - Key: " + key);
		    					Thread.sleep(wsTimeout);
			    			} catch (InterruptedException eInt) {}
	    				} 
	    			}	
	    		}
	    		//Fin-reintentos
	    		
	    		return null;
			} 
			
			//Confirmacion
			if (entradaWS instanceof EntradaCalipsoConfirmacionWS) {
				entradaConfirmacionWS = (EntradaCalipsoConfirmacionWS) entradaWS;
				
				Long idOperacion = new Long(entradaConfirmacionWS.getIdOperacion().toString());
				
				CobMensajeriaTransaccionDto mensajeriaDto = 
						(CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajePorIdOperacion(
								idOperacion, MensajeServicioEnum.CLP_CONFIRMACION);
				
				//Reintentos
	    		for (int i=1; i <= wsCantidadReintentos; i++) {
	    			
	    			Traza.auditoria(getClass(), "Ejecutando el intento ("+i+") de Confirmacion Calipso por WS - Key: " + key);
	    			boolean incrementarContadorEnBD = (i==1)?true:false;
	    			Date fechaEnvio = new Date();
	    			
	    			try {
	    				SalidaCalipsoConfirmacionWS salidaConfirmacionWS  
							= calipsoCobranzasWS.confirmarCobro(entradaConfirmacionWS);
	    				
	    				mensajeriaDto = guardarMensajeCompletado(key, 
	    						idOperacion, null, MensajeServicioEnum.CLP_CONFIRMACION, 
	    						fechaEnvio, new Date(), mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
	    				Resultado resultado = salidaConfirmacionWS.getResultadoInvocacion();
						if (resultado!=null && !Validaciones.isNullOrEmpty(resultado.getResultado())) {
							return salidaConfirmacionWS; 
						} else {
							throw new WebServiceExcepcion("Confirmacion, Resultado Vacio", true);
						}
		    			
	    			} catch (WebServiceFormatoMensajeExcepcion e3) {
	    				Traza.error(getClass(), "Error de Webservice Mensaje Confirmacion: ", e3);
	    				
	    				mensajeriaDto = guardarMensajeRecibidoConError(key,
	    						idOperacion, null, MensajeServicioEnum.CLP_CONFIRMACION, 
	    						e3.getMessage(), fechaEnvio, new Date(), mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
						return null;
						
	    			} catch (WebServiceExcepcion e2) {
	    				Traza.error(getClass(), "Error de Webservice Confirmacion", e2);
	    				
	    				boolean mensajeVacio = (e2 instanceof WebServiceExcepcion  
	    						&& e2.isMensajeVacio()) ? true : false;
	    				
	    				mensajeriaDto = guardarMensajePendienteNoRecibido(key, 
	    						idOperacion, null, MensajeServicioEnum.CLP_CONFIRMACION, 
	    						fechaEnvio, null, mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia, mensajeVacio);
	    				
	    				if (i < wsCantidadReintentos) {
		    				try {
		    					Traza.auditoria(getClass(), "Nuevo reintento("+ (i+1) +") de Confirmacion Calipso por WS - En Espera - Key: " + key);
		    					Thread.sleep(wsTimeout);
			    			} catch (InterruptedException eInt) {}
	    				} 
	    			}	
	    		}
	    		//Fin-reintentos
	    		
	    		return null;
			}	
			
			//Desapropiacion
			if (entradaWS instanceof EntradaCalipsoDesapropiacionWS) {
				entradaDesapropiacionWS = (EntradaCalipsoDesapropiacionWS) entradaWS;
				
				Long idOperacion = new Long(entradaDesapropiacionWS.getIdOperacion().toString());
				
				CobMensajeriaTransaccionDto mensajeriaDto = 
						(CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajePorIdOperacion(
								idOperacion, MensajeServicioEnum.CLP_DESAPROPIACION);
				
				//Reintentos
	    		for (int i=1; i <= wsCantidadReintentos; i++) {
	    			
	    			Traza.auditoria(getClass(), "Ejecutando el intento ("+i+") de Desapropiacion Calipso por WS - Key: " + key);
	    			boolean incrementarContadorEnBD = (i==1)?true:false;
	    			Date fechaEnvio = new Date();
	    			
	    			try {
	    				SalidaCalipsoDesapropiacionWS salidaDesapropiacionWS  
							= calipsoCobranzasWS.desapropiarOperacion(entradaDesapropiacionWS);
	    				
	    				mensajeriaDto = guardarMensajeCompletado(key, 
	    						idOperacion, null, MensajeServicioEnum.CLP_DESAPROPIACION, 
	    						fechaEnvio, new Date(), mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
	    				Resultado resultado = salidaDesapropiacionWS.getResultadoInvocacion();
						if (resultado!=null && !Validaciones.isNullOrEmpty(resultado.getResultado())) {
							return salidaDesapropiacionWS; 
						} else {
							throw new WebServiceExcepcion("Desapropiacion, Resultado Vacio", true);
						}
		    			
	    			} catch (WebServiceFormatoMensajeExcepcion e3) {
	    				Traza.error(getClass(), "Error de Webservice Mensaje Desapropiacion: ", e3);
	    				
	    				mensajeriaDto = guardarMensajeRecibidoConError(key,
	    						idOperacion, null, MensajeServicioEnum.CLP_DESAPROPIACION, 
	    						e3.getMessage(), fechaEnvio, new Date(), mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
						return null;
						
	    			} catch (WebServiceExcepcion e2) {
	    				Traza.error(getClass(), "Error de Webservice Desapropiacion", e2);
	    				
	    				boolean mensajeVacio = (e2 instanceof WebServiceExcepcion  
	    						&& e2.isMensajeVacio()) ? true : false;
	    				mensajeriaDto = guardarMensajePendienteNoRecibido(key, 
	    						idOperacion, null, MensajeServicioEnum.CLP_DESAPROPIACION, 
	    						fechaEnvio, null, mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia, mensajeVacio);
	    				
	    				if (i < wsCantidadReintentos) {
		    				try {
		    					Traza.auditoria(getClass(), "Nuevo reintento("+ (i+1) +") de Desapropiacion Calipso por WS - En Espera - Key: " + key);
		    					Thread.sleep(wsTimeout);
			    			} catch (InterruptedException eInt) {}
	    				} 
	    			}	
	    		}
	    		//Fin-reintentos
	    		
	    		return null;
			}
			
			// Cargos
			if (entradaWS instanceof EntradaCalipsoCargosWS) {
				entradaCargosWS = (EntradaCalipsoCargosWS) entradaWS;
				
				Long idOperacion = Long.valueOf(entradaCargosWS.getIdOperacion());
				
				if(entradaCargosWS.getTipoMensaje().esMensajeDescobrosCalipso()){
					idOperacion = Long.valueOf(entradaCargosWS.getIdOperacionDescobroMensajeria());
				}
				
				Integer idTransaccion = Integer.valueOf(entradaCargosWS.getIdTransaccion());
				
				CobMensajeriaTransaccionDto mensajeriaDto = 
						(CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajePorIdOperacionTransaccion(
								idOperacion, idTransaccion, entradaCargosWS.getTipoMensaje());
				
				//Reintentos
	    		for (int i=1; i <= wsCantidadReintentos; i++) {
	    			
	    			Traza.auditoria(getClass(), "Ejecutando el intento("+i+") de Cargos Calipso por WS - Key: " + key);
	    			boolean incrementarContadorEnBD = (i==1)?true:false;
	    			Date fechaEnvio = new Date();
	    			
	    			try {
	    				SalidaCalipsoCargosWS salidaCargosWS  
							= calipsoCargosWS.generarCargoCalipso(entradaCargosWS);
	    				
	    				
	    				mensajeriaDto = guardarMensajeCompletado(key, 
	    						idOperacion, idTransaccion, entradaCargosWS.getTipoMensaje(), 
	    						fechaEnvio, new Date(), mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
	    				Resultado resultado = salidaCargosWS.getResultado();
						if (resultado!=null && !Validaciones.isNullOrEmpty(resultado.getResultado())) {
							return salidaCargosWS; 
						} else {
							throw new WebServiceExcepcion("Cargos, Resultado Vacio", true);
						}
		    			
	    			} catch (WebServiceFormatoMensajeExcepcion e3) {
	    				Traza.error(getClass(), "Error de Webservice Cargos: ", e3);

	    				mensajeriaDto = guardarMensajeRecibidoConError(key,
	    						idOperacion, idTransaccion, entradaCargosWS.getTipoMensaje(), 
	    						e3.getMessage(), fechaEnvio, new Date(), mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
						return null;
						
	    			} catch (WebServiceExcepcion e2) {
	    				Traza.error(getClass(), "Error de Webservice Cargos: ", e2);
	    				
	    				boolean mensajeVacio = (e2 instanceof WebServiceExcepcion  
	    						&& e2.isMensajeVacio()) ? true : false;
	    				
	    				mensajeriaDto = guardarMensajePendienteNoRecibido(key, 
	    						idOperacion, idTransaccion, entradaCargosWS.getTipoMensaje(), 
	    						fechaEnvio, null, mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia, mensajeVacio);
	    				
	    				if (i < wsCantidadReintentos) {
		    				try {
		    					Traza.auditoria(getClass(), "Nuevo reintento("+ (i+1) +") de Cargos Calipso por WS - En Espera - Key: " + key);
		    					Thread.sleep(wsTimeout);
			    			} catch (InterruptedException eInt) {}
	    				} 
	    			}	
	    		}
	    		//Fin-reintentos
	    		
	    		return null;
			}
			
			
			// Descobros
			if (entradaWS instanceof EntradaCalipsoDescobroWS) {
				entradaDescobroWS = (EntradaCalipsoDescobroWS) entradaWS;
				
				Long idOperacion = Long.valueOf(entradaDescobroWS.getIdOperacionDescobroMensajeria());
				Integer idTransaccion = Integer.valueOf(entradaDescobroWS.getIdTransaccion());
				
				CobMensajeriaTransaccionDto mensajeriaDto = 
						(CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajePorIdOperacionTransaccionYEstadoMensaje(
								idOperacion, idTransaccion, entradaDescobroWS.getTipoMensaje(), MensajeEstadoEnum.ENVIADO);
				
				//Reintentos
	    		for (int i=1; i <= wsCantidadReintentos; i++) {
	    			
	    			Traza.auditoria(getClass(), "Ejecutando el intento("+i+") de " + MENSAJE_DESCOBROS + " por WS - Key: " + key);
	    			boolean incrementarContadorEnBD = (i==1)?true:false;
	    			Date fechaEnvio = new Date();
	    			
	    			try {
	    				
	    				SalidaCalipsoDescobroWS salidaDescobroWS  
							= calipsoDescobrosWS.descobrarCobro(entradaDescobroWS);
	    				
	    				mensajeriaDto = guardarMensajeCompletado(key, 
	    						idOperacion, idTransaccion, entradaDescobroWS.getTipoMensaje(), 
	    						fechaEnvio, new Date(), mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
	    				Resultado resultado = salidaDescobroWS.getResultadoLlamadaServicio();
						if (resultado!=null && !Validaciones.isNullOrEmpty(resultado.getResultado())) {
							return salidaDescobroWS; 
						} else {
							throw new WebServiceExcepcion(MENSAJE_DESCOBROS + ", Resultado Vacio", true);
						}
		    			
	    			} catch (WebServiceFormatoMensajeExcepcion e3) {
	    				Traza.error(getClass(), "Error de Webservice " + MENSAJE_DESCOBROS +": ", e3);

	    				mensajeriaDto = guardarMensajeRecibidoConError(key,
	    						idOperacion, idTransaccion, entradaDescobroWS.getTipoMensaje(), 
	    						e3.getMessage(), fechaEnvio, new Date(), mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
						return null;
						
	    			} catch (WebServiceExcepcion e2) {
	    				Traza.error(getClass(), "Error de Webservice " + MENSAJE_DESCOBROS + ": ", e2);
	    				
	    				boolean mensajeVacio = (e2 instanceof WebServiceExcepcion  
	    						&& e2.isMensajeVacio()) ? true : false;
	    				
	    				mensajeriaDto = guardarMensajePendienteNoRecibido(key, 
	    						idOperacion, idTransaccion, entradaDescobroWS.getTipoMensaje(), 
	    						fechaEnvio, null, mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia, mensajeVacio);
	    				
	    				if (i < wsCantidadReintentos) {
		    				try {
		    					Traza.auditoria(getClass(), "Nuevo reintento("+ (i+1) +") de " + MENSAJE_DESCOBROS + " por WS - En Espera - Key: " + key);
		    					Thread.sleep(wsTimeout);
			    			} catch (InterruptedException eInt) {}
	    				} 
	    			}	
	    		}
	    		//Fin-reintentos
	    		
	    		return null;
			}
			
		} catch (Exception e) {
			Traza.error(getClass(), "Se ha producido el error al invocar al webService de Calipso para la operación: " + key, e);
			return null;
		}
		
		return null;
	}

	
	/********************************************************************************************************
	 * Servicios privados de mensajerias
	 ********************************************************************************************************/
	
	/**
	 * Guardo la transaccion completada en la base de datos
	 * @param mensajeriaDto
	 * @param key
	 * @param idTransaccion
	 * @param idOperacion
	 * @param servicio
	 * @throws NegocioExcepcion
	 */
	private CobMensajeriaTransaccionDto guardarMensajeCompletado(String key, 
			Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio,
			Date fechaEnvio, Date fechaRecepcion,  
			CobMensajeriaTransaccionDto dto, boolean incrementarContador, 
			boolean esTransaccionPropia) throws NegocioExcepcion {
		
		MensajeEstadoEnum estado = MensajeEstadoEnum.COMPLETADO;
		String msgSalida = SoapMensajesSingleton.getMensajeSalida(key);
		String msgEntrada = SoapMensajesSingleton.getMensajeEntrada(key);
				
		if (dto == null) {
			CobMensajeriaTransaccionDto mensajeriaDto = new CobMensajeriaTransaccionDto();
			mensajeriaDto.setIdOperacion(idOperacion);
			mensajeriaDto.setIdTransaccion(idTransaccion);
			mensajeriaDto.setServicio(servicio);
			mensajeriaDto.setEstado(estado);
			mensajeriaDto.setFechaAlta(fechaEnvio);
			mensajeriaDto.setFechaEnvio(fechaEnvio);
			mensajeriaDto.setFechaRecepcion(fechaRecepcion);
			
			mensajeriaDto.setCantReintentos(new Integer(0));
			mensajeriaDto.setMensajeEnviado(msgSalida);
			mensajeriaDto.setRespuestaRecibida(msgEntrada);
			
			//Creo un nuevo mensaje
			if (esTransaccionPropia) {
				mensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.crearTransaccionPropia(mensajeriaDto);
			} else {
				mensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.crearTransaccionPropagada(mensajeriaDto);
			}
			
			return mensajeriaDto;
		} else {
			
			dto.setEstado(estado);
			dto.setFechaEnvio(fechaEnvio);
			dto.setFechaRecepcion(fechaRecepcion);
			dto.setMensajeEnviado(msgSalida);
			dto.setRespuestaRecibida(msgEntrada);
			
			//Actualizo el mensaje
			actualizarMensaje(dto, incrementarContador);
			
			return dto;
		}
	}
	
	/**
	 * Guardo la transaccion completada en la base de datos
	 * @param mensajeriaDto
	 * @param key
	 * @param idTransaccion
	 * @param idOperacion
	 * @param servicio
	 * @throws NegocioExcepcion
	 */
	private CobMensajeriaTransaccionDto guardarMensajePendienteNoRecibido(String key, 
			Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio, 
			Date fechaEnvio, Date fechaRecepcion, CobMensajeriaTransaccionDto dto, 
			boolean incrementarContador, boolean esTransaccionPropia, boolean mensajeVacio) throws NegocioExcepcion {
		
		MensajeEstadoEnum estado = MensajeEstadoEnum.PENDIENTE;
		if (mensajeVacio) {
			estado = MensajeEstadoEnum.NORECIBIDO;
		}
		
		String msgSalida = SoapMensajesSingleton.getMensajeSalida(key);
		String msgEntrada = null;
				
		if (dto == null) {
			CobMensajeriaTransaccionDto mensajeriaDto = new CobMensajeriaTransaccionDto();
			mensajeriaDto.setIdOperacion(idOperacion);
			mensajeriaDto.setIdTransaccion(idTransaccion);
			mensajeriaDto.setServicio(servicio);
			mensajeriaDto.setEstado(estado);
			mensajeriaDto.setFechaAlta(fechaEnvio);
			mensajeriaDto.setFechaEnvio(fechaEnvio);
			mensajeriaDto.setFechaRecepcion(fechaRecepcion);
			
			mensajeriaDto.setCantReintentos(new Integer(0));
			mensajeriaDto.setMensajeEnviado(msgSalida);
			mensajeriaDto.setRespuestaRecibida(msgEntrada);
			
			//Creo un nuevo mensaje
			if (esTransaccionPropia) {
				mensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.crearTransaccionPropia(mensajeriaDto);
			} else {
				mensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.crearTransaccionPropagada(mensajeriaDto);
			}
			
			return mensajeriaDto;
		} else {
			dto.setEstado(estado);
			dto.setFechaEnvio(fechaEnvio);
			dto.setFechaRecepcion(fechaRecepcion);
			dto.setMensajeEnviado(msgSalida);
			dto.setRespuestaRecibida(msgEntrada);
			
			//Actualizo el mensaje
			actualizarMensaje(dto, incrementarContador);
			
			return dto;
		}
	}
	
	
	/**
	 * Guardo el mensaje recibido con error
	 * @param key
	 * @param idOperacion
	 * @param fechaEnvio
	 * @param fechaRecepcion
	 * @param dto
	 * @return
	 * @throws NegocioExcepcion
	 */
	private CobMensajeriaTransaccionDto guardarMensajeRecibidoConError(String key,
			Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio,
			String descripcionError, Date fechaEnvio, Date fechaRecepcion, 
			CobMensajeriaTransaccionDto dto, boolean incrementarContador, 
			boolean esTransaccionPropia) throws NegocioExcepcion {
		
		MensajeEstadoEnum estado = MensajeEstadoEnum.REC_ERROR;
		String msgSalida = SoapMensajesSingleton.getMensajeSalida(key);
		String msgEntrada = SoapMensajesSingleton.getMensajeEntrada(key);
		if (Validaciones.isNullOrEmpty(msgEntrada)) {
			msgEntrada = descripcionError;
		}
		
		if (dto == null) {
			CobMensajeriaTransaccionDto mensajeriaDto = new CobMensajeriaTransaccionDto();
			mensajeriaDto.setIdOperacion(idOperacion);
			mensajeriaDto.setIdTransaccion(idTransaccion);
			mensajeriaDto.setServicio(servicio);
			mensajeriaDto.setEstado(estado);
			mensajeriaDto.setFechaAlta(fechaEnvio);
			mensajeriaDto.setFechaEnvio(fechaEnvio);
			mensajeriaDto.setFechaRecepcion(fechaRecepcion);
			
			mensajeriaDto.setCantReintentos(new Integer(0));
			mensajeriaDto.setMensajeEnviado(msgSalida);
			mensajeriaDto.setRespuestaRecibida(msgEntrada);
			
			//Creo un nuevo mensaje
			if (esTransaccionPropia) {
				mensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.crearTransaccionPropia(mensajeriaDto);
			} else {
				mensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.crearTransaccionPropagada(mensajeriaDto);
			}
			
			return mensajeriaDto;
		} else {
			
			dto.setEstado(estado);
			dto.setFechaEnvio(fechaEnvio);
			dto.setFechaRecepcion(fechaRecepcion);
			dto.setMensajeEnviado(msgSalida);
			dto.setRespuestaRecibida(msgEntrada);
			
			//Actualizo el mensaje
			actualizarMensaje(dto, incrementarContador);
			
			return dto;
		}
	}
	
	/**
	 * Metodo generico que actualizo el mensaje
	 * @param mensajeriaDto
	 * @param key
	 * @param i
	 * @throws NegocioExcepcion
	 */
	private void actualizarMensaje(CobMensajeriaTransaccionDto mensajeriaDto, boolean incrementarContador) throws NegocioExcepcion {
		if (incrementarContador) {
			if (mensajeriaDto.getCantReintentos().compareTo(new Integer("9999999")) <= 0) {
				mensajeriaDto.setCantReintentos(mensajeriaDto.getCantReintentos() + 1);
			} else {
				mensajeriaDto.setCantReintentos(0);
			}
		}
		mensajeriaTransaccionServicio.modificarTransaccionPropia(mensajeriaDto);
	}
	
	/**
	 * Obtengo el key para guardar en la memoria
	 * @param entradaWS
	 * @return
	 * @throws NegocioExcepcion
	 */
	private String obtenerKey(EntradaWS entradaWS) throws NegocioExcepcion {
		if (entradaWS instanceof EntradaCalipsoApropiacionWS) {
			EntradaCalipsoApropiacionWS entradaApropiacionWS = (EntradaCalipsoApropiacionWS) entradaWS;
			
			String idOperacion = Utilidad.rellenarCerosIzquierda(entradaApropiacionWS.getIdOperacion().toString(), 7);
			String numeroTransaccion = Utilidad.rellenarCerosIzquierda(entradaApropiacionWS.getNumeroTransaccion().toString(), 5);
			
			return idOperacion+"."+numeroTransaccion;
		} 
		
		if (entradaWS instanceof EntradaCalipsoConfirmacionWS) {
			EntradaCalipsoConfirmacionWS entradaConfirmacionWS = (EntradaCalipsoConfirmacionWS) entradaWS;
			
			String idOperacion = entradaConfirmacionWS.getIdOperacion().toString();
			return idOperacion + ".";
		}	
		
		
		if (entradaWS instanceof EntradaCalipsoDesapropiacionWS) {
			EntradaCalipsoDesapropiacionWS entradaDesapropiacionWS 
					= (EntradaCalipsoDesapropiacionWS) entradaWS;
			
			String idOperacion = entradaDesapropiacionWS.getIdOperacion().toString();
			return idOperacion + ".";	
		}
		
		if (entradaWS instanceof EntradaCalipsoCargosWS) {
			EntradaCalipsoCargosWS entradaCargosWS = (EntradaCalipsoCargosWS) entradaWS;
			
			String idOperacion = Utilidad.rellenarCerosIzquierda(entradaCargosWS.getIdOperacion().toString(), 7);
			String numeroTransaccion = Utilidad.rellenarCerosIzquierda(entradaCargosWS.getNumeroTransaccion().toString(), 5);
			
			return idOperacion+"."+numeroTransaccion;
		}
		
		if (entradaWS instanceof EntradaCalipsoDescobroWS) {
			EntradaCalipsoDescobroWS entradaDescobroWS = (EntradaCalipsoDescobroWS) entradaWS;
			
			String idOperacion = Utilidad.rellenarCerosIzquierda(entradaDescobroWS.getIdOperacion().toString(), 7);
			String numeroTransaccion = Utilidad.rellenarCerosIzquierda(entradaDescobroWS.getNumeroTransaccion().toString(), 5);
			
			return idOperacion+"."+numeroTransaccion;
		}
		
		throw new NegocioExcepcion("Error de tipo de EntradaWS: " + entradaWS+ " no  encontrada");
	}
	
	/********************************************************************************************************
	 * Para testear 
	 ********************************************************************************************************/
	@Override
	public SalidaCalipsoApropiacionWS testApropiarCobro(
			EntradaCalipsoApropiacionWS entradaCalipsoApropiacionWS)
			throws NegocioExcepcion {
		
		if (entradaCalipsoApropiacionWS.getTipoApropiacion() == null
				|| (!(MensajeServicioEnum.CLP_APROP_DEUDA.equals(entradaCalipsoApropiacionWS.getTipoApropiacion())
					|| MensajeServicioEnum.CLP_APROP_DEUDA_Y_MP.equals(entradaCalipsoApropiacionWS.getTipoApropiacion())
					|| MensajeServicioEnum.CLP_APROP_MP.equals(entradaCalipsoApropiacionWS.getTipoApropiacion())))) {
			throw new NegocioExcepcion("El tipo de apropiacion se encuentra vacio o no "
					+ "corresponde a la operacion apropiacion de calipso: " + entradaCalipsoApropiacionWS.getTipoApropiacion());
		}

		return (SalidaCalipsoApropiacionWS) 
				testearWebService(entradaCalipsoApropiacionWS);
	}

	@Override
	public SalidaCalipsoConfirmacionWS testConfirmarCobro(
			EntradaCalipsoConfirmacionWS entradaCalipsoConfirmacionWS)
			throws NegocioExcepcion {
		
		if (entradaCalipsoConfirmacionWS.getTipoApropiacion() == null
				|| (!MensajeServicioEnum.CLP_CONFIRMACION.equals(entradaCalipsoConfirmacionWS.getTipoApropiacion()))) {
			throw new NegocioExcepcion("El tipo de apropiacion se encuentra vacio o no "
					+ "corresponde a la operacion confirmacion de calipso: " + entradaCalipsoConfirmacionWS.getTipoApropiacion());
		}
		
		return (SalidaCalipsoConfirmacionWS) 
				testearWebService(entradaCalipsoConfirmacionWS);
	}

	@Override
	public SalidaCalipsoDesapropiacionWS testDesapropiarOperacion(
			EntradaCalipsoDesapropiacionWS entradaCalipsoDesapropiacionWS)
			throws NegocioExcepcion {
		
		if (entradaCalipsoDesapropiacionWS.getTipoApropiacion() == null
				|| (!MensajeServicioEnum.CLP_DESAPROPIACION.equals(entradaCalipsoDesapropiacionWS.getTipoApropiacion()))) {
			throw new NegocioExcepcion("El tipo de apropiacion se encuentra vacio o no "
					+ "corresponde a la operacion desapropiacion de calipso: " + entradaCalipsoDesapropiacionWS.getTipoApropiacion());
		}
		
		return (SalidaCalipsoDesapropiacionWS) 
				testearWebService(entradaCalipsoDesapropiacionWS);
	}
	
	/**
	 * Testeo al webservice de calipso sin reintentos
	 * @param entradaWS
	 * @return
	 * @throws NegocioExcepcion
	 */
	private SalidaWS testearWebService(EntradaWS entradaWS) throws NegocioExcepcion {
		String key = obtenerKey(entradaWS);
		
		EntradaCalipsoApropiacionWS entradaApropiacionWS = null;
		EntradaCalipsoDesapropiacionWS entradaDesapropiacionWS = null;
		EntradaCalipsoConfirmacionWS entradaConfirmacionWS = null;
		
		try {
			// Apropiacion
			if (entradaWS instanceof EntradaCalipsoApropiacionWS) {
				entradaApropiacionWS = (EntradaCalipsoApropiacionWS) entradaWS;
				
				try {
					SalidaCalipsoApropiacionWS salidaApropiacionWS  
							= calipsoCobranzasWS.apropiarCobro(entradaApropiacionWS);
	    				
	    			Resultado resultado = salidaApropiacionWS.getResultadoInvocacion();
					if (resultado!=null && !Validaciones.isNullOrEmpty(resultado.getResultado())) {
						return salidaApropiacionWS; 
					} else {
						throw new WebServiceExcepcion("Apropiacion, Resultado Vacio");
					}
		    			
	    		} catch (WebServiceFormatoMensajeExcepcion e3) {
	    			Traza.error(getClass(), "Error de Webservice Apropiacion: ", e3);	
	    		} catch (WebServiceExcepcion e2) {
	    			Traza.error(getClass(), "Error de Webservice Apropiacion: ", e2);	
	    		}
	    		return null;
			} 
			
			//Confirmacion
			if (entradaWS instanceof EntradaCalipsoConfirmacionWS) {
				entradaConfirmacionWS = (EntradaCalipsoConfirmacionWS) entradaWS;
				
				try {
	    			SalidaCalipsoConfirmacionWS salidaConfirmacionWS  
							= calipsoCobranzasWS.confirmarCobro(entradaConfirmacionWS);
	    				
	    			Resultado resultado = salidaConfirmacionWS.getResultadoInvocacion();
					if (resultado!=null && !Validaciones.isNullOrEmpty(resultado.getResultado())) {
						return salidaConfirmacionWS; 
					} else {
						throw new WebServiceExcepcion("Confirmacion, Resultado Vacio");
					}
		    			
	    		} catch (WebServiceFormatoMensajeExcepcion e3) {
	    			Traza.error(getClass(), "Error de Webservice Mensaje Confirmacion: ", e3);
	    		} catch (WebServiceExcepcion e2) {
	    			Traza.error(getClass(), "Error de Webservice Confirmacion", e2);	
	    		}
	    		return null;
			}	
			
			//Desapropiacion
			if (entradaWS instanceof EntradaCalipsoDesapropiacionWS) {
				entradaDesapropiacionWS = (EntradaCalipsoDesapropiacionWS) entradaWS;
				
				try {
	    			SalidaCalipsoDesapropiacionWS salidaDesapropiacionWS  
							= calipsoCobranzasWS.desapropiarOperacion(entradaDesapropiacionWS);
	    				
	    			Resultado resultado = salidaDesapropiacionWS.getResultadoInvocacion();
					if (resultado!=null && !Validaciones.isNullOrEmpty(resultado.getResultado())) {
						return salidaDesapropiacionWS; 
					} else {
						throw new WebServiceExcepcion("Desapropiacion, Resultado Vacio");
					}
		    			
	    		} catch (WebServiceFormatoMensajeExcepcion e3) {
	    				Traza.error(getClass(), "Error de Webservice Mensaje Desapropiacion: ", e3);
	    		} catch (WebServiceExcepcion e2) {
	    				Traza.error(getClass(), "Error de Webservice Desapropiacion", e2);
	    		}
	    		return null;
			}
			
		} catch (Exception e) {
			Traza.error(getClass(), "Se ha producido el error al invocar al webService de Calipso para la operación: " + key, e);
			return null;
		}
		
		return null;
	}
	
	/********************************************************************************************************
	 * SETTERS & GETTERS
	 ********************************************************************************************************/
	public CalipsoCobranzasWS getCalipsoCobranzasWS() {
		return calipsoCobranzasWS;
	}

	public void setCalipsoCobranzasWS(CalipsoCobranzasWS calipsoCobranzasWS) {
		this.calipsoCobranzasWS = calipsoCobranzasWS;
	}

	public CalipsoConsultasWS getCalipsoConsultasWS() {
		return calipsoConsultasWS;
	}

	public void setCalipsoConsultasWS(CalipsoConsultasWS calipsoConsultasWS) {
		this.calipsoConsultasWS = calipsoConsultasWS;
	}

	public CalipsoConsultaAcuerdoWS getCalipsoConsultaAcuerdoWS() {
		return calipsoConsultaAcuerdoWS;
	}

	public void setCalipsoConsultaAcuerdoWS(
			CalipsoConsultaAcuerdoWS calipsoConsultaAcuerdoWS) {
		this.calipsoConsultaAcuerdoWS = calipsoConsultaAcuerdoWS;
	}

	public CalipsoCargosWS getCalipsoCargosWS() {
		return calipsoCargosWS;
	}

	public void setCalipsoCargosWS(CalipsoCargosWS calipsoCargosWS) {
		this.calipsoCargosWS = calipsoCargosWS;
	}

	public CalipsoDescobrosWS getCalipsoDescobrosWS() {
		return calipsoDescobrosWS;
	}

	public void setCalipsoDescobrosWS(CalipsoDescobrosWS calipsoDescobrosWS) {
		this.calipsoDescobrosWS = calipsoDescobrosWS;
	}

}
