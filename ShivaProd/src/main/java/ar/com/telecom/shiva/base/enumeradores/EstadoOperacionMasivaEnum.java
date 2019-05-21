package ar.com.telecom.shiva.base.enumeradores;


public enum EstadoOperacionMasivaEnum {

	ANULADA("Anulada"),
	EN_PROCESO("En Proceso"),
	PENDIENTE_APROBACION("Pendiente de Aprobación"),
	RECHAZADO("Rechazado"),
	EN_ERROR("En Error"),
	FINALIZADA("Finalizada");
	
	String descripcion;
	
	private EstadoOperacionMasivaEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getCodigo() {
		return this.name();
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public static EstadoOperacionMasivaEnum getEnumByName(String name) {
		for (EstadoOperacionMasivaEnum estado : EstadoOperacionMasivaEnum.values()) {
			if (estado.name().equalsIgnoreCase(name)) {
				return estado;
			}
		}
		return null;
	}
	
}
