package ar.com.telecom.shiva.base.soa.reintentos;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.IControlJms;
import ar.com.telecom.shiva.base.jms.util.JmsUtilidad;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

@Component
public class ReintentosMensajeria implements IReintentos {

	@Autowired
	IMensajeriaTransaccionServicio mensajeriaTransaccionServicio;
	
	@Autowired
	IParametroServicio parametroServicio;
	
	@Autowired
	IControlJms micImputacionCobrosControlMQ;
	
	@Autowired
	IControlJms micImputacionDescobrosControlMQ;
	
	@Override
	public void enviarMensaje(DTO dto, String mensajeAEnviar) throws NegocioExcepcion {
		if (dto instanceof CobMensajeriaTransaccionDto) {
			CobMensajeriaTransaccionDto cobMsjDto = (CobMensajeriaTransaccionDto) dto;
			cobMsjDto.setMensajeEnviado(mensajeAEnviar);
			cobMsjDto.setEstado(MensajeEstadoEnum.PENDIENTE);
			cobMsjDto.setFechaEnvio(new Date());
			mensajeriaTransaccionServicio.modificar(cobMsjDto);
			
			//Envio datos
			String contrato = JmsUtilidad.buscarContrato(cobMsjDto.getServicio());
			if (MensajeServicioEnum.MIC_APROP_DEUDA.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_APROP_DEUDA_Y_MP.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_APROP_MP.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_CONFIRMACION.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_DESAPROPIACION.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_GENERACION_CARGO_CREDITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_GENERACION_CARGO_DEBITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_GENERACION_CARGO_INTERES.equals(cobMsjDto.getServicio())
					) 
			{
				micImputacionCobrosControlMQ.enviarMensaje(mensajeAEnviar, contrato);
			} else 
			if (MensajeServicioEnum.MIC_DESCOBRO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_INTERES.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES.equals(cobMsjDto.getServicio())) 
			{
				micImputacionDescobrosControlMQ.enviarMensaje(mensajeAEnviar, contrato);
			} else {
				throw new NegocioExcepcion("Mensajeria enviarMensaje: Servicio incorrecto");
			}
				
			//Traceo
			trazarEnvioTipoMensaje("enviarMensaje", cobMsjDto.getServicio());
			
			//Actualizo
			cobMsjDto.setEstado(MensajeEstadoEnum.ENVIADO);
			cobMsjDto.setFechaEnvio(new Date());
			mensajeriaTransaccionServicio.modificar(cobMsjDto);
		}
	}

	@Override
	public void enviarRespuesta(DTO dto, String mensajeAEnviar) throws NegocioExcepcion {
		if (dto instanceof CobMensajeriaTransaccionDto) {
			CobMensajeriaTransaccionDto cobMsjDto = (CobMensajeriaTransaccionDto) dto;
			cobMsjDto.setMensajeEnviado(mensajeAEnviar);
			cobMsjDto.setEstado(MensajeEstadoEnum.PENDIENTE);
			cobMsjDto.setFechaEnvio(new Date());
			mensajeriaTransaccionServicio.modificar(cobMsjDto);
			
			//Envio datos
			String contrato = JmsUtilidad.buscarContrato(cobMsjDto.getServicio());
			if (MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA_y_MP.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_APROP_MP.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_CONFIRMACION.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_DESAPROPIACION.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_GENERACION_CARGO_CREDITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_GENERACION_CARGO_DEBITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_GENERACION_CARGO_INTERES.equals(cobMsjDto.getServicio())) 
			{
				micImputacionCobrosControlMQ.enviarMensaje(mensajeAEnviar, contrato);
			} else 
			if (MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_CREDITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_DEBITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_INTERES.equals(cobMsjDto.getServicio())) 
			{
				micImputacionDescobrosControlMQ.enviarMensaje(mensajeAEnviar, contrato);
			} else {
				throw new NegocioExcepcion("Mensajeria enviarRespuesta: Servicio incorrecto");
			}
			
			//Traceo
			trazarEnvioTipoMensaje("enviarRespuesta", cobMsjDto.getServicio());
			
			//Actualizo
			cobMsjDto.setEstado(MensajeEstadoEnum.COMPLETADO);
			cobMsjDto.setFechaEnvio(new Date());
			mensajeriaTransaccionServicio.modificar(cobMsjDto);
		}
	}

	@Override
	public Collection<DTO> listaMensajesAReenviarMIC(TipoProcesoEnum tipoProceso) throws NegocioExcepcion {
		
		Integer tiempoParaReenvio = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.MSG_TIEMPO_DE_REINTENTO_MINUTOS).toString());
		Date fechaPermitida = Utilidad.restMinutes(new Date(), tiempoParaReenvio);
		String fechaPermitidaParaReenvio = Utilidad.formatDateAndTimeFull(fechaPermitida);
		Integer cantMaxReintentos = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.MSG_CANTIDAD_REINTENTOS).toString());
		
		return mensajeriaTransaccionServicio.listarMensajeriasPendientesMIC(cantMaxReintentos, fechaPermitidaParaReenvio, tipoProceso);
	}

	@Override
	public synchronized  void reenviarMensaje(DTO dto, boolean incrementar) throws NegocioExcepcion {
		if (dto instanceof CobMensajeriaTransaccionDto) {
			CobMensajeriaTransaccionDto cobMsjDto = (CobMensajeriaTransaccionDto) dto;
			
			//Hago incrementar el reintento
			if (incrementar) {
				int cantReintentos = 1;
				if (cobMsjDto.getCantReintentos() != null) {
					cantReintentos = cobMsjDto.getCantReintentos() + 1;
				}
				cobMsjDto.setCantReintentos(cantReintentos);
			}
			
			cobMsjDto.setFechaEnvio(new Date());
			
			String contrato = null;
			String mensajeAEnviar = "";
			if (MensajeServicioEnum.MIC_APROP_DEUDA.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_APROP_MP.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_APROP_DEUDA_Y_MP.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_DESAPROPIACION.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_CONFIRMACION.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_GENERACION_CARGO_CREDITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_GENERACION_CARGO_DEBITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_GENERACION_CARGO_INTERES.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_DESCOBRO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_INTERES.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES.equals(cobMsjDto.getServicio())) 
			{
				//if (!MensajeEstadoEnum.ENVIADO.equals(cobMsjDto.getEstado())) {
					cobMsjDto.setEstado(MensajeEstadoEnum.PENDIENTE);
				//}
				mensajeAEnviar = cobMsjDto.getMensajeEnviado();
				mensajeriaTransaccionServicio.modificar(cobMsjDto);
				
				//Reenvio datos
				contrato = JmsUtilidad.buscarContrato(cobMsjDto.getServicio());
				
				if (!(MensajeServicioEnum.MIC_DESCOBRO.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_INTERES.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES.equals(cobMsjDto.getServicio()))) {
					micImputacionCobrosControlMQ.enviarMensaje(mensajeAEnviar, contrato);
				}
				if (MensajeServicioEnum.MIC_DESCOBRO.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_INTERES.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES.equals(cobMsjDto.getServicio())) {
					micImputacionDescobrosControlMQ.enviarMensaje(mensajeAEnviar, contrato);
				}
				
				//Traceo
				trazarEnvioTipoMensaje("reenviarMensaje", cobMsjDto.getServicio());
				
				//Actualizo
				cobMsjDto.setEstado(MensajeEstadoEnum.ENVIADO);
				cobMsjDto.setFechaEnvio(new Date());
				mensajeriaTransaccionServicio.modificar(cobMsjDto); 
			}
			if (MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_APROP_MP.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA_y_MP.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_DESAPROPIACION.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_CONFIRMACION.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_GENERACION_CARGO_CREDITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_GENERACION_CARGO_DEBITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_GENERACION_CARGO_INTERES.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_CREDITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_DEBITO.equals(cobMsjDto.getServicio())
					|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_INTERES.equals(cobMsjDto.getServicio())) 
			{
				//if (!MensajeEstadoEnum.RECIBIDO.equals(cobMsjDto.getEstado())) {
					cobMsjDto.setEstado(MensajeEstadoEnum.PENDIENTE);
				//}
				mensajeAEnviar = cobMsjDto.getMensajeEnviado();
				mensajeriaTransaccionServicio.modificar(cobMsjDto);
				
				//Reenvio datos
				contrato = JmsUtilidad.buscarContrato(cobMsjDto.getServicio());
				if (!(MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_CREDITO.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_DEBITO.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_INTERES.equals(cobMsjDto.getServicio()))) {
					micImputacionCobrosControlMQ.enviarMensaje(mensajeAEnviar, contrato);
				}
				if (MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_CREDITO.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_DEBITO.equals(cobMsjDto.getServicio())
						|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_INTERES.equals(cobMsjDto.getServicio())) {
					micImputacionDescobrosControlMQ.enviarMensaje(mensajeAEnviar, contrato);
				}
				
				//Traceo
				trazarEnvioTipoMensaje("reenviarMensaje", cobMsjDto.getServicio());
				
				//Actualizo
				cobMsjDto.setEstado(MensajeEstadoEnum.COMPLETADO);
				cobMsjDto.setFechaEnvio(new Date());
				mensajeriaTransaccionServicio.modificar(cobMsjDto);
			}
		} 
		
		notifyAll();
	}

	/**
	 * Me permite tracear el tipo del mensaje enviado
	 * @param metodo
	 * @param servicio
	 */
	private void trazarEnvioTipoMensaje(String metodo, MensajeServicioEnum servicio) {
		
		if (servicio != null) {
			Traza.transaccionMQ(getClass(), metodo + ": Se ha enviado el mensaje de " + servicio.getDescripcion() + "("+servicio.name()+")" );
		}
	}
	
	/**************************************************************************
	 * Getters / Setters
	 *************************************************************************/
	
	public IMensajeriaTransaccionServicio getMensajeriaTransaccionServicio() {
		return mensajeriaTransaccionServicio;
	}

	public void setMensajeriaTransaccionServicio(
			IMensajeriaTransaccionServicio mensajeriaTransaccionServicio) {
		this.mensajeriaTransaccionServicio = mensajeriaTransaccionServicio;
	}

	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

	public IControlJms getMicImputacionCobrosControlMQ() {
		return micImputacionCobrosControlMQ;
	}

	public void setMicImputacionCobrosControlMQ(
			IControlJms micImputacionCobrosControlMQ) {
		this.micImputacionCobrosControlMQ = micImputacionCobrosControlMQ;
	}
		
}