package ar.com.telecom.shiva.base.enumeradores;

public enum ExcepcionMorosidadFechaEjecucionEnum {
	FECHA_DERIVACION("fecha_derivacion"),
	FECHA_VENCIMIENTO("fecha vencimiento");

	String descripcion;

	private ExcepcionMorosidadFechaEjecucionEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
	public static ExcepcionMorosidadFechaEjecucionEnum getEnumByName(String name) {
		for (ExcepcionMorosidadFechaEjecucionEnum elemento : ExcepcionMorosidadFechaEjecucionEnum.values()) {
			if (elemento.name().equalsIgnoreCase(name)) {
				return elemento;
			}
		}
		return null;
	}
}
