package ar.com.telecom.shiva.base.enumeradores;

public enum ReintegroEnum {

	REINTEGRO("Reintegro"),
	REINTEGRO_PROX_FACT("Reintegro a próxima factura");

	String descripcion;
	private ReintegroEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Devuelve el enumerador segun la descripcion
	 * @param descripcion
	 * @return
	 */
	public static ReintegroEnum getEnumByDescripcion(String descripcion){
		if(REINTEGRO.descripcion.equalsIgnoreCase(descripcion)){
			return REINTEGRO;
		} else if (REINTEGRO_PROX_FACT.descripcion.equalsIgnoreCase(descripcion)) {
			return REINTEGRO_PROX_FACT;
		} else {
			return null;
		}
	}

	/**
	 * Devuelve el enumerador segun el codigo
	 * @param descripcion
	 * @return
	 */
	public static ReintegroEnum getEnumByName(String descripcion){
		if(REINTEGRO.name().equalsIgnoreCase(descripcion)){
			return REINTEGRO;
		} else if (REINTEGRO_PROX_FACT.name().equalsIgnoreCase(descripcion)) {
			return REINTEGRO_PROX_FACT;
		} else {
			return null;
		}
	}
	
	public String getDescripcion() {
	    return this.descripcion;
	}
	
	public String getName() {
	    return this.name();
	}
}

