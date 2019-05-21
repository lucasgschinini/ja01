package ar.com.telecom.shiva.base.enumeradores;


public enum EstadoReglaConciliacionEnum {
	
	ACTIVO("ACTIVO"),
	INACTIVO("INACTIVO");

	int id;
	String descripcion;
	
	private EstadoReglaConciliacionEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
	    return this.descripcion;
	}
	
	public static EstadoReglaConciliacionEnum getEnumByDescripcionEstadoReglaConciliacion(String estadoReglaConciliacionDesc) {
		
		for (EstadoReglaConciliacionEnum estadoReglaConciliacion : EstadoReglaConciliacionEnum.values()) {
			if (estadoReglaConciliacion.getDescripcion().equalsIgnoreCase(estadoReglaConciliacionDesc)) {
				return estadoReglaConciliacion;
			}
		}
		        
		return null; 
	}
}
