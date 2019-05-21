package ar.com.telecom.shiva.base.jms.util.runnable;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicApropiacionDeudaDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicApropiacionDeudaMPDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicApropiacionMPDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicConfirmacionDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDesapropiacionDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDescobroGeneracionCargosCreditoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDescobroGeneracionCargosDebitoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDescobroGeneracionCargosInteresDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicGeneracionCargosCreditoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicGeneracionCargosDebitoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionDescobroDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosDto;

public class MicJmsThreadEnvioMensaje extends Thread {
	
	private Object objetoAEnviar;
	private IMicJmsServicio micServicio;
	
	public MicJmsThreadEnvioMensaje(String idThread,
			DTO objetoAEnviar, 
			IMicJmsServicio micServicio) {
		
		super(idThread);
		this.setObjetoAEnviar(objetoAEnviar);
		this.setMicServicio(micServicio);
	}
	
	public void run() {
		Traza.auditoria(MicJmsThreadEnvioMensaje.class, 
				Utilidad.reemplazarMensajes("Se ha comenzado el proceso del hilo {0}.", this.getName()));
		
		try {
			
			if (objetoAEnviar instanceof MicApropiacionDeudaDto) {
				MicTransaccionADCDto dto = (MicTransaccionADCDto) objetoAEnviar;
				dto.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
				dto.setTipoInvocacion(TipoInvocacionEnum.$01);
				
				Traza.auditoria(MicJmsThreadEnvioMensaje.class, "Preparando datos de apropiacion de deuda para el envio al MIC");
				micServicio.apropiarDeuda(dto);
				
			}
			if (objetoAEnviar instanceof MicApropiacionMPDto) {
				MicTransaccionADCDto dto = (MicTransaccionADCDto) objetoAEnviar;
				dto.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
				dto.setTipoInvocacion(TipoInvocacionEnum.$02);
				
				Traza.auditoria(MicJmsThreadEnvioMensaje.class, "Preparando datos de apropiacion de medio de pago para el envio al MIC");
				micServicio.apropiarMedioPago(dto);
				
			}
			if (objetoAEnviar instanceof MicApropiacionDeudaMPDto) {
				MicTransaccionADCDto dto = (MicTransaccionADCDto) objetoAEnviar;
				dto.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
				dto.setTipoInvocacion(TipoInvocacionEnum.$03);
				
				Traza.auditoria(MicJmsThreadEnvioMensaje.class, "Preparando datos de apropiacion de deuda y medio de pago para el envio al MIC");
				micServicio.apropiarDeudaYMedioPago(dto);
				
			}
			if (objetoAEnviar instanceof MicDesapropiacionDto) {
				MicTransaccionADCDto dto = (MicTransaccionADCDto) objetoAEnviar;
				dto.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
				dto.setTipoInvocacion(TipoInvocacionEnum.$04);
				
				Traza.auditoria(MicJmsThreadEnvioMensaje.class, "Preparando datos de desapropiacion para el envio al MIC");
				micServicio.desapropiarOperacion(dto);
				
			}
			if (objetoAEnviar instanceof MicConfirmacionDto) {
				MicTransaccionADCDto dto = (MicTransaccionADCDto) objetoAEnviar;
				dto.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
				dto.setTipoInvocacion(TipoInvocacionEnum.$05);
				
				Traza.auditoria(MicJmsThreadEnvioMensaje.class, "Preparando datos de confirmacion para el envio al MIC");				
				micServicio.confirmarOperacion(dto);
				
			}
			
			//General
			if (objetoAEnviar instanceof CobMensajeriaTransaccionDto) {
				
				Traza.auditoria(MicJmsThreadEnvioMensaje.class, "Preparando datos para el reenvio al MIC");
				CobMensajeriaTransaccionDto cobMensajeriaTransaccionDto = (CobMensajeriaTransaccionDto) objetoAEnviar;
				micServicio.reenviarMensaje(cobMensajeriaTransaccionDto);
			}
			
			//Sprint 5
			if (objetoAEnviar instanceof MicGeneracionCargosDebitoDto
					|| objetoAEnviar instanceof MicGeneracionCargosCreditoDto) {
				MicTransaccionGeneracionCargosDto dto = (MicTransaccionGeneracionCargosDto) objetoAEnviar;
				dto.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
				
				if (objetoAEnviar instanceof MicGeneracionCargosDebitoDto) {
					dto.setTipoInvocacion(TipoInvocacionEnum.$06);
					Traza.auditoria(MicJmsThreadEnvioMensaje.class, "Preparando datos de generacion cargos debito para el envio al MIC");				
				}
				if (objetoAEnviar instanceof MicGeneracionCargosCreditoDto) {
					dto.setTipoInvocacion(TipoInvocacionEnum.$07);
					Traza.auditoria(MicJmsThreadEnvioMensaje.class, "Preparando datos de generacion cargos credito para el envio al MIC");				
				}
				micServicio.generarCargo(dto);
			}
			//Sprint 7
			if (objetoAEnviar instanceof MicDescobroGeneracionCargosDebitoDto
					|| objetoAEnviar instanceof MicDescobroGeneracionCargosCreditoDto
					|| objetoAEnviar instanceof MicDescobroGeneracionCargosInteresDto) {
				
				MicTransaccionGeneracionCargosDto dto = (MicTransaccionGeneracionCargosDto) objetoAEnviar;
				dto.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
				
				if (objetoAEnviar instanceof MicDescobroGeneracionCargosDebitoDto) {
					dto.setTipoInvocacion(TipoInvocacionEnum.$06);
					Traza.auditoria(MicJmsThreadEnvioMensaje.class, "Preparando datos de descobro generacion cargos debito para el envio al MIC");				
				}
				if (objetoAEnviar instanceof MicDescobroGeneracionCargosCreditoDto) {
					dto.setTipoInvocacion(TipoInvocacionEnum.$07);
					Traza.auditoria(MicJmsThreadEnvioMensaje.class, "Preparando datos de descobro generacion cargos credito para el envio al MIC");				
				}
				if (objetoAEnviar instanceof MicDescobroGeneracionCargosInteresDto) {
					dto.setTipoInvocacion(TipoInvocacionEnum.$08);
					Traza.auditoria(MicJmsThreadEnvioMensaje.class, "Preparando datos de descobro generacion cargos interes para el envio al MIC");				
				}
				micServicio.generarCargo(dto);
			}
			if (objetoAEnviar instanceof MicTransaccionDescobroDto) {
				MicTransaccionDescobroDto dto = (MicTransaccionDescobroDto) objetoAEnviar;
				dto.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
				dto.setTipoInvocacion(TipoInvocacionEnum.$09);
				
				Traza.auditoria(MicJmsThreadEnvioMensaje.class, "Preparando datos de descobro para el envio al MIC");				
				micServicio.descobrarOperacion(dto);
			}
			Traza.auditoria(MicJmsThreadEnvioMensaje.class, "Se ha enviado exitosamente los datos al MIC");
			
		} catch (Exception e) {
			Traza.error(getClass(), e.getMessage(), e);
		}
		
		Traza.auditoria(MicJmsThreadEnvioMensaje.class, 
				Utilidad.reemplazarMensajes("Se ha finalizado el hilo {0}.", this.getName()));
		
	}
	
	public Object getObjetoAEnviar() {
		return objetoAEnviar;
	}

	public void setObjetoAEnviar(Object objetoAEnviar) {
		this.objetoAEnviar = objetoAEnviar;
	}

	public IMicJmsServicio getMicServicio() {
		return micServicio;
	}

	public void setMicServicio(IMicJmsServicio micServicio) {
		this.micServicio = micServicio;
	}

}
