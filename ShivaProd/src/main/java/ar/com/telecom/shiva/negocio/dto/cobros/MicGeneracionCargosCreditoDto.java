package ar.com.telecom.shiva.negocio.dto.cobros;

import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;

public class MicGeneracionCargosCreditoDto extends MicTransaccionGeneracionCargosDto {

	private static final long serialVersionUID = 1L;

	private MensajeServicioEnum servicio = MensajeServicioEnum.MIC_GENERACION_CARGO_CREDITO;
	
	public MicGeneracionCargosCreditoDto() {
		super.setServicio(servicio);
	}
}
