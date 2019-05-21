package ar.com.telecom.shiva.base.enumeradores;

public enum TipoReglaCobroEnum {
	
	CARGO("Cargo"),
	COMPROBANTE("Comprobante");
	
	private TipoReglaCobroEnum(String descripcion) {
		Descripcion = descripcion;
	}

	
	String Descripcion;

	
	public String getDescripcion() {
		return Descripcion;
	}
	
	public static TipoReglaCobroEnum getEnumByName(String name) {
		for (TipoReglaCobroEnum regla : TipoReglaCobroEnum.values()) {
			if (regla.name().equalsIgnoreCase(name)) {
				return regla;
			}
		}
		return null;
	}

}
