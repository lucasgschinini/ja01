package ar.com.telecom.shiva.base.jms.servicios.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.OkNokEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.jms.IControlJms;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicApropiacionDesapropiacionConfirmacionEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaCreditoEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaDeudaEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicGeneracionCargoEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicDescobroEntrada;
import ar.com.telecom.shiva.base.jms.datos.salida.MicConsultaCreditoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicConsultaDeudaSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaADCSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaGeneracionCargoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaDescobroSalida;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsSyncServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionDescobroDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosDto;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;

public class MicJmsSyncServicioImpl extends JmsServicio implements IMicJmsSyncServicio {

	@Autowired
	IControlJms micConsultaDeudaControlMQ;
	
	@Autowired 
	MapeadorJMS micConsultaDeudaMapeoJMS;
	
	@Autowired
	IControlJms micConsultaCreditoControlMQ;
	
	@Autowired 
	MapeadorJMS micConsultaCreditoMapeoJMS;

	@Autowired
	IControlJms micSimulacionCobrosControlMQ;
	
	@Autowired
	IMensajeriaTransaccionServicio mensajeriaTransaccionServicio;
	
	private final String MENSAJE_APROPIACION_DEUDA = "Apropiar Deuda";
	private final String MENSAJE_APROPIACION_MEDIO_PAGO = "Apropiar Medio de Pago";
	private final String MENSAJE_APROPIACION_DEUDA_MEDIO_PAGO = "Apropiar Deuda y Medio de Pago";
	
	private final String MENSAJE_CARGO_DEBITO = "Simulacion Generacion Cargo Debito";
	private final String MENSAJE_CARGO_CREDITO = "Simulacion Generacion Cargo Credito";
	private final String MENSAJE_SIMULACION_INTERES = "Simulacion Interes";
	private final String MENSAJE_SIMULACION_REVERSION = "Simulación Reversión";
	
	private final String MENSAJE_SIMULACION_CONTRACARGO_DEBITO = "Simulacion Generacion Contracargo Debito";
	private final String MENSAJE_SIMULACION_CONTRACARGO_CREDITO = "Simulacion Generacion Contracargo Credito";
	private final String MENSAJE_SIMULACION_CONTRACARGO_INTERES = "Simulacion Contracargo Interes";
	
	@Override
	public MicConsultaDeudaSalida consultarDeuda(MicConsultaDeudaEntrada jms) throws JmsExcepcion {
		String mensajeAuxiliar = "Se ha producido un error en el servicio MIC.";
		
		try {
			// Serializar el objeto a mensaje
			String mensajeAEnviar = micConsultaDeudaMapeoJMS.serializar(jms, true);
				
			//Envio datos
			String mensajeRecibido = 
					micConsultaDeudaControlMQ.consultar(mensajeAEnviar,  
							Propiedades.SHIVA_PROPIEDADES.getString(
									"mq.mic.consulta.deuda.contrato"));
			
			if (mensajeRecibido != null) {
				MicConsultaDeudaSalida salida = 
						(MicConsultaDeudaSalida) micConsultaDeudaMapeoJMS.deserializar(mensajeRecibido, false, true);
				
				if (!OkNokEnum.OK.equals(salida.getResultadoConsulta().getResultadoConsulta())) {
					salida.getControlPaginado().setCantidadRegistrosRetornados(new Long ("0"));
					salida.getControlPaginado().setCantidadRegistrosTotales(new Long ("0"));
					
					if (!OkNokEnum.NOK.equals(salida.getResultadoConsulta().getResultadoConsulta())) {
						String mensaje = salida.getResultadoConsulta().getDescripcionError();
						Traza.advertencia(getClass(), "Error de Servicio: Consulta de Deuda MIC: " + mensaje);
						mensajeAuxiliar = "Se ha producido un error de servicio MIC";
						throw new JmsExcepcion(mensaje, mensajeAuxiliar);
					}
				}
				
				return salida;
				
			} else {
				String mensaje = "Se ha retornado el mensaje vacio.";
				Traza.error(getClass(), "Error de Servicio: Consulta de Deuda MIC: " + mensaje);
				throw new JmsExcepcion(mensaje, mensajeAuxiliar);
			}
			
		} catch (Exception e) {
			Traza.error(getClass(), "Error de servicio: Consulta de Deuda MIC: " + e.getMessage(), e);
			throw new JmsExcepcion(e.getMessage(), e, mensajeAuxiliar);
		}
	}

	@Override
	public MicConsultaCreditoSalida consultarCredito(
			MicConsultaCreditoEntrada jms) throws JmsExcepcion {
		String mensajeAuxiliar = "Se ha producido un error en el servicio MIC.";
		
		try {
			
			// Serializar el objeto a mensaje
			String mensajeAEnviar = micConsultaCreditoMapeoJMS.serializar(jms, true);
				
			//Envio datos
			String mensajeRecibido = 
					micConsultaCreditoControlMQ.consultar(mensajeAEnviar,  
							Propiedades.SHIVA_PROPIEDADES.getString(
									"mq.mic.consulta.credito.contrato"));
			
			if (mensajeRecibido != null) {
				MicConsultaCreditoSalida salida = 
						(MicConsultaCreditoSalida) micConsultaCreditoMapeoJMS.deserializar(mensajeRecibido, false, true);
			
				if (!OkNokEnum.OK.equals(salida.getResultadoConsulta().getResultadoConsulta())) {
					salida.getControlPaginado().setCantidadRegistrosRetornados(new Long ("0"));
					salida.getControlPaginado().setCantidadRegistrosTotales(new Long ("0"));
					
					if (!OkNokEnum.NOK.equals(salida.getResultadoConsulta().getResultadoConsulta())) {
						String mensaje = salida.getResultadoConsulta().getDescripcionError();
						Traza.advertencia(getClass(), "Error de Servicio: Consulta de Credito MIC: " + mensaje);
						mensajeAuxiliar = "Se ha producido un error de servicio MIC";
						throw new JmsExcepcion(mensaje, mensajeAuxiliar);
					}
				}
				
				return salida;
				
			} else {
				String mensaje = "Se ha retornado el mensaje vacio.";
				Traza.error(getClass(), "Error de Servicio: Consulta de creditos MIC: " + mensaje);
				throw new JmsExcepcion(mensaje, mensajeAuxiliar);
			}
			
		} catch (Exception e) {
			Traza.error(getClass(), "Error de servicio: Consulta de creditos MIC: " + e.getMessage(), e);
			throw new JmsExcepcion(e.getMessage(), e, mensajeAuxiliar);
		}
	}
	
	
	/***********************************************************************************************************
	 * Simulaciones de apropiaciones
	 **********************************************************************************************************/
	@Override
	public MicRespuestaADCSalida simularApropiarDeuda(MicTransaccionADCDto dto) throws JmsExcepcion {
		
		try {
			MicApropiacionDesapropiacionConfirmacionEntrada jms = mapeadorImputacionDtoJms(dto);
			
			// Se setean los campos especificos de la operacion
			jms.setTipoInvocacion(TipoInvocacionEnum.$01);
			jms.setTipoMensaje(MensajeServicioEnum.MIC_APROP_DEUDA_SIM);
			
			return (MicRespuestaADCSalida) enviarMensajeSimulacion(jms);
			
		} catch (Exception e) {
			Traza.error(getClass(), "Error de servicio: " + MENSAJE_APROPIACION_DEUDA + " MIC: " + e.getMessage(), e);
			String mensajeAuxiliar = "Se ha producido un error en el servicio MIC.";
			throw new JmsExcepcion(e.getMessage(), e, mensajeAuxiliar);
		}
	}
	
	@Override
	public MicRespuestaADCSalida simularApropiarMedioPago(MicTransaccionADCDto dto) throws JmsExcepcion {
		
		try {
			MicApropiacionDesapropiacionConfirmacionEntrada jms = mapeadorImputacionDtoJms(dto);
			
			// Se setean los campos especificos de la operacion
			jms.setTipoInvocacion(TipoInvocacionEnum.$02);
			jms.setTipoMensaje(MensajeServicioEnum.MIC_APROP_MP_SIM);
			
			jms.getDetalleFactura().setFechaValor(dto.getDetalleFactura().getFechaValor());
			
			return (MicRespuestaADCSalida) enviarMensajeSimulacion(jms);
			
		} catch (Exception e) {
			Traza.error(getClass(), "Error de servicio: " + MENSAJE_APROPIACION_MEDIO_PAGO + " MIC: " + e.getMessage(), e);
			String mensajeAuxiliar = "Se ha producido un error en el servicio MIC.";
			throw new JmsExcepcion(e.getMessage(), e, mensajeAuxiliar);
		}
	}

	@Override
	public MicRespuestaADCSalida simularApropiarDeudaYMedioPago(MicTransaccionADCDto dto) throws JmsExcepcion {
		
		try {
			MicApropiacionDesapropiacionConfirmacionEntrada jms = mapeadorImputacionDtoJms(dto);
			
			// se setean los campos especificos de la operacion
			jms.setTipoInvocacion(TipoInvocacionEnum.$03);
			jms.setTipoMensaje(MensajeServicioEnum.MIC_APROP_DEUDA_Y_MP_SIM);
			
			return (MicRespuestaADCSalida) enviarMensajeSimulacion(jms);
			
		} catch (Exception e) {
			Traza.error(getClass(), "Error de servicio: " + MENSAJE_APROPIACION_DEUDA_MEDIO_PAGO + " MIC: " + e.getMessage(), e);
			String mensajeAuxiliar = "Se ha producido un error en el servicio MIC.";
			throw new JmsExcepcion(e.getMessage(), e, mensajeAuxiliar);
		}
	}
	
	/***********************************************************************************************************
	 * Simulaciones de cargos
	 **********************************************************************************************************/
	@Override
	public MicRespuestaGeneracionCargoSalida simularCargoDebito(
			MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion {
		try {
			MicGeneracionCargoEntrada jms = mapeadorCargoDtoJms(dto);
			
			//Se setean los campos especificos de la operacion
			jms.setTipoInvocacion(TipoInvocacionEnum.$06);
			jms.setTipoMensaje(MensajeServicioEnum.MIC_GENERACION_CARGO_DEBITO_SIM);
			
			return (MicRespuestaGeneracionCargoSalida) enviarMensajeSimulacion(jms);
			
		} catch (Exception e) {
			Traza.error(getClass(), "Error de servicio: " + MENSAJE_CARGO_DEBITO + " MIC: " + e.getMessage(), e);
			String mensajeAuxiliar = "Se ha producido un error en el servicio MIC.";
			throw new JmsExcepcion(e.getMessage(), e, mensajeAuxiliar);
		}
	}

	@Override
	public MicRespuestaGeneracionCargoSalida simularCargoCredito(
			MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion {
		try {
			MicGeneracionCargoEntrada jms = mapeadorCargoDtoJms(dto);
			
			//Se setean los campos especificos de la operacion
			jms.setTipoInvocacion(TipoInvocacionEnum.$07);
			jms.setTipoMensaje(MensajeServicioEnum.MIC_GENERACION_CARGO_CREDITO_SIM);
			
			return (MicRespuestaGeneracionCargoSalida) enviarMensajeSimulacion(jms);
			
		} catch (Exception e) {
			Traza.error(getClass(), "Error de servicio: " + MENSAJE_CARGO_CREDITO + " MIC: " + e.getMessage(), e);
			String mensajeAuxiliar = "Se ha producido un error en el servicio MIC.";
			throw new JmsExcepcion(e.getMessage(), e, mensajeAuxiliar);
		}
	}

	@Override
	public MicRespuestaGeneracionCargoSalida simularInteres(
			MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion {
		try {
			MicGeneracionCargoEntrada jms = mapeadorCargoDtoJms(dto);
			
			//Se setean los campos especificos de la operacion
			jms.setTipoInvocacion(TipoInvocacionEnum.$08);
			jms.setTipoMensaje(MensajeServicioEnum.MIC_GENERACION_CARGO_INTERES_SIM);
			
			return (MicRespuestaGeneracionCargoSalida) enviarMensajeSimulacion(jms);
			
		} catch (Exception e) {
			Traza.error(getClass(), "Error de servicio: " + MENSAJE_SIMULACION_INTERES + " MIC: " + e.getMessage(), e);
			String mensajeAuxiliar = "Se ha producido un error en el servicio MIC.";
			throw new JmsExcepcion(e.getMessage(), e, mensajeAuxiliar);
		}
	}
	
	/***********************************************************************************************************
	 * Simulaciones de reversion de operaciones
	 **********************************************************************************************************/
	@Override
	public MicRespuestaDescobroSalida simularDescobro(MicTransaccionDescobroDto dto) throws JmsExcepcion {
		
		try {
			MicDescobroEntrada jms = mapeadorDescobroDtoJms(dto);
			
			// Se setean los campos especificos de la operacion
			jms.setTipoInvocacion(TipoInvocacionEnum.$09);
			jms.setTipoMensaje(MensajeServicioEnum.MIC_DESCOBRO_SIM);
			
			return (MicRespuestaDescobroSalida) enviarMensajeSimulacion(jms);
		} catch (Exception e) {
			Traza.error(getClass(), "Error de servicio: " + MENSAJE_SIMULACION_REVERSION + " MIC: " + e.getMessage(), e);
			String mensajeAuxiliar = "Se ha producido un error en el servicio MIC.";
			throw new JmsExcepcion(e.getMessage(), e, mensajeAuxiliar);
		}
	}
	
	@Override
	public MicRespuestaGeneracionCargoSalida simularContracargoDebito(
			MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion {
		try {
			MicGeneracionCargoEntrada jms = mapeadorCargoDtoJms(dto);
			
			//Se setean los campos especificos de la operacion
			jms.setTipoInvocacion(TipoInvocacionEnum.$06);
			
			return (MicRespuestaGeneracionCargoSalida) enviarMensajeSimulacion(jms);
			
		} catch (Exception e) {
			Traza.error(getClass(), "Error de servicio: " + MENSAJE_SIMULACION_CONTRACARGO_DEBITO + " MIC: " + e.getMessage(), e);
			String mensajeAuxiliar = "Se ha producido un error en el servicio MIC.";
			throw new JmsExcepcion(e.getMessage(), e, mensajeAuxiliar);
		}
	}

	@Override
	public MicRespuestaGeneracionCargoSalida simularContracargoCredito(
			MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion {
		try {
			MicGeneracionCargoEntrada jms = mapeadorCargoDtoJms(dto);
			
			//Se setean los campos especificos de la operacion
			jms.setTipoInvocacion(TipoInvocacionEnum.$07);
			
			return (MicRespuestaGeneracionCargoSalida) enviarMensajeSimulacion(jms);
			
		} catch (Exception e) {
			Traza.error(getClass(), "Error de servicio: " + MENSAJE_SIMULACION_CONTRACARGO_CREDITO + " MIC: " + e.getMessage(), e);
			String mensajeAuxiliar = "Se ha producido un error en el servicio MIC.";
			throw new JmsExcepcion(e.getMessage(), e, mensajeAuxiliar);
		}
	}

	@Override
	public MicRespuestaGeneracionCargoSalida simularContracargoInteres(
			MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion {
		try {
			MicGeneracionCargoEntrada jms = mapeadorCargoDtoJms(dto);
			
			//Se setean los campos especificos de la operacion
			jms.setTipoInvocacion(TipoInvocacionEnum.$08);
			
			return (MicRespuestaGeneracionCargoSalida) enviarMensajeSimulacion(jms);
			
		} catch (Exception e) {
			Traza.error(getClass(), "Error de servicio: " + MENSAJE_SIMULACION_CONTRACARGO_INTERES + " MIC: " + e.getMessage(), e);
			String mensajeAuxiliar = "Se ha producido un error en el servicio MIC.";
			throw new JmsExcepcion(e.getMessage(), e, mensajeAuxiliar);
		}
	}
	
	/**
	 * Envia un mensaje de tipo simulacion con actualizaciones de mensajerias
	 * @param jms
	 * @return
	 * @throws JmsExcepcion
	 */
	private JMS enviarMensajeSimulacion(JMS jms) throws JmsExcepcion {
		
		//Variables globales
		String mensajeAEnviar = null;
		String mensajeRecibido = null;
		
		//Variables para la mensajeria
		Long idOperacion = null;
		Integer idTransaccion = null;
		Date fechaEnvio = null;
		MensajeServicioEnum tipoMensaje = null;
		CobMensajeriaTransaccionDto mensajeriaDto = null;
		boolean modificarMensajeria = true;
		String contrato = null;
		
		// Mapeador de mensajes y buscar la mensajeria ---------------------------------------------------------------------------
		try {
			if (jms instanceof MicApropiacionDesapropiacionConfirmacionEntrada) {
				MicApropiacionDesapropiacionConfirmacionEntrada entradaADC = (MicApropiacionDesapropiacionConfirmacionEntrada) jms;
				idOperacion = entradaADC.getIdOperacion();
				idTransaccion = Integer.valueOf(entradaADC.getIdTransaccion().toString());
				
				tipoMensaje = entradaADC.getTipoMensaje();
				
				// Serializar el objeto a mensaje
				mensajeAEnviar = micADCMapeoJMS.serializar(jms, true);
				
				contrato = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.simulacion.adc.contrato");
				
				mensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajePorIdOperacionTransaccion(
						idOperacion, idTransaccion, tipoMensaje);
				
			} else if (jms instanceof MicGeneracionCargoEntrada) {
				MicGeneracionCargoEntrada entradaCargo = (MicGeneracionCargoEntrada) jms;
				idOperacion = Long.valueOf(entradaCargo.getIdOperacion().toString());
				
				if(entradaCargo.getTipoMensaje().esMensajeDescobrosCalipso()){
					idOperacion = Long.valueOf(entradaCargo.getIdOperacionDescobroMensajeria().toString());
				}
				
				idTransaccion = Integer.valueOf(entradaCargo.getIdTransaccion().toString());
				
				tipoMensaje = entradaCargo.getTipoMensaje();
				
				// Serializar el objeto a mensaje
				mensajeAEnviar = micGeneracionCargoMapeoJMS.serializar(jms, true);
				
				contrato = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.simulacion.cargo.contrato");
				
				mensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajePorIdOperacionTransaccion(
						idOperacion, idTransaccion, tipoMensaje);
				
			} else if (jms instanceof MicDescobroEntrada) {
				MicDescobroEntrada entradaDescobro = (MicDescobroEntrada) jms;
				idOperacion = Long.valueOf(entradaDescobro.getIdOperacionDescobroMensajeria().toString());
				idTransaccion = Integer.valueOf(entradaDescobro.getIdTransaccion().toString());
				
				tipoMensaje = entradaDescobro.getTipoMensaje();
				
				// Serializar el objeto a mensaje
				mensajeAEnviar = micDescobroMapeoJMS.serializar(jms, true);
				
				contrato = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.simulacion.descobro.contrato");
				
				mensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajePorIdOperacionTransaccionYEstadoMensaje(
						idOperacion, idTransaccion, tipoMensaje, MensajeEstadoEnum.ENVIADO);
				
			} else {
				throw new JmsExcepcion("Parametros de entrada incorrecta para la simulacion");
			}
			
			
			
			if (mensajeriaDto == null) {
				mensajeriaDto = new CobMensajeriaTransaccionDto();
				modificarMensajeria = false;
			}
			
		} catch (NegocioExcepcion e2) {
			//Puede haber una excepcion al serializar, al buscar un mensaje
			throw new JmsExcepcion(e2.getMessage(), e2);
		}
		//--------------------------------------------------------------------------------------------------------------------
		
		// Envio datos ---------------------------------------------------------------------------
		try {
			
			fechaEnvio = new Date();
			mensajeRecibido = micSimulacionCobrosControlMQ.consultar(mensajeAEnviar, contrato);
			
		} catch (Exception e) {
			//Cuando hay un error de comunicacion con MQ, guardamos el mensaje como pendiente
			mensajeriaDto.setIdTransaccion(idTransaccion);
	    	mensajeriaDto.setIdOperacion(idOperacion);
	    	mensajeriaDto.setServicio(tipoMensaje);
	    	mensajeriaDto.setEstado(MensajeEstadoEnum.PENDIENTE);
	    	mensajeriaDto.setFechaAlta(fechaEnvio);
	    	mensajeriaDto.setFechaEnvio(fechaEnvio);
	    	mensajeriaDto.setFechaRecepcion(null);
	    	mensajeriaDto.setCantReintentos(new Integer(0));
	    	mensajeriaDto.setMensajeEnviado(mensajeAEnviar);
	    	mensajeriaDto.setRespuestaRecibida(null);
	    	
	    	try {
				this.guardarMensajeria(mensajeriaDto, modificarMensajeria);
			} catch (NegocioExcepcion e1) {
				throw new JmsExcepcion(e1.getMessage() , e1);
			}
			
	    	throw new JmsExcepcion(e.getMessage(), e);
		}
		//--------------------------------------------------------------------------------------------------------------------
		
		// Salida y mapeo a la salida ----------------------------------------------------------------------------------------
		try {
			if (mensajeRecibido != null) {
				//Cuando recibimos la respuesta, se completa la mensajeria
				Date fechaRecepcion = new Date();
				mensajeriaDto.setIdTransaccion(idTransaccion);
		    	mensajeriaDto.setIdOperacion(idOperacion);
		    	mensajeriaDto.setServicio(tipoMensaje);
		    	mensajeriaDto.setEstado(MensajeEstadoEnum.COMPLETADO);
		    	mensajeriaDto.setFechaAlta(fechaEnvio);
		    	mensajeriaDto.setFechaEnvio(fechaEnvio);
		    	mensajeriaDto.setFechaRecepcion(fechaRecepcion);
		    	mensajeriaDto.setCantReintentos(new Integer(0));
		    	mensajeriaDto.setMensajeEnviado(mensajeAEnviar);
		    	mensajeriaDto.setRespuestaRecibida(mensajeRecibido);
		    	
		    	this.guardarMensajeria(mensajeriaDto, modificarMensajeria);
				
				JMS salida = null; 
				if (jms instanceof MicApropiacionDesapropiacionConfirmacionEntrada) {
					salida = (MicRespuestaADCSalida) micRespuestaADCMapeoJMS.deserializar(mensajeRecibido, true, true);
				}
				if (jms instanceof MicGeneracionCargoEntrada) {
					salida = (MicRespuestaGeneracionCargoSalida) micRespuestaGeneracionCargoMapeoJMS.deserializar(mensajeRecibido, true, true);
				}
				if (jms instanceof MicDescobroEntrada) {
					salida = (MicRespuestaDescobroSalida) micRespuestaDescobroMapeoJMS.deserializar(mensajeRecibido, true, true);
				}
				return salida;
				
			} else {
				//Cuando no recibimos la respuesta, aunque hayamos enviado correctamos, ponemos como NO RECIBIDO
				mensajeriaDto.setIdTransaccion(idTransaccion);
		    	mensajeriaDto.setIdOperacion(idOperacion);
		    	mensajeriaDto.setServicio(tipoMensaje);
		    	mensajeriaDto.setEstado(MensajeEstadoEnum.NORECIBIDO);
		    	mensajeriaDto.setFechaAlta(fechaEnvio);
		    	mensajeriaDto.setFechaEnvio(fechaEnvio);
		    	mensajeriaDto.setFechaRecepcion(null);
		    	mensajeriaDto.setCantReintentos(new Integer(0));
		    	mensajeriaDto.setMensajeEnviado(mensajeAEnviar);
		    	mensajeriaDto.setRespuestaRecibida(null);
		    	
		    	this.guardarMensajeria(mensajeriaDto, modificarMensajeria);
		    			    	
				String mensajeAuxiliar = "Se ha producido un error en el servicio MIC.";
				String mensaje = "Se ha retornado el mensaje vacio.";
				throw new JmsExcepcion(mensaje, mensajeAuxiliar);
			}
			
		} catch (NegocioExcepcion e1) {
			throw new JmsExcepcion(e1.getMessage(), e1);
		}
		//--------------------------------------------------------------------------------------------------------------------
	}
	
	/**
	 * Guardo los mensajes y el estado en la base de datos
	 * @param dto
	 * @return
	 * @throws NegocioExcepcion
	 */
	private void guardarMensajeria(CobMensajeriaTransaccionDto mensajeriaDto, boolean modificarMensajeria) 
			throws NegocioExcepcion {
		
		if (modificarMensajeria) {
    		mensajeriaTransaccionServicio.modificar(mensajeriaDto);
    	} else {
    		mensajeriaTransaccionServicio.crearTransaccionPropagada(mensajeriaDto);
    	} 
	}

	/************************************************************************
	 * Getters/Setters
	 ***********************************************************************/
	public IControlJms getMicConsultaDeudaControlMQ() {
		return micConsultaDeudaControlMQ;
	}

	public void setMicConsultaDeudaControlMQ(IControlJms micConsultaDeudaControlMQ) {
		this.micConsultaDeudaControlMQ = micConsultaDeudaControlMQ;
	}

	public MapeadorJMS getMicConsultaDeudaMapeoJMS() {
		return micConsultaDeudaMapeoJMS;
	}

	public void setMicConsultaDeudaMapeoJMS(MapeadorJMS micConsultaDeudaMapeoJMS) {
		this.micConsultaDeudaMapeoJMS = micConsultaDeudaMapeoJMS;
	}

	public IControlJms getMicConsultaCreditoControlMQ() {
		return micConsultaCreditoControlMQ;
	}

	public void setMicConsultaCreditoControlMQ(
			IControlJms micConsultaCreditoControlMQ) {
		this.micConsultaCreditoControlMQ = micConsultaCreditoControlMQ;
	}

	public MapeadorJMS getMicConsultaCreditoMapeoJMS() {
		return micConsultaCreditoMapeoJMS;
	}

	public void setMicConsultaCreditoMapeoJMS(MapeadorJMS micConsultaCreditoMapeoJMS) {
		this.micConsultaCreditoMapeoJMS = micConsultaCreditoMapeoJMS;
	}

	public IControlJms getMicSimulacionCobrosControlMQ() {
		return micSimulacionCobrosControlMQ;
	}

	public void setMicSimulacionCobrosControlMQ(
			IControlJms micSimulacionCobrosControlMQ) {
		this.micSimulacionCobrosControlMQ = micSimulacionCobrosControlMQ;
	}

	public IMensajeriaTransaccionServicio getMensajeriaTransaccionServicio() {
		return mensajeriaTransaccionServicio;
	}

	public void setMensajeriaTransaccionServicio(
			IMensajeriaTransaccionServicio mensajeriaTransaccionServicio) {
		this.mensajeriaTransaccionServicio = mensajeriaTransaccionServicio;
	}

}
