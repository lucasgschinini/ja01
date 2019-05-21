package ar.com.telecom.shiva.base.enumeradores;



public enum TipoOperacionCargoEnum {
	
	CARGO("Cargo"),
	DESCUENTO("Descuento");

	String descripcion;
	
	private TipoOperacionCargoEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}

	public static TipoOperacionCargoEnum getEnumByName(String cadena){
		for (TipoOperacionCargoEnum en : TipoOperacionCargoEnum.values()) {
			if (en.name().equalsIgnoreCase(cadena)){
				return en;
			}
		}
		return null;
		
	}
	
	public static TipoOperacionCargoEnum getEnumByDescripcion(String descripcion){
		for (TipoOperacionCargoEnum en : TipoOperacionCargoEnum.values()) {
			if (en.descripcion().equalsIgnoreCase(descripcion)){
				return en;
			}
		}
		return null;
		
	}
}
