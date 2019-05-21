package ar.com.telecom.shiva.base.enumeradores;

public enum EstadoValorConstRecepEnum {

	ASIGNADA("Asignada"), ANULADA("Anulada");

	String descripcion;

	private EstadoValorConstRecepEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String descripcion() {
		return this.descripcion;
	}

	public static EstadoValorConstRecepEnum getEnumByName(String name) {

		for (EstadoValorConstRecepEnum estado : EstadoValorConstRecepEnum
				.values()) {
			if (estado.name().equals(name)) {
				return estado;
			}
		}

		return null;
	}

}
