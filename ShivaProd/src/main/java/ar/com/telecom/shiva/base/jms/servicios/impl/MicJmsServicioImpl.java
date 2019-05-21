package ar.com.telecom.shiva.base.jms.servicios.impl;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.IControlJms;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicApropiacionDesapropiacionConfirmacionEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicDescobroEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicGeneracionCargoEntrada;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsServicio;
import ar.com.telecom.shiva.base.soa.reintentos.IReintentos;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionDescobroDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosDto;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ImputacionRecepcionRto;
import ar.com.telecom.shiva.negocio.executor.runnable.ImputacionRecepcionRunnable;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITransaccionCobroServicio;

public class MicJmsServicioImpl extends JmsServicio implements IMicJmsServicio {

	IControlJms micControlMQ;
	@Autowired IControlJms micImputacionCobrosControlMQ;
	@Autowired ICobroImputacionServicio cobroServicio;
	
	@Autowired IControlJms micImputacionDescobrosControlMQ;
	@Autowired IDescobroImputacionServicio descobroImputacionServicio;
	
	@Autowired IReintentos reintentosMensajeria;
	@Autowired IMensajeriaTransaccionServicio mensajeriaTransaccionServicio;
	@Autowired ITransaccionCobroServicio transaccionCobroServicio;
	
	@Autowired IParametroServicio parametroServicio;
	
	/**************************************************************************************************
	 * Recepcion de mensajes
	 *************************************************************************************************/
	
	/********************************************************************************
	 * Servicio de Apropiacion, Desapropiacion y Confirmacion 
	 */
	@Override
	public void apropiarDeuda(MicTransaccionADCDto dto)
			throws NegocioExcepcion {
		
		// Preparar el objeto a enviar
		MicApropiacionDesapropiacionConfirmacionEntrada jms = mapeadorImputacionDtoJms(dto);
		jms.setTipoInvocacion(TipoInvocacionEnum.$01);
		
		enviarMensajeSolicitud(jms, dto.getMensajeria());
	}

	@Override
	public void apropiarMedioPago(
			MicTransaccionADCDto dto)
			throws NegocioExcepcion {
		
		// Preparar el objeto a enviar
		MicApropiacionDesapropiacionConfirmacionEntrada jms = mapeadorImputacionDtoJms(dto);
		jms.setTipoInvocacion(TipoInvocacionEnum.$02);
		
		enviarMensajeSolicitud(jms, dto.getMensajeria());
	}

	@Override
	public void apropiarDeudaYMedioPago(
			MicTransaccionADCDto dto)
			throws NegocioExcepcion {
		
		// Preparar el objeto a enviar
		MicApropiacionDesapropiacionConfirmacionEntrada jms = mapeadorImputacionDtoJms(dto);
		jms.setTipoInvocacion(TipoInvocacionEnum.$03);
		
		enviarMensajeSolicitud(jms, dto.getMensajeria());
	}

	@Override
	public void desapropiarOperacion(
			MicTransaccionADCDto dto)
			throws NegocioExcepcion {
		
		// Preparar el objeto a enviar
		MicApropiacionDesapropiacionConfirmacionEntrada jms = mapeadorImputacionDtoJms(dto);
		jms.setTipoInvocacion(TipoInvocacionEnum.$04);
		
		enviarMensajeSolicitud(jms, dto.getMensajeria());
	}

	@Override
	public void confirmarOperacion(MicTransaccionADCDto dto)
			throws NegocioExcepcion {
		// Preparar el objeto a enviar
		MicApropiacionDesapropiacionConfirmacionEntrada jms = mapeadorImputacionDtoJms(dto);		
		jms.setTipoInvocacion(TipoInvocacionEnum.$05);
		
		enviarMensajeSolicitud(jms, dto.getMensajeria());
	}

	/********************************************************************************
	 * Servicio de generar cargos 
	 */
	@Override
	public void generarCargo(
			MicTransaccionGeneracionCargosDto dto) throws NegocioExcepcion {
		// Preparar el objeto a enviar
		MicGeneracionCargoEntrada jms = mapeadorCargoDtoJms(dto);
		
		enviarMensajeSolicitud(jms, dto.getMensajeria());
	}
	
	/********************************************************************************
	 * Servicio de Reversión de Operaciones 
	 */
	@Override
	public void descobrarOperacion(MicTransaccionDescobroDto dto)
			throws NegocioExcepcion {
		
		// Preparar el objeto a enviar
		MicDescobroEntrada jms = mapeadorDescobroDtoJms(dto);
		jms.setTipoInvocacion(TipoInvocacionEnum.$09);
		
		enviarMensajeSolicitud(jms, dto.getMensajeria());
	}
	
	/**
	 * Ejecuto el envio de la info y guardo en la BD
	 * @param jms
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	private synchronized void enviarMensajeSolicitud(JMS jms, CobMensajeriaTransaccionDto dto) throws NegocioExcepcion{
		String mensajeAEnviar = "";
		
		//--------------------------------------------------------------------------
		// Serializar el objeto a mensaje
		if (jms instanceof MicApropiacionDesapropiacionConfirmacionEntrada) {
			mensajeAEnviar = micADCMapeoJMS.serializar(jms, false);
		} else
		if (jms instanceof MicGeneracionCargoEntrada) {
			mensajeAEnviar = micGeneracionCargoMapeoJMS.serializar(jms, false);
		} else
		if (jms instanceof MicDescobroEntrada) {
			mensajeAEnviar = micDescobroMapeoJMS.serializar(jms, false);
		} else{
			throw new NegocioExcepcion("No se ha encontrado mapeo");
		}
		//--------------------------------------------------------------------------
		
		//Envio el mensaje por primera vez
		reintentosMensajeria.enviarMensaje(dto, mensajeAEnviar);
		
		notifyAll();
	}

	/**************************************************************************************************
	 * Recepcion de mensajes
	 *************************************************************************************************/
	/**
	 * 
	 * @throws NegocioExcepcion
	 */
	public void recibirMensajes(TipoProcesoEnum tipoProceso)  throws NegocioExcepcion {
		boolean continuarBuscarMensajeMic = true;
		int contador = 0;
		
		//Pool de Imputacion Recepcion
		Long cantConcurrenciasRecepcionCobros = parametroServicio.getValorNumerico(Constantes.CANTIDAD_CONCURRENCIAS_IMPUTACION_RECEPCION_COBROS);
		Integer cantConcurrencias = Integer.valueOf(cantConcurrenciasRecepcionCobros.toString());

		if (TipoProcesoEnum.DESCOBRO.equals(tipoProceso)) {
			Long cantConcurrenciasRecepcionDescobros = parametroServicio.getValorNumerico(Constantes.CANTIDAD_CONCURRENCIAS_IMPUTACION_RECEPCION_DESCOBROS);
			cantConcurrencias = Integer.valueOf(cantConcurrenciasRecepcionDescobros.toString());;
		}
		
		if (cantConcurrencias > 0) {
			ExecutorService executorImputacionesRecepcion = 
					Executors.newFixedThreadPool(cantConcurrencias);
			while (continuarBuscarMensajeMic) {
				if (TipoProcesoEnum.COBRO.equals(tipoProceso)) {
					micControlMQ = micImputacionCobrosControlMQ;
				} else 
				if (TipoProcesoEnum.DESCOBRO.equals(tipoProceso)) {
					micControlMQ = micImputacionDescobrosControlMQ;
				} else {
					throw new NegocioExcepcion("Tipo de proceso no declarado");
				}
				
				Message mensajeRecibido = micControlMQ.recibirMensaje();
				
				if (mensajeRecibido != null) {
					contador++;
					
					if (mensajeRecibido instanceof TextMessage) {
						Traza.transaccionMQ(getClass(), "Entrada - Propiedades: " + mensajeRecibido.toString());
						
						ImputacionRecepcionRto rto = 
								new ImputacionRecepcionRto(contador, mensajeRecibido, tipoProceso, micControlMQ);
						Runnable tarea = new ImputacionRecepcionRunnable(rto);
						executorImputacionesRecepcion.execute(tarea);
													
					} else {
						Traza.error(getClass(), "Este mensaje recibido debe ser de tipo TextMessage");
					}
				} else {
					continuarBuscarMensajeMic = false;
					Traza.auditoria_MQ(getClass(), "La cola receptora de mensajes se encuentra vacia");
				}
			}
			
			executorImputacionesRecepcion.shutdown();
			// Espero a que terminen de ejecutarse todos los procesos 
			while (!executorImputacionesRecepcion.isTerminated()) {}
//			o
//			try {
//				executorImputacionesRecepcion.awaitTermination(1, TimeUnit.HOURS);	
//			} catch (InterruptedException e) {
//				Traza.error(getClass(), "Problemas de executor:", e);
//			}
			
			executorImputacionesRecepcion = null;
		} else {
			Traza.error(this.getClass(), 
					"No se correrá el proceso de recepcion por encontrarse en el poolImputacionRecepcion con la cantidad de concurrencias = " 
								+ cantConcurrencias);
		}
		Traza.auditoria_MQ(getClass(), "recibirMensajes - Se ha recibido un total de mensajes: " + contador);
	}	

	
	/********************************************************************************
	 * Servicio de Mensajeria 
	 */
	public CobMensajeriaTransaccionDto crearMensajeria(CobMensajeriaTransaccionDto dto) 
			throws NegocioExcepcion {
		
		return (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.crearTransaccionPropia(dto);
	}
	
	@Override
	public Collection<DTO> listarMensajesPendientes(TipoProcesoEnum tipoImputacion) throws NegocioExcepcion {
		return reintentosMensajeria.listaMensajesAReenviarMIC(tipoImputacion);
	}
	
	@Override
	public void reenviarMensaje(DTO dto) throws NegocioExcepcion {
		reintentosMensajeria.reenviarMensaje(dto, true);		
	}
	
	/************************************************************************
	 * Getters/Setters
	 ***********************************************************************/

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

	public void setMicImputacionCobrosControlMQ(
			IControlJms micImputacionCobrosControlMQ) {
		this.micImputacionCobrosControlMQ = micImputacionCobrosControlMQ;
	}

	public IControlJms getMicImputacionDescobrosControlMQ() {
		return micImputacionDescobrosControlMQ;
	}

	public void setMicImputacionDescobrosControlMQ(
			IControlJms micImputacionDescobrosControlMQ) {
		this.micImputacionDescobrosControlMQ = micImputacionDescobrosControlMQ;
	}

	public IDescobroImputacionServicio getDescobroImputacionServicio() {
		return descobroImputacionServicio;
	}

	public void setDescobroImputacionServicio(
			IDescobroImputacionServicio descobroImputacionServicio) {
		this.descobroImputacionServicio = descobroImputacionServicio;
	}

}
