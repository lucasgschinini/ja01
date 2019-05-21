package ar.com.telecom.shiva.negocio.dto.cobros;

import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;

public class MicDescobroGeneracionCargosInteresDto extends MicTransaccionGeneracionCargosDto {

	private static final long serialVersionUID = 1L;

	private MensajeServicioEnum servicio = MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_INTERES;
	
	public MicDescobroGeneracionCargosInteresDto() {
		super.setServicio(servicio);
	}
}
