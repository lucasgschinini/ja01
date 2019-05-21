package ar.com.telecom.shiva.base.jms.servicios.impl.dummy;

import java.util.Collection;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.IControlJms;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicApropiacionDesapropiacionConfirmacionEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicRespuestaRecepcionEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicDetalleMedioPago;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaADCSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaRecepcionSalida;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.soa.reintentos.IReintentos;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionDescobroDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleMedioPagoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCRespuestaDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosDto;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionMicServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.negocio.servicios.ITransaccionCobroServicio;

public class MicDummyServicioImpl implements IMicJmsServicio {

	@Autowired
	IControlJms micImputacionCobrosControlMQ;
	
	@Autowired 
	MapeadorJMS micRespuestaRecepcionMapeoJMS;
	
	@Autowired 
	MapeadorJMS micADCMapeoJMS;

	@Autowired 
	MapeadorJMS micRespuestaADCMapeoJMS;
	
	@Autowired
	ICobroImputacionServicio cobroServicio;
	
	@Autowired
	private ICobroBatchSoporteImputacionMicServicio cobroBatchSoporteImputacionMicServicio;

	@Autowired
	IReintentos reintentosMensajeria;
	
	@Autowired
	IMensajeriaTransaccionServicio mensajeriaTransaccionServicio;
	
	@Autowired
	ITransaccionCobroServicio transaccionCobroServicio;
	
	/********************************************************************************
	 * Servicio de Apropiacion, Desapropiacion y Confirmacion 
	 */
	
	@Override
	public void apropiarDeuda(MicTransaccionADCDto dto)
			throws NegocioExcepcion {
		
		// Preparar el objeto a enviar
		MicApropiacionDesapropiacionConfirmacionEntrada jms = new MicApropiacionDesapropiacionConfirmacionEntrada();
		jms.setTipoInvocacion(TipoInvocacionEnum.$01);
		jms.setIdOperacion(dto.getIdOperacion());
		jms.setIdSecuencia(dto.getNumeroTransaccion());
		jms.setModoEjecucion(dto.getModoEjecucion()); 
		jms.setUsuarioCobrador(dto.getUsuarioCobrador());
		
		jms.getDetalleFactura().setTipoOperacion(dto.getDetalleFactura().getTipoOperacion());
		jms.getDetalleFactura().setReferenciaFactura(dto.getDetalleFactura().getReferenciaFactura());
		jms.getDetalleFactura().setFechaValor(dto.getDetalleFactura().getFechaValor());
		jms.getDetalleFactura().setAcuerdoFacturacionIntereses(dto.getDetalleFactura().getAcuerdoFacturacionIntereses());
		jms.getDetalleFactura().setImporte(dto.getDetalleFactura().getImporte());
		jms.getDetalleFactura().setQueHacerConLosIntereses(dto.getDetalleFactura().getQueHacerConLosIntereses());
		jms.getDetalleFactura().setImporteBonificacionIntereses(dto.getDetalleFactura().getImporteBonificacionIntereses());
		jms.getDetalleFactura().setQueHacerConLosTerceros(dto.getDetalleFactura().getQueHacerConLosTerceros());
		
		enviarADC(jms, dto.getMensajeria());
	}

	@Override
	public void apropiarMedioPago(
			MicTransaccionADCDto dto)
			throws NegocioExcepcion {
		
		// Preparar el objeto a enviar
		MicApropiacionDesapropiacionConfirmacionEntrada jms = new MicApropiacionDesapropiacionConfirmacionEntrada();
		jms.setTipoInvocacion(TipoInvocacionEnum.$02);
		jms.setIdOperacion(dto.getIdOperacion());
		jms.setIdSecuencia(dto.getNumeroTransaccion());
		jms.setModoEjecucion(dto.getModoEjecucion()); 
		jms.setUsuarioCobrador(dto.getUsuarioCobrador());
		
		jms.getDetalleFactura().setFechaValor(dto.getDetalleFactura().getFechaValor());
		
		if (Validaciones.isCollectionNotEmpty(dto.getListaMediosPago())) {
			for (MicDetalleMedioPagoDto mpDto: dto.getListaMediosPago()) {
				MicDetalleMedioPago mp = new MicDetalleMedioPago();
				mp.setTipoMedioPago(mpDto.getTipoMedioPago());
				mp.setImporte(mpDto.getImporteMedioPago());
				mp.setCuentaRemanente(mpDto.getCuentaRemanente());
				mp.setTipoRemanente(mpDto.getTipoRemanente());
				mp.setNumeroNC(mpDto.getNumeroNC());
				jms.getListaMediosPago().add(mp);
			}
		}
		
		enviarADC(jms, dto.getMensajeria());
	}

	@Override
	public void apropiarDeudaYMedioPago(
			MicTransaccionADCDto dto)
			throws NegocioExcepcion {
		
		// Preparar el objeto a enviar
		MicApropiacionDesapropiacionConfirmacionEntrada jms = new MicApropiacionDesapropiacionConfirmacionEntrada();
		jms.setTipoInvocacion(TipoInvocacionEnum.$03);
		jms.setIdOperacion(dto.getIdOperacion());
		jms.setIdSecuencia(dto.getNumeroTransaccion());
		jms.setModoEjecucion(dto.getModoEjecucion()); 
		jms.setUsuarioCobrador(dto.getUsuarioCobrador());
		
		jms.getDetalleFactura().setTipoOperacion(dto.getDetalleFactura().getTipoOperacion());
		jms.getDetalleFactura().setReferenciaFactura(dto.getDetalleFactura().getReferenciaFactura());
		jms.getDetalleFactura().setFechaValor(dto.getDetalleFactura().getFechaValor());
		jms.getDetalleFactura().setAcuerdoFacturacionIntereses(dto.getDetalleFactura().getAcuerdoFacturacionIntereses());
		jms.getDetalleFactura().setImporte(dto.getDetalleFactura().getImporte());
		jms.getDetalleFactura().setQueHacerConLosIntereses(dto.getDetalleFactura().getQueHacerConLosIntereses());
		jms.getDetalleFactura().setImporteBonificacionIntereses(dto.getDetalleFactura().getImporteBonificacionIntereses());
		jms.getDetalleFactura().setQueHacerConLosTerceros(dto.getDetalleFactura().getQueHacerConLosTerceros());
		
		if (Validaciones.isCollectionNotEmpty(dto.getListaMediosPago())) {
			for (MicDetalleMedioPagoDto mpDto: dto.getListaMediosPago()) {
				MicDetalleMedioPago mp = new MicDetalleMedioPago();
				mp.setTipoMedioPago(mpDto.getTipoMedioPago());
				mp.setImporte(mpDto.getImporteMedioPago());
				mp.setCuentaRemanente(mpDto.getCuentaRemanente());
				mp.setTipoRemanente(mpDto.getTipoRemanente());
				mp.setNumeroNC(mpDto.getNumeroNC());
				jms.getListaMediosPago().add(mp);
			}
		}
		
		enviarADC(jms, dto.getMensajeria());
	}

	@Override
	public void desapropiarOperacion(
			MicTransaccionADCDto dto)
			throws NegocioExcepcion {
		
		// Preparar el objeto a enviar
		MicApropiacionDesapropiacionConfirmacionEntrada jms = new MicApropiacionDesapropiacionConfirmacionEntrada();
		jms.setTipoInvocacion(TipoInvocacionEnum.$04);
		jms.setIdOperacion(dto.getIdOperacion());
		jms.setIdSecuencia(dto.getNumeroTransaccion());
		jms.setModoEjecucion(dto.getModoEjecucion()); 
		jms.setUsuarioCobrador(dto.getUsuarioCobrador());
		
		enviarADC(jms, dto.getMensajeria());
	}

	@Override
	public void confirmarOperacion(MicTransaccionADCDto dto)
			throws NegocioExcepcion {
		// Preparar el objeto a enviar
		MicApropiacionDesapropiacionConfirmacionEntrada jms = new MicApropiacionDesapropiacionConfirmacionEntrada();
		jms.setTipoInvocacion(TipoInvocacionEnum.$05);
		jms.setIdOperacion(dto.getIdOperacion());
		jms.setIdSecuencia(dto.getNumeroTransaccion());
		jms.setModoEjecucion(dto.getModoEjecucion()); 
		jms.setUsuarioCobrador(dto.getUsuarioCobrador());
		
		enviarADC(jms, dto.getMensajeria());
	}

	@Override
	public void generarCargo(MicTransaccionGeneracionCargosDto dto)
			throws NegocioExcepcion {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void descobrarOperacion(MicTransaccionDescobroDto dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		String a = "0";
		a.substring(0);
	}

	/**
	 * Ejecuto el envio de la info y guardo en la BD
	 * @param jms
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	private synchronized void enviarADC(JMS jms, CobMensajeriaTransaccionDto dto) throws NegocioExcepcion{
		// Serializar el objeto a mensaje
		String mensajeAEnviar = micADCMapeoJMS.serializar(jms, false);
		
		//Envio el mensaje por primera vez
		reintentosMensajeria.enviarMensaje(dto, mensajeAEnviar);
		
		notifyAll();
	}

	/**
	 * 
	 * @throws NegocioExcepcion
	 */
	@Override
	public void recibirMensajes(TipoProcesoEnum tipoProceso) throws NegocioExcepcion {
		boolean continuarBuscarMensajeMic = true;
		int contador = 0;
		while (continuarBuscarMensajeMic) {
			
			Message mensajeRecibido = micImputacionCobrosControlMQ.recibirMensaje();
			if (mensajeRecibido != null) {
				contador++;
				
				if (mensajeRecibido instanceof TextMessage) {
					procesarMensajeRecibido(mensajeRecibido);
					
				} else {
					Traza.error(getClass(), "Este mensaje recibido debe ser de tipo TextMessage");
				}
			} else {
				continuarBuscarMensajeMic = false;
				Traza.auditoria_MQ(getClass(), "La cola receptora de mensajes se encuentra vacia");
			}
		}
		
		Traza.auditoria_MQ(getClass(), "recibirProcesarMensajesMic - Se ha recibido un total de mensajes: " + contador);
	}
	
	
	/**
	 * Servicio que procesa el mensaje recibido
	 * @param message
	 */
	private synchronized void procesarMensajeRecibido(Message message) {
		try {   
			Traza.transaccionMQ(getClass(), "Entrada - Propiedades: " + message.toString());
			
			TextMessage tm = (TextMessage) message;
            String tmText = tm.getText();
            
            // He visto que recibimos caracteres 0 (de tipo nulo(NUL)) 
            // y en las trazas se eliminan estos caracteres por eso
            // he decidido reemplazarlos por un espacio
            char[] caracteres = tmText.toCharArray(); 
    	    for (int i=0;i<caracteres.length;i++) {
    	        if (caracteres[i] == 0) caracteres[i] = ' '; 
    	    }
    	    
    	    String msg = new String(caracteres); 
            Traza.transaccionMQ(getClass(), "Entrada - Contenido:\n"+msg);
            
            //Seguridad Informatica
            String app = message.getStringProperty("JMSXAppID");
            if (Validaciones.isNullOrEmpty(app) 
            		|| !(micImputacionCobrosControlMQ.getJmsPropiedadesContexto().getAplicacion().trim().equalsIgnoreCase(app.trim()))) 
            {
				Traza.error(getClass(), "No puede procesar este mensaje recibido ya que no cumple con los requisitos de seguridad ("+app+")...");
			} else {
				if (micADCMapeoJMS.verificarServicio(msg, false) &&
	            		micADCMapeoJMS.verificarLongitudMsjRecibida(msg, false)) 
	            {
	            	procesarRespuestaADC(msg);
	            
	            } else 
                if (micRespuestaADCMapeoJMS.verificarServicio(msg, false) &&
	            		micRespuestaADCMapeoJMS.verificarLongitudMsjRecibida(msg, false)) 
	            {
	            	procesarRespuestaOperacion(msg);
	            	
	            } else {
	               Traza.error(getClass(), "No puede procesar este mensaje recibido ya que el formato es invalido...");
	            }
			}
            //Fin - Seguridad Informatica
            
        } catch (JMSException e) {
        	Traza.error(getClass(), "Error JMS", e);
        } catch (Exception e) {
        	Traza.error(getClass(), "Otro Error", e);
        } finally {
        	notify();
        }
	}
	
	
	/**
	 * 
	 * @param mensaje
	 * @throws NegocioExcepcion
	 */
	private void procesarRespuestaADC(String mensaje) throws NegocioExcepcion {
		
		try {
			MicRespuestaRecepcionSalida rta = 
					(MicRespuestaRecepcionSalida)micADCMapeoJMS.deserializar(mensaje, true, false);
			
			Long idOperacion = new Long(rta.getIdOperacionTransaccion().split("\\.")[0]);
			Integer numeroTransaccion = new Integer(rta.getIdOperacionTransaccion().split("\\.")[1]);
			
			/*********************** Comienzo Dummy **************************************/
			String mensajeDummy = mensaje;
			Long idOperacionDummy = new Long(909);
			if (idOperacion.compareTo(idOperacionDummy) == 0
					&& numeroTransaccion.compareTo(new Integer(1)) == 0) {
				mensajeDummy="CRASYNCU                                                                                                                                00002147800                                                                                                                                                                                         000000000909.00001";
			} else {
				if (idOperacion.compareTo(idOperacionDummy) == 0
						&& numeroTransaccion.compareTo(new Integer(2)) == 0) {
					mensajeDummy="CRASYNCU                                                                                                                                00002147800                                                                                                                                                                                         000000000909.00002";
				} else {
					if (idOperacion.compareTo(idOperacionDummy) == 0
							&& numeroTransaccion.compareTo(new Integer(3)) == 0) {
						mensajeDummy="CRASYNCU                                                                                                                                00002147800                                                                                                                                                                                         000000000909.00003";
					} else {
						if (idOperacion.compareTo(idOperacionDummy) == 0
								&& numeroTransaccion.compareTo(new Integer(4)) == 0) {
							mensajeDummy="CRASYNCU                                                                                                                                00002147800                                                                                                                                                                                         000000000909.00004";
						} else {
							if (idOperacion.compareTo(idOperacionDummy) == 0
									&& numeroTransaccion.compareTo(new Integer(0)) == 0) {
								//desapropiacion o confirmacion
								mensajeDummy="CRASYNCU                                                                                                                                00002147800                                                                                                                                                                                         000000000909.00000";
							}
						}
					}
				}
			}
			rta = (MicRespuestaRecepcionSalida)micADCMapeoJMS.deserializar(mensajeDummy, true, false);
			
			idOperacion = new Long(rta.getIdOperacionTransaccion().split("\\.")[0]);
			numeroTransaccion = new Integer(rta.getIdOperacionTransaccion().split("\\.")[1]);
			/*********************** Fin Dummy **************************************/
			
			Integer idTransaccion = null;
			if (numeroTransaccion.compareTo(new Integer("0")) != 0) {
				idTransaccion = transaccionCobroServicio.buscarIdTransaccion(idOperacion, numeroTransaccion);
			}
			
			CobMensajeriaTransaccionDto cobroMensajeriaDto = null;
			if (numeroTransaccion.compareTo(new Integer("0")) == 0) {
				cobroMensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajeDesapropiacionConfirmacionEnviadoAlMIC(idOperacion);
			} else {
				cobroMensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajeApropiacionCargoEnviadoAlMIC(idOperacion, idTransaccion);
			}
			
			if (cobroMensajeriaDto != null) {
				Traza.transaccionMQ(getClass(), "procesarRespuestaADC: Se ha recibido el mensaje ("+rta.getIdOperacionTransaccion()+") de tipo: " + cobroMensajeriaDto.getServicio().getDescripcion() + "("+ cobroMensajeriaDto.getServicio().name()+")" );
				
				cobroMensajeriaDto.setFechaRecepcion(new Date());
				
				//Guardo la respuesta en la bd 
				cobroMensajeriaDto.setRespuestaRecibida(mensaje);
				
				//Si es OK, pongo como completado
				if (Constantes.MIC_COD_RESP_OK.equalsIgnoreCase(rta.getRetorno().getCodRetorno())) {
					cobroMensajeriaDto.setEstado(MensajeEstadoEnum.COMPLETADO);
				} else {
					cobroMensajeriaDto.setEstado(MensajeEstadoEnum.REC_ERROR);
				}
				
				//Pero en caso de no tener un mensaje enviado, pongo como recibido
				if (Validaciones.isNullOrEmpty(cobroMensajeriaDto.getMensajeEnviado())) {
					cobroMensajeriaDto.setEstado(MensajeEstadoEnum.RECIBIDO);
				}
				mensajeriaTransaccionServicio.modificar(cobroMensajeriaDto);
				
				//Traceos
				Traza.auditoria(getClass(), "procesarRespuestaADC: Mensaje recibido ("+rta.getIdOperacionTransaccion()+") actualizado en la bd con estado: " + cobroMensajeriaDto.getEstado());
			} else {
				Traza.error(getClass(), "procesarRespuestaADC: Mensaje recibido ("+rta.getIdOperacionTransaccion()+") NO Encontrada en la BD");
			}
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
	}

	/**
	 * Servicio de Respuesta
	 * @param mensaje
	 * @throws NegocioExcepcion
	 */
	private void procesarRespuestaOperacion(String mensaje) throws NegocioExcepcion {
		
		String idOperacionTransaccion = "";
		MicRespuestaRecepcionEntrada jms = new MicRespuestaRecepcionEntrada();
		try {
			//Ahi uso campos generales
			MicRespuestaADCSalida rta = 
				(MicRespuestaADCSalida) micRespuestaADCMapeoJMS.deserializar(mensaje, false, false);
			
			Long idOperacion = new Long(rta.getIdOperacionTransaccion().split("\\.")[0]);
			Integer numeroTransaccion = new Integer(rta.getIdOperacionTransaccion().split("\\.")[1]);
			
			/*********************** Comienzo Dummy **************************************/
			String mensajeDummy = mensaje;
			Long idOperacionDummy = new Long(909);
			if (idOperacion.compareTo(idOperacionDummy) == 0
					&& numeroTransaccion.compareTo(new Integer(1)) == 0) {
				mensajeDummy="CRASYNCU                                                                                                                                00004167800                                                                                                                                                                                         000000000909.000010000909.0000101893046853263OKK072FACTURA APROPIADA                                                                                   000000000000000000000000000000000000000000000                00000000";
			} else {
				if (idOperacion.compareTo(idOperacionDummy) == 0
						&& numeroTransaccion.compareTo(new Integer(2)) == 0) {
					mensajeDummy="CRASYNCU                                                                                                                                00004167800                                                                                                                                                                                         000000000909.000020000909.0000201218146853263OKK072FACTURA APROPIADA                                                                                   000000000000000000000000000000000000000000000                00000000";
				} else {
					if (idOperacion.compareTo(idOperacionDummy) == 0
							&& numeroTransaccion.compareTo(new Integer(3)) == 0) {
						mensajeDummy="CRASYNCU                                                                                                                                00004167800                                                                                                                                                                                         000000000909.000030000909.0000301523346853263OKK072FACTURA APROPIADA                                                                                   000000000000000000000000000000000000000000000                00000000";
					} else {
						if (idOperacion.compareTo(idOperacionDummy) == 0
								&& numeroTransaccion.compareTo(new Integer(4)) == 0) {
							mensajeDummy="CRASYNCU                                                                                                                                0000416S043               AKI07011Ÿ00066D9A05                                                                                                                                                       000000000909.000040000909.0000401000000000000ERS043ERROR EN RUTINA DE MOVIMIENTOS ONLINE                                                               000000000000000000000000000000000000000000000                00000000";
						} else {
							if (idOperacion.compareTo(idOperacionDummy) == 0
									&& numeroTransaccion.compareTo(new Integer(0)) == 0) {
								//desapropiacion o confirmacion
								mensajeDummy="CRASYNCU                                                                                                                                00004167800                                                                                     875057236010932  05SH01AK732HIESHIVA            02D                                                 000010000909.000000000909.0000004000000000000OKK075DESAPROPIACION OK                                                                                   000000000000000000000000000000000000000000000                00000000";
							} 
						}
					}
				}
			}
			rta = (MicRespuestaADCSalida)micRespuestaADCMapeoJMS.deserializar(mensajeDummy, false, false);
			
			idOperacion = new Long(rta.getIdOperacionTransaccion().split("\\.")[0]);
			numeroTransaccion = new Integer(rta.getIdOperacionTransaccion().split("\\.")[1]);
			mensaje = mensajeDummy;
			/*********************** Fin Dummy **************************************/
			
			idOperacionTransaccion = rta.getIdOperacionTransaccion();
			jms.setMensajeRecibido(mensaje);
			jms.setIdOperacionTransaccion(idOperacionTransaccion);
			Integer idTransaccion = null;
			if (idOperacion.compareTo(new Long("0")) == 0) {
				idOperacion = null;
			}
			if (numeroTransaccion.compareTo(new Integer("0")) != 0) {
				idTransaccion = transaccionCobroServicio.buscarIdTransaccion(idOperacion, numeroTransaccion);
			}
			jms.setIdOperacion(idOperacion);
			jms.setIdTransaccion(idTransaccion);
						
			//Si no esta entonces procesar la respuesta
			MensajeServicioEnum servicio = null;
			if (TipoInvocacionEnum.$01.equals(rta.getTipoInvocacion())) {
				servicio = MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA;
			} else 
			if (TipoInvocacionEnum.$02.equals(rta.getTipoInvocacion())) {
				servicio = MensajeServicioEnum.MIC_RESPUESTA_APROP_MP;	
			} else
			if (TipoInvocacionEnum.$03.equals(rta.getTipoInvocacion())) {
				servicio = MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA_y_MP;
			} else
			if (TipoInvocacionEnum.$04.equals(rta.getTipoInvocacion())) {
				servicio = MensajeServicioEnum.MIC_RESPUESTA_DESAPROPIACION;		
			} else
			if (TipoInvocacionEnum.$05.equals(rta.getTipoInvocacion())) {
				servicio = MensajeServicioEnum.MIC_RESPUESTA_CONFIRMACION;
			}
			jms.setServicio(servicio);
			
			if (idOperacion != null && servicio != null) {
				//Traceos
				Traza.transaccionMQ(getClass(), "procesarRespuestaOperacion: Se ha recibido el mensaje ("+rta.getIdOperacionTransaccion()+") de tipo: " + servicio.getDescripcion() + "("+ servicio.name()+")" );
				
				//Uso todos los campos
		    	rta = (MicRespuestaADCSalida) micRespuestaADCMapeoJMS.deserializar(mensaje, true, false);
		    	rta.setIdTransaccion(idTransaccion);
		    	
		    	
		    	// Buscar primero si está la transaccion en la base de datos
				CobMensajeriaTransaccionDto dto 
					= (CobMensajeriaTransaccionDto) 
							mensajeriaTransaccionServicio.buscarMensajeRecibido(idOperacion, idTransaccion, servicio, mensaje);
					
				Date fechaRecepcion = new Date();
				
				if (dto == null) {
				
					//No existe el mismo mensaje recibido y por lo tanto creo la mensajeria
					CobMensajeriaTransaccionDto mensajeriaDto = new CobMensajeriaTransaccionDto();
			    	mensajeriaDto.setIdTransaccion(idTransaccion);
			    	mensajeriaDto.setIdOperacion(new Long(idOperacion));
			    	mensajeriaDto.setServicio(servicio);
			    	mensajeriaDto.setEstado(MensajeEstadoEnum.RECIBIDO);
			    	mensajeriaDto.setFechaAlta(fechaRecepcion);
			    	mensajeriaDto.setFechaRecepcion(fechaRecepcion);
			    	mensajeriaDto.setCantReintentos(new Integer(0));
			    	mensajeriaDto.setMensajeEnviado(null);
			    	mensajeriaDto.setRespuestaRecibida(mensaje);
			    	mensajeriaDto = crearMensajeria(mensajeriaDto);
			    	
			    	jms.setId(mensajeriaDto.getIdTransaccionMensajeria());	
			    	Traza.auditoria(getClass(), "procesarRespuestaOperacion: Mensaje recibido ("+rta.getIdOperacionTransaccion()+") actualizado en la bd con estado: " + mensajeriaDto.getEstado());
			    	
			    	//Proceso el mensaje
					procesarRespuestaOperacion(rta);
					
					MicResultado resultado = new MicResultado();
					resultado.setResultadoInvocacion(TipoResultadoEnum.OK.getDescripcionMic());
					jms.setResultado(resultado);
					
					//No envio la fecha de recepcion ya que ya fue seteada 
					responderRespuestaOperacion(jms, null);
					
				} else {
					jms.setId(dto.getIdTransaccionMensajeria());
					
					//Si ya tengo la respuesta para el mismo mensaje recibido, simplemente lo reenvio
					if (!Validaciones.isNullOrEmpty(dto.getMensajeEnviado()) 
							&& (MensajeEstadoEnum.COMPLETADO.equals(dto.getEstado()) 
											|| MensajeEstadoEnum.CANCELADO.equals(dto.getEstado()))) {
						
						dto.setFechaRecepcion(fechaRecepcion);
						reintentosMensajeria.reenviarMensaje(dto, false);
						Traza.auditoria(getClass(), "procesarRespuestaOperacion: Se ha reenviado  el mensaje ("+rta.getIdOperacionTransaccion()+")");
						
					} else {
						
						//Proceso el mensaje
						procesarRespuestaOperacion(rta);
						
						//Le reenvio con un OK si no la tengo en la bd
						MicResultado resultado = new MicResultado();
						resultado.setResultadoInvocacion(TipoResultadoEnum.OK.getDescripcionMic());
						jms.setResultado(resultado);
						
						responderRespuestaOperacion(jms, fechaRecepcion);
					}
				}
				
	    	} else {
				Traza.error(getClass(), "Mensaje recibido (" + idOperacionTransaccion + ") con operacion o servicio desconocido");
				
				//Segun la gente de MIC, dicen que no lo necesitan esta respuesta con error y no le envio
				//MicResultado resultado = new MicResultado();
	    		//resultado.setResultadoInvocacion(TipoResultadoEnum.NOK);
	    		//jms.setResultado(resultado);
	    		
	    		//responderRespuestaOperacion(jms);
			}
		} catch (Exception e) {
			Traza.error(getClass(), "Mensaje recibido (" + idOperacionTransaccion + ") con error de aplicacion SHIVA al procesar el mensaje", e);
			
			//Segun la gente de MIC, dicen que no lo necesitan esta respuesta con error y no le envio
			//MicResultado resultado = new MicResultado();
    		//resultado.setResultadoInvocacion(TipoResultadoEnum.NOK);
    		//jms.setResultado(resultado);
    		
			//responderRespuestaOperacion(jms);
		}
	}

	/**
	 * Procesa los mensajes de respuesta
	 * @param rta
	 * @throws Exception
	 */
	private void procesarRespuestaOperacion(MicRespuestaADCSalida rta) throws Exception {
		
		MicTransaccionADCRespuestaDto dto = new MicTransaccionADCRespuestaDto();
		dto.setIdOperacionTransaccion(rta.getIdOperacionTransaccion());
		dto.setIdTransaccion(rta.getIdTransaccion());
		dto.setTipoInvocacion(rta.getTipoInvocacion());
		String resultadoInvocacion = !Validaciones.isNullOrEmpty(rta.getResultadoLLamadaServicio().getResultadoInvocacion())?
				rta.getResultadoLLamadaServicio().getResultadoInvocacion():null;
		dto.setResultadoInvocacion(resultadoInvocacion);
		dto.setCodigoError(rta.getResultadoLLamadaServicio().getCodigoError());
		dto.setDescripcionError(rta.getResultadoLLamadaServicio().getDescripcionError());
		
		if (TipoInvocacionEnum.$01.equals(rta.getTipoInvocacion())) {
			
			dto.setIdCobranza(rta.getIdCobranza());
			
			//Detalle de cobro de factura
			dto.setInteresesGenerados(rta.getDetalleCobroFactura().getInteresesGenerados());
			dto.setInteresesBonificadosRegulados(rta.getDetalleCobroFactura().getInteresesBonificadosRegulados());
			dto.setInteresesBonificadosNoRegulados(rta.getDetalleCobroFactura().getInteresesBonificadosNoRegulados());
			
			cobroBatchSoporteImputacionMicServicio.micRecepcionApropiacionDeuda(dto);
			
		} else 
		if (TipoInvocacionEnum.$02.equals(rta.getTipoInvocacion())) {
			
			dto.setIdCobranza(rta.getIdCobranza());
			
			cobroBatchSoporteImputacionMicServicio.micRecepcionApropiacionMedioPago(dto);
			
		} else
		if (TipoInvocacionEnum.$03.equals(rta.getTipoInvocacion())) {
			
			dto.setIdCobranza(rta.getIdCobranza());
			
			//Detalle de cobro de factura
			dto.setInteresesGenerados(rta.getDetalleCobroFactura().getInteresesGenerados());
			dto.setInteresesBonificadosRegulados(rta.getDetalleCobroFactura().getInteresesBonificadosRegulados());
			dto.setInteresesBonificadosNoRegulados(rta.getDetalleCobroFactura().getInteresesBonificadosNoRegulados());
			
			cobroBatchSoporteImputacionMicServicio.micRecepcionApropiacionDeudaMedioPago(dto);
			
		} else
		if (TipoInvocacionEnum.$04.equals(rta.getTipoInvocacion())) {
			
			cobroBatchSoporteImputacionMicServicio.micRecepcionDesapropiacion(dto);
			
		} else
		if (TipoInvocacionEnum.$05.equals(rta.getTipoInvocacion())) {
			
			cobroBatchSoporteImputacionMicServicio.micRecepcionConfirmacion(dto);
			
		}
	}
		
	/**
	 * Respuesta a MIC (Servicio de Respuesta)
	 * @param jms
	 * @throws NegocioExcepcion
	 */
	private void responderRespuestaOperacion(MicRespuestaRecepcionEntrada jms, Date fechaRecepcion) throws NegocioExcepcion {
		
		try {
			if (jms != null 
					&& !Validaciones.isNullOrEmpty(jms.getIdOperacionTransaccion())
					&& jms.getResultado() != null) {
				
				if (jms.getId() != null) {
					CobMensajeriaTransaccionDto dto 
						= (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscar(jms.getId());
					
					if (dto != null) {
						if (fechaRecepcion != null) {
							dto.setFechaRecepcion(fechaRecepcion);
						}
						enviarRespuesta(jms, dto);
						
						Traza.auditoria(getClass(), "responderRespuestaOperacion: Mensaje de respuesta enviada ("+jms.getIdOperacionTransaccion()+") actualizado en la bd con estado: " + dto.getEstado());
					} else {
						Traza.error(getClass(), "responderRespuestaOperacion: Mensaje ("+ jms.getIdOperacionTransaccion() +") NO Encontrada en la BD");
					}
					
				} else {
					if (!Validaciones.isNullOrEmpty(jms.getMensajeRecibido()) 
							&& jms.getIdOperacion() != null) {
						
						// En casos de errores de validacion
						CobMensajeriaTransaccionDto mensajeriaDto = new CobMensajeriaTransaccionDto();
						mensajeriaDto.setIdOperacion(jms.getIdOperacion());
				    	mensajeriaDto.setIdTransaccion(jms.getIdTransaccion());
				    	mensajeriaDto.setServicio(jms.getServicio());
				    	mensajeriaDto.setEstado(MensajeEstadoEnum.RECIBIDO);
				    	mensajeriaDto.setFechaAlta(fechaRecepcion);
				    	mensajeriaDto.setFechaRecepcion(fechaRecepcion);
				    	mensajeriaDto.setCantReintentos(new Integer(0));
				    	mensajeriaDto.setMensajeEnviado(null);
				    	mensajeriaDto.setRespuestaRecibida(jms.getMensajeRecibido());
				    	mensajeriaDto = crearMensajeria(mensajeriaDto);
				    	
				    	jms.setId(mensajeriaDto.getIdTransaccionMensajeria());
				    	
				    	enviarRespuesta(jms, mensajeriaDto);
				    	
				    	Traza.auditoria(getClass(), "responderRespuestaOperacion (2): Mensaje de respuesta enviada ("+jms.getIdOperacionTransaccion()+") actualizado en la bd con estado: " + mensajeriaDto.getEstado());
				    } else {
				    	Traza.error(getClass(), "responderRespuestaOperacion (" + jms.getIdOperacionTransaccion() + "): Por no tener datos suficientes, no puede enviar la respuesta al mic, ni actualizar en la BD");
				    }
				}
			} else {
				Traza.error(getClass(), "responderRespuestaOperacion: Por no tener datos suficientes, no puede enviar la respuesta al mic, ni actualizar en la BD");
			}
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
	}

	/**
	 * Ejecuto el envio de la info y guardo en la BD
	 * @param jms
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	private void enviarRespuesta(JMS jms, CobMensajeriaTransaccionDto dto) throws NegocioExcepcion {
		// Serializar el objeto a mensaje
		String mensajeAEnviar = micRespuestaRecepcionMapeoJMS.serializar(jms, false);
				
		//Envio la respuesta por primera vez
		reintentosMensajeria.enviarRespuesta(dto, mensajeAEnviar);
	}
	
	
	/********************************************************************************
	 * Servicio de Mensajeria 
	 */
	public CobMensajeriaTransaccionDto crearMensajeria(CobMensajeriaTransaccionDto dto) 
			throws NegocioExcepcion {
		
		return (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.crearTransaccionPropia(dto);
	}
	
	@Override
	public Collection<DTO> listarMensajesPendientes(TipoProcesoEnum tipoProceso)
			throws NegocioExcepcion {
		return reintentosMensajeria.listaMensajesAReenviarMIC(tipoProceso);
	}
	
	@Override
	public void reenviarMensaje(DTO dto) throws NegocioExcepcion {
		reintentosMensajeria.reenviarMensaje(dto, true);		
	}
	
	/************************************************************************
	 * Getters/Setters
	 ***********************************************************************/
	/**
	 * @return the micADCMapeoJMS
	 */
	public MapeadorJMS getMicADCMapeoJMS() {
		return micADCMapeoJMS;
	}

	/**
	 * @param micADCMapeoJMS the micADCMapeoJMS to set
	 */
	public void setMicADCMapeoJMS(MapeadorJMS micADCMapeoJMS) {
		this.micADCMapeoJMS = micADCMapeoJMS;
	}

	/**
	 * @return the micRespuestaADCMapeoJMS
	 */
	public MapeadorJMS getMicRespuestaADCMapeoJMS() {
		return micRespuestaADCMapeoJMS;
	}

	/**
	 * @param micRespuestaADCMapeoJMS the micRespuestaADCMapeoJMS to set
	 */
	public void setMicRespuestaADCMapeoJMS(MapeadorJMS micRespuestaADCMapeoJMS) {
		this.micRespuestaADCMapeoJMS = micRespuestaADCMapeoJMS;
	}

	public IMensajeriaTransaccionServicio getMensajeriaTransaccionServicio() {
		return mensajeriaTransaccionServicio;
	}

	public void setMensajeriaTransaccionServicio(
			IMensajeriaTransaccionServicio mensajeriaTransaccionServicio) {
		this.mensajeriaTransaccionServicio = mensajeriaTransaccionServicio;
	}

	public ICobroImputacionServicio getCobroServicio() {
		return cobroServicio;
	}

	public void setCobroServicio(ICobroImputacionServicio cobroServicio) {
		this.cobroServicio = cobroServicio;
	}

	public IReintentos getReintentosMensajeria() {
		return reintentosMensajeria;
	}

	public void setReintentosMensajeria(IReintentos reintentosMensajeria) {
		this.reintentosMensajeria = reintentosMensajeria;
	}

	public ITransaccionCobroServicio getTransaccionCobroServicio() {
		return transaccionCobroServicio;
	}

	public void setTransaccionCobroServicio(
			ITransaccionCobroServicio transaccionCobroServicio) {
		this.transaccionCobroServicio = transaccionCobroServicio;
	}

	public IControlJms getMicImputacionCobrosControlMQ() {
		return micImputacionCobrosControlMQ;
	}

	public void setMicImputacionCobrosControlMQ(IControlJms micImputacionCobrosControlMQ) {
		this.micImputacionCobrosControlMQ = micImputacionCobrosControlMQ;
	}

	public MapeadorJMS getMicRespuestaRecepcionMapeoJMS() {
		return micRespuestaRecepcionMapeoJMS;
	}

	public void setMicRespuestaRecepcionMapeoJMS(
			MapeadorJMS micRespuestaRecepcionMapeoJMS) {
		this.micRespuestaRecepcionMapeoJMS = micRespuestaRecepcionMapeoJMS;
	}
}
