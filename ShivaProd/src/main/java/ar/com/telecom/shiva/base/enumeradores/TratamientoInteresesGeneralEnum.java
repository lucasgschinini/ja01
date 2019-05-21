package ar.com.telecom.shiva.base.enumeradores;

public enum TratamientoInteresesGeneralEnum {

	T("Traslado "),
	B("Bonifica"),
	S("Sin Calculo");

	String descripcion;
	
	public String getDescripcion() {
	    return this.descripcion;
	}
	
	private TratamientoInteresesGeneralEnum(String descripcion) {
	    this.descripcion = descripcion;
	}

	public static TratamientoInteresesGeneralEnum getEnumByName(String name) {
		for (TratamientoInteresesGeneralEnum tipo : TratamientoInteresesGeneralEnum.values()) {
			if (tipo.name().equalsIgnoreCase(name)) {
				return tipo;
			}
		}
		return null; 
	}

}