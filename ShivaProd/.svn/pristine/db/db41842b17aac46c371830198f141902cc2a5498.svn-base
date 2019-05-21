package ar.com.telecom.shiva.base.enumeradores;

public enum EnviarMailBoletaEstadoEnum {

	SI("SI"),
	NO("NO"),
	ERR("ERROR"),
	VACIO("-");

	String descripcion;
	
	/**
	 * 
	 * @param descripcion
	 */
	private EnviarMailBoletaEstadoEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDescripcion() {
	    return this.descripcion;
	}
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static EnviarMailBoletaEstadoEnum getEnum(String descripcion) {
		if (SI.descripcion.equalsIgnoreCase(descripcion)) {
			return SI;
		} else if (NO.descripcion.equalsIgnoreCase(descripcion)) {
			return NO;
		} else if (VACIO.descripcion.equalsIgnoreCase(descripcion)) {
			return VACIO;
		} else {
			return ERR;
		}
	}
}

