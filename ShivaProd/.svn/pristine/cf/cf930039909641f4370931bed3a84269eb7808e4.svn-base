package ar.com.telecom.shiva.base.enumeradores;

public enum ImprimirBoletaEstadoEnum {

	SI("SI"),
	NO("NO"),
	ERR("ERROR"),
	VACIO("-");

	String descripcion;
	
	/**
	 * 
	 * @param descripcion
	 */
	private ImprimirBoletaEstadoEnum(String descripcion) {
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
	public static ImprimirBoletaEstadoEnum getEnum(String descripcion) {
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

