package ar.com.telecom.shiva.base.enumeradores;

public enum EstadoRegistroOperacionMasivaEnum {
	PENDIENTE_DATOS_SIEBEL("Pendiente de recibir datos de Siebel"),
	ERROR_SIEBEL("Error al procesar datos de Siebel"),
	ERROR_SHIVA("Error en la gestionabilidad"),
	PENDIENTE_PROCESAR("Pendiente de Procesar"),
	PENDIENTE_APROBACION("Pendiente de Aprobación"),
	APROBACION_RECHAZADA("Aprobación Rechazada"),
	ERROR_MIC("Error al procesar datos de MIC"),
	PROCESO_IMPUTACION("En proceso de Imputación"),
	PROCESADO("Procesado"),
	ERROR("Error");

	String descripcion;

	private EstadoRegistroOperacionMasivaEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public static EstadoRegistroOperacionMasivaEnum getEnumByName (String name) {
		for (EstadoRegistroOperacionMasivaEnum enumReg : EstadoRegistroOperacionMasivaEnum.values()) {
			if (enumReg.name().equalsIgnoreCase(name)) {
				return enumReg;
			}
		}
		return null;
	}
}
