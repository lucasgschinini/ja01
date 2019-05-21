package ar.com.telecom.shiva.base.enumeradores;

public enum TipoResultadoEnum {

	OK("OK", "OK", "S", "Ok"),
	NOK("NOK", "NOK", "A", ""),
	ERROR("ER", "ERR", "E", "Error"),
	ERROR_SERVICIO("ER", "ERX", "X","Error de servicio"),
	WRN("WR", "WRN", "W", "Warning");
	
	String descripcionMic;
	String descripcionCalipso;
	String descripcionSap;
	String descripcion;
	
	/**
	 * 
	 * @param descripcionMic
	 * @param descripcionCalipso
	 * @param descripcionSap
	 * @param descripcion
	 */
	private TipoResultadoEnum(String descripcionMic, String descripcionCalipso, String descripcionSap, String descripcion) {

		this.descripcionMic = descripcionMic;
		this.descripcionCalipso = descripcionCalipso;
		this.descripcionSap = descripcionSap;
		this.descripcion = descripcion;
	}
	
	/**
	 * Devuelve el enumerador segun la descripcion
	 * @param descripcion
	 * @return
	 */
	public static TipoResultadoEnum getEnumByDescripcionMic(String descripcion){
		for (TipoResultadoEnum enumerador : TipoResultadoEnum.values()) {
			if (enumerador.descripcionMic.equalsIgnoreCase(descripcion)) {
				return enumerador;
			}
		}
		        
		return null; 
	}
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static TipoResultadoEnum getEnumByDescripcionCalipso(String descripcion){
		for (TipoResultadoEnum enumerador : TipoResultadoEnum.values()) {
			if (enumerador.descripcionCalipso.equalsIgnoreCase(descripcion)) {
				return enumerador;
			}
		}
		        
		return null; 
	}
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static TipoResultadoEnum getEnumByDescripcionSap(String descripcion){
		for (TipoResultadoEnum enumerador : TipoResultadoEnum.values()) {
			if (enumerador.descripcionSap.equalsIgnoreCase(descripcion)) {
				return enumerador;
			}
		}
		        
		return null; 
	}

	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static TipoResultadoEnum getEnumByDescripcion(String descripcion){
		for (TipoResultadoEnum enumerador : TipoResultadoEnum.values()) {
			if (enumerador.descripcion.equalsIgnoreCase(descripcion)) {
				return enumerador;
			}
		}
		        
		return null; 
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static TipoResultadoEnum getEnumByName(String name){
		for (TipoResultadoEnum enumerador : TipoResultadoEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		        
		return null; 
	}

	/**
	 * 
	 * @return
	 */
	public String getDescripcionMic() {
		return descripcionMic;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescripcionCalipso() {
		return descripcionCalipso;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDescripcionSap() {
		return descripcionSap;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

}
