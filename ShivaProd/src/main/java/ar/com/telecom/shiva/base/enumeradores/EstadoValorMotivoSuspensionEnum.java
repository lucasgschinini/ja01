package ar.com.telecom.shiva.base.enumeradores;

public enum EstadoValorMotivoSuspensionEnum {

	VAL_MOTIVO_SUSPENSION_CAMBIO_CLIENTE("Cambio de cliente solicitado"),
	VAL_MOTIVO_SUSPENSION_CHEQUE_RECHAZADO("Cheque rechazado"),
	VAL_MOTIVO_OTROS("Otros");

	String descripcion;

	private EstadoValorMotivoSuspensionEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String descripcion() {
		return this.descripcion;
	}

	public static EstadoValorMotivoSuspensionEnum getEnumByName(String name) {

		for (EstadoValorMotivoSuspensionEnum estado : EstadoValorMotivoSuspensionEnum
				.values()) {
			if (estado.name().equals(name)) {
				return estado;
			}
		}

		return null;
	}

}
