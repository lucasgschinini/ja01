package ar.com.telecom.shiva.base.enumeradores;


public enum TipoSimularDisabled {
	ERROR("Error"),
	TRATAMIENTO("Tratamiento");

	String descripcion;

	private TipoSimularDisabled(String descripcion) {
		this.descripcion = descripcion;
	}

	public String descripcion() {
		return this.descripcion;
	}

	public static TipoSimularDisabled getEnumByName(String name) {
		for (TipoSimularDisabled tipo : TipoSimularDisabled.values()) {
			if (tipo.name().equalsIgnoreCase(name)) {
				return tipo;
			}
		}
		return null;
	}
}
