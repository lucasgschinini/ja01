package ar.com.telecom.shiva.base.enumeradores;

public enum TipoAccionEnum {

	APROBADA("Aprobada"),
	RECHAZADA("Rechazada"),
	CONFIRMADA("Confirmada"),
	ANULADA("Anulada"),
	REEDICION_COMPLETADA("Reedicion completada"); 
	
	String descripcion;

	private TipoAccionEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	/**
	 * Devuelve el enumerador segun el nombre
	 * @param name
	 * @return
	 */
	public static TipoTareaEnum getEnumByName(String name){
		
		for (TipoTareaEnum tarea : TipoTareaEnum.values()) {
			if (tarea.name().equals(name)) {
				return tarea;
			}
		}
		return null; 
	}

}
