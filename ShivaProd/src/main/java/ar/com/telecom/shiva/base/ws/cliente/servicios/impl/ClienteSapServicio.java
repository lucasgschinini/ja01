package ar.com.telecom.shiva.base.ws.cliente.servicios.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceFormatoMensajeExcepcion;
import ar.com.telecom.shiva.base.soa.singleton.SoapMensajesSingleton;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.SapRegistrarCompensacionWS;
import ar.com.telecom.shiva.base.ws.cliente.SapS4ConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.SapVerificacionPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapRegistrarCompensacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapVerificacionPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapRegistrarCompensacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapVerificacionPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.ResultadoInvocacion;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSapServicio;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class ClienteSapServicio implements IClienteSapServicio {
	
	@Autowired
	SapRegistrarCompensacionWS sapRegistrarCompensacionWS;
	
	@Autowired
	SapVerificacionPartidasWS sapVerificacionPartidasWS;
	
	@Autowired
	IMensajeriaTransaccionServicio mensajeriaTransaccionServicio;
	
	@Autowired
	IParametroServicio parametroServicio;
	
	@Autowired
	SapS4ConsultaProveedoresWS sapS4ConsultaProveedoresWS;
	
	
	@Override
	public SalidaSapVerificacionPartidasWS verificarPartidas(EntradaSapVerificacionPartidasWS entrada) throws NegocioExcepcion {
	
		// El servicio de verificacion de partidas de SAP es exclusivo para simular
		SalidaSapVerificacionPartidasWS salida = (SalidaSapVerificacionPartidasWS) invocarAlWebServiceVerificacionPartidas(entrada, false, true);
		return salida;
	}
	
	/**
	 * Invoco al webservice de verificacion de partidas de SAP
	 * @param entradaWS
	 * @return
	 * @throws NegocioExcepcion
	 */
	private SalidaWS invocarAlWebServiceVerificacionPartidas(EntradaWS entradaWS, boolean hayReintentos, boolean esTransaccionPropia) throws NegocioExcepcion {
		String key = obtenerKey(entradaWS);
		
		EntradaSapVerificacionPartidasWS entradaSapVerificacionPartidas = (EntradaSapVerificacionPartidasWS) entradaWS;
		
		try {
			Long idOperacion = Long.valueOf(entradaSapVerificacionPartidas.getIdOperacionShiva());
			
			Traza.auditoria(getClass(), "Ejecutando el intento (1) de Verificar Partidas Sap por WS - Key: " + key);
			boolean incrementarContadorEnBD = false; //(i==1)?true:false;
			Date fechaEnvio = new Date();
			
			try {
				SalidaSapVerificacionPartidasWS salidaSapVerificacionPartidas = sapVerificacionPartidasWS.verificarPartidas(entradaSapVerificacionPartidas);
				
				guardarMensajeCompletado(key, 
										idOperacion, null, MensajeServicioEnum.SAP_VERIFICAR_PARTIDAS_SIM, 
										fechaEnvio, new Date(), null, 
										incrementarContadorEnBD, esTransaccionPropia, false);
				
				ResultadoInvocacion resultado = salidaSapVerificacionPartidas.getResultadoInvocacion();
				if (resultado != null && !Validaciones.isNullOrEmpty(resultado.getResultadoInvocacion())) {
					return salidaSapVerificacionPartidas; 
				} else {
					throw new WebServiceExcepcion("Verificar Partidas Sap , Resultado Vacio");
				}
    			
			} catch (WebServiceFormatoMensajeExcepcion e3) {
				Traza.error(getClass(), "Error de Webservice Verificar Partidas Sap: ", e3);

				guardarMensajeRecibidoConError(key,
				    					idOperacion, null, MensajeServicioEnum.SAP_VERIFICAR_PARTIDAS_SIM, 
				    					e3.getMessage(), fechaEnvio, new Date(), null, 
				    					incrementarContadorEnBD, esTransaccionPropia, false);
				return null;
				
			} catch (WebServiceExcepcion e2) {
				Traza.error(getClass(), "Error de Webservice Verificar Partidas Sap: ", e2);
				
				guardarMensajePendiente(key, 
			    						idOperacion, null, MensajeServicioEnum.SAP_VERIFICAR_PARTIDAS_SIM, 
			    						fechaEnvio, null, null, 
			    						incrementarContadorEnBD, esTransaccionPropia, false);
			}	
			
		} catch (Exception e) {
			Traza.error(getClass(), "Se ha producido el error al invocar al webService de Verificar Partidas Sap para la operación: " + key, e);
			return null;
		}
		
		return null;
	}


	@Override
	public SalidaSapRegistrarCompensacionWS registrarCompensacion (EntradaSapRegistrarCompensacionWS entrada) throws NegocioExcepcion {
		
		// El servicio de registrar compensaciones de SAP es exclusivo para imputar
		SalidaSapRegistrarCompensacionWS salida = (SalidaSapRegistrarCompensacionWS) invocarAlWebServiceRegistrarCompensacion(entrada, true, false);
		return salida;
	}
	
	/**
	 * Invoco al webservice de verificacion de partidas de SAP usando o no la politica de reintentos
	 * @param entradaWS
	 * @return
	 * @throws NegocioExcepcion
	 */
	private SalidaWS invocarAlWebServiceRegistrarCompensacion(EntradaWS entradaWS,boolean hayReintentos  ,boolean esTransaccionPropia) throws NegocioExcepcion {
		String key = obtenerKey(entradaWS);
		
		EntradaSapRegistrarCompensacionWS entradaImputacionWS = null;
		MensajeServicioEnum servicio = null;
		
		try {
			int wsCantidadReintentos ;
			
			if (hayReintentos) {
				wsCantidadReintentos = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.WS_CANTIDAD_REINTENTOS).toString());
			}else {
				wsCantidadReintentos = 1;
			}
			
			int wsTimeout = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.WS_CANTIDAD_TIMEOUT_SEGUNDOS).toString());
			wsTimeout = wsTimeout * 1000;
			
			
			if (entradaWS instanceof EntradaSapRegistrarCompensacionWS) {
				entradaImputacionWS = (EntradaSapRegistrarCompensacionWS) entradaWS;
				
				servicio = MensajeServicioEnum.SAP_REGISTRAR_COMPENSACION;
				
				Long idOperacion = Long.valueOf(entradaImputacionWS.getIdOperacionShiva());
				
				CobMensajeriaTransaccionDto mensajeriaDto = 
						(CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajePorIdOperacion(idOperacion, servicio);
				
				//Reintentos
	    		for (int i=1; i <= wsCantidadReintentos; i++) {
	    			
	    			Traza.auditoria(getClass(), "Ejecutando el intento("+i+") de Imputacion Sap por WS - Key: " + key);
	    			boolean incrementarContadorEnBD = (i==1)?true:false;
	    			Date fechaEnvio = new Date();
	    			
	    			try {
	    				SalidaSapRegistrarCompensacionWS salidaImputacionWS = sapRegistrarCompensacionWS.imputar(entradaImputacionWS);
	    				
	    				mensajeriaDto = guardarMensajeCompletado(key, 
	    														idOperacion, null, servicio, 
	    														fechaEnvio, new Date(), mensajeriaDto, 
	    														incrementarContadorEnBD, esTransaccionPropia, false);
	    				
	    				ResultadoInvocacion resultado = salidaImputacionWS.getResultadoInvocacion();
						if (resultado!=null && !Validaciones.isNullOrEmpty(resultado.getResultadoInvocacion())) {
							return salidaImputacionWS; 
						} else {
							throw new WebServiceExcepcion("Solicitud Imputacíon Sap , Resultado Vacio");
						}
		    			
	    			} catch (WebServiceFormatoMensajeExcepcion e3) {
	    				Traza.error(getClass(), "Error de Webservice Apropiacion: ", e3);

	    				mensajeriaDto = guardarMensajeRecibidoConError(key,
									    						idOperacion, null, servicio, 
									    						e3.getMessage(), fechaEnvio, new Date(), mensajeriaDto, 
									    						incrementarContadorEnBD, esTransaccionPropia, false);
						return null;
						
	    			} catch (WebServiceExcepcion e2) {
	    				Traza.error(getClass(), "Error de Webservice Apropiacion: ", e2);
	    				
	    				mensajeriaDto = guardarMensajePendiente(key, 
									    						idOperacion, null, servicio, 
									    						fechaEnvio, null, mensajeriaDto, 
									    						incrementarContadorEnBD, esTransaccionPropia, false);
	    				
	    				if (i < wsCantidadReintentos) {
		    				try {
		    					Traza.auditoria(getClass(), "Nuevo reintento("+ (i + 1) +") de Imputacíon Sap por WS - En Espera - Key: " + key);
		    					Thread.sleep(wsTimeout);
			    			} catch (InterruptedException eInt) {}
	    				} 
	    			}	
	    		}
	    		
	    		return null;
			} 
			
		} catch (Exception e) {
			Traza.error(getClass(), "Se ha producido el error al invocar al webService de Sap para la operación: " + key, e);
			return null;
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param entrada
	 * @param idOperacion
	 * @param hayReintentos
	 * @param esTransaccionPropia
	 * @param servicio
	 * @return
	 */
	public SalidaSapConsultaProveedoresWS consultarProveedoresParaSimulacion(EntradaSapConsultaProveedoresWS entrada, Long idOperacion) {

		String key = Utilidad.rellenarCerosIzquierda(idOperacion.toString(), 7);
		
		try {
			Traza.auditoria(getClass(), "Ejecutando el intento (1) de Consulta de Proveedores Sap por WS : " + key);
			
			int wsTimeout = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.WS_CANTIDAD_TIMEOUT_SEGUNDOS).toString());
			wsTimeout = wsTimeout * 1000;
			
			Traza.auditoria(getClass(), "Ejecutando el intento(1) de Consulta de Proveedores por WS - Key: " + key);
			
			try {
				SalidaSapConsultaProveedoresWS salida = sapS4ConsultaProveedoresWS.consultarProveedores(entrada);
				
				ResultadoInvocacion resultado = salida.getResultadoInvocacion();
				if (resultado != null && !Validaciones.isNullOrEmpty(resultado.getResultadoInvocacion())) {
					return salida; 
				} else {
					throw new WebServiceExcepcion("Verificar Proveedores Sap , Resultado Vacio");
				}
    			
			} catch (WebServiceFormatoMensajeExcepcion e3) {
				Traza.error(getClass(), "Error de Webservice Apropiacion: ", e3);
				return null;
				
			} catch (WebServiceExcepcion e2) {
				Traza.error(getClass(), "Error de Webservice Apropiacion: ", e2);
			}	
			
		} catch (Exception e) {
			Traza.error(getClass(), "Se ha producido el error al invocar al webService de Verificar Partidas Sap para la operación: " + key, e);
			return null;
		}
		return null;
	}	
	
	/**
	 * 
	 * @param entrada
	 * @param idOperacion
	 * @param hayReintentos
	 * @param esTransaccionPropia
	 * @param servicio
	 * @return
	 */
	public SalidaSapConsultaProveedoresWS consultarProveedoresParaImputacion(EntradaSapConsultaProveedoresWS entrada, Long idOperacion) {

		String key = Utilidad.rellenarCerosIzquierda(idOperacion.toString(), 7);
		
		try {
			Traza.auditoria(getClass(), "Ejecutando el intento (1) de Consulta de Proveedores Sap por WS : " + key);
			
			int wsCantidadReintentos = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.WS_CANTIDAD_REINTENTOS).toString());
			
			int wsTimeout = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.WS_CANTIDAD_TIMEOUT_SEGUNDOS).toString());
			wsTimeout = wsTimeout * 1000;
			
			CobMensajeriaTransaccionDto mensajeriaDto = 
					(CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajePorIdOperacion(idOperacion, MensajeServicioEnum.SAP_CONSULTAR_PROVEEDOR);
			
			//Reintentos
    		for (int i=1; i <= wsCantidadReintentos; i++) {
    			
    			Traza.auditoria(getClass(), "Ejecutando el intento("+i+") de Imputacion Sap por WS - Key: " + key);
    			boolean incrementarContadorEnBD = (i==1)?true:false;
    			Date fechaEnvio = new Date();
    			
    			try {
    				SalidaSapConsultaProveedoresWS salida = sapS4ConsultaProveedoresWS.consultarProveedores(entrada);
    				
    				mensajeriaDto = guardarMensajeCompletado(key, 
    														idOperacion, null, MensajeServicioEnum.SAP_CONSULTAR_PROVEEDOR, 
    														fechaEnvio, new Date(), mensajeriaDto, 
    														incrementarContadorEnBD, false, true);
    				
    				ResultadoInvocacion resultado = salida.getResultadoInvocacion();
    				if (resultado != null && !Validaciones.isNullOrEmpty(resultado.getResultadoInvocacion())) {
    					return salida; 
    				} else {
    					throw new WebServiceExcepcion("Verificar Proveedores Sap , Resultado Vacio");
    				}
	    			
    			} catch (WebServiceFormatoMensajeExcepcion e3) {
    				Traza.error(getClass(), "Error de Webservice Apropiacion: ", e3);

    				mensajeriaDto = guardarMensajeRecibidoConError(key,
								    						idOperacion, null, MensajeServicioEnum.SAP_CONSULTAR_PROVEEDOR, 
								    						e3.getMessage(), fechaEnvio, new Date(), mensajeriaDto, 
								    						incrementarContadorEnBD, false, true);
					return null;
					
    			} catch (WebServiceExcepcion e2) {
    				Traza.error(getClass(), "Error de Webservice Apropiacion: ", e2);
    				
    				mensajeriaDto = guardarMensajePendiente(key, 
								    						idOperacion, null, MensajeServicioEnum.SAP_CONSULTAR_PROVEEDOR, 
								    						fechaEnvio, null, mensajeriaDto, 
								    						incrementarContadorEnBD, false, true);
    				
    				if (i < wsCantidadReintentos) {
	    				try {
	    					Traza.auditoria(getClass(), "Nuevo reintento("+ (i + 1) +") de Imputacíon Sap por WS - En Espera - Key: " + key);
	    					Thread.sleep(wsTimeout);
		    			} catch (InterruptedException eInt) {}
    				} 
    			}	
    		}	
			
		} catch (Exception e) {
			Traza.error(getClass(), "Se ha producido el error al invocar al webService de Verificar Partidas Sap para la operación: " + key, e);
			return null;
		}
		return null;
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
	private CobMensajeriaTransaccionDto guardarMensajeCompletado(String key, 
			Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio,
			Date fechaEnvio, Date fechaRecepcion,  
			CobMensajeriaTransaccionDto dto, boolean incrementarContador, 
			boolean esTransaccionPropia, boolean tratarXmlEntradaSalidaVacios) throws NegocioExcepcion {
		
		MensajeEstadoEnum estado = MensajeEstadoEnum.COMPLETADO;
		String msgSalida = null;
		String msgEntrada = null;
		
		if (!tratarXmlEntradaSalidaVacios) {
			msgSalida = SoapMensajesSingleton.getMensajeSalida(key);
			msgEntrada = SoapMensajesSingleton.getMensajeEntrada(key);
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
			boolean incrementarContador, boolean esTransaccionPropia, boolean tratarXmlEntradaSalidaVacios) throws NegocioExcepcion {
		
		MensajeEstadoEnum estado = MensajeEstadoEnum.PENDIENTE;
		String msgSalida = null;
		String msgEntrada = null;
		
		if (!tratarXmlEntradaSalidaVacios) {
			msgSalida = SoapMensajesSingleton.getMensajeSalida(key);
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
			boolean esTransaccionPropia, boolean tratarXmlEntradaSalidaVacios) throws NegocioExcepcion {
		
		MensajeEstadoEnum estado = MensajeEstadoEnum.REC_ERROR;
		String msgSalida = null;
		String msgEntrada = null;

		if (!tratarXmlEntradaSalidaVacios) {
			msgSalida = SoapMensajesSingleton.getMensajeSalida(key);
			msgEntrada = SoapMensajesSingleton.getMensajeEntrada(key);

			if (Validaciones.isNullOrEmpty(msgEntrada)) {
				msgEntrada = descripcionError;
			}
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
	
//	/**
//	 * Guardo la transaccion completada en la base de datos
//	 * @param mensajeriaDto
//	 * @param key
//	 * @param idTransaccion
//	 * @param idOperacion
//	 * @param servicio
//	 * @throws NegocioExcepcion
//	 */
//	private CobMensajeriaTransaccionDto guardarMensajeCompletadoSinXMLsEntradaSalida(String key, 
//			Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio,
//			Date fechaEnvio, Date fechaRecepcion,  
//			CobMensajeriaTransaccionDto dto, boolean incrementarContador, 
//			boolean esTransaccionPropia) throws NegocioExcepcion {
//		
//		MensajeEstadoEnum estado = MensajeEstadoEnum.COMPLETADO;
//		String msgSalida = null;
//		String msgEntrada = null;
//				
//		if (dto == null) {
//			CobMensajeriaTransaccionDto mensajeriaDto = new CobMensajeriaTransaccionDto();
//			mensajeriaDto.setIdOperacion(idOperacion);
//			mensajeriaDto.setIdTransaccion(idTransaccion);
//			mensajeriaDto.setServicio(servicio);
//			mensajeriaDto.setEstado(estado);
//			mensajeriaDto.setFechaAlta(fechaEnvio);
//			mensajeriaDto.setFechaEnvio(fechaEnvio);
//			mensajeriaDto.setFechaRecepcion(fechaRecepcion);
//			
//			mensajeriaDto.setCantReintentos(new Integer(0));
//			mensajeriaDto.setMensajeEnviado(msgSalida);
//			mensajeriaDto.setRespuestaRecibida(msgEntrada);
//			
//			//Creo un nuevo mensaje
//			if (esTransaccionPropia) {
//				mensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.crearTransaccionPropia(mensajeriaDto);
//			} else {
//				mensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.crearTransaccionPropagada(mensajeriaDto);
//			}
//			
//			return mensajeriaDto;
//		} else {
//			
//			dto.setEstado(estado);
//			dto.setFechaEnvio(fechaEnvio);
//			dto.setFechaRecepcion(fechaRecepcion);
//			dto.setMensajeEnviado(msgSalida);
//			dto.setRespuestaRecibida(msgEntrada);
//			
//			//Actualizo el mensaje
//			actualizarMensaje(dto, incrementarContador);
//			
//			return dto;
//		}
//	}
	
//	/**
//	 * Guardo la transaccion completada en la base de datos
//	 * @param mensajeriaDto
//	 * @param key
//	 * @param idTransaccion
//	 * @param idOperacion
//	 * @param servicio
//	 * @throws NegocioExcepcion
//	 */
//	private CobMensajeriaTransaccionDto guardarMensajePendienteSinXMLsEntradaSalida(String key, 
//			Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio, 
//			Date fechaEnvio, Date fechaRecepcion, CobMensajeriaTransaccionDto dto, 
//			boolean incrementarContador, boolean esTransaccionPropia) throws NegocioExcepcion {
//		
//		MensajeEstadoEnum estado = MensajeEstadoEnum.PENDIENTE;
//		String msgSalida = null;
//		String msgEntrada = null;
//				
//		if (dto == null) {
//			CobMensajeriaTransaccionDto mensajeriaDto = new CobMensajeriaTransaccionDto();
//			mensajeriaDto.setIdOperacion(idOperacion);
//			mensajeriaDto.setIdTransaccion(idTransaccion);
//			mensajeriaDto.setServicio(servicio);
//			mensajeriaDto.setEstado(estado);
//			mensajeriaDto.setFechaAlta(fechaEnvio);
//			mensajeriaDto.setFechaEnvio(fechaEnvio);
//			mensajeriaDto.setFechaRecepcion(fechaRecepcion);
//			
//			mensajeriaDto.setCantReintentos(new Integer(0));
//			mensajeriaDto.setMensajeEnviado(msgSalida);
//			mensajeriaDto.setRespuestaRecibida(msgEntrada);
//			
//			//Creo un nuevo mensaje
//			if (esTransaccionPropia) {
//				mensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.crearTransaccionPropia(mensajeriaDto);
//			} else {
//				mensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.crearTransaccionPropagada(mensajeriaDto);
//			}
//			
//			return mensajeriaDto;
//		} else {
//			dto.setEstado(estado);
//			dto.setFechaEnvio(fechaEnvio);
//			dto.setFechaRecepcion(fechaRecepcion);
//			dto.setMensajeEnviado(msgSalida);
//			dto.setRespuestaRecibida(msgEntrada);
//			
//			//Actualizo el mensaje
//			actualizarMensaje(dto, incrementarContador);
//			
//			return dto;
//		}
//	}
	
	
//	/**
//	 * Guardo el mensaje recibido con error
//	 * @param key
//	 * @param idOperacion
//	 * @param fechaEnvio
//	 * @param fechaRecepcion
//	 * @param dto
//	 * @return
//	 * @throws NegocioExcepcion
//	 */
//	private CobMensajeriaTransaccionDto guardarMensajeRecibidoConErrorSinXMLsEntradaSalida(String key,
//			Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio,
//			String descripcionError, Date fechaEnvio, Date fechaRecepcion, 
//			CobMensajeriaTransaccionDto dto, boolean incrementarContador, 
//			boolean esTransaccionPropia) throws NegocioExcepcion {
//		
//		MensajeEstadoEnum estado = MensajeEstadoEnum.REC_ERROR;
//		String msgSalida = null;
//		String msgEntrada = null;
//		if (Validaciones.isNullOrEmpty(msgEntrada)) {
//			msgEntrada = descripcionError;
//		}
//		
//		if (dto == null) {
//			CobMensajeriaTransaccionDto mensajeriaDto = new CobMensajeriaTransaccionDto();
//			mensajeriaDto.setIdOperacion(idOperacion);
//			mensajeriaDto.setIdTransaccion(idTransaccion);
//			mensajeriaDto.setServicio(servicio);
//			mensajeriaDto.setEstado(estado);
//			mensajeriaDto.setFechaAlta(fechaEnvio);
//			mensajeriaDto.setFechaEnvio(fechaEnvio);
//			mensajeriaDto.setFechaRecepcion(fechaRecepcion);
//			
//			mensajeriaDto.setCantReintentos(new Integer(0));
//			mensajeriaDto.setMensajeEnviado(msgSalida);
//			mensajeriaDto.setRespuestaRecibida(msgEntrada);
//			
//			//Creo un nuevo mensaje
//			if (esTransaccionPropia) {
//				mensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.crearTransaccionPropia(mensajeriaDto);
//			} else {
//				mensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.crearTransaccionPropagada(mensajeriaDto);
//			}
//			
//			return mensajeriaDto;
//		} else {
//			
//			dto.setEstado(estado);
//			dto.setFechaEnvio(fechaEnvio);
//			dto.setFechaRecepcion(fechaRecepcion);
//			dto.setMensajeEnviado(msgSalida);
//			dto.setRespuestaRecibida(msgEntrada);
//			
//			//Actualizo el mensaje
//			actualizarMensaje(dto, incrementarContador);
//			
//			return dto;
//		}
//	}	

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

		if (entradaWS instanceof EntradaSapVerificacionPartidasWS) {
			EntradaSapVerificacionPartidasWS entradaApropiacionWS = (EntradaSapVerificacionPartidasWS) entradaWS;
			
			String idOperacion = Utilidad.rellenarCerosIzquierda(entradaApropiacionWS.getIdOperacionShiva().toString(), 7);
			return idOperacion;
		} 
		if (entradaWS instanceof EntradaSapRegistrarCompensacionWS) {
			EntradaSapRegistrarCompensacionWS entradaApropiacionWS = (EntradaSapRegistrarCompensacionWS) entradaWS;
			
			String idOperacion = Utilidad.rellenarCerosIzquierda(entradaApropiacionWS.getIdOperacionShiva().toString(), 7);
			return idOperacion;
		} 
		
		throw new NegocioExcepcion("Error de tipo de EntradaWS: " + entradaWS + " no  encontrada");
	}
	




	/**
	 * @return the mensajeriaTransaccionServicio
	 */
	public IMensajeriaTransaccionServicio getMensajeriaTransaccionServicio() {
		return mensajeriaTransaccionServicio;
	}

	/**
	 * @param mensajeriaTransaccionServicio the mensajeriaTransaccionServicio to set
	 */
	public void setMensajeriaTransaccionServicio(
			IMensajeriaTransaccionServicio mensajeriaTransaccionServicio) {
		this.mensajeriaTransaccionServicio = mensajeriaTransaccionServicio;
	}

	/**
	 * @return the parametroServicio
	 */
	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	/**
	 * @param parametroServicio the parametroServicio to set
	 */
	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}
}
