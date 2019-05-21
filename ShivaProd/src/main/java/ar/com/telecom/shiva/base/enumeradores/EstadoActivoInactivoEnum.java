package ar.com.telecom.shiva.base.enumeradores;


public enum EstadoActivoInactivoEnum {
	
	ACTIVO("Activo"),
	INACTIVO("Inactivo");
	
	String descripcion;
	
	/**
	 * 
	 * @param descripcion
	 */
	private EstadoActivoInactivoEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	/**
	 * 
	 * @return
	 */
	public String descripcion() {
	    return this.descripcion;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static EstadoActivoInactivoEnum getEnumByName(String name) {
		
		for (EstadoActivoInactivoEnum estado : EstadoActivoInactivoEnum.values()) {
			if (estado.name().equals(name)) {
				return estado;
			}
		}
		        
		return null; 
	}
	
}
