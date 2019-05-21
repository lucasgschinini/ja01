package ar.com.telecom.shiva.base.enumeradores;


public enum EstadoGestorEnum {
	
	ACTIVO("Activo"),
	INACTIVO("Inactivo");
	
	String descripcion;
	
	private EstadoGestorEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}

	public static EstadoGestorEnum getEnumByName(String name) {
		
		for (EstadoGestorEnum estado : EstadoGestorEnum.values()) {
			if (estado.name().equals(name)) {
				return estado;
			}
		}
		        
		return null; 
	}
	
}
