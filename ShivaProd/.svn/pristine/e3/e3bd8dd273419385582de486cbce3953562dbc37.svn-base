package ar.com.telecom.shiva.base.enumeradores;

public enum TipoCobroHiloEnum {
	
	CHICO("Chico"),
	GRANDE("Grande");
	
	String descripcion;
	
	private TipoCobroHiloEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	/**
	 * 
	 * @return
	 */
	public String descripcion() {
	    return this.descripcion;
	}
	
	public static TipoCobroHiloEnum getEnumByName(String name) {
		
		for (TipoCobroHiloEnum tipo : TipoCobroHiloEnum.values()) {
			if (tipo.name().equalsIgnoreCase(name)) {
				return tipo;
			}
		}
		        
		return null; 
	}

}
