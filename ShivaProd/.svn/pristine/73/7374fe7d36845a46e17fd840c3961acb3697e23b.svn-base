package ar.com.telecom.shiva.base.enumeradores;


public enum TipoIdReversionPadreEnum {
	
	SUGERIDO("SUGERIDO", "Sugerido"),
	OTRO("OTRO", "Otro");

	String descripcion;
	String descripcionCorta;
	
	private TipoIdReversionPadreEnum(String descripcion, String descripcionCorta) {
	    this.descripcion = descripcion;
	    this.descripcionCorta = descripcionCorta;
	}
	
	public String getDescripcion() {
	    return this.descripcion;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	
	/**
	 * 
	 * @param descripcionCorta
	 * @return
	 */
	public static TipoIdReversionPadreEnum getEnumByDescripcionCorta(String descripcionCorta){
		
		for (TipoIdReversionPadreEnum item : TipoIdReversionPadreEnum.values()) {
			if (item.getDescripcionCorta().equals(descripcionCorta)) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param descripcionCorta
	 * @return
	 */
	public static TipoIdReversionPadreEnum getEnumByDescripcion(String descripcion){
		
		for (TipoIdReversionPadreEnum item : TipoIdReversionPadreEnum.values()) {
			if (item.getDescripcion().equals(descripcion)) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static TipoIdReversionPadreEnum getEnumByName(String name){
		for (TipoIdReversionPadreEnum item : TipoIdReversionPadreEnum.values()) {
			if (item.name().equals(name)) {
				return item;
			}
		}
		return null;
	}
	
}
