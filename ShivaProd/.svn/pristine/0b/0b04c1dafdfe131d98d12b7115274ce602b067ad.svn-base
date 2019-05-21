package ar.com.telecom.shiva.base.enumeradores;

public enum EstadoProcesamientoHilosEnum {
	
	EN_CURSO("En curso"),
	FINALIZADO("Finalizado"),
	ERROR("Error");
	
	String descripcion;
	
	private EstadoProcesamientoHilosEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	/**
	 * 
	 * @return
	 */
	public String descripcion() {
	    return this.descripcion;
	}
	
	public static EstadoProcesamientoHilosEnum getEnumByName(String name) {
		
		for (EstadoProcesamientoHilosEnum estadoProcesamiento : EstadoProcesamientoHilosEnum.values()) {
			if (estadoProcesamiento.name().equalsIgnoreCase(name)) {
				return estadoProcesamiento;
			}
		}
		        
		return null; 
	}
	

}
