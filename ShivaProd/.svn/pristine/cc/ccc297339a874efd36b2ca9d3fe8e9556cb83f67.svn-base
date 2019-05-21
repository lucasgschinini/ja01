package ar.com.telecom.shiva.base.enumeradores;


public enum TipoExcepcionEnum {
	SHV("SHIVA");
	
	String descripcion;
	
	private TipoExcepcionEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDescripcion() {
	    return this.descripcion;
	}

	public static TipoExcepcionEnum getEnumByName(String name) {
		for (TipoExcepcionEnum enumerado : TipoExcepcionEnum.values()) {
			if (enumerado.name().equalsIgnoreCase(name)) {
				return enumerado;
			}
		}
		return null;
	}
}
