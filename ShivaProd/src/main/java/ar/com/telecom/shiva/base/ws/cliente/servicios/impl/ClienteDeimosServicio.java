package ar.com.telecom.shiva.base.ws.cliente.servicios.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceFormatoMensajeExcepcion;
import ar.com.telecom.shiva.base.soa.singleton.SoapMensajesSingleton;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.DeimosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosConsultaEstadoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosConsultaEstadoDocumentoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ResultadoProcesamiento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.util.IDatosComunesEntrada;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteDeimosServicio;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class ClienteDeimosServicio implements IClienteDeimosServicio {
	
	@Autowired
	DeimosWS deimosWS;
	
	@Autowired
	IMensajeriaTransaccionServicio mensajeriaTransaccionServicio;
	
	@Autowired
	IParametroServicio parametroServicio;
	
	private final String MENSAJE_AUXILIAR = "Se ha producido un error en el servicio de DEIMOs.";
	
	@Override
	public SalidaDeimosConsultaEstadoDocumentoWS consultarEstadoDocumento(
			EntradaDeimosConsultaEstadoDeudaWS entradaWS)
			throws NegocioExcepcion {
		
		try {
			SalidaDeimosConsultaEstadoDocumentoWS salidaWS  
				= deimosWS.consultarEstadoDocumento(entradaWS);
		    				
		    ResultadoProcesamiento resultado = salidaWS.getResultadoProcesamiento();
			if (resultado!=null && !Validaciones.isNullOrEmpty(resultado.getResultadoImputacion())) {
				if (TipoResultadoEnum.NOK.name().equalsIgnoreCase(resultado.getResultadoImputacion())) {
					Traza.error(getClass(), resultado.toString());
					throw new WebServiceExcepcion("Consulta Estado Deuda Deimos, Resultado NOK", null, MENSAJE_AUXILIAR);
				}
				return salidaWS; 
			} else {
				Traza.error(getClass(), ConstantesCobro.MENSAJE_CONSULTA_ESTADO_DEUDA + ", Resultado Vacio.");
				throw new WebServiceExcepcion(ConstantesCobro.MENSAJE_CONSULTA_ESTADO_DEUDA + ", Resultado Vacio", null, MENSAJE_AUXILIAR);
			}
			    			
		} catch (WebServiceFormatoMensajeExcepcion e3) {
			Traza.error(getClass(), "Error de Webservice " + ConstantesCobro.MENSAJE_CONSULTA_ESTADO_DEUDA + ": ", e3);
		    throw new WebServiceExcepcion(e3.getMessage(), e3, MENSAJE_AUXILIAR);	
							
		} catch (WebServiceExcepcion e2) {
			Traza.error(getClass(), "Error de Webservice " + ConstantesCobro.MENSAJE_CONSULTA_ESTADO_DEUDA + ": ", e2);
		    throw new WebServiceExcepcion(e2.getMessage(), e2, MENSAJE_AUXILIAR);			
		}
	}
	
	@Override
	public SalidaDeimosConsultaEstadoDocumentoWS consultarDeimos(IDatosComunesEntrada entradaDeimos, EmpresaEnum empresa)
			throws NegocioExcepcion {
		
		try {
			SalidaDeimosConsultaEstadoDocumentoWS salidaWS  
				= deimosWS.consultarDeimos(entradaDeimos, empresa);
		    				
		    ResultadoProcesamiento resultado = salidaWS.getResultadoProcesamiento();
			if (resultado!=null && !Validaciones.isNullOrEmpty(resultado.getResultadoImputacion())) {
				if (TipoResultadoEnum.ERROR.name().equalsIgnoreCase(resultado.getResultadoImputacion())) {
					Traza.error(getClass(), resultado.toString());
					throw new WebServiceExcepcion("Consulta Estado Deuda Deimos, Resultado ERROR", null, MENSAJE_AUXILIAR);
				}
				return salidaWS; 
			} else {
				Traza.error(getClass(), ConstantesCobro.MENSAJE_CONSULTA_ESTADO_DEUDA + ", Resultado Vacio.");
				throw new WebServiceExcepcion(ConstantesCobro.MENSAJE_CONSULTA_ESTADO_DEUDA + ", Resultado Vacio", null, MENSAJE_AUXILIAR);
			}
			    			
		} catch (WebServiceFormatoMensajeExcepcion e3) {
		    Traza.error(getClass(), "Error de Formato " + ConstantesCobro.MENSAJE_CONSULTA_ESTADO_DEUDA + ": ", e3);
		    throw new WebServiceExcepcion(e3.getMessage(), e3, MENSAJE_AUXILIAR);	
							
		} catch (WebServiceExcepcion e2) {
		    Traza.error(getClass(), "Error de Webservice " + ConstantesCobro.MENSAJE_CONSULTA_ESTADO_DEUDA + ": ", e2);
		    throw new WebServiceExcepcion(e2.getMessage(), e2, MENSAJE_AUXILIAR);		
		}
	}

	
	
	@Override
	public SalidaDeimosApropiacionWS apropiarDocumento(EntradaDeimosApropiacionWS entrada)
			throws NegocioExcepcion {
		
		
			
		SalidaDeimosApropiacionWS salida = null;
		
		if (SiNoEnum.SI.equals(entrada.getModoOperacion())) {
			//Si es simulacion, no hay reintentos
			salida = (SalidaDeimosApropiacionWS) invocarAlWebService(entrada, false, true);
		} else {
			salida = (SalidaDeimosApropiacionWS) invocarAlWebService(entrada, true, false);
		}
		return salida;
	}
	
	
	@Override
	public SalidaDeimosDesapropiacionWS desapropiarDocumento(EntradaDeimosDesapropiacionWS entrada) 
			throws NegocioExcepcion {
		
		//No es simulacion, por eso hay reintentos
		SalidaDeimosDesapropiacionWS salida = (SalidaDeimosDesapropiacionWS) invocarAlWebService(entrada, true, false);
		return salida;
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
		
		EntradaDeimosApropiacionWS entradaApropiacionWS = null;
		EntradaDeimosDesapropiacionWS entradaDesapropiacionWS = null;
		MensajeServicioEnum servicio = null;
		
		try {
			int wsCantidadReintentos = 1;
			if (hayReintentos) {
				wsCantidadReintentos = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.WS_CANTIDAD_REINTENTOS).toString());
			}
			
			int wsTimeout = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.WS_CANTIDAD_TIMEOUT_SEGUNDOS).toString());
			wsTimeout = wsTimeout * 1000;
			
			// Apropiacion
			if (entradaWS instanceof EntradaDeimosApropiacionWS) {
				entradaApropiacionWS = (EntradaDeimosApropiacionWS) entradaWS;
				
				servicio = MensajeServicioEnum.DEI_APROPIACION_SIM;
				if (hayReintentos) {
					servicio = MensajeServicioEnum.DEI_APROPIACION;
				}
				
				Long idOperacion = Long.valueOf(entradaApropiacionWS.getIdOperacionShiva());
				Integer idTransaccion = Integer.valueOf(entradaApropiacionWS.getTransaccion().getIdTransaccion());
				
				CobMensajeriaTransaccionDto mensajeriaDto = 
						(CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajePorIdOperacionTransaccion(
								idOperacion, idTransaccion, servicio);
				
				//Reintentos
	    		for (int i=1; i <= wsCantidadReintentos; i++) {
	    			
	    			Traza.auditoria(getClass(), "Ejecutando el intento("+i+") de Apropiacion Deimos por WS - Key: " + key);
	    			boolean incrementarContadorEnBD = (i==1)?true:false;
	    			Date fechaEnvio = new Date();
	    			
	    			try {
	    				SalidaDeimosApropiacionWS salidaApropiacionWS  
							= deimosWS.apropiarDocumento(entradaApropiacionWS);
	    				
	    				
	    				mensajeriaDto = guardarMensajeCompletado(key, 
	    						idOperacion, idTransaccion, servicio, 
	    						fechaEnvio, new Date(), mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
	    				ResultadoProcesamiento resultado = salidaApropiacionWS.getResultadoProcesamiento();
						if (resultado!=null && !Validaciones.isNullOrEmpty(resultado.getResultadoImputacion())) {
							return salidaApropiacionWS; 
						} else {
							throw new WebServiceExcepcion("Apropiacion, Resultado Vacio");
						}
		    			
	    			} catch (WebServiceFormatoMensajeExcepcion e3) {
	    				Traza.error(getClass(), "Error de Webservice Apropiacion: ", e3);

	    				mensajeriaDto = guardarMensajeRecibidoConError(key,
	    						idOperacion, idTransaccion, servicio, 
	    						e3.getMessage(), fechaEnvio, new Date(), mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
						return null;
						
	    			} catch (WebServiceExcepcion e2) {
	    				Traza.error(getClass(), "Error de Webservice Apropiacion: ", e2);
	    				
	    				mensajeriaDto = guardarMensajePendiente(key, 
	    						idOperacion, idTransaccion, servicio, 
	    						fechaEnvio, null, mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
	    				if (i < wsCantidadReintentos) {
		    				try {
		    					Traza.auditoria(getClass(), "Nuevo reintento("+ (i+1) +") de Apropiacion Deimos por WS - En Espera - Key: " + key);
		    					Thread.sleep(wsTimeout);
			    			} catch (InterruptedException eInt) {}
	    				} 
	    			}	
	    		}
	    		//Fin-reintentos
	    		
	    		return null;
			} 
			
			//Desapropiacion
			if (entradaWS instanceof EntradaDeimosDesapropiacionWS) {
				entradaDesapropiacionWS = (EntradaDeimosDesapropiacionWS) entradaWS;
				
				Long idOperacion = new Long(entradaDesapropiacionWS.getIdOperacionShiva().toString());
				
				CobMensajeriaTransaccionDto mensajeriaDto = 
						(CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajePorIdOperacion(
								idOperacion, MensajeServicioEnum.DEI_DESAPROPIACION);
				
				//Reintentos
	    		for (int i=1; i <= wsCantidadReintentos; i++) {
	    			
	    			Traza.auditoria(getClass(), "Ejecutando el intento ("+i+") de Desapropiacion Deimos por WS - Key: " + key);
	    			boolean incrementarContadorEnBD = (i==1)?true:false;
	    			Date fechaEnvio = new Date();
	    			
	    			try {
	    				SalidaDeimosDesapropiacionWS salidaDesapropiacionWS  
							= deimosWS.desapropiarDocumento(entradaDesapropiacionWS);
	    				
	    				mensajeriaDto = guardarMensajeCompletado(key, 
	    						idOperacion, null, MensajeServicioEnum.DEI_DESAPROPIACION, 
	    						fechaEnvio, new Date(), mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
	    				ResultadoProcesamiento resultado = salidaDesapropiacionWS.getResultadoProcesamiento();
						if (resultado!=null && !Validaciones.isNullOrEmpty(resultado.getResultadoImputacion())) {
							return salidaDesapropiacionWS; 
						} else {
							throw new WebServiceExcepcion("Desapropiacion, Resultado Vacio");
						}
		    			
	    			} catch (WebServiceFormatoMensajeExcepcion e3) {
	    				Traza.error(getClass(), "Error de Webservice Mensaje Desapropiacion: ", e3);
	    				
	    				mensajeriaDto = guardarMensajeRecibidoConError(key,
	    						idOperacion, null, MensajeServicioEnum.DEI_DESAPROPIACION, 
	    						e3.getMessage(), fechaEnvio, new Date(), mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
						return null;
						
	    			} catch (WebServiceExcepcion e2) {
	    				Traza.error(getClass(), "Error de Webservice Desapropiacion", e2);
	    				
	    				mensajeriaDto = guardarMensajePendiente(key, 
	    						idOperacion, null, MensajeServicioEnum.DEI_DESAPROPIACION, 
	    						fechaEnvio, null, mensajeriaDto, 
	    						incrementarContadorEnBD, esTransaccionPropia);
	    				
	    				if (i < wsCantidadReintentos) {
		    				try {
		    					Traza.auditoria(getClass(), "Nuevo reintento("+ (i+1) +") de Desapropiacion Deimos por WS - En Espera - Key: " + key);
		    					Thread.sleep(wsTimeout);
			    			} catch (InterruptedException eInt) {}
	    				} 
	    			}	
	    		}
	    		//Fin-reintentos
	    		
	    		return null;
			}
			
		} catch (Exception e) {
			Traza.error(getClass(), "Se ha producido el error al invocar al webService de Deimos para la operación: " + key, e);
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
	private CobMensajeriaTransaccionDto guardarMensajePendiente(String key, 
			Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio, 
			Date fechaEnvio, Date fechaRecepcion, CobMensajeriaTransaccionDto dto, 
			boolean incrementarContador, boolean esTransaccionPropia) throws NegocioExcepcion {
		
		MensajeEstadoEnum estado = MensajeEstadoEnum.PENDIENTE;
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
		if (entradaWS instanceof EntradaDeimosApropiacionWS) {
			EntradaDeimosApropiacionWS entradaApropiacionWS = (EntradaDeimosApropiacionWS) entradaWS;
			
			String idOperacion = Utilidad.rellenarCerosIzquierda(entradaApropiacionWS.getIdOperacionShiva().toString(), 7);
			String numeroTransaccion = Utilidad.rellenarCerosIzquierda(entradaApropiacionWS.getTransaccion().getIdSecuencia().toString(), 5);
			
			return idOperacion+"."+numeroTransaccion;
		} 
		
		if (entradaWS instanceof EntradaDeimosDesapropiacionWS) {
			EntradaDeimosDesapropiacionWS entradaConfirmacionWS = (EntradaDeimosDesapropiacionWS) entradaWS;
			
			String idOperacion = entradaConfirmacionWS.getIdOperacionShiva().toString();
			return idOperacion + ".";
		}
		
		throw new NegocioExcepcion("Error de tipo de EntradaWS: " + entradaWS+ " no  encontrada");
	}
	
	
	/*******************************************************************************************************************************
	 * Getters & Setters
	 */
	public DeimosWS getDeimosWS() {
		return deimosWS;
	}

	public void setDeimosWS(DeimosWS deimosWS) {
		this.deimosWS = deimosWS;
	}
	
}