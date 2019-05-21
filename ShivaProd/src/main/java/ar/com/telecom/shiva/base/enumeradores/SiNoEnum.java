package ar.com.telecom.shiva.base.enumeradores;

public enum SiNoEnum {

	SI("SI","S","Y"),
	NO("NO","N","N");

	String descripcion;
	String descripcionCorta;
	String descripcionCortaIngles;
	
	private SiNoEnum(String descripcion, String descripcionCorta, String descripcionCortaIngles) {
		this.descripcion = descripcion;
	    this.descripcionCorta = descripcionCorta;
	    this.descripcionCortaIngles = descripcionCortaIngles;
	}
	
	/**
	 * Devuelve el enumerador segun la descripcion
	 * @param descripcion
	 * @return
	 */
	public static SiNoEnum getEnumByDescripcion(String descripcion){
		if(SI.descripcion.equalsIgnoreCase(descripcion)){
			return SI;
		} else if (NO.descripcion.equalsIgnoreCase(descripcion)) {
			return NO;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Devuelve el enumerador segun la descripcion corta
	 * @param descripcion
	 * @return
	 */
	public static SiNoEnum getEnumByDescripcionCorta(String descripcion){
		if(SI.descripcionCorta.equalsIgnoreCase(descripcion)){
			return SI;
		} else if (NO.descripcionCorta.equalsIgnoreCase(descripcion)) {
			return NO;
		} else {
			return null;
		}
	}
	
	/**
	 * Devuelve el enumerador segun la descripcion corta en ingles
	 * @param descripcionCortaIngles
	 * @return
	 */
	public static SiNoEnum getEnumByDescripcionCortaIngles(String descripcionCortaIngles){
		if(SI.descripcionCorta.equalsIgnoreCase(descripcionCortaIngles)){
			return SI;
		} else if (NO.descripcionCorta.equalsIgnoreCase(descripcionCortaIngles)) {
			return NO;
		} else {
			return null;
		}
	}
	
	/**
	 * Devuelve el boolean
	 * @return
	 */
	public boolean getEnum(){
		if(SI.descripcion.equalsIgnoreCase(this.descripcion)){
			return true;
		}
		
		return false;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static SiNoEnum getEnumByName(String name) {
		for (SiNoEnum enumerador : SiNoEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null;
	}
	
	public String getDescripcion() {
	    return this.descripcion;
	}
	
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	
	public String getDescripcionCortaIngles() {
		return descripcionCortaIngles;
	}
}

